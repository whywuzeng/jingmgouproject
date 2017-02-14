package com.ismartgo.app.item;

import android.content.Intent;

public class TabItem
{
  private int bg;
  private int icon;
  private Intent intent;
  private String title;

  public TabItem(String paramString, int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.title = paramString;
    this.icon = paramInt1;
    this.bg = paramInt2;
    this.intent = paramIntent;
  }

  public int getBg()
  {
    return this.bg;
  }

  public int getIcon()
  {
    return this.icon;
  }

  public Intent getIntent()
  {
    return this.intent;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setBg(int paramInt)
  {
    this.bg = paramInt;
  }

  public void setIcon(int paramInt)
  {
    this.icon = paramInt;
  }

  public void setIntent(Intent paramIntent)
  {
    this.intent = paramIntent;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.item.TabItem
 * JD-Core Version:    0.6.2
 */