package com.umeng.message.local;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UmengCalendar
{
  private static final int[] a = { 19168, 42352, 21096, 53856, 55632, 27304, 22176, 39632, 19176, 19168, 42200, 42192, 53840, 54600, 46416, 22176, 38608, 38320, 18872, 18864, 42160, 45656, 27216, 27968, 44456, 11104, 38256, 18808, 18800, 25776, 54432, 59984, 27976, 23248, 11104, 37744, 37600, 51560, 51536, 54432, 55888, 46416, 22176, 43736, 9680, 37584, 51544, 43344, 46248, 27808, 46416, 21928, 19872, 42416, 21176, 21168, 43344, 59728, 27296, 44368, 43856, 19296, 42352, 42352, 21088, 59696, 55632, 23208, 22176, 38608, 19176, 19152, 42192, 53864, 53840, 54568, 46400, 46752, 38608, 38320, 18864, 42168, 42160, 45656, 27216, 27968, 44448, 43872, 37744, 18808, 18800, 25776, 27216, 59984, 27432, 23232, 43872, 37736, 37600, 51552, 54440, 54432, 55888, 23208, 22176, 43736, 9680, 37584, 51544, 43344, 46240, 46416, 46416, 21928, 19360, 42416, 21176, 21168, 43312, 29864, 27296, 44368, 19880, 19296, 38256, 42208, 53856, 59696, 54576, 23200, 27472, 38608, 19176, 19152, 42192, 53848, 53840, 54560, 55968, 46496, 22224, 19160, 18864, 42168, 42160, 43600, 46376, 27936, 44448, 21936 };
  private static final char[] b = { 0, 80, 4, 0, 32, 96, 5, 0, 32, 112, 5, 0, 64, 2, 6, 0, 80, 3, 7, 0, 96, 4, 0, 32, 112, 5, 0, 48, 128, 6, 0, 64, 3, 7, 0, 80, 4, 8, 0, 96, 4, 10, 0, 96, 5, 0, 48, 128, 5, 0, 64, 2, 7, 0, 80, 4, 9, 0, 96, 4, 0, 32, 96, 5, 0, 48, 176, 6, 0, 80, 2, 7, 0, 80, 3 };
  private static final int[] c = { 29, 30 };
  private static final int[][] d = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }, { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
  private static final int[][] e = { { 23, 3, 2, 17, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0 }, arrayOfInt1, arrayOfInt2, { 49, 0, 6, 33, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1 }, arrayOfInt3, arrayOfInt4, { 45, 0, 3, 49, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 }, { 35, 0, 4, 54, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1 }, { 24, 4, 5, 59, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1 }, { 43, 0, 0, 5, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 }, { 32, 0, 1, 10, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1 }, arrayOfInt5, arrayOfInt6, arrayOfInt7, { 47, 0, 6, 31, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1 }, { 36, 0, 0, 36, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 }, arrayOfInt8, arrayOfInt9, { 33, 0, 4, 52, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0 }, arrayOfInt10, { 42, 0, 6, 2, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1 }, arrayOfInt11, arrayOfInt12, arrayOfInt13, { 27, 6, 4, 23, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0 }, { 45, 0, 6, 29, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0 }, { 35, 0, 0, 34, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1 }, arrayOfInt14, { 43, 0, 2, 44, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0 }, arrayOfInt15, { 20, 3, 5, 55, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0 }, { 39, 0, 6, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0 }, arrayOfInt16, { 47, 0, 2, 11, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1 }, arrayOfInt17, arrayOfInt18, arrayOfInt19, { 33, 0, 0, 32, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1 }, { 22, 4, 1, 37, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1 }, { 41, 0, 2, 42, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1 }, { 30, 8, 3, 47, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1 }, arrayOfInt20, arrayOfInt21, arrayOfInt22, arrayOfInt23, arrayOfInt24, { 24, 4, 4, 19, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1 }, arrayOfInt25, arrayOfInt26, { 50, 0, 1, 35, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0 }, { 39, 0, 2, 40, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1 }, { 28, 6, 3, 45, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0 }, { 47, 0, 4, 50, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1 }, { 36, 0, 6, 56, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0 }, arrayOfInt27, { 45, 0, 1, 6, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0 }, { 34, 0, 2, 11, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0 }, { 22, 3, 4, 17, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0 }, arrayOfInt28, { 30, 8, 6, 27, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1 }, { 49, 0, 0, 32, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1 }, arrayOfInt29, { 27, 5, 3, 43, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 }, { 46, 0, 4, 48, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1 }, arrayOfInt30, { 23, 4, 0, 59, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1 }, { 42, 0, 1, 4, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1 }, arrayOfInt31, { 21, 2, 3, 14, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 39, 0, 5, 20, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1 }, arrayOfInt32, arrayOfInt33, arrayOfInt34, arrayOfInt35, { 44, 0, 4, 46, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1 }, { 33, 0, 5, 51, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1 }, arrayOfInt36, arrayOfInt37, arrayOfInt38, arrayOfInt39, { 38, 0, 4, 17, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0 }, { 27, 6, 6, 23, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1 }, { 46, 0, 0, 28, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0 }, { 35, 0, 1, 33, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0 }, { 24, 4, 2, 38, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1 }, arrayOfInt40, { 31, 0, 5, 49, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0 }, arrayOfInt41, { 40, 0, 0, 59, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1 }, { 28, 6, 2, 5, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0 }, { 47, 0, 3, 10, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1 }, { 36, 0, 4, 15, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1 }, { 25, 5, 5, 20, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0 }, { 43, 0, 0, 26, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1 }, arrayOfInt42, arrayOfInt43 };

  static
  {
    int[] arrayOfInt1 = { 41, 0, 4, 23, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1 };
    int[] arrayOfInt2 = { 30, 7, 5, 28, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1 };
    int[] arrayOfInt3 = { 38, 0, 0, 38, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1 };
    int[] arrayOfInt4 = { 26, 6, 2, 44, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0 };
    int[] arrayOfInt5 = { 21, 2, 2, 15, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1 };
    int[] arrayOfInt6 = { 40, 0, 3, 20, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1 };
    int[] arrayOfInt7 = { 28, 7, 5, 26, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1 };
    int[] arrayOfInt8 = { 26, 5, 1, 41, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1 };
    int[] arrayOfInt9 = { 44, 0, 3, 47, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1 };
    int[] arrayOfInt10 = { 23, 3, 5, 57, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1 };
    int[] arrayOfInt11 = { 30, 8, 1, 8, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0 };
    int[] arrayOfInt12 = { 48, 0, 2, 13, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0 };
    int[] arrayOfInt13 = { 38, 0, 3, 18, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 };
    int[] arrayOfInt14 = { 24, 4, 1, 39, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0 };
    int[] arrayOfInt15 = { 32, 0, 4, 50, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1 };
    int[] arrayOfInt16 = { 29, 7, 0, 5, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1 };
    int[] arrayOfInt17 = { 36, 0, 3, 16, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0 };
    int[] arrayOfInt18 = { 26, 5, 4, 21, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1 };
    int[] arrayOfInt19 = { 45, 0, 5, 26, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1 };
    int[] arrayOfInt20 = { 48, 0, 5, 53, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1 };
    int[] arrayOfInt21 = { 37, 0, 6, 58, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1 };
    int[] arrayOfInt22 = { 27, 6, 0, 3, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0 };
    int[] arrayOfInt23 = { 46, 0, 1, 8, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0 };
    int[] arrayOfInt24 = { 35, 0, 3, 14, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1 };
    int[] arrayOfInt25 = { 43, 0, 5, 24, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1 };
    int[] arrayOfInt26 = { 32, 10, 6, 29, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1 };
    int[] arrayOfInt27 = { 26, 5, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1 };
    int[] arrayOfInt28 = { 40, 0, 5, 22, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0 };
    int[] arrayOfInt29 = { 37, 0, 2, 38, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1 };
    int[] arrayOfInt30 = { 35, 0, 5, 53, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1 };
    int[] arrayOfInt31 = { 31, 0, 2, 9, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0 };
    int[] arrayOfInt32 = { 28, 7, 6, 25, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1 };
    int[] arrayOfInt33 = { 48, 0, 0, 30, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 };
    int[] arrayOfInt34 = { 37, 0, 1, 35, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1 };
    int[] arrayOfInt35 = { 25, 5, 3, 41, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1 };
    int[] arrayOfInt36 = { 22, 4, 6, 56, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 };
    int[] arrayOfInt37 = { 40, 0, 1, 2, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 };
    int[] arrayOfInt38 = { 30, 9, 2, 7, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1 };
    int[] arrayOfInt39 = { 49, 0, 3, 12, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1 };
    int[] arrayOfInt40 = { 42, 0, 4, 44, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 };
    int[] arrayOfInt41 = { 21, 2, 6, 54, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1 };
    int[] arrayOfInt42 = { 32, 0, 1, 31, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0 };
    int[] arrayOfInt43 = { 22, 3, 2, 36, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0 };
  }

  static int a(int paramInt)
  {
    int i = b[((paramInt - 1901) / 2)];
    if (paramInt % 2 == 0)
      return i & 0xF;
    return (i & 0xF0) >> 4;
  }

  private static String a(Date paramDate, int paramInt)
  {
    SimpleDateFormat localSimpleDateFormat = null;
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return localSimpleDateFormat.format(paramDate);
      new SimpleDateFormat("yyyy-MM-dd");
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    }
  }

  private static Date a(String paramString)
  {
    int i1 = 1;
    int i2 = 0;
    paramString = c(paramString);
    int m = paramString.get(1);
    int j = paramString.get(2) + 1;
    int n = paramString.get(5);
    int i3 = m - 1936;
    int i = j;
    if (e[i3][1] != 0)
    {
      i = j;
      if (j > e[i3][1])
        i = j + 1;
    }
    j = 0;
    int k = 0;
    while (j < i - 1)
    {
      k += c[e[i3][(j + 4)]];
      j += 1;
    }
    n = k + n + e[i3][0];
    j = 365;
    if (b(m))
      j = 366;
    i = n;
    k = m;
    if (n > j)
    {
      k = m + 1;
      i = n - j;
    }
    paramString = d[0];
    j = i;
    m = i2;
    n = i1;
    if (b(k))
    {
      paramString = d[1];
      n = i1;
      m = i2;
      j = i;
    }
    while (true)
    {
      i = j;
      if (m < 12)
      {
        j -= paramString[m];
        if (j <= 0)
          i = j;
      }
      else
      {
        j = paramString[m];
        return d(k + "-" + n + "-" + (i + j));
      }
      n += 1;
      m += 1;
    }
  }

  private static Date b(String paramString)
  {
    int j = 0;
    paramString = c(paramString);
    int m = paramString.get(1);
    if ((m < 1936) || (m > 2028))
      return null;
    int n = m - 1936;
    int k = e[n][0];
    int i = paramString.get(6);
    int i2;
    label70: int i1;
    if (i >= k)
    {
      k = i - k;
      i2 = 0;
      i = 1;
      i1 = j;
      if (i2 < 13)
      {
        j += c[e[n][(i2 + 4)]];
        if (j < k)
          break label246;
        i1 = j;
      }
      j = c[e[n][(i2 + 4)]];
      if ((e[n][1] == 0) || (i <= e[n][1]))
        break label259;
      i -= 1;
    }
    label259: 
    while (true)
    {
      return d(m + "-" + i + "-" + (j - (i1 - k)));
      n -= 1;
      m -= 1;
      k = i + c(m + "-12-31").get(6) - e[n][0];
      break;
      label246: i2 += 1;
      i += 1;
      break label70;
    }
  }

  private static boolean b(int paramInt)
  {
    if (paramInt % 400 == 0);
    do
    {
      return true;
      if (paramInt % 100 == 0)
        return false;
    }
    while (paramInt % 4 == 0);
    return false;
  }

  private static Calendar c(String paramString)
  {
    paramString = d(paramString);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramString);
    return localCalendar;
  }

  private static Date d(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static int iGetLMonthDays(int paramInt1, int paramInt2)
  {
    int j = 29;
    int k = a(paramInt1);
    if (((paramInt2 > 12) && (paramInt2 - 12 != k)) || (paramInt2 < 0))
    {
      System.out.println(" Wrong month, ^_^ , i think you are want a -1, go to death! ");
      paramInt2 = -1;
    }
    int i;
    do
    {
      do
      {
        return paramInt2;
        if (paramInt2 - 12 != k)
          break;
        paramInt2 = j;
      }
      while ((32768 >> k & a[(paramInt1 - 1901)]) == 0);
      return 30;
      i = paramInt2;
      if (k > 0)
      {
        i = paramInt2;
        if (paramInt2 > k)
          i = paramInt2 + 1;
      }
      paramInt2 = j;
    }
    while ((a[(paramInt1 - 1901)] & 32768 >> i - 1) == 0);
    return 30;
  }

  public static String lunarTosolar(String paramString)
  {
    paramString = paramString.split(" ");
    String str = a(a(paramString[0].trim()), 1);
    return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + paramString[1].trim();
  }

  public static String solarTolunar(String paramString)
  {
    paramString = paramString.split(" ");
    String str = a(b(paramString[0].trim()), 1);
    int i = iGetLMonthDays(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(4, 6)));
    if (i < Integer.parseInt(str.substring(6, 8)))
      return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + String.valueOf(i) + " " + paramString[1].trim();
    return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + paramString[1].trim();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.local.UmengCalendar
 * JD-Core Version:    0.6.2
 */