package org.android.agoo.client;

public final class MtopSyncResult
{
  private volatile String data;
  private volatile boolean isSuccess;
  private volatile String retCode;
  private volatile String retDesc;

  public final String getData()
  {
    return this.data;
  }

  public final String getRetCode()
  {
    return this.retCode;
  }

  public final String getRetDesc()
  {
    return this.retDesc;
  }

  public final boolean isSuccess()
  {
    return this.isSuccess;
  }

  public final void setData(String paramString)
  {
    this.data = paramString;
  }

  public final void setRetCode(String paramString)
  {
    this.retCode = paramString;
  }

  public final void setRetDesc(String paramString)
  {
    this.retDesc = paramString;
  }

  public final void setSuccess(boolean paramBoolean)
  {
    this.isSuccess = paramBoolean;
  }

  public final String toString()
  {
    return "Result [isSuccess=" + this.isSuccess + ", data=" + this.data + ", retDesc=" + this.retDesc + ", retCode=" + this.retCode + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.MtopSyncResult
 * JD-Core Version:    0.6.2
 */