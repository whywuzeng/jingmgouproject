package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import com.baidu.platform.comjni.map.basemap.a;
import java.util.Iterator;
import java.util.List;

class D extends Handler
{
  D(C paramC)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    if (((Long)paramMessage.obj).longValue() != C.a(this.a).g);
    while (true)
    {
      return;
      if (paramMessage.what == 4000)
      {
        Iterator localIterator = C.a(this.a).e.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
            break label258;
          i locali = (i)localIterator.next();
          Object localObject = null;
          if (paramMessage.arg2 == 1)
          {
            int[] arrayOfInt = new int[C.a * C.b];
            localObject = new int[C.a * C.b];
            if (C.a(this.a).f == null)
              break;
            arrayOfInt = C.a(this.a).f.a(arrayOfInt, C.a, C.b);
            int i = 0;
            while (i < C.b)
            {
              int j = 0;
              while (j < C.a)
              {
                int k = arrayOfInt[(C.a * i + j)];
                localObject[((C.b - i - 1) * C.a + j)] = (k & 0xFF00FF00 | k << 16 & 0xFF0000 | k >> 16 & 0xFF);
                j += 1;
              }
              i += 1;
            }
            localObject = Bitmap.createBitmap((int[])localObject, C.a, C.b, Bitmap.Config.ARGB_8888);
          }
          locali.a((Bitmap)localObject);
        }
      }
      else
      {
        label258: if (paramMessage.what == 39)
        {
          if (C.a(this.a) != null)
          {
            if (paramMessage.arg1 == 100)
              C.a(this.a).u();
            while ((!C.a(this.a).h) && (C.b > 0) && (C.a > 0) && (C.a(this.a).b(0, 0) != null))
            {
              C.a(this.a).h = true;
              paramMessage = C.a(this.a).e.iterator();
              while (paramMessage.hasNext())
                ((i)paramMessage.next()).b();
              if (paramMessage.arg1 == 200)
                C.a(this.a).B();
              else if (paramMessage.arg1 == 1)
              {
                if (C.b(this.a) != null)
                  C.b(this.a).a();
              }
              else if ((paramMessage.arg1 == 0) && (C.b(this.a) != null))
                C.b(this.a).a();
            }
            paramMessage = C.a(this.a).e.iterator();
            while (paramMessage.hasNext())
              ((i)paramMessage.next()).a();
          }
        }
        else if (paramMessage.what == 41)
        {
          if ((C.a(this.a) != null) && ((C.a(this.a).k) || (C.a(this.a).l)))
          {
            paramMessage = C.a(this.a).e.iterator();
            while (paramMessage.hasNext())
              ((i)paramMessage.next()).b(C.a(this.a).v());
          }
        }
        else if (paramMessage.what == 999)
        {
          paramMessage = C.a(this.a).e.iterator();
          while (paramMessage.hasNext())
            ((i)paramMessage.next()).d();
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.D
 * JD-Core Version:    0.6.2
 */