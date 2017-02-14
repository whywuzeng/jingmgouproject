package com.dodowaterfall.widget;

import android.content.res.AssetManager;

public class FlowTag
{
  private int ItemWidth;
  private AssetManager assetManager;
  private String fileName;
  private int flowId;
  public final int what = 1;

  public AssetManager getAssetManager()
  {
    return this.assetManager;
  }

  public String getFileName()
  {
    return this.fileName;
  }

  public int getFlowId()
  {
    return this.flowId;
  }

  public int getItemWidth()
  {
    return this.ItemWidth;
  }

  public void setAssetManager(AssetManager paramAssetManager)
  {
    this.assetManager = paramAssetManager;
  }

  public void setFileName(String paramString)
  {
    this.fileName = paramString;
  }

  public void setFlowId(int paramInt)
  {
    this.flowId = paramInt;
  }

  public void setItemWidth(int paramInt)
  {
    this.ItemWidth = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.dodowaterfall.widget.FlowTag
 * JD-Core Version:    0.6.2
 */