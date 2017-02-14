package cn.jpush.android;

import android.os.Environment;
import java.io.File;

public abstract interface b
{
  public static final String a;
  public static final String b;

  static
  {
    Object localObject1 = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append(File.separator);
    int k = -1;
    Object localObject2 = "Y/M,3\037".toCharArray();
    int j = localObject2.length;
    int i;
    Object localObject3;
    int n;
    label55: int m;
    int i1;
    label153: Object localObject4;
    if (j <= 1)
    {
      i = 0;
      localObject3 = localObject1;
      localObject1 = localObject2;
      n = k;
      m = i;
      k = i;
      localObject2 = localObject1;
      i1 = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 64;
      case 0:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        localObject2[k] = ((char)(i ^ i1));
        m += 1;
        if (j != 0)
          break label153;
        k = j;
        break;
        i = 119;
        continue;
        i = 69;
        continue;
        i = 61;
        continue;
        i = 89;
      }
      k = j;
      localObject4 = localObject3;
      localObject2 = localObject1;
      i1 = n;
    }
    while (true)
    {
      n = i1;
      localObject1 = localObject2;
      localObject3 = localObject4;
      j = k;
      i = m;
      if (k > m)
        break label55;
      localObject1 = new String((char[])localObject2).intern();
      switch (i1)
      {
      default:
        a = (String)localObject1 + File.separator;
        localObject1 = new StringBuilder().append(a);
        k = 0;
        break;
      case 0:
        b = (String)localObject1;
        return;
        m = 0;
        i1 = k;
        localObject4 = localObject1;
        k = j;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.b
 * JD-Core Version:    0.6.2
 */