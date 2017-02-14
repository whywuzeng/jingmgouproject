package cn.jpush.android.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import cn.jpush.android.service.PushService;
import cn.jpush.android.util.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c
  implements Thread.UncaughtExceptionHandler
{
  private static c b;
  private static final String[] z;
  public boolean a = false;
  private Thread.UncaughtExceptionHandler c = null;
  private Context d;

  static
  {
    String[] arrayOfString = new String[14];
    int j = 0;
    Object localObject2 = "=2\034{I\b7\007k@\"%\001|D/!\fxU>-\007WG>.\f";
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
        i = 33;
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
        localObject2 = "40\b{I;-\016{";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "#;\031m";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "40\b{I\b.\006o";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "9'\035N%)6|X''";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "4-\034fU";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "$6\bkJ#0\bkD";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "9'\035N%)\035qQ2";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "!'\033{H8,\007iL2";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "40\b{I#+\004m";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = ":'\032{@0'";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "!'\033{H8,\ngE2";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "97\005d";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        b = new c();
        return;
        i = 87;
        continue;
        i = 66;
        continue;
        i = 105;
        continue;
        i = 8;
      }
    }
  }

  public static c a()
  {
    return b;
  }

  private JSONArray a(Context paramContext, Throwable paramThrowable)
  {
    Object localObject2 = d(paramContext);
    Object localObject1 = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter((Writer)localObject1));
    String str = ((StringWriter)localObject1).toString();
    localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = new JSONArray();
    int i = 0;
    Object localObject3;
    while (true)
    {
      JSONObject localJSONObject = null;
      localObject2 = localObject1;
      localObject3 = localObject1;
      try
      {
        if (i < ((JSONArray)localObject1).length())
        {
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject = ((JSONArray)localObject1).optJSONObject(i);
          if (localJSONObject == null)
            break label530;
          localObject2 = localObject1;
          localObject3 = localObject1;
          if (!str.equals(localJSONObject.getString(z[7])))
            break label530;
          localObject2 = localObject1;
          localObject3 = localObject1;
          int j = localJSONObject.getInt(z[6]);
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject.put(z[6], j + 1);
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject.put(z[10], System.currentTimeMillis());
          if (localJSONObject != null)
          {
            localObject2 = localObject1;
            localObject3 = localObject1;
            paramContext = a((JSONArray)localObject1, i);
            localObject2 = paramContext;
            localObject3 = paramContext;
            paramContext.put(localJSONObject);
            return paramContext;
          }
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject = new JSONObject();
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject.put(z[10], System.currentTimeMillis());
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject.put(z[7], str);
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject.put(z[11], paramThrowable.toString());
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject.put(z[6], 1);
          localObject2 = localObject1;
          localObject3 = localObject1;
          localJSONObject.put(z[8], a.d(paramContext));
          localObject2 = localObject1;
          localObject3 = localObject1;
          paramThrowable = this.d.getPackageManager().getPackageInfo(this.d.getPackageName(), 1);
          if (paramThrowable != null)
          {
            localObject2 = localObject1;
            localObject3 = localObject1;
            if (paramThrowable.versionName != null)
              break label505;
            localObject2 = localObject1;
            localObject3 = localObject1;
          }
          for (paramContext = z[13]; ; paramContext = paramThrowable.versionName)
          {
            localObject2 = localObject1;
            localObject3 = localObject1;
            paramThrowable = paramThrowable.versionCode;
            localObject2 = localObject1;
            localObject3 = localObject1;
            localJSONObject.put(z[9], paramContext);
            localObject2 = localObject1;
            localObject3 = localObject1;
            localJSONObject.put(z[12], paramThrowable);
            localObject2 = localObject1;
            localObject3 = localObject1;
            ((JSONArray)localObject1).put(localJSONObject);
            return localObject1;
            label505: localObject2 = localObject1;
            localObject3 = localObject1;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        while (true)
        {
          return localObject2;
          i = 0;
        }
        label530: i += 1;
      }
      catch (JSONException paramContext)
      {
      }
    }
    return localObject3;
  }

  private static JSONArray a(JSONArray paramJSONArray, int paramInt)
  {
    if (paramJSONArray == null)
      return null;
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    while (true)
    {
      if ((i >= paramJSONArray.length()) || (i != paramInt));
      try
      {
        localJSONArray.put(paramJSONArray.get(i));
        label39: i += 1;
        continue;
        return localJSONArray;
      }
      catch (JSONException localJSONException)
      {
        break label39;
      }
    }
  }

  private static void a(Context paramContext, JSONArray paramJSONArray)
  {
    paramJSONArray = paramJSONArray.toString();
    if ((paramJSONArray != null) && (paramJSONArray.length() > 0) && (paramContext != null));
    try
    {
      paramContext = paramContext.openFileOutput(z[0], 0);
      paramContext.write(paramJSONArray.getBytes());
      paramContext.flush();
      paramContext.close();
      return;
    }
    catch (IOException paramContext)
    {
    }
    catch (FileNotFoundException paramContext)
    {
    }
  }

  public static void b(Context paramContext)
  {
    paramContext = new File(paramContext.getFilesDir(), z[0]);
    if (paramContext.exists())
      paramContext.delete();
  }

  private static JSONArray d(Context paramContext)
  {
    if (!new File(paramContext.getFilesDir(), z[0]).exists())
      return null;
    try
    {
      paramContext = paramContext.openFileInput(z[0]);
      byte[] arrayOfByte = new byte[1024];
      StringBuffer localStringBuffer = new StringBuffer();
      while (true)
      {
        int i = paramContext.read(arrayOfByte);
        if (i == -1)
          break;
        localStringBuffer.append(new String(arrayOfByte, 0, i));
      }
      if (localStringBuffer.toString().length() > 0)
        paramContext = new JSONArray(localStringBuffer.toString());
      else
        paramContext = null;
    }
    catch (Exception paramContext)
    {
      paramContext = null;
    }
    return paramContext;
  }

  public final void a(Context paramContext)
  {
    this.d = paramContext;
    if (this.c == null)
      this.c = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
    this.a = true;
  }

  public final JSONObject c(Context paramContext)
  {
    if (!this.a);
    JSONArray localJSONArray;
    do
    {
      return null;
      localJSONArray = d(paramContext);
    }
    while (localJSONArray == null);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(z[1], localJSONArray);
      localJSONObject.put(z[3], PushService.m + Math.abs(System.currentTimeMillis() / 1000L - PushService.n));
      localJSONObject.put(z[2], z[4]);
      localJSONObject.put(z[5], a.d(paramContext));
      return localJSONObject;
    }
    catch (JSONException paramContext)
    {
    }
    return localJSONObject;
  }

  public final void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    JSONArray localJSONArray = a(this.d, paramThrowable);
    b(this.d);
    a(this.d, localJSONArray);
    if (this.c != this)
      this.c.uncaughtException(paramThread, paramThrowable);
    throw new RuntimeException(paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.c
 * JD-Core Version:    0.6.2
 */