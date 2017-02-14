package cn.jpush.android.api;

import android.app.Notification;
import android.content.Context;
import cn.jpush.android.a;

public class BasicPushNotificationBuilder extends DefaultPushNotificationBuilder
{
  private static final String[] z;
  protected Context a;
  public String developerArg0 = "";
  public int notificationDefaults = -1;
  public int notificationFlags = 16;
  public int statusBarDrawable = a.a;

  static
  {
    String[] arrayOfString = new String[5];
    Object localObject1 = "GNT\t";
    int i = -1;
    int j = 0;
    Object localObject3 = arrayOfString;
    localObject1 = ((String)localObject1).toCharArray();
    int k = localObject1.length;
    int m;
    int n;
    int i1;
    label42: Object localObject2;
    int i2;
    label91: label113: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      n = j;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 86;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label113;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j)
        break label42;
      localObject1 = new String(localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject1 = "VDlGv{~N3`e";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        localObject1 = "zpSb5GNT\t";
        j = 2;
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        localObject1 = "{dS9u";
        j = 3;
        i = 2;
        break;
      case 2:
        localObject4[i3] = localObject1;
        j = 4;
        localObject1 = "zpSb5";
        i = 3;
        break;
      case 3:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 24;
        break label91;
        i = 17;
        break label91;
        i = 32;
        break label91;
        i = 11;
        break label91;
        m = 0;
        i2 = i;
        localObject2 = localObject1;
        i3 = j;
        localObject4 = localObject3;
        i = k;
        j = m;
      }
    }
  }

  public BasicPushNotificationBuilder(Context paramContext)
  {
    if (paramContext == null)
      throw new IllegalArgumentException(z[1]);
    this.a = paramContext;
  }

  static PushNotificationBuilder a(String paramString)
  {
    String[] arrayOfString = paramString.split(z[0]);
    paramString = arrayOfString[0];
    if (z[4].equals(paramString))
      paramString = new BasicPushNotificationBuilder(a.d);
    while (true)
    {
      paramString.a(arrayOfString);
      return paramString;
      if (z[3].equals(paramString))
        paramString = new CustomPushNotificationBuilder(a.d);
      else
        paramString = new BasicPushNotificationBuilder(a.d);
    }
  }

  public final String a()
  {
    return this.developerArg0;
  }

  final void a(Notification paramNotification)
  {
    paramNotification.defaults = this.notificationDefaults;
    paramNotification.flags = this.notificationFlags;
    paramNotification.icon = this.statusBarDrawable;
  }

  void a(String[] paramArrayOfString)
  {
    this.notificationDefaults = Integer.parseInt(paramArrayOfString[1]);
    this.notificationFlags = Integer.parseInt(paramArrayOfString[2]);
    this.statusBarDrawable = Integer.parseInt(paramArrayOfString[3]);
    this.developerArg0 = paramArrayOfString[4];
  }

  String b()
  {
    return this.notificationDefaults + z[0] + this.notificationFlags + z[0] + this.statusBarDrawable + z[0] + this.developerArg0;
  }

  public String toString()
  {
    return z[2] + b();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.BasicPushNotificationBuilder
 * JD-Core Version:    0.6.2
 */