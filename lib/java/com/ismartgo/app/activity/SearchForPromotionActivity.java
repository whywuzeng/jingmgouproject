package com.ismartgo.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.dodowaterfall.widget.ScaleImageView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.DistanceConversionUtils;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.item.Promotion_Select_menu;
import com.ismartgo.app.map.BaiduMapActivity;
import com.ismartgo.app.ownself.view.ExpandTabView;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.service.IbeaconService;
import com.ismartgo.app.share.ShareDialog;
import com.ismartgo.app.tools.ImageUrlLoader;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import me.maxwin.view.XListViewRefresh;
import me.maxwin.view.XListViewRefresh.IXListViewListener;

public class SearchForPromotionActivity extends BaseActivity
  implements View.OnClickListener, TextView.OnEditorActionListener, XListViewRefresh.IXListViewListener
{
  public static boolean isChangeCollected;
  private final int REQUEST_CODE = 10001;
  private final int RESULT_CODE = 10002;
  private String TAG = "SearchForPromotionActivity";
  public String brandIds = "";
  private String brandValues = "";
  public int categoryId = -1;
  int collectPosition = -1;
  public int distance = -1;
  public int districtId = -1;
  ExpandTabView expandTabView;
  int flag = 1;
  boolean goodsCollectState;
  int imageWidth;
  private ImgLoader imgLoader;
  private boolean isLoadingMore;
  private String key;
  private StaggeredAdapter mAdapter = null;
  private XListViewRefresh mAdapterView = null;
  private MyDialog mDialog;
  public int page = 1;
  private final int pageSize = 30;
  private int pages = 1;
  private ImageView pv_back;
  private TextView pv_input;
  private ImageView pv_screening;
  private View pv_search;
  public int retailId = -1;
  private View search_empty;
  private TextView search_tips;
  public int shopTypeId = -1;
  private ToastDefine toastDefine;
  private Promotion_Select_menu top_menu;
  public int townId = -1;

  private void AddItemToContainer(int paramInt1, final int paramInt2)
  {
    Log.i(this.TAG, "isLoadingMore: " + this.isLoadingMore);
    if (!this.isLoadingMore)
      this.mDialog.show();
    String str2 = "";
    String str1;
    HashMap localHashMap;
    if ((IbeaconService.districtId < -1) && (IbeaconService.townId == 1000))
    {
      str1 = "1000";
      localHashMap = new HashMap();
      if (BaseActivity.loginUser != null)
        break label402;
    }
    label402: for (str2 = ""; ; str2 = BaseActivity.loginUser.getId())
    {
      localHashMap.put("uid=", str2);
      localHashMap.put("cityName", BaseActivity.city);
      localHashMap.put("districtId", IbeaconService.districtId);
      localHashMap.put("townId", IbeaconService.townId);
      localHashMap.put("shopTypeId", IbeaconService.shopTypeId);
      localHashMap.put("retailId", IbeaconService.retailId);
      localHashMap.put("distance", str1);
      localHashMap.put("searchname", this.key);
      localHashMap.put("categoryId", IbeaconService.category1Id);
      localHashMap.put("categoryId2", IbeaconService.category2Id);
      localHashMap.put("brandIds", IbeaconService.brandValues);
      localHashMap.put("lon", BaseActivity.log);
      localHashMap.put("lat", BaseActivity.lat);
      localHashMap.put("pages", paramInt1);
      localHashMap.put("pageSize", "30");
      HttpRequest.getInstance().executePostStringRequest(this, Url.SELECT_GOODS_URL, 22, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          paramAnonymousResponse = HttpJsonParse.getSearchForPromotionData(((String)paramAnonymousResponse.get()).toString());
          SearchForPromotionActivity.this.mDialog.dismiss();
          if (paramInt2 == 1)
          {
            SearchForPromotionActivity.this.mAdapter.clearData();
            if (paramAnonymousResponse.size() > 0)
            {
              SearchForPromotionActivity.this.mAdapterView.setVisibility(0);
              SearchForPromotionActivity.this.search_empty.setVisibility(8);
              SearchForPromotionActivity.this.mAdapter.addItemLast(paramAnonymousResponse);
              SearchForPromotionActivity.this.mAdapter.notifyDataSetChanged();
              SearchForPromotionActivity.this.mAdapterView.stopRefresh();
            }
          }
          while (true)
          {
            if (paramAnonymousResponse.size() >= 30)
              break label242;
            SearchForPromotionActivity.this.mAdapterView.setFootHide();
            return;
            SearchForPromotionActivity.this.mAdapterView.setVisibility(8);
            SearchForPromotionActivity.this.search_empty.setVisibility(0);
            break;
            if (paramInt2 == 2)
            {
              SearchForPromotionActivity.this.mAdapterView.stopLoadMore();
              if (paramAnonymousResponse.size() > 0)
              {
                SearchForPromotionActivity.this.mAdapter.addItemLast(paramAnonymousResponse);
                SearchForPromotionActivity.this.mAdapter.notifyDataSetChanged();
              }
              else
              {
                SearchForPromotionActivity.this.toastDefine.setMessage("― 没有更多了 ―");
                SearchForPromotionActivity.this.toastDefine.setDisplay(false);
                SearchForPromotionActivity.this.toastDefine.show();
              }
            }
          }
          label242: SearchForPromotionActivity.this.mAdapterView.setFootVisible();
        }
      });
      if (paramInt2 == 1)
        this.mAdapterView.setFootHide();
      return;
      str1 = str2;
      if (IbeaconService.districtId >= -1)
        break;
      str1 = str2;
      if (IbeaconService.townId != 2000)
        break;
      str1 = "2000";
      break;
    }
  }

  private String getStr(int paramInt)
  {
    if (paramInt < 1)
      return "";
    return paramInt;
  }

  private void initData()
  {
    IbeaconService.brandIds = "";
    IbeaconService.brandValues = "";
    IbeaconService.category1Id = "-1";
    IbeaconService.category2Id = "-1";
    IbeaconService.districtId = -2;
    IbeaconService.townId = -2;
    IbeaconService.shopTypeId = -1;
    IbeaconService.retailId = -1;
  }

  private void initView()
  {
    this.pv_input = ((TextView)findViewById(2131231335));
    if ((this.key != null) && (!this.key.equals("")))
      this.pv_input.setText(this.key);
    initTitleBar();
    this.toastDefine = new ToastDefine(getApplicationContext());
    this.imageWidth = (getWindowManager().getDefaultDisplay().getWidth() / 2);
    this.mAdapterView = ((XListViewRefresh)findViewById(2131230854));
    this.mAdapterView.setPullLoadEnable(true);
    this.mAdapterView.setXListViewListener(this);
    this.mAdapter = new StaggeredAdapter(this, this.mAdapterView);
    this.mAdapterView.setAdapter(this.mAdapter);
    this.search_empty = findViewById(2131231005);
    this.search_tips = ((TextView)findViewById(2131231006));
    this.pv_back = ((ImageView)findViewById(2131231000));
    this.pv_back.setOnClickListener(this);
    this.pv_screening = ((ImageView)findViewById(2131231002));
    this.pv_screening.setOnClickListener(this);
    this.pv_search = findViewById(2131231334);
    this.pv_search.setOnClickListener(this);
    this.expandTabView = ((ExpandTabView)findViewById(2131231004));
    this.top_menu = new Promotion_Select_menu(this, this.expandTabView, this.flag, 2);
    this.top_menu.setItem();
  }

  private void setScreen(String paramString1, String paramString2)
  {
    if (paramString1.equals(""))
      this.pv_screening.setImageResource(2130837844);
    while (true)
    {
      this.brandIds = paramString1;
      this.brandValues = paramString2;
      loadGoods();
      return;
      this.pv_screening.setImageResource(2130837845);
    }
  }

  public void addView()
  {
    this.pages = 1;
    AddItemToContainer(this.pages, 1);
  }

  public void clickCollect(final int paramInt, final ImageView paramImageView)
  {
    final Goods localGoods = (Goods)((Store)this.mAdapter.mInfos.get(paramInt)).getGoods_list().get(0);
    User localUser = BaseActivity.loginUser;
    if ((localUser == null) || (localUser.getLoginType() == 6))
    {
      paramImageView = new Intent(this, LoginActivity.class);
      paramImageView.putExtra("goodsid", localGoods.getGoodsId());
      paramImageView.putExtra("shopid", ((Store)this.mAdapter.mInfos.get(paramInt)).getShopId());
      startActivity(paramImageView);
      return;
    }
    final boolean bool = localGoods.isCollect();
    if (bool);
    for (String str = Url.DEL_COLLECT_URL; ; str = Url.COLLECT_GOODS_URL)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("uid", localUser.getId());
      localHashMap.put("goodsid", localGoods.getGoodsId());
      localHashMap.put("shopid", ((Store)this.mAdapter.mInfos.get(paramInt)).getShopId());
      HttpRequest.getInstance().executePostStringRequest(this, str, HttpWhat.COLLECT_GOODS, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          SearchForPromotionActivity.this.toastDefine.setDisplay(false);
          SearchForPromotionActivity.this.toastDefine.setMessage("亲，网络好像出问题了~");
          SearchForPromotionActivity.this.toastDefine.show();
        }

        // ERROR //
        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          // Byte code:
          //   0: getstatic 69	java/lang/System:out	Ljava/io/PrintStream;
          //   3: new 71	java/lang/StringBuilder
          //   6: dup
          //   7: ldc 73
          //   9: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   12: aload_2
          //   13: invokeinterface 81 1 0
          //   18: checkcast 83	java/lang/String
          //   21: invokevirtual 87	java/lang/String:toString	()Ljava/lang/String;
          //   24: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   27: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   30: invokevirtual 97	java/io/PrintStream:println	(Ljava/lang/String;)V
          //   33: new 99	org/json/JSONObject
          //   36: dup
          //   37: aload_2
          //   38: invokeinterface 81 1 0
          //   43: checkcast 83	java/lang/String
          //   46: invokevirtual 87	java/lang/String:toString	()Ljava/lang/String;
          //   49: invokespecial 100	org/json/JSONObject:<init>	(Ljava/lang/String;)V
          //   52: astore_2
          //   53: aload_2
          //   54: ldc 102
          //   56: invokevirtual 106	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   59: invokestatic 112	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
          //   62: invokevirtual 116	java/lang/Integer:intValue	()I
          //   65: getstatic 121	com/ismartgo/app/url/ResultCode:RESULT_OK	I
          //   68: if_icmpne +340 -> 408
          //   71: iconst_1
          //   72: putstatic 126	com/ismartgo/app/activity/HomeActivity:isChanged	Z
          //   75: iconst_1
          //   76: putstatic 129	com/ismartgo/app/activity/HomeActivity:isNeedRefresh	Z
          //   79: getstatic 133	com/ismartgo/app/activity/HomeActivity:isNeedRefreshGoodsIdList	Ljava/util/List;
          //   82: ifnonnull +13 -> 95
          //   85: new 135	java/util/ArrayList
          //   88: dup
          //   89: invokespecial 136	java/util/ArrayList:<init>	()V
          //   92: putstatic 133	com/ismartgo/app/activity/HomeActivity:isNeedRefreshGoodsIdList	Ljava/util/List;
          //   95: new 138	java/util/HashMap
          //   98: dup
          //   99: invokespecial 139	java/util/HashMap:<init>	()V
          //   102: astore_2
          //   103: aload_2
          //   104: ldc 141
          //   106: aload_0
          //   107: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   110: invokestatic 145	com/ismartgo/app/activity/SearchForPromotionActivity:access$0	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/activity/SearchForPromotionActivity$StaggeredAdapter;
          //   113: getfield 151	com/ismartgo/app/activity/SearchForPromotionActivity$StaggeredAdapter:mInfos	Ljava/util/LinkedList;
          //   116: aload_0
          //   117: getfield 28	com/ismartgo/app/activity/SearchForPromotionActivity$2:val$position	I
          //   120: invokevirtual 156	java/util/LinkedList:get	(I)Ljava/lang/Object;
          //   123: checkcast 158	com/ismartgo/app/bean/Store
          //   126: invokevirtual 161	com/ismartgo/app/bean/Store:getShopId	()I
          //   129: invokestatic 164	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   132: invokevirtual 168	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
          //   135: pop
          //   136: aload_2
          //   137: ldc 170
          //   139: aload_0
          //   140: getfield 30	com/ismartgo/app/activity/SearchForPromotionActivity$2:val$goods	Lcom/ismartgo/app/bean/Goods;
          //   143: invokevirtual 175	com/ismartgo/app/bean/Goods:getGoodsId	()I
          //   146: invokestatic 164	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   149: invokevirtual 168	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
          //   152: pop
          //   153: aload_0
          //   154: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   157: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   160: sipush 150
          //   163: bipush 50
          //   165: invokevirtual 179	com/ismartgo/app/ownself/view/ToastDefine:setLayoutParams	(II)V
          //   168: aload_0
          //   169: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   172: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   175: iconst_1
          //   176: invokevirtual 50	com/ismartgo/app/ownself/view/ToastDefine:setDisplay	(Z)V
          //   179: aload_0
          //   180: getfield 32	com/ismartgo/app/activity/SearchForPromotionActivity$2:val$isCollect	Z
          //   183: ifeq +123 -> 306
          //   186: aload_0
          //   187: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   190: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   193: iconst_0
          //   194: invokevirtual 182	com/ismartgo/app/ownself/view/ToastDefine:setImageResource	(Z)V
          //   197: aload_0
          //   198: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   201: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   204: ldc 184
          //   206: invokevirtual 56	com/ismartgo/app/ownself/view/ToastDefine:setMessage	(Ljava/lang/String;)V
          //   209: aload_0
          //   210: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   213: invokestatic 145	com/ismartgo/app/activity/SearchForPromotionActivity:access$0	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/activity/SearchForPromotionActivity$StaggeredAdapter;
          //   216: getfield 151	com/ismartgo/app/activity/SearchForPromotionActivity$StaggeredAdapter:mInfos	Ljava/util/LinkedList;
          //   219: aload_0
          //   220: getfield 28	com/ismartgo/app/activity/SearchForPromotionActivity$2:val$position	I
          //   223: invokevirtual 156	java/util/LinkedList:get	(I)Ljava/lang/Object;
          //   226: checkcast 158	com/ismartgo/app/bean/Store
          //   229: invokevirtual 188	com/ismartgo/app/bean/Store:getGoods_list	()Ljava/util/List;
          //   232: iconst_0
          //   233: invokeinterface 191 2 0
          //   238: checkcast 172	com/ismartgo/app/bean/Goods
          //   241: iconst_0
          //   242: invokevirtual 194	com/ismartgo/app/bean/Goods:setCollect	(Z)V
          //   245: aload_0
          //   246: getfield 34	com/ismartgo/app/activity/SearchForPromotionActivity$2:val$img	Landroid/widget/ImageView;
          //   249: ldc 195
          //   251: invokevirtual 200	android/widget/ImageView:setImageResource	(I)V
          //   254: aload_0
          //   255: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   258: iconst_0
          //   259: putfield 203	com/ismartgo/app/activity/SearchForPromotionActivity:goodsCollectState	Z
          //   262: aload_2
          //   263: ldc 205
          //   265: iconst_2
          //   266: invokestatic 164	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   269: invokevirtual 168	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
          //   272: pop
          //   273: aload_0
          //   274: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   277: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   280: invokevirtual 59	com/ismartgo/app/ownself/view/ToastDefine:show	()V
          //   283: getstatic 133	com/ismartgo/app/activity/HomeActivity:isNeedRefreshGoodsIdList	Ljava/util/List;
          //   286: aload_2
          //   287: invokeinterface 209 2 0
          //   292: pop
          //   293: aload_0
          //   294: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   297: invokevirtual 213	com/ismartgo/app/activity/SearchForPromotionActivity:getApplicationContext	()Landroid/content/Context;
          //   300: ldc 215
          //   302: invokestatic 221	com/umeng/analytics/MobclickAgent:onEvent	(Landroid/content/Context;Ljava/lang/String;)V
          //   305: return
          //   306: aload_0
          //   307: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   310: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   313: iconst_1
          //   314: invokevirtual 182	com/ismartgo/app/ownself/view/ToastDefine:setImageResource	(Z)V
          //   317: aload_0
          //   318: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   321: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   324: ldc 223
          //   326: invokevirtual 56	com/ismartgo/app/ownself/view/ToastDefine:setMessage	(Ljava/lang/String;)V
          //   329: aload_0
          //   330: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   333: invokestatic 145	com/ismartgo/app/activity/SearchForPromotionActivity:access$0	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/activity/SearchForPromotionActivity$StaggeredAdapter;
          //   336: getfield 151	com/ismartgo/app/activity/SearchForPromotionActivity$StaggeredAdapter:mInfos	Ljava/util/LinkedList;
          //   339: aload_0
          //   340: getfield 28	com/ismartgo/app/activity/SearchForPromotionActivity$2:val$position	I
          //   343: invokevirtual 156	java/util/LinkedList:get	(I)Ljava/lang/Object;
          //   346: checkcast 158	com/ismartgo/app/bean/Store
          //   349: invokevirtual 188	com/ismartgo/app/bean/Store:getGoods_list	()Ljava/util/List;
          //   352: iconst_0
          //   353: invokeinterface 191 2 0
          //   358: checkcast 172	com/ismartgo/app/bean/Goods
          //   361: iconst_1
          //   362: invokevirtual 194	com/ismartgo/app/bean/Goods:setCollect	(Z)V
          //   365: aload_0
          //   366: getfield 34	com/ismartgo/app/activity/SearchForPromotionActivity$2:val$img	Landroid/widget/ImageView;
          //   369: ldc 224
          //   371: invokevirtual 200	android/widget/ImageView:setImageResource	(I)V
          //   374: aload_0
          //   375: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   378: iconst_1
          //   379: putfield 203	com/ismartgo/app/activity/SearchForPromotionActivity:goodsCollectState	Z
          //   382: aload_2
          //   383: ldc 205
          //   385: iconst_1
          //   386: invokestatic 164	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   389: invokevirtual 168	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
          //   392: pop
          //   393: goto -120 -> 273
          //   396: astore_2
          //   397: aload_2
          //   398: invokevirtual 227	java/lang/Exception:printStackTrace	()V
          //   401: return
          //   402: astore_2
          //   403: aload_2
          //   404: invokevirtual 227	java/lang/Exception:printStackTrace	()V
          //   407: return
          //   408: aload_0
          //   409: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   412: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   415: aload_2
          //   416: ldc 229
          //   418: invokevirtual 106	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
          //   421: invokevirtual 56	com/ismartgo/app/ownself/view/ToastDefine:setMessage	(Ljava/lang/String;)V
          //   424: aload_0
          //   425: getfield 26	com/ismartgo/app/activity/SearchForPromotionActivity$2:this$0	Lcom/ismartgo/app/activity/SearchForPromotionActivity;
          //   428: invokestatic 44	com/ismartgo/app/activity/SearchForPromotionActivity:access$4	(Lcom/ismartgo/app/activity/SearchForPromotionActivity;)Lcom/ismartgo/app/ownself/view/ToastDefine;
          //   431: invokevirtual 59	com/ismartgo/app/ownself/view/ToastDefine:show	()V
          //   434: return
          //
          // Exception table:
          //   from	to	target	type
          //   33	95	396	java/lang/Exception
          //   95	273	396	java/lang/Exception
          //   273	293	396	java/lang/Exception
          //   306	393	396	java/lang/Exception
          //   403	407	396	java/lang/Exception
          //   408	434	396	java/lang/Exception
          //   293	305	402	java/lang/Exception
        }
      });
      return;
    }
  }

  public void loadGoods()
  {
    addView();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 10001) && (paramInt2 == 10002))
    {
      this.expandTabView.hidePopup();
      String str = paramIntent.getStringExtra("brandIds");
      paramIntent = paramIntent.getStringExtra("brandValues");
      if ((str != null) && (!str.equals(this.brandIds)))
        setScreen(str, paramIntent);
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131231000:
    case 2131231002:
    case 2131231334:
    }
    do
    {
      return;
      if (this.top_menu != null)
        this.top_menu.unRegisterReceiver(this);
      finish();
      return;
      if ((IbeaconService.category1Id.trim().equals("")) || (Integer.valueOf(IbeaconService.category1Id).intValue() < 0))
      {
        this.toastDefine.setMessage("请先选择品类！");
        this.toastDefine.show();
        return;
      }
      paramView = new Intent();
      paramView.setClass(this, ScreenBrandActivity.class);
      paramView.putExtra("categoryId", IbeaconService.category1Id);
      if (!this.brandIds.equals(""))
        paramView.putExtra("brandIds", this.brandIds);
      startActivityForResult(paramView, 10001);
      return;
      paramView = new Intent();
      paramView.setClass(this, SearchActivity.class);
      if (!this.pv_input.getText().toString().equals("搜索商店或商品"))
        paramView.putExtra("goods", this.key);
      startActivity(paramView);
    }
    while (this.pv_input.getText().toString().equals("搜索商店或商品"));
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903081);
    this.imgLoader = ImgLoader.getInstance(this);
    this.key = getIntent().getStringExtra("goods");
    this.mDialog = new MyDialog(this);
    initData();
    initView();
    loadGoods();
    Log.i(this.TAG, getClass().getName());
  }

  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 0) && (this.top_menu != null))
      this.top_menu.unRegisterReceiver(this);
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onLoadMore()
  {
    this.isLoadingMore = true;
    this.pages += 1;
    AddItemToContainer(this.pages, 2);
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  public void onRefresh()
  {
    this.mAdapterView.stopRefresh();
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
    boolean bool;
    if (isChangeCollected)
    {
      isChangeCollected = false;
      if (this.collectPosition > -1)
      {
        if (!this.goodsCollectState)
          break label116;
        bool = false;
        this.goodsCollectState = bool;
        if (!((Goods)((Store)this.mAdapter.mInfos.get(this.collectPosition)).getGoods_list().get(0)).isCollect())
          break label121;
        ((Goods)((Store)this.mAdapter.mInfos.get(this.collectPosition)).getGoods_list().get(0)).setCollect(false);
      }
    }
    while (true)
    {
      this.mAdapter.notifyDataSetChanged();
      return;
      label116: bool = true;
      break;
      label121: ((Goods)((Store)this.mAdapter.mInfos.get(this.collectPosition)).getGoods_list().get(0)).setCollect(true);
    }
  }

  public void setScreenArea(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((this.distance != paramInt3) || (this.townId != paramInt2) || (this.districtId != -1))
    {
      this.distance = paramInt3;
      if (paramInt3 != -1)
        break label49;
      this.districtId = paramInt1;
    }
    for (this.townId = paramInt2; ; this.townId = -1)
    {
      loadGoods();
      return;
      label49: this.districtId = -1;
    }
  }

  public void setScreenStore(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 == 0)
      i = -1;
    paramInt1 = paramInt2;
    if (paramInt2 == 0)
      paramInt1 = -1;
    if ((this.shopTypeId != i) || (this.retailId != paramInt1))
    {
      this.shopTypeId = i;
      this.retailId = paramInt1;
      this.categoryId = -1;
      this.top_menu.onRefresh(this.top_menu.getViewMiddle_class(), "全部品类");
      loadGoods();
    }
  }

  public void setShopClazz(int paramInt)
  {
    this.categoryId = paramInt;
    setScreen("", "");
  }

  public class StaggeredAdapter extends BaseAdapter
  {
    private Store duitangInfo;
    private Context mContext;
    public LinkedList<Store> mInfos;
    private XListViewRefresh mListView;

    public StaggeredAdapter(Context paramXListViewRefresh, XListViewRefresh arg3)
    {
      this.mContext = paramXListViewRefresh;
      this.mInfos = new LinkedList();
      Object localObject;
      this.mListView = localObject;
    }

    public void addItemLast(List<Store> paramList)
    {
      this.mInfos.addAll(paramList);
    }

    public void clearData()
    {
      this.mInfos.clear();
    }

    public int getCount()
    {
      return this.mInfos.size();
    }

    public Object getItem(int paramInt)
    {
      return this.mInfos.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return 0L;
    }

    public LinkedList<Store> getStoreInfo()
    {
      return this.mInfos;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      this.duitangInfo = ((Store)this.mInfos.get(paramInt));
      Store localStore = this.duitangInfo;
      Goods localGoods = (Goods)localStore.getGoods_list().get(0);
      View localView = paramView;
      if (paramView == null)
      {
        localView = LayoutInflater.from(paramViewGroup.getContext()).inflate(2130903246, null);
        paramView = new ViewHolder();
        paramView.layout_collect_share = localView.findViewById(2131231485);
        paramView.store_name = ((TextView)localView.findViewById(2131231297));
        paramView.store_distance = ((TextView)localView.findViewById(2131231299));
        paramView.iv_location = ((ImageView)localView.findViewById(2131231484));
        paramView.store_logo = ((ImageView)localView.findViewById(2131231483));
        paramView.mer_scan = ((ImageView)localView.findViewById(2131230948));
        paramView.goods_frame = ((FrameLayout)localView.findViewById(2131231480));
        paramView.progressBar = localView.findViewById(2131231489);
        paramView.imageView = ((ScaleImageView)localView.findViewById(2131231134));
        paramView.store_info_layout = ((LinearLayout)localView.findViewById(2131231482));
        paramView.share_coll_layout = ((LinearLayout)localView.findViewById(2131231486));
        paramView.mer_time = ((TextView)localView.findViewById(2131231481));
        paramView.layout_collect_share = localView.findViewById(2131231485);
        paramView.layout_mer_share = localView.findViewById(2131231488);
        paramView.mer_share = ((ImageView)localView.findViewById(2131230951));
        paramView.mer_collect = ((ImageView)localView.findViewById(2131230945));
        paramView.layout_mer_collect = localView.findViewById(2131231487);
        localView.setTag(paramView);
      }
      ViewHolder localViewHolder = (ViewHolder)localView.getTag();
      localGoods.getGoodsLogo();
      localViewHolder.progressBar.setVisibility(8);
      int i = SearchForPromotionActivity.this.mAdapterView.getItemColumnWidth();
      paramView = localViewHolder.imageView.getLayoutParams();
      paramView.width = i;
      paramView.height = (Integer.valueOf(localGoods.getHeight()).intValue() * i / Integer.valueOf(localGoods.getWidth()).intValue());
      localViewHolder.imageView.setLayoutParams(paramView);
      paramViewGroup = localGoods.getGoodsLogo();
      paramView = paramViewGroup;
      if (paramViewGroup != null)
      {
        paramView = paramViewGroup;
        if (!paramViewGroup.equals(""))
        {
          paramView = paramViewGroup;
          if (paramViewGroup.contains("?"))
            paramView = localGoods.getGoodsLogo().substring(0, localGoods.getGoodsLogo().lastIndexOf("?"));
        }
      }
      paramView = paramView + "?width=" + i;
      ImgLoader.getInstance(SearchForPromotionActivity.this).showPic(paramView, localViewHolder.imageView, false);
      localViewHolder.goods_frame.setOnClickListener(new SearchForPromotionActivity.onClickListener(SearchForPromotionActivity.this, paramInt));
      localViewHolder.imageView.setOnClickListener(new SearchForPromotionActivity.onClickListener(SearchForPromotionActivity.this, paramInt));
      localViewHolder.store_name.setText(StringUtils.ToDBC(StringUtils.StringFilter(localStore.getShopName())));
      localViewHolder.store_distance.setOnClickListener(new SearchForPromotionActivity.onClickListener(SearchForPromotionActivity.this, paramInt));
      localViewHolder.store_distance.setText(DistanceConversionUtils.getDistance(localStore.getDistance()));
      new ImageUrlLoader(SearchForPromotionActivity.this).showPic(localStore.getShopLogo() + "?width=" + i / 3, localViewHolder.store_logo);
      if (localGoods.getGoodsScan() != 1)
      {
        localViewHolder.mer_scan.setVisibility(4);
        if (localGoods.getGoodsEndDate().equals(""))
          break label809;
        paramView = localGoods.getGoodsEndDate().split("-");
        localViewHolder.mer_time.setText(paramView[1] + "月" + paramView[2] + "日" + "前有效");
        label722: if (!localGoods.isCollect())
          break label821;
        localViewHolder.mer_collect.setImageResource(2130837620);
      }
      for (SearchForPromotionActivity.this.goodsCollectState = true; ; SearchForPromotionActivity.this.goodsCollectState = false)
      {
        localViewHolder.layout_mer_collect.setOnClickListener(new SearchForPromotionActivity.onClickListener(SearchForPromotionActivity.this, paramInt, localViewHolder.mer_collect));
        localViewHolder.layout_mer_share.setOnClickListener(new SearchForPromotionActivity.onClickListener(SearchForPromotionActivity.this, paramInt));
        return localView;
        localViewHolder.mer_scan.setVisibility(0);
        break;
        label809: System.out.println("--->有效期为空");
        break label722;
        label821: localViewHolder.mer_collect.setImageResource(2130837619);
      }
    }

    class ViewHolder
    {
      FrameLayout goods_frame;
      ScaleImageView imageView;
      ImageView iv_location;
      View layout_collect_share;
      View layout_mer_collect;
      View layout_mer_share;
      ImageView mer_collect;
      ImageView mer_scan;
      ImageView mer_share;
      TextView mer_time;
      View progressBar;
      LinearLayout share_coll_layout;
      TextView store_distance;
      LinearLayout store_info_layout;
      ImageView store_logo;
      TextView store_name;
      TextView tv_beans;

      ViewHolder()
      {
      }
    }
  }

  class onClickListener
    implements View.OnClickListener
  {
    ImageView img;
    int position;

    public onClickListener(int arg2)
    {
      int i;
      this.position = i;
    }

    public onClickListener(int paramImageView, ImageView arg3)
    {
      this.position = paramImageView;
      Object localObject;
      this.img = localObject;
    }

    public void onClick(View paramView)
    {
      if (this.position >= SearchForPromotionActivity.this.mAdapter.mInfos.size())
        return;
      Object localObject = (Store)SearchForPromotionActivity.this.mAdapter.mInfos.get(this.position);
      Goods localGoods = (Goods)((Store)localObject).getGoods_list().get(0);
      switch (paramView.getId())
      {
      default:
        return;
      case 2131231134:
      case 2131231480:
        paramView = BaseActivity.loginUser;
        SearchForPromotionActivity.this.collectPosition = this.position;
        paramView = new Intent(SearchForPromotionActivity.this, MerChandiseDetailActivity.class);
        paramView.putExtra("store", (Serializable)localObject);
        paramView.putExtra("goods", (Serializable)((Store)SearchForPromotionActivity.this.mAdapter.mInfos.get(this.position)).getGoods_list().get(0));
        paramView.putExtra("position", this.position);
        SearchForPromotionActivity.this.startActivity(paramView);
        return;
      case 2131231299:
        paramView = new Intent();
        paramView.setClass(SearchForPromotionActivity.this, BaiduMapActivity.class);
        paramView.putExtra("storeLon", ((Store)localObject).getLon());
        paramView.putExtra("storeLat", ((Store)localObject).getLat());
        paramView.putExtra("shopName", ((Store)localObject).getShopName());
        paramView.putExtra("storeAddress", ((Store)localObject).getShopAddress());
        paramView.setFlags(268435456);
        SearchForPromotionActivity.this.getApplicationContext().startActivity(paramView);
        return;
      case 2131231487:
        SearchForPromotionActivity.this.clickCollect(this.position, this.img);
        return;
      case 2131231488:
      }
      paramView = new ShareDialog(SearchForPromotionActivity.this);
      localObject = SearchForPromotionActivity.this.mAdapter.getStoreInfo();
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        localObject = (Store)((List)localObject).get(this.position);
        if (TextUtils.isEmpty(((Store)localObject).getReatilName()))
          paramView.setShare(localGoods.getGoodsLogo(), localGoods.getGoodsName(), localGoods.getGoodsDescribe(), localGoods.getGoodsId(), AndroidApplication.getInstance().getUser().getId(), 1);
      }
      while (true)
      {
        paramView.show();
        return;
        paramView.setShare(localGoods.getGoodsLogo(), "【" + ((Store)localObject).getReatilName() + "】 " + localGoods.getGoodsName(), localGoods.getGoodsDescribe(), localGoods.getGoodsId(), AndroidApplication.getInstance().getUser().getId(), 1);
        continue;
        paramView.setShare(localGoods.getGoodsLogo(), localGoods.getGoodsName(), localGoods.getGoodsDescribe(), localGoods.getGoodsId(), AndroidApplication.getInstance().getUser().getId(), 1);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SearchForPromotionActivity
 * JD-Core Version:    0.6.2
 */