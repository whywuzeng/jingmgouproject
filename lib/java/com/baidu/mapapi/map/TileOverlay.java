package com.baidu.mapapi.map;

import android.util.Log;
import com.baidu.mapapi.common.Logger;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.B.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public final class TileOverlay
{
  private static final String b = TileOverlay.class.getSimpleName();
  private static int f = 0;
  BaiduMap a;
  private ExecutorService c;
  private HashMap<String, Tile> d;
  private HashSet<String> e;
  private TileProvider g;

  public TileOverlay(BaiduMap paramBaiduMap, TileProvider paramTileProvider)
  {
    this.a = paramBaiduMap;
    this.g = paramTileProvider;
    this.d = new HashMap();
    this.e = new HashSet();
    this.c = Executors.newFixedThreadPool(1);
  }

  private Tile a(String paramString)
  {
    try
    {
      if (this.d.containsKey(paramString))
      {
        Tile localTile = (Tile)this.d.get(paramString);
        this.d.remove(paramString);
        paramString = localTile;
        return paramString;
      }
      paramString = null;
    }
    finally
    {
    }
  }

  private void a(String paramString, Tile paramTile)
  {
    try
    {
      this.d.put(paramString, paramTile);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  private boolean b(String paramString)
  {
    try
    {
      boolean bool = this.e.contains(paramString);
      return bool;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  private void c(String paramString)
  {
    try
    {
      this.e.add(paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  Tile a(int paramInt1, int paramInt2, int paramInt3)
  {
    String str = paramInt1 + "_" + paramInt2 + "_" + paramInt3;
    Object localObject = a(str);
    if (localObject != null)
      return localObject;
    if ((this.a != null) && (f == 0))
    {
      localObject = this.a.getMapStatus();
      int i = (((MapStatus)localObject).a.j.b - ((MapStatus)localObject).a.j.a) / 256;
      f = ((((MapStatus)localObject).a.j.d - ((MapStatus)localObject).a.j.c) / 256 + 2) * (i + 2);
    }
    if (this.d.size() > f)
      a();
    if ((!b(str)) && (!this.c.isShutdown()));
    try
    {
      c(str);
      this.c.execute(new n(this, paramInt1, paramInt2, paramInt3, str));
      return null;
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
      while (true)
        Log.e(b, "ThreadPool excepiton");
    }
    catch (Exception localException)
    {
      while (true)
        Log.e(b, "fileDir is not legal");
    }
  }

  void a()
  {
    try
    {
      Logger.logE(b, "clearTaskSet");
      this.e.clear();
      this.d.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void b()
  {
    this.c.shutdownNow();
  }

  public boolean clearTileCache()
  {
    return this.a.b();
  }

  public void removeTileOverlay()
  {
    if (this.a == null)
      return;
    this.a.a(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.TileOverlay
 * JD-Core Version:    0.6.2
 */