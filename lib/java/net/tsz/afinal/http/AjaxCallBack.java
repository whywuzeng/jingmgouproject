package net.tsz.afinal.http;

public abstract class AjaxCallBack<T>
{
  private boolean progress = true;
  private int rate = 1000;

  public int getRate()
  {
    return this.rate;
  }

  public boolean isProgress()
  {
    return this.progress;
  }

  public void onFailure(Throwable paramThrowable, int paramInt, String paramString)
  {
  }

  public void onLoading(long paramLong1, long paramLong2)
  {
  }

  public void onStart()
  {
  }

  public void onSuccess(T paramT)
  {
  }

  public AjaxCallBack<T> progress(boolean paramBoolean, int paramInt)
  {
    this.progress = paramBoolean;
    this.rate = paramInt;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.AjaxCallBack
 * JD-Core Version:    0.6.2
 */