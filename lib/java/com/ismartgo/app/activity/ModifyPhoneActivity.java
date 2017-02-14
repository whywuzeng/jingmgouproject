package com.ismartgo.app.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ModifyPhoneActivity extends BaseActivity
  implements View.OnClickListener
{
  public static String TAG = "ModifyPhoneActivity";
  private Button btn_confirm;
  private Button btn_get_verify;
  private EditText et_new_phone;
  private EditText et_verify;
  private MyDialog mDialog;
  private String phoneNum = "";
  public int phone_ResultCode = 20004;
  TimeCount timeCount = new TimeCount(60000L, 1000L);

  private void getVerifyCode()
  {
    this.mDialog.show();
    final String str1 = this.et_new_phone.getText().toString();
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
        ModifyPhoneActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        ModifyPhoneActivity.this.toast.show();
        ModifyPhoneActivity.this.mDialog.dismiss();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        ModifyPhoneActivity.this.mDialog.dismiss();
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            ModifyPhoneActivity.this.timeCount.start();
            ModifyPhoneActivity.this.toast.setMessage("验证码已发送");
            ModifyPhoneActivity.this.phoneNum = str1;
          }
          while (true)
          {
            ModifyPhoneActivity.this.toast.show();
            return;
            ModifyPhoneActivity.this.btn_get_verify.setEnabled(true);
            ModifyPhoneActivity.this.btn_get_verify.setBackgroundResource(2130837525);
            ModifyPhoneActivity.this.btn_get_verify.setText("获取验证码");
            ModifyPhoneActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
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
    this.tv_title.setText("更换手机号码");
    this.et_new_phone = ((EditText)findViewById(2131230844));
    this.et_verify = ((EditText)findViewById(2131230846));
    this.btn_get_verify = ((Button)findViewById(2131230845));
    this.btn_confirm = ((Button)findViewById(2131230782));
    this.btn_get_verify.setOnClickListener(this);
    this.btn_confirm.setOnClickListener(this);
    this.et_new_phone.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        ModifyPhoneActivity.this.setLoginButtonBg();
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    this.et_verify.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        ModifyPhoneActivity.this.setLoginButtonBg();
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
  }

  private void setLoginButtonBg()
  {
    if ((!TextUtils.isEmpty(this.et_new_phone.getText().toString().trim())) && (!TextUtils.isEmpty(this.et_verify.getText().toString().trim())))
    {
      this.btn_confirm.setBackgroundResource(2130837524);
      this.btn_confirm.setEnabled(true);
      return;
    }
    this.btn_confirm.setBackgroundResource(2130837527);
    this.btn_confirm.setEnabled(false);
  }

  public void confirm()
  {
    final String str1 = this.et_new_phone.getText().toString();
    String str2 = this.et_verify.getText().toString();
    if (TextUtils.isEmpty(str1))
    {
      this.toast.setMessage("您还没有输入新号码哦...");
      this.toast.show();
      return;
    }
    if (TextUtils.isEmpty(str2))
    {
      this.toast.setMessage("您还没有输入验证码哦...");
      this.toast.show();
      return;
    }
    if (!this.phoneNum.equals(str1))
    {
      this.toast.setMessage("这不是您获取验证码时填写的新手机号码哦...");
      this.toast.show();
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("phone", loginUser.getUsername());
    localHashMap.put("newPhone", str1);
    localHashMap.put("validCode", str2);
    HttpRequest.getInstance().executePostStringRequest(this, Url.MODIFY_PHONE_URL, HttpWhat.MODIFY_PHONE, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        ModifyPhoneActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        ModifyPhoneActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            ModifyPhoneActivity.this.toast.setMessage("手机号码更改成功");
            paramAnonymousResponse = ModifyPhoneActivity.this.getSharedPreferences("user_info", 0).edit();
            ModifyPhoneActivity.loginUser.setUsername(str1);
            paramAnonymousResponse.putString("username", str1);
            paramAnonymousResponse.commit();
            ModifyPhoneActivity.this.setResult(ModifyPhoneActivity.this.phone_ResultCode);
            ModifyPhoneActivity.this.finish();
            return;
          }
          ModifyPhoneActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          ModifyPhoneActivity.this.toast.show();
          return;
        }
        catch (Exception paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131230845:
      do
        return;
      while (!isMobileNo(this.et_new_phone.getText().toString()));
      getVerifyCode();
      return;
    case 2131230782:
    }
    confirm();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mDialog = new MyDialog(this);
    setContentView(2130903047);
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

  class TimeCount extends CountDownTimer
  {
    public TimeCount(long arg2, long arg4)
    {
      super(localObject);
    }

    public void onFinish()
    {
      ModifyPhoneActivity.this.btn_get_verify.setText("获取验证码");
      ModifyPhoneActivity.this.btn_get_verify.setEnabled(true);
    }

    public void onTick(long paramLong)
    {
      ModifyPhoneActivity.this.btn_get_verify.setEnabled(false);
      ModifyPhoneActivity.this.btn_get_verify.setText("(" + paramLong / 1000L + ")重新获取");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ModifyPhoneActivity
 * JD-Core Version:    0.6.2
 */