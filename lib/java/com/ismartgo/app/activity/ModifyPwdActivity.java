package com.ismartgo.app.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.DataUtil;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ModifyPwdActivity extends BaseActivity
  implements TextWatcher
{
  public static String TAG = "ModifyPwdActivity";

  @AbIocView(click="confirm", id=2131230782)
  private Button btn_confirm;

  @AbIocView(id=2131230849)
  private EditText et_confirm_new_pwd;

  @AbIocView(id=2131230848)
  private EditText et_new_pwd;

  @AbIocView(id=2131230847)
  private EditText et_old_pwd;
  public int sResultCode = 20002;

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("密码修改");
    this.et_old_pwd.addTextChangedListener(this);
    this.et_new_pwd.addTextChangedListener(this);
    this.et_confirm_new_pwd.addTextChangedListener(this);
  }

  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void confirm(final View paramView)
  {
    Object localObject = this.et_old_pwd.getText().toString();
    paramView = this.et_new_pwd.getText().toString();
    String str = this.et_confirm_new_pwd.getText().toString();
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      this.toast.setMessage("请输入原密码...");
      this.toast.show();
      return;
    }
    if (TextUtils.isEmpty(paramView))
    {
      this.toast.setMessage("请输入新密码...");
      this.toast.show();
      return;
    }
    if (TextUtils.isEmpty(str))
    {
      this.toast.setMessage("请确认新密码...");
      this.toast.show();
      return;
    }
    if (paramView.length() < 6)
    {
      this.toast.setMessage("新密码长度不能少于6...");
      this.toast.show();
      return;
    }
    if (!paramView.equals(str))
    {
      this.toast.setMessage("两次密码输入不一致...");
      this.toast.show();
      return;
    }
    if (!loginUser.getPassword().equals(DataUtil.md5((String)localObject)))
    {
      this.toast.setMessage("原密码输入不正确...");
      this.toast.show();
      return;
    }
    if (((String)localObject).equals(paramView))
    {
      this.toast.setMessage("新密码与原密码一致，请重新输入");
      this.toast.show();
      return;
    }
    localObject = new HashMap();
    ((Map)localObject).put("phone", loginUser.getUsername());
    ((Map)localObject).put("type", "2");
    ((Map)localObject).put("password", loginUser.getPassword());
    ((Map)localObject).put("newPassword", DataUtil.md5(paramView));
    ((Map)localObject).put("validCode", "");
    HttpRequest.getInstance().executePostStringRequest(this, Url.RESET_PASSWORD_URL, HttpWhat.RESET_PASSWORD, (Map)localObject, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        ModifyPwdActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        ModifyPwdActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          paramAnonymousInt = Integer.valueOf(paramAnonymousResponse.getString("status")).intValue();
          ModifyPwdActivity.this.toast.setMessage(paramAnonymousResponse.getString("status"));
          if (paramAnonymousInt == ResultCode.RESULT_OK)
          {
            ModifyPwdActivity.this.toast.setMessage("密码修改成功");
            paramAnonymousResponse = ModifyPwdActivity.this.getSharedPreferences("user_info", 0).edit();
            paramAnonymousResponse.putString("username", ModifyPwdActivity.loginUser.getUsername());
            paramAnonymousResponse.putString("password", DataUtil.md5(paramView));
            paramAnonymousResponse.commit();
            ModifyPwdActivity.this.setResult(ModifyPwdActivity.this.sResultCode);
            ModifyPwdActivity.this.finish();
          }
          while (true)
          {
            ModifyPwdActivity.this.toast.show();
            return;
            ModifyPwdActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          }
        }
        catch (Exception paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903048);
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
    if ((!TextUtils.isEmpty(this.et_old_pwd.getText().toString().trim())) && (!TextUtils.isEmpty(this.et_new_pwd.getText().toString().trim())) && (!TextUtils.isEmpty(this.et_confirm_new_pwd.getText().toString().trim())))
    {
      this.btn_confirm.setBackgroundResource(2130837524);
      this.btn_confirm.setEnabled(true);
      return;
    }
    this.btn_confirm.setBackgroundResource(2130837527);
    this.btn_confirm.setEnabled(false);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ModifyPwdActivity
 * JD-Core Version:    0.6.2
 */