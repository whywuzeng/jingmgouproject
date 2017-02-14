package cn.sharesdk.tencent.qzone;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.RegisterView;
import com.mob.tools.FakeActivity;
import com.mob.tools.MobUIShell;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class i extends FakeActivity
{
  private String a;
  private boolean b;
  private PlatformActionListener c;
  private RegisterView d;
  private WebView e;
  private String f;
  private boolean g;
  private boolean h;
  private QZoneWebShareAdapter i;

  private QZoneWebShareAdapter b()
  {
    try
    {
      Object localObject = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("QZoneWebShareAdapter");
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        localObject = Class.forName((String)localObject).newInstance();
        if (!(localObject instanceof QZoneWebShareAdapter))
          return null;
        localObject = (QZoneWebShareAdapter)localObject;
        return localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
      return null;
    }
    return null;
  }

  private void b(String paramString)
  {
    if (paramString == null);
    for (String str1 = ""; ; str1 = new String(paramString))
    {
      paramString = R.urlToBundle(paramString);
      if (paramString != null)
        break;
      this.h = true;
      finish();
      this.c.onError(null, 0, new Throwable("failed to parse callback uri: " + str1));
      return;
    }
    String str2 = paramString.getString("action");
    if ((!"share".equals(str2)) && (!"shareToQzone".equals(str2)))
    {
      this.h = true;
      finish();
      this.c.onError(null, 0, new Throwable("action error: " + str1));
      return;
    }
    str2 = paramString.getString("result");
    if ("cancel".equals(str2))
    {
      finish();
      this.c.onCancel(null, 0);
      return;
    }
    if (!"complete".equals(str2))
    {
      this.h = true;
      finish();
      this.c.onError(null, 0, new Throwable("operation failed: " + str1));
      return;
    }
    paramString = paramString.getString("response");
    if (TextUtils.isEmpty(paramString))
    {
      this.h = true;
      finish();
      this.c.onError(null, 0, new Throwable("response empty" + str1));
      return;
    }
    this.g = true;
    finish();
    this.c.onComplete(null, 0, new Hashon().fromJson(paramString));
  }

  private void c()
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(this.a));
      this.activity.startActivity(localIntent);
      MobUIShell.registerExecutor(this.f, this);
      finish();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (this.c == null);
      this.c.onError(null, 0, localThrowable);
    }
  }

  private void c(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    try
    {
      paramString = this.activity.getPackageManager().queryIntentActivities(localIntent, 1);
      if ((paramString != null) && (paramString.size() > 0))
        startActivity(localIntent);
      return;
    }
    catch (Throwable paramString)
    {
      while (true)
      {
        Ln.e(paramString);
        paramString = null;
      }
    }
  }

  private void d()
  {
    this.d = a();
    try
    {
      int j = R.getStringRes(getContext(), "share_to_qzone");
      if (j > 0)
        this.d.c().getTvTitle().setText(j);
      this.i.setBodyView(this.d.d());
      this.i.setWebView(this.d.b());
      this.i.setTitleView(this.d.c());
      this.i.onCreate();
      this.activity.setContentView(this.d);
      if ("none".equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic()))
      {
        this.h = true;
        finish();
        this.c.onError(null, 0, new Throwable("failed to load webpage, network disconnected."));
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Ln.e(localThrowable);
        this.d.c().setVisibility(8);
      }
      this.d.b().loadUrl(this.a);
    }
  }

  protected RegisterView a()
  {
    RegisterView localRegisterView = new RegisterView(this.activity);
    int j = localRegisterView.c().getChildCount();
    localRegisterView.c().getChildAt(j - 1).setVisibility(8);
    localRegisterView.a().setOnClickListener(new j(this));
    this.e = localRegisterView.b();
    WebSettings localWebSettings = this.e.getSettings();
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setCacheMode(1);
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setDatabasePath(this.activity.getDir("database", 0).getPath());
    this.e.setVerticalScrollBarEnabled(false);
    this.e.setHorizontalScrollBarEnabled(false);
    this.e.setWebViewClient(new l(this));
    return localRegisterView;
  }

  public void a(PlatformActionListener paramPlatformActionListener)
  {
    this.c = paramPlatformActionListener;
  }

  public void a(String paramString)
  {
    this.f = ("tencent" + paramString);
  }

  public void a(String paramString, boolean paramBoolean)
  {
    this.a = paramString;
    this.b = paramBoolean;
  }

  public void onCreate()
  {
    Object localObject = this.activity.getIntent();
    String str1 = ((Intent)localObject).getScheme();
    if ((str1 != null) && (str1.startsWith(this.f)))
    {
      finish();
      localObject = R.urlToBundle(((Intent)localObject).getDataString());
      str1 = String.valueOf(((Bundle)localObject).get("result"));
      String str2 = String.valueOf(((Bundle)localObject).get("action"));
      if (("shareToQQ".equals(str2)) || ("shareToQzone".equals(str2)))
      {
        if (!"complete".equals(str1))
          break label131;
        if (this.c != null)
        {
          localObject = String.valueOf(((Bundle)localObject).get("response"));
          localObject = new Hashon().fromJson((String)localObject);
          this.c.onComplete(null, 9, (HashMap)localObject);
        }
      }
      label131: 
      do
      {
        do
        {
          return;
          if (!"error".equals(str1))
            break;
        }
        while (this.c == null);
        localObject = new Throwable(String.valueOf(((Bundle)localObject).get("response")));
        this.c.onError(null, 9, (Throwable)localObject);
        return;
      }
      while (this.c == null);
      this.c.onCancel(null, 9);
      return;
    }
    if (this.b)
    {
      c();
      return;
    }
    d();
  }

  public void onDestroy()
  {
    if ((!this.b) && (!this.h) && (!this.g))
      this.c.onCancel(null, 0);
    if (this.i != null)
      this.i.onDestroy();
  }

  public boolean onFinish()
  {
    if (this.i != null)
      return this.i.onFinish();
    return super.onFinish();
  }

  public void onPause()
  {
    if (this.i != null)
      this.i.onPause();
  }

  public void onRestart()
  {
    if (this.i != null)
      this.i.onRestart();
  }

  public void onResume()
  {
    if (this.i != null)
      this.i.onResume();
  }

  public void onStart()
  {
    if (this.i != null)
      this.i.onStart();
  }

  public void onStop()
  {
    if (this.i != null)
      this.i.onStop();
  }

  public void setActivity(Activity paramActivity)
  {
    super.setActivity(paramActivity);
    if (this.i == null)
    {
      this.i = b();
      if (this.i == null)
        this.i = new QZoneWebShareAdapter();
    }
    this.i.setActivity(paramActivity);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qzone.i
 * JD-Core Version:    0.6.2
 */