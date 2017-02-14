package net.tsz.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.tsz.afinal.db.sqlite.CursorUtils;
import net.tsz.afinal.db.sqlite.DbModel;
import net.tsz.afinal.db.sqlite.ManyToOneLazyLoader;
import net.tsz.afinal.db.sqlite.OneToManyLazyLoader;
import net.tsz.afinal.db.sqlite.SqlBuilder;
import net.tsz.afinal.db.sqlite.SqlInfo;
import net.tsz.afinal.db.table.Id;
import net.tsz.afinal.db.table.KeyValue;
import net.tsz.afinal.db.table.ManyToOne;
import net.tsz.afinal.db.table.OneToMany;
import net.tsz.afinal.db.table.TableInfo;
import net.tsz.afinal.exception.DbException;

public class FinalDb
{
  private static final String TAG = "FinalDb";
  private static HashMap<String, FinalDb> daoMap = new HashMap();
  private DaoConfig config;
  private SQLiteDatabase db;

  private FinalDb(DaoConfig paramDaoConfig)
  {
    if (paramDaoConfig == null)
      throw new DbException("daoConfig is null");
    if (paramDaoConfig.getContext() == null)
      throw new DbException("android context is null");
    if ((paramDaoConfig.getTargetDirectory() != null) && (paramDaoConfig.getTargetDirectory().trim().length() > 0));
    for (this.db = createDbFileOnSDCard(paramDaoConfig.getTargetDirectory(), paramDaoConfig.getDbName()); ; this.db = new SqliteDbHelper(paramDaoConfig.getContext().getApplicationContext(), paramDaoConfig.getDbName(), paramDaoConfig.getDbVersion(), paramDaoConfig.getDbUpdateListener()).getWritableDatabase())
    {
      this.config = paramDaoConfig;
      return;
    }
  }

  private void checkTableExist(Class<?> paramClass)
  {
    if (!tableIsExist(TableInfo.get(paramClass)))
    {
      paramClass = SqlBuilder.getCreatTableSQL(paramClass);
      debugSql(paramClass);
      this.db.execSQL(paramClass);
    }
  }

  public static FinalDb create(Context paramContext)
  {
    DaoConfig localDaoConfig = new DaoConfig();
    localDaoConfig.setContext(paramContext);
    return create(localDaoConfig);
  }

  public static FinalDb create(Context paramContext, String paramString)
  {
    DaoConfig localDaoConfig = new DaoConfig();
    localDaoConfig.setContext(paramContext);
    localDaoConfig.setDbName(paramString);
    return create(localDaoConfig);
  }

  public static FinalDb create(Context paramContext, String paramString1, String paramString2)
  {
    DaoConfig localDaoConfig = new DaoConfig();
    localDaoConfig.setContext(paramContext);
    localDaoConfig.setDbName(paramString2);
    localDaoConfig.setTargetDirectory(paramString1);
    return create(localDaoConfig);
  }

  public static FinalDb create(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    DaoConfig localDaoConfig = new DaoConfig();
    localDaoConfig.setContext(paramContext);
    localDaoConfig.setTargetDirectory(paramString1);
    localDaoConfig.setDbName(paramString2);
    localDaoConfig.setDebug(paramBoolean);
    return create(localDaoConfig);
  }

  public static FinalDb create(Context paramContext, String paramString1, String paramString2, boolean paramBoolean, int paramInt, DbUpdateListener paramDbUpdateListener)
  {
    DaoConfig localDaoConfig = new DaoConfig();
    localDaoConfig.setContext(paramContext);
    localDaoConfig.setTargetDirectory(paramString1);
    localDaoConfig.setDbName(paramString2);
    localDaoConfig.setDebug(paramBoolean);
    localDaoConfig.setDbVersion(paramInt);
    localDaoConfig.setDbUpdateListener(paramDbUpdateListener);
    return create(localDaoConfig);
  }

  public static FinalDb create(Context paramContext, String paramString, boolean paramBoolean)
  {
    DaoConfig localDaoConfig = new DaoConfig();
    localDaoConfig.setContext(paramContext);
    localDaoConfig.setDbName(paramString);
    localDaoConfig.setDebug(paramBoolean);
    return create(localDaoConfig);
  }

  public static FinalDb create(Context paramContext, String paramString, boolean paramBoolean, int paramInt, DbUpdateListener paramDbUpdateListener)
  {
    DaoConfig localDaoConfig = new DaoConfig();
    localDaoConfig.setContext(paramContext);
    localDaoConfig.setDbName(paramString);
    localDaoConfig.setDebug(paramBoolean);
    localDaoConfig.setDbVersion(paramInt);
    localDaoConfig.setDbUpdateListener(paramDbUpdateListener);
    return create(localDaoConfig);
  }

