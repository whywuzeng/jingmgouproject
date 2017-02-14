package net.tsz.afinal.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.ManyToOne;
import net.tsz.afinal.annotation.sqlite.OneToMany;
import net.tsz.afinal.annotation.sqlite.Property;
import net.tsz.afinal.annotation.sqlite.Transient;

public class FieldUtils
{
  public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static Method getBooleanFieldGetMethod(Class<?> paramClass, String paramString)
  {
    String str = "is" + paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
    if (isISStart(paramString))
      str = paramString;
    try
    {
      paramClass = paramClass.getDeclaredMethod(str, new Class[0]);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      paramClass.printStackTrace();
    }
    return null;
  }

  public static Method getBooleanFieldSetMethod(Class<?> paramClass, Field paramField)
  {
    String str2 = paramField.getName();
    String str1 = "set" + str2.substring(0, 1).toUpperCase() + str2.substring(1);
    if (isISStart(paramField.getName()))
      str1 = "set" + str2.substring(2, 3).toUpperCase() + str2.substring(3);
    try
    {
      paramClass = paramClass.getDeclaredMethod(str1, new Class[] { paramField.getType() });
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      paramClass.printStackTrace();
    }
    return null;
  }

  public static String getColumnByField(Field paramField)
  {
    Object localObject = (Property)paramField.getAnnotation(Property.class);
    if ((localObject != null) && (((Property)localObject).column().trim().length() != 0))
      return ((Property)localObject).column();
    localObject = (ManyToOne)paramField.getAnnotation(ManyToOne.class);
    if ((localObject != null) && (((ManyToOne)localObject).column().trim().length() != 0))
      return ((ManyToOne)localObject).column();
    localObject = (OneToMany)paramField.getAnnotation(OneToMany.class);
    if ((localObject != null) && (((OneToMany)localObject).manyColumn() != null) && (((OneToMany)localObject).manyColumn().trim().length() != 0))
      return ((OneToMany)localObject).manyColumn();
    localObject = (Id)paramField.getAnnotation(Id.class);
    if ((localObject != null) && (((Id)localObject).column().trim().length() != 0))
      return ((Id)localObject).column();
    return paramField.getName();
  }

