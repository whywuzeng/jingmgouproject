package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.util.Hashtable;

public final class StringUtils
{
  private static final boolean ASSUME_SHIFT_JIS;
  private static final String EUC_JP = "EUC_JP";
  private static final String ISO88591 = "ISO8859_1";
  private static final String PLATFORM_DEFAULT_ENCODING = System.getProperty("file.encoding");
  public static final String SHIFT_JIS = "SJIS";
  private static final String UTF8 = "UTF8";

  static
  {
    if (("SJIS".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING)) || ("EUC_JP".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING)));
    for (boolean bool = true; ; bool = false)
    {
      ASSUME_SHIFT_JIS = bool;
      return;
    }
  }

  public static String guessEncoding(byte[] paramArrayOfByte, Hashtable paramHashtable)
  {
    if (paramHashtable != null)
    {
      paramHashtable = (String)paramHashtable.get(DecodeHintType.CHARACTER_SET);
      if (paramHashtable != null)
        return paramHashtable;
    }
    if ((paramArrayOfByte.length > 3) && (paramArrayOfByte[0] == -17) && (paramArrayOfByte[1] == -69) && (paramArrayOfByte[2] == -65))
      return "UTF8";
    int i10 = paramArrayOfByte.length;
    int i1 = 0;
    int i6 = 0;
    int i5 = 0;
    int i8 = 0;
    int i2 = 0;
    int i4 = 0;
    int i = 0;
    int j = 1;
    int k = 1;
    int i7 = 1;
    int i9;
    int n;
    int m;
    int i3;
    if ((i2 < i10) && ((i7 != 0) || (k != 0) || (j != 0)))
    {
      i9 = paramArrayOfByte[i2] & 0xFF;
      if ((i9 >= 128) && (i9 <= 191))
      {
        if (i1 <= 0)
          break label682;
        n = i1 - 1;
        m = j;
        i3 = i8;
      }
    }
    while (true)
    {
      if (i9 != 194)
      {
        i1 = i5;
        if (i9 != 195);
      }
      else
      {
        i1 = i5;
        if (i2 < i10 - 1)
        {
          j = paramArrayOfByte[(i2 + 1)] & 0xFF;
          i1 = i5;
          if (j <= 191)
            if ((i9 != 194) || (j < 160))
            {
              i1 = i5;
              if (i9 == 195)
              {
                i1 = i5;
                if (j < 128);
              }
            }
            else
            {
              i1 = 1;
            }
        }
      }
      i5 = i7;
      if (i9 >= 127)
      {
        i5 = i7;
        if (i9 <= 159)
          i5 = 0;
      }
      i7 = i6;
      if (i9 >= 161)
      {
        i7 = i6;
        if (i9 <= 223)
        {
          i7 = i6;
          if (i4 == 0)
            i7 = i6 + 1;
        }
      }
      if ((i4 == 0) && (((i9 >= 240) && (i9 <= 255)) || (i9 == 128) || (i9 == 160)))
      {
        j = 0;
        if (((i9 >= 129) && (i9 <= 159)) || ((i9 >= 224) && (i9 <= 239)))
          if (i4 != 0)
          {
            i4 = 0;
            k = j;
            j = i;
            i = i4;
            label399: i2 += 1;
            i4 = i;
            i = j;
            j = m;
            i6 = i7;
            i7 = i5;
            i5 = i1;
            i8 = i3;
            i1 = n;
            break;
            if (i1 <= 0)
              break label679;
            j = 0;
          }
      }
      label679: 
      while (true)
      {
        i3 = i8;
        n = i1;
        m = j;
        if (i9 < 192)
          break;
        i3 = i8;
        n = i1;
        m = j;
        if (i9 > 253)
          break;
        m = i1;
        n = i9;
        while (true)
          if ((n & 0x40) != 0)
          {
            n <<= 1;
            m += 1;
            continue;
            if (i2 >= paramArrayOfByte.length - 1)
            {
              k = 0;
              j = i;
              i = 1;
              break label399;
            }
            k = paramArrayOfByte[(i2 + 1)] & 0xFF;
            if ((k < 64) || (k > 252))
              j = 0;
            while (true)
            {
              k = j;
              j = i;
              i = 1;
              break;
              i += 1;
            }
            k = j;
            j = i;
            i = 0;
            break label399;
            if (i1 > 0)
              j = 0;
            if ((k != 0) && (ASSUME_SHIFT_JIS))
              return "SJIS";
            if ((j != 0) && (i8 != 0))
              return "UTF8";
            if ((k != 0) && ((i >= 3) || (i6 * 20 > i10)))
              return "SJIS";
            if ((i5 == 0) && (i7 != 0))
              return "ISO8859_1";
            return PLATFORM_DEFAULT_ENCODING;
            j = k;
            break;
          }
        i3 = 1;
        n = m;
        m = j;
        break;
      }
      label682: i3 = i8;
      n = i1;
      m = j;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.StringUtils
 * JD-Core Version:    0.6.2
 */