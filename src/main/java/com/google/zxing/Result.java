package com.google.zxing;

import java.util.Enumeration;
import java.util.Hashtable;

public final class Result
{
  private final BarcodeFormat format;
  private final byte[] rawBytes;
  private Hashtable resultMetadata;
  private ResultPoint[] resultPoints;
  private final String text;
  private final long timestamp;

  public Result(String paramString, byte[] paramArrayOfByte, ResultPoint[] paramArrayOfResultPoint, BarcodeFormat paramBarcodeFormat)
  {
    this(paramString, paramArrayOfByte, paramArrayOfResultPoint, paramBarcodeFormat, System.currentTimeMillis());
  }

  public Result(String paramString, byte[] paramArrayOfByte, ResultPoint[] paramArrayOfResultPoint, BarcodeFormat paramBarcodeFormat, long paramLong)
  {
    if ((paramString == null) && (paramArrayOfByte == null))
      throw new IllegalArgumentException("Text and bytes are null");
    this.text = paramString;
    this.rawBytes = paramArrayOfByte;
    this.resultPoints = paramArrayOfResultPoint;
    this.format = paramBarcodeFormat;
    this.resultMetadata = null;
    this.timestamp = paramLong;
  }

  public void addResultPoints(ResultPoint[] paramArrayOfResultPoint)
  {
    if (this.resultPoints == null)
      this.resultPoints = paramArrayOfResultPoint;
    while ((paramArrayOfResultPoint == null) || (paramArrayOfResultPoint.length <= 0))
      return;
    ResultPoint[] arrayOfResultPoint = new ResultPoint[this.resultPoints.length + paramArrayOfResultPoint.length];
    System.arraycopy(this.resultPoints, 0, arrayOfResultPoint, 0, this.resultPoints.length);
    System.arraycopy(paramArrayOfResultPoint, 0, arrayOfResultPoint, this.resultPoints.length, paramArrayOfResultPoint.length);
    this.resultPoints = arrayOfResultPoint;
  }

  public BarcodeFormat getBarcodeFormat()
  {
    return this.format;
  }

  public byte[] getRawBytes()
  {
    return this.rawBytes;
  }

  public Hashtable getResultMetadata()
  {
    return this.resultMetadata;
  }

  public ResultPoint[] getResultPoints()
  {
    return this.resultPoints;
  }

  public String getText()
  {
    return this.text;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public void putAllMetadata(Hashtable paramHashtable)
  {
    if (paramHashtable != null)
    {
      if (this.resultMetadata != null)
        break label17;
      this.resultMetadata = paramHashtable;
    }
    while (true)
    {
      return;
      label17: Enumeration localEnumeration = paramHashtable.keys();
      while (localEnumeration.hasMoreElements())
      {
        ResultMetadataType localResultMetadataType = (ResultMetadataType)localEnumeration.nextElement();
        Object localObject = paramHashtable.get(localResultMetadataType);
        this.resultMetadata.put(localResultMetadataType, localObject);
      }
    }
  }

  public void putMetadata(ResultMetadataType paramResultMetadataType, Object paramObject)
  {
    if (this.resultMetadata == null)
      this.resultMetadata = new Hashtable(3);
    this.resultMetadata.put(paramResultMetadataType, paramObject);
  }

  public String toString()
  {
    if (this.text == null)
      return "[" + this.rawBytes.length + " bytes]";
    return this.text;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.Result
 * JD-Core Version:    0.6.2
 */