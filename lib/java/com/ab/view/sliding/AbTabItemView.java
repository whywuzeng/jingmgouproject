package com.ab.view.sliding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AbTabItemView extends LinearLayout
{
  private Context mContext;
  private int mIndex;
  private TextView mTextView;

  public AbTabItemView(Context paramContext)
  {
    this(paramContext, null);
  }

  public AbTabItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setOrientation(0);
    this.mContext = paramContext;
    this.mTextView = new TextView(paramContext);
    this.mTextView.setGravity(17);
    this.mTextView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    this.mTextView.setFocusable(true);
    this.mTextView.setPadding(12, 5, 12, 5);
    this.mTextView.setSingleLine();
    addView(this.mTextView);
  }

  public int getIndex()
  {
    return this.mIndex;
  }

  public TextView getTextView()
  {
    return this.mTextView;
  }

  public void init(int paramInt, String paramString)
  {
    this.mIndex = paramInt;
    this.mTextView.setText(paramString);
  }

  public void setTabBackgroundResource(int paramInt)
  {
    setBackgroundResource(paramInt);
  }

  public void setTabCompoundDrawables(Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
  {
    if (paramDrawable1 != null)
      paramDrawable1.setBounds(0, 0, paramDrawable1.getIntrinsicWidth(), paramDrawable1.getIntrinsicHeight());
    if (paramDrawable2 != null)
      paramDrawable2.setBounds(0, 0, paramDrawable2.getIntrinsicWidth(), paramDrawable2.getIntrinsicHeight());
    if (paramDrawable3 != null)
      paramDrawable3.setBounds(0, 0, paramDrawable3.getIntrinsicWidth(), paramDrawable3.getIntrinsicHeight());
    if (paramDrawable4 != null)
      paramDrawable4.setBounds(0, 0, paramDrawable4.getIntrinsicWidth(), paramDrawable4.getIntrinsicHeight());
    this.mTextView.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }

  public void setTabTextColor(int paramInt)
  {
    this.mTextView.setTextColor(paramInt);
  }

  public void setTabTextSize(int paramInt)
  {
    this.mTextView.setTextSize(paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sliding.AbTabItemView
 * JD-Core Version:    0.6.2
 */