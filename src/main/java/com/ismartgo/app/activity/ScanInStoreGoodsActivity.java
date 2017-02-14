package com.ismartgo.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dodowaterfall.widget.ScaleImageView;
import com.ismartgo.app.adapter.ImagePagerAdapter;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Advertise;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.net.HomeAdvertiseRequest;
import com.ismartgo.app.ownself.view.AlwaysMarqueeTextView;
import com.ismartgo.app.ownself.view.CircleFlowIndicator;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.ownself.view.ViewFlow;
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
import java.util.List;
import java.util.Map;
import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScanInStoreGoodsActivity extends BaseActivity
  implements XListView.IXListViewListener
{
  private static final String TAG = "ScanInStoreGoodsActivity";
  public static boolean isScan;
  private ArrayList<String> adIdList = new ArrayList();
  private Button btnScanProduct;
  private String flag;
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
  private int shopId;
  private Store store;
  private ToastDefine toastDefine;
  private AlwaysMarqueeTextView tv_title;

  private void AddItemToContainer(int paramInt1, final int paramInt2)
  {
    this.mDialog.show();
    HashMap localHashMap = new HashMap();
    if (loginUser == null);
    for (String str = ""; ; str = loginUser.getId())
    {
      localHashMap.put("uid", str);
      localHashMap.put("shopid", this.shopId);
      localHashMap.put("pages", paramInt1);
      localHashMap.put("pageSize", this.pageSize);
      HttpRequest.getInstance().executePostStringRequest(this, Url.IN_SHOP_GOODS_SCAN_LIST_URL, HttpWhat.IN_SHOP_GOODS_SCAN_LIST, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          paramAnonymousResponse = ScanInStoreGoodsActivity.this.parseNewsJSON(((String)paramAnonymousResponse.get()).toString());
          if ((paramAnonymousResponse != null) && (ScanInStoreGoodsActivity.this.store != null))
          {
            ((TextView)ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231215)).setText(ScanInStoreGoodsActivity.this.store.getBeans() + "豆");
            if ((ScanInStoreGoodsActivity.this.store.getUserIsSign() != null) && (ScanInStoreGoodsActivity.this.store.getUserIsSign().equals("Y")))
            {
              ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231365).setVisibility(8);
              ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231367).setVisibility(0);
            }
          }
          else
          {
            if (paramInt2 != 1)
              break label328;
            ScanInStoreGoodsActivity.this.mAdapter.clearData();
            if ((paramAnonymousResponse == null) || (paramAnonymousResponse.size() <= 0))
              break label312;
            ScanInStoreGoodsActivity.this.findViewById(2131230856).setVisibility(8);
            ScanInStoreGoodsActivity.this.mAdapter.addItemLast(paramAnonymousResponse);
            ScanInStoreGoodsActivity.this.mAdapter.notifyDataSetChanged();
            label219: ScanInStoreGoodsActivity.this.mDialog.dismiss();
            label229: if ((paramAnonymousResponse == null) || (paramAnonymousResponse.size() >= ScanInStoreGoodsActivity.this.pageSize))
              break label426;
            ScanInStoreGoodsActivity.this.mAdapterView.setFootHide();
          }
          while (true)
          {
            ScanInStoreGoodsActivity.this.mDialog.dismiss();
            return;
            ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231365).setVisibility(0);
            ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231367).setVisibility(8);
            break;
            label312: ScanInStoreGoodsActivity.this.findViewById(2131230856).setVisibility(0);
            break label219;
            label328: if (paramInt2 != 2)
              break label229;
            ScanInStoreGoodsActivity.this.mAdapterView.stopLoadMore();
            if ((paramAnonymousResponse != null) && (paramAnonymousResponse.size() > 0))
            {
              ScanInStoreGoodsActivity.this.mAdapter.addItemLast(paramAnonymousResponse);
              ScanInStoreGoodsActivity.this.mAdapter.notifyDataSetChanged();
              break label229;
            }
            ScanInStoreGoodsActivity.this.toast.setMessage(ScanInStoreGoodsActivity.this.getString(2131296376));
            ScanInStoreGoodsActivity.this.toast.setDisplay(false);
            ScanInStoreGoodsActivity.this.toast.show();
            break label229;
            label426: ScanInStoreGoodsActivity.this.mAdapterView.setFootVisible();
          }
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
        paramAnonymousThrowable = new ToastDefine(ScanInStoreGoodsActivity.this.getApplicationContext());
        paramAnonymousThrowable.setMessage(ScanInStoreGoodsActivity.this.getString(2131296392));
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
            paramAnonymousObject = ScanInStoreGoodsActivity.this;
            localObject = new ImagePagerAdapter(ScanInStoreGoodsActivity.this, ScanInStoreGoodsActivity.this.imageUrlList, ScanInStoreGoodsActivity.this.linkUrlArray, ScanInStoreGoodsActivity.this.adIdList);
            if (ScanInStoreGoodsActivity.this.imageUrlList.size() <= 1)
              break label297;
            bool = true;
            paramAnonymousObject.imgAdapter = ((ImagePagerAdapter)localObject).setInfiniteLoop(bool);
          }
        }
        label297: 
        while ((((Infos)localObject).getMsg().equals("")) || (((Infos)localObject).getMsg().equals("成功")))
          while (true)
          {
            return;
            Log.e("TAG", ((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath() + "---" + ((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
            ScanInStoreGoodsActivity.this.adIdList.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getId());
            ScanInStoreGoodsActivity.this.imageUrlList.add(StringUtils.getImgUrl(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath()));
            if (!((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink().equals(""))
              ScanInStoreGoodsActivity.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
            while (true)
            {
              paramAnonymousInt += 1;
              break;
              ScanInStoreGoodsActivity.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getDetail());
            }
            boolean bool = false;
          }
        paramAnonymousObject = new ToastDefine(ScanInStoreGoodsActivity.this.getApplicationContext());
        paramAnonymousObject.setMessage(((Infos)localObject).getMsg());
        paramAnonymousObject.show();
      }
    });
    localHomeAdvertiseRequest.startRequest();
  }

  private void getShopData()
  {
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(Url.IN_SHOP_GOODS_SCAN_LIST_URL)).append("?uid=");
    if (BaseActivity.loginUser == null);
    for (String str = ""; ; str = BaseActivity.loginUser.getId())
    {
      str = str + "&shopid=" + this.shopId + "&pages=1&pageSize=" + 30;
      Log.i("ScanInStoreGoodsActivity", "请求数据url： " + str);
      new FinalHttp().get(str, new AjaxCallBack()
      {
        public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
        {
          super.onFailure(paramAnonymousThrowable, paramAnonymousInt, paramAnonymousString);
          ScanInStoreGoodsActivity.this.toastDefine.setMessage("连接失败，请检查网络是否连接");
          ScanInStoreGoodsActivity.this.toastDefine.show();
        }

        public void onSuccess(Object paramAnonymousObject)
        {
          super.onSuccess(paramAnonymousObject);
          Log.i("ScanInStoreGoodsActivity", "请求数据结果： " + paramAnonymousObject.toString());
          try
          {
            paramAnonymousObject = new JSONObject(paramAnonymousObject.toString());
            if (paramAnonymousObject.getInt("status") == ResultCode.RESULT_OK)
            {
              paramAnonymousObject = paramAnonymousObject.getJSONObject("data").getJSONObject("goodsscanlist");
              if (!paramAnonymousObject.isNull("beanSum"))
                ((TextView)ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231215)).setText(paramAnonymousObject.getInt("beanSum") + "豆");
              if (paramAnonymousObject.isNull("userIsSign"))
                return;
              if (paramAnonymousObject.getString("userIsSign").equals("Y"))
              {
                ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231365).setVisibility(8);
                ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231367).setVisibility(0);
                return;
              }
              ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231365).setVisibility(0);
              ScanInStoreGoodsActivity.this.mAdapterView.getHeaderView().findViewById(2131231367).setVisibility(8);
              return;
            }
          }
          catch (Exception paramAnonymousObject)
          {
            paramAnonymousObject.printStackTrace();
            return;
          }
          if (!paramAnonymousObject.getString("msg").equals(""))
          {
            ScanInStoreGoodsActivity.this.toastDefine.setMessage(paramAnonymousObject.getString("msg"));
            ScanInStoreGoodsActivity.this.toastDefine.show();
          }
        }
      });
      return;
    }
  }

  private void initAdBanner()
  {
    this.mViewFlow = ((ViewFlow)this.mAdapterView.getHeaderView().findViewById(2131231084));
    this.mFlowIndicator = ((CircleFlowIndicator)this.mAdapterView.getHeaderView().findViewById(2131231085));
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
    this.shopId = getIntent().getIntExtra("shopId", -1);
    this.tv_title = ((AlwaysMarqueeTextView)findViewById(2131231001));
    this.tv_title.setText(getIntent().getStringExtra("shopName"));
    this.btnScanProduct = ((Button)findViewById(2131231363));
    this.mAdapterView = ((XListView)findViewById(2131230854));
    this.mAdapterView.setCacheColorHint(Color.parseColor("#000000"));
    this.mAdapterView.setSelector(new ColorDrawable(0));
    this.mAdapterView.setHeadView(2130903208);
    this.mAdapterView.setPullLoadEnable(true);
    this.mAdapterView.setXListViewListener(this);
    this.mAdapterView.setOnItemClickListener(null);
    this.mAdapter = new StaggeredAdapter(this, this.mAdapterView);
    this.mAdapterView.setAdapter(this.mAdapter);
    this.btnScanProduct.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = AndroidApplication.getInstance().getCurrentLocation();
        if (paramAnonymousView != null)
        {
          if (paramAnonymousView.getScanShopId() == ScanInStoreGoodsActivity.this.shopId)
          {
            paramAnonymousView = new Intent();
            paramAnonymousView.setClass(ScanInStoreGoodsActivity.this, CaptureActivity.class);
            ScanInStoreGoodsActivity.this.startActivity(paramAnonymousView);
            return;
          }
          ScanInStoreGoodsActivity.this.toastDefine.setMessage("您不在扫描范围内哦~");
          ScanInStoreGoodsActivity.this.toastDefine.show();
          return;
        }
        ScanInStoreGoodsActivity.this.toastDefine.setMessage("您不在扫描范围内哦~");
        ScanInStoreGoodsActivity.this.toastDefine.show();
      }
    });
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
    setContentView(2130903207);
    this.mDialog = new MyDialog(this);
    this.toastDefine = new ToastDefine(this);
    this.imgLoader = ImgLoader.getInstance(this);
    initView();
    addView();
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
    if (isScan)
    {
      isScan = false;
      addView();
    }
  }

  public List<Goods> parseNewsJSON(String paramString)
  {
    if (this.store == null)
      this.store = new Store();
    ArrayList localArrayList = new ArrayList();
    if (paramString != null);
    while (true)
    {
      int i;
      JSONObject localJSONObject;
      try
      {
        paramString = new JSONObject(paramString).getJSONObject("data").getJSONObject("goodsscanlist");
        this.store.setShopId(this.shopId);
        this.store.setUserIsSign(paramString.getString("userIsSign"));
        this.store.setUserIsScan(paramString.getString("isScanFinish"));
        this.store.setBeans(paramString.optInt("beanSum"));
        JSONArray localJSONArray = paramString.getJSONArray("goodslist");
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
        {
          i = 0;
          if (i < localJSONArray.length());
        }
        else
        {
          this.store.setGoods_list(localArrayList);
          return localArrayList;
        }
        localJSONObject = localJSONArray.getJSONObject(i);
        Goods localGoods = new Goods();
        int j;
        if (localJSONObject.isNull("goodsId"))
        {
          j = 0;
          localGoods.setGoodsId(j);
          if (!(localJSONObject.get("goodsLogo") instanceof String))
            break label550;
        }
        else
        {
          j = localJSONObject.getInt("goodsId");
          continue;
        }
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL2));
        if (localJSONObject.isNull("goodsLogo"))
        {
          paramString = "";
          localGoods.setGoodsLogo(paramString);
          if (!localJSONObject.isNull("goodsName"))
            break label511;
          paramString = "";
          localGoods.setGoodsName(paramString);
          if (!localJSONObject.isNull("goodsEndDate"))
            break label523;
          paramString = "";
          localGoods.setGoodsEndDate(paramString);
          localGoods.setGoodsScan(localJSONObject.getInt("goodsScan"));
          localGoods.setCollect(localJSONObject.getString("isCollect").equalsIgnoreCase("Y"));
          localGoods.setBeannumber(Integer.valueOf(localJSONObject.getString("beannumber")).intValue());
          localGoods.setScaned(localJSONObject.getString("isScan").equalsIgnoreCase("Y"));
          if (!localJSONObject.isNull("isCollect"))
            break label535;
          paramString = "https://";
          localGoods.setH5Url(paramString);
          localGoods.setShopId(this.shopId);
          if (!(localJSONObject.get("imgHeight") instanceof Integer))
            break label550;
          localGoods.setHeight(localJSONObject.getInt("imgHeight"));
          if (!(localJSONObject.get("imgWidth") instanceof Integer))
            break label550;
          localGoods.setWidth(localJSONObject.getInt("imgWidth"));
          localArrayList.add(localGoods);
        }
      }
      catch (JSONException paramString)
      {
        Log.e("ScanInStoreGoodsActivity", "JSON解析异常： " + paramString.getMessage());
        paramString.printStackTrace();
        return localArrayList;
      }
      paramString = localJSONObject.getString("goodsLogo");
      continue;
      label511: paramString = localJSONObject.getString("goodsName");
      continue;
      label523: paramString = localJSONObject.getString("goodsEndDate");
      continue;
      label535: paramString = localJSONObject.getString("h5Url");
      continue;
      return localArrayList;
      label550: i += 1;
    }
  }

  public class StaggeredAdapter extends BaseAdapter
  {
    private Context mContext;
    private List<Goods> mInfos;
    private XListView mListView;

    public StaggeredAdapter(Context paramXListView, XListView arg3)
    {
      this.mContext = paramXListView;
      this.mInfos = new ArrayList();
      Object localObject;
      this.mListView = localObject;
    }

    public void addItemLast(List<Goods> paramList)
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
      Goods localGoods = (Goods)this.mInfos.get(paramInt);
      View localView = paramView;
      if (paramView == null)
      {
        localView = LayoutInflater.from(paramViewGroup.getContext()).inflate(2130903249, null);
        paramView = new ViewHolder();
        paramView.mer_share = ((ImageView)localView.findViewById(2131230951));
        paramView.mer_scan = ((ImageView)localView.findViewById(2131230948));
        paramView.mer_collect = ((ImageView)localView.findViewById(2131230945));
        paramView.goods_frame = ((FrameLayout)localView.findViewById(2131231480));
        paramView.progressBar = localView.findViewById(2131231489);
        paramView.imageView = ((ScaleImageView)localView.findViewById(2131231134));
        paramView.mer_time = ((TextView)localView.findViewById(2131231481));
        paramView.tv_beans = ((TextView)localView.findViewById(2131230783));
        paramView.tv_goods_name = ((TextView)localView.findViewById(2131230958));
        paramView.layout_scanproduct = ((LinearLayout)localView.findViewById(2131231216));
        localView.setTag(paramView);
      }
      ViewHolder localViewHolder = (ViewHolder)localView.getTag();
      localViewHolder.progressBar.setVisibility(8);
      int i = ScanInStoreGoodsActivity.this.mAdapterView.getItemColumnWidth();
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
      ScanInStoreGoodsActivity.this.imgLoader.showPic(paramView, localViewHolder.imageView, false);
      localViewHolder.goods_frame.setOnClickListener(new ScanInStoreGoodsActivity.onClickListener(ScanInStoreGoodsActivity.this, paramInt));
      localViewHolder.imageView.setOnClickListener(new ScanInStoreGoodsActivity.onClickListener(ScanInStoreGoodsActivity.this, paramInt));
      localViewHolder.tv_beans.setText(localGoods.getBeannumber() + "豆");
      localViewHolder.layout_scanproduct.setOnClickListener(new ScanInStoreGoodsActivity.onClickListener(ScanInStoreGoodsActivity.this, paramInt));
      if (localGoods.getGoodsScan() == 1)
      {
        if (localGoods.isScaned())
          ((ImageView)localView.findViewById(2131231368)).setImageResource(2130837748);
      }
      else
      {
        if (localGoods.getGoodsEndDate().equals(""))
          break label632;
        paramView = localGoods.getGoodsEndDate().split("-");
        localViewHolder.mer_time.setText(paramView[1] + "月" + paramView[2] + "日" + "前有效");
      }
      while (true)
      {
        localViewHolder.tv_goods_name.setText(localGoods.getGoodsName());
        return localView;
        ((ImageView)localView.findViewById(2131231368)).setImageResource(2130837838);
        ((ImageView)localView.findViewById(2131231368)).setOnClickListener(new ScanInStoreGoodsActivity.onClickListener(ScanInStoreGoodsActivity.this, paramInt));
        break;
        label632: System.out.println("--->有效期为空");
      }
    }

    class ViewHolder
    {
      FrameLayout goods_frame;
      ScaleImageView imageView;
      ImageView iv_scan;
      LinearLayout layout_scanproduct;
      ImageView mer_collect;
      ImageView mer_scan;
      ImageView mer_share;
      TextView mer_time;
      View progressBar;
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
      switch (paramView.getId())
      {
      default:
        return;
      case 2131231134:
      case 2131231480:
        paramView = BaseActivity.loginUser;
        paramView = new Intent(ScanInStoreGoodsActivity.this, MerChandiseDetailActivity.class);
        paramView.putExtra("store", ScanInStoreGoodsActivity.this.store);
        paramView.putExtra("goods", (Serializable)ScanInStoreGoodsActivity.access$1(ScanInStoreGoodsActivity.this).mInfos.get(this.position));
        paramView.putExtra("position", this.position);
        paramView.putExtra("goodsType", 2);
        ScanInStoreGoodsActivity.this.startActivity(paramView);
        return;
      case 2131230948:
      case 2131231216:
      case 2131231368:
      }
      paramView = AndroidApplication.getInstance().getCurrentLocation();
      if (paramView != null)
      {
        if (paramView.getScanShopId() == ScanInStoreGoodsActivity.this.shopId)
        {
          paramView = new Intent();
          paramView.setClass(ScanInStoreGoodsActivity.this, CaptureActivity.class);
          ScanInStoreGoodsActivity.this.startActivity(paramView);
          return;
        }
        ScanInStoreGoodsActivity.this.toastDefine.setMessage("您不在扫描范围内哦~");
        ScanInStoreGoodsActivity.this.toastDefine.show();
        return;
      }
      ScanInStoreGoodsActivity.this.toastDefine.setMessage("您不在扫描范围内哦~");
      ScanInStoreGoodsActivity.this.toastDefine.show();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ScanInStoreGoodsActivity
 * JD-Core Version:    0.6.2
 */