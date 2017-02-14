package com.mob.tools.utils;

import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.HashMap;

public class LocalDB
{
  private File dbFile;
  private HashMap<String, Object> map;

  private void commit()
  {
    if (this.map == null);
    while (this.dbFile == null)
      return;
    while (true)
    {
      try
      {
        if (!this.dbFile.getParentFile().exists())
          this.dbFile.getParentFile().mkdirs();
        synchronized (this.map)
        {
          Object localObject1 = new FileOutputStream(this.dbFile);
          if (((FileOutputStream)localObject1).getChannel().tryLock() != null)
          {
            localObject1 = new ObjectOutputStream((OutputStream)localObject1);
            ((ObjectOutputStream)localObject1).writeObject(this.map);
            ((ObjectOutputStream)localObject1).flush();
            ((ObjectOutputStream)localObject1).close();
            return;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        Ln.w(localThrowable);
        return;
      }
      localObject2.close();
    }
  }

  private Object get(String paramString)
  {
    if (this.map == null)
      return null;
    return this.map.get(paramString);
  }

  private void put(String paramString, Object paramObject)
  {
    if (this.map == null)
      this.map = new HashMap();
    this.map.put(paramString, paramObject);
  }

  public boolean getBoolean(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return false;
    if ((paramString instanceof Boolean))
      return ((Boolean)paramString).booleanValue();
    return false;
  }

  public float getFloat(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0.0F;
    if ((paramString instanceof Integer))
      return ((Float)paramString).floatValue();
    return 0.0F;
  }

  public int getInt(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0;
    if ((paramString instanceof Integer))
      return ((Integer)paramString).intValue();
    return 0;
  }

  public long getLong(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0L;
    if ((paramString instanceof Long))
      return ((Long)paramString).longValue();
    return 0L;
  }

  public Object getObject(String paramString)
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

  public String getString(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return null;
    if ((paramString instanceof String))
      return (String)paramString;
    return String.valueOf(paramString);
  }

  public void open(String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString))
        return;
      this.dbFile = new File(paramString);
      if (this.dbFile.exists())
      {
        paramString = new ObjectInputStream(new FileInputStream(this.dbFile));
        this.map = ((HashMap)paramString.readObject());
        paramString.close();
        return;
      }
    }
    catch (Throwable paramString)
    {
      Ln.w(paramString);
    }
  }

  public void putBoolean(String paramString, Boolean paramBoolean)
  {
    put(paramString, paramBoolean);
    commit();
  }

  public void putFloat(String paramString, Float paramFloat)
  {
    put(paramString, paramFloat);
    commit();
  }

  public void putInt(String paramString, Integer paramInteger)
  {
    put(paramString, paramInteger);
    commit();
  }

  public void putLong(String paramString, Long paramLong)
  {
    put(paramString, paramLong);
    commit();
  }

  public void putObject(String paramString, Object paramObject)
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

  public void putString(String paramString1, String paramString2)
  {
    put(paramString1, paramString2);
    commit();
  }

  public void remove(String paramString)
  {
    if (this.map == null)
      this.map = new HashMap();
    this.map.remove(paramString);
    commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.utils.LocalDB
 * JD-Core Version:    0.6.2
 */