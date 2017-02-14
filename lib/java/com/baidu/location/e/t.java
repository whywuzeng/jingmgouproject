package com.baidu.location.e;

import android.os.Environment;
import java.io.File;

class t extends Thread
{
  t(h paramh)
  {
  }

  public void run()
  {
    File localFile = new File(Environment.getExternalStorageDirectory() + "/baidu/tempdata", "intime.dat");
    h.jdMethod_if(this.a, localFile, "http://itsdata.map.baidu.com/long-conn-gps/sdk.php");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.t
 * JD-Core Version:    0.6.2
 */