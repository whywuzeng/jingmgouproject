package com.mob.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SharePrefrenceHelper
{
  private static SharePrefrenceHelper instance;
  private Context context;
  private SharedPreferences prefrence;

  private SharePrefrenceHelper(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }

  public static SharePrefrenceHelper getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new SharePrefrenceHelper(paramContext);
    return instance;
  }

  public Object get(String paramString)
  {
    try
    {
      paramString = getString(paramString);
      if (TextUtils.isEmpty(paramString))
        return null;
      paramString = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(paramString, 2)));
      Object localObject = paramString.readObject();
      paramString.close();
      return localObject;
    }
    catch (Throwable paramString)
    {
      Ln.w(paramString);
    }
    return null;
  }

  public boolean getBoolean(String paramString)
  {
    return this.prefrence.getBoolean(paramString, false);
  }

  public float getFloat(String paramString)
  {
    return this.prefrence.getFloat(paramString, 0.0F);
  }

  public int getInt(String paramString)
  {
    return this.prefrence.getInt(paramString, 0);
  }

  public long getLong(String paramString)
  {
    return this.prefrence.getLong(paramString, 0L);
  }

  public String getString(String paramString)
  {
    return this.prefrence.getString(paramString, "");
  }

  public void open(String paramString)
  {
    open(paramString, 0);
  }

  public void open(String paramString, int paramInt)
  {
    paramString = paramString + "_" + paramInt;
    this.prefrence = this.context.getSharedPreferences(paramString, 0);
  }

  public void put(String paramString, Object paramObject)
  {
    if (paramObject == null)
      return;
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramObject);
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      putString(paramString, Base64.encodeToString(localByteArrayOutputStream.toByteArray(), 2));
      return;
    }
    catch (Throwable paramString)
    {
      Ln.w(paramString);
    }
  }

  public void putBoolean(String paramString, Boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.prefrence.edit();
    localEditor.putBoolean(paramString, paramBoolean.booleanValue());
    localEditor.commit();
  }

  public void putFloat(String paramString, Float paramFloat)
  {
    SharedPreferences.Editor localEditor = this.prefrence.edit();
    localEditor.putFloat(paramString, paramFloat.floatValue());
    localEditor.commit();
  }

  public void putInt(String paramString, Integer paramInteger)
  {
    SharedPreferences.Editor localEditor = this.prefrence.edit();
    localEditor.putInt(paramString, paramInteger.intValue());
    localEditor.commit();
  }

  public void putLong(String paramString, Long paramLong)
  {
    SharedPreferences.Editor localEditor = this.prefrence.edit();
    localEditor.putLong(paramString, paramLong.longValue());
    localEditor.commit();
  }

  public void putString(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.prefrence.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public void remove(String paramString)
  {
    SharedPreferences.Editor localEditor = this.prefrence.edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.utils.SharePrefrenceHelper
 * JD-Core Version:    0.6.2
 */