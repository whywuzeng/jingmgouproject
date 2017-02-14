package com.alimama.mobile.csdk.umupdate.b;

import com.alimama.mobile.csdk.umupdate.a.g;
import com.alimama.mobile.csdk.umupdate.a.j;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends u.upd.e
{
  public static String[] b;
  private static final String c = d.class.getName();
  public Map<String, Object> a;

  public d(Map<String, Object> paramMap)
  {
    this.a = paramMap;
  }

  public static d a(JSONObject paramJSONObject)
    throws JSONException
  {
    Iterator localIterator = paramJSONObject.keys();
    HashMap localHashMap = new HashMap();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, paramJSONObject.get(str));
    }
    return new d(localHashMap);
  }

  public void a()
  {
    new c().sendAsync(this, null);
  }

  public String b()
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.a.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((!str.equals("date")) && (!str.equals("action_type")) && (!str.equals("time")) && (!str.equals("ts")))
        localHashMap.put(str, this.a.get(str));
    }
    return j.a(a.d[0], localHashMap);
  }

  public String getHttpMethod()
  {
    return GET;
  }

  public String toGetUrl()
  {
    return j.a(a.d[0], this.a).toString();
  }

  public JSONObject toJson()
  {
    return new JSONObject(this.a);
  }

  public static class a
  {
    private static final Random k = new Random();
    private static final int l = 32767;
    List<Promoter> a = new ArrayList();
    private MMEntity b;
    private String c;
    private String d;
    private String e;
    private int f;
    private int g;
    private int h;
    private String i;
    private String j;
    private int m = 0;
    private String n = "";
    private int o = 1;

    public a(MMEntity paramMMEntity)
    {
      this.b = paramMMEntity;
    }

    private boolean a(Map<String, Object> paramMap)
    {
      if (d.b == null)
        d.b = new String[] { "category", "sid", "device_id", "idmd5", "mc", "action_type", "action_index", "layout_type", "time", "date", "access", "access_subtype" };
      boolean bool2;
      if ((paramMap == null) || (paramMap.size() == 0))
      {
        bool2 = false;
        return bool2;
      }
      String[] arrayOfString = d.b;
      int i2 = arrayOfString.length;
      int i1 = 0;
      boolean bool1 = true;
      while (true)
      {
        bool2 = bool1;
        if (i1 >= i2)
          break;
        String str = arrayOfString[i1];
        if (!paramMap.containsKey(str))
        {
          g.e(com.alimama.mobile.csdk.umupdate.a.e.e, new Object[] { "Report params has no required param [" + str + "]" });
          bool1 = false;
        }
        i1 += 1;
      }
    }

    // ERROR //
    private Map<String, Object> c()
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_1
      //   2: invokestatic 129	com/alimama/mobile/a:a	()Lcom/alimama/mobile/a;
      //   5: invokevirtual 132	com/alimama/mobile/a:b	()Lcom/alimama/mobile/csdk/umupdate/a/a;
      //   8: astore 6
      //   10: new 134	java/util/HashMap
      //   13: dup
      //   14: invokespecial 135	java/util/HashMap:<init>	()V
      //   17: astore 5
      //   19: aload_0
      //   20: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   23: getfield 140	com/alimama/mobile/csdk/umupdate/models/MMEntity:slotId	Ljava/lang/String;
      //   26: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   29: ifne +264 -> 293
      //   32: aload 5
      //   34: ldc 148
      //   36: aload_0
      //   37: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   40: getfield 140	com/alimama/mobile/csdk/umupdate/models/MMEntity:slotId	Ljava/lang/String;
      //   43: invokeinterface 152 3 0
      //   48: pop
      //   49: aload_0
      //   50: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   53: invokevirtual 155	com/alimama/mobile/csdk/umupdate/models/MMEntity:getTimeConsuming	()Ljava/lang/String;
      //   56: astore 4
      //   58: aload 4
      //   60: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   63: ifne +15 -> 78
      //   66: aload 5
      //   68: ldc 157
      //   70: aload 4
      //   72: invokeinterface 152 3 0
      //   77: pop
      //   78: aload_0
      //   79: getfield 159	com/alimama/mobile/csdk/umupdate/b/d$a:i	Ljava/lang/String;
      //   82: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   85: ifne +17 -> 102
      //   88: aload 5
      //   90: ldc 161
      //   92: aload_0
      //   93: getfield 159	com/alimama/mobile/csdk/umupdate/b/d$a:i	Ljava/lang/String;
      //   96: invokeinterface 152 3 0
      //   101: pop
      //   102: aload_0
      //   103: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   106: getfield 164	com/alimama/mobile/csdk/umupdate/models/MMEntity:tabId	Ljava/lang/String;
      //   109: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   112: ifne +20 -> 132
      //   115: aload 5
      //   117: ldc 166
      //   119: aload_0
      //   120: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   123: getfield 164	com/alimama/mobile/csdk/umupdate/models/MMEntity:tabId	Ljava/lang/String;
      //   126: invokeinterface 152 3 0
      //   131: pop
      //   132: aload_0
      //   133: getfield 168	com/alimama/mobile/csdk/umupdate/b/d$a:j	Ljava/lang/String;
      //   136: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   139: ifne +17 -> 156
      //   142: aload 5
      //   144: ldc 170
      //   146: aload_0
      //   147: getfield 168	com/alimama/mobile/csdk/umupdate/b/d$a:j	Ljava/lang/String;
      //   150: invokeinterface 152 3 0
      //   155: pop
      //   156: aload 5
      //   158: ldc 172
      //   160: getstatic 174	com/alimama/mobile/csdk/umupdate/a/e:b	Ljava/lang/String;
      //   163: invokeinterface 152 3 0
      //   168: pop
      //   169: aload 5
      //   171: ldc 176
      //   173: getstatic 178	com/alimama/mobile/csdk/umupdate/a/e:c	Ljava/lang/String;
      //   176: invokeinterface 152 3 0
      //   181: pop
      //   182: aload 5
      //   184: ldc 180
      //   186: invokestatic 186	java/lang/System:currentTimeMillis	()J
      //   189: invokestatic 192	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   192: invokeinterface 152 3 0
      //   197: pop
      //   198: aload 5
      //   200: ldc 194
      //   202: getstatic 199	android/os/Build:MODEL	Ljava/lang/String;
      //   205: invokeinterface 152 3 0
      //   210: pop
      //   211: aload_0
      //   212: getfield 46	com/alimama/mobile/csdk/umupdate/b/d$a:n	Ljava/lang/String;
      //   215: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   218: istore_3
      //   219: iload_3
      //   220: ifne +195 -> 415
      //   223: aload_0
      //   224: getfield 46	com/alimama/mobile/csdk/umupdate/b/d$a:n	Ljava/lang/String;
      //   227: ldc 201
      //   229: invokevirtual 205	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   232: astore 7
      //   234: new 134	java/util/HashMap
      //   237: dup
      //   238: invokespecial 135	java/util/HashMap:<init>	()V
      //   241: astore 4
      //   243: aload 7
      //   245: arraylength
      //   246: istore_2
      //   247: iload_1
      //   248: iload_2
      //   249: if_icmpge +106 -> 355
      //   252: aload 7
      //   254: iload_1
      //   255: aaload
      //   256: ldc 207
      //   258: invokevirtual 205	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   261: astore 8
      //   263: aload 8
      //   265: arraylength
      //   266: iconst_2
      //   267: if_icmpne +19 -> 286
      //   270: aload 4
      //   272: aload 8
      //   274: iconst_0
      //   275: aaload
      //   276: aload 8
      //   278: iconst_1
      //   279: aaload
      //   280: invokeinterface 152 3 0
      //   285: pop
      //   286: iload_1
      //   287: iconst_1
      //   288: iadd
      //   289: istore_1
      //   290: goto -43 -> 247
      //   293: aload_0
      //   294: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   297: getfield 210	com/alimama/mobile/csdk/umupdate/models/MMEntity:appkey	Ljava/lang/String;
      //   300: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   303: ifne +35 -> 338
      //   306: aload 5
      //   308: ldc 212
      //   310: aload_0
      //   311: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   314: getfield 210	com/alimama/mobile/csdk/umupdate/models/MMEntity:appkey	Ljava/lang/String;
      //   317: invokeinterface 152 3 0
      //   322: pop
      //   323: goto -274 -> 49
      //   326: astore 4
      //   328: new 214	java/lang/RuntimeException
      //   331: dup
      //   332: aload 4
      //   334: invokespecial 217	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
      //   337: athrow
      //   338: getstatic 99	com/alimama/mobile/csdk/umupdate/a/e:e	Ljava/lang/String;
      //   341: iconst_1
      //   342: anewarray 4	java/lang/Object
      //   345: dup
      //   346: iconst_0
      //   347: ldc 219
      //   349: aastore
      //   350: invokestatic 221	com/alimama/mobile/csdk/umupdate/a/g:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   353: aconst_null
      //   354: areturn
      //   355: aload 4
      //   357: invokeinterface 225 1 0
      //   362: invokeinterface 231 1 0
      //   367: astore 7
      //   369: aload 7
      //   371: invokeinterface 237 1 0
      //   376: ifeq +39 -> 415
      //   379: aload 7
      //   381: invokeinterface 241 1 0
      //   386: checkcast 61	java/lang/String
      //   389: astore 8
      //   391: aload 5
      //   393: aload 8
      //   395: aload 4
      //   397: aload 8
      //   399: invokeinterface 245 2 0
      //   404: invokeinterface 152 3 0
      //   409: pop
      //   410: goto -41 -> 369
      //   413: astore 4
      //   415: aload 6
      //   417: invokeinterface 250 1 0
      //   422: astore 4
      //   424: aload 4
      //   426: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   429: ifne +15 -> 444
      //   432: aload 5
      //   434: ldc 71
      //   436: aload 4
      //   438: invokeinterface 152 3 0
      //   443: pop
      //   444: aload 5
      //   446: ldc 252
      //   448: aload 6
      //   450: invokeinterface 255 1 0
      //   455: invokeinterface 152 3 0
      //   460: pop
      //   461: aload_0
      //   462: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   465: getfield 259	com/alimama/mobile/csdk/umupdate/models/MMEntity:module	Lcom/alimama/mobile/csdk/umupdate/models/d;
      //   468: ifnull +21 -> 489
      //   471: aload 5
      //   473: ldc_w 260
      //   476: aload_0
      //   477: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   480: getfield 259	com/alimama/mobile/csdk/umupdate/models/MMEntity:module	Lcom/alimama/mobile/csdk/umupdate/models/d;
      //   483: invokeinterface 152 3 0
      //   488: pop
      //   489: aload 5
      //   491: ldc_w 262
      //   494: getstatic 267	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   497: invokeinterface 152 3 0
      //   502: pop
      //   503: aload 5
      //   505: ldc_w 269
      //   508: ldc_w 271
      //   511: invokeinterface 152 3 0
      //   516: pop
      //   517: aload 5
      //   519: ldc_w 273
      //   522: getstatic 37	com/alimama/mobile/csdk/umupdate/b/d$a:k	Ljava/util/Random;
      //   525: sipush 32767
      //   528: invokevirtual 277	java/util/Random:nextInt	(I)I
      //   531: invokestatic 282	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   534: invokeinterface 152 3 0
      //   539: pop
      //   540: aload 6
      //   542: invokeinterface 286 1 0
      //   547: astore 4
      //   549: aload 5
      //   551: ldc 83
      //   553: aload 4
      //   555: iconst_0
      //   556: aaload
      //   557: invokeinterface 152 3 0
      //   562: pop
      //   563: aload 5
      //   565: ldc 85
      //   567: aload 4
      //   569: iconst_1
      //   570: aaload
      //   571: invokeinterface 152 3 0
      //   576: pop
      //   577: aload_0
      //   578: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   581: getfield 288	com/alimama/mobile/csdk/umupdate/models/MMEntity:sid	Ljava/lang/String;
      //   584: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   587: ifne +20 -> 607
      //   590: aload 5
      //   592: ldc 65
      //   594: aload_0
      //   595: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   598: getfield 288	com/alimama/mobile/csdk/umupdate/models/MMEntity:sid	Ljava/lang/String;
      //   601: invokeinterface 152 3 0
      //   606: pop
      //   607: aload_0
      //   608: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   611: getfield 291	com/alimama/mobile/csdk/umupdate/models/MMEntity:psid	Ljava/lang/String;
      //   614: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   617: ifne +21 -> 638
      //   620: aload 5
      //   622: ldc_w 292
      //   625: aload_0
      //   626: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   629: getfield 291	com/alimama/mobile/csdk/umupdate/models/MMEntity:psid	Ljava/lang/String;
      //   632: invokeinterface 152 3 0
      //   637: pop
      //   638: aload 5
      //   640: ldc 67
      //   642: aload 6
      //   644: invokeinterface 295 1 0
      //   649: invokeinterface 152 3 0
      //   654: pop
      //   655: aload 5
      //   657: ldc 69
      //   659: aload 6
      //   661: invokeinterface 295 1 0
      //   666: invokestatic 300	com/alimama/mobile/csdk/umupdate/a/j:c	(Ljava/lang/String;)Ljava/lang/String;
      //   669: invokeinterface 152 3 0
      //   674: pop
      //   675: aload 6
      //   677: invokeinterface 304 1 0
      //   682: astore 4
      //   684: aload 4
      //   686: ifnull +95 -> 781
      //   689: aload 5
      //   691: ldc_w 306
      //   694: aload 4
      //   696: invokevirtual 312	android/location/Location:getLatitude	()D
      //   699: invokestatic 315	java/lang/String:valueOf	(D)Ljava/lang/String;
      //   702: invokeinterface 152 3 0
      //   707: pop
      //   708: aload 5
      //   710: ldc_w 317
      //   713: aload 4
      //   715: invokevirtual 320	android/location/Location:getLongitude	()D
      //   718: invokestatic 315	java/lang/String:valueOf	(D)Ljava/lang/String;
      //   721: invokeinterface 152 3 0
      //   726: pop
      //   727: aload 5
      //   729: ldc_w 322
      //   732: aload 4
      //   734: invokevirtual 325	android/location/Location:getProvider	()Ljava/lang/String;
      //   737: invokeinterface 152 3 0
      //   742: pop
      //   743: aload 5
      //   745: ldc_w 327
      //   748: aload 4
      //   750: invokevirtual 330	android/location/Location:getTime	()J
      //   753: invokestatic 333	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   756: invokeinterface 152 3 0
      //   761: pop
      //   762: aload 5
      //   764: ldc_w 335
      //   767: aload 4
      //   769: invokevirtual 339	android/location/Location:getAccuracy	()F
      //   772: invokestatic 342	java/lang/String:valueOf	(F)Ljava/lang/String;
      //   775: invokeinterface 152 3 0
      //   780: pop
      //   781: new 344	java/text/SimpleDateFormat
      //   784: dup
      //   785: ldc_w 346
      //   788: invokespecial 349	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
      //   791: new 351	java/util/Date
      //   794: dup
      //   795: invokespecial 352	java/util/Date:<init>	()V
      //   798: invokevirtual 358	java/text/DateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   801: astore 7
      //   803: aload 7
      //   805: ldc_w 360
      //   808: invokevirtual 205	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   811: iconst_0
      //   812: aaload
      //   813: astore 4
      //   815: aload 7
      //   817: ldc_w 360
      //   820: invokevirtual 205	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   823: iconst_1
      //   824: aaload
      //   825: astore 7
      //   827: aload 5
      //   829: ldc 81
      //   831: aload 4
      //   833: invokeinterface 152 3 0
      //   838: pop
      //   839: aload 5
      //   841: ldc 79
      //   843: aload 7
      //   845: invokeinterface 152 3 0
      //   850: pop
      //   851: aload 5
      //   853: ldc_w 362
      //   856: aload 6
      //   858: invokeinterface 364 1 0
      //   863: invokeinterface 152 3 0
      //   868: pop
      //   869: aload 5
      //   871: ldc_w 366
      //   874: aload_0
      //   875: getfield 367	com/alimama/mobile/csdk/umupdate/b/d$a:c	Ljava/lang/String;
      //   878: invokeinterface 152 3 0
      //   883: pop
      //   884: aload_0
      //   885: getfield 369	com/alimama/mobile/csdk/umupdate/b/d$a:d	Ljava/lang/String;
      //   888: ifnull +246 -> 1134
      //   891: aload_0
      //   892: getfield 369	com/alimama/mobile/csdk/umupdate/b/d$a:d	Ljava/lang/String;
      //   895: astore 4
      //   897: aload 5
      //   899: ldc_w 371
      //   902: aload 4
      //   904: invokeinterface 152 3 0
      //   909: pop
      //   910: aload 5
      //   912: ldc 63
      //   914: aload_0
      //   915: getfield 372	com/alimama/mobile/csdk/umupdate/b/d$a:e	Ljava/lang/String;
      //   918: invokeinterface 152 3 0
      //   923: pop
      //   924: aload 5
      //   926: ldc 73
      //   928: aload_0
      //   929: getfield 374	com/alimama/mobile/csdk/umupdate/b/d$a:f	I
      //   932: invokestatic 282	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   935: invokeinterface 152 3 0
      //   940: pop
      //   941: aload 5
      //   943: ldc 75
      //   945: aload_0
      //   946: getfield 376	com/alimama/mobile/csdk/umupdate/b/d$a:g	I
      //   949: invokestatic 282	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   952: invokeinterface 152 3 0
      //   957: pop
      //   958: aload 5
      //   960: ldc 77
      //   962: aload_0
      //   963: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   966: getfield 379	com/alimama/mobile/csdk/umupdate/models/MMEntity:layoutType	I
      //   969: invokestatic 282	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   972: invokeinterface 152 3 0
      //   977: pop
      //   978: aload 5
      //   980: ldc_w 381
      //   983: aload_0
      //   984: getfield 383	com/alimama/mobile/csdk/umupdate/b/d$a:h	I
      //   987: invokestatic 282	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   990: invokeinterface 152 3 0
      //   995: pop
      //   996: getstatic 384	com/alimama/mobile/csdk/umupdate/a/e:d	Ljava/lang/String;
      //   999: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1002: ifeq +104 -> 1106
      //   1005: aload 6
      //   1007: ldc_w 386
      //   1010: invokeinterface 388 2 0
      //   1015: astore 4
      //   1017: aload 4
      //   1019: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1022: ifne +16 -> 1038
      //   1025: aload 5
      //   1027: ldc_w 390
      //   1030: aload 4
      //   1032: invokeinterface 152 3 0
      //   1037: pop
      //   1038: aload_0
      //   1039: getfield 42	com/alimama/mobile/csdk/umupdate/b/d$a:m	I
      //   1042: ifeq +21 -> 1063
      //   1045: aload 5
      //   1047: ldc_w 392
      //   1050: aload_0
      //   1051: getfield 42	com/alimama/mobile/csdk/umupdate/b/d$a:m	I
      //   1054: invokestatic 282	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1057: invokeinterface 152 3 0
      //   1062: pop
      //   1063: aload 5
      //   1065: ldc_w 394
      //   1068: aload_0
      //   1069: getfield 48	com/alimama/mobile/csdk/umupdate/b/d$a:o	I
      //   1072: invokestatic 282	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1075: invokeinterface 152 3 0
      //   1080: pop
      //   1081: aload_0
      //   1082: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   1085: getfield 140	com/alimama/mobile/csdk/umupdate/models/MMEntity:slotId	Ljava/lang/String;
      //   1088: invokestatic 146	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1091: ifeq +23 -> 1114
      //   1094: aload_0
      //   1095: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   1098: getfield 210	com/alimama/mobile/csdk/umupdate/models/MMEntity:appkey	Ljava/lang/String;
      //   1101: astore 4
      //   1103: goto +28 -> 1131
      //   1106: getstatic 384	com/alimama/mobile/csdk/umupdate/a/e:d	Ljava/lang/String;
      //   1109: astore 4
      //   1111: goto -94 -> 1017
      //   1114: aload_0
      //   1115: getfield 55	com/alimama/mobile/csdk/umupdate/b/d$a:b	Lcom/alimama/mobile/csdk/umupdate/models/MMEntity;
      //   1118: getfield 140	com/alimama/mobile/csdk/umupdate/models/MMEntity:slotId	Ljava/lang/String;
      //   1121: astore 4
      //   1123: goto +8 -> 1131
      //   1126: astore 4
      //   1128: goto -347 -> 781
      //   1131: aload 5
      //   1133: areturn
      //   1134: ldc 44
      //   1136: astore 4
      //   1138: goto -241 -> 897
      //
      // Exception table:
      //   from	to	target	type
      //   19	49	326	java/lang/Exception
      //   49	78	326	java/lang/Exception
      //   78	102	326	java/lang/Exception
      //   102	132	326	java/lang/Exception
      //   132	156	326	java/lang/Exception
      //   156	219	326	java/lang/Exception
      //   293	323	326	java/lang/Exception
      //   338	353	326	java/lang/Exception
      //   415	444	326	java/lang/Exception
      //   444	489	326	java/lang/Exception
      //   489	607	326	java/lang/Exception
      //   607	638	326	java/lang/Exception
      //   638	675	326	java/lang/Exception
      //   781	897	326	java/lang/Exception
      //   897	1017	326	java/lang/Exception
      //   1017	1038	326	java/lang/Exception
      //   1038	1063	326	java/lang/Exception
      //   1063	1103	326	java/lang/Exception
      //   1106	1111	326	java/lang/Exception
      //   1114	1123	326	java/lang/Exception
      //   223	247	413	java/lang/Exception
      //   252	286	413	java/lang/Exception
      //   355	369	413	java/lang/Exception
      //   369	410	413	java/lang/Exception
      //   675	684	1126	java/lang/Exception
      //   689	781	1126	java/lang/Exception
    }

    public a a(int paramInt)
    {
      this.f = paramInt;
      return this;
    }

    public a a(String paramString)
    {
      this.i = paramString;
      return this;
    }

    public a a(Promoter[] paramArrayOfPromoter)
    {
      int i2 = 0;
      int i3 = paramArrayOfPromoter.length;
      int i1 = 0;
      Object localObject;
      while (i1 < i3)
      {
        localObject = paramArrayOfPromoter[i1];
        this.a.add(localObject);
        i1 += 1;
      }
      if ((this.a != null) && (this.a.size() > 0))
      {
        i3 = this.a.size();
        try
        {
          paramArrayOfPromoter = (Promoter)this.a.get(0);
          if (this.a.size() == 1)
          {
            this.n = paramArrayOfPromoter.prom_act_pams;
            g.c("set promoter act_pams to report act_params. [" + this.n + "]", new Object[0]);
          }
          while (true)
          {
            label138: paramArrayOfPromoter = new StringBuffer();
            localObject = new StringBuffer();
            i1 = i2;
            while (i1 < i3)
            {
              Promoter localPromoter = (Promoter)this.a.get(i1);
              paramArrayOfPromoter.append(localPromoter.promoter + ",");
              ((StringBuffer)localObject).append(localPromoter.category + ",");
              i1 += 1;
            }
            this.n = paramArrayOfPromoter.slot_act_pams;
            g.c("set slot act_pams to report act_params. [" + this.n + "]", new Object[0]);
          }
        }
        catch (NullPointerException paramArrayOfPromoter)
        {
          break label138;
          paramArrayOfPromoter.deleteCharAt(paramArrayOfPromoter.length() - 1);
          this.d = paramArrayOfPromoter.toString();
          this.e = ((StringBuffer)localObject).toString();
        }
      }
      return this;
    }

    public d a()
    {
      return new d(b());
    }

    public a b(int paramInt)
    {
      this.g = paramInt;
      return this;
    }

    public a b(String paramString)
    {
      this.j = paramString;
      return this;
    }

    public Map<String, Object> b()
    {
      if (com.alimama.mobile.a.a().b().l());
      for (String str = "0"; ; str = "1")
      {
        this.c = str;
        return c();
      }
    }

    public a c(int paramInt)
    {
      this.h = paramInt;
      return this;
    }

    public a d(int paramInt)
    {
      this.o = paramInt;
      return this;
    }

    public a e(int paramInt)
    {
      this.m = paramInt;
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.b.d
 * JD-Core Version:    0.6.2
 */