package com.ab.db.orm.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.ab.db.AbBasicDBDao;
import com.ab.db.orm.AbTableHelper;
import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
import com.ab.db.orm.annotation.Relations;
import com.ab.db.orm.annotation.Table;
import com.ab.util.AbStrUtil;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AbDBDaoImpl<T> extends AbBasicDBDao
  implements AbDBDao<T>
{
  private static final int METHOD_INSERT = 0;
  private static final int METHOD_UPDATE = 1;
  private static final int TYPE_INCREMENT = 1;
  private static final int TYPE_NOT_INCREMENT = 0;
  private String TAG = "AbDBDaoImpl";
  private List<Field> allFields;
  private Class<T> clazz;
  private SQLiteDatabase db = null;
  private SQLiteOpenHelper dbHelper;
  private String idColumn;
  private final ReentrantLock lock = new ReentrantLock();
  private String tableName;

  public AbDBDaoImpl(SQLiteOpenHelper paramSQLiteOpenHelper)
  {
    this(paramSQLiteOpenHelper, null);
  }

  public AbDBDaoImpl(SQLiteOpenHelper paramSQLiteOpenHelper, Class<T> paramClass)
  {
    this.dbHelper = paramSQLiteOpenHelper;
    if (paramClass == null)
    {
      this.clazz = ((Class)((ParameterizedType)super.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
      if (this.clazz.isAnnotationPresent(Table.class))
        this.tableName = ((Table)this.clazz.getAnnotation(Table.class)).name();
      this.allFields = AbTableHelper.joinFields(this.clazz.getDeclaredFields(), this.clazz.getSuperclass().getDeclaredFields());
      paramSQLiteOpenHelper = this.allFields.iterator();
      label126: if (paramSQLiteOpenHelper.hasNext())
        break label195;
    }
    while (true)
    {
      Log.d(this.TAG, "clazz:" + this.clazz + " tableName:" + this.tableName + " idColumn:" + this.idColumn);
      return;
      this.clazz = paramClass;
      break;
      label195: paramClass = (Field)paramSQLiteOpenHelper.next();
      if (!paramClass.isAnnotationPresent(Id.class))
        break label126;
      this.idColumn = ((Column)paramClass.getAnnotation(Column.class)).name();
    }
  }

  private void getListFromCursor(Class<?> paramClass, List<T> paramList, Cursor paramCursor)
    throws IllegalAccessException, InstantiationException
  {
    if (!paramCursor.moveToNext())
      return;
    Object localObject1 = paramClass.newInstance();
    Iterator localIterator = AbTableHelper.joinFields(localObject1.getClass().getDeclaredFields(), localObject1.getClass().getSuperclass().getDeclaredFields()).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        paramList.add(localObject1);
        break;
      }
      Field localField = (Field)localIterator.next();
      if (localField.isAnnotationPresent(Column.class))
      {
        Object localObject2 = (Column)localField.getAnnotation(Column.class);
        localField.setAccessible(true);
        Class localClass = localField.getType();
        int i = paramCursor.getColumnIndex(((Column)localObject2).name());
        if (i >= 0)
          if ((Integer.TYPE == localClass) || (Integer.class == localClass))
          {
            localField.set(localObject1, Integer.valueOf(paramCursor.getInt(i)));
          }
          else if (String.class == localClass)
          {
            localField.set(localObject1, paramCursor.getString(i));
          }
          else if ((Long.TYPE == localClass) || (Long.class == localClass))
          {
            localField.set(localObject1, Long.valueOf(paramCursor.getLong(i)));
          }
          else if ((Float.TYPE == localClass) || (Float.class == localClass))
          {
            localField.set(localObject1, Float.valueOf(paramCursor.getFloat(i)));
          }
          else if ((Short.TYPE == localClass) || (Short.class == localClass))
          {
            localField.set(localObject1, Short.valueOf(paramCursor.getShort(i)));
          }
          else if ((Double.TYPE == localClass) || (Double.class == localClass))
          {
            localField.set(localObject1, Double.valueOf(paramCursor.getDouble(i)));
          }
          else if (Date.class == localClass)
          {
            localObject2 = new Date();
            ((Date)localObject2).setTime(paramCursor.getLong(i));
            localField.set(localObject1, localObject2);
          }
          else if (Blob.class == localClass)
          {
            localField.set(localObject1, paramCursor.getBlob(i));
          }
          else if (Character.TYPE == localClass)
          {
            localObject2 = paramCursor.getString(i);
            if ((localObject2 != null) && (((String)localObject2).length() > 0))
              localField.set(localObject1, Character.valueOf(((String)localObject2).charAt(0)));
          }
          else if ((Boolean.TYPE == localClass) || (Boolean.class == localClass))
          {
            localObject2 = paramCursor.getString(i);
            if (("true".equals(localObject2)) || ("1".equals(localObject2)))
              localField.set(localObject1, Boolean.valueOf(true));
            else
              localField.set(localObject1, Boolean.valueOf(false));
          }
      }
    }
  }

  private String getLogSql(String paramString, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0))
      return paramString;
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfObject.length)
        return paramString;
      paramString = paramString.replaceFirst("\\?", "'" + String.valueOf(paramArrayOfObject[i]) + "'");
      i += 1;
    }
  }

  private String setContentValues(T paramT, ContentValues paramContentValues, int paramInt1, int paramInt2)
    throws IllegalAccessException
  {
    StringBuffer localStringBuffer1 = new StringBuffer("(");
    StringBuffer localStringBuffer2 = new StringBuffer(" values(");
    StringBuffer localStringBuffer3 = new StringBuffer(" ");
    Iterator localIterator = AbTableHelper.joinFields(paramT.getClass().getDeclaredFields(), paramT.getClass().getSuperclass().getDeclaredFields()).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (paramInt2 != 0)
          break;
        localStringBuffer1.deleteCharAt(localStringBuffer1.length() - 1).append(")");
        localStringBuffer2.deleteCharAt(localStringBuffer2.length() - 1).append(")");
        return localStringBuffer1.toString() + localStringBuffer2.toString();
      }
      Object localObject1 = (Field)localIterator.next();
      if (((Field)localObject1).isAnnotationPresent(Column.class))
      {
        Column localColumn = (Column)((Field)localObject1).getAnnotation(Column.class);
        ((Field)localObject1).setAccessible(true);
        Object localObject2 = ((Field)localObject1).get(paramT);
        if ((localObject2 != null) && ((paramInt1 != 1) || (!((Field)localObject1).isAnnotationPresent(Id.class))))
          if (Date.class == ((Field)localObject1).getType())
          {
            paramContentValues.put(localColumn.name(), Long.valueOf(((Date)localObject2).getTime()));
          }
          else
          {
            localObject1 = String.valueOf(localObject2);
            paramContentValues.put(localColumn.name(), (String)localObject1);
            if (paramInt2 == 0)
            {
              localStringBuffer1.append(localColumn.name()).append(",");
              localStringBuffer2.append("'").append((String)localObject1).append("',");
            }
            else
            {
              localStringBuffer3.append(localColumn.name()).append("=").append("'").append((String)localObject1).append("',");
            }
          }
      }
    }
    return " ";
  }

  public void closeDatabase(boolean paramBoolean)
  {
    try
    {
      this.lock.lock();
      if (this.db != null)
      {
        if (paramBoolean)
          this.db.endTransaction();
        if (this.db.isOpen())
          this.db.close();
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public long delete(int paramInt)
  {
    try
    {
      this.lock.lock();
      String str1 = this.idColumn + " = ?";
      String str2 = Integer.toString(paramInt);
      Log.d(this.TAG, "[delete]: delelte from " + this.tableName + " where " + str1.replace("?", String.valueOf(paramInt)));
      paramInt = this.db.delete(this.tableName, str1, new String[] { str2 });
      long l = paramInt;
      return l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return -1L;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public long delete(String paramString, String[] paramArrayOfString)
  {
    try
    {
      this.lock.lock();
      String str2 = getLogSql(paramString, paramArrayOfString);
      String str1 = str2;
      if (!AbStrUtil.isEmpty(str2))
        str1 = str2 + " where ";
      Log.d(this.TAG, "[delete]: delete from " + this.tableName + str1);
      int i = this.db.delete(this.tableName, paramString, paramArrayOfString);
      long l = i;
      return l;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return -1L;
    }
    finally
    {
      this.lock.unlock();
    }
    throw paramString;
  }

  public long delete(Integer[] paramArrayOfInteger)
  {
    long l1 = -1L;
    long l2 = l1;
    int i;
    if (paramArrayOfInteger.length > 0)
      i = 0;
    while (true)
    {
      if (i >= paramArrayOfInteger.length)
      {
        l2 = l1;
        return l2;
      }
      l1 += delete(paramArrayOfInteger[i].intValue());
      i += 1;
    }
  }

  public long deleteAll()
  {
    try
    {
      this.lock.lock();
      Log.d(this.TAG, "[delete]: delete from " + this.tableName);
      int i = this.db.delete(this.tableName, null, null);
      long l = i;
      return l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return -1L;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public void execSql(String paramString, Object[] paramArrayOfObject)
  {
    try
    {
      this.lock.lock();
      Log.d(this.TAG, "[execSql]: " + getLogSql(paramString, paramArrayOfObject));
      if (paramArrayOfObject == null)
        this.db.execSQL(paramString);
      while (true)
      {
        return;
        this.db.execSQL(paramString, paramArrayOfObject);
      }
    }
    catch (Exception paramString)
    {
      Log.e(this.TAG, "[execSql] DB exception.");
      paramString.printStackTrace();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
    throw paramString;
  }

  public SQLiteOpenHelper getDbHelper()
  {
    return this.dbHelper;
  }

  public long insert(T paramT)
  {
    return insert(paramT, true);
  }

  public long insert(T paramT, boolean paramBoolean)
  {
    try
    {
      this.lock.lock();
      Object localObject2 = new ContentValues();
      Iterator localIterator;
      if (paramBoolean)
      {
        localObject1 = setContentValues(paramT, (ContentValues)localObject2, 1, 0);
        Log.d(this.TAG, "[insert]: insert into " + this.tableName + " " + (String)localObject1);
        l1 = this.db.insert(this.tableName, null, (ContentValues)localObject2);
        localIterator = this.allFields.iterator();
      }
      Object localObject3;
      label386: long l2;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                boolean bool = localIterator.hasNext();
                if (!bool)
                {
                  return l1;
                  localObject1 = setContentValues(paramT, (ContentValues)localObject2, 0, 0);
                  break;
                }
                localObject1 = (Field)localIterator.next();
              }
              while (!((Field)localObject1).isAnnotationPresent(Relations.class));
              localObject3 = (Relations)((Field)localObject1).getAnnotation(Relations.class);
              ((Relations)localObject3).foreignKey();
              localObject2 = ((Relations)localObject3).type();
              localObject3 = ((Relations)localObject3).action();
              ((Field)localObject1).setAccessible(true);
              int i = ((String)localObject3).indexOf("insert");
              if (i == -1)
                return l1;
              if (!"one2one".equals(localObject2))
                break label386;
              localObject4 = ((Field)localObject1).get(paramT);
            }
            while (localObject4 == null);
            localObject3 = new ContentValues();
            if (paramBoolean);
            for (localObject1 = setContentValues(localObject4, (ContentValues)localObject3, 1, 0); ; localObject1 = setContentValues(localObject4, (ContentValues)localObject3, 0, 0))
            {
              localObject2 = "";
              if (localObject4.getClass().isAnnotationPresent(Table.class))
                localObject2 = ((Table)localObject4.getClass().getAnnotation(Table.class)).name();
              Log.d(this.TAG, "[insert]: insert into " + (String)localObject2 + " " + (String)localObject1);
              l1 += this.db.insert((String)localObject2, null, (ContentValues)localObject3);
              break;
            }
          }
          while ((!"one2many".equals(localObject2)) && (!"many2many".equals(localObject2)));
          localObject1 = (List)((Field)localObject1).get(paramT);
        }
        while ((localObject1 == null) || (((List)localObject1).size() <= 0));
        localObject3 = ((List)localObject1).iterator();
        l2 = l1;
        l1 = l2;
      }
      while (!((Iterator)localObject3).hasNext());
      Object localObject5 = (Object)((Iterator)localObject3).next();
      Object localObject4 = new ContentValues();
      if (paramBoolean);
      for (Object localObject1 = setContentValues(localObject5, (ContentValues)localObject4, 1, 0); ; localObject1 = setContentValues(localObject5, (ContentValues)localObject4, 0, 0))
      {
        localObject2 = "";
        if (localObject5.getClass().isAnnotationPresent(Table.class))
          localObject2 = ((Table)localObject5.getClass().getAnnotation(Table.class)).name();
        Log.d(this.TAG, "[insert]: insert into " + (String)localObject2 + " " + (String)localObject1);
        l2 += this.db.insert((String)localObject2, null, (ContentValues)localObject4);
        break;
      }
    }
    catch (Exception paramT)
    {
      while (true)
      {
        Log.d(this.TAG, "[insert] into DB Exception.");
        paramT.printStackTrace();
        long l1 = -1L;
        this.lock.unlock();
      }
    }
    finally
    {
      this.lock.unlock();
    }
    throw paramT;
  }

  public long insertList(List<T> paramList)
  {
    return insertList(paramList, true);
  }

  public long insertList(List<T> paramList, boolean paramBoolean)
  {
    long l1 = 0L;
    long l2 = l1;
    try
    {
      this.lock.lock();
      l2 = l1;
      Iterator localIterator1 = paramList.iterator();
      Object localObject1;
      label100: long l3;
      Object localObject2;
      label188: 
      do
      {
        do
        {
          do
          {
            do
            {
              Iterator localIterator2;
              do
              {
                do
                {
                  do
                  {
                    l2 = l1;
                    boolean bool = localIterator1.hasNext();
                    if (!bool)
                      return l1;
                    l2 = l1;
                    localObject4 = (Object)localIterator1.next();
                    l2 = l1;
                    localObject1 = new ContentValues();
                    if (!paramBoolean)
                      break;
                    l2 = l1;
                    paramList = setContentValues(localObject4, (ContentValues)localObject1, 1, 0);
                    l2 = l1;
                    Log.d(this.TAG, "[insertList]: insert into " + this.tableName + " " + paramList);
                    l2 = l1;
                    l3 = l1 + this.db.insert(this.tableName, null, (ContentValues)localObject1);
                    localObject2 = null;
                    paramList = null;
                    localObject1 = null;
                    l2 = l3;
                    localIterator2 = this.allFields.iterator();
                    l2 = l3;
                    if (localIterator2.hasNext())
                      break label427;
                    l1 = l3;
                  }
                  while (localObject1 == null);
                  l1 = l3;
                  l2 = l3;
                }
                while (paramList.indexOf("insert") == -1);
                l2 = l3;
                if (!"one2one".equals(localObject2))
                  break label543;
                l2 = l3;
                localObject3 = ((Field)localObject1).get(localObject4);
                l1 = l3;
              }
              while (localObject3 == null);
              l2 = l3;
              localObject2 = new ContentValues();
              if (paramBoolean)
                l2 = l3;
              for (paramList = setContentValues(localObject3, (ContentValues)localObject2, 1, 0); ; paramList = setContentValues(localObject3, (ContentValues)localObject2, 0, 0))
              {
                localObject1 = "";
                l2 = l3;
                if (localObject3.getClass().isAnnotationPresent(Table.class))
                {
                  l2 = l3;
                  localObject1 = ((Table)localObject3.getClass().getAnnotation(Table.class)).name();
                }
                l2 = l3;
                Log.d(this.TAG, "[insertList]: insert into " + (String)localObject1 + " " + paramList);
                l2 = l3;
                l1 = l3 + this.db.insert((String)localObject1, null, (ContentValues)localObject2);
                break;
                l2 = l1;
                paramList = setContentValues(localObject4, (ContentValues)localObject1, 0, 0);
                break label100;
                l2 = l3;
                localObject3 = (Field)localIterator2.next();
                l2 = l3;
                if (!((Field)localObject3).isAnnotationPresent(Relations.class))
                  break label188;
                l2 = l3;
                paramList = (Relations)((Field)localObject3).getAnnotation(Relations.class);
                l2 = l3;
                paramList.foreignKey();
                l2 = l3;
                localObject2 = paramList.type();
                l2 = l3;
                paramList = paramList.action();
                l2 = l3;
                ((Field)localObject3).setAccessible(true);
                localObject1 = localObject3;
                break label188;
                l2 = l3;
              }
              l2 = l3;
              if ("one2many".equals(localObject2))
                break;
              l1 = l3;
              l2 = l3;
            }
            while (!"many2many".equals(localObject2));
            l2 = l3;
            paramList = (List)((Field)localObject1).get(localObject4);
            l1 = l3;
          }
          while (paramList == null);
          l1 = l3;
          l2 = l3;
        }
        while (paramList.size() <= 0);
        l2 = l3;
        localObject2 = paramList.iterator();
        l1 = l3;
        l2 = l3;
      }
      while (!((Iterator)localObject2).hasNext());
      label427: l2 = l3;
      label543: Object localObject4 = (Object)((Iterator)localObject2).next();
      l2 = l3;
      Object localObject3 = new ContentValues();
      if (paramBoolean)
        l2 = l3;
      for (paramList = setContentValues(localObject4, (ContentValues)localObject3, 1, 0); ; paramList = setContentValues(localObject4, (ContentValues)localObject3, 0, 0))
      {
        localObject1 = "";
        l2 = l3;
        if (localObject4.getClass().isAnnotationPresent(Table.class))
        {
          l2 = l3;
          localObject1 = ((Table)localObject4.getClass().getAnnotation(Table.class)).name();
        }
        l2 = l3;
        Log.d(this.TAG, "[insertList]: insert into " + (String)localObject1 + " " + paramList);
        l2 = l3;
        l3 += this.db.insert((String)localObject1, null, (ContentValues)localObject3);
        break;
        l2 = l3;
      }
    }
    catch (Exception paramList)
    {
      Log.d(this.TAG, "[insertList] into DB Exception.");
      paramList.printStackTrace();
      return l2;
    }
    finally
    {
      this.lock.unlock();
    }
    throw paramList;
  }

  public boolean isExist(String paramString, String[] paramArrayOfString)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    Object localObject1 = localObject4;
    while (true)
    {
      try
      {
        this.lock.lock();
        localObject2 = localObject3;
        localObject1 = localObject4;
        Log.d(this.TAG, "[isExist]: " + getLogSql(paramString, paramArrayOfString));
        localObject2 = localObject3;
        localObject1 = localObject4;
        paramString = this.db.rawQuery(paramString, paramArrayOfString);
        localObject2 = paramString;
        localObject1 = paramString;
        int i = paramString.getCount();
        if (i > 0)
        {
          closeCursor(paramString);
          this.lock.unlock();
          return true;
        }
      }
      catch (Exception paramString)
      {
        localObject1 = localObject2;
        Log.e(this.TAG, "[isExist] from DB Exception.");
        localObject1 = localObject2;
        paramString.printStackTrace();
        return false;
      }
      finally
      {
        closeCursor((Cursor)localObject1);
        this.lock.unlock();
      }
      closeCursor(paramString);
      this.lock.unlock();
    }
  }

  public int queryCount(String paramString, String[] paramArrayOfString)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    int i = 0;
    Object localObject2 = localObject3;
    Object localObject1 = localObject4;
    try
    {
      this.lock.lock();
      localObject2 = localObject3;
      localObject1 = localObject4;
      Log.d(this.TAG, "[queryCount]: " + getLogSql(paramString, paramArrayOfString));
      localObject2 = localObject3;
      localObject1 = localObject4;
      paramString = this.db.query(this.tableName, null, paramString, paramArrayOfString, null, null, null);
      if (paramString != null)
      {
        localObject2 = paramString;
        localObject1 = paramString;
        i = paramString.getCount();
      }
      closeCursor(paramString);
      this.lock.unlock();
      return i;
    }
    catch (Exception paramString)
    {
      localObject1 = localObject2;
      Log.e(this.TAG, "[queryCount] from DB exception");
      localObject1 = localObject2;
      paramString.printStackTrace();
      return 0;
    }
    finally
    {
      closeCursor((Cursor)localObject1);
      this.lock.unlock();
    }
    throw paramString;
  }

  public List<T> queryList()
  {
    return queryList(null, null, null, null, null, null, null);
  }

  public List<T> queryList(String paramString, String[] paramArrayOfString)
  {
    return queryList(null, paramString, paramArrayOfString, null, null, null, null);
  }

  public List<T> queryList(String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = null;
    String str = null;
    Object localObject1 = str;
    Object localObject2 = localIterator;
    while (true)
    {
      int i;
      Object localObject3;
      Object localObject5;
      try
      {
        this.lock.lock();
        localObject1 = str;
        localObject2 = localIterator;
        Log.d(this.TAG, "[queryList] from" + this.tableName + " where " + paramString1 + "(" + paramArrayOfString2 + ")" + " group by " + paramString2 + " having " + paramString3 + " order by " + paramString4 + " limit " + paramString5);
        localObject1 = str;
        localObject2 = localIterator;
        paramString1 = this.db.query(this.tableName, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramString3, paramString4, paramString5);
        localObject1 = paramString1;
        localObject2 = paramString1;
        getListFromCursor(this.clazz, localArrayList, paramString1);
        localObject1 = paramString1;
        localObject2 = paramString1;
        closeCursor(paramString1);
        localObject1 = paramString1;
        localObject2 = paramString1;
        paramString3 = this.allFields.iterator();
        localObject1 = paramString1;
        localObject2 = paramString1;
        boolean bool = paramString3.hasNext();
        if (!bool)
        {
          closeCursor(paramString1);
          this.lock.unlock();
          return localArrayList;
        }
        localObject1 = paramString1;
        localObject2 = paramString1;
        paramString4 = (Field)paramString3.next();
        localObject1 = paramString1;
        localObject2 = paramString1;
        if (!paramString4.isAnnotationPresent(Relations.class))
          continue;
        localObject1 = paramString1;
        localObject2 = paramString1;
        paramArrayOfString1 = (Relations)paramString4.getAnnotation(Relations.class);
        localObject1 = paramString1;
        localObject2 = paramString1;
        paramString5 = paramArrayOfString1.foreignKey();
        localObject1 = paramString1;
        localObject2 = paramString1;
        str = paramArrayOfString1.type();
        localObject1 = paramString1;
        localObject2 = paramString1;
        paramArrayOfString1 = paramArrayOfString1.action();
        localObject1 = paramString1;
        localObject2 = paramString1;
        paramString4.setAccessible(true);
        localObject1 = paramString1;
        localObject2 = paramString1;
        i = paramArrayOfString1.indexOf("query");
        if (i == -1)
        {
          closeCursor(paramString1);
          this.lock.unlock();
          return localArrayList;
        }
        localObject1 = paramString1;
        localObject2 = paramString1;
        localIterator = localArrayList.iterator();
        paramArrayOfString1 = paramString1;
        paramString1 = paramArrayOfString1;
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        if (!localIterator.hasNext())
          continue;
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        localObject3 = (Object)localIterator.next();
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        if ("one2one".equals(str))
        {
          paramString1 = "";
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          if (paramString4.getType().isAnnotationPresent(Table.class))
          {
            localObject1 = paramArrayOfString1;
            localObject2 = paramArrayOfString1;
            paramString1 = ((Table)paramString4.getType().getAnnotation(Table.class)).name();
          }
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          paramString2 = new ArrayList();
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          paramArrayOfString2 = paramString4.getType().getDeclaredFields();
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          j = paramArrayOfString2.length;
          i = 0;
          if (i >= j)
            continue;
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          if (!((Column)paramArrayOfString2[i].getAnnotation(Column.class)).name().equals(paramString5))
            break label1383;
          paramArrayOfString2 = "-1";
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          localObject4 = this.allFields.iterator();
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          if (!((Iterator)localObject4).hasNext())
          {
            localObject1 = paramArrayOfString1;
            localObject2 = paramArrayOfString1;
            paramString1 = this.db.query(paramString1, null, paramString5 + " = ?", new String[] { paramArrayOfString2 }, null, null, null, null);
            localObject1 = paramString1;
            localObject2 = paramString1;
            getListFromCursor(paramString4.getType(), paramString2, paramString1);
            paramArrayOfString1 = paramString1;
            localObject1 = paramString1;
            localObject2 = paramString1;
            if (paramString2.size() <= 0)
              continue;
            localObject1 = paramString1;
            localObject2 = paramString1;
            paramString4.set(localObject3, paramString2.get(0));
            paramArrayOfString1 = paramString1;
            continue;
          }
        }
      }
      catch (Exception paramArrayOfString1)
      {
        localObject2 = localObject1;
        Log.e(this.TAG, "[queryList] from DB Exception");
        localObject2 = localObject1;
        paramArrayOfString1.printStackTrace();
        return localArrayList;
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        localObject5 = (Field)((Iterator)localObject4).next();
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        ((Field)localObject5).setAccessible(true);
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        localObject6 = (Column)((Field)localObject5).getAnnotation(Column.class);
        if (localObject6 == null)
          continue;
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        if (!((Column)localObject6).name().equals(paramString5))
          continue;
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        paramArrayOfString2 = String.valueOf(((Field)localObject5).get(localObject3));
        continue;
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        if (!"one2many".equals(str))
        {
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          if (!"many2many".equals(str))
            continue;
        }
        paramString1 = null;
        localObject1 = paramArrayOfString1;
        paramArrayOfString2 = paramString1;
        localObject2 = paramArrayOfString1;
        if (paramString4.getType().isAssignableFrom(List.class))
        {
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          paramString2 = paramString4.getGenericType();
          if (paramString2 == null)
            continue;
          localObject1 = paramArrayOfString1;
          paramArrayOfString2 = paramString1;
          localObject2 = paramArrayOfString1;
          if ((paramString2 instanceof ParameterizedType))
          {
            localObject1 = paramArrayOfString1;
            localObject2 = paramArrayOfString1;
            paramArrayOfString2 = (Class)((ParameterizedType)paramString2).getActualTypeArguments()[0];
          }
        }
        if (paramArrayOfString2 == null)
        {
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          Log.e(this.TAG, "对象模型需要设置List的泛型");
          closeCursor(paramArrayOfString1);
          this.lock.unlock();
          return null;
        }
        paramString1 = "";
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        if (paramArrayOfString2.isAnnotationPresent(Table.class))
        {
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          paramString1 = ((Table)paramArrayOfString2.getAnnotation(Table.class)).name();
        }
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        Object localObject4 = new ArrayList();
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        paramString2 = paramArrayOfString2.getDeclaredFields();
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        int j = paramString2.length;
        i = 0;
        if (i >= j)
          continue;
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        if (!((Column)paramString2[i].getAnnotation(Column.class)).name().equals(paramString5))
          break label1374;
        paramString2 = "-1";
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        localObject5 = this.allFields.iterator();
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        if (!((Iterator)localObject5).hasNext())
        {
          localObject1 = paramArrayOfString1;
          localObject2 = paramArrayOfString1;
          paramString1 = this.db.query(paramString1, null, paramString5 + " = ?", new String[] { paramString2 }, null, null, null, null);
          localObject1 = paramString1;
          localObject2 = paramString1;
          getListFromCursor(paramArrayOfString2, (List)localObject4, paramString1);
          paramArrayOfString1 = paramString1;
          localObject1 = paramString1;
          localObject2 = paramString1;
          if (((List)localObject4).size() <= 0)
            continue;
          localObject1 = paramString1;
          localObject2 = paramString1;
          paramString4.set(localObject3, localObject4);
          paramArrayOfString1 = paramString1;
          continue;
        }
      }
      finally
      {
        closeCursor((Cursor)localObject2);
        this.lock.unlock();
      }
      localObject1 = paramArrayOfString1;
      localObject2 = paramArrayOfString1;
      Object localObject6 = (Field)((Iterator)localObject5).next();
      localObject1 = paramArrayOfString1;
      localObject2 = paramArrayOfString1;
      ((Field)localObject6).setAccessible(true);
      localObject1 = paramArrayOfString1;
      localObject2 = paramArrayOfString1;
      if (((Column)((Field)localObject6).getAnnotation(Column.class)).name().equals(paramString5))
      {
        localObject1 = paramArrayOfString1;
        localObject2 = paramArrayOfString1;
        paramString2 = String.valueOf(((Field)localObject6).get(localObject3));
        continue;
        label1374: i += 1;
        continue;
        label1383: i += 1;
      }
    }
  }

  public List<Map<String, String>> queryMapList(String paramString, String[] paramArrayOfString)
  {
    String str = null;
    String[] arrayOfString = null;
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = arrayOfString;
    Object localObject1 = str;
    while (true)
    {
      int i;
      try
      {
        this.lock.lock();
        localObject2 = arrayOfString;
        localObject1 = str;
        Log.d(this.TAG, "[queryMapList]: " + getLogSql(paramString, paramArrayOfString));
        localObject2 = arrayOfString;
        localObject1 = str;
        paramString = this.db.rawQuery(paramString, paramArrayOfString);
        localObject2 = paramString;
        localObject1 = paramString;
        boolean bool = paramString.moveToNext();
        if (!bool)
        {
          closeCursor(paramString);
          this.lock.unlock();
          return localArrayList;
        }
        localObject2 = paramString;
        localObject1 = paramString;
        paramArrayOfString = new HashMap();
        localObject2 = paramString;
        localObject1 = paramString;
        arrayOfString = paramString.getColumnNames();
        localObject2 = paramString;
        localObject1 = paramString;
        int j = arrayOfString.length;
        i = 0;
        if (i >= j)
        {
          localObject2 = paramString;
          localObject1 = paramString;
          localArrayList.add(paramArrayOfString);
          continue;
        }
      }
      catch (Exception paramString)
      {
        localObject1 = localObject2;
        paramString.printStackTrace();
        localObject1 = localObject2;
        Log.e(this.TAG, "[queryMapList] from DB exception");
        return localArrayList;
        str = arrayOfString[i];
        localObject2 = paramString;
        localObject1 = paramString;
        int k = paramString.getColumnIndex(str);
        if (k >= 0)
        {
          localObject2 = paramString;
          localObject1 = paramString;
          paramArrayOfString.put(str.toLowerCase(), paramString.getString(k));
        }
      }
      finally
      {
        closeCursor((Cursor)localObject1);
        this.lock.unlock();
      }
      i += 1;
    }
  }

  public T queryOne(int paramInt)
  {
    synchronized (this.lock)
    {
      Object localObject1 = this.idColumn + " = ?";
      String str = Integer.toString(paramInt);
      Log.d(this.TAG, "[queryOne]: select * from " + this.tableName + " where " + this.idColumn + " = '" + paramInt + "'");
      localObject1 = queryList(null, (String)localObject1, new String[] { str }, null, null, null, null);
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        localObject1 = ((List)localObject1).get(0);
        return localObject1;
      }
      return null;
    }
  }

  public List<T> rawQuery(String paramString, String[] paramArrayOfString, Class<T> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    Object localObject1 = localObject4;
    try
    {
      this.lock.lock();
      localObject2 = localObject3;
      localObject1 = localObject4;
      Log.d(this.TAG, "[rawQuery]: " + getLogSql(paramString, paramArrayOfString));
      localObject2 = localObject3;
      localObject1 = localObject4;
      paramString = this.db.rawQuery(paramString, paramArrayOfString);
      localObject2 = paramString;
      localObject1 = paramString;
      getListFromCursor(paramClass, localArrayList, paramString);
      closeCursor(paramString);
      this.lock.unlock();
      return localArrayList;
    }
    catch (Exception paramString)
    {
      localObject1 = localObject2;
      Log.e(this.TAG, "[rawQuery] from DB Exception.");
      localObject1 = localObject2;
      paramString.printStackTrace();
      return localArrayList;
    }
    finally
    {
      closeCursor((Cursor)localObject1);
      this.lock.unlock();
    }
    throw paramString;
  }

  public void setTransactionSuccessful()
  {
    try
    {
      this.lock.lock();
      if (this.db != null)
        this.db.setTransactionSuccessful();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public void startReadableDatabase(boolean paramBoolean)
  {
    try
    {
      this.lock.lock();
      if ((this.db == null) || (!this.db.isOpen()))
        this.db = this.dbHelper.getReadableDatabase();
      if ((this.db != null) && (paramBoolean))
        this.db.beginTransaction();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public void startWritableDatabase(boolean paramBoolean)
  {
    try
    {
      this.lock.lock();
      if ((this.db == null) || (!this.db.isOpen()))
        this.db = this.dbHelper.getWritableDatabase();
      if ((this.db != null) && (paramBoolean))
        this.db.beginTransaction();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public long update(T paramT)
  {
    try
    {
      this.lock.lock();
      ContentValues localContentValues = new ContentValues();
      String str = setContentValues(paramT, localContentValues, 0, 1);
      paramT = this.idColumn + " = ?";
      int i = Integer.parseInt(localContentValues.get(this.idColumn).toString());
      localContentValues.remove(this.idColumn);
      Log.d(this.TAG, "[update]: update " + this.tableName + " set " + str + " where " + paramT.replace("?", String.valueOf(i)));
      str = Integer.toString(i);
      i = this.db.update(this.tableName, localContentValues, paramT, new String[] { str });
      long l = i;
      return l;
    }
    catch (Exception paramT)
    {
      Log.d(this.TAG, "[update] DB Exception.");
      paramT.printStackTrace();
      return 0L;
    }
    finally
    {
      this.lock.unlock();
    }
    throw paramT;
  }

  public long updateList(List<T> paramList)
  {
    try
    {
      this.lock.lock();
      paramList = paramList.iterator();
      while (true)
      {
        boolean bool = paramList.hasNext();
        if (!bool)
          return 0L;
        Object localObject = (Object)paramList.next();
        ContentValues localContentValues = new ContentValues();
        String str = setContentValues(localObject, localContentValues, 0, 1);
        localObject = this.idColumn + " = ?";
        int i = Integer.parseInt(localContentValues.get(this.idColumn).toString());
        localContentValues.remove(this.idColumn);
        Log.d(this.TAG, "[update]: update " + this.tableName + " set " + str + " where " + ((String)localObject).replace("?", String.valueOf(i)));
        str = Integer.toString(i);
        this.db.update(this.tableName, localContentValues, (String)localObject, new String[] { str });
      }
    }
    catch (Exception paramList)
    {
      Log.d(this.TAG, "[update] DB Exception.");
      paramList.printStackTrace();
      return 0L;
    }
    finally
    {
      this.lock.unlock();
    }
    throw paramList;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.orm.dao.AbDBDaoImpl
 * JD-Core Version:    0.6.2
 */