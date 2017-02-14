package com.ismartgo.app.tools;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ismartgo.app.bean.Address;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CityDBManager
{
  public static final String DB_NAME = "city.db";
  public static String DB_PATH;
  private Context context;
  private SQLiteDatabase database;

  public CityDBManager(Context paramContext)
  {
    this.context = paramContext;
    DB_PATH = paramContext.getApplicationContext().getFilesDir().getAbsolutePath() + "/Adress/city";
  }

  private void closeDatabase()
  {
    this.database.close();
  }

  private SQLiteDatabase openDatabase(String paramString)
  {
    try
    {
      InputStream localInputStream;
      FileOutputStream localFileOutputStream;
      byte[] arrayOfByte;
      if (!new File(paramString).exists())
      {
        new File(DB_PATH).mkdirs();
        localInputStream = this.context.getResources().openRawResource(2131034115);
        localFileOutputStream = new FileOutputStream(paramString);
        arrayOfByte = new byte[1024];
      }
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i <= 0)
        {
          localFileOutputStream.close();
          localInputStream.close();
          return SQLiteDatabase.openOrCreateDatabase(paramString, null);
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (FileNotFoundException paramString)
    {
      Log.e("Database", "File not found");
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      Log.e("Database", "IO exception");
      paramString.printStackTrace();
    }
    return null;
  }

  private void openDatabase()
  {
    this.database = openDatabase(DB_PATH + "/" + "city.db");
  }

  public String getAddressByAreaId(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    String str1 = "";
    Cursor localCursor = this.database.query("citylist", null, " type=3 and area_id='" + paramString + "'", null, null, null, null);
    paramString = str1;
    if (localCursor != null)
    {
      localCursor.moveToNext();
      str1 = localCursor.getString(localCursor.getColumnIndex("city_id"));
      String str2 = getProvinceIdByCityId(str1);
      paramString = getRovinceNameByRovinceId(str2) + "省" + getCityNameByCityId(str1) + "市" + localCursor.getString(localCursor.getColumnIndex("name"));
      Log.d("ww", str2 + "," + str1 + "," + paramString);
    }
    localCursor.close();
    closeDatabase();
    return paramString;
  }

  public String getAreaByAreaId(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    String str = "";
    Cursor localCursor = this.database.query("citylist", null, " type=3 and area_id='" + paramString + "'", null, null, null, null);
    paramString = str;
    if (localCursor != null)
    {
      localCursor.moveToNext();
      paramString = localCursor.getString(localCursor.getColumnIndex("name"));
      Log.d("ww", paramString);
    }
    localCursor.close();
    closeDatabase();
    return paramString;
  }

  public ArrayList<Address> getAreaList(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    paramString = this.database.query("citylist", null, " type=3 and city_id='" + paramString + "'", null, null, null, null);
    ArrayList localArrayList = new ArrayList();
    if (paramString != null);
    while (true)
    {
      if (!paramString.moveToNext())
      {
        paramString.close();
        closeDatabase();
        return localArrayList;
      }
      Address localAddress = new Address();
      localAddress.setId(paramString.getString(paramString.getColumnIndex("area_id")));
      localAddress.setName(paramString.getString(paramString.getColumnIndex("name")));
      localArrayList.add(localAddress);
    }
  }

  public String getCityId(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    String str = "";
    Object localObject = this.database;
    paramString = "name like '" + paramString + "' and type=2";
    localObject = ((SQLiteDatabase)localObject).query("citylist", new String[] { "city_id" }, paramString, null, null, null, null);
    paramString = str;
    if (localObject != null)
    {
      paramString = str;
      if (((Cursor)localObject).moveToFirst())
        paramString = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("city_id"));
    }
    ((Cursor)localObject).close();
    closeDatabase();
    return paramString;
  }

  public ArrayList<Address> getCityList(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    paramString = this.database.query("citylist", null, " type=2 and rovince_id='" + paramString + "'", null, null, null, null);
    ArrayList localArrayList = new ArrayList();
    if (paramString != null);
    while (true)
    {
      if (!paramString.moveToNext())
      {
        paramString.close();
        closeDatabase();
        return localArrayList;
      }
      Address localAddress = new Address();
      localAddress.setId(paramString.getString(paramString.getColumnIndex("city_id")));
      localAddress.setName(paramString.getString(paramString.getColumnIndex("name")));
      localArrayList.add(localAddress);
    }
  }

  public String getCityNameByAreaId(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    String str = "";
    Object localObject = this.database;
    paramString = "area_id = '" + paramString + "' and type=3";
    localObject = ((SQLiteDatabase)localObject).query("citylist", new String[] { "city_id" }, paramString, null, null, null, null);
    paramString = str;
    if (localObject != null)
    {
      if (((Cursor)localObject).moveToFirst())
        str = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("city_id"));
      paramString = getCityNameByCityId(str);
    }
    ((Cursor)localObject).close();
    closeDatabase();
    return paramString;
  }

  public String getCityNameByCityId(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    String str = "";
    Cursor localCursor = this.database.query("citylist", null, " type=2 and city_id='" + paramString + "'", null, null, null, null);
    paramString = str;
    if (localCursor != null);
    for (paramString = str; ; paramString = localCursor.getString(localCursor.getColumnIndex("name")))
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        closeDatabase();
        return paramString;
      }
  }

  public String getProvinceIdByCityId(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    String str = "";
    Cursor localCursor = this.database.query("citylist", null, " type=2 and city_id='" + paramString + "'", null, null, null, null);
    paramString = str;
    if (localCursor != null);
    for (paramString = str; ; paramString = localCursor.getString(localCursor.getColumnIndex("rovince_id")))
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        closeDatabase();
        return paramString;
      }
  }

  public ArrayList<Address> getRovinceList()
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    Cursor localCursor = this.database.query("citylist", null, " type=1 ", null, null, null, null);
    ArrayList localArrayList = new ArrayList();
    if (localCursor != null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        closeDatabase();
        return localArrayList;
      }
      Address localAddress = new Address();
      localAddress.setId(localCursor.getString(localCursor.getColumnIndex("rovince_id")));
      localAddress.setName(localCursor.getString(localCursor.getColumnIndex("name")));
      localArrayList.add(localAddress);
    }
  }

  public String getRovinceNameByRovinceId(String paramString)
  {
    if ((this.database == null) || (!this.database.isOpen()))
      openDatabase();
    String str = "";
    Cursor localCursor = this.database.query("citylist", null, " type=1 and rovince_id='" + paramString + "'", null, null, null, null);
    paramString = str;
    if (localCursor != null);
    for (paramString = str; ; paramString = localCursor.getString(localCursor.getColumnIndex("name")))
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        closeDatabase();
        return paramString;
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.CityDBManager
 * JD-Core Version:    0.6.2
 */