package com.baidu.platform.comapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.inner.Point;

public class B
{
  private static final String t = B.class.getSimpleName();
  public float a = 12.0F;
  public int b = 0;
  public int c = 0;
  public double d = 12958162.0D;
  public double e = 4825907.0D;
  public int f = -1;
  public int g = -1;
  public long h = 0L;
  public long i = 0L;
  public b j = new b();
  public a k = new a();
  public boolean l = false;
  public double m;
  public double n;
  public int o;
  public String p;
  public float q;
  public boolean r;
  public int s;

  public Bundle a(c paramc)
  {
    int i2 = 1;
    if (this.a < paramc.b)
      this.a = paramc.b;
    if (this.a > paramc.a)
      this.a = paramc.a;
    while (this.b < 0)
      this.b += 360;
    this.b %= 360;
    if (this.c > 0)
      this.c = 0;
    if (this.c < -45)
      this.c = -45;
    paramc = new Bundle();
    paramc.putDouble("level", this.a);
    paramc.putDouble("rotation", this.b);
    paramc.putDouble("overlooking", this.c);
    paramc.putDouble("centerptx", this.d);
    paramc.putDouble("centerpty", this.e);
    paramc.putInt("left", this.j.a);
    paramc.putInt("right", this.j.b);
    paramc.putInt("top", this.j.c);
    paramc.putInt("bottom", this.j.d);
    if ((this.f >= 0) && (this.g >= 0) && (this.f <= this.j.b) && (this.g <= this.j.d) && (this.j.b > 0) && (this.j.d > 0))
    {
      i1 = (this.j.b - this.j.a) / 2;
      int i3 = (this.j.d - this.j.c) / 2;
      int i4 = this.f;
      int i5 = this.g;
      this.h = (i4 - i1);
      this.i = (-(i5 - i3));
      paramc.putLong("xoffset", this.h);
      paramc.putLong("yoffset", this.i);
    }
    paramc.putInt("lbx", this.k.e.x);
    paramc.putInt("lby", this.k.e.y);
    paramc.putInt("ltx", this.k.f.x);
    paramc.putInt("lty", this.k.f.y);
    paramc.putInt("rtx", this.k.g.x);
    paramc.putInt("rty", this.k.g.y);
    paramc.putInt("rbx", this.k.h.x);
    paramc.putInt("rby", this.k.h.y);
    if (this.l)
    {
      i1 = 1;
      paramc.putInt("bfpp", i1);
      paramc.putInt("animation", 1);
      paramc.putInt("animatime", this.o);
      paramc.putString("panoid", this.p);
      paramc.putInt("autolink", 0);
      paramc.putFloat("siangle", this.q);
      if (!this.r)
        break label588;
    }
    label588: for (int i1 = i2; ; i1 = 0)
    {
      paramc.putInt("isbirdeye", i1);
      paramc.putInt("ssext", this.s);
      return paramc;
      i1 = 0;
      break;
    }
  }

  public void a(Bundle paramBundle)
  {
    boolean bool2 = true;
    this.a = ((float)paramBundle.getDouble("level"));
    this.b = ((int)paramBundle.getDouble("rotation"));
    this.c = ((int)paramBundle.getDouble("overlooking"));
    this.d = paramBundle.getDouble("centerptx");
    this.e = paramBundle.getDouble("centerpty");
    this.j.a = paramBundle.getInt("left");
    this.j.b = paramBundle.getInt("right");
    this.j.c = paramBundle.getInt("top");
    this.j.d = paramBundle.getInt("bottom");
    this.h = paramBundle.getLong("xoffset");
    this.i = paramBundle.getLong("yoffset");
    if ((this.j.b != 0) && (this.j.d != 0))
    {
      int i1 = (this.j.b - this.j.a) / 2;
      int i2 = (this.j.d - this.j.c) / 2;
      int i3 = (int)this.h;
      int i4 = (int)-this.i;
      this.f = (i1 + i3);
      this.g = (i4 + i2);
    }
    this.k.a = paramBundle.getLong("gleft");
    this.k.b = paramBundle.getLong("gright");
    this.k.c = paramBundle.getLong("gtop");
    this.k.d = paramBundle.getLong("gbottom");
    if (this.k.a <= -20037508L)
      this.k.a = -20037508L;
    if (this.k.b >= 20037508L)
      this.k.b = 20037508L;
    if (this.k.c >= 20037508L)
      this.k.c = 20037508L;
    if (this.k.d <= -20037508L)
      this.k.d = -20037508L;
    this.k.e.x = paramBundle.getInt("lbx");
    this.k.e.y = paramBundle.getInt("lby");
    this.k.f.x = paramBundle.getInt("ltx");
    this.k.f.y = paramBundle.getInt("lty");
    this.k.g.x = paramBundle.getInt("rtx");
    this.k.g.y = paramBundle.getInt("rty");
    this.k.h.x = paramBundle.getInt("rbx");
    this.k.h.y = paramBundle.getInt("rby");
    if (paramBundle.getInt("bfpp") == 1)
    {
      bool1 = true;
      this.l = bool1;
      this.m = paramBundle.getDouble("adapterzoomunit");
      this.n = paramBundle.getDouble("zoomunit");
      this.p = paramBundle.getString("panoid");
      this.q = paramBundle.getFloat("siangle");
      if (paramBundle.getInt("isbirdeye") == 0)
        break label586;
    }
    label586: for (boolean bool1 = bool2; ; bool1 = false)
    {
      this.r = bool1;
      this.s = paramBundle.getInt("ssext");
      return;
      bool1 = false;
      break;
    }
  }

  public class a
  {
    public long a = 0L;
    public long b = 0L;
    public long c = 0L;
    public long d = 0L;
    public Point e = new Point(0, 0);
    public Point f = new Point(0, 0);
    public Point g = new Point(0, 0);
    public Point h = new Point(0, 0);

    public a()
    {
    }
  }

  public class b
  {
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;

    public b()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.B
 * JD-Core Version:    0.6.2
 */