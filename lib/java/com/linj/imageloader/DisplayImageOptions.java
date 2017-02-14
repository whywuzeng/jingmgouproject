package com.linj.imageloader;

import com.linj.imageloader.displayer.BitmapDisplayer;

public class DisplayImageOptions
{
  public boolean cacheInMemory;
  public boolean cacheOnDisk;
  public BitmapDisplayer displayer;
  public boolean fromNet;
  public int imageResOnFail;
  public int imageResOnLoading;

  private DisplayImageOptions(Builder paramBuilder)
  {
    this.imageResOnLoading = paramBuilder.imageResOnLoading;
    this.imageResOnFail = paramBuilder.imageResOnFail;
    this.cacheInMemory = paramBuilder.cacheInMemory;
    this.cacheOnDisk = paramBuilder.cacheOnDisk;
    this.displayer = paramBuilder.displayer;
    this.fromNet = paramBuilder.fromNet;
  }

  public static class Builder
  {
    private boolean cacheInMemory;
    private boolean cacheOnDisk;
    private BitmapDisplayer displayer;
    private boolean fromNet;
    private int imageResOnFail;
    private int imageResOnLoading;

    public DisplayImageOptions build()
    {
      return new DisplayImageOptions(this, null);
    }

    public Builder cacheInMemory(boolean paramBoolean)
    {
      this.cacheInMemory = paramBoolean;
      return this;
    }

    public Builder cacheOnDisk(boolean paramBoolean)
    {
      this.cacheOnDisk = paramBoolean;
      return this;
    }

    public Builder displayer(BitmapDisplayer paramBitmapDisplayer)
    {
      if (paramBitmapDisplayer == null)
        throw new IllegalArgumentException("displayer can't be null");
      this.displayer = paramBitmapDisplayer;
      return this;
    }

    public Builder setFromNet(boolean paramBoolean)
    {
      this.fromNet = paramBoolean;
      return this;
    }

    public Builder showImageOnFail(int paramInt)
    {
      this.imageResOnFail = paramInt;
      return this;
    }

    public Builder showImageOnLoading(int paramInt)
    {
      this.imageResOnLoading = paramInt;
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.imageloader.DisplayImageOptions
 * JD-Core Version:    0.6.2
 */