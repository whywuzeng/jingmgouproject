package cn.smssdk.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class e
{
  private static e a;
  private Context b;
  private SharedPreferences c;

  private e(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
  }

  public static e a(Context paramContext)
  {
    if (a == null)
      a = new e(paramContext);
    return a;
  }

  public String a(String paramString)
  {
    return this.c.getString(paramString, "");
  }

  public void a(String paramString, int paramInt)
  {
    paramString = paramString + "_" + paramInt;
    this.c = this.b.getSharedPreferences(paramString, 0);
  }

  public void a(String paramString, Long paramLong)
  {
    SharedPreferences.Editor localEditor = this.c.edit();
    localEditor.putLong(paramString, paramLong.longValue());
    localEditor.commit();
  }

  public void a(String paramString, Object paramObject)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramObject);
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      a(paramString, Base64.encodeToString(localByteArrayOutputStream.toByteArray(), 2));
      return;
    }
    catch (Throwable paramString)
    {
      d.c(paramString);
    }
  }

  public void a(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.c.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public long b(String paramString)
  {
    return this.c.getLong(paramString, 0L);
  }

  public Object c(String paramString)
  {
    try
    {
      paramString = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(a(paramString), 2)));
      Object localObject = paramString.readObject();
      paramString.close();
      return localObject;
    }
    catch (Throwable paramString)
    {
      d.b(paramString);
    }
    return null;
  }

  public void d(String paramString)
  {
    SharedPreferences.Editor localEditor = this.c.edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.utils.e
 * JD-Core Version:    0.6.2
 */