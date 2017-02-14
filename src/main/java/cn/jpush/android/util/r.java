package cn.jpush.android.util;

import android.content.Context;
import cn.jpush.android.data.f;
import cn.jpush.android.service.PushService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class r
{
  private static final String[] z;
  protected int a = 0;
  protected boolean b = false;
  private int c = 2;
  private int d = 0;
  private long e = 0L;
  private long f = 0L;
  private long g = 0L;
  private long h = 0L;
  private Context i;
  private ArrayList<f> j = new ArrayList();

  static
  {
    String[] arrayOfString = new String[20];
    int m = 0;
    Object localObject2 = "&\004\022[";
    int k = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int n = arrayOfChar.length;
    int i3 = 0;
    int i1 = 0;
    int i5 = k;
    localObject2 = arrayOfChar;
    int i6 = m;
    Object localObject3 = localObject1;
    int i2 = n;
    Object localObject4;
    int i4;
    if (n <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i4 = k;
      label68: i2 = i1;
      label71: localObject2 = localObject1;
      i3 = localObject2[i1];
      switch (i2 % 5)
      {
      default:
        k = 90;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i1] = ((char)(k ^ i3));
      i2 += 1;
      if (n == 0)
      {
        i1 = n;
        break label71;
      }
      i3 = i2;
      i2 = n;
      localObject3 = localObject4;
      i6 = m;
      localObject2 = localObject1;
      i5 = i4;
      i4 = i5;
      localObject1 = localObject2;
      m = i6;
      localObject4 = localObject3;
      n = i2;
      i1 = i3;
      if (i2 > i3)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i5)
      {
      default:
        localObject3[i6] = localObject1;
        m = 1;
        localObject2 = "1\022\fJ?<\t";
        k = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i6] = localObject1;
        m = 2;
        localObject2 = ";\t\013S?";
        k = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i6] = localObject1;
        m = 3;
        localObject2 = "8\r\027M2\r\021\rY=7\017";
        k = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i6] = localObject1;
        m = 4;
        localObject2 = "刻伤斔闊｀";
        k = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i6] = localObject1;
        m = 5;
        localObject2 = "\0301\rY=7\0170[*=\017\026v?>\r\007L";
        k = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i6] = localObject1;
        m = 6;
        localObject2 = "斷循奅就｀";
        k = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i6] = localObject1;
        m = 7;
        localObject2 = "斷循笫纙｀";
        k = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i6] = localObject1;
        m = 8;
        localObject2 = "?\022\006[";
        k = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i6] = localObject1;
        m = 9;
        localObject2 = "\"\030\020W56";
        k = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i6] = localObject1;
        m = 10;
        localObject2 = "乘拘吊朡｀";
        k = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i6] = localObject1;
        m = 11;
        localObject2 = "\"\034\001U?&";
        k = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i6] = localObject1;
        m = 12;
        localObject2 = "覱柭奓贛｀";
        k = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i6] = localObject1;
        m = 13;
        localObject2 = "乘拘橃弱｀";
        k = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i6] = localObject1;
        m = 14;
        localObject2 = "麊诙卧夙展ｈLR\fnb";
        k = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i6] = localObject1;
        m = 15;
        localObject2 = "归妶要枮t|S";
        k = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i6] = localObject1;
        m = 16;
        localObject2 = "覱柭扲务";
        k = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i6] = localObject1;
        m = 17;
        localObject2 = "敤前杯功乑厃捺了Ｄ";
        k = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i6] = localObject1;
        m = 18;
        localObject2 = ">\030\024[6!";
        k = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i6] = localObject1;
        m = 19;
        localObject2 = "&\024\017[";
        k = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i6] = localObject1;
        z = arrayOfString;
        return;
        k = 82;
        continue;
        k = 125;
        continue;
        k = 98;
        continue;
        k = 62;
      }
    }
  }

  private void a(String paramString)
  {
    int k = paramString.length();
    while (k > 0)
    {
      k -= 1;
      switch (paramString.charAt(k))
      {
      default:
        break;
      case 'd':
        this.a |= 2;
        break;
      case 'v':
        this.a |= 1;
        break;
      case 'i':
        this.a |= 4;
        break;
      case 'w':
        this.a |= 8;
        break;
      case 'e':
        this.a |= 16;
      }
    }
  }

  private void b()
  {
    JSONObject localJSONObject = d();
    if (localJSONObject != null)
    {
      x.b(z[5], localJSONObject.toString());
      ac.b(this.i, localJSONObject);
    }
  }

  private void c()
  {
    this.b = false;
    this.d = 0;
    this.h = 0L;
    this.e = 0L;
    this.f = 0L;
    this.a = 0;
    this.g = 0L;
    this.c = 2;
    this.i = null;
    this.j.clear();
  }

  private JSONObject d()
  {
    if (this.j == null)
      return null;
    int m = this.j.size();
    if (m <= 0)
      return null;
    JSONArray localJSONArray = new JSONArray();
    int k = 0;
    while (k < m)
    {
      localJSONArray.put(((f)this.j.get(k)).b());
      k += 1;
    }
    if (localJSONArray.length() <= 0)
      return null;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(z[1], localJSONArray);
      localJSONObject.put(z[0], z[3]);
      localJSONObject.put(z[2], PushService.m + Math.abs(System.currentTimeMillis() / 1000L - PushService.n));
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  protected final void a()
  {
    if (this.b)
    {
      if (System.currentTimeMillis() - this.g < this.h)
        break label32;
      b();
      c();
    }
    label32: 
    while ((this.c != 1) || (System.currentTimeMillis() - this.f < this.e))
      return;
    synchronized (this.j)
    {
      b();
      this.j.clear();
      this.d = 0;
      this.f = System.currentTimeMillis();
      return;
    }
  }

  protected final void a(Context paramContext, String paramString)
  {
    x.b(z[5], z[17] + paramString);
    x.b(z[5], z[15]);
    while (true)
    {
      try
      {
        if (this.b)
        {
          this.b = false;
          b();
          c();
        }
        this.i = paramContext;
        paramContext = new JSONObject(paramString);
        paramString = paramContext.getString(z[8]);
        String str = paramContext.getString(z[18]);
        if (paramString != null)
        {
          if (paramString.equals(z[11]))
            this.c = 2;
        }
        else
        {
          a(str);
          this.h = (paramContext.getLong(z[19]) * 1000L);
          x.b(z[5], z[13] + paramString);
          x.b(z[5], z[7] + str);
          if (this.c != 1)
            break label373;
          this.e = (paramContext.getLong(z[9]) * 1000L);
          this.f = System.currentTimeMillis();
          x.b(z[5], z[10] + this.e / 1000L + "s");
          if (this.h < 300000L)
            this.c = 2;
          this.g = System.currentTimeMillis();
          this.b = true;
          x.b(z[5], z[16]);
          return;
        }
        if (!paramString.equals(z[9]))
          continue;
        this.c = 1;
        continue;
      }
      catch (JSONException paramContext)
      {
        c();
        x.b(z[5], z[12] + paramContext.getMessage());
        return;
      }
      label373: x.b(z[5], z[14]);
    }
  }

  protected final void a(f paramf)
  {
    if (this.b)
      while (true)
      {
        synchronized (this.j)
        {
          this.d += paramf.a();
          x.b(z[5], z[6] + paramf.a());
          x.b(z[5], z[4] + (this.h - (System.currentTimeMillis() - this.g)) / 1000L + "s");
          switch (this.c)
          {
          case 1:
            return;
            if (System.currentTimeMillis() - this.g >= this.h)
            {
              b();
              c();
            }
            break;
          case 2:
          }
        }
        if (System.currentTimeMillis() - this.f > this.e);
        synchronized (this.j)
        {
          b();
          this.j.clear();
          this.d = 0;
          this.f = System.currentTimeMillis();
          this.j.add(paramf);
        }
        b();
        c();
        continue;
        if (this.d >= 10240L)
        {
          b();
          this.j.clear();
          this.d = paramf.a();
        }
        this.j.add(paramf);
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.r
 * JD-Core Version:    0.6.2
 */