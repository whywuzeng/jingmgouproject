package cn.jpush.android.a;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import cn.jpush.android.util.x;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class b
{
  private static final String[] z;
  private int a;
  private int b;
  private int c;
  private boolean d;
  private boolean e;
  private int f;
  private double g;
  private PhoneStateListener h;
  private double i;
  private int j;
  private int k;
  private int l;
  private int m;
  private TelephonyManager n;
  private boolean o;
  private String p;
  private ArrayList<a> q = new ArrayList();

  static
  {
    String[] arrayOfString = new String[14];
    int i2 = 0;
    Object localObject2 = "|_;I";
    int i1 = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i3 = arrayOfChar.length;
    int i6 = 0;
    int i4 = 0;
    int i8 = i1;
    localObject2 = arrayOfChar;
    int i9 = i2;
    Object localObject3 = localObject1;
    int i5 = i3;
    Object localObject4;
    int i7;
    if (i3 <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i7 = i1;
      label68: i5 = i4;
      label71: localObject2 = localObject1;
      i6 = localObject2[i4];
      switch (i5 % 5)
      {
      default:
        i1 = 112;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i4] = ((char)(i1 ^ i6));
      i5 += 1;
      if (i3 == 0)
      {
        i4 = i3;
        break label71;
      }
      i6 = i5;
      i5 = i3;
      localObject3 = localObject4;
      i9 = i2;
      localObject2 = localObject1;
      i8 = i7;
      i7 = i8;
      localObject1 = localObject2;
      i2 = i9;
      localObject4 = localObject3;
      i3 = i5;
      i4 = i6;
      if (i5 > i6)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i8)
      {
      default:
        localObject3[i9] = localObject1;
        i2 = 1;
        localObject2 = "x^\"j\021l^\005\\\021kR9F<pU1A\004j_3";
        i1 = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i9] = localObject1;
        i2 = 2;
        localObject2 = "x^\"f\025kL9Z\033V_";
        i1 = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i9] = localObject1;
        i2 = 3;
        localObject2 = "x^\"{\tlO3E9{";
        i1 = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i9] = localObject1;
        i2 = 4;
        localObject2 = "x^\"j\021l^\005\\\021kR9F<~O?\\\005{^";
        i1 = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i9] = localObject1;
        i2 = 5;
        localObject2 = "x^\"j\021l^\005\\\021kR9F9{";
        i1 = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i9] = localObject1;
        i2 = 6;
        localObject2 = "~U2Z\037v_xX\025mV?[\003vT8\0061\\x\023{#@x\031i\"L~\td?\\z\002a?Q";
        i1 = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i9] = localObject1;
        i2 = 7;
        localObject2 = "oS9F\025";
        i1 = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i9] = localObject1;
        i2 = 8;
        localObject2 = "rT4A\034zd5G\005qO$Q/|T2M";
        i1 = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i9] = localObject1;
        i2 = 9;
        localObject2 = "~\\3";
        i1 = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i9] = localObject1;
        i2 = 10;
        localObject2 = "rT4A\034zd8M\004hT$C/|T2M";
        i1 = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i9] = localObject1;
        i2 = 11;
        localObject2 = "|^:D/v_";
        i1 = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i9] = localObject1;
        i2 = 12;
        localObject2 = "sT5I\004vT8w\021m^7w\023p_3";
        i1 = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i9] = localObject1;
        i2 = 13;
        localObject2 = "lR1F\021sd%\\\002zU1\\\030";
        i1 = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i9] = localObject1;
        z = arrayOfString;
        return;
        i1 = 31;
        continue;
        i1 = 59;
        continue;
        i1 = 86;
        continue;
        i1 = 40;
      }
    }
  }

  public b(Context paramContext)
  {
    if (!cn.jpush.android.util.a.c(paramContext, z[6]))
      return;
    this.h = new c(this);
    this.n = ((TelephonyManager)paramContext.getSystemService(z[7]));
    this.n.listen(this.h, 18);
  }

  private String h()
  {
    Object localObject = this.n.getCellLocation();
    try
    {
      String str = this.n.getNetworkOperator();
      int i1 = str.length();
      if (i1 != 5)
        if (i1 == 6);
      while (this.n.getPhoneType() == 2)
      {
        this.o = true;
        if ((!(localObject instanceof CdmaCellLocation)) || (localObject == null))
          break;
        localObject = (CdmaCellLocation)localObject;
        this.g = (((CdmaCellLocation)localObject).getBaseStationLatitude() / 14400.0D);
        this.i = (((CdmaCellLocation)localObject).getBaseStationLongitude() / 14400.0D);
        this.m = ((CdmaCellLocation)localObject).getSystemId();
        this.b = ((CdmaCellLocation)localObject).getBaseStationId();
        this.l = ((CdmaCellLocation)localObject).getNetworkId();
        new StringBuilder().append(this.m).toString();
        x.d();
        new StringBuilder().append(this.b).toString();
        x.d();
        new StringBuilder().append(this.l).toString();
        x.d();
        localObject = new a();
        ((a)localObject).a(this.b);
        ((a)localObject).d(this.l);
        ((a)localObject).c(this.m);
        ((a)localObject).b(Integer.parseInt(str.substring(0, 3)));
        ((a)localObject).a(z[0]);
        this.q.add(localObject);
        new StringBuilder().append(((a)localObject).a()).toString();
        x.c();
        this.p = ((a)localObject).toString();
        return this.p;
        this.j = Integer.parseInt(str.substring(0, 3));
        this.k = Integer.parseInt(str.substring(3, i1));
      }
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  private int i()
  {
    if (!this.o)
      j();
    return this.c;
  }

  private void j()
  {
    this.e = false;
    this.d = false;
    this.c = 0;
    this.f = 0;
    this.j = 0;
    this.k = 0;
    Object localObject1 = this.n.getCellLocation();
    if (localObject1 == null)
      return;
    int i1;
    if ((this.n.getPhoneType() == 1) && ((localObject1 instanceof GsmCellLocation)))
    {
      this.e = true;
      localObject2 = (GsmCellLocation)localObject1;
      i1 = ((GsmCellLocation)localObject2).getCid();
      if ((i1 > 0) && (i1 != 65535))
      {
        this.c = i1;
        this.f = ((GsmCellLocation)localObject2).getLac();
      }
    }
    while (true)
    {
      try
      {
        localObject2 = this.n.getNetworkOperator();
        i1 = ((String)localObject2).length();
        if (i1 != 5)
        {
          if ((i1 != 6) && (this.n.getPhoneType() != 2))
            break;
          if (!(localObject1 instanceof CdmaCellLocation))
            break label407;
          if (localObject1 == null)
            break;
          localObject1 = (CdmaCellLocation)localObject1;
          this.g = (((CdmaCellLocation)localObject1).getBaseStationLatitude() / 14400.0D);
          this.i = (((CdmaCellLocation)localObject1).getBaseStationLongitude() / 14400.0D);
          this.m = ((CdmaCellLocation)localObject1).getSystemId();
          this.b = ((CdmaCellLocation)localObject1).getBaseStationId();
          this.l = ((CdmaCellLocation)localObject1).getNetworkId();
          new StringBuilder().append(this.m).toString();
          x.d();
          new StringBuilder().append(this.b).toString();
          x.d();
          new StringBuilder().append(this.l).toString();
          x.d();
          localObject1 = new a();
          ((a)localObject1).a(this.b);
          ((a)localObject1).d(this.l);
          ((a)localObject1).c(this.m);
          if ((localObject2 != null) && (((String)localObject2).length() >= 3))
            ((a)localObject1).b(Integer.parseInt(((String)localObject2).substring(0, 3)));
          ((a)localObject1).a(z[0]);
          this.q.add(localObject1);
          new StringBuilder().append(((a)localObject1).a()).toString();
          x.c();
          this.p = ((a)localObject1).toString();
          this.d = true;
          return;
        }
      }
      catch (Exception localException)
      {
        x.j();
        return;
      }
      this.j = Integer.parseInt(((String)localObject2).substring(0, 3));
      this.k = Integer.parseInt(((String)localObject2).substring(3, i1));
    }
    label407: Object localObject3 = localException.getClass();
    Class[] arrayOfClass = new Class[0];
    Method localMethod1 = ((Class)localObject3).getMethod(z[5], arrayOfClass);
    Method localMethod2 = ((Class)localObject3).getMethod(z[3], arrayOfClass);
    Method localMethod3 = ((Class)localObject3).getMethod(z[2], arrayOfClass);
    Object localObject2 = new Object[0];
    this.b = ((Integer)localMethod1.invoke(localException, (Object[])localObject2)).intValue();
    this.m = ((Integer)localMethod2.invoke(localException, (Object[])localObject2)).intValue();
    this.l = ((Integer)localMethod3.invoke(localException, (Object[])localObject2)).intValue();
    localMethod1 = ((Class)localObject3).getMethod(z[4], arrayOfClass);
    localObject3 = ((Class)localObject3).getMethod(z[1], arrayOfClass);
    this.g = ((Integer)localMethod1.invoke(localException, (Object[])localObject2)).intValue();
    this.i = ((Integer)((Method)localObject3).invoke(localException, (Object[])localObject2)).intValue();
    this.d = true;
  }

  public final int a()
  {
    if (!this.o)
      j();
    return this.b;
  }

  public final JSONArray b()
  {
    Object localObject = this.n.getCellLocation();
    try
    {
      String str = this.n.getNetworkOperator();
      int i1 = str.length();
      if (i1 != 5)
        if (i1 == 6);
      while (this.n.getPhoneType() == 2)
      {
        this.o = true;
        if ((!(localObject instanceof CdmaCellLocation)) || (localObject == null))
          break;
        localObject = (CdmaCellLocation)localObject;
        this.g = (((CdmaCellLocation)localObject).getBaseStationLatitude() / 14400.0D);
        this.i = (((CdmaCellLocation)localObject).getBaseStationLongitude() / 14400.0D);
        this.m = ((CdmaCellLocation)localObject).getSystemId();
        this.b = ((CdmaCellLocation)localObject).getBaseStationId();
        this.l = ((CdmaCellLocation)localObject).getNetworkId();
        new StringBuilder().append(this.m).toString();
        x.d();
        new StringBuilder().append(this.b).toString();
        x.d();
        new StringBuilder().append(this.l).toString();
        x.d();
        localObject = new a();
        ((a)localObject).a(this.b);
        ((a)localObject).d(this.l);
        ((a)localObject).c(this.m);
        ((a)localObject).b(Integer.parseInt(str.substring(0, 3)));
        ((a)localObject).a(z[0]);
        this.q.add(localObject);
        return ((a)localObject).b();
        this.j = Integer.parseInt(str.substring(0, 3));
        this.k = Integer.parseInt(str.substring(3, i1));
      }
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public final JSONArray c()
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject2 = d();
    if (!this.o)
      j();
    double d1 = this.f;
    if (!this.o)
      j();
    int i3 = this.j;
    if (!this.o)
      j();
    int i4 = this.k;
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (localObject2.length >= 2);
    }
    else
    {
      localObject1 = new int[2];
      localObject1[0] = this.c;
      localObject1[1] = -60;
    }
    int i1 = 0;
    while (true)
      if (((i1 < localObject1.length) && (i1 <= 4)) || (e()))
        try
        {
          localObject1 = new JSONObject(h());
          localObject1 = new JSONArray().put(localObject1);
          return localObject1;
          int i2 = i1 + 1;
          if ((i2 >= 0) && (i2 <= 31))
            i2 = i2 * 2 - 113;
          try
          {
            while (true)
            {
              localObject2 = new JSONObject();
              ((JSONObject)localObject2).put(z[11], localObject1[i1]);
              ((JSONObject)localObject2).put(z[12], d1);
              ((JSONObject)localObject2).put(z[8], i3);
              ((JSONObject)localObject2).put(z[10], i4);
              ((JSONObject)localObject2).put(z[13], i2);
              ((JSONObject)localObject2).put(z[9], 0);
              localJSONArray.put(localObject2);
              i1 += 2;
              break;
              i2 = 0;
            }
          }
          catch (Exception localException)
          {
            while (true)
            {
              localException.getMessage();
              x.f();
            }
          }
        }
        catch (JSONException localJSONException)
        {
        }
    return localJSONArray;
  }

  public final int[] d()
  {
    if (i() == 0)
      return new int[0];
    Object localObject = this.n.getNeighboringCellInfo();
    if ((localObject == null) || (((List)localObject).size() == 0))
      return new int[] { i() };
    int[] arrayOfInt = new int[((List)localObject).size() * 2 + 2];
    arrayOfInt[0] = i();
    arrayOfInt[1] = this.a;
    localObject = ((List)localObject).iterator();
    int i1 = 2;
    while (((Iterator)localObject).hasNext())
    {
      NeighboringCellInfo localNeighboringCellInfo = (NeighboringCellInfo)((Iterator)localObject).next();
      int i3 = localNeighboringCellInfo.getCid();
      if ((i3 > 0) && (i3 != 65535))
      {
        int i2 = i1 + 1;
        arrayOfInt[i1] = i3;
        i1 = i2 + 1;
        arrayOfInt[i2] = localNeighboringCellInfo.getRssi();
      }
    }
    localObject = new int[i1];
    System.arraycopy(arrayOfInt, 0, localObject, 0, i1);
    return localObject;
  }

  public final boolean e()
  {
    if (!this.o)
      j();
    return this.d;
  }

  public final boolean f()
  {
    if (!this.o)
      j();
    return this.e;
  }

  public final float g()
  {
    if (e());
    while (!f())
      return 1.065353E+009F;
    d();
    return 1.065353E+009F;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a.b
 * JD-Core Version:    0.6.2
 */