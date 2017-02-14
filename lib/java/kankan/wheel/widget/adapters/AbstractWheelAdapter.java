package kankan.wheel.widget.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWheelAdapter
  implements WheelViewAdapter
{
  private List<DataSetObserver> datasetObservers;

  public View getEmptyItem(View paramView, ViewGroup paramViewGroup)
  {
    return null;
  }

  protected void notifyDataChangedEvent()
  {
    Iterator localIterator;
    if (this.datasetObservers != null)
      localIterator = this.datasetObservers.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((DataSetObserver)localIterator.next()).onChanged();
    }
  }

  protected void notifyDataInvalidatedEvent()
  {
    Iterator localIterator;
    if (this.datasetObservers != null)
      localIterator = this.datasetObservers.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((DataSetObserver)localIterator.next()).onInvalidated();
    }
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (this.datasetObservers == null)
      this.datasetObservers = new LinkedList();
    this.datasetObservers.add(paramDataSetObserver);
  }

  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (this.datasetObservers != null)
      this.datasetObservers.remove(paramDataSetObserver);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     kankan.wheel.widget.adapters.AbstractWheelAdapter
 * JD-Core Version:    0.6.2
 */