package com.umeng.message.local;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UmengLocalNotificationHelper
{
  private static final String a = UmengLocalNotificationHelper.class.getName();

  public static String getDateFromTime(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(paramLong));
  }

  public static String getDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    String str = "" + paramInt1 + "-";
    if (paramInt2 < 10)
    {
      str = str + "0" + paramInt2 + "-";
      if (paramInt3 >= 10)
        break label238;
      str = str + "0" + paramInt3 + " ";
      label100: if (paramInt4 >= 10)
        break label267;
      str = str + "0" + paramInt4 + ":";
      label137: if (paramInt5 >= 10)
        break label296;
    }
    label267: label296: for (str = str + "0" + paramInt5 + ":"; ; str = str + paramInt5 + ":")
    {
      if (paramInt6 >= 10)
        break label326;
      return str + "0" + paramInt6;
      str = str + paramInt2 + "-";
      break;
      label238: str = str + paramInt3 + " ";
      break label100;
      str = str + paramInt4 + ":";
      break label137;
    }
    label326: return str + paramInt6;
  }

  public static long getQingMingTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws Exception
  {
    Object localObject = String.valueOf(paramInt1);
    int j = Integer.parseInt(((String)localObject).substring(((String)localObject).length() - 2, ((String)localObject).length()));
    int i = (int)(j * 0.2422D + 4.81D);
    j /= 4;
    localObject = paramInt1 + "-04-0" + (i - j) + " ";
    StringBuilder localStringBuilder = new StringBuilder().append((String)localObject);
    if (paramInt2 >= 10)
    {
      localObject = Integer.valueOf(paramInt2);
      localObject = localObject + ":";
      localStringBuilder = new StringBuilder().append((String)localObject);
      if (paramInt3 < 10)
        break label233;
      localObject = Integer.valueOf(paramInt3);
      label152: localObject = localObject + ":";
      localStringBuilder = new StringBuilder().append((String)localObject);
      if (paramInt4 < 10)
        break label257;
    }
    label257: for (localObject = Integer.valueOf(paramInt4); ; localObject = "0" + paramInt4)
    {
      return getTimeFromDate(localObject);
      localObject = "0" + paramInt2;
      break;
      label233: localObject = "0" + paramInt3;
      break label152;
    }
  }

  // ERROR //
  public static long getTimeAndUpdateLocalNotification(android.content.Context paramContext, UmengLocalNotification paramUmengLocalNotification)
  {
    // Byte code:
    //   0: invokestatic 112	java/lang/System:currentTimeMillis	()J
    //   3: lstore 16
    //   5: aload_1
    //   6: invokevirtual 117	com/umeng/message/local/UmengLocalNotification:getYear	()I
    //   9: istore 4
    //   11: aload_1
    //   12: invokevirtual 120	com/umeng/message/local/UmengLocalNotification:getMonth	()I
    //   15: istore_3
    //   16: aload_1
    //   17: invokevirtual 123	com/umeng/message/local/UmengLocalNotification:getDay	()I
    //   20: istore_2
    //   21: aload_1
    //   22: invokevirtual 126	com/umeng/message/local/UmengLocalNotification:getHour	()I
    //   25: istore 9
    //   27: aload_1
    //   28: invokevirtual 129	com/umeng/message/local/UmengLocalNotification:getMinute	()I
    //   31: istore 6
    //   33: aload_1
    //   34: invokevirtual 132	com/umeng/message/local/UmengLocalNotification:getSecond	()I
    //   37: istore 7
    //   39: aload_1
    //   40: invokevirtual 135	com/umeng/message/local/UmengLocalNotification:getRepeatingNum	()I
    //   43: istore 8
    //   45: invokestatic 141	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   48: astore 18
    //   50: aload 18
    //   52: iconst_1
    //   53: iload 4
    //   55: invokevirtual 145	java/util/Calendar:set	(II)V
    //   58: aload 18
    //   60: iconst_2
    //   61: iload_3
    //   62: iconst_1
    //   63: isub
    //   64: invokevirtual 145	java/util/Calendar:set	(II)V
    //   67: aload 18
    //   69: iconst_5
    //   70: iload_2
    //   71: invokevirtual 145	java/util/Calendar:set	(II)V
    //   74: aload 18
    //   76: bipush 11
    //   78: iload 9
    //   80: invokevirtual 145	java/util/Calendar:set	(II)V
    //   83: aload 18
    //   85: bipush 12
    //   87: iload 6
    //   89: invokevirtual 145	java/util/Calendar:set	(II)V
    //   92: aload 18
    //   94: bipush 13
    //   96: iload 7
    //   98: invokevirtual 145	java/util/Calendar:set	(II)V
    //   101: aload 18
    //   103: invokevirtual 148	java/util/Calendar:getTimeInMillis	()J
    //   106: lstore 12
    //   108: aload_1
    //   109: invokevirtual 151	com/umeng/message/local/UmengLocalNotification:getSpecialDay	()I
    //   112: istore 11
    //   114: lload 12
    //   116: ldc2_w 152
    //   119: lload 16
    //   121: ladd
    //   122: lcmp
    //   123: ifge +1677 -> 1800
    //   126: iload 8
    //   128: ifle +1672 -> 1800
    //   131: iload 11
    //   133: ifeq +1091 -> 1224
    //   136: aload_1
    //   137: invokevirtual 156	com/umeng/message/local/UmengLocalNotification:getRepeatingInterval	()I
    //   140: iload 4
    //   142: iadd
    //   143: istore 4
    //   145: iload 11
    //   147: tableswitch	default:+61 -> 208, 1:+78->225, 2:+315->462, 3:+356->503, 4:+384->531, 5:+413->560, 6:+433->580, 7:+670->817, 8:+698->845, 9:+728->875, 10:+758->905, 11:+995->1142, 12:+1025->1172
    //   209: lconst_0
    //   210: istore 5
    //   212: iload 8
    //   214: iconst_1
    //   215: isub
    //   216: istore 8
    //   218: iload 5
    //   220: istore 9
    //   222: goto -108 -> 114
    //   225: new 42	java/lang/StringBuilder
    //   228: dup
    //   229: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   232: iload 4
    //   234: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   237: ldc 158
    //   239: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   245: astore 18
    //   247: new 42	java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   254: aload 18
    //   256: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: astore 19
    //   261: iload 9
    //   263: bipush 10
    //   265: if_icmplt +122 -> 387
    //   268: iload 9
    //   270: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   273: astore 18
    //   275: aload 19
    //   277: aload 18
    //   279: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   282: ldc 63
    //   284: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   290: astore 18
    //   292: new 42	java/lang/StringBuilder
    //   295: dup
    //   296: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   299: aload 18
    //   301: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: astore 19
    //   306: iload 6
    //   308: bipush 10
    //   310: if_icmplt +102 -> 412
    //   313: iload 6
    //   315: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   318: astore 18
    //   320: aload 19
    //   322: aload 18
    //   324: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   327: ldc 63
    //   329: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   335: astore 18
    //   337: new 42	java/lang/StringBuilder
    //   340: dup
    //   341: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   344: aload 18
    //   346: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: astore 19
    //   351: iload 7
    //   353: bipush 10
    //   355: if_icmplt +82 -> 437
    //   358: iload 7
    //   360: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   363: astore 18
    //   365: aload 19
    //   367: aload 18
    //   369: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   372: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   375: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   378: lstore 14
    //   380: lload 14
    //   382: lstore 12
    //   384: goto -176 -> 208
    //   387: new 42	java/lang/StringBuilder
    //   390: dup
    //   391: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   394: ldc 59
    //   396: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: iload 9
    //   401: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   404: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   407: astore 18
    //   409: goto -134 -> 275
    //   412: new 42	java/lang/StringBuilder
    //   415: dup
    //   416: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   419: ldc 59
    //   421: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: iload 6
    //   426: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   429: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   432: astore 18
    //   434: goto -114 -> 320
    //   437: new 42	java/lang/StringBuilder
    //   440: dup
    //   441: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   444: ldc 59
    //   446: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: iload 7
    //   451: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   454: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   457: astore 18
    //   459: goto -94 -> 365
    //   462: iload 4
    //   464: bipush 12
    //   466: invokestatic 164	com/umeng/message/local/UmengCalendar:iGetLMonthDays	(II)I
    //   469: istore 5
    //   471: iload 5
    //   473: istore_2
    //   474: iload 4
    //   476: bipush 12
    //   478: iload_2
    //   479: iload 9
    //   481: iload 6
    //   483: iload 7
    //   485: invokestatic 166	com/umeng/message/local/UmengLocalNotificationHelper:getDateTime	(IIIIII)Ljava/lang/String;
    //   488: invokestatic 170	com/umeng/message/local/UmengCalendar:lunarTosolar	(Ljava/lang/String;)Ljava/lang/String;
    //   491: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   494: lstore 14
    //   496: lload 14
    //   498: lstore 12
    //   500: goto -292 -> 208
    //   503: iload 4
    //   505: iconst_1
    //   506: iconst_1
    //   507: iload 9
    //   509: iload 6
    //   511: iload 7
    //   513: invokestatic 166	com/umeng/message/local/UmengLocalNotificationHelper:getDateTime	(IIIIII)Ljava/lang/String;
    //   516: invokestatic 170	com/umeng/message/local/UmengCalendar:lunarTosolar	(Ljava/lang/String;)Ljava/lang/String;
    //   519: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   522: lstore 14
    //   524: lload 14
    //   526: lstore 12
    //   528: goto -320 -> 208
    //   531: iload 4
    //   533: iconst_1
    //   534: bipush 15
    //   536: iload 9
    //   538: iload 6
    //   540: iload 7
    //   542: invokestatic 166	com/umeng/message/local/UmengLocalNotificationHelper:getDateTime	(IIIIII)Ljava/lang/String;
    //   545: invokestatic 170	com/umeng/message/local/UmengCalendar:lunarTosolar	(Ljava/lang/String;)Ljava/lang/String;
    //   548: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   551: lstore 14
    //   553: lload 14
    //   555: lstore 12
    //   557: goto -349 -> 208
    //   560: iload 4
    //   562: iload 9
    //   564: iload 6
    //   566: iload 7
    //   568: invokestatic 172	com/umeng/message/local/UmengLocalNotificationHelper:getQingMingTime	(IIII)J
    //   571: lstore 14
    //   573: lload 14
    //   575: lstore 12
    //   577: goto -369 -> 208
    //   580: new 42	java/lang/StringBuilder
    //   583: dup
    //   584: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   587: iload 4
    //   589: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   592: ldc 174
    //   594: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   600: astore 18
    //   602: new 42	java/lang/StringBuilder
    //   605: dup
    //   606: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   609: aload 18
    //   611: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: astore 19
    //   616: iload 9
    //   618: bipush 10
    //   620: if_icmplt +122 -> 742
    //   623: iload 9
    //   625: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   628: astore 18
    //   630: aload 19
    //   632: aload 18
    //   634: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   637: ldc 63
    //   639: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   642: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   645: astore 18
    //   647: new 42	java/lang/StringBuilder
    //   650: dup
    //   651: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   654: aload 18
    //   656: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: astore 19
    //   661: iload 6
    //   663: bipush 10
    //   665: if_icmplt +102 -> 767
    //   668: iload 6
    //   670: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   673: astore 18
    //   675: aload 19
    //   677: aload 18
    //   679: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   682: ldc 63
    //   684: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   690: astore 18
    //   692: new 42	java/lang/StringBuilder
    //   695: dup
    //   696: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   699: aload 18
    //   701: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   704: astore 19
    //   706: iload 7
    //   708: bipush 10
    //   710: if_icmplt +82 -> 792
    //   713: iload 7
    //   715: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   718: astore 18
    //   720: aload 19
    //   722: aload 18
    //   724: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   727: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   730: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   733: lstore 14
    //   735: lload 14
    //   737: lstore 12
    //   739: goto -531 -> 208
    //   742: new 42	java/lang/StringBuilder
    //   745: dup
    //   746: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   749: ldc 59
    //   751: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   754: iload 9
    //   756: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   759: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   762: astore 18
    //   764: goto -134 -> 630
    //   767: new 42	java/lang/StringBuilder
    //   770: dup
    //   771: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   774: ldc 59
    //   776: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   779: iload 6
    //   781: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   784: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   787: astore 18
    //   789: goto -114 -> 675
    //   792: new 42	java/lang/StringBuilder
    //   795: dup
    //   796: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   799: ldc 59
    //   801: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   804: iload 7
    //   806: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   809: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   812: astore 18
    //   814: goto -94 -> 720
    //   817: iload 4
    //   819: iconst_5
    //   820: iconst_5
    //   821: iload 9
    //   823: iload 6
    //   825: iload 7
    //   827: invokestatic 166	com/umeng/message/local/UmengLocalNotificationHelper:getDateTime	(IIIIII)Ljava/lang/String;
    //   830: invokestatic 170	com/umeng/message/local/UmengCalendar:lunarTosolar	(Ljava/lang/String;)Ljava/lang/String;
    //   833: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   836: lstore 14
    //   838: lload 14
    //   840: lstore 12
    //   842: goto -634 -> 208
    //   845: iload 4
    //   847: bipush 7
    //   849: bipush 7
    //   851: iload 9
    //   853: iload 6
    //   855: iload 7
    //   857: invokestatic 166	com/umeng/message/local/UmengLocalNotificationHelper:getDateTime	(IIIIII)Ljava/lang/String;
    //   860: invokestatic 170	com/umeng/message/local/UmengCalendar:lunarTosolar	(Ljava/lang/String;)Ljava/lang/String;
    //   863: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   866: lstore 14
    //   868: lload 14
    //   870: lstore 12
    //   872: goto -664 -> 208
    //   875: iload 4
    //   877: bipush 8
    //   879: bipush 15
    //   881: iload 9
    //   883: iload 6
    //   885: iload 7
    //   887: invokestatic 166	com/umeng/message/local/UmengLocalNotificationHelper:getDateTime	(IIIIII)Ljava/lang/String;
    //   890: invokestatic 170	com/umeng/message/local/UmengCalendar:lunarTosolar	(Ljava/lang/String;)Ljava/lang/String;
    //   893: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   896: lstore 14
    //   898: lload 14
    //   900: lstore 12
    //   902: goto -694 -> 208
    //   905: new 42	java/lang/StringBuilder
    //   908: dup
    //   909: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   912: iload 4
    //   914: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   917: ldc 176
    //   919: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   922: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   925: astore 18
    //   927: new 42	java/lang/StringBuilder
    //   930: dup
    //   931: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   934: aload 18
    //   936: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   939: astore 19
    //   941: iload 9
    //   943: bipush 10
    //   945: if_icmplt +122 -> 1067
    //   948: iload 9
    //   950: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   953: astore 18
    //   955: aload 19
    //   957: aload 18
    //   959: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   962: ldc 63
    //   964: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   967: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   970: astore 18
    //   972: new 42	java/lang/StringBuilder
    //   975: dup
    //   976: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   979: aload 18
    //   981: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   984: astore 19
    //   986: iload 6
    //   988: bipush 10
    //   990: if_icmplt +102 -> 1092
    //   993: iload 6
    //   995: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   998: astore 18
    //   1000: aload 19
    //   1002: aload 18
    //   1004: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1007: ldc 63
    //   1009: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1012: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1015: astore 18
    //   1017: new 42	java/lang/StringBuilder
    //   1020: dup
    //   1021: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   1024: aload 18
    //   1026: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1029: astore 19
    //   1031: iload 7
    //   1033: bipush 10
    //   1035: if_icmplt +82 -> 1117
    //   1038: iload 7
    //   1040: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1043: astore 18
    //   1045: aload 19
    //   1047: aload 18
    //   1049: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1052: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1055: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   1058: lstore 14
    //   1060: lload 14
    //   1062: lstore 12
    //   1064: goto -856 -> 208
    //   1067: new 42	java/lang/StringBuilder
    //   1070: dup
    //   1071: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   1074: ldc 59
    //   1076: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1079: iload 9
    //   1081: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1084: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1087: astore 18
    //   1089: goto -134 -> 955
    //   1092: new 42	java/lang/StringBuilder
    //   1095: dup
    //   1096: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   1099: ldc 59
    //   1101: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1104: iload 6
    //   1106: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1109: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1112: astore 18
    //   1114: goto -114 -> 1000
    //   1117: new 42	java/lang/StringBuilder
    //   1120: dup
    //   1121: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   1124: ldc 59
    //   1126: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1129: iload 7
    //   1131: invokevirtual 52	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1134: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1137: astore 18
    //   1139: goto -94 -> 1045
    //   1142: iload 4
    //   1144: bipush 9
    //   1146: bipush 9
    //   1148: iload 9
    //   1150: iload 6
    //   1152: iload 7
    //   1154: invokestatic 166	com/umeng/message/local/UmengLocalNotificationHelper:getDateTime	(IIIIII)Ljava/lang/String;
    //   1157: invokestatic 170	com/umeng/message/local/UmengCalendar:lunarTosolar	(Ljava/lang/String;)Ljava/lang/String;
    //   1160: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   1163: lstore 14
    //   1165: lload 14
    //   1167: lstore 12
    //   1169: goto -961 -> 208
    //   1172: iload 4
    //   1174: bipush 12
    //   1176: bipush 8
    //   1178: iload 9
    //   1180: iload 6
    //   1182: iload 7
    //   1184: invokestatic 166	com/umeng/message/local/UmengLocalNotificationHelper:getDateTime	(IIIIII)Ljava/lang/String;
    //   1187: invokestatic 170	com/umeng/message/local/UmengCalendar:lunarTosolar	(Ljava/lang/String;)Ljava/lang/String;
    //   1190: invokestatic 103	com/umeng/message/local/UmengLocalNotificationHelper:getTimeFromDate	(Ljava/lang/String;)J
    //   1193: lstore 14
    //   1195: lload 14
    //   1197: lstore 12
    //   1199: goto -991 -> 208
    //   1202: astore 18
    //   1204: aload 18
    //   1206: invokevirtual 179	java/lang/Exception:printStackTrace	()V
    //   1209: getstatic 16	com/umeng/message/local/UmengLocalNotificationHelper:a	Ljava/lang/String;
    //   1212: aload 18
    //   1214: invokevirtual 180	java/lang/Exception:toString	()Ljava/lang/String;
    //   1217: invokestatic 186	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1220: pop
    //   1221: goto -1013 -> 208
    //   1224: aload_1
    //   1225: invokevirtual 189	com/umeng/message/local/UmengLocalNotification:getRepeatingUnit	()I
    //   1228: istore 5
    //   1230: aload_1
    //   1231: invokevirtual 156	com/umeng/message/local/UmengLocalNotification:getRepeatingInterval	()I
    //   1234: istore 10
    //   1236: iload 5
    //   1238: tableswitch	default:+38 -> 1276, 1:+45->1283, 2:+122->1360, 3:+232->1470, 4:+319->1557, 5:+403->1641, 6:+484->1722
    //   1277: lconst_0
    //   1278: istore 5
    //   1280: goto -1068 -> 212
    //   1283: iload 4
    //   1285: iload 10
    //   1287: iadd
    //   1288: istore 4
    //   1290: invokestatic 141	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   1293: astore 18
    //   1295: aload 18
    //   1297: iconst_1
    //   1298: iload 4
    //   1300: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1303: aload 18
    //   1305: iconst_2
    //   1306: iload_3
    //   1307: iconst_1
    //   1308: isub
    //   1309: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1312: aload 18
    //   1314: iconst_5
    //   1315: iload_2
    //   1316: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1319: aload 18
    //   1321: bipush 11
    //   1323: iload 9
    //   1325: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1328: aload 18
    //   1330: bipush 12
    //   1332: iload 6
    //   1334: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1337: aload 18
    //   1339: bipush 13
    //   1341: iload 7
    //   1343: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1346: aload 18
    //   1348: invokevirtual 148	java/util/Calendar:getTimeInMillis	()J
    //   1351: lstore 12
    //   1353: iload 9
    //   1355: istore 5
    //   1357: goto -1145 -> 212
    //   1360: iload_3
    //   1361: iload 10
    //   1363: iadd
    //   1364: istore 10
    //   1366: iload 10
    //   1368: istore_3
    //   1369: iload 4
    //   1371: istore 5
    //   1373: iload 10
    //   1375: bipush 12
    //   1377: if_icmple +19 -> 1396
    //   1380: iload 4
    //   1382: iload 10
    //   1384: bipush 12
    //   1386: idiv
    //   1387: iadd
    //   1388: istore 5
    //   1390: iload 10
    //   1392: bipush 12
    //   1394: irem
    //   1395: istore_3
    //   1396: invokestatic 141	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   1399: astore 18
    //   1401: aload 18
    //   1403: iconst_1
    //   1404: iload 5
    //   1406: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1409: aload 18
    //   1411: iconst_2
    //   1412: iload_3
    //   1413: iconst_1
    //   1414: isub
    //   1415: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1418: aload 18
    //   1420: iconst_5
    //   1421: iload_2
    //   1422: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1425: aload 18
    //   1427: bipush 11
    //   1429: iload 9
    //   1431: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1434: aload 18
    //   1436: bipush 12
    //   1438: iload 6
    //   1440: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1443: aload 18
    //   1445: bipush 13
    //   1447: iload 7
    //   1449: invokevirtual 145	java/util/Calendar:set	(II)V
    //   1452: aload 18
    //   1454: invokevirtual 148	java/util/Calendar:getTimeInMillis	()J
    //   1457: lstore 12
    //   1459: iload 5
    //   1461: istore 4
    //   1463: iload 9
    //   1465: istore 5
    //   1467: goto -1255 -> 212
    //   1470: lload 12
    //   1472: iload 10
    //   1474: bipush 24
    //   1476: imul
    //   1477: bipush 60
    //   1479: imul
    //   1480: bipush 60
    //   1482: imul
    //   1483: sipush 1000
    //   1486: imul
    //   1487: i2l
    //   1488: ladd
    //   1489: lstore 12
    //   1491: invokestatic 141	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   1494: astore 18
    //   1496: aload 18
    //   1498: lload 12
    //   1500: invokevirtual 192	java/util/Calendar:setTimeInMillis	(J)V
    //   1503: aload 18
    //   1505: iconst_1
    //   1506: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1509: istore 4
    //   1511: aload 18
    //   1513: iconst_2
    //   1514: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1517: iconst_1
    //   1518: iadd
    //   1519: istore_3
    //   1520: aload 18
    //   1522: iconst_5
    //   1523: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1526: istore_2
    //   1527: aload 18
    //   1529: bipush 11
    //   1531: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1534: istore 5
    //   1536: aload 18
    //   1538: bipush 12
    //   1540: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1543: istore 6
    //   1545: aload 18
    //   1547: bipush 13
    //   1549: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1552: istore 7
    //   1554: goto -1342 -> 212
    //   1557: lload 12
    //   1559: iload 10
    //   1561: bipush 60
    //   1563: imul
    //   1564: bipush 60
    //   1566: imul
    //   1567: sipush 1000
    //   1570: imul
    //   1571: i2l
    //   1572: ladd
    //   1573: lstore 12
    //   1575: invokestatic 141	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   1578: astore 18
    //   1580: aload 18
    //   1582: lload 12
    //   1584: invokevirtual 192	java/util/Calendar:setTimeInMillis	(J)V
    //   1587: aload 18
    //   1589: iconst_1
    //   1590: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1593: istore 4
    //   1595: aload 18
    //   1597: iconst_2
    //   1598: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1601: iconst_1
    //   1602: iadd
    //   1603: istore_3
    //   1604: aload 18
    //   1606: iconst_5
    //   1607: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1610: istore_2
    //   1611: aload 18
    //   1613: bipush 11
    //   1615: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1618: istore 5
    //   1620: aload 18
    //   1622: bipush 12
    //   1624: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1627: istore 6
    //   1629: aload 18
    //   1631: bipush 13
    //   1633: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1636: istore 7
    //   1638: goto -1426 -> 212
    //   1641: lload 12
    //   1643: iload 10
    //   1645: bipush 60
    //   1647: imul
    //   1648: sipush 1000
    //   1651: imul
    //   1652: i2l
    //   1653: ladd
    //   1654: lstore 12
    //   1656: invokestatic 141	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   1659: astore 18
    //   1661: aload 18
    //   1663: lload 12
    //   1665: invokevirtual 192	java/util/Calendar:setTimeInMillis	(J)V
    //   1668: aload 18
    //   1670: iconst_1
    //   1671: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1674: istore 4
    //   1676: aload 18
    //   1678: iconst_2
    //   1679: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1682: iconst_1
    //   1683: iadd
    //   1684: istore_3
    //   1685: aload 18
    //   1687: iconst_5
    //   1688: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1691: istore_2
    //   1692: aload 18
    //   1694: bipush 11
    //   1696: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1699: istore 5
    //   1701: aload 18
    //   1703: bipush 12
    //   1705: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1708: istore 6
    //   1710: aload 18
    //   1712: bipush 13
    //   1714: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1717: istore 7
    //   1719: goto -1507 -> 212
    //   1722: lload 12
    //   1724: iload 10
    //   1726: sipush 1000
    //   1729: imul
    //   1730: i2l
    //   1731: ladd
    //   1732: lstore 12
    //   1734: invokestatic 141	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   1737: astore 18
    //   1739: aload 18
    //   1741: lload 12
    //   1743: invokevirtual 192	java/util/Calendar:setTimeInMillis	(J)V
    //   1746: aload 18
    //   1748: iconst_1
    //   1749: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1752: istore 4
    //   1754: aload 18
    //   1756: iconst_2
    //   1757: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1760: iconst_1
    //   1761: iadd
    //   1762: istore_3
    //   1763: aload 18
    //   1765: iconst_5
    //   1766: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1769: istore_2
    //   1770: aload 18
    //   1772: bipush 11
    //   1774: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1777: istore 5
    //   1779: aload 18
    //   1781: bipush 12
    //   1783: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1786: istore 6
    //   1788: aload 18
    //   1790: bipush 13
    //   1792: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1795: istore 7
    //   1797: goto -1585 -> 212
    //   1800: invokestatic 141	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   1803: astore 18
    //   1805: aload 18
    //   1807: lload 12
    //   1809: invokevirtual 192	java/util/Calendar:setTimeInMillis	(J)V
    //   1812: aload 18
    //   1814: iconst_1
    //   1815: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1818: istore_2
    //   1819: aload 18
    //   1821: iconst_2
    //   1822: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1825: istore_3
    //   1826: aload 18
    //   1828: iconst_5
    //   1829: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1832: istore 4
    //   1834: aload 18
    //   1836: bipush 11
    //   1838: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1841: istore 5
    //   1843: aload 18
    //   1845: bipush 12
    //   1847: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1850: istore 6
    //   1852: aload 18
    //   1854: bipush 13
    //   1856: invokevirtual 196	java/util/Calendar:get	(I)I
    //   1859: istore 7
    //   1861: aload_1
    //   1862: iload_2
    //   1863: invokevirtual 200	com/umeng/message/local/UmengLocalNotification:setYear	(I)V
    //   1866: aload_1
    //   1867: iload_3
    //   1868: iconst_1
    //   1869: iadd
    //   1870: invokevirtual 203	com/umeng/message/local/UmengLocalNotification:setMonth	(I)V
    //   1873: aload_1
    //   1874: iload 4
    //   1876: invokevirtual 206	com/umeng/message/local/UmengLocalNotification:setDay	(I)V
    //   1879: aload_1
    //   1880: iload 5
    //   1882: invokevirtual 209	com/umeng/message/local/UmengLocalNotification:setHour	(I)V
    //   1885: aload_1
    //   1886: iload 6
    //   1888: invokevirtual 212	com/umeng/message/local/UmengLocalNotification:setMinute	(I)V
    //   1891: aload_1
    //   1892: iload 7
    //   1894: invokevirtual 215	com/umeng/message/local/UmengLocalNotification:setSecond	(I)V
    //   1897: aload_1
    //   1898: iload 8
    //   1900: invokevirtual 218	com/umeng/message/local/UmengLocalNotification:setRepeatingNum	(I)V
    //   1903: aload_0
    //   1904: invokestatic 223	com/umeng/message/local/UmengLocalNotificationStore:getInstance	(Landroid/content/Context;)Lcom/umeng/message/local/UmengLocalNotificationStore;
    //   1907: aload_1
    //   1908: invokevirtual 227	com/umeng/message/local/UmengLocalNotificationStore:updateLocalNotification	(Lcom/umeng/message/local/UmengLocalNotification;)Z
    //   1911: pop
    //   1912: lload 12
    //   1914: lreturn
    //   1915: astore_0
    //   1916: getstatic 16	com/umeng/message/local/UmengLocalNotificationHelper:a	Ljava/lang/String;
    //   1919: aload_0
    //   1920: invokevirtual 180	java/lang/Exception:toString	()Ljava/lang/String;
    //   1923: invokestatic 186	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1926: pop
    //   1927: aload_0
    //   1928: invokevirtual 179	java/lang/Exception:printStackTrace	()V
    //   1931: lload 12
    //   1933: lreturn
    //   1934: astore 18
    //   1936: goto -732 -> 1204
    //
    // Exception table:
    //   from	to	target	type
    //   225	261	1202	java/lang/Exception
    //   268	275	1202	java/lang/Exception
    //   275	306	1202	java/lang/Exception
    //   313	320	1202	java/lang/Exception
    //   320	351	1202	java/lang/Exception
    //   358	365	1202	java/lang/Exception
    //   365	380	1202	java/lang/Exception
    //   387	409	1202	java/lang/Exception
    //   412	434	1202	java/lang/Exception
    //   437	459	1202	java/lang/Exception
    //   462	471	1202	java/lang/Exception
    //   503	524	1202	java/lang/Exception
    //   531	553	1202	java/lang/Exception
    //   560	573	1202	java/lang/Exception
    //   580	616	1202	java/lang/Exception
    //   623	630	1202	java/lang/Exception
    //   630	661	1202	java/lang/Exception
    //   668	675	1202	java/lang/Exception
    //   675	706	1202	java/lang/Exception
    //   713	720	1202	java/lang/Exception
    //   720	735	1202	java/lang/Exception
    //   742	764	1202	java/lang/Exception
    //   767	789	1202	java/lang/Exception
    //   792	814	1202	java/lang/Exception
    //   817	838	1202	java/lang/Exception
    //   845	868	1202	java/lang/Exception
    //   875	898	1202	java/lang/Exception
    //   905	941	1202	java/lang/Exception
    //   948	955	1202	java/lang/Exception
    //   955	986	1202	java/lang/Exception
    //   993	1000	1202	java/lang/Exception
    //   1000	1031	1202	java/lang/Exception
    //   1038	1045	1202	java/lang/Exception
    //   1045	1060	1202	java/lang/Exception
    //   1067	1089	1202	java/lang/Exception
    //   1092	1114	1202	java/lang/Exception
    //   1117	1139	1202	java/lang/Exception
    //   1142	1165	1202	java/lang/Exception
    //   1172	1195	1202	java/lang/Exception
    //   1903	1912	1915	java/lang/Exception
    //   474	496	1934	java/lang/Exception
  }

  public static long getTimeFromDate(String paramString)
    throws Exception
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString).getTime();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.local.UmengLocalNotificationHelper
 * JD-Core Version:    0.6.2
 */