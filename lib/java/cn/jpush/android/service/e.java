package cn.jpush.android.service;

import android.os.Handler;
import android.os.Message;
import cn.jpush.android.api.m;

final class e extends Handler
{
  e(DownloadService paramDownloadService)
  {
  }

  public final void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    m.b(this.a, paramMessage.what);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.e
 * JD-Core Version:    0.6.2
 */