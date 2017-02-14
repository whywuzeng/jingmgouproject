package org.android.agoo.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.aW;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bw;
import com.umeng.message.proguard.by;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.android.Config;
import org.android.agoo.net.mtop.IMtopSynClient;
import org.android.agoo.net.mtop.MtopSyncClientV3;
import org.android.agoo.service.ElectionReceiverService;
import org.android.agoo.service.ElectionReceiverService.Stub;
import org.android.agoo.ut.UTFactroy;
import org.json.JSONObject;

final class a
{
  private static final String a = "ElectionService";
  private static final IMtopSynClient b = new MtopSyncClientV3();
  private static final Random c = new Random();
  private static final String d = "set_CurrentSudo";
  private static final String e = "get_CurrentSudo";
  private static String f = null;
  private static String g = "";
  private static Map<String, String> h = null;
  private static Context i;
  private static HandlerThread j = null;
  private static Handler k = new Handler(j.getLooper(), new b());
  private static final String l = "org.agoo.android.sudo.%s";
  private static final String m = "org.agoo.android.packs_v1.%s";
  private static final String n = "17984173941739471471917341";

  static
  {
    j = new HandlerThread("electionService-thread");
    j.start();
  }

  private static int a(PackageInfo paramPackageInfo)
  {
    try
    {
      String str = paramPackageInfo.versionName;
      int i1 = paramPackageInfo.versionCode;
      i1 = Math.abs((str + "." + i1).hashCode());
      return i1;
    }
    catch (Throwable paramPackageInfo)
    {
    }
    return -1;
  }

  // ERROR //
  public static final String a(Context paramContext)
  {
    // Byte code:
    //   0: ldc 44
    //   2: iconst_1
    //   3: anewarray 4	java/lang/Object
    //   6: dup
    //   7: iconst_0
    //   8: aload_0
    //   9: invokestatic 147	org/android/Config:getAgooGroup	(Landroid/content/Context;)Ljava/lang/String;
    //   12: aastore
    //   13: invokestatic 151	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   16: astore_2
    //   17: aload_0
    //   18: invokevirtual 157	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   21: aload_2
    //   22: invokestatic 163	android/provider/Settings$System:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   25: astore_2
    //   26: aload_0
    //   27: invokestatic 165	org/android/agoo/impl/a:f	(Landroid/content/Context;)Ljava/lang/String;
    //   30: astore_3
    //   31: aload_3
    //   32: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   35: ifne +87 -> 122
    //   38: aload_2
    //   39: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   42: ifne +80 -> 122
    //   45: aload_3
    //   46: aload_2
    //   47: iconst_2
    //   48: invokestatic 176	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   51: astore_2
    //   52: aload_0
    //   53: aload_2
    //   54: invokestatic 179	org/android/agoo/impl/a:c	(Landroid/content/Context;Ljava/lang/String;)Z
    //   57: istore_1
    //   58: iload_1
    //   59: ifeq +34 -> 93
    //   62: ldc 24
    //   64: new 112	java/lang/StringBuilder
    //   67: dup
    //   68: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   71: ldc 181
    //   73: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: aload_2
    //   77: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: ldc 183
    //   82: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   91: aload_2
    //   92: areturn
    //   93: ldc 24
    //   95: new 112	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   102: ldc 190
    //   104: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: aload_2
    //   108: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: ldc 192
    //   113: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   122: aconst_null
    //   123: areturn
    //   124: astore_3
    //   125: aconst_null
    //   126: astore_2
    //   127: invokestatic 198	org/android/agoo/ut/UTFactroy:getInstance	()Lorg/android/agoo/ut/UTFactroy;
    //   130: aload_0
    //   131: ldc 30
    //   133: iconst_2
    //   134: anewarray 128	java/lang/String
    //   137: dup
    //   138: iconst_0
    //   139: ldc 30
    //   141: aastore
    //   142: dup
    //   143: iconst_1
    //   144: new 112	java/lang/StringBuilder
    //   147: dup
    //   148: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   151: ldc 200
    //   153: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: aload_3
    //   157: invokevirtual 201	java/lang/Throwable:toString	()Ljava/lang/String;
    //   160: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   166: aastore
    //   167: invokevirtual 205	org/android/agoo/ut/UTFactroy:commitEvent	(Landroid/content/Context;Ljava/lang/Object;[Ljava/lang/String;)V
    //   170: aload_2
    //   171: areturn
    //   172: astore_3
    //   173: goto -46 -> 127
    //
    // Exception table:
    //   from	to	target	type
    //   0	58	124	java/lang/Throwable
    //   93	122	124	java/lang/Throwable
    //   62	91	172	java/lang/Throwable
  }

  // ERROR //
  private static final a a(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 210	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore 10
    //   6: iload_3
    //   7: iconst_m1
    //   8: if_icmpne +17 -> 25
    //   11: aload 10
    //   13: aload_0
    //   14: invokevirtual 213	android/content/Context:getPackageName	()Ljava/lang/String;
    //   17: iconst_0
    //   18: invokevirtual 219	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   21: getfield 224	android/content/pm/ApplicationInfo:flags	I
    //   24: istore_3
    //   25: aload 10
    //   27: aload_1
    //   28: iconst_0
    //   29: invokevirtual 219	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   32: astore 9
    //   34: aload 9
    //   36: ifnonnull +34 -> 70
    //   39: ldc 24
    //   41: new 112	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   48: ldc 226
    //   50: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_1
    //   54: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc 228
    //   59: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   68: aconst_null
    //   69: areturn
    //   70: aload 9
    //   72: getfield 234	android/content/pm/ApplicationInfo:enabled	Z
    //   75: ifne +39 -> 114
    //   78: ldc 24
    //   80: new 112	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   87: ldc 226
    //   89: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload_1
    //   93: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: ldc 236
    //   98: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: aconst_null
    //   108: areturn
    //   109: astore_0
    //   110: aconst_null
    //   111: astore_0
    //   112: aload_0
    //   113: areturn
    //   114: aload 10
    //   116: aload_1
    //   117: iconst_0
    //   118: invokevirtual 240	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   121: astore 9
    //   123: aload 9
    //   125: ifnonnull +34 -> 159
    //   128: ldc 24
    //   130: new 112	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   137: ldc 226
    //   139: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_1
    //   143: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: ldc 242
    //   148: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   157: aconst_null
    //   158: areturn
    //   159: aload_2
    //   160: astore 10
    //   162: aload_2
    //   163: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   166: ifeq +151 -> 317
    //   169: ldc 47
    //   171: iconst_1
    //   172: anewarray 4	java/lang/Object
    //   175: dup
    //   176: iconst_0
    //   177: aload_0
    //   178: invokestatic 147	org/android/Config:getAgooGroup	(Landroid/content/Context;)Ljava/lang/String;
    //   181: aastore
    //   182: invokestatic 151	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   185: astore_2
    //   186: aload_0
    //   187: invokevirtual 157	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   190: aload_2
    //   191: invokestatic 163	android/provider/Settings$System:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   194: astore_2
    //   195: aload_0
    //   196: invokestatic 165	org/android/agoo/impl/a:f	(Landroid/content/Context;)Ljava/lang/String;
    //   199: astore_0
    //   200: aload_0
    //   201: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   204: ifne +473 -> 677
    //   207: aload_2
    //   208: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   211: ifne +466 -> 677
    //   214: aload_0
    //   215: aload_2
    //   216: iconst_2
    //   217: invokestatic 176	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   220: astore_2
    //   221: aload_2
    //   222: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   225: ifeq +34 -> 259
    //   228: ldc 24
    //   230: new 112	java/lang/StringBuilder
    //   233: dup
    //   234: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   237: ldc 226
    //   239: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: aload_1
    //   243: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: ldc 244
    //   248: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   257: aconst_null
    //   258: areturn
    //   259: new 246	org/json/JSONObject
    //   262: dup
    //   263: aload_2
    //   264: invokespecial 247	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   267: astore_0
    //   268: aload_0
    //   269: ifnonnull +41 -> 310
    //   272: ldc 24
    //   274: new 112	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   281: ldc 226
    //   283: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: aload_1
    //   287: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: ldc 249
    //   292: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: aload_2
    //   296: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: ldc 251
    //   301: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   307: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   310: aload_0
    //   311: aload_1
    //   312: invokevirtual 255	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   315: astore 10
    //   317: aload 10
    //   319: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   322: ifeq +35 -> 357
    //   325: ldc 24
    //   327: new 112	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   334: ldc 226
    //   336: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: aload_1
    //   340: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: ldc_w 257
    //   346: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   355: aconst_null
    //   356: areturn
    //   357: aload 10
    //   359: invokestatic 260	org/android/agoo/impl/a$a:a	(Ljava/lang/String;)Lorg/android/agoo/impl/a$a;
    //   362: astore_0
    //   363: aload_0
    //   364: ifnonnull +35 -> 399
    //   367: ldc 24
    //   369: new 112	java/lang/StringBuilder
    //   372: dup
    //   373: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   376: ldc 226
    //   378: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: aload_1
    //   382: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   385: ldc_w 262
    //   388: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   394: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   397: aconst_null
    //   398: areturn
    //   399: aload_0
    //   400: invokevirtual 265	org/android/agoo/impl/a$a:b	()J
    //   403: lstore 5
    //   405: aload_0
    //   406: invokevirtual 267	org/android/agoo/impl/a$a:a	()I
    //   409: istore_3
    //   410: lload 5
    //   412: ldc2_w 268
    //   415: lcmp
    //   416: ifeq +8 -> 424
    //   419: iload_3
    //   420: iconst_m1
    //   421: if_icmpne +35 -> 456
    //   424: ldc 24
    //   426: new 112	java/lang/StringBuilder
    //   429: dup
    //   430: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   433: ldc 226
    //   435: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: aload_1
    //   439: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: ldc_w 271
    //   445: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   451: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   454: aconst_null
    //   455: areturn
    //   456: aload 9
    //   458: invokestatic 274	org/android/agoo/impl/a:b	(Landroid/content/pm/PackageInfo;)J
    //   461: lstore 7
    //   463: aload 9
    //   465: invokestatic 276	org/android/agoo/impl/a:a	(Landroid/content/pm/PackageInfo;)I
    //   468: istore 4
    //   470: lload 7
    //   472: ldc2_w 268
    //   475: lcmp
    //   476: ifeq +12 -> 488
    //   479: lload 7
    //   481: ldc2_w 268
    //   484: lcmp
    //   485: ifne +35 -> 520
    //   488: ldc 24
    //   490: new 112	java/lang/StringBuilder
    //   493: dup
    //   494: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   497: ldc 226
    //   499: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: aload_1
    //   503: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: ldc_w 278
    //   509: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   512: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   515: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   518: aconst_null
    //   519: areturn
    //   520: lload 7
    //   522: lload 5
    //   524: lcmp
    //   525: ifeq +56 -> 581
    //   528: ldc 24
    //   530: new 112	java/lang/StringBuilder
    //   533: dup
    //   534: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   537: ldc 226
    //   539: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: aload_1
    //   543: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   546: ldc_w 280
    //   549: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: lload 7
    //   554: invokevirtual 283	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   557: ldc_w 285
    //   560: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: lload 5
    //   565: invokevirtual 283	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   568: ldc 236
    //   570: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   576: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   579: aconst_null
    //   580: areturn
    //   581: iload 4
    //   583: iload_3
    //   584: if_icmpeq +55 -> 639
    //   587: ldc 24
    //   589: new 112	java/lang/StringBuilder
    //   592: dup
    //   593: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   596: ldc 226
    //   598: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: aload_1
    //   602: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   605: ldc_w 287
    //   608: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   611: iload 4
    //   613: invokevirtual 122	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   616: ldc_w 289
    //   619: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   622: iload_3
    //   623: invokevirtual 122	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   626: ldc 236
    //   628: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   631: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   634: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   637: aconst_null
    //   638: areturn
    //   639: ldc 24
    //   641: new 112	java/lang/StringBuilder
    //   644: dup
    //   645: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   648: ldc 226
    //   650: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   653: aload_1
    //   654: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   657: ldc_w 291
    //   660: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   666: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   669: goto -557 -> 112
    //   672: astore 9
    //   674: goto -649 -> 25
    //   677: aconst_null
    //   678: areturn
    //   679: astore 9
    //   681: aconst_null
    //   682: astore 9
    //   684: goto -650 -> 34
    //   687: astore 9
    //   689: aconst_null
    //   690: astore 9
    //   692: goto -569 -> 123
    //   695: astore_0
    //   696: aconst_null
    //   697: astore_0
    //   698: goto -430 -> 268
    //
    // Exception table:
    //   from	to	target	type
    //   0	6	109	java/lang/Throwable
    //   39	68	109	java/lang/Throwable
    //   70	107	109	java/lang/Throwable
    //   128	157	109	java/lang/Throwable
    //   162	257	109	java/lang/Throwable
    //   272	310	109	java/lang/Throwable
    //   310	317	109	java/lang/Throwable
    //   317	355	109	java/lang/Throwable
    //   357	363	109	java/lang/Throwable
    //   367	397	109	java/lang/Throwable
    //   399	410	109	java/lang/Throwable
    //   424	454	109	java/lang/Throwable
    //   456	470	109	java/lang/Throwable
    //   488	518	109	java/lang/Throwable
    //   528	579	109	java/lang/Throwable
    //   587	637	109	java/lang/Throwable
    //   639	669	109	java/lang/Throwable
    //   11	25	672	java/lang/Throwable
    //   25	34	679	java/lang/Throwable
    //   114	123	687	java/lang/Throwable
    //   259	268	695	java/lang/Throwable
  }

