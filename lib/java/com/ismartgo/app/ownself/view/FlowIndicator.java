package com.ismartgo.app.ownself.view;

public abstract interface FlowIndicator extends ViewFlow.ViewSwitchListener
{
  public abstract void onScrolled(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract void setViewFlow(ViewFlow paramViewFlow);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.FlowIndicator
 * JD-Core Version:    0.6.2
 */