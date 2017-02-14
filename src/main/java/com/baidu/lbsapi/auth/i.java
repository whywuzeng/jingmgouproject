package com.baidu.lbsapi.auth;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Hashtable;

class i extends Handler
{
  i(LBSAuthManager paramLBSAuthManager, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    if (a.a)
      a.a("handleMessage !!");
    Object localObject = paramMessage.getData().getString("listenerKey");
    localObject = (LBSAuthManagerListener)LBSAuthManager.access$0().get(localObject);
    if (a.a)
      a.a("handleMessage listener = " + localObject);
    if (localObject != null)
      ((LBSAuthManagerListener)localObject).onAuthResult(paramMessage.what, paramMessage.obj.toString());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.lbsapi.auth.i
 * JD-Core Version:    0.6.2
 */