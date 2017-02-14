package com.ismartgo.app.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ismartgo.app.bean.DateTime;
import com.ismartgo.app.bean.IbeaconDataBean;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.ibeacon.iBeaconClass.iBeacon;
import com.ismartgo.app.oos.FederationToken;
import java.io.PrintStream;

public class SharedPreferenceUtil
{
  public static final String AGE = "age";
  public static final String APP_VERSION = "app_version";
  public static final String BEANNUMBER = "beanNumber";
  public static final String BIRTHDAY = "birthday";
  public static final String BYINVITECODE = "byInviteCode";
  public static final String COME_COUNT = "come_count";
  public static final String DEV_INVITED = "dev_invited";
  public static final String EMAIL = "email";
  private static final String FIRST_USE = "first_use";
  public static final String FUNCTION_VERSION = "function_version";
  public static final String GIFT_ID = "gift_id";
  public static final String GUIDE_COUNT = "guideCount_1.2.0";
  public static final String HIDE_GUIDE = "show_guide";
  public static final String HOME_BUTTON_FLAG = "home_button_flag";
  public static final String HOME_BUTTON_VERSION = "home_button_version";
  public static final String HOME_HEAD_BUTTON = "home_head_button";
  private static final String IBEACONDATA = "ibeaconDate";
  public static final String INVEITED_CODE = "inveited_code";
  public static final String LATEST_SIGN_DATE = "latest_sign_date";
  public static final String LATITUDE = "latitude";
  private static final String LOCATION_CITY = "location_info";
  private static final String LOGINCOUNT_INFO = "loginCount_info";
  public static final String LOGINTYPE = "logintype";
  private static final String LOGIN_COUNT = "loginCount";
  public static final String LOGISTICS_EXPIRE = "logistics_expire";
  public static final String LOGISTICS_INFO = "logistics_info";
  public static final String LONGITUDE = "longitude";
  public static final String MY_RECEIPT_COUNT = "my_receipt_count";
  public static final String NICKNUMBER = "nickNumber";
  public static final String OSS = "oSS";
  public static final String OSS_AK = "accessKeyId";
  public static final String OSS_EXPIRATION = "expiration";
  public static final String OSS_SK = "accessKeySecret";
  public static final String OSS_TOKEN = "securityToken";
  public static final String PASSWORD = "psd";
  private static final String PUSH_SWITCH = "push_switch";
  public static final String RECEIPT_SHOPTYPE_FLAG = "receipt_shoptype_flag";
  public static final String RECEIPT_SHOPTYPE_VERSION = "receipt_shoptype_version";
  public static final String RECEIPT_SHOP_TYPE = "receipt_shop_type";
  public static final String RECEIPT_SV_URL = "receipt_sv_url";
  public static final String RECODE_BEFORE_SCANMORE = "recode_before_scanmore";
  public static final String RECODE_COME_COUNT = "recode_come_count";
  public static final String RECODE_DAY = "day";
  public static final String RECODE_GUIDE_COUNT = "recode_guide_count";
  public static final String RECODE_HOUR = "hour";
  public static final String RECODE_MINUTE = "minute";
  public static final String RECODE_MONTH = "month";
  public static final String RECODE_SECOND = "second";
  public static final String RECODE_STARTPROGRAM_TIME = "recode_program_time";
  public static final String RECODE_YEAR = "year";
  private static final String REQUEST_IBEACON_DATA = "requestIbeaconData";
  public static final String SESSION_EXPIRE = "session";
  public static final String SESSION_ID = "session_id";
  public static final String SEX = "sex";
  public static final String THIRDID = "thirdid";
  public static final String TICKET_NEW = "ticket_new";
  public static final String TIP = "tip";
  public static final String TOURIST_USER = "tourist_user";
  public static final String TYPE = "type";
  public static final String USERID = "userId";
  public static final String USERNAME = "userName";
  public static final String USER_HEADURL = "headurl";
  public static final String USER_INFO = "user_info";
  public static final String USER_PASSWORD = "password";
  public static final String USER_PHONE = "user_phone";
  public static final String USER_TYPE = "login_type";
  public static final String USER_USERNAME = "username";
  public static final String USE_APP_COUNT = "use_app_count";
  public static final String USE_COUNT = "use_count";
  public static final String VOICE_SET = "app_voice";
  private static final String VOICE_SWITCH = "voice_switch";

  public static void clearLoginCount(Context paramContext)
  {
    paramContext.getSharedPreferences("loginCount_info", 0).edit().clear().commit();
  }

  public static void clearLoginPwd(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.clear();
    paramContext.commit();
  }

