package com.ab.util;

import java.io.PrintStream;
import java.security.MessageDigest;

public class AbMd5
{
  public static final String MD5(String paramString)
  {
    char[] arrayOfChar = new char[16];
    char[] tmp8_6 = arrayOfChar;
    tmp8_6[0] = 48;
    char[] tmp13_8 = tmp8_6;
    tmp13_8[1] = 49;
    char[] tmp18_13 = tmp13_8;
    tmp18_13[2] = 50;
    char[] tmp23_18 = tmp18_13;
    tmp23_18[3] = 51;
    char[] tmp28_23 = tmp23_18;
    tmp28_23[4] = 52;
    char[] tmp33_28 = tmp28_23;
    tmp33_28[5] = 53;
    char[] tmp38_33 = tmp33_28;
    tmp38_33[6] = 54;
    char[] tmp44_38 = tmp38_33;
    tmp44_38[7] = 55;
    char[] tmp50_44 = tmp44_38;
    tmp50_44[8] = 56;
    char[] tmp56_50 = tmp50_44;
    tmp56_50[9] = 57;
    char[] tmp62_56 = tmp56_50;
    tmp62_56[10] = 97;
    char[] tmp68_62 = tmp62_56;
    tmp68_62[11] = 98;
    char[] tmp74_68 = tmp68_62;
    tmp74_68[12] = 99;
    char[] tmp80_74 = tmp74_68;
    tmp80_74[13] = 100;
    char[] tmp86_80 = tmp80_74;
    tmp86_80[14] = 101;
    char[] tmp92_86 = tmp86_80;
    tmp92_86[15] = 102;
    tmp92_86;
    try
    {
      paramString = paramString.getBytes();
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString);
      paramString = ((MessageDigest)localObject).digest();
      localObject = new char[32];
      int i = 0;
      int j = 0;
      while (true)
      {
        if (i >= 16)
          return new String((char[])localObject).toUpperCase();
        int k = paramString[i];
        int m = j + 1;
        localObject[j] = arrayOfChar[(k >>> 4 & 0xF)];
        j = m + 1;
        localObject[m] = arrayOfChar[(k & 0xF)];
        i += 1;
      }
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(MD5("2011123456").toLowerCase());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.AbMd5
 * JD-Core Version:    0.6.2
 */