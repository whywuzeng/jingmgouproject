package com.umeng.message.proguard;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class bI
{
  private Context a;
  private String b;
  private ClassLoader c;
  private HashMap<Class<?>, ArrayList<Class<?>>> d;
  private boolean e;

  public bI(Context paramContext, String paramString)
  {
    this.a = paramContext;
    this.b = paramString;
    this.e = b();
    if (this.e)
      this.d = a(paramContext);
  }

  public static void a(Context paramContext, ClassLoader paramClassLoader)
  {
    try
    {
      Log.i("ClassLoaderEngine", "setAPKClassLoader");
      Object localObject = bN.a(Activity.class, "mMainThread").get(paramContext);
      paramContext = ((WeakReference)((HashMap)bN.a(localObject.getClass(), "mPackages").get(localObject)).get(paramContext.getPackageName())).get();
      localObject = paramContext.getClass();
      if (localObject != null)
        bN.a((Class)localObject, "mClassLoader").set(paramContext, paramClassLoader);
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public String a(Class<?> paramClass)
  {
    if ((paramClass != null) && (paramClass.isInterface()) && (this.d != null) && (this.d.get(paramClass) != null) && (((ArrayList)this.d.get(paramClass)).size() > 0))
      return ((Class)((ArrayList)this.d.get(paramClass)).get(0)).getName();
    return null;
  }

  public HashMap<Class<?>, ArrayList<Class<?>>> a(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    if (this.c != null)
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject1 = bN.a(this.c, "pathList");
      if (localObject1 == null)
        return localHashMap;
      localObject1 = bN.a(localObject1, "dexElements");
      Object localObject2;
      int j;
      if ((localObject1 != null) && (localObject1.getClass().isArray()))
      {
        localObject1 = (Object[])localObject1;
        i = 0;
        if (i < localObject1.length)
        {
          localObject2 = bN.a(localObject1[i], "dexFile");
          if (localObject2 == null);
          while (true)
          {
            i += 1;
            break;
            localObject2 = bN.a(bN.a(paramContext, localObject2, "entries", null, null), "mNameList");
            if (localObject2 != null)
            {
              if (localObject2.getClass().isArray())
              {
                localObject2 = (Object[])localObject2;
                j = 0;
                while (j < localObject2.length)
                  try
                  {
                    localArrayList.add(this.c.loadClass(localObject2[j] + ""));
                    j += 1;
                  }
                  catch (ClassNotFoundException localClassNotFoundException)
                  {
                    while (true)
                      localClassNotFoundException.printStackTrace();
                  }
              }
              Log.d("ClassLoaderEngine", "mNameList:" + localObject2);
            }
            else
            {
              Log.d("ClassLoaderEngine", "mNameList");
            }
          }
        }
      }
      else
      {
        if (localObject1 != null)
          break label366;
        Log.e("ClassLoaderEngine", "dexs == null");
      }
      int i = 0;
      while (true)
      {
        if (i >= localArrayList.size())
          break label384;
        localObject2 = (Class)localArrayList.get(i);
        Class[] arrayOfClass = ((Class)localObject2).getInterfaces();
        if (arrayOfClass != null)
        {
          j = 0;
          while (true)
            if (j < arrayOfClass.length)
            {
              localObject1 = (ArrayList)localHashMap.get(arrayOfClass[j]);
              paramContext = (Context)localObject1;
              if (localObject1 == null)
                paramContext = new ArrayList();
              paramContext.add(localObject2);
              localHashMap.put(arrayOfClass[j], paramContext);
              j += 1;
              continue;
              label366: Log.e("ClassLoaderEngine", "dexs is not array");
              break;
            }
        }
        i += 1;
      }
      label384: return localHashMap;
    }
    Log.e("ClassLoaderEngine", "loader ==null");
    return null;
  }

  public boolean a()
  {
    return this.e;
  }

  public boolean b()
  {
    Object localObject = Boolean.valueOf(false);
    File localFile = bO.a(this.a, this.b);
    ClassLoader localClassLoader = bI.class.getClassLoader();
    if ((localFile != null) && (localFile.exists()))
    {
      localObject = localFile.getParentFile().getAbsolutePath();
      if (this.c == null)
      {
        this.c = new bJ(localFile.getAbsolutePath(), (String)localObject, (String)localObject, localClassLoader);
        Log.i("ClassLoaderEngine", "jar path=:" + (String)localObject);
      }
      localObject = Boolean.valueOf(true);
    }
    while (true)
    {
      return ((Boolean)localObject).booleanValue();
      this.c = localClassLoader;
      Log.i("ClassLoaderEngine", "Jarfile not exist,Use caller's ClassLoader");
    }
  }

  public ClassLoader c()
  {
    return this.c;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bI
 * JD-Core Version:    0.6.2
 */