package com.linj.album.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import com.linj.FileOperateUtil;
import com.linj.imageloader.DisplayImageOptions;
import com.linj.imageloader.DisplayImageOptions.Builder;
import com.linj.imageloader.ImageLoader;
import com.linj.imageloader.displayer.MatrixBitmapDisplayer;
import java.util.List;

public class AlbumViewPager extends ViewPager
  implements MatrixImageView.OnMovingListener
{
  public static final String TAG = "AlbumViewPager";
  private boolean mChildIsBeingDragged = false;
  private ImageLoader mImageLoader;
  private DisplayImageOptions mOptions;
  private OnPlayVideoListener onPlayVideoListener;
  private MatrixImageView.OnSingleTapListener onSingleTapListener;

  public AlbumViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mImageLoader = ImageLoader.getInstance(paramContext);
    this.mOptions = new DisplayImageOptions.Builder().showImageOnLoading(2130837715).showImageOnFail(2130837687).cacheInMemory(true).cacheOnDisk(false).displayer(new MatrixBitmapDisplayer()).build();
  }

  public String deleteCurrentPath()
  {
    return ((ViewPagerAdapter)getAdapter()).deleteCurrentItem(getCurrentItem());
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mChildIsBeingDragged)
      return false;
    return super.onInterceptTouchEvent(paramMotionEvent);
  }

  public void setOnPlayVideoListener(OnPlayVideoListener paramOnPlayVideoListener)
  {
    this.onPlayVideoListener = paramOnPlayVideoListener;
  }

  public void setOnSingleTapListener(MatrixImageView.OnSingleTapListener paramOnSingleTapListener)
  {
    this.onSingleTapListener = paramOnSingleTapListener;
  }

  public void startDrag()
  {
    this.mChildIsBeingDragged = true;
  }

  public void stopDrag()
  {
    this.mChildIsBeingDragged = false;
  }

  public static abstract interface OnPlayVideoListener
  {
    public abstract void onPlay(String paramString);
  }

  public class ViewPagerAdapter extends PagerAdapter
  {
    private List<String> paths;
    View.OnClickListener playVideoListener = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = paramAnonymousView.getTag().toString().replace(AlbumViewPager.this.getContext().getResources().getString(2131296409), AlbumViewPager.this.getContext().getResources().getString(2131296410)).replace(".jpg", ".3gp");
        if (AlbumViewPager.this.onPlayVideoListener != null)
        {
          AlbumViewPager.this.onPlayVideoListener.onPlay(paramAnonymousView);
          return;
        }
        Toast.makeText(AlbumViewPager.this.getContext(), "onPlayVideoListener", 0).show();
      }
    };

    static
    {
      if (!AlbumViewPager.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    public ViewPagerAdapter()
    {
      Object localObject;
      this.paths = localObject;
    }

    public String deleteCurrentItem(int paramInt)
    {
      String str = (String)this.paths.get(paramInt);
      if (str != null)
      {
        FileOperateUtil.deleteSourceFile(str, AlbumViewPager.this.getContext());
        this.paths.remove(str);
        notifyDataSetChanged();
        if (this.paths.size() > 0)
          return AlbumViewPager.this.getCurrentItem() + 1 + "/" + this.paths.size();
        return "0/0";
      }
      return null;
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      ((ViewPager)paramViewGroup).removeView((View)paramObject);
    }

    public int getCount()
    {
      return this.paths.size();
    }

    public int getItemPosition(Object paramObject)
    {
      return -2;
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      View localView = AlbumViewPager.inflate(AlbumViewPager.this.getContext(), 2130903134, null);
      paramViewGroup.addView(localView);
      assert (localView != null);
      paramViewGroup = (MatrixImageView)localView.findViewById(2131231140);
      paramViewGroup.setOnMovingListener(AlbumViewPager.this);
      paramViewGroup.setOnSingleTapListener(AlbumViewPager.this.onSingleTapListener);
      String str = (String)this.paths.get(paramInt);
      ImageButton localImageButton = (ImageButton)localView.findViewById(2131231138);
      if (str.contains("video"))
        localImageButton.setVisibility(0);
      while (true)
      {
        localImageButton.setOnClickListener(this.playVideoListener);
        localImageButton.setTag(str);
        localView.setTag(str);
        AlbumViewPager.this.mImageLoader.loadImage(str, paramViewGroup, AlbumViewPager.this.mOptions);
        return localView;
        localImageButton.setVisibility(8);
      }
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.album.view.AlbumViewPager
 * JD-Core Version:    0.6.2
 */