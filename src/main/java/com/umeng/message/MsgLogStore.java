package com.umeng.message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.g;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import org.json.JSONObject;

public class MsgLogStore
{
  public static final String ActionType = "ActionType";
  public static final String AppLaunchAt = "AppLaunchAt";
  public static final String MsgId = "MsgId";
  public static final String MsgStatus = "MsgStatus";
  public static final String MsgType = "MsgType";
  public static final String SerialNo = "SerialNo";
  public static final String TaskId = "TaskId";
  public static final String Time = "Time";
  public static final String UpdateResponse = "UpdateResponse";
  private static final String a = MsgLogStore.class.getName();
  private static MsgLogStore b;
  private static final String f = " And ";
  private static final String g = " Asc ";
  private static final String h = " Desc ";
  private static final String i = "MsgLogStore.db";
  private static final int j = 4;
  private static final String k = "MsgLogStore";
  private static final String l = "MsgLogIdTypeStore";
  private static final String m = "MsgLogStoreForAgoo";
  private static final String n = "MsgLogIdTypeStoreForAgoo";
  private static final String o = "MsgConfigInfo";
  private SQLiteDatabase c;
  private a d;
  private Context e;

  private MsgLogStore(Context paramContext)
  {
    this.e = paramContext.getApplicationContext();
    this.d = new a(paramContext);
    this.c = this.d.getWritableDatabase();
  }

  private void a(File paramFile)
  {
    try
    {
      paramFile = new JSONObject(b(paramFile));
      addLog(paramFile.optString("msg_id"), paramFile.optInt("action_type"), paramFile.optLong("ts"));
      return;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
  }

  // ERROR //
  private String b(File paramFile)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 153	java/io/BufferedReader
    //   3: dup
    //   4: new 155	java/io/FileReader
    //   7: dup
    //   8: aload_1
    //   9: invokespecial 157	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   12: invokespecial 160	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_2
    //   16: new 162	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 163	java/lang/StringBuilder:<init>	()V
    //   23: astore_1
    //   24: aload_2
    //   25: invokevirtual 166	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull +23 -> 53
    //   33: aload_1
    //   34: aload_3
    //   35: invokevirtual 170	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: goto -15 -> 24
    //   42: astore_1
    //   43: aload_2
    //   44: ifnull +7 -> 51
    //   47: aload_2
    //   48: invokevirtual 173	java/io/BufferedReader:close	()V
    //   51: aload_1
    //   52: athrow
    //   53: aload_1
    //   54: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: astore_1
    //   58: aload_2
    //   59: ifnull +7 -> 66
    //   62: aload_2
    //   63: invokevirtual 173	java/io/BufferedReader:close	()V
    //   66: aload_1
    //   67: areturn
    //   68: astore_2
    //   69: aload_2
    //   70: invokevirtual 177	java/io/IOException:printStackTrace	()V
    //   73: aload_1
    //   74: areturn
    //   75: astore_2
    //   76: aload_2
    //   77: invokevirtual 177	java/io/IOException:printStackTrace	()V
    //   80: goto -29 -> 51
    //   83: astore_1
    //   84: aconst_null
    //   85: astore_2
    //   86: goto -43 -> 43
    //
    // Exception table:
    //   from	to	target	type
    //   16	24	42	finally
    //   24	29	42	finally
    //   33	39	42	finally
    //   53	58	42	finally
    //   62	66	68	java/io/IOException
    //   47	51	75	java/io/IOException
    //   0	16	83	finally
  }

