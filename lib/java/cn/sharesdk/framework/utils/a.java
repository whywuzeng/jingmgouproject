package cn.sharesdk.framework.utils;

import android.util.Base64;
import com.mob.tools.network.KVPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class a
{
  private b a = new b();
  private b b = new b("-._~", false);

  private ArrayList<KVPair<String>> a(long paramLong, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("oauth_consumer_key", this.a.a));
    localArrayList.add(new KVPair("oauth_signature_method", paramString));
    localArrayList.add(new KVPair("oauth_timestamp", String.valueOf(paramLong / 1000L)));
    localArrayList.add(new KVPair("oauth_nonce", String.valueOf(paramLong)));
    localArrayList.add(new KVPair("oauth_version", "1.0"));
    paramString = this.a.c;
    if ((paramString != null) && (paramString.length() > 0))
      localArrayList.add(new KVPair("oauth_token", paramString));
    return localArrayList;
  }

  private ArrayList<KVPair<String>> a(long paramLong, ArrayList<KVPair<String>> paramArrayList, String paramString)
  {
    int j = 0;
    HashMap localHashMap = new HashMap();
    KVPair localKVPair;
    if (paramArrayList != null)
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localKVPair = (KVPair)paramArrayList.next();
        localHashMap.put(a(localKVPair.name), a((String)localKVPair.value));
      }
    }
    paramArrayList = a(paramLong, paramString);
    if (paramArrayList != null)
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        paramString = (KVPair)paramArrayList.next();
        localHashMap.put(a(paramString.name), a((String)paramString.value));
      }
    }
    paramArrayList = new String[localHashMap.size()];
    paramString = localHashMap.entrySet().iterator();
    int i = 0;
    while (paramString.hasNext())
    {
      paramArrayList[i] = ((String)((Map.Entry)paramString.next()).getKey());
      i += 1;
    }
    Arrays.sort(paramArrayList);
    paramString = new ArrayList();
    int k = paramArrayList.length;
    i = j;
    while (i < k)
    {
      localKVPair = paramArrayList[i];
      paramString.add(new KVPair(localKVPair, localHashMap.get(localKVPair)));
      i += 1;
    }
    return paramString;
  }

  private ArrayList<KVPair<String>> a(String paramString1, String paramString2, ArrayList<KVPair<String>> paramArrayList, a parama)
  {
    Mac localMac = null;
    long l = System.currentTimeMillis();
    switch (1.a[parama.ordinal()])
    {
    default:
      paramString2 = null;
      paramString1 = localMac;
    case 1:
    case 2:
    }
    while (true)
    {
      paramString1 = a(l, paramString1);
      paramString1.add(new KVPair("oauth_signature", paramString2));
      return paramString1;
      parama = "HMAC-SHA1";
      SecretKeySpec localSecretKeySpec = new SecretKeySpec((a(this.a.b) + '&' + a(this.a.d)).getBytes("utf-8"), "HMAC-SHA1");
      localMac = Mac.getInstance("HMAC-SHA1");
      localMac.init(localSecretKeySpec);
      paramArrayList = b(a(l, paramArrayList, "HMAC-SHA1"));
      paramString2 = new String(Base64.encode(localMac.doFinal((paramString2 + '&' + a(paramString1.toLowerCase()) + '&' + a(paramArrayList)).getBytes("utf-8")), 0)).trim();
      paramString1 = parama;
      continue;
      paramString1 = "PLAINTEXT";
      paramString2 = a(this.a.b) + '&' + a(this.a.d);
    }
  }

  private String b(ArrayList<KVPair<String>> paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.size() <= 0))
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    paramArrayList = paramArrayList.iterator();
    int i = 0;
    while (paramArrayList.hasNext())
    {
      KVPair localKVPair = (KVPair)paramArrayList.next();
      if (i > 0)
        localStringBuilder.append('&');
      localStringBuilder.append(localKVPair.name).append('=').append((String)localKVPair.value);
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public b a()
  {
    return this.a;
  }

  public String a(String paramString)
  {
    if (paramString == null)
      return "";
    return this.b.escape(paramString);
  }

  public ArrayList<KVPair<String>> a(String paramString, ArrayList<KVPair<String>> paramArrayList)
  {
    return a(paramString, paramArrayList, a.a);
  }

  public ArrayList<KVPair<String>> a(String paramString, ArrayList<KVPair<String>> paramArrayList, a parama)
  {
    return a(paramString, "POST", paramArrayList, parama);
  }

  public ArrayList<KVPair<String>> a(ArrayList<KVPair<String>> paramArrayList)
  {
    StringBuilder localStringBuilder = new StringBuilder("OAuth ");
    paramArrayList = paramArrayList.iterator();
    int i = 0;
    while (paramArrayList.hasNext())
    {
      KVPair localKVPair = (KVPair)paramArrayList.next();
      if (i > 0)
        localStringBuilder.append(',');
      String str = a((String)localKVPair.value);
      localStringBuilder.append(localKVPair.name).append("=\"").append(str).append("\"");
      i += 1;
    }
    paramArrayList = new ArrayList();
    paramArrayList.add(new KVPair("Authorization", localStringBuilder.toString()));
    paramArrayList.add(new KVPair("Content-Type", "application/x-www-form-urlencoded"));
    return paramArrayList;
  }

  public void a(String paramString1, String paramString2)
  {
    this.a.c = paramString1;
    this.a.d = paramString2;
  }

  public void a(String paramString1, String paramString2, String paramString3)
  {
    this.a.a = paramString1;
    this.a.b = paramString2;
    this.a.e = paramString3;
  }

  public ArrayList<KVPair<String>> b(String paramString, ArrayList<KVPair<String>> paramArrayList)
  {
    return b(paramString, paramArrayList, a.a);
  }

  public ArrayList<KVPair<String>> b(String paramString, ArrayList<KVPair<String>> paramArrayList, a parama)
  {
    return a(paramString, "GET", paramArrayList, parama);
  }

  public ArrayList<KVPair<String>> c(String paramString, ArrayList<KVPair<String>> paramArrayList, a parama)
  {
    return a(paramString, "PUT", paramArrayList, parama);
  }

  public static enum a
  {
  }

  public static class b
  {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.utils.a
 * JD-Core Version:    0.6.2
 */