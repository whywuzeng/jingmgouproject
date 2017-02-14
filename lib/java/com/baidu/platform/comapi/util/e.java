package com.baidu.platform.comapi.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.storage.StorageManager;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class e
{
  private static volatile e a = null;
  private boolean b = false;
  private boolean c = true;
  private final List<d> d = new ArrayList();
  private d e = null;
  private String f;

  public static e a()
  {
    if (a == null);
    try
    {
      if (a == null)
        a = new e();
      return a;
    }
    finally
    {
    }
  }

  // ERROR //
  private boolean a(String paramString)
  {
    // Byte code:
    //   0: new 42	java/io/File
    //   3: dup
    //   4: new 44	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 45	java/lang/StringBuilder:<init>	()V
    //   11: aload_1
    //   12: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: ldc 51
    //   17: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   26: astore_1
    //   27: aload_1
    //   28: invokevirtual 62	java/io/File:exists	()Z
    //   31: ifeq +8 -> 39
    //   34: aload_1
    //   35: invokevirtual 65	java/io/File:delete	()Z
    //   38: pop
    //   39: aload_1
    //   40: invokevirtual 68	java/io/File:createNewFile	()Z
    //   43: istore_2
    //   44: aload_1
    //   45: invokevirtual 62	java/io/File:exists	()Z
    //   48: ifeq +8 -> 56
    //   51: aload_1
    //   52: invokevirtual 65	java/io/File:delete	()Z
    //   55: pop
    //   56: iload_2
    //   57: ireturn
    //   58: astore_1
    //   59: iconst_0
    //   60: istore_2
    //   61: aload_1
    //   62: invokevirtual 71	java/lang/Exception:printStackTrace	()V
    //   65: iload_2
    //   66: ireturn
    //   67: astore_1
    //   68: goto -7 -> 61
    //
    // Exception table:
    //   from	to	target	type
    //   0	39	58	java/lang/Exception
    //   39	44	58	java/lang/Exception
    //   44	56	67	java/lang/Exception
  }

  @SuppressLint({"NewApi"})
  @TargetApi(14)
  private void c(Context paramContext)
  {
    while (true)
    {
      label199: boolean bool;
      try
      {
        localObject2 = (StorageManager)paramContext.getSystemService("storage");
        localObject1 = localObject2.getClass().getMethod("getVolumeList", new Class[0]);
        localObject3 = localObject2.getClass().getMethod("getVolumeState", new Class[] { String.class });
        Object localObject5 = Class.forName("android.os.storage.StorageVolume");
        localObject4 = ((Class)localObject5).getMethod("isRemovable", new Class[0]);
        localObject5 = ((Class)localObject5).getMethod("getPath", new Class[0]);
        Object[] arrayOfObject = (Object[])((Method)localObject1).invoke(localObject2, new Object[0]);
        if (arrayOfObject != null)
        {
          int k = arrayOfObject.length;
          i = 0;
          if (i >= k)
            break label367;
          localObject1 = arrayOfObject[i];
          String str = (String)((Method)localObject5).invoke(localObject1, new Object[0]);
          if ((str == null) || (str.length() <= 0))
            break label575;
          if (!"mounted".equals(((Method)localObject3).invoke(localObject2, new Object[] { str })))
            break label575;
          if (((Boolean)((Method)localObject4).invoke(localObject1, new Object[0])).booleanValue())
            break label582;
          j = 1;
          if ((Build.VERSION.SDK_INT <= 19) && (a(str)))
          {
            List localList = this.d;
            if (j != 0)
              break label587;
            bool = true;
            break label564;
            label232: localList.add(new d(str, bool, (String)localObject1, paramContext));
            break label575;
          }
          if ((Build.VERSION.SDK_INT < 19) || (!new File(str + File.separator + "BaiduMapSDKNew").exists()) || (!str.equals(paramContext.getSharedPreferences("map_pref", 0).getString("PREFFERED_SD_CARD", ""))))
            break label575;
          this.f = (str + File.separator + "BaiduMapSDKNew");
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      label367: 
      while (Build.VERSION.SDK_INT < 19)
        return;
      Object localObject1 = paramContext.getExternalFilesDirs(null);
      Object localObject2 = new ArrayList();
      ((List)localObject2).addAll(this.d);
      int i = 0;
      if ((i >= localObject1.length) || (localObject1[i] == null))
      {
        this.d.clear();
        this.d.addAll((Collection)localObject2);
        return;
      }
      Object localObject3 = localObject1[i].getAbsolutePath();
      Object localObject4 = this.d.iterator();
      do
        if (!((Iterator)localObject4).hasNext())
          break;
      while (!((String)localObject3).startsWith(((d)((Iterator)localObject4).next()).a()));
      for (int j = 1; ; j = 0)
      {
        localObject4 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).packageName;
        if ((localObject4 != null) && (j == 0) && (((String)localObject3).indexOf((String)localObject4) != -1))
          ((List)localObject2).add(new d((String)localObject3, true, "外置存储卡", paramContext));
        i += 1;
        break;
      }
      while (true)
      {
        label564: if (j == 0)
          break label593;
        localObject1 = "内置存储卡";
        break label232;
        label575: i += 1;
        break;
        label582: j = 0;
        break label199;
        label587: bool = false;
      }
      label593: localObject1 = "外置存储卡";
    }
  }

  // ERROR //
  private void d(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: new 30	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 31	java/util/ArrayList:<init>	()V
    //   10: astore 8
    //   12: new 30	java/util/ArrayList
    //   15: dup
    //   16: invokespecial 31	java/util/ArrayList:<init>	()V
    //   19: astore 7
    //   21: new 42	java/io/File
    //   24: dup
    //   25: ldc 238
    //   27: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   30: astore_2
    //   31: aload_2
    //   32: invokevirtual 62	java/io/File:exists	()Z
    //   35: ifeq +109 -> 144
    //   38: new 240	java/util/Scanner
    //   41: dup
    //   42: aload_2
    //   43: invokespecial 243	java/util/Scanner:<init>	(Ljava/io/File;)V
    //   46: astore_3
    //   47: aload_3
    //   48: astore_2
    //   49: aload_3
    //   50: invokevirtual 244	java/util/Scanner:hasNext	()Z
    //   53: ifeq +85 -> 138
    //   56: aload_3
    //   57: astore_2
    //   58: aload_3
    //   59: invokevirtual 247	java/util/Scanner:nextLine	()Ljava/lang/String;
    //   62: astore 4
    //   64: aload_3
    //   65: astore_2
    //   66: aload 4
    //   68: ldc 249
    //   70: invokevirtual 209	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   73: ifeq -26 -> 47
    //   76: aload_3
    //   77: astore_2
    //   78: aload 4
    //   80: bipush 9
    //   82: bipush 32
    //   84: invokevirtual 253	java/lang/String:replace	(CC)Ljava/lang/String;
    //   87: ldc 255
    //   89: invokevirtual 259	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   92: astore 4
    //   94: aload 4
    //   96: ifnull -49 -> 47
    //   99: aload_3
    //   100: astore_2
    //   101: aload 4
    //   103: arraylength
    //   104: ifle -57 -> 47
    //   107: aload_3
    //   108: astore_2
    //   109: aload 8
    //   111: aload 4
    //   113: iconst_1
    //   114: aaload
    //   115: invokeinterface 154 2 0
    //   120: pop
    //   121: goto -74 -> 47
    //   124: astore_1
    //   125: aload_1
    //   126: invokevirtual 71	java/lang/Exception:printStackTrace	()V
    //   129: aload_2
    //   130: ifnull +7 -> 137
    //   133: aload_2
    //   134: invokevirtual 262	java/util/Scanner:close	()V
    //   137: return
    //   138: aload_3
    //   139: astore_2
    //   140: aload_3
    //   141: invokevirtual 262	java/util/Scanner:close	()V
    //   144: new 42	java/io/File
    //   147: dup
    //   148: ldc_w 264
    //   151: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   154: astore_2
    //   155: aload_2
    //   156: invokevirtual 62	java/io/File:exists	()Z
    //   159: ifeq +148 -> 307
    //   162: new 240	java/util/Scanner
    //   165: dup
    //   166: aload_2
    //   167: invokespecial 243	java/util/Scanner:<init>	(Ljava/io/File;)V
    //   170: astore_3
    //   171: aload_3
    //   172: astore_2
    //   173: aload_3
    //   174: invokevirtual 244	java/util/Scanner:hasNext	()Z
    //   177: ifeq +124 -> 301
    //   180: aload_3
    //   181: astore_2
    //   182: aload_3
    //   183: invokevirtual 247	java/util/Scanner:nextLine	()Ljava/lang/String;
    //   186: astore 4
    //   188: aload_3
    //   189: astore_2
    //   190: aload 4
    //   192: ldc_w 266
    //   195: invokevirtual 209	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   198: ifeq -27 -> 171
    //   201: aload_3
    //   202: astore_2
    //   203: aload 4
    //   205: bipush 9
    //   207: bipush 32
    //   209: invokevirtual 253	java/lang/String:replace	(CC)Ljava/lang/String;
    //   212: ldc 255
    //   214: invokevirtual 259	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   217: astore 4
    //   219: aload 4
    //   221: ifnull -50 -> 171
    //   224: aload_3
    //   225: astore_2
    //   226: aload 4
    //   228: arraylength
    //   229: ifle -58 -> 171
    //   232: aload 4
    //   234: iconst_2
    //   235: aaload
    //   236: astore 6
    //   238: aload_3
    //   239: astore_2
    //   240: aload 6
    //   242: astore 4
    //   244: aload 6
    //   246: ldc_w 268
    //   249: invokevirtual 272	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   252: ifeq +21 -> 273
    //   255: aload_3
    //   256: astore_2
    //   257: aload 6
    //   259: iconst_0
    //   260: aload 6
    //   262: ldc_w 268
    //   265: invokevirtual 231	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   268: invokevirtual 276	java/lang/String:substring	(II)Ljava/lang/String;
    //   271: astore 4
    //   273: aload_3
    //   274: astore_2
    //   275: aload 7
    //   277: aload 4
    //   279: invokeinterface 154 2 0
    //   284: pop
    //   285: goto -114 -> 171
    //   288: astore_1
    //   289: aload_3
    //   290: astore_2
    //   291: aload_2
    //   292: ifnull +7 -> 299
    //   295: aload_2
    //   296: invokevirtual 262	java/util/Scanner:close	()V
    //   299: aload_1
    //   300: athrow
    //   301: aload_3
    //   302: astore_2
    //   303: aload_3
    //   304: invokevirtual 262	java/util/Scanner:close	()V
    //   307: invokestatic 282	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   310: invokevirtual 191	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   313: astore_2
    //   314: aload_0
    //   315: getfield 33	com/baidu/platform/comapi/util/e:d	Ljava/util/List;
    //   318: new 146	com/baidu/platform/comapi/util/d
    //   321: dup
    //   322: aload_2
    //   323: iconst_0
    //   324: ldc_w 284
    //   327: aload_1
    //   328: invokespecial 149	com/baidu/platform/comapi/util/d:<init>	(Ljava/lang/String;ZLjava/lang/String;Landroid/content/Context;)V
    //   331: invokeinterface 154 2 0
    //   336: pop
    //   337: aload 8
    //   339: invokeinterface 195 1 0
    //   344: astore_3
    //   345: aload_3
    //   346: invokeinterface 200 1 0
    //   351: ifeq +103 -> 454
    //   354: aload_3
    //   355: invokeinterface 204 1 0
    //   360: checkcast 103	java/lang/String
    //   363: astore 6
    //   365: aload 7
    //   367: aload 6
    //   369: invokeinterface 286 2 0
    //   374: ifeq -29 -> 345
    //   377: aload 6
    //   379: aload_2
    //   380: invokevirtual 131	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   383: ifne -38 -> 345
    //   386: new 42	java/io/File
    //   389: dup
    //   390: aload 6
    //   392: invokespecial 58	java/io/File:<init>	(Ljava/lang/String;)V
    //   395: astore 4
    //   397: aload 4
    //   399: invokevirtual 62	java/io/File:exists	()Z
    //   402: ifeq -57 -> 345
    //   405: aload 4
    //   407: invokevirtual 289	java/io/File:isDirectory	()Z
    //   410: ifeq -65 -> 345
    //   413: aload 4
    //   415: invokevirtual 292	java/io/File:canWrite	()Z
    //   418: ifeq -73 -> 345
    //   421: aload_0
    //   422: getfield 33	com/baidu/platform/comapi/util/e:d	Ljava/util/List;
    //   425: new 146	com/baidu/platform/comapi/util/d
    //   428: dup
    //   429: aload 6
    //   431: iconst_0
    //   432: ldc_w 284
    //   435: aload_1
    //   436: invokespecial 149	com/baidu/platform/comapi/util/d:<init>	(Ljava/lang/String;ZLjava/lang/String;Landroid/content/Context;)V
    //   439: invokeinterface 154 2 0
    //   444: pop
    //   445: goto -100 -> 345
    //   448: astore_1
    //   449: aconst_null
    //   450: astore_2
    //   451: goto -326 -> 125
    //   454: iconst_0
    //   455: ifeq -318 -> 137
    //   458: new 294	java/lang/NullPointerException
    //   461: dup
    //   462: invokespecial 295	java/lang/NullPointerException:<init>	()V
    //   465: athrow
    //   466: astore_1
    //   467: aload 5
    //   469: astore_2
    //   470: goto -179 -> 291
    //   473: astore_1
    //   474: aload_3
    //   475: astore_2
    //   476: goto -185 -> 291
    //   479: astore_1
    //   480: goto -189 -> 291
    //
    // Exception table:
    //   from	to	target	type
    //   49	56	124	java/lang/Exception
    //   58	64	124	java/lang/Exception
    //   66	76	124	java/lang/Exception
    //   78	94	124	java/lang/Exception
    //   101	107	124	java/lang/Exception
    //   109	121	124	java/lang/Exception
    //   140	144	124	java/lang/Exception
    //   173	180	124	java/lang/Exception
    //   182	188	124	java/lang/Exception
    //   190	201	124	java/lang/Exception
    //   203	219	124	java/lang/Exception
    //   226	232	124	java/lang/Exception
    //   244	255	124	java/lang/Exception
    //   257	273	124	java/lang/Exception
    //   275	285	124	java/lang/Exception
    //   303	307	124	java/lang/Exception
    //   173	180	288	finally
    //   182	188	288	finally
    //   190	201	288	finally
    //   203	219	288	finally
    //   226	232	288	finally
    //   244	255	288	finally
    //   257	273	288	finally
    //   275	285	288	finally
    //   303	307	288	finally
    //   21	47	448	java/lang/Exception
    //   144	171	448	java/lang/Exception
    //   307	345	448	java/lang/Exception
    //   345	445	448	java/lang/Exception
    //   21	47	466	finally
    //   144	171	466	finally
    //   307	345	466	finally
    //   345	445	466	finally
    //   49	56	473	finally
    //   58	64	473	finally
    //   66	76	473	finally
    //   78	94	473	finally
    //   101	107	473	finally
    //   109	121	473	finally
    //   140	144	473	finally
    //   125	129	479	finally
  }

  public void a(Context paramContext)
  {
    int i = 0;
    if (this.b)
      return;
    this.b = true;
    label411: 
    while (true)
      try
      {
        if (Build.VERSION.SDK_INT >= 14)
          c(paramContext);
      }
      catch (Exception localException1)
      {
        try
        {
          Iterator localIterator1;
          while (true)
          {
            if (this.d.size() > 0)
            {
              Object localObject = null;
              Iterator localIterator2 = this.d.iterator();
              d locald;
              while (true)
                if (localIterator2.hasNext())
                {
                  locald = (d)localIterator2.next();
                  boolean bool = new File(locald.b()).exists();
                  if (!bool)
                    break label411;
                  localObject = locald;
                  i += 1;
                  continue;
                  d(paramContext);
                  break;
                  localException1 = localException1;
                  localException1.printStackTrace();
                  break;
                }
              if (i != 0)
                break label324;
              this.e = b(paramContext);
              if (this.e == null)
              {
                localIterator1 = this.d.iterator();
                while (localIterator1.hasNext())
                {
                  locald = (d)localIterator1.next();
                  if (a(paramContext, locald))
                    this.e = locald;
                }
              }
              if (this.e == null)
                this.e = ((d)this.d.get(0));
            }
            try
            {
              if ((this.e == null) || (!a(this.e.a())))
                break label370;
              paramContext = new File(this.e.b());
              if (!paramContext.exists())
                paramContext.mkdirs();
              paramContext = new File(this.e.c());
              if (!paramContext.exists())
                paramContext.mkdirs();
              paramContext = new File(paramContext, ".nomedia");
              if (paramContext.exists())
                break;
              paramContext.createNewFile();
              return;
            }
            catch (Exception paramContext)
            {
              paramContext.printStackTrace();
              return;
            }
          }
          label324: if (i == 1)
          {
            if (!a(paramContext, localIterator1))
              continue;
            this.e = localIterator1;
          }
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          continue;
          this.e = b(paramContext);
          continue;
          label370: this.c = false;
          this.e = new d(paramContext);
          this.d.clear();
          this.d.add(this.e);
          return;
        }
      }
  }

  public boolean a(Context paramContext, d paramd)
  {
    paramd = paramd.a();
    if (!a(paramd))
      return false;
    paramContext = paramContext.getSharedPreferences("map_pref", 0).edit();
    paramContext.putString("PREFFERED_SD_CARD", paramd);
    return paramContext.commit();
  }

  public d b()
  {
    return this.e;
  }

  public d b(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("map_pref", 0).getString("PREFFERED_SD_CARD", "");
    if ((paramContext != null) && (paramContext.length() > 0))
    {
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext())
      {
        d locald = (d)localIterator.next();
        if (locald.a().equals(paramContext))
          return locald;
      }
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.util.e
 * JD-Core Version:    0.6.2
 */