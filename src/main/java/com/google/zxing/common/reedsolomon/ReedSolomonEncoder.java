package com.google.zxing.common.reedsolomon;

import java.util.Vector;

public final class ReedSolomonEncoder
{
  private final Vector cachedGenerators;
  private final GF256 field;

  public ReedSolomonEncoder(GF256 paramGF256)
  {
    if (!GF256.QR_CODE_FIELD.equals(paramGF256))
      throw new IllegalArgumentException("Only QR Code is supported at this time");
    this.field = paramGF256;
    this.cachedGenerators = new Vector();
    this.cachedGenerators.addElement(new GF256Poly(paramGF256, new int[] { 1 }));
  }

  private GF256Poly buildGenerator(int paramInt)
  {
    if (paramInt >= this.cachedGenerators.size())
    {
      GF256Poly localGF256Poly = (GF256Poly)this.cachedGenerators.elementAt(this.cachedGenerators.size() - 1);
      int i = this.cachedGenerators.size();
      while (i <= paramInt)
      {
        localGF256Poly = localGF256Poly.multiply(new GF256Poly(this.field, new int[] { 1, this.field.exp(i - 1) }));
        this.cachedGenerators.addElement(localGF256Poly);
        i += 1;
      }
    }
    return (GF256Poly)this.cachedGenerators.elementAt(paramInt);
  }

  public void encode(int[] paramArrayOfInt, int paramInt)
  {
    if (paramInt == 0)
      throw new IllegalArgumentException("No error correction bytes");
    int i = paramArrayOfInt.length - paramInt;
    if (i <= 0)
      throw new IllegalArgumentException("No data bytes provided");
    Object localObject = buildGenerator(paramInt);
    int[] arrayOfInt = new int[i];
    System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, i);
    localObject = new GF256Poly(this.field, arrayOfInt).multiplyByMonomial(paramInt, 1).divide(localObject)[1].getCoefficients();
    int j = paramInt - localObject.length;
    paramInt = 0;
    while (paramInt < j)
    {
      paramArrayOfInt[(i + paramInt)] = 0;
      paramInt += 1;
    }
    System.arraycopy(localObject, 0, paramArrayOfInt, i + j, localObject.length);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.ReedSolomonEncoder
 * JD-Core Version:    0.6.2
 */