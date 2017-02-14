package android.support.v4.internal.view;

import android.view.Menu;

public abstract interface SupportMenu extends Menu
{
  public static final int CATEGORY_MASK = -65536;
  public static final int CATEGORY_SHIFT = 16;
  public static final int USER_MASK = 65535;
  public static final int USER_SHIFT = 0;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.internal.view.SupportMenu
 * JD-Core Version:    0.6.2
 */