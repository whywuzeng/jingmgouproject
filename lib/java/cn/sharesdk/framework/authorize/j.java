package cn.sharesdk.framework.authorize;

import android.os.Message;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.UIHandler;

class j extends Thread
{
  j(g paramg)
  {
  }

  public void run()
  {
    try
    {
      Message localMessage = new Message();
      localMessage.what = 2;
      if ("none".equals(DeviceHelper.getInstance(g.a(this.a)).getDetailNetworkTypeForStatic()))
      {
        localMessage.arg1 = 1;
        UIHandler.sendMessage(localMessage, this.a);
        return;
      }
      if (ShareSDK.isRemoveCookieOnAuthorize())
      {
        CookieSyncManager.createInstance(g.b(this.a));
        CookieManager.getInstance().removeAllCookie();
      }
      localMessage.obj = this.a.a.getAuthorizeUrl();
      UIHandler.sendMessage(localMessage, this.a);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.j
 * JD-Core Version:    0.6.2
 */