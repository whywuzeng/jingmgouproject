package com.ismartgo.app.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.tools.DataUtil;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ResetPwdActivity extends BaseActivity
  implements View.OnClickListener, TextWatcher
{
  public static String TAG = "ResetPwdActivity";

  @AbIocView(click="onClick", id=2131230845)
  private Button btn_get_verify;

  @AbIocView(click="onClick", id=2131230899)
  private Button btn_reset_pwd;

  @AbIocView(id=2131230814)
  private EditText et_phone;

  @AbIocView(id=2131230815)
  private EditText et_pwd;

  @AbIocView(id=2131230846)
  private EditText et_verify;
  private MyDialog mDialog;
  private String phoneNum = "";
  TimeCount timeCount = new TimeCount(60000L, 1000L);

  private void getVerifyCode()
  {
    this.mDialog.show();
    final String str1 = this.et_phone.getText().toString();
    String str2 = TelephoneUtils.getIMEI(getApplicationContext());
    HashMap localHashMap = new HashMap();
    localHashMap.put("phone", str1);
    localHashMap.put("type", "2");
    localHashMap.put("devcode", str2);
    localHashMap.put("key", Helper.MD5Params(new String[] { str1, str2 }));
    HttpRequest.getInstance().executePostStringRequest(this, Url.GET_VALID_CODE_URL, 55, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        ResetPwdActivity.this.showToast("亲，网络好像出问题了哦~");
        ResetPwdActivity.this.mDialog.dismiss();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        ResetPwdActivity.this.mDialog.dismiss();
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            ResetPwdActivity.this.showToast("验证码已发送");
            ResetPwdActivity.this.timeCount.start();
            ResetPwdActivity.this.phoneNum = str1;
            return;
          }
          ResetPwdActivity.this.showToast(paramAnonymousResponse.getString("msg"));
          return;
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
    this.tv_title.setText("重设密码");
    this.et_phone.addTextChangedListener(this);
    this.et_verify.addTextChangedListener(this);
    this.et_pwd.addTextChangedListener(this);
  }

  private boolean isVerifyOK(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      showToast("您还没输入验证码哦...");
      return false;
    }
    return true;
  }

  private void resetPwd()
  {
    String str1 = this.et_phone.getText().toString();
    String str2 = this.et_verify.getText().toString();
    String str3 = this.et_pwd.getText().toString();
    if ((isMobileNo(str1)) && (isVerifyOK(str2)))
    {
      if (!this.phoneNum.equals(str1))
        showToast("您更改了手机号哦...");
    }
    else
      return;
    if (str3.length() < 6)
    {
      showToast("密码不能少于6位哦...");
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("phone", str1);
    localHashMap.put("type", "1");
    localHashMap.put("newPassword", "");
    localHashMap.put("password", DataUtil.md5(str3));
    localHashMap.put("validCode", str2);
    this.btn_reset_pwd.setEnabled(false);
    this.mDialog.show();
    HttpRequest.getInstance().executePostStringRequest(this, Url.RESET_PASSWORD_URL, HttpWhat.RESET_PASSWORD, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        ResetPwdActivity.this.showToast("亲，网络好像出问题了哦~");
        ResetPwdActivity.this.mDialog.dismiss();
        ResetPwdActivity.this.btn_reset_pwd.setEnabled(true);
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        ResetPwdActivity.this.mDialog.dismiss();
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        ResetPwdActivity.this.btn_reset_pwd.setEnabled(true);
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            SharedPreferenceUtil.setPassword(ResetPwdActivity.this, DataUtil.md5(ResetPwdActivity.this.et_pwd.toString()));
            ResetPwdActivity.this.showToast("密码重设成功");
            ResetPwdActivity.this.finish();
            return;
          }
          ResetPwdActivity.this.showToast(paramAnonymousResponse.getString("msg"));
          return;
        }
        catch (Exception paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  public void afterTextChanged(Editable paramEditable)
  {
    if ((!TextUtils.isEmpty(this.et_phone.getText().toString().trim())) && (!TextUtils.isEmpty(this.et_verify.getText().toString().trim())) && (!TextUtils.isEmpty(this.et_pwd.getText().toString().trim())))
    {
      this.btn_reset_pwd.setBackgroundResource(2130837524);
      this.btn_reset_pwd.setEnabled(true);
      return;
    }
    this.btn_reset_pwd.setBackgroundResource(2130837527);
    this.btn_reset_pwd.setEnabled(false);
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
    case 2131230899:
    }
    resetPwd();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903057);
    this.mDialog = new MyDialog(this);
    initView();
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
  }

  class TimeCount extends CountDownTimer
  {
    public TimeCount(long arg2, long arg4)
    {
      super(localObject);
    }

    public void onFinish()
    {
      ResetPwdActivity.this.btn_get_verify.setText("获取验证码");
      ResetPwdActivity.this.btn_get_verify.setEnabled(true);
    }

    public void onTick(long paramLong)
    {
      ResetPwdActivity.this.btn_get_verify.setEnabled(false);
      ResetPwdActivity.this.btn_get_verify.setText("(" + paramLong / 1000L + ")重新获取");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ResetPwdActivity
 * JD-Core Version:    0.6.2
 */