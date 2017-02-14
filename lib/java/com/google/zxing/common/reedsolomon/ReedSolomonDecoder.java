package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder
{
  private final GF256 field;

  public ReedSolomonDecoder(GF256 paramGF256)
  {
    this.field = paramGF256;
  }

  private int[] findErrorLocations(GF256Poly paramGF256Poly)
    throws ReedSolomonException
  {
    int i = 1;
    int m = paramGF256Poly.getDegree();
    if (m == 1)
      return new int[] { paramGF256Poly.getCoefficient(1) };
    int[] arrayOfInt = new int[m];
    int k;
    for (int j = 0; (i < 256) && (j < m); j = k)
    {
      k = j;
      if (paramGF256Poly.evaluateAt(i) == 0)
      {
        arrayOfInt[j] = this.field.inverse(i);
        k = j + 1;
      }
      i += 1;
    }
    if (j != m)
      throw new ReedSolomonException("Error locator degree does not match number of roots");
    return arrayOfInt;
  }

  private int[] findErrorMagnitudes(GF256Poly paramGF256Poly, int[] paramArrayOfInt, boolean paramBoolean)
  {
    int n = paramArrayOfInt.length;
    int[] arrayOfInt = new int[n];
    int j = 0;
    int i1;
    int i;
    int k;
    label39: int m;
    if (j < n)
    {
      i1 = this.field.inverse(paramArrayOfInt[j]);
      i = 1;
      k = 0;
      if (k < n)
      {
        if (j == k)
          break label175;
        m = this.field.multiply(paramArrayOfInt[k], i1);
        if ((m & 0x1) == 0)
        {
          m |= 1;
          label81: i = this.field.multiply(i, m);
        }
      }
    }
    label175: 
    while (true)
    {
      k += 1;
      break label39;
      m &= -2;
      break label81;
      arrayOfInt[j] = this.field.multiply(paramGF256Poly.evaluateAt(i1), this.field.inverse(i));
      if (paramBoolean)
        arrayOfInt[j] = this.field.multiply(arrayOfInt[j], i1);
      j += 1;
      break;
      return arrayOfInt;
    }
  }

  private GF256Poly[] runEuclideanAlgorithm(GF256Poly paramGF256Poly1, GF256Poly paramGF256Poly2, int paramInt)
    throws ReedSolomonException
  {
    if (paramGF256Poly1.getDegree() < paramGF256Poly2.getDegree());
    while (true)
    {
      GF256Poly localGF256Poly1 = this.field.getOne();
      GF256Poly localGF256Poly2 = this.field.getZero();
      Object localObject3 = this.field.getZero();
      Object localObject2 = this.field.getOne();
      Object localObject1 = paramGF256Poly2;
      paramGF256Poly2 = paramGF256Poly1;
      paramGF256Poly1 = localGF256Poly2;
      while (paramGF256Poly2.getDegree() >= paramInt / 2)
      {
        if (paramGF256Poly2.isZero())
          throw new ReedSolomonException("r_{i-1} was zero");
        localGF256Poly2 = this.field.getZero();
        int i = paramGF256Poly2.getCoefficient(paramGF256Poly2.getDegree());
        i = this.field.inverse(i);
        while ((((GF256Poly)localObject1).getDegree() >= paramGF256Poly2.getDegree()) && (!((GF256Poly)localObject1).isZero()))
        {
          int j = ((GF256Poly)localObject1).getDegree() - paramGF256Poly2.getDegree();
          int k = this.field.multiply(((GF256Poly)localObject1).getCoefficient(((GF256Poly)localObject1).getDegree()), i);
          localGF256Poly2 = localGF256Poly2.addOrSubtract(this.field.buildMonomial(j, k));
          localObject1 = ((GF256Poly)localObject1).addOrSubtract(paramGF256Poly2.multiplyByMonomial(j, k));
        }
        GF256Poly localGF256Poly3 = localGF256Poly2.multiply(paramGF256Poly1).addOrSubtract(localGF256Poly1);
        localObject3 = localGF256Poly2.multiply((GF256Poly)localObject2).addOrSubtract((GF256Poly)localObject3);
        localGF256Poly2 = paramGF256Poly2;
        paramGF256Poly2 = (GF256Poly)localObject1;
        localGF256Poly1 = paramGF256Poly1;
        paramGF256Poly1 = localGF256Poly3;
        localObject1 = localObject3;
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localGF256Poly2;
      }
      paramInt = ((GF256Poly)localObject2).getCoefficient(0);
      if (paramInt == 0)
        throw new ReedSolomonException("sigmaTilde(0) was zero");
      paramInt = this.field.inverse(paramInt);
      return new GF256Poly[] { ((GF256Poly)localObject2).multiply(paramInt), paramGF256Poly2.multiply(paramInt) };
      localObject1 = paramGF256Poly1;
      paramGF256Poly1 = paramGF256Poly2;
      paramGF256Poly2 = (GF256Poly)localObject1;
    }
  }

  public void decode(int[] paramArrayOfInt, int paramInt)
    throws ReedSolomonException
  {
    int m = 0;
    Object localObject1 = new GF256Poly(this.field, paramArrayOfInt);
    Object localObject2 = new int[paramInt];
    boolean bool = this.field.equals(GF256.DATA_MATRIX_FIELD);
    int i = 0;
    int j = 1;
    int k;
    if (i < paramInt)
    {
      GF256 localGF256 = this.field;
      if (bool)
      {
        k = i + 1;
        label60: k = ((GF256Poly)localObject1).evaluateAt(localGF256.exp(k));
        localObject2[(localObject2.length - 1 - i)] = k;
        if (k == 0)
          break label239;
        j = 0;
      }
    }
    label239: 
    while (true)
    {
      i += 1;
      break;
      k = i;
      break label60;
      if (j != 0);
      while (true)
      {
        return;
        localObject1 = new GF256Poly(this.field, (int[])localObject2);
        localObject2 = runEuclideanAlgorithm(this.field.buildMonomial(paramInt, 1), (GF256Poly)localObject1, paramInt);
        localObject1 = localObject2[0];
        localObject2 = localObject2[1];
        localObject1 = findErrorLocations((GF256Poly)localObject1);
        localObject2 = findErrorMagnitudes((GF256Poly)localObject2, (int[])localObject1, bool);
        paramInt = m;
        while (paramInt < localObject1.length)
        {
          i = paramArrayOfInt.length - 1 - this.field.log(localObject1[paramInt]);
          if (i < 0)
            throw new ReedSolomonException("Bad error location");
          paramArrayOfInt[i] = GF256.addOrSubtract(paramArrayOfInt[i], localObject2[paramInt]);
          paramInt += 1;
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.ReedSolomonDecoder
 * JD-Core Version:    0.6.2
 */