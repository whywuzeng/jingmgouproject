package com.umeng.message.proguard;

public class ak
{
  public static byte[] a(int paramInt)
  {
    int i = (byte)(paramInt % 256);
    paramInt >>= 8;
    int j = (byte)(paramInt % 256);
    paramInt >>= 8;
    int k = (byte)(paramInt % 256);
    return new byte[] { (byte)((paramInt >> 8) % 256), k, j, i };
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ak
 * JD-Core Version:    0.6.2
 */