package com.ismartgo.beacon;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.ismartgo.beacon.db.DBDao;
import com.ismartgo.beacon.http.HttpConstants;
import com.ismartgo.beacon.http.HttpJsonParse;
import com.ismartgo.beacon.http.HttpRequestParam;
import com.ismartgo.beacon.http.RequestTask;
import com.ismartgo.beacon.pojo.BeaconActivityInfo;
import com.ismartgo.beacon.util.CalendarUtil;
import com.ismartgo.beacon.util.CommonMethod;
import com.ismartgo.beacon.util.DeviceInfoUtil;
import com.ismartgo.beacon.util.NotificationUtil;
import com.ismartgo.beacon.view.IbeaconWebActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

public class SmartgoBeaconUtil
{
  public static final String DEVICE_FLAG = "Android";
  public static final String KEY = "SMARTGO_APPKEY";
  public static final String TAG = "smartgo_beacon";
  private static Map<String, Boolean> beaconRequestMap = new HashMap();
  private static BluetoothManager bluetoothManager;
  private static Context context;
  private static boolean isRequest;
  private static BluetoothAdapter mBluetoothAdapter;
  private static List<IBeaconClass.iBeacon> mLeDevices = new ArrayList();
  private static BluetoothAdapter.LeScanCallback mLeScanCallback;
  private static SmartgoBeaconUtil smartgoBean;

  private void checkIbeacon(IBeaconClass.iBeacon paramiBeacon, boolean paramBoolean)
  {
  }

  public static SmartgoBeaconUtil getInstance(Context paramContext)
  {
    context = paramContext;
    if (smartgoBean == null);
    try
    {
      smartgoBean = new SmartgoBeaconUtil();
      return smartgoBean;
    }
    finally
    {
    }
    throw paramContext;
  }

  private void reqeustActivityInfo(IBeaconClass.iBeacon paramiBeacon)
  {
    try
    {
      String str = paramiBeacon.proximityUuid + "_" + paramiBeacon.major + "_" + paramiBeacon.minor;
      try
      {
        beaconRequestMap.put(str, Boolean.valueOf(true));
        Object localObject = new JSONArray();
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("uuid", paramiBeacon.proximityUuid);
        localJSONObject.put("major", paramiBeacon.major);
        localJSONObject.put("minor", paramiBeacon.minor);
        ((JSONArray)localObject).put(localJSONObject);
        localObject = HttpRequestParam.getBeaconInfoParam(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("SMARTGO_APPKEY"), "ismartgo.com", "Android", ((JSONArray)localObject).toString(), DeviceInfoUtil.getUDID(context));
        new RequestTask(context, HttpConstants.BEACON_INFO, (Map)localObject, new BeaconHandler(paramiBeacon)).executeOnExecutor(Executors.newCachedThreadPool(), null);
        return;
      }
      catch (Exception paramiBeacon)
      {
        while (true)
        {
          paramiBeacon.printStackTrace();
          beaconRequestMap.put(str, Boolean.valueOf(false));
        }
      }
    }
    finally
    {
    }
    throw paramiBeacon;
  }

  public static void showDialog(Context paramContext, BeaconActivityInfo paramBeaconActivityInfo)
  {
    Intent localIntent = new Intent(paramContext, IbeaconWebActivity.class);
    localIntent.putExtra("activity_info", paramBeaconActivityInfo);
    paramContext.startActivity(localIntent);
  }

  private static void showDialog(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.startActivity(new Intent(paramContext, IbeaconWebActivity.class));
  }

  public boolean closeBlutoothLe()
  {
    if (mBluetoothAdapter == null)
      return false;
    if (mBluetoothAdapter != null)
      mBluetoothAdapter.disable();
    return true;
  }

  public void initLbs()
  {
    if (!isSuportBlutoohLe())
    {
      Log.i("smartgo_beacon", "设备不支持beacon扫描");
      return;
    }
    if (!isOpenBlutoothLe())
    {
      Log.i("smartgo_beacon", "蓝牙未开启");
      return;
    }
    initLbsScan();
  }

