package org.android.agoo.impl;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.aY;
import com.umeng.message.proguard.aZ;
import com.umeng.message.proguard.bM;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bl;
import java.util.HashMap;
import org.android.agoo.IUpdateService;
import org.android.agoo.net.mtop.MtopRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateService
  implements IUpdateService
{
  static final String f = "wifi";
  static final String g = "2g";
  static final String h = "3g";
  static final String i = "4g";
  private static final String j = "mtop.atlas.getBaseUpdateList";
  private static final String k = "1.0";
  private static final String l = "hasAvailableUpdate";
  private static final String m = "updateInfo";
  private static final String n = "info";
  private static final String o = "name";
  private static final String p = "size";
  private static final String q = "url";
  private static final String r = "version";
  private static final String s = "md5";
  private static final String t = "UpdateService";
  String a = "androidVersion";
  String b = "mainVersion";
  String c = "atlasVersion";
  String d = "netStatus";
  String e = "group";

  private static int a(String paramString, bl parambl)
  {
    if ("2g".equals(parambl.c()))
      return 1;
    if ("3g".equals(parambl.c()))
      return 2;
    if ("4g".equals(parambl.c()))
      return 4;
    if ("wifi".equals(parambl.c()))
      return 10;
    return 0;
  }

  private static aY a(String paramString)
  {
    String str5 = null;
    if (TextUtils.isEmpty(paramString))
      return null;
    aY localaY = new aY();
    try
    {
      paramString = new JSONObject(paramString);
      HashMap localHashMap = new HashMap();
      if (paramString.has("hasAvailableUpdate"))
        localHashMap.put("hasAvailableUpdate", paramString.getString("hasAvailableUpdate"));
      if (paramString.has("updateInfo"))
      {
        aZ localaZ = new aZ();
        JSONObject localJSONObject = paramString.getJSONObject("updateInfo");
        if (!localJSONObject.has("info"))
          break label285;
        paramString = localJSONObject.getString("info");
        if (!localJSONObject.has("name"))
          break label280;
        str1 = localJSONObject.getString("name");
        if (!localJSONObject.has("size"))
          break label275;
        str2 = localJSONObject.getString("size");
        if (!localJSONObject.has("url"))
          break label270;
        str3 = localJSONObject.getString("url");
        if (localJSONObject.has("version"))
        {
          str4 = localJSONObject.getString("version");
          if (localJSONObject.has("md5"))
            str5 = localJSONObject.getString("md5");
          localaZ.c(paramString);
          localaZ.a(str1);
          localaZ.d(str2);
          localaZ.f(str3);
          localaZ.g(str4);
          localaZ.h(str5);
          localHashMap.put("updateInfo", localaZ);
        }
      }
      else
      {
        localaY.a(localHashMap);
        return localaY;
      }
    }
    catch (JSONException paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        continue;
        String str4 = null;
        continue;
        label270: String str3 = null;
        continue;
        label275: String str2 = null;
        continue;
        label280: String str1 = null;
        continue;
        label285: paramString = null;
      }
    }
  }

  private static MtopRequest a(Context paramContext, String paramString1, String paramString2, MtopRequest paramMtopRequest)
  {
    paramString1 = "tw_dymic_sdk_2";
    if (!"com.taobao.taobao".equals(paramContext.getPackageName()))
      paramString1 = "tw_dymic_sdk_" + paramContext.getPackageName();
    paramMtopRequest.putParams("group", paramString1);
    paramMtopRequest.putParams("androidVersion", Integer.valueOf(Build.VERSION.SDK_INT));
    paramString1 = new bl(paramContext);
    paramMtopRequest.putParams("netStatus", Integer.valueOf(a(paramString1.c(), paramString1)));
    bM.f(paramContext, paramString1.c(), "push");
    if (!TextUtils.isEmpty(paramString2))
      paramMtopRequest.putParams("version", bM.a() + "." + paramString2);
    while (true)
    {
      paramMtopRequest.putParams("name", paramContext.getPackageName());
      return paramMtopRequest;
      paramMtopRequest.putParams("version", bM.a() + ".0");
    }
  }

  // ERROR //
  private static void a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: new 175	org/android/agoo/net/mtop/MtopRequest
    //   6: dup
    //   7: invokespecial 212	org/android/agoo/net/mtop/MtopRequest:<init>	()V
    //   10: invokestatic 214	org/android/agoo/impl/UpdateService:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lorg/android/agoo/net/mtop/MtopRequest;)Lorg/android/agoo/net/mtop/MtopRequest;
    //   13: astore_2
    //   14: aload_0
    //   15: ifnull +121 -> 136
    //   18: aload_2
    //   19: ifnull +117 -> 136
    //   22: aload_2
    //   23: ldc 22
    //   25: invokevirtual 217	org/android/agoo/net/mtop/MtopRequest:setApi	(Ljava/lang/String;)V
    //   28: aload_2
    //   29: ldc 25
    //   31: invokevirtual 220	org/android/agoo/net/mtop/MtopRequest:setV	(Ljava/lang/String;)V
    //   34: aload_2
    //   35: aload_0
    //   36: invokestatic 225	org/android/agoo/a:f	(Landroid/content/Context;)Ljava/lang/String;
    //   39: invokevirtual 228	org/android/agoo/net/mtop/MtopRequest:setAppKey	(Ljava/lang/String;)V
    //   42: aload_2
    //   43: aload_0
    //   44: invokestatic 230	org/android/agoo/a:j	(Landroid/content/Context;)Ljava/lang/String;
    //   47: invokevirtual 233	org/android/agoo/net/mtop/MtopRequest:setAppSecret	(Ljava/lang/String;)V
    //   50: aload_2
    //   51: aload_0
    //   52: invokestatic 235	org/android/agoo/a:n	(Landroid/content/Context;)Ljava/lang/String;
    //   55: invokevirtual 238	org/android/agoo/net/mtop/MtopRequest:setDeviceId	(Ljava/lang/String;)V
    //   58: new 240	org/android/agoo/net/mtop/MtopSyncClientV3
    //   61: dup
    //   62: invokespecial 241	org/android/agoo/net/mtop/MtopSyncClientV3:<init>	()V
    //   65: astore_3
    //   66: aload_3
    //   67: aload_0
    //   68: invokestatic 244	org/android/agoo/a:G	(Landroid/content/Context;)Ljava/lang/String;
    //   71: invokeinterface 249 2 0
    //   76: aload_3
    //   77: aload_0
    //   78: aload_2
    //   79: invokeinterface 253 3 0
    //   84: astore_2
    //   85: aload_2
    //   86: ifnull +50 -> 136
    //   89: aload_2
    //   90: invokevirtual 259	org/android/agoo/net/mtop/Result:isSuccess	()Z
    //   93: ifeq +43 -> 136
    //   96: aload_2
    //   97: invokevirtual 262	org/android/agoo/net/mtop/Result:getData	()Ljava/lang/String;
    //   100: astore_2
    //   101: ldc_w 264
    //   104: new 108	org/json/JSONObject
    //   107: dup
    //   108: aload_2
    //   109: invokespecial 111	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   112: ldc 28
    //   114: invokevirtual 268	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   117: checkcast 90	java/lang/String
    //   120: invokevirtual 94	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   123: ifeq +14 -> 137
    //   126: ldc_w 270
    //   129: iconst_0
    //   130: anewarray 4	java/lang/Object
    //   133: invokestatic 275	com/umeng/message/proguard/bd:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   136: return
    //   137: ldc 52
    //   139: aload_2
    //   140: invokestatic 278	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   143: aload_2
    //   144: invokestatic 280	org/android/agoo/impl/UpdateService:a	(Ljava/lang/String;)Lcom/umeng/message/proguard/aY;
    //   147: astore_2
    //   148: aload_2
    //   149: ifnull -13 -> 136
    //   152: aload_2
    //   153: invokevirtual 283	com/umeng/message/proguard/aY:b	()Lcom/umeng/message/proguard/aZ;
    //   156: astore 4
    //   158: aload_0
    //   159: invokevirtual 287	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   162: astore_3
    //   163: aload_0
    //   164: invokevirtual 161	android/content/Context:getPackageName	()Ljava/lang/String;
    //   167: astore 5
    //   169: aconst_null
    //   170: astore_2
    //   171: new 163	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   178: aload_3
    //   179: aload 5
    //   181: iconst_0
    //   182: invokevirtual 293	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   185: getfield 298	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   188: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: getstatic 303	java/io/File:separator	Ljava/lang/String;
    //   194: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: aload_1
    //   198: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: astore_3
    //   205: aload_3
    //   206: astore_2
    //   207: aload_2
    //   208: ifnull -72 -> 136
    //   211: aload 4
    //   213: ifnull -77 -> 136
    //   216: ldc 52
    //   218: new 163	java/lang/StringBuilder
    //   221: dup
    //   222: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   225: ldc_w 305
    //   228: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: aload 4
    //   233: invokevirtual 306	com/umeng/message/proguard/aZ:toString	()Ljava/lang/String;
    //   236: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: invokestatic 278	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   245: aload_0
    //   246: aload 4
    //   248: aload_2
    //   249: invokestatic 311	com/umeng/message/proguard/bO:a	()Lorg/android/du/CpuType;
    //   252: invokestatic 316	com/umeng/message/proguard/aX:a	(Landroid/content/Context;Lcom/umeng/message/proguard/aZ;Ljava/lang/String;Lorg/android/du/CpuType;)Z
    //   255: ifeq -119 -> 136
    //   258: aload 4
    //   260: invokevirtual 318	com/umeng/message/proguard/aZ:g	()Ljava/lang/String;
    //   263: astore_3
    //   264: aload_0
    //   265: aload_1
    //   266: aload_3
    //   267: invokestatic 320	com/umeng/message/proguard/bM:c	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   270: aload_0
    //   271: ifnull -135 -> 136
    //   274: aload_0
    //   275: aload_1
    //   276: invokestatic 323	com/umeng/message/proguard/bM:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   279: astore 4
    //   281: aload_3
    //   282: astore_2
    //   283: aload_3
    //   284: invokestatic 103	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   287: ifne +61 -> 348
    //   290: aload_3
    //   291: ldc_w 325
    //   294: invokevirtual 329	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   297: astore 5
    //   299: aload_3
    //   300: astore_2
    //   301: aload 5
    //   303: ifnull +45 -> 348
    //   306: aload_3
    //   307: astore_2
    //   308: aload 5
    //   310: arraylength
    //   311: ifle +37 -> 348
    //   314: aload 5
    //   316: aload 5
    //   318: arraylength
    //   319: iconst_1
    //   320: isub
    //   321: aaload
    //   322: astore_2
    //   323: ldc 52
    //   325: new 163	java/lang/StringBuilder
    //   328: dup
    //   329: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   332: ldc_w 331
    //   335: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: aload_2
    //   339: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   345: invokestatic 278	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   348: aload_0
    //   349: aload_1
    //   350: aload_2
    //   351: invokestatic 333	com/umeng/message/proguard/bM:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload_0
    //   355: aload_1
    //   356: aload 4
    //   358: invokestatic 335	com/umeng/message/proguard/bM:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   361: aload_1
    //   362: invokestatic 340	org/android/du/DuSdk:unInit	(Ljava/lang/String;)V
    //   365: aload_0
    //   366: iconst_1
    //   367: invokestatic 346	org/android/Config:setNeedNotAutoUpdate	(Landroid/content/Context;Z)V
    //   370: aload_0
    //   371: aload_0
    //   372: invokestatic 350	org/android/Config:getAppVersion	(Landroid/content/Context;)I
    //   375: invokestatic 354	org/android/Config:setLastAppVersion	(Landroid/content/Context;I)V
    //   378: return
    //   379: astore_1
    //   380: aload_0
    //   381: aload_1
    //   382: invokevirtual 355	java/lang/Throwable:toString	()Ljava/lang/String;
    //   385: ldc 198
    //   387: invokestatic 357	com/umeng/message/proguard/bM:e	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   390: return
    //   391: astore_1
    //   392: ldc 52
    //   394: new 163	java/lang/StringBuilder
    //   397: dup
    //   398: invokespecial 164	java/lang/StringBuilder:<init>	()V
    //   401: ldc_w 359
    //   404: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: aload_1
    //   408: invokevirtual 362	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   411: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: invokestatic 278	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   417: aload_0
    //   418: aload_1
    //   419: invokevirtual 355	java/lang/Throwable:toString	()Ljava/lang/String;
    //   422: ldc 198
    //   424: invokestatic 357	com/umeng/message/proguard/bM:e	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   427: return
    //   428: astore_3
    //   429: ldc 52
    //   431: ldc_w 364
    //   434: invokestatic 278	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   437: goto -230 -> 207
    //
    // Exception table:
    //   from	to	target	type
    //   137	148	379	java/lang/Throwable
    //   152	169	379	java/lang/Throwable
    //   216	270	379	java/lang/Throwable
    //   274	281	379	java/lang/Throwable
    //   283	299	379	java/lang/Throwable
    //   308	348	379	java/lang/Throwable
    //   348	378	379	java/lang/Throwable
    //   429	437	379	java/lang/Throwable
    //   22	85	391	java/lang/Throwable
    //   89	136	391	java/lang/Throwable
    //   380	390	391	java/lang/Throwable
    //   171	205	428	java/lang/Throwable
  }

  public boolean checkUpdateJar(int paramInt, String paramString)
  {
    Object localObject = new StringBuilder().append("checkUpdateJar version=").append(paramString).append(",cert chceck=");
    boolean bool;
    if (613410465 == paramInt)
    {
      bool = true;
      Log.i("UpdateService", bool);
      if ((!TextUtils.isEmpty(paramString)) && (paramInt != 0))
        break label67;
    }
    label67: 
    do
    {
      return false;
      bool = false;
      break;
      String[] arrayOfString = paramString.split("\\.");
      localObject = paramString;
      if (arrayOfString != null)
      {
        localObject = paramString;
        if (arrayOfString.length > 0)
          localObject = arrayOfString[0];
      }
    }
    while ((613410465 != paramInt) || (!bM.a().equals((String)localObject + ".0")));
    return true;
  }

  public void downloadUpdate(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      a(paramContext, paramString1, paramString2);
      return;
    }
    catch (Throwable paramContext)
    {
      bd.b("UpdateService", "update fialed,e=" + paramContext);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.impl.UpdateService
 * JD-Core Version:    0.6.2
 */