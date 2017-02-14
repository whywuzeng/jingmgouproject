package cn.sharesdk.framework.a;

import android.text.TextUtils;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.network.HTTPPart;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.RawNetworkCallback;
import java.util.ArrayList;

public class a extends NetworkHelper
{
  private static a a = null;

  public static a a()
  {
    if (a == null)
      a = new a();
    return a;
  }

  private void a(String paramString, int paramInt)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramInt <= 0))
      return;
    ShareSDK.logApiEvent(paramString, paramInt);
  }

  public String a(String paramString1, ArrayList<KVPair<String>> paramArrayList, KVPair<String> paramKVPair, String paramString2, int paramInt)
  {
    return a(paramString1, paramArrayList, paramKVPair, null, paramString2, paramInt);
  }

  public String a(String paramString1, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, ArrayList<KVPair<String>> paramArrayList2, String paramString2, int paramInt)
  {
    return a(paramString1, paramArrayList1, paramKVPair, paramArrayList2, null, paramString2, paramInt);
  }

  public String a(String paramString1, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList, String paramString2, int paramInt)
  {
    a(paramString2, paramInt);
    return super.httpPost(paramString1, paramArrayList1, paramKVPair, paramArrayList2, paramArrayList);
  }

  public String a(String paramString1, ArrayList<KVPair<String>> paramArrayList, String paramString2, int paramInt)
  {
    return a(paramString1, paramArrayList, null, null, paramString2, paramInt);
  }

  public String a(String paramString1, ArrayList<KVPair<String>> paramArrayList1, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList, String paramString2, int paramInt)
  {
    a(paramString2, paramInt);
    return super.httpGet(paramString1, paramArrayList1, paramArrayList2, paramArrayList);
  }

  public void a(String paramString1, ArrayList<KVPair<String>> paramArrayList, HTTPPart paramHTTPPart, RawNetworkCallback paramRawNetworkCallback, String paramString2, int paramInt)
  {
    a(paramString2, paramInt);
    super.rawPost(paramString1, paramArrayList, paramHTTPPart, paramRawNetworkCallback);
  }

  public String b(String paramString1, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList, String paramString2, int paramInt)
  {
    a(paramString2, paramInt);
    return super.httpPut(paramString1, paramArrayList1, paramKVPair, paramArrayList2, paramArrayList);
  }

  public String b(String paramString1, ArrayList<KVPair<String>> paramArrayList, String paramString2, int paramInt)
  {
    return a(paramString1, paramArrayList, null, paramString2, paramInt);
  }

  public String b(String paramString1, ArrayList<KVPair<String>> paramArrayList1, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList, String paramString2, int paramInt)
  {
    a(paramString2, paramInt);
    return super.jsonPost(paramString1, paramArrayList1, paramArrayList2, paramArrayList);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.a.a
 * JD-Core Version:    0.6.2
 */