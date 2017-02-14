package cn.jpush.android.service;

final class q
  implements Runnable
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "\036qG53 qZ\"r*q\t7r$xL53`9\t";
    int k = -1;
    int m = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int j = localObject2.length;
    int i;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (j <= 1)
    {
      i = 0;
      localObject3 = localObject1;
      n = m;
      localObject1 = localObject2;
      i1 = k;
      m = i;
      for (k = i; ; k = j)
      {
        localObject2 = localObject1;
        i2 = localObject2[k];
        switch (m % 5)
        {
        default:
          i = 19;
          localObject2[k] = ((char)(i ^ i2));
          m += 1;
          if (j != 0)
            break label121;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      k = j;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      j = k;
      i = m;
      if (k > m)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "\030P";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 77;
        break label99;
        i = 20;
        break label99;
        i = 41;
        break label99;
        i = 81;
        break label99;
        i = 0;
        i2 = k;
        i3 = m;
        localObject4 = localObject1;
        k = j;
        m = i;
      }
    }
  }

  q(String paramString, byte[] paramArrayOfByte)
  {
  }

  // ERROR //
  public final void run()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 49	java/net/DatagramSocket
    //   5: dup
    //   6: invokespecial 50	java/net/DatagramSocket:<init>	()V
    //   9: astore_3
    //   10: aload_3
    //   11: astore_2
    //   12: aload_3
    //   13: sipush 20000
    //   16: invokevirtual 54	java/net/DatagramSocket:setSoTimeout	(I)V
    //   19: aload_3
    //   20: astore_2
    //   21: new 56	java/io/ByteArrayOutputStream
    //   24: dup
    //   25: invokespecial 57	java/io/ByteArrayOutputStream:<init>	()V
    //   28: astore 4
    //   30: aload_3
    //   31: astore_2
    //   32: aload 4
    //   34: aload_0
    //   35: getfield 38	cn/jpush/android/service/q:a	Ljava/lang/String;
    //   38: invokevirtual 61	java/lang/String:getBytes	()[B
    //   41: invokevirtual 65	java/io/ByteArrayOutputStream:write	([B)V
    //   44: aload_3
    //   45: astore_2
    //   46: aload 4
    //   48: aload_0
    //   49: getfield 40	cn/jpush/android/service/q:b	[B
    //   52: invokevirtual 65	java/io/ByteArrayOutputStream:write	([B)V
    //   55: aload_3
    //   56: astore_2
    //   57: aload 4
    //   59: invokevirtual 68	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   62: astore 4
    //   64: aload_3
    //   65: astore_2
    //   66: new 56	java/io/ByteArrayOutputStream
    //   69: dup
    //   70: invokespecial 57	java/io/ByteArrayOutputStream:<init>	()V
    //   73: astore 5
    //   75: aload_3
    //   76: astore_2
    //   77: aload 5
    //   79: aload 4
    //   81: arraylength
    //   82: iconst_2
    //   83: invokestatic 73	cn/jpush/android/util/aa:a	(II)[B
    //   86: invokevirtual 65	java/io/ByteArrayOutputStream:write	([B)V
    //   89: aload_3
    //   90: astore_2
    //   91: aload 5
    //   93: aload 4
    //   95: invokevirtual 65	java/io/ByteArrayOutputStream:write	([B)V
    //   98: aload_3
    //   99: astore_2
    //   100: aload 5
    //   102: invokevirtual 68	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   105: astore 5
    //   107: aload_3
    //   108: astore_2
    //   109: getstatic 34	cn/jpush/android/service/q:z	[Ljava/lang/String;
    //   112: iconst_1
    //   113: aaload
    //   114: aload_0
    //   115: getfield 38	cn/jpush/android/service/q:a	Ljava/lang/String;
    //   118: invokevirtual 77	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   121: ifeq +49 -> 170
    //   124: aload_3
    //   125: astore_2
    //   126: invokestatic 82	cn/jpush/android/service/ServiceInterface:c	()Ljava/lang/String;
    //   129: astore 4
    //   131: aload_3
    //   132: astore_2
    //   133: invokestatic 86	cn/jpush/android/service/ServiceInterface:d	()I
    //   136: istore_1
    //   137: aload_3
    //   138: astore_2
    //   139: aload_3
    //   140: new 88	java/net/DatagramPacket
    //   143: dup
    //   144: aload 5
    //   146: aload 5
    //   148: arraylength
    //   149: aload 4
    //   151: invokestatic 94	java/net/InetAddress:getByName	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   154: iload_1
    //   155: invokespecial 97	java/net/DatagramPacket:<init>	([BILjava/net/InetAddress;I)V
    //   158: invokevirtual 101	java/net/DatagramSocket:send	(Ljava/net/DatagramPacket;)V
    //   161: aload_3
    //   162: ifnull +7 -> 169
    //   165: aload_3
    //   166: invokevirtual 104	java/net/DatagramSocket:close	()V
    //   169: return
    //   170: aload_3
    //   171: astore_2
    //   172: invokestatic 107	cn/jpush/android/service/ServiceInterface:e	()Ljava/lang/String;
    //   175: astore 4
    //   177: aload_3
    //   178: astore_2
    //   179: invokestatic 110	cn/jpush/android/service/ServiceInterface:f	()I
    //   182: istore_1
    //   183: goto -46 -> 137
    //   186: astore_2
    //   187: aconst_null
    //   188: astore_3
    //   189: aload_3
    //   190: astore_2
    //   191: invokestatic 115	cn/jpush/android/util/x:h	()V
    //   194: aload_3
    //   195: astore_2
    //   196: new 16	java/lang/String
    //   199: dup
    //   200: aload_0
    //   201: getfield 40	cn/jpush/android/service/q:b	[B
    //   204: invokespecial 117	java/lang/String:<init>	([B)V
    //   207: astore 4
    //   209: aload_3
    //   210: astore_2
    //   211: new 119	java/lang/StringBuilder
    //   214: dup
    //   215: getstatic 34	cn/jpush/android/service/q:z	[Ljava/lang/String;
    //   218: iconst_0
    //   219: aaload
    //   220: invokespecial 122	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   223: aload 4
    //   225: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: pop
    //   232: aload_3
    //   233: astore_2
    //   234: invokestatic 131	cn/jpush/android/util/x:c	()V
    //   237: aload_3
    //   238: astore_2
    //   239: getstatic 136	cn/jpush/android/a:d	Landroid/content/Context;
    //   242: aload_0
    //   243: getfield 38	cn/jpush/android/service/q:a	Ljava/lang/String;
    //   246: aload 4
    //   248: invokestatic 141	cn/jpush/android/data/r:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   251: pop
    //   252: aload_3
    //   253: ifnull -84 -> 169
    //   256: aload_3
    //   257: invokevirtual 104	java/net/DatagramSocket:close	()V
    //   260: return
    //   261: astore_2
    //   262: aconst_null
    //   263: astore_3
    //   264: aload_3
    //   265: astore_2
    //   266: invokestatic 115	cn/jpush/android/util/x:h	()V
    //   269: aload_3
    //   270: astore_2
    //   271: new 16	java/lang/String
    //   274: dup
    //   275: aload_0
    //   276: getfield 40	cn/jpush/android/service/q:b	[B
    //   279: invokespecial 117	java/lang/String:<init>	([B)V
    //   282: astore 4
    //   284: aload_3
    //   285: astore_2
    //   286: new 119	java/lang/StringBuilder
    //   289: dup
    //   290: getstatic 34	cn/jpush/android/service/q:z	[Ljava/lang/String;
    //   293: iconst_0
    //   294: aaload
    //   295: invokespecial 122	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   298: aload 4
    //   300: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   306: pop
    //   307: aload_3
    //   308: astore_2
    //   309: invokestatic 131	cn/jpush/android/util/x:c	()V
    //   312: aload_3
    //   313: astore_2
    //   314: getstatic 136	cn/jpush/android/a:d	Landroid/content/Context;
    //   317: aload_0
    //   318: getfield 38	cn/jpush/android/service/q:a	Ljava/lang/String;
    //   321: aload 4
    //   323: invokestatic 141	cn/jpush/android/data/r:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   326: pop
    //   327: aload_3
    //   328: ifnull -159 -> 169
    //   331: aload_3
    //   332: invokevirtual 104	java/net/DatagramSocket:close	()V
    //   335: return
    //   336: astore_3
    //   337: aload_2
    //   338: ifnull +7 -> 345
    //   341: aload_2
    //   342: invokevirtual 104	java/net/DatagramSocket:close	()V
    //   345: aload_3
    //   346: athrow
    //   347: astore_3
    //   348: goto -11 -> 337
    //   351: astore_2
    //   352: goto -88 -> 264
    //   355: astore_2
    //   356: goto -167 -> 189
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	186	java/net/SocketException
    //   2	10	261	java/io/IOException
    //   2	10	336	finally
    //   12	19	347	finally
    //   21	30	347	finally
    //   32	44	347	finally
    //   46	55	347	finally
    //   57	64	347	finally
    //   66	75	347	finally
    //   77	89	347	finally
    //   91	98	347	finally
    //   100	107	347	finally
    //   109	124	347	finally
    //   126	131	347	finally
    //   133	137	347	finally
    //   139	161	347	finally
    //   172	177	347	finally
    //   179	183	347	finally
    //   191	194	347	finally
    //   196	209	347	finally
    //   211	232	347	finally
    //   234	237	347	finally
    //   239	252	347	finally
    //   266	269	347	finally
    //   271	284	347	finally
    //   286	307	347	finally
    //   309	312	347	finally
    //   314	327	347	finally
    //   12	19	351	java/io/IOException
    //   21	30	351	java/io/IOException
    //   32	44	351	java/io/IOException
    //   46	55	351	java/io/IOException
    //   57	64	351	java/io/IOException
    //   66	75	351	java/io/IOException
    //   77	89	351	java/io/IOException
    //   91	98	351	java/io/IOException
    //   100	107	351	java/io/IOException
    //   109	124	351	java/io/IOException
    //   126	131	351	java/io/IOException
    //   133	137	351	java/io/IOException
    //   139	161	351	java/io/IOException
    //   172	177	351	java/io/IOException
    //   179	183	351	java/io/IOException
    //   12	19	355	java/net/SocketException
    //   21	30	355	java/net/SocketException
    //   32	44	355	java/net/SocketException
    //   46	55	355	java/net/SocketException
    //   57	64	355	java/net/SocketException
    //   66	75	355	java/net/SocketException
    //   77	89	355	java/net/SocketException
    //   91	98	355	java/net/SocketException
    //   100	107	355	java/net/SocketException
    //   109	124	355	java/net/SocketException
    //   126	131	355	java/net/SocketException
    //   133	137	355	java/net/SocketException
    //   139	161	355	java/net/SocketException
    //   172	177	355	java/net/SocketException
    //   179	183	355	java/net/SocketException
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.q
 * JD-Core Version:    0.6.2
 */