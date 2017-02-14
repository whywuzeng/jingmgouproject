package kankan.wheel.widget.adapters;

import android.content.Context;

public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter
{
  private T[] items;

  public ArrayWheelAdapter(Context paramContext, T[] paramArrayOfT)
  {
    super(paramContext);
    this.items = paramArrayOfT;
  }

  public CharSequence getItemText(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.items.length))
    {
      Object localObject = this.items[paramInt];
      if ((localObject instanceof CharSequence))
        return (CharSequence)localObject;
      return localObject.toString();
    }
    return null;
  }

  public int getItemsCount()
  {
    return this.items.length;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     kankan.wheel.widget.adapters.ArrayWheelAdapter
 * JD-Core Version:    0.6.2
 */