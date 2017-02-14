package cn.jpush.android.data;

import cn.jpush.android.util.h;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public final class o
  implements Serializable
{
  private static final String[] z;
  public String a;
  public String b;
  public String c;
  public String d;
  public boolean e;
  public String f;
  public String g;
  public String h;
  public boolean i;
  public String j;
  public ArrayList<String> k = new ArrayList();
  public String l;
  public String m;
  public String n;

  static
  {
    String[] arrayOfString = new String[14];
    int i2 = 0;
    Object localObject2 = "/N<nw)t\nvd\"";
    int i1 = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i3 = arrayOfChar.length;
    int i6 = 0;
    int i4 = 0;
    int i8 = i1;
    localObject2 = arrayOfChar;
    int i9 = i2;
    Object localObject3 = localObject1;
    int i5 = i3;
    Object localObject4;
    int i7;
    if (i3 <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i7 = i1;
      label68: i5 = i4;
      label71: localObject2 = localObject1;
      i6 = localObject2[i4];
      switch (i5 % 5)
      {
      default:
        i1 = 22;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i4] = ((char)(i1 ^ i6));
      i5 += 1;
      if (i3 == 0)
      {
        i4 = i3;
        break label71;
      }
      i6 = i5;
      i5 = i3;
      localObject3 = localObject4;
      i9 = i2;
      localObject2 = localObject1;
      i8 = i7;
      i7 = i8;
      localObject1 = localObject2;
      i2 = i9;
      localObject4 = localObject3;
      i3 = i5;
      i4 = i6;
      if (i5 > i6)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i8)
      {
      default:
        localObject3[i9] = localObject1;
        i2 = 1;
        localObject2 = "/N!jb\"t";
        i1 = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i9] = localObject1;
        i2 = 2;
        localObject2 = "/N&jl+";
        i1 = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i9] = localObject1;
        i2 = 3;
        localObject2 = "/N#fd";
        i1 = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i9] = localObject1;
        i2 = 4;
        localObject2 = "/N'fe";
        i1 = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i9] = localObject1;
        i2 = 5;
        localObject2 = "/N<mp!";
        i1 = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i9] = localObject1;
        i2 = 6;
        localObject2 = "/N0qs=";
        i1 = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i9] = localObject1;
        i2 = 7;
        localObject2 = "/N!zf+";
        i1 = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i9] = localObject1;
        i2 = 8;
        localObject2 = "/N&`y<t";
        i1 = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i9] = localObject1;
        i2 = 9;
        localObject2 = "/N<`y N qz";
        i1 = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i9] = localObject1;
        i2 = 10;
        localObject2 = "/N0vd\"";
        i1 = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i9] = localObject1;
        i2 = 11;
        localObject2 = "\021x8bq+A4w~";
        i1 = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i9] = localObject1;
        i2 = 12;
        localObject2 = "\021f0aF/v0Sw:y";
        i1 = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i9] = localObject1;
        i2 = 13;
        localObject2 = "\021x6lx\036p!k";
        i1 = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i9] = localObject1;
        z = arrayOfString;
        return;
        i1 = 78;
        continue;
        i1 = 17;
        continue;
        i1 = 85;
        continue;
        i1 = 3;
      }
    }
  }

  public o(l paraml)
  {
  }

  private JSONObject a()
  {
    int i2 = 0;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(z[1], this.a);
      localJSONObject.put(z[9], this.b);
      localJSONObject.put(z[3], this.c);
      localJSONObject.put(z[7], this.d);
      String str = z[8];
      if (this.e)
      {
        i1 = 0;
        localJSONObject.put(str, i1);
        localJSONObject.put(z[2], this.f);
        localJSONObject.put(z[5], this.g);
        localJSONObject.put(z[0], this.h);
        localJSONObject.put(z[10], this.j);
        str = z[4];
        if (!this.i)
          break label199;
      }
      label199: for (int i1 = i2; ; i1 = 1)
      {
        localJSONObject.put(str, i1);
        localJSONObject.put(z[6], h.a(this.k));
        return localJSONObject;
        i1 = 1;
        break;
      }
    }
    catch (JSONException localJSONException)
    {
    }
    return localJSONObject;
  }

  public final String toString()
  {
    JSONObject localJSONObject = a();
    try
    {
      localJSONObject.put(z[13], this.l);
      localJSONObject.put(z[11], this.m);
      localJSONObject.put(z[12], this.n);
      label50: return localJSONObject.toString();
    }
    catch (JSONException localJSONException)
    {
      break label50;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.o
 * JD-Core Version:    0.6.2
 */