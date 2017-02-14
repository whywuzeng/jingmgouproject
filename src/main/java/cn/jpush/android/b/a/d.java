package cn.jpush.android.b.a;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import cn.jpush.android.util.x;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public final class d
{
  private static final String[] z;
  private HashMap<String, Method> a;
  private String b;
  private String c;

  static
  {
    String[] arrayOfString = new String[35];
    int j = 0;
    Object localObject2 = "\r+S K\r\"A#I\027h";
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
        i = 39;
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
        localObject2 = "g;q-KA\002S:F";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "qj";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "VjQ#CHj\bl\002Id\022nUH;G S\017r\022iTP";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\r:W?RA<\b";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "C=^ ";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "B*X)DY";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "r\030";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\004hE%SEhD-KD,\022<F_)_)SH:A";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "r\007";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "^<@%IJ";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "C=_.B_";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "N)^ \007I)F-\007H%B8^";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "r\n";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "O'] BL&";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "C'FlAB=\\(\007@-F$HI`";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "L:U?";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "r\033";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "@-F$HIhW4BN=F)\007H:@#U\027";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "@-F$HI";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = "Y1B)T";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = "r\006";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        j = 22;
        localObject2 = "Lf\027?\032";
        i = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i4] = localObject1;
        j = 23;
        localObject2 = "\r!\\%SD)^%]L<[#I\r-\\(\005\0045\033dPD&V#P\004s";
        i = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i4] = localObject1;
        j = 24;
        localObject2 = "\r+S K\r-@>H_d\022/HI-\bn\fJfQ#CHc\020`\007@-A?FJ-\bn\fJf@)TX$F1UH<G>I\r/\034>B^=^8Z\026\007P&BN<\034+BY\007E\"w_'B)UY1|-JH;\032-\016\003.]>bL+ZdAX&Q8NB&\032(\016V>S>\007NuS\027Cps[*\017Y1B)HKhQq\032\020jT9IN<[#I\017n\024(\006\020u\020/FA$P-DFj\0337Fv,oqAX&Q8NB&\032e\\_-F9UChQbF]8^5\017Ldi(z\003+]\"DL<\032\rU_)KbW_'F#ST8WbTA!Q)\tN)^ \017L:U9JH&F?\013\035a\033eZP5\033wE\003";
        i = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i4] = localObject1;
        j = 25;
        localObject2 = "\r!\\%SD)^%]L<[#I\r*W+NCj\033wQL:\022-\032V9G)RHri\021\013N)^ EL+YvAX&Q8NB&\032e\\[)@lC\020\t@>FTfB>HY'F5WHfA NN-\034/FA$\032-UJ=_)IY;\036|\016\026>S>\007NuVbTE!T8\017\004sD-U\r-\017(\t^ [*S\005a\t8OD;\034=RH=W\027DpfS<WA1\0328OD;\036(\016\026!Td\006HaI(BA-F)\007Y [?\t\\=W9Bv+o1ZPs";
        i = 24;
        localObject1 = arrayOfString;
        break;
      case 24:
        localObject3[i4] = localObject1;
        j = 26;
        localObject2 = "";
        i = 25;
        localObject1 = arrayOfString;
        break;
      case 25:
        localObject3[i4] = localObject1;
        j = 27;
        localObject2 = "\r+S K\r-@>H_d\022!B^;S+B\027%[?T\r%W8OB,\022\"F@-\0201QL:\022)\032v\025\t*H_`D-U\r \017}\034EtTbKH&U8O\026 \031g\016V>S>\007NuT\027OpsD-U\r\"\0178^]-]*\007NsW\027B\003$W\"@Y oqM\026!TdM\020u\020*RC+F%HCj\0337QL:\022(\032LfC9BX-\034 BC/F$\034LfC9BX-i(z\020+\t*|E\025\017(ZP>S>\007Jux\037hcfB-U^-\032<UB%B8\017g\033}\002\t^<@%IJ!T5\017V%W8OB,\b*\t^ [*S\005a\0368^]-AvB\001)@+T\027.Oe\016\004s[*\017JfQ#CHi\017~\027\035aI8O_'En";
        i = 26;
        localObject1 = arrayOfString;
        break;
      case 26:
        localObject3[i4] = localObject1;
        j = 28;
        localObject2 = "G)D-TN:[<S\027`T9IN<[#I\005*\0337DB&A#KHf^#@\005j";
        i = 27;
        localObject1 = arrayOfString;
        break;
      case 27:
        localObject3[i4] = localObject1;
        j = 29;
        localObject2 = "D&[8\007G;\022)U_'@v";
        i = 28;
        localObject1 = arrayOfString;
        break;
      case 28:
        localObject3[i4] = localObject1;
        j = 30;
        localObject2 = "K=\\/SD'\\d\016V>S>\007Kus>UL1\034<UB<]8^]-\034?KD+WbDL$^dF_/G!BC<A`\027\004s[*\017Kf^)IJ<Zp\026\0043F$UB?\020";
        i = 29;
        localObject1 = arrayOfString;
        break;
      case 29:
        localObject3[i4] = localObject1;
        j = 31;
        localObject2 = "\020)\t/HC;] B\003$]+\017\017";
        i = 30;
        localObject1 = arrayOfString;
        break;
      case 30:
        localObject3[i4] = localObject1;
        j = 32;
        localObject2 = "D&X)DY-VlIL%WlDL&\022\"HYhP)\007C=^ ";
        i = 31;
        localObject1 = arrayOfString;
        break;
      case 31:
        localObject3[i4] = localObject1;
        j = 33;
        localObject2 = "\004h_9TYhG?B\r?W.QD-ElSBhP)\007K!@?S\r8S>F@-F)U\001hE%KAhP)\007])A?";
        i = 32;
        localObject1 = arrayOfString;
        break;
      case 32:
        localObject3[i4] = localObject1;
        j = 34;
        localObject2 = "@-F$HI`";
        i = 33;
        localObject1 = arrayOfString;
        break;
      case 33:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 45;
        continue;
        i = 72;
        continue;
        i = 50;
        continue;
        i = 76;
      }
    }
  }

  public d(String paramString, Class paramClass)
  {
    try
    {
      if (TextUtils.isEmpty(paramString))
        throw new Exception(z[32]);
    }
    catch (Exception paramString)
    {
      Log.e(z[1], z[29] + paramString.getMessage());
      return;
    }
    this.b = paramString;
    this.a = new HashMap();
    paramString = paramClass.getDeclaredMethods();
    paramClass = new StringBuilder(z[28]);
    paramClass.append(this.b);
    paramClass.append(z[25]);
    int j = paramString.length;
    while (true)
    {
      if (i < j)
      {
        Method localMethod = paramString[i];
        if (localMethod.getModifiers() == 9)
        {
          String str = a(localMethod);
          if (str != null)
          {
            this.a.put(str, localMethod);
            paramClass.append(String.format(z[22], new Object[] { localMethod.getName() }));
          }
        }
      }
      else
      {
        paramClass.append(z[30]);
        paramClass.append(this.b);
        paramClass.append(z[27]);
        paramClass.append(this.b);
        paramClass.append(z[24]);
        paramClass.append(this.b);
        paramClass.append(z[31]);
        paramClass.append(this.b);
        paramClass.append(z[23]);
        this.c = paramClass.toString();
        new StringBuilder(z[26]).append(paramClass.toString()).toString();
        x.c();
        return;
      }
      i += 1;
    }
  }

  private String a(String paramString, int paramInt, Object paramObject)
  {
    if (paramObject == null)
      paramObject = z[5];
    while (true)
    {
      paramObject = String.format(z[3], new Object[] { Integer.valueOf(paramInt), paramObject });
      Log.d(z[1], this.b + z[0] + paramString + z[4] + paramObject);
      return paramObject;
      if ((paramObject instanceof String))
      {
        paramObject = ((String)paramObject).replace("\"", z[2]);
        paramObject = "\"" + paramObject + "\"";
      }
      else if ((!(paramObject instanceof Integer)) && (!(paramObject instanceof Long)) && (!(paramObject instanceof Boolean)) && (!(paramObject instanceof Float)) && (!(paramObject instanceof Double)) && (!(paramObject instanceof JSONObject)))
      {
        paramObject = "";
      }
      else
      {
        paramObject = String.valueOf(paramObject);
      }
    }
  }

  private static String a(Method paramMethod)
  {
    Object localObject = paramMethod.getName();
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    int j = arrayOfClass.length;
    if ((j <= 0) || (arrayOfClass[0] != WebView.class))
    {
      Log.w(z[1], z[34] + (String)localObject + z[33]);
      localObject = null;
    }
    int i;
    do
    {
      return localObject;
      paramMethod = (Method)localObject;
      i = 1;
      localObject = paramMethod;
    }
    while (i >= j);
    localObject = arrayOfClass[i];
    if (localObject == String.class)
      paramMethod = paramMethod + z[17];
    while (true)
    {
      i += 1;
      break;
      if ((localObject == Integer.TYPE) || (localObject == Long.TYPE) || (localObject == Float.TYPE) || (localObject == Double.TYPE))
        paramMethod = paramMethod + z[21];
      else if (localObject == Boolean.TYPE)
        paramMethod = paramMethod + z[13];
      else if (localObject == JSONObject.class)
        paramMethod = paramMethod + z[9];
      else
        paramMethod = paramMethod + z[7];
    }
  }

  public final String a()
  {
    return this.c;
  }

  public final String a(WebView paramWebView, String paramString)
  {
    Object localObject1;
    Object[] arrayOfObject;
    int j;
    if (!TextUtils.isEmpty(paramString))
      try
      {
        localObject2 = new JSONObject(paramString);
        localObject1 = ((JSONObject)localObject2).getString(z[19]);
        JSONArray localJSONArray = ((JSONObject)localObject2).getJSONArray(z[20]);
        localObject2 = ((JSONObject)localObject2).getJSONArray(z[16]);
        int k = localJSONArray.length();
        arrayOfObject = new Object[k + 1];
        int i = 0;
        arrayOfObject[0] = paramWebView;
        j = 0;
        paramWebView = (WebView)localObject1;
        if (j < k)
        {
          localObject1 = localJSONArray.optString(j);
          if (z[10].equals(localObject1))
          {
            localObject1 = paramWebView + z[17];
            if (((JSONArray)localObject2).isNull(j))
            {
              paramWebView = null;
              break label647;
            }
            paramWebView = ((JSONArray)localObject2).getString(j);
            break label647;
          }
          if (z[11].equals(localObject1))
          {
            paramWebView = paramWebView + z[21];
            i = i * 10 + j + 1;
            break label658;
          }
          if (z[14].equals(localObject1))
          {
            paramWebView = paramWebView + z[13];
            arrayOfObject[(j + 1)] = Boolean.valueOf(((JSONArray)localObject2).getBoolean(j));
            break label658;
          }
          if (z[6].equals(localObject1))
          {
            localObject1 = paramWebView + z[9];
            if (((JSONArray)localObject2).isNull(j))
            {
              paramWebView = null;
              break label667;
            }
            paramWebView = ((JSONArray)localObject2).getJSONObject(j);
            break label667;
          }
          paramWebView = paramWebView + z[7];
          break label658;
        }
        localObject1 = (Method)this.a.get(paramWebView);
        if (localObject1 == null)
          return a(paramString, 500, z[15] + paramWebView + z[8]);
        if (i > 0)
        {
          paramWebView = ((Method)localObject1).getParameterTypes();
          if (i > 0)
          {
            j = i - i / 10 * 10;
            localJSONArray = paramWebView[j];
            if (localJSONArray == Integer.TYPE)
              arrayOfObject[j] = Integer.valueOf(((JSONArray)localObject2).getInt(j - 1));
            while (true)
            {
              i /= 10;
              break;
              if (localJSONArray != Long.TYPE)
                break label561;
              arrayOfObject[j] = Long.valueOf(Long.parseLong(((JSONArray)localObject2).getString(j - 1)));
            }
          }
        }
      }
      catch (Exception paramWebView)
      {
        Object localObject2;
        while (paramWebView.getCause() != null)
        {
          return a(paramString, 500, z[18] + paramWebView.getCause().getMessage());
          label561: arrayOfObject[j] = Double.valueOf(((JSONArray)localObject2).getDouble(j - 1));
          continue;
          paramWebView = a(paramString, 200, ((Method)localObject1).invoke(null, arrayOfObject));
          return paramWebView;
        }
        return a(paramString, 500, z[18] + paramWebView.getMessage());
      }
    else
      return a(paramString, 500, z[12]);
    label647: arrayOfObject[(j + 1)] = paramWebView;
    for (paramWebView = (WebView)localObject1; ; paramWebView = (WebView)localObject1)
    {
      label658: j += 1;
      break;
      label667: arrayOfObject[(j + 1)] = paramWebView;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.b.a.d
 * JD-Core Version:    0.6.2
 */