  @SuppressLint({"NewApi"})
  public void initLbsScan()
  {
    if (mBluetoothAdapter == null)
    {
      bluetoothManager = (BluetoothManager)context.getSystemService("bluetooth");
      mBluetoothAdapter = bluetoothManager.getAdapter();
    }
    mLeScanCallback = new BluetoothAdapter.LeScanCallback()
    {
      @SuppressLint({"DefaultLocale"})
      public void onLeScan(BluetoothDevice paramAnonymousBluetoothDevice, int paramAnonymousInt, byte[] paramAnonymousArrayOfByte)
      {
        paramAnonymousBluetoothDevice = IBeaconClass.fromScanData(paramAnonymousBluetoothDevice, paramAnonymousInt, paramAnonymousArrayOfByte);
        if ((paramAnonymousBluetoothDevice == null) || (paramAnonymousBluetoothDevice.distance > 1.0D));
        do
        {
          do
          {
            return;
            paramAnonymousBluetoothDevice.proximityUuid = paramAnonymousBluetoothDevice.proximityUuid.toUpperCase();
          }
          while (SmartgoBeaconUtil.isRequest);
          if (DBDao.getInstance(SmartgoBeaconUtil.context).queryBeacon(paramAnonymousBluetoothDevice.proximityUuid, paramAnonymousBluetoothDevice.major, paramAnonymousBluetoothDevice.minor, CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY)))
          {
            Log.e("smartgo_beacon", "数据库有该数据");
            return;
          }
        }
        while (1 == 0);
        paramAnonymousArrayOfByte = paramAnonymousBluetoothDevice.proximityUuid + "_" + paramAnonymousBluetoothDevice.major + "_" + paramAnonymousBluetoothDevice.minor;
        if ((SmartgoBeaconUtil.beaconRequestMap.containsKey(paramAnonymousArrayOfByte)) && (((Boolean)SmartgoBeaconUtil.beaconRequestMap.get(paramAnonymousArrayOfByte)).booleanValue()))
        {
          Log.i("smartgo_beacon", "正在请求");
          return;
        }
        SmartgoBeaconUtil.isRequest = true;
        DBDao.getInstance(SmartgoBeaconUtil.context).insertBeacon(paramAnonymousBluetoothDevice);
        Log.i("smartgo_beacon", "--------------------------------");
        paramAnonymousArrayOfByte = new StringBuffer();
        paramAnonymousArrayOfByte.append("name: " + paramAnonymousBluetoothDevice.name).append('\n').append("uuid: " + paramAnonymousBluetoothDevice.proximityUuid).append('\n').append("major: " + paramAnonymousBluetoothDevice.major).append('\n').append("minor: " + paramAnonymousBluetoothDevice.minor).append('\n').append("distance: " + paramAnonymousBluetoothDevice.distance).append('\n').append("rssi: " + paramAnonymousBluetoothDevice.rssi);
        Log.d("smartgo_beacon", paramAnonymousArrayOfByte.toString());
        Log.i("smartgo_beacon", "--------------------------------");
        SmartgoBeaconUtil.this.reqeustActivityInfo(paramAnonymousBluetoothDevice);
      }
    };
    startScan();
  }

  @SuppressLint({"NewApi"})
  public boolean isOpenBlutoothLe()
  {
    bluetoothManager = (BluetoothManager)context.getSystemService("bluetooth");
    mBluetoothAdapter = bluetoothManager.getAdapter();
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

  @SuppressLint({"NewApi"})
  public void startScan()
  {
    if ((mBluetoothAdapter != null) && (mLeScanCallback != null))
    {
      boolean bool = mBluetoothAdapter.startLeScan(mLeScanCallback);
      Log.i("smartgo_beacon", "开始扫描: " + bool);
    }
  }

  @SuppressLint({"NewApi"})
  public void stopScan()
  {
    if (mBluetoothAdapter != null)
      mBluetoothAdapter.stopLeScan(mLeScanCallback);
  }

  private static class BeaconHandler extends Handler
  {
    private IBeaconClass.iBeacon ibeacon;

    public BeaconHandler(IBeaconClass.iBeacon paramiBeacon)
    {
      this.ibeacon = paramiBeacon;
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      String str = this.ibeacon.proximityUuid + "_" + this.ibeacon.major + "_" + this.ibeacon.minor;
      if (paramMessage.what == 260)
      {
        if (paramMessage.obj == null)
          return;
        paramMessage = HttpJsonParse.parseJson_ActivityInfo(paramMessage.obj.toString());
        if ((paramMessage != null) && (paramMessage.size() > 0))
        {
          paramMessage = paramMessage.iterator();
          if (paramMessage.hasNext());
        }
      }
      while (true)
      {
        SmartgoBeaconUtil.beaconRequestMap.put(str, Boolean.valueOf(false));
        SmartgoBeaconUtil.isRequest = false;
        return;
        BeaconActivityInfo localBeaconActivityInfo = (BeaconActivityInfo)paramMessage.next();
        int i = DBDao.getInstance(SmartgoBeaconUtil.context).queryActivityInfo(localBeaconActivityInfo.getUuid(), localBeaconActivityInfo.getMajor(), localBeaconActivityInfo.getMinor(), CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY), localBeaconActivityInfo.getActivityId());
        if (i == 3)
        {
          DBDao.getInstance(SmartgoBeaconUtil.context).updateBeanFlag(this.ibeacon.proximityUuid, localBeaconActivityInfo.getMajor(), localBeaconActivityInfo.getMinor(), CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY), localBeaconActivityInfo.getActivityId(), localBeaconActivityInfo.getId());
          break;
        }
        if (i == 2)
        {
          if (!DBDao.getInstance(SmartgoBeaconUtil.context).queryPush(this.ibeacon.proximityUuid, localBeaconActivityInfo.getMajor(), localBeaconActivityInfo.getMinor(), CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY)))
            break;
          DBDao.getInstance(SmartgoBeaconUtil.context).updateBeanFlag(this.ibeacon.proximityUuid, localBeaconActivityInfo.getMajor(), localBeaconActivityInfo.getMinor(), CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY), localBeaconActivityInfo.getActivityId(), localBeaconActivityInfo.getId());
          if (CommonMethod.getAppSatus(SmartgoBeaconUtil.context, SmartgoBeaconUtil.context.getPackageName()) != 1)
          {
            NotificationUtil.pushNotification(SmartgoBeaconUtil.context, localBeaconActivityInfo);
            break;
          }
          DBDao.getInstance(SmartgoBeaconUtil.context).updateBean(localBeaconActivityInfo);
          SmartgoBeaconUtil.showDialog(SmartgoBeaconUtil.context, localBeaconActivityInfo);
          break;
        }
        if (i != 1)
          break;
        DBDao.getInstance(SmartgoBeaconUtil.context).delSameBean(this.ibeacon.proximityUuid, localBeaconActivityInfo.getMajor(), localBeaconActivityInfo.getMinor(), CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY));
        break;
        Log.i("smartgo_beacon", "没有活动的beacon: " + this.ibeacon.major + " : " + this.ibeacon.minor);
        DBDao.getInstance(SmartgoBeaconUtil.context).updateNoActivityBean(this.ibeacon.proximityUuid, this.ibeacon.major, this.ibeacon.minor, CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY));
        continue;
        if (paramMessage.what == 258)
          Log.i("smartgo_beacon", "网络异常");
        else if (paramMessage.what == 259)
          Log.i("smartgo_beacon", "服务端异常");
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.SmartgoBeaconUtil
 * JD-Core Version:    0.6.2
 */