package cn.jpush.android.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.jpush.android.data.d;
import cn.jpush.android.util.a;
import java.util.List;

final class e
  implements AdapterView.OnItemClickListener
{
  e(ListViewActivity paramListViewActivity, List paramList)
  {
  }

  public final void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (d)this.a.get(paramInt);
    paramAdapterView.q = false;
    paramAdapterView = a.a(this.b, paramAdapterView, false);
    this.b.startActivity(paramAdapterView);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.e
 * JD-Core Version:    0.6.2
 */