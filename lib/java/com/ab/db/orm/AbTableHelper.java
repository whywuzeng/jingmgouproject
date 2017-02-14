package com.ab.db.orm;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
import com.ab.db.orm.annotation.Relations;
import com.ab.db.orm.annotation.Table;
import com.ab.util.AbStrUtil;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AbTableHelper
{
  private static final String TAG = "AbTableHelper";

  public static <T> void createTable(SQLiteDatabase paramSQLiteDatabase, Class<T> paramClass)
  {
    String str = "";
    if (paramClass.isAnnotationPresent(Table.class))
      str = ((Table)paramClass.getAnnotation(Table.class)).name();
    if (AbStrUtil.isEmpty(str))
    {
      Log.d("AbTableHelper", "想要映射的实体[" + paramClass.getName() + "],未注解@Table(name=\"?\"),被跳过");
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CREATE TABLE ").append(str).append(" (");
    Iterator localIterator = joinFieldsOnlyColumn(paramClass.getDeclaredFields(), paramClass.getSuperclass().getDeclaredFields()).iterator();
    Field localField;
    do
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.delete(localStringBuilder.length() - 2, localStringBuilder.length() - 1);
        localStringBuilder.append(")");
        paramClass = localStringBuilder.toString();
        Log.d("AbTableHelper", "create table [" + str + "]: " + paramClass);
        paramSQLiteDatabase.execSQL(paramClass);
        return;
      }
      localField = (Field)localIterator.next();
    }
    while (!localField.isAnnotationPresent(Column.class));
    Column localColumn = (Column)localField.getAnnotation(Column.class);
    if (localColumn.type().equals(""))
    {
      paramClass = getColumnType(localField.getType());
      label244: localStringBuilder.append(localColumn.name() + " " + paramClass);
      if (localColumn.length() != 0)
        localStringBuilder.append("(" + localColumn.length() + ")");
      if ((!localField.isAnnotationPresent(Id.class)) || ((localField.getType() != Integer.TYPE) && (localField.getType() != Integer.class)))
        break label379;
      localStringBuilder.append(" primary key autoincrement");
    }
    while (true)
    {
      localStringBuilder.append(", ");
      break;
      paramClass = localColumn.type();
      break label244;
      label379: if (localField.isAnnotationPresent(Id.class))
        localStringBuilder.append(" primary key");
    }
  }

  public static <T> void createTablesByClasses(SQLiteDatabase paramSQLiteDatabase, Class<?>[] paramArrayOfClass)
  {
    int j = paramArrayOfClass.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      createTable(paramSQLiteDatabase, paramArrayOfClass[i]);
      i += 1;
    }
  }

  public static <T> void dropTable(SQLiteDatabase paramSQLiteDatabase, Class<T> paramClass)
  {
    String str = "";
    if (paramClass.isAnnotationPresent(Table.class))
      str = ((Table)paramClass.getAnnotation(Table.class)).name();
    paramClass = "DROP TABLE IF EXISTS " + str;
    Log.d("AbTableHelper", "dropTable[" + str + "]:" + paramClass);
    paramSQLiteDatabase.execSQL(paramClass);
  }

  public static <T> void dropTablesByClasses(SQLiteDatabase paramSQLiteDatabase, Class<?>[] paramArrayOfClass)
  {
    int j = paramArrayOfClass.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      dropTable(paramSQLiteDatabase, paramArrayOfClass[i]);
      i += 1;
    }
  }

  private static String getColumnType(Class<?> paramClass)
  {
    if (String.class == paramClass)
      return "TEXT";
    if ((Integer.TYPE == paramClass) || (Integer.class == paramClass))
      return "INTEGER";
    if ((Long.TYPE == paramClass) || (Long.class == paramClass))
      return "BIGINT";
    if ((Float.TYPE == paramClass) || (Float.class == paramClass))
      return "FLOAT";
    if ((Short.TYPE == paramClass) || (Short.class == paramClass))
      return "INT";
    if ((Double.TYPE == paramClass) || (Double.class == paramClass))
      return "DOUBLE";
    if (Blob.class == paramClass)
      return "BLOB";
    return "TEXT";
  }

  public static List<Field> joinFields(Field[] paramArrayOfField1, Field[] paramArrayOfField2)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    int j = paramArrayOfField1.length;
    int i = 0;
    if (i >= j)
    {
      j = paramArrayOfField2.length;
      i = 0;
      if (i < j)
        break label146;
      paramArrayOfField1 = new ArrayList();
      paramArrayOfField2 = localLinkedHashMap.keySet().iterator();
    }
    while (true)
    {
      if (!paramArrayOfField2.hasNext())
      {
        return paramArrayOfField1;
        localObject = paramArrayOfField1[i];
        if (((Field)localObject).isAnnotationPresent(Column.class))
          localLinkedHashMap.put(((Column)((Field)localObject).getAnnotation(Column.class)).name(), localObject);
        while (true)
        {
          i += 1;
          break;
          if (((Field)localObject).isAnnotationPresent(Relations.class))
            localLinkedHashMap.put(((Relations)((Field)localObject).getAnnotation(Relations.class)).name(), localObject);
        }
        label146: paramArrayOfField1 = paramArrayOfField2[i];
        if (paramArrayOfField1.isAnnotationPresent(Column.class))
        {
          localObject = (Column)paramArrayOfField1.getAnnotation(Column.class);
          if (!localLinkedHashMap.containsKey(((Column)localObject).name()))
            localLinkedHashMap.put(((Column)localObject).name(), paramArrayOfField1);
        }
        while (true)
        {
          i += 1;
          break;
          if (paramArrayOfField1.isAnnotationPresent(Relations.class))
          {
            localObject = (Relations)paramArrayOfField1.getAnnotation(Relations.class);
            if (!localLinkedHashMap.containsKey(((Relations)localObject).name()))
              localLinkedHashMap.put(((Relations)localObject).name(), paramArrayOfField1);
          }
        }
      }
      Object localObject = (Field)localLinkedHashMap.get((String)paramArrayOfField2.next());
      if (((Field)localObject).isAnnotationPresent(Id.class))
        paramArrayOfField1.add(0, localObject);
      else
        paramArrayOfField1.add(localObject);
    }
  }

  public static List<Field> joinFieldsOnlyColumn(Field[] paramArrayOfField1, Field[] paramArrayOfField2)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    int j = paramArrayOfField1.length;
    int i = 0;
    if (i >= j)
    {
      j = paramArrayOfField2.length;
      i = 0;
      if (i < j)
        break label111;
      paramArrayOfField1 = new ArrayList();
      paramArrayOfField2 = localLinkedHashMap.keySet().iterator();
    }
    while (true)
    {
      if (!paramArrayOfField2.hasNext())
      {
        return paramArrayOfField1;
        localObject = paramArrayOfField1[i];
        if (!((Field)localObject).isAnnotationPresent(Column.class));
        while (true)
        {
          i += 1;
          break;
          localLinkedHashMap.put(((Column)((Field)localObject).getAnnotation(Column.class)).name(), localObject);
        }
        label111: paramArrayOfField1 = paramArrayOfField2[i];
        if (!paramArrayOfField1.isAnnotationPresent(Column.class));
        while (true)
        {
          i += 1;
          break;
          localObject = (Column)paramArrayOfField1.getAnnotation(Column.class);
          if (!localLinkedHashMap.containsKey(((Column)localObject).name()))
            localLinkedHashMap.put(((Column)localObject).name(), paramArrayOfField1);
        }
      }
      Object localObject = (Field)localLinkedHashMap.get((String)paramArrayOfField2.next());
      if (((Field)localObject).isAnnotationPresent(Id.class))
        paramArrayOfField1.add(0, localObject);
      else
        paramArrayOfField1.add(localObject);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.orm.AbTableHelper
 * JD-Core Version:    0.6.2
 */