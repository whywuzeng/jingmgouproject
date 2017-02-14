package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.content.Intent;
import cn.sharesdk.framework.Platform;
import com.mob.tools.FakeActivity;

public class a extends FakeActivity
{
  protected AuthorizeHelper a;

  public AuthorizeHelper a()
  {
    return this.a;
  }

  public void a(AuthorizeHelper paramAuthorizeHelper)
  {
    this.a = paramAuthorizeHelper;
    super.show(paramAuthorizeHelper.getPlatform().getContext(), null);
  }

  public void show(Context paramContext, Intent paramIntent)
  {
    throw new RuntimeException("This method is deprecated, use show(AuthorizeHelper, Intent) instead");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.a
 * JD-Core Version:    0.6.2
 */