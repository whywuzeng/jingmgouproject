package com.baidu.location.b;

import java.util.Iterator;
import java.util.LinkedList;

public class h
  implements f
{
  private static final double[][] bC = { { 120.0618433387417D, 52.993102280803413D }, arrayOfDouble1, { 119.3643163114595D, 47.072753800668799D }, { 111.7970645774334D, 45.245117118706723D }, { 104.9683464256928D, 41.862504156197687D }, { 97.599080653340465D, 43.229770875966622D }, { 90.172611555796934D, 47.840454616690003D }, arrayOfDouble2, arrayOfDouble3, { 72.992644850608954D, 39.20573694893438D }, { 78.453035931162432D, 31.72421462055291D }, arrayOfDouble4, { 92.099129614868886D, 27.71669293109786D }, arrayOfDouble5, { 98.441145440329763D, 27.28076394915907D }, arrayOfDouble6, { 97.52260341956044D, 23.713268294453169D }, arrayOfDouble7, arrayOfDouble8, arrayOfDouble9, { 107.2643282303716D, 21.454138880714609D }, { 108.37613426320191D, 17.314595169353261D }, { 113.0483020881062D, 16.81442875978696D }, arrayOfDouble10, { 123.9556508773411D, 35.664128738043829D }, { 124.2746994608336D, 39.773554305319337D }, arrayOfDouble11, { 132.93686888278461D, 44.59499883995769D }, { 135.34615620473011D, 48.600803994365549D }, arrayOfDouble12, { 124.48501940313891D, 54.244770450983999D }, arrayOfDouble13 };
  private static h bD = null;
  private LinkedList bB = null;

  static
  {
    double[] arrayOfDouble1 = { 114.4030602114635D, 47.796523164269189D };
    double[] arrayOfDouble2 = { 87.088763100354697D, 49.352141072494689D };
    double[] arrayOfDouble3 = { 83.037122886181322D, 47.191596469259132D };
    double[] arrayOfDouble4 = { 85.917798717429307D, 27.930639076213879D };
    double[] arrayOfDouble5 = { 95.273331936203817D, 28.987847625338109D };
    double[] arrayOfDouble6 = { 97.506815466074045D, 24.714273859299919D };
    double[] arrayOfDouble7 = { 100.0708944887605D, 21.176736533987601D };
    double[] arrayOfDouble8 = { 101.9206763604854D, 21.133030159808179D };
    double[] arrayOfDouble9 = { 105.2746400703396D, 23.11902125989214D };
    double[] arrayOfDouble10 = { 122.2284167146931D, 21.663740455826971D };
    double[] arrayOfDouble11 = { 130.84283673400699D, 42.335144302851738D };
    double[] arrayOfDouble12 = { 131.7265723825281D, 48.003302912746562D };
    double[] arrayOfDouble13 = { 120.0618433387417D, 52.993102280803413D };
  }

  private h()
  {
    int j = bC.length;
    this.bB = new LinkedList();
    int i = 0;
    while (i < j - 1)
    {
      this.bB.add(new b(new a(bC[i][0] * 100000.0D, bC[i][1] * 100000.0D), new a(bC[(i + 1)][0] * 100000.0D, bC[(i + 1)][1] * 100000.0D)));
      i += 1;
    }
  }

  public static h W()
  {
    if (bD == null)
      bD = new h();
    return bD;
  }

  public boolean jdMethod_for(double paramDouble1, double paramDouble2)
  {
    label193: 
    while (true)
    {
      try
      {
        a locala = new a(100000.0D * paramDouble1, 100000.0D * paramDouble2);
        int i = 0;
        Iterator localIterator = this.bB.iterator();
        if (localIterator.hasNext())
        {
          b localb = (b)localIterator.next();
          if (localb.b(locala))
            return true;
          int k = jdMethod_if(localb.a(locala));
          int m = jdMethod_if(localb.a.b - locala.b);
          int n = jdMethod_if(localb.b.b - locala.b);
          if ((k <= 0) || (m > 0) || (n <= 0))
            break label193;
          i += 1;
          int j = i;
          if (k < 0)
          {
            j = i;
            if (n <= 0)
            {
              j = i;
              if (m > 0)
                j = i - 1;
            }
          }
          i = j;
          continue;
        }
        if (i == 0)
          return false;
      }
      catch (Exception localException)
      {
        return true;
      }
      return true;
    }
  }

  int jdMethod_if(double paramDouble)
  {
    if (paramDouble < -1.0E-008D)
      return -1;
    if (paramDouble > 1.0E-008D)
      return 1;
    return 0;
  }

  class a
  {
    double a;
    double b;

    a(double arg2, double arg4)
    {
      this.a = ???;
      Object localObject;
      this.b = localObject;
    }
  }

  class b
  {
    h.a a;
    h.a b;

    b(h.a parama1, h.a arg3)
    {
      this.a = parama1;
      Object localObject;
      this.b = localObject;
    }

    double a(h.a parama)
    {
      h.a locala = new h.a(h.this, this.b.a - this.a.a, this.b.b - this.a.b);
      parama = new h.a(h.this, parama.a - this.a.a, parama.b - this.a.b);
      return locala.a * parama.b - locala.b * parama.a;
    }

    boolean b(h.a parama)
    {
      return (h.this.jdMethod_if(a(parama)) == 0) && (parama.a < Math.max(this.a.a, this.b.a) + 1.0E-008D) && (parama.a > Math.min(this.a.a, this.b.a) - 1.0E-008D) && (parama.b < Math.max(this.a.b, this.b.b) + 1.0E-008D) && (parama.b > Math.min(this.a.b, this.b.b) - 1.0E-008D);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.h
 * JD-Core Version:    0.6.2
 */