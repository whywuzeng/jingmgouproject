package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
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

public class UserArgeementActivity extends BaseActivity
{
  public static String TAG = "AboutActivity";
  private String title;
  private ToastDefine toast;

  @AbIocView(id=2131230833)
  private TextView tv_about;

  private void initView()
  {
    this.toast = new ToastDefine(this);
    initTitleBar();
    this.tv_title.setText(this.title);
    HashMap localHashMap = new HashMap();
    localHashMap.put("title", this.title);
    HttpRequest.getInstance().executePostStringRequest(this, Url.ABOUT_URL, HttpWhat.ABOUT, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        UserArgeementActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        UserArgeementActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONObject("agreeAbout");
            String str = paramAnonymousResponse.getString("memo");
            UserArgeementActivity.this.tv_about.setText(str);
            UserArgeementActivity.this.tv_title.setText(paramAnonymousResponse.getString("title"));
            return;
          }
          UserArgeementActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          UserArgeementActivity.this.toast.show();
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
    setContentView(2130903060);
    this.title = getIntent().getStringExtra("title");
    if (this.title == null)
      this.title = "关于";
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
 * Qualified Name:     com.ismartgo.app.activity.UserArgeementActivity
 * JD-Core Version:    0.6.2
 */