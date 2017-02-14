package com.baidu.location.c;

import java.util.concurrent.Callable;

class i
  implements Callable
{
  i(d paramd, String[] paramArrayOfString)
  {
  }

  // ERROR //
  public com.baidu.location.BDLocation a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 5
    //   9: new 25	com/baidu/location/BDLocation
    //   12: dup
    //   13: invokespecial 26	com/baidu/location/BDLocation:<init>	()V
    //   16: astore_2
    //   17: aload_2
    //   18: astore 4
    //   20: aload_0
    //   21: getfield 16	com/baidu/location/c/i:a	[Ljava/lang/String;
    //   24: arraylength
    //   25: ifle +101 -> 126
    //   28: invokestatic 32	com/baidu/location/c/d:else	()Landroid/content/Context;
    //   31: invokevirtual 38	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   34: getstatic 42	com/baidu/location/c/d:ab	Ljava/lang/String;
    //   37: iconst_0
    //   38: invokevirtual 48	android/content/pm/PackageManager:resolveContentProvider	(Ljava/lang/String;I)Landroid/content/pm/ProviderInfo;
    //   41: astore_3
    //   42: aload_3
    //   43: ifnull +86 -> 129
    //   46: aload_3
    //   47: ifnull +173 -> 220
    //   50: invokestatic 32	com/baidu/location/c/d:else	()Landroid/content/Context;
    //   53: invokevirtual 52	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   56: aload_3
    //   57: getfield 57	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   60: invokestatic 61	com/baidu/location/c/d:for	(Ljava/lang/String;)Landroid/net/Uri;
    //   63: aload_0
    //   64: getfield 16	com/baidu/location/c/i:a	[Ljava/lang/String;
    //   67: aconst_null
    //   68: aconst_null
    //   69: aconst_null
    //   70: invokevirtual 67	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   73: astore_3
    //   74: aload_3
    //   75: invokestatic 72	com/baidu/location/c/h:a	(Landroid/database/Cursor;)Lcom/baidu/location/BDLocation;
    //   78: astore 4
    //   80: aload 4
    //   82: astore_2
    //   83: aload_3
    //   84: ifnull +12 -> 96
    //   87: aload_3
    //   88: invokeinterface 77 1 0
    //   93: aload 4
    //   95: astore_2
    //   96: aload_2
    //   97: astore_3
    //   98: aload_3
    //   99: astore 4
    //   101: aload_3
    //   102: ifnull +24 -> 126
    //   105: aload_3
    //   106: astore 4
    //   108: aload_3
    //   109: invokevirtual 81	com/baidu/location/BDLocation:getLocType	()I
    //   112: bipush 67
    //   114: if_icmpeq +12 -> 126
    //   117: aload_3
    //   118: bipush 66
    //   120: invokevirtual 85	com/baidu/location/BDLocation:setLocType	(I)V
    //   123: aload_3
    //   124: astore 4
    //   126: aload 4
    //   128: areturn
    //   129: aload_0
    //   130: getfield 14	com/baidu/location/c/i:b	Lcom/baidu/location/c/d;
    //   133: invokestatic 89	com/baidu/location/c/d:if	(Lcom/baidu/location/c/d;)Lcom/baidu/location/c/j;
    //   136: invokevirtual 95	com/baidu/location/c/j:o	()[Ljava/lang/String;
    //   139: astore 8
    //   141: iconst_0
    //   142: istore_1
    //   143: iload_1
    //   144: aload 8
    //   146: arraylength
    //   147: if_icmpge -101 -> 46
    //   150: invokestatic 32	com/baidu/location/c/d:else	()Landroid/content/Context;
    //   153: invokevirtual 38	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   156: aload 8
    //   158: iload_1
    //   159: aaload
    //   160: iconst_0
    //   161: invokevirtual 48	android/content/pm/PackageManager:resolveContentProvider	(Ljava/lang/String;I)Landroid/content/pm/ProviderInfo;
    //   164: astore 4
    //   166: aload 4
    //   168: astore_3
    //   169: aload 4
    //   171: ifnonnull -125 -> 46
    //   174: iload_1
    //   175: iconst_1
    //   176: iadd
    //   177: istore_1
    //   178: aload 4
    //   180: astore_3
    //   181: goto -38 -> 143
    //   184: astore_3
    //   185: aconst_null
    //   186: astore_3
    //   187: aload_3
    //   188: ifnull +171 -> 359
    //   191: aload_3
    //   192: invokeinterface 77 1 0
    //   197: goto -101 -> 96
    //   200: astore_3
    //   201: goto -105 -> 96
    //   204: astore_2
    //   205: aload 5
    //   207: astore_3
    //   208: aload_3
    //   209: ifnull +9 -> 218
    //   212: aload_3
    //   213: invokeinterface 77 1 0
    //   218: aload_2
    //   219: athrow
    //   220: new 97	com/baidu/location/c/h$a
    //   223: dup
    //   224: aload_0
    //   225: getfield 16	com/baidu/location/c/i:a	[Ljava/lang/String;
    //   228: invokespecial 100	com/baidu/location/c/h$a:<init>	([Ljava/lang/String;)V
    //   231: astore 5
    //   233: aload 7
    //   235: astore 4
    //   237: aload 6
    //   239: astore_3
    //   240: aload_0
    //   241: getfield 14	com/baidu/location/c/i:b	Lcom/baidu/location/c/d;
    //   244: invokestatic 104	com/baidu/location/c/d:do	(Lcom/baidu/location/c/d;)Lcom/baidu/location/c/a;
    //   247: aload 5
    //   249: invokevirtual 109	com/baidu/location/c/a:a	(Lcom/baidu/location/c/h$a;)Landroid/database/Cursor;
    //   252: astore 5
    //   254: aload 5
    //   256: astore 4
    //   258: aload 5
    //   260: astore_3
    //   261: aload 5
    //   263: invokestatic 72	com/baidu/location/c/h:a	(Landroid/database/Cursor;)Lcom/baidu/location/BDLocation;
    //   266: astore 6
    //   268: aload 6
    //   270: astore_2
    //   271: aload_2
    //   272: astore_3
    //   273: aload 5
    //   275: ifnull -177 -> 98
    //   278: aload 5
    //   280: invokeinterface 77 1 0
    //   285: aload_2
    //   286: astore_3
    //   287: goto -189 -> 98
    //   290: astore_3
    //   291: aload_2
    //   292: astore_3
    //   293: goto -195 -> 98
    //   296: astore_3
    //   297: aload_2
    //   298: astore_3
    //   299: aload 4
    //   301: ifnull -203 -> 98
    //   304: aload 4
    //   306: invokeinterface 77 1 0
    //   311: aload_2
    //   312: astore_3
    //   313: goto -215 -> 98
    //   316: astore_3
    //   317: aload_2
    //   318: astore_3
    //   319: goto -221 -> 98
    //   322: astore_2
    //   323: aload_3
    //   324: ifnull +9 -> 333
    //   327: aload_3
    //   328: invokeinterface 77 1 0
    //   333: aload_2
    //   334: athrow
    //   335: astore_2
    //   336: aload 4
    //   338: astore_2
    //   339: goto -243 -> 96
    //   342: astore_3
    //   343: goto -125 -> 218
    //   346: astore_3
    //   347: goto -14 -> 333
    //   350: astore_2
    //   351: goto -143 -> 208
    //   354: astore 4
    //   356: goto -169 -> 187
    //   359: goto -263 -> 96
    //
    // Exception table:
    //   from	to	target	type
    //   50	74	184	java/lang/Exception
    //   191	197	200	java/lang/Exception
    //   50	74	204	finally
    //   278	285	290	java/lang/Exception
    //   240	254	296	java/lang/Exception
    //   261	268	296	java/lang/Exception
    //   304	311	316	java/lang/Exception
    //   240	254	322	finally
    //   261	268	322	finally
    //   87	93	335	java/lang/Exception
    //   212	218	342	java/lang/Exception
    //   327	333	346	java/lang/Exception
    //   74	80	350	finally
    //   74	80	354	java/lang/Exception
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c.i
 * JD-Core Version:    0.6.2
 */