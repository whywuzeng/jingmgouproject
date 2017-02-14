package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.GoodsDetail;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.impl.WebDownloadListener;
import com.ismartgo.app.grid.impl.WebDownloadListener.DownLoadCompleteReceiver;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.share.ShareDialog;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.tsz.afinal.FinalHttp;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class MerChandiseDetailActivity extends BaseActivity
{
  private int LOGIN_RESULT_CODE = 20002;
  private int REQUEST_LOGIN = 20001;
  private String TAG = "MerChandiseDetailActivity";
  private GoodsDetail detail;
  private WebDownloadListener downloadListener;
  private Goods goods;

  @AbIocView(id=2131230955)
  private WebView goods_detail;

  @AbIocView(id=2131230957)
  private ImageView iv_logo;
  private LinearLayout llTitle;

  @AbIocView(click="merchandiseClick", id=2131230943)
  private LinearLayout lyt_collect;

  @AbIocView(click="merchandiseClick", id=2131230946)
  private LinearLayout lyt_scan;

  @AbIocView(click="merchandiseClick", id=2131230949)
  private LinearLayout lyt_share;
  MyDialog mDialog;

  @AbIocView(id=2131230945)
  private ImageView mer_collect;
  private ImageView mer_scan;

  @AbIocView(click="merchandiseClick", id=2131231000)
  private TextView pv_back;

  @AbIocView(id=2131231002)
  private ImageView pv_screening;
  private ShareDialog share;
  private Store store;
  private ToastDefine toast;

  @AbIocView(id=2131230960)
  private TextView tv_goods_detail;

  @AbIocView(id=2131230958)
  private TextView tv_goods_name;

  @AbIocView(id=2131230959)
  private TextView tv_goods_price;

  @AbIocView(id=2131230937)
  private TextView tv_shop_address;

  @AbIocView(id=2131230961)
  private TextView tv_shop_name;
  int type;

  private void getGoodsDetail()
  {
    if ((this.goods != null) && (this.goods.getH5Url() != null))
    {
      Log.i(this.TAG, this.goods.getH5Url());
      this.goods_detail.getSettings().setJavaScriptEnabled(true);
      this.goods_detail.loadUrl(addUserIdUrl(this.goods.getH5Url()));
      this.downloadListener = new WebDownloadListener(this);
      this.goods_detail.setDownloadListener(this.downloadListener);
      this.goods_detail.addJavascriptInterface(new JavaScriptInterface(), "Receipt");
      Log.i("Test", "礼品详情Url: " + addUserIdUrl(this.goods.getH5Url()));
      this.goods_detail.setWebViewClient(new WebViewClient()
      {
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          paramAnonymousWebView.loadUrl(paramAnonymousString);
          return false;
        }
      });
    }
    while (true)
    {
      HttpRequest.getInstance().clickCount(this, String.valueOf(this.goods.getGoodsId()));
      try
      {
        MobclickAgent.onEvent(this, "msgdetail");
        return;
        this.goods_detail.setVisibility(8);
        findViewById(2131230956).setVisibility(0);
        ViewGroup.LayoutParams localLayoutParams = this.iv_logo.getLayoutParams();
        localLayoutParams.height = (this.goods.getHeight() * 2);
        this.iv_logo.setLayoutParams(localLayoutParams);
        ImgLoader.getInstance(this).showPic(StringUtils.getImgUrl(this.goods.getGoodsLogo()), this.iv_logo, false);
        this.tv_goods_name.setText(this.goods.getGoodsName());
        if (this.detail.getPromotion().equals(""))
          this.tv_goods_detail.setVisibility(8);
        while (true)
        {
          if (this.goods.getGoodsPrice() > 0.0D)
          {
            this.tv_goods_price.setVisibility(0);
            this.tv_goods_price.setText("￥" + this.goods.getGoodsPrice());
          }
          this.tv_shop_name.setText(this.store.getShopName());
          this.tv_shop_address.setText(this.store.getShopAddress());
          break;
          this.tv_goods_detail.setText(this.detail.getPromotion());
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  private void initView()
  {
    this.pv_screening.setVisibility(4);
    initTitleBar(0);
    this.tv_title.setText("商品详情");
    this.tv_title.setTextColor(getResources().getColor(2131099660));
    this.llTitle = ((LinearLayout)findViewById(2131230952));
    this.llTitle.setBackgroundColor(Color.parseColor("#ffffff"));
    Drawable localDrawable = getResources().getDrawable(2130837803);
    localDrawable.setBounds(0, 0, localDrawable.getMinimumWidth(), localDrawable.getMinimumHeight());
    this.pv_back.setCompoundDrawables(localDrawable, null, null, null);
    this.mer_scan = ((ImageView)findViewById(2131230948));
    if (this.goods.isCollect())
      this.mer_collect.setImageResource(2130837620);
    while (true)
    {
      if (this.goods.getGoodsScan() != 1)
        this.lyt_scan.setVisibility(8);
      this.type = getIntent().getIntExtra("goodsType", 1);
      return;
      this.mer_collect.setImageResource(2130837619);
    }
  }

  public void clickCollect(final Goods paramGoods)
  {
    if ((loginUser == null) || (loginUser.getLoginType() == 6))
    {
      startActivityForResult(new Intent(this, LoginActivity.class), this.REQUEST_LOGIN);
      return;
    }
    this.mDialog.show();
    String str2 = loginUser.getId();
    final boolean bool = paramGoods.isCollect();
    new FinalHttp();
    int i;
    if (this.store != null)
    {
      i = this.store.getShopId();
      if (!bool)
        break label197;
    }
    label197: for (String str1 = Url.DEL_COLLECT_URL; ; str1 = Url.COLLECT_GOODS_URL)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("uid", str2);
      localHashMap.put("goodsid", paramGoods.getGoodsId());
      localHashMap.put("shopid", i);
      HttpRequest.getInstance().executePostStringRequest(this, str1, HttpWhat.COLLECT_GOODS, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          MerChandiseDetailActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
          MerChandiseDetailActivity.this.toast.show();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
          PromotionActivity.clickCollectGoodsId = paramGoods.getGoodsId();
          SearchStorePromotionActivity.clickCollectGoodsId = paramGoods.getGoodsId();
          MyCollectActivity.refreshGoodsId = paramGoods.getGoodsId();
          int i;
          Goods localGoods;
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
            {
              HomeActivity.isChanged = true;
              PromotionActivity.isChangeCollect_waterfull = true;
              PromotionActivity.isNeedRefresh = true;
              SearchStorePromotionActivity.isChangeCollect_waterfull = true;
              SearchStorePromotionActivity.isNeedRefresh = true;
              SearchForPromotionActivity.isChangeCollected = true;
              MyCollectActivity.needRefresh = true;
              HomeActivity.isNeedRefresh = true;
              if (HomeActivity.isNeedRefreshGoodsIdList == null)
                HomeActivity.isNeedRefreshGoodsIdList = new ArrayList();
              paramAnonymousResponse = new HashMap();
              paramAnonymousResponse.put("store", Integer.valueOf(MerChandiseDetailActivity.this.store.getShopId()));
              paramAnonymousResponse.put("goods", Integer.valueOf(paramGoods.getGoodsId()));
              MerChandiseDetailActivity.this.mDialog.dismiss();
              MerChandiseDetailActivity.this.toast.setDisplay(true);
              MerChandiseDetailActivity.this.toast.setLayoutParams(150, 50);
              if (bool)
              {
                MerChandiseDetailActivity.this.toast.setImageResource(false);
                MerChandiseDetailActivity.this.toast.setMessage("已取消");
                paramGoods.setCollect(false);
                MerChandiseDetailActivity.this.mer_collect.setImageResource(2130837619);
                PromotionActivity.isCollectionState = 2;
                SearchStorePromotionActivity.isCollectionState = 2;
                MyCollectActivity.collectionState = 2;
                paramAnonymousResponse.put("isCollected", Integer.valueOf(2));
              }
              while (true)
              {
                HomeActivity.isNeedRefreshGoodsIdList.add(paramAnonymousResponse);
                System.out.println("position: " + MerChandiseDetailActivity.this.getIntent().getIntExtra("position", -1) + "  i: " + MerChandiseDetailActivity.this.getIntent().getIntExtra("i", -1));
                if ((MerChandiseDetailActivity.this.getIntent().getIntExtra("position", -1) > -1) && (MerChandiseDetailActivity.this.getIntent().getIntExtra("i", -1) > -1))
                {
                  paramAnonymousInt = MerChandiseDetailActivity.this.getIntent().getIntExtra("position", -1);
                  i = ((Goods)((Store)PromotionActivity.store_list.get(paramAnonymousInt)).getGoods_list().get(MerChandiseDetailActivity.this.getIntent().getIntExtra("i", -1))).getGoodsTag();
                  paramAnonymousResponse = (Goods)((Store)PromotionActivity.store_list.get(MerChandiseDetailActivity.this.getIntent().getIntExtra("position", -1))).getGoods_list().get(i);
                  if (!bool)
                    break;
                  bool = false;
                  paramAnonymousResponse.setCollect(bool);
                  PromotionActivity.isChangeCollected = true;
                  System.out.println("执行更新操作： " + paramAnonymousInt + " : " + i);
                }
                MerChandiseDetailActivity.this.mDialog.dismiss();
                MerChandiseDetailActivity.this.toast.show();
                ScanInStoreGoodsActivity.isScan = true;
                return;
                MerChandiseDetailActivity.this.toast.setImageResource(true);
                MerChandiseDetailActivity.this.toast.setMessage("已收藏");
                paramGoods.setCollect(true);
                MerChandiseDetailActivity.this.mer_collect.setImageResource(2130837620);
                PromotionActivity.isCollectionState = 1;
                SearchStorePromotionActivity.isCollectionState = 1;
                MyCollectActivity.collectionState = 1;
                paramAnonymousResponse.put("isCollected", Integer.valueOf(1));
                try
                {
                  MobclickAgent.onEvent(MerChandiseDetailActivity.this.getApplicationContext(), "collect");
                }
                catch (Exception localException)
                {
                  localException.printStackTrace();
                }
              }
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            paramAnonymousInt = MerChandiseDetailActivity.this.getIntent().getIntExtra("position", -1);
            i = ((Goods)((Store)SearchStorePromotionActivity.store_list.get(paramAnonymousInt)).getGoods_list().get(MerChandiseDetailActivity.this.getIntent().getIntExtra("i", -1))).getGoodsTag();
            localGoods = (Goods)((Store)SearchStorePromotionActivity.store_list.get(MerChandiseDetailActivity.this.getIntent().getIntExtra("position", -1))).getGoods_list().get(i);
            if (!bool);
          }
          for (boolean bool = false; ; bool = true)
          {
            localGoods.setCollect(bool);
            SearchStorePromotionActivity.isChangeCollected = true;
            System.out.println("执行更新操作： " + paramAnonymousInt + " : " + i);
            MerChandiseDetailActivity.this.mDialog.dismiss();
            paramAnonymousResponse.printStackTrace();
            return;
            bool = true;
            break;
            MerChandiseDetailActivity.this.mDialog.dismiss();
            MerChandiseDetailActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
            MerChandiseDetailActivity.this.toast.show();
            return;
          }
        }
      });
      return;
      i = paramGoods.getShopId();
      break;
    }
  }

  public void merchandiseClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231000:
      finish();
      return;
    case 2131230943:
      clickCollect(this.goods);
      return;
    case 2131230946:
      paramView = AndroidApplication.getInstance().getCurrentLocation();
      if (paramView != null)
      {
        if ((this.store != null) && (this.store.getShopId() == paramView.getScanShopId()))
        {
          paramView = new Intent();
          paramView.setClass(this, CaptureActivity.class);
          startActivity(paramView);
          return;
        }
        this.toast.setMessage("亲，您当前不在店内，无法扫描");
        this.toast.show();
        return;
      }
      this.toast.setMessage("亲，您当前不在店内，无法扫描");
      this.toast.show();
      return;
    case 2131230949:
    }
    this.share = new ShareDialog(this);
    if ((this.store != null) && (!TextUtils.isEmpty(this.store.getReatilName())))
    {
      this.share.setShare(this.goods.getGoodsLogo(), "【" + this.store.getReatilName() + "】 " + this.goods.getGoodsName(), this.goods.getGoodsDescribe(), this.goods.getGoodsId(), AndroidApplication.getInstance().getUser().getId(), this.type);
      this.share.show();
      return;
    }
    this.share.setShare(this.goods.getGoodsLogo(), this.goods.getGoodsName(), this.goods.getGoodsDescribe(), this.goods.getGoodsId(), AndroidApplication.getInstance().getUser().getId(), this.type);
    this.share.show();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == this.REQUEST_LOGIN) && (paramInt2 == this.LOGIN_RESULT_CODE))
      clickCollect(this.goods);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903072);
    this.store = ((Store)getIntent().getSerializableExtra("store"));
    this.goods = ((Goods)getIntent().getSerializableExtra("goods"));
    this.mDialog = new MyDialog(this);
    this.toast = new ToastDefine(this);
    initView();
    getGoodsDetail();
  }

  protected void onDestroy()
  {
    if (this.downloadListener != null)
    {
      WebDownloadListener.DownLoadCompleteReceiver localDownLoadCompleteReceiver = this.downloadListener.getReceiver();
      if (localDownLoadCompleteReceiver != null)
        unregisterReceiver(localDownLoadCompleteReceiver);
    }
    super.onDestroy();
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

  final class JavaScriptInterface
  {
    JavaScriptInterface()
    {
    }

    @JavascriptInterface
    public void clickOnClient(String paramString)
    {
      if ((!CommonMethod.isEmpty(paramString)) && (paramString.startsWith("smartgoapp")))
      {
        if (!paramString.equals("smartgoapp://receipt/take"))
          break label48;
        paramString = new Intent(MerChandiseDetailActivity.this, CameraActivity1.class);
        MerChandiseDetailActivity.this.startActivity(paramString);
      }
      label48: 
      do
      {
        return;
        if (paramString.equals("smartgoapp://receipt/list"))
        {
          paramString = new Intent(MerChandiseDetailActivity.this, MyReceiptListActivity.class);
          MerChandiseDetailActivity.this.startActivity(paramString);
          return;
        }
      }
      while (!paramString.equals("smartgoapp://giftlist"));
      paramString = new Intent(MerChandiseDetailActivity.this, RewardExchangeActivity.class);
      MerChandiseDetailActivity.this.startActivity(paramString);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MerChandiseDetailActivity
 * JD-Core Version:    0.6.2
 */