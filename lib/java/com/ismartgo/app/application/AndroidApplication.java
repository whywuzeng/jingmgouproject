package com.ismartgo.app.application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonUtils;
import com.ismartgo.app.eventbus.HomeActivityEvent;
import com.ismartgo.app.grid.location.ToolLocation;
import com.ismartgo.app.grid.location.ToolLocation.InterfaceBDLocation;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpRequestParam;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ibeacon.MyIbeacon;
import com.ismartgo.app.ibeacon.iBeaconClass.iBeacon;
import com.ismartgo.app.net.TouristUserRequest;
import com.ismartgo.app.net.TouristUserRequest.TouristListener;
import com.ismartgo.app.service.IbeaconService;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.push.FeedbackPush;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.Response;
import de.greenrobot.event.EventBus;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.tsz.afinal.FinalHttp;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidApplication extends Application
  implements ToolLocation.InterfaceBDLocation
{
  private static AndroidApplication instance;
  public static boolean isFirst = false;
  private String TAG = "AndroidApplication";
  LinkedList<Activity> ac_list = new LinkedList();
  private StringBuffer beacons1 = new StringBuffer();
  private StringBuffer beacons2 = new StringBuffer();
  private CurrentLocationInfo currentLocationInfo;
  private boolean hasRequestLesInfo;
  Vector<iBeaconClass.iBeacon> ibeacon1;
  Vector<iBeaconClass.iBeacon> ibeacon2;
  Vector<iBeaconClass.iBeacon> ibeacons;
  String mCity;
  FinalHttp mHttp;
  private MyIbeacon myIbeacon;
  private String sPassword = "";
  private String sUsername = "";
  private User user;

  private String getCurProcessName(Context paramContext)
  {
    int i = Process.myPid();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!paramContext.hasNext())
        return "";
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
    }
    while (localRunningAppProcessInfo.pid != i);
    return localRunningAppProcessInfo.processName;
  }

  public static AndroidApplication getInstance()
  {
    return instance;
  }

  private void parseJson(String paramString)
  {
    while (true)
    {
      try
      {
        paramString = new JSONObject(paramString);
        if (paramString.isNull("data"))
          return;
        if (paramString.getInt("status") != 10001)
          break;
        if (this.currentLocationInfo == null)
          this.currentLocationInfo = new CurrentLocationInfo();
        if (paramString.getJSONObject("data").isNull("lbsInfo"))
          break;
        paramString = paramString.getJSONObject("data").getJSONObject("lbsInfo");
        this.currentLocationInfo.setCityId(paramString.optInt("cityId"));
        Logger.i("cityId: " + paramString.optInt("cityId"));
        BaseActivity.city = paramString.optString("cityName");
        this.currentLocationInfo.setCityName(paramString.optString("cityName"));
        this.currentLocationInfo.setLatestSignDate(paramString.optString("latestSignDate"));
        this.currentLocationInfo.setBeaconId(paramString.optInt("signBeacon"));
        if ((paramString.get("scanShopId") instanceof Integer))
        {
          this.currentLocationInfo.setScanShopId(paramString.optInt("scanShopId"));
          this.currentLocationInfo.setScanShopName(paramString.optString("scanShopName"));
          this.currentLocationInfo.setScanRetailName(paramString.optString("scanRetailName"));
          this.currentLocationInfo.setScanRetailLogo(Url.ADS_URL + paramString.optString("scanRetailLogo"));
          if ((paramString.get("scanDistance") instanceof Integer))
            this.currentLocationInfo.setScanDistance(paramString.optInt("scanDistance"));
          if (!(paramString.get("signShopId") instanceof Integer))
            break label465;
          this.currentLocationInfo.setSignShopId(paramString.optInt("signShopId"));
          this.currentLocationInfo.setSignShopName(paramString.optString("signShopName"));
          this.currentLocationInfo.setSignRetailName(paramString.optString("signRetailName"));
          this.currentLocationInfo.setSignRetailLogo(Url.ADS_URL + paramString.optString("signRetailLogo"));
          if ((paramString.get("signDistance") instanceof Integer))
            this.currentLocationInfo.setSignDistance(paramString.optInt("signDistance"));
          if (this.currentLocationInfo == null)
            break;
          Log.d("HomeActivity", "eventbus发送---");
          EventBus.getDefault().post(new HomeActivityEvent("com.ismartgo.home.headview.refreshData"));
          return;
        }
      }
      catch (JSONException paramString)
      {
        Log.e(this.TAG, "获取是否在店内数据异常： " + paramString.getMessage());
        paramString.printStackTrace();
        return;
      }
      this.currentLocationInfo.setScanShopId(-1);
      continue;
      label465: this.currentLocationInfo.setSignShopId(-1);
    }
  }

  public void addActivity(Activity paramActivity)
  {
    if (paramActivity == null)
      return;
    if (this.ac_list == null)
      this.ac_list = new LinkedList();
    this.ac_list.add(paramActivity);
  }

  public void appIsGetInBackground()
  {
    new Thread()
    {
      public void run()
      {
        super.run();
        SystemClock.sleep(5000L);
        if (Helper.isBackground(AndroidApplication.this.getApplicationContext()))
        {
          boolean bool = MyIbeacon.isSupportIbeacon;
          Log.i("Test", "程序进入后台");
          return;
        }
        Log.i("Test", "程序进入前台");
      }
    }
    .start();
  }

  public void finishActivityList()
  {
    Iterator localIterator = this.ac_list.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.ac_list.clear();
        this.ac_list = null;
        return;
      }
      Activity localActivity = (Activity)localIterator.next();
      System.out.println(localActivity.getClass().getSimpleName());
      localActivity.finish();
    }
  }

  public CurrentLocationInfo getCurrentLocation()
  {
    return this.currentLocationInfo;
  }

  public void getFoundiBeacon()
  {
    while (true)
    {
      int i;
      try
      {
        this.ibeacons = MyIbeacon.getInstance(getBaseContext()).getFoundDevices();
        if ((this.ibeacons == null) || (this.ibeacons.size() == 0))
        {
          this.beacons1.setLength(0);
          this.beacons2.setLength(0);
          return;
        }
        if (this.ibeacon1 == null)
          this.ibeacon1 = new Vector();
        if (this.ibeacon2 == null)
          this.ibeacon2 = new Vector();
        this.ibeacon1.clear();
        this.ibeacon2.clear();
        Iterator localIterator = this.ibeacons.iterator();
        if (!localIterator.hasNext())
        {
          this.beacons1.setLength(0);
          i = 0;
          if (i < this.ibeacon1.size())
            break label347;
          if (this.beacons1.length() > 0)
            this.beacons1.deleteCharAt(this.beacons1.length() - 1);
          this.beacons2.setLength(0);
          i = 0;
          if (i < this.ibeacon2.size())
            break label410;
          if (this.beacons2.length() > 0)
            this.beacons2.deleteCharAt(this.beacons2.length() - 1);
          System.out.println("beacons1: " + this.beacons1.toString());
          System.out.println("beacons2: " + this.beacons2.toString());
          continue;
        }
      }
      finally
      {
      }
      iBeaconClass.iBeacon localiBeacon2 = (iBeaconClass.iBeacon)localObject.next();
      if (localiBeacon2.proximityUuid.toUpperCase().equals("FDA50693-A4E2-4FB1-AFCF-C6EB07647825"))
      {
        this.ibeacon1.add(localiBeacon2);
      }
      else if (localiBeacon2.proximityUuid.toUpperCase().equals("E2C56DB5-DFFB-48D2-B060-D0F5A71096E0"))
      {
        this.ibeacon2.add(localiBeacon2);
        continue;
        label347: iBeaconClass.iBeacon localiBeacon1 = (iBeaconClass.iBeacon)this.ibeacon1.get(i);
        this.beacons1.append(localiBeacon1.major + "_" + localiBeacon1.minor).append(",");
        i += 1;
        continue;
        label410: localiBeacon1 = (iBeaconClass.iBeacon)this.ibeacon2.get(i);
        this.beacons2.append(localiBeacon1.major + "_" + localiBeacon1.minor).append(",");
        i += 1;
      }
    }
  }

  public User getUser()
  {
    return this.user;
  }

  public void isInShop(String paramString1, String paramString2)
  {
    getFoundiBeacon();
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", paramString1);
    localHashMap.put("lon", BaseActivity.log);
    localHashMap.put("lat", BaseActivity.lat);
    localHashMap.put("locationName", paramString2);
    localHashMap.put("beacons1", this.beacons1.toString());
    localHashMap.put("beacons2", this.beacons2.toString());
    HttpRequest.getInstance().executePostStringRequest(getApplicationContext(), Url.LBS_IFNO_URL, HttpWhat.LBS_IFNO, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i(AndroidApplication.this.TAG, " 是否在店内结果： " + ((String)paramAnonymousResponse.get()).toString());
        AndroidApplication.this.parseJson(((String)paramAnonymousResponse.get()).toString());
      }
    });
    MyIbeacon.getInstance(this).clearDevice();
    MyIbeacon.getInstance(this).stopScan();
    MyIbeacon.getInstance(this).startScan();
  }

  public boolean isLogin()
  {
    return getUser() != null;
  }

  public void onCreate()
  {
    super.onCreate();
    if (getCurProcessName(this).equals(getString(2131296277) + ":remote"))
      return;
    instance = this;
    JPushInterface.setDebugMode(false);
    JPushInterface.init(this);
    JPushInterface.setLatestNotificationNumber(getApplicationContext(), 3);
    SDKInitializer.initialize(getApplicationContext());
    FeedbackPush.getInstance(this).init(false);
    Logger.setDebug(true);
  }

  @SuppressLint({"SimpleDateFormat"})
  public void onLocationSuccess(BDLocation paramBDLocation)
  {
    Object localObject = Calendar.getInstance();
    localObject = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Calendar)localObject).getTime());
    Log.i(this.TAG, "AndroidApplication定位结果： (时间 : " + (String)localObject + ")" + paramBDLocation.getAddrStr() + " 经纬度： " + paramBDLocation.getLatitude() + "--" + paramBDLocation.getLongitude() + " city: " + paramBDLocation.getCity());
    if ((paramBDLocation.getCity() != null) && (!"null".equals(paramBDLocation.getCity())))
    {
      this.mCity = paramBDLocation.getCity();
      String str = paramBDLocation.getCity();
      localObject = str;
      if (str.endsWith("市"))
        localObject = str.substring(0, str.length() - 1);
      BaseActivity.city = (String)localObject;
      BaseActivity.log = String.valueOf(paramBDLocation.getLongitude());
      BaseActivity.lat = String.valueOf(paramBDLocation.getLatitude());
      BaseActivity.location = paramBDLocation.getAddrStr();
      SharedPreferenceUtil.setLocationCity(instance, (String)localObject, String.valueOf(paramBDLocation.getLongitude()), String.valueOf(paramBDLocation.getLatitude()));
      if ((this.user != null) && (localObject != null))
      {
        isInShop(this.user.getId(), paramBDLocation.getCity());
        this.hasRequestLesInfo = true;
      }
      if ((IbeaconService.area == null) || (IbeaconService.area.size() <= 0))
        IbeaconService.getAreaCondition_1(instance);
    }
  }

  public void onLowMemory()
  {
    super.onLowMemory();
    System.gc();
  }

  public void openBle()
  {
    this.myIbeacon = MyIbeacon.getInstance(getBaseContext());
    if (!this.myIbeacon.isSuportBlutoohLe())
      return;
    this.myIbeacon.initScanCallback();
    this.myIbeacon.openBlutoothLe();
    MyIbeacon.isSupportIbeacon = true;
  }

  public void setApplicationUser()
  {
    final SharedPreferences localSharedPreferences = getSharedPreferences("user_info", 0);
    String str2 = localSharedPreferences.getString("username", this.sUsername);
    String str3 = localSharedPreferences.getString("password", this.sPassword);
    if ((!str2.equals("")) && (!str3.equals("")))
    {
      setUser(SharedPreferenceUtil.getLoginInfo(getInstance()));
      final int i = localSharedPreferences.getInt("login_type", 1);
      Object localObject = new HashMap();
      String str1;
      switch (i)
      {
      default:
        str1 = Url.THIRD_LOGIN_URL;
        String str4 = Helper.MD5Params(new String[] { str2, TelephoneUtils.getIMEI(getApplicationContext()) });
        ((Map)localObject).put("thirdid", str2);
        ((Map)localObject).put("nickname", str3);
        ((Map)localObject).put("type", String.valueOf(i));
        ((Map)localObject).put("cityName", SharedPreferenceUtil.getLocationCity(instance));
        ((Map)localObject).put("devcode", TelephoneUtils.getIMEI(getApplicationContext()));
        ((Map)localObject).put("key", str4);
        localObject = HttpRequestParam.addDevInfoParams((Map)localObject, this);
      case 1:
      }
      while (true)
      {
        HttpRequest.getInstance().executePostStringRequest(instance.getApplicationContext(), str1, 2, (Map)localObject, new HttpCallback()
        {
          public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
          {
            AndroidApplication.this.touristRegistAndLogin();
          }

          public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
          {
            try
            {
              JSONObject localJSONObject = new JSONObject(((String)paramAnonymousResponse.get()).toString());
              if (Integer.valueOf(localJSONObject.getString("status")).intValue() == ResultCode.RESULT_OK)
              {
                paramAnonymousResponse = localJSONObject.getJSONObject("data").getJSONObject("user");
                AndroidApplication.this.user = new User();
                AndroidApplication.this.user.setId(paramAnonymousResponse.getString("id"));
                AndroidApplication.this.user.setUsername(paramAnonymousResponse.getString("username"));
                AndroidApplication.this.user.setPassword(paramAnonymousResponse.getString("password"));
                AndroidApplication.this.user.setNickname(URLDecoder.decode(paramAnonymousResponse.getString("nickname")));
                AndroidApplication.this.user.setInviteCode(paramAnonymousResponse.getString("inviteCode"));
                AndroidApplication.this.user.setByInviteCode(paramAnonymousResponse.getString("byInviteCode"));
                AndroidApplication.this.user.setEmail(paramAnonymousResponse.getString("email"));
                AndroidApplication.this.user.setSex(paramAnonymousResponse.getString("sex"));
                AndroidApplication.this.user.setAge(paramAnonymousResponse.getString("age"));
                AndroidApplication.this.user.setDevInvited(paramAnonymousResponse.optInt("devInvited"));
                AndroidApplication.this.user.setBeanNumber(localJSONObject.getJSONObject("data").getInt("userBeanNum"));
                AndroidApplication.this.user.setBirthday(paramAnonymousResponse.getString("birthday"));
                AndroidApplication.this.user.setType(paramAnonymousResponse.getString("type"));
                AndroidApplication.this.user.setThirdId(paramAnonymousResponse.getString("thirdid"));
                AndroidApplication.this.user.setPhone(paramAnonymousResponse.getString("mobile"));
                AndroidApplication.this.user.setLoginType(i);
                if (!localJSONObject.getJSONObject("data").isNull("signInfo"))
                {
                  localJSONObject = localJSONObject.getJSONObject("data").optJSONObject("signInfo");
                  AndroidApplication.this.user.setSignDays(localJSONObject.optInt("signdays"));
                  AndroidApplication.this.user.setSignRank(localJSONObject.optInt("signrank"));
                  AndroidApplication.this.user.setSignRankRate(localJSONObject.optString("signrankrate"));
                }
                if (i == 1)
                  AndroidApplication.this.user.setHeadIcon(Url.SERVER_URL + "/" + paramAnonymousResponse.getString("logoPath"));
                while (true)
                {
                  SharedPreferenceUtil.setDevInvited(AndroidApplication.this.getApplicationContext(), paramAnonymousResponse.optInt("devInvited"));
                  AndroidApplication.this.setUser(AndroidApplication.this.user);
                  Log.i(AndroidApplication.this.TAG, "header: " + AndroidApplication.this.user.getHeadIcon());
                  EventBus.getDefault().postSticky(new HomeActivityEvent("com.ismartgo.home.receiver"));
                  if ((AndroidApplication.this.user != null) && (AndroidApplication.this.mCity != null) && (!AndroidApplication.this.hasRequestLesInfo))
                  {
                    AndroidApplication.this.isInShop(AndroidApplication.this.user.getId(), AndroidApplication.this.mCity);
                    AndroidApplication.this.hasRequestLesInfo = true;
                  }
                  return;
                  AndroidApplication.this.user.setHeadIcon(localSharedPreferences.getString("headurl", ""));
                }
              }
            }
            catch (Exception paramAnonymousResponse)
            {
              while (true)
              {
                paramAnonymousResponse.printStackTrace();
                continue;
                AndroidApplication.this.touristRegistAndLogin();
              }
            }
          }
        });
        return;
        str1 = Url.LOGIN_URL;
        ((Map)localObject).put("phone", str2);
        ((Map)localObject).put("password", str3);
        ((Map)localObject).put("type", String.valueOf(1));
        ((Map)localObject).put("devcode", TelephoneUtils.getIMEI(getApplicationContext()));
      }
    }
    touristRegistAndLogin();
  }

  public void setUser(User paramUser)
  {
    this.user = paramUser;
    BaseActivity.loginUser = paramUser;
  }

  public void startIbeaconService()
  {
    startService(new Intent(this, IbeaconService.class));
  }

  public void stopService()
  {
    ToolLocation.stopLocation();
    if (CommonUtils.isStopService)
      return;
    CommonUtils.isStopService = true;
    MobclickAgent.onKillProcess(getApplicationContext());
    MyIbeacon.getInstance(this).stopScan();
    MyIbeacon.getInstance(this).closeBlutoothLe();
    System.out.println("停止服务");
    stopService(new Intent(this, IbeaconService.class));
  }

  public void touristRegistAndLogin()
  {
    setUser(SharedPreferenceUtil.getTouriseUser(getInstance()));
    TouristUserRequest localTouristUserRequest = new TouristUserRequest();
    localTouristUserRequest.setTouristRegistListener(new TouristUserRequest.TouristListener()
    {
      public void TouristInfo(User paramAnonymousUser)
      {
        SharedPreferenceUtil.setTouristUser(AndroidApplication.this.getApplicationContext(), paramAnonymousUser);
        AndroidApplication.this.setUser(paramAnonymousUser);
        EventBus.getDefault().postSticky(new HomeActivityEvent("com.ismartgo.home.receiver"));
        Log.i(AndroidApplication.this.TAG, "注册游客用户id: " + paramAnonymousUser.getId());
        if ((paramAnonymousUser != null) && (AndroidApplication.this.mCity != null) && (!AndroidApplication.this.hasRequestLesInfo))
        {
          AndroidApplication.this.isInShop(paramAnonymousUser.getId(), AndroidApplication.this.mCity);
          AndroidApplication.this.hasRequestLesInfo = true;
        }
      }

      public void rigistFail()
      {
      }
    });
    localTouristUserRequest.TouristRegistRequest(getApplicationContext());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.application.AndroidApplication
 * JD-Core Version:    0.6.2
 */