package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.dodowaterfall.widget.ScaleImageView;
import com.example.android.bitmapfun.util.ImageFetcher;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.IsNewMsg;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonUtils;
import com.ismartgo.app.eventbus.HomeActivityEvent;
import com.ismartgo.app.grid.SmartStraggeredGridView;
import com.ismartgo.app.grid.SmartStraggeredGridView.IXListViewListener;
import com.ismartgo.app.grid.SmartStraggeredGridView.OnScrollState;
import com.ismartgo.app.grid.location.ToolLocation;
import com.ismartgo.app.grid.location.ToolLocation.InterfaceBDLocation;
import com.ismartgo.app.grid.utils.DisplayUtil;
import com.ismartgo.app.grid.utils.DistanceConversionUtils;
import com.ismartgo.app.grid.view.HomeHeadView;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpRequestParam;
import com.ismartgo.app.ibeacon.MyIbeacon;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.jpush.JPushSetAlias;
import com.ismartgo.app.map.BaiduMapActivity;
import com.ismartgo.app.net.MessageRequest;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.service.IbeaconService;
import com.ismartgo.app.share.ShareDialog;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.ismartgo.app.utils.UMengStatisticsUtils;
import com.ismartgo.beacon.impl.SmartgoBeanImpl;
import com.ismartgo.beacon.util.ScreenUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.SyncListener;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Reply;
import com.umeng.update.UmengUpdateAgent;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.Response;
import de.greenrobot.event.EventBus;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends BaseActivity
  implements View.OnClickListener, SmartStraggeredGridView.IXListViewListener, ToolLocation.InterfaceBDLocation, SwipeRefreshLayout.OnRefreshListener, SmartStraggeredGridView.OnScrollState
{
  public static final String KEY_EXTRAS = "extras";
  public static final String KEY_MESSAGE = "message";
  public static final String KEY_TITLE = "title";
  public static final String MESSAGE_RECEIVED_ACTION = "com.ismartgo.app.MESSAGE_RECEIVED_ACTION";
  public static final String SCAN_GOOD = "smartgoapp://saomiao";
  public static String TAG = "HomeActivity";
  public static final String UPLOAD_TICKET = "smartgoapp://xiaopiao";
  public static int bean = 0;
  public static String beanGet;
  public static int imageWidth;
  public static boolean isChanged = false;
  public static boolean isForeground;
  public static boolean isNeedRefresh = false;
  public static List<HashMap<String, Integer>> isNeedRefreshGoodsIdList;
  public static HomeActivity mContext;
  public static ToastDefine toast;
  public final int CLOTHING_STORE_TAG = 1;
  public int CURRENT_TAG = 0;
  public final int SHOP_TAG = 0;
  AndroidApplication application;
  boolean bv_flag = false;
  private LinearLayout choose_pro_item;
  private boolean collectClick = true;
  private float currentAlpha;
  private int currentClothingPage = 0;
  private int currentShopPage = 0;
  private TextView deparement_cloth;
  Handler han = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 2)
        if (AndroidApplication.isFirst)
        {
          AndroidApplication.isFirst = false;
          HomeActivity.this.displayPopWin(HomeActivity.beanGet);
        }
      while ((paramAnonymousMessage.what != 3) || (HomeActivity.this.popupWindow == null) || (!HomeActivity.this.popupWindow.isShowing()))
      {
        return false;
        HomeActivity.this.getBeansAtFirstOpenEveryDay();
        return false;
      }
      HomeActivity.this.popupWindow.dismiss();
      HomeActivity.this.popupWindow = null;
      return false;
    }
  });
  private int headerHeight;
  private HomeHeadView headerView;
  private ArrayList<String> imageUrlList = new ArrayList();
  private ImageView img_new_msg;
  private RelativeLayout imgv_msg;
  private boolean isFirstLocation;
  private boolean isLoading;
  private IsNewMsg isNewMsg;
  JPushSetAlias jpush;
  private RelativeLayout layoutRoot;
  private View layout_search;
  private StaggeredAdapter mAdapter = null;
  boolean mBound = false;
  private SmartStraggeredGridView mGridView;
  private ImageFetcher mImageFetcher;
  private LinkedList<Store> mInfos;
  private WaterFallHandler mWaterFallHandler;
  private WaterFallThread mWaterFallThread;
  MessageRequest msgRequest;
  private boolean once;
  private List<String> parseData;
  PopupWindow popupWindow = null;
  private int quitCount = 0;
  public SwipeRefreshLayout refreshLayout;
  private int shopType = 1;
  private TextView store;
  ContentTask task = new ContentTask(this, 2);
  TimeCount2 timeCount2 = new TimeCount2(2000L, 1000L);
  TimeCount3 timeCount3 = new TimeCount3(3250L, 65L);
  private LinearLayout titleLayout;
  private TextView tv_beans;
  public TextView tv_location;
  int uId;
  Handler uiHandler = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 0)
      {
        HomeActivity.this.tv_beans.setText(paramAnonymousMessage.obj.toString() + "豆");
        return false;
      }
      if (paramAnonymousMessage.what == 1)
      {
        HomeActivity.this.img_new_msg.setVisibility(4);
        return false;
      }
      if (paramAnonymousMessage.what == 2)
      {
        HomeActivity.this.img_new_msg.setVisibility(0);
        return false;
      }
      int i = paramAnonymousMessage.what;
      return false;
    }
  });
  User user;

  static
  {
    isForeground = false;
  }

  public static void changeCity(int paramInt)
  {
    switch (paramInt)
    {
    case 0:
    }
  }

  private void displayPopWin(String paramString)
  {
    View localView = LayoutInflater.from(this).inflate(2130903118, null);
    ((TextView)localView.findViewById(2131231043)).setText(String.valueOf(paramString));
    this.popupWindow = new PopupWindow(localView, -1, -2);
    this.popupWindow.setTouchable(true);
    this.popupWindow.setOutsideTouchable(true);
    this.popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), null));
    this.popupWindow.setAnimationStyle(2131427346);
    paramString = LayoutInflater.from(this).inflate(2130903042, null);
    this.popupWindow.showAtLocation(paramString, 17, 0, 0);
    if (SharedPreferenceUtil.getVoiceSwitch(this) == 1)
      startSuccessPlayer();
    this.timeCount3.start();
  }

  private void getBeaNumbers()
  {
    this.application = ((AndroidApplication)getApplication());
    this.user = this.application.getUser();
    this.application.addActivity(this);
    Message localMessage = new Message();
    localMessage.what = 0;
    if (this.user != null);
    for (localMessage.obj = String.valueOf(this.user.getBeanNumber()); ; localMessage.obj = "0")
    {
      this.uiHandler.sendMessage(localMessage);
      return;
    }
  }

  private void getBeansAtFirstOpenEveryDay()
  {
    HashMap localHashMap = new HashMap();
    if (this.user == null);
    for (Object localObject = "0"; ; localObject = this.user.getId())
    {
      localHashMap.put("uid", localObject);
      localObject = HttpRequestParam.addDevInfoParams(localHashMap, this);
      Log.i(TAG, "获取每天打开豆数url: " + Url.GET_BEAN_BY_OPENAPP + "?" + localObject);
      HttpRequest.getInstance().executePostStringRequest(this, Url.GET_BEAN_BY_OPENAPP, 9, (Map)localObject, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          Log.i(HomeActivity.TAG, "获取每天打开豆数失败: " + paramAnonymousInt);
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          Log.i(HomeActivity.TAG, "获取每天打开豆数结果: " + ((String)paramAnonymousResponse.get()).toString());
          while (true)
          {
            try
            {
              paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
              if (paramAnonymousResponse.getInt("status") != ResultCode.RESULT_OK)
                break;
              paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data");
              HomeActivity.bean = paramAnonymousResponse.getInt("beanNumber");
              paramAnonymousInt = paramAnonymousResponse.getInt("userBeanNum");
              if (HomeActivity.this.user != null)
              {
                if (HomeActivity.this.user.getLoginType() == 6)
                {
                  HomeActivity.loginUser.setBeanNumber(paramAnonymousInt);
                  SharedPreferenceUtil.setTouristBeanNumber(HomeActivity.this.getBaseContext(), paramAnonymousInt);
                }
              }
              else
              {
                if (HomeActivity.bean > 0)
                  HomeActivity.this.displayPopWin(String.valueOf(HomeActivity.bean));
                if (!paramAnonymousResponse.isNull("signInfo"))
                {
                  paramAnonymousResponse = paramAnonymousResponse.optJSONObject("signInfo");
                  HomeActivity.loginUser.setSignDays(paramAnonymousResponse.optInt("signdays"));
                  HomeActivity.loginUser.setSignRank(paramAnonymousResponse.optInt("signrank"));
                  HomeActivity.loginUser.setSignRankRate(paramAnonymousResponse.optString("signrankrate"));
                }
                paramAnonymousResponse = new Message();
                paramAnonymousResponse.what = 0;
                if (BaseActivity.loginUser == null)
                  break label275;
                paramAnonymousResponse.obj = String.valueOf(HomeActivity.loginUser.getBeanNumber());
                HomeActivity.this.uiHandler.sendMessage(paramAnonymousResponse);
                return;
              }
              HomeActivity.loginUser.setBeanNumber(paramAnonymousInt);
              SharedPreferenceUtil.setTouristBeanNumber(HomeActivity.this.getBaseContext(), 0);
              continue;
            }
            catch (JSONException paramAnonymousResponse)
            {
              paramAnonymousResponse.printStackTrace();
              return;
            }
            label275: paramAnonymousResponse.obj = "0";
          }
          Log.i("Test", "每天获取豆数异常： " + paramAnonymousResponse.getString("msg"));
        }
      });
      return;
    }
  }

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

  private void getMsgRequest(int paramInt1, int paramInt2)
  {
    this.msgRequest.initParams(paramInt1, paramInt2, 1);
    this.msgRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        HomeActivity.this.uiHandler.sendEmptyMessage(1);
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
        {
          HomeActivity.this.isNewMsg = ((IsNewMsg)paramAnonymousObject);
          if (HomeActivity.this.isNewMsg.isNews())
            HomeActivity.this.uiHandler.sendEmptyMessage(2);
        }
        else
        {
          return;
        }
        HomeActivity.this.uiHandler.sendEmptyMessage(1);
      }
    });
    this.msgRequest.startRequest();
  }

  private void init()
  {
    JPushInterface.init(this.application.getApplicationContext());
  }

  private void initView(HomeHeadView paramHomeHeadView)
  {
    this.imgv_msg = ((RelativeLayout)findViewById(2131230913));
    this.imgv_msg.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UMengStatisticsUtils.homeMessage(HomeActivity.this);
        paramAnonymousView = new Intent(HomeActivity.this, MsgActivity.class);
        HomeActivity.this.startActivity(paramAnonymousView);
      }
    });
    this.img_new_msg = ((ImageView)findViewById(2131230915));
    this.img_new_msg.setVisibility(4);
    this.layout_search = findViewById(2131230912);
    this.layout_search.setOnClickListener(this);
    this.tv_location = ((TextView)findViewById(2131230911));
    this.tv_beans = ((TextView)paramHomeHeadView.getView().findViewById(2131230783));
    this.choose_pro_item = ((LinearLayout)paramHomeHeadView.getView().findViewById(2131230786));
    this.deparement_cloth = ((TextView)paramHomeHeadView.getView().findViewById(2131230788));
    this.deparement_cloth.setOnClickListener(this);
    this.store = ((TextView)paramHomeHeadView.getView().findViewById(2131230787));
    this.store.setOnClickListener(this);
    this.layoutRoot = ((RelativeLayout)findViewById(2131230907));
  }

  private void refreshData()
  {
    if ((this.mInfos == null) || (this.mInfos.size() == 0) || (isNeedRefreshGoodsIdList == null) || (isNeedRefreshGoodsIdList.size() == 0))
      return;
    int i = 0;
    Store localStore;
    int j;
    while (true)
    {
      if (i >= this.mInfos.size())
      {
        if (this.mAdapter == null)
          break;
        this.mAdapter.notifyDataSetChanged();
        return;
      }
      localStore = (Store)this.mInfos.get(i);
      j = 0;
      if (j < isNeedRefreshGoodsIdList.size())
        break label97;
      i += 1;
    }
    label97: HashMap localHashMap = (HashMap)isNeedRefreshGoodsIdList.get(j);
    int k;
    if ((localStore.getShopId() == ((Integer)localHashMap.get("store")).intValue()) && (((Goods)localStore.getGoods_list().get(0)).getGoodsId() == ((Integer)localHashMap.get("goods")).intValue()))
    {
      this.mInfos.remove(i);
      k = ((Integer)localHashMap.get("isCollected")).intValue();
      if (k != 1)
        break label231;
      ((Goods)localStore.getGoods_list().get(0)).setCollect(true);
    }
    while (true)
    {
      this.mInfos.add(i, localStore);
      j += 1;
      break;
      label231: if (k == 2)
        ((Goods)localStore.getGoods_list().get(0)).setCollect(false);
    }
  }

  private void refreshData(int paramInt)
  {
    this.mInfos.clear();
    this.mAdapter.clearData();
    this.mAdapter.setItemList(this.mInfos);
    this.mGridView.clearColumnData();
    this.titleLayout.setBackgroundColor(0);
    if (paramInt == 0)
    {
      this.currentShopPage = 1;
      this.CURRENT_TAG = 0;
      this.shopType = Integer.parseInt(this.store.getTag().toString());
      setItem(this.currentShopPage, this.shopType);
      return;
    }
    this.currentClothingPage = 1;
    this.CURRENT_TAG = 1;
    this.shopType = Integer.parseInt(this.deparement_cloth.getTag().toString());
    setItem(this.currentClothingPage, this.shopType);
  }

  private void requestWaterFallData(int paramInt1, int paramInt2)
  {
    this.isLoading = true;
    String str = "";
    if (this.user != null)
      str = this.user.getId();
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", str);
    localHashMap.put("cityName", BaseActivity.city);
    localHashMap.put("shopType", this.shopType);
    localHashMap.put("lon", BaseActivity.log);
    localHashMap.put("lat", BaseActivity.lat);
    localHashMap.put("pages", paramInt1);
    localHashMap.put("pageSize", "30");
    HttpRequest.getInstance().executePostStringRequest(mContext, Url.NEARBYMACKET_URL, 7, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        HomeActivity.this.mWaterFallHandler.sendEmptyMessage(1001);
      }

      public void onSucceed(int paramAnonymousInt, Response<String> arg2)
      {
        Log.i(HomeActivity.TAG, "请求成功数据：" + ((String)???.get()).toString());
        if (HomeActivity.this.parseData != null)
        {
          HomeActivity.this.parseData.add(((String)???.get()).toString());
          synchronized (HomeActivity.this.mWaterFallThread)
          {
            HomeActivity.this.mWaterFallThread.notify();
            return;
          }
        }
        if (HomeActivity.this.refreshLayout.isRefreshing())
        {
          Log.i("Home", "refreshLayout 停止");
          HomeActivity.this.refreshLayout.setRefreshing(false);
        }
      }
    });
  }

  private void startSuccessPlayer()
  {
    MediaPlayer localMediaPlayer = MediaPlayer.create(this, 2131034116);
    localMediaPlayer.setLooping(false);
    localMediaPlayer.start();
  }

  private void umengUpdate()
  {
    UmengUpdateAgent.setUpdateOnlyWifi(false);
    UmengUpdateAgent.update(this);
  }

  private void updateBeansNum(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", paramString);
    Log.i(TAG, "获取用户精明豆数url: " + Url.GET_USER_BEAN + "?" + localHashMap.toString());
    HttpRequest.getInstance().executePostStringRequest(this, Url.GET_USER_BEAN, 17, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i(HomeActivity.TAG, "获取用户精明豆数结果 " + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousInt = new JSONObject(((String)paramAnonymousResponse.get()).toString()).getJSONObject("data").getInt("userBeanNum");
          HomeActivity.this.tv_beans.setText(paramAnonymousInt + "豆");
          HomeActivity.loginUser.setBeanNumber(paramAnonymousInt);
          return;
        }
        catch (JSONException paramAnonymousResponse)
        {
          Log.i(HomeActivity.TAG, "获取用户精明豆数结果解析有误 " + paramAnonymousResponse.getMessage());
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  private void updateMessage()
  {
    if ((this.user == null) || (TextUtils.isEmpty(this.user.getId())))
    {
      this.uId = 0;
      return;
    }
    if (this.user == null);
    for (int i = 0; ; i = Integer.valueOf(this.user.getId()).intValue())
    {
      this.uId = i;
      if (this.msgRequest == null)
        this.msgRequest = new MessageRequest(this, Url.MESSAGE_URL);
      getMsgRequest(this.uId, 0);
      return;
    }
  }

  public void clickCollect(final int paramInt, final ImageView paramImageView)
  {
    this.collectClick = false;
    Goods localGoods = (Goods)((Store)this.mAdapter.mInfos.get(paramInt)).getGoods_list().get(0);
    User localUser = this.application.getUser();
    if ((localUser == null) || (localUser.getLoginType() == 6))
    {
      this.collectClick = true;
      paramImageView = new Intent(this, LoginActivity.class);
      paramImageView.putExtra("goodsid", localGoods.getGoodsId());
      paramImageView.putExtra("shopid", ((Store)this.mInfos.get(paramInt)).getShopId());
      startActivityForResult(paramImageView, 0);
      return;
    }
    final boolean bool = localGoods.isCollect();
    HashMap localHashMap = new HashMap();
    if (bool);
    for (String str = Url.DEL_COLLECT_URL; ; str = Url.COLLECT_GOODS_URL)
    {
      localHashMap.put("uid", localUser.getId());
      localHashMap.put("goodsid", localGoods.getGoodsId());
      localHashMap.put("shopid", ((Store)this.mAdapter.mInfos.get(paramInt)).getShopId());
      Log.i("Home", "clickCollect 收藏url: " + str);
      HttpRequest.getInstance().executePostStringRequest(this, str, 16, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          HomeActivity.toast.setDisplay(false);
          HomeActivity.toast.setMessage("亲，网络好像出问题了哦~");
          HomeActivity.toast.show();
          HomeActivity.this.collectClick = true;
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          HomeActivity.this.collectClick = true;
          Log.i("Home", "clickCollect 收藏结果: " + ((String)paramAnonymousResponse.get()).toString());
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
            {
              HomeActivity.isChanged = true;
              HomeActivity.toast.setLayoutParams(150, 50);
              HomeActivity.toast.setDisplay(true);
              if (bool)
              {
                HomeActivity.toast.setImageResource(false);
                HomeActivity.toast.setMessage("已取消");
                ((Goods)((Store)HomeActivity.StaggeredAdapter.access$0(HomeActivity.this.mAdapter).get(paramInt)).getGoods_list().get(0)).setCollect(false);
                paramImageView.setImageResource(2130837619);
              }
              while (true)
              {
                HomeActivity.toast.show();
                return;
                HomeActivity.toast.setImageResource(true);
                HomeActivity.toast.setMessage("已收藏");
                ((Goods)((Store)HomeActivity.StaggeredAdapter.access$0(HomeActivity.this.mAdapter).get(paramInt)).getGoods_list().get(0)).setCollect(true);
                HomeActivity.this.mAdapter.updateData();
                paramImageView.setImageResource(2130837620);
                try
                {
                  MobclickAgent.onEvent(HomeActivity.this.getApplicationContext(), "home_collect");
                }
                catch (Exception paramAnonymousResponse)
                {
                  paramAnonymousResponse.printStackTrace();
                }
              }
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            paramAnonymousResponse.printStackTrace();
            return;
          }
          HomeActivity.toast.setMessage(paramAnonymousResponse.getString("msg"));
          HomeActivity.toast.show();
        }
      });
      return;
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Log.i("Home", "onActivityResult: " + paramInt1 + "  " + paramInt2);
    if ((paramInt1 == 0) && (paramInt2 == 20002))
      Log.i("Home", "收藏");
  }

  public void onClick(View paramView)
  {
    Intent localIntent = new Intent();
    switch (paramView.getId())
    {
    default:
    case 2131230912:
    case 2131230788:
    case 2131230787:
    }
    do
    {
      do
      {
        return;
        UMengStatisticsUtils.homeSearch(this);
        PromotionActivity.isChange = true;
        localIntent.setClass(this, SearchActivity.class);
        startActivity(localIntent);
        return;
      }
      while (this.CURRENT_TAG == 1);
      refreshData(1);
      return;
    }
    while (this.CURRENT_TAG == 0);
    refreshData(0);
  }

  @SuppressLint({"ClickableViewAccessibility"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().setBackgroundDrawable(null);
    setContentView(2130903061);
    Log.i("Test", "onCreate");
    this.jpush = new JPushSetAlias(getApplicationContext());
    if (CommonUtils.JPUSH_EXTRA != null)
    {
      paramBundle = new Intent(this, WebViewActivity.class);
      paramBundle.putExtra("url", CommonUtils.JPUSH_EXTRA);
      paramBundle.setFlags(268435456);
      startActivity(paramBundle);
    }
    this.headerHeight = ScreenUtil.dip2px(this, 362.0F);
    this.titleLayout = ((LinearLayout)findViewById(2131230910));
    this.titleLayout.setBackgroundColor(0);
    new FeedbackAgent(this).getDefaultConversation().sync(new SyncListener()
    {
      public void onReceiveDevReply(List<Reply> paramAnonymousList)
      {
        if ((paramAnonymousList == null) || (paramAnonymousList.size() == 0))
          return;
        if (paramAnonymousList.size() == 1);
        for (paramAnonymousList = ((Reply)paramAnonymousList.get(0)).content; ; paramAnonymousList = "有 " + paramAnonymousList.size() + "条反馈")
          try
          {
            NotificationManager localNotificationManager = (NotificationManager)HomeActivity.mContext.getSystemService("notification");
            Object localObject = new Intent(HomeActivity.mContext, UMengFbActivity.class);
            ((Intent)localObject).setFlags(131072);
            int i = (int)SystemClock.uptimeMillis();
            localObject = PendingIntent.getActivity(HomeActivity.mContext, i, (Intent)localObject, 134217728);
            i = HomeActivity.mContext.getPackageManager().getPackageInfo(HomeActivity.mContext.getPackageName(), 0).applicationInfo.icon;
            localNotificationManager.notify(0, new NotificationCompat.Builder(HomeActivity.mContext).setSmallIcon(i).setContentTitle("有新的回复").setTicker("有新的回复").setContentText(paramAnonymousList).setAutoCancel(true).setContentIntent((PendingIntent)localObject).build());
            return;
          }
          catch (Exception paramAnonymousList)
          {
            paramAnonymousList.printStackTrace();
            return;
          }
      }

      public void onSendUserReply(List<Reply> paramAnonymousList)
      {
      }
    });
    umengUpdate();
    MyIbeacon.getInstance(getBaseContext()).startScan();
    mContext = this;
    this.application = ((AndroidApplication)getApplication());
    this.user = this.application.getUser();
    imageWidth = DisplayUtil.getScreenWidth(this) / 2;
    toast = new ToastDefine(this);
    this.parseData = new ArrayList();
    this.mWaterFallHandler = new WaterFallHandler();
    this.mWaterFallThread = new WaterFallThread();
    this.mWaterFallThread.start();
    this.mInfos = new LinkedList();
    this.mAdapter = new StaggeredAdapter(this, this.mInfos);
    this.refreshLayout = ((SwipeRefreshLayout)findViewById(2131230908));
    this.refreshLayout.setOnRefreshListener(this);
    this.refreshLayout.setColorScheme(new int[] { 2131099700 });
    this.mGridView = ((SmartStraggeredGridView)findViewById(2131230909));
    this.mGridView.setCacheColorHint(Color.parseColor("#000000"));
    this.mGridView.setPullLoadEnable(true);
    this.mGridView.setXListViewListener(this);
    this.mGridView.setOnScrollState(this);
    int i = SharedPreferenceUtil.getGuideCount(this);
    Object localObject = SharedPreferenceUtil.getLocationInfo(this);
    paramBundle = ((SharedPreferences)localObject).getString("log", "");
    String str = ((SharedPreferences)localObject).getString("lat", "");
    localObject = ((SharedPreferences)localObject).getString("city", "");
    if (("".equals(paramBundle)) || ("".equals(str)))
    {
      Log.i(TAG, "本地经纬度为空，需要重新定位");
      ToolLocation.requestLocation(AndroidApplication.getInstance(), this, false);
    }
    while (true)
    {
      if (i <= 0)
      {
        SharedPreferenceUtil.setUsed(this);
        SharedPreferenceUtil.setGuideCount(this, 1);
      }
      this.headerView = new HomeHeadView(this, this.mGridView, this.mAdapter, this.mInfos);
      if ((AndroidApplication.getInstance().getCurrentLocation() != null) && (AndroidApplication.getInstance().getCurrentLocation().getCityId() != 0))
        this.headerView.initData(true);
      initView(this.headerView);
      this.mGridView.addHeaderView(this.headerView, null, false);
      this.mGridView.setAdapter(this.mAdapter);
      this.headerView.getAdvertiseImg(BaseActivity.city);
      getBeaNumbers();
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          HomeActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              HomeActivity.this.updateMessage();
            }
          });
        }
      }
      , 0L, 300000L);
      this.mImageFetcher = new ImageFetcher(this, 240);
      this.timeCount2.start();
      this.mImageFetcher.setExitTasksEarly(false);
      EventBus.getDefault().registerSticky(this);
      SmartgoBeanImpl.init(this);
      paramBundle = new IntentFilter();
      paramBundle.addAction("com.receiver.IntentReceiver");
      registerReceiver(new MyBroadCoast(null), paramBundle);
      return;
      this.tv_location = ((TextView)findViewById(2131230911));
      this.tv_location.setText((CharSequence)localObject);
      BaseActivity.lat = str;
      BaseActivity.log = paramBundle;
      BaseActivity.city = (String)localObject;
      int j = this.currentShopPage + 1;
      this.currentShopPage = j;
      requestWaterFallData(j, this.CURRENT_TAG);
      if (i > 0)
      {
        Log.i(TAG, "本地经纬度不为空，先请求数据，再重新定位");
        ToolLocation.requestLocation(AndroidApplication.getInstance(), this, false);
      }
      else
      {
        Log.i(TAG, "第一次打开，首页不需要定位");
      }
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    try
    {
      EventBus.getDefault().unregister(this);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void onEvent(HomeActivityEvent paramHomeActivityEvent)
  {
    if (paramHomeActivityEvent == null)
    {
      EventBus.getDefault().unregister(this);
      return;
    }
    if (paramHomeActivityEvent.getMsg().equals("com.ismartgo.home.receiver"))
    {
      Log.i(TAG, "首页收到广播");
      if ((this.user == null) || (!this.user.getId().equals(AndroidApplication.getInstance().getUser().getId())))
      {
        Log.i(TAG, "首页收到广播并更新数据");
        this.user = AndroidApplication.getInstance().getUser();
      }
    }
    while (true)
    {
      try
      {
        if (this.jpush == null)
          this.jpush = new JPushSetAlias(getApplicationContext());
        this.jpush.setAlias(this.user.getId());
        this.tv_beans.setText(this.user.getBeanNumber() + "豆");
        this.mGridView.setStateReady();
        this.mInfos.clear();
        this.mAdapter.setItemList(this.mInfos);
        this.mGridView.clearColumnData();
        if (this.CURRENT_TAG == 0)
        {
          this.currentShopPage = 0;
          i = this.currentShopPage + 1;
          this.currentShopPage = i;
          requestWaterFallData(i, this.CURRENT_TAG);
          if (this.imageUrlList.size() == 0)
            this.headerView.getAdvertiseImg(city);
          Log.d(TAG, "eventbus调用了");
          return;
        }
      }
      catch (Exception paramHomeActivityEvent)
      {
        StringBuilder localStringBuilder = new StringBuilder("jpush: ");
        if (this.jpush == null)
        {
          bool = true;
          Log.i("Exception", bool);
          localStringBuilder = new StringBuilder("user: ");
          if (this.user != null)
            continue;
          bool = true;
          Log.i("Exception", bool);
          paramHomeActivityEvent.printStackTrace();
          continue;
        }
        boolean bool = false;
        continue;
        bool = false;
        continue;
        if (this.CURRENT_TAG != 1)
          continue;
        this.currentClothingPage = 0;
        int i = this.currentClothingPage + 1;
        this.currentClothingPage = i;
        requestWaterFallData(i, this.CURRENT_TAG);
        continue;
      }
      this.user = AndroidApplication.getInstance().getUser();
      continue;
      if (paramHomeActivityEvent.getMsg().equals("com.ismartgo.home.scrollTop"))
      {
        Log.v("oneven", "top");
        refreshData(this.CURRENT_TAG);
      }
      else if (paramHomeActivityEvent.getMsg().equals("com.ismartgo.home.headview.refreshData"))
      {
        if (!this.once)
        {
          this.once = true;
          if (this.headerView != null)
          {
            this.headerView.initData(true);
            Log.d(TAG, "刷新了");
          }
        }
      }
      else if (paramHomeActivityEvent.getMsg().equals("com.ismartgo.home.headview.loginrefreshData"))
      {
        this.headerView.initData(true);
        Log.d("smartgo", "刷新精明头条");
      }
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.quitCount == 1)
      {
        AndroidApplication.getInstance().stopService();
        super.onKeyDown(paramInt, paramKeyEvent);
        return true;
      }
      this.quitCount += 1;
      toast.setDisplay(false);
      toast.setMessage("再按一次退出精明购！");
      toast.show();
      new QuitThread(null).start();
      return true;
    }
    return false;
  }

  public void onLoadMore()
  {
    if (!this.isLoading)
    {
      if (this.CURRENT_TAG != 0)
        break label36;
      i = this.currentShopPage + 1;
      this.currentShopPage = i;
      requestWaterFallData(i, this.CURRENT_TAG);
    }
    label36: 
    while (this.CURRENT_TAG != 1)
      return;
    int i = this.currentClothingPage + 1;
    this.currentClothingPage = i;
    requestWaterFallData(i, this.CURRENT_TAG);
  }

  public void onLocationSuccess(BDLocation paramBDLocation)
  {
    Object localObject = Calendar.getInstance();
    localObject = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(((Calendar)localObject).getTime());
    Log.i(TAG, "首页定位结果： (时间 : " + (String)localObject + ")" + paramBDLocation.getAddrStr() + " 经纬度： " + paramBDLocation.getLatitude() + "--" + paramBDLocation.getLongitude() + " city: " + paramBDLocation.getCity());
    if ((paramBDLocation.getCity() != null) && (!"null".equals(paramBDLocation.getCity())))
    {
      String str = paramBDLocation.getCity();
      localObject = str;
      if (str.endsWith("市"))
        localObject = str.substring(0, str.length() - 1);
      if (((String)localObject).equals(BaseActivity.city))
        break label428;
      BaseActivity.city = (String)localObject;
      BaseActivity.log = String.valueOf(paramBDLocation.getLongitude());
      BaseActivity.lat = String.valueOf(paramBDLocation.getLatitude());
      BaseActivity.location = paramBDLocation.getAddrStr();
      this.tv_location.setText((CharSequence)localObject);
      SharedPreferenceUtil.setLocationCity(this, (String)localObject, String.valueOf(paramBDLocation.getLongitude()), String.valueOf(paramBDLocation.getLatitude()));
      IbeaconService.area.clear();
      IbeaconService.getAreaCondition_1(this);
      Log.i(TAG, "切换城市");
      if ((this.user != null) && (!TextUtils.isEmpty(this.user.getId())));
    }
    else
    {
      return;
    }
    if (this.CURRENT_TAG == 1)
    {
      this.mGridView.clearColumnData();
      this.currentClothingPage = 1;
      this.CURRENT_TAG = 1;
      this.shopType = Integer.parseInt(this.deparement_cloth.getTag().toString());
      setItem(this.currentClothingPage, this.shopType);
    }
    while (true)
    {
      if ((IbeaconService.area == null) || (IbeaconService.area.size() <= 0))
        IbeaconService.getAreaCondition_1(this);
      if (!this.isFirstLocation)
        this.isFirstLocation = true;
      if ((BaseActivity.loginUser == null) || (localObject == null))
        break;
      AndroidApplication.getInstance().isInShop(BaseActivity.loginUser.getId(), paramBDLocation.getCity());
      return;
      if (this.CURRENT_TAG == 0)
      {
        this.mGridView.clearColumnData();
        this.currentShopPage = 1;
        this.CURRENT_TAG = 0;
        this.shopType = Integer.parseInt(this.store.getTag().toString());
        setItem(this.currentShopPage, this.shopType);
        continue;
        label428: BaseActivity.city = (String)localObject;
        BaseActivity.log = String.valueOf(paramBDLocation.getLongitude());
        BaseActivity.lat = String.valueOf(paramBDLocation.getLatitude());
        BaseActivity.location = paramBDLocation.getAddrStr();
        this.tv_location.setText((CharSequence)localObject);
        SharedPreferenceUtil.setLocationCity(this, (String)localObject, String.valueOf(paramBDLocation.getLongitude()), String.valueOf(paramBDLocation.getLatitude()));
      }
    }
  }

  protected void onPause()
  {
    super.onPause();
    isForeground = false;
    MobclickAgent.onPause(this);
    PromotionActivity.isClickInto = true;
  }

  public void onRefresh()
  {
    this.mGridView.setStateReady();
    this.mInfos.clear();
    this.mAdapter.setItemList(this.mInfos);
    this.mGridView.clearColumnData();
    int i;
    if (this.CURRENT_TAG == 0)
    {
      this.currentShopPage = 0;
      i = this.currentShopPage + 1;
      this.currentShopPage = i;
      requestWaterFallData(i, this.CURRENT_TAG);
    }
    while (true)
    {
      if (this.imageUrlList.size() == 0)
        this.headerView.getAdvertiseImg(city);
      Log.i(TAG, "刷新重新请求定位");
      if (ToolLocation.getLocationClient() != null)
      {
        ToolLocation.getLocationClient().stop();
        ToolLocation.getLocationClient().start();
      }
      if (this.headerView != null)
        this.headerView.initData(true);
      return;
      if (this.CURRENT_TAG == 1)
      {
        this.currentClothingPage = 0;
        i = this.currentClothingPage + 1;
        this.currentClothingPage = i;
        requestWaterFallData(i, this.CURRENT_TAG);
      }
    }
  }

  protected void onResume()
  {
    super.onResume();
    isForeground = true;
    MobclickAgent.onResume(this);
    if (BaseActivity.loginUser != null)
      this.jpush.setAlias(BaseActivity.loginUser.getId());
    getBeaNumbers();
    if (AndroidApplication.getInstance().getUser() != null)
      updateBeansNum(AndroidApplication.getInstance().getUser().getId());
    if (isNeedRefresh)
    {
      System.out.println("refresh");
      isNeedRefresh = false;
      refreshData();
    }
    do
    {
      do
        return;
      while (!isChanged);
      isChanged = false;
      if (this.CURRENT_TAG == 1)
      {
        this.mGridView.clearColumnData();
        this.currentClothingPage = 1;
        setItem(this.currentClothingPage, this.shopType);
        return;
      }
    }
    while (this.CURRENT_TAG != 0);
    this.mGridView.clearColumnData();
    this.currentShopPage = 1;
    setItem(this.currentShopPage, this.shopType);
  }

  public void onScrollChange(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    Logger.i("onScrollChange");
    if (!EventBus.getDefault().isRegistered(this))
    {
      Log.v("oneven", "register");
      EventBus.getDefault().register(this);
    }
    do
    {
      do
      {
        return;
        if (paramInt1 != 0)
          break;
        paramAbsListView = this.mGridView.getChildAt(0);
      }
      while (paramAbsListView == null);
      paramInt1 = -paramAbsListView.getTop() * 3;
      this.headerHeight = paramAbsListView.getHeight();
    }
    while ((paramInt1 > this.headerHeight) || (paramInt1 < 0));
    this.titleLayout.setBackgroundColor(getResources().getColor(2131099700));
    float f = paramInt1 / this.headerHeight;
    this.currentAlpha = (f * 255.0F);
    this.titleLayout.getBackground().setAlpha((int)(f * 255.0F));
    return;
    if (paramInt1 > 0)
    {
      this.titleLayout.setBackgroundColor(getResources().getColor(2131099700));
      return;
    }
    this.titleLayout.setBackgroundColor(0);
  }

  public void setItem(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 1)
    {
      this.choose_pro_item.setBackgroundResource(2130837610);
      this.store.setTextColor(-1);
      this.deparement_cloth.setTextColor(Color.parseColor("#FF9030"));
    }
    while (true)
    {
      this.mGridView.setStateReady();
      requestWaterFallData(paramInt1, paramInt2);
      return;
      if (paramInt2 == 2)
      {
        this.choose_pro_item.setBackgroundResource(2130837611);
        this.store.setTextColor(Color.parseColor("#FF9030"));
        this.deparement_cloth.setTextColor(-1);
      }
    }
  }

  private class ContentTask extends AsyncTask<String, Integer, List<Store>>
  {
    private Context mContext;
    private int mType = 1;

    public ContentTask(Context paramInt, int arg3)
    {
      this.mContext = paramInt;
      int i;
      this.mType = i;
    }

    protected List<Store> doInBackground(String[] paramArrayOfString)
    {
      try
      {
        paramArrayOfString = parseNewsJSON(paramArrayOfString[0]);
        return paramArrayOfString;
      }
      catch (IOException paramArrayOfString)
      {
        paramArrayOfString.printStackTrace();
      }
      return null;
    }

    protected void onPostExecute(List<Store> paramList)
    {
      if (this.mType == 1)
      {
        HomeActivity.this.mAdapter.clearData();
        if (paramList.size() > 0)
        {
          HomeActivity.this.findViewById(2131230856).setVisibility(8);
          HomeActivity.this.mAdapter.addItemLast(paramList);
          HomeActivity.this.mAdapter.notifyDataSetChanged();
        }
      }
      while ((this.mType != 2) || (paramList.size() <= 0))
      {
        return;
        HomeActivity.this.findViewById(2131230856).setVisibility(0);
        return;
      }
      HomeActivity.this.mAdapter.addItemLast(paramList);
      HomeActivity.this.mAdapter.notifyDataSetChanged();
    }

    protected void onPreExecute()
    {
    }

    public List<Store> parseNewsJSON(String paramString)
      throws IOException
    {
      return HttpJsonParse.getHomeStoreData(paramString, this.mContext, HomeActivity.imageWidth);
    }
  }

  private class MyBroadCoast extends BroadcastReceiver
  {
    private MyBroadCoast()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("com.receiver.IntentReceiver"))
      {
        paramContext = paramIntent.getStringExtra("url");
        if (!paramContext.equals("smartgoapp://xiaopiao"))
          break label63;
        paramContext = new Intent();
        paramContext.setFlags(268435456);
        paramContext.setClass(HomeActivity.this, UploadReceiptActivity.class);
        HomeActivity.this.startActivity(paramContext);
      }
      label63: 
      do
      {
        return;
        if (paramContext.equals("smartgoapp://dou"))
        {
          paramContext = new Intent();
          paramContext.setFlags(268435456);
          paramContext.setClass(HomeActivity.this, EarnBeansActivity.class);
          HomeActivity.this.startActivity(paramContext);
          return;
        }
        if (paramContext.equals("smartgoapp://msgdetail/298"))
        {
          paramContext = new Intent();
          paramContext.setFlags(268435456);
          paramContext.setClass(HomeActivity.this, PromotionActivity.class);
          HomeActivity.this.startActivity(paramContext);
          return;
        }
      }
      while (!paramContext.equals("smartgoapp://saomiao"));
      EarnBeansActivity.clickSignInAndScan(HomeActivity.this, 2);
    }
  }

  private class QuitThread extends Thread
  {
    private QuitThread()
    {
    }

    public void run()
    {
      try
      {
        sleep(2000L);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      finally
      {
        HomeActivity.this.quitCount = 0;
      }
    }
  }

  public class StaggeredAdapter extends BaseAdapter
  {
    private Context mContext;
    private LinkedList<Store> mInfos;

    public StaggeredAdapter(LinkedList<Store> arg2)
    {
      Object localObject1;
      this.mContext = localObject1;
      Object localObject2;
      this.mInfos = localObject2;
    }

    public void addItemLast(List<Store> paramList)
    {
      this.mInfos.addAll(paramList);
    }

    public void clearData()
    {
      this.mInfos.clear();
      notifyDataSetChanged();
    }

    public int getCount()
    {
      return this.mInfos.size();
    }

    public Object getItem(int paramInt)
    {
      return this.mInfos.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return 0L;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Store localStore = (Store)this.mInfos.get(paramInt);
      Goods localGoods;
      Object localObject;
      if (paramView == null)
      {
        paramView = LayoutInflater.from(paramViewGroup.getContext()).inflate(2130903247, null);
        paramViewGroup = new ViewHolder();
        paramViewGroup.layout_collect_share = paramView.findViewById(2131231485);
        paramViewGroup.store_name = ((TextView)paramView.findViewById(2131231297));
        paramViewGroup.store_distance = ((TextView)paramView.findViewById(2131231299));
        paramViewGroup.iv_location = ((ImageView)paramView.findViewById(2131231484));
        paramViewGroup.layout_mer_share = paramView.findViewById(2131231488);
        paramViewGroup.mer_share = ((ImageView)paramView.findViewById(2131230951));
        paramViewGroup.store_logo = ((ImageView)paramView.findViewById(2131231483));
        paramViewGroup.mer_scan = ((ImageView)paramView.findViewById(2131230948));
        paramViewGroup.mer_collect = ((ImageView)paramView.findViewById(2131230945));
        paramViewGroup.layout_mer_collect = paramView.findViewById(2131231487);
        paramViewGroup.goods_frame = ((FrameLayout)paramView.findViewById(2131231480));
        paramViewGroup.imageView = ((ScaleImageView)paramView.findViewById(2131231134));
        paramViewGroup.store_info_layout = ((LinearLayout)paramView.findViewById(2131231482));
        paramViewGroup.share_coll_layout = ((LinearLayout)paramView.findViewById(2131231486));
        paramViewGroup.mer_time = ((TextView)paramView.findViewById(2131231481));
        paramView.setTag(paramViewGroup);
        localGoods = (Goods)localStore.getGoods_list().get(0);
        int i = HomeActivity.this.mGridView.getColumnWidth();
        localObject = paramViewGroup.imageView.getLayoutParams();
        ((ViewGroup.LayoutParams)localObject).width = i;
        ((ViewGroup.LayoutParams)localObject).height = (Integer.valueOf(localGoods.getHeight()).intValue() * i / Integer.valueOf(localGoods.getWidth()).intValue());
        paramViewGroup.imageView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        String str = localGoods.getGoodsLogo();
        localObject = str;
        if (str != null)
        {
          localObject = str;
          if (!str.equals(""))
          {
            localObject = str;
            if (str.contains("?"))
              localObject = localGoods.getGoodsLogo().substring(0, localGoods.getGoodsLogo().lastIndexOf("?"));
          }
        }
        localObject = localObject + "?width=" + i;
        ImgLoader.getInstance(this.mContext).showPic((String)localObject, paramViewGroup.imageView, false);
        if (paramViewGroup.imageView.getTag() != null)
          break label806;
        Logger.i("tag为null 设置tag");
        paramViewGroup.imageView.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, 17432576));
        label458: paramViewGroup.imageView.setTag(localObject);
        paramViewGroup.goods_frame.setOnClickListener(new HomeActivity.onClickListener(HomeActivity.this, paramInt));
        paramViewGroup.imageView.setOnClickListener(new HomeActivity.onClickListener(HomeActivity.this, paramInt));
        paramViewGroup.store_name.setText(StringUtils.ToDBC(StringUtils.StringFilter(localStore.getShopName())));
        paramViewGroup.store_distance.setOnClickListener(new HomeActivity.onClickListener(HomeActivity.this, paramInt));
        paramViewGroup.store_distance.setText(DistanceConversionUtils.getDistance(localStore.getDistance()));
        paramViewGroup.store_info_layout.setOnClickListener(new HomeActivity.onClickListener(HomeActivity.this, paramInt));
        ImgLoader.getInstance(HomeActivity.this).showPic(localStore.getShopLogo() + "?width=" + i / 3, paramViewGroup.store_logo, false);
        if (localGoods.getGoodsScan() == 1)
          break label870;
        paramViewGroup.mer_scan.setVisibility(4);
        label639: if (!localGoods.isCollect())
          break label881;
        paramViewGroup.mer_collect.setImageResource(2130837620);
      }
      while (true)
      {
        paramViewGroup.layout_mer_collect.setOnClickListener(new HomeActivity.onClickListener(HomeActivity.this, paramInt, paramViewGroup.mer_collect));
        paramViewGroup.mer_share.setOnClickListener(new HomeActivity.onClickListener(HomeActivity.this, paramInt));
        paramViewGroup.layout_mer_share.setOnClickListener(new HomeActivity.onClickListener(HomeActivity.this, paramInt));
        if (localGoods.getGoodsEndDate().equals(""))
          break label894;
        localObject = localGoods.getGoodsEndDate().split("-");
        paramViewGroup.mer_time.setText(localObject[1] + "月" + localObject[2] + "日" + "前有效");
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
        break;
        label806: if (((String)paramViewGroup.imageView.getTag()).equals(localObject))
          break label458;
        Logger.i("tag不为null 设置tag" + paramViewGroup.imageView.getTag());
        paramViewGroup.imageView.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, 17432576));
        break label458;
        label870: paramViewGroup.mer_scan.setVisibility(0);
        break label639;
        label881: paramViewGroup.mer_collect.setImageResource(2130837619);
      }
      label894: System.out.println("--->有效期为空");
      return paramView;
    }

    public void setItemList(LinkedList<Store> paramLinkedList)
    {
      this.mInfos = paramLinkedList;
      notifyDataSetChanged();
    }

    public void updateData()
    {
      notifyDataSetChanged();
    }

    class ViewHolder
    {
      FrameLayout goods_frame;
      ScaleImageView imageView;
      ImageView iv_location;
      ImageView iv_scan;
      View layout_collect_share;
      View layout_mer_collect;
      View layout_mer_share;
      ImageView mer_collect;
      ImageView mer_scan;
      ImageView mer_share;
      TextView mer_time;
      LinearLayout share_coll_layout;
      TextView store_distance;
      LinearLayout store_info_layout;
      ImageView store_logo;
      TextView store_name;
      TextView tv_beans;

      ViewHolder()
      {
      }
    }
  }

  class TimeCount2 extends CountDownTimer
  {
    public TimeCount2(long arg2, long arg4)
    {
      super(localObject);
    }

    public void onFinish()
    {
      HomeActivity.this.han.sendEmptyMessage(2);
    }

    public void onTick(long paramLong)
    {
    }
  }

  class TimeCount3 extends CountDownTimer
  {
    public TimeCount3(long arg2, long arg4)
    {
      super(localObject);
    }

    public void onFinish()
    {
      HomeActivity.this.han.sendEmptyMessage(3);
    }

    public void onTick(long paramLong)
    {
    }
  }

  @SuppressLint({"HandlerLeak"})
  class WaterFallHandler extends Handler
  {
    WaterFallHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default:
      case 1000:
      case 1001:
      }
      while (true)
      {
        HomeActivity.this.isLoading = false;
        if (HomeActivity.this.refreshLayout.isRefreshing())
        {
          Log.i("Home", "refreshLayout 停止");
          HomeActivity.this.refreshLayout.setRefreshing(false);
        }
        do
        {
          do
          {
            return;
            paramMessage = (List)paramMessage.obj;
            if ((paramMessage == null) || (paramMessage.size() != 0))
              break label185;
            HomeActivity.this.mGridView.stopLoadMore();
            HomeActivity.this.isLoading = false;
            if (HomeActivity.this.CURRENT_TAG != 0)
              break;
          }
          while (HomeActivity.this.currentShopPage <= 0);
          paramMessage = HomeActivity.this;
          paramMessage.currentShopPage -= 1;
          return;
        }
        while ((HomeActivity.this.CURRENT_TAG != 1) || (HomeActivity.this.currentClothingPage <= 0));
        paramMessage = HomeActivity.this;
        paramMessage.currentClothingPage -= 1;
        return;
        label185: HomeActivity.this.mGridView.stopLoadMore();
        HomeActivity.this.isLoading = false;
        if (HomeActivity.this.CURRENT_TAG == 0)
        {
          if (HomeActivity.this.currentShopPage == 1)
            HomeActivity.this.mInfos.clear();
          if ((paramMessage != null) && (paramMessage.size() > 0))
          {
            HomeActivity.this.findViewById(2131230856).setVisibility(8);
            HomeActivity.this.mInfos.addAll(paramMessage);
            HomeActivity.this.mAdapter.setItemList(HomeActivity.this.mInfos);
          }
          else
          {
            HomeActivity.this.findViewById(2131230856).setVisibility(0);
          }
        }
        else if (HomeActivity.this.CURRENT_TAG == 1)
        {
          if (HomeActivity.this.currentClothingPage == 1)
            HomeActivity.this.mInfos.clear();
          if ((paramMessage != null) && (paramMessage.size() > 0))
          {
            HomeActivity.this.mInfos.addAll(paramMessage);
            HomeActivity.this.mAdapter.setItemList(HomeActivity.this.mInfos);
          }
          else
          {
            HomeActivity.this.findViewById(2131230856).setVisibility(0);
            continue;
            HomeActivity.this.mGridView.stopLoadMore();
            HomeActivity.toast.setMessage("亲，网络好像出问题了~ 再试一次");
            HomeActivity.toast.show();
            if (HomeActivity.this.CURRENT_TAG == 0)
            {
              if (HomeActivity.this.currentShopPage > 0)
              {
                paramMessage = HomeActivity.this;
                paramMessage.currentShopPage -= 1;
              }
            }
            else if ((HomeActivity.this.CURRENT_TAG == 1) && (HomeActivity.this.currentClothingPage > 0))
            {
              paramMessage = HomeActivity.this;
              paramMessage.currentClothingPage -= 1;
            }
          }
        }
      }
    }
  }

  class WaterFallThread extends Thread
  {
    WaterFallThread()
    {
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: invokespecial 24	java/lang/Thread:run	()V
      //   4: ldc 26
      //   6: ldc 28
      //   8: invokestatic 34	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   11: pop
      //   12: aload_0
      //   13: getfield 13	com/ismartgo/app/activity/HomeActivity$WaterFallThread:this$0	Lcom/ismartgo/app/activity/HomeActivity;
      //   16: invokestatic 38	com/ismartgo/app/activity/HomeActivity:access$9	(Lcom/ismartgo/app/activity/HomeActivity;)Ljava/util/List;
      //   19: invokeinterface 44 1 0
      //   24: ifle +60 -> 84
      //   27: aload_0
      //   28: getfield 13	com/ismartgo/app/activity/HomeActivity$WaterFallThread:this$0	Lcom/ismartgo/app/activity/HomeActivity;
      //   31: invokestatic 38	com/ismartgo/app/activity/HomeActivity:access$9	(Lcom/ismartgo/app/activity/HomeActivity;)Ljava/util/List;
      //   34: iconst_0
      //   35: invokeinterface 48 2 0
      //   40: checkcast 50	java/lang/String
      //   43: astore_1
      //   44: aload_1
      //   45: invokestatic 56	com/ismartgo/app/grid/utils/ResponseJsonUtils:parseWaterFallJson	(Ljava/lang/String;)Ljava/util/ArrayList;
      //   48: astore_1
      //   49: aload_0
      //   50: getfield 13	com/ismartgo/app/activity/HomeActivity$WaterFallThread:this$0	Lcom/ismartgo/app/activity/HomeActivity;
      //   53: invokestatic 60	com/ismartgo/app/activity/HomeActivity:access$10	(Lcom/ismartgo/app/activity/HomeActivity;)Lcom/ismartgo/app/activity/HomeActivity$WaterFallHandler;
      //   56: invokevirtual 66	com/ismartgo/app/activity/HomeActivity$WaterFallHandler:obtainMessage	()Landroid/os/Message;
      //   59: astore_2
      //   60: aload_2
      //   61: sipush 1000
      //   64: putfield 72	android/os/Message:what	I
      //   67: aload_2
      //   68: aload_1
      //   69: putfield 76	android/os/Message:obj	Ljava/lang/Object;
      //   72: aload_0
      //   73: getfield 13	com/ismartgo/app/activity/HomeActivity$WaterFallThread:this$0	Lcom/ismartgo/app/activity/HomeActivity;
      //   76: invokestatic 60	com/ismartgo/app/activity/HomeActivity:access$10	(Lcom/ismartgo/app/activity/HomeActivity;)Lcom/ismartgo/app/activity/HomeActivity$WaterFallHandler;
      //   79: aload_2
      //   80: invokevirtual 80	com/ismartgo/app/activity/HomeActivity$WaterFallHandler:sendMessage	(Landroid/os/Message;)Z
      //   83: pop
      //   84: aload_0
      //   85: monitorenter
      //   86: aload_0
      //   87: invokevirtual 85	java/lang/Object:wait	()V
      //   90: aload_0
      //   91: monitorexit
      //   92: goto -88 -> 4
      //   95: astore_1
      //   96: aload_0
      //   97: monitorexit
      //   98: aload_1
      //   99: athrow
      //   100: astore_1
      //   101: ldc 87
      //   103: new 89	java/lang/StringBuilder
      //   106: dup
      //   107: ldc 91
      //   109: invokespecial 94	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   112: aload_1
      //   113: invokevirtual 98	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   116: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   119: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   122: invokestatic 34	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   125: pop
      //   126: aload_1
      //   127: invokevirtual 108	java/lang/Exception:printStackTrace	()V
      //   130: goto -46 -> 84
      //   133: astore_1
      //   134: aload_1
      //   135: invokevirtual 109	java/lang/InterruptedException:printStackTrace	()V
      //   138: goto -48 -> 90
      //
      // Exception table:
      //   from	to	target	type
      //   86	90	95	finally
      //   90	92	95	finally
      //   96	98	95	finally
      //   134	138	95	finally
      //   44	84	100	java/lang/Exception
      //   86	90	133	java/lang/InterruptedException
    }
  }

  class onClickListener
    implements View.OnClickListener
  {
    ImageView img;
    int position;

    public onClickListener(int arg2)
    {
      int i;
      this.position = i;
    }

    public onClickListener(int paramImageView, ImageView arg3)
    {
      this.position = paramImageView;
      Object localObject;
      this.img = localObject;
    }

    public void onClick(View paramView)
    {
      System.out.println("--->click position=" + this.position);
      Store localStore = (Store)HomeActivity.access$5(HomeActivity.this).mInfos.get(this.position);
      Goods localGoods = (Goods)localStore.getGoods_list().get(0);
      Log.i(HomeActivity.TAG, "图片： " + localGoods.getGoodsLogo());
      switch (paramView.getId())
      {
      default:
      case 2131231134:
      case 2131231480:
      case 2131231299:
      case 2131231482:
      case 2131231487:
        do
        {
          return;
          UMengStatisticsUtils.homeMsgDetail(HomeActivity.this);
          paramView = new Intent(HomeActivity.this, MerChandiseDetailActivity.class);
          paramView.putExtra("store", localStore);
          paramView.putExtra("goods", (Serializable)((Store)HomeActivity.access$5(HomeActivity.this).mInfos.get(this.position)).getGoods_list().get(0));
          paramView.putExtra("position", this.position);
          paramView.putExtra("shopType", HomeActivity.this.shopType);
          HomeActivity.this.startActivity(paramView);
          return;
          paramView = new Intent();
          paramView.setClass(HomeActivity.this, BaiduMapActivity.class);
          paramView.putExtra("storeLon", localStore.getLon());
          paramView.putExtra("storeLat", localStore.getLat());
          paramView.putExtra("shopName", localStore.getShopName());
          paramView.putExtra("storeAddress", localStore.getShopAddress());
          paramView.setFlags(268435456);
          HomeActivity.this.getApplicationContext().startActivity(paramView);
          return;
        }
        while (!HomeActivity.this.collectClick);
        HomeActivity.this.clickCollect(this.position, this.img);
        return;
      case 2131230951:
      case 2131231488:
      }
      if (HomeActivity.this.user == null)
      {
        HomeActivity.toast.setMessage("用户数据有误，请检查网络");
        return;
      }
      paramView = new ShareDialog(HomeActivity.this);
      paramView.setShare(localGoods.getGoodsLogo(), "【" + ((Store)HomeActivity.access$5(HomeActivity.this).mInfos.get(this.position)).getReatilName() + "】 " + localGoods.getGoodsName(), localGoods.getGoodsDescribe(), localGoods.getGoodsId(), HomeActivity.this.user.getId(), 1);
      paramView.show();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.HomeActivity
 * JD-Core Version:    0.6.2
 */