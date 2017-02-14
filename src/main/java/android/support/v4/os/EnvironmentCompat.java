package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public class EnvironmentCompat
{
  public static final String MEDIA_UNKNOWN = "unknown";
  private static final String TAG = "EnvironmentCompat";

  public static String getStorageState(File paramFile)
  {
    if (Build.VERSION.SDK_INT >= 19)
      return EnvironmentCompatKitKat.getStorageState(paramFile);
    try
    {
      if (paramFile.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath()))
      {
        paramFile = Environment.getExternalStorageState();
        return paramFile;
      }
    }
    catch (IOException paramFile)
    {
      Log.w("EnvironmentCompat", "Failed to resolve canonical path: " + paramFile);
    }
    return "unknown";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.os.EnvironmentCompat
 * JD-Core Version:    0.6.2
 */