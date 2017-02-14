package com.ab.db.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import com.ab.util.AbStrUtil;
import java.io.File;
import java.io.IOException;

public abstract class AbSDSQLiteOpenHelper extends SQLiteOpenHelper
{
  private static final String TAG = "SDSQLiteOpenHelper";
  private final Context mContext;
  private SQLiteDatabase mDatabase = null;
  private final SQLiteDatabase.CursorFactory mFactory;
  private boolean mIsInitializing = false;
  private final String mName;
  private final int mNewVersion;
  private final String mPath;

  public AbSDSQLiteOpenHelper(Context paramContext, String paramString1, String paramString2, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString2, paramCursorFactory, paramInt);
    if (paramInt < 1)
      throw new IllegalArgumentException("Version must be >= 1, was " + paramInt);
    this.mContext = paramContext;
    this.mPath = paramString1;
    this.mName = paramString2;
    this.mFactory = paramCursorFactory;
    this.mNewVersion = paramInt;
  }

  public void close()
  {
    try
    {
      if (this.mIsInitializing)
        throw new IllegalStateException("Closed during initialization");
    }
    finally
    {
    }
    if ((this.mDatabase != null) && (this.mDatabase.isOpen()))
    {
      this.mDatabase.close();
      this.mDatabase = null;
    }
  }

  public File getDatabasePath(String paramString1, String paramString2)
  {
    paramString1 = AbStrUtil.parseEmpty(paramString1);
    paramString1 = new File(Environment.getExternalStorageDirectory() + "/" + paramString1);
    paramString2 = new File(paramString1.getPath(), paramString2);
    if (!paramString1.exists())
      paramString1.mkdirs();
    if (!paramString2.exists());
    try
    {
      paramString2.createNewFile();
      return paramString2;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
    return paramString2;
  }

  public SQLiteDatabase getReadableDatabase()
  {
    while (true)
    {
      try
      {
        if ((this.mDatabase != null) && (this.mDatabase.isOpen()))
        {
          SQLiteDatabase localSQLiteDatabase1 = this.mDatabase;
          return localSQLiteDatabase1;
        }
        if (this.mIsInitializing)
          throw new IllegalStateException("数据库已被占用getReadableDatabase()");
      }
      finally
      {
      }
      Object localObject2 = null;
      try
      {
        SQLiteDatabase localSQLiteDatabase2 = getWritableDatabase();
        localObject2 = localSQLiteDatabase2;
        this.mDatabase = localSQLiteDatabase2;
        label64: localObject2 = this.mDatabase;
      }
      catch (Exception localException)
      {
        Object localObject5 = localObject2;
        Object localObject6 = localObject2;
        try
        {
          this.mIsInitializing = true;
          localObject5 = localObject2;
          localObject6 = localObject2;
          localObject7 = getDatabasePath(this.mPath, this.mName).getPath();
          localObject5 = localObject2;
          localObject6 = localObject2;
          localSQLiteDatabase3 = SQLiteDatabase.openDatabase((String)localObject7, this.mFactory, 1);
          localObject5 = localSQLiteDatabase3;
          localObject6 = localSQLiteDatabase3;
          if (localSQLiteDatabase3.getVersion() != this.mNewVersion)
          {
            localObject5 = localSQLiteDatabase3;
            localObject6 = localSQLiteDatabase3;
            throw new SQLiteException("不能更新只读数据库的版本 from version " + localSQLiteDatabase3.getVersion() + " to " + this.mNewVersion + ": " + (String)localObject7);
          }
        }
        catch (SQLiteException localSQLiteException)
        {
          SQLiteDatabase localSQLiteDatabase3;
          this.mIsInitializing = false;
          if ((localObject5 == null) || (localObject5 == this.mDatabase))
            break label64;
          localObject5.close();
          break label64;
          localObject5 = localSQLiteDatabase3;
          localObject6 = localSQLiteDatabase3;
          onOpen(localSQLiteDatabase3);
          localObject5 = localSQLiteDatabase3;
          localObject6 = localSQLiteDatabase3;
          this.mDatabase = localSQLiteDatabase3;
          localObject5 = localSQLiteDatabase3;
          localObject6 = localSQLiteDatabase3;
          Object localObject7 = this.mDatabase;
          this.mIsInitializing = false;
          Object localObject3 = localObject7;
          if (localSQLiteDatabase3 == null)
            continue;
          localObject3 = localObject7;
          if (localSQLiteDatabase3 == this.mDatabase)
            continue;
          localSQLiteDatabase3.close();
          localObject3 = localObject7;
        }
        finally
        {
          this.mIsInitializing = false;
          if ((localObject6 != null) && (localObject6 != this.mDatabase))
            localObject6.close();
        }
      }
    }
  }

  // ERROR //
  public SQLiteDatabase getWritableDatabase()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   6: ifnull +32 -> 38
    //   9: aload_0
    //   10: getfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   13: invokevirtual 71	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   16: ifeq +22 -> 38
    //   19: aload_0
    //   20: getfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   23: invokevirtual 155	android/database/sqlite/SQLiteDatabase:isReadOnly	()Z
    //   26: ifne +12 -> 38
    //   29: aload_0
    //   30: getfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   33: astore_3
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_3
    //   37: areturn
    //   38: aload_0
    //   39: getfield 29	com/ab/db/orm/AbSDSQLiteOpenHelper:mIsInitializing	Z
    //   42: ifeq +18 -> 60
    //   45: new 62	java/lang/IllegalStateException
    //   48: dup
    //   49: ldc 157
    //   51: invokespecial 65	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   54: athrow
    //   55: astore_2
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_2
    //   59: athrow
    //   60: aconst_null
    //   61: astore_2
    //   62: aload_2
    //   63: astore_3
    //   64: aload_0
    //   65: iconst_1
    //   66: putfield 29	com/ab/db/orm/AbSDSQLiteOpenHelper:mIsInitializing	Z
    //   69: aload_2
    //   70: astore_3
    //   71: aload_0
    //   72: getfield 53	com/ab/db/orm/AbSDSQLiteOpenHelper:mName	Ljava/lang/String;
    //   75: ifnonnull +102 -> 177
    //   78: aload_2
    //   79: astore_3
    //   80: aconst_null
    //   81: invokestatic 161	android/database/sqlite/SQLiteDatabase:create	(Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   84: astore_2
    //   85: aload_2
    //   86: astore_3
    //   87: aload_2
    //   88: invokevirtual 141	android/database/sqlite/SQLiteDatabase:getVersion	()I
    //   91: istore_1
    //   92: aload_2
    //   93: astore_3
    //   94: iload_1
    //   95: aload_0
    //   96: getfield 57	com/ab/db/orm/AbSDSQLiteOpenHelper:mNewVersion	I
    //   99: if_icmpeq +36 -> 135
    //   102: aload_2
    //   103: astore_3
    //   104: aload_2
    //   105: invokevirtual 164	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   108: iload_1
    //   109: ifne +96 -> 205
    //   112: aload_0
    //   113: aload_2
    //   114: invokevirtual 167	com/ab/db/orm/AbSDSQLiteOpenHelper:onCreate	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   117: aload_2
    //   118: aload_0
    //   119: getfield 57	com/ab/db/orm/AbSDSQLiteOpenHelper:mNewVersion	I
    //   122: invokevirtual 171	android/database/sqlite/SQLiteDatabase:setVersion	(I)V
    //   125: aload_2
    //   126: invokevirtual 174	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   129: aload_2
    //   130: astore_3
    //   131: aload_2
    //   132: invokevirtual 177	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   135: aload_2
    //   136: astore_3
    //   137: aload_0
    //   138: aload_2
    //   139: invokevirtual 152	com/ab/db/orm/AbSDSQLiteOpenHelper:onOpen	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   142: aload_0
    //   143: iconst_0
    //   144: putfield 29	com/ab/db/orm/AbSDSQLiteOpenHelper:mIsInitializing	Z
    //   147: iconst_1
    //   148: ifeq +118 -> 266
    //   151: aload_0
    //   152: getfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   155: astore_3
    //   156: aload_3
    //   157: ifnull +10 -> 167
    //   160: aload_0
    //   161: getfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   164: invokevirtual 73	android/database/sqlite/SQLiteDatabase:close	()V
    //   167: aload_0
    //   168: aload_2
    //   169: putfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   172: aload_2
    //   173: astore_3
    //   174: goto -140 -> 34
    //   177: aload_2
    //   178: astore_3
    //   179: aload_0
    //   180: aload_0
    //   181: getfield 51	com/ab/db/orm/AbSDSQLiteOpenHelper:mPath	Ljava/lang/String;
    //   184: aload_0
    //   185: getfield 53	com/ab/db/orm/AbSDSQLiteOpenHelper:mName	Ljava/lang/String;
    //   188: invokevirtual 133	com/ab/db/orm/AbSDSQLiteOpenHelper:getDatabasePath	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
    //   191: invokevirtual 105	java/io/File:getPath	()Ljava/lang/String;
    //   194: aload_0
    //   195: getfield 55	com/ab/db/orm/AbSDSQLiteOpenHelper:mFactory	Landroid/database/sqlite/SQLiteDatabase$CursorFactory;
    //   198: invokestatic 181	android/database/sqlite/SQLiteDatabase:openOrCreateDatabase	(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   201: astore_2
    //   202: goto -117 -> 85
    //   205: aload_0
    //   206: aload_2
    //   207: iload_1
    //   208: aload_0
    //   209: getfield 57	com/ab/db/orm/AbSDSQLiteOpenHelper:mNewVersion	I
    //   212: invokevirtual 185	com/ab/db/orm/AbSDSQLiteOpenHelper:onUpgrade	(Landroid/database/sqlite/SQLiteDatabase;II)V
    //   215: goto -98 -> 117
    //   218: astore 4
    //   220: aload_2
    //   221: astore_3
    //   222: aload_2
    //   223: invokevirtual 177	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   226: aload_2
    //   227: astore_3
    //   228: aload 4
    //   230: athrow
    //   231: astore_2
    //   232: aload_0
    //   233: iconst_0
    //   234: putfield 29	com/ab/db/orm/AbSDSQLiteOpenHelper:mIsInitializing	Z
    //   237: iconst_0
    //   238: ifeq +43 -> 281
    //   241: aload_0
    //   242: getfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   245: astore 4
    //   247: aload 4
    //   249: ifnull +10 -> 259
    //   252: aload_0
    //   253: getfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   256: invokevirtual 73	android/database/sqlite/SQLiteDatabase:close	()V
    //   259: aload_0
    //   260: aload_3
    //   261: putfield 27	com/ab/db/orm/AbSDSQLiteOpenHelper:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   264: aload_2
    //   265: athrow
    //   266: aload_2
    //   267: astore_3
    //   268: aload_2
    //   269: ifnull -235 -> 34
    //   272: aload_2
    //   273: invokevirtual 73	android/database/sqlite/SQLiteDatabase:close	()V
    //   276: aload_2
    //   277: astore_3
    //   278: goto -244 -> 34
    //   281: aload_3
    //   282: ifnull -18 -> 264
    //   285: aload_3
    //   286: invokevirtual 73	android/database/sqlite/SQLiteDatabase:close	()V
    //   289: goto -25 -> 264
    //   292: astore 4
    //   294: goto -35 -> 259
    //   297: astore_3
    //   298: goto -131 -> 167
    //
    // Exception table:
    //   from	to	target	type
    //   2	34	55	finally
    //   38	55	55	finally
    //   142	147	55	finally
    //   151	156	55	finally
    //   160	167	55	finally
    //   167	172	55	finally
    //   232	237	55	finally
    //   241	247	55	finally
    //   252	259	55	finally
    //   259	264	55	finally
    //   264	266	55	finally
    //   272	276	55	finally
    //   285	289	55	finally
    //   112	117	218	finally
    //   117	129	218	finally
    //   205	215	218	finally
    //   64	69	231	finally
    //   71	78	231	finally
    //   80	85	231	finally
    //   87	92	231	finally
    //   94	102	231	finally
    //   104	108	231	finally
    //   131	135	231	finally
    //   137	142	231	finally
    //   179	202	231	finally
    //   222	226	231	finally
    //   228	231	231	finally
    //   252	259	292	java/lang/Exception
    //   160	167	297	java/lang/Exception
  }

  public abstract void onCreate(SQLiteDatabase paramSQLiteDatabase);

  public void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
  }

  public abstract void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.orm.AbSDSQLiteOpenHelper
 * JD-Core Version:    0.6.2
 */