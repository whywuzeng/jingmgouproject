package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.Arrays;

class BundleUtil
{
  public static Bundle[] getBundleArrayFromBundle(Bundle paramBundle, String paramString)
  {
    Object localObject = paramBundle.getParcelableArray(paramString);
    if (((localObject instanceof Bundle[])) || (localObject == null))
      return (Bundle[])localObject;
    localObject = (Bundle[])Arrays.copyOf((Object[])localObject, localObject.length, [Landroid.os.Bundle.class);
    paramBundle.putParcelableArray(paramString, (Parcelable[])localObject);
    return localObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.app.BundleUtil
 * JD-Core Version:    0.6.2
 */