  public static FinalDb create(Context paramContext, boolean paramBoolean)
  {
    DaoConfig localDaoConfig = new DaoConfig();
    localDaoConfig.setContext(paramContext);
    localDaoConfig.setDebug(paramBoolean);
    return create(localDaoConfig);
  }

  public static FinalDb create(DaoConfig paramDaoConfig)
  {
    return getInstance(paramDaoConfig);
  }

  private SQLiteDatabase createDbFileOnSDCard(String paramString1, String paramString2)
  {
    Object localObject = null;
    paramString2 = new File(paramString1, paramString2);
    if (!paramString2.exists())
    {
      paramString1 = localObject;
      try
      {
        if (paramString2.createNewFile())
          paramString1 = SQLiteDatabase.openOrCreateDatabase(paramString2, null);
        return paramString1;
      }
      catch (IOException paramString1)
      {
        throw new DbException("数据库文件创建失败", paramString1);
      }
    }
    return SQLiteDatabase.openOrCreateDatabase(paramString2, null);
  }

  private void debugSql(String paramString)
  {
    if ((this.config != null) && (this.config.isDebug()))
      Log.d("Debug SQL", ">>>>>>  " + paramString);
  }

  private void exeSqlInfo(SqlInfo paramSqlInfo)
  {
    if (paramSqlInfo != null)
    {
      debugSql(paramSqlInfo.getSql());
      this.db.execSQL(paramSqlInfo.getSql(), paramSqlInfo.getBindArgsAsArray());
      return;
    }
    Log.e("FinalDb", "sava error:sqlInfo is null");
  }

  private <T> List<T> findAllBySql(Class<T> paramClass, String paramString)
  {
    checkTableExist(paramClass);
    debugSql(paramString);
    paramString = this.db.rawQuery(paramString, null);
    try
    {
      ArrayList localArrayList = new ArrayList();
      while (true)
      {
        boolean bool = paramString.moveToNext();
        if (!bool)
          return localArrayList;
        localArrayList.add(CursorUtils.getEntity(paramString, paramClass, this));
      }
    }
    catch (Exception paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    finally
    {
      if (paramString != null)
        paramString.close();
    }
    throw paramClass;
  }

  private static FinalDb getInstance(DaoConfig paramDaoConfig)
  {
    try
    {
      FinalDb localFinalDb2 = (FinalDb)daoMap.get(paramDaoConfig.getDbName());
      FinalDb localFinalDb1 = localFinalDb2;
      if (localFinalDb2 == null)
      {
        localFinalDb1 = new FinalDb(paramDaoConfig);
        daoMap.put(paramDaoConfig.getDbName(), localFinalDb1);
      }
      return localFinalDb1;
    }
    finally
    {
    }
    throw paramDaoConfig;
  }

  private void insertContentValues(List<KeyValue> paramList, ContentValues paramContentValues)
  {
    if ((paramList != null) && (paramContentValues != null))
    {
      paramList = paramList.iterator();
      while (true)
      {
        if (!paramList.hasNext())
          return;
        KeyValue localKeyValue = (KeyValue)paramList.next();
        paramContentValues.put(localKeyValue.getKey(), localKeyValue.getValue().toString());
      }
    }
    Log.w("FinalDb", "insertContentValues: List<KeyValue> is empty or ContentValues is empty!");
  }

  private boolean tableIsExist(TableInfo paramTableInfo)
  {
    if (paramTableInfo.isCheckDatabese())
      return true;
    Object localObject2 = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    Object localObject1 = localObject2;
    while (true)
    {
      try
      {
        String str = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='" + paramTableInfo.getTableName() + "' ";
        localCursor1 = localCursor2;
        localObject1 = localObject2;
        debugSql(str);
        localCursor1 = localCursor2;
        localObject1 = localObject2;
        localCursor2 = this.db.rawQuery(str, null);
        if (localCursor2 != null)
        {
          localCursor1 = localCursor2;
          localObject1 = localCursor2;
          if (localCursor2.moveToNext())
          {
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            if (localCursor2.getInt(0) > 0)
            {
              localCursor1 = localCursor2;
              localObject1 = localCursor2;
              paramTableInfo.setCheckDatabese(true);
              if (localCursor2 != null)
                localCursor2.close();
              return true;
            }
          }
        }
      }
      catch (Exception paramTableInfo)
      {
        localObject1 = localCursor1;
        paramTableInfo.printStackTrace();
        if (localCursor1 != null)
          localCursor1.close();
        return false;
      }
      finally
      {
        if (localObject1 != null)
          ((Cursor)localObject1).close();
      }
      if (localCursor2 != null)
        localCursor2.close();
    }
  }

  public void delete(Object paramObject)
  {
    checkTableExist(paramObject.getClass());
    exeSqlInfo(SqlBuilder.buildDeleteSql(paramObject));
  }

  public void deleteAll(Class<?> paramClass)
  {
    checkTableExist(paramClass);
    paramClass = SqlBuilder.buildDeleteSql(paramClass, null);
    debugSql(paramClass);
    this.db.execSQL(paramClass);
  }

  public void deleteById(Class<?> paramClass, Object paramObject)
  {
    checkTableExist(paramClass);
    exeSqlInfo(SqlBuilder.buildDeleteSql(paramClass, paramObject));
  }

  public void deleteByWhere(Class<?> paramClass, String paramString)
  {
    checkTableExist(paramClass);
    paramClass = SqlBuilder.buildDeleteSql(paramClass, paramString);
    debugSql(paramClass);
    this.db.execSQL(paramClass);
  }

  public void dropDb()
  {
    Cursor localCursor = this.db.rawQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name != 'sqlite_sequence'", null);
    if (localCursor != null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        if (localCursor != null)
          localCursor.close();
        return;
      }
      this.db.execSQL("DROP TABLE " + localCursor.getString(0));
    }
  }

