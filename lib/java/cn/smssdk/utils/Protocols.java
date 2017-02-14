package cn.smssdk.utils;

import android.content.Context;
import android.text.TextUtils;
import cn.smssdk.framework.network.e;
import cn.smssdk.framework.utils.Data;
import cn.smssdk.framework.utils.R;
import cn.smssdk.framework.utils.c;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

public class Protocols
{
  private static Protocols a;
  private String appSecrect;
  private String appkey;
  private c b;
  private e c;
  private Context context;
  private b d;
  private cn.smssdk.framework.utils.a e;

  static
  {
    System.loadLibrary("smssdk");
  }

  private Protocols(Context paramContext)
  {
    R.setCacheRoot("SMSSDK");
    this.context = paramContext;
    this.b = new c();
    this.c = new e();
    this.d = b.a(paramContext, "SMSSDK");
    this.e = cn.smssdk.framework.utils.a.a(paramContext);
  }

  public static Protocols a(Context paramContext)
  {
    if (a == null)
      a = new Protocols(paramContext);
    return a;
  }

  private String a(int paramInt)
  {
    try
    {
      String str = "smssdk_error_desc_" + paramInt;
      paramInt = R.getStringRes(this.context, str);
      if (paramInt > 0)
      {
        str = this.context.getString(paramInt);
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      cn.smssdk.framework.utils.d.b(localThrowable);
    }
    return null;
  }

  private String a(byte[] paramArrayOfByte, String paramString)
  {
    paramArrayOfByte = new String(Data.AES128Decode(Data.rawMD5(paramString), paramArrayOfByte), "utf-8");
    if (TextUtils.isEmpty(paramArrayOfByte))
      throw new Throwable("Response is empty");
    return paramArrayOfByte;
  }

  private HashMap<String, Object> a(String paramString1, HashMap<String, Object> paramHashMap, String paramString2, String paramString3, int paramInt, boolean paramBoolean)
  {
    cn.smssdk.framework.utils.d.a("               url: " + paramString1, new Object[0]);
    return httpPost(paramString1, paramHashMap, paramString2, paramString3, paramInt, a(paramHashMap, paramString2, paramBoolean));
  }

  private HashMap<String, Object> a(String paramString1, HashMap<String, Object> paramHashMap1, String paramString2, String paramString3, HashMap<String, Object> paramHashMap2, int paramInt, byte[] paramArrayOfByte)
  {
    if (paramInt >= 5)
    {
      paramString1 = null;
      paramInt = R.getStringRes(this.context, "smssdk_error_desc_server_busy");
      if (paramInt > 0)
        paramString1 = this.context.getString(paramInt);
      paramHashMap2.put("description", paramString1);
      throw new Throwable(this.b.a(paramHashMap2));
    }
    return httpPost(paramString1, paramHashMap1, paramString2, paramString3, paramInt, paramArrayOfByte);
  }

  private HashMap<String, Object> a(String paramString1, HashMap<String, Object> paramHashMap, String paramString2, String paramString3, boolean paramBoolean)
  {
    return a(paramString1, paramHashMap, paramString2, paramString3, 0, paramBoolean);
  }

  private byte[] a(HashMap<String, Object> paramHashMap, String paramString, boolean paramBoolean)
  {
    paramHashMap = this.b.a(paramHashMap);
    cn.smssdk.framework.utils.d.a("data before encode: " + paramHashMap, new Object[0]);
    paramString = Data.AES128Encode(Data.rawMD5(paramString), paramHashMap);
    paramHashMap = paramString;
    if (paramBoolean)
    {
      paramHashMap = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(paramHashMap);
      localGZIPOutputStream.write(paramString);
      localGZIPOutputStream.flush();
      localGZIPOutputStream.close();
      paramHashMap = paramHashMap.toByteArray();
    }
    return paramHashMap;
  }

  private String b()
  {
    return "http://sms.sharesdk.cn:5566/init/duid";
  }

  private String b(int paramInt)
  {
    try
    {
      String str = "smssdk_error_detail_" + paramInt;
      paramInt = R.getStringRes(this.context, str);
      if (paramInt > 0)
      {
        str = this.context.getString(paramInt);
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      cn.smssdk.framework.utils.d.b(localThrowable);
    }
    return null;
  }

  private ArrayList<cn.smssdk.framework.network.d<String>> b(byte[] paramArrayOfByte, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new cn.smssdk.framework.network.d("appkey", this.appkey));
    String str = paramString;
    if (TextUtils.isEmpty(paramString))
      str = "";
    localArrayList.add(new cn.smssdk.framework.network.d("token", str));
    localArrayList.add(new cn.smssdk.framework.network.d("hash", Data.CRC32(paramArrayOfByte)));
    return localArrayList;
  }

  private String c()
  {
    String str = this.d.b();
    if (!TextUtils.isEmpty(str))
      return str;
    str = nativeGetToken();
    if (TextUtils.isEmpty(str))
      throw new Throwable("token is empty");
    this.d.b(str);
    return str;
  }

  private String d()
  {
    return "http://sms.sharesdk.cn:5566/contacts/upload";
  }

  private String e()
  {
    return "http://sms.sharesdk.cn:5566/sms/sendcode";
  }

  private String f()
  {
    return "http://sms.sharesdk.cn:5566/sms/checkcode";
  }

  private String g()
  {
    return "http://sms.sharesdk.cn:5566/utils/zonelist";
  }

  private String getDUID()
  {
    Object localObject = this.d.a();
    if (!TextUtils.isEmpty((CharSequence)localObject))
      return localObject;
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("appkey", this.appkey);
    localHashMap1.put("apppkg", this.e.l());
    localHashMap1.put("appver", this.e.m());
    localHashMap1.put("sdkver", Integer.valueOf(5));
    localHashMap1.put("plat", Integer.valueOf(1));
    localHashMap1.put("network", this.e.k());
    HashMap localHashMap2 = new HashMap();
    String str = this.e.d();
    localObject = str;
    if (str == null)
      localObject = "";
    localHashMap2.put("imei", localObject);
    str = this.e.e();
    localObject = str;
    if (str == null)
      localObject = "";
    localHashMap2.put("serialno", localObject);
    str = this.e.a();
    localObject = str;
    if (str == null)
      localObject = "";
    localHashMap2.put("mac", localObject);
    str = this.e.b();
    localObject = str;
    if (str == null)
      localObject = "";
    localHashMap2.put("model", localObject);
    str = this.e.c();
    localObject = str;
    if (str == null)
      localObject = "";
    localHashMap2.put("factory", localObject);
    str = this.e.h();
    localObject = str;
    if (str == null)
      localObject = "";
    localHashMap2.put("carrier", localObject);
    str = this.e.g();
    localObject = str;
    if (str == null)
      localObject = "";
    localHashMap2.put("screensize", localObject);
    str = this.e.f();
    localObject = str;
    if (str == null)
      localObject = "";
    localHashMap2.put("sysver", localObject);
    localHashMap1.put("deviceinfo", localHashMap2);
    localObject = a(b(), localHashMap1, this.appSecrect, null, false);
    str = (String)((HashMap)((HashMap)localObject).get("result")).get("duid");
    if (TextUtils.isEmpty(str))
      throw new Throwable(this.b.a((HashMap)localObject));
    this.d.a(str);
    return str;
  }

  private String getTokenUrl()
  {
    return "http://sms.sharesdk.cn:5566/token/get";
  }

  private String h()
  {
    return "http://sms.sharesdk.cn:5566/app/submituserinfo";
  }

  private HashMap<String, Object> httpPost(String paramString1, HashMap<String, Object> paramHashMap, String paramString2, String paramString3, int paramInt, byte[] paramArrayOfByte)
  {
    cn.smssdk.framework.network.b localb = new cn.smssdk.framework.network.b();
    localb.a(paramArrayOfByte);
    Object localObject2 = b(paramArrayOfByte, paramString3);
    Object localObject3 = new HashMap();
    try
    {
      this.c.a(paramString1, (ArrayList)localObject2, localb, new a(this, (HashMap)localObject3));
      if ((localObject3 == null) || (((HashMap)localObject3).size() <= 0))
        throw new Throwable("Response is empty");
    }
    catch (Throwable localThrowable)
    {
      cn.smssdk.framework.utils.d.b(localThrowable);
      localObject2 = localThrowable.getMessage();
      try
      {
        localObject2 = this.b.a((String)localObject2);
        i = ((Integer)((HashMap)localObject2).get("status")).intValue();
        if (i == 522)
        {
          this.d.a("");
          this.d.b("");
          return a(paramString1, paramHashMap, paramString2, paramString3, (HashMap)localObject2, paramInt + 1, paramArrayOfByte);
        }
        if (i == 521)
        {
          this.d.b("");
          return a(paramString1, paramHashMap, paramString2, paramString3, (HashMap)localObject2, paramInt + 1, paramArrayOfByte);
        }
        ((HashMap)localObject2).put("description", a(i));
        ((HashMap)localObject2).put("detail", b(i));
        paramString1 = new Throwable(this.b.a((HashMap)localObject2));
        throw paramString1;
      }
      catch (Throwable paramString1)
      {
        while (true)
        {
          cn.smssdk.framework.utils.d.b(paramString1);
          paramString1 = localThrowable;
        }
      }
      Object localObject1 = (byte[])((HashMap)localObject3).get("bResp");
      if ((localObject1 == null) || (localObject1.length <= 0))
        throw new Throwable("Response is empty");
      localObject1 = a((byte[])localObject1, paramString2);
      cn.smssdk.framework.utils.d.a("resp: " + (String)localObject1, new Object[0]);
      localObject2 = this.b.a((String)localObject1);
      if ((localObject2 == null) || (((HashMap)localObject2).size() <= 0))
        throw new Throwable("Response is empty");
      localObject3 = ((HashMap)localObject2).get("status");
      if ((localObject3 == null) || (!(localObject3 instanceof Integer)))
        throw new Throwable((String)localObject1);
      int i = ((Integer)localObject3).intValue();
      if (i != 200)
      {
        if (i == 522)
        {
          this.d.a("");
          this.d.b("");
          return a(paramString1, paramHashMap, paramString2, paramString3, (HashMap)localObject2, paramInt + 1, paramArrayOfByte);
        }
        if (i == 521)
        {
          this.d.b("");
          return a(paramString1, paramHashMap, paramString2, paramString3, (HashMap)localObject2, paramInt + 1, paramArrayOfByte);
        }
        ((HashMap)localObject2).put("description", a(i));
        ((HashMap)localObject2).put("detail", b(i));
        throw new Throwable(this.b.a((HashMap)localObject2));
      }
    }
    return localObject2;
  }

  private String i()
  {
    return "http://sms.sharesdk.cn:5566/contacts/myfriends";
  }

  private native String nativeGetToken();

  public ArrayList<HashMap<String, Object>> a()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appkey", this.appkey);
    String str = getDUID();
    localHashMap.put("duid", str);
    return (ArrayList)((HashMap)a(g(), localHashMap, str, c(), false).get("result")).get("list");
  }

