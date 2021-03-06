package com.google.zxing.client.result;

public final class ProductParsedResult extends ParsedResult
{
  private final String normalizedProductID;
  private final String productID;

  ProductParsedResult(String paramString)
  {
    this(paramString, paramString);
  }

  ProductParsedResult(String paramString1, String paramString2)
  {
    super(ParsedResultType.PRODUCT);
    this.productID = paramString1;
    this.normalizedProductID = paramString2;
  }

  public String getDisplayResult()
  {
    return this.productID;
  }

  public String getNormalizedProductID()
  {
    return this.normalizedProductID;
  }

  public String getProductID()
  {
    return this.productID;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ProductParsedResult
 * JD-Core Version:    0.6.2
 */