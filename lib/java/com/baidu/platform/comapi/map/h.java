package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import com.baidu.platform.comjni.map.basemap.a;
import java.util.Iterator;
import java.util.List;

class h extends Handler
{
  h(g paramg)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    if (((Long)paramMessage.obj).longValue() != g.a(this.a).g);
    while (true)
    {
      return;
      if (paramMessage.what == 4000)
      {
        Iterator localIterator = g.a(this.a).e.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
            break label310;
          i locali = (i)localIterator.next();
          Object localObject = null;
          if (paramMessage.arg2 == 1)
          {
            int[] arrayOfInt = new int[g.b(this.a) * g.c(this.a)];
            localObject = new int[g.b(this.a) * g.c(this.a)];
            if (g.a(this.a).f == null)
              break;
            arrayOfInt = g.a(this.a).f.a(arrayOfInt, g.b(this.a), g.c(this.a));
            int i = 0;
            while (i < g.c(this.a))
            {
              int j = 0;
              while (j < g.b(this.a))
              {
                int k = arrayOfInt[(g.b(this.a) * i + j)];
                localObject[((g.c(this.a) - i - 1) * g.b(this.a) + j)] = (k & 0xFF00FF00 | k << 16 & 0xFF0000 | k >> 16 & 0xFF);
                j += 1;
              }
              i += 1;
            }
            localObject = Bitmap.createBitmap((int[])localObject, g.b(this.a), g.c(this.a), Bitmap.Config.ARGB_8888);
          }
          locali.a((Bitmap)localObject);
        }
      }
      else
      {
        label310: if (paramMessage.what == 39)
        {
          if (g.a(this.a) != null)
          {
            if (paramMessage.arg1 == 100)
              g.a(this.a).u();
            while ((!g.a(this.a).h) && (g.c(this.a) > 0) && (g.b(this.a) > 0) && (g.a(this.a).b(0, 0) != null))
            {
              g.a(this.a).h = true;
              paramMessage = g.a(this.a).e.iterator();
              while (paramMessage.hasNext())
                ((i)paramMessage.next()).b();
              if (paramMessage.arg1 == 200)
              {
                g.a(this.a).B();
              }
              else if (paramMessage.arg1 == 1)
              {
                this.a.requestRender();
              }
              else if (paramMessage.arg1 == 0)
              {
                this.a.requestRender();
                if ((!g.a(this.a).c()) && (this.a.getRenderMode() != 0))
                  this.a.setRenderMode(0);
              }
            }
            paramMessage = g.a(this.a).e.iterator();
            while (paramMessage.hasNext())
              ((i)paramMessage.next()).a();
          }
        }
        else if (paramMessage.what == 41)
        {
          if ((g.a(this.a) != null) && ((g.a(this.a).k) || (g.a(this.a).l)))
          {
            paramMessage = g.a(this.a).e.iterator();
            while (paramMessage.hasNext())
              ((i)paramMessage.next()).b(g.a(this.a).v());
          }
        }
        else if (paramMessage.what == 999)
        {
          paramMessage = g.a(this.a).e.iterator();
          while (paramMessage.hasNext())
            ((i)paramMessage.next()).d();
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.h
 * JD-Core Version:    0.6.2
 */