  public static void clearStartProgramTime(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("recode_program_time", 1).edit();
    paramContext.clear();
    paramContext.commit();
  }

  public static int getDevInvited(Context paramContext)
  {
    return paramContext.getSharedPreferences("inveited_code", 1).getInt("dev_invited", 0);
  }

  public static String getGiftId(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 0).getString("gift_id", null);
  }

  public static int getGuideCount(Context paramContext)
  {
    return paramContext.getSharedPreferences("recode_guide_count", 0).getInt("guideCount_1.2.0", 0);
  }

  public static int getHomeBottonFlag(Context paramContext)
  {
    return paramContext.getSharedPreferences("function_version", 0).getInt("home_button_flag", 0);
  }

  public static int getHomeBottonVersion(Context paramContext)
  {
    return paramContext.getSharedPreferences("function_version", 0).getInt("home_button_version", -1);
  }

  public static String getHomeHeadButton(Context paramContext)
  {
    return paramContext.getSharedPreferences("app_voice", 0).getString("home_head_button", "");
  }

  public static iBeaconClass.iBeacon getIbeaconData(Context paramContext)
  {
    iBeaconClass.iBeacon localiBeacon = new iBeaconClass.iBeacon();
    paramContext = paramContext.getSharedPreferences("ibeaconDate", 0);
    localiBeacon.proximityUuid = paramContext.getString("uuid", "");
    localiBeacon.minor = paramContext.getInt("minor", -1);
    localiBeacon.major = paramContext.getInt("major", -1);
    localiBeacon.name = paramContext.getString("name", "");
    localiBeacon.rssi = paramContext.getInt("rssi", -1);
    localiBeacon.txPower = paramContext.getInt("txPower", -1);
    localiBeacon.bluetoothAddress = paramContext.getString("bluetoothAddress", "");
    localiBeacon.distance = paramContext.getFloat("distance", 0.0F);
    paramContext = localiBeacon;
    if (localiBeacon.proximityUuid.equals(""))
      paramContext = null;
    return paramContext;
  }

  public static String getLocationCity(Context paramContext)
  {
    if (paramContext == null)
      return "";
    return paramContext.getSharedPreferences("location_info", 0).getString("city", "");
  }

  public static SharedPreferences getLocationInfo(Context paramContext)
  {
    return paramContext.getSharedPreferences("location_info", 0);
  }

  public static int getLoginCount(Context paramContext)
  {
    return paramContext.getSharedPreferences("loginCount_info", 0).getInt("loginCount", 0);
  }

  public static User getLoginInfo(Context paramContext)
  {
    User localUser = new User();
    paramContext = paramContext.getSharedPreferences("user_info", 0);
    if (paramContext.getString("username", "").equals(""))
      return null;
    localUser.setUsername(paramContext.getString("username", ""));
    localUser.setPassword(paramContext.getString("password", ""));
    localUser.setPhone(paramContext.getString("user_phone", ""));
    localUser.setLoginType(paramContext.getInt("login_type", 6));
    localUser.setId(paramContext.getString("userId", ""));
    localUser.setBeanNumber(paramContext.getInt("beanNumber", 0));
    localUser.setNickname(paramContext.getString("nickNumber", ""));
    localUser.setByInviteCode(paramContext.getString("byInviteCode", ""));
    localUser.setEmail(paramContext.getString("email", ""));
    localUser.setSex(paramContext.getString("sex", "0"));
    localUser.setAge(paramContext.getString("age", "0"));
    localUser.setLongitude(paramContext.getString("longitude", ""));
    localUser.setLatitude(paramContext.getString("latitude", ""));
    localUser.setBirthday(paramContext.getString("birthday", ""));
    localUser.setThirdId(paramContext.getString("thirdid", ""));
    return localUser;
  }

  public static int getLoginType(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 1).getInt("login_type", 6);
  }

  public static String getLogisticsExpire(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 0).getString("logistics_expire", null);
  }

  public static String getLogisticsInfo(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 0).getString("logistics_info", null);
  }

  public static int getMyReceiptCount(Context paramContext)
  {
    return paramContext.getSharedPreferences("recode_guide_count", 0).getInt("my_receipt_count", 0);
  }

  public static FederationToken getOSSToken(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("oSS", 0);
    return new FederationToken(paramContext.getString("accessKeyId", ""), paramContext.getString("accessKeySecret", ""), paramContext.getString("securityToken", ""), paramContext.getLong("expiration", -1L));
  }

  public static int getPushSwitch(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 0).getInt("push_switch", 1);
  }

  public static int getReceiptShopFlag(Context paramContext)
  {
    return paramContext.getSharedPreferences("function_version", 0).getInt("receipt_shoptype_flag", 0);
  }

  public static String getReceiptShopType(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 0).getString("receipt_shop_type", "");
  }

  public static int getReceiptShopVersion(Context paramContext)
  {
    return paramContext.getSharedPreferences("function_version", 0).getInt("receipt_shoptype_version", -1);
  }

  public static String getReceiptSvUrl(Context paramContext)
  {
    return paramContext.getSharedPreferences("function_version", 0).getString("receipt_sv_url", "");
  }

  public static IbeaconDataBean getRequestIbeaconData(Context paramContext)
  {
    IbeaconDataBean localIbeaconDataBean = new IbeaconDataBean();
    paramContext = paramContext.getSharedPreferences("requestIbeaconData", 0);
    localIbeaconDataBean.setDistance(paramContext.getString("distance", ""));
    localIbeaconDataBean.setIbeaconnumber(paramContext.getString("ibeaconnumber", ""));
    localIbeaconDataBean.setId(paramContext.getString("id", ""));
    localIbeaconDataBean.setMajor(paramContext.getString("major", ""));
    localIbeaconDataBean.setMinor(paramContext.getString("minor", ""));
    localIbeaconDataBean.setShopid(paramContext.getString("shopid", ""));
    localIbeaconDataBean.setShopname(paramContext.getString("shopname", ""));
    localIbeaconDataBean.setStoptime(paramContext.getString("stoptime", ""));
    localIbeaconDataBean.setThirdlink(paramContext.getString("thirdlink", ""));
    localIbeaconDataBean.setTitle(paramContext.getString("title", ""));
    localIbeaconDataBean.setUuid(paramContext.getString("uuid", ""));
    return localIbeaconDataBean;
  }

  public static String getSessionExpire(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 0).getString("session", null);
  }

  public static String getSessionId(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 0).getString("session_id", null);
  }

  public static String getSessionVersion(Context paramContext)
  {
    return paramContext.getSharedPreferences("user_info", 0).getString("app_version", "");
  }

  public static DateTime getStartProgramTime(Context paramContext)
  {
    DateTime localDateTime = new DateTime();
    paramContext = paramContext.getSharedPreferences("recode_program_time", 1);
    localDateTime.setYear(paramContext.getInt("year", 0));
    localDateTime.setMonth(paramContext.getInt("month", 0));
    localDateTime.setDay(paramContext.getInt("day", 0));
    localDateTime.setHour(paramContext.getInt("hour", 0));
    localDateTime.setMinute(paramContext.getInt("minute", 0));
    localDateTime.setSecond(paramContext.getInt("second", 0));
    return localDateTime;
  }

  public static boolean getTicketNew(Context paramContext)
  {
    return paramContext.getSharedPreferences("tip", 0).getBoolean("ticket_new", false);
  }

  public static User getTouriseUser(Context paramContext)
  {
    User localUser = new User();
    paramContext = paramContext.getSharedPreferences("tourist_user", 1);
    localUser.setUsername(paramContext.getString("userName", ""));
    localUser.setId(paramContext.getString("userId", "0"));
    localUser.setBeanNumber(paramContext.getInt("beanNumber", 0));
    localUser.setNickname(paramContext.getString("nickNumber", ""));
    localUser.setPassword(paramContext.getString("psd", ""));
    localUser.setByInviteCode(paramContext.getString("byInviteCode", ""));
    localUser.setEmail(paramContext.getString("email", ""));
    localUser.setSex(paramContext.getString("sex", "0"));
    localUser.setAge(paramContext.getString("age", "0"));
    localUser.setLongitude(paramContext.getString("longitude", ""));
    localUser.setLatitude(paramContext.getString("latitude", ""));
    localUser.setBirthday(paramContext.getString("birthday", ""));
    localUser.setType(paramContext.getString("type", "1"));
    localUser.setThirdId(paramContext.getString("thirdid", ""));
    localUser.setLoginType(paramContext.getInt("logintype", 6));
    return localUser;
  }

  public static int getTouristBeanNumber(Context paramContext)
  {
    return paramContext.getSharedPreferences("tourist_user", 1).getInt("beanNumber", 0);
  }

  public static String getTouristId(Context paramContext)
  {
    return paramContext.getSharedPreferences("tourist_user", 1).getString("userId", "0");
  }

  public static int getUserAppCount(Context paramContext)
  {
    return paramContext.getSharedPreferences("use_app_count", 1).getInt("use_count", -1);
  }

  public static int getVoiceSwitch(Context paramContext)
  {
    return paramContext.getSharedPreferences("app_voice", 0).getInt("voice_switch", 1);
  }

  public static void saveIbeaconData(Context paramContext, iBeaconClass.iBeacon paramiBeacon)
  {
    paramContext = paramContext.getSharedPreferences("ibeaconDate", 0).edit();
    paramContext.putString("uuid", paramiBeacon.proximityUuid);
    paramContext.putInt("major", paramiBeacon.major);
    paramContext.putInt("minor", paramiBeacon.minor);
    paramContext.putString("name", paramiBeacon.name);
    paramContext.putInt("rssi", paramiBeacon.rssi);
    paramContext.putInt("txPower", paramiBeacon.txPower);
    paramContext.putString("bluetoothAddress", paramiBeacon.bluetoothAddress);
    paramContext.putFloat("distance", Float.parseFloat(paramiBeacon.distance));
    paramContext.commit();
  }

  public static void saveRequestIbeaconData(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11)
  {
    paramContext = paramContext.getSharedPreferences("requestIbeaconData", 0);
    System.out.println("--->save IB data:uuid=" + paramString11 + ",major=" + paramString4 + ",minor=" + paramString5);
    paramContext = paramContext.edit();
    paramContext.putString("distance", paramString1);
    paramContext.putString("ibeaconnumber", paramString2);
    paramContext.putString("id", paramString3);
    paramContext.putString("major", paramString4);
    paramContext.putString("minor", paramString5);
    paramContext.putString("shopid", paramString6);
    paramContext.putString("shopname", paramString7);
    paramContext.putString("stoptime", paramString8);
    paramContext.putString("thirdlink", paramString9);
    paramContext.putString("title", paramString10);
    paramContext.putString("uuid", paramString11);
    paramContext.commit();
  }

  public static void setDevInvited(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("inveited_code", 1).edit();
    paramContext.putInt("dev_invited", paramInt);
    paramContext.commit();
  }

  public static void setGuideCount(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("recode_guide_count", 0).edit();
    paramContext.putInt("guideCount_1.2.0", paramInt);
    paramContext.commit();
  }

  public static void setHomeBottonFlag(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("function_version", 0).edit();
    paramContext.putInt("home_button_flag", paramInt);
    paramContext.commit();
  }

  public static void setHomeBottonVersion(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("function_version", 0).edit();
    paramContext.putInt("home_button_version", paramInt);
    paramContext.commit();
  }

  public static void setHomeHeadButton(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("app_voice", 0).edit();
    paramContext.putString("home_head_button", paramString);
    paramContext.commit();
  }

  public static void setLocationCity(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = paramContext.getSharedPreferences("location_info", 0).edit();
    paramContext.putString("city", paramString1);
    paramContext.putString("log", paramString2);
    paramContext.putString("lat", paramString3);
    paramContext.commit();
  }

  public static void setLoginCount(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("loginCount_info", 0).edit();
    paramContext.putInt("loginCount", paramInt);
    paramContext.commit();
  }

  public static void setLoginInfo(Context paramContext, User paramUser)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user_info", 0).edit();
    localEditor.putString("username", paramUser.getUsername());
    localEditor.putString("password", paramUser.getPassword());
    localEditor.putString("user_phone", paramUser.getPhone());
    localEditor.putInt("login_type", paramUser.getLoginType());
    localEditor.putString("userId", paramUser.getId());
    localEditor.putInt("beanNumber", paramUser.getBeanNumber());
    localEditor.putString("nickNumber", paramUser.getNickname());
    localEditor.putString("byInviteCode", paramUser.getByInviteCode());
    localEditor.putString("email", paramUser.getEmail());
    if (paramUser.getSex() == null)
    {
      paramContext = "0";
      localEditor.putString("sex", paramContext);
      if (paramUser.getAge() != null)
        break label240;
    }
    label240: for (paramContext = "0"; ; paramContext = paramUser.getAge())
    {
      localEditor.putString("age", paramContext);
      localEditor.putString("longitude", paramUser.getLongitude());
      localEditor.putString("latitude", paramUser.getLatitude());
      localEditor.putString("birthday", paramUser.getBirthday());
      localEditor.putString("thirdid", paramUser.getThirdId());
      localEditor.commit();
      return;
      paramContext = paramUser.getSex();
      break;
    }
  }

  public static void setLoginType(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 1).edit();
    paramContext.putInt("login_type", paramInt);
    paramContext.commit();
  }

  public static void setLogisticsExpire(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.putString("logistics_expire", paramString);
    paramContext.commit();
  }

  public static void setLogisticsInfo(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.putString("logistics_info", paramString1);
    paramContext.putString("gift_id", paramString2);
    paramContext.commit();
  }

  public static void setMyReceiptCount(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("recode_guide_count", 0).edit();
    paramContext.putInt("my_receipt_count", paramInt);
    paramContext.commit();
  }

  public static void setOSSToken(Context paramContext, String paramString1, String paramString2, String paramString3, long paramLong)
  {
    paramContext = paramContext.getSharedPreferences("oSS", 0).edit();
    paramContext.putString("accessKeyId", paramString1);
    paramContext.putString("accessKeySecret", paramString2);
    paramContext.putString("securityToken", paramString3);
    paramContext.putLong("expiration", paramLong);
    paramContext.commit();
  }

  public static void setPassword(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.putString("password", paramString);
    paramContext.commit();
  }

  public static void setPushSwitch(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.putInt("push_switch", paramInt);
    paramContext.commit();
  }

  public static void setReceiptShopFlag(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("function_version", 0).edit();
    paramContext.putInt("receipt_shoptype_flag", paramInt);
    paramContext.commit();
  }

  public static void setReceiptShopType(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.putString("receipt_shop_type", paramString);
    paramContext.commit();
  }

  public static void setReceiptShopVersion(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("function_version", 0).edit();
    paramContext.putInt("receipt_shoptype_version", paramInt);
    paramContext.commit();
  }

  public static void setReceiptSvUrl(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("function_version", 0).edit();
    paramContext.putString("receipt_sv_url", paramString);
    paramContext.commit();
  }

  public static void setSessionExpire(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.putString("session", paramString);
    paramContext.commit();
  }

  public static void setSessionId(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.putString("session_id", paramString);
    paramContext.commit();
  }

  public static void setSessionVersion(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("user_info", 0).edit();
    localEditor.putString("app_version", Helper.getVersion(paramContext));
    localEditor.commit();
  }

  public static void setStartProgramTime(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    paramContext = paramContext.getSharedPreferences("recode_program_time", 1).edit();
    paramContext.putInt("year", paramInt1);
    paramContext.putInt("month", paramInt2);
    paramContext.putInt("day", paramInt3);
    paramContext.putInt("hour", paramInt4);
    paramContext.putInt("minute", paramInt5);
    paramContext.putInt("second", paramInt6);
    paramContext.commit();
  }

  public static void setTicketNew(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("tip", 0).edit();
    paramContext.putBoolean("ticket_new", true);
    paramContext.commit();
  }

  public static void setTouristBeanNumber(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("tourist_user", 1).edit();
    paramContext.putInt("beanNumber", paramInt);
    paramContext.commit();
  }

  public static void setTouristUser(Context paramContext, User paramUser)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("tourist_user", 1).edit();
    localEditor.putString("userId", paramUser.getId());
    localEditor.putString("userName", paramUser.getUsername());
    localEditor.putInt("beanNumber", paramUser.getBeanNumber());
    localEditor.putString("nickNumber", paramUser.getNickname());
    localEditor.putString("psd", paramUser.getPassword());
    localEditor.putString("byInviteCode", paramUser.getByInviteCode());
    localEditor.putString("email", paramUser.getEmail());
    if (paramUser.getSex() == null)
    {
      paramContext = "0";
      localEditor.putString("sex", paramContext);
      if (paramUser.getAge() != null)
        break label238;
    }
    label238: for (paramContext = "0"; ; paramContext = paramUser.getAge())
    {
      localEditor.putString("age", paramContext);
      localEditor.putString("longitude", paramUser.getLongitude());
      localEditor.putString("latitude", paramUser.getLatitude());
      localEditor.putString("birthday", paramUser.getBirthday());
      localEditor.putString("type", paramUser.getType());
      localEditor.putString("thirdid", paramUser.getThirdId());
      localEditor.putInt("logintype", 6);
      localEditor.commit();
      return;
      paramContext = paramUser.getSex();
      break;
    }
  }

  public static void setUsed(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("user_info", 0).edit();
    paramContext.putInt("first_use", 1);
    paramContext.commit();
  }

  public static void setUserAppCount(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("use_app_count", 1).edit();
    paramContext.putInt("use_count", paramInt);
    paramContext.commit();
  }

  public static void setVoiceSwitch(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("app_voice", 0).edit();
    paramContext.putInt("voice_switch", paramInt);
    paramContext.commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.SharedPreferenceUtil
 * JD-Core Version:    0.6.2
 */