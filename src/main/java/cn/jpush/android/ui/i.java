package cn.jpush.android.ui;

import android.view.View;
import android.view.View.OnClickListener;

final class i
  implements View.OnClickListener
{
  i(PushActivity paramPushActivity)
  {
  }

  public final void onClick(View paramView)
  {
    if ((PushActivity.f(this.a) != null) && (PushActivity.f(this.a).a()))
    {
      PushActivity.f(this.a).b();
      return;
    }
    PushActivity.g(this.a);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.i
 * JD-Core Version:    0.6.2
 */