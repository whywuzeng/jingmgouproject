package android.support.v4.content;

import android.content.Context;
import java.io.File;

class ContextCompatKitKat
{
  public static File[] getExternalCacheDirs(Context paramContext)
  {
    return paramContext.getExternalCacheDirs();
  }

  public static File[] getExternalFilesDirs(Context paramContext, String paramString)
  {
    return paramContext.getExternalFilesDirs(paramString);
  }

  public static File[] getObbDirs(Context paramContext)
  {
    return paramContext.getObbDirs();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.content.ContextCompatKitKat
 * JD-Core Version:    0.6.2
 */