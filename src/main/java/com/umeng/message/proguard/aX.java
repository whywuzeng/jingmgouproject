package com.umeng.message.proguard;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.android.du.CpuType;

public final class aX
{
  private static final String a = "/download/";
  private static final String b = ".zip";

  private static Boolean a(String paramString1, String paramString2, String paramString3, String paramString4, Context paramContext)
  {
    Object localObject1;
    if (!TextUtils.isEmpty(paramString3))
    {
      localObject1 = paramString3;
      if (!"".equals(paramString3));
    }
    else
    {
      localObject1 = paramString1.substring(paramString1.lastIndexOf("/") + 1);
    }
    Object localObject2;
    try
    {
      localObject2 = new URL(paramString1).openConnection();
      ((URLConnection)localObject2).connect();
      paramString3 = ((URLConnection)localObject2).getInputStream();
      if (((URLConnection)localObject2).getContentLength() <= 0)
        Log.d("FileUtil", "无法获知文件大小");
      if (paramString3 == null)
      {
        Log.d("FileUtil", "无法获取文件");
        return Boolean.valueOf(false);
      }
      if (!new File(paramString2 + (String)localObject1).exists())
        new File(paramString2 + (String)localObject1).getParentFile().mkdirs();
      localObject2 = new File(paramString2 + (String)localObject1);
      paramString2 = new FileOutputStream(paramString2 + (String)localObject1);
      localObject1 = new byte[1024];
      while (true)
      {
        int i = paramString3.read((byte[])localObject1);
        if (i == -1)
          break;
        paramString2.write((byte[])localObject1, 0, i);
      }
    }
    catch (Throwable paramString1)
    {
      Log.d("FileUtil", paramString1.toString());
      bM.e(paramContext, paramString1.toString(), "push");
      return Boolean.valueOf(false);
    }
    paramString2.flush();
    paramString3.close();
    paramString2.close();
    if ((localObject2 != null) && (((File)localObject2).exists()));
    try
    {
      paramString2 = bA.a((File)localObject2);
      if ((paramString2 != null) && (paramString2.equalsIgnoreCase(paramString4)))
        return Boolean.valueOf(true);
      ((File)localObject2).delete();
      Log.e("FileUtil", "md5 check failed url=" + paramString1 + "computer md5=" + paramString2 + ",getMd5()=" + paramString4);
      bM.e(paramContext, "url=" + paramString1 + "computer md5=" + paramString2 + ",getMd5()=" + paramString4, "push");
      return Boolean.valueOf(false);
    }
    catch (IOException paramString1)
    {
      bM.e(paramContext, paramString1.toString(), "push");
    }
    return Boolean.valueOf(false);
  }

  public static Boolean a(String paramString1, String paramString2, CpuType paramCpuType, Context paramContext)
  {
    if (new File(paramString2).exists())
      new File(paramString2).delete();
    while (true)
    {
      try
      {
        ZipInputStream localZipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(paramString1)));
        Object localObject1 = localZipInputStream.getNextEntry();
        if (localObject1 != null)
        {
          Log.e("FileUtil", "FileUtil entry.getName() = " + ((ZipEntry)localObject1).getName() + ",targetDir = " + paramString2);
          Object localObject2;
          try
          {
            byte[] arrayOfByte = new byte[4096];
            localObject2 = ((ZipEntry)localObject1).getName();
            if (((String)localObject2).endsWith("/"))
              continue;
            if (paramCpuType == CpuType.armeabi)
            {
              if (((String)localObject2).contains(CpuType.mips.name()))
                break label519;
              if (!((String)localObject2).contains(CpuType.x86.name()))
                break label513;
              break label519;
              if (i == 0)
                continue;
              i = ((String)localObject2).lastIndexOf("/");
              localObject1 = localObject2;
              if (i != -1)
                localObject1 = ((String)localObject2).substring(i + 1);
              localObject1 = new File(paramString2 + (String)localObject1);
              localObject2 = new File(((File)localObject1).getParent());
              if (!((File)localObject2).exists())
                ((File)localObject2).mkdirs();
              localObject1 = new BufferedOutputStream(new FileOutputStream((File)localObject1), 4096);
              i = localZipInputStream.read(arrayOfByte, 0, 4096);
              if (i == -1)
                continue;
              ((BufferedOutputStream)localObject1).write(arrayOfByte, 0, i);
              continue;
            }
          }
          catch (Throwable paramString1)
          {
            localZipInputStream.close();
            Log.d("FileUtil", paramString1.toString());
            bM.e(paramContext, paramString1.toString(), "push");
            return Boolean.valueOf(false);
          }
          if (paramCpuType == CpuType.mips)
          {
            if (((String)localObject2).contains(CpuType.armeabi.name()))
              break label525;
            if (!((String)localObject2).contains(CpuType.x86.name()))
              break label513;
            break label525;
          }
          if (paramCpuType != CpuType.x86)
            break label513;
          if (((String)localObject2).contains(CpuType.armeabi.name()))
            break label531;
          if (!((String)localObject2).contains(CpuType.mips.name()))
            break label513;
          break label531;
          ((BufferedOutputStream)localObject1).flush();
          ((BufferedOutputStream)localObject1).close();
          continue;
        }
        localZipInputStream.close();
        if (new File(paramString1).exists())
        {
          Log.d("FileUtil", "file.delete : " + paramString1);
          new File(paramString1).delete();
        }
        return Boolean.valueOf(true);
      }
      catch (Throwable paramString1)
      {
        Log.d("FileUtil", paramString1.toString());
        bM.e(paramContext, paramString1.toString(), "push");
        return Boolean.valueOf(false);
      }
      label513: int i = 1;
      continue;
      label519: i = 0;
      continue;
      label525: i = 0;
      continue;
      label531: i = 0;
    }
  }

  public static final boolean a(Context paramContext, aZ paramaZ, String paramString, CpuType paramCpuType)
  {
    String str1 = paramString + "/download/";
    String str2 = paramaZ.a() + ".zip";
    Boolean localBoolean3 = a(paramaZ.f(), str1, str2, paramaZ.h(), paramContext);
    Boolean localBoolean2 = Boolean.valueOf(false);
    Boolean localBoolean1 = localBoolean2;
    if (localBoolean3.booleanValue())
    {
      bM.d(paramContext, "1", "push");
      Log.i("FileUtil", "下载成功");
      if ((paramaZ.g() == null) || (paramaZ.g().trim().length() <= 0))
        break label288;
      paramaZ = paramString + File.separator + paramaZ.g() + File.separator;
      Log.i("FileUtil", "targetDir = " + paramaZ);
      localBoolean1 = a(str1 + str2, paramaZ, paramCpuType, paramContext);
      if (localBoolean1.booleanValue())
        break label241;
      new File(paramaZ).delete();
    }
    while (true)
    {
      boolean bool = localBoolean3.booleanValue();
      return localBoolean1.booleanValue() & bool;
      label241: bM.g(paramContext, "push", paramaZ + "push.jar");
      bM.d(paramContext, "2", "push");
      Log.i("FileUtil", "解压成功");
      continue;
      label288: Log.d("FileUtil", "version error");
      localBoolean1 = localBoolean2;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aX
 * JD-Core Version:    0.6.2
 */