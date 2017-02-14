package org.android.agoo.net.channel;

public enum ChannelError
{
  private int A;
  private Level B;

  private ChannelError(int paramInt)
  {
    this(paramInt, Level.b);
  }

  private ChannelError(int paramInt, Level paramLevel)
  {
    this.A = paramInt;
    this.B = paramLevel;
  }

  public static ChannelError get(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return s;
    case 307:
      return p;
    case 400:
      return t;
    case 403:
      return v;
    case 404:
      return q;
    case 405:
      return w;
    case 408:
    }
    return r;
  }

  public static ChannelError getEasySpdy(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return i;
    case -2030:
      return l;
    case -2031:
      return m;
    case -2002:
      return k;
    case -2500:
    }
    return j;
  }

  public Level getChannelLevel()
  {
    return this.B;
  }

  public int getErrorCode()
  {
    return this.A;
  }

  public static enum Level
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.ChannelError
 * JD-Core Version:    0.6.2
 */