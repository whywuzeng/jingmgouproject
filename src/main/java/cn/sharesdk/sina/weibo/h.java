package cn.sharesdk.sina.weibo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.IBinder;
import android.text.TextUtils;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.authorize.f;
import java.lang.reflect.Method;

public class h extends f
  implements ServiceConnection
{
  private String d;
  private String e;
  private String[] f;

  public h(e parame)
  {
    super(parame);
  }

  private boolean a(String paramString1, String paramString2)
  {
    boolean bool = true;
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString1, paramString2);
    localIntent.putExtra("appKey", this.d);
    localIntent.putExtra("redirectUri", this.e);
    if ((this.f != null) && (this.f.length > 0))
      localIntent.putExtra("scope", TextUtils.join(",", this.f));
    if (!b(localIntent))
      return false;
    try
    {
      this.a.startActivityForResult(localIntent, this.b);
      this.a.getContext().getApplicationContext().unbindService(this);
      return bool;
    }
    catch (Throwable paramString1)
    {
      while (true)
        bool = false;
    }
  }

  private boolean b(Intent paramIntent)
  {
    paramIntent = this.a.getContext().getPackageManager().resolveActivity(paramIntent, 0);
    if (paramIntent == null);
    while (true)
    {
      return false;
      paramIntent = paramIntent.activityInfo.packageName;
      try
      {
        paramIntent = this.a.getContext().getPackageManager().getPackageInfo(paramIntent, 64).signatures;
        int j = paramIntent.length;
        int i = 0;
        while (i < j)
        {
          boolean bool = "30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4".equals(paramIntent[i].toCharsString());
          if (bool)
            return true;
          i += 1;
        }
      }
      catch (PackageManager.NameNotFoundException paramIntent)
      {
      }
    }
    return false;
  }

  private void c(Intent paramIntent)
  {
    if (this.c == null)
      return;
    String str2 = paramIntent.getStringExtra("error");
    String str1 = str2;
    if (str2 == null)
      str1 = paramIntent.getStringExtra("error_type");
    if (str1 == null)
    {
      this.c.onComplete(paramIntent.getExtras());
      return;
    }
    if ((str1.equals("access_denied")) || (str1.equals("OAuthAccessDeniedException")))
    {
      this.c.onCancel();
      return;
    }
    str2 = paramIntent.getStringExtra("error_description");
    paramIntent = str1;
    if (str2 != null)
      paramIntent = str1 + ": " + str2;
    this.c.onFailed(new Throwable(paramIntent));
  }

  private void d(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      str = paramIntent.getStringExtra("error");
      i = paramIntent.getIntExtra("error_code", -1);
      paramIntent = new Throwable(str + " (" + i + ")");
      if (this.c != null)
        this.c.onFailed(paramIntent);
    }
    while (this.c == null)
    {
      String str;
      int i;
      return;
    }
    this.c.onCancel();
  }

  public void a()
  {
    Intent localIntent = new Intent();
    localIntent.setClassName("com.sina.weibo", "com.sina.weibo.business.RemoteSSOService");
    if (!this.a.getContext().getApplicationContext().bindService(localIntent, this, 1))
    {
      this.a.finish();
      if (this.c != null)
        this.c.onFailed(new Throwable());
    }
  }

  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.a.finish();
    if (paramInt1 == this.b);
    switch (paramInt2)
    {
    default:
      return;
    case -1:
      c(paramIntent);
      return;
    case 0:
    }
    d(paramIntent);
  }

  public void a(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    this.d = paramString1;
    this.e = paramString2;
    this.f = paramArrayOfString;
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    try
    {
      paramComponentName = Class.forName("com.sina.sso.RemoteSSO$Stub");
      Object localObject = paramComponentName.getMethod("asInterface", new Class[] { IBinder.class });
      ((Method)localObject).setAccessible(true);
      paramIBinder = ((Method)localObject).invoke(null, new Object[] { paramIBinder });
      localObject = paramComponentName.getMethod("getPackageName", new Class[0]);
      ((Method)localObject).setAccessible(true);
      localObject = String.valueOf(((Method)localObject).invoke(paramIBinder, new Object[0]));
      paramComponentName = paramComponentName.getMethod("getActivityName", new Class[0]);
      paramComponentName.setAccessible(true);
      if (!a((String)localObject, String.valueOf(paramComponentName.invoke(paramIBinder, new Object[0]))))
      {
        this.a.finish();
        if (this.c != null)
          this.c.onFailed(new Throwable());
      }
      return;
    }
    catch (Throwable paramComponentName)
    {
      do
        this.a.finish();
      while (this.c == null);
      this.c.onFailed(paramComponentName);
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    this.a.finish();
    if (this.c != null)
      this.c.onFailed(new Throwable());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.h
 * JD-Core Version:    0.6.2
 */