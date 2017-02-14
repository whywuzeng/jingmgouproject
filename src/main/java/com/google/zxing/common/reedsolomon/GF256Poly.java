package com.google.zxing.common.reedsolomon;

final class GF256Poly
{
  private final int[] coefficients;
  private final GF256 field;

  GF256Poly(GF256 paramGF256, int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
      throw new IllegalArgumentException();
    this.field = paramGF256;
    int j = paramArrayOfInt.length;
    if ((j > 1) && (paramArrayOfInt[0] == 0))
    {
      while ((i < j) && (paramArrayOfInt[i] == 0))
        i += 1;
      if (i == j)
      {
        this.coefficients = paramGF256.getZero().coefficients;
        return;
      }
      this.coefficients = new int[j - i];
      System.arraycopy(paramArrayOfInt, i, this.coefficients, 0, this.coefficients.length);
      return;
    }
    this.coefficients = paramArrayOfInt;
  }

  GF256Poly addOrSubtract(GF256Poly paramGF256Poly)
  {
    if (!this.field.equals(paramGF256Poly.field))
      throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
    if (isZero())
      return paramGF256Poly;
    if (paramGF256Poly.isZero())
      return this;
    Object localObject = this.coefficients;
    int[] arrayOfInt = paramGF256Poly.coefficients;
    if (localObject.length > arrayOfInt.length)
      paramGF256Poly = arrayOfInt;
    while (true)
    {
      arrayOfInt = new int[localObject.length];
      int j = localObject.length - paramGF256Poly.length;
      System.arraycopy(localObject, 0, arrayOfInt, 0, j);
      int i = j;
      while (i < localObject.length)
      {
        arrayOfInt[i] = GF256.addOrSubtract(paramGF256Poly[(i - j)], localObject[i]);
        i += 1;
      }
      return new GF256Poly(this.field, arrayOfInt);
      paramGF256Poly = (GF256Poly)localObject;
      localObject = arrayOfInt;
    }
  }

  GF256Poly[] divide(GF256Poly paramGF256Poly)
  {
    if (!this.field.equals(paramGF256Poly.field))
      throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
    if (paramGF256Poly.isZero())
      throw new IllegalArgumentException("Divide by 0");
    GF256Poly localGF256Poly2 = this.field.getZero();
    int i = paramGF256Poly.getCoefficient(paramGF256Poly.getDegree());
    i = this.field.inverse(i);
    GF256Poly localGF256Poly3;
    for (GF256Poly localGF256Poly1 = this; (localGF256Poly1.getDegree() >= paramGF256Poly.getDegree()) && (!localGF256Poly1.isZero()); localGF256Poly1 = localGF256Poly1.addOrSubtract(localGF256Poly3))
    {
      int j = localGF256Poly1.getDegree() - paramGF256Poly.getDegree();
      int k = this.field.multiply(localGF256Poly1.getCoefficient(localGF256Poly1.getDegree()), i);
      localGF256Poly3 = paramGF256Poly.multiplyByMonomial(j, k);
      localGF256Poly2 = localGF256Poly2.addOrSubtract(this.field.buildMonomial(j, k));
    }
    return new GF256Poly[] { localGF256Poly2, localGF256Poly1 };
  }

  int evaluateAt(int paramInt)
  {
    int j = 0;
    int i;
    if (paramInt == 0)
    {
      i = getCoefficient(0);
      return i;
    }
    int m = this.coefficients.length;
    if (paramInt == 1)
    {
      paramInt = 0;
      while (true)
      {
        i = paramInt;
        if (j >= m)
          break;
        paramInt = GF256.addOrSubtract(paramInt, this.coefficients[j]);
        j += 1;
      }
    }
    j = this.coefficients[0];
    int k = 1;
    while (true)
    {
      i = j;
      if (k >= m)
        break;
      j = GF256.addOrSubtract(this.field.multiply(paramInt, j), this.coefficients[k]);
      k += 1;
    }
  }

  int getCoefficient(int paramInt)
  {
    return this.coefficients[(this.coefficients.length - 1 - paramInt)];
  }

  int[] getCoefficients()
  {
    return this.coefficients;
  }

  int getDegree()
  {
    return this.coefficients.length - 1;
  }

  boolean isZero()
  {
    boolean bool = false;
    if (this.coefficients[0] == 0)
      bool = true;
    return bool;
  }

  GF256Poly multiply(int paramInt)
  {
    if (paramInt == 0)
      localObject = this.field.getZero();
    do
    {
      return localObject;
      localObject = this;
    }
    while (paramInt == 1);
    int j = this.coefficients.length;
    Object localObject = new int[j];
    int i = 0;
    while (i < j)
    {
      localObject[i] = this.field.multiply(this.coefficients[i], paramInt);
      i += 1;
    }
    return new GF256Poly(this.field, (int[])localObject);
  }

  GF256Poly multiply(GF256Poly paramGF256Poly)
  {
    if (!this.field.equals(paramGF256Poly.field))
      throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
    if ((isZero()) || (paramGF256Poly.isZero()))
      return this.field.getZero();
    int[] arrayOfInt1 = this.coefficients;
    int k = arrayOfInt1.length;
    paramGF256Poly = paramGF256Poly.coefficients;
    int m = paramGF256Poly.length;
    int[] arrayOfInt2 = new int[k + m - 1];
    int i = 0;
    while (i < k)
    {
      int n = arrayOfInt1[i];
      int j = 0;
      while (j < m)
      {
        arrayOfInt2[(i + j)] = GF256.addOrSubtract(arrayOfInt2[(i + j)], this.field.multiply(n, paramGF256Poly[j]));
        j += 1;
      }
      i += 1;
    }
    return new GF256Poly(this.field, arrayOfInt2);
  }

  GF256Poly multiplyByMonomial(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException();
    if (paramInt2 == 0)
      return this.field.getZero();
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[i + paramInt1];
    paramInt1 = 0;
    while (paramInt1 < i)
    {
      arrayOfInt[paramInt1] = this.field.multiply(this.coefficients[paramInt1], paramInt2);
      paramInt1 += 1;
    }
    return new GF256Poly(this.field, arrayOfInt);
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(getDegree() * 8);
    int i = getDegree();
    if (i >= 0)
    {
      int k = getCoefficient(i);
      int j;
      if (k != 0)
      {
        if (k >= 0)
          break label104;
        localStringBuffer.append(" - ");
        j = -k;
        label50: if ((i == 0) || (j != 1))
        {
          j = this.field.log(j);
          if (j != 0)
            break label127;
          localStringBuffer.append('1');
        }
        label80: if (i != 0)
        {
          if (i != 1)
            break label161;
          localStringBuffer.append('x');
        }
      }
      while (true)
      {
        i -= 1;
        break;
        label104: j = k;
        if (localStringBuffer.length() <= 0)
          break label50;
        localStringBuffer.append(" + ");
        j = k;
        break label50;
        label127: if (j == 1)
        {
          localStringBuffer.append('a');
          break label80;
        }
        localStringBuffer.append("a^");
        localStringBuffer.append(j);
        break label80;
        label161: localStringBuffer.append("x^");
        localStringBuffer.append(i);
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.GF256Poly
 * JD-Core Version:    0.6.2
 */