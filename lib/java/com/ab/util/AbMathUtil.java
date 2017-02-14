package com.ab.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;

public class AbMathUtil
{
  public static int[][] arrayToMatrix(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { paramInt2, paramInt1 });
    int i = 0;
    if (i >= paramInt2)
      return arrayOfInt;
    int j = 0;
    while (true)
    {
      if (j >= paramInt1)
      {
        i += 1;
        break;
      }
      arrayOfInt[i][j] = paramArrayOfInt[(j * paramInt2 + i)];
      j += 1;
    }
  }

  public static int average(double[] paramArrayOfDouble)
  {
    float f = 0.0F;
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfDouble.length)
        return (int)(f / paramArrayOfDouble.length);
      f = (float)(f + paramArrayOfDouble[i]);
      i += 1;
    }
  }

  public static int average(int[] paramArrayOfInt)
  {
    float f = 0.0F;
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfInt.length)
        return (int)(f / paramArrayOfInt.length);
      f += paramArrayOfInt[i];
      i += 1;
    }
  }

  public static char binaryToHex(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return ' ';
    case 0:
      return '0';
    case 1:
      return '1';
    case 2:
      return '2';
    case 3:
      return '3';
    case 4:
      return '4';
    case 5:
      return '5';
    case 6:
      return '6';
    case 7:
      return '7';
    case 8:
      return '8';
    case 9:
      return '9';
    case 10:
      return 'a';
    case 11:
      return 'b';
    case 12:
      return 'c';
    case 13:
      return 'd';
    case 14:
      return 'e';
    case 15:
    }
    return 'f';
  }

  public static String byte2HexStr(byte[] paramArrayOfByte, int paramInt)
  {
    String str1 = "";
    int i = 0;
    if (i >= paramInt)
      return str1.toUpperCase();
    String str2 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
    if (str2.length() == 1);
    for (str1 = str1 + "0" + str2; ; str1 = str1 + str2)
    {
      str1 = str1 + ",";
      i += 1;
      break;
    }
  }

  public static double[] intToDoubleArray(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    double[] arrayOfDouble = new double[j];
    int i = 0;
    while (true)
    {
      if (i >= j)
        return arrayOfDouble;
      arrayOfDouble[i] = Double.valueOf(String.valueOf(paramArrayOfInt[i])).doubleValue();
      i += 1;
    }
  }

  public static double[][] intToDoubleMatrix(int[][] paramArrayOfInt)
  {
    int k = paramArrayOfInt.length;
    int m = paramArrayOfInt[0].length;
    double[][] arrayOfDouble = (double[][])Array.newInstance(Double.TYPE, new int[] { k, m });
    int i = 0;
    if (i >= k)
      return arrayOfDouble;
    int j = 0;
    while (true)
    {
      if (j >= m)
      {
        i += 1;
        break;
      }
      arrayOfDouble[i][j] = Double.valueOf(String.valueOf(paramArrayOfInt[i][j])).doubleValue();
      j += 1;
    }
  }

  public static double[] matrixToArray(double[][] paramArrayOfDouble)
  {
    double[] arrayOfDouble = new double[paramArrayOfDouble.length * paramArrayOfDouble[0].length];
    int i = 0;
    if (i >= paramArrayOfDouble.length)
      return arrayOfDouble;
    int j = 0;
    while (true)
    {
      if (j >= paramArrayOfDouble[i].length)
      {
        i += 1;
        break;
      }
      arrayOfDouble[(paramArrayOfDouble.length * j + i)] = paramArrayOfDouble[i][j];
      j += 1;
    }
  }

  public static BigDecimal round(double paramDouble, int paramInt)
  {
    return new BigDecimal(paramDouble).setScale(paramInt, 4);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.AbMathUtil
 * JD-Core Version:    0.6.2
 */