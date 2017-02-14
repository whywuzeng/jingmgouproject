package com.ab.view.sample;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.ab.bitmap.AbImageDownloader;

public class AbNetworkImageView extends ImageView
{
  private AbImageDownloader mAbImageDownloader = null;
  private String mUrl;

  public AbNetworkImageView(Context paramContext)
  {
    this(paramContext, null);
  }

  public AbNetworkImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public AbNetworkImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void loadImageIfNecessary(boolean paramBoolean)
  {
    int j = getWidth();
    int k = getHeight();
    if ((getLayoutParams() != null) && (getLayoutParams().height == -2) && (getLayoutParams().width == -2));
    for (int i = 1; (j == 0) && (k == 0) && (i == 0); i = 0)
      return;
    if (TextUtils.isEmpty(this.mUrl))
    {
      setImageBitmap(null);
      return;
    }
    this.mAbImageDownloader.display(this, this.mUrl);
  }

  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    invalidate();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    loadImageIfNecessary(true);
  }

  public void setImageUrl(String paramString, AbImageDownloader paramAbImageDownloader)
  {
    this.mUrl = paramString;
    this.mAbImageDownloader = paramAbImageDownloader;
    loadImageIfNecessary(false);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbNetworkImageView
 * JD-Core Version:    0.6.2
 */