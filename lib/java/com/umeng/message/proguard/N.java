package com.umeng.message.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class N
{
  private static Random b = new Random();
  private Context a = null;
  private Map<String, Object> c = new HashMap();
  private SharedPreferences d = null;
  private boolean e = true;

  public static N a(Context paramContext)
  {
    if (paramContext != null)
    {
      N localN = new N();
      localN.b(paramContext);
      localN.c();
      return localN;
    }
    return null;
  }

  private static String a(String paramString)
  {
    String str = paramString;
    if (at.a(paramString))
      str = "3";
    int i = b.nextInt(99999);
    paramString = "" + (i + 100000);
    return String.format("%s%s%s", new Object[] { str, Long.valueOf(System.currentTimeMillis()), paramString });
  }

  private void a(SharedPreferences.Editor paramEditor, String[] paramArrayOfString, int paramInt, boolean paramBoolean)
  {
    if (paramArrayOfString != null);
    while (true)
    {
      int i;
      try
      {
        if ((paramArrayOfString.length <= 0) || (paramInt <= 0))
          break label192;
        i = 0;
        if (i < paramInt)
        {
          if (i >= paramArrayOfString.length)
            break label195;
          String str = paramArrayOfString[i];
          if (at.a(str))
            break label195;
          if (Build.VERSION.SDK_INT >= 9)
          {
            if (paramEditor != null)
            {
              paramEditor.remove(str);
              if (y.a())
                y.b(2, "_clearlog", "key=" + str);
            }
          }
          else if (this.c.containsKey(str))
          {
            this.c.remove(str);
            if (y.a())
              y.b(2, "_clearlog", "key=" + str);
          }
        }
      }
      finally
      {
      }
      if ((Build.VERSION.SDK_INT >= 9) && (paramEditor != null))
        as.a(paramEditor);
      if (paramBoolean)
        b();
      label192: return;
      label195: i += 1;
    }
  }

  private void b(Context paramContext)
  {
    this.a = paramContext;
  }

  private void c()
  {
    Object localObject = ao.a(this.a, "UTMCLog");
    localObject = this.a.getSharedPreferences((String)localObject, 0);
    if (localObject != null)
    {
      this.d = ((SharedPreferences)localObject);
      if (Build.VERSION.SDK_INT < 9)
        this.c = this.d.getAll();
    }
  }

  // ERROR //
  public String a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_2
    //   6: invokestatic 159	com/umeng/message/proguard/N:a	(Ljava/lang/String;)Ljava/lang/String;
    //   9: astore 5
    //   11: getstatic 101	android/os/Build$VERSION:SDK_INT	I
    //   14: bipush 9
    //   16: if_icmplt +168 -> 184
    //   19: aload_0
    //   20: getfield 35	com/umeng/message/proguard/N:d	Landroid/content/SharedPreferences;
    //   23: ifnull +241 -> 264
    //   26: aload_0
    //   27: getfield 35	com/umeng/message/proguard/N:d	Landroid/content/SharedPreferences;
    //   30: invokeinterface 163 1 0
    //   35: astore_2
    //   36: aload_2
    //   37: aload 5
    //   39: new 90	java/lang/String
    //   42: dup
    //   43: aload_1
    //   44: invokevirtual 167	java/lang/String:getBytes	()[B
    //   47: invokestatic 171	com/umeng/message/proguard/t:b	()Ljava/lang/String;
    //   50: invokestatic 176	com/umeng/message/proguard/x:a	([BLjava/lang/String;)[B
    //   53: iconst_2
    //   54: invokestatic 181	com/umeng/message/proguard/ai:c	([BI)[B
    //   57: ldc 183
    //   59: invokespecial 186	java/lang/String:<init>	([BLjava/lang/String;)V
    //   62: invokeinterface 190 3 0
    //   67: pop
    //   68: aload_2
    //   69: invokestatic 133	com/umeng/message/proguard/as:a	(Landroid/content/SharedPreferences$Editor;)V
    //   72: iconst_1
    //   73: ldc 192
    //   75: aload_1
    //   76: invokestatic 119	com/umeng/message/proguard/y:b	(ILjava/lang/String;Ljava/lang/Object;)V
    //   79: aload_0
    //   80: getfield 35	com/umeng/message/proguard/N:d	Landroid/content/SharedPreferences;
    //   83: invokeinterface 154 1 0
    //   88: astore_1
    //   89: aload_1
    //   90: ifnull +79 -> 169
    //   93: aload_1
    //   94: invokeinterface 196 1 0
    //   99: sipush 1000
    //   102: if_icmple +67 -> 169
    //   105: aload_1
    //   106: invokeinterface 196 1 0
    //   111: sipush 1000
    //   114: isub
    //   115: istore_3
    //   116: iload_3
    //   117: ifle +52 -> 169
    //   120: iconst_2
    //   121: ldc 198
    //   123: ldc 200
    //   125: invokestatic 119	com/umeng/message/proguard/y:b	(ILjava/lang/String;Ljava/lang/Object;)V
    //   128: aload_1
    //   129: invokeinterface 196 1 0
    //   134: anewarray 90	java/lang/String
    //   137: astore 4
    //   139: aload_1
    //   140: invokeinterface 204 1 0
    //   145: aload 4
    //   147: invokeinterface 210 2 0
    //   152: pop
    //   153: aload_0
    //   154: aload_2
    //   155: invokestatic 215	com/umeng/message/proguard/al:a	()Lcom/umeng/message/proguard/al;
    //   158: aload 4
    //   160: iconst_1
    //   161: invokevirtual 218	com/umeng/message/proguard/al:a	([Ljava/lang/String;Z)[Ljava/lang/String;
    //   164: iload_3
    //   165: iconst_0
    //   166: invokespecial 220	com/umeng/message/proguard/N:a	(Landroid/content/SharedPreferences$Editor;[Ljava/lang/String;IZ)V
    //   169: aload_0
    //   170: monitorexit
    //   171: aload 5
    //   173: areturn
    //   174: astore_1
    //   175: aload_1
    //   176: invokevirtual 223	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   179: aconst_null
    //   180: astore_1
    //   181: goto -92 -> 89
    //   184: aload_0
    //   185: getfield 33	com/umeng/message/proguard/N:c	Ljava/util/Map;
    //   188: aload 5
    //   190: new 90	java/lang/String
    //   193: dup
    //   194: aload_1
    //   195: invokevirtual 167	java/lang/String:getBytes	()[B
    //   198: invokestatic 171	com/umeng/message/proguard/t:b	()Ljava/lang/String;
    //   201: invokestatic 176	com/umeng/message/proguard/x:a	([BLjava/lang/String;)[B
    //   204: iconst_2
    //   205: invokestatic 181	com/umeng/message/proguard/ai:c	([BI)[B
    //   208: ldc 183
    //   210: invokespecial 186	java/lang/String:<init>	([BLjava/lang/String;)V
    //   213: invokeinterface 227 3 0
    //   218: pop
    //   219: iconst_1
    //   220: ldc 192
    //   222: aload_1
    //   223: invokestatic 119	com/umeng/message/proguard/y:b	(ILjava/lang/String;Ljava/lang/Object;)V
    //   226: aload_0
    //   227: getfield 33	com/umeng/message/proguard/N:c	Ljava/util/Map;
    //   230: astore_1
    //   231: aload_0
    //   232: iconst_1
    //   233: putfield 37	com/umeng/message/proguard/N:e	Z
    //   236: aload 4
    //   238: astore_2
    //   239: goto -150 -> 89
    //   242: astore_2
    //   243: aload_2
    //   244: invokevirtual 223	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   247: aload 4
    //   249: astore_2
    //   250: goto -161 -> 89
    //   253: astore_1
    //   254: aload_0
    //   255: monitorexit
    //   256: aload_1
    //   257: athrow
    //   258: astore_2
    //   259: aconst_null
    //   260: astore_1
    //   261: goto -18 -> 243
    //   264: aconst_null
    //   265: astore_1
    //   266: aload 4
    //   268: astore_2
    //   269: goto -180 -> 89
    //
    // Exception table:
    //   from	to	target	type
    //   36	89	174	java/io/UnsupportedEncodingException
    //   231	236	242	java/io/UnsupportedEncodingException
    //   5	36	253	finally
    //   36	89	253	finally
    //   93	116	253	finally
    //   120	169	253	finally
    //   175	179	253	finally
    //   184	231	253	finally
    //   231	236	253	finally
    //   243	247	253	finally
    //   184	231	258	java/io/UnsupportedEncodingException
  }

  public Map<String, Object> a()
  {
    HashMap localHashMap = null;
    while (true)
    {
      try
      {
        Map localMap;
        if (Build.VERSION.SDK_INT >= 9)
        {
          if (this.d != null)
          {
            localMap = this.d.getAll();
            if (localMap != null)
            {
              localHashMap = new HashMap();
              localHashMap.putAll(localMap);
            }
            return localHashMap;
          }
        }
        else
        {
          localMap = this.c;
          continue;
        }
      }
      finally
      {
      }
      Object localObject2 = null;
    }
  }

  public void a(List<String> paramList)
  {
    if (paramList != null);
    try
    {
      String[] arrayOfString = new String[paramList.size()];
      paramList.toArray(arrayOfString);
      a(arrayOfString);
      return;
    }
    finally
    {
      paramList = finally;
    }
    throw paramList;
  }

  public void a(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null);
    try
    {
      if (paramArrayOfString.length > 0)
        a(this.d.edit(), paramArrayOfString, paramArrayOfString.length, true);
      return;
    }
    finally
    {
      paramArrayOfString = finally;
    }
    throw paramArrayOfString;
  }

  @TargetApi(9)
  public void b()
  {
    while (true)
    {
      try
      {
        if (Build.VERSION.SDK_INT >= 9)
        {
          if (this.d != null)
            as.a(this.d.edit());
          if (y.a())
            y.b(2, "saveToStorage", "commit to storage");
          return;
        }
        if (!this.e)
        {
          y.b(2, "saveToStorage", "return [beacuse no new logs was cached.];");
          continue;
        }
      }
      finally
      {
      }
      if ((this.d != null) && (this.c != null) && (this.c.size() > 0))
      {
        SharedPreferences.Editor localEditor = this.d.edit();
        localEditor.clear();
        Iterator localIterator = this.c.keySet().iterator();
        if (localIterator != null)
          while (localIterator.hasNext())
          {
            String str1 = (String)localIterator.next();
            if ((!at.a(str1)) && (this.c.containsKey(str1)))
            {
              String str2 = at.a(this.c.get(str1));
              if (!at.a(str2))
                localEditor.putString(str1, str2);
            }
          }
        localEditor.commit();
        this.e = false;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.N
 * JD-Core Version:    0.6.2
 */