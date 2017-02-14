package u.aly;

import android.os.Build;
import android.os.Build.VERSION;

public class h extends a
{
  private static final String a = "serial";

  public h()
  {
    super("serial");
  }

  public String f()
  {
    if (Build.VERSION.SDK_INT >= 9)
      return Build.SERIAL;
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.h
 * JD-Core Version:    0.6.2
 */