package org.android.agoo.net.channel;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.message.proguard.bD;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bv;
import com.umeng.message.proguard.by;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SignHelper
{
  static final String a = "wifi";
  static final String b = "2g";
  static final String c = "3g";
  static final String d = "4g";
  public static final Uri e = Uri.parse("content://telephony/carriers/preferapn");
  private static final String f = "SignHelper";
  private static final String g = "|";
  private static final byte h = 0;
  private static final byte i = 1;
  private static final byte j = 2;
  private static final byte k = 3;
  private static final byte l = 4;
  private static final byte m = 5;
  private static final byte n = 6;
  private static final byte o = 7;
  private static final byte p = 8;

  private static final String a()
  {
    return a(System.currentTimeMillis());
  }

  private static final String a(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(2000, 1, 1, 0, 0, 0);
    return String.format("%010d", new Object[] { Long.valueOf((paramLong - localCalendar.getTimeInMillis()) / 1000L) });
  }

  // ERROR //
  private static final String a(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aconst_null
    //   3: astore 7
    //   5: bipush 6
    //   7: newarray byte
    //   9: astore 6
    //   11: aload_0
    //   12: invokevirtual 108	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   15: ldc 110
    //   17: invokevirtual 114	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   20: checkcast 116	android/net/ConnectivityManager
    //   23: astore 9
    //   25: aload 9
    //   27: invokevirtual 120	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   30: astore 8
    //   32: aload 8
    //   34: astore 7
    //   36: aload 7
    //   38: ifnull +11 -> 49
    //   41: aload 7
    //   43: invokevirtual 126	android/net/NetworkInfo:isConnected	()Z
    //   46: ifne +804 -> 850
    //   49: aload 9
    //   51: invokevirtual 130	android/net/ConnectivityManager:getAllNetworkInfo	()[Landroid/net/NetworkInfo;
    //   54: astore 8
    //   56: aload 8
    //   58: ifnull +792 -> 850
    //   61: iconst_0
    //   62: istore_1
    //   63: iload_1
    //   64: aload 8
    //   66: arraylength
    //   67: if_icmpge +783 -> 850
    //   70: aload 8
    //   72: iload_1
    //   73: aaload
    //   74: ifnull +352 -> 426
    //   77: aload 8
    //   79: iload_1
    //   80: aaload
    //   81: invokevirtual 126	android/net/NetworkInfo:isConnected	()Z
    //   84: ifeq +342 -> 426
    //   87: aload 8
    //   89: iload_1
    //   90: aaload
    //   91: astore 7
    //   93: aload_0
    //   94: invokevirtual 134	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   97: getstatic 56	org/android/agoo/net/channel/SignHelper:e	Landroid/net/Uri;
    //   100: aconst_null
    //   101: aconst_null
    //   102: aconst_null
    //   103: aconst_null
    //   104: invokevirtual 140	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   107: astore 8
    //   109: aload 8
    //   111: ifnull +734 -> 845
    //   114: aload 8
    //   116: invokeinterface 145 1 0
    //   121: pop
    //   122: aload 8
    //   124: aload 8
    //   126: ldc 147
    //   128: invokeinterface 151 2 0
    //   133: invokeinterface 155 2 0
    //   138: astore 9
    //   140: aload 9
    //   142: invokestatic 161	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   145: ifne +695 -> 840
    //   148: ldc 163
    //   150: aload 9
    //   152: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   155: istore 5
    //   157: iload 5
    //   159: ifeq +274 -> 433
    //   162: iconst_3
    //   163: istore_1
    //   164: aload 8
    //   166: invokeinterface 170 1 0
    //   171: iload_1
    //   172: istore_2
    //   173: iload_2
    //   174: istore_1
    //   175: iload_2
    //   176: ifne +38 -> 214
    //   179: iload_2
    //   180: istore_3
    //   181: aload 7
    //   183: invokevirtual 173	android/net/NetworkInfo:getExtraInfo	()Ljava/lang/String;
    //   186: astore 8
    //   188: iload_2
    //   189: istore_1
    //   190: iload_2
    //   191: istore_3
    //   192: aload 8
    //   194: invokestatic 161	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   197: ifne +17 -> 214
    //   200: iload_2
    //   201: istore_3
    //   202: ldc 175
    //   204: aload 8
    //   206: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   209: ifeq +252 -> 461
    //   212: iconst_1
    //   213: istore_1
    //   214: iload_1
    //   215: istore_3
    //   216: aload 7
    //   218: invokevirtual 179	android/net/NetworkInfo:getType	()I
    //   221: istore 4
    //   223: iload_1
    //   224: istore_3
    //   225: aload 7
    //   227: invokevirtual 182	android/net/NetworkInfo:getSubtype	()I
    //   230: istore_2
    //   231: iconst_1
    //   232: iload 4
    //   234: if_icmpne +355 -> 589
    //   237: aload_0
    //   238: ldc 8
    //   240: invokevirtual 114	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   243: checkcast 184	android/net/wifi/WifiManager
    //   246: invokevirtual 188	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   249: invokevirtual 193	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   252: invokestatic 196	org/android/agoo/net/channel/SignHelper:a	(Ljava/lang/String;)[B
    //   255: astore 7
    //   257: aload 7
    //   259: astore 6
    //   261: new 198	java/lang/StringBuilder
    //   264: dup
    //   265: invokespecial 199	java/lang/StringBuilder:<init>	()V
    //   268: astore 7
    //   270: aload_0
    //   271: invokestatic 202	org/android/agoo/net/channel/SignHelper:b	(Landroid/content/Context;)I
    //   274: istore_3
    //   275: aload 7
    //   277: new 198	java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial 199	java/lang/StringBuilder:<init>	()V
    //   284: ldc 204
    //   286: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: iload_3
    //   290: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   293: invokevirtual 214	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   296: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: pop
    //   300: aload 7
    //   302: ldc 25
    //   304: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: pop
    //   308: aload 7
    //   310: new 198	java/lang/StringBuilder
    //   313: dup
    //   314: invokespecial 199	java/lang/StringBuilder:<init>	()V
    //   317: ldc 204
    //   319: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: iload_2
    //   323: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   326: invokevirtual 214	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   329: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: pop
    //   333: aload 7
    //   335: ldc 25
    //   337: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: pop
    //   341: aload 7
    //   343: new 198	java/lang/StringBuilder
    //   346: dup
    //   347: invokespecial 199	java/lang/StringBuilder:<init>	()V
    //   350: ldc 204
    //   352: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: iload_1
    //   356: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   359: invokevirtual 214	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   362: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: pop
    //   366: aload 7
    //   368: ldc 25
    //   370: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   373: pop
    //   374: aload 7
    //   376: new 95	java/lang/String
    //   379: dup
    //   380: aload 6
    //   382: ldc 216
    //   384: invokespecial 219	java/lang/String:<init>	([BLjava/lang/String;)V
    //   387: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: pop
    //   391: ldc 221
    //   393: new 198	java/lang/StringBuilder
    //   396: dup
    //   397: invokespecial 199	java/lang/StringBuilder:<init>	()V
    //   400: ldc 223
    //   402: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: aload 7
    //   407: invokevirtual 214	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   410: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   413: invokevirtual 214	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   416: invokestatic 228	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   419: pop
    //   420: aload 7
    //   422: invokevirtual 214	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   425: areturn
    //   426: iload_1
    //   427: iconst_1
    //   428: iadd
    //   429: istore_1
    //   430: goto -367 -> 63
    //   433: ldc 230
    //   435: aload 9
    //   437: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   440: istore 5
    //   442: iload 5
    //   444: ifeq +396 -> 840
    //   447: iconst_4
    //   448: istore_1
    //   449: goto -285 -> 164
    //   452: astore 8
    //   454: iload_2
    //   455: istore_1
    //   456: iload_1
    //   457: istore_2
    //   458: goto -285 -> 173
    //   461: iload_2
    //   462: istore_3
    //   463: ldc 232
    //   465: aload 8
    //   467: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   470: ifeq +8 -> 478
    //   473: iconst_2
    //   474: istore_1
    //   475: goto -261 -> 214
    //   478: iload_2
    //   479: istore_3
    //   480: ldc 234
    //   482: aload 8
    //   484: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   487: ifeq +9 -> 496
    //   490: bipush 8
    //   492: istore_1
    //   493: goto -279 -> 214
    //   496: iload_2
    //   497: istore_3
    //   498: ldc 236
    //   500: aload 8
    //   502: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   505: ifeq +8 -> 513
    //   508: iconst_5
    //   509: istore_1
    //   510: goto -296 -> 214
    //   513: iload_2
    //   514: istore_3
    //   515: ldc 238
    //   517: aload 8
    //   519: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   522: ifeq +9 -> 531
    //   525: bipush 6
    //   527: istore_1
    //   528: goto -314 -> 214
    //   531: iload_2
    //   532: istore_3
    //   533: ldc 240
    //   535: aload 8
    //   537: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   540: ifeq +9 -> 549
    //   543: bipush 7
    //   545: istore_1
    //   546: goto -332 -> 214
    //   549: iload_2
    //   550: istore_3
    //   551: ldc 163
    //   553: aload 8
    //   555: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   558: ifeq +8 -> 566
    //   561: iconst_3
    //   562: istore_1
    //   563: goto -349 -> 214
    //   566: iload_2
    //   567: istore_3
    //   568: ldc 230
    //   570: aload 8
    //   572: invokevirtual 167	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   575: istore 5
    //   577: iload_2
    //   578: istore_1
    //   579: iload 5
    //   581: ifeq -367 -> 214
    //   584: iconst_4
    //   585: istore_1
    //   586: goto -372 -> 214
    //   589: iload 4
    //   591: ifne +246 -> 837
    //   594: aload_0
    //   595: ldc 242
    //   597: invokevirtual 114	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   600: checkcast 244	android/telephony/TelephonyManager
    //   603: invokevirtual 248	android/telephony/TelephonyManager:getCellLocation	()Landroid/telephony/CellLocation;
    //   606: astore 8
    //   608: bipush 6
    //   610: newarray byte
    //   612: astore 7
    //   614: aload 8
    //   616: instanceof 250
    //   619: ifeq +74 -> 693
    //   622: aload 8
    //   624: checkcast 250	android/telephony/gsm/GsmCellLocation
    //   627: astore 6
    //   629: aload 6
    //   631: ifnull +222 -> 853
    //   634: aload 6
    //   636: invokevirtual 253	android/telephony/gsm/GsmCellLocation:getLac	()I
    //   639: istore_3
    //   640: aload 7
    //   642: iconst_3
    //   643: ldc 254
    //   645: iload_3
    //   646: iand
    //   647: bipush 8
    //   649: ishr
    //   650: i2b
    //   651: bastore
    //   652: aload 7
    //   654: iconst_2
    //   655: iload_3
    //   656: sipush 255
    //   659: iand
    //   660: i2b
    //   661: bastore
    //   662: aload 6
    //   664: invokevirtual 257	android/telephony/gsm/GsmCellLocation:getCid	()I
    //   667: istore_3
    //   668: aload 7
    //   670: iconst_1
    //   671: ldc 254
    //   673: iload_3
    //   674: iand
    //   675: bipush 8
    //   677: ishr
    //   678: i2b
    //   679: bastore
    //   680: aload 7
    //   682: iconst_0
    //   683: iload_3
    //   684: sipush 255
    //   687: iand
    //   688: i2b
    //   689: bastore
    //   690: goto +163 -> 853
    //   693: aload 8
    //   695: instanceof 259
    //   698: ifeq +71 -> 769
    //   701: aload 8
    //   703: checkcast 259	android/telephony/cdma/CdmaCellLocation
    //   706: astore 6
    //   708: aload 6
    //   710: ifnull +59 -> 769
    //   713: aload 6
    //   715: invokevirtual 262	android/telephony/cdma/CdmaCellLocation:getNetworkId	()I
    //   718: istore_3
    //   719: aload 7
    //   721: iconst_3
    //   722: ldc 254
    //   724: iload_3
    //   725: iand
    //   726: bipush 8
    //   728: ishr
    //   729: i2b
    //   730: bastore
    //   731: aload 7
    //   733: iconst_2
    //   734: iload_3
    //   735: sipush 255
    //   738: iand
    //   739: i2b
    //   740: bastore
    //   741: aload 6
    //   743: invokevirtual 265	android/telephony/cdma/CdmaCellLocation:getBaseStationId	()I
    //   746: istore_3
    //   747: aload 7
    //   749: iconst_1
    //   750: ldc 254
    //   752: iload_3
    //   753: iand
    //   754: bipush 8
    //   756: ishr
    //   757: i2b
    //   758: bastore
    //   759: aload 7
    //   761: iconst_0
    //   762: iload_3
    //   763: sipush 255
    //   766: iand
    //   767: i2b
    //   768: bastore
    //   769: aload 7
    //   771: astore 6
    //   773: goto -512 -> 261
    //   776: astore_0
    //   777: aload 7
    //   779: new 95	java/lang/String
    //   782: dup
    //   783: aload 6
    //   785: invokespecial 268	java/lang/String:<init>	([B)V
    //   788: invokevirtual 208	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   791: pop
    //   792: goto -401 -> 391
    //   795: astore 8
    //   797: goto -761 -> 36
    //   800: astore 7
    //   802: iconst_0
    //   803: istore_1
    //   804: iconst_m1
    //   805: istore_2
    //   806: goto -545 -> 261
    //   809: astore 7
    //   811: iload_3
    //   812: istore_1
    //   813: iconst_m1
    //   814: istore_2
    //   815: goto -554 -> 261
    //   818: astore 7
    //   820: goto -559 -> 261
    //   823: astore 6
    //   825: aload 7
    //   827: astore 6
    //   829: goto -568 -> 261
    //   832: astore 8
    //   834: goto -378 -> 456
    //   837: goto -576 -> 261
    //   840: iconst_0
    //   841: istore_1
    //   842: goto -678 -> 164
    //   845: iconst_0
    //   846: istore_1
    //   847: goto -676 -> 171
    //   850: goto -757 -> 93
    //   853: aload 7
    //   855: astore 6
    //   857: goto -596 -> 261
    //
    // Exception table:
    //   from	to	target	type
    //   93	109	452	java/lang/Throwable
    //   114	157	452	java/lang/Throwable
    //   433	442	452	java/lang/Throwable
    //   374	391	776	java/lang/Throwable
    //   25	32	795	java/lang/Throwable
    //   11	25	800	java/lang/Throwable
    //   41	49	800	java/lang/Throwable
    //   49	56	800	java/lang/Throwable
    //   63	70	800	java/lang/Throwable
    //   77	87	800	java/lang/Throwable
    //   181	188	809	java/lang/Throwable
    //   192	200	809	java/lang/Throwable
    //   202	212	809	java/lang/Throwable
    //   216	223	809	java/lang/Throwable
    //   225	231	809	java/lang/Throwable
    //   463	473	809	java/lang/Throwable
    //   480	490	809	java/lang/Throwable
    //   498	508	809	java/lang/Throwable
    //   515	525	809	java/lang/Throwable
    //   533	543	809	java/lang/Throwable
    //   551	561	809	java/lang/Throwable
    //   568	577	809	java/lang/Throwable
    //   237	257	818	java/lang/Throwable
    //   594	614	818	java/lang/Throwable
    //   614	629	823	java/lang/Throwable
    //   634	640	823	java/lang/Throwable
    //   662	668	823	java/lang/Throwable
    //   693	708	823	java/lang/Throwable
    //   713	719	823	java/lang/Throwable
    //   741	747	823	java/lang/Throwable
    //   164	171	832	java/lang/Throwable
  }

  private static final String a(String paramString, Map<String, String> paramMap)
  {
    int i1 = 0;
    try
    {
      ArrayList localArrayList = new ArrayList();
      int i2;
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = paramString.split("&");
        if (paramString != null)
          i2 = paramString.length;
      }
      while (true)
      {
        Object localObject;
        if (i1 < i2)
        {
          localObject = paramString[i1].split("=");
          if ((localObject != null) && (localObject.length == 2))
            localArrayList.add(localObject[0] + localObject[1]);
        }
        else
        {
          if ((paramMap != null) && (paramMap.size() > 0))
          {
            paramString = paramMap.entrySet().iterator();
            while (paramString.hasNext())
            {
              localObject = (Map.Entry)paramString.next();
              paramMap = (String)((Map.Entry)localObject).getKey();
              localObject = (String)((Map.Entry)localObject).getValue();
              if ((!TextUtils.isEmpty(paramMap)) && (!TextUtils.isEmpty((CharSequence)localObject)))
                localArrayList.add(paramMap + (String)localObject);
            }
          }
          if ((localArrayList != null) && (localArrayList.size() > 0))
          {
            Collections.sort(localArrayList);
            paramString = new StringBuilder();
            paramMap = localArrayList.iterator();
            while (paramMap.hasNext())
              paramString.append((String)paramMap.next());
            paramString = paramString.toString();
            return paramString;
          }
          return null;
        }
        i1 += 1;
      }
    }
    catch (Throwable paramString)
    {
    }
    return null;
  }

  private static final byte[] a(String paramString)
  {
    byte[] arrayOfByte = new byte[6];
    if ((paramString == null) || (paramString.length() != 17))
      return arrayOfByte;
    paramString = paramString.split(":");
    int i1 = 0;
    while (i1 < paramString.length)
    {
      arrayOfByte[i1] = ((byte)Integer.parseInt(paramString[i1], 16));
      i1 += 1;
    }
    return arrayOfByte;
  }

  private static final int b(Context paramContext)
  {
    paramContext = (String)bD.g(paramContext).get("netType");
    if (!TextUtils.isEmpty(paramContext))
    {
      if ("wifi".equals(paramContext))
        return 1;
      if ("2g".equals(paramContext))
        return 2;
      if ("3g".equals(paramContext))
        return 0;
      if ("4g".equals(paramContext))
        return 3;
    }
    return -1;
  }

  public static final String generatorClient(Context paramContext, int paramInt1, short paramShort, long paramLong1, long paramLong2, int paramInt2, int paramInt3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(a(paramContext));
    localStringBuilder.append("|");
    localStringBuilder.append("" + paramInt1);
    localStringBuilder.append("|");
    localStringBuilder.append("" + paramShort);
    localStringBuilder.append("|");
    localStringBuilder.append("" + a(paramLong1));
    localStringBuilder.append("|");
    localStringBuilder.append("" + a(paramLong2));
    localStringBuilder.append("|");
    localStringBuilder.append("" + paramInt2);
    localStringBuilder.append("|");
    localStringBuilder.append("" + paramInt3);
    return bv.a(localStringBuilder.toString().getBytes());
  }

  public static final String generatorSign(Context paramContext, String paramString1, Map<String, String> paramMap, String paramString2, String paramString3)
  {
    while (true)
    {
      try
      {
        URI localURI = new URI(paramString1);
        paramString2 = a();
        paramContext = new StringBuilder();
        paramContext.append(paramString2);
        paramContext.append(localURI.getPath());
        paramMap = a(localURI.getQuery(), paramMap);
        if (!TextUtils.isEmpty(paramMap))
          paramContext.append(paramMap);
        if (!TextUtils.isEmpty(paramString3))
        {
          paramContext = by.a(paramString3, paramContext.toString());
          paramMap = new StringBuilder();
          paramMap.append(paramString2);
          paramMap.append(paramContext);
          paramContext = bv.a(paramMap.toString().getBytes());
          return paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        bd.d("SignHelper", "generatorSign[" + paramString1 + "]", paramContext);
        return null;
      }
      paramContext = null;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.SignHelper
 * JD-Core Version:    0.6.2
 */