package cn.jpush.android.util;

import android.content.Context;
import org.json.JSONArray;

final class ad
  implements Runnable
{
  Context a;
  JSONArray b;

  public ad(Context paramContext, JSONArray paramJSONArray)
  {
    this.a = paramContext;
    this.b = paramJSONArray;
  }

  public final void run()
  {
    ac.b(this.a, this.b);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.ad
 * JD-Core Version:    0.6.2
 */