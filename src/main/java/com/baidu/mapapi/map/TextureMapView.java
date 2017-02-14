package com.baidu.mapapi.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ZoomControls;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.C;
import com.baidu.platform.comapi.map.c;
import com.baidu.platform.comapi.map.i;
import java.io.IOException;
import java.io.InputStream;

public final class TextureMapView extends ViewGroup
{
  private static final String a = TextureMapView.class.getSimpleName();
  private static final SparseArray<Integer> m = new SparseArray();
  private C b;
  private BaiduMap c;
  private ImageView d;
  private Bitmap e;
  private ZoomControls f;
  private Point g;
  private Point h;
  private RelativeLayout i;
  private TextView j;
  private TextView k;
  private ImageView l;
  private float n;
  private i o;

  static
  {
    m.append(3, Integer.valueOf(2000000));
    m.append(4, Integer.valueOf(1000000));
    m.append(5, Integer.valueOf(500000));
    m.append(6, Integer.valueOf(200000));
    m.append(7, Integer.valueOf(100000));
    m.append(8, Integer.valueOf(50000));
    m.append(9, Integer.valueOf(25000));
    m.append(10, Integer.valueOf(20000));
    m.append(11, Integer.valueOf(10000));
    m.append(12, Integer.valueOf(5000));
    m.append(13, Integer.valueOf(2000));
    m.append(14, Integer.valueOf(1000));
    m.append(15, Integer.valueOf(500));
    m.append(16, Integer.valueOf(200));
    m.append(17, Integer.valueOf(100));
    m.append(18, Integer.valueOf(50));
    m.append(19, Integer.valueOf(20));
    m.append(20, Integer.valueOf(10));
  }

  public TextureMapView(Context paramContext)
  {
    super(paramContext);
    a(paramContext, null);
  }

