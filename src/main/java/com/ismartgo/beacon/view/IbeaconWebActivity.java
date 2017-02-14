package com.ismartgo.beacon.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.ismartgo.beacon.db.DBDao;
import com.ismartgo.beacon.pojo.BeaconActivityInfo;
import com.ismartgo.beacon.util.CommonMethod;
import com.ismartgo.beacon.util.ScreenUtil;

@TargetApi(11)
public class IbeaconWebActivity extends Activity
  implements DeleteView.OnDelClickListener
{
  public static final String SCAN_GOOD = "smartgoapp://saomiao";
  public static final String UPLOAD_TICKET = "smartgoapp://xiaopiao";

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    paramBundle = (BeaconActivityInfo)getIntent().getSerializableExtra("activity_info");
    Object localObject2 = new RelativeLayout(this);
    ((RelativeLayout)localObject2).setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    ((RelativeLayout)localObject2).setBackgroundResource(17170445);
    Object localObject3 = new LinearLayout(this);
    ((LinearLayout)localObject3).setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    ((LinearLayout)localObject3).setBackgroundResource(((Integer)CommonMethod.getResourceId(this, "layout_corner", "drawable")).intValue());
    ((LinearLayout)localObject3).setOrientation(1);
    Object localObject1 = new RelativeLayout.LayoutParams(-1, -1);
    int i = ScreenUtil.dip2px(this, 15.0F);
    ((RelativeLayout.LayoutParams)localObject1).setMargins(i, i, i, i);
    ((LinearLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject1);
    ((RelativeLayout)localObject2).addView((View)localObject3);
    localObject1 = new WebView(this);
    ((WebView)localObject1).setBackgroundColor(Color.parseColor("#FFFFFF"));
    ((WebView)localObject1).setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    ((LinearLayout)localObject3).addView((View)localObject1);
    localObject3 = new DeleteView(this);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    localLayoutParams.width = ScreenUtil.dip2px(this, 30.0F);
    localLayoutParams.height = ScreenUtil.dip2px(this, 30.0F);
    ((DeleteView)localObject3).setLayoutParams(localLayoutParams);
    ((RelativeLayout)localObject2).addView((View)localObject3);
    ((DeleteView)localObject3).setOnDelClickListener(this);
    setContentView((View)localObject2);
    localObject2 = getWindow();
    ((Window)localObject2).setGravity(17);
    ((Window)localObject2).setBackgroundDrawableResource(17170445);
    localObject3 = ((Window)localObject2).getAttributes();
    ((WindowManager.LayoutParams)localObject3).width = ScreenUtil.dip2px(this, 305.0F);
    ((WindowManager.LayoutParams)localObject3).height = ScreenUtil.dip2px(this, 480.0F);
    ((Window)localObject2).setAttributes((WindowManager.LayoutParams)localObject3);
    localObject2 = ((WebView)localObject1).getSettings();
    ((WebSettings)localObject2).setDisplayZoomControls(false);
    ((WebSettings)localObject2).setSupportZoom(true);
    ((WebSettings)localObject2).setJavaScriptEnabled(true);
    ((WebView)localObject1).setWebViewClient(new WebViewClient()
    {
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if ((paramAnonymousString.equals("smartgoapp://xiaopiao")) || (paramAnonymousString.equals("smartgoapp://dou")) || (paramAnonymousString.equals("smartgoapp://msgdetail/298")) || (paramAnonymousString.equals("smartgoapp://saomiao")))
        {
          paramAnonymousWebView = new Intent();
          paramAnonymousWebView.setAction("com.receiver.IntentReceiver");
          paramAnonymousWebView.putExtra("url", paramAnonymousString);
          IbeaconWebActivity.this.sendBroadcast(paramAnonymousWebView);
          IbeaconWebActivity.this.finish();
          return true;
        }
        return super.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString);
      }
    });
    if (paramBundle != null)
    {
      ((WebView)localObject1).loadUrl(paramBundle.getActivityUrl());
      DBDao.getInstance(this).updateBean(paramBundle);
    }
  }

  public void onDeleteListener(View paramView)
  {
    finish();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.view.IbeaconWebActivity
 * JD-Core Version:    0.6.2
 */