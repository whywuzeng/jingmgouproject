package com.ismartgo.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dodowaterfall.widget.ScaleImageView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.CurrentLocationInfo;
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
import com.ismartgo.app.map.BaiduMapActivity;
import com.ismartgo.app.net.DelCollcetGoodsRequest;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.share.ShareDialog;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
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

public class MyCollectActivity extends BaseActivity
  implements View.OnClickListener, XListViewRefresh.IXListViewListener
{
  public static String TAG = "MyCollectActivity";
  public static int collectionState;
  public static boolean needRefresh;
  public static int refreshGoodsId;
  AndroidApplication application;
  int imageWidth;
  private ImgLoader imgLoader;
  private LinearLayout layoutNoData;
  private StaggeredAdapter mAdapter = null;
  private XListViewRefresh mAdapterView = null;
  MyDialog mDialog;
  private final int pageSize = 30;
  private int pages = 1;
  ToastDefine toast;
  User user;

  private void AddItemToContainer(int paramInt1, final int paramInt2)
  {
    String str = "";
    if (BaseActivity.loginUser != null)
      str = BaseActivity.loginUser.getId();
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", str);
    localHashMap.put("lon", BaseActivity.log);
    localHashMap.put("lat", BaseActivity.lat);
    localHashMap.put("pages", paramInt1);
    localHashMap.put("pageSize", "30");
    HttpRequest.getInstance().executePostStringRequest(this, Url.MY_COLLECT_LIST_URL, HttpWhat.MY_COLLECT_LIST, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        paramAnonymousResponse = HttpJsonParse.getMyCollectData(((String)paramAnonymousResponse.get()).toString());
        if (paramInt2 == 1)
        {
          MyCollectActivity.this.mAdapter.clearData();
          if (paramAnonymousResponse.size() > 0)
          {
            MyCollectActivity.this.layoutNoData.setVisibility(8);
            MyCollectActivity.this.mAdapter.addItemLast(paramAnonymousResponse);
            MyCollectActivity.this.mAdapter.notifyDataSetChanged();
            MyCollectActivity.this.mAdapterView.stopRefresh();
            MyCollectActivity.this.mDialog.dismiss();
          }
        }
        while (true)
        {
          if (paramAnonymousResponse.size() >= 30)
            break label246;
          MyCollectActivity.this.mAdapterView.setFootHide();
          return;
          MyCollectActivity.this.mAdapter.clearData();
          MyCollectActivity.this.mAdapter.notifyDataSetChanged();
          MyCollectActivity.this.layoutNoData.setVisibility(0);
          break;
          if (paramInt2 == 2)
          {
            MyCollectActivity.this.mAdapterView.stopLoadMore();
            if (paramAnonymousResponse.size() > 0)
            {
              MyCollectActivity.this.mAdapter.addItemLast(paramAnonymousResponse);
              MyCollectActivity.this.mAdapter.notifyDataSetChanged();
            }
            else
            {
              MyCollectActivity.this.toast.setMessage(MyCollectActivity.this.getString(2131296376));
              MyCollectActivity.this.toast.setDisplay(false);
              MyCollectActivity.this.toast.show();
            }
          }
        }
        label246: MyCollectActivity.this.mAdapterView.setFootVisible();
      }
    });
    if (paramInt2 == 1)
    {
      this.mAdapterView.setFootHide();
      this.layoutNoData.setVisibility(8);
    }
  }

  private void initView()
  {
    initTitleBar();
    ((TextView)findViewById(2131231001)).setText("我的收藏");
    ((ImageView)findViewById(2131231076)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyCollectActivity.this.finish();
      }
    });
    this.toast = new ToastDefine(this);
    this.application = ((AndroidApplication)getApplication());
    this.user = this.application.getUser();
    this.layoutNoData = ((LinearLayout)findViewById(2131230855));
  }

  public void addView()
  {
    this.pages = 1;
    AddItemToContainer(this.pages, 1);
  }

  public void onClick(View paramView)
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903050);
    this.mDialog = new MyDialog(this);
    this.imgLoader = ImgLoader.getInstance(this);
    initView();
    this.imageWidth = (getWindowManager().getDefaultDisplay().getWidth() / 2);
    this.mAdapterView = ((XListViewRefresh)findViewById(2131230854));
    this.mAdapterView.setCacheColorHint(Color.parseColor("#000000"));
    this.mAdapterView.setSelector(new ColorDrawable(0));
    this.mAdapterView.setPullLoadEnable(true);
    this.mAdapterView.setXListViewListener(this);
    this.mAdapter = new StaggeredAdapter(this, this.mAdapterView);
    this.mAdapterView.setAdapter(this.mAdapter);
    addView();
  }

  public void onLoadMore()
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

  public void onRefresh()
  {
    addView();
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
    int i;
    if ((needRefresh) && (this.mAdapter != null))
    {
      needRefresh = false;
      i = 0;
    }
    while (true)
    {
      if (i >= this.mAdapter.mInfos.size())
        return;
      Store localStore = (Store)this.mAdapter.mInfos.get(i);
      if ((((Goods)localStore.getGoods_list().get(0)).getGoodsId() == refreshGoodsId) && (((Goods)localStore.getGoods_list().get(0)).isCollect()) && (collectionState == 2))
      {
        this.mAdapter.mInfos.remove(i);
        this.mAdapter.notifyDataSetChanged();
        return;
      }
      i += 1;
    }
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

    public LinkedList<Store> getData()
    {
      return this.mInfos;
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
        localView.setTag(paramView);
      }
      ViewHolder localViewHolder = (ViewHolder)localView.getTag();
      localViewHolder.layout_collect_share.setVisibility(8);
      localViewHolder.progressBar.setVisibility(8);
      int i = MyCollectActivity.this.mAdapterView.getItemColumnWidth();
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
      MyCollectActivity.this.imgLoader.showPic(paramView + "?width=" + i / 3, localViewHolder.imageView, false);
      localViewHolder.goods_frame.setOnClickListener(new MyCollectActivity.onClickListener(MyCollectActivity.this, paramInt));
      localViewHolder.imageView.setOnClickListener(new MyCollectActivity.onClickListener(MyCollectActivity.this, paramInt));
      localViewHolder.store_name.setText(StringUtils.ToDBC(StringUtils.StringFilter(localStore.getShopName())));
      localViewHolder.store_distance.setOnClickListener(new MyCollectActivity.onClickListener(MyCollectActivity.this, paramInt));
      localViewHolder.store_distance.setText(DistanceConversionUtils.getDistance1(localStore.getDistance()));
      ImgLoader.getInstance(MyCollectActivity.this).showPic(localStore.getShopLogo(), localViewHolder.store_logo, false);
      if (localGoods.getGoodsScan() != 1)
      {
        localViewHolder.mer_scan.setVisibility(4);
        if (localGoods.getGoodsEndDate().equals(""))
          break label717;
        paramView = localGoods.getGoodsEndDate().split("-");
        localViewHolder.mer_time.setText(paramView[1] + "月" + paramView[2] + "日" + "前有效");
      }
      while (true)
      {
        localViewHolder.goods_frame.setOnLongClickListener(new MyCollectActivity.longClickListener(MyCollectActivity.this, paramInt));
        localViewHolder.imageView.setOnLongClickListener(new MyCollectActivity.longClickListener(MyCollectActivity.this, paramInt));
        return localView;
        localViewHolder.mer_scan.setVisibility(0);
        break;
        label717: System.out.println("--->有效期为空");
      }
    }

    class ViewHolder
    {
      FrameLayout goods_frame;
      ScaleImageView imageView;
      ImageView iv_location;
      ImageView iv_scan;
      View layout_collect_share;
      ImageView mer_scan;
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

  class longClickListener
    implements View.OnLongClickListener
  {
    int position;

    public longClickListener(int arg2)
    {
      int i;
      this.position = i;
    }

    public boolean onLongClick(final View paramView)
    {
      switch (paramView.getId())
      {
      default:
      case 2131231134:
      case 2131231480:
      }
      while (true)
      {
        return false;
        paramView = new AlertDialog.Builder(MyCollectActivity.this).create();
        paramView.show();
        final Store localStore = (Store)MyCollectActivity.this.mAdapter.mInfos.get(this.position);
        if (localStore == null)
          return true;
        paramView.getWindow().setContentView(2130903116);
        paramView.getWindow().findViewById(2131230782).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramView.dismiss();
            MyCollectActivity.this.mDialog.show();
            paramAnonymousView = new DelCollcetGoodsRequest(MyCollectActivity.this, Url.DELETECOLLECTGOODS_URL);
            paramAnonymousView.initParams(MyCollectActivity.this.user.getId(), ((Goods)localStore.getGoods_list().get(0)).getGoodsId(), localStore.getShopId());
            MyCollectActivity.this.mDialog.show();
            paramAnonymousView.setOnCompleteListener(new OnCompleteListener()
            {
              public void onFailure(Throwable paramAnonymous2Throwable, int paramAnonymous2Int, String paramAnonymous2String)
              {
                MyCollectActivity.this.toast.setMessage(MyCollectActivity.this.getString(2131296392));
                MyCollectActivity.this.toast.show();
                MyCollectActivity.this.mDialog.dismiss();
              }

              public void onResult(Object paramAnonymous2Object, int paramAnonymous2Int)
              {
                paramAnonymous2Object = (Infos)paramAnonymous2Object;
                if (paramAnonymous2Int == ResultCode.RESULT_OK)
                  if ((MyCollectActivity.this.mAdapter.mInfos != null) && (MyCollectActivity.this.mAdapter.mInfos.size() > 0))
                  {
                    MyCollectActivity.this.mAdapter.mInfos.remove(MyCollectActivity.longClickListener.this.position);
                    MyCollectActivity.this.mAdapter.notifyDataSetChanged();
                  }
                while (true)
                {
                  MyCollectActivity.this.mDialog.dismiss();
                  return;
                  if (!paramAnonymous2Object.getMsg().equals(""))
                  {
                    MyCollectActivity.this.toast.setMessage(paramAnonymous2Object.getMsg());
                    MyCollectActivity.this.toast.show();
                  }
                }
              }
            });
            paramAnonymousView.startRequest();
          }
        });
        paramView.getWindow().findViewById(2131230873).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramView.dismiss();
          }
        });
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
      if (this.position >= MyCollectActivity.this.mAdapter.mInfos.size())
        return;
      System.out.println("--->click position=" + this.position);
      Object localObject = (Store)MyCollectActivity.this.mAdapter.mInfos.get(this.position);
      Goods localGoods = (Goods)((Store)localObject).getGoods_list().get(0);
      switch (paramView.getId())
      {
      default:
        return;
      case 2131230948:
      case 2131231368:
        paramView = AndroidApplication.getInstance().getCurrentLocation();
        if (paramView != null)
          if (paramView.getScanShopId() > 0)
          {
            paramView = new Intent();
            paramView.setClass(MyCollectActivity.this, CaptureActivity.class);
            paramView.putExtra("shop", (Serializable)localObject);
            paramView.putExtra("position", this.position);
            paramView.putExtra("goods", localGoods);
            MyCollectActivity.this.startActivity(paramView);
            return;
          }
      case 2131231134:
      case 2131231480:
        paramView = BaseActivity.loginUser;
        paramView = new Intent(MyCollectActivity.this, MerChandiseDetailActivity.class);
        paramView.putExtra("store", (Serializable)localObject);
        paramView.putExtra("goods", (Serializable)((Store)MyCollectActivity.this.mAdapter.mInfos.get(this.position)).getGoods_list().get(0));
        paramView.putExtra("position", this.position);
        MyCollectActivity.this.startActivity(paramView);
        return;
      case 2131231299:
        paramView = new Intent();
        paramView.setClass(MyCollectActivity.this, BaiduMapActivity.class);
        paramView.putExtra("storeLon", ((Store)localObject).getLon());
        paramView.putExtra("storeLat", ((Store)localObject).getLat());
        paramView.putExtra("shopName", ((Store)localObject).getShopName());
        paramView.putExtra("storeAddress", ((Store)localObject).getShopAddress());
        paramView.setFlags(268435456);
        MyCollectActivity.this.getApplicationContext().startActivity(paramView);
        return;
        MyCollectActivity.this.toast.setMessage("您不在扫描范围内哦~");
        MyCollectActivity.this.toast.show();
        return;
        MyCollectActivity.this.toast.setMessage("您不在扫描范围内哦~");
        MyCollectActivity.this.toast.show();
        return;
      case 2131231488:
      }
      paramView = new ShareDialog(MyCollectActivity.this);
      localObject = MyCollectActivity.this.mAdapter.getData();
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
 * Qualified Name:     com.ismartgo.app.activity.MyCollectActivity
 * JD-Core Version:    0.6.2
 */