  // ERROR //
  private static d a(Context paramContext, Map<String, Long> paramMap)
  {
    // Byte code:
    //   0: new 17	org/android/agoo/impl/a$d
    //   3: dup
    //   4: invokespecial 293	org/android/agoo/impl/a$d:<init>	()V
    //   7: astore 7
    //   9: aload_0
    //   10: invokestatic 298	org/android/agoo/a:m	(Landroid/content/Context;)Z
    //   13: ifeq +484 -> 497
    //   16: aload_0
    //   17: invokestatic 300	org/android/agoo/impl/a:g	(Landroid/content/Context;)Z
    //   20: ifeq +477 -> 497
    //   23: ldc 24
    //   25: new 112	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   32: ldc_w 302
    //   35: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_1
    //   39: invokevirtual 303	java/lang/Object:toString	()Ljava/lang/String;
    //   42: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc 183
    //   47: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   56: new 305	org/android/agoo/net/mtop/MtopRequest
    //   59: dup
    //   60: invokespecial 306	org/android/agoo/net/mtop/MtopRequest:<init>	()V
    //   63: astore 8
    //   65: aload 8
    //   67: ldc_w 308
    //   70: invokevirtual 311	org/android/agoo/net/mtop/MtopRequest:setApi	(Ljava/lang/String;)V
    //   73: aload 8
    //   75: ldc_w 313
    //   78: invokevirtual 316	org/android/agoo/net/mtop/MtopRequest:setV	(Ljava/lang/String;)V
    //   81: aload 8
    //   83: aload_0
    //   84: invokestatic 318	org/android/agoo/a:h	(Landroid/content/Context;)Ljava/lang/String;
    //   87: invokevirtual 321	org/android/agoo/net/mtop/MtopRequest:setTtId	(Ljava/lang/String;)V
    //   90: aload 8
    //   92: aload_0
    //   93: invokestatic 323	org/android/agoo/a:l	(Landroid/content/Context;)Ljava/lang/String;
    //   96: invokevirtual 326	org/android/agoo/net/mtop/MtopRequest:setDeviceId	(Ljava/lang/String;)V
    //   99: aload 8
    //   101: ldc_w 328
    //   104: new 246	org/json/JSONObject
    //   107: dup
    //   108: aload_1
    //   109: invokespecial 331	org/json/JSONObject:<init>	(Ljava/util/Map;)V
    //   112: invokevirtual 332	org/json/JSONObject:toString	()Ljava/lang/String;
    //   115: invokevirtual 336	org/android/agoo/net/mtop/MtopRequest:putParams	(Ljava/lang/String;Ljava/lang/Object;)V
    //   118: getstatic 59	org/android/agoo/impl/a:b	Lorg/android/agoo/net/mtop/IMtopSynClient;
    //   121: aload_0
    //   122: aload 8
    //   124: invokeinterface 342 3 0
    //   129: astore 8
    //   131: aload 8
    //   133: ifnull +389 -> 522
    //   136: aload 8
    //   138: invokevirtual 348	org/android/agoo/net/mtop/Result:getHeaders	()Ljava/util/Map;
    //   141: aload 8
    //   143: invokevirtual 351	org/android/agoo/net/mtop/Result:getHttpCode	()I
    //   146: invokestatic 356	com/umeng/message/proguard/bE:a	(Ljava/util/Map;I)Z
    //   149: ifne +47 -> 196
    //   152: aload 7
    //   154: ldc_w 358
    //   157: invokevirtual 360	org/android/agoo/impl/a$d:a	(Ljava/lang/String;)V
    //   160: aload 7
    //   162: aload 8
    //   164: invokevirtual 364	org/android/agoo/net/mtop/Result:isSuccess	()Z
    //   167: invokevirtual 367	org/android/agoo/impl/a$d:a	(Z)V
    //   170: getstatic 72	org/android/agoo/impl/a:h	Ljava/util/Map;
    //   173: ldc_w 369
    //   176: ldc_w 358
    //   179: invokeinterface 375 3 0
    //   184: pop
    //   185: ldc 24
    //   187: ldc_w 377
    //   190: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   193: aload 7
    //   195: areturn
    //   196: aload 8
    //   198: invokevirtual 364	org/android/agoo/net/mtop/Result:isSuccess	()Z
    //   201: ifeq +279 -> 480
    //   204: ldc 24
    //   206: new 112	java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   213: ldc_w 379
    //   216: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: aload 8
    //   221: invokevirtual 382	org/android/agoo/net/mtop/Result:getData	()Ljava/lang/String;
    //   224: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: ldc 183
    //   229: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   238: new 246	org/json/JSONObject
    //   241: dup
    //   242: aload 8
    //   244: invokevirtual 382	org/android/agoo/net/mtop/Result:getData	()Ljava/lang/String;
    //   247: invokespecial 247	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   250: astore 10
    //   252: ldc 24
    //   254: new 112	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   261: ldc_w 384
    //   264: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: aload 10
    //   269: invokevirtual 332	org/json/JSONObject:toString	()Ljava/lang/String;
    //   272: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: ldc 183
    //   277: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   283: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   286: aload 10
    //   288: ifnull +192 -> 480
    //   291: aload 10
    //   293: ldc_w 386
    //   296: invokevirtual 388	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   299: astore 9
    //   301: ldc2_w 268
    //   304: lstore_2
    //   305: ldc 24
    //   307: new 112	java/lang/StringBuilder
    //   310: dup
    //   311: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   314: ldc_w 390
    //   317: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: aload 9
    //   322: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: ldc 183
    //   327: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   333: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: aload 9
    //   338: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   341: istore 6
    //   343: iload 6
    //   345: ifne +110 -> 455
    //   348: aload 10
    //   350: ldc_w 392
    //   353: invokevirtual 388	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   356: invokestatic 398	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   359: lstore 4
    //   361: lload 4
    //   363: lstore_2
    //   364: aload 7
    //   366: aload 9
    //   368: invokevirtual 400	org/android/agoo/impl/a$d:c	(Ljava/lang/String;)V
    //   371: aload 7
    //   373: aload 8
    //   375: invokevirtual 364	org/android/agoo/net/mtop/Result:isSuccess	()Z
    //   378: invokevirtual 367	org/android/agoo/impl/a$d:a	(Z)V
    //   381: aload 7
    //   383: ldc_w 402
    //   386: invokevirtual 404	org/android/agoo/impl/a$d:b	(Ljava/lang/String;)V
    //   389: aload_0
    //   390: aload_1
    //   391: aload 9
    //   393: lload_2
    //   394: ldc_w 406
    //   397: invokestatic 409	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/util/Map;Ljava/lang/String;JLjava/lang/String;)V
    //   400: aload 7
    //   402: areturn
    //   403: astore_0
    //   404: aload 7
    //   406: ldc_w 402
    //   409: invokevirtual 404	org/android/agoo/impl/a$d:b	(Ljava/lang/String;)V
    //   412: aload 7
    //   414: aload_0
    //   415: invokevirtual 201	java/lang/Throwable:toString	()Ljava/lang/String;
    //   418: invokevirtual 360	org/android/agoo/impl/a$d:a	(Ljava/lang/String;)V
    //   421: aload 7
    //   423: iconst_0
    //   424: invokevirtual 367	org/android/agoo/impl/a$d:a	(Z)V
    //   427: getstatic 72	org/android/agoo/impl/a:h	Ljava/util/Map;
    //   430: ldc_w 369
    //   433: aload_0
    //   434: invokevirtual 201	java/lang/Throwable:toString	()Ljava/lang/String;
    //   437: invokeinterface 375 3 0
    //   442: pop
    //   443: ldc 24
    //   445: ldc_w 402
    //   448: aload_0
    //   449: invokestatic 412	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   452: aload 7
    //   454: areturn
    //   455: aload 7
    //   457: iconst_0
    //   458: invokevirtual 367	org/android/agoo/impl/a$d:a	(Z)V
    //   461: aload 7
    //   463: ldc_w 402
    //   466: invokevirtual 404	org/android/agoo/impl/a$d:b	(Ljava/lang/String;)V
    //   469: aload 7
    //   471: ldc_w 414
    //   474: invokevirtual 360	org/android/agoo/impl/a$d:a	(Ljava/lang/String;)V
    //   477: aload 7
    //   479: areturn
    //   480: aload 7
    //   482: ldc_w 402
    //   485: invokevirtual 404	org/android/agoo/impl/a$d:b	(Ljava/lang/String;)V
    //   488: aload 7
    //   490: iconst_0
    //   491: invokevirtual 367	org/android/agoo/impl/a$d:a	(Z)V
    //   494: aload 7
    //   496: areturn
    //   497: aload 7
    //   499: ldc_w 402
    //   502: invokevirtual 404	org/android/agoo/impl/a$d:b	(Ljava/lang/String;)V
    //   505: aload 7
    //   507: iconst_0
    //   508: invokevirtual 367	org/android/agoo/impl/a$d:a	(Z)V
    //   511: aload 7
    //   513: ldc_w 416
    //   516: invokevirtual 360	org/android/agoo/impl/a$d:a	(Ljava/lang/String;)V
    //   519: aload 7
    //   521: areturn
    //   522: aconst_null
    //   523: areturn
    //   524: astore 10
    //   526: goto -162 -> 364
    //
    // Exception table:
    //   from	to	target	type
    //   238	286	403	java/lang/Throwable
    //   291	301	403	java/lang/Throwable
    //   305	343	403	java/lang/Throwable
    //   364	400	403	java/lang/Throwable
    //   455	477	403	java/lang/Throwable
    //   348	361	524	java/lang/Throwable
  }

