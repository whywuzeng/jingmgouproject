package u.aly;

import com.umeng.analytics.AnalyticsConfig;

public class n
  implements Thread.UncaughtExceptionHandler
{
  private Thread.UncaughtExceptionHandler a;
  private v b;

  public n()
  {
    if (Thread.getDefaultUncaughtExceptionHandler() == this)
      return;
    this.a = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  private void a(Throwable paramThrowable)
  {
    if (AnalyticsConfig.CATCH_EXCEPTION)
    {
      this.b.a(paramThrowable);
      return;
    }
    this.b.a(null);
  }

  public void a(v paramv)
  {
    this.b = paramv;
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    a(paramThrowable);
    if ((this.a != null) && (this.a != Thread.getDefaultUncaughtExceptionHandler()))
      this.a.uncaughtException(paramThread, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.n
 * JD-Core Version:    0.6.2
 */