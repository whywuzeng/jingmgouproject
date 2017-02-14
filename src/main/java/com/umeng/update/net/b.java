package com.umeng.update.net;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import u.upd.n;

public class b
{
  private static final String a = b.class.getName();
  private static final String b = "umeng_download_task_list";
  private static final String c = "UMENG_DATA";
  private static final String d = "cp";
  private static final String e = "url";
  private static final String f = "progress";
  private static final String g = "last_modified";
  private static final String h = "extra";
  private static Context i;
  private static final String j = "yyyy-MM-dd HH:mm:ss";
  private a k = new a(i);

  public static b a(Context paramContext)
  {
    if ((i == null) && (paramContext == null))
      throw new NullPointerException();
    if (i == null)
      i = paramContext;
    return b.a;
  }

  public List<String> a(String paramString)
  {
    paramString = this.k.getReadableDatabase().query("umeng_download_task_list", new String[] { "url" }, "cp=?", new String[] { paramString }, null, null, null, "1");
    ArrayList localArrayList = new ArrayList();
    paramString.moveToFirst();
    while (!paramString.isAfterLast())
    {
      localArrayList.add(paramString.getString(0));
      paramString.moveToNext();
    }
    paramString.close();
    return localArrayList;
  }

  public void a(int paramInt)
  {
    try
    {
      Date localDate = new Date(new Date().getTime() - paramInt * 1000);
      String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localDate);
      str = " DELETE FROM umeng_download_task_list WHERE strftime('yyyy-MM-dd HH:mm:ss', last_modified)<=strftime('yyyy-MM-dd HH:mm:ss', '" + str + "')";
      this.k.getWritableDatabase().execSQL(str);
      u.upd.b.c(a, "clearOverdueTasks(" + paramInt + ")" + " remove all tasks before " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localDate));
      return;
    }
    catch (Exception localException)
    {
      u.upd.b.b(a, localException.getMessage());
    }
  }

  public void a(String paramString1, String paramString2, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("progress", Integer.valueOf(paramInt));
    localContentValues.put("last_modified", n.a());
    this.k.getWritableDatabase().update("umeng_download_task_list", localContentValues, "cp=? and url=?", new String[] { paramString1, paramString2 });
    u.upd.b.c(a, "updateProgress(" + paramString1 + ", " + paramString2 + ", " + paramInt + ")");
  }

  public void a(String paramString1, String paramString2, String paramString3)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("extra", paramString3);
    localContentValues.put("last_modified", n.a());
    this.k.getWritableDatabase().update("umeng_download_task_list", localContentValues, "cp=? and url=?", new String[] { paramString1, paramString2 });
    u.upd.b.c(a, "updateExtra(" + paramString1 + ", " + paramString2 + ", " + paramString3 + ")");
  }

  // ERROR //
  public boolean a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 181	android/content/ContentValues
    //   3: dup
    //   4: invokespecial 182	android/content/ContentValues:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: ldc 21
    //   13: aload_1
    //   14: invokevirtual 198	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   17: aload 6
    //   19: ldc 24
    //   21: aload_2
    //   22: invokevirtual 198	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   25: aload 6
    //   27: ldc 27
    //   29: iconst_0
    //   30: invokestatic 188	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   33: invokevirtual 192	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   36: aload 6
    //   38: ldc 30
    //   40: invokestatic 196	u/upd/n:a	()Ljava/lang/String;
    //   43: invokevirtual 198	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   46: aload_0
    //   47: getfield 61	com/umeng/update/net/b:k	Lcom/umeng/update/net/b$a;
    //   50: invokevirtual 75	com/umeng/update/net/b$a:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   53: ldc 15
    //   55: iconst_1
    //   56: anewarray 77	java/lang/String
    //   59: dup
    //   60: iconst_0
    //   61: ldc 27
    //   63: aastore
    //   64: ldc 200
    //   66: iconst_2
    //   67: anewarray 77	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: aload_1
    //   73: aastore
    //   74: dup
    //   75: iconst_1
    //   76: aload_2
    //   77: aastore
    //   78: aconst_null
    //   79: aconst_null
    //   80: aconst_null
    //   81: ldc 81
    //   83: invokevirtual 87	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   86: astore 7
    //   88: aload 7
    //   90: invokeinterface 216 1 0
    //   95: ifle +58 -> 153
    //   98: getstatic 50	com/umeng/update/net/b:a	Ljava/lang/String;
    //   101: new 141	java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   108: ldc 218
    //   110: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: aload_1
    //   114: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: ldc 208
    //   119: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: aload_2
    //   123: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc 220
    //   128: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: ldc 222
    //   133: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: invokestatic 173	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   142: iconst_0
    //   143: istore_3
    //   144: aload 7
    //   146: invokeinterface 115 1 0
    //   151: iload_3
    //   152: ireturn
    //   153: aload_0
    //   154: getfield 61	com/umeng/update/net/b:k	Lcom/umeng/update/net/b$a;
    //   157: invokevirtual 156	com/umeng/update/net/b$a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   160: ldc 15
    //   162: aconst_null
    //   163: aload 6
    //   165: invokevirtual 226	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   168: lstore 4
    //   170: lload 4
    //   172: ldc2_w 227
    //   175: lcmp
    //   176: ifne +57 -> 233
    //   179: iconst_0
    //   180: istore_3
    //   181: getstatic 50	com/umeng/update/net/b:a	Ljava/lang/String;
    //   184: new 141	java/lang/StringBuilder
    //   187: dup
    //   188: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   191: ldc 218
    //   193: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: aload_1
    //   197: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: ldc 208
    //   202: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: aload_2
    //   206: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: ldc 220
    //   211: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: ldc 230
    //   216: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: lload 4
    //   221: invokevirtual 233	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   224: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokestatic 173	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   230: goto -86 -> 144
    //   233: iconst_1
    //   234: istore_3
    //   235: goto -54 -> 181
    //   238: astore 6
    //   240: iconst_0
    //   241: istore_3
    //   242: getstatic 50	com/umeng/update/net/b:a	Ljava/lang/String;
    //   245: new 141	java/lang/StringBuilder
    //   248: dup
    //   249: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   252: ldc 218
    //   254: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: aload_1
    //   258: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: ldc 208
    //   263: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: aload_2
    //   267: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: ldc 220
    //   272: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload 6
    //   277: invokevirtual 176	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   280: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   286: aload 6
    //   288: invokestatic 236	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   291: iload_3
    //   292: ireturn
    //   293: astore 6
    //   295: goto -53 -> 242
    //   298: astore 6
    //   300: goto -58 -> 242
    //
    // Exception table:
    //   from	to	target	type
    //   46	142	238	java/lang/Exception
    //   153	170	238	java/lang/Exception
    //   181	230	293	java/lang/Exception
    //   144	151	298	java/lang/Exception
  }

  public int b(String paramString1, String paramString2)
  {
    paramString1 = this.k.getReadableDatabase().query("umeng_download_task_list", new String[] { "progress" }, "cp=? and url=?", new String[] { paramString1, paramString2 }, null, null, null, "1");
    if (paramString1.getCount() > 0)
      paramString1.moveToFirst();
    for (int m = paramString1.getInt(0); ; m = -1)
    {
      paramString1.close();
      return m;
    }
  }

  public String c(String paramString1, String paramString2)
  {
    Object localObject = null;
    paramString2 = this.k.getReadableDatabase().query("umeng_download_task_list", new String[] { "extra" }, "cp=? and url=?", new String[] { paramString1, paramString2 }, null, null, null, "1");
    paramString1 = localObject;
    if (paramString2.getCount() > 0)
    {
      paramString2.moveToFirst();
      paramString1 = paramString2.getString(0);
    }
    paramString2.close();
    return paramString1;
  }

  public Date d(String paramString1, String paramString2)
  {
    Object localObject2 = null;
    Cursor localCursor = this.k.getReadableDatabase().query("umeng_download_task_list", new String[] { "last_modified" }, "cp=? and url=?", new String[] { paramString1, paramString2 }, null, null, null, null);
    Object localObject1 = localObject2;
    if (localCursor.getCount() > 0)
    {
      localCursor.moveToFirst();
      localObject1 = localCursor.getString(0);
      u.upd.b.c(a, "getLastModified(" + paramString1 + ", " + paramString2 + "): " + (String)localObject1);
    }
    try
    {
      localObject1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)localObject1);
      localCursor.close();
      return localObject1;
    }
    catch (Exception paramString1)
    {
      while (true)
      {
        u.upd.b.c(a, paramString1.getMessage());
        localObject1 = localObject2;
      }
    }
  }

  public void e(String paramString1, String paramString2)
  {
    this.k.getWritableDatabase().delete("umeng_download_task_list", "cp=? and url=?", new String[] { paramString1, paramString2 });
    u.upd.b.c(a, "delete(" + paramString1 + ", " + paramString2 + ")");
  }

  public void finalize()
  {
    this.k.close();
  }

  class a extends SQLiteOpenHelper
  {
    private static final int b = 2;
    private static final String c = "CREATE TABLE umeng_download_task_list (cp TEXT, url TEXT, progress INTEGER, extra TEXT, last_modified TEXT, UNIQUE (cp,url) ON CONFLICT ABORT);";

    a(Context arg2)
    {
      super("UMENG_DATA", null, 2);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      u.upd.b.c(b.a(), "CREATE TABLE umeng_download_task_list (cp TEXT, url TEXT, progress INTEGER, extra TEXT, last_modified TEXT, UNIQUE (cp,url) ON CONFLICT ABORT);");
      paramSQLiteDatabase.execSQL("CREATE TABLE umeng_download_task_list (cp TEXT, url TEXT, progress INTEGER, extra TEXT, last_modified TEXT, UNIQUE (cp,url) ON CONFLICT ABORT);");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
    }
  }

  private static class b
  {
    public static final b a = new b(null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.net.b
 * JD-Core Version:    0.6.2
 */