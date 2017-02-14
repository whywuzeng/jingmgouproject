package com.ismartgo.app.tools;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.ibeacon.MyIbeacon;
import com.ismartgo.app.ibeacon.iBeaconClass.iBeacon;
import com.ismartgo.app.ownself.view.ToastDefine;
import java.util.Vector;

public class MonitorIBeaconIsInShop
{
  iBeaconData beaconData;
  Context context;
  Vector<iBeaconClass.iBeacon> ibeacons_vector;
  MyIbeacon myIbeacon;
  ToastDefine toast;

  public MonitorIBeaconIsInShop(Context paramContext)
  {
    this.context = paramContext;
    this.ibeacons_vector = new Vector();
    this.myIbeacon = MyIbeacon.getInstance(paramContext);
    this.toast = new ToastDefine(paramContext);
  }

  public void findIbeacon()
  {
    this.ibeacons_vector = new Vector();
    iBeaconClass.iBeacon localiBeacon = SharedPreferenceUtil.getIbeaconData(this.context);
    if (localiBeacon == null)
    {
      this.beaconData.setIbeaconData(null, false);
      return;
    }
    this.ibeacons_vector.add(localiBeacon);
    Log.e("Home", ((iBeaconClass.iBeacon)this.ibeacons_vector.get(0)).name + "----------------");
    this.beaconData.setIbeaconData(this.ibeacons_vector, true);
  }

  public void setIbeaconListener(iBeaconData paramiBeaconData)
  {
    this.beaconData = paramiBeaconData;
  }

  public static abstract interface iBeaconData
  {
    public abstract void setIbeaconData(Vector<iBeaconClass.iBeacon> paramVector, boolean paramBoolean);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.MonitorIBeaconIsInShop
 * JD-Core Version:    0.6.2
 */