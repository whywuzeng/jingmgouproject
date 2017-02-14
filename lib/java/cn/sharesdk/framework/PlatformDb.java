package cn.sharesdk.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PlatformDb
{
  private static final String DB_NAME = "cn_sharesdk_weibodb";
  private SharedPreferences db;
  private String platformNname;
  private int platformVersion;

  public PlatformDb(Context paramContext, String paramString, int paramInt)
  {
    this.db = paramContext.getSharedPreferences("cn_sharesdk_weibodb_" + paramString + "_" + paramInt, 0);
    this.platformNname = paramString;
    this.platformVersion = paramInt;
  }

  public String exportData()
  {
    try
    {
      Object localObject = new HashMap();
      ((HashMap)localObject).putAll(this.db.getAll());
      localObject = new Hashon().fromHashMap((HashMap)localObject);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
    }
    return null;
  }

  public String get(String paramString)
  {
    return this.db.getString(paramString, "");
  }

  public long getExpiresIn()
  {
    try
    {
      long l = this.db.getLong("expiresIn", 0L);
      return l;
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        int i = this.db.getInt("expiresIn", 0);
        return i;
      }
      catch (Throwable localThrowable2)
      {
      }
    }
    return 0L;
  }

  public long getExpiresTime()
  {
    return this.db.getLong("expiresTime", 0L) + getExpiresIn() * 1000L;
  }

  public String getPlatformNname()
  {
    return this.platformNname;
  }

  public int getPlatformVersion()
  {
    return this.platformVersion;
  }

  public String getToken()
  {
    return this.db.getString("token", "");
  }

  public String getTokenSecret()
  {
    return this.db.getString("secret", "");
  }

  public String getUserGender()
  {
    String str = this.db.getString("gender", "2");
    if ("0".equals(str))
      return "m";
    if ("1".equals(str))
      return "f";
    return null;
  }

  public String getUserIcon()
  {
    return this.db.getString("icon", "");
  }

  public String getUserId()
  {
    return this.db.getString("weibo", "");
  }

  public String getUserName()
  {
    return this.db.getString("nickname", "");
  }

  public void importData(String paramString)
  {
    while (true)
    {
      Map.Entry localEntry;
      Object localObject2;
      try
      {
        Object localObject1 = new Hashon().fromJson(paramString);
        if (localObject1 != null)
        {
          paramString = this.db.edit();
          localObject1 = ((HashMap)localObject1).entrySet().iterator();
          if (!((Iterator)localObject1).hasNext())
            break;
          localEntry = (Map.Entry)((Iterator)localObject1).next();
          localObject2 = localEntry.getValue();
          if (!(localObject2 instanceof Boolean))
            break label105;
          paramString.putBoolean((String)localEntry.getKey(), ((Boolean)localObject2).booleanValue());
          continue;
        }
      }
      catch (Throwable paramString)
      {
        Ln.w(paramString);
      }
      return;
      label105: if ((localObject2 instanceof Float))
        paramString.putFloat((String)localEntry.getKey(), ((Float)localObject2).floatValue());
      else if ((localObject2 instanceof Integer))
        paramString.putInt((String)localEntry.getKey(), ((Integer)localObject2).intValue());
      else if ((localObject2 instanceof Long))
        paramString.putLong((String)localEntry.getKey(), ((Long)localObject2).longValue());
      else
        paramString.putString((String)localEntry.getKey(), String.valueOf(localObject2));
    }
    paramString.commit();
  }

  public boolean isValid()
  {
    boolean bool2 = true;
    String str = getToken();
    boolean bool1;
    if ((str == null) || (str.length() <= 0))
      bool1 = false;
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (getExpiresIn() == 0L);
      bool1 = bool2;
    }
    while (getExpiresTime() > System.currentTimeMillis());
    return false;
  }

  public void put(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.db.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public void putExpiresIn(long paramLong)
  {
    SharedPreferences.Editor localEditor = this.db.edit();
    localEditor.putLong("expiresIn", paramLong);
    localEditor.putLong("expiresTime", System.currentTimeMillis());
    localEditor.commit();
  }

  public void putToken(String paramString)
  {
    SharedPreferences.Editor localEditor = this.db.edit();
    localEditor.putString("token", paramString);
    localEditor.commit();
  }

  public void putTokenSecret(String paramString)
  {
    SharedPreferences.Editor localEditor = this.db.edit();
    localEditor.putString("secret", paramString);
    localEditor.commit();
  }

  public void putUserId(String paramString)
  {
    SharedPreferences.Editor localEditor = this.db.edit();
    localEditor.putString("weibo", paramString);
    localEditor.commit();
  }

  public void removeAccount()
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = this.db.getAll().entrySet().iterator();
    while (((Iterator)localObject2).hasNext())
      ((ArrayList)localObject1).add(((Map.Entry)((Iterator)localObject2).next()).getKey());
    localObject2 = this.db.edit();
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
      ((SharedPreferences.Editor)localObject2).remove((String)((Iterator)localObject1).next());
    ((SharedPreferences.Editor)localObject2).commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.PlatformDb
 * JD-Core Version:    0.6.2
 */