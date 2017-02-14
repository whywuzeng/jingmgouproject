package com.umeng.message.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UmengLocalNotificationStore
{
  private static final String a = UmengLocalNotificationStore.class.getName();
  private static UmengLocalNotificationStore b;
  private static final String f = "UmengLocalNotificationStore.db";
  private static final int g = 1;
  private static final String h = "UmengLocalNotification";
  private static final String i = "UmengNotificationBuilder";
  private SQLiteDatabase c;
  private a d;
  private Context e;

  private UmengLocalNotificationStore(Context paramContext)
  {
    this.e = paramContext.getApplicationContext();
    this.d = new a(paramContext);
    this.c = this.d.getWritableDatabase();
  }

  public static UmengLocalNotificationStore getInstance(Context paramContext)
  {
    try
    {
      if (b == null)
        b = new UmengLocalNotificationStore(paramContext);
      paramContext = b;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public boolean addLocalNotification(UmengLocalNotification paramUmengLocalNotification)
  {
    if ((TextUtils.isEmpty(paramUmengLocalNotification.getId())) || (TextUtils.isEmpty(paramUmengLocalNotification.getNotificationBuilder().getId())));
    long l1;
    long l2;
    do
    {
      return false;
      l1 = this.c.insert("UmengLocalNotification", null, paramUmengLocalNotification.getContentValues());
      l2 = this.c.insert("UmengNotificationBuilder", null, paramUmengLocalNotification.getNotificationBuilder().getContentValues());
    }
    while ((l1 == -1L) || (l2 == -1L));
    return true;
  }

  public void deleteAllLocalNotifications()
  {
    Iterator localIterator = findAllLocalNotifications().iterator();
    while (localIterator.hasNext())
    {
      UmengLocalNotification localUmengLocalNotification = (UmengLocalNotification)localIterator.next();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = localUmengLocalNotification.getId();
      this.c.delete("UmengLocalNotification", "id=?", arrayOfString);
      this.c.delete("UmengNotificationBuilder", "localnotification_id=?", arrayOfString);
    }
  }

  public boolean deleteLocalNotification(String paramString)
  {
    boolean bool = true;
    if (TextUtils.isEmpty(paramString))
      return false;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    int j = this.c.delete("UmengLocalNotification", "id=?", arrayOfString);
    int k = this.c.delete("UmengNotificationBuilder", "localnotification_id=?", arrayOfString);
    if ((j == 1) && (k == 1));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public List<UmengLocalNotification> findAllLocalNotifications()
  {
    Cursor localCursor = this.c.query("UmengLocalNotification", null, null, null, null, null, null, null);
    boolean bool = localCursor.moveToFirst();
    ArrayList localArrayList = new ArrayList();
    Object localObject = null;
    while (bool)
    {
      UmengLocalNotification localUmengLocalNotification = new UmengLocalNotification(localCursor);
      localObject = localUmengLocalNotification.getId();
      localObject = this.c.query("UmengNotificationBuilder", null, "localnotification_id=?", new String[] { localObject }, null, null, null, null);
      if (((Cursor)localObject).moveToFirst())
        localUmengLocalNotification.setNotificationBuilder(new UmengNotificationBuilder((Cursor)localObject));
      localArrayList.add(localUmengLocalNotification);
      bool = localCursor.moveToNext();
    }
    localCursor.close();
    if (localObject != null)
      ((Cursor)localObject).close();
    return localArrayList;
  }

  public UmengLocalNotification findLocalNotification(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    Cursor localCursor = this.c.query("UmengLocalNotification", null, "id=?", new String[] { paramString }, null, null, null, null);
    boolean bool1 = localCursor.moveToFirst();
    if (bool1);
    for (UmengLocalNotification localUmengLocalNotification = new UmengLocalNotification(localCursor); ; localUmengLocalNotification = null)
    {
      localCursor.close();
      paramString = this.c.query("UmengNotificationBuilder", null, "localnotification_id=?", new String[] { paramString }, null, null, null, null);
      boolean bool2 = paramString.moveToFirst();
      if ((bool1) && (bool2))
        localUmengLocalNotification.setNotificationBuilder(new UmengNotificationBuilder(paramString));
      return localUmengLocalNotification;
    }
  }

  public List<UmengLocalNotification> findLocalNotifications(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    ArrayList localArrayList = new ArrayList();
    paramString = "title LIKE '%" + paramString + "%' OR content LIKE '%" + paramString + "%'";
    Cursor localCursor = this.c.query("UmengLocalNotification", null, paramString, null, null, null, null, null);
    boolean bool = localCursor.moveToFirst();
    paramString = null;
    while (bool)
    {
      UmengLocalNotification localUmengLocalNotification = new UmengLocalNotification(localCursor);
      paramString = localUmengLocalNotification.getId();
      paramString = this.c.query("UmengNotificationBuilder", null, "localnotification_id=?", new String[] { paramString }, null, null, null, null);
      if (paramString.moveToFirst())
        localUmengLocalNotification.setNotificationBuilder(new UmengNotificationBuilder(paramString));
      localArrayList.add(localUmengLocalNotification);
      bool = localCursor.moveToNext();
    }
    localCursor.close();
    if (paramString != null)
      paramString.close();
    return localArrayList;
  }

  public boolean updateLocalNotification(UmengLocalNotification paramUmengLocalNotification)
  {
    boolean bool = true;
    if ((TextUtils.isEmpty(paramUmengLocalNotification.getId())) || (TextUtils.isEmpty(paramUmengLocalNotification.getNotificationBuilder().getId())))
      bool = false;
    long l1;
    long l2;
    do
    {
      return bool;
      l1 = this.c.update("UmengLocalNotification", paramUmengLocalNotification.getContentValues(), "id=?", new String[] { paramUmengLocalNotification.getId() });
      l2 = this.c.update("UmengNotificationBuilder", paramUmengLocalNotification.getNotificationBuilder().getContentValues(), "id=?", new String[] { paramUmengLocalNotification.getNotificationBuilder().getId() });
    }
    while ((l1 != -1L) && (l2 != -1L));
    return false;
  }

  private class a extends SQLiteOpenHelper
  {
    public a(Context arg2)
    {
      super("UmengLocalNotificationStore.db", null, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("create table if not exists UmengLocalNotification (id varchar, year integer, month integer, day integer, hour integer, minute integer, second integer, repeating_num integer, repeating_unit integer, repeating_interval integer, special_day integer, title varchar, content varchar, ticker varchar, PRIMARY KEY(id))");
      paramSQLiteDatabase.execSQL("create table if not exists UmengNotificationBuilder (id varchar, localnotification_id varchar, flags integer, defaults integer, smallicon_drawable varchar, largeicon_drawable varchar, sound_drawable varchar, play_vibrate integer, play_lights integer, play_sound integer, screen_on integer, layout_id integer, layout_title_id integer, layout_content_id integer, layout_icon_id integer, layout_icon_drawable_id, FOREIGN KEY(localnotification_id) REFERENCES UmengLocalNotification(id), PRIMARY KEY(id))");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      onCreate(paramSQLiteDatabase);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.local.UmengLocalNotificationStore
 * JD-Core Version:    0.6.2
 */