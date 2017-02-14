package net.tsz.afinal.db.sqlite;

import java.util.LinkedList;

public class SqlInfo
{
  private LinkedList<Object> bindArgs;
  private String sql;

  public void addValue(Object paramObject)
  {
    if (this.bindArgs == null)
      this.bindArgs = new LinkedList();
    this.bindArgs.add(paramObject);
  }

  public LinkedList<Object> getBindArgs()
  {
    return this.bindArgs;
  }

  public Object[] getBindArgsAsArray()
  {
    if (this.bindArgs != null)
      return this.bindArgs.toArray();
    return null;
  }

  public String[] getBindArgsAsStringArray()
  {
    if (this.bindArgs != null)
    {
      String[] arrayOfString = new String[this.bindArgs.size()];
      int i = 0;
      while (true)
      {
        if (i >= this.bindArgs.size())
          return arrayOfString;
        arrayOfString[i] = this.bindArgs.get(i).toString();
        i += 1;
      }
    }
    return null;
  }

  public String getSql()
  {
    return this.sql;
  }

  public void setBindArgs(LinkedList<Object> paramLinkedList)
  {
    this.bindArgs = paramLinkedList;
  }

  public void setSql(String paramString)
  {
    this.sql = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.sqlite.SqlInfo
 * JD-Core Version:    0.6.2
 */