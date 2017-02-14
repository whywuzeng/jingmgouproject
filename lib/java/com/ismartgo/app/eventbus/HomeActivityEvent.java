package com.ismartgo.app.eventbus;

public class HomeActivityEvent
{
  String msg;

  public HomeActivityEvent(String paramString)
  {
    this.msg = paramString;
  }

  public String getMsg()
  {
    return this.msg;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.eventbus.HomeActivityEvent
 * JD-Core Version:    0.6.2
 */