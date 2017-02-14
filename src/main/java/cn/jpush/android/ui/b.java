package cn.jpush.android.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class b
  implements View.OnTouchListener
{
  b(a parama)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    a.a(this.a).sendEmptyMessage(0);
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.b
 * JD-Core Version:    0.6.2
 */