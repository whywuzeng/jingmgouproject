package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

public class DrawableCompat
{
  static final DrawableImpl IMPL = new BaseDrawableImpl();

  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 21)
    {
      IMPL = new LDrawableImpl();
      return;
    }
    if (i >= 19)
    {
      IMPL = new KitKatDrawableImpl();
      return;
    }
    if (i >= 11)
    {
      IMPL = new HoneycombDrawableImpl();
      return;
    }
  }

  public static boolean isAutoMirrored(Drawable paramDrawable)
  {
    return IMPL.isAutoMirrored(paramDrawable);
  }

  public static void jumpToCurrentState(Drawable paramDrawable)
  {
    IMPL.jumpToCurrentState(paramDrawable);
  }

  public static void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean)
  {
    IMPL.setAutoMirrored(paramDrawable, paramBoolean);
  }

  public static void setHotspot(Drawable paramDrawable, float paramFloat1, float paramFloat2)
  {
    IMPL.setHotspot(paramDrawable, paramFloat1, paramFloat2);
  }

  public static void setHotspotBounds(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    IMPL.setHotspotBounds(paramDrawable, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public static void setTint(Drawable paramDrawable, int paramInt)
  {
    IMPL.setTint(paramDrawable, paramInt);
  }

  public static void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList)
  {
    IMPL.setTintList(paramDrawable, paramColorStateList);
  }

  public static void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode)
  {
    IMPL.setTintMode(paramDrawable, paramMode);
  }

  static class BaseDrawableImpl
    implements DrawableCompat.DrawableImpl
  {
    public boolean isAutoMirrored(Drawable paramDrawable)
    {
      return false;
    }

    public void jumpToCurrentState(Drawable paramDrawable)
    {
    }

    public void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean)
    {
    }

    public void setHotspot(Drawable paramDrawable, float paramFloat1, float paramFloat2)
    {
    }

    public void setHotspotBounds(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
    }

    public void setTint(Drawable paramDrawable, int paramInt)
    {
    }

    public void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList)
    {
    }

    public void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode)
    {
    }
  }

  static abstract interface DrawableImpl
  {
    public abstract boolean isAutoMirrored(Drawable paramDrawable);

    public abstract void jumpToCurrentState(Drawable paramDrawable);

    public abstract void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean);

    public abstract void setHotspot(Drawable paramDrawable, float paramFloat1, float paramFloat2);

    public abstract void setHotspotBounds(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public abstract void setTint(Drawable paramDrawable, int paramInt);

    public abstract void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList);

    public abstract void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode);
  }

  static class HoneycombDrawableImpl extends DrawableCompat.BaseDrawableImpl
  {
    public void jumpToCurrentState(Drawable paramDrawable)
    {
      DrawableCompatHoneycomb.jumpToCurrentState(paramDrawable);
    }
  }

  static class KitKatDrawableImpl extends DrawableCompat.HoneycombDrawableImpl
  {
    public boolean isAutoMirrored(Drawable paramDrawable)
    {
      return DrawableCompatKitKat.isAutoMirrored(paramDrawable);
    }

    public void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean)
    {
      DrawableCompatKitKat.setAutoMirrored(paramDrawable, paramBoolean);
    }
  }

  static class LDrawableImpl extends DrawableCompat.KitKatDrawableImpl
  {
    public void setHotspot(Drawable paramDrawable, float paramFloat1, float paramFloat2)
    {
      DrawableCompatL.setHotspot(paramDrawable, paramFloat1, paramFloat2);
    }

    public void setHotspotBounds(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      DrawableCompatL.setHotspotBounds(paramDrawable, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public void setTint(Drawable paramDrawable, int paramInt)
    {
      DrawableCompatL.setTint(paramDrawable, paramInt);
    }

    public void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList)
    {
      DrawableCompatL.setTintList(paramDrawable, paramColorStateList);
    }

    public void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode)
    {
      DrawableCompatL.setTintMode(paramDrawable, paramMode);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableCompat
 * JD-Core Version:    0.6.2
 */