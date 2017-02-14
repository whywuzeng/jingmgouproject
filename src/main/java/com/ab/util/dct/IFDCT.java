package com.ab.util.dct;

import java.lang.reflect.Array;

public class IFDCT
  implements DCT
{
  public static double[][] iFDctTransform(double[][] paramArrayOfDouble)
  {
    double[][] arrayOfDouble = (double[][])Array.newInstance(Double.TYPE, new int[] { 8, 8 });
    int i = 0;
    if (i >= 8)
    {
      i = 0;
      label33: if (i <= 7)
        break label81;
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
        label81: paramArrayOfDouble = new double[16];
        paramArrayOfDouble[0] = (arrayOfDouble[0][i] * 0.707106781D + arrayOfDouble[2][i] * 0.923879532D);
        paramArrayOfDouble[1] = (arrayOfDouble[4][i] * 0.707106781D + arrayOfDouble[6][i] * 0.382683432D);
        paramArrayOfDouble[2] = (arrayOfDouble[0][i] * 0.707106781D + arrayOfDouble[2][i] * 0.382683432D);
        paramArrayOfDouble[3] = (-arrayOfDouble[4][i] * 0.707106781D - arrayOfDouble[6][i] * 0.923879532D);
        paramArrayOfDouble[4] = (arrayOfDouble[0][i] * 0.707106781D - arrayOfDouble[2][i] * 0.382683432D);
        paramArrayOfDouble[5] = (-arrayOfDouble[4][i] * 0.707106781D + arrayOfDouble[6][i] * 0.923879532D);
        paramArrayOfDouble[6] = (arrayOfDouble[0][i] * 0.707106781D - arrayOfDouble[2][i] * 0.923879532D);
        paramArrayOfDouble[7] = (arrayOfDouble[4][i] * 0.707106781D - arrayOfDouble[6][i] * 0.382683432D);
        paramArrayOfDouble[8] = (arrayOfDouble[1][i] * 0.195090322D - arrayOfDouble[3][i] * 0.555570233D);
        paramArrayOfDouble[9] = (arrayOfDouble[5][i] * 0.831469612D - arrayOfDouble[7][i] * 0.98078528D);
        paramArrayOfDouble[10] = (arrayOfDouble[1][i] * 0.555570233D - arrayOfDouble[3][i] * 0.98078528D);
        paramArrayOfDouble[11] = (arrayOfDouble[5][i] * 0.195090322D + arrayOfDouble[7][i] * 0.831469612D);
        paramArrayOfDouble[12] = (arrayOfDouble[1][i] * 0.831469612D - arrayOfDouble[3][i] * 0.195090322D);
        paramArrayOfDouble[13] = (-arrayOfDouble[5][i] * 0.98078528D - arrayOfDouble[7][i] * 0.555570233D);
        paramArrayOfDouble[14] = (arrayOfDouble[1][i] * 0.98078528D + arrayOfDouble[3][i] * 0.831469612D);
        paramArrayOfDouble[15] = (arrayOfDouble[5][i] * 0.555570233D + arrayOfDouble[7][i] * 0.195090322D);
        paramArrayOfDouble[0] = (0.5D * (paramArrayOfDouble[0] + paramArrayOfDouble[1]));
        paramArrayOfDouble[1] = (0.5D * (paramArrayOfDouble[2] + paramArrayOfDouble[3]));
        paramArrayOfDouble[2] = (0.5D * (paramArrayOfDouble[4] + paramArrayOfDouble[5]));
        paramArrayOfDouble[3] = (0.5D * (paramArrayOfDouble[6] + paramArrayOfDouble[7]));
        paramArrayOfDouble[4] = (0.5D * (paramArrayOfDouble[8] + paramArrayOfDouble[9]));
        paramArrayOfDouble[5] = (0.5D * (paramArrayOfDouble[10] + paramArrayOfDouble[11]));
        paramArrayOfDouble[6] = (0.5D * (paramArrayOfDouble[12] + paramArrayOfDouble[13]));
        paramArrayOfDouble[7] = (0.5D * (paramArrayOfDouble[14] + paramArrayOfDouble[15]));
        arrayOfDouble[0][i] = (paramArrayOfDouble[0] + paramArrayOfDouble[7]);
        arrayOfDouble[1][i] = (paramArrayOfDouble[1] + paramArrayOfDouble[6]);
        arrayOfDouble[2][i] = (paramArrayOfDouble[2] + paramArrayOfDouble[5]);
        arrayOfDouble[3][i] = (paramArrayOfDouble[3] + paramArrayOfDouble[4]);
        arrayOfDouble[4][i] = (paramArrayOfDouble[3] - paramArrayOfDouble[4]);
        arrayOfDouble[5][i] = (paramArrayOfDouble[2] - paramArrayOfDouble[5]);
        arrayOfDouble[6][i] = (paramArrayOfDouble[1] - paramArrayOfDouble[6]);
        arrayOfDouble[7][i] = (paramArrayOfDouble[0] - paramArrayOfDouble[7]);
        i += 1;
        break label33;
      }
      paramArrayOfDouble = new double[16];
      paramArrayOfDouble[0] = (arrayOfDouble[i][0] * 0.707106781D + arrayOfDouble[i][2] * 0.923879532D);
      paramArrayOfDouble[1] = (arrayOfDouble[i][4] * 0.707106781D + arrayOfDouble[i][6] * 0.382683432D);
      paramArrayOfDouble[2] = (arrayOfDouble[i][0] * 0.707106781D + arrayOfDouble[i][2] * 0.382683432D);
      paramArrayOfDouble[3] = (-arrayOfDouble[i][4] * 0.707106781D - arrayOfDouble[i][6] * 0.923879532D);
      paramArrayOfDouble[4] = (arrayOfDouble[i][0] * 0.707106781D - arrayOfDouble[i][2] * 0.382683432D);
      paramArrayOfDouble[5] = (-arrayOfDouble[i][4] * 0.707106781D + arrayOfDouble[i][6] * 0.923879532D);
      paramArrayOfDouble[6] = (arrayOfDouble[i][0] * 0.707106781D - arrayOfDouble[i][2] * 0.923879532D);
      paramArrayOfDouble[7] = (arrayOfDouble[i][4] * 0.707106781D - arrayOfDouble[i][6] * 0.382683432D);
      paramArrayOfDouble[8] = (arrayOfDouble[i][1] * 0.195090322D - arrayOfDouble[i][3] * 0.555570233D);
      paramArrayOfDouble[9] = (arrayOfDouble[i][5] * 0.831469612D - arrayOfDouble[i][7] * 0.98078528D);
      paramArrayOfDouble[10] = (arrayOfDouble[i][1] * 0.555570233D - arrayOfDouble[i][3] * 0.98078528D);
      paramArrayOfDouble[11] = (arrayOfDouble[i][5] * 0.195090322D + arrayOfDouble[i][7] * 0.831469612D);
      paramArrayOfDouble[12] = (arrayOfDouble[i][1] * 0.831469612D - arrayOfDouble[i][3] * 0.195090322D);
      paramArrayOfDouble[13] = (-arrayOfDouble[i][5] * 0.98078528D - arrayOfDouble[i][7] * 0.555570233D);
      paramArrayOfDouble[14] = (arrayOfDouble[i][1] * 0.98078528D + arrayOfDouble[i][3] * 0.831469612D);
      paramArrayOfDouble[15] = (arrayOfDouble[i][5] * 0.555570233D + arrayOfDouble[i][7] * 0.195090322D);
      paramArrayOfDouble[0] = (0.5D * (paramArrayOfDouble[0] + paramArrayOfDouble[1]));
      paramArrayOfDouble[1] = (0.5D * (paramArrayOfDouble[2] + paramArrayOfDouble[3]));
      paramArrayOfDouble[2] = (0.5D * (paramArrayOfDouble[4] + paramArrayOfDouble[5]));
      paramArrayOfDouble[3] = (0.5D * (paramArrayOfDouble[6] + paramArrayOfDouble[7]));
      paramArrayOfDouble[4] = (0.5D * (paramArrayOfDouble[8] + paramArrayOfDouble[9]));
      paramArrayOfDouble[5] = (0.5D * (paramArrayOfDouble[10] + paramArrayOfDouble[11]));
      paramArrayOfDouble[6] = (0.5D * (paramArrayOfDouble[12] + paramArrayOfDouble[13]));
      paramArrayOfDouble[7] = (0.5D * (paramArrayOfDouble[14] + paramArrayOfDouble[15]));
      arrayOfDouble[i][0] = (paramArrayOfDouble[0] + paramArrayOfDouble[7]);
      arrayOfDouble[i][1] = (paramArrayOfDouble[1] + paramArrayOfDouble[6]);
      arrayOfDouble[i][2] = (paramArrayOfDouble[2] + paramArrayOfDouble[5]);
      arrayOfDouble[i][3] = (paramArrayOfDouble[3] + paramArrayOfDouble[4]);
      arrayOfDouble[i][4] = (paramArrayOfDouble[3] - paramArrayOfDouble[4]);
      arrayOfDouble[i][5] = (paramArrayOfDouble[2] - paramArrayOfDouble[5]);
      arrayOfDouble[i][6] = (paramArrayOfDouble[1] - paramArrayOfDouble[6]);
      arrayOfDouble[i][7] = (paramArrayOfDouble[0] - paramArrayOfDouble[7]);
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.dct.IFDCT
 * JD-Core Version:    0.6.2
 */