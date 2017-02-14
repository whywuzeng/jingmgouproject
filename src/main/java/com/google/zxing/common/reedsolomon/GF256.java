package com.google.zxing.common.reedsolomon;

public final class GF256
{
  public static final GF256 DATA_MATRIX_FIELD = new GF256(301);
  public static final GF256 QR_CODE_FIELD = new GF256(285);
  private final int[] expTable = new int[256];
  private final int[] logTable = new int[256];
  private final GF256Poly one;
  private final GF256Poly zero;

  private GF256(int paramInt)
  {
    int j = 0;
    int i = 1;
    while (j < 256)
    {
      this.expTable[j] = i;
      int k = i << 1;
      i = k;
      if (k >= 256)
        i = k ^ paramInt;
      j += 1;
    }
    paramInt = 0;
    while (paramInt < 255)
    {
      this.logTable[this.expTable[paramInt]] = paramInt;
      paramInt += 1;
    }
    this.zero = new GF256Poly(this, new int[] { 0 });
    this.one = new GF256Poly(this, new int[] { 1 });
  }

  static int addOrSubtract(int paramInt1, int paramInt2)
  {
    return paramInt1 ^ paramInt2;
  }

  GF256Poly buildMonomial(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException();
    if (paramInt2 == 0)
      return this.zero;
    int[] arrayOfInt = new int[paramInt1 + 1];
    arrayOfInt[0] = paramInt2;
    return new GF256Poly(this, arrayOfInt);
  }

  int exp(int paramInt)
  {
    return this.expTable[paramInt];
  }

  GF256Poly getOne()
  {
    return this.one;
  }

  GF256Poly getZero()
  {
    return this.zero;
  }

  int inverse(int paramInt)
  {
    if (paramInt == 0)
      throw new ArithmeticException();
    return this.expTable[(255 - this.logTable[paramInt])];
  }

  int log(int paramInt)
  {
    if (paramInt == 0)
      throw new IllegalArgumentException();
    return this.logTable[paramInt];
  }

  int multiply(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) || (paramInt2 == 0))
      return 0;
    paramInt1 = this.logTable[paramInt1] + this.logTable[paramInt2];
    return this.expTable[((paramInt1 >>> 8) + (paramInt1 & 0xFF))];
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.GF256
 * JD-Core Version:    0.6.2
 */