package com.ab.view.sliding;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.ab.global.AbAppData;

public class AbSlidingButton extends CheckBox
{
  private boolean D = AbAppData.DEBUG;
  private String TAG = AbSlidingButton.class.getSimpleName();
  private Bitmap btnBottom;
  private Bitmap btnFrame;
  private Bitmap btnMask;
  private Context context;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 0:
      }
      AbSlidingButton.this.mRealPos = AbSlidingButton.this.getRealPos(((Float)paramAnonymousMessage.obj).floatValue());
      AbSlidingButton.this.mLastBtnPos = ((Float)paramAnonymousMessage.obj).floatValue();
      AbSlidingButton.this.invalidate();
    }
  };
  private boolean isChecked;
  private int mAlpha;
  private float mAnimatedVelocity;
  private boolean mAnimating;
  private float mAnimationPosition;
  private Bitmap mBtnNormal;
  private float mBtnOffPos;
  private float mBtnOnPos;
  private float mBtnPos;
  private Bitmap mBtnPressed;
  private float mBtnWidth;
  private Bitmap mCurBtnPic;
  private float mExtendOffsetY;
  private float mFirstDownX;
  private float mLastBtnPos;
  private float mMaskHeight;
  private float mMaskWidth;
  private boolean mMoveEvent;
  private Paint mPaint;
  private float mRealPos;
  private RectF mSaveLayerRectF;
  private PorterDuffXfermode mXfermode;
  private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

  public AbSlidingButton(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public AbSlidingButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  private float getRealPos(float paramFloat)
  {
    return paramFloat - this.mBtnWidth / 2.0F;
  }

  private void init(Context paramContext)
  {
    this.context = paramContext;
    this.mAlpha = 255;
    this.isChecked = false;
    this.mPaint = new Paint();
    this.mPaint.setColor(-1);
    this.mAnimatedVelocity = ((int)(0.5F + 350.0F * getResources().getDisplayMetrics().density));
  }

  private void moveView(float paramFloat)
  {
    moveView(paramFloat, false);
  }

  private void moveView(float paramFloat, boolean paramBoolean)
  {
    if (this.handler != null)
      this.handler.obtainMessage(0, Float.valueOf(paramFloat)).sendToTarget();
  }

  private void moveViewToTarget()
  {
    moveView(this.mBtnPos);
    if (this.mBtnPos == this.mBtnOnPos)
      if (!this.isChecked)
      {
        this.isChecked = true;
        if (this.onCheckedChangeListener != null)
          this.onCheckedChangeListener.onCheckedChanged(this, this.isChecked);
      }
    do
    {
      do
        return;
      while ((this.mBtnPos != this.mBtnOffPos) || (!this.isChecked));
      this.isChecked = false;
    }
    while (this.onCheckedChangeListener == null);
    this.onCheckedChangeListener.onCheckedChanged(this, this.isChecked);
  }

  private void startAnimation()
  {
    if (this.mLastBtnPos == this.mBtnPos)
      return;
    this.mAnimating = true;
    if (this.D)
      Log.d(this.TAG, "目标移动X到：" + this.mBtnPos + ",当前在:" + this.mLastBtnPos);
    float f = this.mAnimatedVelocity;
    if (this.mLastBtnPos > this.mBtnPos)
      f = -this.mAnimatedVelocity;
    this.mAnimationPosition = this.mLastBtnPos;
    int i = 0;
    while (true)
    {
      this.mAnimationPosition += 16.0F * f / 1000.0F;
      if (this.D)
        Log.d(this.TAG, i + "次移动X到：" + this.mAnimationPosition);
      if (this.mAnimationPosition >= this.mBtnOnPos)
      {
        this.mAnimationPosition = this.mBtnOnPos;
        moveView(this.mAnimationPosition, true);
        if (!this.isChecked)
        {
          this.isChecked = true;
          if (this.onCheckedChangeListener != null)
            this.onCheckedChangeListener.onCheckedChanged(this, this.isChecked);
        }
      }
      while (true)
      {
        this.mAnimating = false;
        return;
        if (this.mAnimationPosition > this.mBtnOffPos)
          break;
        this.mAnimationPosition = this.mBtnOffPos;
        moveView(this.mAnimationPosition, true);
        if (this.isChecked)
        {
          this.isChecked = false;
          if (this.onCheckedChangeListener != null)
            this.onCheckedChangeListener.onCheckedChanged(this, this.isChecked);
        }
      }
      moveView(this.mAnimationPosition, true);
      i += 1;
    }
  }

  public boolean isChecked()
  {
    return this.isChecked;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.saveLayerAlpha(this.mSaveLayerRectF, this.mAlpha, 31);
    paramCanvas.drawBitmap(this.btnMask, 0.0F, this.mExtendOffsetY, this.mPaint);
    this.mPaint.setXfermode(this.mXfermode);
    paramCanvas.drawBitmap(this.btnBottom, this.mRealPos, this.mExtendOffsetY, this.mPaint);
    this.mPaint.setXfermode(null);
    paramCanvas.drawBitmap(this.btnFrame, 0.0F, this.mExtendOffsetY, this.mPaint);
    paramCanvas.drawBitmap(this.mCurBtnPic, this.mRealPos, 0.4F + this.mExtendOffsetY, this.mPaint);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension((int)this.mMaskWidth, (int)(this.mMaskHeight + 2.0F * this.mExtendOffsetY));
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    if (this.mAnimating)
      return true;
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
      if (!this.mMoveEvent)
        break label389;
      if (this.D)
        Log.d(this.TAG, "－－－－弹起－－－－");
      this.mCurBtnPic = this.mBtnNormal;
      if (this.mBtnPos >= (this.mBtnOnPos - this.mBtnOffPos) / 2.0F + this.mBtnOffPos)
        break;
    case 0:
    case 2:
    }
    for (this.mBtnPos = this.mBtnOffPos; ; this.mBtnPos = this.mBtnOnPos)
    {
      startAnimation();
      return true;
      this.mMoveEvent = false;
      this.mFirstDownX = paramMotionEvent.getX();
      this.mCurBtnPic = this.mBtnPressed;
      if (this.isChecked);
      for (this.mBtnPos = this.mBtnOnPos; ; this.mBtnPos = this.mBtnOffPos)
      {
        this.mLastBtnPos = this.mBtnPos;
        if (!this.D)
          break;
        Log.d(this.TAG, "原来的X位置：" + this.mBtnPos);
        return true;
      }
      if (this.D)
        Log.d(this.TAG, "－－－－移动－－－－");
      float f = paramMotionEvent.getX() - this.mFirstDownX;
      if (this.D)
        Log.d(this.TAG, "X需要移动：" + f);
      if (Math.abs(f) < 5.0F)
        break;
      this.mMoveEvent = true;
      this.mFirstDownX = paramMotionEvent.getX();
      this.mBtnPos += f;
      if (this.D)
        Log.d(this.TAG, "现在的X位置：" + this.mBtnPos);
      if (this.mBtnPos < this.mBtnOffPos)
        this.mBtnPos = this.mBtnOffPos;
      if (this.mBtnPos > this.mBtnOnPos)
        this.mBtnPos = this.mBtnOnPos;
      moveViewToTarget();
      return true;
    }
    label389: if (this.isChecked);
    while (true)
    {
      setChecked(bool, true);
      return true;
      bool = true;
    }
  }

  public boolean performClick()
  {
    if (this.isChecked);
    for (boolean bool = false; ; bool = true)
    {
      setChecked(bool);
      return true;
    }
  }

  public void setChecked(boolean paramBoolean)
  {
    setChecked(paramBoolean, false);
  }

  public void setChecked(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1);
    for (this.mBtnPos = this.mBtnOnPos; paramBoolean2; this.mBtnPos = this.mBtnOffPos)
    {
      startAnimation();
      return;
    }
    moveViewToTarget();
  }

  public void setImageResource(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    this.btnBottom = BitmapFactory.decodeResource(this.context.getResources(), paramInt1);
    this.btnFrame = BitmapFactory.decodeResource(this.context.getResources(), paramInt2);
    this.btnMask = BitmapFactory.decodeResource(this.context.getResources(), paramInt3);
    this.mBtnNormal = BitmapFactory.decodeResource(this.context.getResources(), paramInt4);
    this.mBtnPressed = BitmapFactory.decodeResource(this.context.getResources(), paramInt5);
    this.mMaskWidth = this.btnMask.getWidth();
    this.mMaskHeight = this.btnMask.getHeight();
    this.mExtendOffsetY = ((int)(0.5F + 0.0F * getResources().getDisplayMetrics().density));
    this.mSaveLayerRectF = new RectF(-20.0F, this.mExtendOffsetY, 20.0F + this.mMaskWidth, this.mMaskHeight + this.mExtendOffsetY);
    this.mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    this.mCurBtnPic = this.mBtnNormal;
    this.mBtnWidth = this.mBtnPressed.getWidth();
    this.mBtnOnPos = (this.mBtnWidth / 2.0F);
    this.mBtnOffPos = (this.mMaskWidth - this.mBtnWidth / 2.0F);
    if (this.isChecked);
    for (this.mBtnPos = this.mBtnOnPos; ; this.mBtnPos = this.mBtnOffPos)
    {
      this.mRealPos = getRealPos(this.mBtnPos);
      return;
    }
  }

  public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    this.onCheckedChangeListener = paramOnCheckedChangeListener;
  }

  public void toggle()
  {
    if (this.isChecked);
    for (boolean bool = false; ; bool = true)
    {
      setChecked(bool);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sliding.AbSlidingButton
 * JD-Core Version:    0.6.2
 */