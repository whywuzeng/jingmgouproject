package com.baidu.mapapi.map;

import android.graphics.Typeface;
import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;

public final class TextOptions extends OverlayOptions
{
  public static final int ALIGN_BOTTOM = 16;
  public static final int ALIGN_CENTER_HORIZONTAL = 4;
  public static final int ALIGN_CENTER_VERTICAL = 32;
  public static final int ALIGN_LEFT = 1;
  public static final int ALIGN_RIGHT = 2;
  public static final int ALIGN_TOP = 8;
  int a;
  boolean b = true;
  Bundle c;
  private String d;
  private LatLng e;
  private int f;
  private int g = -16777216;
  private int h = 12;
  private Typeface i;
  private int j = 4;
  private int k = 32;
  private float l;

  Overlay a()
  {
    Text localText = new Text();
    localText.s = this.b;
    localText.r = this.a;
    localText.t = this.c;
    localText.a = this.d;
    localText.b = this.e;
    localText.c = this.f;
    localText.d = this.g;
    localText.e = this.h;
    localText.f = this.i;
    localText.g = this.j;
    localText.h = this.k;
    localText.i = this.l;
    return localText;
  }

  public TextOptions align(int paramInt1, int paramInt2)
  {
    this.j = paramInt1;
    this.k = paramInt2;
    return this;
  }

  public TextOptions bgColor(int paramInt)
  {
    this.f = paramInt;
    return this;
  }

  public TextOptions extraInfo(Bundle paramBundle)
  {
    this.c = paramBundle;
    return this;
  }

  public TextOptions fontColor(int paramInt)
  {
    this.g = paramInt;
    return this;
  }

  public TextOptions fontSize(int paramInt)
  {
    this.h = paramInt;
    return this;
  }

  public float getAlignX()
  {
    return this.j;
  }

  public float getAlignY()
  {
    return this.k;
  }

  public int getBgColor()
  {
    return this.f;
  }

  public Bundle getExtraInfo()
  {
    return this.c;
  }

  public int getFontColor()
  {
    return this.g;
  }

  public int getFontSize()
  {
    return this.h;
  }

  public LatLng getPosition()
  {
    return this.e;
  }

  public float getRotate()
  {
    return this.l;
  }

  public String getText()
  {
    return this.d;
  }

  public Typeface getTypeface()
  {
    return this.i;
  }

  public int getZIndex()
  {
    return this.a;
  }

  public boolean isVisible()
  {
    return this.b;
  }

  public TextOptions position(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("position can not be null");
    this.e = paramLatLng;
    return this;
  }

  public TextOptions rotate(float paramFloat)
  {
    this.l = paramFloat;
    return this;
  }

  public TextOptions text(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")))
      throw new IllegalArgumentException("text can not be null or empty");
    this.d = paramString;
    return this;
  }

  public TextOptions typeface(Typeface paramTypeface)
  {
    this.i = paramTypeface;
    return this;
  }

  public TextOptions visible(boolean paramBoolean)
  {
    this.b = paramBoolean;
    return this;
  }

  public TextOptions zIndex(int paramInt)
  {
    this.a = paramInt;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.TextOptions
 * JD-Core Version:    0.6.2
 */