package com.baidu.platform.comjni.tools;

import android.os.Bundle;
import java.util.Iterator;
import java.util.Set;

public class BundleKeySet
{
  public String[] getBundleKeys(Bundle paramBundle)
  {
    if (paramBundle == null);
    while (paramBundle.isEmpty())
      return null;
    String[] arrayOfString = new String[paramBundle.size()];
    paramBundle = paramBundle.keySet().iterator();
    int i = 0;
    while (paramBundle.hasNext())
    {
      arrayOfString[i] = ((String)paramBundle.next()).toString();
      i += 1;
    }
    return arrayOfString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.tools.BundleKeySet
 * JD-Core Version:    0.6.2
 */