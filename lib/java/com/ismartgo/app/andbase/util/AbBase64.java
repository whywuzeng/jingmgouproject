package com.ismartgo.app.andbase.util;

import java.io.UnsupportedEncodingException;

public final class AbBase64
{
  private static final byte[] base64DecodeChars = arrayOfByte;
  private static final char[] base64EncodeChars = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };

  static
  {
    byte[] arrayOfByte = new byte[''];
    arrayOfByte[0] = -1;
    arrayOfByte[1] = -1;
    arrayOfByte[2] = -1;
    arrayOfByte[3] = -1;
    arrayOfByte[4] = -1;
    arrayOfByte[5] = -1;
    arrayOfByte[6] = -1;
    arrayOfByte[7] = -1;
    arrayOfByte[8] = -1;
    arrayOfByte[9] = -1;
    arrayOfByte[10] = -1;
    arrayOfByte[11] = -1;
    arrayOfByte[12] = -1;
    arrayOfByte[13] = -1;
    arrayOfByte[14] = -1;
    arrayOfByte[15] = -1;
    arrayOfByte[16] = -1;
    arrayOfByte[17] = -1;
    arrayOfByte[18] = -1;
    arrayOfByte[19] = -1;
    arrayOfByte[20] = -1;
    arrayOfByte[21] = -1;
    arrayOfByte[22] = -1;
    arrayOfByte[23] = -1;
    arrayOfByte[24] = -1;
    arrayOfByte[25] = -1;
    arrayOfByte[26] = -1;
    arrayOfByte[27] = -1;
    arrayOfByte[28] = -1;
    arrayOfByte[29] = -1;
    arrayOfByte[30] = -1;
    arrayOfByte[31] = -1;
    arrayOfByte[32] = -1;
    arrayOfByte[33] = -1;
    arrayOfByte[34] = -1;
    arrayOfByte[35] = -1;
    arrayOfByte[36] = -1;
    arrayOfByte[37] = -1;
    arrayOfByte[38] = -1;
    arrayOfByte[39] = -1;
    arrayOfByte[40] = -1;
    arrayOfByte[41] = -1;
    arrayOfByte[42] = -1;
    arrayOfByte[43] = 62;
    arrayOfByte[44] = -1;
    arrayOfByte[45] = -1;
    arrayOfByte[46] = -1;
    arrayOfByte[47] = 63;
    arrayOfByte[48] = 52;
    arrayOfByte[49] = 53;
    arrayOfByte[50] = 54;
    arrayOfByte[51] = 55;
    arrayOfByte[52] = 56;
    arrayOfByte[53] = 57;
    arrayOfByte[54] = 58;
    arrayOfByte[55] = 59;
    arrayOfByte[56] = 60;
    arrayOfByte[57] = 61;
    arrayOfByte[58] = -1;
    arrayOfByte[59] = -1;
    arrayOfByte[60] = -1;
    arrayOfByte[61] = -1;
    arrayOfByte[62] = -1;
    arrayOfByte[63] = -1;
    arrayOfByte[64] = -1;
    arrayOfByte[66] = 1;
    arrayOfByte[67] = 2;
    arrayOfByte[68] = 3;
    arrayOfByte[69] = 4;
    arrayOfByte[70] = 5;
    arrayOfByte[71] = 6;
    arrayOfByte[72] = 7;
    arrayOfByte[73] = 8;
    arrayOfByte[74] = 9;
    arrayOfByte[75] = 10;
    arrayOfByte[76] = 11;
    arrayOfByte[77] = 12;
    arrayOfByte[78] = 13;
    arrayOfByte[79] = 14;
    arrayOfByte[80] = 15;
    arrayOfByte[81] = 16;
    arrayOfByte[82] = 17;
    arrayOfByte[83] = 18;
    arrayOfByte[84] = 19;
    arrayOfByte[85] = 20;
    arrayOfByte[86] = 21;
    arrayOfByte[87] = 22;
    arrayOfByte[88] = 23;
    arrayOfByte[89] = 24;
    arrayOfByte[90] = 25;
    arrayOfByte[91] = -1;
    arrayOfByte[92] = -1;
    arrayOfByte[93] = -1;
    arrayOfByte[94] = -1;
    arrayOfByte[95] = -1;
    arrayOfByte[96] = -1;
    arrayOfByte[97] = 26;
    arrayOfByte[98] = 27;
    arrayOfByte[99] = 28;
    arrayOfByte[100] = 29;
    arrayOfByte[101] = 30;
    arrayOfByte[102] = 31;
    arrayOfByte[103] = 32;
    arrayOfByte[104] = 33;
    arrayOfByte[105] = 34;
    arrayOfByte[106] = 35;
    arrayOfByte[107] = 36;
    arrayOfByte[108] = 37;
    arrayOfByte[109] = 38;
    arrayOfByte[110] = 39;
    arrayOfByte[111] = 40;
    arrayOfByte[112] = 41;
    arrayOfByte[113] = 42;
    arrayOfByte[114] = 43;
    arrayOfByte[115] = 44;
    arrayOfByte[116] = 45;
    arrayOfByte[117] = 46;
    arrayOfByte[118] = 47;
    arrayOfByte[119] = 48;
    arrayOfByte[120] = 49;
    arrayOfByte[121] = 50;
    arrayOfByte[122] = 51;
    arrayOfByte[123] = -1;
    arrayOfByte[124] = -1;
    arrayOfByte[125] = -1;
    arrayOfByte[126] = -1;
    arrayOfByte[127] = -1;
  }

  // ERROR //
  public static final String decode(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 92	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   5: astore_0
    //   6: aload_0
    //   7: arraylength
    //   8: istore 7
    //   10: new 94	java/io/ByteArrayOutputStream
    //   13: dup
    //   14: iload 7
    //   16: i2d
    //   17: ldc2_w 95
    //   20: dmul
    //   21: d2i
    //   22: invokespecial 99	java/io/ByteArrayOutputStream:<init>	(I)V
    //   25: astore 8
    //   27: iconst_0
    //   28: istore 4
    //   30: iload 4
    //   32: istore_2
    //   33: iload 4
    //   35: iload 7
    //   37: if_icmplt +19 -> 56
    //   40: aload 8
    //   42: aload_1
    //   43: invokevirtual 103	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
    //   46: astore_0
    //   47: aload_0
    //   48: areturn
    //   49: astore_0
    //   50: aload_0
    //   51: invokevirtual 106	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   54: aconst_null
    //   55: areturn
    //   56: iload_2
    //   57: iload 7
    //   59: if_icmplt +115 -> 174
    //   62: iconst_m1
    //   63: istore_3
    //   64: iload_3
    //   65: iconst_m1
    //   66: if_icmpeq -26 -> 40
    //   69: iload_2
    //   70: iload 7
    //   72: if_icmplt +151 -> 223
    //   75: iconst_m1
    //   76: istore 4
    //   78: iload 4
    //   80: iconst_m1
    //   81: if_icmpeq -41 -> 40
    //   84: aload 8
    //   86: iload_3
    //   87: iconst_2
    //   88: ishl
    //   89: iload 4
    //   91: bipush 48
    //   93: iand
    //   94: iconst_4
    //   95: iushr
    //   96: ior
    //   97: invokevirtual 109	java/io/ByteArrayOutputStream:write	(I)V
    //   100: iload_2
    //   101: iload 7
    //   103: if_icmplt +171 -> 274
    //   106: iconst_m1
    //   107: istore 5
    //   109: iload_2
    //   110: istore_3
    //   111: iload 5
    //   113: istore_2
    //   114: iload_2
    //   115: iconst_m1
    //   116: if_icmpeq -76 -> 40
    //   119: aload 8
    //   121: iload 4
    //   123: bipush 15
    //   125: iand
    //   126: iconst_4
    //   127: ishl
    //   128: iload_2
    //   129: bipush 60
    //   131: iand
    //   132: iconst_2
    //   133: iushr
    //   134: ior
    //   135: invokevirtual 109	java/io/ByteArrayOutputStream:write	(I)V
    //   138: iload_3
    //   139: iload 7
    //   141: if_icmplt +194 -> 335
    //   144: iconst_m1
    //   145: istore 5
    //   147: iload_3
    //   148: istore 4
    //   150: iload 5
    //   152: istore_3
    //   153: iload_3
    //   154: iconst_m1
    //   155: if_icmpeq -115 -> 40
    //   158: aload 8
    //   160: iload_2
    //   161: iconst_3
    //   162: iand
    //   163: bipush 6
    //   165: ishl
    //   166: iload_3
    //   167: ior
    //   168: invokevirtual 109	java/io/ByteArrayOutputStream:write	(I)V
    //   171: goto -141 -> 30
    //   174: getstatic 78	com/ismartgo/app/andbase/util/AbBase64:base64DecodeChars	[B
    //   177: astore 9
    //   179: iload_2
    //   180: iconst_1
    //   181: iadd
    //   182: istore 4
    //   184: aload 9
    //   186: aload_0
    //   187: iload_2
    //   188: baload
    //   189: baload
    //   190: istore 5
    //   192: iload 5
    //   194: istore_3
    //   195: iload 4
    //   197: istore_2
    //   198: iload 4
    //   200: iload 7
    //   202: if_icmpge -138 -> 64
    //   205: iload 4
    //   207: istore_2
    //   208: iload 5
    //   210: iconst_m1
    //   211: if_icmpeq -155 -> 56
    //   214: iload 5
    //   216: istore_3
    //   217: iload 4
    //   219: istore_2
    //   220: goto -156 -> 64
    //   223: getstatic 78	com/ismartgo/app/andbase/util/AbBase64:base64DecodeChars	[B
    //   226: astore 9
    //   228: iload_2
    //   229: iconst_1
    //   230: iadd
    //   231: istore 5
    //   233: aload 9
    //   235: aload_0
    //   236: iload_2
    //   237: baload
    //   238: baload
    //   239: istore 6
    //   241: iload 6
    //   243: istore 4
    //   245: iload 5
    //   247: istore_2
    //   248: iload 5
    //   250: iload 7
    //   252: if_icmpge -174 -> 78
    //   255: iload 5
    //   257: istore_2
    //   258: iload 6
    //   260: iconst_m1
    //   261: if_icmpeq -192 -> 69
    //   264: iload 6
    //   266: istore 4
    //   268: iload 5
    //   270: istore_2
    //   271: goto -193 -> 78
    //   274: iload_2
    //   275: iconst_1
    //   276: iadd
    //   277: istore 5
    //   279: aload_0
    //   280: iload_2
    //   281: baload
    //   282: istore_2
    //   283: iload_2
    //   284: bipush 61
    //   286: if_icmpne +11 -> 297
    //   289: iconst_m1
    //   290: istore_2
    //   291: iload 5
    //   293: istore_3
    //   294: goto -180 -> 114
    //   297: getstatic 78	com/ismartgo/app/andbase/util/AbBase64:base64DecodeChars	[B
    //   300: iload_2
    //   301: baload
    //   302: istore 6
    //   304: iload 6
    //   306: istore_2
    //   307: iload 5
    //   309: istore_3
    //   310: iload 5
    //   312: iload 7
    //   314: if_icmpge -200 -> 114
    //   317: iload 5
    //   319: istore_2
    //   320: iload 6
    //   322: iconst_m1
    //   323: if_icmpeq -223 -> 100
    //   326: iload 6
    //   328: istore_2
    //   329: iload 5
    //   331: istore_3
    //   332: goto -218 -> 114
    //   335: iload_3
    //   336: iconst_1
    //   337: iadd
    //   338: istore 4
    //   340: aload_0
    //   341: iload_3
    //   342: baload
    //   343: istore_3
    //   344: iload_3
    //   345: bipush 61
    //   347: if_icmpne +8 -> 355
    //   350: iconst_m1
    //   351: istore_3
    //   352: goto -199 -> 153
    //   355: getstatic 78	com/ismartgo/app/andbase/util/AbBase64:base64DecodeChars	[B
    //   358: iload_3
    //   359: baload
    //   360: istore 5
    //   362: iload 4
    //   364: istore_3
    //   365: iload 5
    //   367: iconst_m1
    //   368: if_icmpeq -230 -> 138
    //   371: iload 5
    //   373: istore_3
    //   374: goto -221 -> 153
    //   377: astore_0
    //   378: aload_0
    //   379: invokevirtual 106	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   382: aconst_null
    //   383: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	6	49	java/io/UnsupportedEncodingException
    //   40	47	377	java/io/UnsupportedEncodingException
  }

  public static final String encode(String paramString1, String paramString2)
  {
    return encode(paramString1, paramString2, 0);
  }

  public static final String encode(String paramString1, String paramString2, int paramInt)
  {
    while (true)
    {
      int m;
      try
      {
        paramString1 = paramString1.getBytes(paramString2);
        k = paramString1.length;
        i = (int)Math.ceil(k * 1.36D);
        if (paramInt > 0)
        {
          j = i / paramInt;
          paramString2 = new StringBuffer(i + j);
          m = k % 3;
          i = 0;
          if (i < k - m)
            break label140;
          if (m != 1)
            break label255;
          i = paramString1[i] & 0xFF;
          paramString2.append(base64EncodeChars[(i >> 2)]);
          paramString2.append(base64EncodeChars[((i & 0x3) << 4)]);
          paramString2.append("==");
          if (j > 0)
          {
            i = paramInt;
            if (i < paramString2.length())
              break label350;
          }
          return paramString2.toString();
        }
      }
      catch (UnsupportedEncodingException paramString1)
      {
        paramString1.printStackTrace();
        return null;
      }
      int j = 0;
      continue;
      label140: int n = i + 1;
      int i1 = paramString1[i];
      int i = n + 1;
      n = (i1 & 0xFF) << 16 | (paramString1[n] & 0xFF) << 8 | paramString1[i] & 0xFF;
      paramString2.append(base64EncodeChars[(n >> 18)]);
      paramString2.append(base64EncodeChars[(n >> 12 & 0x3F)]);
      paramString2.append(base64EncodeChars[(n >> 6 & 0x3F)]);
      paramString2.append(base64EncodeChars[(n & 0x3F)]);
      i += 1;
      continue;
      label255: int k = i;
      if (m == 2)
      {
        m = i + 1;
        i = paramString1[i];
        k = m + 1;
        i = (i & 0xFF) << 8 | paramString1[m] & 0xFF;
        paramString2.append(base64EncodeChars[(i >> 10)]);
        paramString2.append(base64EncodeChars[(i >> 4 & 0x3F)]);
        paramString2.append(base64EncodeChars[((i & 0xF) << 2)]);
        paramString2.append("=");
      }
      continue;
      label350: paramString2.insert(i, '\n');
      i = i + paramInt + 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbBase64
 * JD-Core Version:    0.6.2
 */