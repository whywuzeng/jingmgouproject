package net.tsz.afinal.db.table;

import java.text.SimpleDateFormat;
import net.tsz.afinal.utils.FieldUtils;

public class KeyValue
{
  private String key;
  private Object value;

  public KeyValue()
  {
  }

  public KeyValue(String paramString, Object paramObject)
  {
    this.key = paramString;
    this.value = paramObject;
  }

  public String getKey()
  {
    return this.key;
  }

  public Object getValue()
  {
    if (((this.value instanceof java.util.Date)) || ((this.value instanceof java.sql.Date)))
      return FieldUtils.SDF.format(this.value);
    return this.value;
  }

  public void setKey(String paramString)
  {
    this.key = paramString;
  }

  public void setValue(Object paramObject)
  {
    this.value = paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.table.KeyValue
 * JD-Core Version:    0.6.2
 */