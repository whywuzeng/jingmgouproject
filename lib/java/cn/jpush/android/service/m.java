package cn.jpush.android.service;

import android.content.Context;
import cn.jpush.android.a.d;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class m extends d
{
  private static final String[] A;

  static
  {
    String[] arrayOfString = new String[17];
    int j = 0;
    Object localObject2 = "k8)\\";
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
        i = 67;
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
        localObject2 = "q?)U&";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "{.,T";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "{.,T\034l$7]1k";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "T$#Y7q$.\002c";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = ";3";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "y',";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "l20]";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "o\"&Q";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "o\"&Q\034l$7]1k";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "t$#g*v-/";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "t$#g }',";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "t$#g4q-)";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "O\"&Qct.._7pq`";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "t$#Y/G/.K";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "v.4O,j \037L:h.";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "t$#g$h8";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        A = arrayOfString;
        return;
        i = 24;
        continue;
        i = 75;
        continue;
        i = 64;
        continue;
        i = 56;
      }
    }
  }

  m(PushService paramPushService, Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext, paramString, paramBoolean1, paramBoolean2);
  }

  private static boolean a(JSONArray paramJSONArray1, JSONArray paramJSONArray2, String paramString)
  {
    if (ai.a(paramString))
    {
      if (!ai.a(PushService.j()))
        return false;
    }
    else if (!paramString.equals(PushService.j()))
      return false;
    if (ai.a(PushService.k()))
    {
      if ((paramJSONArray2 != null) && (paramJSONArray2.length() != 0))
        return false;
    }
    else
    {
      if (paramJSONArray2 == null)
        return false;
      if (paramJSONArray2.length() == 0)
        return false;
      if (!PushService.k().equals(paramJSONArray2.toString()))
        return false;
      x.d();
    }
    if (ai.a(PushService.l()))
    {
      if ((paramJSONArray1 != null) && (paramJSONArray1.length() != 0))
        return false;
    }
    else
    {
      if (paramJSONArray1 == null)
        return false;
      if (paramJSONArray1.length() == 0)
        return false;
      try
      {
        paramJSONArray1 = ((JSONObject)paramJSONArray1.get(0)).optString(A[0]);
        if (!ai.a(paramJSONArray1))
        {
          boolean bool = paramJSONArray1.equals(PushService.l());
          if (!bool)
            return false;
        }
      }
      catch (Exception paramJSONArray1)
      {
        return false;
      }
    }
    return true;
  }

  public final void d()
  {
    boolean bool;
    JSONArray localJSONArray2;
    JSONArray localJSONArray3;
    JSONArray localJSONArray1;
    String str;
    while (true)
    {
      try
      {
        bool = PushService.m();
        if (!bool)
          return;
        Object localObject1;
        if (this.a.equals(A[3]))
        {
          if (cn.jpush.android.a.j)
          {
            localObject1 = b();
            localObject1 = cn.jpush.android.util.a.a(A[11], (JSONArray)localObject1);
            if ((localObject1 != null) && (((JSONObject)localObject1).length() > 0))
            {
              ac.a(this.e.getApplicationContext(), (JSONObject)localObject1);
              new StringBuilder(A[4]).append(localObject1).toString();
              x.d();
            }
          }
          return;
        }
        if (this.a.equals(A[9]))
        {
          if (!cn.jpush.android.a.j)
            continue;
          localObject1 = c();
          localObject1 = cn.jpush.android.util.a.a(A[12], (JSONArray)localObject1);
          if ((localObject1 == null) || (((JSONObject)localObject1).length() <= 0))
            continue;
          ac.a(this.e.getApplicationContext(), (JSONObject)localObject1);
          new StringBuilder(A[13]).append(((JSONObject)localObject1).toString().getBytes().length).toString();
          x.d();
          new StringBuilder(A[4]).append(localObject1).toString();
          x.d();
          continue;
        }
      }
      catch (Exception localException1)
      {
        x.j();
        return;
        if (this.a.equals(A[5]))
        {
          if (!cn.jpush.android.a.j)
            continue;
          Object localObject2 = a();
          if (localObject2 == null)
            continue;
          bool = "".equals(localObject2);
          if (bool)
            continue;
          try
          {
            localObject2 = new JSONObject((String)localObject2);
            localJSONArray2 = new JSONArray();
            localJSONArray2.put(localObject2);
            localObject2 = cn.jpush.android.util.a.a(A[16], localJSONArray2);
            if ((localObject2 == null) || (((JSONObject)localObject2).length() <= 0))
              continue;
            ac.a(this.e.getApplicationContext(), (JSONObject)localObject2);
            new StringBuilder(A[4]).append(localObject2).toString();
            x.d();
          }
          catch (JSONException localJSONException1)
          {
            localJSONException1.getMessage();
            x.f();
          }
          continue;
        }
      }
      finally
      {
        g();
      }
      if ((this.a.equals(A[6])) && (cn.jpush.android.a.j))
      {
        localJSONArray2 = c();
        localJSONArray3 = b();
        localJSONArray1 = new JSONArray();
        str = a();
        if (!a(localJSONArray2, localJSONArray3, str))
          break;
        x.d();
      }
    }
    if (str != null)
    {
      bool = "".equals(str);
      if (bool);
    }
    while (true)
      try
      {
        while (true)
        {
          localJSONArray1.put(new JSONObject(str));
          JSONObject localJSONObject = new JSONObject();
          try
          {
            localJSONObject.put(A[7], A[10]);
            localJSONObject.put(A[1], PushService.m + Math.abs(System.currentTimeMillis() / 1000L - PushService.n));
            localJSONObject.put(A[15], cn.jpush.android.util.a.d(this.e.getApplicationContext()));
            localJSONObject.put(A[14], cn.jpush.android.util.a.d());
            if ((localJSONArray2 != null) && (localJSONArray2.length() > 0))
            {
              localJSONObject.put(A[8], localJSONArray2);
              PushService.e(((JSONObject)localJSONArray2.get(0)).optString(A[0]));
            }
            if ((localJSONArray3 != null) && (localJSONArray3.length() > 0))
            {
              localJSONObject.put(A[2], localJSONArray3);
              PushService.f(localJSONArray3.toString());
            }
            if ((localJSONArray1 != null) && (localJSONArray1.length() > 0))
            {
              localJSONObject.put(A[5], localJSONArray1);
              PushService.g(str);
            }
            ac.a(this.e.getApplicationContext(), localJSONObject);
          }
          catch (JSONException localJSONException2)
          {
          }
        }
      }
      catch (Exception localException2)
      {
        Object localObject4 = null;
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.m
 * JD-Core Version:    0.6.2
 */