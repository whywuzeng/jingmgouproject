package cn.jpush.android.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.telephony.CellLocation;
import cn.jpush.android.service.PushService;
import cn.jpush.android.util.a;
import cn.jpush.android.util.x;
import java.util.Date;
import org.json.JSONArray;

public abstract class d
{
  private static final String[] A;
  public static int b;
  public static boolean c;
  private static boolean f;
  public String a;
  protected boolean d;
  private boolean e = false;
  private int g;
  private b h;
  private g i;
  private Context j;
  private int[] k;
  private f l;
  private boolean m;
  private final BroadcastReceiver n = new e(this, (byte)0);
  private long o;
  private int p;
  private boolean q = false;
  private boolean r;
  private i s;
  private int t;
  private final Date u = new Date();
  private JSONArray v;
  private boolean w;
  private boolean x = false;
  private String y;
  private JSONArray z;

  static
  {
    String[] arrayOfString = new String[7];
    int i2 = 0;
    Object localObject2 = "Pep\017xXo:\rrCf}\016dXdzSVrHQ.DnM]3RnG[>VeB[3";
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
        i1 = 23;
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
        localObject2 = "Pep\017xXo:\rrCf}\016dXdzSTyJZ:Rn\\];^nX@<Ct";
        i1 = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i9] = localObject1;
        i2 = 2;
        localObject2 = "Pgx";
        i1 = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i9] = localObject1;
        i2 = 3;
        localObject2 = "Pep\017xXo:\rrCf}\016dXdzSVrHQ.DnH[<EbNK1XrJ@4X";
        i1 = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i9] = localObject1;
        i2 = 4;
        localObject2 = "Pep\017xXo:\023rE%c\024qX%G>VTF8DdG@.";
        i1 = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i9] = localObject1;
        i2 = 5;
        localObject2 = "Pep\017xXo:\023rE%c\024qX%C4QxTG)VeNK>_pES8S";
        i1 = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i9] = localObject1;
        i2 = 6;
        localObject2 = "Pep\017xXo:\rrCf}\016dXdzSVrHQ.Dn\\];^nX@<Ct";
        i1 = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i9] = localObject1;
        A = arrayOfString;
        b = 15000;
        c = true;
        f = false;
        return;
        i1 = 49;
        continue;
        i1 = 11;
        continue;
        i1 = 20;
        continue;
        i1 = 125;
      }
    }
  }

  private d(Context paramContext)
  {
    this.j = paramContext.getApplicationContext();
    this.h = new b(paramContext);
    this.s = new i(paramContext);
    this.i = new g(paramContext);
  }

  private d(Context paramContext, String paramString)
  {
    this(paramContext);
    if ((paramString == null) || ("".equals(paramString)))
    {
      this.a = A[2];
      return;
    }
    this.a = paramString;
  }

  private d(Context paramContext, String paramString, boolean paramBoolean)
  {
    this(paramContext, paramString);
    this.x = paramBoolean;
  }

  public d(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramContext, paramString, paramBoolean1);
    this.e = paramBoolean2;
  }

  public static boolean a(Context paramContext)
  {
    return (a.c(paramContext, A[3])) && (a.c(paramContext, A[6])) && (a.c(paramContext, A[1])) && (a.c(paramContext, A[0]));
  }

  public final String a()
  {
    String str2 = this.i.d();
    String str1 = str2;
    if (str2 == null)
      str1 = "";
    return str1;
  }

  public final void a(JSONArray paramJSONArray)
  {
    this.v = paramJSONArray;
  }

  public final JSONArray b()
  {
    if (!a.c(this.j, A[3]))
      return null;
    if (this.h.e())
      return this.h.b();
    return this.v;
  }

  public final void b(JSONArray paramJSONArray)
  {
    this.z = paramJSONArray;
  }

  public final JSONArray c()
  {
    if (this.z != null)
      return this.z;
    return null;
  }

  public abstract void d();

  public final void e()
  {
    if (this.p != 1)
      return;
    CellLocation.requestLocationUpdate();
    this.p = 2;
    this.l.sendEmptyMessage(1);
    if (this.s.b().isWifiEnabled())
    {
      this.s.b().startScan();
      this.r = false;
      return;
    }
    if (!this.e)
    {
      this.l.sendEmptyMessageDelayed(5, 0L);
      return;
    }
    this.o = System.currentTimeMillis();
    if ((!c) || (!this.s.b().setWifiEnabled(true)))
    {
      this.l.sendEmptyMessageDelayed(5, 8000L);
      return;
    }
    this.r = true;
  }

  public final void f()
  {
    if (PushService.A);
    label71: label89: label350: 
    while (true)
    {
      return;
      PushService.A = true;
      this.e = false;
      this.x = false;
      if (a.c(this.j, A[3]))
      {
        this.v = this.h.c();
        if (a.c(this.j, A[6]))
          break label125;
        x.f();
        this.z = null;
        if (a.c(this.j, A[0]))
          break label276;
        x.f();
      }
      for (this.y = ""; ; this.y = this.i.d())
      {
        label125: 
        do
        {
          if ((this.q) || (this.x))
            break label350;
          d();
          return;
          x.f();
          this.v = null;
          break;
          this.w = this.s.a();
          if (!this.s.a())
          {
            if (!this.e)
            {
              this.z = null;
              break label71;
            }
            if (!a.c(this.j, A[1]))
            {
              x.f();
              this.z = null;
              break label71;
            }
            if (this.x)
              break label71;
            this.j.registerReceiver(this.n, new IntentFilter(A[4]));
            this.j.registerReceiver(this.n, new IntentFilter(A[5]));
            this.s.b().setWifiEnabled(true);
            this.q = true;
            break label71;
          }
          this.z = this.s.c();
          break label71;
          if (!this.i.a())
            break label89;
          this.i.b();
        }
        while (("" == this.i.d()) || (this.i.d() == null) || (System.currentTimeMillis() - this.i.e() >= 30000L));
        this.t = 0;
      }
    }
  }

  public final void g()
  {
    PushService.A = false;
    if ((a.c(this.j, A[0])) && (this.i.a()))
      this.i.c();
    if ((!this.w) && (a.c(this.j, A[1])))
      this.s.b().setWifiEnabled(false);
    if ((this.p > 0) && (a(this.j)))
    {
      this.j.unregisterReceiver(this.n);
      this.l = null;
      this.p = 0;
      if (!this.w)
      {
        this.d = false;
        this.s.b().setWifiEnabled(false);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a.d
 * JD-Core Version:    0.6.2
 */