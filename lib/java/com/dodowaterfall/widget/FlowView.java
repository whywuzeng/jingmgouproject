package com.dodowaterfall.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class FlowView extends ImageView
  implements View.OnClickListener, View.OnLongClickListener
{
  private int ItemWidth;
  private String _url;
  public Bitmap bitmap;
  private int columnIndex;
  private Context context;
  private String fileName;
  private Handler viewHandler;

  public FlowView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    Init();
  }

  public FlowView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    Init();
  }

  public FlowView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.context = paramContext;
    Init();
  }

  private void Init()
  {
    setOnClickListener(this);
    setOnLongClickListener(this);
    setAdjustViewBounds(true);
  }

  public int getColumnIndex()
  {
    return this.columnIndex;
  }

  public String getFileName()
  {
    return this.fileName;
  }

  public int getItemWidth()
  {
    return this.ItemWidth;
  }

  public Handler getViewHandler()
  {
    return this.viewHandler;
  }

  public String get_url()
  {
    return this._url;
  }

  public void onClick(View paramView)
  {
    Log.d("FlowView", "Click");
    Toast.makeText(this.context, "单击：" + getId(), 0).show();
  }

  public boolean onLongClick(View paramView)
  {
    Log.d("FlowView", "LongClick");
    Toast.makeText(this.context, "长按：" + getId(), 0).show();
    return true;
  }

  public void recycle()
  {
    setImageBitmap(null);
    if ((this.bitmap == null) || (this.bitmap.isRecycled()))
      return;
    this.bitmap.recycle();
    this.bitmap = null;
  }

  public void setColumnIndex(int paramInt)
  {
    this.columnIndex = paramInt;
  }

  public void setFileName(String paramString)
  {
    this.fileName = paramString;
  }

  public void setItemWidth(int paramInt)
  {
    this.ItemWidth = paramInt;
  }

  public FlowView setViewHandler(Handler paramHandler)
  {
    this.viewHandler = paramHandler;
    return this;
  }

  public void set_url(String paramString)
  {
    this._url = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.dodowaterfall.widget.FlowView
 * JD-Core Version:    0.6.2
 */