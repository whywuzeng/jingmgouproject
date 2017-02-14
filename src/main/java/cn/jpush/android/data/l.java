package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.a;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.h;
import cn.jpush.android.util.x;
import org.json.JSONObject;

public class l extends d
{
  private static final String[] bb;
  public String W;
  public boolean X;
  public boolean Y;
  public int Z;
  public String a;
  public boolean aa;
  public int ab;
  public String ac;
  public o ad = new o(this);
  public String ae;
  public boolean af;
  public boolean ag = true;
  public String ah;
  public boolean ai;

  static
  {
    String[] arrayOfString = new String[28];
    int j = 0;
    Object localObject2 = ":`(\034N>";
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
        i = 62;
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
        localObject2 = ":O7:K";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = ":`5\b_<Z\003\020L7";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = ":J(\na6";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = ":O7:M3P+";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = ":`5\013X4";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = ":O7:M3P+:X2Q5\026V>[\003\013Q/V";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = ":J(\na)";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = ":O7:[8P2";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = ":`9\027[(";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = ":`>\027Q(H9\027";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = ":O7:S?\n";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = ":O7:_.K3:W5L(\004R7";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = ":O7:K)S";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "\004V?\nP\013^(\r";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = ":J(\na5";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = ":`(\fJ7Z";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = ":`/\006Q)Z";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = ":J(\na)\\";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = ":`/\fD>";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        j = 22;
        localObject2 = "\004V1\004Y>o=\021V";
        i = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i4] = localObject1;
        j = 23;
        localObject2 = ":`9\020L7";
        i = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i4] = localObject1;
        j = 24;
        localObject2 = "\004H9\007n:X95_/W";
        i = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i4] = localObject1;
        j = 25;
        localObject2 = ":O7:P";
        i = 24;
        localObject1 = arrayOfString;
        break;
      case 24:
        localObject3[i4] = localObject1;
        j = 26;
        localObject2 = ":`5\006Q5`)\027R";
        i = 25;
        localObject1 = arrayOfString;
        break;
      case 25:
        localObject3[i4] = localObject1;
        j = 27;
        localObject2 = "";
        i = 26;
        localObject1 = arrayOfString;
        break;
      case 26:
        localObject3[i4] = localObject1;
        bb = arrayOfString;
        return;
        i = 91;
        continue;
        i = 63;
        continue;
        i = 92;
        continue;
        i = 101;
      }
    }
  }

  public l()
  {
    this.o = 1;
  }

  public void a(Context paramContext)
  {
    x.b();
    boolean bool = a.f(paramContext, this.a);
    int j = 995;
    if ((!this.X) && (bool))
    {
      x.c();
      ServiceInterface.a(this.c, 997, paramContext);
      return;
    }
    int i = j;
    if (this.X)
    {
      i = j;
      if (bool)
      {
        x.c();
        i = 998;
      }
    }
    if (this.ab == 1)
    {
      new m(this, this, paramContext, i).start();
      return;
    }
    if (this.ab == 0)
    {
      new n(this, paramContext, i, this).start();
      return;
    }
    new StringBuilder(bb[27]).append(this.ab).toString();
    x.e();
  }

  public boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    boolean bool2 = false;
    x.b();
    this.a = paramJSONObject.optString(bb[25], "");
    if (paramJSONObject.optInt(bb[1], 0) > 0)
    {
      bool1 = true;
      this.X = bool1;
      if (paramJSONObject.optInt(bb[3], 0) <= 0)
        break label241;
      bool1 = true;
      label57: this.Y = bool1;
      this.Z = paramJSONObject.optInt(bb[17], 0);
      if (paramJSONObject.optInt(bb[8], 0) <= 0)
        break label246;
      bool1 = true;
      label93: this.aa = bool1;
      this.ab = paramJSONObject.optInt(bb[4], 1);
      this.ac = paramJSONObject.optString(bb[15], "").trim();
      this.ae = paramJSONObject.optString(bb[13], "");
      this.W = paramJSONObject.optString(bb[20], "");
      if (paramJSONObject.optInt(bb[7], 0) <= 0)
        break label251;
      bool1 = true;
      label179: this.af = bool1;
      if (paramJSONObject.optInt(bb[14], 1) != 1)
        break label256;
    }
    label256: for (boolean bool1 = true; ; bool1 = false)
    {
      this.ag = bool1;
      if (this.o != 1)
        break label588;
      paramContext = p.a(paramContext, this.c, paramJSONObject, bb[9]);
      if (paramContext != null)
        break label261;
      return false;
      bool1 = false;
      break;
      label241: bool1 = false;
      break label57;
      label246: bool1 = false;
      break label93;
      label251: bool1 = false;
      break label179;
    }
    label261: paramJSONObject = this.ad;
    x.b();
    paramJSONObject.a = paramContext.optString(bb[18], "");
    paramJSONObject.b = paramContext.optString(bb[26], "").trim();
    paramJSONObject.c = paramContext.optString(bb[10], "");
    paramJSONObject.d = paramContext.optString(bb[0], "");
    l locall;
    if (paramContext.optInt(bb[19], 0) == 0)
    {
      bool1 = true;
      paramJSONObject.e = bool1;
      paramJSONObject.f = paramContext.optString(bb[21], "");
      paramJSONObject.g = paramContext.optString(bb[6], "");
      paramJSONObject.h = paramContext.optString(bb[2], "").trim();
      paramJSONObject.j = paramContext.optString(bb[23], "").trim();
      locall = paramJSONObject.o;
      if (paramContext.optInt(bb[12], 0) != 1)
        break label595;
    }
    label588: label595: for (bool1 = true; ; bool1 = false)
    {
      locall.O = bool1;
      bool1 = bool2;
      if (paramContext.optInt(bb[5], 0) == 0)
        bool1 = true;
      paramJSONObject.i = bool1;
      if (paramJSONObject.i)
        paramJSONObject.k = h.a(paramContext.optJSONArray(bb[11]));
      if (ai.a(paramJSONObject.m))
        paramJSONObject.m = paramContext.optString(bb[22], "").trim();
      if (ai.a(paramJSONObject.l))
        paramJSONObject.l = paramContext.optString(bb[16], "").trim();
      if (ai.a(paramJSONObject.l))
        paramJSONObject.l = paramContext.optString(bb[24], "").trim();
      return true;
      bool1 = false;
      break;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.l
 * JD-Core Version:    0.6.2
 */