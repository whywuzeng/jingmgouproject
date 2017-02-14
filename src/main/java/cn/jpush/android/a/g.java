package cn.jpush.android.a;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import cn.jpush.android.util.x;

public final class g
{
  private static final String[] z;
  public double a;
  public double b;
  private Context c;
  private LocationManager d;
  private Location e;
  private String f;
  private long g;
  private final LocationListener h = new h(this);

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "\032e+.N\037e&";
    int i = -1;
    int j = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int k = localObject2.length;
    int m;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      localObject3 = localObject1;
      n = j;
      localObject1 = localObject2;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 58;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label121;
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
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "\021z;";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 118;
        break label99;
        i = 10;
        break label99;
        i = 72;
        break label99;
        i = 79;
        break label99;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  public g(Context paramContext)
  {
    this.c = paramContext;
    paramContext = z[0];
    this.d = ((LocationManager)this.c.getSystemService(paramContext));
  }

  private void a(Location paramLocation)
  {
    if (paramLocation != null)
      try
      {
        this.a = paramLocation.getLatitude();
        this.b = paramLocation.getLongitude();
        this.g = System.currentTimeMillis();
        this.f = String.format(z[1], new Object[] { Double.valueOf(this.a), Double.valueOf(this.b), Double.valueOf(paramLocation.getAltitude()), Float.valueOf(paramLocation.getBearing()), Float.valueOf(paramLocation.getAccuracy()) });
        return;
      }
      catch (Exception paramLocation)
      {
        paramLocation.getMessage();
        x.f();
      }
    this.f = "";
  }

  public final boolean a()
  {
    return this.d.isProviderEnabled(z[2]);
  }

  public final void b()
  {
    Object localObject = new Criteria();
    ((Criteria)localObject).setAccuracy(1);
    ((Criteria)localObject).setAltitudeRequired(false);
    ((Criteria)localObject).setBearingRequired(false);
    ((Criteria)localObject).setCostAllowed(true);
    ((Criteria)localObject).setPowerRequirement(1);
    localObject = this.d.getBestProvider((Criteria)localObject, true);
    this.e = this.d.getLastKnownLocation((String)localObject);
    if (this.e != null)
      a(this.e);
    this.d.requestLocationUpdates((String)localObject, 2000L, 10.0F, this.h);
  }

  public final void c()
  {
    this.d.removeUpdates(this.h);
  }

  public final String d()
  {
    return this.f;
  }

  public final long e()
  {
    return this.g;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a.g
 * JD-Core Version:    0.6.2
 */