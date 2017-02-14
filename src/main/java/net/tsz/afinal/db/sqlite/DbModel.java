package net.tsz.afinal.db.sqlite;

import java.util.HashMap;

public class DbModel
{
  private HashMap<String, Object> dataMap = new HashMap();

  public Object get(String paramString)
  {
    return this.dataMap.get(paramString);
  }

  public boolean getBoolean(String paramString)
  {
    return Boolean.valueOf(getString(paramString)).booleanValue();
  }

  public HashMap<String, Object> getDataMap()
  {
    return this.dataMap;
  }

  public double getDouble(String paramString)
  {
    return Double.valueOf(getString(paramString)).doubleValue();
  }

  public float getFloat(String paramString)
  {
    return Float.valueOf(getString(paramString)).floatValue();
  }

  public int getInt(String paramString)
  {
    return Integer.valueOf(getString(paramString)).intValue();
  }

  public long getLong(String paramString)
  {
    return Long.valueOf(getString(paramString)).longValue();
  }

  public String getString(String paramString)
  {
    return String.valueOf(get(paramString));
  }

  public void set(String paramString, Object paramObject)
  {
    this.dataMap.put(paramString, paramObject);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.sqlite.DbModel
 * JD-Core Version:    0.6.2
 */