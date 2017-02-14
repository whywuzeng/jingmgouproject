package com.ab.db.storage;

import java.util.List;

public class AbSqliteStorageListener
{
  public static abstract interface AbDataInfoListener
  {
    public abstract void onFailure(int paramInt, String paramString);

    public abstract void onSuccess(List<?> paramList);
  }

  public static abstract interface AbDataInsertListener
  {
    public abstract void onFailure(int paramInt, String paramString);

    public abstract void onSuccess(long paramLong);
  }

  public static abstract interface AbDataOperationListener
  {
    public abstract void onFailure(int paramInt, String paramString);

    public abstract void onSuccess(long paramLong);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.storage.AbSqliteStorageListener
 * JD-Core Version:    0.6.2
 */