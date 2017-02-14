package com.ab.view.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.ab.util.AbImageUtil;
import java.util.List;

public class CarouselImageAdapter extends BaseAdapter
{
  private CarouselItemImage[] mCarouselImageViews = null;
  private Context mContext;
  private List<Drawable> mDrawables;
  private boolean mReflected = true;

  public CarouselImageAdapter(Context paramContext, List<Drawable> paramList, boolean paramBoolean)
  {
    this.mContext = paramContext;
    this.mDrawables = paramList;
    this.mReflected = paramBoolean;
    setImages();
  }

  public int getCount()
  {
    if (this.mDrawables == null)
      return 0;
    return this.mDrawables.size();
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
    return this.mCarouselImageViews[paramInt];
  }

  public void setImages()
  {
    this.mCarouselImageViews = new CarouselItemImage[this.mDrawables.size()];
    int i = 0;
    while (true)
    {
      if (i >= this.mDrawables.size())
        return;
      Object localObject2 = ((BitmapDrawable)this.mDrawables.get(i)).getBitmap();
      Object localObject1 = localObject2;
      if (this.mReflected)
        localObject1 = AbImageUtil.toReflectionBitmap((Bitmap)localObject2);
      localObject2 = new CarouselItemImage(this.mContext);
      ((CarouselItemImage)localObject2).setImageBitmap((Bitmap)localObject1);
      ((CarouselItemImage)localObject2).setIndex(i);
      this.mCarouselImageViews[i] = localObject2;
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.carousel.CarouselImageAdapter
 * JD-Core Version:    0.6.2
 */