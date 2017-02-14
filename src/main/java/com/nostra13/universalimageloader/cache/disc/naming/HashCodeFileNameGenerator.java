package com.nostra13.universalimageloader.cache.disc.naming;

public class HashCodeFileNameGenerator
  implements FileNameGenerator
{
  public String generate(String paramString)
  {
    return String.valueOf(paramString.hashCode());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
 * JD-Core Version:    0.6.2
 */