package cn.jpush.android.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import cn.jpush.android.util.x;
import java.util.concurrent.atomic.AtomicInteger;

public final class c extends SQLiteOpenHelper
{
  public static final String[] a;
  public static final String[] b = localObject3;
  private static c c;
  private static SQLiteDatabase d;
  private static AtomicInteger e = new AtomicInteger();
  private static final String[] z;

  static
  {
    Object localObject1 = new String[13];
    int j = 0;
    Object localObject2 = "4h(x\0342z(U\007?";
    int i = -1;
    Object localObject4 = localObject1;
    int i4;
    Object localObject5;
    Object localObject3;
    while (true)
    {
      char[] arrayOfChar = ((String)localObject2).toCharArray();
      int k = arrayOfChar.length;
      int i1 = 0;
      int m = 0;
      int i3 = i;
      localObject2 = arrayOfChar;
      i4 = j;
      localObject5 = localObject1;
      localObject3 = localObject4;
      int n = k;
      Object localObject6;
      int i2;
      if (k <= 1)
      {
        localObject6 = localObject1;
        localObject1 = arrayOfChar;
        i2 = i;
        label72: n = m;
        label75: localObject2 = localObject1;
        i1 = localObject2[m];
        switch (n % 5)
        {
        default:
          i = 110;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      while (true)
      {
        localObject2[m] = ((char)(i ^ i1));
        n += 1;
        if (k == 0)
        {
          m = k;
          break label75;
        }
        i1 = n;
        n = k;
        localObject3 = localObject4;
        localObject5 = localObject6;
        i4 = j;
        localObject2 = localObject1;
        i3 = i2;
        i2 = i3;
        localObject1 = localObject2;
        j = i4;
        localObject6 = localObject5;
        localObject4 = localObject3;
        k = n;
        m = i1;
        if (n > i1)
          break label72;
        localObject1 = new String((char[])localObject2).intern();
        switch (i3)
        {
        default:
          localObject5[i4] = localObject1;
          j = 1;
          localObject2 = "6$d12z";
          i = 0;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 0:
          localObject5[i4] = localObject1;
          j = 2;
          localObject2 = ":n=U\007?";
          i = 1;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 1:
          localObject5[i4] = localObject1;
          j = 3;
          localObject2 = "6m*U\007?";
          i = 2;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 2:
          localObject5[i4] = localObject1;
          j = 4;
          localObject2 = ".n!c\035/";
          i = 3;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 3:
          localObject5[i4] = localObject1;
          j = 5;
          localObject2 = ":n=U\007?#r";
          i = 4;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 4:
          localObject5[i4] = localObject1;
          j = 6;
          localObject2 = "({?|\0078{cn\f";
          i = 5;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 5:
          localObject5[i4] = localObject1;
          j = 7;
          localObject2 = "";
          i = 6;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 6:
          localObject5[i4] = localObject1;
          j = 8;
          localObject2 = "?l\"zN//f\013{k=f\007(j";
          i = 7;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 7:
          localObject5[i4] = localObject1;
          j = 9;
          localObject2 = "\017v(*\0017z\033o\034(w\"dN2mw*";
          i = 8;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 8:
          localObject5[i4] = localObject1;
          j = 10;
          localObject2 = "";
          i = 9;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 9:
          localObject5[i4] = localObject1;
          j = 11;
          localObject2 = "";
          i = 10;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 10:
          localObject5[i4] = localObject1;
          j = 12;
          localObject2 = "";
          i = 11;
          localObject1 = localObject3;
          localObject4 = localObject3;
          break;
        case 11:
          localObject5[i4] = localObject1;
          z = localObject3;
          localObject1 = new String[6];
          j = 0;
          localObject2 = "\004w)";
          i = 12;
          localObject4 = localObject1;
          break;
          i = 91;
          continue;
          i = 30;
          continue;
          i = 77;
          continue;
          i = 10;
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        }
      }
      localObject5[i4] = localObject1;
      localObject3[1] = z[3];
      j = 2;
      localObject2 = "){=o\017/A#\003";
      i = 13;
      localObject1 = localObject3;
      localObject4 = localObject3;
      continue;
      localObject5[i4] = localObject1;
      j = 3;
      localObject2 = "(j,x\032\004n\"y";
      i = 14;
      localObject1 = localObject3;
      localObject4 = localObject3;
      continue;
      localObject5[i4] = localObject1;
      j = 4;
      localObject2 = ">p)U\0364m";
      i = 15;
      localObject1 = localObject3;
      localObject4 = localObject3;
      continue;
      localObject5[i4] = localObject1;
      j = 5;
      localObject2 = "8q#~\0135j";
      i = 16;
      localObject1 = localObject3;
      localObject4 = localObject3;
      continue;
      localObject5[i4] = localObject1;
      a = localObject3;
      localObject1 = new String[4];
      j = 0;
      localObject2 = "\004w)";
      i = 17;
      localObject4 = localObject1;
    }
    localObject5[i4] = localObject1;
    localObject3[1] = z[3];
    localObject3[2] = z[2];
    localObject3[3] = z[1];
  }

  private c(Context paramContext)
  {
    super(paramContext, z[6], null, 3);
  }

  private SQLiteDatabase a()
  {
    try
    {
      int i = e.incrementAndGet();
      if (1 == i);
      try
      {
        d = c.getWritableDatabase();
        SQLiteDatabase localSQLiteDatabase = d;
        return localSQLiteDatabase;
      }
      catch (Exception localException)
      {
        while (true)
          x.j();
      }
    }
    finally
    {
    }
  }

  private static c a(Context paramContext)
  {
    if (c == null)
      c = new c(paramContext);
    return c;
  }

  // ERROR //
  public static String a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 108	cn/jpush/android/data/c:a	(Landroid/content/Context;)Lcn/jpush/android/data/c;
    //   4: astore 7
    //   6: aload 7
    //   8: invokespecial 110	cn/jpush/android/data/c:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   11: astore 8
    //   13: aload 8
    //   15: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   18: iconst_4
    //   19: aaload
    //   20: getstatic 72	cn/jpush/android/data/c:b	[Ljava/lang/String;
    //   23: aconst_null
    //   24: aconst_null
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: invokevirtual 116	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   31: astore_0
    //   32: aload_0
    //   33: ifnull +244 -> 277
    //   36: aload_0
    //   37: invokeinterface 121 1 0
    //   42: iflt +235 -> 277
    //   45: aload_0
    //   46: invokeinterface 125 1 0
    //   51: ifeq +226 -> 277
    //   54: aload_0
    //   55: aload_0
    //   56: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   59: iconst_2
    //   60: aaload
    //   61: invokeinterface 129 2 0
    //   66: invokeinterface 133 2 0
    //   71: astore 4
    //   73: aload_0
    //   74: aload_0
    //   75: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   78: iconst_3
    //   79: aaload
    //   80: invokeinterface 129 2 0
    //   85: invokeinterface 133 2 0
    //   90: astore 6
    //   92: aload_0
    //   93: aload_0
    //   94: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   97: iconst_1
    //   98: aaload
    //   99: invokeinterface 129 2 0
    //   104: invokeinterface 133 2 0
    //   109: astore 5
    //   111: aload_0
    //   112: aload_0
    //   113: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   116: iconst_0
    //   117: aaload
    //   118: invokeinterface 129 2 0
    //   123: invokeinterface 133 2 0
    //   128: astore_3
    //   129: aload_1
    //   130: aload 4
    //   132: invokevirtual 137	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   135: istore_2
    //   136: iload_2
    //   137: ifeq -92 -> 45
    //   140: aload 6
    //   142: astore_1
    //   143: aload 8
    //   145: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   148: iconst_4
    //   149: aaload
    //   150: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   153: iconst_5
    //   154: aaload
    //   155: iconst_1
    //   156: anewarray 18	java/lang/String
    //   159: dup
    //   160: iconst_0
    //   161: aload 4
    //   163: aastore
    //   164: invokevirtual 141	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   167: pop
    //   168: new 143	java/lang/StringBuilder
    //   171: dup
    //   172: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   175: aload_1
    //   176: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: ldc 150
    //   181: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: aload 5
    //   186: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: ldc 150
    //   191: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: aload_3
    //   195: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   201: astore_3
    //   202: aload_0
    //   203: ifnull +9 -> 212
    //   206: aload_0
    //   207: invokeinterface 156 1 0
    //   212: aload 7
    //   214: invokespecial 158	cn/jpush/android/data/c:b	()V
    //   217: aload_3
    //   218: areturn
    //   219: astore_0
    //   220: aconst_null
    //   221: astore_0
    //   222: ldc 160
    //   224: astore_1
    //   225: aload_0
    //   226: ifnull +9 -> 235
    //   229: aload_0
    //   230: invokeinterface 156 1 0
    //   235: aload 7
    //   237: invokespecial 158	cn/jpush/android/data/c:b	()V
    //   240: aload_1
    //   241: areturn
    //   242: astore_1
    //   243: aconst_null
    //   244: astore_0
    //   245: aload_0
    //   246: ifnull +9 -> 255
    //   249: aload_0
    //   250: invokeinterface 156 1 0
    //   255: aload 7
    //   257: invokespecial 158	cn/jpush/android/data/c:b	()V
    //   260: aload_1
    //   261: athrow
    //   262: astore_1
    //   263: goto -18 -> 245
    //   266: astore_1
    //   267: ldc 160
    //   269: astore_1
    //   270: goto -45 -> 225
    //   273: astore_3
    //   274: goto -49 -> 225
    //   277: ldc 160
    //   279: astore_3
    //   280: ldc 160
    //   282: astore 4
    //   284: ldc 160
    //   286: astore 5
    //   288: ldc 160
    //   290: astore_1
    //   291: goto -148 -> 143
    //
    // Exception table:
    //   from	to	target	type
    //   13	32	219	java/lang/Exception
    //   13	32	242	finally
    //   36	45	262	finally
    //   45	136	262	finally
    //   143	202	262	finally
    //   36	45	266	java/lang/Exception
    //   45	136	266	java/lang/Exception
    //   143	202	273	java/lang/Exception
  }

  // ERROR //
  public static void a(Context paramContext, d paramd, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 166	cn/jpush/android/data/d:c	Ljava/lang/String;
    //   4: astore 5
    //   6: aload_1
    //   7: getfield 168	cn/jpush/android/data/d:d	Ljava/lang/String;
    //   10: astore_1
    //   11: aload_0
    //   12: invokestatic 108	cn/jpush/android/data/c:a	(Landroid/content/Context;)Lcn/jpush/android/data/c;
    //   15: astore 4
    //   17: aload 4
    //   19: invokespecial 110	cn/jpush/android/data/c:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore 6
    //   24: aload 6
    //   26: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   29: iconst_4
    //   30: aaload
    //   31: getstatic 72	cn/jpush/android/data/c:b	[Ljava/lang/String;
    //   34: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   37: iconst_5
    //   38: aaload
    //   39: iconst_1
    //   40: anewarray 18	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: aload_2
    //   46: aastore
    //   47: aconst_null
    //   48: aconst_null
    //   49: aconst_null
    //   50: invokevirtual 116	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   53: astore_0
    //   54: aload_0
    //   55: ifnull +12 -> 67
    //   58: aload_0
    //   59: invokeinterface 121 1 0
    //   64: ifgt +87 -> 151
    //   67: new 170	android/content/ContentValues
    //   70: dup
    //   71: invokespecial 171	android/content/ContentValues:<init>	()V
    //   74: astore 7
    //   76: aload 7
    //   78: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   81: iconst_3
    //   82: aaload
    //   83: aload 5
    //   85: invokevirtual 175	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   88: aload 7
    //   90: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   93: iconst_2
    //   94: aaload
    //   95: aload_2
    //   96: invokevirtual 175	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   99: aload 7
    //   101: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   104: iconst_1
    //   105: aaload
    //   106: aload_3
    //   107: invokevirtual 175	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   110: aload 7
    //   112: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   115: iconst_0
    //   116: aaload
    //   117: aload_1
    //   118: invokevirtual 175	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   121: aload 6
    //   123: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   126: iconst_4
    //   127: aaload
    //   128: aconst_null
    //   129: aload 7
    //   131: invokevirtual 179	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   134: pop2
    //   135: aload_0
    //   136: ifnull +9 -> 145
    //   139: aload_0
    //   140: invokeinterface 156 1 0
    //   145: aload 4
    //   147: invokespecial 158	cn/jpush/android/data/c:b	()V
    //   150: return
    //   151: aload_0
    //   152: invokeinterface 182 1 0
    //   157: pop
    //   158: new 170	android/content/ContentValues
    //   161: dup
    //   162: invokespecial 171	android/content/ContentValues:<init>	()V
    //   165: astore 7
    //   167: aload 7
    //   169: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   172: iconst_3
    //   173: aaload
    //   174: aload 5
    //   176: invokevirtual 175	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   179: aload 7
    //   181: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   184: iconst_1
    //   185: aaload
    //   186: aload_3
    //   187: invokevirtual 175	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   190: aload 7
    //   192: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   195: iconst_0
    //   196: aaload
    //   197: aload_1
    //   198: invokevirtual 175	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   201: aload 6
    //   203: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   206: iconst_4
    //   207: aaload
    //   208: aload 7
    //   210: getstatic 58	cn/jpush/android/data/c:z	[Ljava/lang/String;
    //   213: iconst_5
    //   214: aaload
    //   215: iconst_1
    //   216: anewarray 18	java/lang/String
    //   219: dup
    //   220: iconst_0
    //   221: aload_2
    //   222: aastore
    //   223: invokevirtual 186	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   226: pop
    //   227: goto -92 -> 135
    //   230: astore_1
    //   231: aload_0
    //   232: ifnull +9 -> 241
    //   235: aload_0
    //   236: invokeinterface 156 1 0
    //   241: aload 4
    //   243: invokespecial 158	cn/jpush/android/data/c:b	()V
    //   246: return
    //   247: astore_1
    //   248: aconst_null
    //   249: astore_0
    //   250: aload_0
    //   251: ifnull +9 -> 260
    //   254: aload_0
    //   255: invokeinterface 156 1 0
    //   260: aload 4
    //   262: invokespecial 158	cn/jpush/android/data/c:b	()V
    //   265: aload_1
    //   266: athrow
    //   267: astore_1
    //   268: goto -18 -> 250
    //   271: astore_0
    //   272: aconst_null
    //   273: astore_0
    //   274: goto -43 -> 231
    //
    // Exception table:
    //   from	to	target	type
    //   58	67	230	java/lang/Exception
    //   67	135	230	java/lang/Exception
    //   151	227	230	java/lang/Exception
    //   24	54	247	finally
    //   58	67	267	finally
    //   67	135	267	finally
    //   151	227	267	finally
    //   24	54	271	java/lang/Exception
  }

  private void b()
  {
    try
    {
      if (e.decrementAndGet() == 0)
        d.close();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    x.c();
    paramSQLiteDatabase.execSQL(z[11]);
    paramSQLiteDatabase.execSQL(z[12]);
  }

  @SuppressLint({"NewApi"})
  public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 11)
      super.onDowngrade(paramSQLiteDatabase, paramInt1, paramInt2);
    new StringBuilder(z[9]).append(paramInt1).append(z[10]).append(paramInt2).toString();
    x.c();
    paramSQLiteDatabase.execSQL(z[7]);
    paramSQLiteDatabase.execSQL(z[8]);
    onCreate(paramSQLiteDatabase);
  }

  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    new StringBuilder(z[9]).append(paramInt1).append(z[10]).append(paramInt2).toString();
    x.c();
    paramSQLiteDatabase.execSQL(z[7]);
    paramSQLiteDatabase.execSQL(z[8]);
    onCreate(paramSQLiteDatabase);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.c
 * JD-Core Version:    0.6.2
 */