package com.google.zxing.client.result.optional;

final class NDEFRecord
{
  public static final String ACTION_WELL_KNOWN_TYPE = "act";
  public static final String SMART_POSTER_WELL_KNOWN_TYPE = "Sp";
  private static final int SUPPORTED_HEADER = 17;
  private static final int SUPPORTED_HEADER_MASK = 63;
  public static final String TEXT_WELL_KNOWN_TYPE = "T";
  public static final String URI_WELL_KNOWN_TYPE = "U";
  private final int header;
  private final byte[] payload;
  private final int totalRecordLength;
  private final String type;

  private NDEFRecord(int paramInt1, String paramString, byte[] paramArrayOfByte, int paramInt2)
  {
    this.header = paramInt1;
    this.type = paramString;
    this.payload = paramArrayOfByte;
    this.totalRecordLength = paramInt2;
  }

  static NDEFRecord readRecord(byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramArrayOfByte[paramInt] & 0xFF;
    if (((i ^ 0x11) & 0x3F) != 0)
      return null;
    int j = paramArrayOfByte[(paramInt + 1)] & 0xFF;
    int k = paramArrayOfByte[(paramInt + 2)] & 0xFF;
    String str = AbstractNDEFResultParser.bytesToString(paramArrayOfByte, paramInt + 3, j, "US-ASCII");
    byte[] arrayOfByte = new byte[k];
    System.arraycopy(paramArrayOfByte, paramInt + 3 + j, arrayOfByte, 0, k);
    return new NDEFRecord(i, str, arrayOfByte, j + 3 + k);
  }

  byte[] getPayload()
  {
    return this.payload;
  }

  int getTotalRecordLength()
  {
    return this.totalRecordLength;
  }

  String getType()
  {
    return this.type;
  }

  boolean isMessageBegin()
  {
    return (this.header & 0x80) != 0;
  }

  boolean isMessageEnd()
  {
    return (this.header & 0x40) != 0;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.optional.NDEFRecord
 * JD-Core Version:    0.6.2
 */