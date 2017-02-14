package com.ab.db.storage;

import android.content.Context;
import com.ab.db.orm.dao.AbDBDaoImpl;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListListener;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import java.util.List;

public class AbSqliteStorage
{
  private static AbTaskQueue mAbTaskQueue = null;
  private static Context mContext;
  private static AbSqliteStorage mSqliteStorage = null;
  private int errorCode100 = 100;
  private int errorCode101 = 101;
  private String errorMessage100 = "参数错误";
  private String errorMessage101 = "执行时错误";
  private long retValue = -1L;

  private AbSqliteStorage(Context paramContext)
  {
  }

  public static AbSqliteStorage getInstance(Context paramContext)
  {
    mContext = paramContext;
    if (mSqliteStorage == null)
      mSqliteStorage = new AbSqliteStorage(paramContext);
    mAbTaskQueue = AbTaskQueue.getInstance();
    return mSqliteStorage;
  }

  public <T> void deleteData(final AbStorageQuery paramAbStorageQuery, final AbDBDaoImpl<T> paramAbDBDaoImpl, final AbSqliteStorageListener.AbDataOperationListener paramAbDataOperationListener)
  {
    AbTaskItem localAbTaskItem = new AbTaskItem();
    localAbTaskItem.listener = new AbTaskListener()
    {
      public void get()
      {
        paramAbDBDaoImpl.startWritableDatabase(false);
        AbSqliteStorage.this.retValue = paramAbDBDaoImpl.delete(paramAbStorageQuery.getWhereClause(), paramAbStorageQuery.getWhereArgs());
        paramAbDBDaoImpl.closeDatabase(false);
      }

      public void update()
      {
        if (AbSqliteStorage.this.retValue >= 0L)
          if (paramAbDataOperationListener != null)
            paramAbDataOperationListener.onSuccess(AbSqliteStorage.this.retValue);
        while (paramAbDataOperationListener == null)
          return;
        paramAbDataOperationListener.onFailure(AbSqliteStorage.this.errorCode101, AbSqliteStorage.this.errorMessage101);
      }
    };
    mAbTaskQueue.execute(localAbTaskItem);
  }

  public <T> void findData(final AbStorageQuery paramAbStorageQuery, final AbDBDaoImpl<T> paramAbDBDaoImpl, final AbSqliteStorageListener.AbDataInfoListener paramAbDataInfoListener)
  {
    final AbTaskItem localAbTaskItem = new AbTaskItem();
    localAbTaskItem.listener = new AbTaskListListener()
    {
      public void get()
      {
        paramAbDBDaoImpl.startReadableDatabase(false);
        if ((paramAbStorageQuery.getLimit() != -1) && (paramAbStorageQuery.getOffset() != -1));
        for (List localList = paramAbDBDaoImpl.queryList(null, paramAbStorageQuery.getWhereClause(), paramAbStorageQuery.getWhereArgs(), paramAbStorageQuery.getGroupBy(), paramAbStorageQuery.getHaving(), paramAbStorageQuery.getOrderBy() + " limit " + paramAbStorageQuery.getLimit() + " offset " + paramAbStorageQuery.getOffset(), null); ; localList = paramAbDBDaoImpl.queryList(null, paramAbStorageQuery.getWhereClause(), paramAbStorageQuery.getWhereArgs(), paramAbStorageQuery.getGroupBy(), paramAbStorageQuery.getHaving(), paramAbStorageQuery.getOrderBy(), null))
        {
          paramAbDBDaoImpl.closeDatabase(false);
          localAbTaskItem.setResult(localList);
          return;
        }
      }

      public void update(List<?> paramAnonymousList)
      {
        if (paramAbDataInfoListener != null)
          paramAbDataInfoListener.onSuccess(paramAnonymousList);
      }
    };
    mAbTaskQueue.execute(localAbTaskItem);
  }

  public <T> void insertData(final T paramT, final AbDBDaoImpl<T> paramAbDBDaoImpl, final AbSqliteStorageListener.AbDataInsertListener paramAbDataInsertListener)
  {
    if (paramT != null)
    {
      localAbTaskItem = new AbTaskItem();
      localAbTaskItem.listener = new AbTaskListener()
      {
        public void get()
        {
          paramAbDBDaoImpl.startWritableDatabase(false);
          AbSqliteStorage.this.retValue = paramAbDBDaoImpl.insert(paramT);
          paramAbDBDaoImpl.closeDatabase(false);
        }

        public void update()
        {
          if (AbSqliteStorage.this.retValue >= 0L)
            if (paramAbDataInsertListener != null)
              paramAbDataInsertListener.onSuccess(AbSqliteStorage.this.retValue);
          while (paramAbDataInsertListener == null)
            return;
          paramAbDataInsertListener.onFailure(AbSqliteStorage.this.errorCode101, AbSqliteStorage.this.errorMessage101);
        }
      };
      mAbTaskQueue.execute(localAbTaskItem);
    }
    while (paramAbDataInsertListener == null)
    {
      AbTaskItem localAbTaskItem;
      return;
    }
    paramAbDataInsertListener.onFailure(this.errorCode100, this.errorMessage100);
  }

