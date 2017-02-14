package com.ab.view.app;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class AbRotate3dAnimation2 extends Animation
{
  private static final String TAG = "Rotate3d";
  private Camera mCamera;
  private float mCenterX;
  private float mCenterY;
  private float mFromDegree;
  private float mLeft;
  private float mToDegree;
  private float mTop;

  public AbRotate3dAnimation2(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.mFromDegree = paramFloat1;
    this.mToDegree = paramFloat2;
    this.mLeft = paramFloat3;
    this.mTop = paramFloat4;
    this.mCenterX = paramFloat5;
    this.mCenterY = paramFloat6;
  }

  protected void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    paramFloat = this.mFromDegree + (this.mToDegree - this.mFromDegree) * paramFloat;
    float f1 = this.mCenterX;
    float f2 = this.mCenterY;
    paramTransformation = paramTransformation.getMatrix();
    if (paramFloat <= -76.0F)
    {
      this.mCamera.save();
      this.mCamera.rotateY(-90.0F);
      this.mCamera.getMatrix(paramTransformation);
      this.mCamera.restore();
    }
    while (true)
    {
      paramTransformation.preTranslate(-f1, -f2);
      paramTransformation.postTranslate(f1, f2);
      return;
      if (paramFloat >= 76.0F)
      {
        this.mCamera.save();
        this.mCamera.rotateY(90.0F);
        this.mCamera.getMatrix(paramTransformation);
        this.mCamera.restore();
      }
      else
      {
        this.mCamera.save();
        this.mCamera.translate(0.0F, 0.0F, f1);
        this.mCamera.rotateY(paramFloat);
        this.mCamera.translate(0.0F, 0.0F, -f1);
        this.mCamera.getMatrix(paramTransformation);
        this.mCamera.restore();
      }
    }
  }

  public void initialize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.initialize(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mCamera = new Camera();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.app.AbRotate3dAnimation2
 * JD-Core Version:    0.6.2
 */