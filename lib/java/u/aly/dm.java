package u.aly;

import java.util.BitSet;

public final class dm extends da
{
  public dm(du paramdu)
  {
    super(paramdu);
  }

  public static BitSet a(byte[] paramArrayOfByte)
  {
    BitSet localBitSet = new BitSet();
    int i = 0;
    while (i < paramArrayOfByte.length * 8)
    {
      if ((paramArrayOfByte[(paramArrayOfByte.length - i / 8 - 1)] & 1 << i % 8) > 0)
        localBitSet.set(i);
      i += 1;
    }
    return localBitSet;
  }

  public static byte[] b(BitSet paramBitSet, int paramInt)
  {
    byte[] arrayOfByte = new byte[(int)Math.ceil(paramInt / 8.0D)];
    paramInt = 0;
    while (paramInt < paramBitSet.length())
    {
      if (paramBitSet.get(paramInt))
      {
        int i = arrayOfByte.length - paramInt / 8 - 1;
        arrayOfByte[i] = ((byte)(arrayOfByte[i] | 1 << paramInt % 8));
      }
      paramInt += 1;
    }
    return arrayOfByte;
  }

  public Class<? extends do> D()
  {
    return dr.class;
  }

  public void a(BitSet paramBitSet, int paramInt)
    throws cn
  {
    paramBitSet = b(paramBitSet, paramInt);
    int i = paramBitSet.length;
    paramInt = 0;
    while (paramInt < i)
    {
      a(paramBitSet[paramInt]);
      paramInt += 1;
    }
  }

  public BitSet b(int paramInt)
    throws cn
  {
    int i = (int)Math.ceil(paramInt / 8.0D);
    byte[] arrayOfByte = new byte[i];
    paramInt = 0;
    while (paramInt < i)
    {
      arrayOfByte[paramInt] = u();
      paramInt += 1;
    }
    return a(arrayOfByte);
  }

  public static class a
    implements di
  {
    public dg a(du paramdu)
    {
      return new dm(paramdu);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.dm
 * JD-Core Version:    0.6.2
 */