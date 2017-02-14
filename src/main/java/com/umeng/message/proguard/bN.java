package com.umeng.message.proguard;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.android.agoo.ut.UTFactroy;

public class bN
{
  ClassLoader a;

  public bN(ClassLoader paramClassLoader)
  {
    this.a = paramClassLoader;
  }

  // ERROR //
  public static Object a(Context paramContext, ClassLoader paramClassLoader, String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload 7
    //   5: astore 6
    //   7: aload_1
    //   8: ifnull +32 -> 40
    //   11: aload_1
    //   12: aload_2
    //   13: invokevirtual 23	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   16: astore_1
    //   17: aload 7
    //   19: astore 6
    //   21: aload_1
    //   22: ifnull +18 -> 40
    //   25: aload_1
    //   26: aload_3
    //   27: aload 4
    //   29: invokevirtual 29	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   32: aload_1
    //   33: aload 5
    //   35: invokevirtual 35	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   38: astore 6
    //   40: aload 6
    //   42: areturn
    //   43: astore_1
    //   44: aconst_null
    //   45: astore_1
    //   46: invokestatic 41	org/android/agoo/ut/UTFactroy:getInstance	()Lorg/android/agoo/ut/UTFactroy;
    //   49: aload_0
    //   50: aload_1
    //   51: iconst_2
    //   52: anewarray 43	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc 45
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: new 47	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   69: ldc 50
    //   71: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: aload_2
    //   75: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: ldc 56
    //   80: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload_3
    //   84: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: ldc 58
    //   89: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload 4
    //   94: invokestatic 64	java/util/Arrays:toString	([Ljava/lang/Object;)Ljava/lang/String;
    //   97: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: ldc 66
    //   102: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: aload 5
    //   107: invokestatic 64	java/util/Arrays:toString	([Ljava/lang/Object;)Ljava/lang/String;
    //   110: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: aastore
    //   117: invokevirtual 73	org/android/agoo/ut/UTFactroy:commitEvent	(Landroid/content/Context;Ljava/lang/Object;[Ljava/lang/String;)V
    //   120: aload_1
    //   121: invokestatic 76	com/umeng/message/proguard/bN:a	(Ljava/lang/Class;)V
    //   124: aconst_null
    //   125: areturn
    //   126: astore 6
    //   128: goto -82 -> 46
    //
    // Exception table:
    //   from	to	target	type
    //   11	17	43	java/lang/Throwable
    //   25	40	126	java/lang/Throwable
  }

  // ERROR //
  public static Object a(Context paramContext, ClassLoader paramClassLoader, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_1
    //   4: ifnull +21 -> 25
    //   7: aload_1
    //   8: aload_2
    //   9: invokevirtual 23	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   12: astore_1
    //   13: aload_1
    //   14: aload_3
    //   15: invokevirtual 83	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   18: aload 4
    //   20: invokevirtual 89	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   23: astore 5
    //   25: aload 5
    //   27: areturn
    //   28: astore_1
    //   29: aconst_null
    //   30: astore_1
    //   31: aload_1
    //   32: invokestatic 76	com/umeng/message/proguard/bN:a	(Ljava/lang/Class;)V
    //   35: invokestatic 41	org/android/agoo/ut/UTFactroy:getInstance	()Lorg/android/agoo/ut/UTFactroy;
    //   38: aload_0
    //   39: aload_1
    //   40: iconst_2
    //   41: anewarray 43	java/lang/String
    //   44: dup
    //   45: iconst_0
    //   46: ldc 91
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: new 47	java/lang/StringBuilder
    //   54: dup
    //   55: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   58: ldc 50
    //   60: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_2
    //   64: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: ldc 58
    //   69: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: aload_3
    //   73: invokestatic 64	java/util/Arrays:toString	([Ljava/lang/Object;)Ljava/lang/String;
    //   76: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: ldc 66
    //   81: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: aload 4
    //   86: invokestatic 64	java/util/Arrays:toString	([Ljava/lang/Object;)Ljava/lang/String;
    //   89: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: aastore
    //   96: invokevirtual 73	org/android/agoo/ut/UTFactroy:commitEvent	(Landroid/content/Context;Ljava/lang/Object;[Ljava/lang/String;)V
    //   99: aconst_null
    //   100: areturn
    //   101: astore 5
    //   103: goto -72 -> 31
    //
    // Exception table:
    //   from	to	target	type
    //   7	13	28	java/lang/Throwable
    //   13	25	101	java/lang/Throwable
  }

  public static Object a(Context paramContext, Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    Class localClass;
    if (paramObject != null)
      localClass = paramObject.getClass();
    try
    {
      Method localMethod = localClass.getMethod(paramString, paramArrayOfClass);
      localObject1 = localObject2;
      if (localMethod != null)
        localObject1 = localMethod.invoke(paramObject, paramArrayOfObject);
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      a(localClass);
      UTFactroy.getInstance().commitEvent(paramContext, null, new String[] { "ERROR_EVENT_INVOKE_METHOD_ERROR", "instance=" + paramObject + ",methodName=" + paramString + ",paramType=" + Arrays.toString(paramArrayOfClass) + ",param=" + Arrays.toString(paramArrayOfObject) });
    }
    return null;
  }

  public static Object a(Object paramObject, String paramString)
  {
    if ((paramObject == null) || (paramString == null));
    do
    {
      return null;
      paramString = a(paramObject.getClass(), paramString);
    }
    while (paramString == null);
    try
    {
      paramObject = paramString.get(paramObject);
      return paramObject;
    }
    catch (Throwable paramObject)
    {
      paramObject.printStackTrace();
    }
    return null;
  }

  public static Field a(Class<?> paramClass, String paramString)
  {
    Field[] arrayOfField = paramClass.getDeclaredFields();
    int j = arrayOfField.length;
    int i = 0;
    while (i < j)
    {
      Field localField = arrayOfField[i];
      if (!localField.isAccessible())
        localField.setAccessible(true);
      if (localField.getName().equals(paramString))
        return localField;
      i += 1;
    }
    if (paramClass.getSuperclass() != null)
      return a(paramClass.getSuperclass(), paramString);
    return null;
  }

  private static void a(Class<?> paramClass)
  {
    if (paramClass == null);
    while (true)
    {
      return;
      paramClass = paramClass.getDeclaredMethods();
      if (paramClass != null)
      {
        int i = 0;
        while (i < paramClass.length)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          Class[] arrayOfClass = paramClass[i].getParameterTypes();
          paramClass[i].getReturnType();
          int j = 0;
          while (j < arrayOfClass.length)
          {
            localStringBuilder.append(arrayOfClass[j].toString()).append(",");
            j += 1;
          }
          Log.d("com.taobao.du.util.InvokeUtil", paramClass[i].getName() + "|" + localStringBuilder.toString() + "|" + paramClass[i].getParameterTypes().length);
          i += 1;
        }
      }
    }
  }

  public Object a(Context paramContext, String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    return a(paramContext, this.a, paramString1, paramString2, paramArrayOfClass, paramArrayOfObject);
  }

  public Object a(Context paramContext, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    return a(paramContext, this.a, paramString, paramArrayOfClass, paramArrayOfObject);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bN
 * JD-Core Version:    0.6.2
 */