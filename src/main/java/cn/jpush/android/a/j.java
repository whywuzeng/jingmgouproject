package cn.jpush.android.a;

import android.net.wifi.ScanResult;
import cn.jpush.android.util.x;
import org.json.JSONObject;

public final class j
  implements Comparable<j>
{
  private static final String[] z;
  public final String a;
  public final int b;
  public final String c;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "X1E";
    int i = -1;
    int j = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int k = localObject2.length;
    int m;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      localObject3 = localObject1;
      n = j;
      localObject1 = localObject2;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 75;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label121;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
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
      k = i;
      m = j;
      if (i > j)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "J?G\005*U\tS\0379\\8G\037#";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "T7C4*]2R\0168J";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 57;
        break label99;
        i = 86;
        break label99;
        i = 32;
        break label99;
        i = 107;
        break label99;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  public j(i parami, ScanResult paramScanResult)
  {
    this.a = paramScanResult.BSSID;
    this.b = paramScanResult.level;
    this.c = paramScanResult.SSID;
  }

  public j(i parami, String paramString1, int paramInt, String paramString2)
  {
    this.a = paramString1;
    this.b = paramInt;
    this.c = paramString2;
  }

  public final JSONObject a()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      String str = this.a;
      localJSONObject.put(z[2], str);
      int i = this.b;
      localJSONObject.put(z[1], i);
      localJSONObject.put(z[0], 0);
      return localJSONObject;
    }
    catch (Exception localException)
    {
      x.j();
    }
    return localJSONObject;
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == this);
    do
    {
      return true;
      if (!(paramObject instanceof j))
        break;
      paramObject = (j)paramObject;
    }
    while ((paramObject.b == this.b) && (paramObject.a.equals(this.a)));
    return false;
  }

  public final int hashCode()
  {
    return this.b ^ this.a.hashCode();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a.j
 * JD-Core Version:    0.6.2
 */