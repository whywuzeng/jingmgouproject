package com.ab.view.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import com.ab.util.AbImageUtil;
import com.ab.util.AbViewUtil;
import java.util.List;

public class CarouselViewAdapter extends BaseAdapter
{
  private CarouselItemView[] mCarouselItemViews = null;
  private Context mContext;
  private boolean mReflected = true;
  private List<View> mViews;

  public CarouselViewAdapter(Context paramContext, List<View> paramList, boolean paramBoolean)
  {
    this.mContext = paramContext;
    this.mViews = paramList;
    this.mReflected = paramBoolean;
    setImages();
  }

  public int getCount()
  {
    if (this.mViews == null)
      return 0;
    return this.mViews.size();
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return this.mCarouselItemViews[paramInt];
  }

  public void setImages()
  {
    this.mCarouselItemViews = new CarouselItemView[this.mViews.size()];
    int i = 0;
    if (i >= this.mViews.size())
      return;
    Object localObject1 = (View)this.mViews.get(i);
    CarouselItemView localCarouselItemView = new CarouselItemView(this.mContext);
    localCarouselItemView.setIndex(i);
    Object localObject2;
    if (this.mReflected)
    {
      localObject1 = AbImageUtil.view2Bitmap((View)localObject1);
      localObject2 = new ImageView(this.mContext);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams.gravity = 1;
      ((ImageView)localObject2).setScaleType(ImageView.ScaleType.CENTER);
      ((ImageView)localObject2).setImageBitmap(AbImageUtil.toReflectionBitmap((Bitmap)localObject1));
      localCarouselItemView.addView((View)localObject2, localLayoutParams);
      ((ImageView)localObject2).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
        }
      });
    }
    while (true)
    {
      this.mCarouselItemViews[i] = localCarouselItemView;
      i += 1;
      break;
      AbViewUtil.measureView((View)localObject1);
      localObject2 = new LinearLayout.LayoutParams(-2, -2);
      ((LinearLayout.LayoutParams)localObject2).gravity = 1;
      localCarouselItemView.addView((View)localObject1, (ViewGroup.LayoutParams)localObject2);
      ((View)localObject1).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
        }
      });
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.carousel.CarouselViewAdapter
 * JD-Core Version:    0.6.2
 */