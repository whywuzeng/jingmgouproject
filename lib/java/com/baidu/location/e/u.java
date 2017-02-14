package com.baidu.location.e;

import android.location.Location;
import android.os.Handler;

class u
  implements Runnable
{
  u(h paramh)
  {
  }

  public void run()
  {
    Location localLocation = new Location("GPS");
    h localh = this.a;
    localh.hf -= 0.0001123456789D;
    localh = this.a;
    localh.hn -= 1.02340567E-005D;
    localLocation.setTime(System.currentTimeMillis());
    localLocation.setLongitude(this.a.hf);
    localLocation.setLatitude(this.a.hn);
    localLocation.setSpeed(20.0F);
    localLocation.setBearing(35.0F);
    localh = this.a;
    localh.ho += 1;
    this.a.jdMethod_for(localLocation);
    if (this.a.ho < 20)
      h.jdMethod_if(this.a).postDelayed(this, 1000L);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.u
 * JD-Core Version:    0.6.2
 */