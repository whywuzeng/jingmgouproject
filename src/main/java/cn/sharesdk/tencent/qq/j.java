package cn.sharesdk.tencent.qq;

import android.os.Bundle;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Ln;

class j extends Thread
{
  j(i parami, String paramString1, Bundle paramBundle, String paramString2, String paramString3, String paramString4)
  {
  }

  public void run()
  {
    try
    {
      str1 = BitmapHelper.downloadBitmap(i.a(this.f), this.a);
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        while (true)
        {
          String str1;
          String str2 = this.b.getString("musicUrl");
          String str3 = DeviceHelper.getInstance(i.b(this.f)).getAppName();
          String str4 = this.b.getString("appId");
          i.a(this.f, this.c, this.d, this.e, str1, str2, str3, str4);
          return;
          localThrowable1 = localThrowable1;
          Ln.e(localThrowable1);
          Object localObject = null;
        }
      }
      catch (Throwable localThrowable2)
      {
        Ln.e(localThrowable2);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qq.j
 * JD-Core Version:    0.6.2
 */