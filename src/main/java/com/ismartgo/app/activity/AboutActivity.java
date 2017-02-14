package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ismartgo.app.grid.utils.Helper;
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

public class AboutActivity extends BaseActivity
{
  private LinearLayout layoutContactUs;
  private ToastDefine toast;
  private TextView tvAbout;
  private TextView tvAgreement;
  private TextView tvVersion;

  private void initView()
  {
    this.toast = new ToastDefine(this);
    initTitleBar();
    this.tv_title.setText("关于");
    this.tvAbout = ((TextView)findViewById(2131230833));
    this.tvVersion = ((TextView)findViewById(2131230842));
    Object localObject = Helper.getVersion(this);
    this.tvVersion.setText((CharSequence)localObject);
    localObject = new HashMap();
    ((Map)localObject).put("title", "关于");
    HttpRequest.getInstance().executePostStringRequest(this, Url.ABOUT_URL, HttpWhat.ABOUT, (Map)localObject, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        AboutActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        AboutActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONObject("agreeAbout").getString("memo");
            AboutActivity.this.tvAbout.setText(paramAnonymousResponse);
            return;
          }
          AboutActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          AboutActivity.this.toast.show();
          return;
        }
        catch (Exception paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
    this.tvAgreement = ((TextView)findViewById(2131230918));
    this.tvAgreement.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(AboutActivity.this, UserArgeementActivity.class);
        paramAnonymousView.putExtra("title", "用户协议");
        AboutActivity.this.startActivity(paramAnonymousView);
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903063);
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
 * Qualified Name:     com.ismartgo.app.activity.AboutActivity
 * JD-Core Version:    0.6.2
 */