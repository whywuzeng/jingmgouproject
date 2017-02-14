package com.ab.download;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.ab.db.AbBasicDBDao;
import com.ab.db.MyDBHelper;

public class DownFileDao extends AbBasicDBDao
{
  public static Context mContext = null;
  public static DownFileDao mDownFileDao = null;
  private MyDBHelper openHelper;

  public DownFileDao(Context paramContext)
  {
    this.openHelper = new MyDBHelper(paramContext);
  }

  public static DownFileDao getInstance(Context paramContext)
  {
    mContext = paramContext;
    if (mDownFileDao == null)
      mDownFileDao = new DownFileDao(paramContext);
    return mDownFileDao;
  }

  public long delete(String paramString)
  {
    long l = -1L;
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      localSQLiteDatabase = this.openHelper.getWritableDatabase();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      int i = localSQLiteDatabase.delete("FILEDOWN", "DOWNURL = ? ", new String[] { paramString });
      l = i;
    }
    catch (Exception paramString)
    {
    }
    finally
    {
      SQLiteDatabase localSQLiteDatabase;
      closeDB(null, localObject2);
    }
    throw paramString;
  }

  // ERROR //
  public DownFile getDownFile(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 9
    //   8: aconst_null
    //   9: astore 8
    //   11: aconst_null
    //   12: astore 7
    //   14: aload 8
    //   16: astore 6
    //   18: aload 9
    //   20: astore_3
    //   21: aload_0
    //   22: getfield 27	com/ab/download/DownFileDao:openHelper	Lcom/ab/db/MyDBHelper;
    //   25: invokevirtual 63	com/ab/db/MyDBHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   28: astore 4
    //   30: aload 4
    //   32: astore 5
    //   34: aload 8
    //   36: astore 6
    //   38: aload 4
    //   40: astore_2
    //   41: aload 9
    //   43: astore_3
    //   44: aload 4
    //   46: ldc 42
    //   48: aconst_null
    //   49: ldc 65
    //   51: iconst_1
    //   52: anewarray 46	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: aload_1
    //   58: aastore
    //   59: aconst_null
    //   60: aconst_null
    //   61: aconst_null
    //   62: invokevirtual 69	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   65: astore_1
    //   66: aload 4
    //   68: astore 5
    //   70: aload_1
    //   71: astore 6
    //   73: aload 4
    //   75: astore_2
    //   76: aload_1
    //   77: astore_3
    //   78: aload_1
    //   79: invokeinterface 75 1 0
    //   84: ifeq +146 -> 230
    //   87: aload 4
    //   89: astore 5
    //   91: aload_1
    //   92: astore 6
    //   94: aload 4
    //   96: astore_2
    //   97: aload_1
    //   98: astore_3
    //   99: new 77	com/ab/download/DownFile
    //   102: dup
    //   103: invokespecial 78	com/ab/download/DownFile:<init>	()V
    //   106: astore 7
    //   108: aload 7
    //   110: aload_0
    //   111: ldc 80
    //   113: aload_1
    //   114: invokevirtual 84	com/ab/download/DownFileDao:getIntColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)I
    //   117: invokevirtual 88	com/ab/download/DownFile:set_ID	(I)V
    //   120: aload 7
    //   122: aload_0
    //   123: ldc 90
    //   125: aload_1
    //   126: invokevirtual 94	com/ab/download/DownFileDao:getStringColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)Ljava/lang/String;
    //   129: invokevirtual 98	com/ab/download/DownFile:setName	(Ljava/lang/String;)V
    //   132: aload 7
    //   134: aload_0
    //   135: ldc 100
    //   137: aload_1
    //   138: invokevirtual 94	com/ab/download/DownFileDao:getStringColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)Ljava/lang/String;
    //   141: invokevirtual 103	com/ab/download/DownFile:setDescription	(Ljava/lang/String;)V
    //   144: aload 7
    //   146: aload_0
    //   147: ldc 105
    //   149: aload_1
    //   150: invokevirtual 94	com/ab/download/DownFileDao:getStringColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)Ljava/lang/String;
    //   153: invokevirtual 108	com/ab/download/DownFile:setPakageName	(Ljava/lang/String;)V
    //   156: aload 7
    //   158: aload_0
    //   159: ldc 110
    //   161: aload_1
    //   162: invokevirtual 94	com/ab/download/DownFileDao:getStringColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)Ljava/lang/String;
    //   165: invokevirtual 113	com/ab/download/DownFile:setDownUrl	(Ljava/lang/String;)V
    //   168: aload 7
    //   170: aload_0
    //   171: ldc 115
    //   173: aload_1
    //   174: invokevirtual 94	com/ab/download/DownFileDao:getStringColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)Ljava/lang/String;
    //   177: invokevirtual 118	com/ab/download/DownFile:setDownPath	(Ljava/lang/String;)V
    //   180: aload 7
    //   182: aload_0
    //   183: ldc 120
    //   185: aload_1
    //   186: invokevirtual 84	com/ab/download/DownFileDao:getIntColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)I
    //   189: invokevirtual 123	com/ab/download/DownFile:setState	(I)V
    //   192: aload 7
    //   194: aload_0
    //   195: ldc 125
    //   197: aload_1
    //   198: invokevirtual 84	com/ab/download/DownFileDao:getIntColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)I
    //   201: i2l
    //   202: invokevirtual 129	com/ab/download/DownFile:setDownLength	(J)V
    //   205: aload 7
    //   207: aload_0
    //   208: ldc 131
    //   210: aload_1
    //   211: invokevirtual 84	com/ab/download/DownFileDao:getIntColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)I
    //   214: i2l
    //   215: invokevirtual 134	com/ab/download/DownFile:setTotalLength	(J)V
    //   218: aload 7
    //   220: aload_0
    //   221: ldc 136
    //   223: aload_1
    //   224: invokevirtual 94	com/ab/download/DownFileDao:getStringColumnValue	(Ljava/lang/String;Landroid/database/Cursor;)Ljava/lang/String;
    //   227: invokevirtual 139	com/ab/download/DownFile:setSuffix	(Ljava/lang/String;)V
    //   230: aload_0
    //   231: aload_1
    //   232: aload 4
    //   234: invokevirtual 55	com/ab/download/DownFileDao:closeDB	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   237: aload 7
    //   239: areturn
    //   240: astore 7
    //   242: aload 6
    //   244: astore_1
    //   245: aload 5
    //   247: astore 4
    //   249: aload 4
    //   251: astore_2
    //   252: aload_1
    //   253: astore_3
    //   254: aload 7
    //   256: invokevirtual 58	java/lang/Exception:printStackTrace	()V
    //   259: aload_0
    //   260: aload_1
    //   261: aload 4
    //   263: invokevirtual 55	com/ab/download/DownFileDao:closeDB	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   266: aconst_null
    //   267: areturn
    //   268: astore 4
    //   270: aload_3
    //   271: astore_1
    //   272: aload 4
    //   274: astore_3
    //   275: aload_0
    //   276: aload_1
    //   277: aload_2
    //   278: invokevirtual 55	com/ab/download/DownFileDao:closeDB	(Landroid/database/Cursor;Landroid/database/sqlite/SQLiteDatabase;)V
    //   281: aload_3
    //   282: athrow
    //   283: astore_3
    //   284: aload 4
    //   286: astore_2
    //   287: goto -12 -> 275
    //   290: astore 7
    //   292: goto -43 -> 249
    //
    // Exception table:
    //   from	to	target	type
    //   21	30	240	java/lang/Exception
    //   44	66	240	java/lang/Exception
    //   78	87	240	java/lang/Exception
    //   99	108	240	java/lang/Exception
    //   21	30	268	finally
    //   44	66	268	finally
    //   78	87	268	finally
    //   99	108	268	finally
    //   254	259	268	finally
    //   108	230	283	finally
    //   108	230	290	java/lang/Exception
  }

  public long save(DownFile paramDownFile)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    long l1 = 0L;
    try
    {
      localSQLiteDatabase = this.openHelper.getWritableDatabase();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      ContentValues localContentValues = new ContentValues();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("NAME", paramDownFile.getName());
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("DESCRIPTION", paramDownFile.getDescription());
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("PAKAGENAME", paramDownFile.getPakageName());
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("DOWNURL", paramDownFile.getDownUrl());
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("DOWNPATH", paramDownFile.getDownPath());
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("STATE", Integer.valueOf(paramDownFile.getState()));
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("DOWNLENGTH", Long.valueOf(paramDownFile.getDownLength()));
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("TOTALLENGTH", Long.valueOf(paramDownFile.getTotalLength()));
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("DOWNSUFFIX", paramDownFile.getSuffix());
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      long l2 = localSQLiteDatabase.insert("FILEDOWN", null, localContentValues);
      l1 = l2;
    }
    catch (Exception paramDownFile)
    {
    }
    finally
    {
      SQLiteDatabase localSQLiteDatabase;
      closeDB(null, localObject2);
    }
    throw paramDownFile;
  }

  public long update(DownFile paramDownFile)
  {
    long l = -1L;
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      localSQLiteDatabase = this.openHelper.getWritableDatabase();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      String str = paramDownFile.getDownUrl();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      ContentValues localContentValues = new ContentValues();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("STATE", Integer.valueOf(paramDownFile.getState()));
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("DOWNLENGTH", Long.valueOf(paramDownFile.getDownLength()));
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localContentValues.put("TOTALLENGTH", Long.valueOf(paramDownFile.getTotalLength()));
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      int i = localSQLiteDatabase.update("FILEDOWN", localContentValues, "DOWNURL = ? ", new String[] { str });
      l = i;
    }
    catch (Exception paramDownFile)
    {
    }
    finally
    {
      SQLiteDatabase localSQLiteDatabase;
      closeDB(null, localObject2);
    }
    throw paramDownFile;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.download.DownFileDao
 * JD-Core Version:    0.6.2
 */