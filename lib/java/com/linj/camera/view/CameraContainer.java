package com.linj.camera.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.media.ExifInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.linj.FileOperateUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraContainer extends RelativeLayout
  implements CameraOperation
{
  public static final String TAG = "CameraContainer";
  private final Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback()
  {
    public void onAutoFocus(boolean paramAnonymousBoolean, Camera paramAnonymousCamera)
    {
      if (paramAnonymousBoolean)
      {
        CameraContainer.this.mFocusImageView.onFocusSuccess();
        return;
      }
      CameraContainer.this.mFocusImageView.onFocusFailed();
    }
  };
  private String bitmapPath;
  private CameraView mCameraView;
  private DataHandler mDataHandler;
  private FocusImageView mFocusImageView;
  private Handler mHandler;
  private TakePictureListener mListener;
  private long mRecordStartTime;
  private TextView mRecordingInfoTextView;
  private String mSavePath;
  private TempImageView mTempImageView;
  private SimpleDateFormat mTimeFormat;
  private ImageView mWaterMarkImageView;
  private SeekBar mZoomSeekBar;
  private final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener()
  {
    public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
    {
      CameraContainer.this.mCameraView.setZoom(paramAnonymousInt);
      CameraContainer.this.mHandler.removeCallbacksAndMessages(CameraContainer.this.mZoomSeekBar);
      CameraContainer.this.mHandler.postAtTime(new Runnable()
      {
        public void run()
        {
          CameraContainer.this.mZoomSeekBar.setVisibility(8);
        }
      }
      , CameraContainer.this.mZoomSeekBar, SystemClock.uptimeMillis() + 2000L);
    }

    public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
    {
    }

    public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
    {
    }
  };
  private final Camera.PictureCallback pictureCallback = new Camera.PictureCallback()
  {
    public void onPictureTaken(byte[] paramAnonymousArrayOfByte, Camera paramAnonymousCamera)
    {
      if (CameraContainer.this.mSavePath == null)
        throw new RuntimeException("mSavePath is null");
      if (CameraContainer.this.mDataHandler == null)
        CameraContainer.this.mDataHandler = new CameraContainer.DataHandler(CameraContainer.this);
      CameraContainer.this.mDataHandler.setMaxSize(200);
      paramAnonymousArrayOfByte = CameraContainer.this.mDataHandler.save(paramAnonymousArrayOfByte);
      CameraContainer.this.mTempImageView.setListener(CameraContainer.this.mListener);
      CameraContainer.this.mTempImageView.isVideo(false);
      CameraContainer.this.mTempImageView.setImageBitmap(paramAnonymousArrayOfByte);
      CameraContainer.this.mTempImageView.setVisibility(8);
      paramAnonymousCamera.startPreview();
      if (CameraContainer.this.mListener != null)
        CameraContainer.this.mListener.onTakePictureEnd(paramAnonymousArrayOfByte, CameraContainer.this.bitmapPath);
    }
  };
  Runnable recordRunnable = new Runnable()
  {
    public void run()
    {
      if (CameraContainer.this.mCameraView.isRecording())
      {
        long l1 = SystemClock.uptimeMillis();
        long l2 = CameraContainer.this.mRecordStartTime;
        CameraContainer.this.mRecordingInfoTextView.setText(CameraContainer.this.mTimeFormat.format(new Date(l1 - l2)));
        CameraContainer.this.mHandler.postAtTime(this, CameraContainer.this.mRecordingInfoTextView, SystemClock.uptimeMillis() + 500L);
        return;
      }
      CameraContainer.this.mRecordingInfoTextView.setVisibility(8);
    }
  };

  public CameraContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
    this.mHandler = new Handler();
    this.mTimeFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());
    setOnTouchListener(new TouchListener(null));
  }

  private int dip2px(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }

  private void initView(Context paramContext)
  {
    inflate(paramContext, 2130903107, this);
    this.mCameraView = ((CameraView)findViewById(2131231093));
    this.mTempImageView = ((TempImageView)findViewById(2131231094));
    this.mFocusImageView = ((FocusImageView)findViewById(2131231095));
    this.mRecordingInfoTextView = ((TextView)findViewById(2131231096));
    this.mWaterMarkImageView = ((ImageView)findViewById(2131231097));
  }

  private static int readPictureDegree(String paramString)
  {
    try
    {
      int i = new ExifInterface(paramString).getAttributeInt("Orientation", 1);
      switch (i)
      {
      case 4:
      case 5:
      case 7:
      default:
        return 0;
      case 6:
        return 90;
      case 3:
        return 180;
      case 8:
      }
      return 270;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }

  private static Bitmap rotaingImageView(int paramInt, Bitmap paramBitmap)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramInt);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }

  public CameraView.FlashMode getFlashMode()
  {
    return this.mCameraView.getFlashMode();
  }

  public int getMaxZoom()
  {
    return this.mCameraView.getMaxZoom();
  }

  public int getZoom()
  {
    return this.mCameraView.getZoom();
  }

  public void setFlashMode(CameraView.FlashMode paramFlashMode)
  {
    this.mCameraView.setFlashMode(paramFlashMode);
  }

  public void setRootPath(String paramString)
  {
    this.mSavePath = paramString;
  }

  public void setWaterMark()
  {
    if (this.mWaterMarkImageView.getVisibility() == 0)
    {
      this.mWaterMarkImageView.setVisibility(8);
      return;
    }
    this.mWaterMarkImageView.setVisibility(0);
  }

  public void setZoom(int paramInt)
  {
    this.mCameraView.setZoom(paramInt);
  }

  public boolean startRecord()
  {
    boolean bool = false;
    this.mRecordStartTime = SystemClock.uptimeMillis();
    this.mRecordingInfoTextView.setVisibility(0);
    this.mRecordingInfoTextView.setText("00:00");
    if (this.mCameraView.startRecord())
    {
      this.mHandler.postAtTime(this.recordRunnable, this.mRecordingInfoTextView, SystemClock.uptimeMillis() + 1000L);
      bool = true;
    }
    return bool;
  }

  public Bitmap stopRecord()
  {
    this.mRecordingInfoTextView.setVisibility(8);
    Bitmap localBitmap = this.mCameraView.stopRecord();
    if (localBitmap != null)
    {
      this.mTempImageView.setListener(this.mListener);
      this.mTempImageView.isVideo(true);
      this.mTempImageView.setImageBitmap(localBitmap);
      this.mTempImageView.startAnimation(2130968599);
    }
    return localBitmap;
  }

  public Bitmap stopRecord(TakePictureListener paramTakePictureListener)
  {
    this.mListener = paramTakePictureListener;
    return stopRecord();
  }

  public void switchCamera()
  {
    this.mCameraView.switchCamera();
  }

  public void switchMode(int paramInt)
  {
    this.mZoomSeekBar.setProgress(paramInt);
    this.mCameraView.setZoom(paramInt);
    this.mCameraView.onFocus(new Point(getWidth() / 2, getHeight() / 2), this.autoFocusCallback);
    this.mWaterMarkImageView.setVisibility(8);
  }

  public void takePicture()
  {
    takePicture(this.pictureCallback, this.mListener);
  }

  public void takePicture(Camera.PictureCallback paramPictureCallback, TakePictureListener paramTakePictureListener)
  {
    this.mCameraView.takePicture(paramPictureCallback, paramTakePictureListener);
  }

  public void takePicture(TakePictureListener paramTakePictureListener)
  {
    this.mListener = paramTakePictureListener;
    takePicture(this.pictureCallback, this.mListener);
  }

  private final class DataHandler
  {
    private String mImageFolder = FileOperateUtil.getFolderPath(CameraContainer.this.getContext(), 1, CameraContainer.this.mSavePath);
    private String mThumbnailFolder = FileOperateUtil.getFolderPath(CameraContainer.this.getContext(), 2, CameraContainer.this.mSavePath);
    private int maxSize = 200;

    public DataHandler()
    {
      this$1 = new File(this.mImageFolder);
      if (!CameraContainer.this.exists())
        CameraContainer.this.mkdirs();
      this$1 = new File(this.mThumbnailFolder);
      if (!CameraContainer.this.exists())
        CameraContainer.this.mkdirs();
    }

    private Bitmap getBitmapWithWaterMark(Bitmap paramBitmap)
    {
      if (CameraContainer.this.mWaterMarkImageView.getVisibility() != 0)
        return paramBitmap;
      Bitmap localBitmap1 = drawableToBitmap(CameraContainer.this.mWaterMarkImageView.getDrawable());
      int i = paramBitmap.getWidth();
      int j = paramBitmap.getHeight();
      int k = localBitmap1.getWidth();
      int m = localBitmap1.getHeight();
      Bitmap localBitmap2 = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap2);
      localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
      localCanvas.drawBitmap(localBitmap1, i - k + 5, j - m + 5, null);
      localCanvas.save(31);
      localCanvas.restore();
      paramBitmap.recycle();
      localBitmap1.recycle();
      return localBitmap2;
    }

    public ByteArrayOutputStream compress(Bitmap paramBitmap)
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
      int i = 99;
      while (true)
      {
        if (localByteArrayOutputStream.toByteArray().length / 1024 <= this.maxSize);
        do
        {
          return localByteArrayOutputStream;
          i -= 3;
        }
        while (i < 0);
        Log.i("CameraContainer", localByteArrayOutputStream.toByteArray().length / 1024);
        localByteArrayOutputStream.reset();
        paramBitmap.compress(Bitmap.CompressFormat.JPEG, i, localByteArrayOutputStream);
      }
    }

    public Bitmap drawableToBitmap(Drawable paramDrawable)
    {
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      if (paramDrawable.getOpacity() != -1);
      for (Object localObject = Bitmap.Config.ARGB_8888; ; localObject = Bitmap.Config.RGB_565)
      {
        localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
        Canvas localCanvas = new Canvas((Bitmap)localObject);
        paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
        paramDrawable.draw(localCanvas);
        return localObject;
      }
    }

    public Bitmap save(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte != null)
      {
        Bitmap localBitmap = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
        String str = FileOperateUtil.createFileNmae(".jpg");
        paramArrayOfByte = this.mImageFolder + File.separator + str;
        new StringBuilder(String.valueOf(this.mThumbnailFolder)).append(File.separator).append(str).toString();
        CameraContainer.this.bitmapPath = paramArrayOfByte;
        int i = CameraContainer.readPictureDegree(paramArrayOfByte);
        System.out.println("��ת�Ƕȣ� " + i);
        localBitmap = CameraContainer.rotaingImageView(i, localBitmap);
        paramArrayOfByte = new File(paramArrayOfByte);
        try
        {
          paramArrayOfByte = new FileOutputStream(paramArrayOfByte);
          paramArrayOfByte.write(compress(localBitmap).toByteArray());
          paramArrayOfByte.flush();
          paramArrayOfByte.close();
          return localBitmap;
        }
        catch (Exception paramArrayOfByte)
        {
          Log.e("CameraContainer", paramArrayOfByte.toString());
          Toast.makeText(CameraContainer.this.getContext(), "����������ʧ��", 0).show();
        }
      }
      while (true)
      {
        return null;
        Toast.makeText(CameraContainer.this.getContext(), "����ʧ�ܣ�������", 0).show();
      }
    }

    public void setMaxSize(int paramInt)
    {
      this.maxSize = paramInt;
    }
  }

  public static abstract interface TakePictureListener
  {
    public abstract void onAnimtionEnd(Bitmap paramBitmap, boolean paramBoolean);

    public abstract void onTakePictureEnd(Bitmap paramBitmap, String paramString);
  }

  private final class TouchListener
    implements View.OnTouchListener
  {
    private static final int MODE_INIT = 0;
    private static final int MODE_ZOOM = 1;
    private int mode = 0;
    private float startDis;

    private TouchListener()
    {
    }

    private float distance(MotionEvent paramMotionEvent)
    {
      float f1 = paramMotionEvent.getX(1) - paramMotionEvent.getX(0);
      float f2 = paramMotionEvent.getY(1) - paramMotionEvent.getY(0);
      return (float)Math.sqrt(f1 * f1 + f2 * f2);
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      switch (paramMotionEvent.getAction() & 0xFF)
      {
      case 3:
      case 4:
      default:
      case 0:
      case 5:
      case 2:
        float f;
        do
        {
          do
          {
            do
            {
              return true;
              this.mode = 0;
              return true;
            }
            while (CameraContainer.this.mZoomSeekBar == null);
            CameraContainer.this.mHandler.removeCallbacksAndMessages(CameraContainer.this.mZoomSeekBar);
            CameraContainer.this.mZoomSeekBar.setVisibility(0);
            this.mode = 1;
            this.startDis = distance(paramMotionEvent);
            return true;
          }
          while ((this.mode != 1) || (paramMotionEvent.getPointerCount() < 2));
          f = distance(paramMotionEvent);
          i = (int)((f - this.startDis) / 10.0F);
        }
        while ((i < 1) && (i > -1));
        int j = CameraContainer.this.mCameraView.getZoom() + i;
        int i = j;
        if (j > CameraContainer.this.mCameraView.getMaxZoom())
          i = CameraContainer.this.mCameraView.getMaxZoom();
        j = i;
        if (i < 0)
          j = 0;
        CameraContainer.this.mCameraView.setZoom(j);
        CameraContainer.this.mZoomSeekBar.setProgress(j);
        this.startDis = f;
        return true;
      case 1:
      }
      if (this.mode != 1)
      {
        paramView = new Point((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
        CameraContainer.this.mCameraView.onFocus(paramView, CameraContainer.this.autoFocusCallback);
        CameraContainer.this.mFocusImageView.startFocus(paramView);
        return true;
      }
      CameraContainer.this.mHandler.postAtTime(new Runnable()
      {
        public void run()
        {
          CameraContainer.this.mZoomSeekBar.setVisibility(8);
        }
      }
      , CameraContainer.this.mZoomSeekBar, SystemClock.uptimeMillis() + 2000L);
      return true;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.camera.view.CameraContainer
 * JD-Core Version:    0.6.2
 */