  public TextureMapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, null);
  }

  public TextureMapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext, null);
  }

  public TextureMapView(Context paramContext, BaiduMapOptions paramBaiduMapOptions)
  {
    super(paramContext);
    a(paramContext, paramBaiduMapOptions);
  }

  private void a(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getAssets();
      int i1 = SysOSUtil.getDensityDpi();
      if (i1 >= 180)
      {
        localObject = ((AssetManager)localObject).open("logo_h.png");
      }
      else
      {
        localObject = ((AssetManager)localObject).open("logo_l.png");
        break label198;
        localObject = BitmapFactory.decodeStream((InputStream)localObject);
        Matrix localMatrix;
        if (i1 > 480)
        {
          localMatrix = new Matrix();
          localMatrix.postScale(2.0F, 2.0F);
        }
        for (this.e = Bitmap.createBitmap((Bitmap)localObject, 0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight(), localMatrix, true); ; this.e = Bitmap.createBitmap((Bitmap)localObject, 0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight(), localMatrix, true))
        {
          if (this.e == null)
            return;
          this.d = new ImageView(paramContext);
          this.d.setImageBitmap(this.e);
          addView(this.d);
          return;
          if ((i1 <= 320) || (i1 > 480))
            break;
          localMatrix = new Matrix();
          localMatrix.postScale(1.5F, 1.5F);
        }
      }
    }
    catch (Exception localException)
    {
      label198: 
      do
        while (true)
        {
          localException.printStackTrace();
          continue;
          this.e = localException;
        }
      while (localException != null);
    }
  }

  private void a(Context paramContext, BaiduMapOptions paramBaiduMapOptions)
  {
    setBackgroundColor(-1);
    BMapManager.init();
    b(paramContext, paramBaiduMapOptions);
    this.c = new BaiduMap(this.b);
    a(paramContext);
    b(paramContext);
    if ((paramBaiduMapOptions != null) && (!paramBaiduMapOptions.h))
      this.f.setVisibility(4);
    c(paramContext);
    if ((paramBaiduMapOptions != null) && (!paramBaiduMapOptions.i))
      this.i.setVisibility(4);
  }

  private void a(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams2 = paramView.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null)
      localLayoutParams1 = new ViewGroup.LayoutParams(-2, -2);
    int i1 = localLayoutParams1.width;
    if (i1 > 0)
    {
      i1 = View.MeasureSpec.makeMeasureSpec(i1, 1073741824);
      i2 = localLayoutParams1.height;
      if (i2 <= 0)
        break label78;
    }
    label78: for (int i2 = View.MeasureSpec.makeMeasureSpec(i2, 1073741824); ; i2 = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(i1, i2);
      return;
      i1 = View.MeasureSpec.makeMeasureSpec(0, 0);
      break;
    }
  }

  private void b()
  {
    boolean bool2 = false;
    float f1 = this.b.b().v().a;
    ZoomControls localZoomControls = this.f;
    if (f1 <= this.b.b().b)
    {
      bool1 = false;
      localZoomControls.setIsZoomOutEnabled(bool1);
      localZoomControls = this.f;
      if (f1 < this.b.b().a)
        break label80;
    }
    label80: for (boolean bool1 = bool2; ; bool1 = true)
    {
      localZoomControls.setIsZoomInEnabled(bool1);
      return;
      bool1 = true;
      break;
    }
  }

  private void b(Context paramContext)
  {
    this.f = new ZoomControls(paramContext);
    this.f.setOnZoomOutClickListener(new l(this));
    this.f.setOnZoomInClickListener(new m(this));
    addView(this.f);
  }

  private void b(Context paramContext, BaiduMapOptions paramBaiduMapOptions)
  {
    if (paramBaiduMapOptions == null);
    for (this.b = new C(paramContext, null); ; this.b = new C(paramContext, paramBaiduMapOptions.a()))
    {
      addView(this.b);
      this.o = new k(this);
      this.b.b().a(this.o);
      return;
    }
  }

  private void c(Context paramContext)
  {
    this.i = new RelativeLayout(paramContext);
    Object localObject = new ViewGroup.LayoutParams(-2, -2);
    this.i.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.j = new TextView(paramContext);
    localObject = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject).addRule(14);
    this.j.setTextColor(Color.parseColor("#FFFFFF"));
    this.j.setTextSize(2, 11.0F);
    this.j.setTypeface(this.j.getTypeface(), 1);
    this.j.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.j.setId(2147483647);
    this.i.addView(this.j);
    this.k = new TextView(paramContext);
    localObject = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject).width = -2;
    ((RelativeLayout.LayoutParams)localObject).height = -2;
    ((RelativeLayout.LayoutParams)localObject).addRule(14);
    this.k.setTextColor(Color.parseColor("#000000"));
    this.k.setTextSize(2, 11.0F);
    this.k.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.i.addView(this.k);
    this.l = new ImageView(paramContext);
    localObject = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject).width = -2;
    ((RelativeLayout.LayoutParams)localObject).height = -2;
    ((RelativeLayout.LayoutParams)localObject).addRule(14);
    ((RelativeLayout.LayoutParams)localObject).addRule(3, this.j.getId());
    this.l.setLayoutParams((ViewGroup.LayoutParams)localObject);
    paramContext = paramContext.getAssets();
    try
    {
      paramContext = BitmapFactory.decodeStream(paramContext.open("icon_scale.9.png"));
      localObject = paramContext.getNinePatchChunk();
      NinePatch.isNinePatchChunk((byte[])localObject);
      paramContext = new NinePatchDrawable(paramContext, (byte[])localObject, new Rect(), null);
      this.l.setBackgroundDrawable(paramContext);
      this.i.addView(this.l);
      addView(this.i);
      return;
    }
    catch (IOException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof MapViewLayoutParams))
      super.addView(paramView, paramLayoutParams);
  }

  public final BaiduMap getMap()
  {
    this.c.b = this;
    return this.c;
  }

  public final void onDestroy()
  {
    this.b.e();
    this.e.recycle();
    BMapManager.destroy();
  }

  @SuppressLint({"NewApi"})
  protected final void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt3 = getChildCount();
    paramInt1 = 0;
    if (paramInt1 < paramInt3)
    {
      View localView = getChildAt(paramInt1);
      if (localView == this.b)
        this.b.layout(0, 0, getWidth(), getHeight());
      int i1;
      int i2;
      int i3;
      int i4;
      label383: 
      do
      {
        while (true)
        {
          paramInt1 += 1;
          break;
          if (localView == this.d)
          {
            a(this.d);
            i1 = getHeight() - 5;
            i2 = this.d.getMeasuredWidth();
            i3 = this.d.getMeasuredHeight();
            this.d.layout(5, i1 - i3, i2 + 5, i1);
          }
          else if (localView == this.f)
          {
            a(this.f);
            if (this.h == null)
            {
              i1 = getHeight() - 5;
              i2 = getWidth() - 5;
              i3 = this.f.getMeasuredWidth();
              i4 = this.f.getMeasuredHeight();
              this.f.layout(i2 - i3, i1 - i4, i2, i1);
            }
            else
            {
              this.f.layout(this.h.x, this.h.y, this.h.x + this.f.getMeasuredWidth(), this.h.y + this.f.getMeasuredHeight());
            }
          }
          else
          {
            if (localView != this.i)
              break label383;
            a(this.i);
            if (this.g == null)
            {
              i1 = this.i.getMeasuredWidth();
              i2 = this.i.getMeasuredHeight();
              this.i.layout(5, paramInt4 - paramInt2 - i2 - 56, i1 + 5, paramInt4 - paramInt2 - 56);
            }
            else
            {
              this.i.layout(this.g.x, this.g.y, this.g.x + this.i.getMeasuredWidth(), this.g.y + this.i.getMeasuredHeight());
            }
          }
        }
        localObject = localView.getLayoutParams();
      }
      while (!(localObject instanceof MapViewLayoutParams));
      MapViewLayoutParams localMapViewLayoutParams = (MapViewLayoutParams)localObject;
      if (localMapViewLayoutParams.c == MapViewLayoutParams.ELayoutMode.absoluteMode);
      for (Object localObject = localMapViewLayoutParams.b; ; localObject = this.b.b().a((GeoPoint)localObject))
      {
        a(localView);
        i1 = localView.getMeasuredWidth();
        i2 = localView.getMeasuredHeight();
        float f1 = localMapViewLayoutParams.d;
        float f2 = localMapViewLayoutParams.e;
        i3 = (int)(((Point)localObject).x - f1 * i1);
        i4 = (int)(((Point)localObject).y - f2 * i2);
        i4 = localMapViewLayoutParams.f + i4;
        localView.layout(i3, i4, i3 + i1, i4 + i2);
        break;
        localObject = CoordUtil.ll2mc(localMapViewLayoutParams.a);
      }
    }
  }

  public final void onPause()
  {
    this.b.d();
  }

  public final void onResume()
  {
    this.b.c();
  }

  public void removeView(View paramView)
  {
    if (paramView == this.d)
      return;
    super.removeView(paramView);
  }

  public void setScaleControlPosition(Point paramPoint)
  {
    if (paramPoint == null);
    while ((paramPoint.x < 0) || (paramPoint.y < 0) || (paramPoint.x > getWidth()) || (paramPoint.y > getHeight()))
      return;
    this.g = paramPoint;
    requestLayout();
  }

  public void setZoomControlsPosition(Point paramPoint)
  {
    if (paramPoint == null);
    while ((paramPoint.x < 0) || (paramPoint.y < 0) || (paramPoint.x > getWidth()) || (paramPoint.y > getHeight()))
      return;
    this.h = paramPoint;
    requestLayout();
  }

  public void showScaleControl(boolean paramBoolean)
  {
    RelativeLayout localRelativeLayout = this.i;
    if (paramBoolean);
    for (int i1 = 0; ; i1 = 8)
    {
      localRelativeLayout.setVisibility(i1);
      return;
    }
  }

  public void showZoomControls(boolean paramBoolean)
  {
    ZoomControls localZoomControls = this.f;
    if (paramBoolean);
    for (int i1 = 0; ; i1 = 8)
    {
      localZoomControls.setVisibility(i1);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.TextureMapView
 * JD-Core Version:    0.6.2
 */