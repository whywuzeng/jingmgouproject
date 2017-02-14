package cn.jpush.android.data;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.k;
import cn.jpush.android.util.m;
import cn.jpush.android.util.n;
import cn.jpush.android.util.x;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class d
  implements Serializable
{
  private static final String[] W;
  public String A;
  public String B;
  public List<d> C;
  public int D = -1;
  public int E;
  public int F;
  public String G;
  public String H;
  public int I;
  public int J;
  public int K;
  public boolean L = false;
  public boolean M = false;
  public boolean N = false;
  public boolean O = false;
  public int P = -1;
  public String Q;
  public ArrayList<String> R = null;
  public String S = "";
  public String T;
  public String U;
  public String V;
  private boolean a = false;
  public int b;
  public String c;
  public String d;
  public boolean e;
  public int f;
  public int g = 0;
  public boolean h;
  public String i;
  public String j;
  public String k;
  public String l;
  public String m;
  public String n;
  public int o;
  public int p;
  public boolean q;
  public String r;
  public boolean s = false;
  public String t;
  public boolean u = false;
  public List<String> v = null;
  public boolean w;
  public int x;
  public String y;
  public int z;

  static
  {
    String[] arrayOfString = new String[32];
    int i2 = 0;
    Object localObject2 = "&l\027\031,-b\004W&#j\f\022$ｎj\r\026''V\022\033zb";
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
        i1 = 64;
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
        localObject2 = "\021v\003\024%'g@\003/bo\017\026$bj\r\026''#MW";
        i1 = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i9] = localObject1;
        i2 = 2;
        localObject2 = "/p\007()/d?\0022.";
        i1 = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i9] = localObject1;
        i2 = 3;
        localObject2 = "";
        i1 = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i9] = localObject1;
        i2 = 4;
        localObject2 = "&f\023\0344-s?\004(-t?\024/,w\005\0314";
        i1 = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i9] = localObject1;
        i2 = 5;
        localObject2 = "1z\023(6+f\027";
        i1 = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i9] = localObject1;
        i2 = 6;
        localObject2 = "d%";
        i1 = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i9] = localObject1;
        i2 = 7;
        localObject2 = "+n\001\020%\035v\022\033\037.j\023\003";
        i1 = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i9] = localObject1;
        i2 = 8;
        localObject2 = ",\\\003\030.6f\016\003";
        i1 = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i9] = localObject1;
        i2 = 9;
        localObject2 = ",\\\t\032!%f?\0022.";
        i1 = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i9] = localObject1;
        i2 = 10;
        localObject2 = "&f\023\0344-s?\004(-t?\00392f";
        i1 = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i9] = localObject1;
        i2 = 11;
        localObject2 = "#s\020(4;s\005";
        i1 = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i9] = localObject1;
        i2 = 12;
        localObject2 = "$v\f\033\0371`\022\022%,";
        i1 = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i9] = localObject1;
        i2 = 13;
        localObject2 = ",\\\024\0364.f";
        i1 = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i9] = localObject1;
        i2 = 14;
        localObject2 = ",\\\005\01740b\023";
        i1 = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i9] = localObject1;
        i2 = 15;
        localObject2 = ",\\\006\033!%";
        i1 = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i9] = localObject1;
        i2 = 16;
        localObject2 = "/b\022\020),\\\024\0300";
        i1 = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i9] = localObject1;
        i2 = 17;
        localObject2 = "#g?\024/,w\005\0314";
        i1 = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i9] = localObject1;
        i2 = 18;
        localObject2 = "#\\\002\005/1t\005\005";
        i1 = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i9] = localObject1;
        i2 = 19;
        localObject2 = "0j\016\020\037/";
        i1 = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i9] = localObject1;
        i2 = 20;
        localObject2 = "/b\022\020),\\\f\022&6";
        i1 = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i9] = localObject1;
        i2 = 21;
        localObject2 = "dv\t\023}";
        i1 = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i9] = localObject1;
        i2 = 22;
        localObject2 = "+`\017\031";
        i1 = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i9] = localObject1;
        i2 = 23;
        localObject2 = "";
        i1 = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i9] = localObject1;
        i2 = 24;
        localObject2 = "+`\017\031\0377q\f";
        i1 = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i9] = localObject1;
        i2 = 25;
        localObject2 = "#o\f\0307\035`\001\031#'o";
        i1 = 24;
        localObject1 = arrayOfString;
        break;
      case 24:
        localObject3[i9] = localObject1;
        i2 = 26;
        localObject2 = "";
        i1 = 25;
        localObject1 = arrayOfString;
        break;
      case 25:
        localObject3[i9] = localObject1;
        i2 = 27;
        localObject2 = "#`\024\036/,9\f\030!&K\024\032,\013n\001\020%\020f\023\03050`\005\004`o#\025\005,\022q\005\021):9";
        i1 = 26;
        localObject1 = arrayOfString;
        break;
      case 26:
        localObject3[i9] = localObject1;
        i2 = 28;
        localObject2 = "\007m\024\0364;";
        i1 = 27;
        localObject1 = arrayOfString;
        break;
      case 27:
        localObject3[i9] = localObject1;
        i2 = 29;
        localObject2 = "*w\024\007zm,";
        i1 = 28;
        localObject1 = arrayOfString;
        break;
      case 28:
        localObject3[i9] = localObject1;
        i2 = 30;
        localObject2 = "\025q\t\003%bp\024\0302#d\005W%0q\017\005lb#\003\005%#w\005W)/d@\021).f@\021!+oN";
        i1 = 29;
        localObject1 = arrayOfString;
        break;
      case 29:
        localObject3[i9] = localObject1;
        i2 = 31;
        localObject2 = "#`\024\036/,9\f\030!&J\r\020\022'p@Z`7q\fM";
        i1 = 30;
        localObject1 = arrayOfString;
        break;
      case 30:
        localObject3[i9] = localObject1;
        W = arrayOfString;
        return;
        i1 = 66;
        continue;
        i1 = 3;
        continue;
        i1 = 96;
        continue;
        i1 = 119;
      }
    }
  }

  public static d a(Context paramContext, d paramd)
  {
    String str;
    int i1;
    if ((paramd.v != null) && (paramd.v.size() > 0))
    {
      paramd.R = new ArrayList();
      Iterator localIterator = paramd.v.iterator();
      while (true)
        if (localIterator.hasNext())
        {
          str = (String)localIterator.next();
          if (!ai.a(str))
          {
            byte[] arrayOfByte = n.a(str, 5, 5000L, 4);
            if (arrayOfByte != null)
              try
              {
                str = str.substring(str.lastIndexOf("/") + 1);
                str = k.a(paramContext, paramd.c) + str;
                if (m.a(str, arrayOfByte, paramContext))
                {
                  paramd.R.add(str);
                  new StringBuilder(W[1]).append(str).toString();
                  x.b();
                }
              }
              catch (IOException localIOException)
              {
                x.h();
                i1 = 0;
              }
          }
        }
    }
    while (i1 != 0)
    {
      x.d();
      return paramd;
      x.f();
      i1 = 0;
      continue;
      new StringBuilder(W[0]).append(str).toString();
      x.e();
      ServiceInterface.a(paramd.c, 1020, cn.jpush.android.util.a.b(paramContext, str), paramContext);
      i1 = 0;
      continue;
      x.f();
      i1 = 0;
      continue;
      i1 = 1;
      continue;
      x.f();
      i1 = 0;
    }
    x.e();
    ServiceInterface.a(paramd.c, 1014, paramContext);
    return null;
  }

  static String a(String paramString1, String paramString2, String paramString3, Context paramContext)
  {
    new StringBuilder(W[31]).append(paramString1).toString();
    x.b();
    if ((p.a(paramString1)) && (paramContext != null) && (!TextUtils.isEmpty(paramString2)) && (!TextUtils.isEmpty(paramString3)))
    {
      byte[] arrayOfByte = n.a(paramString1, 5, 5000L, 4);
      if (arrayOfByte != null)
        try
        {
          paramString1 = k.a(paramContext, paramString2) + paramString3;
          m.a(paramString1, arrayOfByte, paramContext);
          new StringBuilder(W[1]).append(paramString1).toString();
          x.b();
          return paramString1;
        }
        catch (IOException paramString1)
        {
          x.j();
          return "";
        }
      ServiceInterface.a(paramString2, 1020, cn.jpush.android.util.a.b(paramContext, paramString1), paramContext);
    }
    return "";
  }

  static boolean a(ArrayList<String> paramArrayList, Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    boolean bool2 = true;
    new StringBuilder(W[27]).append(paramString1).toString();
    x.b();
    boolean bool1 = bool2;
    if (p.a(paramString1))
    {
      bool1 = bool2;
      if (paramContext != null)
      {
        bool1 = bool2;
        if (paramArrayList.size() > 0)
        {
          bool1 = bool2;
          if (!TextUtils.isEmpty(paramString2))
          {
            Iterator localIterator = paramArrayList.iterator();
            bool1 = true;
            if (localIterator.hasNext())
            {
              paramArrayList = (String)localIterator.next();
              if ((paramArrayList == null) || (paramArrayList.startsWith(W[29])))
                break label310;
            }
          }
        }
      }
    }
    label260: label310: for (Object localObject = paramString1 + paramArrayList; ; localObject = paramArrayList)
    {
      byte[] arrayOfByte = n.a((String)localObject, 5, 5000L, 4);
      if (arrayOfByte != null)
      {
        localObject = paramArrayList;
        while (true)
        {
          try
          {
            if (paramArrayList.startsWith(W[29]))
              localObject = m.c(paramArrayList);
            if (paramBoolean)
              break label260;
            paramArrayList = k.a(paramContext, paramString2) + (String)localObject;
            m.a(paramArrayList, arrayOfByte, paramContext);
            new StringBuilder(W[1]).append(paramArrayList).toString();
            x.b();
          }
          catch (IOException paramArrayList)
          {
            x.a(W[28], W[30], paramArrayList);
            bool1 = false;
          }
          break;
          paramArrayList = k.b(paramContext, paramString2) + (String)localObject;
        }
      }
      ServiceInterface.a(paramString2, 1020, cn.jpush.android.util.a.b(paramContext, (String)localObject), paramContext);
      bool1 = false;
      break;
      return bool1;
    }
  }

  public abstract void a(Context paramContext);

  public final void a(String paramString)
  {
    this.V = paramString;
  }

  public final void a(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  public final boolean a()
  {
    return (this.o == 3) || (this.o == 1);
  }

  protected abstract boolean a(Context paramContext, JSONObject paramJSONObject);

  public final boolean b()
  {
    return this.o == 2;
  }

  public final boolean b(Context paramContext, JSONObject paramJSONObject)
  {
    x.b();
    this.p = paramJSONObject.optInt(W[19], 3);
    if (paramJSONObject.optInt(W[12], 0) > 0)
    {
      bool = true;
      this.q = bool;
      if (paramJSONObject.optInt(W[18], 0) <= 0)
        break label615;
    }
    label547: label615: for (boolean bool = true; ; bool = false)
    {
      this.O = bool;
      this.z = paramJSONObject.optInt(W[15], 0);
      this.x = paramJSONObject.optInt(W[22], 1);
      this.y = paramJSONObject.optString(W[24], "");
      this.A = paramJSONObject.optString(W[13], "");
      this.B = paramJSONObject.optString(W[8], "");
      this.r = paramJSONObject.optString(W[9]);
      this.S = paramJSONObject.optString(W[2], "");
      this.l = paramJSONObject.optString(W[14], "");
      JSONObject localJSONObject = paramJSONObject.optJSONObject(W[4]);
      if (localJSONObject != null)
      {
        x.d();
        this.D = localJSONObject.optInt(W[10], -1);
        this.E = localJSONObject.optInt(W[23], -1);
        this.F = localJSONObject.optInt(W[3], -1);
        this.G = localJSONObject.optString(W[26]);
        this.I = localJSONObject.optInt(W[16]);
        this.J = localJSONObject.optInt(W[20], -2147483648);
        this.K = localJSONObject.optInt(W[11], 0);
      }
      if (!ai.a(this.S))
        this.B = this.B.replaceAll(W[6], W[21] + cn.jpush.android.util.a.r(paramContext));
      localJSONObject = paramJSONObject.optJSONObject(W[5]);
      if (localJSONObject != null)
        x.d();
      do
      {
        try
        {
          this.t = localJSONObject.toString();
          JSONArray localJSONArray = localJSONObject.getJSONArray(W[7]);
          int i2 = localJSONArray.length();
          this.v = new LinkedList();
          int i1 = 0;
          while (i1 < i2)
          {
            this.v.add(localJSONArray.getString(i1));
            i1 += 1;
          }
          if (localJSONObject.optInt(W[25], 1) > 0);
          for (bool = true; ; bool = false)
          {
            this.w = bool;
            if (!ai.a(this.A))
              break label547;
            if (this.h)
              break;
            x.c();
            ServiceInterface.a(this.c, 996, paramContext);
            return false;
          }
        }
        catch (Exception paramJSONObject)
        {
          x.h();
          ServiceInterface.a(this.c, 996, paramContext);
          return false;
        }
        x.c();
        this.A = cn.jpush.android.a.c;
        paramJSONObject = p.a(paramContext, this.c, paramJSONObject, W[17]);
        if (paramJSONObject != null)
          break;
      }
      while ((!this.h) || (!this.e));
      return true;
      if ((this.h) && (this.e))
        this.a = true;
      return a(paramContext, paramJSONObject);
      bool = false;
      break;
    }
  }

  public final boolean c()
  {
    return this.o == 3;
  }

  public final String d()
  {
    if (a())
      return ((l)this).ac;
    if (b())
      return ((y)this).W;
    if (this.a)
      return this.V;
    return "";
  }

  public final String e()
  {
    if (a())
      return ((l)this).ah;
    if (b())
      return ((y)this).aa;
    return "";
  }

  public final boolean f()
  {
    return this.a;
  }

  public final String g()
  {
    Object localObject2 = null;
    String str;
    Object localObject1;
    if (a())
    {
      str = ((l)this).ae;
      localObject1 = localObject2;
      if (str != null)
      {
        localObject1 = localObject2;
        if (!"".equals(str))
          localObject1 = str.trim();
      }
    }
    do
    {
      do
      {
        return localObject1;
        if (!b())
          break;
        str = ((y)this).Z;
        localObject1 = localObject2;
      }
      while (str == null);
      localObject1 = localObject2;
    }
    while ("".equals(str));
    return str.trim();
    return "";
  }

  public final e h()
  {
    return new e(this, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.d
 * JD-Core Version:    0.6.2
 */