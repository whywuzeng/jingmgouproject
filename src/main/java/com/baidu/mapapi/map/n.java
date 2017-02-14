package com.baidu.mapapi.map;

import android.util.Log;
import java.util.HashSet;

class n
  implements Runnable
{
  n(TileOverlay paramTileOverlay, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
  }

  public void run()
  {
    Tile localTile = ((FileTileProvider)TileOverlay.a(this.e)).getTile(this.a, this.b, this.c);
    if (localTile != null)
      if ((localTile.height == 256) && (localTile.height == 256))
      {
        String str = this.a + "_" + this.b + "_" + this.c;
        TileOverlay.a(this.e, str, localTile);
      }
    while (true)
    {
      TileOverlay.b(this.e).remove(this.d);
      return;
      Log.e(TileOverlay.c(), "FileTile pic must be 256 * 256");
      continue;
      Log.e(TileOverlay.c(), "FileTile pic is null");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.n
 * JD-Core Version:    0.6.2
 */