  static final void a(Context paramContext, Class<?> paramClass)
  {
    try
    {
      d(paramContext);
      if (paramClass != null)
        paramContext.getPackageManager().setComponentEnabledSetting(new ComponentName(paramContext, paramClass), 1, 1);
      return;
    }
    catch (Throwable paramContext)
    {
      bd.c("ElectionService", "registerApp", paramContext);
    }
  }

  // ERROR //
  static void a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: new 438	java/util/HashMap
    //   6: dup
    //   7: invokespecial 439	java/util/HashMap:<init>	()V
    //   10: astore 17
    //   12: aconst_null
    //   13: astore 14
    //   15: aconst_null
    //   16: astore 9
    //   18: aconst_null
    //   19: astore 13
    //   21: aconst_null
    //   22: astore 15
    //   24: aconst_null
    //   25: astore 12
    //   27: ldc 68
    //   29: astore 5
    //   31: aload_0
    //   32: invokestatic 441	org/android/agoo/impl/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   35: astore 18
    //   37: invokestatic 446	java/lang/System:currentTimeMillis	()J
    //   40: lstore_3
    //   41: new 438	java/util/HashMap
    //   44: dup
    //   45: invokespecial 439	java/util/HashMap:<init>	()V
    //   48: putstatic 72	org/android/agoo/impl/a:h	Ljava/util/Map;
    //   51: aload_1
    //   52: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   55: ifne +7 -> 62
    //   58: aload_1
    //   59: putstatic 66	org/android/agoo/impl/a:f	Ljava/lang/String;
    //   62: aload_0
    //   63: invokestatic 450	com/umeng/message/proguard/aW:g	(Landroid/content/Context;)V
    //   66: new 452	org/android/agoo/net/async/c
    //   69: dup
    //   70: aload_0
    //   71: ldc_w 454
    //   74: invokespecial 456	org/android/agoo/net/async/c:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   77: aload_0
    //   78: invokestatic 460	org/android/Config:getConnectHeader	(Landroid/content/Context;)Ljava/util/LinkedHashMap;
    //   81: invokevirtual 463	org/android/agoo/net/async/c:a	(Ljava/util/LinkedHashMap;)V
    //   84: new 246	org/json/JSONObject
    //   87: dup
    //   88: invokespecial 464	org/json/JSONObject:<init>	()V
    //   91: astore 22
    //   93: ldc 24
    //   95: new 112	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   102: ldc_w 466
    //   105: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: aload_0
    //   109: invokevirtual 213	android/content/Context:getPackageName	()Ljava/lang/String;
    //   112: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: ldc 183
    //   117: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   126: aload_0
    //   127: invokestatic 165	org/android/agoo/impl/a:f	(Landroid/content/Context;)Ljava/lang/String;
    //   130: astore 19
    //   132: aload_0
    //   133: invokestatic 147	org/android/Config:getAgooGroup	(Landroid/content/Context;)Ljava/lang/String;
    //   136: astore_1
    //   137: aload_0
    //   138: invokevirtual 213	android/content/Context:getPackageName	()Ljava/lang/String;
    //   141: astore 21
    //   143: aload 19
    //   145: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   148: ifne +18 -> 166
    //   151: aload_1
    //   152: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   155: ifne +11 -> 166
    //   158: aload 21
    //   160: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   163: ifeq +55 -> 218
    //   166: getstatic 72	org/android/agoo/impl/a:h	Ljava/util/Map;
    //   169: ldc_w 369
    //   172: ldc_w 468
    //   175: invokeinterface 375 3 0
    //   180: pop
    //   181: ldc 24
    //   183: ldc_w 470
    //   186: invokestatic 472	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   189: aload_0
    //   190: aload 18
    //   192: getstatic 70	org/android/agoo/impl/a:g	Ljava/lang/String;
    //   195: lload_3
    //   196: aload 17
    //   198: aconst_null
    //   199: aconst_null
    //   200: ldc 68
    //   202: invokestatic 475	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Lorg/android/agoo/impl/a$d;Lorg/android/agoo/impl/a$d;Ljava/lang/String;)V
    //   205: iconst_0
    //   206: ifeq +11 -> 217
    //   209: new 477	java/lang/NullPointerException
    //   212: dup
    //   213: invokespecial 478	java/lang/NullPointerException:<init>	()V
    //   216: athrow
    //   217: return
    //   218: aload_0
    //   219: invokevirtual 157	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   222: astore 20
    //   224: aload 20
    //   226: ifnonnull +54 -> 280
    //   229: ldc 24
    //   231: ldc_w 480
    //   234: invokestatic 472	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   237: getstatic 72	org/android/agoo/impl/a:h	Ljava/util/Map;
    //   240: ldc_w 369
    //   243: ldc_w 482
    //   246: invokeinterface 375 3 0
    //   251: pop
    //   252: aload_0
    //   253: aload 18
    //   255: getstatic 70	org/android/agoo/impl/a:g	Ljava/lang/String;
    //   258: lload_3
    //   259: aload 17
    //   261: aconst_null
    //   262: aconst_null
    //   263: ldc 68
    //   265: invokestatic 475	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Lorg/android/agoo/impl/a$d;Lorg/android/agoo/impl/a$d;Ljava/lang/String;)V
    //   268: iconst_0
    //   269: ifeq -52 -> 217
    //   272: new 477	java/lang/NullPointerException
    //   275: dup
    //   276: invokespecial 478	java/lang/NullPointerException:<init>	()V
    //   279: athrow
    //   280: new 484	com/umeng/message/proguard/bc
    //   283: dup
    //   284: ldc_w 486
    //   287: bipush 10
    //   289: invokespecial 489	com/umeng/message/proguard/bc:<init>	(Ljava/lang/String;I)V
    //   292: astore 11
    //   294: aload 14
    //   296: astore 10
    //   298: aload 5
    //   300: astore 6
    //   302: aload 13
    //   304: astore 9
    //   306: aload 5
    //   308: astore 7
    //   310: aload 11
    //   312: invokeinterface 494 1 0
    //   317: ifne +87 -> 404
    //   320: aload 14
    //   322: astore 10
    //   324: aload 5
    //   326: astore 6
    //   328: aload 13
    //   330: astore 9
    //   332: aload 5
    //   334: astore 7
    //   336: getstatic 72	org/android/agoo/impl/a:h	Ljava/util/Map;
    //   339: ldc_w 496
    //   342: ldc_w 498
    //   345: invokeinterface 375 3 0
    //   350: pop
    //   351: aload 14
    //   353: astore 10
    //   355: aload 5
    //   357: astore 6
    //   359: aload 13
    //   361: astore 9
    //   363: aload 5
    //   365: astore 7
    //   367: ldc 24
    //   369: ldc_w 498
    //   372: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   375: aload_0
    //   376: aload 18
    //   378: getstatic 70	org/android/agoo/impl/a:g	Ljava/lang/String;
    //   381: lload_3
    //   382: aload 17
    //   384: aconst_null
    //   385: aconst_null
    //   386: ldc 68
    //   388: invokestatic 475	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Lorg/android/agoo/impl/a$d;Lorg/android/agoo/impl/a$d;Ljava/lang/String;)V
    //   391: aload 11
    //   393: ifnull -176 -> 217
    //   396: aload 11
    //   398: invokeinterface 501 1 0
    //   403: return
    //   404: aload 14
    //   406: astore 10
    //   408: aload 5
    //   410: astore 6
    //   412: aload 13
    //   414: astore 9
    //   416: aload 5
    //   418: astore 7
    //   420: ldc 47
    //   422: iconst_1
    //   423: anewarray 4	java/lang/Object
    //   426: dup
    //   427: iconst_0
    //   428: aload_1
    //   429: aastore
    //   430: invokestatic 151	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   433: astore 23
    //   435: aload 14
    //   437: astore 10
    //   439: aload 5
    //   441: astore 6
    //   443: aload 13
    //   445: astore 9
    //   447: aload 5
    //   449: astore 7
    //   451: aload 20
    //   453: aload 23
    //   455: invokestatic 163	android/provider/Settings$System:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   458: astore 24
    //   460: aconst_null
    //   461: astore 16
    //   463: aconst_null
    //   464: astore 8
    //   466: aload 5
    //   468: astore_1
    //   469: aload 14
    //   471: astore 10
    //   473: aload 5
    //   475: astore 6
    //   477: aload 13
    //   479: astore 9
    //   481: aload 5
    //   483: astore 7
    //   485: aload 24
    //   487: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   490: ifne +748 -> 1238
    //   493: aload 14
    //   495: astore 10
    //   497: aload 5
    //   499: astore 6
    //   501: aload 13
    //   503: astore 9
    //   505: aload 5
    //   507: astore 7
    //   509: aload 19
    //   511: aload 24
    //   513: iconst_2
    //   514: invokestatic 176	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   517: astore_1
    //   518: aload 13
    //   520: astore 9
    //   522: aload 5
    //   524: astore 7
    //   526: ldc 24
    //   528: new 112	java/lang/StringBuilder
    //   531: dup
    //   532: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   535: ldc_w 503
    //   538: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: aload_1
    //   542: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: ldc 183
    //   547: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   553: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   556: aload 13
    //   558: astore 9
    //   560: aload 5
    //   562: astore 7
    //   564: aload_0
    //   565: invokevirtual 210	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   568: astore 6
    //   570: aload 13
    //   572: astore 9
    //   574: aload 5
    //   576: astore 7
    //   578: aload 6
    //   580: aload 21
    //   582: iconst_0
    //   583: invokevirtual 219	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   586: getfield 224	android/content/pm/ApplicationInfo:flags	I
    //   589: istore_2
    //   590: aload 13
    //   592: astore 9
    //   594: aload 5
    //   596: astore 7
    //   598: new 505	android/content/Intent
    //   601: dup
    //   602: ldc_w 507
    //   605: invokespecial 508	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   608: astore 8
    //   610: aload 13
    //   612: astore 9
    //   614: aload 5
    //   616: astore 7
    //   618: aload 8
    //   620: aload_0
    //   621: invokestatic 147	org/android/Config:getAgooGroup	(Landroid/content/Context;)Ljava/lang/String;
    //   624: invokevirtual 512	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   627: pop
    //   628: aload 13
    //   630: astore 9
    //   632: aload 5
    //   634: astore 7
    //   636: aload 6
    //   638: aload 8
    //   640: bipush 32
    //   642: invokevirtual 516	android/content/pm/PackageManager:queryBroadcastReceivers	(Landroid/content/Intent;I)Ljava/util/List;
    //   645: astore 8
    //   647: aload 13
    //   649: astore 9
    //   651: aload 5
    //   653: astore 7
    //   655: new 246	org/json/JSONObject
    //   658: dup
    //   659: aload_1
    //   660: invokespecial 247	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   663: astore 6
    //   665: aload 13
    //   667: astore 9
    //   669: aload 5
    //   671: astore 7
    //   673: aload 8
    //   675: invokeinterface 522 1 0
    //   680: astore 8
    //   682: ldc 68
    //   684: astore_1
    //   685: aload_1
    //   686: astore 5
    //   688: aload 8
    //   690: invokeinterface 527 1 0
    //   695: ifeq +539 -> 1234
    //   698: aload_1
    //   699: astore 5
    //   701: aload 8
    //   703: invokeinterface 531 1 0
    //   708: checkcast 533	android/content/pm/ResolveInfo
    //   711: astore 9
    //   713: aload 9
    //   715: ifnull -30 -> 685
    //   718: aload_1
    //   719: astore 7
    //   721: aload_1
    //   722: astore 5
    //   724: aload 9
    //   726: getfield 537	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   729: astore 10
    //   731: aload 10
    //   733: ifnull +17 -> 750
    //   736: aload_1
    //   737: astore 7
    //   739: aload_1
    //   740: astore 5
    //   742: aload 10
    //   744: instanceof 539
    //   747: ifne +40 -> 787
    //   750: ldc_w 541
    //   753: astore_1
    //   754: aload_1
    //   755: astore 7
    //   757: aload_1
    //   758: astore 5
    //   760: ldc 24
    //   762: ldc_w 543
    //   765: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   768: goto -83 -> 685
    //   771: astore_1
    //   772: ldc 24
    //   774: ldc_w 545
    //   777: aload_1
    //   778: invokestatic 412	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   781: aload 7
    //   783: astore_1
    //   784: goto -99 -> 685
    //   787: aload_1
    //   788: astore 7
    //   790: aload_1
    //   791: astore 5
    //   793: aload 10
    //   795: getfield 548	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   798: astore 9
    //   800: aload_1
    //   801: astore 7
    //   803: aload_1
    //   804: astore 5
    //   806: aload 9
    //   808: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   811: ifne +17 -> 828
    //   814: aload_1
    //   815: astore 7
    //   817: aload_1
    //   818: astore 5
    //   820: aload 10
    //   822: invokevirtual 551	android/content/pm/ActivityInfo:isEnabled	()Z
    //   825: ifne +87 -> 912
    //   828: aload_1
    //   829: astore 7
    //   831: aload_1
    //   832: astore 5
    //   834: new 112	java/lang/StringBuilder
    //   837: dup
    //   838: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   841: aload_1
    //   842: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   845: ldc_w 553
    //   848: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   851: aload 9
    //   853: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   856: ldc 236
    //   858: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   861: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   864: astore_1
    //   865: aload_1
    //   866: astore 7
    //   868: aload_1
    //   869: astore 5
    //   871: ldc 24
    //   873: new 112	java/lang/StringBuilder
    //   876: dup
    //   877: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   880: ldc_w 555
    //   883: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   886: aload 9
    //   888: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   891: ldc 236
    //   893: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   896: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   899: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   902: goto -217 -> 685
    //   905: astore_1
    //   906: aload 7
    //   908: astore_1
    //   909: goto -224 -> 685
    //   912: aload_1
    //   913: astore 7
    //   915: aload_1
    //   916: astore 5
    //   918: aload 6
    //   920: aload 9
    //   922: invokevirtual 255	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   925: astore 16
    //   927: aload_1
    //   928: astore 7
    //   930: aload_1
    //   931: astore 5
    //   933: aload 16
    //   935: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   938: ifeq +122 -> 1060
    //   941: aload_1
    //   942: astore 7
    //   944: aload_1
    //   945: astore 5
    //   947: new 112	java/lang/StringBuilder
    //   950: dup
    //   951: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   954: aload_1
    //   955: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   958: ldc_w 553
    //   961: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   964: aload 9
    //   966: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   969: ldc_w 557
    //   972: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   975: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   978: astore_1
    //   979: aload_1
    //   980: astore 7
    //   982: aload_1
    //   983: astore 5
    //   985: ldc 24
    //   987: new 112	java/lang/StringBuilder
    //   990: dup
    //   991: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   994: ldc_w 555
    //   997: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1000: aload 9
    //   1002: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1005: ldc_w 559
    //   1008: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1011: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1014: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1017: goto -332 -> 685
    //   1020: astore_1
    //   1021: aload 11
    //   1023: astore 6
    //   1025: aload 15
    //   1027: astore 9
    //   1029: aload_0
    //   1030: aload 18
    //   1032: getstatic 70	org/android/agoo/impl/a:g	Ljava/lang/String;
    //   1035: lload_3
    //   1036: aload 17
    //   1038: aload 9
    //   1040: aconst_null
    //   1041: aload 5
    //   1043: invokestatic 475	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Lorg/android/agoo/impl/a$d;Lorg/android/agoo/impl/a$d;Ljava/lang/String;)V
    //   1046: aload 6
    //   1048: ifnull +10 -> 1058
    //   1051: aload 6
    //   1053: invokeinterface 501 1 0
    //   1058: aload_1
    //   1059: athrow
    //   1060: aload_1
    //   1061: astore 7
    //   1063: aload_1
    //   1064: astore 5
    //   1066: aload_0
    //   1067: aload 9
    //   1069: aload 16
    //   1071: iload_2
    //   1072: invokestatic 561	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;I)Lorg/android/agoo/impl/a$a;
    //   1075: astore 10
    //   1077: aload 10
    //   1079: ifnull +101 -> 1180
    //   1082: aload_1
    //   1083: astore 7
    //   1085: aload_1
    //   1086: astore 5
    //   1088: ldc 24
    //   1090: new 112	java/lang/StringBuilder
    //   1093: dup
    //   1094: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   1097: ldc_w 555
    //   1100: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1103: aload 9
    //   1105: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1108: ldc_w 563
    //   1111: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1114: aload 10
    //   1116: invokevirtual 565	org/android/agoo/impl/a$a:c	()J
    //   1119: invokevirtual 283	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1122: ldc_w 291
    //   1125: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1128: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1131: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1134: aload_1
    //   1135: astore 7
    //   1137: aload_1
    //   1138: astore 5
    //   1140: aload 22
    //   1142: aload 9
    //   1144: aload 10
    //   1146: invokevirtual 568	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1149: pop
    //   1150: aload_1
    //   1151: astore 7
    //   1153: aload_1
    //   1154: astore 5
    //   1156: aload 17
    //   1158: aload 9
    //   1160: aload 10
    //   1162: invokevirtual 565	org/android/agoo/impl/a$a:c	()J
    //   1165: invokestatic 572	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1168: invokeinterface 375 3 0
    //   1173: pop
    //   1174: aload_1
    //   1175: astore 7
    //   1177: goto -396 -> 781
    //   1180: aload_1
    //   1181: astore 7
    //   1183: aload_1
    //   1184: astore 5
    //   1186: new 112	java/lang/StringBuilder
    //   1189: dup
    //   1190: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   1193: aload_1
    //   1194: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1197: ldc_w 574
    //   1200: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1203: aload 9
    //   1205: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1208: ldc_w 576
    //   1211: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1214: aload 16
    //   1216: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1219: ldc 183
    //   1221: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1224: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1227: astore_1
    //   1228: aload_1
    //   1229: astore 7
    //   1231: goto -450 -> 781
    //   1234: aload 6
    //   1236: astore 8
    //   1238: aload 8
    //   1240: ifnonnull +238 -> 1478
    //   1243: aload 14
    //   1245: astore 10
    //   1247: aload_1
    //   1248: astore 6
    //   1250: aload 13
    //   1252: astore 9
    //   1254: aload_1
    //   1255: astore 7
    //   1257: aload_0
    //   1258: invokestatic 298	org/android/agoo/a:m	(Landroid/content/Context;)Z
    //   1261: ifeq +217 -> 1478
    //   1264: aload 14
    //   1266: astore 10
    //   1268: aload_1
    //   1269: astore 6
    //   1271: aload 13
    //   1273: astore 9
    //   1275: aload_1
    //   1276: astore 7
    //   1278: ldc 24
    //   1280: new 112	java/lang/StringBuilder
    //   1283: dup
    //   1284: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   1287: ldc_w 578
    //   1290: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1293: aload 21
    //   1295: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1298: ldc_w 580
    //   1301: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1304: aload 21
    //   1306: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1309: ldc_w 582
    //   1312: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1315: invokestatic 584	org/android/agoo/a:a	()J
    //   1318: invokevirtual 283	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1321: ldc 183
    //   1323: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1326: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1329: invokestatic 589	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   1332: pop
    //   1333: aload 14
    //   1335: astore 10
    //   1337: aload_1
    //   1338: astore 6
    //   1340: aload 13
    //   1342: astore 9
    //   1344: aload_1
    //   1345: astore 7
    //   1347: aload 17
    //   1349: aload 21
    //   1351: invokestatic 584	org/android/agoo/a:a	()J
    //   1354: invokestatic 572	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1357: invokeinterface 375 3 0
    //   1362: pop
    //   1363: aload 14
    //   1365: astore 10
    //   1367: aload_1
    //   1368: astore 6
    //   1370: aload 13
    //   1372: astore 9
    //   1374: aload_1
    //   1375: astore 7
    //   1377: new 6	org/android/agoo/impl/a$a
    //   1380: dup
    //   1381: invokespecial 590	org/android/agoo/impl/a$a:<init>	()V
    //   1384: astore 5
    //   1386: aload 14
    //   1388: astore 10
    //   1390: aload_1
    //   1391: astore 6
    //   1393: aload 13
    //   1395: astore 9
    //   1397: aload_1
    //   1398: astore 7
    //   1400: aload 5
    //   1402: aload_0
    //   1403: invokestatic 593	org/android/agoo/impl/a:b	(Landroid/content/Context;)I
    //   1406: invokevirtual 596	org/android/agoo/impl/a$a:a	(I)V
    //   1409: aload 14
    //   1411: astore 10
    //   1413: aload_1
    //   1414: astore 6
    //   1416: aload 13
    //   1418: astore 9
    //   1420: aload_1
    //   1421: astore 7
    //   1423: aload 5
    //   1425: invokestatic 584	org/android/agoo/a:a	()J
    //   1428: invokevirtual 599	org/android/agoo/impl/a$a:b	(J)V
    //   1431: aload 14
    //   1433: astore 10
    //   1435: aload_1
    //   1436: astore 6
    //   1438: aload 13
    //   1440: astore 9
    //   1442: aload_1
    //   1443: astore 7
    //   1445: aload 5
    //   1447: aload_0
    //   1448: invokestatic 602	org/android/agoo/impl/a:c	(Landroid/content/Context;)J
    //   1451: invokevirtual 604	org/android/agoo/impl/a$a:a	(J)V
    //   1454: aload 14
    //   1456: astore 10
    //   1458: aload_1
    //   1459: astore 6
    //   1461: aload 13
    //   1463: astore 9
    //   1465: aload_1
    //   1466: astore 7
    //   1468: aload 22
    //   1470: aload 21
    //   1472: aload 5
    //   1474: invokevirtual 568	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1477: pop
    //   1478: aload 17
    //   1480: ifnull +27 -> 1507
    //   1483: aload 14
    //   1485: astore 10
    //   1487: aload_1
    //   1488: astore 6
    //   1490: aload 13
    //   1492: astore 9
    //   1494: aload_1
    //   1495: astore 7
    //   1497: aload 17
    //   1499: invokeinterface 607 1 0
    //   1504: ifgt +178 -> 1682
    //   1507: aload 14
    //   1509: astore 10
    //   1511: aload_1
    //   1512: astore 6
    //   1514: aload 13
    //   1516: astore 9
    //   1518: aload_1
    //   1519: astore 7
    //   1521: getstatic 72	org/android/agoo/impl/a:h	Ljava/util/Map;
    //   1524: ldc_w 369
    //   1527: ldc_w 609
    //   1530: invokeinterface 375 3 0
    //   1535: pop
    //   1536: aload 14
    //   1538: astore 10
    //   1540: aload_1
    //   1541: astore 6
    //   1543: aload 13
    //   1545: astore 9
    //   1547: aload_1
    //   1548: astore 7
    //   1550: aload 20
    //   1552: aload 23
    //   1554: aconst_null
    //   1555: invokestatic 613	android/provider/Settings$System:putString	(Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;)Z
    //   1558: pop
    //   1559: aload_0
    //   1560: aload 18
    //   1562: getstatic 70	org/android/agoo/impl/a:g	Ljava/lang/String;
    //   1565: lload_3
    //   1566: aload 17
    //   1568: aconst_null
    //   1569: aconst_null
    //   1570: aload_1
    //   1571: invokestatic 475	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Lorg/android/agoo/impl/a$d;Lorg/android/agoo/impl/a$d;Ljava/lang/String;)V
    //   1574: aload 11
    //   1576: ifnull -1359 -> 217
    //   1579: aload 11
    //   1581: invokeinterface 501 1 0
    //   1586: return
    //   1587: astore 8
    //   1589: aload 16
    //   1591: astore_1
    //   1592: aload 14
    //   1594: astore 10
    //   1596: aload 5
    //   1598: astore 6
    //   1600: aload 13
    //   1602: astore 9
    //   1604: aload 5
    //   1606: astore 7
    //   1608: ldc 24
    //   1610: ldc_w 545
    //   1613: aload 8
    //   1615: invokestatic 412	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1618: aload_1
    //   1619: astore 8
    //   1621: aload 5
    //   1623: astore_1
    //   1624: goto -386 -> 1238
    //   1627: astore 7
    //   1629: aload 11
    //   1631: astore_1
    //   1632: aload 6
    //   1634: astore 5
    //   1636: aload 10
    //   1638: astore 9
    //   1640: aload 7
    //   1642: astore 6
    //   1644: ldc 24
    //   1646: ldc_w 545
    //   1649: aload 6
    //   1651: invokestatic 412	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1654: aload_0
    //   1655: aload 18
    //   1657: getstatic 70	org/android/agoo/impl/a:g	Ljava/lang/String;
    //   1660: lload_3
    //   1661: aload 17
    //   1663: aload 9
    //   1665: aconst_null
    //   1666: aload 5
    //   1668: invokestatic 475	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Lorg/android/agoo/impl/a$d;Lorg/android/agoo/impl/a$d;Ljava/lang/String;)V
    //   1671: aload_1
    //   1672: ifnull -1455 -> 217
    //   1675: aload_1
    //   1676: invokeinterface 501 1 0
    //   1681: return
    //   1682: aload 13
    //   1684: astore 9
    //   1686: aload_1
    //   1687: astore 7
    //   1689: aload 22
    //   1691: invokevirtual 332	org/json/JSONObject:toString	()Ljava/lang/String;
    //   1694: astore 5
    //   1696: aload 13
    //   1698: astore 9
    //   1700: aload_1
    //   1701: astore 7
    //   1703: ldc 24
    //   1705: new 112	java/lang/StringBuilder
    //   1708: dup
    //   1709: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   1712: ldc_w 615
    //   1715: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1718: aload 5
    //   1720: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1723: ldc 183
    //   1725: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1728: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1731: invokestatic 230	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   1734: aload 13
    //   1736: astore 9
    //   1738: aload_1
    //   1739: astore 7
    //   1741: aload 20
    //   1743: aload 23
    //   1745: aload 19
    //   1747: aload 5
    //   1749: iconst_2
    //   1750: invokestatic 617	com/umeng/message/proguard/by:a	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   1753: invokestatic 613	android/provider/Settings$System:putString	(Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;)Z
    //   1756: pop
    //   1757: aload 14
    //   1759: astore 10
    //   1761: aload_1
    //   1762: astore 6
    //   1764: aload 13
    //   1766: astore 9
    //   1768: aload_1
    //   1769: astore 7
    //   1771: aload_0
    //   1772: aload 17
    //   1774: invokestatic 619	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/util/Map;)Lorg/android/agoo/impl/a$d;
    //   1777: astore 5
    //   1779: aload 5
    //   1781: ifnull +29 -> 1810
    //   1784: aload 5
    //   1786: astore 10
    //   1788: aload_1
    //   1789: astore 6
    //   1791: aload 12
    //   1793: astore 8
    //   1795: aload 5
    //   1797: astore 9
    //   1799: aload_1
    //   1800: astore 7
    //   1802: aload 5
    //   1804: invokevirtual 621	org/android/agoo/impl/a$d:b	()Z
    //   1807: ifne +25 -> 1832
    //   1810: aload 5
    //   1812: astore 10
    //   1814: aload_1
    //   1815: astore 6
    //   1817: aload 5
    //   1819: astore 9
    //   1821: aload_1
    //   1822: astore 7
    //   1824: aload_0
    //   1825: aload 17
    //   1827: invokestatic 623	org/android/agoo/impl/a:b	(Landroid/content/Context;Ljava/util/Map;)Lorg/android/agoo/impl/a$d;
    //   1830: astore 8
    //   1832: aload_0
    //   1833: aload 18
    //   1835: getstatic 70	org/android/agoo/impl/a:g	Ljava/lang/String;
    //   1838: lload_3
    //   1839: aload 17
    //   1841: aload 5
    //   1843: aload 8
    //   1845: aload_1
    //   1846: invokestatic 475	org/android/agoo/impl/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Lorg/android/agoo/impl/a$d;Lorg/android/agoo/impl/a$d;Ljava/lang/String;)V
    //   1849: aload 11
    //   1851: ifnull -1634 -> 217
    //   1854: aload 11
    //   1856: invokeinterface 501 1 0
    //   1861: return
    //   1862: astore_1
    //   1863: aconst_null
    //   1864: astore 6
    //   1866: aload 15
    //   1868: astore 9
    //   1870: goto -841 -> 1029
    //   1873: astore_1
    //   1874: aload 7
    //   1876: astore 5
    //   1878: aload 11
    //   1880: astore 6
    //   1882: goto -853 -> 1029
    //   1885: astore_1
    //   1886: aload 7
    //   1888: astore 5
    //   1890: aload 15
    //   1892: astore 9
    //   1894: aload 11
    //   1896: astore 6
    //   1898: goto -869 -> 1029
    //   1901: astore 7
    //   1903: aload_1
    //   1904: astore 6
    //   1906: aload 7
    //   1908: astore_1
    //   1909: goto -880 -> 1029
    //   1912: astore 6
    //   1914: aload 7
    //   1916: astore_1
    //   1917: goto -273 -> 1644
    //   1920: astore 5
    //   1922: goto -165 -> 1757
    //   1925: astore 8
    //   1927: aload 6
    //   1929: astore_1
    //   1930: goto -338 -> 1592
    //   1933: astore 8
    //   1935: aload_1
    //   1936: astore 5
    //   1938: aload 6
    //   1940: astore_1
    //   1941: goto -349 -> 1592
    //   1944: astore 8
    //   1946: aload 7
    //   1948: astore 5
    //   1950: aload 6
    //   1952: astore_1
    //   1953: goto -361 -> 1592
    //
    // Exception table:
    //   from	to	target	type
    //   724	731	771	java/lang/Throwable
    //   742	750	771	java/lang/Throwable
    //   760	768	771	java/lang/Throwable
    //   793	800	771	java/lang/Throwable
    //   918	927	771	java/lang/Throwable
    //   933	941	771	java/lang/Throwable
    //   947	979	771	java/lang/Throwable
    //   985	1017	771	java/lang/Throwable
    //   1066	1077	771	java/lang/Throwable
    //   1088	1134	771	java/lang/Throwable
    //   1140	1150	771	java/lang/Throwable
    //   1156	1174	771	java/lang/Throwable
    //   1186	1228	771	java/lang/Throwable
    //   806	814	905	java/lang/Throwable
    //   820	828	905	java/lang/Throwable
    //   834	865	905	java/lang/Throwable
    //   871	902	905	java/lang/Throwable
    //   688	698	1020	finally
    //   701	713	1020	finally
    //   724	731	1020	finally
    //   742	750	1020	finally
    //   760	768	1020	finally
    //   793	800	1020	finally
    //   806	814	1020	finally
    //   820	828	1020	finally
    //   834	865	1020	finally
    //   871	902	1020	finally
    //   918	927	1020	finally
    //   933	941	1020	finally
    //   947	979	1020	finally
    //   985	1017	1020	finally
    //   1066	1077	1020	finally
    //   1088	1134	1020	finally
    //   1140	1150	1020	finally
    //   1156	1174	1020	finally
    //   1186	1228	1020	finally
    //   526	556	1587	java/lang/Throwable
    //   564	570	1587	java/lang/Throwable
    //   578	590	1587	java/lang/Throwable
    //   598	610	1587	java/lang/Throwable
    //   618	628	1587	java/lang/Throwable
    //   636	647	1587	java/lang/Throwable
    //   655	665	1587	java/lang/Throwable
    //   310	320	1627	java/lang/Throwable
    //   336	351	1627	java/lang/Throwable
    //   367	375	1627	java/lang/Throwable
    //   420	435	1627	java/lang/Throwable
    //   451	460	1627	java/lang/Throwable
    //   485	493	1627	java/lang/Throwable
    //   509	518	1627	java/lang/Throwable
    //   1257	1264	1627	java/lang/Throwable
    //   1278	1333	1627	java/lang/Throwable
    //   1347	1363	1627	java/lang/Throwable
    //   1377	1386	1627	java/lang/Throwable
    //   1400	1409	1627	java/lang/Throwable
    //   1423	1431	1627	java/lang/Throwable
    //   1445	1454	1627	java/lang/Throwable
    //   1468	1478	1627	java/lang/Throwable
    //   1497	1507	1627	java/lang/Throwable
    //   1521	1536	1627	java/lang/Throwable
    //   1550	1559	1627	java/lang/Throwable
    //   1608	1618	1627	java/lang/Throwable
    //   1771	1779	1627	java/lang/Throwable
    //   1802	1810	1627	java/lang/Throwable
    //   1824	1832	1627	java/lang/Throwable
    //   51	62	1862	finally
    //   62	166	1862	finally
    //   166	189	1862	finally
    //   218	224	1862	finally
    //   229	252	1862	finally
    //   280	294	1862	finally
    //   310	320	1873	finally
    //   336	351	1873	finally
    //   367	375	1873	finally
    //   420	435	1873	finally
    //   451	460	1873	finally
    //   485	493	1873	finally
    //   509	518	1873	finally
    //   526	556	1873	finally
    //   564	570	1873	finally
    //   578	590	1873	finally
    //   598	610	1873	finally
    //   618	628	1873	finally
    //   636	647	1873	finally
    //   655	665	1873	finally
    //   673	682	1873	finally
    //   1257	1264	1873	finally
    //   1278	1333	1873	finally
    //   1347	1363	1873	finally
    //   1377	1386	1873	finally
    //   1400	1409	1873	finally
    //   1423	1431	1873	finally
    //   1445	1454	1873	finally
    //   1468	1478	1873	finally
    //   1497	1507	1873	finally
    //   1521	1536	1873	finally
    //   1550	1559	1873	finally
    //   1608	1618	1873	finally
    //   1689	1696	1873	finally
    //   1703	1734	1873	finally
    //   1741	1757	1873	finally
    //   1771	1779	1873	finally
    //   1802	1810	1873	finally
    //   1824	1832	1873	finally
    //   772	781	1885	finally
    //   1644	1654	1901	finally
    //   51	62	1912	java/lang/Throwable
    //   62	166	1912	java/lang/Throwable
    //   166	189	1912	java/lang/Throwable
    //   218	224	1912	java/lang/Throwable
    //   229	252	1912	java/lang/Throwable
    //   280	294	1912	java/lang/Throwable
    //   1689	1696	1920	java/lang/Throwable
    //   1703	1734	1920	java/lang/Throwable
    //   1741	1757	1920	java/lang/Throwable
    //   673	682	1925	java/lang/Throwable
    //   688	698	1933	java/lang/Throwable
    //   701	713	1933	java/lang/Throwable
    //   772	781	1944	java/lang/Throwable
  }

  private static void a(Context paramContext, String paramString1, String paramString2, long paramLong, Map<String, Long> paramMap, d paramd1, d paramd2, String paramString3)
  {
    if (paramd1 != null);
    while (true)
    {
      try
      {
        if (paramd1.b())
          break label953;
        if ((paramd2 == null) || (!paramd2.b()))
          break label961;
        break label953;
        String str3 = Config.getDeviceToken(paramContext);
        if ((paramd1 != null) && (!paramd1.b()))
        {
          paramd2 = "remoteRet=" + paramd1.a() + "-->remoteFailed=" + paramd1.b() + "-->remoteSudoPack=" + paramd1.d() + "-->source=" + paramd1.c();
          paramd1 = null;
          if ((paramMap == null) || (paramMap.isEmpty()))
            break label938;
          paramMap = paramMap.toString();
          if (TextUtils.isEmpty(null))
          {
            str2 = (String)h.get("electionFailed");
            String str4 = (String)h.get("lock");
            StringBuffer localStringBuffer = new StringBuffer();
            localStringBuffer.append("utdid=" + aW.c(paramContext));
            localStringBuffer.append("-->");
            localStringBuffer.append("deviceId=" + str3);
            localStringBuffer.append("-->");
            localStringBuffer.append("appkey=" + org.android.agoo.a.f(paramContext));
            localStringBuffer.append("-->");
            localStringBuffer.append("startTime=" + bw.a(paramLong));
            localStringBuffer.append("-->");
            localStringBuffer.append("ret=" + str1);
            localStringBuffer.append("-->");
            localStringBuffer.append("fail_reasons=" + null);
            localStringBuffer.append("-->");
            localStringBuffer.append("currentPackage=" + paramContext.getPackageName());
            localStringBuffer.append("-->");
            localStringBuffer.append("packsMap=" + paramMap);
            localStringBuffer.append("-->");
            localStringBuffer.append("lastsudo=" + paramString1);
            localStringBuffer.append("-->");
            localStringBuffer.append("currentsudo=" + paramString2);
            localStringBuffer.append("-->");
            localStringBuffer.append("finishtime=" + bw.a(System.currentTimeMillis()));
            localStringBuffer.append("-->");
            localStringBuffer.append("errorMapString=" + str2);
            localStringBuffer.append("-->");
            localStringBuffer.append("lock=" + str4);
            localStringBuffer.append("-->");
            localStringBuffer.append("errorStr=" + paramString3);
            localStringBuffer.append("-->");
            localStringBuffer.append("sdkVersion=" + org.android.agoo.a.a());
            localStringBuffer.append("-->");
            localStringBuffer.append("remoteInfo=" + paramd2);
            localStringBuffer.append("-->");
            localStringBuffer.append("localInfo=" + paramd1);
            localStringBuffer.append("-->");
            localStringBuffer.append("election");
            aW.e(paramContext, str1, localStringBuffer.toString());
          }
        }
        else
        {
          if ((paramd2 == null) || (paramd2.b()))
            break label944;
          paramd1 = "LocalRet=" + paramd2.a() + "-->localFailed=" + paramd2.b() + "-->localSudoPack=" + paramd2.d() + "-->source=" + paramd2.c();
          paramd2 = null;
          continue;
        }
      }
      catch (Throwable paramString1)
      {
        aW.e(paramContext, "n", paramString1.toString());
        return;
      }
      String str2 = null;
      continue;
      label938: paramMap = null;
      continue;
      label944: paramd2 = null;
      paramd1 = null;
      continue;
      label953: String str1 = "y";
      continue;
      label961: str1 = "n";
    }
  }

  private static final void a(Context paramContext, Map<String, Long> paramMap, String paramString1, long paramLong, String paramString2)
  {
    boolean bool2 = true;
    if ((paramMap == null) || (paramMap.size() <= 0))
      return;
    paramMap = a(paramContext);
    if (!TextUtils.isEmpty(paramMap))
      bd.c("ElectionService", "noticeElectionResult[lastSudoPack:" + paramMap + "]");
    bd.c("ElectionService", "noticeElectionResult[sudoPack:" + paramString1 + "][timeout:" + paramLong + "][electionSource:" + paramString2 + "]");
    g = paramString1;
    b(paramContext, paramString1);
    Object localObject = new Intent();
    ((Intent)localObject).putExtra("election_result", paramString1);
    ((Intent)localObject).putExtra("election_source", paramString2);
    ((Intent)localObject).putExtra("election_timeout", paramLong);
    ((Intent)localObject).setAction("org.agoo.android.intent.action.ELECTION_RESULT_V4");
    ((Intent)localObject).setFlags(32);
    ((Intent)localObject).addCategory(Config.getAgooGroup(paramContext));
    ((Intent)localObject).setPackage(paramString1);
    if (!TextUtils.isEmpty(f))
      ((Intent)localObject).putExtra("eventId", f);
    i = paramContext;
    Config.setNoticeResult(paramContext, false);
    paramContext.sendBroadcast((Intent)localObject);
    a(paramString1, (Intent)localObject);
    bd.c("ElectionService", "lastSudoPack=" + paramMap);
    localObject = new StringBuilder().append("!TextUtils.isEmpty(lastSudoPack)=");
    if (!TextUtils.isEmpty(paramMap))
    {
      bool1 = true;
      label285: bd.c("ElectionService", bool1);
      localObject = new StringBuilder().append("!TextUtils.equals(currentSudoPack, lastSudoPack)=");
      if (TextUtils.equals(paramString1, paramMap))
        break label481;
    }
    label481: for (boolean bool1 = bool2; ; bool1 = false)
    {
      bd.c("ElectionService", bool1);
      if ((TextUtils.isEmpty(paramMap)) || (TextUtils.equals(paramString1, paramMap)))
        break;
      bd.c("ElectionService", "noticeElectionResult[lastSudoPack:" + paramMap + "]stop");
      localObject = new Intent();
      ((Intent)localObject).putExtra("election_result", paramString1);
      ((Intent)localObject).putExtra("election_source", paramString2);
      ((Intent)localObject).putExtra("election_timeout", paramLong);
      ((Intent)localObject).setAction("org.agoo.android.intent.action.ELECTION_RESULT_V4");
      ((Intent)localObject).setFlags(32);
      ((Intent)localObject).addCategory(Config.getAgooGroup(paramContext));
      ((Intent)localObject).setPackage(paramMap);
      paramContext.sendBroadcast((Intent)localObject);
      a(paramMap, (Intent)localObject);
      return;
      bool1 = false;
      break label285;
    }
  }

  private static void a(String paramString, Intent paramIntent)
  {
    try
    {
      k.post(new e(paramString, paramIntent));
      return;
    }
    catch (Throwable paramString)
    {
      Log.e("ElectionService", "noticeElectionBindService error >>", paramString);
    }
  }

  private static final boolean a(Context paramContext, String paramString, int paramInt)
  {
    return a(paramContext, paramString, null, paramInt) != null;
  }

  private static int b(Context paramContext)
  {
    int i1 = -1;
    if (paramContext != null);
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      String str = paramContext.versionName;
      i1 = paramContext.versionCode;
      i1 = Math.abs((str + "." + i1).hashCode());
      return i1;
    }
    catch (Throwable paramContext)
    {
    }
    return -1;
  }

  private static final long b(PackageInfo paramPackageInfo)
  {
    long l1 = -1L;
    try
    {
      if (Build.VERSION.SDK_INT >= 9)
        l1 = paramPackageInfo.lastUpdateTime;
      return l1;
    }
    catch (Throwable paramPackageInfo)
    {
      bd.e("ElectionService", "registerApp", paramPackageInfo);
    }
    return -1L;
  }

  private static d b(Context paramContext, Map<String, Long> paramMap)
  {
    d locald = new d();
    if ((paramMap == null) || (paramMap.size() <= 0))
    {
      bd.e("ElectionService", "localElection failed [null == packMap || 0 >= packMap.size()]");
      locald.b("localElection");
      locald.a("[null == packMap || 0 >= packMap.size()");
      locald.a(false);
      return locald;
    }
    Object localObject = paramMap.entrySet().iterator();
    ArrayList localArrayList = new ArrayList();
    long l1 = -1L;
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      String str = (String)localEntry.getKey();
      long l3 = ((Long)localEntry.getValue()).longValue();
      long l2 = l1;
      if (l3 > l1)
      {
        localArrayList.clear();
        l2 = l3;
      }
      l1 = l2;
      if (l3 == l2)
      {
        localArrayList.add(str);
        l1 = l2;
      }
    }
    localObject = (String)localArrayList.get(c.nextInt(10000) % localArrayList.size());
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      locald.b("localElection");
      locald.c((String)localObject);
      locald.a(true);
      localObject = paramContext.getPackageName();
      bd.c("ElectionService", "sudoPack==currentPack[:" + (String)localObject + "]");
    }
    while (true)
    {
      a(paramContext, paramMap, (String)localObject, -1L, "local");
      return locald;
    }
  }

  static void b(Context paramContext, Class<?> paramClass)
  {
    if (paramContext != null);
    try
    {
      e(paramContext);
      if (paramClass != null)
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        paramContext = new ComponentName(paramContext, paramClass);
        bd.c("ElectionService", "unRegisterApp[" + paramContext.toString() + "]");
        localPackageManager.setComponentEnabledSetting(paramContext, 2, 1);
      }
      return;
    }
    catch (Throwable paramContext)
    {
      bd.c("ElectionService", "unRegisterApp", paramContext);
    }
  }

  private static final void b(Context paramContext, String paramString)
  {
    try
    {
      String str1 = String.format("org.agoo.android.sudo.%s", new Object[] { Config.getAgooGroup(paramContext) });
      String str2 = f(paramContext);
      if ((!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(paramString)))
      {
        paramString = by.a(str2, paramString, 2);
        Settings.System.putString(paramContext.getContentResolver(), str1, paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      Log.e("ElectionService", "setCurrentSudo", paramString);
      UTFactroy.getInstance().commitEvent(paramContext, "set_CurrentSudo", new String[] { "set_CurrentSudo", "exp=" + paramString.toString() });
    }
  }

  private static final long c(Context paramContext)
  {
    long l1 = -1L;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 2);
      if (Build.VERSION.SDK_INT >= 9)
        l1 = paramContext.lastUpdateTime;
      return l1;
    }
    catch (Throwable paramContext)
    {
      bd.e("ElectionService", "registerApp", paramContext);
    }
    return -1L;
  }

  private static final boolean c(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, -1);
  }

  // ERROR //
  private static final void d(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 165	org/android/agoo/impl/a:f	(Landroid/content/Context;)Ljava/lang/String;
    //   4: astore 4
    //   6: aload_0
    //   7: invokevirtual 213	android/content/Context:getPackageName	()Ljava/lang/String;
    //   10: astore 5
    //   12: aload_0
    //   13: invokestatic 147	org/android/Config:getAgooGroup	(Landroid/content/Context;)Ljava/lang/String;
    //   16: astore_2
    //   17: aload 4
    //   19: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   22: ifne +318 -> 340
    //   25: aload_2
    //   26: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifne +311 -> 340
    //   32: aload 5
    //   34: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   37: ifeq +4 -> 41
    //   40: return
    //   41: aload_0
    //   42: invokevirtual 157	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   45: astore 6
    //   47: aload 6
    //   49: ifnull +291 -> 340
    //   52: new 6	org/android/agoo/impl/a$a
    //   55: dup
    //   56: invokespecial 590	org/android/agoo/impl/a$a:<init>	()V
    //   59: astore 7
    //   61: aload 7
    //   63: invokestatic 584	org/android/agoo/a:a	()J
    //   66: invokevirtual 599	org/android/agoo/impl/a$a:b	(J)V
    //   69: aload 7
    //   71: aload_0
    //   72: invokestatic 602	org/android/agoo/impl/a:c	(Landroid/content/Context;)J
    //   75: invokevirtual 604	org/android/agoo/impl/a$a:a	(J)V
    //   78: aload 7
    //   80: aload_0
    //   81: invokestatic 593	org/android/agoo/impl/a:b	(Landroid/content/Context;)I
    //   84: invokevirtual 596	org/android/agoo/impl/a$a:a	(I)V
    //   87: aload 7
    //   89: aload_0
    //   90: invokestatic 666	org/android/agoo/a:f	(Landroid/content/Context;)Ljava/lang/String;
    //   93: invokevirtual 854	org/android/agoo/impl/a$a:b	(Ljava/lang/String;)V
    //   96: ldc 47
    //   98: iconst_1
    //   99: anewarray 4	java/lang/Object
    //   102: dup
    //   103: iconst_0
    //   104: aload_2
    //   105: aastore
    //   106: invokestatic 151	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   109: astore 8
    //   111: aload 6
    //   113: aload 8
    //   115: invokestatic 163	android/provider/Settings$System:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   118: astore 9
    //   120: aconst_null
    //   121: astore_3
    //   122: aload 9
    //   124: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   127: istore_1
    //   128: aload_3
    //   129: astore_2
    //   130: iload_1
    //   131: ifne +53 -> 184
    //   134: aload 4
    //   136: aload 9
    //   138: iconst_2
    //   139: invokestatic 176	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   142: astore_2
    //   143: aload_2
    //   144: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   147: ifeq +166 -> 313
    //   150: ldc 24
    //   152: new 112	java/lang/StringBuilder
    //   155: dup
    //   156: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   159: ldc_w 856
    //   162: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: aload 9
    //   167: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: ldc_w 858
    //   173: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   179: invokestatic 472	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   182: aload_3
    //   183: astore_2
    //   184: aload_2
    //   185: astore_3
    //   186: aload_2
    //   187: ifnonnull +11 -> 198
    //   190: new 246	org/json/JSONObject
    //   193: dup
    //   194: invokespecial 464	org/json/JSONObject:<init>	()V
    //   197: astore_3
    //   198: aload_3
    //   199: aload 5
    //   201: aload 7
    //   203: invokevirtual 568	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   206: pop
    //   207: ldc 24
    //   209: new 112	java/lang/StringBuilder
    //   212: dup
    //   213: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   216: ldc_w 860
    //   219: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: aload_3
    //   223: invokevirtual 332	org/json/JSONObject:toString	()Ljava/lang/String;
    //   226: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: ldc 183
    //   231: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: invokestatic 188	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   240: aload 6
    //   242: aload 8
    //   244: aload 4
    //   246: aload_3
    //   247: invokevirtual 332	org/json/JSONObject:toString	()Ljava/lang/String;
    //   250: iconst_2
    //   251: invokestatic 617	com/umeng/message/proguard/by:a	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   254: invokestatic 613	android/provider/Settings$System:putString	(Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;)Z
    //   257: pop
    //   258: return
    //   259: astore_2
    //   260: ldc 24
    //   262: ldc_w 432
    //   265: aload_2
    //   266: invokestatic 412	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   269: invokestatic 198	org/android/agoo/ut/UTFactroy:getInstance	()Lorg/android/agoo/ut/UTFactroy;
    //   272: aload_0
    //   273: ldc 30
    //   275: iconst_2
    //   276: anewarray 128	java/lang/String
    //   279: dup
    //   280: iconst_0
    //   281: ldc 30
    //   283: aastore
    //   284: dup
    //   285: iconst_1
    //   286: new 112	java/lang/StringBuilder
    //   289: dup
    //   290: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   293: ldc 200
    //   295: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: aload_2
    //   299: invokevirtual 201	java/lang/Throwable:toString	()Ljava/lang/String;
    //   302: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   308: aastore
    //   309: invokevirtual 205	org/android/agoo/ut/UTFactroy:commitEvent	(Landroid/content/Context;Ljava/lang/Object;[Ljava/lang/String;)V
    //   312: return
    //   313: new 246	org/json/JSONObject
    //   316: dup
    //   317: aload_2
    //   318: invokespecial 247	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   321: astore_2
    //   322: goto -138 -> 184
    //   325: astore_2
    //   326: ldc 24
    //   328: ldc_w 432
    //   331: aload_2
    //   332: invokestatic 412	com/umeng/message/proguard/bd:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   335: aload_3
    //   336: astore_2
    //   337: goto -153 -> 184
    //   340: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	40	259	java/lang/Throwable
    //   41	47	259	java/lang/Throwable
    //   52	120	259	java/lang/Throwable
    //   122	128	259	java/lang/Throwable
    //   190	198	259	java/lang/Throwable
    //   198	258	259	java/lang/Throwable
    //   326	335	259	java/lang/Throwable
    //   134	182	325	java/lang/Throwable
    //   313	322	325	java/lang/Throwable
  }

  private static void e(Context paramContext)
  {
    try
    {
      String str1 = f(paramContext);
      String str2 = paramContext.getPackageName();
      String str3 = Config.getAgooGroup(paramContext);
      if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str3)))
      {
        if (TextUtils.isEmpty(str2))
          return;
        Object localObject = paramContext.getContentResolver();
        if (localObject != null)
        {
          str3 = String.format("org.agoo.android.packs_v1.%s", new Object[] { str3 });
          localObject = Settings.System.getString((ContentResolver)localObject, str3);
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            localObject = by.b(str1, (String)localObject, 2);
            if (!TextUtils.isEmpty((CharSequence)localObject))
            {
              bd.c("ElectionService", "unRegister old appInfo[" + (String)localObject + "]");
              localObject = new JSONObject((String)localObject);
              ((JSONObject)localObject).remove(str2);
              bd.c("ElectionService", "unRegister save[" + ((JSONObject)localObject).toString() + "]");
              str1 = by.a(str1, ((JSONObject)localObject).toString(), 2);
              Settings.System.putString(paramContext.getContentResolver(), str3, str1);
              return;
            }
          }
        }
      }
    }
    catch (Throwable localThrowable)
    {
      bd.e("ElectionService", "Exception", localThrowable);
      UTFactroy.getInstance().commitEvent(paramContext, "get_CurrentSudo", new String[] { "get_CurrentSudo", "exp=" + localThrowable.toString() });
    }
  }

  private static final String f(Context paramContext)
  {
    String str = aW.c(paramContext);
    paramContext = str;
    if (TextUtils.isEmpty(str))
    {
      bd.c("ElectionService", "getPassword[utdid==null]");
      paramContext = "17984173941739471471917341";
    }
    return paramContext;
  }

  private static final boolean g(Context paramContext)
  {
    String str1 = org.android.agoo.a.f(paramContext);
    String str2 = org.android.agoo.a.h(paramContext);
    if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2)));
    do
    {
      return false;
      b.setDefaultAppkey(str1);
      str1 = org.android.agoo.a.j(paramContext);
    }
    while ((TextUtils.isEmpty(str1)) && (!org.android.agoo.a.E(paramContext)));
    b.setDefaultAppSecret(str1);
    b.setBaseUrl(org.android.agoo.a.G(paramContext));
    return true;
  }

  static final class a
  {
    private long a = -1L;
    private long b = -1L;
    private int c = -1;
    private String d = null;

    // ERROR //
    static a a(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokestatic 37	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   4: ifne +76 -> 80
      //   7: new 39	org/json/JSONObject
      //   10: dup
      //   11: aload_0
      //   12: invokespecial 42	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   15: astore_1
      //   16: new 2	org/android/agoo/impl/a$a
      //   19: dup
      //   20: invokespecial 43	org/android/agoo/impl/a$a:<init>	()V
      //   23: astore_0
      //   24: aload_0
      //   25: aload_1
      //   26: ldc 45
      //   28: ldc2_w 18
      //   31: invokevirtual 49	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
      //   34: putfield 21	org/android/agoo/impl/a$a:a	J
      //   37: aload_0
      //   38: aload_1
      //   39: ldc 51
      //   41: ldc2_w 18
      //   44: invokevirtual 49	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
      //   47: putfield 23	org/android/agoo/impl/a$a:b	J
      //   50: aload_0
      //   51: aload_1
      //   52: ldc 53
      //   54: iconst_m1
      //   55: invokevirtual 57	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
      //   58: putfield 25	org/android/agoo/impl/a$a:c	I
      //   61: aload_0
      //   62: aload_1
      //   63: ldc 59
      //   65: aconst_null
      //   66: invokevirtual 63	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   69: putfield 27	org/android/agoo/impl/a$a:d	Ljava/lang/String;
      //   72: aload_0
      //   73: areturn
      //   74: astore_0
      //   75: aconst_null
      //   76: areturn
      //   77: astore_1
      //   78: aload_0
      //   79: areturn
      //   80: aconst_null
      //   81: areturn
      //
      // Exception table:
      //   from	to	target	type
      //   0	24	74	java/lang/Throwable
      //   24	72	77	java/lang/Throwable
    }

    public int a()
    {
      return this.c;
    }

    public void a(int paramInt)
    {
      this.c = paramInt;
    }

    public void a(long paramLong)
    {
      this.a = paramLong;
    }

    public long b()
    {
      return this.a;
    }

    public void b(long paramLong)
    {
      this.b = paramLong;
    }

    public void b(String paramString)
    {
      this.d = paramString;
    }

    public long c()
    {
      return this.b;
    }

    public String d()
    {
      return this.d;
    }

    public String toString()
    {
      try
      {
        Object localObject = new JSONObject();
        ((JSONObject)localObject).put("appInstallTime", this.a);
        ((JSONObject)localObject).put("appSdkVersion", this.b);
        ((JSONObject)localObject).put("appVersionHash", this.c);
        ((JSONObject)localObject).put("appKey", this.d);
        localObject = ((JSONObject)localObject).toString();
        return localObject;
      }
      catch (Throwable localThrowable)
      {
      }
      return null;
    }
  }

  static class b
    implements Handler.Callback
  {
    public boolean handleMessage(Message paramMessage)
    {
      return true;
    }
  }

  static class c
    implements ServiceConnection
  {
    private Intent a;
    private ElectionReceiverService b;
    private ServiceConnection c;

    public c(Intent paramIntent)
    {
      this.a = paramIntent;
      this.c = this;
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      Log.d("ElectionService", "ElectionConnection conneted:" + paramComponentName);
      this.b = ElectionReceiverService.Stub.asInterface(paramIBinder);
      Log.d("ElectionService", "onConnected current tid:" + Thread.currentThread().getId());
      Log.d("ElectionService", "ElectionConnection sent:" + this.a);
      if (this.b != null)
        a.b().post(new Runnable()
        {
          public void run()
          {
            try
            {
              Log.d("ElectionService", "onConnected running tid:" + Thread.currentThread().getId());
              a.c.b(a.c.this).sendElectionResult(a.c.a(a.c.this));
              return;
            }
            catch (Throwable localThrowable)
            {
              Log.e("ElectionService", "send error", localThrowable);
              return;
            }
            finally
            {
              a.a().unbindService(a.c.c(a.c.this));
            }
          }
        });
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      Log.d("ElectionService", "MessageConnection disConnected");
    }
  }

  static final class d
  {
    private String a;
    private boolean b;
    private String c;
    private String d;

    public String a()
    {
      return this.a;
    }

    public void a(String paramString)
    {
      this.a = paramString;
    }

    public void a(boolean paramBoolean)
    {
      this.b = paramBoolean;
    }

    public void b(String paramString)
    {
      this.c = paramString;
    }

    public boolean b()
    {
      return this.b;
    }

    public String c()
    {
      return this.c;
    }

    public void c(String paramString)
    {
      this.d = paramString;
    }

    public String d()
    {
      return this.d;
    }
  }

  static class e
    implements Runnable
  {
    private String a;
    private Intent b;

    public e(String paramString, Intent paramIntent)
    {
      this.a = paramString;
      this.b = paramIntent;
    }

    public void run()
    {
      Object localObject = new Intent();
      Log.d("ElectionService", "this election sudupack:" + this.a + ",action=" + this.b.getAction());
      ((Intent)localObject).setAction(this.b.getAction());
      ((Intent)localObject).putExtras(this.b.getExtras());
      ((Intent)localObject).setFlags(this.b.getFlags());
      ((Intent)localObject).setPackage(this.b.getPackage());
      ((Intent)localObject).addCategory(Config.getAgooGroup(a.a()));
      Log.d("ElectionService", "start to service...");
      boolean bool1 = false;
      try
      {
        localObject = new a.c((Intent)localObject);
        Intent localIntent = new Intent("org.android.agoo.client.ElectionReceiverService");
        Log.d("ElectionService", "this message pack:" + this.a);
        localIntent.setPackage(this.a);
        boolean bool2 = a.a().bindService(localIntent, (ServiceConnection)localObject, 17);
        bool1 = bool2;
        Log.d("ElectionService", "start service ret:" + bool1);
        return;
      }
      catch (Exception localException)
      {
        while (true)
          Log.d("ElectionService", "ElectionConnection,bindService error,e=" + localException);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.impl.a
 * JD-Core Version:    0.6.2
 */