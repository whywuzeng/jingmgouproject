package com.ismartgo.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dodowaterfall.widget.ScaleImageView;
import com.ismartgo.app.adapter.ImagePagerAdapter;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Advertise;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.DistanceConversionUtils;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.item.Not_In_Shop_ScanMenu;
import com.ismartgo.app.map.BaiduMapActivity;
import com.ismartgo.app.net.HomeAdvertiseRequest;
import com.ismartgo.app.ownself.view.CircleFlowIndicator;
import com.ismartgo.app.ownself.view.NotInShopExpandTabView;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.ownself.view.ViewFlow;
import com.ismartgo.app.share.ShareDialog;
import com.ismartgo.app.tools.ImageUrlLoader;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

public class ScanStoreGoodsActivity extends BaseActivity
  implements XListView.IXListViewListener
{
  private ArrayList<String> adIdList = new ArrayList();
  private NotInShopExpandTabView expandTabView;
  private ArrayList<String> imageUrlList = new ArrayList();
  private ImagePagerAdapter imgAdapter;
  private ImgLoader imgLoader;
  private ArrayList<String> linkUrlArray = new ArrayList();
  private StaggeredAdapter mAdapter = null;
  private XListView mAdapterView = null;
  private MyDialog mDialog;
  private CircleFlowIndicator mFlowIndicator;
  private ViewFlow mViewFlow;
  private int pageSize = 30;
  private int pages = 1;
  private int retailId = -1;
  private int shopTypeId = -1;
  private ToastDefine toastDefine;
  private Not_In_Shop_ScanMenu top_menu;

  private void AddItemToContainer(int paramInt1, final int paramInt2)
  {
    if (BaseActivity.loginUser != null)
      BaseActivity.loginUser.getId();
    HashMap localHashMap = new HashMap();
    if (loginUser == null);
    for (String str = ""; ; str = loginUser.getId())
    {
      localHashMap.put("uid", str);
      localHashMap.put("cityName", BaseActivity.city);
      localHashMap.put("shopTypeId", this.shopTypeId);
      localHashMap.put("retailId", this.retailId);
      localHashMap.put("lon", BaseActivity.log);
      localHashMap.put("lat", BaseActivity.lat);
      localHashMap.put("pages", this.pages);
      localHashMap.put("pageSize", this.pageSize);
      HttpRequest.getInstance().executePostStringRequest(this, Url.GOODS_SCAN_LIST_URL, HttpWhat.GOODS_SCAN_LIST, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          paramAnonymousResponse = HttpJsonParse.getScanStoreGoods(((String)paramAnonymousResponse.get()).toString());
          if (paramInt2 == 1)
          {
            ScanStoreGoodsActivity.this.mAdapter.clearData();
            if (paramAnonymousResponse.size() > 0)
            {
              ScanStoreGoodsActivity.this.mAdapterView.setVisibility(0);
              ScanStoreGoodsActivity.this.findViewById(2131230856).setVisibility(8);
              ScanStoreGoodsActivity.this.mAdapter.addItemLast(paramAnonymousResponse);
              ScanStoreGoodsActivity.this.mAdapter.notifyDataSetChanged();
              ScanStoreGoodsActivity.this.mDialog.dismiss();
            }
          }
          while (true)
          {
            if (paramAnonymousResponse.size() >= ScanStoreGoodsActivity.this.pageSize)
              break label248;
            ScanStoreGoodsActivity.this.mAdapterView.setFootHide();
            return;
            ScanStoreGoodsActivity.this.findViewById(2131230856).setVisibility(0);
            ScanStoreGoodsActivity.this.mAdapterView.setVisibility(8);
            break;
            if (paramInt2 == 2)
            {
              ScanStoreGoodsActivity.this.mAdapterView.stopLoadMore();
              if (paramAnonymousResponse.size() > 0)
              {
                ScanStoreGoodsActivity.this.mAdapter.addItemLast(paramAnonymousResponse);
                ScanStoreGoodsActivity.this.mAdapter.notifyDataSetChanged();
              }
              else
              {
                ScanStoreGoodsActivity.this.toast.setMessage(ScanStoreGoodsActivity.this.getString(2131296381));
                ScanStoreGoodsActivity.this.toast.setDisplay(false);
                ScanStoreGoodsActivity.this.toast.show();
              }
            }
          }
          label248: ScanStoreGoodsActivity.this.mAdapterView.setFootVisible();
        }
      });
      if (paramInt2 == 1)
      {
        this.mAdapter.clearData();
        this.mAdapter.notifyDataSetChanged();
        this.mAdapterView.setFootHide();
        findViewById(2131230856).setVisibility(8);
        this.mDialog.show();
      }
      return;
    }
  }

  private void getAdvertiseImg()
  {
    HomeAdvertiseRequest localHomeAdvertiseRequest = new HomeAdvertiseRequest(this, Url.ADVERTISE_URL);
    localHomeAdvertiseRequest.initParams(BaseActivity.city, 3);
    localHomeAdvertiseRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        paramAnonymousThrowable = new ToastDefine(ScanStoreGoodsActivity.this.getApplicationContext());
        paramAnonymousThrowable.setMessage("亲，网络好像出问题了~");
        paramAnonymousThrowable.show();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        Object localObject = (Infos)paramAnonymousObject;
        if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
        {
          paramAnonymousObject = ((Infos)localObject).getAdver_list();
          paramAnonymousInt = 0;
          if (paramAnonymousInt >= paramAnonymousObject.size())
          {
            paramAnonymousObject = ScanStoreGoodsActivity.this;
            localObject = new ImagePagerAdapter(ScanStoreGoodsActivity.this, ScanStoreGoodsActivity.this.imageUrlList, ScanStoreGoodsActivity.this.linkUrlArray, ScanStoreGoodsActivity.this.imageUrlList);
            if (ScanStoreGoodsActivity.this.imageUrlList.size() <= 1)
              break label304;
            bool = true;
            paramAnonymousObject.imgAdapter = ((ImagePagerAdapter)localObject).setInfiniteLoop(bool);
            ScanStoreGoodsActivity.this.initAdBanner();
          }
        }
        label304: 
        while ((((Infos)localObject).getMsg().toString().trim().equals("")) || (((Infos)localObject).getMsg().toString().trim().equals("成功")))
          while (true)
          {
            return;
            Log.e("TAG", ((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath() + "---" + ((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
            ScanStoreGoodsActivity.this.adIdList.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getId());
            ScanStoreGoodsActivity.this.imageUrlList.add(StringUtils.getImgUrl(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath()));
            if (!((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink().equals(""))
              ScanStoreGoodsActivity.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
            while (true)
            {
              paramAnonymousInt += 1;
              break;
              ScanStoreGoodsActivity.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getDetail());
            }
            boolean bool = false;
          }
        paramAnonymousObject = new ToastDefine(ScanStoreGoodsActivity.this.getApplicationContext());
        paramAnonymousObject.setMessage(((Infos)localObject).getMsg());
        paramAnonymousObject.show();
      }
    });
    localHomeAdvertiseRequest.startRequest();
  }

  private void initAdBanner()
  {
    this.mViewFlow = ((ViewFlow)findViewById(2131231084));
    this.mFlowIndicator = ((CircleFlowIndicator)findViewById(2131231085));
    this.mViewFlow.setAdapter(this.imgAdapter);
    this.mViewFlow.setmSideBuffer(this.imageUrlList.size());
    this.mViewFlow.setFlowIndicator(this.mFlowIndicator);
    if (this.imageUrlList.size() > 1)
    {
      this.mViewFlow.setTimeSpan(5000L);
      this.mViewFlow.setSelection(this.imageUrlList.size() * 1000);
      this.mViewFlow.startAutoFlowTimer();
    }
  }

  private void initView()
  {
    this.expandTabView = ((NotInShopExpandTabView)findViewById(2131231004));
    this.top_menu = new Not_In_Shop_ScanMenu(this, this.expandTabView, 2, 3);
    this.top_menu.setItem();
    this.mAdapterView = ((XListView)findViewById(2131230854));
    this.mAdapterView.setCacheColorHint(Color.parseColor("#000000"));
    this.mAdapterView.setSelector(new ColorDrawable(0));
    this.mAdapterView.setPullLoadEnable(true);
    this.mAdapterView.setXListViewListener(this);
    this.mAdapter = new StaggeredAdapter(this, this.mAdapterView);
    this.mAdapterView.setAdapter(this.mAdapter);
  }

  public void addView()
  {
    this.pages = 1;
    AddItemToContainer(this.pages, 1);
  }

  public void back(View paramView)
  {
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    applyKitKatTranslucency();
    this.toastDefine = new ToastDefine(this);
    setContentView(2130903204);
    this.mDialog = new MyDialog(this);
    this.imgLoader = ImgLoader.getInstance(this);
    initView();
    addView();
    getAdvertiseImg();
  }

  public void onLoadMore1()
  {
    int i = this.pages + 1;
    this.pages = i;
    AddItemToContainer(i, 2);
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  public void onRefresh1()
  {
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  public void setScreenStore(int paramInt1, int paramInt2)
  {
    if ((this.shopTypeId != paramInt1) || (this.retailId != paramInt2))
    {
      this.shopTypeId = paramInt1;
      this.retailId = paramInt2;
      addView();
    }
  }

  public class StaggeredAdapter extends BaseAdapter
  {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private LinkedList<Store> mInfos;
    private XListView mListView;

    public StaggeredAdapter(Context paramXListView, XListView arg3)
    {
      this.mContext = paramXListView;
      this.mInfos = new LinkedList();
      Object localObject;
      this.mListView = localObject;
      this.layoutInflater = LayoutInflater.from(paramXListView);
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

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      final Store localStore = (Store)this.mInfos.get(paramInt);
      Goods localGoods = (Goods)localStore.getGoods_list().get(0);
      paramViewGroup = paramView;
      if (paramView == null)
      {
        paramViewGroup = this.layoutInflater.inflate(2130903248, null);
        paramView = new ViewHolder();
        paramView.store_distance = ((TextView)paramViewGroup.findViewById(2131231299));
        paramView.iv_location = ((ImageView)paramViewGroup.findViewById(2131231484));
        paramView.mer_share = ((ImageView)paramViewGroup.findViewById(2131230951));
        paramView.store_logo = ((ImageView)paramViewGroup.findViewById(2131231483));
        paramView.mer_scan = ((ImageView)paramViewGroup.findViewById(2131230948));
        paramView.goods_frame = ((FrameLayout)paramViewGroup.findViewById(2131231480));
        paramView.progressBar = paramViewGroup.findViewById(2131231489);
        paramView.imageView = ((ScaleImageView)paramViewGroup.findViewById(2131231134));
        paramView.store_info_layout = ((LinearLayout)paramViewGroup.findViewById(2131231482));
        paramView.share_coll_layout = ((LinearLayout)paramViewGroup.findViewById(2131231486));
        paramView.store_name = ((TextView)paramViewGroup.findViewById(2131231297));
        paramView.mer_time = ((TextView)paramViewGroup.findViewById(2131231481));
        paramView.tv_beans = ((TextView)paramViewGroup.findViewById(2131230783));
        paramView.layout_scanproduct = ((LinearLayout)paramViewGroup.findViewById(2131231216));
        paramView.tv_goods_name = ((TextView)paramViewGroup.findViewById(2131230958));
        paramViewGroup.setTag(paramView);
      }
      ViewHolder localViewHolder = (ViewHolder)paramViewGroup.getTag();
      localViewHolder.store_info_layout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent();
          paramAnonymousView.setClass(ScanStoreGoodsActivity.this, BaiduMapActivity.class);
          paramAnonymousView.putExtra("storeLon", localStore.getLon());
          paramAnonymousView.putExtra("storeLat", localStore.getLat());
          paramAnonymousView.putExtra("shopName", localStore.getShopName());
          paramAnonymousView.putExtra("storeAddress", localStore.getShopAddress());
          paramAnonymousView.setFlags(268435456);
          ScanStoreGoodsActivity.this.getApplicationContext().startActivity(paramAnonymousView);
        }
      });
      new ImageUrlLoader(ScanStoreGoodsActivity.this).showPic(localStore.getShopLogo(), localViewHolder.store_logo);
      localViewHolder.progressBar.setVisibility(8);
      localViewHolder.store_name.setText(localStore.getShopName());
      int i = ScanStoreGoodsActivity.this.mAdapterView.getItemColumnWidth();
      paramView = localViewHolder.imageView.getLayoutParams();
      paramView.width = i;
      paramView.height = (Integer.valueOf(localGoods.getHeight()).intValue() * i / Integer.valueOf(localGoods.getWidth()).intValue());
      localViewHolder.imageView.setLayoutParams(paramView);
      String str = localGoods.getGoodsLogo();
      paramView = str;
      if (str != null)
      {
        paramView = str;
        if (!str.equals(""))
        {
          paramView = str;
          if (str.contains("?"))
            paramView = localGoods.getGoodsLogo().substring(0, localGoods.getGoodsLogo().lastIndexOf("?"));
        }
      }
      paramView = paramView + "?width=" + i;
      ImgLoader.getInstance(ScanStoreGoodsActivity.this).showPic(paramView, localViewHolder.imageView, false);
      localViewHolder.goods_frame.setOnClickListener(new ScanStoreGoodsActivity.onClickListener(ScanStoreGoodsActivity.this, paramInt));
      localViewHolder.imageView.setOnClickListener(new ScanStoreGoodsActivity.onClickListener(ScanStoreGoodsActivity.this, paramInt));
      localViewHolder.store_distance.setText(DistanceConversionUtils.getDistance1(localStore.getDistance()));
      localViewHolder.tv_beans.setText(String.valueOf(localGoods.getBeannumber()) + "豆");
      if (!localGoods.getGoodsEndDate().equals(""))
      {
        paramView = localGoods.getGoodsEndDate().split("-");
        localViewHolder.mer_time.setText(paramView[1] + "月" + paramView[2] + "日" + "前有效");
      }
      while (true)
      {
        localViewHolder.tv_goods_name.setText(localGoods.getGoodsName());
        localViewHolder.layout_scanproduct.setOnClickListener(new ScanStoreGoodsActivity.onClickListener(ScanStoreGoodsActivity.this, paramInt));
        return paramViewGroup;
        System.out.println("--->有效期为空");
      }
    }

    class ViewHolder
    {
      FrameLayout goods_frame;
      ScaleImageView imageView;
      ImageView iv_location;
      ImageView iv_scan;
      View layout_collect_share;
      LinearLayout layout_scanproduct;
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
      TextView tv_goods_name;

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
      System.out.println("--->click position=" + this.position);
      Store localStore = (Store)ScanStoreGoodsActivity.access$0(ScanStoreGoodsActivity.this).mInfos.get(this.position);
      Goods localGoods = (Goods)localStore.getGoods_list().get(0);
      switch (paramView.getId())
      {
      default:
        return;
      case 2131231134:
      case 2131231480:
        paramView = new Intent(ScanStoreGoodsActivity.this, MerChandiseDetailActivity.class);
        paramView.putExtra("store", localStore);
        paramView.putExtra("goods", (Serializable)((Store)ScanStoreGoodsActivity.access$0(ScanStoreGoodsActivity.this).mInfos.get(this.position)).getGoods_list().get(0));
        paramView.putExtra("position", this.position);
        paramView.putExtra("goodsType", 2);
        ScanStoreGoodsActivity.this.startActivity(paramView);
        return;
      case 2131230948:
      case 2131231216:
      case 2131231368:
        ScanStoreGoodsActivity.this.toastDefine.setMessage("您不在扫描范围内哦~");
        ScanStoreGoodsActivity.this.toastDefine.show();
        return;
      case 2131231488:
      }
      paramView = new ShareDialog(ScanStoreGoodsActivity.this);
      localStore = (Store)ScanStoreGoodsActivity.access$0(ScanStoreGoodsActivity.this).mInfos.get(this.position);
      if ((localStore != null) && (!TextUtils.isEmpty(localStore.getReatilName())))
        paramView.setShare(localGoods.getGoodsLogo(), "【" + localStore.getReatilName() + "】 " + localGoods.getGoodsName(), localGoods.getGoodsDescribe(), localGoods.getGoodsId(), AndroidApplication.getInstance().getUser().getId(), 2);
      while (true)
      {
        paramView.show();
        return;
        paramView.setShare(localGoods.getGoodsLogo(), localGoods.getGoodsName(), localGoods.getGoodsDescribe(), localGoods.getGoodsId(), AndroidApplication.getInstance().getUser().getId(), 2);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ScanStoreGoodsActivity
 * JD-Core Version:    0.6.2
 */