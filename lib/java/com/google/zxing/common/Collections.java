package com.google.zxing.common;

import java.util.Vector;

public final class Collections
{
  public static void insertionSort(Vector paramVector, Comparator paramComparator)
  {
    int k = paramVector.size();
    int i = 1;
    while (i < k)
    {
      Object localObject1 = paramVector.elementAt(i);
      int j = i - 1;
      while (j >= 0)
      {
        Object localObject2 = paramVector.elementAt(j);
        if (paramComparator.compare(localObject2, localObject1) <= 0)
          break;
        paramVector.setElementAt(localObject2, j + 1);
        j -= 1;
      }
      paramVector.setElementAt(localObject1, j + 1);
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.Collections
 * JD-Core Version:    0.6.2
 */