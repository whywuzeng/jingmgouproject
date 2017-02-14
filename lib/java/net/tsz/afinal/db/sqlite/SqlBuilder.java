package net.tsz.afinal.db.sqlite;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.tsz.afinal.db.table.Id;
import net.tsz.afinal.db.table.KeyValue;
import net.tsz.afinal.db.table.ManyToOne;
import net.tsz.afinal.db.table.Property;
import net.tsz.afinal.db.table.TableInfo;
import net.tsz.afinal.exception.DbException;

public class SqlBuilder
{
  public static String buildDeleteSql(Class<?> paramClass, String paramString)
  {
    paramClass = new StringBuffer(getDeleteSqlBytableName(TableInfo.get(paramClass).getTableName()));
    if (!TextUtils.isEmpty(paramString))
    {
      paramClass.append(" WHERE ");
      paramClass.append(paramString);
    }
    return paramClass.toString();
  }

  public static SqlInfo buildDeleteSql(Class<?> paramClass, Object paramObject)
  {
    Object localObject = TableInfo.get(paramClass);
    paramClass = ((TableInfo)localObject).getId();
    if (paramObject == null)
      throw new DbException("getDeleteSQL:idValue is null");
    localObject = new StringBuffer(getDeleteSqlBytableName(((TableInfo)localObject).getTableName()));
    ((StringBuffer)localObject).append(" WHERE ").append(paramClass.getColumn()).append("=?");
    paramClass = new SqlInfo();
    paramClass.setSql(((StringBuffer)localObject).toString());
    paramClass.addValue(paramObject);
    return paramClass;
  }

  public static SqlInfo buildDeleteSql(Object paramObject)
  {
    TableInfo localTableInfo = TableInfo.get(paramObject.getClass());
    Object localObject2 = localTableInfo.getId();
    Object localObject1 = ((Id)localObject2).getValue(paramObject);
    if (localObject1 == null)
      throw new DbException("getDeleteSQL:" + paramObject.getClass() + " id value is null");
    paramObject = new StringBuffer(getDeleteSqlBytableName(localTableInfo.getTableName()));
    paramObject.append(" WHERE ").append(((Id)localObject2).getColumn()).append("=?");
    localObject2 = new SqlInfo();
    ((SqlInfo)localObject2).setSql(paramObject.toString());
    ((SqlInfo)localObject2).addValue(localObject1);
    return localObject2;
  }

  public static SqlInfo buildInsertSql(Object paramObject)
  {
    List localList = getSaveKeyValueListByEntity(paramObject);
    StringBuffer localStringBuffer = new StringBuffer();
    KeyValue localKeyValue = null;
    Object localObject = localKeyValue;
    int j;
    int i;
    if (localList != null)
    {
      localObject = localKeyValue;
      if (localList.size() > 0)
      {
        localObject = new SqlInfo();
        localStringBuffer.append("INSERT INTO ");
        localStringBuffer.append(TableInfo.get(paramObject.getClass()).getTableName());
        localStringBuffer.append(" (");
        paramObject = localList.iterator();
        if (paramObject.hasNext())
          break label164;
        localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
        localStringBuffer.append(") VALUES ( ");
        j = localList.size();
        i = 0;
      }
    }
    while (true)
    {
      if (i >= j)
      {
        localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
        localStringBuffer.append(")");
        ((SqlInfo)localObject).setSql(localStringBuffer.toString());
        return localObject;
        label164: localKeyValue = (KeyValue)paramObject.next();
        localStringBuffer.append(localKeyValue.getKey()).append(",");
        ((SqlInfo)localObject).addValue(localKeyValue.getValue());
        break;
      }
      localStringBuffer.append("?,");
      i += 1;
    }
  }

