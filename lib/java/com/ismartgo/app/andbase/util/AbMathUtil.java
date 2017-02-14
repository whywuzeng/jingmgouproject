package com.ismartgo.app.andbase.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;

public class AbMathUtil
{
  public static boolean LineAtLine(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8)
  {
    if ((paramDouble4 - paramDouble2) / (paramDouble3 - paramDouble1) == (paramDouble8 - paramDouble6) / (paramDouble7 - paramDouble5))
      return false;
    paramDouble1 = (paramDouble1 * paramDouble4 - paramDouble2 * paramDouble3 - (paramDouble4 - paramDouble2) * (((paramDouble1 * paramDouble4 - paramDouble2 * paramDouble3) * (paramDouble5 - paramDouble7) - (paramDouble5 * paramDouble8 - paramDouble6 * paramDouble7) * (paramDouble1 - paramDouble3)) / ((paramDouble4 - paramDouble2) * (paramDouble5 - paramDouble7) - (paramDouble8 - paramDouble6) * (paramDouble1 - paramDouble3)))) / (paramDouble1 - paramDouble3);
    return true;
  }

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

  public static boolean circleAtRect(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7)
  {
    if ((paramDouble1 >= Math.min(paramDouble4, paramDouble6)) && (paramDouble1 <= Math.max(paramDouble4, paramDouble6)) && (paramDouble2 >= Math.min(paramDouble5, paramDouble7)) && (paramDouble2 <= Math.max(paramDouble5, paramDouble7)))
    {
      paramDouble4 = Math.abs(paramDouble1 - paramDouble4);
      paramDouble5 = Math.abs(paramDouble2 - paramDouble7);
      paramDouble1 = Math.abs(paramDouble1 - paramDouble6);
      paramDouble2 = Math.abs(paramDouble2 - paramDouble7);
      return (paramDouble3 <= paramDouble4) && (paramDouble3 <= paramDouble5) && (paramDouble3 <= paramDouble1) && (paramDouble3 <= paramDouble2);
    }
    return false;
  }

  public static boolean eLineAtELine(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8)
  {
    if ((paramDouble4 - paramDouble2) / (paramDouble3 - paramDouble1) == (paramDouble8 - paramDouble6) / (paramDouble7 - paramDouble5))
      return false;
    double d1 = ((paramDouble1 * paramDouble4 - paramDouble2 * paramDouble3) * (paramDouble5 - paramDouble7) - (paramDouble5 * paramDouble8 - paramDouble6 * paramDouble7) * (paramDouble1 - paramDouble3)) / ((paramDouble4 - paramDouble2) * (paramDouble5 - paramDouble7) - (paramDouble8 - paramDouble6) * (paramDouble1 - paramDouble3));
    double d2 = (paramDouble1 * paramDouble4 - paramDouble2 * paramDouble3 - (paramDouble4 - paramDouble2) * d1) / (paramDouble1 - paramDouble3);
    return (d1 >= Math.min(paramDouble1, paramDouble3)) && (d1 <= Math.max(paramDouble1, paramDouble3)) && (d2 >= Math.min(paramDouble2, paramDouble4)) && (d2 <= Math.max(paramDouble2, paramDouble4)) && (d1 >= Math.min(paramDouble5, paramDouble7)) && (d1 <= Math.max(paramDouble5, paramDouble7)) && (d2 >= Math.min(paramDouble6, paramDouble8)) && (d2 <= Math.max(paramDouble6, paramDouble8));
  }

  public static boolean eLineAtLine(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8)
  {
    if ((paramDouble4 - paramDouble2) / (paramDouble3 - paramDouble1) == (paramDouble8 - paramDouble6) / (paramDouble7 - paramDouble5))
      return false;
    paramDouble5 = ((paramDouble1 * paramDouble4 - paramDouble2 * paramDouble3) * (paramDouble5 - paramDouble7) - (paramDouble5 * paramDouble8 - paramDouble6 * paramDouble7) * (paramDouble1 - paramDouble3)) / ((paramDouble4 - paramDouble2) * (paramDouble5 - paramDouble7) - (paramDouble8 - paramDouble6) * (paramDouble1 - paramDouble3));
    paramDouble6 = (paramDouble1 * paramDouble4 - paramDouble2 * paramDouble3 - (paramDouble4 - paramDouble2) * paramDouble5) / (paramDouble1 - paramDouble3);
    return (paramDouble5 >= Math.min(paramDouble1, paramDouble3)) && (paramDouble5 <= Math.max(paramDouble1, paramDouble3)) && (paramDouble6 >= Math.min(paramDouble2, paramDouble4)) && (paramDouble6 <= Math.max(paramDouble2, paramDouble4));
  }

  public static double getDistance(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    paramDouble1 -= paramDouble3;
    paramDouble2 -= paramDouble4;
    return Math.sqrt(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
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

  public static boolean isRectCollision(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8)
  {
    if ((paramFloat5 > paramFloat1) && (paramFloat5 > paramFloat1 + paramFloat3));
    while (((paramFloat5 < paramFloat1) && (paramFloat5 < paramFloat1 - paramFloat7)) || ((paramFloat6 > paramFloat2) && (paramFloat6 > paramFloat2 + paramFloat4)) || ((paramFloat6 < paramFloat2) && (paramFloat6 < paramFloat2 - paramFloat8)))
      return false;
    return true;
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

  public static boolean pointAtELine(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    if ((paramDouble1 - paramDouble3) * (paramDouble6 - paramDouble4) - (paramDouble2 - paramDouble4) * (paramDouble5 - paramDouble3) == 0.0D)
      return (paramDouble1 >= Math.min(paramDouble3, paramDouble5)) && (paramDouble1 <= Math.max(paramDouble3, paramDouble5)) && (paramDouble2 >= Math.min(paramDouble4, paramDouble6)) && (paramDouble2 <= Math.max(paramDouble4, paramDouble6));
    return false;
  }

  public static boolean pointAtRect(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    return (paramDouble1 >= Math.min(paramDouble3, paramDouble5)) && (paramDouble1 <= Math.max(paramDouble3, paramDouble5)) && (paramDouble2 >= Math.min(paramDouble4, paramDouble6)) && (paramDouble2 <= Math.max(paramDouble4, paramDouble6));
  }

  public static boolean rectAtRect(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8)
  {
    return (paramDouble1 >= Math.min(paramDouble5, paramDouble7)) && (paramDouble1 <= Math.max(paramDouble5, paramDouble7)) && (paramDouble2 >= Math.min(paramDouble6, paramDouble8)) && (paramDouble2 <= Math.max(paramDouble6, paramDouble8)) && (paramDouble3 >= Math.min(paramDouble5, paramDouble7)) && (paramDouble3 <= Math.max(paramDouble5, paramDouble7)) && (paramDouble4 >= Math.min(paramDouble6, paramDouble8)) && (paramDouble4 <= Math.max(paramDouble6, paramDouble8));
  }

  public static BigDecimal round(double paramDouble, int paramInt)
  {
    return new BigDecimal(paramDouble).setScale(paramInt, 4);
  }

  public boolean pointAtSLine(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    return (paramDouble1 - paramDouble3) * (paramDouble6 - paramDouble4) - (paramDouble2 - paramDouble4) * (paramDouble5 - paramDouble3) == 0.0D;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbMathUtil
 * JD-Core Version:    0.6.2
 */