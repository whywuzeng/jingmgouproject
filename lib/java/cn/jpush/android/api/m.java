package cn.jpush.android.api;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.jpush.android.data.d;
import cn.jpush.android.data.s;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.k;
import cn.jpush.android.util.x;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.zip.Adler32;

public final class m
{
  private static Queue<Integer> a;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[41];
    int j = 0;
    Object localObject2 = "m!CD\032j-VY\025l ";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 124;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`^C\bf C\003/Z\035ch1\\\bba0P\reh9M\021tl(F\tx%";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "M!CD\032j-VY\025l H\020s+E";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`yb(J\b~n=W\007xc#W\027gh";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = ",'TB\022";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`yb(J\b~n=W\007xc#@\001yy9M\032hy5W\002r";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "D!C\r2V\002{\r\022l:^K\025`/CD\023m`\027j\025u+\027X\f#:X\r\017k!@\003";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`^C\bf C\003/Z\035ch1\\\bba0P\reh9M\021vn(J\001y";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "a!ST";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "m!CD\032b-CD\023m\021YX\021";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`ru(Q\017";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`gx/K\021~i";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`^C\bf C\0032L\032~k5@\017cd3M\021x}9M\013s";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`yb(J\b~n=W\007xc#G\013ah0L\036r#B\034p\035";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`va9Q\032";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`^C\bf C\0032L\032~k5@\017cd3M\021x}9M\013sr,Q\001otR";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "b>G";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`z~;\\\007s";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "P;TN\031f*\027Y\023#\"XL\030#'ZL\033fn\032\r";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = "g!@C\020l/S\r\017w/CX\017#'TB\022#(VD\020f*［D\021b)Rx\016ot\027";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`^C\bf C\003/K\001`r:O\001vy#U\007rz#B\rcd3M";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        j = 22;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`yb(J\b~n=W\007xc#J\n";
        i = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i4] = localObject1;
        j = 23;
        localObject2 = "P+YI\\s;DE\\q+TH\025u+S\r\036q!VI\037b=C\r\blnSH\nf\"X]\031qnSH\032j RI\\q+TH\025u+E";
        i = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i4] = localObject1;
        j = 24;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`^C\bf C\0032L\032~k5@\017cd3M\021eh?F\007ah8";
        i = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i4] = localObject1;
        j = 25;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`y1O\021eh/";
        i = 24;
        localObject1 = arrayOfString;
        break;
      case 24:
        localObject3[i4] = localObject1;
        j = 26;
        localObject2 = "->R_\021j=DD\023m`}})P\006h`9P\035vj9";
        i = 25;
        localObject1 = arrayOfString;
        break;
      case 25:
        localObject3[i4] = localObject1;
        j = 27;
        localObject2 = "` \031G\fv=_\003\035m*EB\025g`y1O\021gl(K";
        i = 26;
        localObject1 = arrayOfString;
        break;
      case 26:
        localObject3[i4] = localObject1;
        j = 28;
        localObject2 = "e'[HF,a";
        i = 27;
        localObject1 = arrayOfString;
        break;
      case 27:
        localObject3[i4] = localObject1;
        j = 29;
        localObject2 = "k:C]F,a";
        i = 28;
        localObject1 = arrayOfString;
        break;
      case 28:
        localObject3[i4] = localObject1;
        j = 30;
        localObject2 = "i>B^\024\\ XY\025e'TL\bj!Yr\025`!Y";
        i = 29;
        localObject1 = arrayOfString;
        break;
      case 29:
        localObject3[i4] = localObject1;
        j = 31;
        localObject2 = "QjS_\035t/UA\031";
        i = 30;
        localObject1 = arrayOfString;
        break;
      case 30:
        localObject3[i4] = localObject1;
        j = 32;
        localObject2 = "-\034";
        i = 31;
        localObject1 = arrayOfString;
        break;
      case 31:
        localObject3[i4] = localObject1;
        j = 33;
        localObject2 = "";
        i = 32;
        localObject1 = arrayOfString;
        break;
      case 32:
        localObject3[i4] = localObject1;
        j = 34;
        localObject2 = "b-CD\023mtTA\031b yB\bj(^N\035w'XC\\.nZH\017p/PH5gt";
        i = 33;
        localObject1 = arrayOfString;
        break;
      case 33:
        localObject3[i4] = localObject1;
        j = 35;
        localObject2 = "g!@L\022o!VI\\e\"XL\b#>_B\blnQL\025o+SＡ\025n/PH)q\"\r\r";
        i = 34;
        localObject1 = arrayOfString;
        break;
      case 34:
        localObject3[i4] = localObject1;
        j = 36;
        localObject2 = "b-CD\023mtTA\031b yB\bj(^N\035w'XC\\.nYB\bj(^N\035w'XC5gt";
        i = 35;
        localObject1 = arrayOfString;
        break;
      case 35:
        localObject3[i4] = localObject1;
        j = 37;
        localObject2 = "Qj^I";
        i = 36;
        localObject1 = arrayOfString;
        break;
      case 36:
        localObject3[i4] = localObject1;
        j = 38;
        localObject2 = "n+D^\035d+hD\021b)Rr\017w/CH#a/Er\020b7XX\b";
        i = 37;
        localObject1 = arrayOfString;
        break;
      case 37:
        localObject3[i4] = localObject1;
        j = 39;
        localObject2 = "Qj[L\005l;C";
        i = 38;
        localObject1 = arrayOfString;
        break;
      case 38:
        localObject3[i4] = localObject1;
        j = 40;
        localObject2 = "p:VY\031\\,V_#j#VJ\031\\8^H\013";
        i = 39;
        localObject1 = arrayOfString;
        break;
      case 39:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = new LinkedList();
        return;
        i = 3;
        continue;
        i = 78;
        continue;
        i = 55;
        continue;
        i = 45;
      }
    }
  }

  public static int a(int paramInt)
  {
    int j = 17301586;
    int i = j;
    switch (paramInt)
    {
    default:
      i = j;
    case 1:
    case -1:
      while (true)
      {
        return i;
        HashMap localHashMap = a(z[31], new String[] { z[30] });
        try
        {
          paramInt = ((Integer)localHashMap.get(z[30])).intValue();
          i = paramInt;
          if (paramInt <= 0)
            return 17301618;
        }
        catch (Exception localException)
        {
          while (true)
          {
            paramInt = 0;
            x.f();
          }
        }
      }
    case 0:
      return 17301647;
    case 2:
      return 17301618;
    case 3:
    }
    return 17301567;
  }

  public static int a(d paramd, int paramInt)
  {
    String str = paramd.c;
    if (!ai.a(paramd.d))
      str = paramd.d;
    return a(str, paramInt);
  }

  private static int a(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString))
    {
      x.c();
      paramInt = 0;
    }
    int i;
    do
    {
      return paramInt;
      try
      {
        i = Integer.valueOf(paramString).intValue();
        return i;
      }
      catch (Exception localException)
      {
        x.f();
        Adler32 localAdler32 = new Adler32();
        localAdler32.update(paramString.getBytes());
        int j = (int)localAdler32.getValue();
        i = j;
        if (j < 0)
          i = Math.abs(j);
        i += 13889152 * paramInt;
        paramInt = i;
      }
    }
    while (i >= 0);
    return Math.abs(i);
  }

  // ERROR //
  public static Notification a(Context paramContext, int paramInt, Intent paramIntent, d paramd, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: iload 4
    //   2: ifeq +421 -> 423
    //   5: invokestatic 169	cn/jpush/android/util/x:c	()V
    //   8: aload_0
    //   9: invokevirtual 203	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: aload_0
    //   13: invokevirtual 206	android/content/Context:getPackageName	()Ljava/lang/String;
    //   16: sipush 256
    //   19: invokevirtual 212	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   22: getfield 218	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   25: getfield 224	android/content/pm/ApplicationInfo:icon	I
    //   28: istore 6
    //   30: iload 6
    //   32: istore 7
    //   34: iload 6
    //   36: ifge +12 -> 48
    //   39: aload_3
    //   40: getfield 227	cn/jpush/android/data/d:x	I
    //   43: invokestatic 229	cn/jpush/android/api/m:a	(I)I
    //   46: istore 7
    //   48: new 231	android/app/Notification
    //   51: dup
    //   52: invokespecial 232	android/app/Notification:<init>	()V
    //   55: astore 8
    //   57: aload 8
    //   59: invokestatic 237	java/lang/System:currentTimeMillis	()J
    //   62: putfield 241	android/app/Notification:when	J
    //   65: aload 8
    //   67: iload 7
    //   69: putfield 242	android/app/Notification:icon	I
    //   72: aload 8
    //   74: aload_3
    //   75: getfield 245	cn/jpush/android/data/d:B	Ljava/lang/String;
    //   78: putfield 249	android/app/Notification:tickerText	Ljava/lang/CharSequence;
    //   81: aload 8
    //   83: aload_3
    //   84: getfield 251	cn/jpush/android/data/d:z	I
    //   87: invokestatic 254	cn/jpush/android/api/m:b	(I)I
    //   90: putfield 257	android/app/Notification:flags	I
    //   93: aload_3
    //   94: getfield 260	cn/jpush/android/data/d:p	I
    //   97: tableswitch	default:+31 -> 128, 0:+267->364, 1:+255->352, 2:+261->358, 3:+273->370
    //   129: istore 6
    //   131: aload 8
    //   133: iload 6
    //   135: putfield 263	android/app/Notification:defaults	I
    //   138: aload_3
    //   139: getfield 267	cn/jpush/android/data/d:h	Z
    //   142: ifeq +22 -> 164
    //   145: aload 8
    //   147: iconst_3
    //   148: putfield 263	android/app/Notification:defaults	I
    //   151: aload_0
    //   152: invokestatic 273	cn/jpush/android/util/a:o	(Landroid/content/Context;)Z
    //   155: ifeq +9 -> 164
    //   158: aload 8
    //   160: iconst_0
    //   161: putfield 263	android/app/Notification:defaults	I
    //   164: iload 5
    //   166: ifeq +210 -> 376
    //   169: aload_0
    //   170: iload_1
    //   171: aload_2
    //   172: ldc_w 274
    //   175: invokestatic 280	android/app/PendingIntent:getBroadcast	(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   178: astore_2
    //   179: aload_3
    //   180: getfield 283	cn/jpush/android/data/d:Q	Ljava/lang/String;
    //   183: invokestatic 158	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   186: ifne +219 -> 405
    //   189: aload_3
    //   190: getfield 283	cn/jpush/android/data/d:Q	Ljava/lang/String;
    //   193: invokestatic 289	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   196: astore_3
    //   197: aload_3
    //   198: ifnull +196 -> 394
    //   201: getstatic 109	cn/jpush/android/api/m:z	[Ljava/lang/String;
    //   204: bipush 37
    //   206: aaload
    //   207: iconst_1
    //   208: anewarray 13	java/lang/String
    //   211: dup
    //   212: iconst_0
    //   213: getstatic 109	cn/jpush/android/api/m:z	[Ljava/lang/String;
    //   216: bipush 40
    //   218: aaload
    //   219: aastore
    //   220: invokestatic 123	cn/jpush/android/api/m:a	(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/HashMap;
    //   223: getstatic 109	cn/jpush/android/api/m:z	[Ljava/lang/String;
    //   226: bipush 40
    //   228: aaload
    //   229: invokevirtual 129	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   232: checkcast 131	java/lang/Integer
    //   235: astore 9
    //   237: getstatic 109	cn/jpush/android/api/m:z	[Ljava/lang/String;
    //   240: bipush 39
    //   242: aaload
    //   243: iconst_1
    //   244: anewarray 13	java/lang/String
    //   247: dup
    //   248: iconst_0
    //   249: getstatic 109	cn/jpush/android/api/m:z	[Ljava/lang/String;
    //   252: bipush 38
    //   254: aaload
    //   255: aastore
    //   256: invokestatic 123	cn/jpush/android/api/m:a	(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/HashMap;
    //   259: getstatic 109	cn/jpush/android/api/m:z	[Ljava/lang/String;
    //   262: bipush 38
    //   264: aaload
    //   265: invokevirtual 129	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   268: checkcast 131	java/lang/Integer
    //   271: astore 10
    //   273: aload 9
    //   275: ifnull +114 -> 389
    //   278: aload 10
    //   280: ifnull +109 -> 389
    //   283: aload 9
    //   285: invokevirtual 135	java/lang/Integer:intValue	()I
    //   288: ifle +101 -> 389
    //   291: aload 10
    //   293: invokevirtual 135	java/lang/Integer:intValue	()I
    //   296: ifle +93 -> 389
    //   299: new 291	android/widget/RemoteViews
    //   302: dup
    //   303: aload_0
    //   304: invokevirtual 206	android/content/Context:getPackageName	()Ljava/lang/String;
    //   307: aload 10
    //   309: invokevirtual 135	java/lang/Integer:intValue	()I
    //   312: invokespecial 294	android/widget/RemoteViews:<init>	(Ljava/lang/String;I)V
    //   315: astore_0
    //   316: aload_0
    //   317: aload 9
    //   319: invokevirtual 135	java/lang/Integer:intValue	()I
    //   322: aload_3
    //   323: invokevirtual 298	android/widget/RemoteViews:setImageViewBitmap	(ILandroid/graphics/Bitmap;)V
    //   326: aload 8
    //   328: aload_0
    //   329: putfield 302	android/app/Notification:contentView	Landroid/widget/RemoteViews;
    //   332: aload 8
    //   334: aload_2
    //   335: putfield 306	android/app/Notification:contentIntent	Landroid/app/PendingIntent;
    //   338: aload 8
    //   340: areturn
    //   341: astore 8
    //   343: iconst_m1
    //   344: istore 6
    //   346: invokestatic 308	cn/jpush/android/util/x:h	()V
    //   349: goto -319 -> 30
    //   352: iconst_1
    //   353: istore 6
    //   355: goto -224 -> 131
    //   358: iconst_2
    //   359: istore 6
    //   361: goto -230 -> 131
    //   364: iconst_m1
    //   365: istore 6
    //   367: goto -236 -> 131
    //   370: iconst_4
    //   371: istore 6
    //   373: goto -242 -> 131
    //   376: aload_0
    //   377: iload_1
    //   378: aload_2
    //   379: ldc_w 274
    //   382: invokestatic 311	android/app/PendingIntent:getActivity	(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   385: astore_2
    //   386: goto -207 -> 179
    //   389: invokestatic 314	cn/jpush/android/util/x:e	()V
    //   392: aconst_null
    //   393: areturn
    //   394: invokestatic 314	cn/jpush/android/util/x:e	()V
    //   397: aconst_null
    //   398: areturn
    //   399: astore_0
    //   400: invokestatic 317	cn/jpush/android/util/x:i	()V
    //   403: aconst_null
    //   404: areturn
    //   405: aload 8
    //   407: aload_0
    //   408: aload_3
    //   409: getfield 320	cn/jpush/android/data/d:A	Ljava/lang/String;
    //   412: aload_3
    //   413: getfield 245	cn/jpush/android/data/d:B	Ljava/lang/String;
    //   416: aload_2
    //   417: invokevirtual 324	android/app/Notification:setLatestEventInfo	(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V
    //   420: goto -82 -> 338
    //   423: iconst_m1
    //   424: istore 6
    //   426: goto -396 -> 30
    //
    // Exception table:
    //   from	to	target	type
    //   8	30	341	android/content/pm/PackageManager$NameNotFoundException
    //   189	197	399	java/lang/Exception
    //   201	273	399	java/lang/Exception
    //   283	338	399	java/lang/Exception
    //   389	392	399	java/lang/Exception
    //   394	397	399	java/lang/Exception
  }

  public static HashMap<String, Integer> a(String paramString, String[] paramArrayOfString)
  {
    int j = 0;
    if ((ai.a(paramString)) || (paramArrayOfString == null) || (paramArrayOfString.length == 0))
      throw new NullPointerException(z[33]);
    HashMap localHashMap = new HashMap();
    try
    {
      String str = cn.jpush.android.a.d.getPackageName();
      Class[] arrayOfClass = Class.forName(str + z[32]).getDeclaredClasses();
      int k = arrayOfClass.length;
      int i = 0;
      while (i < k)
      {
        str = arrayOfClass[i];
        if (str.getName().contains(paramString))
        {
          k = paramArrayOfString.length;
          i = j;
          while (i < k)
          {
            paramString = paramArrayOfString[i];
            localHashMap.put(paramString, Integer.valueOf(str.getDeclaredField(paramString).getInt(paramString)));
            i += 1;
          }
        }
        i += 1;
      }
    }
    catch (Exception paramString)
    {
      x.i();
    }
    return localHashMap;
  }

  public static void a(Context paramContext)
  {
    while (true)
    {
      Integer localInteger = (Integer)a.poll();
      if (localInteger == null)
        break;
      b(paramContext, localInteger.intValue());
    }
  }

  public static void a(Context paramContext, int paramInt)
  {
    int i = a.size() - paramInt;
    if (i <= 0);
    while (true)
    {
      return;
      paramInt = 0;
      while (paramInt < i)
      {
        Integer localInteger = (Integer)a.poll();
        if (localInteger != null)
          b(paramContext, localInteger.intValue());
        paramInt += 1;
      }
    }
  }

  public static void a(Context paramContext, d paramd)
  {
    if (Thread.currentThread().getId() == PushService.a)
    {
      x.d();
      new Thread(new n(paramContext, paramd)).start();
      return;
    }
    b(paramContext, paramd);
  }

  public static void a(Context paramContext, d paramd, int paramInt)
  {
    new StringBuilder(z[34]).append(paramd.c).toString();
    x.c();
    Context localContext = paramContext;
    if (paramContext == null)
      localContext = cn.jpush.android.a.d;
    ((NotificationManager)localContext.getSystemService(z[0])).cancel(a(paramd, paramInt));
  }

  public static void a(Context paramContext, d paramd, boolean paramBoolean1, boolean paramBoolean2)
  {
    x.b();
    int i = a(paramd, 0);
    if ((paramd.h) && (paramd.e))
    {
      NotificationManager localNotificationManager2 = (NotificationManager)paramContext.getSystemService(z[0]);
      String str1;
      if ((paramd instanceof s))
      {
        str1 = paramd.B;
        localObject2 = paramd.A;
        str2 = paramd.l;
        if (!ai.a(paramd.m))
          break label234;
      }
      HashMap localHashMap;
      label234: for (localObject1 = paramContext.getPackageName(); ; localObject1 = paramd.m)
      {
        localHashMap = new HashMap();
        localHashMap.put(z[12], paramd.c);
        localHashMap.put(z[18], paramd.c);
        localHashMap.put(z[15], str1);
        if (!TextUtils.isEmpty((CharSequence)localObject2))
          localHashMap.put(z[6], localObject2);
        if (!ai.a(str2))
          localHashMap.put(z[11], str2);
        if (!ai.a(str2))
          localHashMap.put(z[11], str2);
        if (!ai.a(str1))
          break;
        a(paramContext, localHashMap, 0, "", (String)localObject1, paramd);
        return;
      }
      Object localObject2 = JPushInterface.b(paramd.f);
      String str2 = ((PushNotificationBuilder)localObject2).a();
      Notification localNotification = ((PushNotificationBuilder)localObject2).a(str1, localHashMap);
      if ((localNotification != null) && (!ai.a(str1)))
      {
        if (!paramd.f())
          if (cn.jpush.android.util.a.d(paramContext, PushReceiver.class.getCanonicalName()))
          {
            localObject2 = new Intent(z[16] + UUID.randomUUID().toString());
            ((Intent)localObject2).setClass(paramContext, PushReceiver.class);
            ((Intent)localObject2).putExtra(z[4], paramd.g);
          }
        while (true)
        {
          a((Intent)localObject2, localHashMap, i);
          ((Intent)localObject2).putExtra(z[15], str1);
          ((Intent)localObject2).putExtra(z[17], (String)localObject1);
          if (!ai.a(str2))
            ((Intent)localObject2).putExtra(z[14], str2);
          localObject2 = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject2, 1073741824);
          localNotification.contentIntent = ((PendingIntent)localObject2);
          if (!JPushInterface.a(paramd.f))
          {
            if (1 == paramd.g)
              paramd.z = 1;
            localNotification.flags = b(paramd.z);
            if (localNotification.defaults == 0)
              localNotification.defaults = 3;
          }
          if (cn.jpush.android.util.a.o(paramContext))
            localNotification.defaults = 0;
          localNotificationManager2.notify(i, localNotification);
          if ((!a.contains(Integer.valueOf(i))) && (paramd.g != 1))
            a.offer(Integer.valueOf(i));
          if (a.size() > ae.a(paramContext, z[10], 2147483647));
          try
          {
            int j = ((Integer)a.poll()).intValue();
            if (j != 0)
              localNotificationManager2.cancel(j);
            if (1 != paramd.g)
              ServiceInterface.a(paramd.c, 1018, paramContext);
            a(paramContext, localHashMap, i, str2, (String)localObject1, paramd);
            return;
            x.d();
            localObject2 = new Intent(z[13]);
            ((Intent)localObject2).addCategory((String)localObject1);
            continue;
            localObject2 = PendingIntent.getActivity(paramContext, i, cn.jpush.android.util.a.a(paramContext, paramd, false), 134217728);
          }
          catch (Exception localException)
          {
            while (true)
              x.f();
          }
        }
      }
      x.d(z[3], z[7]);
      return;
    }
    NotificationManager localNotificationManager1 = (NotificationManager)paramContext.getSystemService(z[0]);
    if ((paramd.R != null) && (paramd.R.size() > 0))
    {
      localObject1 = new Intent();
      ((Intent)localObject1).putExtra(z[9], paramd);
      ((Intent)localObject1).setAction(z[8]);
      ((Intent)localObject1).addCategory(z[2]);
    }
    for (Object localObject1 = a(paramContext, i, (Intent)localObject1, paramd, paramBoolean1, true); ; localObject1 = a(paramContext, i, cn.jpush.android.util.a.a(paramContext, paramd, paramBoolean2), paramd, paramBoolean1, false))
    {
      if ((paramd instanceof s))
        ((s)paramd).S.length();
      if (localObject1 != null)
        break;
      x.e();
      return;
    }
    if ((!paramBoolean1) && (!TextUtils.isEmpty(paramd.y)))
    {
      localObject1 = new p(paramContext.getMainLooper(), (Notification)localObject1, localNotificationManager1);
      paramContext = paramContext.getFilesDir().getAbsolutePath() + z[5];
      cn.jpush.android.util.n.a(paramd.y, paramContext, new o((p)localObject1, i, paramd));
      return;
    }
    ServiceInterface.a(paramd.c, 1018, paramContext);
    localNotificationManager1.notify(i, (Notification)localObject1);
  }

  public static void a(Context paramContext, String paramString)
  {
    new StringBuilder(z[1]).append(paramString).toString();
    x.c();
    Context localContext = paramContext;
    if (paramContext == null)
      localContext = cn.jpush.android.a.d;
    paramContext = (NotificationManager)localContext.getSystemService(z[0]);
    paramContext.cancel(a(paramString, 0));
    paramContext.cancel(a(paramString, 1));
  }

  private static void a(Context paramContext, Map<String, String> paramMap, int paramInt, String paramString1, String paramString2, d paramd)
  {
    x.b(z[3], z[23]);
    Intent localIntent = new Intent(z[24]);
    a(localIntent, paramMap, paramInt);
    if (!ai.a(paramString1))
      localIntent.putExtra(z[14], paramString1);
    if ((paramd.f()) && ((paramd instanceof s)))
    {
      paramMap = (s)paramd;
      if (paramMap.ac.startsWith(z[28]))
        paramMap.ac = paramMap.ac.replaceFirst(z[28], "");
      localIntent.putExtra(z[27], paramMap.ac);
      if ((paramMap.Z != null) && (paramMap.Z.size() > 0))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        paramd = k.b(paramContext, paramd.c);
        Iterator localIterator = paramMap.Z.iterator();
        while (localIterator.hasNext())
        {
          paramString1 = (String)localIterator.next();
          paramMap = paramString1;
          if (paramString1.startsWith(z[29]))
            paramMap = cn.jpush.android.util.m.c(paramString1);
          if (ai.a(localStringBuilder.toString()))
            localStringBuilder.append(paramd).append(paramMap);
          else
            localStringBuilder.append(",").append(paramd).append(paramMap);
        }
        localIntent.putExtra(z[25], localStringBuilder.toString());
      }
    }
    localIntent.addCategory(paramString2);
    paramContext.sendBroadcast(localIntent, paramString2 + z[26]);
  }

  private static void a(Intent paramIntent, Map<String, String> paramMap, int paramInt)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      paramIntent.putExtra(str, (String)paramMap.get(str));
    }
    if (paramInt != 0)
      paramIntent.putExtra(z[22], paramInt);
  }

  private static int b(int paramInt)
  {
    switch (paramInt)
    {
    case 0:
    default:
      return 1;
    case 1:
      return 16;
    case 2:
    }
    return 32;
  }

  public static void b(Context paramContext, int paramInt)
  {
    new StringBuilder(z[36]).append(paramInt).toString();
    x.c();
    Context localContext = paramContext;
    if (paramContext == null)
      localContext = cn.jpush.android.a.d;
    ((NotificationManager)localContext.getSystemService(z[0])).cancel(paramInt);
  }

  public static void b(Context paramContext, d paramd)
  {
    Object localObject;
    if ((!ai.a(paramd.t)) && (!paramd.u))
    {
      localObject = d.a(paramContext, paramd);
      if (localObject == null)
      {
        x.f();
        localObject = null;
        if (localObject != null)
          break label47;
      }
    }
    label47: label99: 
    do
    {
      do
      {
        return;
        ((d)localObject).u = true;
        localObject = paramd;
        break;
        if (paramd.D < 0)
          break label99;
      }
      while (d(paramContext, paramd) == null);
      localObject = new Intent();
      ((Intent)localObject).setAction(z[21]);
      ((Intent)localObject).putExtra(z[9], paramd);
      paramContext.sendBroadcast((Intent)localObject);
      return;
    }
    while (c(paramContext, paramd) == null);
    a(paramContext, paramd, false, false);
  }

  private static d c(Context paramContext, d paramd)
  {
    Object localObject = paramd;
    if (!ai.a(paramd.r))
    {
      localObject = paramd;
      if (!paramd.s)
      {
        x.d();
        localObject = cn.jpush.android.util.n.a(paramd.r, 5, 5000L, 4);
        if (localObject != null)
          try
          {
            int i = paramd.r.lastIndexOf("/");
            String str = paramd.r.substring(i + 1);
            str = k.a(paramContext, paramd.c) + str;
            if (cn.jpush.android.util.m.a(str, (byte[])localObject, paramContext))
            {
              paramd.Q = str;
              paramd.s = true;
              new StringBuilder(z[19]).append(str).toString();
              x.b();
              return paramd;
            }
            x.f();
            return null;
          }
          catch (Exception paramContext)
          {
            x.h();
            return null;
          }
        new StringBuilder(z[20]).append(paramd.r).toString();
        x.e();
        localObject = null;
      }
    }
    return localObject;
  }

  private static d d(Context paramContext, d paramd)
  {
    Object localObject = paramd;
    if (!ai.a(paramd.G))
    {
      x.d();
      localObject = cn.jpush.android.util.n.a(paramd.G, 5, 5000L, 4);
      if (localObject != null)
        try
        {
          int i = paramd.G.lastIndexOf("/");
          String str = paramd.G.substring(i + 1);
          str = k.a(paramContext, paramd.c) + str;
          if (cn.jpush.android.util.m.a(str, (byte[])localObject, paramContext))
          {
            paramd.H = str;
            new StringBuilder(z[19]).append(str).toString();
            x.b();
            return paramd;
          }
          x.f();
          return null;
        }
        catch (Exception paramContext)
        {
          x.h();
          return null;
        }
      new StringBuilder(z[35]).append(paramd.H).toString();
      x.e();
      localObject = null;
    }
    return localObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.m
 * JD-Core Version:    0.6.2
 */