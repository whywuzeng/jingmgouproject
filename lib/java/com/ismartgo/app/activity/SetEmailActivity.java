package com.ismartgo.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SetEmailActivity extends BaseActivity
  implements View.OnClickListener, HttpCallback<String>
{
  private static final String TAG = "SetEmailActivity";
  private Button btnConfirm;
  String email;
  private EditText etEmail;
  private Handler handler;
  private MyDialog mDialog;
  private TextView tvUploadResult;

  private void changeBtnConfirmStatus()
  {
    String str = this.etEmail.getText().toString().trim();
    if ((str != null) && (!"".equals(str)))
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
    this.mDialog.show();
    Object localObject = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.email = this.etEmail.getText().toString().trim();
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", String.valueOf(loginUser.getId()));
    localHashMap.put("email", this.email);
    localObject = ((SimpleDateFormat)localObject).format(new Date());
    localHashMap.put("sendDate", localObject);
    localHashMap.put("key", Helper.MD5Params(new String[] { String.valueOf(loginUser.getId()), this.email, localObject }));
    HttpRequest.getInstance().executePostStringRequest(this, Url.SEND_VAILD_EMAIL, HttpWhat.SEND_VAILD_EMAIL, localHashMap, this);
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("设置邮箱");
    this.etEmail = ((EditText)findViewById(2131230864));
    this.btnConfirm = ((Button)findViewById(2131230782));
    this.btnConfirm.setOnClickListener(this);
    this.etEmail.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        SetEmailActivity.this.changeBtnConfirmStatus();
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

  private AlertDialog uploadResultDialog()
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.show();
    localAlertDialog.setCancelable(false);
    View localView = LayoutInflater.from(this).inflate(2130903117, null);
    localView.findViewById(2131231116).setVisibility(8);
    this.tvUploadResult = ((TextView)localView.findViewById(2131231117));
    localAlertDialog.setContentView(localView);
    return localAlertDialog;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230782:
    }
    confirm();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903087);
    initView();
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    this.mDialog.dismiss();
    Toast.makeText(this, 2131296392, 0).show();
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

  public void onSucceed(int paramInt, final Response<String> paramResponse)
  {
    this.mDialog.dismiss();
    Log.i("SetEmailActivity", "邮箱验证： " + ((String)paramResponse.get()).toString());
    if (!TextUtils.isEmpty(this.email))
    {
      paramResponse = uploadResultDialog();
      this.tvUploadResult.setText("验证邮件已发送到\n" + this.email);
      paramResponse.show();
      if (this.handler == null)
        this.handler = new Handler();
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          if ((paramResponse != null) && (paramResponse.isShowing()))
            paramResponse.dismiss();
          SetEmailActivity.this.finish();
        }
      }
      , 1500L);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SetEmailActivity
 * JD-Core Version:    0.6.2
 */