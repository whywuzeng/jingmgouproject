package net.tsz.afinal.db.table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.tsz.afinal.utils.FieldUtils;

public class Property
{
  private String column;
  private Class<?> dataType;
  private String defaultValue;
  private Field field;
  private String fieldName;
  private Method get;
  private Method set;

  public String getColumn()
  {
    return this.column;
  }

  public Class<?> getDataType()
  {
    return this.dataType;
  }

  public String getDefaultValue()
  {
    return this.defaultValue;
  }

  public Field getField()
  {
    return this.field;
  }

  public String getFieldName()
  {
    return this.fieldName;
  }

  public Method getGet()
  {
    return this.get;
  }

  public Method getSet()
  {
    return this.set;
  }

  public <T> T getValue(Object paramObject)
  {
    if ((paramObject != null) && (this.get != null))
      try
      {
        paramObject = this.get.invoke(paramObject, new Object[0]);
        return paramObject;
      }
      catch (Exception paramObject)
      {
        paramObject.printStackTrace();
      }
    return null;
  }

  public void setColumn(String paramString)
  {
    this.column = paramString;
  }

  public void setDataType(Class<?> paramClass)
  {
    this.dataType = paramClass;
  }

  public void setDefaultValue(String paramString)
  {
    this.defaultValue = paramString;
  }

  public void setField(Field paramField)
  {
    this.field = paramField;
  }

  public void setFieldName(String paramString)
  {
    this.fieldName = paramString;
  }

  public void setGet(Method paramMethod)
  {
    this.get = paramMethod;
  }

  public void setSet(Method paramMethod)
  {
    this.set = paramMethod;
  }

  public void setValue(Object paramObject1, Object paramObject2)
  {
    Method localMethod1 = null;
    if ((this.set != null) && (paramObject2 != null))
    {
      while (true)
      {
        try
        {
          if (this.dataType == String.class)
          {
            this.set.invoke(paramObject1, new Object[] { paramObject2.toString() });
            return;
          }
          if ((this.dataType != Integer.TYPE) && (this.dataType != Integer.class))
            break;
          localMethod1 = this.set;
          if (paramObject2 == null)
          {
            throw new NullPointerException();
            localMethod1.invoke(paramObject1, new Object[] { Integer.valueOf(i) });
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
      if ((this.dataType == Float.TYPE) || (this.dataType == Float.class))
      {
        localMethod1 = this.set;
        if (paramObject2 == null)
          throw new NullPointerException();
        while (true)
        {
          Object localObject2;
          localMethod1.invoke(paramObject1, new Object[] { Float.valueOf(localObject2) });
          return;
          float f = Float.parseFloat(paramObject2.toString());
        }
      }
      if ((this.dataType == Double.TYPE) || (this.dataType == Double.class))
      {
        localMethod1 = this.set;
        if (paramObject2 == null)
          throw new NullPointerException();
        while (true)
        {
          Object localObject1;
          localMethod1.invoke(paramObject1, new Object[] { Double.valueOf(localObject1) });
          return;
          double d = Double.parseDouble(paramObject2.toString());
        }
      }
      if ((this.dataType == Long.TYPE) || (this.dataType == Long.class))
      {
        localMethod1 = this.set;
        if (paramObject2 == null)
          throw new NullPointerException();
        while (true)
        {
          Object localObject3;
          localMethod1.invoke(paramObject1, new Object[] { Long.valueOf(localObject3) });
          return;
          long l = Long.parseLong(paramObject2.toString());
        }
      }
      if ((this.dataType == java.util.Date.class) || (this.dataType == java.sql.Date.class))
      {
        Method localMethod2 = this.set;
        if (paramObject2 == null);
        for (paramObject2 = localMethod1; ; paramObject2 = FieldUtils.stringToDateTime(paramObject2.toString()))
        {
          localMethod2.invoke(paramObject1, new Object[] { paramObject2 });
          return;
        }
      }
      if ((this.dataType == Boolean.TYPE) || (this.dataType == Boolean.class))
      {
        localMethod1 = this.set;
        if (paramObject2 == null)
          throw new NullPointerException();
        while (true)
        {
          localMethod1.invoke(paramObject1, new Object[] { Boolean.valueOf(bool) });
          return;
          boolean bool = "1".equals(paramObject2.toString());
        }
      }
      this.set.invoke(paramObject1, new Object[] { paramObject2 });
      return;
    }
    try
    {
      this.field.setAccessible(true);
      this.field.set(paramObject1, paramObject2);
      return;
    }
    catch (Exception paramObject1)
    {
      paramObject1.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.table.Property
 * JD-Core Version:    0.6.2
 */