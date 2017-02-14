package cn.sharesdk.wechat.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.security.MessageDigest;

public class j
{
  private Context a;
  private String b;

  private String a(String paramString)
  {
    Object localObject = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
    while (true)
    {
      try
      {
        localObject = this.a.getContentResolver().query((Uri)localObject, new String[] { "_id", "key", "type", "value" }, "key = ?", new String[] { paramString }, null);
        if (localObject == null)
          return null;
        if (((Cursor)localObject).moveToFirst())
        {
          paramString = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("value"));
          ((Cursor)localObject).close();
          return paramString;
        }
      }
      catch (Throwable paramString)
      {
        Ln.e(paramString);
        return null;
      }
      paramString = null;
    }
  }

  private boolean a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length == 0))
    {
      Ln.e("checkSumConsistent fail, invalid arguments, \"_mmessage_checksum\" is empty", new Object[0]);
      return false;
    }
    if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length == 0))
    {
      Ln.e("checkSumConsistent fail, invalid arguments, checksum is empty", new Object[0]);
      return false;
    }
    if (paramArrayOfByte1.length != paramArrayOfByte2.length)
    {
      Ln.e("checkSumConsistent fail, length is different", new Object[0]);
      return false;
    }
    int i = 0;
    while (i < paramArrayOfByte1.length)
    {
      if (paramArrayOfByte1[i] != paramArrayOfByte2[i])
      {
        Ln.e("checkSumConsistent fail, not match", new Object[0]);
        return false;
      }
      i += 1;
    }
    return true;
  }

  private byte[] a(String paramString1, String paramString2, int paramInt)
  {
    Object localObject1 = null;
    int j = 0;
    Object localObject2 = new StringBuffer();
    if (paramString1 != null)
      ((StringBuffer)localObject2).append(paramString1);
    ((StringBuffer)localObject2).append(paramInt);
    ((StringBuffer)localObject2).append(paramString2);
    ((StringBuffer)localObject2).append("mMcShCsTr");
    paramString2 = ((StringBuffer)localObject2).toString().substring(1, 9).getBytes();
    paramString1 = new char[16];
    String tmp69_68 = paramString1;
    tmp69_68[0] = 48;
    String tmp74_69 = tmp69_68;
    tmp74_69[1] = 49;
    String tmp79_74 = tmp74_69;
    tmp79_74[2] = 50;
    String tmp84_79 = tmp79_74;
    tmp84_79[3] = 51;
    String tmp89_84 = tmp84_79;
    tmp89_84[4] = 52;
    String tmp94_89 = tmp89_84;
    tmp94_89[5] = 53;
    String tmp99_94 = tmp94_89;
    tmp99_94[6] = 54;
    String tmp105_99 = tmp99_94;
    tmp105_99[7] = 55;
    String tmp111_105 = tmp105_99;
    tmp111_105[8] = 56;
    String tmp117_111 = tmp111_105;
    tmp117_111[9] = 57;
    String tmp123_117 = tmp117_111;
    tmp123_117[10] = 97;
    String tmp129_123 = tmp123_117;
    tmp129_123[11] = 98;
    String tmp135_129 = tmp129_123;
    tmp135_129[12] = 99;
    String tmp141_135 = tmp135_129;
    tmp141_135[13] = 100;
    String tmp147_141 = tmp141_135;
    tmp147_141[14] = 101;
    String tmp153_147 = tmp147_141;
    tmp153_147[15] = 102;
    tmp153_147;
    try
    {
      localObject2 = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject2).update(paramString2);
      paramString2 = ((MessageDigest)localObject2).digest();
      localObject2 = new char[paramString2.length * 2];
      int i = 0;
      paramInt = j;
      while (paramInt < paramString2.length)
      {
        j = paramString2[paramInt];
        int k = i + 1;
        localObject2[i] = paramString1[(j >>> 4 & 0xF)];
        i = k + 1;
        localObject2[k] = paramString1[(j & 0xF)];
        paramInt += 1;
      }
      paramString1 = new String((char[])localObject2);
      paramString2 = localObject1;
      if (paramString1 != null)
        paramString2 = paramString1.getBytes();
      return paramString2;
    }
    catch (Throwable paramString1)
    {
      while (true)
      {
        Ln.e(paramString1);
        paramString1 = null;
      }
    }
  }

  private boolean d()
  {
    boolean bool2 = false;
    Ln.d("checking signature of wechat client...", new Object[0]);
    while (true)
    {
      int i;
      try
      {
        PackageInfo localPackageInfo = this.a.getPackageManager().getPackageInfo("com.tencent.mm", 64);
        int j = localPackageInfo.signatures.length;
        i = 0;
        boolean bool1 = bool2;
        if (i < j)
        {
          if ("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499".equals(localPackageInfo.signatures[i].toCharsString()))
          {
            Ln.d("pass!", new Object[0]);
            bool1 = true;
          }
        }
        else
          return bool1;
      }
      catch (Throwable localThrowable)
      {
        Ln.e(localThrowable);
        return false;
      }
      i += 1;
    }
  }

  public void a(m paramm)
  {
    if (!b())
      throw new WechatClientNotExistException();
    if (!paramm.b())
      throw new Throwable("sendReq checkArgs fail");
    String str1 = this.a.getPackageName();
    String str2 = "weixin://sendreq?appid=" + this.b;
    Intent localIntent = new Intent();
    localIntent.setClassName("com.tencent.mm", "com.tencent.mm.plugin.base.stub.WXEntryActivity");
    Bundle localBundle = new Bundle();
    paramm.a(localBundle);
    if (localBundle != null)
      localIntent.putExtras(localBundle);
    localIntent.putExtra("_mmessage_sdkVersion", 553844737);
    localIntent.putExtra("_mmessage_appPackage", str1);
    localIntent.putExtra("_mmessage_content", str2);
    localIntent.putExtra("_mmessage_checksum", a(str2, str1, 553844737));
    localIntent.addFlags(268435456);
    localIntent.addFlags(134217728);
    this.a.startActivity(localIntent);
    Ln.d("starting activity, packageName=com.tencent.mm, className=com.tencent.mm.plugin.base.stub.WXEntryActivity", new Object[0]);
  }

  public boolean a()
  {
    boolean bool2 = false;
    try
    {
      localObject = this.a.getPackageManager().getPackageInfo("com.tencent.mm", 0).versionName;
      Ln.i("wechat versionName ==>> " + (String)localObject, new Object[0]);
      localObject = localObject.split("_")[0].split("\\.");
      arrayOfInt = new int[localObject.length];
      i = 0;
      if (i >= arrayOfInt.length);
    }
    catch (Throwable localThrowable1)
    {
      int[] arrayOfInt;
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
      boolean bool1 = bool2;
      if (arrayOfInt.length >= 4)
      {
        bool1 = bool2;
        if (arrayOfInt[0] == 6)
        {
          bool1 = bool2;
          if (arrayOfInt[1] == 0)
          {
            bool1 = bool2;
            if (arrayOfInt[2] == 2)
            {
              bool1 = bool2;
              if (arrayOfInt[3] <= 56)
                bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
  }

  public boolean a(Context paramContext, String paramString)
  {
    this.a = paramContext;
    this.b = paramString;
    if (!d())
    {
      Ln.e("register app failed for wechat app signature check failed", new Object[0]);
      return false;
    }
    paramString = "weixin://registerapp?appid=" + paramString;
    String str = paramContext.getPackageName();
    Intent localIntent = new Intent("com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER");
    localIntent.putExtra("_mmessage_sdkVersion", 553910273);
    localIntent.putExtra("_mmessage_appPackage", str);
    localIntent.putExtra("_mmessage_content", paramString);
    localIntent.putExtra("_mmessage_checksum", a(paramString, str, 553910273));
    paramContext.sendBroadcast(localIntent, "com.tencent.mm.permission.MM_MESSAGE");
    Ln.d("sending broadcast, intent=" + "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER" + ", perm=" + "com.tencent.mm.permission.MM_MESSAGE", new Object[0]);
    return true;
  }

  public boolean a(WechatHandlerActivity paramWechatHandlerActivity, k paramk)
  {
    Object localObject = paramWechatHandlerActivity.getIntent();
    if (localObject == null)
      return false;
    String str1 = ((Intent)localObject).getStringExtra("wx_token_key");
    if ((str1 == null) || (!str1.equals("com.tencent.mm.openapi.token")))
    {
      Ln.e("invalid argument, \"wx_token_key\" is empty or does not equals \"com.tencent.mm.openapi.token\"", new Object[0]);
      return false;
    }
    str1 = ((Intent)localObject).getStringExtra("_mmessage_appPackage");
    if (TextUtils.isEmpty(str1))
    {
      Ln.e("invalid argument, \"_mmessage_appPackage\" is empty", new Object[0]);
      return false;
    }
    String str2 = ((Intent)localObject).getStringExtra("_mmessage_content");
    int i = ((Intent)localObject).getIntExtra("_mmessage_sdkVersion", 0);
    if (!a(((Intent)localObject).getByteArrayExtra("_mmessage_checksum"), a(str2, str1, i)))
    {
      Ln.e("checksum fail", new Object[0]);
      return false;
    }
    localObject = ((Intent)localObject).getExtras();
    switch (((Bundle)localObject).getInt("_wxapi_command_type", 0))
    {
    default:
      return false;
    case 1:
      paramk.a(new b((Bundle)localObject));
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return true;
      paramk.a(new e((Bundle)localObject));
      continue;
      paramWechatHandlerActivity.onGetMessageFromWXReq(new c((Bundle)localObject).a);
      continue;
      paramWechatHandlerActivity.onShowMessageFromWXReq(new f((Bundle)localObject).a);
    }
  }

  public boolean b()
  {
    if (!d());
    while (true)
    {
      return false;
      String str = a("_build_info_sdk_int_");
      try
      {
        i = R.parseInt(str);
        if (i < 553844737)
          continue;
        return true;
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          Ln.e(localThrowable);
          int i = -1;
        }
      }
    }
  }

  public boolean c()
  {
    String str = a("_build_info_sdk_int_");
    try
    {
      i = R.parseInt(str);
      if (i >= 553779201)
        return true;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Ln.e(localThrowable);
        int i = -1;
      }
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.j
 * JD-Core Version:    0.6.2
 */