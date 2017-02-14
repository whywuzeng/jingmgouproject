package com.ismartgo.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.eventbus.HomeActivityEvent;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpRequestParam;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.jpush.JPushSetAlias;
import com.ismartgo.app.net.SynchronousBeansRequest;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.DataUtil;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import de.greenrobot.event.EventBus;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity
  implements View.OnClickListener, Handler.Callback, PlatformActionListener
{
  public static String TAG = "LoginActivity";
  private int LOGIN_RESULT_CODE = 20002;
  AndroidApplication application;

  @AbIocView(click="onClick", id=2131230816)
  private Button btn_login;

  @AbIocView(click="onClick", id=2131230798)
  private Button btn_qq;

  @AbIocView(click="onClick", id=2131230799)
  private Button btn_weibo;

  @AbIocView(click="onClick", id=2131230820)
  private Button btn_weichat;

  @AbIocView(id=2131230814)
  private EditText et_phone;

  @AbIocView(id=2131230815)
  private EditText et_pwd;
  private int goodsid;
  private Handler handler;
  String headImgUrl = "";
  JPushSetAlias jpush;
  private LinearLayout layoutWechatLogin;
  private int loginType;
  private List<String> packageInfos;
  private PopupWindow popWin;
  private int requestCode = 10001;
  private int resultCode = 10002;
  private int shopid;
  TimeCount2 timeCount2 = new TimeCount2(3250L, 65L);
  private ToastDefine toastDefine;
  private TextView tvWeChat;

  @AbIocView(click="onClick", id=2131230817)
  private TextView tv_forget_pwd;

  private void SynchronousBeanNumber()
  {
    SynchronousBeansRequest localSynchronousBeansRequest = new SynchronousBeansRequest(this, Url.SYNCHRO_BEAN_NUMBER_URL);
    localSynchronousBeansRequest.initParams(this, loginUser.getId());
    localSynchronousBeansRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        LoginActivity.this.updateUser();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        if (paramAnonymousInt == ResultCode.RESULT_OK)
          try
          {
            paramAnonymousObject = new JSONObject(paramAnonymousObject.toString());
            paramAnonymousInt = paramAnonymousObject.getJSONObject("data").getInt("userBeanNum");
            int i = paramAnonymousObject.getJSONObject("data").getInt("moveBeanNum");
            LoginActivity.loginUser.setBeanNumber(paramAnonymousInt);
            if (i > 0)
            {
              LoginActivity.this.toastDefine.setMessage("您的" + i + "精明豆\n已成功放入当前登录账号");
              LoginActivity.this.toastDefine.show();
            }
            SharedPreferenceUtil.setTouristBeanNumber(LoginActivity.this, 0);
            LoginActivity.this.updateUser();
            return;
          }
          catch (JSONException paramAnonymousObject)
          {
            while (true)
            {
              Log.e(LoginActivity.TAG, "同步数据异常: " + paramAnonymousObject.getMessage());
              paramAnonymousObject.printStackTrace();
            }
          }
        LoginActivity.this.updateUser();
      }
    });
    localSynchronousBeansRequest.startRequest();
  }

  private void authorize(Platform paramPlatform)
  {
    if (paramPlatform == null)
      return;
    paramPlatform.setPlatformActionListener(this);
    paramPlatform.showUser(null);
  }

  private List<String> getDeviceAllAppPackage()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPackageManager().getInstalledPackages(4096).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      localArrayList.add(((PackageInfo)localIterator.next()).packageName);
    }
  }

  private void initView()
  {
    this.jpush = new JPushSetAlias(getApplicationContext());
    this.application = ((AndroidApplication)getApplication());
    initTitleBar();
    this.tvWeChat = ((TextView)findViewById(2131230819));
    this.tv_title.setText("登录");
    this.tv_left.setImageResource(2130837598);
    this.tv_right.setVisibility(0);
    this.tv_right.setText("注册");
    this.tv_right.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivityForResult(paramAnonymousView, LoginActivity.this.requestCode);
      }
    });
    this.et_phone.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        LoginActivity.this.setLoginButtonBg();
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    this.et_pwd.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        LoginActivity.this.setLoginButtonBg();
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    this.layoutWechatLogin = ((LinearLayout)findViewById(2131230818));
    this.layoutWechatLogin.setOnClickListener(this);
  }

  private void login()
  {
    String str1 = this.et_phone.getText().toString();
    String str2 = this.et_pwd.getText().toString();
    if (isMobileNo(str1))
    {
      if (str2.length() > 0)
      {
        this.btn_login.setText("正在登录...");
        this.btn_login.setEnabled(false);
        toLogin(str1, DataUtil.md5(str2));
      }
    }
    else
      return;
    this.toast.setMessage("您还没有输入密码呢...");
    this.toast.show();
  }

  private void loginResult(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject("data").getJSONObject("user");
      loginUser = new User();
      loginUser.setId(localJSONObject.getString("id"));
      loginUser.setUsername(paramString1);
      loginUser.setPassword(paramString2);
      loginUser.setNickname(URLDecoder.decode(localJSONObject.getString("nickname")));
      loginUser.setInviteCode(localJSONObject.getString("inviteCode"));
      loginUser.setByInviteCode(localJSONObject.getString("byInviteCode"));
      loginUser.setEmail(localJSONObject.getString("email"));
      loginUser.setSex(localJSONObject.getString("sex"));
      loginUser.setAge(localJSONObject.getString("age"));
      loginUser.setBeanNumber(paramJSONObject.getJSONObject("data").getInt("userBeanNum"));
      loginUser.setBirthday(localJSONObject.getString("birthday"));
      loginUser.setType(localJSONObject.getString("type"));
      loginUser.setThirdId(localJSONObject.getString("thirdid"));
      loginUser.setLoginType(this.loginType);
      loginUser.setPhone(localJSONObject.getString("mobile"));
      loginUser.setDevInvited(localJSONObject.getInt("devInvited"));
      if (!paramJSONObject.getJSONObject("data").isNull("signInfo"))
      {
        paramJSONObject = paramJSONObject.getJSONObject("data").optJSONObject("signInfo");
        loginUser.setSignDays(paramJSONObject.optInt("signdays"));
        loginUser.setSignRank(paramJSONObject.optInt("signrank"));
        loginUser.setSignRankRate(paramJSONObject.optString("signrankrate"));
      }
      SharedPreferenceUtil.setDevInvited(this, localJSONObject.optInt("devInvited"));
      if (this.loginType != 1)
        if ((this.headImgUrl != null) && (!"".equals(this.headImgUrl.trim())))
          loginUser.setHeadIcon(this.headImgUrl);
      while (true)
      {
        getSharedPreferences("user_info", 0).edit().putString("headurl", this.headImgUrl).commit();
        if ((this.goodsid > -1) && (this.shopid > -1))
          clickCollect();
        SynchronousBeanNumber();
        EventBus.getDefault().post(new HomeActivityEvent("com.ismartgo.home.headview.loginrefreshData"));
        AndroidApplication.getInstance().isInShop(loginUser.getId(), BaseActivity.city + "市");
        MobclickAgent.onEvent(this, "login");
        return;
        this.headImgUrl = (Url.SERVER_URL + "/" + localJSONObject.getString("logoPath"));
        loginUser.setHeadIcon(this.headImgUrl);
      }
    }
    catch (JSONException paramJSONObject)
    {
      while (true)
        paramJSONObject.printStackTrace();
    }
  }

  private void setLoginButtonBg()
  {
    if ((!TextUtils.isEmpty(this.et_phone.getText().toString().trim())) && (!TextUtils.isEmpty(this.et_pwd.getText().toString().trim())))
    {
      this.btn_login.setBackgroundResource(2130837524);
      this.btn_login.setEnabled(true);
      return;
    }
    this.btn_login.setBackgroundResource(2130837527);
    this.btn_login.setEnabled(false);
  }

  private void startSuccessPlayer()
  {
    MediaPlayer localMediaPlayer = MediaPlayer.create(this, 2131034116);
    localMediaPlayer.setLooping(false);
    localMediaPlayer.start();
  }

  private void toLogin(final String paramString1, final String paramString2)
  {
    String str;
    Object localObject;
    if (this.loginType == 1)
    {
      str = Url.LOGIN_URL;
      localObject = new HashMap();
      ((Map)localObject).put("phone", paramString1);
      ((Map)localObject).put("password", paramString2);
      ((Map)localObject).put("type", String.valueOf(1));
      ((Map)localObject).put("devcode", TelephoneUtils.getIMEI(getApplicationContext()));
    }
    while (true)
    {
      Log.i(TAG, "登录url: " + str);
      HttpRequest.getInstance().executePostStringRequest(this, str, HttpWhat.LOGIN, (Map)localObject, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          LoginActivity.this.btn_login.setText("登录");
          LoginActivity.this.btn_login.setEnabled(true);
          LoginActivity.this.tvWeChat.setText("微信登录");
          LoginActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
          LoginActivity.this.toast.show();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
          Log.i(LoginActivity.TAG, "登录结果： " + ((String)paramAnonymousResponse.get()).toString());
          LoginActivity.this.btn_login.setEnabled(true);
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
            {
              if ((paramAnonymousResponse.getJSONObject("data").get("beanGet") instanceof Integer))
              {
                paramAnonymousInt = paramAnonymousResponse.getJSONObject("data").getInt("beanGet");
                if (paramAnonymousInt <= 0)
                  return;
                Object localObject = LayoutInflater.from(LoginActivity.this);
                View localView = ((LayoutInflater)localObject).inflate(2130903118, null);
                TextView localTextView = (TextView)localView.findViewById(2131231043);
                localTextView.setText(String.valueOf(paramAnonymousInt));
                localTextView.setBackgroundResource(2130837834);
                LoginActivity.this.popWin = new PopupWindow(localView, -1, -2, true);
                LoginActivity.this.popWin.setAnimationStyle(2131427346);
                localObject = ((LayoutInflater)localObject).inflate(2130903056, null);
                LoginActivity.this.popWin.showAtLocation((View)localObject, 17, 0, 0);
                if (SharedPreferenceUtil.getVoiceSwitch(LoginActivity.this) == 1)
                  LoginActivity.this.startSuccessPlayer();
                LoginActivity.this.timeCount2.setData(paramAnonymousResponse, paramString1, paramString2);
                LoginActivity.this.timeCount2.start();
                return;
              }
              LoginActivity.this.loginResult(paramAnonymousResponse, paramString1, paramString2);
              return;
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            paramAnonymousResponse.printStackTrace();
            return;
          }
          LoginActivity.this.showToast(paramAnonymousResponse.getString("msg"));
          LoginActivity.this.btn_login.setText("登录");
          LoginActivity.this.btn_weichat.setText("微信登录");
        }
      });
      return;
      str = Url.THIRD_LOGIN_URL;
      localObject = Helper.MD5Params(new String[] { paramString1, TelephoneUtils.getIMEI(getApplicationContext()) });
      HashMap localHashMap = new HashMap();
      localHashMap.put("thirdid", paramString1);
      localHashMap.put("nickname", paramString2);
      localHashMap.put("type", String.valueOf(this.loginType));
      localHashMap.put("cityName", city);
      localHashMap.put("devcode", TelephoneUtils.getIMEI(getApplicationContext()));
      localHashMap.put("logopath", this.headImgUrl);
      localHashMap.put("key", localObject);
      localObject = HttpRequestParam.addDevInfoParams(localHashMap, this);
      Log.i(TAG, "url: " + Url.THIRD_LOGIN_URL + "?" + localObject);
    }
  }

  private void updateUser()
  {
    this.application.setUser(loginUser);
    this.jpush.setAlias(loginUser.getId());
    SharedPreferenceUtil.setLoginInfo(this, loginUser);
    SharedPreferenceUtil.setLoginCount(this, SharedPreferenceUtil.getLoginCount(this) + 1);
    HomeActivity.isChanged = true;
    Intent localIntent = new Intent();
    setResult(this.LOGIN_RESULT_CODE, localIntent);
    finish();
  }

  public void clickCollect()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", loginUser.getId());
    localHashMap.put("goodsid", this.goodsid);
    localHashMap.put("shopid", this.shopid);
    HttpRequest.getInstance().executePostStringRequest(this, Url.COLLECT_GOODS_URL, HttpWhat.COLLECT_GOODS, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i("Test", "Login 收藏结果： " + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          if (Integer.valueOf(new JSONObject(((String)paramAnonymousResponse.get()).toString()).getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            PromotionActivity.isClickInto = true;
            SearchStorePromotionActivity.isChangeCollect_waterfull = true;
            HomeActivity.isChanged = true;
            SearchStorePromotionActivity.isChangeCollected = true;
          }
          return;
        }
        catch (Exception paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  public void finish()
  {
    super.finish();
    overridePendingTransition(2130968584, 2130968586);
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return false;
    case 0:
      this.tvWeChat.setText("微信登录");
      this.toast.setMessage("已取消");
      this.toast.show();
      return false;
    case -1:
      this.tvWeChat.setText("微信登录");
      this.toast.setMessage("授权失败");
      this.toast.show();
      return false;
    case 1:
    }
    paramMessage = paramMessage.obj.toString();
    toLogin(paramMessage.substring(0, paramMessage.indexOf(',')), paramMessage.substring(paramMessage.indexOf(',') + 1));
    return false;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((this.requestCode == paramInt1) && (this.resultCode == paramInt2))
    {
      this.loginType = 1;
      this.btn_login.setText("正在登录...");
      this.btn_login.setEnabled(false);
      toLogin(paramIntent.getStringExtra("phone"), paramIntent.getStringExtra("pwd"));
    }
  }

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    Log.i("Test", "onCancel");
    if (paramInt == 8)
      this.handler.sendEmptyMessage(0);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230817:
      startActivity(new Intent(this, ResetPwdActivity.class));
      return;
    case 2131230816:
      this.loginType = 1;
      login();
      return;
    case 2131230818:
    case 2131230820:
      this.tvWeChat.setText("正在授权...");
      this.loginType = 4;
      authorize(ShareSDK.getPlatform(this, Wechat.NAME));
      return;
    case 2131230799:
      this.loginType = 5;
      authorize(ShareSDK.getPlatform(this, SinaWeibo.NAME));
      return;
    case 2131230798:
    }
    this.loginType = 3;
    paramView = ShareSDK.getPlatform(this, QQ.NAME);
    paramView.setPlatformActionListener(this);
    paramView.showUser(null);
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    Object localObject;
    if (paramInt == 8)
    {
      localObject = paramPlatform.getDb();
      paramPlatform = ((PlatformDb)localObject).getUserId();
      localObject = ((PlatformDb)localObject).getUserName();
      ShareSDK.removeCookieOnAuthorize(true);
      switch (this.loginType)
      {
      default:
      case 3:
      case 5:
      case 4:
      }
    }
    while (true)
    {
      paramHashMap = new Message();
      paramHashMap.what = 1;
      paramHashMap.obj = (paramPlatform + "," + (String)localObject);
      this.handler.sendMessage(paramHashMap);
      return;
      if ((paramHashMap != null) && (!paramHashMap.isEmpty()))
        this.headImgUrl = ((String)paramHashMap.get("figureurl_qq_2"));
      ShareSDK.getPlatform(this, QZone.NAME).removeAccount();
      Log.i(TAG, "--->QQ登录成功, id=" + paramPlatform + ",用户名=" + (String)localObject);
      this.jpush.setAlias(paramPlatform);
      continue;
      if ((paramHashMap != null) && (!paramHashMap.isEmpty()))
        this.headImgUrl = ((String)paramHashMap.get("avatar_large"));
      ShareSDK.getPlatform(this, SinaWeibo.NAME).removeAccount();
      Log.i(TAG, "--->微博登录成功, id=" + paramPlatform + ",用户名=" + (String)localObject);
      this.jpush.setAlias(paramPlatform);
      continue;
      ShareSDK.getPlatform(this, Wechat.NAME).removeAccount();
      if ((paramHashMap != null) && (!paramHashMap.isEmpty()))
        this.headImgUrl = ((String)paramHashMap.get("headimgurl"));
      paramPlatform = (String)paramHashMap.get("unionid");
      Log.i(TAG, "--->微信登录成功, id=" + paramPlatform + ",用户名=" + (String)localObject);
      this.jpush.setAlias(paramPlatform);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903045);
    overridePendingTransition(2130968583, 2130968585);
    this.toastDefine = new ToastDefine(this);
    this.goodsid = getIntent().getIntExtra("goodsid", -1);
    this.shopid = getIntent().getIntExtra("shopid", -1);
    this.handler = new Handler(this);
    initView();
    ShareSDK.initSDK(this);
    MobclickAgent.setDebugMode(false);
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    if (paramInt == 8)
    {
      if (paramThrowable != null)
      {
        paramPlatform = paramThrowable.getClass().getSimpleName();
        if ((!CommonMethod.isEmpty(paramPlatform)) && (("WechatClientNotExistException".equals(paramPlatform)) || ("WechatTimelineNotSupportedException".equals(paramPlatform)) || ("WechatFavoriteNotSupportedException".equals(paramPlatform))))
        {
          System.out.println("没有安装客端");
          runOnUiThread(new Runnable()
          {
            public void run()
            {
              Toast.makeText(LoginActivity.this, "没有安装微信", 0).show();
            }
          });
        }
      }
      this.handler.sendEmptyMessage(-1);
    }
    paramThrowable.printStackTrace();
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  class TimeCount2 extends CountDownTimer
  {
    private String md5Pwd;
    private String phone;
    private JSONObject resultJson;

    public TimeCount2(long arg2, long arg4)
    {
      super(localObject);
    }

    public void onFinish()
    {
      if ((LoginActivity.this.popWin != null) && (LoginActivity.this.popWin.isShowing()))
      {
        LoginActivity.this.popWin.dismiss();
        LoginActivity.this.popWin = null;
      }
      LoginActivity.this.loginResult(this.resultJson, this.phone, this.md5Pwd);
    }

    public void onTick(long paramLong)
    {
    }

    public void setData(JSONObject paramJSONObject, String paramString1, String paramString2)
    {
      this.resultJson = paramJSONObject;
      this.phone = paramString1;
      this.md5Pwd = paramString2;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.LoginActivity
 * JD-Core Version:    0.6.2
 */