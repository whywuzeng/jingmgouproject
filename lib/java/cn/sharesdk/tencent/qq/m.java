package cn.sharesdk.tencent.qq;

import android.app.Instrumentation;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.tools.utils.Ln;

class m extends Thread
{
  m(l paraml)
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
      this.a.a.finish();
      k.a(this.a.a).onCancel(null, 0);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qq.m
 * JD-Core Version:    0.6.2
 */