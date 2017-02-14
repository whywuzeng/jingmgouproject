package cn.smssdk.utils;

import android.content.Context;
import cn.smssdk.framework.utils.e;
import java.util.ArrayList;
import java.util.HashMap;

public class b
{
  private static b a;
  private e b;

  private b(Context paramContext, String paramString)
  {
    this.b = e.a(paramContext);
    this.b.a(paramString, 1);
  }

  public static b a(Context paramContext, String paramString)
  {
    if (a == null)
      a = new b(paramContext, paramString);
    return a;
  }

  public String a()
  {
    return this.b.a("duid");
  }

  public void a(String paramString)
  {
    this.b.a("duid", paramString);
  }

  public void a(ArrayList<HashMap<String, Object>> paramArrayList)
  {
    this.b.a("bufferedContacts", paramArrayList);
  }

  public void a(String[] paramArrayOfString)
  {
    this.b.a("bufferedContactPhones", paramArrayOfString);
  }

  public String b()
  {
    return this.b.a("token");
  }

  public void b(String paramString)
  {
    this.b.a("token", paramString);
  }

  public void b(ArrayList<HashMap<String, Object>> paramArrayList)
  {
    try
    {
      this.b.a("bufferedFriends", paramArrayList);
      return;
    }
    finally
    {
    }
    throw paramArrayList;
  }

  public void c()
  {
    this.b.d("bufferedNewFriends");
    this.b.d("bufferedFriends");
    this.b.d("lastRequestNewFriendsTime");
    this.b.d("bufferedContactPhones");
  }

  public void c(String paramString)
  {
    this.b.a("bufferedContactsSignature", paramString);
  }

  public void c(ArrayList<HashMap<String, Object>> paramArrayList)
  {
    this.b.a("bufferedNewFriends", paramArrayList);
  }

  public String d()
  {
    return this.b.a("bufferedContactsSignature");
  }

  public ArrayList<HashMap<String, Object>> e()
  {
    try
    {
      Object localObject1 = this.b.c("bufferedFriends");
      if (localObject1 != null)
      {
        localObject1 = (ArrayList)localObject1;
        return localObject1;
      }
      localObject1 = new ArrayList();
      return localObject1;
    }
    finally
    {
    }
  }

  public ArrayList<HashMap<String, Object>> f()
  {
    Object localObject = this.b.c("bufferedNewFriends");
    if (localObject != null)
      return (ArrayList)localObject;
    return new ArrayList();
  }

  public long g()
  {
    return this.b.b("lastRequestNewFriendsTime");
  }

  public void h()
  {
    this.b.a("lastRequestNewFriendsTime", Long.valueOf(System.currentTimeMillis()));
  }

  public String[] i()
  {
    Object localObject = this.b.c("bufferedContactPhones");
    if (localObject != null)
      return (String[])localObject;
    return new String[0];
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.utils.b
 * JD-Core Version:    0.6.2
 */