package cn.sharesdk.framework.statistics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.statistics.a.d;
import cn.sharesdk.framework.statistics.a.e;
import cn.sharesdk.framework.statistics.b.b;
import cn.sharesdk.framework.statistics.b.f;
import cn.sharesdk.framework.statistics.b.f.a;
import cn.sharesdk.framework.statistics.b.g;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  public static String b = "http://api.share.mob.com:80";
  static String c;
  static boolean d = false;
  private static a e;
  private static cn.sharesdk.framework.statistics.a.c f;
  private static String g = "http://l.mob.com/url/ShareSdkMapping.do";
  private static String h = "http://up.sharesdk.cn/upload/image";
  Context a;
  private boolean i;
  private boolean j = true;
  private NetworkHelper k = new NetworkHelper();

  public static a a(Context paramContext)
  {
    if (e == null)
    {
      e = new a();
      e.a = paramContext.getApplicationContext();
      f = cn.sharesdk.framework.statistics.a.c.a(e.a);
    }
    return e;
  }

  private String a(Bitmap paramBitmap, a parama)
  {
    try
    {
      File localFile = File.createTempFile("bm_tmp", ".png");
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      paramBitmap = a(localFile.getAbsolutePath(), parama);
      return paramBitmap;
    }
    catch (Throwable paramBitmap)
    {
      Ln.e(paramBitmap);
    }
    return null;
  }

  private String a(String paramString, a parama)
  {
    if ((TextUtils.isEmpty(paramString)) || (!new File(paramString).exists()))
      return null;
    Bitmap.CompressFormat localCompressFormat = BitmapHelper.getBmpFormat(paramString);
    float f1 = 200.0F;
    if (parama == a.b)
      f1 = 600.0F;
    parama = new BitmapFactory.Options();
    parama.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, parama);
    parama.inJustDecodeBounds = false;
    int m = parama.outWidth;
    int n = parama.outHeight;
    if ((m >= n) && (n > f1))
      m = (int)Math.ceil(parama.outHeight / f1);
    while (true)
    {
      n = m;
      if (m <= 0)
        n = 1;
      parama.inSampleSize = n;
      parama.inPurgeable = true;
      parama.inInputShareable = true;
      try
      {
        paramString = BitmapFactory.decodeFile(paramString, parama);
        paramString.getHeight();
        paramString.getWidth();
        parama = File.createTempFile("bm_tmp2", "." + localCompressFormat.name().toLowerCase());
        FileOutputStream localFileOutputStream = new FileOutputStream(parama);
        paramString.compress(localCompressFormat, 80, localFileOutputStream);
        localFileOutputStream.flush();
        localFileOutputStream.close();
        paramString = h(parama.getAbsolutePath());
        return paramString;
        if ((m < n) && (m > f1))
          m = (int)Math.ceil(parama.outWidth / f1);
        else
          return h(paramString);
      }
      catch (Throwable paramString)
      {
        Ln.e(paramString);
      }
    }
    return null;
  }

  private String a(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    if ((TextUtils.isEmpty(paramString1)) || (!this.j));
    while (true)
    {
      return paramString1;
      Object localObject1 = new ArrayList();
      paramString3 = Pattern.compile(paramString3);
      Object localObject2 = paramString3.matcher(paramString1);
      while (((Matcher)localObject2).find())
      {
        String str = ((Matcher)localObject2).group();
        if ((str != null) && (str.length() > 0))
          ((ArrayList)localObject1).add(str);
      }
      if (((ArrayList)localObject1).size() != 0)
      {
        localObject2 = new ArrayList();
        ((ArrayList)localObject2).add(new KVPair("key", paramString2));
        int m = 0;
        while (m < ((ArrayList)localObject1).size())
        {
          ((ArrayList)localObject2).add(new KVPair("urls", ((String)((ArrayList)localObject1).get(m)).toString()));
          m += 1;
        }
        ((ArrayList)localObject2).add(new KVPair("deviceid", DeviceHelper.getInstance(this.a).getDeviceKey()));
        ((ArrayList)localObject2).add(new KVPair("snsplat", String.valueOf(paramInt)));
        paramString2 = d(paramString2, paramString4);
        if (!TextUtils.isEmpty(paramString2))
        {
          ((ArrayList)localObject2).add(new KVPair("m", paramString2));
          paramString2 = new ArrayList();
          paramString2.add(new KVPair("User-Agent", c));
          paramString4 = new ArrayList();
          paramString4.add(new KVPair("http.socket.timeout", Integer.valueOf(5000)));
          paramString4.add(new KVPair("http.connection.timeout", Integer.valueOf(5000)));
          try
          {
            paramString2 = this.k.httpPost(g, (ArrayList)localObject2, null, paramString2, paramString4);
            if (TextUtils.isEmpty(paramString2))
            {
              this.j = false;
              return paramString1;
            }
          }
          catch (Throwable paramString2)
          {
            while (true)
            {
              Ln.e(paramString2);
              paramString2 = null;
            }
            paramString2 = new Hashon().fromJson(paramString2);
            try
            {
              paramInt = ((Integer)paramString2.get("status")).intValue();
              if (paramInt == 200)
              {
                paramString4 = (ArrayList)paramString2.get("data");
                paramString2 = new HashMap();
                paramString4 = paramString4.iterator();
                while (paramString4.hasNext())
                {
                  localObject1 = (HashMap)paramString4.next();
                  localObject2 = String.valueOf(((HashMap)localObject1).get("surl"));
                  paramString2.put(String.valueOf(((HashMap)localObject1).get("source")), localObject2);
                }
              }
            }
            catch (Throwable paramString4)
            {
              while (true)
              {
                Ln.e(paramString4);
                paramInt = -1;
              }
              paramString3 = paramString3.matcher(paramString1);
              paramString4 = new StringBuilder();
              for (paramInt = 0; paramString3.find(); paramInt = paramString3.end())
              {
                paramString4.append(paramString1.substring(paramInt, paramString3.start()));
                paramString4.append((String)paramString2.get(paramString3.group()));
              }
              paramString4.append(paramString1.substring(paramInt, paramString1.length()));
              paramString1 = paramString4.toString();
              Ln.w("> SERVER_SHORT_LINK_URL content after replace link ===  %s", new Object[] { paramString1 });
            }
          }
        }
      }
    }
    return paramString1;
  }

  // ERROR //
  private String a(String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_2
    //   1: aload_1
    //   2: invokestatic 359	com/mob/tools/utils/Data:AES128Encode	([BLjava/lang/String;)[B
    //   5: iconst_0
    //   6: invokestatic 365	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   9: astore_1
    //   10: aload_1
    //   11: astore_2
    //   12: aload_1
    //   13: ldc_w 367
    //   16: invokevirtual 370	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   19: ifeq +14 -> 33
    //   22: aload_1
    //   23: ldc_w 367
    //   26: ldc_w 372
    //   29: invokevirtual 376	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   32: astore_2
    //   33: aload_2
    //   34: areturn
    //   35: astore_2
    //   36: aconst_null
    //   37: astore_1
    //   38: aload_2
    //   39: invokestatic 117	com/mob/tools/utils/Ln:e	(Ljava/lang/Throwable;)I
    //   42: pop
    //   43: aload_1
    //   44: areturn
    //   45: astore_2
    //   46: goto -8 -> 38
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	35	java/lang/Throwable
    //   12	33	45	java/lang/Throwable
  }

  private String b()
  {
    return b + "/date";
  }

  private String c()
  {
    return b + "/log4";
  }

  private boolean c(String paramString1, String paramString2)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new KVPair("m", paramString1));
      localArrayList.add(new KVPair("t", paramString2));
      paramString1 = new ArrayList();
      paramString1.add(new KVPair("User-Agent", c));
      paramString2 = new ArrayList();
      paramString2.add(new KVPair("http.socket.timeout", Integer.valueOf(30000)));
      paramString2.add(new KVPair("http.connection.timeout", Integer.valueOf(30000)));
      paramString1 = this.k.httpPost(c(), localArrayList, null, paramString1, paramString2);
      Ln.i("> %s  resp: %s", new Object[] { c(), paramString1 });
      if (paramString1 != null)
        return true;
    }
    catch (Throwable paramString1)
    {
      Ln.e(paramString1);
      return false;
    }
    return false;
  }

  private String d()
  {
    return b + "/data2";
  }

  private String d(String paramString1, String paramString2)
  {
    DeviceHelper localDeviceHelper = DeviceHelper.getInstance(this.a);
    boolean bool1 = f.b();
    boolean bool2 = f.c();
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      try
      {
        localStringBuilder.append(Data.urlEncode(localDeviceHelper.getPackageName(), "utf-8")).append("|");
        localStringBuilder.append(Data.urlEncode(localDeviceHelper.getAppVersionName(), "utf-8")).append("|");
        localStringBuilder.append(Data.urlEncode(String.valueOf(50000 + ShareSDK.getSDKVersionCode()), "utf-8")).append("|");
        localStringBuilder.append(Data.urlEncode(String.valueOf(localDeviceHelper.getPlatformCode()), "utf-8")).append("|");
        localStringBuilder.append(Data.urlEncode(localDeviceHelper.getDetailNetworkTypeForStatic(), "utf-8")).append("|");
        if (bool1)
        {
          localStringBuilder.append(Data.urlEncode(localDeviceHelper.getOSVersion(), "utf-8")).append("|");
          localStringBuilder.append(Data.urlEncode(localDeviceHelper.getScreenSize(), "utf-8")).append("|");
          localStringBuilder.append(Data.urlEncode(localDeviceHelper.getManufacturer(), "utf-8")).append("|");
          localStringBuilder.append(Data.urlEncode(localDeviceHelper.getModel(), "utf-8")).append("|");
          localStringBuilder.append(Data.urlEncode(localDeviceHelper.getCarrier(), "utf-8")).append("|");
          if (bool2)
          {
            localStringBuilder.append(paramString2);
            return a(localStringBuilder.toString(), Data.rawMD5(String.format("%s:%s", new Object[] { localDeviceHelper.getDeviceKey(), paramString1 })));
          }
        }
        else
        {
          localStringBuilder.append("|||||");
          continue;
        }
      }
      catch (Throwable paramString1)
      {
        Ln.e(paramString1);
        return "";
      }
      localStringBuilder.append(paramString2.split("\\|")[0]);
      localStringBuilder.append("|||||");
    }
  }

  private String e()
  {
    return b + "/snsconf";
  }

  private String f()
  {
    return b + "/conf4";
  }

  private String g()
  {
    return b + "/conn";
  }

  private long h()
  {
    if (!f.h())
      return 0L;
    Object localObject = "{}";
    try
    {
      String str = this.k.httpGet(b(), null, null, null);
      localObject = str;
      localObject = new Hashon().fromJson((String)localObject);
      if (((HashMap)localObject).containsKey("timestamp"))
        try
        {
          long l = R.parseLong(String.valueOf(((HashMap)localObject).get("timestamp")));
          l = System.currentTimeMillis() - l;
          f.a("service_time", Long.valueOf(l));
          return l;
        }
        catch (Throwable localThrowable1)
        {
          Ln.w(localThrowable1);
          return f.a();
        }
    }
    catch (Throwable localThrowable2)
    {
      while (true)
        Ln.e(localThrowable2);
    }
    return f.a();
  }

  private String h(String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = h;
    Ln.i(" upload file , server url = %s, file path = %s", new Object[] { localObject2, paramString });
    while (true)
    {
      try
      {
        paramString = new KVPair("file", paramString);
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new KVPair("User-Agent", c));
        localObject2 = this.k.httpPost((String)localObject2, null, paramString, localArrayList, null);
        Ln.i("upload file response == %s", new Object[] { localObject2 });
        paramString = localObject1;
        if (localObject2 != null)
        {
          paramString = localObject1;
          if (((String)localObject2).length() > 0)
          {
            localObject2 = new Hashon().fromJson((String)localObject2);
            boolean bool = ((HashMap)localObject2).containsKey("status");
            if (!bool)
              break label215;
          }
        }
        try
        {
          m = R.parseInt(String.valueOf(((HashMap)localObject2).get("status")));
          paramString = localObject1;
          if (m == 200)
          {
            if (((HashMap)localObject2).containsKey("url"))
              paramString = ((HashMap)localObject2).get("url").toString();
          }
          else
            return paramString;
        }
        catch (Throwable paramString)
        {
          m = -1;
          continue;
          paramString = null;
          continue;
        }
      }
      catch (Throwable paramString)
      {
        Ln.e(paramString);
        return null;
      }
      label215: int m = -1;
    }
  }

  private String i(String paramString)
  {
    paramString = new ByteArrayInputStream(paramString.getBytes());
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      localObject = new GZIPOutputStream(localByteArrayOutputStream);
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int m = paramString.read(arrayOfByte, 0, 1024);
        if (m == -1)
          break;
        ((GZIPOutputStream)localObject).write(arrayOfByte, 0, m);
      }
    }
    catch (IOException paramString)
    {
      Ln.e(paramString);
      return null;
    }
    ((GZIPOutputStream)localObject).flush();
    ((GZIPOutputStream)localObject).close();
    Object localObject = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.flush();
    localByteArrayOutputStream.close();
    paramString.close();
    paramString = Base64.encodeToString((byte[])localObject, 2);
    return paramString;
  }

  private JSONObject i()
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject = DeviceHelper.getInstance(this.a);
    try
    {
      localJSONObject.put("mac", ((DeviceHelper)localObject).getMacAddress());
      localJSONObject.put("udid", ((DeviceHelper)localObject).getDeviceId());
      localJSONObject.put("model", ((DeviceHelper)localObject).getModel());
      localJSONObject.put("factory", ((DeviceHelper)localObject).getManufacturer());
      localJSONObject.put("plat", ((DeviceHelper)localObject).getPlatformCode());
      localJSONObject.put("sysver", ((DeviceHelper)localObject).getOSVersion());
      localJSONObject.put("breaked", false);
      localJSONObject.put("screensize", ((DeviceHelper)localObject).getScreenSize());
      localJSONObject.put("androidid", ((DeviceHelper)localObject).getAndroidID());
      localObject = ((DeviceHelper)localObject).getAdvertisingID();
      if (!TextUtils.isEmpty((CharSequence)localObject))
        localJSONObject.put("adsid", localObject);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      Ln.e(localJSONException);
    }
    return localJSONObject;
  }

  private String j(String paramString)
  {
    JSONObject localJSONObject = i();
    DeviceHelper localDeviceHelper = DeviceHelper.getInstance(this.a);
    try
    {
      localJSONObject.put("type", "DEVICE");
      localJSONObject.put("key", localDeviceHelper.getDeviceKey());
      localJSONObject.put("carrier", localDeviceHelper.getCarrier());
      localJSONObject.put("appkey", paramString);
      localJSONObject.put("apppkg", localDeviceHelper.getPackageName());
      localJSONObject.put("appver", String.valueOf(localDeviceHelper.getAppVersion()));
      localJSONObject.put("sdkver", 50000 + ShareSDK.getSDKVersionCode());
      localJSONObject.put("networktype", localDeviceHelper.getDetailNetworkTypeForStatic());
      return localJSONObject.toString();
    }
    catch (JSONException paramString)
    {
      while (true)
        Ln.e(paramString);
    }
  }

  private JSONObject j()
  {
    JSONObject localJSONObject = new JSONObject();
    DeviceHelper localDeviceHelper = DeviceHelper.getInstance(this.a);
    try
    {
      localJSONObject.put("type", "DEVEXT");
      localJSONObject.put("plat", localDeviceHelper.getPlatformCode());
      localJSONObject.put("device", localDeviceHelper.getDeviceKey());
      localJSONObject.put("phonename", localDeviceHelper.getBluetoothName());
      localJSONObject.put("signmd5", localDeviceHelper.getSignMD5());
      if (localDeviceHelper.getDetailNetworkTypeForStatic().equals("wifi"))
      {
        localJSONObject.put("ssid", localDeviceHelper.getSSID());
        localJSONObject.put("bssid", localDeviceHelper.getBssid());
      }
      Ln.d(" networkType == getDeviceExtInfo == %s", new Object[] { localJSONObject.toString() });
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        Ln.e(localJSONException);
    }
  }

  public String a(Bitmap paramBitmap)
  {
    return a(paramBitmap, a.b);
  }

  public String a(String paramString1, String paramString2, int paramInt, boolean paramBoolean, String paramString3)
  {
    if (!f.h());
    do
    {
      return paramString1;
      Ln.w("> SERVER_SHORT_LINK_URL content before replace link ===  %s", new Object[] { paramString1 });
      if (paramBoolean)
      {
        String str = a(paramString1, paramString2, "<a[^>]*?href[\\s]*=[\\s]*[\"']?([^'\">]+?)['\"]?>", paramInt, paramString3);
        if ((str != null) && (!str.equals(paramString1)))
          return str;
      }
      paramString2 = a(paramString1, paramString2, "(http://|https://){1}[\\w\\.\\-/:\\?&%=,;\\[\\]\\{\\}`~!@#\\$\\^\\*\\(\\)_\\+\\\\\\|]+", paramInt, paramString3);
    }
    while ((paramString2 == null) || (paramString2.equals(paramString1)));
    return paramString2;
  }

  public void a()
  {
    if (!f.h());
    while ("none".equals(DeviceHelper.getInstance(this.a).getDetailNetworkTypeForStatic()))
      return;
    ArrayList localArrayList = e.a(this.a);
    int m = 0;
    label39: d locald;
    if (m < localArrayList.size())
    {
      locald = (d)localArrayList.get(m);
      if (locald.b.size() != 1)
        break label106;
    }
    label106: for (boolean bool = c(locald.a, "0"); ; bool = c(i(locald.a), "1"))
    {
      if (bool)
        e.a(this.a, locald.b);
      m += 1;
      break label39;
      break;
    }
  }

  public void a(cn.sharesdk.framework.statistics.b.c paramc)
  {
    if (!f.h())
      return;
    Object localObject1 = DeviceHelper.getInstance(this.a);
    Object localObject2 = ((DeviceHelper)localObject1).getDetailNetworkTypeForStatic();
    String str;
    if (((paramc instanceof g)) && (!((String)localObject2).endsWith("none")))
    {
      localObject2 = ((DeviceHelper)localObject1).getPackageName() + "/" + ((DeviceHelper)localObject1).getAppVersionName();
      str = "ShareSDK/" + ShareSDK.getSDKVersionName();
      localObject1 = "Android/" + ((DeviceHelper)localObject1).getOSVersion();
      c = (String)localObject2 + " " + str + " " + (String)localObject1;
    }
    while (true)
    {
      if (!f.b())
        paramc.m = null;
      long l2 = f.a();
      long l1 = l2;
      if (l2 == 0L)
        l1 = h();
      paramc.e = (System.currentTimeMillis() - l1);
      Ln.i(" insert event in DB == %s", new Object[] { paramc.toString() });
      e.a(this.a, paramc.toString(), paramc.e);
      return;
      boolean bool;
      if ((paramc instanceof b))
      {
        bool = f.c();
        localObject1 = ((b)paramc).c;
        if ((bool) && (!TextUtils.isEmpty((CharSequence)localObject1)))
        {
          ((b)paramc).c = Data.Base64AES((String)localObject1, paramc.f.substring(0, 16));
        }
        else
        {
          ((b)paramc).d = null;
          ((b)paramc).c = null;
        }
      }
      else if ((paramc instanceof f))
      {
        localObject1 = (f)paramc;
        int m = f.d();
        bool = f.c();
        localObject2 = ((f)localObject1).d;
        if ((m == 1) || ((m == 0) && (this.i)))
        {
          if ((localObject2 == null) || (((f.a)localObject2).e == null));
          int n;
          for (m = 0; ; m = ((f.a)localObject2).e.size())
          {
            n = 0;
            while (n < m)
            {
              str = a((String)((f.a)localObject2).e.get(n), a.a);
              if (str != null)
                ((f.a)localObject2).d.add(str);
              n += 1;
            }
          }
          if ((localObject2 == null) || (((f.a)localObject2).f == null));
          for (m = 0; ; m = ((f.a)localObject2).f.size())
          {
            n = 0;
            while (n < m)
            {
              str = a((Bitmap)((f.a)localObject2).f.get(n), a.a);
              if (str != null)
                ((f.a)localObject2).d.add(str);
              n += 1;
            }
          }
        }
        ((f)localObject1).d = null;
        if (!bool)
          ((f)localObject1).n = null;
      }
    }
  }

  public void a(String paramString1, String paramString2)
  {
    f.b(paramString1, paramString2);
  }

  public void a(String paramString, ArrayList<HashMap<String, String>> paramArrayList)
  {
    if (!f.h())
      return;
    Object localObject = new HashMap();
    ((HashMap)localObject).put("type", paramString);
    paramString = DeviceHelper.getInstance(this.a);
    ((HashMap)localObject).put("plat", Integer.valueOf(paramString.getPlatformCode()));
    ((HashMap)localObject).put("device", paramString.getDeviceKey());
    ((HashMap)localObject).put("list", paramArrayList);
    paramArrayList = new Hashon().fromHashMap((HashMap)localObject);
    Ln.d(" upload apps info == %s", new Object[] { paramArrayList });
    paramString = new ArrayList();
    paramString.add(new KVPair("m", Data.Base64AES(paramArrayList, "sdk.sharesdk.sdk")));
    paramArrayList = new ArrayList();
    paramArrayList.add(new KVPair("User-Agent", c));
    localObject = new ArrayList();
    ((ArrayList)localObject).add(new KVPair("http.socket.timeout", Integer.valueOf(30000)));
    ((ArrayList)localObject).add(new KVPair("http.connection.timeout", Integer.valueOf(30000)));
    Ln.i("> APPS_UNFINISHED  resp: %s", new Object[] { this.k.httpPost(d(), paramString, null, paramArrayList, (ArrayList)localObject) });
  }

  public void a(boolean paramBoolean)
  {
    this.i = paramBoolean;
  }

  public boolean a(String paramString)
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).setTimeInMillis(f.i().longValue());
    int m = ((Calendar)localObject).get(1);
    int n = ((Calendar)localObject).get(2);
    int i1 = ((Calendar)localObject).get(5);
    ((Calendar)localObject).setTimeInMillis(System.currentTimeMillis());
    int i2 = ((Calendar)localObject).get(1);
    int i3 = ((Calendar)localObject).get(2);
    int i4 = ((Calendar)localObject).get(5);
    boolean bool2;
    if ((m == i2) || (n == i3) || (i1 == i4))
      bool2 = f.h();
    while (true)
    {
      return bool2;
      try
      {
        localObject = new ArrayList();
        ((ArrayList)localObject).add(new KVPair("appkey", paramString));
        paramString = new ArrayList();
        paramString.add(new KVPair("User-Agent", c));
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new KVPair("http.socket.timeout", Integer.valueOf(30000)));
        localArrayList.add(new KVPair("http.connection.timeout", Integer.valueOf(30000)));
        localObject = this.k.httpPost(g(), (ArrayList)localObject, null, paramString, localArrayList);
        paramString = (String)localObject;
        if (TextUtils.isEmpty((CharSequence)localObject))
          paramString = "{}";
        Ln.i(" get server connection response == %s", new Object[] { paramString });
        localObject = new Hashon().fromJson(paramString);
        if (((HashMap)localObject).containsKey("res"))
        {
          bool1 = Boolean.parseBoolean(((HashMap)localObject).get("res").toString());
          f.a(bool1);
          bool2 = bool1;
          if ("{}".equals(paramString))
            continue;
          f.b(System.currentTimeMillis());
          return bool1;
        }
      }
      catch (Throwable paramString)
      {
        while (true)
        {
          Ln.e(paramString);
          paramString = "{}";
          continue;
          boolean bool1 = true;
        }
      }
    }
  }

  // ERROR //
  public long b(String paramString)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_3
    //   2: getstatic 69	cn/sharesdk/framework/statistics/a:f	Lcn/sharesdk/framework/statistics/a/c;
    //   5: invokevirtual 464	cn/sharesdk/framework/statistics/a/c:h	()Z
    //   8: ifne +5 -> 13
    //   11: lconst_0
    //   12: lreturn
    //   13: aload_0
    //   14: getfield 62	cn/sharesdk/framework/statistics/a:a	Landroid/content/Context;
    //   17: invokestatic 261	com/mob/tools/utils/DeviceHelper:getInstance	(Landroid/content/Context;)Lcom/mob/tools/utils/DeviceHelper;
    //   20: astore 10
    //   22: getstatic 69	cn/sharesdk/framework/statistics/a:f	Lcn/sharesdk/framework/statistics/a/c;
    //   25: invokestatic 488	java/lang/System:currentTimeMillis	()J
    //   28: invokevirtual 810	cn/sharesdk/framework/statistics/a/c:a	(J)V
    //   31: new 210	java/util/ArrayList
    //   34: dup
    //   35: invokespecial 211	java/util/ArrayList:<init>	()V
    //   38: astore 9
    //   40: aload 9
    //   42: new 241	com/mob/tools/network/KVPair
    //   45: dup
    //   46: ldc_w 607
    //   49: aload_1
    //   50: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   53: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   56: pop
    //   57: aload 9
    //   59: new 241	com/mob/tools/network/KVPair
    //   62: dup
    //   63: ldc_w 623
    //   66: aload 10
    //   68: invokevirtual 264	com/mob/tools/utils/DeviceHelper:getDeviceKey	()Ljava/lang/String;
    //   71: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   74: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   77: pop
    //   78: aload 9
    //   80: new 241	com/mob/tools/network/KVPair
    //   83: dup
    //   84: ldc_w 575
    //   87: aload 10
    //   89: invokevirtual 417	com/mob/tools/utils/DeviceHelper:getPlatformCode	()I
    //   92: invokestatic 270	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   95: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   98: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   101: pop
    //   102: aload 9
    //   104: new 241	com/mob/tools/network/KVPair
    //   107: dup
    //   108: ldc_w 609
    //   111: aload 10
    //   113: invokevirtual 398	com/mob/tools/utils/DeviceHelper:getPackageName	()Ljava/lang/String;
    //   116: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   119: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   122: pop
    //   123: aload 9
    //   125: new 241	com/mob/tools/network/KVPair
    //   128: dup
    //   129: ldc_w 611
    //   132: aload 10
    //   134: invokevirtual 614	com/mob/tools/utils/DeviceHelper:getAppVersion	()I
    //   137: invokestatic 270	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   140: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   143: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   146: pop
    //   147: aload 9
    //   149: new 241	com/mob/tools/network/KVPair
    //   152: dup
    //   153: ldc_w 616
    //   156: ldc_w 409
    //   159: invokestatic 414	cn/sharesdk/framework/ShareSDK:getSDKVersionCode	()I
    //   162: iadd
    //   163: invokestatic 270	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   166: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   169: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   172: pop
    //   173: aload 9
    //   175: new 241	com/mob/tools/network/KVPair
    //   178: dup
    //   179: ldc_w 618
    //   182: aload 10
    //   184: invokevirtual 420	com/mob/tools/utils/DeviceHelper:getDetailNetworkTypeForStatic	()Ljava/lang/String;
    //   187: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   190: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   193: pop
    //   194: new 210	java/util/ArrayList
    //   197: dup
    //   198: invokespecial 211	java/util/ArrayList:<init>	()V
    //   201: astore 10
    //   203: aload 10
    //   205: new 241	com/mob/tools/network/KVPair
    //   208: dup
    //   209: ldc_w 277
    //   212: getstatic 279	cn/sharesdk/framework/statistics/a:c	Ljava/lang/String;
    //   215: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   218: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   221: pop
    //   222: new 210	java/util/ArrayList
    //   225: dup
    //   226: invokespecial 211	java/util/ArrayList:<init>	()V
    //   229: astore 11
    //   231: aload 11
    //   233: new 241	com/mob/tools/network/KVPair
    //   236: dup
    //   237: ldc_w 281
    //   240: sipush 10000
    //   243: invokestatic 286	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   246: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   249: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   252: pop
    //   253: aload 11
    //   255: new 241	com/mob/tools/network/KVPair
    //   258: dup
    //   259: ldc_w 288
    //   262: sipush 10000
    //   265: invokestatic 286	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   268: invokespecial 246	com/mob/tools/network/KVPair:<init>	(Ljava/lang/String;Ljava/lang/Object;)V
    //   271: invokevirtual 236	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   274: pop
    //   275: aload_0
    //   276: getfield 50	cn/sharesdk/framework/statistics/a:k	Lcom/mob/tools/network/NetworkHelper;
    //   279: aload_0
    //   280: invokespecial 812	cn/sharesdk/framework/statistics/a:f	()Ljava/lang/String;
    //   283: aload 9
    //   285: aconst_null
    //   286: aload 10
    //   288: aload 11
    //   290: invokevirtual 292	com/mob/tools/network/NetworkHelper:httpPost	(Ljava/lang/String;Ljava/util/ArrayList;Lcom/mob/tools/network/KVPair;Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/lang/String;
    //   293: astore 10
    //   295: aload 10
    //   297: astore 9
    //   299: aload 10
    //   301: invokestatic 123	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   304: ifeq +8 -> 312
    //   307: ldc_w 466
    //   310: astore 9
    //   312: ldc_w 814
    //   315: iconst_1
    //   316: anewarray 4	java/lang/Object
    //   319: dup
    //   320: iconst_0
    //   321: aload 9
    //   323: aastore
    //   324: invokestatic 389	com/mob/tools/utils/Ln:i	(Ljava/lang/Object;[Ljava/lang/Object;)I
    //   327: pop
    //   328: new 294	com/mob/tools/utils/Hashon
    //   331: dup
    //   332: invokespecial 295	com/mob/tools/utils/Hashon:<init>	()V
    //   335: aload 9
    //   337: invokevirtual 299	com/mob/tools/utils/Hashon:fromJson	(Ljava/lang/String;)Ljava/util/HashMap;
    //   340: astore 10
    //   342: aload 10
    //   344: ldc_w 301
    //   347: invokevirtual 477	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   350: ifeq +81 -> 431
    //   353: aload 10
    //   355: ldc_w 301
    //   358: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   361: invokestatic 330	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   364: invokestatic 512	com/mob/tools/utils/R:parseInt	(Ljava/lang/String;)I
    //   367: istore_2
    //   368: iload_2
    //   369: sipush -200
    //   372: if_icmpne +59 -> 431
    //   375: invokestatic 817	cn/sharesdk/framework/ShareSDK:isDebug	()Z
    //   378: ifeq -367 -> 11
    //   381: getstatic 821	java/lang/System:err	Ljava/io/PrintStream;
    //   384: aload 10
    //   386: ldc_w 823
    //   389: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   392: invokestatic 330	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   395: invokevirtual 828	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   398: lconst_0
    //   399: lreturn
    //   400: astore 9
    //   402: aload 9
    //   404: invokestatic 117	com/mob/tools/utils/Ln:e	(Ljava/lang/Throwable;)I
    //   407: pop
    //   408: ldc_w 466
    //   411: astore 9
    //   413: goto -85 -> 328
    //   416: astore 9
    //   418: aload 9
    //   420: invokestatic 500	com/mob/tools/utils/Ln:w	(Ljava/lang/Throwable;)I
    //   423: pop
    //   424: sipush -200
    //   427: istore_2
    //   428: goto -60 -> 368
    //   431: aload 10
    //   433: ldc_w 474
    //   436: invokevirtual 477	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   439: ifeq +268 -> 707
    //   442: aload 10
    //   444: ldc_w 474
    //   447: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   450: invokevirtual 515	java/lang/Object:toString	()Ljava/lang/String;
    //   453: invokestatic 483	com/mob/tools/utils/R:parseLong	(Ljava/lang/String;)J
    //   456: lstore 5
    //   458: lload 5
    //   460: lstore_3
    //   461: invokestatic 488	java/lang/System:currentTimeMillis	()J
    //   464: lstore 7
    //   466: lload 7
    //   468: lload 5
    //   470: lsub
    //   471: lstore_3
    //   472: getstatic 69	cn/sharesdk/framework/statistics/a:f	Lcn/sharesdk/framework/statistics/a/c;
    //   475: ldc_w 490
    //   478: lload_3
    //   479: invokestatic 495	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   482: invokevirtual 498	cn/sharesdk/framework/statistics/a/c:a	(Ljava/lang/String;Ljava/lang/Long;)V
    //   485: aload 10
    //   487: ldc_w 830
    //   490: invokevirtual 477	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   493: ifeq +87 -> 580
    //   496: new 303	java/util/HashMap
    //   499: dup
    //   500: invokespecial 312	java/util/HashMap:<init>	()V
    //   503: pop
    //   504: aload 10
    //   506: ldc_w 830
    //   509: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   512: checkcast 303	java/util/HashMap
    //   515: astore 12
    //   517: aload 12
    //   519: ldc_w 623
    //   522: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   525: invokevirtual 515	java/lang/Object:toString	()Ljava/lang/String;
    //   528: astore 9
    //   530: aload 12
    //   532: ldc_w 832
    //   535: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   538: invokevirtual 515	java/lang/Object:toString	()Ljava/lang/String;
    //   541: astore 11
    //   543: aload 12
    //   545: ldc_w 834
    //   548: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   551: invokevirtual 515	java/lang/Object:toString	()Ljava/lang/String;
    //   554: astore 12
    //   556: getstatic 69	cn/sharesdk/framework/statistics/a:f	Lcn/sharesdk/framework/statistics/a/c;
    //   559: aload 9
    //   561: invokevirtual 836	cn/sharesdk/framework/statistics/a/c:d	(Ljava/lang/String;)V
    //   564: getstatic 69	cn/sharesdk/framework/statistics/a:f	Lcn/sharesdk/framework/statistics/a/c;
    //   567: aload 11
    //   569: invokevirtual 838	cn/sharesdk/framework/statistics/a/c:f	(Ljava/lang/String;)V
    //   572: getstatic 69	cn/sharesdk/framework/statistics/a:f	Lcn/sharesdk/framework/statistics/a/c;
    //   575: aload 12
    //   577: invokevirtual 840	cn/sharesdk/framework/statistics/a/c:e	(Ljava/lang/String;)V
    //   580: aload 10
    //   582: ldc_w 842
    //   585: invokevirtual 477	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   588: ifeq +91 -> 679
    //   591: aload 10
    //   593: ldc_w 844
    //   596: invokevirtual 477	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   599: ifeq +80 -> 679
    //   602: aload 10
    //   604: ldc_w 842
    //   607: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   610: invokestatic 330	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   613: astore 9
    //   615: aload 10
    //   617: ldc_w 844
    //   620: invokevirtual 306	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   623: invokestatic 330	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   626: astore 10
    //   628: aload 9
    //   630: invokestatic 123	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   633: ifne +46 -> 679
    //   636: aload 10
    //   638: invokestatic 123	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   641: ifne +38 -> 679
    //   644: new 186	java/lang/StringBuilder
    //   647: dup
    //   648: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   651: ldc_w 846
    //   654: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   657: aload 9
    //   659: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: ldc_w 848
    //   665: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   668: aload 10
    //   670: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: invokevirtual 204	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   676: putstatic 31	cn/sharesdk/framework/statistics/a:b	Ljava/lang/String;
    //   679: aload_0
    //   680: aload_1
    //   681: invokevirtual 849	cn/sharesdk/framework/statistics/a:d	(Ljava/lang/String;)V
    //   684: aload_0
    //   685: aload_1
    //   686: invokevirtual 851	cn/sharesdk/framework/statistics/a:c	(Ljava/lang/String;)V
    //   689: lload_3
    //   690: lreturn
    //   691: astore 9
    //   693: aload 9
    //   695: invokestatic 500	com/mob/tools/utils/Ln:w	(Ljava/lang/Throwable;)I
    //   698: pop
    //   699: goto -214 -> 485
    //   702: astore 9
    //   704: goto -11 -> 693
    //   707: lconst_0
    //   708: lstore_3
    //   709: goto -224 -> 485
    //
    // Exception table:
    //   from	to	target	type
    //   31	295	400	java/lang/Throwable
    //   299	307	400	java/lang/Throwable
    //   312	328	400	java/lang/Throwable
    //   353	368	416	java/lang/Throwable
    //   442	458	691	java/lang/Throwable
    //   461	466	691	java/lang/Throwable
    //   472	485	702	java/lang/Throwable
  }

  public String b(String paramString1, String paramString2)
  {
    paramString2 = Base64.decode(paramString2, 0);
    String str = DeviceHelper.getInstance(this.a).getDeviceKey();
    return new String(Data.AES128Decode(Data.rawMD5(paramString1 + ":" + str), paramString2), "UTF-8");
  }

  public void c(String paramString)
  {
    if (!f.h());
    while (true)
    {
      return;
      try
      {
        paramString = f.f();
        Object localObject = Data.Base64AES(j().toString(), "sdk.sharesdk.sdk");
        if (!((String)localObject).equals(paramString))
        {
          f.i((String)localObject);
          paramString = new ArrayList();
          paramString.add(new KVPair("m", localObject));
          localObject = new ArrayList();
          ((ArrayList)localObject).add(new KVPair("User-Agent", c));
          ArrayList localArrayList = new ArrayList();
          localArrayList.add(new KVPair("http.socket.timeout", Integer.valueOf(10000)));
          localArrayList.add(new KVPair("http.connection.timeout", Integer.valueOf(10000)));
          Ln.i("> DEVICE_EXT_DATA_UNFINISHED  resp: %s", new Object[] { this.k.httpPost(d(), paramString, null, (ArrayList)localObject, localArrayList) });
          return;
        }
      }
      catch (Throwable paramString)
      {
        Ln.e(paramString);
      }
    }
  }

  public void d(String paramString)
  {
    if (!f.h());
    while (true)
    {
      return;
      try
      {
        Object localObject1 = f.e().trim();
        Object localObject2 = i().toString().trim();
        if (!((String)localObject2).equals(localObject1))
        {
          f.h((String)localObject2);
          Ln.d(" curBaseDeviceInfo == %s", new Object[] { localObject2 });
          localObject1 = new ArrayList();
          ((ArrayList)localObject1).add(new KVPair("m", Data.Base64AES(j(paramString), "sdk.sharesdk.sdk")));
          paramString = new ArrayList();
          paramString.add(new KVPair("User-Agent", c));
          localObject2 = new ArrayList();
          ((ArrayList)localObject2).add(new KVPair("http.socket.timeout", Integer.valueOf(30000)));
          ((ArrayList)localObject2).add(new KVPair("http.connection.timeout", Integer.valueOf(30000)));
          Ln.i("> DEVICE_UNFINISHED  resp: %s", new Object[] { this.k.httpPost(d(), (ArrayList)localObject1, null, paramString, (ArrayList)localObject2) });
          return;
        }
      }
      catch (Throwable paramString)
      {
        Ln.e(paramString);
      }
    }
  }

  public String e(String paramString)
  {
    return a(paramString, a.b);
  }

  public HashMap<String, Object> f(String paramString)
  {
    try
    {
      HashMap localHashMap = new HashMap();
      String str = f.g(paramString);
      paramString = localHashMap;
      if (!TextUtils.isEmpty(str))
        paramString = new Hashon().fromJson(str);
      return paramString;
    }
    catch (Throwable paramString)
    {
      Ln.w(paramString);
    }
    return new HashMap();
  }

  public String g(String paramString)
  {
    ArrayList localArrayList1 = new ArrayList();
    localArrayList1.add(new KVPair("appkey", paramString));
    localArrayList1.add(new KVPair("device", DeviceHelper.getInstance(this.a).getDeviceKey()));
    paramString = new ArrayList();
    paramString.add(new KVPair("User-Agent", c));
    ArrayList localArrayList2 = new ArrayList();
    localArrayList2.add(new KVPair("http.socket.timeout", Integer.valueOf(10000)));
    localArrayList2.add(new KVPair("http.connection.timeout", Integer.valueOf(10000)));
    return this.k.httpPost(e(), localArrayList1, null, paramString, localArrayList2);
  }

  public static enum a
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.a
 * JD-Core Version:    0.6.2
 */