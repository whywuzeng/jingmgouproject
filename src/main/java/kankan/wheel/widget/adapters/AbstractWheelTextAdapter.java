package kankan.wheel.widget.adapters;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class AbstractWheelTextAdapter extends AbstractWheelAdapter
{
  public static final int DEFAULT_TEXT_COLOR = -10987432;
  public static final int DEFAULT_TEXT_SIZE = 18;
  public static final int LABEL_COLOR = -9437072;
  protected static final int NO_RESOURCE = 0;
  public static final int TEXT_VIEW_ITEM_RESOURCE = -1;
  protected Context context;
  protected int emptyItemResourceId;
  protected LayoutInflater inflater;
  protected int itemResourceId;
  protected int itemTextResourceId;
  private int textColor = -10987432;
  private int textSize = 18;

  protected AbstractWheelTextAdapter(Context paramContext)
  {
    this(paramContext, -1);
  }

  protected AbstractWheelTextAdapter(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, 0);
  }

  protected AbstractWheelTextAdapter(Context paramContext, int paramInt1, int paramInt2)
  {
    this.context = paramContext;
    this.itemResourceId = paramInt1;
    this.itemTextResourceId = paramInt2;
    this.inflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  private TextView getTextView(View paramView, int paramInt)
  {
    if (paramInt == 0);
    try
    {
      if ((paramView instanceof TextView))
        return (TextView)paramView;
      if (paramInt != 0)
      {
        paramView = (TextView)paramView.findViewById(paramInt);
        return paramView;
      }
    }
    catch (ClassCastException paramView)
    {
      Log.e("AbstractWheelAdapter", "You must supply a resource ID for a TextView");
      throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", paramView);
    }
    return null;
  }

  private View getView(int paramInt, ViewGroup paramViewGroup)
  {
    switch (paramInt)
    {
    default:
      return this.inflater.inflate(paramInt, paramViewGroup, false);
    case 0:
      return null;
    case -1:
    }
    return new TextView(this.context);
  }

  protected void configureTextView(TextView paramTextView)
  {
    paramTextView.setTextColor(this.textColor);
    paramTextView.setGravity(17);
    paramTextView.setTextSize(this.textSize);
    paramTextView.setEllipsize(TextUtils.TruncateAt.END);
    paramTextView.setLines(1);
  }

  public View getEmptyItem(View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (paramView == null)
      localView = getView(this.emptyItemResourceId, paramViewGroup);
    if ((this.emptyItemResourceId == -1) && ((localView instanceof TextView)))
      configureTextView((TextView)localView);
    return localView;
  }

  public int getEmptyItemResource()
  {
    return this.emptyItemResourceId;
  }

  public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramInt >= 0) && (paramInt < getItemsCount()))
    {
      View localView = paramView;
      if (paramView == null)
        localView = getView(this.itemResourceId, paramViewGroup);
      TextView localTextView = getTextView(localView, this.itemTextResourceId);
      if (localTextView != null)
      {
        paramViewGroup = getItemText(paramInt);
        paramView = paramViewGroup;
        if (paramViewGroup == null)
          paramView = "";
        localTextView.setText(paramView);
        if (this.itemResourceId == -1)
          configureTextView(localTextView);
      }
      return localView;
    }
    return null;
  }

  public int getItemResource()
  {
    return this.itemResourceId;
  }

  protected abstract CharSequence getItemText(int paramInt);

  public int getItemTextResource()
  {
    return this.itemTextResourceId;
  }

  public int getTextColor()
  {
    return this.textColor;
  }

  public int getTextSize()
  {
    return this.textSize;
  }

  public void setEmptyItemResource(int paramInt)
  {
    this.emptyItemResourceId = paramInt;
  }

  public void setItemResource(int paramInt)
  {
    this.itemResourceId = paramInt;
  }

  public void setItemTextResource(int paramInt)
  {
    this.itemTextResourceId = paramInt;
  }

  public void setTextColor(int paramInt)
  {
    this.textColor = paramInt;
  }

  public void setTextSize(int paramInt)
  {
    this.textSize = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     kankan.wheel.widget.adapters.AbstractWheelTextAdapter
 * JD-Core Version:    0.6.2
 */