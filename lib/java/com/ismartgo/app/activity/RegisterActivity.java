package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.DeviceIMEIUtils;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.RequestJSONUtils;
import com.ismartgo.app.grid.utils.ResponseJsonUtils;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpRequestParam;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.DataUtil;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint({"NewApi", "InflateParams"})
public class RegisterActivity extends BaseActivity
  implements View.OnClickListener, TextWatcher
{
  public static String TAG = "RegisterActivity";

  @AbIocView(click="onClick", id=2131230845)
  private Button btn_get_verify;

  @AbIocView(click="onClick", id=2131230896)
  private Button btn_register;

  @AbIocView(id=2131230897)
  private CheckBox cb_protocol;
  private Context context;

  @AbIocView(id=2131230871)
  private EditText et_invite_code;

  @AbIocView(id=2131230814)
  private EditText et_phone;

  @AbIocView(id=2131230815)
  private EditText et_pwd;

  @AbIocView(id=2131230846)
  private EditText et_verify;
  private LinearLayout layout_invite_code;
  private MyDialog mDialog;
  private PopupWindow popWin;
  private String pwd;
  private int resultCode = 10002;
  private int soundId = 2131034116;
  TimeCount timeCount = new TimeCount(60000L, 1000L);
  TimeCount2 timeCount2 = new TimeCount2(3250L, 65L);
  TimeCount3 timeCount3 = new TimeCount3(2000L, 100L);

  @AbIocView(click="onClick", id=2131230898)
  private TextView tv_get_agreement;

  private void getVerifyCode()
  {
    String str1 = this.et_phone.getText().toString().trim();
    this.mDialog.show();
    String str2 = TelephoneUtils.getIMEI(getApplicationContext());
    HashMap localHashMap = new HashMap();
    localHashMap.put("phone", str1);
    localHashMap.put("type", "1");
    localHashMap.put("devcode", str2);
    localHashMap.put("key", Helper.MD5Params(new String[] { str1, str2 }));
    HttpRequest.getInstance().executePostStringRequest(this, Url.GET_VALID_CODE_URL, 55, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        RegisterActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        RegisterActivity.this.toast.show();
        RegisterActivity.this.mDialog.dismiss();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        RegisterActivity.this.mDialog.dismiss();
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            RegisterActivity.this.toast.setMessage("验证码已发送");
            RegisterActivity.this.timeCount.start();
          }
          while (true)
          {
            RegisterActivity.this.toast.show();
            return;
            RegisterActivity.this.timeCount.cancel();
            RegisterActivity.this.btn_get_verify.setEnabled(true);
            RegisterActivity.this.btn_get_verify.setText("获取验证码");
            RegisterActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          }
        }
        catch (Exception paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("注册");
    this.et_phone.addTextChangedListener(this);
    this.et_pwd.addTextChangedListener(this);
    this.et_verify.addTextChangedListener(this);
    this.layout_invite_code = ((LinearLayout)findViewById(2131230870));
  }

  private boolean isVerifyOK(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      this.toast.setMessage("您还没输入验证码哦...");
      this.toast.show();
      return false;
    }
    return true;
  }

  private void register()
  {
    final String str1 = this.et_phone.getText().toString().trim();
    String str3 = this.et_verify.getText().toString().trim();
    this.pwd = this.et_pwd.getText().toString().trim();
    final String str2 = this.et_invite_code.getText().toString().trim();
    if (!this.cb_protocol.isChecked())
    {
      this.toast.setMessage("您没勾选同意用户协议哦...");
      this.toast.show();
    }
    while ((!isMobileNo(str1)) || (!isVerifyOK(str3)))
      return;
    if (this.pwd.length() < 6)
    {
      this.toast.setMessage("密码不能少于6位哦...");
      this.toast.show();
      return;
    }
    this.pwd = DataUtil.md5(this.pwd);
    Object localObject = new HashMap();
    ((Map)localObject).put("phone", str1);
    ((Map)localObject).put("password", this.pwd);
    ((Map)localObject).put("validCode", str3);
    ((Map)localObject).put("byInviteCode", str2);
    ((Map)localObject).put("cityName", BaseActivity.city);
    ((Map)localObject).put("devCode", TelephoneUtils.getIMEI(getApplicationContext()));
    str3 = Helper.MD5Params(new String[] { str1, str3, TelephoneUtils.getIMEI(getApplicationContext()) });
    Log.i(TAG, "注册key: " + str3);
    ((Map)localObject).put("key", str3);
    localObject = HttpRequestParam.addDevInfoParams((Map)localObject, this);
    this.mDialog.show();
    this.btn_register.setEnabled(false);
    HttpRequest.getInstance().executePostStringRequest(this.context, Url.REGISTER_URL, HttpWhat.REGISTER, (Map)localObject, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        RegisterActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        RegisterActivity.this.toast.show();
        RegisterActivity.this.mDialog.dismiss();
        RegisterActivity.this.btn_register.setEnabled(true);
      }

      // ERROR //
      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        // Byte code:
        //   0: getstatic 74	java/lang/System:out	Ljava/io/PrintStream;
        //   3: new 76	java/lang/StringBuilder
        //   6: dup
        //   7: ldc 78
        //   9: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   12: aload_2
        //   13: invokeinterface 86 1 0
        //   18: checkcast 88	java/lang/String
        //   21: invokevirtual 92	java/lang/String:toString	()Ljava/lang/String;
        //   24: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   27: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   30: invokevirtual 102	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   33: getstatic 105	com/ismartgo/app/activity/RegisterActivity:TAG	Ljava/lang/String;
        //   36: new 76	java/lang/StringBuilder
        //   39: dup
        //   40: ldc 107
        //   42: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   45: aload_2
        //   46: invokeinterface 86 1 0
        //   51: checkcast 88	java/lang/String
        //   54: invokevirtual 92	java/lang/String:toString	()Ljava/lang/String;
        //   57: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   60: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   63: invokestatic 113	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   66: pop
        //   67: aload_0
        //   68: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   71: invokestatic 49	com/ismartgo/app/activity/RegisterActivity:access$7	(Lcom/ismartgo/app/activity/RegisterActivity;)Lcom/ismartgo/app/grid/utils/MyDialog;
        //   74: invokevirtual 54	com/ismartgo/app/grid/utils/MyDialog:dismiss	()V
        //   77: aload_0
        //   78: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   81: invokestatic 58	com/ismartgo/app/activity/RegisterActivity:access$8	(Lcom/ismartgo/app/activity/RegisterActivity;)Landroid/widget/Button;
        //   84: iconst_1
        //   85: invokevirtual 64	android/widget/Button:setEnabled	(Z)V
        //   88: aload_0
        //   89: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   92: ldc 114
        //   94: invokestatic 120	com/umeng/analytics/MobclickAgent:onEvent	(Landroid/content/Context;Ljava/lang/String;)V
        //   97: new 122	org/json/JSONObject
        //   100: dup
        //   101: aload_2
        //   102: invokeinterface 86 1 0
        //   107: checkcast 88	java/lang/String
        //   110: invokevirtual 92	java/lang/String:toString	()Ljava/lang/String;
        //   113: invokespecial 123	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   116: astore_2
        //   117: aload_2
        //   118: ldc 125
        //   120: invokevirtual 129	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   123: invokestatic 135	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
        //   126: invokevirtual 139	java/lang/Integer:intValue	()I
        //   129: istore_1
        //   130: aload_2
        //   131: ldc 141
        //   133: invokevirtual 145	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   136: astore 5
        //   138: getstatic 151	com/ismartgo/app/url/ResultCode:RESULT_OK	I
        //   141: istore_3
        //   142: iload_1
        //   143: iload_3
        //   144: if_icmpne +290 -> 434
        //   147: aload 5
        //   149: ldc 153
        //   151: invokevirtual 156	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
        //   154: instanceof 131
        //   157: ifeq +171 -> 328
        //   160: aload 5
        //   162: ldc 153
        //   164: invokevirtual 160	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   167: istore_1
        //   168: iload_1
        //   169: ifle +135 -> 304
        //   172: aload_0
        //   173: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   176: invokestatic 164	com/ismartgo/app/activity/RegisterActivity:access$9	(Lcom/ismartgo/app/activity/RegisterActivity;)Landroid/content/Context;
        //   179: invokestatic 170	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
        //   182: astore_2
        //   183: aload_2
        //   184: ldc 171
        //   186: aconst_null
        //   187: invokevirtual 175	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
        //   190: astore 5
        //   192: aload 5
        //   194: ldc 176
        //   196: invokevirtual 182	android/view/View:findViewById	(I)Landroid/view/View;
        //   199: checkcast 184	android/widget/TextView
        //   202: astore 6
        //   204: aload 6
        //   206: ldc 185
        //   208: invokevirtual 189	android/widget/TextView:setBackgroundResource	(I)V
        //   211: aload 6
        //   213: iload_1
        //   214: invokestatic 192	java/lang/String:valueOf	(I)Ljava/lang/String;
        //   217: invokevirtual 196	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
        //   220: aload_0
        //   221: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   224: new 198	android/widget/PopupWindow
        //   227: dup
        //   228: aload 5
        //   230: iconst_m1
        //   231: bipush 254
        //   233: iconst_1
        //   234: invokespecial 201	android/widget/PopupWindow:<init>	(Landroid/view/View;IIZ)V
        //   237: invokestatic 205	com/ismartgo/app/activity/RegisterActivity:access$2	(Lcom/ismartgo/app/activity/RegisterActivity;Landroid/widget/PopupWindow;)V
        //   240: aload_0
        //   241: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   244: invokestatic 209	com/ismartgo/app/activity/RegisterActivity:access$1	(Lcom/ismartgo/app/activity/RegisterActivity;)Landroid/widget/PopupWindow;
        //   247: ldc 210
        //   249: invokevirtual 213	android/widget/PopupWindow:setAnimationStyle	(I)V
        //   252: aload_2
        //   253: ldc 214
        //   255: aconst_null
        //   256: invokevirtual 175	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
        //   259: astore_2
        //   260: aload_0
        //   261: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   264: invokestatic 209	com/ismartgo/app/activity/RegisterActivity:access$1	(Lcom/ismartgo/app/activity/RegisterActivity;)Landroid/widget/PopupWindow;
        //   267: aload_2
        //   268: bipush 17
        //   270: iconst_0
        //   271: iconst_0
        //   272: invokevirtual 218	android/widget/PopupWindow:showAtLocation	(Landroid/view/View;III)V
        //   275: aload_0
        //   276: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   279: invokestatic 224	com/ismartgo/app/tools/SharedPreferenceUtil:getVoiceSwitch	(Landroid/content/Context;)I
        //   282: iconst_1
        //   283: if_icmpne +10 -> 293
        //   286: aload_0
        //   287: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   290: invokestatic 228	com/ismartgo/app/activity/RegisterActivity:access$10	(Lcom/ismartgo/app/activity/RegisterActivity;)V
        //   293: aload_0
        //   294: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   297: getfield 232	com/ismartgo/app/activity/RegisterActivity:timeCount2	Lcom/ismartgo/app/activity/RegisterActivity$TimeCount2;
        //   300: invokevirtual 238	com/ismartgo/app/activity/RegisterActivity$TimeCount2:start	()Landroid/os/CountDownTimer;
        //   303: pop
        //   304: aload_0
        //   305: getfield 25	com/ismartgo/app/activity/RegisterActivity$3:val$invite	Ljava/lang/String;
        //   308: invokestatic 244	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   311: istore 4
        //   313: iload 4
        //   315: ifne +12 -> 327
        //   318: aload_0
        //   319: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   322: ldc 246
        //   324: invokestatic 120	com/umeng/analytics/MobclickAgent:onEvent	(Landroid/content/Context;Ljava/lang/String;)V
        //   327: return
        //   328: new 248	android/content/Intent
        //   331: dup
        //   332: invokespecial 249	android/content/Intent:<init>	()V
        //   335: astore_2
        //   336: aload_2
        //   337: ldc 251
        //   339: aload_0
        //   340: getfield 23	com/ismartgo/app/activity/RegisterActivity$3:val$phone	Ljava/lang/String;
        //   343: invokevirtual 255	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   346: pop
        //   347: aload_2
        //   348: ldc_w 257
        //   351: aload_0
        //   352: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   355: invokestatic 261	com/ismartgo/app/activity/RegisterActivity:access$4	(Lcom/ismartgo/app/activity/RegisterActivity;)Ljava/lang/String;
        //   358: invokevirtual 255	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   361: pop
        //   362: aload_0
        //   363: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   366: aload_0
        //   367: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   370: invokestatic 265	com/ismartgo/app/activity/RegisterActivity:access$5	(Lcom/ismartgo/app/activity/RegisterActivity;)I
        //   373: aload_2
        //   374: invokevirtual 269	com/ismartgo/app/activity/RegisterActivity:setResult	(ILandroid/content/Intent;)V
        //   377: aload_0
        //   378: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   381: invokevirtual 272	com/ismartgo/app/activity/RegisterActivity:finish	()V
        //   384: goto -80 -> 304
        //   387: astore_2
        //   388: aload_2
        //   389: invokevirtual 275	java/lang/Exception:printStackTrace	()V
        //   392: goto -88 -> 304
        //   395: astore_2
        //   396: getstatic 105	com/ismartgo/app/activity/RegisterActivity:TAG	Ljava/lang/String;
        //   399: new 76	java/lang/StringBuilder
        //   402: dup
        //   403: ldc_w 277
        //   406: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   409: aload_2
        //   410: invokevirtual 280	java/lang/Exception:getMessage	()Ljava/lang/String;
        //   413: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   419: invokestatic 113	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   422: pop
        //   423: aload_2
        //   424: invokevirtual 275	java/lang/Exception:printStackTrace	()V
        //   427: return
        //   428: astore_2
        //   429: aload_2
        //   430: invokevirtual 275	java/lang/Exception:printStackTrace	()V
        //   433: return
        //   434: aload_0
        //   435: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   438: getfield 34	com/ismartgo/app/activity/RegisterActivity:toast	Lcom/ismartgo/app/ownself/view/ToastDefine;
        //   441: aload_2
        //   442: ldc_w 282
        //   445: invokevirtual 129	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   448: invokevirtual 42	com/ismartgo/app/ownself/view/ToastDefine:setMessage	(Ljava/lang/String;)V
        //   451: aload_0
        //   452: getfield 21	com/ismartgo/app/activity/RegisterActivity$3:this$0	Lcom/ismartgo/app/activity/RegisterActivity;
        //   455: getfield 34	com/ismartgo/app/activity/RegisterActivity:toast	Lcom/ismartgo/app/ownself/view/ToastDefine;
        //   458: invokevirtual 45	com/ismartgo/app/ownself/view/ToastDefine:show	()V
        //   461: return
        //
        // Exception table:
        //   from	to	target	type
        //   147	168	387	java/lang/Exception
        //   172	293	387	java/lang/Exception
        //   293	304	387	java/lang/Exception
        //   328	384	387	java/lang/Exception
        //   97	142	395	java/lang/Exception
        //   304	313	395	java/lang/Exception
        //   388	392	395	java/lang/Exception
        //   429	433	395	java/lang/Exception
        //   434	461	395	java/lang/Exception
        //   318	327	428	java/lang/Exception
      }
    });
  }

  private void startSuccessPlayer()
  {
    MediaPlayer localMediaPlayer = MediaPlayer.create(this, 2131034116);
    localMediaPlayer.setLooping(false);
    localMediaPlayer.start();
  }

  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131230845:
      do
        return;
      while (!isMobileNo(this.et_phone.getText().toString()));
      getVerifyCode();
      return;
    case 2131230896:
      register();
      return;
    case 2131230898:
    }
    paramView = new Intent(this, UserArgeementActivity.class);
    paramView.putExtra("title", "用户协议");
    startActivity(paramView);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903056);
    this.context = this;
    this.mDialog = new MyDialog(this);
    initView();
    MobclickAgent.setDebugMode(true);
    if ((loginUser == null) || (TextUtils.isEmpty(loginUser.getId())))
      return;
    RequestJSONUtils.getUserInfoRequest(this, loginUser.getId(), DeviceIMEIUtils.getUDID(this), new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        paramAnonymousInt = ResponseJsonUtils.parseUserJSON(((String)paramAnonymousResponse.get()).toString());
        Log.i(RegisterActivity.TAG, "是否显示邀请码： " + paramAnonymousInt);
        if (paramAnonymousInt == 0)
        {
          RegisterActivity.this.et_invite_code.setEnabled(false);
          RegisterActivity.this.et_invite_code.setHint("您已输入过邀请码，不能再输入哦~");
          return;
        }
        RegisterActivity.this.et_invite_code.setEnabled(true);
      }
    });
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.popWin != null)
    {
      if (this.popWin.isShowing())
        this.popWin.dismiss();
      this.popWin = null;
    }
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

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((!TextUtils.isEmpty(this.et_phone.getText().toString().trim())) && (!TextUtils.isEmpty(this.et_pwd.getText().toString().trim())) && (!TextUtils.isEmpty(this.et_verify.getText().toString().trim())))
    {
      this.btn_register.setBackgroundResource(2130837524);
      this.btn_register.setEnabled(true);
      return;
    }
    this.btn_register.setBackgroundResource(2130837527);
    this.btn_register.setEnabled(false);
  }

  class TimeCount extends CountDownTimer
  {
    public TimeCount(long arg2, long arg4)
    {
      super(localObject);
    }

    public void onFinish()
    {
      RegisterActivity.this.btn_get_verify.setEnabled(true);
      RegisterActivity.this.btn_get_verify.setText("获取验证码");
    }

    public void onTick(long paramLong)
    {
      RegisterActivity.this.btn_get_verify.setEnabled(false);
      RegisterActivity.this.btn_get_verify.setText("(" + paramLong / 1000L + ")重新获取");
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
      RegisterActivity.this.timeCount3.start();
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
      if ((RegisterActivity.this.popWin != null) && (RegisterActivity.this.popWin.isShowing()))
      {
        RegisterActivity.this.popWin.dismiss();
        RegisterActivity.this.popWin = null;
      }
      Intent localIntent = new Intent();
      localIntent.putExtra("phone", RegisterActivity.this.et_phone.getText().toString().trim());
      localIntent.putExtra("pwd", RegisterActivity.this.pwd);
      RegisterActivity.this.setResult(RegisterActivity.this.resultCode, localIntent);
      RegisterActivity.this.finish();
    }

    public void onTick(long paramLong)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.RegisterActivity
 * JD-Core Version:    0.6.2
 */