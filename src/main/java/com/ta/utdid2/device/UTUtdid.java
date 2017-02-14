package com.ta.utdid2.device;

import android.content.Context;
import android.provider.Settings.System;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.IntUtils;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.core.persistent.PersistentConfiguration;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class UTUtdid
{
  private static final Object CREATE_LOCK = new Object();
  private static final String HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
  private static final String S_GLOBAL_PERSISTENT_CONFIG_DIR = ".UTSystemConfig" + File.separator + "Global";
  private static final String S_GLOBAL_PERSISTENT_CONFIG_KEY = "Alvin2";
  private static final String S_LOCAL_STORAGE_KEY = "ContextData";
  private static final String S_LOCAL_STORAGE_NAME = ".DataStorage";
  static final String UM_SETTINGS_STORAGE = "dxCRMxhQkdGePGnp";
  static final String UM_SETTINGS_STORAGE_NEW = "mqBRboGZkQPcAkyk";
  private static UTUtdid s_umutdid = null;
  private String mCBDomain = "xx_utdid_domain";
  private String mCBKey = "xx_utdid_key";
  private Context mContext = null;
  private PersistentConfiguration mPC = null;
  private Pattern mPattern = Pattern.compile("[^0-9a-zA-Z=/+]+");
  private PersistentConfiguration mTaoPC = null;
  private String mUtdid = null;
  private UTUtdidHelper mUtdidHelper = null;

  public UTUtdid(Context paramContext)
  {
    this.mContext = paramContext;
    this.mTaoPC = new PersistentConfiguration(paramContext, S_GLOBAL_PERSISTENT_CONFIG_DIR, "Alvin2", false, true);
    this.mPC = new PersistentConfiguration(paramContext, ".DataStorage", "ContextData", false, true);
    this.mUtdidHelper = new UTUtdidHelper();
    this.mCBKey = String.format("K_%d", new Object[] { Integer.valueOf(StringUtils.hashCode(this.mCBKey)) });
    this.mCBDomain = String.format("D_%d", new Object[] { Integer.valueOf(StringUtils.hashCode(this.mCBDomain)) });
  }

  private static String _calcHmac(byte[] paramArrayOfByte)
    throws Exception
  {
    Mac localMac = Mac.getInstance("HmacSHA1");
    localMac.init(new SecretKeySpec("d6fc3a4a06adbde89223bvefedc24fecde188aaa9161".getBytes(), localMac.getAlgorithm()));
    return Base64.encodeToString(localMac.doFinal(paramArrayOfByte), 2);
  }

  private final byte[] _generateUtdid()
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i = (int)(System.currentTimeMillis() / 1000L);
    int j = new Random().nextInt();
    Object localObject = IntUtils.getBytes(i);
    byte[] arrayOfByte = IntUtils.getBytes(j);
    localByteArrayOutputStream.write((byte[])localObject, 0, 4);
    localByteArrayOutputStream.write(arrayOfByte, 0, 4);
    localByteArrayOutputStream.write(3);
    localByteArrayOutputStream.write(0);
    try
    {
      localObject = PhoneInfoUtils.getImei(this.mContext);
      localByteArrayOutputStream.write(IntUtils.getBytes(StringUtils.hashCode((String)localObject)), 0, 4);
      localByteArrayOutputStream.write(IntUtils.getBytes(StringUtils.hashCode(_calcHmac(localByteArrayOutputStream.toByteArray()))));
      return localByteArrayOutputStream.toByteArray();
    }
    catch (Exception localException)
    {
      while (true)
        String str = "" + new Random().nextInt();
    }
  }

  static long getMetadataCheckSum(Device paramDevice)
  {
    if (paramDevice != null)
    {
      paramDevice = String.format("%s%s%s%s%s", new Object[] { paramDevice.getUtdid(), paramDevice.getDeviceId(), Long.valueOf(paramDevice.getCreateTimestamp()), paramDevice.getImsi(), paramDevice.getImei() });
      if (!StringUtils.isEmpty(paramDevice))
      {
        Adler32 localAdler32 = new Adler32();
        localAdler32.reset();
        localAdler32.update(paramDevice.getBytes());
        return localAdler32.getValue();
      }
    }
    return 0L;
  }

  private String getUtdidFromTaoPPC()
  {
    if (this.mTaoPC != null)
    {
      String str = this.mTaoPC.getString("UTDID");
      if ((!StringUtils.isEmpty(str)) && (this.mUtdidHelper.packUtdidStr(str) != null))
        return str;
    }
    return null;
  }

  public static UTUtdid instance(Context paramContext)
  {
    if ((paramContext != null) && (s_umutdid == null));
    synchronized (CREATE_LOCK)
    {
      if (s_umutdid == null)
        s_umutdid = new UTUtdid(paramContext);
      return s_umutdid;
    }
  }

  private boolean isValidUTDID(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString != null)
    {
      String str = paramString;
      if (paramString.endsWith("\n"))
        str = paramString.substring(0, paramString.length() - 1);
      bool1 = bool2;
      if (24 == str.length())
      {
        bool1 = bool2;
        if (!this.mPattern.matcher(str).find())
          bool1 = true;
      }
    }
    return bool1;
  }

  private void saveUtdidToLocalStorage(String paramString)
  {
    if ((paramString != null) && (this.mPC != null) && (!paramString.equals(this.mPC.getString(this.mCBKey))))
    {
      this.mPC.putString(this.mCBKey, paramString);
      this.mPC.commit();
    }
  }

  private void saveUtdidToNewSettings(String paramString)
  {
    if ((this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) && (isValidUTDID(paramString)))
    {
      String str = paramString;
      if (paramString.endsWith("\n"))
        str = paramString.substring(0, paramString.length() - 1);
      if ((24 == str.length()) && (!isValidUTDID(Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk"))))
        Settings.System.putString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk", str);
    }
  }

  private void saveUtdidToSettings(String paramString)
  {
    if ((this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) && (paramString != null))
      syncUTDIDToSettings(paramString);
  }

  private void saveUtdidToTaoPPC(String paramString)
  {
    if (isValidUTDID(paramString))
    {
      String str1 = paramString;
      if (paramString.endsWith("\n"))
        str1 = paramString.substring(0, paramString.length() - 1);
      if ((str1.length() == 24) && (this.mTaoPC != null))
      {
        String str2 = this.mTaoPC.getString("UTDID");
        Object localObject1 = this.mTaoPC.getString("EI");
        paramString = (String)localObject1;
        if (StringUtils.isEmpty((String)localObject1))
          paramString = PhoneInfoUtils.getImei(this.mContext);
        Object localObject2 = this.mTaoPC.getString("SI");
        localObject1 = localObject2;
        if (StringUtils.isEmpty((String)localObject2))
          localObject1 = PhoneInfoUtils.getImsi(this.mContext);
        Object localObject3 = this.mTaoPC.getString("DID");
        localObject2 = localObject3;
        if (StringUtils.isEmpty((String)localObject3))
          localObject2 = paramString;
        if ((str2 == null) || (!str2.equals(str1)))
        {
          localObject3 = new Device();
          ((Device)localObject3).setImei(paramString);
          ((Device)localObject3).setImsi((String)localObject1);
          ((Device)localObject3).setUtdid(str1);
          ((Device)localObject3).setDeviceId((String)localObject2);
          ((Device)localObject3).setCreateTimestamp(System.currentTimeMillis());
          this.mTaoPC.putString("UTDID", str1);
          this.mTaoPC.putString("EI", ((Device)localObject3).getImei());
          this.mTaoPC.putString("SI", ((Device)localObject3).getImsi());
          this.mTaoPC.putString("DID", ((Device)localObject3).getDeviceId());
          this.mTaoPC.putLong("timestamp", ((Device)localObject3).getCreateTimestamp());
          this.mTaoPC.putLong("S", getMetadataCheckSum((Device)localObject3));
          this.mTaoPC.commit();
        }
      }
    }
  }

  private void syncUTDIDToSettings(String paramString)
  {
    if (!paramString.equals(Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp")))
      Settings.System.putString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp", paramString);
  }

  public String getValue()
  {
    String str2;
    UTUtdidHelper2 localUTUtdidHelper2;
    int i;
    Object localObject2;
    while (true)
    {
      try
      {
        if (this.mUtdid != null)
        {
          localObject1 = this.mUtdid;
          return localObject1;
        }
        str2 = Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
        Object localObject1 = str2;
        if (isValidUTDID(str2))
          continue;
        localUTUtdidHelper2 = new UTUtdidHelper2();
        i = 0;
        localObject1 = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        if (StringUtils.isEmpty((String)localObject1))
          break label425;
        str2 = localUTUtdidHelper2.dePackWithBase64((String)localObject1);
        if (isValidUTDID(str2))
        {
          saveUtdidToNewSettings(str2);
          localObject1 = str2;
          continue;
        }
      }
      finally
      {
      }
      str2 = localUTUtdidHelper2.dePack(str1);
      if (!isValidUTDID(str2))
        break label422;
      str2 = this.mUtdidHelper.packUtdidStr(str2);
      if (StringUtils.isEmpty(str2))
        break label422;
      saveUtdidToSettings(str2);
      localObject2 = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
      label151: str2 = this.mUtdidHelper.dePack((String)localObject2);
      if (!isValidUTDID(str2))
        break;
      this.mUtdid = str2;
      saveUtdidToTaoPPC(str2);
      saveUtdidToLocalStorage((String)localObject2);
      saveUtdidToNewSettings(this.mUtdid);
      localObject2 = this.mUtdid;
    }
    while (true)
    {
      while (true)
      {
        localObject2 = getUtdidFromTaoPPC();
        if (isValidUTDID((String)localObject2))
        {
          str2 = this.mUtdidHelper.packUtdidStr((String)localObject2);
          if (i != 0)
            saveUtdidToSettings(str2);
          saveUtdidToNewSettings((String)localObject2);
          saveUtdidToLocalStorage(str2);
          this.mUtdid = ((String)localObject2);
          break;
        }
        String str3 = this.mPC.getString(this.mCBKey);
        if (!StringUtils.isEmpty(str3))
        {
          str2 = localUTUtdidHelper2.dePack(str3);
          localObject2 = str2;
          if (!isValidUTDID(str2))
            localObject2 = this.mUtdidHelper.dePack(str3);
          if (isValidUTDID((String)localObject2))
          {
            str2 = this.mUtdidHelper.packUtdidStr((String)localObject2);
            if (!StringUtils.isEmpty((String)localObject2))
            {
              this.mUtdid = ((String)localObject2);
              if (i != 0)
                saveUtdidToSettings(str2);
              saveUtdidToTaoPPC(this.mUtdid);
              localObject2 = this.mUtdid;
              break;
            }
          }
        }
        try
        {
          localObject2 = _generateUtdid();
          if (localObject2 != null)
          {
            this.mUtdid = Base64.encodeToString((byte[])localObject2, 2);
            saveUtdidToTaoPPC(this.mUtdid);
            localObject2 = this.mUtdidHelper.pack((byte[])localObject2);
            if (localObject2 != null)
            {
              if (i != 0)
                saveUtdidToSettings((String)localObject2);
              saveUtdidToLocalStorage((String)localObject2);
            }
            localObject2 = this.mUtdid;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          Object localObject3 = null;
        }
      }
      break;
      label422: break label151;
      label425: i = 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.device.UTUtdid
 * JD-Core Version:    0.6.2
 */