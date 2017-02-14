package net.tsz.afinal.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;
import net.tsz.afinal.db.sqlite.ManyToOneLazyLoader;
import net.tsz.afinal.db.table.ManyToOne;
import net.tsz.afinal.db.table.OneToMany;
import net.tsz.afinal.db.table.Property;
import net.tsz.afinal.exception.DbException;

public class ClassUtils
{
  public static List<ManyToOne> getManyToOneList(Class<?> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      int j;
      int i;
      try
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        j = arrayOfField.length;
        i = 0;
        break label187;
        Field localField = arrayOfField[i];
        if ((!FieldUtils.isTransient(localField)) && (FieldUtils.isManyToOne(localField)))
        {
          ManyToOne localManyToOne = new ManyToOne();
          if (localField.getType() == ManyToOneLazyLoader.class)
          {
            Class localClass = (Class)((ParameterizedType)localField.getGenericType()).getActualTypeArguments()[1];
            if (localClass != null)
              localManyToOne.setManyClass(localClass);
            localManyToOne.setColumn(FieldUtils.getColumnByField(localField));
            localManyToOne.setFieldName(localField.getName());
            localManyToOne.setDataType(localField.getType());
            localManyToOne.setSet(FieldUtils.getFieldSetMethod(paramClass, localField));
            localManyToOne.setGet(FieldUtils.getFieldGetMethod(paramClass, localField));
            localArrayList.add(localManyToOne);
          }
          else
          {
            localManyToOne.setManyClass(localField.getType());
            continue;
          }
        }
      }
      catch (Exception paramClass)
      {
        throw new RuntimeException(paramClass.getMessage(), paramClass);
      }
      label187: 
      while (i >= j)
      {
        return localArrayList;
        i += 1;
      }
    }
  }

  public static List<OneToMany> getOneToManyList(Class<?> paramClass)
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    label260: 
    while (true)
    {
      int j;
      Field localField;
      try
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        j = arrayOfField.length;
        break label253;
        localField = arrayOfField[i];
        if ((FieldUtils.isTransient(localField)) || (!FieldUtils.isOneToMany(localField)))
          break label260;
        OneToMany localOneToMany = new OneToMany();
        localOneToMany.setColumn(FieldUtils.getColumnByField(localField));
        localOneToMany.setFieldName(localField.getName());
        if ((localField.getGenericType() instanceof ParameterizedType))
        {
          Object localObject = (ParameterizedType)localField.getGenericType();
          if (((ParameterizedType)localObject).getActualTypeArguments().length == 1)
          {
            localObject = (Class)localObject.getActualTypeArguments()[0];
            if (localObject != null)
              localOneToMany.setOneClass((Class)localObject);
            localOneToMany.setDataType(localField.getType());
            localOneToMany.setSet(FieldUtils.getFieldSetMethod(paramClass, localField));
            localOneToMany.setGet(FieldUtils.getFieldGetMethod(paramClass, localField));
            localArrayList.add(localOneToMany);
            break label260;
          }
          localObject = (Class)localObject.getActualTypeArguments()[1];
          if (localObject == null)
            continue;
          localOneToMany.setOneClass((Class)localObject);
          continue;
        }
      }
      catch (Exception paramClass)
      {
        throw new RuntimeException(paramClass.getMessage(), paramClass);
      }
      throw new DbException("getOneToManyList Exception:" + localField.getName() + "'s type is null");
      label253: 
      while (i >= j)
      {
        return localArrayList;
        i += 1;
      }
    }
  }

  public static String getPrimaryKeyColumn(Class<?> paramClass)
  {
    int j = 0;
    String str = null;
    Field[] arrayOfField = paramClass.getDeclaredFields();
    if (arrayOfField != null)
    {
      paramClass = null;
      Object localObject = null;
      int k = arrayOfField.length;
      int i = 0;
      while (true)
      {
        if (i >= k);
        while (true)
        {
          if (paramClass == null)
            break label106;
          str = paramClass.column();
          if (str != null)
          {
            paramClass = str;
            if (str.trim().length() != 0);
          }
          else
          {
            paramClass = localObject.getName();
          }
          return paramClass;
          Field localField = arrayOfField[i];
          paramClass = (Id)localField.getAnnotation(Id.class);
          if (paramClass == null)
            break;
          localObject = localField;
        }
        i += 1;
      }
      label106: k = arrayOfField.length;
      i = 0;
      label112: if (i >= k)
      {
        k = arrayOfField.length;
        i = j;
      }
      while (true)
      {
        paramClass = str;
        if (i >= k)
          break;
        if ("id".equals(arrayOfField[i].getName()))
        {
          return "id";
          if ("_id".equals(arrayOfField[i].getName()))
            return "_id";
          i += 1;
          break label112;
        }
        i += 1;
      }
    }
    throw new RuntimeException("this model[" + paramClass + "] has no field");
  }

  public static Field getPrimaryKeyField(Class<?> paramClass)
  {
    int j = 0;
    Class<?> localClass = null;
    Field[] arrayOfField = paramClass.getDeclaredFields();
    if (arrayOfField != null)
    {
      int k = arrayOfField.length;
      int i = 0;
      if (i >= k)
      {
        paramClass = localClass;
        label30: localClass = paramClass;
        if (paramClass == null)
        {
          k = arrayOfField.length;
          i = 0;
          label43: if (i < k)
            break label94;
          localClass = paramClass;
        }
        label51: if (localClass == null)
        {
          k = arrayOfField.length;
          i = j;
        }
      }
      while (true)
      {
        if (i >= k)
        {
          return localClass;
          paramClass = arrayOfField[i];
          if (paramClass.getAnnotation(Id.class) != null)
            break label30;
          i += 1;
          break;
          label94: localClass = arrayOfField[i];
          if ("_id".equals(localClass.getName()))
            break label51;
          i += 1;
          break label43;
        }
        paramClass = arrayOfField[i];
        if ("id".equals(paramClass.getName()))
          return paramClass;
        i += 1;
      }
    }
    throw new RuntimeException("this model[" + paramClass + "] has no field");
  }

  public static String getPrimaryKeyFieldName(Class<?> paramClass)
  {
    paramClass = getPrimaryKeyField(paramClass);
    if (paramClass == null)
      return null;
    return paramClass.getName();
  }

  public static Object getPrimaryKeyValue(Object paramObject)
  {
    return FieldUtils.getFieldValue(paramObject, getPrimaryKeyField(paramObject.getClass()));
  }

  public static List<Property> getPropertyList(Class<?> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      int j;
      int i;
      try
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        String str = getPrimaryKeyFieldName(paramClass);
        j = arrayOfField.length;
        i = 0;
        break label171;
        Field localField = arrayOfField[i];
        if ((!FieldUtils.isTransient(localField)) && (FieldUtils.isBaseDateType(localField)) && (!localField.getName().equals(str)))
        {
          Property localProperty = new Property();
          localProperty.setColumn(FieldUtils.getColumnByField(localField));
          localProperty.setFieldName(localField.getName());
          localProperty.setDataType(localField.getType());
          localProperty.setDefaultValue(FieldUtils.getPropertyDefaultValue(localField));
          localProperty.setSet(FieldUtils.getFieldSetMethod(paramClass, localField));
          localProperty.setGet(FieldUtils.getFieldGetMethod(paramClass, localField));
          localProperty.setField(localField);
          localArrayList.add(localProperty);
        }
      }
      catch (Exception paramClass)
      {
        throw new RuntimeException(paramClass.getMessage(), paramClass);
      }
      label171: 
      while (i >= j)
      {
        return localArrayList;
        i += 1;
      }
    }
  }

  public static String getTableName(Class<?> paramClass)
  {
    Table localTable = (Table)paramClass.getAnnotation(Table.class);
    if ((localTable == null) || (localTable.name().trim().length() == 0))
      return paramClass.getName().replace('.', '_');
    return localTable.name();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.utils.ClassUtils
 * JD-Core Version:    0.6.2
 */