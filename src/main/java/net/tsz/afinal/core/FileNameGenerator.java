package net.tsz.afinal.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileNameGenerator
{
  private static String bytesToHexString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfByte.length)
        return localStringBuilder.toString();
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() == 1)
        localStringBuilder.append('0');
      localStringBuilder.append(str);
      i += 1;
    }
  }

  public static String generator(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = bytesToHexString(((MessageDigest)localObject).digest());
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    return String.valueOf(paramString.hashCode());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.core.FileNameGenerator
 * JD-Core Version:    0.6.2
 */