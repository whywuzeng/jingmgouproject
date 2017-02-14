package com.wyy.twodimcode.view;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

public final class ViewfinderResultPointCallback
  implements ResultPointCallback
{
  private final ViewfinderView viewfinderView;

  public ViewfinderResultPointCallback(ViewfinderView paramViewfinderView)
  {
    this.viewfinderView = paramViewfinderView;
  }

  public void foundPossibleResultPoint(ResultPoint paramResultPoint)
  {
    this.viewfinderView.addPossibleResultPoint(paramResultPoint);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.view.ViewfinderResultPointCallback
 * JD-Core Version:    0.6.2
 */