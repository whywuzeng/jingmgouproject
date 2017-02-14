package com.umeng.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;
import u.aly.bq;
import u.aly.br;
import u.aly.cd;

public final class h
{
  private static h a = null;
  private static Context b;
  private static String c;
  private static long e = 0L;
  private static long f = 0L;
  private static final String g = "mobclick_agent_user_";
  private static final String h = "mobclick_agent_online_setting_";
  private static final String i = "mobclick_agent_header_";
  private static final String j = "mobclick_agent_update_";
  private static final String k = "mobclick_agent_state_";
  private static final String l = "mobclick_agent_cached_";
  private a d;

  public h(Context paramContext)
  {
    this.d = new a(paramContext);
    b = paramContext.getApplicationContext();
    c = paramContext.getPackageName();
  }

  public static h a(Context paramContext)
  {
    try
    {
      if (a == null)
        a = new h(paramContext);
      paramContext = a;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private static boolean a(File paramFile)
  {
    long l1 = paramFile.length();
    return (paramFile.exists()) && (l1 > f);
  }

  private SharedPreferences n()
  {
    return b.getSharedPreferences("mobclick_agent_user_" + c, 0);
  }

  private String o()
  {
    return "mobclick_agent_header_" + c;
  }

  private String p()
  {
    return "mobclick_agent_cached_" + c + bq.c(b);
  }

  public void a(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt <= 3))
    {
      SharedPreferences localSharedPreferences = j();
      if (localSharedPreferences != null)
        localSharedPreferences.edit().putInt("oc_dc", paramInt).commit();
    }
  }

  public void a(int paramInt1, int paramInt2)
  {
    SharedPreferences.Editor localEditor = a(b).j().edit();
    localEditor.putInt("umeng_net_report_policy", paramInt1);
    localEditor.putLong("umeng_net_report_interval", paramInt2);
    localEditor.commit();
  }