  public static String getCreatTableSQL(Class<?> paramClass)
  {
    Object localObject1 = TableInfo.get(paramClass);
    Object localObject2 = ((TableInfo)localObject1).getId();
    paramClass = new StringBuffer();
    paramClass.append("CREATE TABLE IF NOT EXISTS ");
    paramClass.append(((TableInfo)localObject1).getTableName());
    paramClass.append(" ( ");
    Object localObject3 = ((Id)localObject2).getDataType();
    if ((localObject3 == Integer.TYPE) || (localObject3 == Integer.class) || (localObject3 == Long.TYPE) || (localObject3 == Long.class))
    {
      paramClass.append(((Id)localObject2).getColumn()).append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
      localObject2 = ((TableInfo)localObject1).propertyMap.values().iterator();
      if (((Iterator)localObject2).hasNext())
        break label170;
      localObject1 = ((TableInfo)localObject1).manyToOneMap.values().iterator();
    }
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        paramClass.deleteCharAt(paramClass.length() - 1);
        paramClass.append(" )");
        return paramClass.toString();
        paramClass.append(((Id)localObject2).getColumn()).append(" TEXT PRIMARY KEY,");
        break;
        label170: localObject3 = (Property)((Iterator)localObject2).next();
        paramClass.append(((Property)localObject3).getColumn());
        localObject3 = ((Property)localObject3).getDataType();
        if ((localObject3 == Integer.TYPE) || (localObject3 == Integer.class) || (localObject3 == Long.TYPE) || (localObject3 == Long.class))
          paramClass.append(" INTEGER");
        while (true)
        {
          paramClass.append(",");
          break;
          if ((localObject3 == Float.TYPE) || (localObject3 == Float.class) || (localObject3 == Double.TYPE) || (localObject3 == Double.class))
            paramClass.append(" REAL");
          else if ((localObject3 == Boolean.TYPE) || (localObject3 == Boolean.class))
            paramClass.append(" NUMERIC");
        }
      }
      paramClass.append(((ManyToOne)((Iterator)localObject1).next()).getColumn()).append(" INTEGER").append(",");
    }
  }

  private static String getDeleteSqlBytableName(String paramString)
  {
    return "DELETE FROM " + paramString;
  }

  private static String getPropertyStrSql(String paramString, Object paramObject)
  {
    paramString = new StringBuffer(paramString).append("=");
    if (((paramObject instanceof String)) || ((paramObject instanceof java.util.Date)) || ((paramObject instanceof java.sql.Date)))
      paramString.append("'").append(paramObject).append("'");
    while (true)
    {
      return paramString.toString();
      paramString.append(paramObject);
    }
  }

  public static List<KeyValue> getSaveKeyValueListByEntity(Object paramObject)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = TableInfo.get(paramObject.getClass());
    Object localObject2 = ((TableInfo)localObject1).getId().getValue(paramObject);
    if ((!(localObject2 instanceof Integer)) && ((localObject2 instanceof String)) && (localObject2 != null))
      localArrayList.add(new KeyValue(((TableInfo)localObject1).getId().getColumn(), localObject2));
    localObject2 = ((TableInfo)localObject1).propertyMap.values().iterator();
    if (!((Iterator)localObject2).hasNext())
      localObject1 = ((TableInfo)localObject1).manyToOneMap.values().iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        return localArrayList;
        KeyValue localKeyValue = property2KeyValue((Property)((Iterator)localObject2).next(), paramObject);
        if (localKeyValue == null)
          break;
        localArrayList.add(localKeyValue);
        break;
      }
      localObject2 = manyToOne2KeyValue((ManyToOne)((Iterator)localObject1).next(), paramObject);
      if (localObject2 != null)
        localArrayList.add(localObject2);
    }
  }

  public static String getSelectSQL(Class<?> paramClass)
  {
    return getSelectSqlByTableName(TableInfo.get(paramClass).getTableName());
  }

  public static String getSelectSQL(Class<?> paramClass, Object paramObject)
  {
    paramClass = TableInfo.get(paramClass);
    StringBuffer localStringBuffer = new StringBuffer(getSelectSqlByTableName(paramClass.getTableName()));
    localStringBuffer.append(" WHERE ");
    localStringBuffer.append(getPropertyStrSql(paramClass.getId().getColumn(), paramObject));
    return localStringBuffer.toString();
  }

  public static String getSelectSQLByWhere(Class<?> paramClass, String paramString)
  {
    paramClass = new StringBuffer(getSelectSqlByTableName(TableInfo.get(paramClass).getTableName()));
    if (!TextUtils.isEmpty(paramString))
      paramClass.append(" WHERE ").append(paramString);
    return paramClass.toString();
  }

  public static SqlInfo getSelectSqlAsSqlInfo(Class<?> paramClass, Object paramObject)
  {
    Object localObject = TableInfo.get(paramClass);
    paramClass = new StringBuffer(getSelectSqlByTableName(((TableInfo)localObject).getTableName()));
    paramClass.append(" WHERE ").append(((TableInfo)localObject).getId().getColumn()).append("=?");
    localObject = new SqlInfo();
    ((SqlInfo)localObject).setSql(paramClass.toString());
    ((SqlInfo)localObject).addValue(paramObject);
    return localObject;
  }

  private static String getSelectSqlByTableName(String paramString)
  {
    return "SELECT * FROM " + paramString;
  }

  public static SqlInfo getUpdateSqlAsSqlInfo(Object paramObject)
  {
    TableInfo localTableInfo = TableInfo.get(paramObject.getClass());
    Object localObject1 = localTableInfo.getId().getValue(paramObject);
    if (localObject1 == null)
      throw new DbException("this entity[" + paramObject.getClass() + "]'s id value is null");
    Object localObject2 = new ArrayList();
    Object localObject3 = localTableInfo.propertyMap.values().iterator();
    if (!((Iterator)localObject3).hasNext())
      localObject3 = localTableInfo.manyToOneMap.values().iterator();
    KeyValue localKeyValue;
    while (true)
    {
      if (!((Iterator)localObject3).hasNext())
      {
        if ((localObject2 != null) && (((List)localObject2).size() != 0))
          break label192;
        return null;
        localKeyValue = property2KeyValue((Property)((Iterator)localObject3).next(), paramObject);
        if (localKeyValue == null)
          break;
        ((List)localObject2).add(localKeyValue);
        break;
      }
      localKeyValue = manyToOne2KeyValue((ManyToOne)((Iterator)localObject3).next(), paramObject);
      if (localKeyValue != null)
        ((List)localObject2).add(localKeyValue);
    }
    label192: paramObject = new SqlInfo();
    localObject3 = new StringBuffer("UPDATE ");
    ((StringBuffer)localObject3).append(localTableInfo.getTableName());
    ((StringBuffer)localObject3).append(" SET ");
    localObject2 = ((List)localObject2).iterator();
    while (true)
    {
      if (!((Iterator)localObject2).hasNext())
      {
        ((StringBuffer)localObject3).deleteCharAt(((StringBuffer)localObject3).length() - 1);
        ((StringBuffer)localObject3).append(" WHERE ").append(localTableInfo.getId().getColumn()).append("=?");
        paramObject.addValue(localObject1);
        paramObject.setSql(((StringBuffer)localObject3).toString());
        return paramObject;
      }
      localKeyValue = (KeyValue)((Iterator)localObject2).next();
      ((StringBuffer)localObject3).append(localKeyValue.getKey()).append("=?,");
      paramObject.addValue(localKeyValue.getValue());
    }
  }

  public static SqlInfo getUpdateSqlAsSqlInfo(Object paramObject, String paramString)
  {
    Object localObject1 = TableInfo.get(paramObject.getClass());
    Object localObject2 = new ArrayList();
    Object localObject3 = ((TableInfo)localObject1).propertyMap.values().iterator();
    if (!((Iterator)localObject3).hasNext())
      localObject3 = ((TableInfo)localObject1).manyToOneMap.values().iterator();
    while (true)
    {
      if (!((Iterator)localObject3).hasNext())
      {
        if ((localObject2 != null) && (((List)localObject2).size() != 0))
          break label177;
        throw new DbException("this entity[" + paramObject.getClass() + "] has no property");
        localKeyValue = property2KeyValue((Property)((Iterator)localObject3).next(), paramObject);
        if (localKeyValue == null)
          break;
        ((List)localObject2).add(localKeyValue);
        break;
      }
      KeyValue localKeyValue = manyToOne2KeyValue((ManyToOne)((Iterator)localObject3).next(), paramObject);
      if (localKeyValue != null)
        ((List)localObject2).add(localKeyValue);
    }
    label177: paramObject = new SqlInfo();
    localObject3 = new StringBuffer("UPDATE ");
    ((StringBuffer)localObject3).append(((TableInfo)localObject1).getTableName());
    ((StringBuffer)localObject3).append(" SET ");
    localObject1 = ((List)localObject2).iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        ((StringBuffer)localObject3).deleteCharAt(((StringBuffer)localObject3).length() - 1);
        if (!TextUtils.isEmpty(paramString))
          ((StringBuffer)localObject3).append(" WHERE ").append(paramString);
        paramObject.setSql(((StringBuffer)localObject3).toString());
        return paramObject;
      }
      localObject2 = (KeyValue)((Iterator)localObject1).next();
      ((StringBuffer)localObject3).append(((KeyValue)localObject2).getKey()).append("=?,");
      paramObject.addValue(((KeyValue)localObject2).getValue());
    }
  }

  private static KeyValue manyToOne2KeyValue(ManyToOne paramManyToOne, Object paramObject)
  {
    Object localObject1 = null;
    String str = paramManyToOne.getColumn();
    Object localObject2 = paramManyToOne.getValue(paramObject);
    paramObject = localObject1;
    if (localObject2 != null)
      if (localObject2.getClass() != ManyToOneLazyLoader.class)
        break label78;
    label78: for (paramManyToOne = TableInfo.get(paramManyToOne.getManyClass()).getId().getValue(((ManyToOneLazyLoader)localObject2).get()); ; paramManyToOne = TableInfo.get(localObject2.getClass()).getId().getValue(localObject2))
    {
      paramObject = localObject1;
      if (str != null)
      {
        paramObject = localObject1;
        if (paramManyToOne != null)
          paramObject = new KeyValue(str, paramManyToOne);
      }
      return paramObject;
    }
  }

  private static KeyValue property2KeyValue(Property paramProperty, Object paramObject)
  {
    Object localObject = null;
    String str = paramProperty.getColumn();
    paramObject = paramProperty.getValue(paramObject);
    if (paramObject != null)
      paramObject = new KeyValue(str, paramObject);
    do
    {
      do
      {
        return paramObject;
        paramObject = localObject;
      }
      while (paramProperty.getDefaultValue() == null);
      paramObject = localObject;
    }
    while (paramProperty.getDefaultValue().trim().length() == 0);
    return new KeyValue(str, paramProperty.getDefaultValue());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.sqlite.SqlBuilder
 * JD-Core Version:    0.6.2
 */