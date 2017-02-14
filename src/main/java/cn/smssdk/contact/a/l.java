package cn.smssdk.contact.a;

import android.text.TextUtils;

public class l extends b
{
  protected int a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return -1;
    case 0:
      return 0;
    case 1:
      return 1;
    case 2:
      return 2;
    case 3:
      return 3;
    case 4:
      return 4;
    case 5:
      return 5;
    case 6:
      return 6;
    case 7:
      return 7;
    case 8:
      return 8;
    case 9:
      return 9;
    case 10:
      return 10;
    case 11:
      return 11;
    case 12:
      return 12;
    case 13:
      return 13;
    case 14:
      return 14;
    case 15:
      return 15;
    case 16:
      return 16;
    case 17:
      return 17;
    case 18:
      return 18;
    case 19:
      return 19;
    case 20:
    }
    return 20;
  }

  public String b()
  {
    String str = b("data1");
    if (TextUtils.isEmpty(str))
      return null;
    return str.replace(" ", "").replace("-", "");
  }

  public int c()
  {
    return a(a("data2", -1));
  }

  public String d()
  {
    if (a("data2", -1) == 0)
      return b("data3");
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.contact.a.l
 * JD-Core Version:    0.6.2
 */