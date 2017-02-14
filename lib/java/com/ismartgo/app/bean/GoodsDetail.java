package com.ismartgo.app.bean;

public class GoodsDetail
{
  private String Barcode;
  private String h5url;
  private int id;
  private int isscan;
  private String productName;
  private String promotion;

  public String getBarcode()
  {
    return this.Barcode;
  }

  public String getH5url()
  {
    return this.h5url;
  }

  public int getId()
  {
    return this.id;
  }

  public int getIsscan()
  {
    return this.isscan;
  }

  public String getProductName()
  {
    return this.productName;
  }

  public String getPromotion()
  {
    return this.promotion;
  }

  public void setBarcode(String paramString)
  {
    this.Barcode = paramString;
  }

  public void setH5url(String paramString)
  {
    this.h5url = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setIsscan(int paramInt)
  {
    this.isscan = paramInt;
  }

  public void setProductName(String paramString)
  {
    this.productName = paramString;
  }

  public void setPromotion(String paramString)
  {
    this.promotion = paramString;
  }

  public String toString()
  {
    return "GoodsDetail [id=" + this.id + ", productName=" + this.productName + ", promotion=" + this.promotion + ", h5url=" + this.h5url + ", Barcode=" + this.Barcode + ", isscan=" + this.isscan + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.GoodsDetail
 * JD-Core Version:    0.6.2
 */