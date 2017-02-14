package com.linj.album.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import com.linj.imageloader.DisplayImageOptions;
import com.linj.imageloader.DisplayImageOptions.Builder;
import com.linj.imageloader.ImageLoader;
import com.linj.imageloader.displayer.RoundedBitmapDisplayer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AlbumGridView extends GridView
{
  public static final String TAG = "AlbumView";
  private boolean mEditable;
  private ImageLoader mImageLoader;
  private DisplayImageOptions mOptions;

  public AlbumGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mImageLoader = ImageLoader.getInstance(paramContext);
    this.mOptions = new DisplayImageOptions.Builder().showImageOnLoading(2130837715).showImageOnFail(2130837687).cacheInMemory(true).cacheOnDisk(false).displayer(new RoundedBitmapDisplayer(20)).build();
    setBackgroundColor(-1);
    setVerticalScrollBarEnabled(false);
  }

  public boolean getEditable()
  {
    return this.mEditable;
  }

  public Set<String> getSelectedItems()
  {
    return ((AlbumViewAdapter)getAdapter()).getSelectedItems();
  }

  public void notifyDataSetChanged()
  {
    ((AlbumViewAdapter)getAdapter()).notifyDataSetChanged();
  }

  public void selectAll(OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    ((AlbumViewAdapter)getAdapter()).selectAll(paramOnCheckedChangeListener);
  }

  public void setEditable(boolean paramBoolean)
  {
    this.mEditable = paramBoolean;
    ((AlbumViewAdapter)getAdapter()).notifyDataSetChanged(null);
  }

  public void setEditable(boolean paramBoolean, OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    this.mEditable = paramBoolean;
    ((AlbumViewAdapter)getAdapter()).notifyDataSetChanged(paramOnCheckedChangeListener);
  }

  public void unSelectAll(OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    ((AlbumViewAdapter)getAdapter()).unSelectAll(paramOnCheckedChangeListener);
  }

  public class AlbumViewAdapter extends BaseAdapter
    implements View.OnClickListener, CompoundButton.OnCheckedChangeListener
  {
    Set<String> itemSelectedSet = new HashSet();
    AlbumGridView.OnCheckedChangeListener listener = null;
    List<String> mPaths;

    public AlbumViewAdapter()
    {
      Object localObject;
      this.mPaths = localObject;
    }

    public int getCount()
    {
      return this.mPaths.size();
    }

    public String getItem(int paramInt)
    {
      return (String)this.mPaths.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return 0L;
    }

    public Set<String> getSelectedItems()
    {
      return this.itemSelectedSet;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = (ThumbnaiImageView)paramView;
      paramView = paramViewGroup;
      if (paramViewGroup == null)
        paramView = new ThumbnaiImageView(AlbumGridView.this.getContext(), AlbumGridView.this.mImageLoader, AlbumGridView.this.mOptions);
      paramView.setOnCheckedChangeListener(this);
      paramView.setOnClickListener(this);
      paramViewGroup = getItem(paramInt);
      paramView.setTags(paramViewGroup, paramInt, AlbumGridView.this.mEditable, this.itemSelectedSet.contains(paramViewGroup));
      return paramView;
    }

    public void notifyDataSetChanged(AlbumGridView.OnCheckedChangeListener paramOnCheckedChangeListener)
    {
      this.itemSelectedSet = new HashSet();
      this.listener = paramOnCheckedChangeListener;
      super.notifyDataSetChanged();
    }

    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      if (paramCompoundButton.getTag() == null);
      while (true)
      {
        return;
        if (paramBoolean)
          this.itemSelectedSet.add(paramCompoundButton.getTag().toString());
        while (this.listener != null)
        {
          this.listener.onCheckedChanged(this.itemSelectedSet);
          return;
          this.itemSelectedSet.remove(paramCompoundButton.getTag().toString());
        }
      }
    }

    public void onClick(View paramView)
    {
      if (AlbumGridView.this.getOnItemClickListener() != null)
      {
        paramView = (ThumbnaiImageView)paramView.getParent().getParent();
        AlbumGridView.this.getOnItemClickListener().onItemClick(AlbumGridView.this, paramView, paramView.getPosition(), 0L);
      }
    }

    public void selectAll(AlbumGridView.OnCheckedChangeListener paramOnCheckedChangeListener)
    {
      Iterator localIterator = this.mPaths.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          this.listener = paramOnCheckedChangeListener;
          super.notifyDataSetChanged();
          if (paramOnCheckedChangeListener != null)
            paramOnCheckedChangeListener.onCheckedChanged(this.itemSelectedSet);
          return;
        }
        String str = (String)localIterator.next();
        this.itemSelectedSet.add(str);
      }
    }

    public void unSelectAll(AlbumGridView.OnCheckedChangeListener paramOnCheckedChangeListener)
    {
      notifyDataSetChanged(paramOnCheckedChangeListener);
      if (paramOnCheckedChangeListener != null)
        paramOnCheckedChangeListener.onCheckedChanged(this.itemSelectedSet);
    }
  }

  public static abstract interface OnCheckedChangeListener
  {
    public abstract void onCheckedChanged(Set<String> paramSet);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.album.view.AlbumGridView
 * JD-Core Version:    0.6.2
 */