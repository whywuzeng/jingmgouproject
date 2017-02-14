package kankan.wheel.widget.adapters;

import android.content.Context;
import kankan.wheel.widget.WheelAdapter;

public class AdapterWheel extends AbstractWheelTextAdapter
{
  private WheelAdapter adapter;

  public AdapterWheel(Context paramContext, WheelAdapter paramWheelAdapter)
  {
    super(paramContext);
    this.adapter = paramWheelAdapter;
  }

  public WheelAdapter getAdapter()
  {
    return this.adapter;
  }

  protected CharSequence getItemText(int paramInt)
  {
    return this.adapter.getItem(paramInt);
  }

  public int getItemsCount()
  {
    return this.adapter.getItemsCount();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     kankan.wheel.widget.adapters.AdapterWheel
 * JD-Core Version:    0.6.2
 */