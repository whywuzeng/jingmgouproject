package com.ab.view.cropimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Handler;
import com.ab.util.AbFileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class CropImage
{
  public File FILE_LOCAL = null;
  private Bitmap mBitmap;
  private Context mContext;
  public HighlightView mCrop;
  private Handler mHandler;
  private CropImageView mImageView;
  Runnable mRunFaceDetection = new Runnable()
  {
    FaceDetector.Face[] mFaces = new FaceDetector.Face[3];
    Matrix mImageMatrix;
    int mNumFaces;
    float mScale = 1.0F;

    private void handleFace(FaceDetector.Face paramAnonymousFace)
    {
      Object localObject = new PointF();
      int i = (int)(paramAnonymousFace.eyesDistance() * this.mScale) * 2;
      paramAnonymousFace.getMidPoint((PointF)localObject);
      ((PointF)localObject).x *= this.mScale;
      ((PointF)localObject).y *= this.mScale;
      int j = (int)((PointF)localObject).x;
      int k = (int)((PointF)localObject).y;
      paramAnonymousFace = new HighlightView(CropImage.this.mImageView);
      localObject = new Rect(0, 0, CropImage.this.mBitmap.getWidth(), CropImage.this.mBitmap.getHeight());
      RectF localRectF = new RectF(j, k, j, k);
      localRectF.inset(-i, -i);
      if (localRectF.left < 0.0F)
        localRectF.inset(-localRectF.left, -localRectF.left);
      if (localRectF.top < 0.0F)
        localRectF.inset(-localRectF.top, -localRectF.top);
      if (localRectF.right > ((Rect)localObject).right)
        localRectF.inset(localRectF.right - ((Rect)localObject).right, localRectF.right - ((Rect)localObject).right);
      if (localRectF.bottom > ((Rect)localObject).bottom)
        localRectF.inset(localRectF.bottom - ((Rect)localObject).bottom, localRectF.bottom - ((Rect)localObject).bottom);
      paramAnonymousFace.setup(this.mImageMatrix, (Rect)localObject, localRectF, false, true);
      CropImage.this.mImageView.add(paramAnonymousFace);
    }

    private void makeDefault()
    {
      HighlightView localHighlightView = new HighlightView(CropImage.this.mImageView);
      int k = CropImage.this.mBitmap.getWidth();
      int j = CropImage.this.mBitmap.getHeight();
      Rect localRect = new Rect(0, 0, k, j);
      int i = Math.min(k, j) * 4 / 5;
      k = (k - i) / 2;
      j = (j - i) / 2;
      RectF localRectF = new RectF(k, j, k + i, j + i);
      localHighlightView.setup(this.mImageMatrix, localRect, localRectF, false, true);
      CropImage.this.mImageView.add(localHighlightView);
    }

    private Bitmap prepareBitmap()
    {
      if (CropImage.this.mBitmap == null)
        return null;
      if (CropImage.this.mBitmap.getWidth() > 256)
        this.mScale = (256.0F / CropImage.this.mBitmap.getWidth());
      Matrix localMatrix = new Matrix();
      localMatrix.setScale(this.mScale, this.mScale);
      return Bitmap.createBitmap(CropImage.this.mBitmap, 0, 0, CropImage.this.mBitmap.getWidth(), CropImage.this.mBitmap.getHeight(), localMatrix, true);
    }

    public void run()
    {
      this.mImageMatrix = CropImage.this.mImageView.getImageMatrix();
      Bitmap localBitmap = prepareBitmap();
      this.mScale = (1.0F / this.mScale);
      if (localBitmap != null)
        this.mNumFaces = new FaceDetector(localBitmap.getWidth(), localBitmap.getHeight(), this.mFaces.length).findFaces(localBitmap, this.mFaces);
      if ((localBitmap != null) && (localBitmap != CropImage.this.mBitmap))
        localBitmap.recycle();
      CropImage.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          CropImage localCropImage = CropImage.this;
          if (CropImage.1.this.mNumFaces > 1);
          for (boolean bool = true; ; bool = false)
          {
            localCropImage.mWaitingToPick = bool;
            CropImage.1.this.makeDefault();
            CropImage.this.mImageView.invalidate();
            if (CropImage.this.mImageView.mHighlightViews.size() > 0)
            {
              CropImage.this.mCrop = ((HighlightView)CropImage.this.mImageView.mHighlightViews.get(0));
              CropImage.this.mCrop.setFocus(true);
            }
            return;
          }
        }
      });
    }
  };
  public boolean mSaving;
  public boolean mWaitingToPick;

  public CropImage(Context paramContext, CropImageView paramCropImageView, Handler paramHandler)
  {
    this.mContext = paramContext;
    this.mImageView = paramCropImageView;
    this.mImageView.setCropImage(this);
    this.mHandler = paramHandler;
    this.FILE_LOCAL = new File(AbFileUtil.getFullImageDownPathDir());
    if (!this.FILE_LOCAL.exists())
      this.FILE_LOCAL.mkdirs();
  }

  private Bitmap onSaveClicked(Bitmap paramBitmap)
  {
    if (this.mSaving);
    while (this.mCrop == null)
      return paramBitmap;
    this.mSaving = true;
    Rect localRect = this.mCrop.getCropRect();
    Bitmap localBitmap = Bitmap.createBitmap(360, 360, Bitmap.Config.RGB_565);
    new Canvas(localBitmap).drawBitmap(paramBitmap, localRect, new Rect(0, 0, 360, 360), null);
    return localBitmap;
  }

  private void startFaceDetection()
  {
    if (((Activity)this.mContext).isFinishing())
      return;
    new Thread(new BackgroundJob("", new Runnable()
    {
      public void run()
      {
        final CountDownLatch localCountDownLatch = new CountDownLatch(1);
        final Bitmap localBitmap = CropImage.this.mBitmap;
        CropImage.this.mHandler.post(new Runnable()
        {
          public void run()
          {
            if ((localBitmap != CropImage.this.mBitmap) && (localBitmap != null))
            {
              CropImage.this.mImageView.setImageBitmapResetBase(localBitmap, true);
              CropImage.this.mBitmap.recycle();
              CropImage.this.mBitmap = localBitmap;
            }
            if (CropImage.this.mImageView.getScale() == 1.0F)
              CropImage.this.mImageView.center(true, true);
            localCountDownLatch.countDown();
          }
        });
        try
        {
          localCountDownLatch.await();
          CropImage.this.mRunFaceDetection.run();
          return;
        }
        catch (Exception localException)
        {
          throw new RuntimeException(localException);
        }
      }
    }
    , this.mHandler)).start();
  }

  public void crop(Bitmap paramBitmap)
  {
    this.mBitmap = paramBitmap;
    startFaceDetection();
  }

  public Bitmap cropAndSave()
  {
    Bitmap localBitmap = onSaveClicked(this.mBitmap);
    this.mImageView.mHighlightViews.clear();
    return localBitmap;
  }

  public Bitmap cropAndSave(Bitmap paramBitmap)
  {
    paramBitmap = onSaveClicked(paramBitmap);
    this.mImageView.mHighlightViews.clear();
    return paramBitmap;
  }

  public void cropCancel()
  {
    this.mImageView.mHighlightViews.clear();
    this.mImageView.invalidate();
  }

  public String saveToLocal(Bitmap paramBitmap)
  {
    String str = System.currentTimeMillis() + ".jpg";
    str = this.FILE_LOCAL + File.separator + str;
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(str);
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 75, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      return str;
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    return null;
  }

  public void startRotate(final float paramFloat)
  {
    if (((Activity)this.mContext).isFinishing())
      return;
    new Thread(new BackgroundJob("", new Runnable()
    {
      public void run()
      {
        CropImage.this.mHandler.post(new Runnable()
        {
          public void run()
          {
            try
            {
              Object localObject = new Matrix();
              ((Matrix)localObject).setRotate(this.val$degrees);
              localObject = Bitmap.createBitmap(CropImage.this.mBitmap, 0, 0, CropImage.this.mBitmap.getWidth(), CropImage.this.mBitmap.getHeight(), (Matrix)localObject, false);
              CropImage.this.mBitmap = ((Bitmap)localObject);
              CropImage.this.mImageView.resetView((Bitmap)localObject);
              if (CropImage.this.mImageView.mHighlightViews.size() > 0)
              {
                CropImage.this.mCrop = ((HighlightView)CropImage.this.mImageView.mHighlightViews.get(0));
                CropImage.this.mCrop.setFocus(true);
              }
              label148: this.val$latch.countDown();
              return;
            }
            catch (Exception localException)
            {
              break label148;
            }
          }
        });
        try
        {
          this.val$latch.await();
          return;
        }
        catch (Exception localException)
        {
          throw new RuntimeException(localException);
        }
      }
    }
    , this.mHandler)).start();
  }

  public class BackgroundJob
    implements Runnable
  {
    private Handler mHandler;
    private Runnable mJob;
    private String message;

    public BackgroundJob(String paramRunnable, Runnable paramHandler, Handler arg4)
    {
      this.message = paramRunnable;
      this.mJob = paramHandler;
      Object localObject;
      this.mHandler = localObject;
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: new 40	java/util/concurrent/CountDownLatch
      //   3: dup
      //   4: iconst_1
      //   5: invokespecial 43	java/util/concurrent/CountDownLatch:<init>	(I)V
      //   8: astore_1
      //   9: aload_0
      //   10: getfield 32	com/ab/view/cropimage/CropImage$BackgroundJob:mHandler	Landroid/os/Handler;
      //   13: new 11	com/ab/view/cropimage/CropImage$BackgroundJob$1
      //   16: dup
      //   17: aload_0
      //   18: aload_1
      //   19: invokespecial 46	com/ab/view/cropimage/CropImage$BackgroundJob$1:<init>	(Lcom/ab/view/cropimage/CropImage$BackgroundJob;Ljava/util/concurrent/CountDownLatch;)V
      //   22: invokevirtual 52	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   25: pop
      //   26: aload_1
      //   27: invokevirtual 55	java/util/concurrent/CountDownLatch:await	()V
      //   30: aload_0
      //   31: getfield 30	com/ab/view/cropimage/CropImage$BackgroundJob:mJob	Ljava/lang/Runnable;
      //   34: invokeinterface 57 1 0
      //   39: aload_0
      //   40: getfield 32	com/ab/view/cropimage/CropImage$BackgroundJob:mHandler	Landroid/os/Handler;
      //   43: aload_0
      //   44: getfield 32	com/ab/view/cropimage/CropImage$BackgroundJob:mHandler	Landroid/os/Handler;
      //   47: iconst_2
      //   48: invokevirtual 61	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
      //   51: invokevirtual 65	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   54: pop
      //   55: return
      //   56: astore_1
      //   57: new 67	java/lang/RuntimeException
      //   60: dup
      //   61: aload_1
      //   62: invokespecial 70	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
      //   65: athrow
      //   66: astore_1
      //   67: aload_1
      //   68: invokevirtual 73	java/lang/Exception:printStackTrace	()V
      //   71: aload_0
      //   72: getfield 32	com/ab/view/cropimage/CropImage$BackgroundJob:mHandler	Landroid/os/Handler;
      //   75: aload_0
      //   76: getfield 32	com/ab/view/cropimage/CropImage$BackgroundJob:mHandler	Landroid/os/Handler;
      //   79: iconst_2
      //   80: invokevirtual 61	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
      //   83: invokevirtual 65	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   86: pop
      //   87: return
      //   88: astore_1
      //   89: aload_0
      //   90: getfield 32	com/ab/view/cropimage/CropImage$BackgroundJob:mHandler	Landroid/os/Handler;
      //   93: aload_0
      //   94: getfield 32	com/ab/view/cropimage/CropImage$BackgroundJob:mHandler	Landroid/os/Handler;
      //   97: iconst_2
      //   98: invokevirtual 61	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
      //   101: invokevirtual 65	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   104: pop
      //   105: aload_1
      //   106: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   26	30	56	java/lang/Exception
      //   30	39	66	java/lang/Exception
      //   30	39	88	finally
      //   67	71	88	finally
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.cropimage.CropImage
 * JD-Core Version:    0.6.2
 */