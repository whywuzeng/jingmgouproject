package cn.jpush.android.util;

 enum b
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[5];
    int j = 0;
    Object localObject2 = "\022}>\020\036\023q,\006\033\004w%\006\016\023l<\020\023\021";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label67: n = m;
      label70: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 93;
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
        break label70;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label67;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "\022}>\020\036\023q,\006\033\004w%\006\023\023o";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\022}>\020\036\023q,\006\033\004w%\006\030\016l-\013\023\027t;\r\022\004y/\034";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\022}>\020\036\023q,\006\033\004w%\006\016\017k";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\022}>\020\036\023q,\006\033\004w%\006\016\036y:\034\r\004}.\n";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = new b(z[1], 0);
        b = new b(z[0], 1);
        c = new b(z[2], 2);
        d = new b(z[4], 3);
        e = new b(z[3], 4);
        f = new b[] { a, b, c, d, e };
        return;
        i = 86;
        continue;
        i = 56;
        continue;
        i = 104;
        continue;
        i = 89;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.b
 * JD-Core Version:    0.6.2
 */