  public void a(String paramString1, String paramString2)
  {
    if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)))
    {
      SharedPreferences.Editor localEditor = n().edit();
      localEditor.putString("au_p", paramString1);
      localEditor.putString("au_u", paramString2);
      localEditor.commit();
    }
  }

  public void a(byte[] paramArrayOfByte)
  {
    String str = p();
    try
    {
      cd.a(new File(b.getFilesDir(), str), paramArrayOfByte);
      return;
    }
    catch (Exception paramArrayOfByte)
    {
      br.b("MobclickAgent", paramArrayOfByte.getMessage());
    }
  }

  public String[] a()
  {
    Object localObject2 = null;
    Object localObject1 = n();
    String str1 = ((SharedPreferences)localObject1).getString("au_p", null);
    String str2 = ((SharedPreferences)localObject1).getString("au_u", null);
    localObject1 = localObject2;
    if (str1 != null)
    {
      localObject1 = localObject2;
      if (str2 != null)
      {
        localObject1 = new String[2];
        localObject1[0] = str1;
        localObject1[1] = str2;
      }
    }
    return localObject1;
  }

  public void b()
  {
    n().edit().remove("au_p").remove("au_u").commit();
  }

  public void b(int paramInt)
  {
    if (paramInt > 0)
    {
      SharedPreferences localSharedPreferences = j();
      if (localSharedPreferences != null)
        localSharedPreferences.edit().putInt("oc_lt", paramInt).commit();
    }
  }

  public void b(byte[] paramArrayOfByte)
  {
    this.d.a(paramArrayOfByte);
  }

  public void c(int paramInt)
  {
    SharedPreferences localSharedPreferences = j();
    if (localSharedPreferences != null)
      localSharedPreferences.edit().putInt("oc_ec", paramInt).commit();
  }

  public int[] c()
  {
    SharedPreferences localSharedPreferences = j();
    int[] arrayOfInt = new int[2];
    if (localSharedPreferences.getInt("umeng_net_report_policy", -1) != -1)
    {
      arrayOfInt[0] = localSharedPreferences.getInt("umeng_net_report_policy", 1);
      arrayOfInt[1] = ((int)localSharedPreferences.getLong("umeng_net_report_interval", 0L));
      return arrayOfInt;
    }
    arrayOfInt[0] = localSharedPreferences.getInt("umeng_local_report_policy", 1);
    arrayOfInt[1] = ((int)localSharedPreferences.getLong("umeng_local_report_interval", 0L));
    return arrayOfInt;
  }

  public int d()
  {
    int m = 0;
    SharedPreferences localSharedPreferences = j();
    if (localSharedPreferences != null)
      m = localSharedPreferences.getInt("oc_dc", 0);
    return m;
  }

  public int d(int paramInt)
  {
    SharedPreferences localSharedPreferences = j();
    int m = paramInt;
    if (localSharedPreferences != null)
      m = localSharedPreferences.getInt("oc_ec", paramInt);
    return m;
  }

  public int e()
  {
    int m = 0;
    SharedPreferences localSharedPreferences = j();
    if (localSharedPreferences != null)
      m = localSharedPreferences.getInt("oc_lt", 0);
    return m;
  }

  // ERROR //
  public byte[] f()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 165	com/umeng/analytics/h:p	()Ljava/lang/String;
    //   4: astore_1
    //   5: new 81	java/io/File
    //   8: dup
    //   9: getstatic 70	com/umeng/analytics/h:b	Landroid/content/Context;
    //   12: invokevirtual 169	android/content/Context:getFilesDir	()Ljava/io/File;
    //   15: aload_1
    //   16: invokespecial 172	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   19: astore_2
    //   20: aload_2
    //   21: invokestatic 221	com/umeng/analytics/h:a	(Ljava/io/File;)Z
    //   24: ifeq +10 -> 34
    //   27: aload_2
    //   28: invokevirtual 224	java/io/File:delete	()Z
    //   31: pop
    //   32: aconst_null
    //   33: areturn
    //   34: aload_2
    //   35: invokevirtual 89	java/io/File:exists	()Z
    //   38: ifeq -6 -> 32
    //   41: getstatic 70	com/umeng/analytics/h:b	Landroid/content/Context;
    //   44: aload_1
    //   45: invokevirtual 228	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   48: astore_2
    //   49: aload_2
    //   50: astore_1
    //   51: aload_2
    //   52: invokestatic 231	u/aly/cd:b	(Ljava/io/InputStream;)[B
    //   55: astore_3
    //   56: aload_2
    //   57: invokestatic 234	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   60: aload_3
    //   61: areturn
    //   62: astore_3
    //   63: aconst_null
    //   64: astore_2
    //   65: aload_2
    //   66: astore_1
    //   67: aload_3
    //   68: invokevirtual 237	java/lang/Exception:printStackTrace	()V
    //   71: aload_2
    //   72: invokestatic 234	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   75: aconst_null
    //   76: areturn
    //   77: astore_1
    //   78: aconst_null
    //   79: astore_3
    //   80: aload_1
    //   81: astore_2
    //   82: aload_3
    //   83: invokestatic 234	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   86: aload_2
    //   87: athrow
    //   88: astore_2
    //   89: aload_1
    //   90: astore_3
    //   91: goto -9 -> 82
    //   94: astore_3
    //   95: goto -30 -> 65
    //
    // Exception table:
    //   from	to	target	type
    //   41	49	62	java/lang/Exception
    //   41	49	77	finally
    //   51	56	88	finally
    //   67	71	88	finally
    //   51	56	94	java/lang/Exception
  }

  public void g()
  {
    b.deleteFile(o());
    b.deleteFile(p());
  }

  public boolean h()
  {
    return this.d.a();
  }

  public a i()
  {
    return this.d;
  }

  public SharedPreferences j()
  {
    return b.getSharedPreferences("mobclick_agent_online_setting_" + c, 0);
  }

  public SharedPreferences k()
  {
    return b.getSharedPreferences("mobclick_agent_header_" + c, 0);
  }

  public SharedPreferences l()
  {
    return b.getSharedPreferences("mobclick_agent_update_" + c, 0);
  }

  public SharedPreferences m()
  {
    return b.getSharedPreferences("mobclick_agent_state_" + c, 0);
  }

  public static class a
  {
    private final int a = 10;
    private File b;
    private FilenameFilter c = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return paramAnonymousString.startsWith("um");
      }
    };

    public a(Context paramContext)
    {
      this(paramContext, ".um");
    }

    public a(Context paramContext, String paramString)
    {
      this.b = new File(paramContext.getFilesDir(), paramString);
      if ((!this.b.exists()) || (!this.b.isDirectory()))
        this.b.mkdir();
    }

    // ERROR //
    public void a(h.b paramb)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 45	com/umeng/analytics/h$a:b	Ljava/io/File;
      //   4: aload_0
      //   5: getfield 32	com/umeng/analytics/h$a:c	Ljava/io/FilenameFilter;
      //   8: invokevirtual 62	java/io/File:listFiles	(Ljava/io/FilenameFilter;)[Ljava/io/File;
      //   11: astore 5
      //   13: aload 5
      //   15: ifnull +88 -> 103
      //   18: aload 5
      //   20: arraylength
      //   21: ifle +82 -> 103
      //   24: aload_1
      //   25: aload_0
      //   26: getfield 45	com/umeng/analytics/h$a:b	Ljava/io/File;
      //   29: invokeinterface 67 2 0
      //   34: aload 5
      //   36: arraylength
      //   37: istore_3
      //   38: iconst_0
      //   39: istore_2
      //   40: iload_2
      //   41: iload_3
      //   42: if_icmpge +51 -> 93
      //   45: aload_1
      //   46: aload 5
      //   48: iload_2
      //   49: aaload
      //   50: invokeinterface 70 2 0
      //   55: istore 4
      //   57: iload 4
      //   59: ifeq +11 -> 70
      //   62: aload 5
      //   64: iload_2
      //   65: aaload
      //   66: invokevirtual 73	java/io/File:delete	()Z
      //   69: pop
      //   70: iload_2
      //   71: iconst_1
      //   72: iadd
      //   73: istore_2
      //   74: goto -34 -> 40
      //   77: astore 6
      //   79: aload 5
      //   81: iload_2
      //   82: aaload
      //   83: invokevirtual 73	java/io/File:delete	()Z
      //   86: pop
      //   87: goto -17 -> 70
      //   90: astore_1
      //   91: aload_1
      //   92: athrow
      //   93: aload_1
      //   94: aload_0
      //   95: getfield 45	com/umeng/analytics/h$a:b	Ljava/io/File;
      //   98: invokeinterface 75 2 0
      //   103: return
      //
      // Exception table:
      //   from	to	target	type
      //   45	57	77	java/lang/Throwable
      //   45	57	90	finally
    }

    public void a(byte[] paramArrayOfByte)
    {
      int i = 0;
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0));
      while (true)
      {
        return;
        Object localObject = String.format(Locale.US, "um_cache_%d.env", new Object[] { Long.valueOf(System.currentTimeMillis()) });
        localObject = new File(this.b, (String)localObject);
        try
        {
          cd.a((File)localObject, paramArrayOfByte);
          label56: paramArrayOfByte = this.b.listFiles(this.c);
          if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 10))
            continue;
          Arrays.sort(paramArrayOfByte);
          int j = paramArrayOfByte.length;
          while (i < j - 10)
          {
            paramArrayOfByte[i].delete();
            i += 1;
          }
        }
        catch (Exception paramArrayOfByte)
        {
          break label56;
        }
      }
    }

    public boolean a()
    {
      File[] arrayOfFile = this.b.listFiles();
      return (arrayOfFile != null) && (arrayOfFile.length > 0);
    }

    public void b()
    {
      File[] arrayOfFile = this.b.listFiles(this.c);
      if ((arrayOfFile != null) && (arrayOfFile.length > 0))
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          arrayOfFile[i].delete();
          i += 1;
        }
      }
    }

    public int c()
    {
      File[] arrayOfFile = this.b.listFiles(this.c);
      if ((arrayOfFile != null) && (arrayOfFile.length > 0))
        return arrayOfFile.length;
      return 0;
    }
  }

  public static abstract interface b
  {
    public abstract void a(File paramFile);

    public abstract boolean b(File paramFile);

    public abstract void c(File paramFile);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.h
 * JD-Core Version:    0.6.2
 */