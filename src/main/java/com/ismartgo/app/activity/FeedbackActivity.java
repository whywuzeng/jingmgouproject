package com.ismartgo.app.activity;

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
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class FeedbackActivity extends BaseActivity
{
  public static String TAG = "FeedbackActivity";

  @AbIocView(click="confirm", id=2131230782)
  private Button btn_confirm;

  @AbIocView(id=2131230781)
  private EditText et_feedback;

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("意见反馈");
    this.et_feedback.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        if (!TextUtils.isEmpty(FeedbackActivity.this.et_feedback.getText().toString().trim()))
        {
          FeedbackActivity.this.btn_confirm.setEnabled(true);
          FeedbackActivity.this.btn_confirm.setBackgroundResource(2130837524);
          return;
        }
        FeedbackActivity.this.btn_confirm.setEnabled(false);
        FeedbackActivity.this.btn_confirm.setBackgroundResource(2130837527);
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
  }

  public void confirm(View paramView)
  {
    paramView = this.et_feedback.getText().toString();
    if (TextUtils.isEmpty(paramView))
    {
      this.toast.setMessage("您还未填写任何意见反馈哦...");
      this.toast.show();
      return;
    }
    if (loginUser == null)
    {
      this.toast.setMessage("您还未登录哦，请登录后再来吧...");
      this.toast.show();
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("userId", loginUser.getId());
    localHashMap.put("content", paramView);
    HttpRequest.getInstance().executePostStringRequest(this, Url.FEEDBACK_URL, HttpWhat.FEEDBACK, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        FeedbackActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        FeedbackActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            FeedbackActivity.this.toast.setMessage("非常感谢！您的意见已提交");
            FeedbackActivity.this.toast.show();
            FeedbackActivity.this.finish();
            return;
          }
          FeedbackActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          FeedbackActivity.this.toast.show();
          return;
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
    setContentView(2130903041);
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.FeedbackActivity
 * JD-Core Version:    0.6.2
 */