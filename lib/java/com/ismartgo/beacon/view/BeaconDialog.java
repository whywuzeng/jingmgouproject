package com.ismartgo.beacon.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.ismartgo.beacon.util.CommonMethod;
import com.ismartgo.beacon.util.ScreenUtil;
import java.io.PrintStream;

public class BeaconDialog
{
  public static final String SCAN_GOOD = "smartgoapp://saomiao";
  public static final String UPLOAD_TICKET = "smartgoapp://xiaopiao";

  public BeaconDialog(Context paramContext, String paramString1, String paramString2)
  {
    createDialog(paramContext, paramString1, paramString2);
  }

  @SuppressLint({"NewApi", "ResourceAsColor"})
  private static void createDialog(Context paramContext, String paramString1, String paramString2)
  {
    paramString1 = new RelativeLayout(paramContext);
    paramString1.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    paramString1.setBackgroundResource(17170445);
    Object localObject2 = new LinearLayout(paramContext.getApplicationContext());
    ((LinearLayout)localObject2).setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    ((LinearLayout)localObject2).setOrientation(1);
    Object localObject1 = new RelativeLayout.LayoutParams(-1, -1);
    int i = ScreenUtil.dip2px(paramContext, 15.0F);
    ((RelativeLayout.LayoutParams)localObject1).setMargins(i, i, i, i);
    ((LinearLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject1);
    paramString1.addView((View)localObject2);
    localObject1 = new WebView(paramContext.getApplicationContext());
    ((WebView)localObject1).setBackgroundColor(17170443);
    ((WebView)localObject1).setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    ((LinearLayout)localObject2).addView((View)localObject1);
    ((LinearLayout)localObject2).setBackgroundResource(((Integer)CommonMethod.getResourceId(paramContext, "layout_corner", "drawable")).intValue());
    ((WebView)localObject1).loadUrl(paramString2);
    ((WebView)localObject1).setWebViewClient(new WebViewClient()
    {
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return true;
      }
    });
    paramString2 = new DeleteView(paramContext);
    localObject2 = new RelativeLayout.LayoutParams(-1, -1);
    ((RelativeLayout.LayoutParams)localObject2).addRule(10);
    ((RelativeLayout.LayoutParams)localObject2).addRule(11);
    ((RelativeLayout.LayoutParams)localObject2).width = ScreenUtil.dip2px(paramContext, 30.0F);
    ((RelativeLayout.LayoutParams)localObject2).height = ScreenUtil.dip2px(paramContext, 30.0F);
    paramString2.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    paramString1.addView(paramString2);
    localObject2 = new Dialog(paramContext);
    ((Dialog)localObject2).requestWindowFeature(1);
    ((Dialog)localObject2).setCancelable(false);
    ((Dialog)localObject2).addContentView(paramString1, new ViewGroup.LayoutParams(ScreenUtil.dip2px(paramContext, 305.0F), ScreenUtil.dip2px(paramContext, 500.0F)));
    ((Dialog)localObject2).getWindow().setBackgroundDrawableResource(17170445);
    paramString1 = ((WebView)localObject1).getSettings();
    paramString1.setJavaScriptEnabled(true);
    paramString1.setDisplayZoomControls(false);
    paramString1.setSupportZoom(true);
    ((WebView)localObject1).setWebViewClient(new WebViewClient()
    {
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (paramAnonymousString.equals("smartgoapp://xiaopiao"))
        {
          paramAnonymousWebView = new Intent();
          paramAnonymousWebView.setFlags(268435456);
          paramAnonymousWebView.setClassName(BeaconDialog.this, "com.ismartgo.app.activity.UploadReceiptActivity");
          BeaconDialog.this.startActivity(paramAnonymousWebView);
          this.val$dialog.dismiss();
          return true;
        }
        if (paramAnonymousString.equals("smartgoapp://dou"))
        {
          Toast.makeText(BeaconDialog.this, "跳转到赚精明豆主页面", 0).show();
          return true;
        }
        if (paramAnonymousString.equals("smartgoapp://msgdetail/298"))
        {
          Toast.makeText(BeaconDialog.this, "打开促销信息详情页并且显示ID为298的促销信息", 0).show();
          return true;
        }
        if (paramAnonymousString.equals("smartgoapp://saomiao"))
        {
          paramAnonymousWebView = new Intent();
          paramAnonymousWebView.setFlags(268435456);
          paramAnonymousWebView.setClassName(BeaconDialog.this, "com.ismartgo.app.activity.ScanStoreGoodsActivity");
          BeaconDialog.this.startActivity(paramAnonymousWebView);
          this.val$dialog.dismiss();
          return true;
        }
        return super.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString);
      }
    });
    paramString2.setOnDelClickListener(new DeleteView.OnDelClickListener()
    {
      public void onDeleteListener(View paramAnonymousView)
      {
        System.out.println("关闭dialog");
        BeaconDialog.this.dismiss();
      }
    });
    ((Dialog)localObject2).show();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.view.BeaconDialog
 * JD-Core Version:    0.6.2
 */