package com.baidu.location.h;

public final class c
{
  public static final int a = 11;
  public static final int b = 5;
  public static final int jdField_byte = 10;
  public static final String c = "3G";
  public static final int jdField_case = 0;
  public static final int jdField_char = 8;
  public static final int d = 9;
  public static final String jdField_do = "WIFI";
  public static final int e = 1;
  public static final int jdField_else = 7;
  public static final int f = 3;
  public static final int jdField_for = 12;
  public static final String g = "unknown";
  public static final int jdField_goto = 2;
  public static final String h = "2G";
  public static final String jdField_if = "4G";
  public static final int jdField_int = 4;
  public static final int jdField_long = 14;
  public static final int jdField_new = 6;
  public static final int jdField_try = 13;
  public static final int jdField_void = 15;

  public static j a()
  {
    return b.cW();
  }

  public static String a(int paramInt)
  {
    if (l.a().db())
      return "WIFI";
    switch (paramInt)
    {
    default:
      return "unknown";
    case 1:
    case 2:
    case 4:
    case 7:
    case 11:
      return "2G";
    case 3:
    case 5:
    case 6:
    case 8:
    case 9:
    case 10:
    case 12:
    case 14:
    case 15:
      return "3G";
    case 13:
    }
    return "4G";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.h.c
 * JD-Core Version:    0.6.2
 */