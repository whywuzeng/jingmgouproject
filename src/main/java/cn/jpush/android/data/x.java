package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.m;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.a;
import org.json.JSONObject;

public final class x extends l
{
  private static final String[] bb;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "'\b`nJ.\027|";
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
          i = 57;
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
        localObject2 = "\023\026`_V1\026+BQ)\017+\\V\"\035+\034\031";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        bb = arrayOfString;
        return;
        i = 70;
        break label99;
        i = 120;
        break label99;
        i = 11;
        break label99;
        i = 49;
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

  public x()
  {
    this.o = 3;
    this.ad = null;
  }

  public final void a(Context paramContext)
  {
    cn.jpush.android.util.x.b();
    if (p.a(this.Y, this.Z, paramContext))
    {
      ServiceInterface.a(paramContext, this);
      return;
    }
    if (this.ab == 1)
    {
      paramContext.startActivity(a.a(paramContext, this, true));
      return;
    }
    if (this.ab == 0)
    {
      m.a(paramContext, this, true, true);
      return;
    }
    new StringBuilder(bb[1]).append(this.ab).toString();
    cn.jpush.android.util.x.c();
  }

  public final boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    cn.jpush.android.util.x.b();
    boolean bool = super.a(paramContext, paramJSONObject);
    this.ab = paramJSONObject.optInt(bb[0], 0);
    return bool;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.x
 * JD-Core Version:    0.6.2
 */