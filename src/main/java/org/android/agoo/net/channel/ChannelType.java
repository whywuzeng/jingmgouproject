package org.android.agoo.net.channel;

public enum ChannelType
{
  private int c;
  private String d;

  private ChannelType(int paramInt, String paramString)
  {
    this.c = paramInt;
    this.d = paramString;
  }

  public static ChannelType get(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return a;
    case 0:
      return a;
    case 1:
    }
    return b;
  }

  public String getDesc()
  {
    return this.d;
  }

  public int getValue()
  {
    return this.c;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.ChannelType
 * JD-Core Version:    0.6.2
 */