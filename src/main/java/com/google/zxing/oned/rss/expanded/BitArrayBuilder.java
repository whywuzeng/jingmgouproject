package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.DataCharacter;
import java.util.Vector;

final class BitArrayBuilder
{
  static BitArray buildBitArray(Vector paramVector)
  {
    int i = (paramVector.size() << 1) - 1;
    if (((ExpandedPair)paramVector.lastElement()).getRightChar() == null)
      i -= 1;
    while (true)
    {
      BitArray localBitArray = new BitArray(i * 12);
      int k = ((ExpandedPair)paramVector.elementAt(0)).getRightChar().getValue();
      int j = 11;
      i = 0;
      while (j >= 0)
      {
        if ((1 << j & k) != 0)
          localBitArray.set(i);
        j -= 1;
        i += 1;
      }
      k = 1;
      while (k < paramVector.size())
      {
        ExpandedPair localExpandedPair = (ExpandedPair)paramVector.elementAt(k);
        int m = localExpandedPair.getLeftChar().getValue();
        j = 11;
        while (j >= 0)
        {
          if ((1 << j & m) != 0)
            localBitArray.set(i);
          j -= 1;
          i += 1;
        }
        j = i;
        if (localExpandedPair.getRightChar() != null)
        {
          int n = localExpandedPair.getRightChar().getValue();
          m = 11;
          while (true)
          {
            j = i;
            if (m < 0)
              break;
            if ((1 << m & n) != 0)
              localBitArray.set(i);
            i += 1;
            m -= 1;
          }
        }
        k += 1;
        i = j;
      }
      return localBitArray;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.BitArrayBuilder
 * JD-Core Version:    0.6.2
 */