package com.ab.view.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class AbScrollTextView extends TextView
{
  public AbScrollTextView(Context paramContext)
  {
    super(paramContext);
  }

  public AbScrollTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public AbScrollTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean isFocused()
  {
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbScrollTextView
 * JD-Core Version:    0.6.2
 */