package com.ismartgo.app.ibeacon;

import android.bluetooth.BluetoothDevice;

public class iBeaconClass
{
  private static String bytesToHexString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfByte.length)
        return localStringBuilder.toString();
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
      i += 1;
    }
  }

  protected static double calculateAccuracy(int paramInt, double paramDouble)
  {
    if (paramDouble == 0.0D)
      return -1.0D;
    paramDouble = paramDouble * 1.0D / paramInt;
    if (paramDouble < 1.0D)
      return Math.pow(paramDouble, 10.0D);
    return 0.89976D * Math.pow(paramDouble, 7.7095D) + 0.111D;
  }

  public static iBeacon fromScanData(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfByte)
  {
    int i = 2;
    int j = 0;
    while (true)
    {
      if (i > 5);
      while (true)
      {
        if (j != 0)
          break label244;
        return null;
        if (((paramArrayOfByte[(i + 2)] & 0xFF) != 2) || ((paramArrayOfByte[(i + 3)] & 0xFF) != 21))
          break;
        j = 1;
      }
      if (((paramArrayOfByte[i] & 0xFF) == 45) && ((paramArrayOfByte[(i + 1)] & 0xFF) == 36) && ((paramArrayOfByte[(i + 2)] & 0xFF) == 191) && ((paramArrayOfByte[(i + 3)] & 0xFF) == 22))
      {
        paramBluetoothDevice = new iBeacon();
        paramBluetoothDevice.major = 0;
        paramBluetoothDevice.minor = 0;
        paramBluetoothDevice.proximityUuid = "00000000-0000-0000-0000-000000000000";
        paramBluetoothDevice.txPower = -55;
        paramBluetoothDevice.distance = -1.0D;
        return paramBluetoothDevice;
      }
      if (((paramArrayOfByte[i] & 0xFF) == 173) && ((paramArrayOfByte[(i + 1)] & 0xFF) == 119) && ((paramArrayOfByte[(i + 2)] & 0xFF) == 0) && ((paramArrayOfByte[(i + 3)] & 0xFF) == 198))
      {
        paramBluetoothDevice = new iBeacon();
        paramBluetoothDevice.major = 0;
        paramBluetoothDevice.minor = 0;
        paramBluetoothDevice.proximityUuid = "00000000-0000-0000-0000-000000000000";
        paramBluetoothDevice.txPower = -55;
        paramBluetoothDevice.distance = -1.0D;
        return paramBluetoothDevice;
      }
      i += 1;
    }
    label244: iBeacon localiBeacon = new iBeacon();
    localiBeacon.major = ((paramArrayOfByte[(i + 20)] & 0xFF) * 256 + (paramArrayOfByte[(i + 21)] & 0xFF));
    localiBeacon.minor = ((paramArrayOfByte[(i + 22)] & 0xFF) * 256 + (paramArrayOfByte[(i + 23)] & 0xFF));
    localiBeacon.txPower = paramArrayOfByte[(i + 24)];
    localiBeacon.rssi = paramInt;
    Object localObject = new byte[16];
    System.arraycopy(paramArrayOfByte, i + 4, localObject, 0, 16);
    paramArrayOfByte = bytesToHexString((byte[])localObject);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(0, 8));
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(8, 12));
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(12, 16));
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(16, 20));
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramArrayOfByte.substring(20, 32));
    localiBeacon.proximityUuid = ((StringBuilder)localObject).toString();
    if (paramBluetoothDevice != null)
    {
      localiBeacon.bluetoothAddress = paramBluetoothDevice.getAddress();
      localiBeacon.name = paramBluetoothDevice.getName();
    }
    localiBeacon.distance = calculateAccuracy(localiBeacon.txPower, localiBeacon.rssi);
    return localiBeacon;
  }

  public static class iBeacon
  {
    public String bluetoothAddress;
    public double distance;
    public int major;
    public int minor;
    public String name;
    public String proximityUuid;
    public int rssi;
    public int txPower;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ibeacon.iBeaconClass
 * JD-Core Version:    0.6.2
 */