package com.linj.album.view;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.linj.imageloader.DisplayImageOptions;
import com.linj.imageloader.ImageLoader;

public class ThumbnaiImageView extends FrameLayout
{
  public static final String TAG = "AlbumItemView";
  private final ImageLoader mImageLoader;
  private final DisplayImageOptions mOptions;
  private String mPath;
  private int mPosition;
  private final ViewHolder mViewHolder;

  public ThumbnaiImageView(Context paramContext, ImageLoader paramImageLoader, DisplayImageOptions paramDisplayImageOptions)
  {
    super(paramContext);
    inflate(paramContext, 2130903133, this);
    this.mViewHolder = new ViewHolder((FilterImageView)findViewById(2131231137), (CheckBox)findViewById(2131231139), (ImageView)findViewById(2131231138));
    this.mImageLoader = paramImageLoader;
    this.mOptions = paramDisplayImageOptions;
  }

  public int getPosition()
  {
    return this.mPosition;
  }

  public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    this.mViewHolder.checkBox.setOnCheckedChangeListener(paramOnCheckedChangeListener);
  }

  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.mViewHolder.imageView.setOnClickListener(paramOnClickListener);
  }

  public void setTags(String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      this.mViewHolder.checkBox.setVisibility(0);
      this.mViewHolder.checkBox.setChecked(paramBoolean2);
      if ((this.mPath == null) || (!this.mPath.equals(paramString)))
      {
        this.mImageLoader.loadImage(paramString, this.mViewHolder.imageView, this.mOptions);
        this.mPath = paramString;
        this.mViewHolder.checkBox.setTag(paramString);
        setTag(paramString);
        if (!this.mPath.contains("video"))
          break label129;
        this.mViewHolder.videoIconView.setVisibility(0);
      }
    }
    while (true)
    {
      this.mPosition = paramInt;
      return;
      this.mViewHolder.checkBox.setVisibility(8);
      break;
      label129: this.mViewHolder.videoIconView.setVisibility(8);
    }
  }

  public class ViewHolder
  {
    CheckBox checkBox;
    ImageView imageView;
    ImageView videoIconView;

    public ViewHolder(ImageView paramCheckBox, CheckBox paramImageView1, ImageView arg4)
    {
      this.imageView = paramCheckBox;
      this.checkBox = paramImageView1;
      Object localObject;
      this.videoIconView = localObject;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.album.view.ThumbnaiImageView
 * JD-Core Version:    0.6.2
 */