package com.ismartgo.beacon.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ismartgo.beacon.IBeaconClass.iBeacon;
import com.ismartgo.beacon.pojo.BeaconActivityInfo;
import com.ismartgo.beacon.util.CalendarUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBDao
{
  private static SQLiteDatabase db;
  private static DBDao dbDao;
  private static DBOpenHelper helper;

  public static DBDao getInstance(Context paramContext)
  {
    if (dbDao == null);
    try
    {
      dbDao = new DBDao();
      helper = new DBOpenHelper(paramContext);
      db = helper.getWritableDatabase();
      return dbDao;
    }
    finally
    {
    }
    throw paramContext;
  }

  public void delNoActivityBean(String paramString)
  {
    if (db != null)
    {
      paramString = "delete from beacon_info  where day_of_time < '" + paramString + "' and " + "flag" + " = " + 0;
      db.execSQL(paramString);
    }
  }

  public void delNoActivitySameBean(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    try
    {
      if (db != null)
      {
        paramString1 = "delete from beacon_info where uuid = '" + paramString1 + "' and " + "major" + " = " + paramInt1 + " and " + "minor" + " = " + paramInt2 + " and " + "day_of_time" + " = '" + paramString2 + "' and " + "activity_id" + " ='" + -1 + "'";
        paramString1 = db.rawQuery(paramString1, null);
        if ((paramString1 != null) && (paramString1.getCount() > 1))
        {
          paramString1.moveToLast();
          paramInt1 = paramString1.getInt(paramString1.getColumnIndex("_id"));
          db.delete("beacon_info", "_id=?", new String[] { String.valueOf(paramInt1) });
        }
        paramString1.close();
      }
      return;
    }
    finally
    {
    }
    throw paramString1;
  }

  public void delOldBean(String paramString)
  {
    try
    {
      if (db != null)
      {
        paramString = "delete from beacon_info  where day_of_time < '" + paramString + "'";
        db.execSQL(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void delSameBean(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    try
    {
      if (db != null)
      {
        paramString1 = "delete from beacon_info where uuid = '" + paramString1 + "' and " + "major" + " = " + paramInt1 + " and " + "minor" + " = " + paramInt2 + " and " + "day_of_time" + " = '" + paramString2 + "' and " + "activity_id" + " ='" + -1 + "'";
        db.execSQL(paramString1);
      }
      return;
    }
    finally
    {
      paramString1 = finally;
    }
    throw paramString1;
  }

  public long insertBeacon(Object paramObject)
  {
    try
    {
      ContentValues localContentValues;
      long l;
      if (db != null)
        if ((paramObject instanceof BeaconActivityInfo))
        {
          paramObject = (BeaconActivityInfo)paramObject;
          localContentValues = new ContentValues();
          localContentValues.put("uuid", paramObject.getUuid());
          localContentValues.put("major", Integer.valueOf(paramObject.getMajor()));
          localContentValues.put("minor", Integer.valueOf(paramObject.getMinor()));
          localContentValues.put("scan_time", CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_SECOND));
          localContentValues.put("push_time", CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_SECOND));
          localContentValues.put("click_time", "");
          localContentValues.put("day_of_time", CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY));
          localContentValues.put("flag", Integer.valueOf(1));
          localContentValues.put("submit_status", Integer.valueOf(1));
          localContentValues.put("activity_id", Integer.valueOf(paramObject.getActivityId()));
          localContentValues.put("beacon_by_activity_id", Integer.valueOf(paramObject.getId()));
          l = db.insert("beacon_info", null, localContentValues);
        }
      while (true)
      {
        return l;
        if ((paramObject instanceof IBeaconClass.iBeacon))
        {
          paramObject = (IBeaconClass.iBeacon)paramObject;
          localContentValues = new ContentValues();
          localContentValues.put("uuid", paramObject.proximityUuid);
          localContentValues.put("major", Integer.valueOf(paramObject.major));
          localContentValues.put("minor", Integer.valueOf(paramObject.minor));
          localContentValues.put("scan_time", CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_SECOND));
          localContentValues.put("push_time", "");
          localContentValues.put("click_time", "");
          localContentValues.put("day_of_time", CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY));
          localContentValues.put("submit_status", Integer.valueOf(1));
          localContentValues.put("flag", Integer.valueOf(0));
          localContentValues.put("activity_id", Integer.valueOf(-1));
          localContentValues.put("beacon_by_activity_id", Integer.valueOf(-1));
          l = db.insert("beacon_info", null, localContentValues);
        }
        else
        {
          l = 0L;
        }
      }
    }
    finally
    {
    }
    throw paramObject;
  }

  public int queryActivityInfo(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3)
  {
    int i = 0;
    int j = 0;
    try
    {
      Object localObject;
      if (db != null)
      {
        localObject = "select * from beacon_info where activity_id = '" + paramInt3 + "'";
        localObject = db.rawQuery((String)localObject, null);
        if ((localObject == null) || (((Cursor)localObject).getCount() <= 0))
          break label216;
        paramInt3 = j;
        if (((Cursor)localObject).moveToNext())
          break label94;
      }
      while (true)
      {
        ((Cursor)localObject).close();
        for (i = paramInt3; ; i = 1)
        {
          return i;
          label94: String str1 = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("uuid"));
          paramInt3 = ((Cursor)localObject).getInt(((Cursor)localObject).getColumnIndex("major"));
          i = ((Cursor)localObject).getInt(((Cursor)localObject).getColumnIndex("minor"));
          String str2 = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("day_of_time"));
          if ((!paramString1.equals(str1)) || (paramInt3 != paramInt1) || (i != paramInt2) || (!str2.equals(paramString2)))
            break;
          ((Cursor)localObject).close();
        }
        paramInt3 = 3;
        break;
        label216: paramInt3 = 2;
      }
    }
    finally
    {
    }
    throw paramString1;
  }

  public boolean queryBeacon(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if (db != null)
      {
        paramString1 = "select * from beacon_info where uuid = '" + paramString1 + "' and " + "major" + " = " + paramInt1 + " and " + "minor" + " = " + paramInt2 + " and " + "day_of_time" + " = '" + paramString2 + "'";
        paramString1 = db.rawQuery(paramString1, null);
        bool1 = bool2;
        if (paramString1 != null)
        {
          bool1 = bool2;
          if (paramString1.getCount() > 0)
          {
            bool1 = bool2;
            if (paramString1.moveToFirst())
            {
              paramString2 = paramString1.getString(paramString1.getColumnIndex("scan_time"));
              if (CalendarUtil.getDatePoor(CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_SECOND), paramString2) <= 30)
                break label193;
              Log.e("smartgo_beacon", "超过30");
              paramString1.close();
            }
          }
        }
      }
      for (bool1 = bool2; ; bool1 = true)
      {
        return bool1;
        label193: paramString1.close();
      }
    }
    finally
    {
    }
    throw paramString1;
  }

  public JSONArray queryOldBean(String paramString)
  {
    JSONObject localJSONObject = null;
    try
    {
      paramString = "select * from beacon_info where day_of_time <= '" + paramString + "' and (" + "flag" + " = " + 1 + " or " + "flag" + " = " + 2 + ") and " + "submit_status" + "=" + 1;
      Cursor localCursor = db.rawQuery(paramString, null);
      paramString = localJSONObject;
      if (localCursor != null)
      {
        paramString = localJSONObject;
        if (localCursor.getCount() > 0)
          paramString = new JSONArray();
      }
      try
      {
        while (true)
        {
          boolean bool = localCursor.moveToNext();
          if (!bool)
            return paramString;
          localJSONObject = new JSONObject();
          try
          {
            localJSONObject.put("beaconActivityId", localCursor.getInt(localCursor.getColumnIndex("beacon_by_activity_id")));
            if ((localCursor.getInt(localCursor.getColumnIndex("flag")) != 1) || (localCursor.getInt(localCursor.getColumnIndex("submit_status")) != 2))
              break label281;
            localJSONObject.put("flag", 3);
            localJSONObject.put("pushTime", localCursor.getString(localCursor.getColumnIndex("push_time")));
            localJSONObject.put("clickTime", localCursor.getString(localCursor.getColumnIndex("click_time")));
            paramString.put(localJSONObject);
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
      }
      finally
      {
      }
      label277: throw paramString;
      label281: localJSONException.put("flag", localCursor.getInt(localCursor.getColumnIndex("flag")));
    }
    finally
    {
      break label277;
    }
  }

  public boolean queryPush(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    try
    {
      if (db != null)
      {
        paramString1 = "select * from beacon_info where uuid = '" + paramString1 + "' and " + "major" + " = " + paramInt1 + " and " + "minor" + " = " + paramInt2 + " and " + "day_of_time" + " = '" + paramString2 + "'";
        paramString1 = db.rawQuery(paramString1, null);
        Log.e("smartgo_beacon", "推送次数： " + paramString1.getCount());
        bool1 = bool2;
        if (paramString1 != null)
        {
          bool1 = bool2;
          if (paramString1.getCount() > 0)
          {
            bool1 = bool2;
            if (paramString1.getCount() >= 3)
            {
              paramInt1 = 0;
              bool1 = paramString1.moveToNext();
              if (bool1)
                break label191;
            }
          }
        }
      }
      for (bool1 = bool2; ; bool1 = false)
      {
        return bool1;
        label191: int i = paramString1.getInt(paramString1.getColumnIndex("flag"));
        if (i != 1)
        {
          paramInt2 = paramInt1;
          if (i != 2);
        }
        else
        {
          paramInt2 = paramInt1 + 1;
          Log.e("smartgo_beacon", "推送次数： " + paramInt2);
        }
        paramInt1 = paramInt2;
        if (paramInt2 < 3)
          break;
      }
    }
    finally
    {
    }
    throw paramString1;
  }

  public long updateBean(BeaconActivityInfo paramBeaconActivityInfo)
  {
    try
    {
      if (db != null)
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("click_time", CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_SECOND));
        localContentValues.put("flag", Integer.valueOf(2));
        localContentValues.put("submit_status", Integer.valueOf(1));
        int i = db.update("beacon_info", localContentValues, "activity_id=?", new String[] { String.valueOf(paramBeaconActivityInfo.getActivityId()) });
        l = i;
        return l;
      }
      long l = 0L;
    }
    finally
    {
    }
  }

  public int updateBeanFlag(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3, int paramInt4)
  {
    int i = 0;
    try
    {
      if (db != null)
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("push_time", CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_SECOND));
        localContentValues.put("flag", Integer.valueOf(1));
        localContentValues.put("activity_id", Integer.valueOf(paramInt3));
        localContentValues.put("beacon_by_activity_id", Integer.valueOf(paramInt4));
        i = db.update("beacon_info", localContentValues, "uuid =? and major =? and minor =? and day_of_time =? and activity_id =?", new String[] { paramString1, String.valueOf(paramInt1), String.valueOf(paramInt2), paramString2, String.valueOf(-1) });
      }
      return i;
    }
    finally
    {
      paramString1 = finally;
    }
    throw paramString1;
  }

  public void updateNoActivityBean(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    try
    {
      if (db != null)
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("scan_time", CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_SECOND));
        db.update("beacon_info", localContentValues, "uuid =? and major =? and minor =? and day_of_time =? and activity_id =?", new String[] { paramString1, String.valueOf(paramInt1), String.valueOf(paramInt2), paramString2, String.valueOf(-1) });
      }
      return;
    }
    finally
    {
      paramString1 = finally;
    }
    throw paramString1;
  }

  public void updateOldBean(String paramString)
  {
    try
    {
      if (db != null)
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("submit_status", Integer.valueOf(2));
        db.update("beacon_info", localContentValues, "day_of_time <=? and (flag = ? or flag = ? )", new String[] { paramString, String.valueOf(1), String.valueOf(2) });
      }
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.db.DBDao
 * JD-Core Version:    0.6.2
 */