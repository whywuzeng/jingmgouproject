package cn.sharesdk.tencent.qq;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.tools.FakeActivity;
import com.mob.tools.MobUIShell;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.io.File;
import java.util.HashMap;

public class i extends FakeActivity
{
  private Platform a;
  private String b;
  private PlatformActionListener c;

  private void a(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));
    localIntent.putExtra("android.intent.extra.TITLE", paramString1);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    localIntent.setType("text/*");
    try
    {
      this.activity.startActivity(localIntent);
      finish();
      if (this.c != null)
        this.c.onComplete(this.a, 9, new HashMap());
      return;
    }
    catch (Throwable paramString1)
    {
      do
        this.activity.finish();
      while (this.c == null);
      this.c.onError(this.a, 9, paramString1);
    }
  }

  private void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    if (TextUtils.isEmpty(paramString2))
    {
      if ((TextUtils.isEmpty(paramString4)) && (!TextUtils.isEmpty(paramString3)))
        a(paramString1, paramString3);
      do
      {
        return;
        if ((!TextUtils.isEmpty(paramString4)) && (TextUtils.isEmpty(paramString3)))
        {
          b(paramString4);
          return;
        }
      }
      while (this.c == null);
      paramString1 = new Throwable("titleUrl is needed");
      this.c.onError(this.a, 9, paramString1);
      return;
    }
    b(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7);
  }

  private int[] a()
  {
    int[] arrayOfInt;
    try
    {
      localObject = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0).versionName;
      localObject = ((String)localObject).split("\\.");
      arrayOfInt = new int[localObject.length];
      i = 0;
      if (i >= arrayOfInt.length);
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        while (true)
        {
          Object localObject;
          arrayOfInt[i] = R.parseInt(localObject[i]);
          i += 1;
        }
        localThrowable1 = localThrowable1;
        Ln.e(localThrowable1);
        String str = "0";
      }
      catch (Throwable localThrowable2)
      {
        while (true)
        {
          int i;
          Ln.e(localThrowable2);
          arrayOfInt[i] = 0;
        }
      }
    }
    return arrayOfInt;
  }

  private void b(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));
    localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(paramString)));
    localIntent.setType("image/*");
    try
    {
      this.activity.startActivity(localIntent);
      finish();
      if (this.c != null)
        this.c.onComplete(this.a, 9, new HashMap());
      return;
    }
    catch (Throwable paramString)
    {
      do
        this.activity.finish();
      while (this.c == null);
      this.c.onError(this.a, 9, paramString);
    }
  }

  private void b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    paramString2 = c(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7);
    paramString1 = new Intent("android.intent.action.VIEW");
    paramString1.setData(Uri.parse(paramString2));
    try
    {
      paramString2 = a();
      paramString3 = new h();
      paramString3.a(this.b);
      paramString3.a(this.a, this.c);
      MobUIShell.registerExecutor(this.b, paramString3);
      if ((paramString2.length <= 1) || ((paramString2[0] < 4) && (paramString2[1] < 6)))
        paramString1.putExtra("key_request_code", 0);
      this.activity.startActivityForResult(paramString1, 0);
      return;
    }
    catch (Throwable paramString1)
    {
      do
        this.activity.finish();
      while (this.c == null);
      this.c.onError(this.a, 9, paramString1);
    }
  }

  private String c(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    String str = "mqqapi://share/to_fri?src_type=app&version=1&file_type=news";
    if (!TextUtils.isEmpty(paramString4))
      str = "mqqapi://share/to_fri?src_type=app&version=1&file_type=news" + "&file_data=" + Base64.encodeToString(paramString4.getBytes(), 2);
    paramString4 = str;
    if (!TextUtils.isEmpty(paramString1))
      paramString4 = str + "&title=" + Base64.encodeToString(paramString1.getBytes(), 2);
    paramString1 = paramString4;
    if (!TextUtils.isEmpty(paramString3))
      paramString1 = paramString4 + "&description=" + Base64.encodeToString(paramString3.getBytes(), 2);
    paramString3 = paramString1;
    if (!TextUtils.isEmpty(paramString6))
    {
      paramString3 = paramString6;
      if (paramString6.length() > 20)
        paramString3 = paramString6.substring(0, 20) + "...";
      paramString3 = paramString1 + "&app_name=" + Base64.encodeToString(paramString3.getBytes(), 2);
    }
    paramString1 = paramString3;
    if (!TextUtils.isEmpty(paramString7))
      paramString1 = paramString3 + "&share_id=" + paramString7;
    paramString3 = paramString1;
    if (!TextUtils.isEmpty(paramString2))
      paramString3 = paramString1 + "&url=" + Base64.encodeToString(paramString2.getBytes(), 2);
    if (!TextUtils.isEmpty(paramString5))
    {
      paramString1 = paramString3 + "&req_type=" + Base64.encodeToString("2".getBytes(), 2);
      paramString1 = paramString1 + "&cflag=" + Base64.encodeToString("0".getBytes(), 2);
      return paramString1 + "&audioUrl=" + Base64.encodeToString(paramString5.getBytes(), 2);
    }
    paramString1 = paramString3 + "&req_type=" + Base64.encodeToString("1".getBytes(), 2);
    return paramString1 + "&cflag=" + Base64.encodeToString("0".getBytes(), 2);
  }

  public void a(Platform paramPlatform, PlatformActionListener paramPlatformActionListener)
  {
    this.a = paramPlatform;
    this.c = paramPlatformActionListener;
  }

  public void a(String paramString)
  {
    this.b = ("tencent" + paramString);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    finish();
  }

  public void onCreate()
  {
    Bundle localBundle = this.activity.getIntent().getExtras();
    String str1 = localBundle.getString("title");
    String str2 = localBundle.getString("titleUrl");
    String str3 = localBundle.getString("summary");
    String str4 = localBundle.getString("imagePath");
    String str5 = localBundle.getString("imageUrl");
    if (((TextUtils.isEmpty(str4)) || (!new File(str4).exists())) && (!TextUtils.isEmpty(str5)))
    {
      new j(this, str5, localBundle, str1, str2, str3).start();
      return;
    }
    a(str1, str2, str3, str4, localBundle.getString("musicUrl"), DeviceHelper.getInstance(this.activity).getAppName(), localBundle.getString("appId"));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qq.i
 * JD-Core Version:    0.6.2
 */