package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.m;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.a;
import cn.jpush.android.util.x;
import org.json.JSONObject;

public final class y extends d
{
  private static final String[] ab;
  public String W;
  public String X;
  public String Y;
  public String Z;
  public int a;
  public String aa;

  static
  {
    String[] arrayOfString = new String[6];
    int j = 0;
    Object localObject2 = "7~\023W*\025~XO,\006u\027\0311\033`\035\031hB";
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
        i = 69;
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
        localObject2 = "\024O\025]p";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\024O\rK)";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\024O\f@5\007";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\024O\021W#\r";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\024O\035L7\016";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        ab = arrayOfString;
        return;
        i = 98;
        continue;
        i = 16;
        continue;
        i = 120;
        continue;
        i = 57;
      }
    }
  }

  public y()
  {
    this.o = 2;
  }

  public final void a(Context paramContext)
  {
    x.b();
    ServiceInterface.a(this.c, 995, paramContext);
    if (this.a == 0)
    {
      if (a.b(paramContext))
        ServiceInterface.a(paramContext, this);
      return;
    }
    if (this.a == 1)
    {
      m.a(paramContext, this);
      return;
    }
    new StringBuilder(ab[0]).append(this.a).toString();
    x.c();
  }

  public final boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    x.b();
    this.a = paramJSONObject.optInt(ab[3], 0);
    this.W = paramJSONObject.optString(ab[2], "");
    this.X = paramJSONObject.optString(ab[5], "");
    this.Z = paramJSONObject.optString(ab[1], "");
    this.Y = paramJSONObject.optString(ab[4], "");
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.y
 * JD-Core Version:    0.6.2
 */