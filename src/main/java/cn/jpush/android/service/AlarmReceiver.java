package cn.jpush.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.x;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[6];
    int j = 0;
    Object localObject2 = "<F\016p7!T.a\026!V";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 68;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "'A?";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "4Y=g)";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\024Y=g)uF(t6!P85%2T5{d\"\\(}d<[(p6#T0/dpQ/";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "8|2a!'C=y";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "!G)p";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 85;
        continue;
        i = 53;
        continue;
        i = 92;
        continue;
        i = 21;
      }
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    x.c();
    if (paramIntent == null);
    boolean bool;
    Calendar localCalendar;
    do
    {
      do
        return;
      while (!cn.jpush.android.a.a(paramContext.getApplicationContext()));
      paramIntent = PendingIntent.getBroadcast(paramContext, 0, paramIntent, 0);
      bool = ae.a(paramContext, z[0], z[5]).equals(z[5]);
      localCalendar = Calendar.getInstance();
      localCalendar.setTimeInMillis(System.currentTimeMillis());
    }
    while (!bool);
    int i = ae.a(paramContext, z[4], PushService.i);
    localCalendar.add(13, i);
    String.format(z[3], new Object[] { Integer.valueOf(i) });
    x.d();
    ((AlarmManager)paramContext.getSystemService(z[2])).set(0, localCalendar.getTimeInMillis(), paramIntent);
    paramIntent = new Intent(paramContext, PushService.class);
    paramIntent.putExtra(z[1], z[1]);
    paramContext.startService(paramIntent);
    cn.jpush.android.util.a.l(paramContext);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.AlarmReceiver
 * JD-Core Version:    0.6.2
 */