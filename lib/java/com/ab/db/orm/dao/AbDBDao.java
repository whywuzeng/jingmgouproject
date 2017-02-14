package com.ab.db.orm.dao;

import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import java.util.Map;

public abstract interface AbDBDao<T>
{
  public abstract long delete(int paramInt);

  public abstract long delete(String paramString, String[] paramArrayOfString);

  public abstract long delete(Integer[] paramArrayOfInteger);

  public abstract long deleteAll();

  public abstract void execSql(String paramString, Object[] paramArrayOfObject);

  public abstract SQLiteOpenHelper getDbHelper();

  public abstract long insert(T paramT);

  public abstract long insert(T paramT, boolean paramBoolean);

  public abstract long insertList(List<T> paramList);

  public abstract long insertList(List<T> paramList, boolean paramBoolean);

  public abstract boolean isExist(String paramString, String[] paramArrayOfString);

  public abstract int queryCount(String paramString, String[] paramArrayOfString);

  public abstract List<T> queryList();

  public abstract List<T> queryList(String paramString, String[] paramArrayOfString);

  public abstract List<T> queryList(String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract List<Map<String, String>> queryMapList(String paramString, String[] paramArrayOfString);

  public abstract T queryOne(int paramInt);

  public abstract List<T> rawQuery(String paramString, String[] paramArrayOfString, Class<T> paramClass);

  public abstract long update(T paramT);

  public abstract long updateList(List<T> paramList);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.orm.dao.AbDBDao
 * JD-Core Version:    0.6.2
 */