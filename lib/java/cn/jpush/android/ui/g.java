package cn.jpush.android.ui;

import android.view.View;
import android.view.View.OnClickListener;
import cn.jpush.android.api.m;
import cn.jpush.android.service.ServiceInterface;

final class g
  implements View.OnClickListener
{
  g(PushActivity paramPushActivity)
  {
  }

  public final void onClick(View paramView)
  {
    ServiceInterface.a(PushActivity.a(this.a), 1006, this.a);
    m.a(this.a, PushActivity.b(this.a), 0);
    this.a.finish();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.g
 * JD-Core Version:    0.6.2
 */