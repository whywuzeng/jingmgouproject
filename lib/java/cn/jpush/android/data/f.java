package cn.jpush.android.data;

import cn.jpush.android.util.ai;
import org.json.JSONException;
import org.json.JSONObject;

public final class f
{
  private static final String[] z;
  public int a;
  public String b;
  public String c;
  public String d;
  public String e;

  static
  {
    String[] arrayOfString = new String[8];
    int j = 0;
    Object localObject2 = "v\"%";
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
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 26;
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
        break label71;
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
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "w44yvh%0";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "o0%";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "w44yv";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "o8/y";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "G?";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = ";q";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "8;2iisr";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 27;
        continue;
        i = 81;
        continue;
        i = 66;
        continue;
        i = 28;
      }
    }
  }

  public f()
  {
  }

  public f(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.a = paramInt;
    this.e = paramString1;
    this.c = paramString3;
    this.b = paramString2;
    this.d = paramString4;
  }

  public final int a()
  {
    return toString().getBytes().length;
  }

  public final JSONObject b()
  {
    int i = 1;
    JSONObject localJSONObject = new JSONObject();
    if ((ai.a(this.d)) || (ai.a(this.e)) || (ai.a(this.b)) || (ai.a(this.c)))
      i = 0;
    if (i == 0)
      return null;
    try
    {
      localJSONObject.put(z[3], this.a);
      localJSONObject.put(z[1], this.e);
      localJSONObject.put(z[4], this.d);
      localJSONObject.put(z[2], this.b);
      localJSONObject.put(z[0], this.c);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  public final boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }

  public final int hashCode()
  {
    return super.hashCode();
  }

  public final String toString()
  {
    if ((this.c != null) && (this.c.contains(z[5])))
      this.c.replaceAll(z[5], z[7]);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.a).append(z[6]);
    localStringBuffer.append(this.e).append(z[6]);
    localStringBuffer.append(this.d).append(z[6]);
    localStringBuffer.append(this.b).append(z[6]);
    localStringBuffer.append(this.c).append(z[6]);
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.f
 * JD-Core Version:    0.6.2
 */