  private void b()
  {
    if (MessageSharedPrefs.getInstance(this.e).h())
      return;
    File[] arrayOfFile = this.e.getCacheDir().listFiles(new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return (!TextUtils.isEmpty(paramAnonymousString)) && (paramAnonymousString.startsWith("umeng_message_log_cache_"));
      }
    });
    if (arrayOfFile != null)
    {
      int i2 = arrayOfFile.length;
      int i1 = 0;
      while (i1 < i2)
      {
        File localFile = arrayOfFile[i1];
        a(localFile);
        localFile.delete();
        i1 += 1;
      }
    }
    MessageSharedPrefs.getInstance(this.e).i();
  }

  public static MsgLogStore getInstance(Context paramContext)
  {
    if (b == null)
    {
      b = new MsgLogStore(paramContext);
      b.b();
    }
    return b;
  }

  public boolean addLog(String paramString, int paramInt, long paramLong)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    paramString = new MsgLog(paramString, paramInt, paramLong);
    if (this.c.insert("MsgLogStore", null, paramString.getContentValues()) != -1L);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean addLogForAgoo(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    if (TextUtils.isEmpty(paramString1));
    do
    {
      return false;
      paramString1 = new MsgLogForAgoo(paramString1, paramString2, paramString3, paramLong);
    }
    while (this.c.insert("MsgLogStoreForAgoo", null, paramString1.getContentValues()) == -1L);
    return true;
  }

  public boolean addLogIdType(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1));
    do
    {
      return false;
      paramString1 = new MsgLogIdType(paramString1, paramString2);
    }
    while (this.c.insert("MsgLogIdTypeStore", null, paramString1.getContentValues()) == -1L);
    return true;
  }

  public boolean addLogIdTypeForAgoo(String paramString1, String paramString2, String paramString3)
  {
    if (TextUtils.isEmpty(paramString1));
    do
    {
      return false;
      paramString1 = new MsgLogIdTypeForAgoo(paramString1, paramString2, paramString3);
    }
    while (this.c.insert("MsgLogIdTypeStoreForAgoo", null, paramString1.getContentValues()) == -1L);
    return true;
  }

  public long getMsgConfigInfo_AppLaunchAt()
  {
    Cursor localCursor = this.c.query("MsgConfigInfo", new String[] { "AppLaunchAt" }, null, null, null, null, null, null);
    boolean bool = localCursor.moveToFirst();
    long l1 = 0L;
    if (bool)
      l1 = localCursor.getLong(localCursor.getColumnIndex("AppLaunchAt"));
    localCursor.close();
    Log.d(a, "appLaunchAt=" + l1);
    return l1;
  }

  public int getMsgConfigInfo_SerialNo()
  {
    Cursor localCursor = this.c.query("MsgConfigInfo", new String[] { "SerialNo" }, null, null, null, null, null, null);
    if (localCursor.moveToFirst());
    for (int i1 = localCursor.getInt(localCursor.getColumnIndex("SerialNo")); ; i1 = 0)
    {
      localCursor.close();
      return i1;
    }
  }

  public Object getMsgConfigInfo_UpdateResponse()
  {
    String str = null;
    Cursor localCursor = this.c.query("MsgConfigInfo", new String[] { "UpdateResponse" }, null, null, null, null, null, null);
    if (localCursor.moveToFirst())
      str = localCursor.getString(localCursor.getColumnIndex("UpdateResponse"));
    localCursor.close();
    Log.d(a, "updateResponse=" + str);
    return g.f(str);
  }

  public MsgLog getMsgLog(String paramString)
  {
    Object localObject = null;
    if (TextUtils.isEmpty(paramString))
      return null;
    Cursor localCursor = this.c.query("MsgLogStore", null, "MsgId=?", new String[] { paramString }, null, null, null, null);
    paramString = localObject;
    if (localCursor.moveToFirst())
      paramString = new MsgLog(localCursor);
    localCursor.close();
    return paramString;
  }

  public MsgLogForAgoo getMsgLogForAgoo(String paramString)
  {
    Object localObject = null;
    if (TextUtils.isEmpty(paramString))
      return null;
    Cursor localCursor = this.c.query("MsgLogStoreForAgoo", null, "MsgId=?", new String[] { paramString }, null, null, null, null);
    paramString = localObject;
    if (localCursor.moveToFirst())
      paramString = new MsgLogForAgoo(localCursor);
    localCursor.close();
    return paramString;
  }

  public ArrayList<MsgLogIdType> getMsgLogIdTypes()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.c.query("MsgLogIdTypeStore", null, null, null, null, null, "MsgId Asc ", null);
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLogIdType(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public ArrayList<MsgLogIdType> getMsgLogIdTypes(int paramInt)
  {
    if (paramInt < 1)
      return null;
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.c.query("MsgLogIdTypeStore", null, null, null, null, null, "MsgId Asc ", paramInt + "");
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLogIdType(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public ArrayList<MsgLogIdTypeForAgoo> getMsgLogIdTypesForAgoo()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.c.query("MsgLogIdTypeStoreForAgoo", null, null, null, null, null, "MsgId Asc ", null);
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLogIdTypeForAgoo(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public ArrayList<MsgLogIdTypeForAgoo> getMsgLogIdTypesForAgoo(int paramInt)
  {
    if (paramInt < 1)
      return null;
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.c.query("MsgLogIdTypeStoreForAgoo", null, null, null, null, null, "MsgId Asc ", paramInt + "");
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLogIdTypeForAgoo(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public ArrayList<MsgLog> getMsgLogs()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.c.query("MsgLogStore", null, null, null, null, null, "Time Asc ", null);
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLog(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public ArrayList<MsgLog> getMsgLogs(int paramInt)
  {
    if (paramInt < 1)
      return null;
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.c.query("MsgLogStore", null, null, null, null, null, "Time Asc ", paramInt + "");
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLog(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public ArrayList<MsgLogForAgoo> getMsgLogsForAgoo()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.c.query("MsgLogStoreForAgoo", null, null, null, null, null, "Time Asc ", null);
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLogForAgoo(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public ArrayList<MsgLogForAgoo> getMsgLogsForAgoo(int paramInt)
  {
    if (paramInt < 1)
      return null;
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.c.query("MsgLogStoreForAgoo", null, null, null, null, null, "Time Asc ", paramInt + "");
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLogForAgoo(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public boolean removeLog(String paramString, int paramInt)
  {
    boolean bool = true;
    if (TextUtils.isEmpty(paramString))
      return false;
    String str = paramInt + "";
    if (this.c.delete("MsgLogStore", "MsgId=? And ActionType=?", new String[] { paramString, str }) == 1);
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public boolean removeLogForAgoo(String paramString1, String paramString2)
  {
    boolean bool = true;
    if (TextUtils.isEmpty(paramString1))
      return false;
    if (this.c.delete("MsgLogStoreForAgoo", "MsgId=? And MsgStatus=?", new String[] { paramString1, paramString2 }) == 1);
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public boolean removeLogIdType(String paramString)
  {
    boolean bool = true;
    if (TextUtils.isEmpty(paramString))
      return false;
    if (this.c.delete("MsgLogIdTypeStore", "MsgId=?", new String[] { paramString }) == 1);
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public boolean removeLogIdTypeForAgoo(String paramString)
  {
    boolean bool = true;
    if (TextUtils.isEmpty(paramString))
      return false;
    if (this.c.delete("MsgLogIdTypeStoreForAgoo", "MsgId=?", new String[] { paramString }) == 1);
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public void setMsgConfigInfo_AppLaunchAt(long paramLong)
  {
    String str = "update MsgConfigInfo set AppLaunchAt = " + paramLong;
    this.c.execSQL(str);
  }

  public void setMsgConfigInfo_SerialNo(int paramInt)
  {
    String str = "update MsgConfigInfo set SerialNo = " + paramInt;
    this.c.execSQL(str);
  }

  public void setMsgConfigInfo_UpdateResponse(Object paramObject)
  {
    paramObject = g.a(paramObject);
    paramObject = "update MsgConfigInfo set UpdateResponse =  '" + paramObject + "'";
    this.c.execSQL(paramObject);
  }

  public class MsgLog
  {
    public int actionType;
    public String msgId;
    public long time;

    public MsgLog(Cursor arg2)
    {
      Object localObject;
      this.msgId = localObject.getString(localObject.getColumnIndex("MsgId"));
      this.time = localObject.getLong(localObject.getColumnIndex("Time"));
      this.actionType = localObject.getInt(localObject.getColumnIndex("ActionType"));
    }

    public MsgLog(String paramInt, int paramLong, long arg4)
    {
      this.msgId = paramInt;
      this.actionType = paramLong;
      Object localObject;
      this.time = localObject;
    }

    public ContentValues getContentValues()
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("MsgId", this.msgId);
      localContentValues.put("Time", Long.valueOf(this.time));
      localContentValues.put("ActionType", Integer.valueOf(this.actionType));
      return localContentValues;
    }
  }

  public class MsgLogForAgoo
  {
    public String msgId;
    public String msgStatus;
    public String taskId;
    public long time;

    public MsgLogForAgoo(Cursor arg2)
    {
      Object localObject;
      this.msgId = localObject.getString(localObject.getColumnIndex("MsgId"));
      this.taskId = localObject.getString(localObject.getColumnIndex("TaskId"));
      this.msgStatus = localObject.getString(localObject.getColumnIndex("MsgStatus"));
      this.time = localObject.getLong(localObject.getColumnIndex("Time"));
    }

    public MsgLogForAgoo(String paramString1, String paramString2, String paramLong, long arg5)
    {
      this.msgId = paramString1;
      this.taskId = paramString2;
      this.msgStatus = paramLong;
      Object localObject;
      this.time = localObject;
    }

    public ContentValues getContentValues()
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("MsgId", this.msgId);
      localContentValues.put("TaskId", this.taskId);
      localContentValues.put("MsgStatus", this.msgStatus);
      localContentValues.put("Time", Long.valueOf(this.time));
      return localContentValues;
    }
  }

  public class MsgLogIdType
  {
    public String msgId;
    public String msgType;

    public MsgLogIdType(Cursor arg2)
    {
      Object localObject;
      this.msgId = localObject.getString(localObject.getColumnIndex("MsgId"));
      this.msgType = localObject.getString(localObject.getColumnIndex("MsgType"));
    }

    public MsgLogIdType(String paramString1, String arg3)
    {
      this.msgId = paramString1;
      Object localObject;
      this.msgType = localObject;
    }

    public ContentValues getContentValues()
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("MsgId", this.msgId);
      localContentValues.put("MsgType", this.msgType);
      return localContentValues;
    }
  }

  public class MsgLogIdTypeForAgoo
  {
    public String msgId;
    public String msgStatus;
    public String taskId;

    public MsgLogIdTypeForAgoo(Cursor arg2)
    {
      Object localObject;
      this.msgId = localObject.getString(localObject.getColumnIndex("MsgId"));
      this.taskId = localObject.getString(localObject.getColumnIndex("TaskId"));
      this.msgStatus = localObject.getString(localObject.getColumnIndex("MsgStatus"));
    }

    public MsgLogIdTypeForAgoo(String paramString1, String paramString2, String arg4)
    {
      this.msgId = paramString1;
      this.taskId = paramString2;
      Object localObject;
      this.msgStatus = localObject;
    }

    public ContentValues getContentValues()
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("MsgId", this.msgId);
      localContentValues.put("TaskId", this.taskId);
      localContentValues.put("MsgStatus", this.msgStatus);
      return localContentValues;
    }
  }

  private class a extends SQLiteOpenHelper
  {
    public a(Context arg2)
    {
      super("MsgLogStore.db", null, 4);
    }

    private boolean a(SQLiteDatabase paramSQLiteDatabase, String paramString)
    {
      if (TextUtils.isEmpty(paramString));
      while (true)
      {
        return false;
        try
        {
          paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type = 'table' and name = '" + paramString.trim() + "'", null);
          if (paramSQLiteDatabase.moveToNext())
          {
            int i = paramSQLiteDatabase.getInt(0);
            if (i > 0)
              return true;
          }
        }
        catch (Exception paramSQLiteDatabase)
        {
        }
      }
      return false;
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("create table if not exists MsgLogStore (MsgId varchar, ActionType Integer, Time long, PRIMARY KEY(MsgId, ActionType))");
      paramSQLiteDatabase.execSQL("create table if not exists MsgLogIdTypeStore (MsgId varchar, MsgType varchar, PRIMARY KEY(MsgId))");
      paramSQLiteDatabase.execSQL("create table if not exists MsgLogStoreForAgoo (MsgId varchar, TaskId varchar, MsgStatus varchar, Time long, PRIMARY KEY(MsgId, MsgStatus))");
      paramSQLiteDatabase.execSQL("create table if not exists MsgLogIdTypeStoreForAgoo (MsgId varchar, TaskId varchar, MsgStatus varchar, PRIMARY KEY(MsgId))");
      boolean bool = a(paramSQLiteDatabase, "MsgConfigInfo");
      paramSQLiteDatabase.execSQL("create table if not exists MsgConfigInfo (SerialNo integer default 1, AppLaunchAt long default 0, UpdateResponse varchar default NULL)");
      if (!bool)
        paramSQLiteDatabase.execSQL("insert into MsgConfigInfo(SerialNo, AppLaunchAt) values (?, ?)", new Object[] { Integer.valueOf(MessageSharedPrefs.getInstance(MsgLogStore.a(MsgLogStore.this)).getSerialNo()), Long.valueOf(MessageSharedPrefs.getInstance(MsgLogStore.a(MsgLogStore.this)).getAppLaunchLogSentAt()) });
      while (true)
      {
        Log.d(MsgLogStore.a(), "onCreate");
        return;
        paramSQLiteDatabase.execSQL("alter table MsgConfigInfo add column UpdateResponse varchar");
      }
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      onCreate(paramSQLiteDatabase);
      Log.d(MsgLogStore.a(), "onUpgrade");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.MsgLogStore
 * JD-Core Version:    0.6.2
 */