  public void dropTable(Class<?> paramClass)
  {
    checkTableExist(paramClass);
    paramClass = TableInfo.get(paramClass);
    paramClass = "DROP TABLE " + paramClass.getTableName();
    debugSql(paramClass);
    this.db.execSQL(paramClass);
  }

  public <T> List<T> findAll(Class<T> paramClass)
  {
    checkTableExist(paramClass);
    return findAllBySql(paramClass, SqlBuilder.getSelectSQL(paramClass));
  }

  public <T> List<T> findAll(Class<T> paramClass, String paramString)
  {
    checkTableExist(paramClass);
    return findAllBySql(paramClass, SqlBuilder.getSelectSQL(paramClass) + " ORDER BY " + paramString);
  }

  public <T> List<T> findAllByWhere(Class<T> paramClass, String paramString)
  {
    checkTableExist(paramClass);
    return findAllBySql(paramClass, SqlBuilder.getSelectSQLByWhere(paramClass, paramString));
  }

  public <T> List<T> findAllByWhere(Class<T> paramClass, String paramString1, String paramString2)
  {
    checkTableExist(paramClass);
    return findAllBySql(paramClass, SqlBuilder.getSelectSQLByWhere(paramClass, paramString1) + " ORDER BY " + paramString2);
  }

  public <T> T findById(Object paramObject, Class<T> paramClass)
  {
    checkTableExist(paramClass);
    paramObject = SqlBuilder.getSelectSqlAsSqlInfo(paramClass, paramObject);
    if (paramObject != null)
    {
      debugSql(paramObject.getSql());
      paramObject = this.db.rawQuery(paramObject.getSql(), paramObject.getBindArgsAsStringArray());
    }
    while (true)
    {
      try
      {
        if (paramObject.moveToNext())
        {
          paramClass = CursorUtils.getEntity(paramObject, paramClass, this);
          return paramClass;
        }
      }
      catch (Exception paramClass)
      {
        paramClass.printStackTrace();
        return null;
      }
      finally
      {
        paramObject.close();
      }
      paramObject.close();
    }
  }

