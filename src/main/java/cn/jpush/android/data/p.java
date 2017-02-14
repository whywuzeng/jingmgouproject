package cn.jpush.android.data;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ai;
import org.json.JSONException;
import org.json.JSONObject;

public final class p
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[29];
    int j = 0;
    Object localObject2 = "!B\\w>\fx~O{\027c";
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
        i = 30;
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
        localObject2 = "\016sOO";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "!X0vM(^T";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\002HsTp\033r~O";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\034LA\033n`^";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "\016sORz";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "\016tdRq\001-`Zl\034r_Iw\b~~Zr\"dwv{\034dq\\{O:0Tl\006pyU\003]cTpU\035";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\002dwdw\013";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = ":y{Uq\0307}HyOciK{O:0";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "8^Vr";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "&yfZr\006s0Nl\0037=\033";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "1LxOj\037kxOj\037dM\020$@8>\021";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "\001xdRx\006tqRq\001HdBn\n";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "\033n`^";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "\002rcH\br";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "\001HUr\026";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "\033~dW{";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "\016tdRq\001-`I{?vbH{ ey\\w\001v|vm\bZuHm\016pu\0333OxbRy\006yqWT\034x~\001\024";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = "\fx~O{\001cOOg\037r";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = "\nodI\034";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        j = 22;
        localObject2 = "!XOvM(^T";
        i = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i4] = localObject1;
        j = 23;
        localObject2 = "\001HrNw\003suIA\006s";
        i = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i4] = localObject1;
        j = 24;
        localObject2 = "\032e|";
        i = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i4] = localObject1;
        j = 25;
        localObject2 = "\035~sSA\002rtR";
        i = 24;
        localObject1 = arrayOfString;
        break;
      case 24:
        localObject3[i4] = localObject1;
        j = 26;
        localObject2 = "\037euk\035dutl\006pyU\003Zc\\S\ndcZy\n7}Hy&s0\006>";
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
        j = 28;
        localObject2 = ":y{Uq\030y0vM(7`Iq\033xsTrOauIm\006x~\025>(~f^>\032g0\026>";
        i = 27;
        localObject1 = arrayOfString;
        break;
      case 27:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 111;
        continue;
        i = 23;
        continue;
        i = 16;
        continue;
        i = 59;
      }
    }
  }

  public static a a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    new StringBuilder(z[19]).append(paramString1).toString();
    cn.jpush.android.util.x.c();
    if (paramContext == null)
      throw new IllegalArgumentException(z[0]);
    if (TextUtils.isEmpty(paramString1))
    {
      cn.jpush.android.util.x.f();
      ServiceInterface.a(z[3], 996, paramContext);
      return null;
    }
    JSONObject localJSONObject = a(paramContext, z[22], paramString1);
    if (localJSONObject == null)
    {
      cn.jpush.android.util.x.c();
      return null;
    }
    paramString1 = localJSONObject.optString(z[8], "");
    if (ai.a(paramString1));
    while (true)
    {
      paramString1 = paramString4;
      if (ai.a(paramString4))
        paramString1 = localJSONObject.optString(z[6], "");
      new StringBuilder(z[26]).append(paramString1).toString();
      cn.jpush.android.util.x.c();
      boolean bool;
      if (localJSONObject.optInt(z[16], 0) == 1)
      {
        bool = true;
        if (!bool)
          break label445;
      }
      label445: for (int i = localJSONObject.optInt(z[23], 0); ; i = 0)
      {
        paramString4 = new a();
        paramString4.c = paramString1;
        paramString4.a = localJSONObject;
        paramString4.b = localJSONObject.optInt(z[5], 3);
        paramString4.e = bool;
        paramString4.f = i;
        paramString4.g = localJSONObject.optInt(z[13], 0);
        paramString4.i = localJSONObject.optString(z[15], "");
        paramString4.j = localJSONObject.optString(z[20], "");
        paramString4.k = localJSONObject.optString(z[17], "");
        paramString4.l = localJSONObject.optString(z[21], "");
        paramString4.m = paramString2;
        paramString4.n = paramString3;
        paramString4.d = localJSONObject.optString(z[18], "");
        if (!ai.a(paramString4.i))
        {
          paramContext = a(paramContext, paramString1, localJSONObject, z[25]);
          if (paramContext != null)
          {
            paramString4.a(paramContext.optString(z[24], ""));
            paramString4.T = paramContext.optString(z[14], "");
            paramString4.a(true);
          }
        }
        return paramString4;
        bool = false;
        break;
      }
      paramString4 = paramString1;
    }
  }

  private static JSONObject a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString2 = new JSONObject(paramString2);
      return paramString2;
    }
    catch (JSONException paramString2)
    {
      cn.jpush.android.util.x.j();
      ServiceInterface.a(paramString1, 996, paramContext);
    }
    return null;
  }

  public static JSONObject a(Context paramContext, String paramString1, JSONObject paramJSONObject, String paramString2)
  {
    if (paramJSONObject == null)
    {
      cn.jpush.android.util.x.e();
      ServiceInterface.a(paramString1, 996, paramContext);
    }
    while (true)
    {
      return null;
      if (TextUtils.isEmpty(paramString2))
      {
        cn.jpush.android.util.x.e();
        return null;
      }
      try
      {
        if (!paramJSONObject.isNull(paramString2))
        {
          paramJSONObject = paramJSONObject.getJSONObject(paramString2);
          return paramJSONObject;
        }
      }
      catch (JSONException paramJSONObject)
      {
        cn.jpush.android.util.x.j();
        ServiceInterface.a(paramString1, 996, paramContext);
      }
    }
    return null;
  }

  public static void a(Context paramContext, a parama)
  {
    cn.jpush.android.util.x.b();
    if (paramContext == null)
      throw new IllegalArgumentException(z[0]);
    int i = parama.b;
    Object localObject = parama.a;
    String str = parama.c;
    if (i == 2)
    {
      parama = ((JSONObject)localObject).optString(z[4], "").trim();
      if (a(parama))
      {
        b(paramContext, parama, str);
        return;
      }
      new StringBuilder(z[2]).append(parama).toString();
      cn.jpush.android.util.x.c();
      ServiceInterface.a(str, 996, paramContext);
      return;
    }
    JSONObject localJSONObject;
    if ((i == 1) || (i == 3) || (i == 4))
    {
      localJSONObject = a(paramContext, str, (JSONObject)localObject, z[4]);
      if (localJSONObject == null)
        cn.jpush.android.util.x.c();
    }
    else
    {
      new StringBuilder(z[28]).append(i).toString();
      cn.jpush.android.util.x.c();
      ServiceInterface.a(str, 996, paramContext);
      return;
    }
    int j = localJSONObject.optInt(z[1], -1);
    switch (j)
    {
    default:
      new StringBuilder(z[9]).append(j).toString();
      cn.jpush.android.util.x.e();
      ServiceInterface.a(str, 996, paramContext);
      return;
    case 0:
      localObject = new s();
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      ((d)localObject).c = str;
      ((d)localObject).b = i;
      ((d)localObject).o = j;
      ((d)localObject).h = parama.h;
      ((d)localObject).e = parama.e;
      ((d)localObject).f = parama.f;
      ((d)localObject).m = parama.m;
      ((d)localObject).d = parama.d;
      ((d)localObject).g = parama.g;
      boolean bool = ((d)localObject).b(paramContext, localJSONObject);
      cn.jpush.android.util.x.b();
      if (!bool)
        break;
      ((d)localObject).a(paramContext);
      cn.jpush.android.util.x.b();
      return;
      localObject = new l();
      continue;
      localObject = new y();
      continue;
      localObject = new x();
    }
    cn.jpush.android.util.x.e();
  }

  public static void a(Context paramContext, String paramString)
  {
    new StringBuilder(z[7]).append(paramString).toString();
    cn.jpush.android.util.x.b();
    if (paramContext == null)
      throw new IllegalArgumentException(z[0]);
    if (TextUtils.isEmpty(paramString))
      cn.jpush.android.util.x.f();
    while (true)
    {
      return;
      JSONObject localJSONObject = a(paramContext, z[3], paramString);
      if (localJSONObject != null)
      {
        paramString = localJSONObject.optString(z[8], "");
        String str = paramString;
        if (ai.a(paramString))
          str = localJSONObject.optString(z[6], "");
        int i = localJSONObject.optInt(z[5], -1);
        if (i == 2)
        {
          paramString = localJSONObject.optString(z[4], "").trim();
          if (a(paramString))
          {
            b(paramContext, paramString, str);
            return;
          }
          new StringBuilder(z[2]).append(paramString).toString();
          cn.jpush.android.util.x.c();
          ServiceInterface.a(str, 996, paramContext);
          return;
        }
        if (i == 1);
        for (localJSONObject = a(paramContext, str, localJSONObject, z[4]); localJSONObject != null; localJSONObject = null)
        {
          int j = localJSONObject.optInt(z[1], -1);
          switch (j)
          {
          default:
            new StringBuilder(z[9]).append(j).toString();
            cn.jpush.android.util.x.e();
            ServiceInterface.a(str, 996, paramContext);
            return;
          case 0:
            paramString = new s();
          case 1:
          case 2:
          case 3:
          }
          while (true)
          {
            boolean bool = paramString.b(paramContext, localJSONObject);
            cn.jpush.android.util.x.b();
            paramString.c = str;
            paramString.b = i;
            paramString.o = j;
            if (!bool)
              break;
            paramString.a(paramContext);
            cn.jpush.android.util.x.b();
            return;
            paramString = new l();
            continue;
            paramString = new y();
            continue;
            paramString = new x();
          }
          cn.jpush.android.util.x.e();
          return;
        }
      }
    }
  }

  public static boolean a(String paramString)
  {
    boolean bool1;
    if (TextUtils.isEmpty(paramString))
      bool1 = false;
    boolean bool2;
    do
    {
      return bool1;
      paramString = paramString.trim();
      bool2 = paramString.matches(z[12]);
      bool1 = bool2;
    }
    while (bool2);
    new StringBuilder(z[11]).append(paramString).toString();
    cn.jpush.android.util.x.e();
    return bool2;
  }

  public static boolean a(boolean paramBoolean, int paramInt, Context paramContext)
  {
    boolean bool = z[10].equalsIgnoreCase(cn.jpush.android.util.a.e(paramContext));
    return ((paramBoolean) && (paramInt == 0)) || ((paramBoolean) && (paramInt == 1) && (bool));
  }

  private static void b(Context paramContext, String paramString1, String paramString2)
  {
    new StringBuilder(z[27]).append(paramString1).toString();
    cn.jpush.android.util.x.b();
    if (paramContext == null)
      throw new IllegalArgumentException(z[0]);
    new q(paramString1, paramContext, paramString2).start();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.p
 * JD-Core Version:    0.6.2
 */