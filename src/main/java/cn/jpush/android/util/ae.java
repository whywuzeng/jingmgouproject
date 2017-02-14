package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.jpush.android.a;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public final class ae
{
  private static SharedPreferences a;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[48];
    int j = 0;
    Object localObject2 = "O.:{Y39~xI2'5yO/?6bK";
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
        i = 11;
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
        localObject2 = "_.2";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "A\0234\"}I2\0359xX\b>#";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "h\002\026";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "@/2\004r\\%";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\004\033a}2q<`\013;\001y\f,9wp|cV\005\034\017xP\034mh\rw\035\033a}2q<c\013;\001s\fy";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "^2=$";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "\005<y";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "E3\0035xX!#$YX#";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "M#%9}I\t?$n^60<BB\0138<ga/55";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "E3\035?hM48?ei.02gI$";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "\005i";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "p\036";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "E3\0265k/>7gI\00154yI3\"";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "e.'1gE$q6d^-0$'\f0$#cx)<51";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "I2#?y_";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "D/$\"1\f";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "A\0224 d^4\0011hG!65BB&>\037e`/67nH\t?";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "\\5\"8_E-4\034dO!=";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "E3\0025gJ\0138<gI$\034?oI";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = "A\b>#";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = "A\0234\"}I2\0359xX\t\001";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        j = 22;
        localObject2 = "A\0234\"}I2\0359xX\020>\"";
        i = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i4] = localObject1;
        j = 23;
        localObject2 = "\001izx";
        i = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i4] = localObject1;
        j = 24;
        localObject2 = "A\r4#xM'4\031[";
        i = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i4] = localObject1;
        j = 25;
        localObject2 = "M/&";
        i = 24;
        localObject1 = arrayOfString;
        break;
      case 24:
        localObject3[i4] = localObject1;
        j = 26;
        localObject2 = "A\t?$n^60<";
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
        localObject2 = "X)<5:\026`";
        i = 27;
        localObject1 = arrayOfString;
        break;
      case 27:
        localObject3[i4] = localObject1;
        j = 29;
        localObject2 = "A\023%1yX\017?\005xI2\001\"n_%?$";
        i = 28;
        localObject1 = arrayOfString;
        break;
      case 28:
        localObject3[i4] = localObject1;
        j = 30;
        localObject2 = "E3\020>x[%#";
        i = 29;
        localObject1 = arrayOfString;
        break;
      case 29:
        localObject3[i4] = localObject1;
        j = 31;
        localObject2 = "A\0328 ^H0\001?yX";
        i = 30;
        localObject1 = arrayOfString;
        break;
      case 30:
        localObject3[i4] = localObject1;
        j = 32;
        localObject2 = "O,>#n\\5\"8";
        i = 31;
        localObject1 = arrayOfString;
        break;
      case 31:
        localObject3[i4] = localObject1;
        j = 33;
        localObject2 = "A\t\001";
        i = 32;
        localObject1 = arrayOfString;
        break;
      case 32:
        localObject3[i4] = localObject1;
        j = 34;
        localObject2 = "@/6\025eM\"=5o`%'5g";
        i = 33;
        localObject1 = arrayOfString;
        break;
      case 33:
        localObject3[i4] = localObject1;
        j = 35;
        localObject2 = "_!'5+|\025\002\030Tx\t\034\025+[)%8+X(4p}M,$51\f";
        i = 34;
        localObject1 = arrayOfString;
        break;
      case 34:
        localObject3[i4] = localObject1;
        j = 36;
        localObject2 = "";
        i = 35;
        localObject1 = arrayOfString;
        break;
      case 35:
        localObject3[i4] = localObject1;
        j = 37;
        localObject2 = "A\020>\"";
        i = 36;
        localObject1 = arrayOfString;
        break;
      case 36:
        localObject3[i4] = localObject1;
        j = 38;
        localObject2 = "\004\033`}<q;`|<Qi\016x#";
        i = 37;
        localObject1 = arrayOfString;
        break;
      case 37:
        localObject3[i4] = localObject1;
        j = 39;
        localObject2 = "_%%pf40\"c.\004#n^\020#5xI.%pCzq";
        i = 38;
        localObject1 = arrayOfString;
        break;
      case 38:
        localObject3[i4] = localObject1;
        j = 40;
        localObject2 = "_%%pb_\f>3jX)>>NB!3<nH`%?1\f";
        i = 39;
        localObject1 = arrayOfString;
        break;
      case 39:
        localObject3[i4] = localObject1;
        j = 41;
        localObject2 = "X2$5";
        i = 40;
        localObject1 = arrayOfString;
        break;
      case 40:
        localObject3[i4] = localObject1;
        j = 42;
        localObject2 = "A\0068\"xX\f>7lI$\030>";
        i = 41;
        localObject1 = arrayOfString;
        break;
      case 41:
        localObject3[i4] = localObject1;
        j = 43;
        localObject2 = "D4% T^%!?yX\037\"9xs5#<";
        i = 42;
        localObject1 = arrayOfString;
        break;
      case 42:
        localObject3[i4] = localObject1;
        j = 44;
        localObject2 = "J!=#n";
        i = 43;
        localObject1 = arrayOfString;
        break;
      case 43:
        localObject3[i4] = localObject1;
        j = 45;
        localObject2 = "_%%pOn\007q$d\026`";
        i = 44;
        localObject1 = arrayOfString;
        break;
      case 44:
        localObject3[i4] = localObject1;
        j = 46;
        localObject2 = "_%%pf40\"c.\0375[/#;HC.?5hX%5pCzq";
        i = 45;
        localObject1 = arrayOfString;
        break;
      case 45:
        localObject3[i4] = localObject1;
        j = 47;
        localObject2 = "_% \017bH";
        i = 46;
        localObject1 = arrayOfString;
        break;
      case 46:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = null;
        return;
        i = 44;
        continue;
        i = 64;
        continue;
        i = 81;
        continue;
        i = 80;
      }
    }
  }

  public static int a(Context paramContext, String paramString, int paramInt)
  {
    c(paramContext);
    return a.getInt(paramString, paramInt);
  }

  public static long a(Context paramContext, String paramString, long paramLong)
  {
    c(paramContext);
    return a.getLong(paramString, paramLong);
  }

  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    c(paramContext);
    return a.getString(paramString1, paramString2);
  }

  private static void a()
  {
    x.d();
    String str = a.getString(z[3], z[15]);
    label134: label188: int i;
    if (!str.equals(z[15]))
    {
      if (str.toLowerCase().equals(z[41]))
      {
        PushService.b = true;
        new StringBuilder(z[45]).append(str).toString();
        x.c();
      }
    }
    else
    {
      str = a.getString(z[13], z[15]);
      if (!str.equals(z[15]))
      {
        if (!str.toLowerCase().equals(z[41]))
          break label1122;
        PushService.D = true;
      }
      str = a.getString(z[10], z[15]);
      if (!str.equals(z[15]))
      {
        if (!str.toLowerCase().equals(z[41]))
          break label1145;
        PushService.d(true);
        new StringBuilder(z[40]).append(str).toString();
        x.c();
      }
      str = a.getString(z[29], z[15]);
      if (!str.equals(z[15]))
      {
        if (!str.toLowerCase().equals(z[41]))
          break label1168;
        PushService.r = true;
        label266: new StringBuilder(z[39]).append(str).toString();
        x.c();
      }
      i = a.getInt(z[1], -1);
      if (i != -1)
      {
        if (i != 1)
          break label1191;
        PushService.s = true;
        label319: new StringBuilder(z[46]).append(PushService.s).toString();
        x.c();
      }
      str = a.getString(z[25], z[15]);
      if (!str.equals(z[15]))
      {
        if (!str.toLowerCase().equals(z[41]))
          break label1202;
        PushService.C = true;
      }
      label399: str = a.getString(z[43], "");
      if (!ai.a(str))
        ac.a(str);
      str = a.getString(z[19], z[15]);
      if (!str.equals(z[15]))
      {
        if (!str.toLowerCase().equals(z[41]))
          break label1225;
        PushService.c(true);
      }
      label482: str = a.getString(z[8], z[15]);
      if (!str.equals(z[15]))
      {
        if (!str.toLowerCase().equals(z[41]))
          break label1248;
        PushService.b(true);
      }
      label536: i = a.getInt(z[9], -1);
      if (i != -1)
        PushService.d(i);
      i = a.getInt(z[26], -1);
      if (i != -1)
      {
        PushService.i = i;
        ServiceInterface.d(a.d);
      }
      i = a.getInt(z[37], -1);
      if (i != -1)
      {
        PushService.p = i;
        PushService.a(i);
      }
      str = a.getString(z[33], z[15]);
      if (!str.equals(z[15]))
      {
        PushService.o = str;
        PushService.a(str);
      }
      str = a.getString(z[21], z[15]);
      if (!str.equals(z[15]))
        PushService.d(str);
      str = a.getString(z[2], z[15]);
      if (!str.equals(z[15]))
        PushService.c(str);
      i = a.getInt(z[34], -1);
      if (i != -1)
        PushService.d = i;
      str = a.getString(z[20], z[15]);
      if (!str.equals(z[15]))
        PushService.b(str);
      i = a.getInt(z[22], -1);
      if (i != -1)
        PushService.b(i);
      str = a.getString(z[4], z[15]);
      if (!str.equals(z[15]))
        PushService.B = str;
      str = a.getString(z[17], z[15]);
      if (!str.equals(z[15]))
      {
        if (!str.toLowerCase().equals(z[41]))
          break label1271;
        PushService.a(true);
      }
    }
    while (true)
    {
      i = a.getInt(z[42], -1);
      if (i != -1)
        PushService.c(i);
      i = a.getInt(z[27], -1);
      if (i != -1)
        ServiceInterface.c(i);
      i = a.getInt(z[31], -1);
      if (i != -1)
        ServiceInterface.d(i);
      str = a.getString(z[24], z[15]);
      if (!str.equals(z[15]))
        ServiceInterface.a(str);
      str = a.getString(z[36], z[15]);
      if (!str.equals(z[15]))
        ServiceInterface.b(str);
      long l = a.getLong(z[6], -1L);
      if (-1L != l)
        PushService.e = l;
      return;
      if (!str.toLowerCase().equals(z[44]))
        break;
      PushService.b = false;
      break;
      label1122: if (!str.toLowerCase().equals(z[44]))
        break label134;
      PushService.D = false;
      break label134;
      label1145: if (!str.toLowerCase().equals(z[44]))
        break label188;
      PushService.d(false);
      break label188;
      label1168: if (!str.toLowerCase().equals(z[44]))
        break label266;
      PushService.r = false;
      break label266;
      label1191: if (i != 0)
        break label319;
      PushService.s = false;
      break label319;
      label1202: if (!str.toLowerCase().equals(z[44]))
        break label399;
      PushService.C = false;
      break label399;
      label1225: if (!str.toLowerCase().equals(z[44]))
        break label482;
      PushService.c(false);
      break label482;
      label1248: if (!str.toLowerCase().equals(z[44]))
        break label536;
      PushService.b(false);
      break label536;
      label1271: if (str.toLowerCase().equals(z[44]))
        PushService.a(false);
    }
  }

  public static void a(Context paramContext)
  {
    try
    {
      c(paramContext);
      a();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.getMessage();
      x.f();
    }
  }

  public static void a(Context paramContext, JSONObject paramJSONObject)
  {
    int k = 1;
    paramContext = c(paramContext).edit();
    Object localObject1 = paramJSONObject.optString(z[3], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[3], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[17], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[17], (String)localObject1);
    int i = paramJSONObject.optInt(z[26], -1);
    if (i != -1)
      paramContext.putInt(z[26], i);
    i = paramJSONObject.optInt(z[1], -1);
    if (i != -1)
      paramContext.putInt(z[1], i);
    i = paramJSONObject.optInt(z[9], -1);
    if (i != -1)
      paramContext.putInt(z[9], i);
    localObject1 = paramJSONObject.optString(z[8], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[8], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[33], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[33], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[21], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[21], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[20], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[20], (String)localObject1);
    i = paramJSONObject.optInt(z[37], -1);
    if (i != -1)
      paramContext.putInt(z[37], i);
    localObject1 = paramJSONObject.optString(z[2], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[2], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[29], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[29], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[10], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[10], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[19], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[19], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[4], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[4], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[25], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[25], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[13], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[13], (String)localObject1);
    i = paramJSONObject.optInt(z[22], -1);
    if (i != -1)
      paramContext.putInt(z[22], i);
    i = paramJSONObject.optInt(z[27], -1);
    if (i != -1)
      paramContext.putInt(z[27], i);
    i = paramJSONObject.optInt(z[31], -1);
    if (i != -1)
      paramContext.putInt(z[31], i);
    localObject1 = paramJSONObject.optString(z[24], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[24], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[36], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[36], (String)localObject1);
    i = paramJSONObject.optInt(z[34], -1);
    if (i != -1)
      paramContext.putInt(z[34], i);
    localObject1 = paramJSONObject.optString(z[30], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[30], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[32], z[15]);
    if (!((String)localObject1).equals(z[15]))
      paramContext.putString(z[32], (String)localObject1);
    localObject1 = paramJSONObject.optString(z[18], z[15]);
    Object localObject2;
    Object localObject3;
    int j;
    Object localObject4;
    Object localObject5;
    if (!((String)localObject1).equals(z[15]))
    {
      localObject2 = z[5];
      if (!Pattern.compile(z[38] + (String)localObject2 + z[7] + (String)localObject2 + z[23] + (String)localObject2 + z[11]).matcher((CharSequence)localObject1).matches())
        break label1631;
      localObject2 = ((String)localObject1).split("_");
      localObject3 = localObject2[1];
      new StringBuilder(z[28]).append((String)localObject3).toString();
      x.b();
      String[] arrayOfString = ((String)localObject3).split("-");
      localObject3 = new ArrayList();
      int n = arrayOfString.length;
      j = 0;
      if (j < n)
      {
        localObject4 = arrayOfString[j];
        new StringBuilder(z[16]).append((String)localObject4).toString();
        x.b();
        localObject5 = ((String)localObject4).split(z[12]);
      }
    }
    while (true)
    {
      int m;
      try
      {
        localObject4 = Integer.valueOf(Integer.parseInt(localObject5[0]));
        localObject5 = Integer.valueOf(Integer.parseInt(localObject5[1]));
        if (((Integer)localObject5).intValue() > ((Integer)localObject4).intValue())
        {
          m = ((Integer)localObject4).intValue();
          i = k;
          if (m <= ((Integer)localObject5).intValue())
          {
            if (((ArrayList)localObject3).contains(Integer.valueOf(m)))
              break label1659;
            ((ArrayList)localObject3).add(String.valueOf(m));
            break label1659;
          }
        }
        else
        {
          new StringBuilder(z[14]).append((String)localObject1).toString();
          x.f();
          i = 0;
        }
        j += 1;
        k = i;
      }
      catch (Exception localException)
      {
        new StringBuilder(z[14]).append((String)localObject1).toString();
        i = 0;
        x.j();
        continue;
      }
      if (k != 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localObject2[0] + "_");
        localObject2 = ((ArrayList)localObject3).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          ((StringBuilder)localObject1).append((String)((Iterator)localObject2).next());
          ((StringBuilder)localObject1).append("-");
        }
        ((StringBuilder)localObject1).deleteCharAt(((StringBuilder)localObject1).length() - 1);
        localObject1 = ((StringBuilder)localObject1).toString();
        paramContext.putString(z[18], (String)localObject1);
        new StringBuilder(z[35]).append((String)localObject1).toString();
        x.c();
      }
      while (true)
      {
        long l = paramJSONObject.optLong(z[6], -1L);
        if (l != -1L)
          paramContext.putLong(z[6], l);
        paramContext.commit();
        a();
        return;
        label1631: new StringBuilder(z[14]).append((String)localObject1).toString();
        x.f();
      }
      label1659: m += 1;
    }
  }

  public static short b(Context paramContext)
  {
    paramContext = c(paramContext).edit();
    short s = (short)((a.getInt(z[47], 100) + 1) % 32767);
    paramContext.putInt(z[47], s);
    paramContext.commit();
    return s;
  }

  public static void b(Context paramContext, String paramString, int paramInt)
  {
    paramContext = c(paramContext).edit();
    paramContext.putInt(paramString, paramInt);
    paramContext.commit();
  }

  public static void b(Context paramContext, String paramString, long paramLong)
  {
    paramContext = c(paramContext).edit();
    paramContext.putLong(paramString, paramLong);
    paramContext.commit();
  }

  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = c(paramContext).edit();
    paramContext.putString(paramString1, paramString2);
    paramContext.commit();
  }

  private static SharedPreferences c(Context paramContext)
  {
    if (a == null)
      a = paramContext.getSharedPreferences(z[0], 0);
    return a;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.ae
 * JD-Core Version:    0.6.2
 */