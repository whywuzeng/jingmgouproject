package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.mob.tools.utils.R;

public class TitleLayout extends LinearLayout
{
  private ImageView btnBack;
  private TextView btnRight;
  private TextView tvTitle;

  public TitleLayout(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public TitleLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    this.btnBack = new ImageView(paramContext);
    this.btnBack.setBackgroundDrawable(null);
    int i = R.getBitmapRes(paramContext, "ssdk_back_arr");
    if (i > 0)
      this.btnBack.setImageResource(i);
    this.btnBack.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    i = R.dipToPx(paramContext, 48);
    this.btnBack.setLayoutParams(new LinearLayout.LayoutParams(i, -1));
    addView(this.btnBack);
    Object localObject = new ImageView(paramContext);
    i = R.dipToPx(paramContext, 2);
    int j = R.getBitmapRes(paramContext, "ssdk_title_div");
    if (j > 0)
      ((ImageView)localObject).setImageResource(j);
    ((ImageView)localObject).setLayoutParams(new LinearLayout.LayoutParams(i, -1));
    addView((View)localObject);
    this.tvTitle = new TextView(paramContext);
    j = R.dipToPx(paramContext, 23);
    this.tvTitle.setPadding(j, 0, j, 0);
    this.tvTitle.setSingleLine();
    this.tvTitle.setTextColor(-1);
    this.tvTitle.setTextSize(1, 18.0F);
    this.tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
    this.tvTitle.setGravity(16);
    localObject = new LinearLayout.LayoutParams(-2, -1);
    ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
    this.tvTitle.setLayoutParams((ViewGroup.LayoutParams)localObject);
    addView(this.tvTitle);
    localObject = new ImageView(paramContext);
    j = R.getBitmapRes(paramContext, "ssdk_title_div");
    if (j > 0)
      ((ImageView)localObject).setImageResource(j);
    ((ImageView)localObject).setLayoutParams(new LinearLayout.LayoutParams(i, -1));
    addView((View)localObject);
    this.btnRight = new n(this, paramContext, (ImageView)localObject);
    this.btnRight.setVisibility(4);
    this.btnRight.setBackgroundDrawable(null);
    i = R.dipToPx(paramContext, 50);
    this.btnRight.setMinWidth(i);
    this.btnRight.setTextColor(-1);
    this.btnRight.setTextSize(1, 12.0F);
    this.btnRight.setGravity(17);
    this.btnRight.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
    addView(this.btnRight);
  }

  public ImageView getBtnBack()
  {
    return this.btnBack;
  }

  public TextView getBtnRight()
  {
    return this.btnRight;
  }

  public TextView getTvTitle()
  {
    return this.tvTitle;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.TitleLayout
 * JD-Core Version:    0.6.2
 */