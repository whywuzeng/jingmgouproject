package org.android.spdy;

public class SpdyStreamContext
{
  public Spdycb callBack;
  public Object streamContext;

  SpdyStreamContext(Object paramObject)
  {
    this.streamContext = paramObject;
    this.callBack = null;
  }

  SpdyStreamContext(Object paramObject, Spdycb paramSpdycb)
  {
    this.streamContext = paramObject;
    this.callBack = paramSpdycb;
  }

  static int getContext(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof Integer)))
      return ((Integer)paramObject).intValue();
    return 0;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.SpdyStreamContext
 * JD-Core Version:    0.6.2
 */