  public static Field getFieldByColumnName(Class<?> paramClass, String paramString)
  {
    Object localObject3 = null;
    Object localObject1 = null;
    Object localObject2 = localObject3;
    Field[] arrayOfField;
    int j;
    int i;
    if (paramString != null)
    {
      arrayOfField = paramClass.getDeclaredFields();
      localObject2 = localObject3;
      if (arrayOfField != null)
      {
        localObject2 = localObject3;
        if (arrayOfField.length > 0)
        {
          localObject2 = localObject1;
          if (paramString.equals(ClassUtils.getPrimaryKeyColumn(paramClass)))
            localObject2 = ClassUtils.getPrimaryKeyField(paramClass);
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            j = arrayOfField.length;
            i = 0;
          }
        }
      }
    }
    while (true)
    {
      if (i >= j)
        localObject1 = localObject2;
      do
      {
        do
        {
          localObject2 = localObject1;
          if (localObject1 == null)
            localObject2 = getFieldByName(paramClass, paramString);
          return localObject2;
          localObject1 = arrayOfField[i];
          localObject3 = (Property)localObject1.getAnnotation(Property.class);
        }
        while ((localObject3 != null) && (paramString.equals(((Property)localObject3).column())));
        localObject3 = (ManyToOne)localObject1.getAnnotation(ManyToOne.class);
      }
      while ((localObject3 != null) && (((ManyToOne)localObject3).column().trim().length() != 0));
      i += 1;
    }
  }

  public static Field getFieldByName(Class<?> paramClass, String paramString)
  {
    Field localField = null;
    if (paramString != null);
    try
    {
      localField = paramClass.getDeclaredField(paramString);
      return localField;
    }
    catch (SecurityException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (NoSuchFieldException paramClass)
    {
      paramClass.printStackTrace();
    }
    return null;
  }

  public static Method getFieldGetMethod(Class<?> paramClass, String paramString)
  {
    paramString = "get" + paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
    try
    {
      paramClass = paramClass.getDeclaredMethod(paramString, new Class[0]);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      paramClass.printStackTrace();
    }
    return null;
  }

  public static Method getFieldGetMethod(Class<?> paramClass, Field paramField)
  {
    String str = paramField.getName();
    Method localMethod = null;
    if (paramField.getType() == Boolean.TYPE)
      localMethod = getBooleanFieldGetMethod(paramClass, str);
    paramField = localMethod;
    if (localMethod == null)
      paramField = getFieldGetMethod(paramClass, str);
    return paramField;
  }

  public static Method getFieldSetMethod(Class<?> paramClass, String paramString)
  {
    try
    {
      paramClass = getFieldSetMethod(paramClass, paramClass.getDeclaredField(paramString));
      return paramClass;
    }
    catch (SecurityException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (NoSuchFieldException paramClass)
    {
      while (true)
        paramClass.printStackTrace();
    }
  }

  public static Method getFieldSetMethod(Class<?> paramClass, Field paramField)
  {
    Object localObject = paramField.getName();
    localObject = "set" + ((String)localObject).substring(0, 1).toUpperCase() + ((String)localObject).substring(1);
    try
    {
      localObject = paramClass.getDeclaredMethod((String)localObject, new Class[] { paramField.getType() });
      return localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      if (paramField.getType() == Boolean.TYPE)
        return getBooleanFieldSetMethod(paramClass, paramField);
    }
    return null;
  }

  public static Object getFieldValue(Object paramObject, String paramString)
  {
    return invoke(paramObject, getFieldGetMethod(paramObject.getClass(), paramString));
  }

  public static Object getFieldValue(Object paramObject, Field paramField)
  {
    return invoke(paramObject, getFieldGetMethod(paramObject.getClass(), paramField));
  }

  public static String getPropertyDefaultValue(Field paramField)
  {
    paramField = (Property)paramField.getAnnotation(Property.class);
    if ((paramField != null) && (paramField.defaultValue().trim().length() != 0))
      return paramField.defaultValue();
    return null;
  }

  private static Object invoke(Object paramObject, Method paramMethod)
  {
    if ((paramObject == null) || (paramMethod == null))
      return null;
    try
    {
      paramObject = paramMethod.invoke(paramObject, new Object[0]);
      return paramObject;
    }
    catch (IllegalArgumentException paramObject)
    {
      paramObject.printStackTrace();
      return null;
    }
    catch (IllegalAccessException paramObject)
    {
      paramObject.printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramObject)
    {
      paramObject.printStackTrace();
    }
    return null;
  }

  public static boolean isBaseDateType(Field paramField)
  {
    paramField = paramField.getType();
    return (paramField.equals(String.class)) || (paramField.equals(Integer.class)) || (paramField.equals(Byte.class)) || (paramField.equals(Long.class)) || (paramField.equals(Double.class)) || (paramField.equals(Float.class)) || (paramField.equals(Character.class)) || (paramField.equals(Short.class)) || (paramField.equals(Boolean.class)) || (paramField.equals(java.util.Date.class)) || (paramField.equals(java.util.Date.class)) || (paramField.equals(java.sql.Date.class)) || (paramField.isPrimitive());
  }

  private static boolean isISStart(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0));
    while ((!paramString.startsWith("is")) || (Character.isLowerCase(paramString.charAt(2))))
      return false;
    return true;
  }

  public static boolean isManyToOne(Field paramField)
  {
    return paramField.getAnnotation(ManyToOne.class) != null;
  }

  public static boolean isManyToOneOrOneToMany(Field paramField)
  {
    return (isManyToOne(paramField)) || (isOneToMany(paramField));
  }

  public static boolean isOneToMany(Field paramField)
  {
    return paramField.getAnnotation(OneToMany.class) != null;
  }

  public static boolean isTransient(Field paramField)
  {
    return paramField.getAnnotation(Transient.class) != null;
  }

  public static void setFieldValue(Object paramObject1, Field paramField, Object paramObject2)
  {
    Object localObject3 = null;
    Method localMethod;
    while (true)
    {
      try
      {
        localMethod = getFieldSetMethod(paramObject1.getClass(), paramField);
        if (localMethod == null)
          return;
        localMethod.setAccessible(true);
        paramField = paramField.getType();
        if (paramField == String.class)
        {
          localMethod.invoke(paramObject1, new Object[] { paramObject2.toString() });
          return;
        }
        if ((paramField != Integer.TYPE) && (paramField != Integer.class))
          break;
        if (paramObject2 == null)
        {
          throw new NullPointerException();
          localMethod.invoke(paramObject1, new Object[] { Integer.valueOf(i) });
          return;
        }
      }
      catch (Exception paramObject1)
      {
        paramObject1.printStackTrace();
        return;
      }
      int i = Integer.parseInt(paramObject2.toString());
    }
    if ((paramField == Float.TYPE) || (paramField == Float.class))
    {
      if (paramObject2 == null)
        throw new NullPointerException();
      while (true)
      {
        Object localObject1;
        localMethod.invoke(paramObject1, new Object[] { Float.valueOf(localObject1) });
        return;
        float f = Float.parseFloat(paramObject2.toString());
      }
    }
    if ((paramField == Long.TYPE) || (paramField == Long.class))
    {
      if (paramObject2 == null)
        throw new NullPointerException();
      while (true)
      {
        Object localObject2;
        localMethod.invoke(paramObject1, new Object[] { Long.valueOf(localObject2) });
        return;
        long l = Long.parseLong(paramObject2.toString());
      }
    }
    if (paramField == java.util.Date.class)
    {
      if (paramObject2 == null);
      for (paramField = localObject3; ; paramField = stringToDateTime(paramObject2.toString()))
      {
        localMethod.invoke(paramObject1, new Object[] { paramField });
        return;
      }
    }
    localMethod.invoke(paramObject1, new Object[] { paramObject2 });
  }

  public static java.util.Date stringToDateTime(String paramString)
  {
    if (paramString != null)
      try
      {
        paramString = SDF.parse(paramString);
        return paramString;
      }
      catch (ParseException paramString)
      {
        paramString.printStackTrace();
      }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.utils.FieldUtils
 * JD-Core Version:    0.6.2
 */