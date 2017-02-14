package com.yolanda.nohttp.download;

import com.yolanda.nohttp.CommonRequest;
import com.yolanda.nohttp.able.Cancelable;

public abstract interface DownloadRequest extends CommonRequest, Cancelable
{
  public static final int STATUS_FINISH = 2;
  public static final int STATUS_RESTART = 0;
  public static final int STATUS_RESUME = 1;

  public abstract int checkBeforeStatus();

  public abstract String getFileDir();

  public abstract String getFileName();

  public abstract boolean isDeleteOld();

  public abstract boolean isRange();

  public abstract void setCancelSign(Object paramObject);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.download.DownloadRequest
 * JD-Core Version:    0.6.2
 */