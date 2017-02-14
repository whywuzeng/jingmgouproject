package com.ismartgo.app.activity;

import android.content.Intent;
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
import android.widget.Toast;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
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

public class SetVerifyActivity extends BaseActivity
  implements View.OnClickListener
{
  private Button btnConfirm;
  private Button btnGetVerify;
  private EditText etPhone;
  private EditText etVerify;
  private MyDialog mDialog;
  TimeCount timeCount = new TimeCount(60000L, 1000L);

  private void changeBtnConfirmStatus()
  {
    String str1 = this.etPhone.getText().toString().trim();
    String str2 = this.etVerify.getText().toString().trim();
    if ((str1 != null) && (!"".equals(str1)) && (str2 != null) && (!"".equals(str2)))
    {
      this.btnConfirm.setBackgroundResource(2130837524);
      this.btnConfirm.setEnabled(true);
      return;
    }
    this.btnConfirm.setBackgroundResource(2130837527);
    this.btnConfirm.setEnabled(false);
  }

  private void confirm()
  {
    String str1 = this.etVerify.getText().toString().trim();
    if (TextUtils.isEmpty(str1))
      Toast.makeText(this, "验证码输入有误", 0).show();
    String str2;
    do
    {
      return;
      str2 = this.etPhone.getText().toString().trim();
    }
    while (TextUtils.isEmpty(str2));
    Intent localIntent = new Intent();
    localIntent.putExtra("phone", str2);
    localIntent.putExtra("verifyCode", str1);
    setResult(-1, localIntent);
    finish();
  }

  private void getVerifyCode()
  {
    String str1 = this.etPhone.getText().toString().trim();
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
        SetVerifyActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        SetVerifyActivity.this.toast.show();
        SetVerifyActivity.this.mDialog.dismiss();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        SetVerifyActivity.this.mDialog.dismiss();
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            SetVerifyActivity.this.toast.setMessage("验证码已发送");
            SetVerifyActivity.this.timeCount.start();
          }
          while (true)
          {
            SetVerifyActivity.this.toast.show();
            return;
            SetVerifyActivity.this.timeCount.cancel();
            SetVerifyActivity.this.btnGetVerify.setEnabled(true);
            SetVerifyActivity.this.btnGetVerify.setText("获取验证码");
            SetVerifyActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
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
    this.tv_title.setText("设置手机号码");
    this.etPhone = ((EditText)findViewById(2131230814));
    this.etVerify = ((EditText)findViewById(2131230846));
    this.btnConfirm = ((Button)findViewById(2131230782));
    this.btnConfirm.setOnClickListener(this);
    this.btnGetVerify = ((Button)findViewById(2131230845));
    this.btnGetVerify.setOnClickListener(this);
    this.etPhone.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        SetVerifyActivity.this.changeBtnConfirmStatus();
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    this.etVerify.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        SetVerifyActivity.this.changeBtnConfirmStatus();
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    this.mDialog = new MyDialog(this);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230845:
      getVerifyCode();
      return;
    case 2131230782:
    }
    confirm();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903089);
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
      SetVerifyActivity.this.btnGetVerify.setText("获取验证码");
      SetVerifyActivity.this.btnGetVerify.setEnabled(true);
    }

    public void onTick(long paramLong)
    {
      SetVerifyActivity.this.btnGetVerify.setEnabled(false);
      SetVerifyActivity.this.btnGetVerify.setText("(" + paramLong / 1000L + ")重新获取");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SetVerifyActivity
 * JD-Core Version:    0.6.2
 */