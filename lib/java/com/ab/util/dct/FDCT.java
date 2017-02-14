package com.ab.util.dct;

import java.lang.reflect.Array;

public class FDCT
  implements DCT
{
  public static double[][] fDctTransform(double[][] paramArrayOfDouble)
  {
    double[][] arrayOfDouble = (double[][])Array.newInstance(Double.TYPE, new int[] { 8, 8 });
    int i = 0;
    if (i >= 8)
    {
      i = 0;
      label37: if (i <= 7)
        break label100;
      i = 0;
    }
    while (true)
    {
      if (i > 7)
      {
        return arrayOfDouble;
        int j = 0;
        while (true)
        {
          if (j >= 8)
          {
            i += 1;
            break;
          }
          arrayOfDouble[i][j] = paramArrayOfDouble[i][j];
          j += 1;
        }
        label100: d9 = arrayOfDouble[i][0] + arrayOfDouble[i][7];
        d7 = arrayOfDouble[i][1] + arrayOfDouble[i][6];
        d8 = arrayOfDouble[i][2] + arrayOfDouble[i][5];
        d10 = arrayOfDouble[i][3] + arrayOfDouble[i][4];
        d1 = d9 + d10;
        d2 = d7 + d8;
        d3 = arrayOfDouble[i][0] - arrayOfDouble[i][7];
        d4 = arrayOfDouble[i][1] - arrayOfDouble[i][6];
        d5 = arrayOfDouble[i][2] - arrayOfDouble[i][5];
        d6 = arrayOfDouble[i][3] - arrayOfDouble[i][4];
        d9 -= d10;
        d7 -= d8;
        arrayOfDouble[i][0] = (0.5D * (0.707106781D * (d1 + d2)));
        arrayOfDouble[i][1] = (0.5D * (0.98078528D * d3 + 0.831469612D * d4 + 0.555570233D * d5 + 0.195090322D * d6));
        arrayOfDouble[i][2] = (0.5D * (0.923879532D * d9 + 0.382683432D * d7));
        arrayOfDouble[i][3] = (0.5D * (0.831469612D * d3 - 0.195090322D * d4 - 0.98078528D * d5 - 0.555570233D * d6));
        arrayOfDouble[i][4] = (0.5D * (0.707106781D * (d1 - d2)));
        arrayOfDouble[i][5] = (0.5D * (0.555570233D * d3 - 0.98078528D * d4 + 0.195090322D * d5 + 0.831469612D * d6));
        arrayOfDouble[i][6] = (0.5D * (0.382683432D * d9 - 0.923879532D * d7));
        arrayOfDouble[i][7] = (0.5D * (0.195090322D * d3 - 0.555570233D * d4 + 0.831469612D * d5 - 0.98078528D * d6));
        i += 1;
        break label37;
      }
      double d9 = arrayOfDouble[0][i] + arrayOfDouble[7][i];
      double d7 = arrayOfDouble[1][i] + arrayOfDouble[6][i];
      double d8 = arrayOfDouble[2][i] + arrayOfDouble[5][i];
      double d10 = arrayOfDouble[3][i] + arrayOfDouble[4][i];
      double d1 = d9 + d10;
      double d2 = d7 + d8;
      double d3 = arrayOfDouble[0][i] - arrayOfDouble[7][i];
      double d4 = arrayOfDouble[1][i] - arrayOfDouble[6][i];
      double d5 = arrayOfDouble[2][i] - arrayOfDouble[5][i];
      double d6 = arrayOfDouble[3][i] - arrayOfDouble[4][i];
      d9 -= d10;
      d7 -= d8;
      arrayOfDouble[0][i] = (0.5D * (0.707106781D * (d1 + d2)));
      arrayOfDouble[1][i] = (0.5D * (0.98078528D * d3 + 0.831469612D * d4 + 0.555570233D * d5 + 0.195090322D * d6));
      arrayOfDouble[2][i] = (0.5D * (0.923879532D * d9 + 0.382683432D * d7));
      arrayOfDouble[3][i] = (0.5D * (0.831469612D * d3 - 0.195090322D * d4 - 0.98078528D * d5 - 0.555570233D * d6));
      arrayOfDouble[4][i] = (0.5D * (0.707106781D * (d1 - d2)));
      arrayOfDouble[5][i] = (0.5D * (0.555570233D * d3 - 0.98078528D * d4 + 0.195090322D * d5 + 0.831469612D * d6));
      arrayOfDouble[6][i] = (0.5D * (0.382683432D * d9 - 0.923879532D * d7));
      arrayOfDouble[7][i] = (0.5D * (0.195090322D * d3 - 0.555570233D * d4 + 0.831469612D * d5 - 0.98078528D * d6));
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.dct.FDCT
 * JD-Core Version:    0.6.2
 */