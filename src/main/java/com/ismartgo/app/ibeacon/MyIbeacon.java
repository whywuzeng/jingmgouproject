package com.ismartgo.app.ibeacon;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.util.Log;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.utils.LogUtils;
import java.util.Vector;

@SuppressLint({"NewApi"})
public class MyIbeacon
{
  private static final String TAG = MyIbeacon.class.getSimpleName();
  private static BluetoothManager bluetoothManager;
  private static Context context;
  private static boolean isFirstScan = true;
  public static boolean isSupportIbeacon;
  private static BluetoothAdapter mBluetoothAdapter;
  private static MyIbeacon mIbeacon;
  private static Vector<iBeaconClass.iBeacon> mLeDevices = new Vector();
  private static BluetoothAdapter.LeScanCallback mLeScanCallback;

  public static MyIbeacon getInstance(Context paramContext)
  {
    if (paramContext != null)
      context = paramContext;
    if (mIbeacon == null);
    try
    {
      if (mIbeacon == null)
        mIbeacon = new MyIbeacon();
      return mIbeacon;
    }
    finally
    {
    }
    throw paramContext;
  }

  public void clearDevice()
  {
    if (mLeDevices != null)
      mLeDevices.clear();
  }

  public boolean closeBlutoothLe()
  {
    if (mBluetoothAdapter == null)
      return false;
    if (mBluetoothAdapter != null)
      mBluetoothAdapter.disable();
    return true;
  }

  public Vector<iBeaconClass.iBeacon> getFoundDevices()
  {
    return mLeDevices;
  }

  public void initScanCallback()
  {
    bluetoothManager = (BluetoothManager)context.getSystemService("bluetooth");
    mBluetoothAdapter = bluetoothManager.getAdapter();
    if (mBluetoothAdapter == null);
    while (mLeScanCallback != null)
      return;
    mLeScanCallback = new BluetoothAdapter.LeScanCallback()
    {
      @SuppressLint({"DefaultLocale"})
      public void onLeScan(BluetoothDevice paramAnonymousBluetoothDevice, int paramAnonymousInt, byte[] paramAnonymousArrayOfByte)
      {
        paramAnonymousBluetoothDevice = iBeaconClass.fromScanData(paramAnonymousBluetoothDevice, paramAnonymousInt, paramAnonymousArrayOfByte);
        if ((paramAnonymousBluetoothDevice == null) || (paramAnonymousBluetoothDevice.bluetoothAddress == null));
        do
        {
          do
            return;
          while ((!paramAnonymousBluetoothDevice.proximityUuid.toUpperCase().equals("FDA50693-A4E2-4FB1-AFCF-C6EB07647825")) && (!paramAnonymousBluetoothDevice.proximityUuid.toUpperCase().equals("E2C56DB5-DFFB-48D2-B060-D0F5A71096E0")));
          paramAnonymousInt = 0;
          if (paramAnonymousInt < MyIbeacon.mLeDevices.size())
            break;
          MyIbeacon.mLeDevices.add(paramAnonymousBluetoothDevice);
          Log.i("hahaha", "ibeaconAddress: " + paramAnonymousBluetoothDevice.bluetoothAddress + "  uuid: " + paramAnonymousBluetoothDevice.proximityUuid + " minor: " + paramAnonymousBluetoothDevice.minor + "  major: " + paramAnonymousBluetoothDevice.major);
        }
        while (!MyIbeacon.isFirstScan);
        MyIbeacon.isFirstScan = false;
        new Thread()
        {
          public void run()
          {
            super.run();
            SystemClock.sleep(15000L);
            String str = SharedPreferenceUtil.getLocationInfo(MyIbeacon.context).getString("city", "");
            if ((!CommonMethod.isEmpty(str)) && (BaseActivity.loginUser != null))
              AndroidApplication.getInstance().isInShop(BaseActivity.loginUser.getId(), str + "市");
          }
        }
        .start();
        return;
        paramAnonymousArrayOfByte = ((iBeaconClass.iBeacon)MyIbeacon.mLeDevices.get(paramAnonymousInt)).bluetoothAddress;
        String str = ((iBeaconClass.iBeacon)MyIbeacon.mLeDevices.get(paramAnonymousInt)).proximityUuid;
        if ((paramAnonymousArrayOfByte == null) || (paramAnonymousBluetoothDevice == null) || (paramAnonymousBluetoothDevice.bluetoothAddress == null) || (paramAnonymousArrayOfByte.trim().equals("")));
        while ((!paramAnonymousArrayOfByte.equals(paramAnonymousBluetoothDevice.bluetoothAddress)) || (!str.equals(paramAnonymousBluetoothDevice.proximityUuid)))
        {
          paramAnonymousInt += 1;
          break;
        }
      }
    };
  }

  public boolean isFirstScan()
  {
    return isFirstScan;
  }

  public boolean isOpenBlutoothLe()
  {
    return (mBluetoothAdapter != null) && (mBluetoothAdapter.getState() == 12);
  }

  public boolean isSuportBlutoohLe()
  {
    return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
  }

  public boolean openBlutoothLe()
  {
    if (mBluetoothAdapter != null)
      return mBluetoothAdapter.enable();
    return false;
  }

  public void setFirstScan(boolean paramBoolean)
  {
    isFirstScan = paramBoolean;
  }

  public void startScan()
  {
    if ((mBluetoothAdapter != null) && (mLeScanCallback != null))
    {
      boolean bool = mBluetoothAdapter.startLeScan(mLeScanCallback);
      LogUtils.i(TAG, "isStartScan: " + bool);
    }
  }

  public void stopScan()
  {
    Log.i(TAG, "stopScan");
    if ((mBluetoothAdapter != null) && (mLeScanCallback != null))
      mBluetoothAdapter.stopLeScan(mLeScanCallback);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ibeacon.MyIbeacon
 * JD-Core Version:    0.6.2
 */