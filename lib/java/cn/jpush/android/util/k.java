package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class k
{
  public static final String a;
  private static final String[] z;

  static
  {
    Object localObject2 = new String[7];
    int j = 0;
    Object localObject3 = "_\025\022/vj\017\020jv+\031\036}8+";
    int i = -1;
    Object localObject1 = localObject2;
    int i4;
    Object localObject4;
    int n;
    while (true)
    {
      char[] arrayOfChar = ((String)localObject3).toCharArray();
      k = arrayOfChar.length;
      int i1 = 0;
      m = 0;
      int i3 = i;
      localObject3 = arrayOfChar;
      i4 = j;
      localObject4 = localObject1;
      n = k;
      Object localObject5;
      int i2;
      if (k <= 1)
      {
        localObject5 = localObject1;
        localObject1 = arrayOfChar;
        i2 = i;
      }
      label142: 
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
            i = 2;
            localObject3[m] = ((char)(i ^ i1));
            n += 1;
            if (k != 0)
              break label142;
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
        localObject3 = "o\034\003n\"o\024\0055\"";
        i = 0;
        localObject1 = localObject2;
        break;
      case 0:
        localObject4[i4] = localObject1;
        j = 2;
        localObject3 = "$<\031kpd\024\023 fj\t\026 ";
        i = 1;
        localObject1 = localObject2;
        break;
      case 1:
        localObject4[i4] = localObject1;
        j = 3;
        localObject3 = "o\024\005";
        i = 2;
        localObject1 = localObject2;
        break;
      case 2:
        localObject4[i4] = localObject1;
        j = 4;
        localObject3 = "$O";
        i = 3;
        localObject1 = localObject2;
        break;
      case 3:
        localObject4[i4] = localObject1;
        j = 5;
        localObject3 = "$L";
        i = 4;
        localObject1 = localObject2;
        break;
      case 4:
        localObject4[i4] = localObject1;
        j = 6;
        localObject3 = "U&G\";V\006Dr&";
        i = 5;
        localObject1 = localObject2;
      case 5:
      }
    }
    localObject4[i4] = localObject1;
    z = (String[])localObject2;
    localObject3 = new StringBuilder().append(File.separator);
    localObject1 = "y\024\024g".toCharArray();
    j = localObject1.length;
    int m = 0;
    i = 0;
    localObject2 = localObject1;
    int k = j;
    if (j <= 1);
    label438: 
    do
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 2;
      case 0:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        localObject2[k] = ((char)(i ^ n));
        m += 1;
        if (j != 0)
          break label552;
        k = j;
        break label438;
        i = 11;
        break;
        i = 125;
        break;
        i = 119;
        break;
        i = 15;
        break;
        i = 11;
        continue;
        i = 125;
        continue;
        i = 119;
        continue;
        i = 15;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
    }
    while (k > m);
    label552: a = new String((char[])localObject2).intern() + File.separator;
  }

  public static String a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getFilesDir() + "/" + paramString;
    paramString = new File(paramContext);
    if (!paramString.exists())
      paramString.mkdir();
    return paramContext + "/";
  }

  private static String a(String paramString)
  {
    String str;
    if (TextUtils.isEmpty(paramString))
      str = "";
    do
    {
      return str;
      str = paramString;
    }
    while (paramString.lastIndexOf(File.separator) == 0);
    return paramString + File.separator;
  }

  public static void a(Context paramContext)
  {
    if (a.a())
    {
      String str1 = e(paramContext);
      String str2 = Environment.getExternalStorageDirectory().getAbsolutePath();
      File localFile = new File(str2 + str1);
      if (!localFile.isDirectory())
        localFile.mkdirs();
      localFile = new File(str2 + str1 + z[5]);
      if (!localFile.isDirectory())
        localFile.mkdirs();
      localFile = new File(str2 + str1 + z[4]);
      if (!localFile.isDirectory())
        localFile.mkdirs();
      paramContext = new File(str2 + str1 + File.separator + paramContext.getPackageName());
      if (!paramContext.isDirectory())
        paramContext.mkdirs();
      return;
    }
    x.f();
  }

  private static boolean a(File paramFile)
  {
    while (true)
    {
      int i;
      try
      {
        if (!paramFile.exists())
          return false;
        if (paramFile.isFile())
          return paramFile.delete();
        String[] arrayOfString = paramFile.list();
        int j = arrayOfString.length;
        i = 0;
        if (i < j)
        {
          File localFile = new File(paramFile, arrayOfString[i]);
          if (localFile.isDirectory())
            a(localFile);
          else
            localFile.delete();
        }
      }
      catch (Exception paramFile)
      {
        x.f();
        return false;
      }
      boolean bool = paramFile.delete();
      return bool;
      i += 1;
    }
  }

  public static String b(Context paramContext)
  {
    if (a.a())
    {
      String str = a(Environment.getExternalStorageDirectory().getAbsolutePath());
      str = str + e(paramContext) + z[5];
      if (!new File(str).isDirectory())
        a(paramContext);
      return str + File.separator;
    }
    return "";
  }

  public static String b(Context paramContext, String paramString)
  {
    if (a.a())
    {
      localObject = Environment.getExternalStorageDirectory().getAbsolutePath();
      paramContext = (String)localObject + z[2] + paramContext.getPackageName() + File.separator + paramString + File.separator;
      paramString = new File(paramContext);
      if (!paramString.exists())
        paramString.mkdirs();
      return paramContext;
    }
    Object localObject = new File(paramContext.getFilesDir() + a);
    if ((((File)localObject).exists()) && (((File)localObject).isDirectory()))
    {
      localObject = ((File)localObject).listFiles();
      if (localObject.length > 10)
      {
        Arrays.sort((Object[])localObject, new l());
        a(localObject[(localObject.length - 1)]);
      }
    }
    paramContext = paramContext.getFilesDir() + a + paramString;
    paramString = new File(paramContext);
    if (!paramString.exists())
      paramString.mkdirs();
    return paramContext + "/";
  }

  public static String c(Context paramContext)
  {
    if (a.a())
    {
      String str = a(Environment.getExternalStorageDirectory().getAbsolutePath());
      str = str + e(paramContext) + z[4];
      if (!new File(str).isDirectory())
        a(paramContext);
      return str + "/";
    }
    return "";
  }

  public static void d(Context paramContext)
  {
    paramContext = paramContext.getFilesDir().listFiles();
    int j = paramContext.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramContext[i];
      String str = localObject.getName();
      if (TextUtils.isEmpty(str));
      for (boolean bool = false; ; bool = Pattern.compile(z[6]).matcher(str).matches())
      {
        if (bool)
          m.a(localObject.getAbsolutePath());
        i += 1;
        break;
      }
    }
  }

  private static String e(Context paramContext)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    String str = localSharedPreferences.getString(z[3], "");
    Object localObject2;
    if (TextUtils.isEmpty(str))
    {
      Object localObject1 = Environment.getExternalStorageDirectory().getAbsolutePath();
      paramContext = z[2];
      localObject2 = new File((String)localObject1 + paramContext);
      if (!((File)localObject2).exists())
        break label288;
      localObject1 = new ArrayList();
      localObject2 = ((File)localObject2).listFiles();
      int j = localObject2.length;
      int i = 0;
      while (i < j)
      {
        Object localObject3 = localObject2[i];
        if (localObject3.isDirectory())
        {
          ((ArrayList)localObject1).add(localObject3.getName());
          new StringBuilder(z[1]).append(localObject3.getName()).toString();
          x.b();
        }
        i += 1;
      }
      i = ((ArrayList)localObject1).size();
      if (i <= 0)
        break label256;
      localObject1 = (String)((ArrayList)localObject1).get(i / 2);
      paramContext = paramContext + (String)localObject1;
    }
    while (true)
    {
      new StringBuilder(z[0]).append(paramContext).toString();
      x.d();
      localSharedPreferences.edit().putString(z[3], paramContext).commit();
      return str;
      label256: paramContext = paramContext + UUID.randomUUID().toString().substring(0, 5);
      continue;
      label288: ((File)localObject2).mkdirs();
      paramContext = paramContext + UUID.randomUUID().toString().substring(0, 5);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.k
 * JD-Core Version:    0.6.2
 */