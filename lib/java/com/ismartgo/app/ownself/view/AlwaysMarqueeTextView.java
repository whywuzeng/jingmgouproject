package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class AlwaysMarqueeTextView extends TextView
{
  public AlwaysMarqueeTextView(Context paramContext)
  {
    super(paramContext);
  }

  public AlwaysMarqueeTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public AlwaysMarqueeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean isFocused()
  {
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.AlwaysMarqueeTextView
 * JD-Core Version:    0.6.2
 */