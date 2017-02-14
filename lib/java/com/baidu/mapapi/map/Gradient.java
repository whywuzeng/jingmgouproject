package com.baidu.mapapi.map;

import android.graphics.Color;
import java.util.HashMap;

public class Gradient
{
  private final int a;
  private final int[] b;
  private final float[] c;

  public Gradient(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    this(paramArrayOfInt, paramArrayOfFloat, 1000);
  }

  private Gradient(int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfFloat == null))
      throw new IllegalArgumentException("colors and startPoints should not be null");
    if (paramArrayOfInt.length != paramArrayOfFloat.length)
      throw new IllegalArgumentException("colors and startPoints should be same length");
    if (paramArrayOfInt.length == 0)
      throw new IllegalArgumentException("No colors have been defined");
    int i = 1;
    while (i < paramArrayOfFloat.length)
    {
      if (paramArrayOfFloat[i] <= paramArrayOfFloat[(i - 1)])
        throw new IllegalArgumentException("startPoints should be in increasing order");
      i += 1;
    }
    this.a = paramInt;
    this.b = new int[paramArrayOfInt.length];
    this.c = new float[paramArrayOfFloat.length];
    System.arraycopy(paramArrayOfInt, 0, this.b, 0, paramArrayOfInt.length);
    System.arraycopy(paramArrayOfFloat, 0, this.c, 0, paramArrayOfFloat.length);
  }

  private static int a(int paramInt1, int paramInt2, float paramFloat)
  {
    int i = 0;
    int j = (int)((Color.alpha(paramInt2) - Color.alpha(paramInt1)) * paramFloat + Color.alpha(paramInt1));
    float[] arrayOfFloat1 = new float[3];
    Color.RGBToHSV(Color.red(paramInt1), Color.green(paramInt1), Color.blue(paramInt1), arrayOfFloat1);
    float[] arrayOfFloat2 = new float[3];
    Color.RGBToHSV(Color.red(paramInt2), Color.green(paramInt2), Color.blue(paramInt2), arrayOfFloat2);
    if (arrayOfFloat1[0] - arrayOfFloat2[0] > 180.0F)
      arrayOfFloat2[0] += 360.0F;
    float[] arrayOfFloat3;
    while (true)
    {
      arrayOfFloat3 = new float[3];
      paramInt1 = i;
      while (paramInt1 < 3)
      {
        arrayOfFloat3[paramInt1] = ((arrayOfFloat2[paramInt1] - arrayOfFloat1[paramInt1]) * paramFloat + arrayOfFloat1[paramInt1]);
        paramInt1 += 1;
      }
      if (arrayOfFloat2[0] - arrayOfFloat1[0] > 180.0F)
        arrayOfFloat1[0] += 360.0F;
    }
    return Color.HSVToColor(j, arrayOfFloat3);
  }

  private HashMap<Integer, a> a()
  {
    HashMap localHashMap = new HashMap();
    int j;
    float f;
    if (this.c[0] != 0.0F)
    {
      i = Color.argb(0, Color.red(this.b[0]), Color.green(this.b[0]), Color.blue(this.b[0]));
      j = this.b[0];
      f = this.a;
      localHashMap.put(Integer.valueOf(0), new a(i, j, this.c[0] * f, null));
    }
    int i = 1;
    while (i < this.b.length)
    {
      j = (int)(this.a * this.c[(i - 1)]);
      int k = this.b[(i - 1)];
      int m = this.b[i];
      f = this.a;
      localHashMap.put(Integer.valueOf(j), new a(k, m, (this.c[i] - this.c[(i - 1)]) * f, null));
      i += 1;
    }
    if (this.c[(this.c.length - 1)] != 1.0F)
    {
      i = this.c.length - 1;
      localHashMap.put(Integer.valueOf((int)(this.a * this.c[i])), new a(this.b[i], this.b[i], this.a * (1.0F - this.c[i]), null));
    }
    return localHashMap;
  }

  int[] a(double paramDouble)
  {
    int k = 0;
    HashMap localHashMap = a();
    int[] arrayOfInt = new int[this.a];
    a locala = (a)localHashMap.get(Integer.valueOf(0));
    int i = 0;
    int j = 0;
    if (i < this.a)
    {
      if (!localHashMap.containsKey(Integer.valueOf(i)))
        break label189;
      locala = (a)localHashMap.get(Integer.valueOf(i));
      j = i;
    }
    label189: 
    while (true)
    {
      float f = (i - j) / a.a(locala);
      arrayOfInt[i] = a(a.b(locala), a.c(locala), f);
      i += 1;
      break;
      if (paramDouble != 1.0D)
      {
        i = k;
        while (i < this.a)
        {
          j = arrayOfInt[i];
          arrayOfInt[i] = Color.argb((int)(Color.alpha(j) * paramDouble), Color.red(j), Color.green(j), Color.blue(j));
          i += 1;
        }
      }
      return arrayOfInt;
    }
  }

  private class a
  {
    private final int b;
    private final int c;
    private final float d;

    private a(int paramInt1, int paramFloat, float arg4)
    {
      this.b = paramInt1;
      this.c = paramFloat;
      Object localObject;
      this.d = localObject;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Gradient
 * JD-Core Version:    0.6.2
 */