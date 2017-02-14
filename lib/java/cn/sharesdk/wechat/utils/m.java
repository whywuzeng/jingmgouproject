package cn.sharesdk.wechat.utils;

import android.os.Bundle;

public abstract class m
{
  public String c;

  public abstract int a();

  public void a(Bundle paramBundle)
  {
    paramBundle.putInt("_wxapi_command_type", a());
    paramBundle.putString("_wxapi_basereq_transaction", this.c);
  }

  public abstract boolean b();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.m
 * JD-Core Version:    0.6.2
 */