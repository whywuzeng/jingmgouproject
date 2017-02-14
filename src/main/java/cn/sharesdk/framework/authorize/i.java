package cn.sharesdk.framework.authorize;

import android.app.Instrumentation;
import com.mob.tools.utils.Ln;

class i extends Thread
{
  i(h paramh)
  {
  }

  public void run()
  {
    try
    {
      new Instrumentation().sendKeyDownUpSync(4);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
      AuthorizeListener localAuthorizeListener = this.a.a.a.getAuthorizeListener();
      if (localAuthorizeListener != null)
        localAuthorizeListener.onCancel();
      this.a.a.finish();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.i
 * JD-Core Version:    0.6.2
 */