  public ArrayList<HashMap<String, Object>> a(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length <= 0))
      return new ArrayList();
    HashMap localHashMap = new HashMap();
    localHashMap.put("appkey", this.appkey);
    String str = getDUID();
    localHashMap.put("duid", str);
    localHashMap.put("contactphones", TextUtils.join(",", paramArrayOfString));
    return (ArrayList)((HashMap)a(i(), localHashMap, str, c(), false).get("result")).get("list");
  }

  public HashMap<String, Object> a(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appkey", this.appkey);
    String str = getDUID();
    localHashMap.put("duid", str);
    localHashMap.put("phone", paramString3);
    localHashMap.put("code", paramString1);
    localHashMap.put("zone", paramString2);
    a(f(), localHashMap, str, c(), false);
    paramString1 = new HashMap();
    paramString1.put("phone", paramString3);
    paramString1.put("country", paramString2);
    this.d.c();
    return paramString1;
  }

  public void a(String paramString1, String paramString2)
  {
    this.appkey = paramString1;
    this.appSecrect = paramString2;
  }

  public void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("avatar", paramString3);
    localHashMap.put("uid", paramString1);
    localHashMap.put("nickname", paramString2);
    localHashMap.put("appkey", this.appkey);
    localHashMap.put("phone", paramString5);
    localHashMap.put("zone", paramString4);
    paramString1 = getDUID();
    localHashMap.put("duid", paramString1);
    a(h(), localHashMap, paramString1, c(), false);
  }

  public void a(ArrayList<HashMap<String, Object>> paramArrayList)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appkey", this.appkey);
    String str = getDUID();
    localHashMap.put("duid", str);
    localHashMap.put("simserial", this.e.i());
    localHashMap.put("contacts", paramArrayList);
    a(d(), localHashMap, str, c(), true);
  }

  public void b(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appkey", this.appkey);
    String str = getDUID();
    localHashMap.put("duid", str);
    localHashMap.put("phone", paramString2);
    localHashMap.put("zone", paramString1);
    a(e(), localHashMap, str, c(), false);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.utils.Protocols
 * JD-Core Version:    0.6.2
 */