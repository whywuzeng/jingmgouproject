package com.nostra13.universalimageloader.cache.disc.impl;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import java.io.File;

public class UnlimitedDiscCache extends BaseDiscCache
{
  public UnlimitedDiscCache(File paramFile)
  {
    super(paramFile);
  }

  public UnlimitedDiscCache(File paramFile1, File paramFile2)
  {
    super(paramFile1, paramFile2);
  }

  public UnlimitedDiscCache(File paramFile1, File paramFile2, FileNameGenerator paramFileNameGenerator)
  {
    super(paramFile1, paramFile2, paramFileNameGenerator);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache
 * JD-Core Version:    0.6.2
 */