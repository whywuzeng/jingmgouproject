package com.baidu.vi;

import android.net.NetworkInfo;

public class c
{
  public String a;
  public int b;
  public int c;

  public c(NetworkInfo paramNetworkInfo)
  {
    this.a = paramNetworkInfo.getTypeName();
    this.b = paramNetworkInfo.getType();
    switch (1.a[paramNetworkInfo.getState().ordinal()])
    {
    default:
      this.c = 0;
      return;
    case 1:
      this.c = 2;
      return;
    case 2:
    }
    this.c = 1;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.vi.c
 * JD-Core Version:    0.6.2
 */