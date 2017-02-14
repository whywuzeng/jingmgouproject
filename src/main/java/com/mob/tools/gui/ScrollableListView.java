package com.mob.tools.gui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

public class ScrollableListView extends ListView
  implements Scrollable
{
  private Scrollable.OnScrollListener osListener;
  private boolean pullEnable;

  public ScrollableListView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public ScrollableListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public ScrollableListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    setCacheColorHint(0);
    setSelector(new ColorDrawable());
    setDividerHeight(0);
    this.osListener = new Scrollable.OnScrollListener()
    {
      public void onScrollChanged(Scrollable paramAnonymousScrollable, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        paramAnonymousScrollable = ScrollableListView.this;
        if ((paramAnonymousInt2 <= 0) && (paramAnonymousInt4 <= 0));
        for (boolean bool = true; ; bool = false)
        {
          ScrollableListView.access$002(paramAnonymousScrollable, bool);
          return;
        }
      }
    };
  }

  protected int computeVerticalScrollOffset()
  {
    int i = super.computeVerticalScrollOffset();
    if (this.osListener != null)
      this.osListener.onScrollChanged(this, 0, i, 0, 0);
    return i;
  }

  public boolean isReadyToPull()
  {
    return this.pullEnable;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.ScrollableListView
 * JD-Core Version:    0.6.2
 */