  public <T> void insertData(final List<T> paramList, final AbDBDaoImpl<T> paramAbDBDaoImpl, final AbSqliteStorageListener.AbDataInsertListener paramAbDataInsertListener)
  {
    if (paramList != null)
    {
      localAbTaskItem = new AbTaskItem();
      localAbTaskItem.listener = new AbTaskListener()
      {
        public void get()
        {
          paramAbDBDaoImpl.startWritableDatabase(false);
          AbSqliteStorage.this.retValue = paramAbDBDaoImpl.insertList(paramList);
          paramAbDBDaoImpl.closeDatabase(false);
        }

        public void update()
        {
          if (AbSqliteStorage.this.retValue >= 0L)
            if (paramAbDataInsertListener != null)
              paramAbDataInsertListener.onSuccess(AbSqliteStorage.this.retValue);
          while (paramAbDataInsertListener == null)
            return;
          paramAbDataInsertListener.onFailure(AbSqliteStorage.this.errorCode101, AbSqliteStorage.this.errorMessage101);
        }
      };
      mAbTaskQueue.execute(localAbTaskItem);
    }
    while (paramAbDataInsertListener == null)
    {
      AbTaskItem localAbTaskItem;
      return;
    }
    paramAbDataInsertListener.onFailure(this.errorCode100, this.errorMessage100);
  }

  public void release()
  {
    if (mAbTaskQueue != null)
      mAbTaskQueue.quit();
  }

  public <T> void updateData(final T paramT, final AbDBDaoImpl<T> paramAbDBDaoImpl, final AbSqliteStorageListener.AbDataOperationListener paramAbDataOperationListener)
  {
    if (paramT != null)
    {
      localAbTaskItem = new AbTaskItem();
      localAbTaskItem.listener = new AbTaskListListener()
      {
        public void get()
        {
          paramAbDBDaoImpl.startWritableDatabase(false);
          AbSqliteStorage.this.retValue = paramAbDBDaoImpl.update(paramT);
          paramAbDBDaoImpl.closeDatabase(false);
        }

        public void update(List<?> paramAnonymousList)
        {
          if (AbSqliteStorage.this.retValue >= 0L)
            if (paramAbDataOperationListener != null)
              paramAbDataOperationListener.onSuccess(AbSqliteStorage.this.retValue);
          while (paramAbDataOperationListener == null)
            return;
          paramAbDataOperationListener.onFailure(AbSqliteStorage.this.errorCode101, AbSqliteStorage.this.errorMessage101);
        }
      };
      mAbTaskQueue.execute(localAbTaskItem);
    }
    while (paramAbDataOperationListener == null)
    {
      AbTaskItem localAbTaskItem;
      return;
    }
    paramAbDataOperationListener.onFailure(this.errorCode100, this.errorMessage100);
  }

  public <T> void updateData(final List<T> paramList, final AbDBDaoImpl<T> paramAbDBDaoImpl, final AbSqliteStorageListener.AbDataOperationListener paramAbDataOperationListener)
  {
    if (paramList != null)
    {
      localAbTaskItem = new AbTaskItem();
      localAbTaskItem.listener = new AbTaskListener()
      {
        public void get()
        {
          paramAbDBDaoImpl.startWritableDatabase(false);
          AbSqliteStorage.this.retValue = paramAbDBDaoImpl.updateList(paramList);
          paramAbDBDaoImpl.closeDatabase(false);
        }

        public void update()
        {
          if (AbSqliteStorage.this.retValue >= 0L)
            if (paramAbDataOperationListener != null)
              paramAbDataOperationListener.onSuccess(AbSqliteStorage.this.retValue);
          while (paramAbDataOperationListener == null)
            return;
          paramAbDataOperationListener.onFailure(AbSqliteStorage.this.errorCode101, AbSqliteStorage.this.errorMessage101);
        }
      };
      mAbTaskQueue.execute(localAbTaskItem);
    }
    while (paramAbDataOperationListener == null)
    {
      AbTaskItem localAbTaskItem;
      return;
    }
    paramAbDataOperationListener.onFailure(this.errorCode100, this.errorMessage100);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.storage.AbSqliteStorage
 * JD-Core Version:    0.6.2
 */