package cn.jpush.android.util;

import org.json.JSONException;
import org.json.JSONObject;

final class u
  implements Runnable
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "\"=-W\021\032\013-F";
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
          i = 126;
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
        localObject2 = "\032\n8[\f\034O$[\031R";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 104;
        break label99;
        i = 111;
        break label99;
        i = 72;
        break label99;
        i = 52;
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

  u(JSONObject paramJSONObject)
  {
  }

  public final void run()
  {
    try
    {
      x.c(z[0], z[1] + this.a.toString(1));
      return;
    }
    catch (JSONException localJSONException)
    {
      x.c(z[0], z[1] + this.a.toString());
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.u
 * JD-Core Version:    0.6.2
 */