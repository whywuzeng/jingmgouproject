package com.umeng.message.proguard;

import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class bu
{
  public static boolean a(String paramString1, String paramString2, StringBuilder paramStringBuilder)
  {
    Log.w("TAG.", paramString2);
    try
    {
      Process localProcess = Runtime.getRuntime().exec("sh");
      DataInputStream localDataInputStream = new DataInputStream(localProcess.getInputStream());
      DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
      if ((paramString1 != null) && (!"".equals(paramString1.trim())))
        localDataOutputStream.writeBytes("cd " + paramString1 + "\n");
      localDataOutputStream.writeBytes(paramString2 + " &\n");
      localDataOutputStream.writeBytes("exit \n");
      localDataOutputStream.flush();
      localProcess.waitFor();
      paramString1 = new byte[localDataInputStream.available()];
      localDataInputStream.read(paramString1);
      paramString1 = new String(paramString1);
      if (paramString1.length() != 0)
        paramStringBuilder.append(paramString1);
      return true;
    }
    catch (Exception paramString1)
    {
      paramStringBuilder.append("Exception:" + paramString1.getMessage());
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bu
 * JD-Core Version:    0.6.2
 */