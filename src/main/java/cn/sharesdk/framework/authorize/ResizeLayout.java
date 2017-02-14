package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ResizeLayout extends LinearLayout
{
  private OnResizeListener a;

  public ResizeLayout(Context paramContext)
  {
    super(paramContext);
  }

  public ResizeLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void a(OnResizeListener paramOnResizeListener)
  {
    this.a = paramOnResizeListener;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.a != null)
      this.a.OnResize(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public static abstract interface OnResizeListener
  {
    public abstract void OnResize(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.ResizeLayout
 * JD-Core Version:    0.6.2
 */