package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.byl.datepicker.wheelview.OnWheelScrollListener;
import com.byl.datepicker.wheelview.WheelView;
import com.byl.datepicker.wheelview.adapter.NumericWheelAdapter;
import com.byl.datepicker.wheelview.adapter.WheelViewAdapter;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.bean.UserUsuallyAddress;
import com.ismartgo.app.common.CommonMethod;
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
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class MyDataActivity extends BaseActivity
  implements View.OnClickListener
{
  public static String TAG = "MyDataActivity";

  @AbIocView(click="onClick", id=2131230782)
  private Button btn_confirm;

  @AbIocView(click="onClick", id=2131230845)
  private Button btn_get_verify;
  String date;
  private WheelView day;
  private String email = "";
  private String emailCode;

  @AbIocView(id=2131230864)
  private TextView et_email;

  @AbIocView(id=2131230871)
  private EditText et_invite_code;

  @AbIocView(id=2131230860)
  private EditText et_nick;
  private LayoutInflater inflater = null;
  boolean isDaySetted = false;
  boolean isMonthSetted = false;
  private LinearLayout layoutModifyPhone;
  private LinearLayout layout_invite_code;

  @AbIocView(click="onClick", id=2131230863)
  private LinearLayout layout_modify_email;
  LinearLayout ll;
  private int mDay = 1;
  private MyDialog mDialog;
  private int mMonth = 0;
  private int mYear = 1950;
  private WheelView month;
  private String phoneNum = "";

  @AbIocView(id=2131230866)
  private RadioGroup rg_gender;
  OnWheelScrollListener scrollListener = new OnWheelScrollListener()
  {
    public void onScrollingFinished(WheelView paramAnonymousWheelView)
    {
      int i = MyDataActivity.this.year.getCurrentItem() + 1950;
      int j = MyDataActivity.this.month.getCurrentItem() + 1;
      int k = MyDataActivity.this.day.getCurrentItem() + 1;
      MyDataActivity.this.initDay(i, j);
      Log.i("Test", i + "年" + j + "月" + k + "日");
      Object localObject = String.valueOf(j);
      String str = String.valueOf(k);
      paramAnonymousWheelView = (WheelView)localObject;
      if (((String)localObject).length() == 1)
        paramAnonymousWheelView = "0" + (String)localObject;
      localObject = str;
      if (str.length() == 1)
        localObject = "0" + str;
      MyDataActivity.this.setTime(String.valueOf(i), paramAnonymousWheelView, (String)localObject);
    }

    public void onScrollingStarted(WheelView paramAnonymousWheelView)
    {
    }
  };
  TimeCount timeCount = new TimeCount(60000L, 1000L);
  TextView tvPhone;

  @AbIocView(click="onClick", id=2131230865)
  private TextView tv_birth;
  private TextView tv_usually_address;
  private ArrayList<UserUsuallyAddress> usuallyAddressList;
  private String verifyCode;
  View view = null;
  private WheelView year;

  private View getDataPick()
  {
    Object localObject = Calendar.getInstance();
    int i = ((Calendar)localObject).get(1);
    int j = ((Calendar)localObject).get(2);
    int k = ((Calendar)localObject).get(5);
    this.view = this.inflater.inflate(2130903252, null);
    this.year = ((WheelView)this.view.findViewById(2131231494));
    localObject = new NumericWheelAdapter(this, this.mYear, i);
    ((NumericWheelAdapter)localObject).setLabel("年");
    this.year.setViewAdapter((WheelViewAdapter)localObject);
    this.year.setCyclic(true);
    this.year.addScrollingListener(this.scrollListener);
    this.month = ((WheelView)this.view.findViewById(2131231495));
    localObject = new NumericWheelAdapter(this, 1, 12, "%02d");
    ((NumericWheelAdapter)localObject).setLabel("月");
    this.month.setViewAdapter((WheelViewAdapter)localObject);
    this.month.setCyclic(true);
    this.month.addScrollingListener(this.scrollListener);
    this.day = ((WheelView)this.view.findViewById(2131231496));
    initDay(i, i);
    this.day.setCyclic(true);
    this.day.addScrollingListener(this.scrollListener);
    this.year.setVisibleItems(7);
    this.month.setVisibleItems(7);
    this.day.setVisibleItems(7);
    this.year.setCurrentItem(i - this.mYear);
    this.month.setCurrentItem(j + 1 - 1);
    this.day.setCurrentItem(k - 1);
    return this.view;
  }

  private int getDay(int paramInt1, int paramInt2)
  {
    switch (paramInt1 % 4)
    {
    default:
    case 0:
    }
    for (paramInt1 = 0; ; paramInt1 = 1)
      switch (paramInt2)
      {
      case 4:
      case 6:
      case 9:
      case 11:
      default:
        return 30;
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
      case 2:
      }
    return 31;
    if (paramInt1 != 0)
      return 29;
    return 28;
  }

  private void initData()
  {
    if ((getUserUsuallyAddress() != null) && (getUserUsuallyAddress().size() > 0))
      localObject = getUserUsuallyAddress().iterator();
    UserUsuallyAddress localUserUsuallyAddress;
    do
    {
      if (!((Iterator)localObject).hasNext())
        return;
      localUserUsuallyAddress = (UserUsuallyAddress)((Iterator)localObject).next();
    }
    while (localUserUsuallyAddress.getIsDefault() != 1);
    Object localObject = "";
    if (!CommonMethod.isEmpty(localUserUsuallyAddress.getDistrict()))
      localObject = localUserUsuallyAddress.getDistrict();
    String str = "";
    if (!CommonMethod.isEmpty(localUserUsuallyAddress.getTown()))
      str = localUserUsuallyAddress.getTown();
    this.tv_usually_address.setText(localUserUsuallyAddress.getProvince() + localUserUsuallyAddress.getCity() + (String)localObject + str + localUserUsuallyAddress.getDetailAddress());
  }

  private void initDay(int paramInt1, int paramInt2)
  {
    NumericWheelAdapter localNumericWheelAdapter = new NumericWheelAdapter(this, 1, getDay(paramInt1, paramInt2), "%02d");
    localNumericWheelAdapter.setLabel("日");
    this.day.setViewAdapter(localNumericWheelAdapter);
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("我的资料");
    this.layout_invite_code = ((LinearLayout)findViewById(2131230870));
    if (!TextUtils.isEmpty(loginUser.getNickname()))
      this.et_nick.setText(loginUser.getNickname());
    if ((loginUser.getNickname() != null) && (!"".equals(loginUser.getNickname())))
    {
      this.btn_confirm.setBackgroundResource(2130837524);
      this.btn_confirm.setEnabled(true);
    }
    this.et_nick.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        if (MyDataActivity.this.et_nick.getText().toString().trim().equals(""))
        {
          MyDataActivity.this.btn_confirm.setBackgroundResource(2130837527);
          MyDataActivity.this.btn_confirm.setEnabled(false);
          return;
        }
        MyDataActivity.this.btn_confirm.setBackgroundResource(2130837524);
        MyDataActivity.this.btn_confirm.setEnabled(true);
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    this.et_email.setText(loginUser.getEmail());
    if (!TextUtils.isEmpty(loginUser.getBirthday()))
      this.tv_birth.setText(loginUser.getBirthday());
    if (loginUser.getSex().equals("1"))
    {
      this.rg_gender.check(2131230868);
      this.tvPhone = ((TextView)findViewById(2131230862));
      if (loginUser.getLoginType() != 1)
        break label376;
      this.tvPhone.setText(loginUser.getUsername());
    }
    while (true)
    {
      this.inflater = ((LayoutInflater)getSystemService("layout_inflater"));
      this.ll = ((LinearLayout)findViewById(2131230872));
      this.ll.addView(getDataPick());
      Button localButton1 = (Button)this.ll.findViewById(2131230874);
      Button localButton2 = (Button)this.ll.findViewById(2131230873);
      localButton1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = MyDataActivity.this.getTime();
          if (!TextUtils.isEmpty(paramAnonymousView))
            MyDataActivity.this.tv_birth.setText(paramAnonymousView);
          while (true)
          {
            MyDataActivity.this.ll.setVisibility(8);
            return;
            paramAnonymousView = Calendar.getInstance();
            int i = paramAnonymousView.get(1);
            int j = paramAnonymousView.get(2);
            int k = paramAnonymousView.get(5);
            Object localObject1 = String.valueOf(j + 1);
            Object localObject2 = String.valueOf(k);
            paramAnonymousView = (View)localObject1;
            if (((String)localObject1).length() == 1)
              paramAnonymousView = "0" + (String)localObject1;
            localObject1 = localObject2;
            if (((String)localObject2).length() == 1)
              localObject1 = "0" + (String)localObject2;
            localObject2 = new StringBuffer();
            ((StringBuffer)localObject2).append(i + "-").append(paramAnonymousView + "-").append((String)localObject1);
            MyDataActivity.this.tv_birth.setText(((StringBuffer)localObject2).toString());
          }
        }
      });
      localButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MyDataActivity.this.ll.setVisibility(8);
        }
      });
      this.layoutModifyPhone = ((LinearLayout)findViewById(2131230861));
      this.layoutModifyPhone.setOnClickListener(this);
      if (loginUser.getLoginType() == 1)
        this.layoutModifyPhone.setEnabled(false);
      this.tv_usually_address = ((TextView)findViewById(2131230869));
      this.tv_usually_address.setOnClickListener(this);
      return;
      this.rg_gender.check(2131230867);
      break;
      label376: this.tvPhone.setText(loginUser.getPhone());
    }
  }

  private void modifyData()
  {
    this.mDialog.show();
    final String str1 = this.et_email.getText().toString().trim();
    final String str2 = findViewById(this.rg_gender.getCheckedRadioButtonId()).getTag().toString().trim();
    final String str3 = this.tv_birth.getText().toString().trim();
    final String str4 = this.et_nick.getText().toString().trim();
    final String str5 = this.et_invite_code.getText().toString().trim();
    final String str6 = this.tvPhone.getText().toString().trim();
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", loginUser.getId());
    localHashMap.put("nickname", str4);
    label180: label202: String str7;
    if (str1 == null)
    {
      localObject = "";
      localHashMap.put("email", localObject);
      if (this.verifyCode != null)
        break label463;
      localObject = "";
      localHashMap.put("validCode", localObject);
      if (this.emailCode != null)
        break label471;
      localObject = "";
      localHashMap.put("emailvalidcode", localObject);
      localHashMap.put("birthday", str3);
      localHashMap.put("sex", str2);
      localHashMap.put("mobile", str6);
      if (!str5.equals(""))
        localHashMap.put("byInviteCode", str5);
      localHashMap.put("devcode", TelephoneUtils.getIMEI(getApplicationContext()));
      str7 = String.valueOf(loginUser.getId());
      if (this.verifyCode != null)
        break label479;
    }
    label463: label471: label479: for (Object localObject = ""; ; localObject = this.verifyCode)
    {
      localObject = Helper.MD5Params(new String[] { str7, str6, localObject, TelephoneUtils.getIMEI(getApplicationContext()) });
      Log.i(TAG, "修改资料key: " + (String)localObject);
      localHashMap.put("key", localObject);
      localObject = HttpRequestParam.addDevInfoParams(localHashMap, this);
      Log.i("HomeActivity", "url: " + Url.MODIFY_DATA_URL + "?" + localObject);
      HttpRequest.getInstance().executePostStringRequest(this, Url.MODIFY_DATA_URL, HttpWhat.MODIFY_USERS_DATA, (Map)localObject, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          MyDataActivity.this.mDialog.dismiss();
          MyDataActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
          MyDataActivity.this.toast.show();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
          Log.i(MyDataActivity.TAG, "修改资料结果： " + ((String)paramAnonymousResponse.get()).toString());
          MyDataActivity.this.mDialog.dismiss();
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
            {
              JSONObject localJSONObject = paramAnonymousResponse.getJSONObject("data").getJSONObject("user");
              MyDataActivity.this.toast.setMessage("修改成功");
              MyDataActivity.loginUser.setNickname(str4);
              User localUser = MyDataActivity.loginUser;
              if (str1 == null)
              {
                paramAnonymousResponse = "";
                localUser.setEmail(paramAnonymousResponse);
                MyDataActivity.loginUser.setBirthday(str3);
                MyDataActivity.loginUser.setSex(str2);
                MyDataActivity.loginUser.setPhone(str6);
                MyDataActivity.loginUser.setDevInvited(localJSONObject.optInt("devInvited"));
                if (!str5.equals(""))
                  MyDataActivity.loginUser.setByInviteCode(str5);
                MyDataActivity.this.finish();
              }
            }
            while (true)
            {
              MyDataActivity.this.toast.show();
              return;
              paramAnonymousResponse = str1;
              break;
              MyDataActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            Log.i(MyDataActivity.TAG, "修改资料异常： " + paramAnonymousResponse.getMessage().toString());
            paramAnonymousResponse.printStackTrace();
          }
        }
      });
      return;
      localObject = str1;
      break;
      localObject = this.verifyCode;
      break label180;
      localObject = this.emailCode;
      break label202;
    }
  }

  public String getTime()
  {
    return this.date;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    String str;
    if ((paramInt1 == 0) && (paramInt2 == -1))
    {
      str = paramIntent.getStringExtra("email");
      if (!TextUtils.isEmpty(str))
      {
        this.et_email.setText(str);
        this.emailCode = paramIntent.getStringExtra("verifyCode");
      }
    }
    do
    {
      do
        return;
      while ((paramInt1 != 1) || (paramInt2 != -1));
      str = paramIntent.getStringExtra("phone");
    }
    while (TextUtils.isEmpty(str));
    this.tvPhone.setText(str);
    this.verifyCode = paramIntent.getStringExtra("verifyCode");
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230865:
      if (this.ll.getVisibility() == 0)
      {
        this.ll.setVisibility(8);
        return;
      }
      paramView = AnimationUtils.loadAnimation(this, 2130968576);
      this.ll.setVisibility(0);
      this.ll.startAnimation(paramView);
      return;
    case 2131230782:
      modifyData();
      return;
    case 2131230863:
      startActivityForResult(new Intent(this, SetEmailActivity.class), 0);
      return;
    case 2131230861:
      startActivityForResult(new Intent(this, SetVerifyActivity.class), 1);
      return;
    case 2131230869:
    }
    startActivityForResult(new Intent(this, SetUsuallyAddressActivity.class), 2);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903053);
    this.mDialog = new MyDialog(this);
    initView();
    if ((loginUser == null) || (TextUtils.isEmpty(loginUser.getId())));
    do
    {
      return;
      RequestJSONUtils.getUserInfoRequest(this, loginUser.getId(), DeviceIMEIUtils.getUDID(this), new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          paramAnonymousInt = ResponseJsonUtils.parseUserJSON(((String)paramAnonymousResponse.get()).toString());
          String str = ResponseJsonUtils.parseUserInviteCode(((String)paramAnonymousResponse.get()).toString());
          Log.i("respose", "respose   : " + paramAnonymousResponse.toString());
          if (paramAnonymousInt == 0)
          {
            MyDataActivity.this.et_invite_code.setEnabled(false);
            if (TextUtils.isEmpty(str))
              MyDataActivity.this.et_invite_code.setHint("您已输入过邀请码，不能再输入哦~");
          }
          while (paramAnonymousInt != 1)
          {
            return;
            MyDataActivity.this.et_invite_code.setHint("已填写： " + str);
            return;
          }
          MyDataActivity.this.et_invite_code.setEnabled(true);
        }
      });
    }
    while ((this.usuallyAddressList != null) && (this.usuallyAddressList.size() > 0));
    RequestJSONUtils.getUserUsuallyAddress(this, loginUser.getId(), new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i("Test", "获取用户常用地址数据： " + ((String)paramAnonymousResponse.get()).toString());
        while (true)
        {
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (paramAnonymousResponse.getInt("status") != ResultCode.RESULT_OK)
              break label352;
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data");
            if (paramAnonymousResponse.isNull("userContact"))
              return;
            paramAnonymousResponse = paramAnonymousResponse.getJSONArray("userContact");
            if ((paramAnonymousResponse == null) || (paramAnonymousResponse.length() <= 0) || (MyDataActivity.this.usuallyAddressList != null))
              break label353;
            MyDataActivity.this.usuallyAddressList = new ArrayList();
            break label353;
            if (paramAnonymousInt >= paramAnonymousResponse.length())
            {
              if ((MyDataActivity.this.usuallyAddressList == null) || (MyDataActivity.this.usuallyAddressList.size() <= 0))
                break label352;
              MyDataActivity.this.setUsuallyAddress(MyDataActivity.this.usuallyAddressList);
              MyDataActivity.this.initData();
              return;
            }
          }
          catch (JSONException paramAnonymousResponse)
          {
            paramAnonymousResponse.printStackTrace();
            return;
          }
          UserUsuallyAddress localUserUsuallyAddress = new UserUsuallyAddress();
          JSONObject localJSONObject = paramAnonymousResponse.getJSONObject(paramAnonymousInt);
          localUserUsuallyAddress.setName(localJSONObject.optString("contactPerson"));
          localUserUsuallyAddress.setMobile(localJSONObject.optString("mobile"));
          localUserUsuallyAddress.setProvince(localJSONObject.optString("province"));
          localUserUsuallyAddress.setCity(localJSONObject.optString("city"));
          localUserUsuallyAddress.setDistrict(localJSONObject.optString("district"));
          localUserUsuallyAddress.setDetailAddress(localJSONObject.optString("address"));
          localUserUsuallyAddress.setIsDefault(localJSONObject.getInt("isDefault"));
          localUserUsuallyAddress.setTown(localJSONObject.optString("town"));
          localUserUsuallyAddress.setProvinceId(localJSONObject.optInt("province_id"));
          localUserUsuallyAddress.setCityId(localJSONObject.optInt("city_id"));
          localUserUsuallyAddress.setDistrictId(localJSONObject.optInt("district_id"));
          localUserUsuallyAddress.setTownId(localJSONObject.optInt("town_id"));
          MyDataActivity.this.usuallyAddressList.add(localUserUsuallyAddress);
          paramAnonymousInt += 1;
          continue;
          label352: return;
          label353: paramAnonymousInt = 0;
        }
      }
    });
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.ll.getVisibility() == 0))
    {
      this.ll.setVisibility(8);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
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
    initData();
  }

  public void setTime(String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramString1 + "-").append(paramString2 + "-").append(paramString3);
    this.date = localStringBuffer.toString();
  }

  class TimeCount extends CountDownTimer
  {
    public TimeCount(long arg2, long arg4)
    {
      super(localObject);
    }

    public void onFinish()
    {
      MyDataActivity.this.btn_get_verify.setEnabled(true);
      MyDataActivity.this.btn_get_verify.setText("获取验证码");
    }

    public void onTick(long paramLong)
    {
      MyDataActivity.this.btn_get_verify.setEnabled(false);
      MyDataActivity.this.btn_get_verify.setText("(" + paramLong / 1000L + ")重新获取");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MyDataActivity
 * JD-Core Version:    0.6.2
 */