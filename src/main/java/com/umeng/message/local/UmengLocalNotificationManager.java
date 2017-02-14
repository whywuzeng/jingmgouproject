package com.umeng.message.local;

import android.content.Context;
import android.content.Intent;
import com.umeng.common.message.Log;
import java.util.Iterator;
import java.util.List;

public class UmengLocalNotificationManager
{
  public static final String ADD_LOCAL_NOTIFICATION = "add_local_notification";
  public static final String CLEAR_LOCAL_NOTIFICATION = "clear_local_notification";
  public static final String DELETE_LOCAL_NOTIFICATION = "delete_local_notification";
  public static final String DISPLAY_LOCAL_NOTIFICATION = "display_local_notification";
  public static final String LOCAL_NOTIFICATION_ID = "local_notification_id";
  public static final String LOCAL_NOTIFICATION_TYPE = "local_notification_type";
  public static final String UPDATE_LOCAL_NOTIFICATION = "update_local_notification";
  private static final String a = UmengLocalNotificationManager.class.getName();
  private static UmengLocalNotificationManager b;
  private Context c;

  private UmengLocalNotificationManager(Context paramContext)
  {
    this.c = paramContext;
  }

  private void a(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this.c, UmengLocalNotificationService.class);
    localIntent.putExtra("local_notification_id", paramString1);
    localIntent.putExtra("local_notification_type", paramString2);
    this.c.startService(localIntent);
  }

  public static UmengLocalNotificationManager getInstance(Context paramContext)
  {
    try
    {
      if (b == null)
        b = new UmengLocalNotificationManager(paramContext);
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
    boolean bool = false;
    if (paramUmengLocalNotification == null);
    while (true)
    {
      try
      {
        Log.c(a, "localNotification is null");
        return bool;
        if (!paramUmengLocalNotification.validateDateTime())
        {
          Log.c(a, "Please reset date time for localNotification");
          continue;
        }
      }
      finally
      {
      }
      if (!paramUmengLocalNotification.validateData(this.c))
      {
        Log.c(a, "Please reset relavent data for localNotification");
      }
      else
      {
        try
        {
          if (findLocalNotification(paramUmengLocalNotification.getId()) == null)
            break label105;
          Log.c(a, "add local notification has exists");
        }
        catch (Exception paramUmengLocalNotification)
        {
          Log.c(a, paramUmengLocalNotification.toString());
          paramUmengLocalNotification.printStackTrace();
        }
        continue;
        label105: UmengLocalNotificationStore.getInstance(this.c).addLocalNotification(paramUmengLocalNotification);
        a(paramUmengLocalNotification.getId(), "add_local_notification");
        bool = true;
      }
    }
  }

  public boolean deleteAllLocalNotifications()
  {
    try
    {
      Object localObject1 = findAllLocalNotifications();
      if ((localObject1 == null) || (((List)localObject1).size() == 0))
        Log.c(a, "list is empty");
      while (true)
      {
        return false;
        UmengLocalNotificationStore.getInstance(this.c).deleteAllLocalNotifications();
        Iterator localIterator = ((List)localObject1).iterator();
        UmengLocalNotification localUmengLocalNotification;
        for (localObject1 = ""; localIterator.hasNext(); localObject1 = (String)localObject1 + localUmengLocalNotification.getId() + ";")
          localUmengLocalNotification = (UmengLocalNotification)localIterator.next();
        a(((String)localObject1).substring(0, ((String)localObject1).length() - 1), "clear_local_notification");
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.c(a, localException.toString());
        localException.printStackTrace();
      }
    }
    finally
    {
    }
  }

  public boolean deleteLocalNotification(String paramString)
  {
    boolean bool = false;
    try
    {
      UmengLocalNotification localUmengLocalNotification = findLocalNotification(paramString);
      if (localUmengLocalNotification == null)
        Log.c(a, "localNotification is null");
      while (true)
      {
        return bool;
        UmengLocalNotificationStore.getInstance(this.c).deleteLocalNotification(paramString);
        a(localUmengLocalNotification.getId(), "delete_local_notification");
        bool = true;
      }
    }
    catch (Exception paramString)
    {
      while (true)
      {
        Log.c(a, paramString.toString());
        paramString.printStackTrace();
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public List<UmengLocalNotification> findAllLocalNotifications()
  {
    Object localObject1 = null;
    try
    {
      List localList = UmengLocalNotificationStore.getInstance(this.c).findAllLocalNotifications();
      localObject1 = localList;
      return localObject1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.c(a, localException.toString());
        localException.printStackTrace();
      }
    }
    finally
    {
    }
  }

  public UmengLocalNotification findLocalNotification(String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = UmengLocalNotificationStore.getInstance(this.c).findLocalNotification(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      while (true)
      {
        Log.c(a, paramString.toString());
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public List<UmengLocalNotification> findLocalNotifications(String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = UmengLocalNotificationStore.getInstance(this.c).findLocalNotifications(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      while (true)
      {
        Log.c(a, paramString.toString());
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public void resetLocalNotifications()
  {
    Iterator localIterator = findAllLocalNotifications().iterator();
    while (localIterator.hasNext())
      a(((UmengLocalNotification)localIterator.next()).getId(), "update_local_notification");
  }

  public boolean updateLocalNotification(UmengLocalNotification paramUmengLocalNotification)
  {
    boolean bool = false;
    if (paramUmengLocalNotification == null);
    while (true)
    {
      try
      {
        Log.c(a, "localNotification is null");
        return bool;
        if (!paramUmengLocalNotification.validateDateTime())
        {
          Log.c(a, "Please reset date time for localNotification");
          continue;
        }
      }
      finally
      {
      }
      if (!paramUmengLocalNotification.validateData(this.c))
        Log.c(a, "Please reset relavent data for localNotification");
      else
        try
        {
          UmengLocalNotificationStore.getInstance(this.c).updateLocalNotification(paramUmengLocalNotification);
          a(paramUmengLocalNotification.getId(), "update_local_notification");
          bool = true;
        }
        catch (Exception paramUmengLocalNotification)
        {
          Log.c(a, paramUmengLocalNotification.toString());
          paramUmengLocalNotification.printStackTrace();
        }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.local.UmengLocalNotificationManager
 * JD-Core Version:    0.6.2
 */