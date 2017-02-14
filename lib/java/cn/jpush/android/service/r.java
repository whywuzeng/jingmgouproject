package cn.jpush.android.service;

import android.text.TextUtils;
import cn.jpush.android.util.x;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class r
{
  private static final HashMap<Integer, String> a;
  private static final HashMap<Integer, String> b;
  private static long c = 0L;
  private static final String[] z;

  static
  {
    Object localObject2 = new String[8];
    int j = 0;
    Object localObject3 = "\022]2g\0160]y{\0047\\+}A$\\=lAj\023";
    int i = -1;
    Object localObject1 = localObject2;
    Object localObject6;
    int k;
    int i1;
    int m;
    int i4;
    Object localObject4;
    int n;
    Object localObject5;
    int i2;
    while (true)
    {
      localObject6 = ((String)localObject3).toCharArray();
      k = localObject6.length;
      i1 = 0;
      m = 0;
      int i3 = i;
      localObject3 = localObject6;
      i4 = j;
      localObject4 = localObject1;
      n = k;
      if (k <= 1)
      {
        localObject5 = localObject1;
        localObject1 = localObject6;
        i2 = i;
      }
      label143: 
      do
      {
        n = m;
        while (true)
        {
          localObject3 = localObject1;
          i1 = localObject3[m];
          switch (n % 5)
          {
          default:
            i = 97;
            localObject3[m] = ((char)(i ^ i1));
            n += 1;
            if (k != 0)
              break label143;
            m = k;
          case 0:
          case 1:
          case 2:
          case 3:
          }
        }
        i1 = n;
        n = k;
        localObject4 = localObject5;
        i4 = j;
        localObject3 = localObject1;
        i3 = i2;
        i2 = i3;
        localObject1 = localObject3;
        j = i4;
        localObject5 = localObject4;
        k = n;
        m = i1;
      }
      while (n > i1);
      localObject1 = new String((char[])localObject3).intern();
      switch (i3)
      {
      default:
        localObject4[i4] = localObject1;
        j = 1;
        localObject3 = "5^/";
        i = 0;
        localObject1 = localObject2;
        break;
      case 0:
        localObject4[i4] = localObject1;
        j = 2;
        localObject3 = "3J)l";
        i = 1;
        localObject1 = localObject2;
        break;
      case 1:
        localObject4[i4] = localObject1;
        j = 3;
        localObject3 = "&C)`\005";
        i = 2;
        localObject1 = localObject2;
        break;
      case 2:
        localObject4[i4] = localObject1;
        j = 4;
        localObject3 = "&P-`\016)";
        i = 3;
        localObject1 = localObject2;
        break;
      case 3:
        localObject4[i4] = localObject1;
        j = 5;
        localObject3 = "";
        i = 4;
        localObject1 = localObject2;
        break;
      case 4:
        localObject4[i4] = localObject1;
        j = 6;
        localObject3 = "\022]2g\0160]yl\0235\\+)\002(W<)Lg";
        i = 5;
        localObject1 = localObject2;
        break;
      case 5:
        localObject4[i4] = localObject1;
        j = 7;
        localObject3 = "&W=";
        i = 6;
        localObject1 = localObject2;
      case 6:
      }
    }
    localObject4[i4] = localObject1;
    z = (String[])localObject2;
    localObject2 = new HashMap();
    a = (HashMap)localObject2;
    localObject1 = Integer.valueOf(0);
    localObject3 = "\bx";
    i = -1;
    while (true)
    {
      char[] arrayOfChar = ((String)localObject3).toCharArray();
      j = arrayOfChar.length;
      n = 0;
      k = 0;
      i2 = i;
      localObject3 = arrayOfChar;
      localObject4 = localObject1;
      localObject5 = localObject2;
      m = j;
      if (j <= 1)
      {
        localObject6 = localObject1;
        localObject1 = arrayOfChar;
        i1 = i;
      }
      label498: 
      do
      {
        m = k;
        localObject3 = localObject1;
        n = localObject3[k];
        switch (m % 5)
        {
        default:
          i = 97;
        case 0:
        case 1:
        case 2:
        case 3:
        }
        while (true)
        {
          localObject3[k] = ((char)(i ^ n));
          m += 1;
          if (j != 0)
            break label613;
          k = j;
          break label498;
          i = 71;
          break;
          i = 51;
          break;
          i = 89;
          break;
          i = 9;
          break;
          i = 71;
          continue;
          i = 51;
          continue;
          i = 89;
          continue;
          i = 9;
        }
        n = m;
        m = j;
        localObject5 = localObject2;
        localObject4 = localObject6;
        localObject3 = localObject1;
        i2 = i1;
        i1 = i2;
        localObject1 = localObject3;
        localObject6 = localObject4;
        localObject2 = localObject5;
        j = m;
        k = n;
      }
      while (m > n);
      label613: localObject1 = new String((char[])localObject3).intern();
      switch (i2)
      {
      default:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(-1001);
        localObject3 = "\002K:l\004#\023;|\007!V+)\022.I<'A\027_<h\022\"\023:f\0173R:}A4F)y\0165Gw";
        i = 0;
        break;
      case 0:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(-1000);
        localObject3 = "";
        i = 1;
        break;
      case 1:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(-998);
        localObject3 = "";
        i = 2;
        break;
      case 2:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(-997);
        localObject3 = "\025V:l\b1Z7nA!R0e\004#\0236{A3Z4l\0162Gw)1+V8z\004ga<}\023>\0235h\025\"Ax";
        i = 3;
        break;
      case 3:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(-996);
        localObject3 = "\004\\7g\004$G0f\017gZ*)\002+\\*l\005i\023\te\004&@<)3\"G+pA+R-l\023f";
        i = 4;
        break;
      case 4:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(-994);
        localObject3 = "\025V*y\016)@<)\025.^<f\0243\035yY\r\"R*lA\025V-{\030g_8}\0045\022";
        i = 5;
        break;
      case 5:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(-993);
        localObject3 = "";
        i = 6;
        break;
      case 6:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(11);
        localObject3 = "\001R0e\004#\023-fA5V>`\0223V+(";
        i = 7;
        break;
      case 7:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(1005);
        localObject3 = "";
        i = 8;
        break;
      case 8:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(1009);
        localObject3 = "";
        i = 9;
        break;
      case 9:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(1006);
        localObject3 = "";
        i = 10;
        break;
      case 10:
        localObject5.put(localObject4, localObject1);
        localObject2 = a;
        localObject1 = Integer.valueOf(1007);
        localObject3 = "\016]/h\r.Wy@\f\"Zu)3\"T0z\025\"Ayh\006&Z7'";
        i = 11;
        break;
      case 11:
        localObject5.put(localObject4, localObject1);
        localObject2 = new HashMap();
        b = (HashMap)localObject2;
        localObject1 = Integer.valueOf(995);
        localObject3 = "";
        i = 12;
        break;
      case 12:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(996);
        localObject3 = "";
        i = 13;
        break;
      case 13:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(997);
        localObject3 = "";
        i = 14;
        break;
      case 14:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(998);
        localObject3 = "";
        i = 15;
        break;
      case 15:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1000);
        localObject3 = "\022@<{A$_0j\n\"Wyh\017#\0236y\004)V=)\025/VyD\0044@8n\004";
        i = 16;
        break;
      case 16:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1001);
        localObject3 = "";
        i = 17;
        break;
      case 17:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1002);
        localObject3 = "";
        i = 18;
        break;
      case 18:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1003);
        localObject3 = "";
        i = 19;
        break;
      case 19:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1004);
        localObject3 = "";
        i = 20;
        break;
      case 20:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1005);
        localObject3 = "";
        i = 21;
        break;
      case 21:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1008);
        localObject3 = "\021Z=l\016gZ*)\007(A:lA$_6z\004#\023;pA2@<{";
        i = 22;
        break;
      case 22:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1007);
        localObject3 = "\022@<{A$_0j\n\"Wy..\f\024";
        i = 23;
        break;
      case 23:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1006);
        localObject3 = "\022@<{A$_0j\n\"Wy.\"&]:l\r`";
        i = 24;
        break;
      case 24:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1011);
        localObject3 = "\003\\.g\r(R=)\007&Z5l\005";
        i = 25;
        break;
      case 25:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1012);
        localObject3 = "\022@<{A$_0j\n\"Wy}\016gW6~\017+\\8mA&T8`\017";
        i = 26;
        break;
      case 26:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1013);
        localObject3 = "";
        i = 27;
        break;
      case 27:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1100);
        localObject3 = "";
        i = 28;
        break;
      case 28:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1014);
        localObject3 = "\001R0e\004#\023-fA7A<e\016&Wy{\0046F0{\004#\023+l\022(F+j\004";
        i = 29;
        break;
      case 29:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1015);
        localObject3 = "\022@<{A$_0j\n\"Wy`\0174G8e\rgR5l\0233\0236gA4G8}\0244\023;h\023gR?}\0045\023=f\026)_6h\005.]>)\007.]0z\t\"Ww";
        i = 30;
        break;
      case 30:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1016);
        localObject3 = "\022@<{A$_0j\n\"Wy}\t\"\023.l\0031Z<~F4\023,{\r";
        i = 31;
        break;
      case 31:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1017);
        localObject3 = "";
        i = 32;
        break;
      case 32:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1018);
        localObject3 = "\023[<),\"@*h\006\"\023*a\0160\0230gA3[<)\0223R-|\022gQ8{";
        i = 33;
        break;
      case 33:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1019);
        localObject3 = "";
        i = 34;
        break;
      case 34:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1020);
        localObject3 = "\003\\.gA.^8n\004gU8`\r\"W";
        i = 35;
        break;
      case 35:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1021);
        localObject3 = "\003\\.gA/G4eA!R0e\004#";
        i = 36;
        break;
      case 36:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1022);
        localObject3 = "";
        i = 37;
        break;
      case 37:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1030);
        localObject3 = "";
        i = 38;
        break;
      case 38:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1031);
        localObject3 = "\024G6yA7F*aA4V+\b$V";
        i = 39;
        break;
      case 39:
        localObject5.put(localObject4, localObject1);
        localObject2 = b;
        localObject1 = Integer.valueOf(1032);
        localObject3 = "\025V*|\f\"\023)|\022/\023*l\0231Z:l";
        i = 40;
      case 40:
      }
    }
    localObject5.put(localObject4, localObject1);
  }

  public static String a(int paramInt)
  {
    if (!a.containsKey(Integer.valueOf(paramInt)))
    {
      new StringBuilder(z[6]).append(paramInt).toString();
      x.c();
      return null;
    }
    return (String)a.get(Integer.valueOf(paramInt));
  }

  public static JSONObject a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put(z[4], z[7]);
      localJSONObject.put(z[3], paramString);
      localJSONObject.put(z[2], z[5]);
      return localJSONObject;
    }
    catch (JSONException paramString)
    {
    }
    return null;
  }

  public static String b(int paramInt)
  {
    if (!b.containsKey(Integer.valueOf(paramInt)))
    {
      new StringBuilder(z[0]).append(paramInt).toString();
      x.c();
      return "";
    }
    return (String)b.get(Integer.valueOf(paramInt));
  }

  public static JSONObject b(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put(z[4], z[1]);
      localJSONObject.put(z[3], paramString);
      localJSONObject.put(z[2], z[5]);
      return localJSONObject;
    }
    catch (JSONException paramString)
    {
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.r
 * JD-Core Version:    0.6.2
 */