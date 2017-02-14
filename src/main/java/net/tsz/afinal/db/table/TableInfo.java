package net.tsz.afinal.db.table;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.tsz.afinal.exception.DbException;
import net.tsz.afinal.utils.ClassUtils;
import net.tsz.afinal.utils.FieldUtils;

public class TableInfo
{
  private static final HashMap<String, TableInfo> tableInfoMap = new HashMap();
  private boolean checkDatabese;
  private String className;
  private Id id;
  public final HashMap<String, ManyToOne> manyToOneMap = new HashMap();
  public final HashMap<String, OneToMany> oneToManyMap = new HashMap();
  public final HashMap<String, Property> propertyMap = new HashMap();
  private String tableName;

  public static TableInfo get(Class<?> paramClass)
  {
    if (paramClass == null)
      throw new DbException("table info get error,because the clazz is null");
    Object localObject2 = (TableInfo)tableInfoMap.get(paramClass.getName());
    Object localObject1 = localObject2;
    Object localObject3;
    if (localObject2 == null)
    {
      localObject1 = new TableInfo();
      ((TableInfo)localObject1).setTableName(ClassUtils.getTableName(paramClass));
      ((TableInfo)localObject1).setClassName(paramClass.getName());
      localObject2 = ClassUtils.getPrimaryKeyField(paramClass);
      if (localObject2 == null)
        break label242;
      localObject3 = new Id();
      ((Id)localObject3).setColumn(FieldUtils.getColumnByField((Field)localObject2));
      ((Id)localObject3).setFieldName(((Field)localObject2).getName());
      ((Id)localObject3).setSet(FieldUtils.getFieldSetMethod(paramClass, (Field)localObject2));
      ((Id)localObject3).setGet(FieldUtils.getFieldGetMethod(paramClass, (Field)localObject2));
      ((Id)localObject3).setDataType(((Field)localObject2).getType());
      ((TableInfo)localObject1).setId((Id)localObject3);
      localObject2 = ClassUtils.getPropertyList(paramClass);
      if (localObject2 != null)
      {
        localObject2 = ((List)localObject2).iterator();
        if (((Iterator)localObject2).hasNext())
          break label271;
      }
      localObject2 = ClassUtils.getManyToOneList(paramClass);
      if (localObject2 != null)
      {
        localObject2 = ((List)localObject2).iterator();
        label163: if (((Iterator)localObject2).hasNext())
          break label301;
      }
      localObject2 = ClassUtils.getOneToManyList(paramClass);
      if (localObject2 != null)
        localObject2 = ((List)localObject2).iterator();
    }
    while (true)
    {
      if (!((Iterator)localObject2).hasNext())
      {
        tableInfoMap.put(paramClass.getName(), localObject1);
        if (localObject1 != null)
          break label361;
        throw new DbException("the class[" + paramClass + "]'s table is null");
        label242: throw new DbException("the class[" + paramClass + "]'s idField is null , \n you can define _id,id property or use annotation @id to solution this exception");
        label271: localObject3 = (Property)((Iterator)localObject2).next();
        if (localObject3 == null)
          break;
        ((TableInfo)localObject1).propertyMap.put(((Property)localObject3).getColumn(), localObject3);
        break;
        label301: localObject3 = (ManyToOne)((Iterator)localObject2).next();
        if (localObject3 == null)
          break label163;
        ((TableInfo)localObject1).manyToOneMap.put(((ManyToOne)localObject3).getColumn(), localObject3);
        break label163;
      }
      localObject3 = (OneToMany)((Iterator)localObject2).next();
      if (localObject3 != null)
        ((TableInfo)localObject1).oneToManyMap.put(((OneToMany)localObject3).getColumn(), localObject3);
    }
    label361: return localObject1;
  }

  public static TableInfo get(String paramString)
  {
    try
    {
      paramString = get(Class.forName(paramString));
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public String getClassName()
  {
    return this.className;
  }

  public Id getId()
  {
    return this.id;
  }

  public String getTableName()
  {
    return this.tableName;
  }

  public boolean isCheckDatabese()
  {
    return this.checkDatabese;
  }

  public void setCheckDatabese(boolean paramBoolean)
  {
    this.checkDatabese = paramBoolean;
  }

  public void setClassName(String paramString)
  {
    this.className = paramString;
  }

  public void setId(Id paramId)
  {
    this.id = paramId;
  }

  public void setTableName(String paramString)
  {
    this.tableName = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.table.TableInfo
 * JD-Core Version:    0.6.2
 */