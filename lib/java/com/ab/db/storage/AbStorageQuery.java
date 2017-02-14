package com.ab.db.storage;

import com.ab.util.AbStrUtil;
import java.io.PrintStream;
import java.util.ArrayList;

public class AbStorageQuery
{
  private String groupBy = null;
  private String having = null;
  private int limit = -1;
  private int offset = -1;
  private String orderBy = null;
  private ArrayList<String> whereArgs = null;
  private String whereClause = null;

  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new AbStorageQuery();
    paramArrayOfString.equals("u_id", "1");
    paramArrayOfString.equals("face_u_id", "2");
    AbStorageQuery localAbStorageQuery = new AbStorageQuery();
    localAbStorageQuery.equals("face_u_id", "3");
    localAbStorageQuery.equals("u_id", "4");
    printLog(paramArrayOfString.or(localAbStorageQuery));
    paramArrayOfString = new AbStorageQuery();
    localAbStorageQuery = new AbStorageQuery();
    paramArrayOfString.equals("u_id", "1");
    localAbStorageQuery.equals("face_u_id", "3");
    printLog(paramArrayOfString.and(localAbStorageQuery));
    paramArrayOfString = new AbStorageQuery();
    localAbStorageQuery = new AbStorageQuery();
    paramArrayOfString.lessThan("u_id", "1");
    localAbStorageQuery.greaterThanEqualTo("face_u_id", "3");
    printLog(paramArrayOfString.and(localAbStorageQuery));
    paramArrayOfString = new AbStorageQuery();
    paramArrayOfString.in("name", new String[] { "1", "2", "3", "4" });
    paramArrayOfString.addSort("time", SortOrder.ASC);
    paramArrayOfString.addSort("state", SortOrder.DESC);
    printLog(paramArrayOfString);
  }

  public static void printLog(AbStorageQuery paramAbStorageQuery)
  {
    System.out.println("where " + paramAbStorageQuery.getWhereClause());
    if (!AbStrUtil.isEmpty(paramAbStorageQuery.getOrderBy()))
      System.out.println("order by " + paramAbStorageQuery.getOrderBy());
    System.out.print("参数:[");
    int i = 0;
    while (true)
    {
      if (i >= paramAbStorageQuery.getWhereArgs().length)
      {
        System.out.print("]");
        System.out.println(" ");
        System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－");
        return;
      }
      if (i != 0)
        System.out.print(",");
      System.out.print(paramAbStorageQuery.getWhereArgs()[i]);
      i += 1;
    }
  }

  public AbStorageQuery addSort(String paramString, SortOrder paramSortOrder)
  {
    if (AbStrUtil.isEmpty(this.orderBy))
    {
      this.orderBy = (" " + paramString + " " + paramSortOrder);
      return this;
    }
    this.orderBy = (this.orderBy + " , " + paramString + " " + paramSortOrder);
    return this;
  }

  public AbStorageQuery and(AbStorageQuery paramAbStorageQuery)
  {
    this.whereClause = (this.whereClause + " and (" + paramAbStorageQuery.getWhereClause() + ")");
    paramAbStorageQuery = paramAbStorageQuery.getWhereArgs();
    int j = paramAbStorageQuery.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return this;
      Object localObject = paramAbStorageQuery[i];
      this.whereArgs.add(localObject);
      i += 1;
    }
  }

  public AbStorageQuery equals(String paramString, Object paramObject)
  {
    if (!AbStrUtil.isEmpty(this.whereClause))
      this.whereClause += " and ";
    this.whereClause = (this.whereClause + " " + paramString + " = ? ");
    this.whereArgs.add(paramObject.toString());
    return this;
  }

  public String getGroupBy()
  {
    return this.groupBy;
  }

  public String getHaving()
  {
    return this.having;
  }

  public int getLimit()
  {
    return this.limit;
  }

  public int getOffset()
  {
    return this.offset;
  }

  public String getOrderBy()
  {
    return this.orderBy;
  }

  public String[] getWhereArgs()
  {
    String[] arrayOfString = new String[this.whereArgs.size()];
    int i = 0;
    while (true)
    {
      if (i >= this.whereArgs.size())
        return arrayOfString;
      arrayOfString[i] = ((String)this.whereArgs.get(i));
      i += 1;
    }
  }

  public String getWhereClause()
  {
    return this.whereClause;
  }

  public AbStorageQuery greaterThan(String paramString, Object paramObject)
  {
    if (!AbStrUtil.isEmpty(this.whereClause))
      this.whereClause += " and ";
    this.whereClause = (this.whereClause + " " + paramString + " > ? ");
    this.whereArgs.add(paramObject.toString());
    return this;
  }

  public AbStorageQuery greaterThanEqualTo(String paramString, Object paramObject)
  {
    if (!AbStrUtil.isEmpty(this.whereClause))
      this.whereClause += " and ";
    this.whereClause = (this.whereClause + " " + paramString + " >= ? ");
    this.whereArgs.add(paramObject.toString());
    return this;
  }

  public AbStorageQuery in(String paramString, Object[] paramArrayOfObject)
  {
    if (!AbStrUtil.isEmpty(this.whereClause))
      this.whereClause += " and ";
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      this.whereClause = (this.whereClause + " " + paramString + " in ( ");
      int i = 0;
      int j;
      if (i >= paramArrayOfObject.length)
      {
        this.whereClause += " ) ";
        j = paramArrayOfObject.length;
        i = 0;
      }
      while (true)
      {
        if (i >= j)
        {
          return this;
          if (i != 0)
            this.whereClause += " , ";
          this.whereClause += " ? ";
          i += 1;
          break;
        }
        paramString = paramArrayOfObject[i];
        this.whereArgs.add((String)paramString);
        i += 1;
      }
    }
    this.whereClause = (this.whereClause + " " + paramString);
    return this;
  }

  public AbStorageQuery lessThan(String paramString, Object paramObject)
  {
    if (!AbStrUtil.isEmpty(this.whereClause))
      this.whereClause += " and ";
    this.whereClause = (this.whereClause + " " + paramString + " < ? ");
    this.whereArgs.add(paramObject.toString());
    return this;
  }

  public AbStorageQuery lessThanEqualTo(String paramString, Object paramObject)
  {
    if (!AbStrUtil.isEmpty(this.whereClause))
      this.whereClause += " and ";
    this.whereClause = (this.whereClause + " " + paramString + " <= ? ");
    this.whereArgs.add(paramObject.toString());
    return this;
  }

  public AbStorageQuery like(String paramString, Object paramObject)
  {
    if (!AbStrUtil.isEmpty(this.whereClause))
      this.whereClause += " and ";
    this.whereClause = (this.whereClause + " " + paramString + "like ? ");
    this.whereArgs.add("'%" + paramObject.toString() + "%'");
    return this;
  }

  public AbStorageQuery notEqual(String paramString, Object paramObject)
  {
    if (!AbStrUtil.isEmpty(this.whereClause))
      this.whereClause += " and ";
    this.whereClause = (this.whereClause + " " + paramString + " <> ? ");
    this.whereArgs.add(paramObject.toString());
    return this;
  }

  public AbStorageQuery notIn(String paramString, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      this.whereClause = (this.whereClause + " " + paramString + " not in ( ");
      int i = 0;
      while (true)
      {
        if (i >= paramArrayOfObject.length)
        {
          this.whereClause += " ) ";
          this.whereArgs.add(paramArrayOfObject.toString());
          return this;
        }
        if (i != 0)
          this.whereClause += " , ";
        this.whereClause += " ? ";
        i += 1;
      }
    }
    this.whereClause = (this.whereClause + " " + paramString);
    return this;
  }

  public AbStorageQuery or(AbStorageQuery paramAbStorageQuery)
  {
    this.whereClause = (this.whereClause + " or (" + paramAbStorageQuery.getWhereClause() + ")");
    paramAbStorageQuery = paramAbStorageQuery.getWhereArgs();
    int j = paramAbStorageQuery.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return this;
      Object localObject = paramAbStorageQuery[i];
      this.whereArgs.add(localObject);
      i += 1;
    }
  }

  public void setGroupBy(String paramString)
  {
    this.groupBy = paramString;
  }

  public void setHaving(String paramString)
  {
    this.having = paramString;
  }

  public void setLimit(int paramInt)
  {
    this.limit = paramInt;
  }

  public void setOffset(int paramInt)
  {
    this.offset = paramInt;
  }

  public void setOrderBy(String paramString)
  {
    this.orderBy = paramString;
  }

  public void setWhereClause(String paramString, String[] paramArrayOfString)
  {
    this.whereClause = paramString;
    int j = paramArrayOfString.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      paramString = paramArrayOfString[i];
      this.whereArgs.add(paramString);
      i += 1;
    }
  }

  public static enum SortOrder
  {
    ASC, DESC;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.storage.AbStorageQuery
 * JD-Core Version:    0.6.2
 */