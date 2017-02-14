package com.baidu.mapapi.map;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.f;
import vi.com.gdi.bgl.android.java.EnvDrawText;

public final class Text extends Overlay
{
  private static final String k = Text.class.getSimpleName();
  String a;
  LatLng b;
  int c;
  int d;
  int e;
  Typeface f;
  int g;
  int h;
  float i;
  int j;

  Text()
  {
    this.q = f.e;
  }

  Bundle a()
  {
    if (this.f != null)
      EnvDrawText.removeFontCache(this.f.hashCode());
    return super.a();
  }

  Bundle a(Bundle paramBundle)
  {
    float f2 = 0.5F;
    super.a(paramBundle);
    if (this.b == null)
      throw new IllegalStateException("when you add a text overlay, you must provide text and the position info.");
    paramBundle.putString("text", this.a);
    GeoPoint localGeoPoint = CoordUtil.ll2mc(this.b);
    paramBundle.putDouble("location_x", localGeoPoint.getLongitudeE6());
    paramBundle.putDouble("location_y", localGeoPoint.getLatitudeE6());
    int m = this.d;
    int n = this.d;
    int i1 = this.d;
    paramBundle.putInt("font_color", Color.argb(m >>> 24, this.d & 0xFF, i1 >> 8 & 0xFF, n >> 16 & 0xFF));
    m = this.c;
    n = this.c;
    i1 = this.c;
    paramBundle.putInt("bg_color", Color.argb(m >>> 24, this.c & 0xFF, i1 >> 8 & 0xFF, n >> 16 & 0xFF));
    paramBundle.putInt("font_size", this.e);
    if (this.f != null)
    {
      EnvDrawText.registFontCache(this.f.hashCode(), this.f);
      paramBundle.putInt("type_face", this.f.hashCode());
    }
    float f1;
    switch (this.g)
    {
    case 3:
    default:
      f1 = 0.5F;
      paramBundle.putFloat("align_x", f1);
      f1 = f2;
      switch (this.h)
      {
      default:
        f1 = f2;
      case 32:
      case 8:
      case 16:
      }
      break;
    case 4:
    case 1:
    case 2:
    }
    while (true)
    {
      paramBundle.putFloat("align_y", f1);
      paramBundle.putFloat("rotate", this.i);
      paramBundle.putInt("update", this.j);
      return paramBundle;
      f1 = 0.5F;
      break;
      f1 = 0.0F;
      break;
      f1 = 1.0F;
      break;
      f1 = 0.0F;
      continue;
      f1 = 1.0F;
    }
  }

  public float getAlignX()
  {
    return this.g;
  }

  public float getAlignY()
  {
    return this.h;
  }

  public int getBgColor()
  {
    return this.c;
  }

  public int getFontColor()
  {
    return this.d;
  }

  public int getFontSize()
  {
    return this.e;
  }

  public LatLng getPosition()
  {
    return this.b;
  }

  public float getRotate()
  {
    return this.i;
  }

  public String getText()
  {
    return this.a;
  }

  public Typeface getTypeface()
  {
    return this.f;
  }

  public void setAlign(int paramInt1, int paramInt2)
  {
    this.g = paramInt1;
    this.h = paramInt2;
    this.j = 1;
    this.listener.b(this);
  }

  public void setBgColor(int paramInt)
  {
    this.c = paramInt;
    this.j = 1;
    this.listener.b(this);
  }

  public void setFontColor(int paramInt)
  {
    this.d = paramInt;
    this.j = 1;
    this.listener.b(this);
  }

  public void setFontSize(int paramInt)
  {
    this.e = paramInt;
    this.j = 1;
    this.listener.b(this);
  }

  public void setPosition(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("position can not be null");
    this.b = paramLatLng;
    this.j = 1;
    this.listener.b(this);
  }

  public void setRotate(float paramFloat)
  {
    this.i = paramFloat;
    this.j = 1;
    this.listener.b(this);
  }

  public void setText(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")))
      throw new IllegalArgumentException("text can not be null or empty");
    this.a = paramString;
    this.j = 1;
    this.listener.b(this);
  }

  public void setTypeface(Typeface paramTypeface)
  {
    this.f = paramTypeface;
    this.j = 1;
    this.listener.b(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Text
 * JD-Core Version:    0.6.2
 */