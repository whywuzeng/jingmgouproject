package cn.jpush.android.a;

import cn.jpush.android.util.x;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  private static final String z;
  private int a;
  private int b;
  private int c;
  private int d;
  private String e;
  private double f;
  private double g;

  static
  {
    Object localObject1 = "".toCharArray();
    int j = localObject1.length;
    int m = 0;
    int i = 0;
    Object localObject2 = localObject1;
    int k = j;
    label30: int n;
    if (j <= 1)
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 58;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[k] = ((char)(i ^ n));
      m += 1;
      if (j == 0)
      {
        k = j;
        break label30;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break;
      z = new String(localObject2).intern();
      return;
      i = 8;
      continue;
      i = 20;
      continue;
      i = 43;
      continue;
      i = 125;
    }
  }

  public final int a()
  {
    return this.a;
  }

  public final void a(int paramInt)
  {
    this.a = paramInt;
  }

  public final void a(String paramString)
  {
    this.e = paramString;
  }

  public final JSONArray b()
  {
    JSONArray localJSONArray = new JSONArray();
    try
    {
      localJSONArray.put(new JSONObject(toString()));
      return localJSONArray;
    }
    catch (JSONException localJSONException)
    {
      a.class.getSimpleName();
      localJSONException.getMessage();
      x.f();
    }
    return null;
  }

  public final void b(int paramInt)
  {
    this.b = paramInt;
  }

  public final void c(int paramInt)
  {
    this.c = paramInt;
  }

  public final void d(int paramInt)
  {
    this.d = paramInt;
  }

  public String toString()
  {
    try
    {
      String str = String.format(z, new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.d), Integer.valueOf(this.c), this.e, Double.valueOf(this.f), Double.valueOf(this.g) });
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a.a
 * JD-Core Version:    0.6.2
 */