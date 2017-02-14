package cn.jpush.android.data;

import cn.jpush.android.util.ai;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class JPushLocalNotification
  implements Serializable
{
  private static final String[] z;
  private int a = 1;
  private String b = "";
  private String c = z[12];
  private String d = z[12];
  private long e = 0L;
  private String f;
  private String g;
  private String h;
  private long i;
  private long j = 1L;
  private int k = 1;
  private String l = "";
  private String m = "";

  static
  {
    String[] arrayOfString = new String[13];
    int i1 = 0;
    Object localObject2 = "}\035Y^Qp\006h^Mn\027";
    int n = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i2 = arrayOfChar.length;
    int i5 = 0;
    int i3 = 0;
    int i7 = n;
    localObject2 = arrayOfChar;
    int i8 = i1;
    Object localObject3 = localObject1;
    int i4 = i2;
    Object localObject4;
    int i6;
    if (i2 <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i6 = n;
      label68: i4 = i3;
      label71: localObject2 = localObject1;
      i5 = localObject2[i3];
      switch (i4 % 5)
      {
      default:
        n = 52;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i3] = ((char)(n ^ i5));
      i4 += 1;
      if (i2 == 0)
      {
        i3 = i2;
        break label71;
      }
      i5 = i4;
      i4 = i2;
      localObject3 = localObject4;
      i8 = i1;
      localObject2 = localObject1;
      i7 = i6;
      i6 = i7;
      localObject1 = localObject2;
      i1 = i8;
      localObject4 = localObject3;
      i2 = i4;
      i3 = i5;
      if (i4 > i5)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i7)
      {
      default:
        localObject3[i8] = localObject1;
        i1 = 1;
        localObject2 = "p-XDXg";
        n = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i8] = localObject1;
        i1 = 2;
        localObject2 = "s\001Pu]z";
        n = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i8] = localObject1;
        i1 = 3;
        localObject2 = "p-CC@r\027";
        n = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i8] = localObject1;
        i1 = 4;
        localObject2 = "p-TEZj\027Y^";
        n = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i8] = localObject1;
        i1 = 5;
        localObject2 = "\026h^";
        n = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i8] = localObject1;
        i1 = 6;
        localObject2 = "p-RR@l\023D";
        n = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i8] = localObject1;
        i1 = 7;
        localObject2 = "m\032X]kj\013GO";
        n = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i8] = localObject1;
        i1 = 8;
        localObject2 = "q\004RXFw\026RuYm\025hCP";
        n = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i8] = localObject1;
        i1 = 9;
        localObject2 = "p-U_]r\026RXkw\026";
        n = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i8] = localObject1;
        i1 = 10;
        localObject2 = "s-TEZj\027Y^";
        n = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i8] = localObject1;
        i1 = 11;
        localObject2 = "p\035CCRw\021VC[p-CSD{";
        n = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i8] = localObject1;
        i1 = 12;
        localObject2 = ".B";
        n = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i8] = localObject1;
        z = arrayOfString;
        return;
        n = 30;
        continue;
        n = 114;
        continue;
        n = 55;
        continue;
        n = 42;
      }
    }
  }

  private static void a(String paramString1, String paramString2, JSONObject paramJSONObject)
  {
    if (!ai.a(paramString2))
      paramJSONObject.put(paramString1, paramString2);
  }

  public boolean equals(Object paramObject)
  {
    return this.j == ((JPushLocalNotification)paramObject).j;
  }

  public long getBroadcastTime()
  {
    return this.e;
  }

  public long getBuilderId()
  {
    return this.i;
  }

  public String getContent()
  {
    return this.f;
  }

  public String getExtras()
  {
    return this.h;
  }

  public long getNotificationId()
  {
    return this.j;
  }

  public String getTitle()
  {
    return this.g;
  }

  public int hashCode()
  {
    return this.j.hashCode();
  }

  public void setBroadcastTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    int i2 = 0;
    Object localObject = Calendar.getInstance();
    int n;
    label70: label75: int i1;
    if (paramInt1 <= ((Calendar)localObject).get(1))
    {
      paramInt1 = ((Calendar)localObject).get(1);
      if (paramInt2 - 1 <= ((Calendar)localObject).get(2))
      {
        n = ((Calendar)localObject).get(2);
        if (paramInt3 <= ((Calendar)localObject).get(5))
        {
          paramInt2 = ((Calendar)localObject).get(5);
          paramInt3 = n;
        }
      }
      while (true)
      {
        if (paramInt4 <= 0)
          break label206;
        if (paramInt5 <= 0)
          break label200;
        n = i2;
        if (paramInt6 > 0)
          n = paramInt6;
        ((Calendar)localObject).set(paramInt1, paramInt3, paramInt2, paramInt4, paramInt5, n);
        localObject = ((Calendar)localObject).getTime();
        long l1 = System.currentTimeMillis();
        if (((Date)localObject).getTime() >= l1)
          break;
        this.e = l1;
        return;
        paramInt2 = paramInt3;
        paramInt3 = n;
        continue;
        paramInt2 -= 1;
        n = paramInt2;
        i1 = paramInt1;
        if (paramInt3 <= 0)
          break label212;
        n = paramInt3;
        paramInt3 = paramInt2;
        paramInt2 = n;
      }
    }
    if (paramInt2 > 0);
    while (true)
    {
      n = paramInt2;
      i1 = paramInt1;
      if (paramInt3 > 0)
      {
        n = paramInt3;
        paramInt3 = paramInt2;
        paramInt2 = n;
        break;
        this.e = ((Date)localObject).getTime();
        return;
        label200: paramInt5 = 0;
        break label75;
        label206: paramInt4 = 0;
        break label70;
      }
      label212: paramInt2 = 0;
      paramInt1 = i1;
      paramInt3 = n;
      break;
      paramInt2 = 0;
    }
  }

  public void setBroadcastTime(long paramLong)
  {
    this.e = paramLong;
  }

  public void setBroadcastTime(Date paramDate)
  {
    this.e = paramDate.getTime();
  }

  public void setBuilderId(long paramLong)
  {
    this.i = paramLong;
  }

  public void setContent(String paramString)
  {
    this.f = paramString;
  }

  public void setExtras(String paramString)
  {
    this.h = paramString;
  }

  public void setNotificationId(long paramLong)
  {
    this.j = paramLong;
  }

  public void setTitle(String paramString)
  {
    this.g = paramString;
  }

  public String toJSON()
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = new JSONObject();
      if (!ai.a(this.h))
      {
        JSONObject localJSONObject3 = new JSONObject(this.h);
        localJSONObject2.put(z[6], localJSONObject3);
      }
      a(z[4], this.f, localJSONObject2);
      a(z[3], this.g, localJSONObject2);
      a(z[4], this.f, localJSONObject2);
      localJSONObject2.put(z[5], 0);
      localJSONObject1.put(z[10], localJSONObject2);
      a(z[2], this.j, localJSONObject1);
      a(z[0], this.m, localJSONObject1);
      a(z[8], this.l, localJSONObject1);
      localJSONObject1.put(z[1], this.k);
      localJSONObject1.put(z[9], this.i);
      localJSONObject1.put(z[7], 3);
      localJSONObject1.put(z[11], 1);
      return localJSONObject1.toString();
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.JPushLocalNotification
 * JD-Core Version:    0.6.2
 */