package org.android.spdy;

public enum SpdyVersion
{
  SPDY2(2), SPDY3(3), SPDY3DOT1(4);

  private int version;

  private SpdyVersion(int paramInt)
  {
    this.version = paramInt;
  }

  int getInt()
  {
    return this.version;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.SpdyVersion
 * JD-Core Version:    0.6.2
 */