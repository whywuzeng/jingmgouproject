package com.nostra13.universalimageloader.core.assist;

public class ImageSize
{
  private static final String SEPARATOR = "x";
  private static final int TO_STRING_MAX_LENGHT = 9;
  private final int height;
  private final int width;

  public ImageSize(int paramInt1, int paramInt2)
  {
    this.width = paramInt1;
    this.height = paramInt2;
  }

  public ImageSize(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 % 180 == 0)
    {
      this.width = paramInt1;
      this.height = paramInt2;
      return;
    }
    this.width = paramInt2;
    this.height = paramInt1;
  }

  public int getHeight()
  {
    return this.height;
  }

  public int getWidth()
  {
    return this.width;
  }

  public ImageSize scale(float paramFloat)
  {
    return new ImageSize((int)(this.width * paramFloat), (int)(this.height * paramFloat));
  }

  public ImageSize scaleDown(int paramInt)
  {
    return new ImageSize(this.width / paramInt, this.height / paramInt);
  }

  public String toString()
  {
    return 9 + this.width + "x" + this.height;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.assist.ImageSize
 * JD-Core Version:    0.6.2
 */