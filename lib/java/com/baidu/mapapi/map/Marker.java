package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.f;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ArrayList<Lcom.baidu.mapapi.map.BitmapDescriptor;>;
import java.util.Iterator;

public final class Marker extends Overlay
{
  LatLng a;
  BitmapDescriptor b;
  float c;
  float d;
  boolean e;
  boolean f;
  float g;
  String h;
  int i;
  boolean j = false;
  boolean k = false;
  float l;
  int m;
  ArrayList<BitmapDescriptor> n;
  int o = 20;

  Marker()
  {
    this.q = f.c;
  }

  private void a(ArrayList<BitmapDescriptor> paramArrayList, Bundle paramBundle)
  {
    int i2 = 0;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramArrayList.iterator();
    int i1;
    while (localIterator.hasNext())
    {
      paramArrayList = (BitmapDescriptor)localIterator.next();
      ParcelItem localParcelItem = new ParcelItem();
      Bundle localBundle = new Bundle();
      paramArrayList = paramArrayList.a;
      Object localObject = ByteBuffer.allocate(paramArrayList.getWidth() * paramArrayList.getHeight() * 4);
      paramArrayList.copyPixelsToBuffer((Buffer)localObject);
      byte[] arrayOfByte = ((ByteBuffer)localObject).array();
      localBundle.putByteArray("image_data", arrayOfByte);
      localBundle.putInt("image_width", paramArrayList.getWidth());
      localBundle.putInt("image_height", paramArrayList.getHeight());
      paramArrayList = null;
      try
      {
        localObject = MessageDigest.getInstance("MD5");
        paramArrayList = (ArrayList<BitmapDescriptor>)localObject;
        paramArrayList.update(arrayOfByte, 0, arrayOfByte.length);
        paramArrayList = paramArrayList.digest();
        localObject = new StringBuilder("");
        i1 = 0;
        while (i1 < paramArrayList.length)
        {
          ((StringBuilder)localObject).append(Integer.toString((paramArrayList[i1] & 0xFF) + 256, 16).substring(1));
          i1 += 1;
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        while (true)
          localNoSuchAlgorithmException.printStackTrace();
        localBundle.putString("image_hashcode", localNoSuchAlgorithmException.toString());
        localParcelItem.setBundle(localBundle);
        localArrayList.add(localParcelItem);
      }
    }
    if (localArrayList.size() > 0)
    {
      paramArrayList = new ParcelItem[localArrayList.size()];
      i1 = i2;
      while (i1 < localArrayList.size())
      {
        paramArrayList[i1] = ((ParcelItem)localArrayList.get(i1));
        i1 += 1;
      }
      paramBundle.putParcelableArray("icons", paramArrayList);
    }
  }

  Bundle a(Bundle paramBundle)
  {
    int i2 = 1;
    super.a(paramBundle);
    Bundle localBundle = new Bundle();
    if (this.b != null)
      paramBundle.putBundle("image_info", this.b.b());
    GeoPoint localGeoPoint = CoordUtil.ll2mc(this.a);
    paramBundle.putInt("animatetype", this.m);
    paramBundle.putDouble("location_x", localGeoPoint.getLongitudeE6());
    paramBundle.putDouble("location_y", localGeoPoint.getLatitudeE6());
    if (this.e)
    {
      i1 = 1;
      paramBundle.putInt("perspective", i1);
      paramBundle.putFloat("anchor_x", this.c);
      paramBundle.putFloat("anchor_y", this.d);
      paramBundle.putFloat("rotate", this.g);
      paramBundle.putInt("y_offset", this.i);
      if (!this.j)
        break label232;
      i1 = 1;
      label143: paramBundle.putInt("isflat", i1);
      if (!this.k)
        break label237;
    }
    label232: label237: for (int i1 = i2; ; i1 = 0)
    {
      paramBundle.putInt("istop", i1);
      paramBundle.putInt("period", this.o);
      paramBundle.putFloat("alpha", this.l);
      if ((this.n != null) && (this.n.size() > 0))
        a(this.n, paramBundle);
      localBundle.putBundle("param", paramBundle);
      return paramBundle;
      i1 = 0;
      break;
      i1 = 0;
      break label143;
    }
  }

  public float getAlpha()
  {
    return this.l;
  }

  public float getAnchorX()
  {
    return this.c;
  }

  public float getAnchorY()
  {
    return this.d;
  }

  public BitmapDescriptor getIcon()
  {
    return this.b;
  }

  public ArrayList<BitmapDescriptor> getIcons()
  {
    return this.n;
  }

  public int getPeriod()
  {
    return this.o;
  }

  public LatLng getPosition()
  {
    return this.a;
  }

  public float getRotate()
  {
    return this.g;
  }

  public String getTitle()
  {
    return this.h;
  }

  public boolean isDraggable()
  {
    return this.f;
  }

  public boolean isFlat()
  {
    return this.j;
  }

  public boolean isPerspective()
  {
    return this.e;
  }

  public void setAlpha(float paramFloat)
  {
    if ((paramFloat < 0.0F) || (paramFloat > 1.0D))
    {
      this.l = 1.0F;
      return;
    }
    this.l = paramFloat;
    this.listener.b(this);
  }

  public void setAnchor(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat1 < 0.0F) || (paramFloat1 > 1.0F));
    while ((paramFloat2 < 0.0F) || (paramFloat2 > 1.0F))
      return;
    this.c = paramFloat1;
    this.d = paramFloat2;
    this.listener.b(this);
  }

  public void setDraggable(boolean paramBoolean)
  {
    this.f = paramBoolean;
    this.listener.b(this);
  }

  public void setFlat(boolean paramBoolean)
  {
    this.j = paramBoolean;
    this.listener.b(this);
  }

  public void setIcon(BitmapDescriptor paramBitmapDescriptor)
  {
    if (paramBitmapDescriptor == null)
      throw new IllegalArgumentException("marker's icon can not be null");
    this.b = paramBitmapDescriptor;
    this.listener.b(this);
  }

  public void setIcons(ArrayList<BitmapDescriptor> paramArrayList)
  {
    if (paramArrayList == null)
      throw new IllegalArgumentException("marker's icons can not be null");
    if (paramArrayList.size() == 0)
      return;
    int i1 = 0;
    while (true)
    {
      if (i1 >= paramArrayList.size())
        break label62;
      if ((paramArrayList.get(i1) == null) || (((BitmapDescriptor)paramArrayList.get(i1)).a == null))
        break;
      i1 += 1;
    }
    label62: this.n = paramArrayList;
    this.listener.b(this);
  }

  public void setPeriod(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("marker's period must be greater than zero ");
    this.o = paramInt;
    this.listener.b(this);
  }

  public void setPerspective(boolean paramBoolean)
  {
    this.e = paramBoolean;
    this.listener.b(this);
  }

  public void setPosition(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("marker's position can not be null");
    this.a = paramLatLng;
    this.listener.b(this);
  }

  public void setRotate(float paramFloat)
  {
    while (paramFloat < 0.0F)
      paramFloat += 360.0F;
    this.g = (paramFloat % 360.0F);
    this.listener.b(this);
  }

  public void setTitle(String paramString)
  {
    this.h = paramString;
  }

  public void setToTop()
  {
    this.k = true;
    this.listener.b(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Marker
 * JD-Core Version:    0.6.2
 */