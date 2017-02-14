package com.linj.camera.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.ismartgo.apppub.R.styleable;

public class TempImageView extends ImageView
  implements Animation.AnimationListener
{
  public static final int NO_ID = -1;
  public static final String TAG = "TempImageView";
  private int mAnimationID = -1;
  private boolean mIsVideo;
  private CameraContainer.TakePictureListener mListener;

  public TempImageView(Context paramContext)
  {
    super(paramContext);
  }

  public TempImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.TempImageView);
    this.mAnimationID = paramContext.getResourceId(0, -1);
    paramContext.recycle();
  }

  public void isVideo(boolean paramBoolean)
  {
    this.mIsVideo = paramBoolean;
  }

  public void onAnimationEnd(Animation paramAnimation)
  {
    setVisibility(8);
    Drawable localDrawable = getDrawable();
    Object localObject = null;
    paramAnimation = localObject;
    if (localDrawable != null)
    {
      paramAnimation = localObject;
      if ((localDrawable instanceof BitmapDrawable))
        paramAnimation = ((BitmapDrawable)localDrawable).getBitmap();
    }
    if (this.mListener != null)
      this.mListener.onAnimtionEnd(paramAnimation, this.mIsVideo);
  }

  public void onAnimationRepeat(Animation paramAnimation)
  {
  }

  public void onAnimationStart(Animation paramAnimation)
  {
    setVisibility(0);
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    super.setImageBitmap(paramBitmap);
  }

  public void setListener(CameraContainer.TakePictureListener paramTakePictureListener)
  {
    this.mListener = paramTakePictureListener;
  }

  public void startAnimation()
  {
    startAnimation(null);
  }

  public void startAnimation(int paramInt)
  {
    this.mAnimationID = paramInt;
    startAnimation();
  }

  public void startAnimation(Animation paramAnimation)
  {
    if (paramAnimation != null)
    {
      paramAnimation.setAnimationListener(this);
      super.startAnimation(paramAnimation);
    }
    while (this.mAnimationID == -1)
      return;
    paramAnimation = AnimationUtils.loadAnimation(getContext(), this.mAnimationID);
    paramAnimation.setAnimationListener(this);
    super.startAnimation(paramAnimation);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.camera.view.TempImageView
 * JD-Core Version:    0.6.2
 */