  public DbModel findDbModelBySQL(String paramString)
  {
    debugSql(paramString);
    paramString = this.db.rawQuery(paramString, null);
    try
    {
      if (paramString.moveToNext())
      {
        DbModel localDbModel = CursorUtils.getDbModel(paramString);
        return localDbModel;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return null;
    }
    finally
    {
      paramString.close();
    }
    paramString.close();
    return null;
  }

  public List<DbModel> findDbModelListBySQL(String paramString)
  {
    debugSql(paramString);
    paramString = this.db.rawQuery(paramString, null);
    ArrayList localArrayList = new ArrayList();
    try
    {
      while (true)
      {
        boolean bool = paramString.moveToNext();
        if (!bool)
          return localArrayList;
        localArrayList.add(CursorUtils.getDbModel(paramString));
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return localArrayList;
    }
    finally
    {
      paramString.close();
    }
  }

  public <T> T findWithManyToOneById(Object paramObject, Class<T> paramClass)
  {
    checkTableExist(paramClass);
    paramObject = SqlBuilder.getSelectSQL(paramClass, paramObject);
    debugSql(paramObject);
    paramObject = findDbModelBySQL(paramObject);
    if (paramObject != null)
      return loadManyToOne(paramObject, CursorUtils.dbModel2Entity(paramObject, paramClass), paramClass, new Class[0]);
    return null;
  }

  public <T> T findWithManyToOneById(Object paramObject, Class<T> paramClass, Class<?>[] paramArrayOfClass)
  {
    checkTableExist(paramClass);
    paramObject = SqlBuilder.getSelectSQL(paramClass, paramObject);
    debugSql(paramObject);
    paramObject = findDbModelBySQL(paramObject);
    if (paramObject != null)
      return loadManyToOne(paramObject, CursorUtils.dbModel2Entity(paramObject, paramClass), paramClass, paramArrayOfClass);
    return null;
  }

  public <T> T findWithOneToManyById(Object paramObject, Class<T> paramClass)
  {
    checkTableExist(paramClass);
    paramObject = SqlBuilder.getSelectSQL(paramClass, paramObject);
    debugSql(paramObject);
    paramObject = findDbModelBySQL(paramObject);
    if (paramObject != null)
      return loadOneToMany(CursorUtils.dbModel2Entity(paramObject, paramClass), paramClass, new Class[0]);
    return null;
  }

  public <T> T findWithOneToManyById(Object paramObject, Class<T> paramClass, Class<?>[] paramArrayOfClass)
  {
    checkTableExist(paramClass);
    paramObject = SqlBuilder.getSelectSQL(paramClass, paramObject);
    debugSql(paramObject);
    paramObject = findDbModelBySQL(paramObject);
    if (paramObject != null)
      return loadOneToMany(CursorUtils.dbModel2Entity(paramObject, paramClass), paramClass, paramArrayOfClass);
    return null;
  }

  public <T> T loadManyToOne(DbModel paramDbModel, T paramT, Class<T> paramClass, Class<?>[] paramArrayOfClass)
  {
    ManyToOne localManyToOne;
    Class<?> localClass;
    Object localObject;
    int i;
    label85: int k;
    int j;
    if (paramT != null)
      while (true)
      {
        try
        {
          Iterator localIterator = TableInfo.get(paramClass).manyToOneMap.values().iterator();
          if (!localIterator.hasNext())
            return paramT;
          localManyToOne = (ManyToOne)localIterator.next();
          localClass = null;
          if (paramDbModel != null)
          {
            localObject = paramDbModel.get(localManyToOne.getColumn());
            if (localObject == null)
              continue;
            i = 0;
            if ((paramArrayOfClass == null) || (paramArrayOfClass.length == 0))
              break label279;
            k = paramArrayOfClass.length;
            j = 0;
            break label285;
            label96: if (i == 0)
              continue;
            localObject = findById(Integer.valueOf(localObject.toString()), localManyToOne.getManyClass());
            if (localObject == null)
              continue;
            if (localManyToOne.getValue(paramT).getClass() != ManyToOneLazyLoader.class)
              break;
            if (localManyToOne.getValue(paramT) == null)
              localManyToOne.setValue(paramT, new ManyToOneLazyLoader(paramT, paramClass, localManyToOne.getManyClass(), this));
            ((ManyToOneLazyLoader)localManyToOne.getValue(paramT)).set(localObject);
            continue;
          }
        }
        catch (Exception paramDbModel)
        {
          paramDbModel.printStackTrace();
          return paramT;
        }
        localObject = localClass;
        if (localManyToOne.getValue(paramT).getClass() == ManyToOneLazyLoader.class)
        {
          localObject = localClass;
          if (localManyToOne.getValue(paramT) != null)
            localObject = ((ManyToOneLazyLoader)localManyToOne.getValue(paramT)).getFieldValue();
        }
      }
    label279: label285: label302: 
    while (true)
    {
      localClass = paramArrayOfClass[j];
      if (localManyToOne.getManyClass() == localClass)
      {
        i = 1;
        break label96;
        localManyToOne.setValue(paramT, localObject);
        break;
        return paramT;
        i = 1;
        break label85;
      }
      while (true)
      {
        if (j < k)
          break label302;
        break;
        j += 1;
      }
    }
  }

  public <T> T loadOneToMany(T paramT, Class<T> paramClass, Class<?>[] paramArrayOfClass)
  {
    OneToMany localOneToMany;
    int i;
    label76: int k;
    int j;
    label86: Object localObject2;
    if (paramT != null)
      try
      {
        Object localObject1 = TableInfo.get(paramClass).oneToManyMap.values();
        paramClass = TableInfo.get(paramClass).getId().getValue(paramT);
        localObject1 = ((Collection)localObject1).iterator();
        while (true)
        {
          if (!((Iterator)localObject1).hasNext())
            return paramT;
          localOneToMany = (OneToMany)((Iterator)localObject1).next();
          i = 0;
          if ((paramArrayOfClass == null) || (paramArrayOfClass.length == 0))
            break label205;
          k = paramArrayOfClass.length;
          j = 0;
          break label211;
          if (i != 0)
          {
            localObject2 = findAllByWhere(localOneToMany.getOneClass(), localOneToMany.getColumn() + "=" + paramClass);
            if (localObject2 != null)
            {
              if (localOneToMany.getDataType() != OneToManyLazyLoader.class)
                break;
              ((OneToManyLazyLoader)localOneToMany.getValue(paramT)).setList((List)localObject2);
            }
          }
        }
      }
      catch (Exception paramClass)
      {
        paramClass.printStackTrace();
        return paramT;
      }
    label205: label211: label228: 
    while (true)
    {
      localObject2 = paramArrayOfClass[j];
      if (localOneToMany.getOneClass() == localObject2)
      {
        i = 1;
        break label86;
        localOneToMany.setValue(paramT, localObject2);
        break;
        return paramT;
        i = 1;
        break label76;
      }
      while (true)
      {
        if (j < k)
          break label228;
        break;
        j += 1;
      }
    }
  }

  public void save(Object paramObject)
  {
    checkTableExist(paramObject.getClass());
    exeSqlInfo(SqlBuilder.buildInsertSql(paramObject));
  }

  public boolean saveBindId(Object paramObject)
  {
    checkTableExist(paramObject.getClass());
    Object localObject = SqlBuilder.getSaveKeyValueListByEntity(paramObject);
    TableInfo localTableInfo;
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      localTableInfo = TableInfo.get(paramObject.getClass());
      ContentValues localContentValues = new ContentValues();
      insertContentValues((List)localObject, localContentValues);
      localObject = Long.valueOf(this.db.insert(localTableInfo.getTableName(), null, localContentValues));
      if (((Long)localObject).longValue() != -1L);
    }
    else
    {
      return false;
    }
    localTableInfo.getId().setValue(paramObject, localObject);
    return true;
  }

  public void update(Object paramObject)
  {
    checkTableExist(paramObject.getClass());
    exeSqlInfo(SqlBuilder.getUpdateSqlAsSqlInfo(paramObject));
  }

  public void update(Object paramObject, String paramString)
  {
    checkTableExist(paramObject.getClass());
    exeSqlInfo(SqlBuilder.getUpdateSqlAsSqlInfo(paramObject, paramString));
  }

  public static class DaoConfig
  {
    private FinalDb.DbUpdateListener dbUpdateListener;
    private int dbVersion = 1;
    private boolean debug = true;
    private Context mContext = null;
    private String mDbName = "afinal.db";
    private String targetDirectory;

    public Context getContext()
    {
      return this.mContext;
    }

    public String getDbName()
    {
      return this.mDbName;
    }

    public FinalDb.DbUpdateListener getDbUpdateListener()
    {
      return this.dbUpdateListener;
    }

    public int getDbVersion()
    {
      return this.dbVersion;
    }

    public String getTargetDirectory()
    {
      return this.targetDirectory;
    }

    public boolean isDebug()
    {
      return this.debug;
    }

    public void setContext(Context paramContext)
    {
      this.mContext = paramContext;
    }

    public void setDbName(String paramString)
    {
      this.mDbName = paramString;
    }

    public void setDbUpdateListener(FinalDb.DbUpdateListener paramDbUpdateListener)
    {
      this.dbUpdateListener = paramDbUpdateListener;
    }

    public void setDbVersion(int paramInt)
    {
      this.dbVersion = paramInt;
    }

    public void setDebug(boolean paramBoolean)
    {
      this.debug = paramBoolean;
    }

    public void setTargetDirectory(String paramString)
    {
      this.targetDirectory = paramString;
    }
  }

  public static abstract interface DbUpdateListener
  {
    public abstract void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2);
  }

  class SqliteDbHelper extends SQLiteOpenHelper
  {
    private FinalDb.DbUpdateListener mDbUpdateListener;

    public SqliteDbHelper(Context paramString, String paramInt, int paramDbUpdateListener, FinalDb.DbUpdateListener arg5)
    {
      super(paramInt, null, paramDbUpdateListener);
      Object localObject;
      this.mDbUpdateListener = localObject;
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (this.mDbUpdateListener != null)
      {
        this.mDbUpdateListener.onUpgrade(paramSQLiteDatabase, paramInt1, paramInt2);
        return;
      }
      FinalDb.this.dropDb();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.FinalDb
 * JD-Core Version:    0.6.2
 */