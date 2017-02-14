package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import com.ismartgo.app.common.BitmapUtils;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.utils.CameraUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CameraActivity1 extends BaseActivity
  implements Camera.AutoFocusCallback, View.OnClickListener, CompoundButton.OnCheckedChangeListener
{
  public static final Uri IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
  public static String PATH;
  public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
  public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
  private Button btAlbum;
  private Button btBack;
  private Camera camera;
  private String cameraPath;
  private Button cameraPhoto;
  private CheckBox cbFlash;
  private CameraView cv = null;
  private ImageView focusview = null;
  private boolean isPreview = false;
  private boolean isTorch;
  private LinearLayout linearLayoutCamera;
  private Uri photoUri;
  private String picPath;
  private Camera.PictureCallback picture = new Camera.PictureCallback()
  {
    public void onPictureTaken(byte[] paramAnonymousArrayOfByte, Camera paramAnonymousCamera)
    {
      Bitmap localBitmap = null;
      if (paramAnonymousArrayOfByte != null)
      {
        localBitmap = BitmapFactory.decodeByteArray(paramAnonymousArrayOfByte, 0, paramAnonymousArrayOfByte.length);
        paramAnonymousCamera.stopPreview();
      }
      paramAnonymousArrayOfByte = new Matrix();
      paramAnonymousArrayOfByte.postRotate(90.0F);
      paramAnonymousArrayOfByte = Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), paramAnonymousArrayOfByte, false);
      new CameraActivity1.SavePicThread(CameraActivity1.this).execute(new Bitmap[] { paramAnonymousArrayOfByte });
    }
  };
  Camera.ShutterCallback shutter = new Camera.ShutterCallback()
  {
    public void onShutter()
    {
    }
  };
  private boolean takenphoto = false;
  int viewHeight = 0;
  int viewWidth = 0;

  public static Bitmap createBitmap(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    String str = "精明购" + System.currentTimeMillis();
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Typeface localTypeface = Typeface.create("宋体", 1);
    localPaint.setColor(-1);
    localPaint.setTypeface(localTypeface);
    localPaint.setTextSize(24.0F);
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    localCanvas.drawText(str, i - 320, j - 30, localPaint);
    localCanvas.save(31);
    localCanvas.restore();
    return localBitmap;
  }

  private void doPhoto(int paramInt, Intent paramIntent)
  {
    if ((paramInt == 2) && (paramIntent != null))
    {
      this.photoUri = paramIntent.getData();
      if (this.photoUri == null)
      {
        Toast.makeText(this, "选择图片文件出错", 1).show();
        finish();
      }
    }
    else
    {
      return;
    }
    paramIntent = new String[1];
    paramIntent[0] = "_data";
    Object localObject = managedQuery(this.photoUri, paramIntent, null, null, null);
    if (localObject != null)
    {
      ((Cursor)localObject).moveToFirst();
      this.picPath = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndexOrThrow(paramIntent[0]));
      ((Cursor)localObject).close();
    }
    if ((this.picPath != null) && ((this.picPath.endsWith(".png")) || (this.picPath.endsWith(".PNG")) || (this.picPath.endsWith(".jpg")) || (this.picPath.endsWith(".JPG")) || (this.picPath.endsWith(".jpeg")) || (this.picPath.endsWith(".JPEG"))))
    {
      paramIntent = BitmapUtils.scal(this.picPath);
      localObject = new Intent(this, PictureActivity.class);
      ((Intent)localObject).putExtra("path", paramIntent.getAbsolutePath());
      startActivity((Intent)localObject);
      finish();
      return;
    }
    if (!CommonMethod.isEmpty(this.photoUri.getPath()))
    {
      paramIntent = BitmapUtils.scal(this.photoUri.getPath());
      localObject = new Intent(this, PictureActivity.class);
      ((Intent)localObject).putExtra("path", paramIntent.getAbsolutePath());
      startActivity((Intent)localObject);
      finish();
      return;
    }
    Toast.makeText(this, "选择图片文件不正确", 1).show();
    finish();
  }

  private Camera.Size findPreviewSizeByScreen(Camera.Parameters paramParameters)
  {
    if ((this.viewWidth != 0) && (this.viewHeight != 0))
    {
      paramParameters = this.camera;
      paramParameters.getClass();
      return new Camera.Size(paramParameters, Math.max(this.viewWidth, this.viewHeight), Math.min(this.viewWidth, this.viewHeight));
    }
    paramParameters = this.camera;
    paramParameters.getClass();
    return new Camera.Size(paramParameters, CameraUtils.getScreenWH(this).heightPixels, CameraUtils.getScreenWH(this).widthPixels);
  }

  private String getSDPath()
  {
    File localFile = null;
    if (Environment.getExternalStorageState().equals("mounted"))
      localFile = Environment.getExternalStorageDirectory();
    if (localFile != null)
      return localFile.toString();
    return "";
  }

  private void initView()
  {
    this.linearLayoutCamera = ((LinearLayout)findViewById(2131231070));
    this.focusview = ((ImageView)findViewById(2131231071));
    this.cameraPhoto = ((Button)findViewById(2131231066));
    this.cameraPhoto.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (CameraActivity1.this.camera == null)
          return;
        try
        {
          paramAnonymousView = CameraActivity1.this.camera.getParameters();
          if (CameraActivity1.this.isTorch)
            paramAnonymousView.setFlashMode("torch");
          CameraActivity1.this.camera.setParameters(paramAnonymousView);
          CameraActivity1.this.camera.autoFocus(CameraActivity1.this);
          CameraActivity1.this.takenphoto = true;
          return;
        }
        catch (Exception paramAnonymousView)
        {
          while (true)
            paramAnonymousView.printStackTrace();
        }
      }
    });
    this.btBack = ((Button)findViewById(2131231065));
    this.btAlbum = ((Button)findViewById(2131231067));
    this.cbFlash = ((CheckBox)findViewById(2131231069));
    this.cbFlash.setOnCheckedChangeListener(this);
    this.cbFlash.setChecked(false);
    this.btBack.setOnClickListener(this);
    this.btAlbum.setOnClickListener(this);
  }

  private void openCamera()
  {
    this.linearLayoutCamera.removeAllViews();
    this.cv = new CameraView(this);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    this.linearLayoutCamera.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default:
        case 0:
        }
        do
        {
          return false;
          int i = (int)paramAnonymousMotionEvent.getX();
          int j = (int)paramAnonymousMotionEvent.getY();
          CameraActivity1.this.focusview.layout(i - 100, j - 100, i + 100, j + 100);
          CameraActivity1.this.focusview.setVisibility(0);
          CameraActivity1.this.focusview.setBackgroundResource(2130837692);
          CameraActivity1.this.focusview.postInvalidate();
        }
        while (CameraActivity1.this.camera == null);
        CameraActivity1.this.camera.autoFocus(CameraActivity1.this);
        return false;
      }
    });
    this.linearLayoutCamera.addView(this.cv, localLayoutParams);
    this.takenphoto = false;
  }

  private void pickPhoto()
  {
    try
    {
      new Intent();
      Intent localIntent;
      if (Build.VERSION.SDK_INT < 19)
      {
        localIntent = new Intent("android.intent.action.GET_CONTENT");
        localIntent.setType("image/*");
      }
      while (true)
      {
        startActivityForResult(localIntent, 2);
        return;
        localIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  @SuppressLint({"NewApi"})
  public void initCamera()
  {
    if ((this.isPreview) && (this.camera != null))
      this.camera.stopPreview();
    Camera.Parameters localParameters;
    if (this.camera != null)
    {
      localParameters = this.camera.getParameters();
      localParameters.setPictureFormat(256);
      Camera.Size localSize = findPreviewSizeByScreen(localParameters);
      localParameters.setPreviewSize(localSize.width, localSize.height);
      localParameters.setPictureSize(localSize.width, localSize.height);
      localParameters.set("jpeg-quality", 100);
      localParameters.setFocusMode("auto");
    }
    try
    {
      this.camera.setParameters(localParameters);
      this.camera.startPreview();
      this.camera.autoFocus(this);
      this.isPreview = true;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
      doPhoto(paramInt1, paramIntent);
  }

  public void onAutoFocus(boolean paramBoolean, Camera paramCamera)
  {
    if (paramBoolean)
    {
      this.focusview.setBackgroundResource(2130837691);
      if ((this.takenphoto) && (this.camera != null))
        this.camera.takePicture(this.shutter, null, this.picture);
    }
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.isTorch = true;
      return;
    }
    this.isTorch = false;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131231066:
    default:
      return;
    case 2131231065:
      finish();
      return;
    case 2131231067:
    }
    pickPhoto();
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    getWindow().addFlags(128);
    super.onCreate(paramBundle);
    setContentView(2130903097);
    initView();
    openCamera();
  }

  public void saveJpeg(Bitmap paramBitmap)
  {
    String str = getSDPath() + "/temp";
    Object localObject = new File(str);
    if (!((File)localObject).exists())
      ((File)localObject).mkdirs();
    long l = System.currentTimeMillis();
    localObject = "";
    while (true)
    {
      if (((String)localObject).length() >= 4)
      {
        localObject = str + "/" + l + "_" + (String)localObject + ".jpg";
        this.cameraPath = ((String)localObject);
      }
      try
      {
        localObject = new BufferedOutputStream(new FileOutputStream((String)localObject));
        paramBitmap.compress(Bitmap.CompressFormat.JPEG, 90, (OutputStream)localObject);
        ((BufferedOutputStream)localObject).flush();
        ((BufferedOutputStream)localObject).close();
        return;
        localObject = localObject + (int)(Math.random() * 10.0D);
      }
      catch (IOException paramBitmap)
      {
        paramBitmap.printStackTrace();
      }
    }
  }

  class CameraView extends SurfaceView
  {
    private SurfaceHolder holder = null;

    public CameraView(Context arg2)
    {
      super();
      this.holder.setType(3);
      this.holder.addCallback(new SurfaceHolder.Callback()
      {
        public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          CameraActivity1.this.initCamera();
        }

        @SuppressLint({"NewApi"})
        public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
        {
          try
          {
            CameraActivity1.this.camera = Camera.open();
            if (CameraActivity1.this.camera == null)
              return;
          }
          catch (Exception localException)
          {
            while (true)
            {
              CameraActivity1.this.camera = null;
              localException.printStackTrace();
            }
            try
            {
              CameraActivity1.this.camera.setDisplayOrientation(90);
              CameraActivity1.this.camera.setPreviewDisplay(paramAnonymousSurfaceHolder);
              return;
            }
            catch (IOException paramAnonymousSurfaceHolder)
            {
              CameraActivity1.this.camera.release();
              CameraActivity1.this.camera = null;
              paramAnonymousSurfaceHolder.printStackTrace();
            }
          }
        }

        public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
        {
          if (CameraActivity1.this.camera != null)
          {
            CameraActivity1.this.camera.stopPreview();
            CameraActivity1.this.camera.release();
            CameraActivity1.this.camera = null;
          }
        }
      });
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
      CameraActivity1.this.viewWidth = View.MeasureSpec.getSize(paramInt1);
      CameraActivity1.this.viewHeight = View.MeasureSpec.getSize(paramInt2);
      super.onMeasure(View.MeasureSpec.makeMeasureSpec(CameraActivity1.this.viewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(CameraActivity1.this.viewHeight, 1073741824));
    }
  }

  class SavePicThread extends AsyncTask<Bitmap, Void, Void>
  {
    SavePicThread()
    {
    }

    protected Void doInBackground(Bitmap[] paramArrayOfBitmap)
    {
      if (paramArrayOfBitmap[0] != null)
        CameraActivity1.this.saveJpeg(paramArrayOfBitmap[0]);
      return null;
    }

    protected void onPostExecute(Void paramVoid)
    {
      CameraActivity1.this.camera.release();
      CameraActivity1.this.camera = null;
      paramVoid = new Intent(CameraActivity1.this, PictureActivity.class);
      paramVoid.putExtra("path", CameraActivity1.this.cameraPath);
      CameraActivity1.this.startActivity(paramVoid);
      CameraActivity1.this.finish();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.CameraActivity1
 * JD-Core Version:    0.6.2
 */