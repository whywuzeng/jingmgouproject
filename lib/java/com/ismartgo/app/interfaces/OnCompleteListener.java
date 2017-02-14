package com.ismartgo.app.interfaces;

public abstract interface OnCompleteListener
{
  public abstract void onFailure(Throwable paramThrowable, int paramInt, String paramString);

  public abstract void onResult(Object paramObject, int paramInt);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.interfaces.OnCompleteListener
 * JD-Core Version:    0.6.2
 */