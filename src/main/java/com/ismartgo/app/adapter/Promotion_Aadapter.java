package com.ismartgo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.activity.HasSignedActivity;
import com.ismartgo.app.activity.HomeActivity;
import com.ismartgo.app.activity.IntoStoreSignInActivity;
import com.ismartgo.app.activity.LoginActivity;
import com.ismartgo.app.activity.MerChandiseDetailActivity;
import com.ismartgo.app.activity.PromotionActivity;
import com.ismartgo.app.activity.ScanInStoreGoodsActivity;
import com.ismartgo.app.activity.SearchStorePromotionActivity;
import com.ismartgo.app.activity.SignInActivity2;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.DisplayUtil;
import com.ismartgo.app.grid.utils.DistanceConversionUtils;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.Utils;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.map.BaiduMapActivity;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.share.ShareDialog;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.ismartgo.app.utils.UMengStatisticsUtils;
import com.yolanda.nohttp.Response;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.maxwin.view.XListViewRefresh;
import org.json.JSONObject;

public class Promotion_Aadapter extends BaseAdapter
{
  private PromotionActivity activity;
  AndroidApplication application;
  private boolean collectClick = true;
  Context context;
  LayoutInflater inflater;
  private List<Store> item_data;
  private MyDialog mDialog;
  private int managerWidth;
  private SearchStorePromotionActivity sActivity;
  int searchFlag = 0;
  String searchName = "";
  private String shopAddress = null;
  private int shopId;
  private boolean signClick;
  private ToastDefine toast;
  User user;

  public Promotion_Aadapter(Context paramContext, PromotionActivity paramPromotionActivity, List<Store> paramList, int paramInt)
  {
    this.context = paramContext;
    this.activity = paramPromotionActivity;
    this.item_data = paramList;
    this.managerWidth = paramInt;
    this.inflater = LayoutInflater.from(paramContext);
    this.toast = new ToastDefine(paramContext);
    this.application = ((AndroidApplication)paramContext.getApplicationContext());
    this.user = this.application.getUser();
    this.mDialog = new MyDialog(paramPromotionActivity);
  }

  public Promotion_Aadapter(Context paramContext, SearchStorePromotionActivity paramSearchStorePromotionActivity, List<Store> paramList, int paramInt1, int paramInt2, String paramString)
  {
    this.searchFlag = paramInt2;
    this.context = paramContext;
    this.sActivity = paramSearchStorePromotionActivity;
    this.item_data = paramList;
    this.managerWidth = paramInt1;
    this.searchName = paramString;
    this.inflater = LayoutInflater.from(paramContext);
    this.toast = new ToastDefine(paramContext);
    this.application = ((AndroidApplication)paramContext.getApplicationContext());
    this.user = this.application.getUser();
    this.mDialog = new MyDialog(paramSearchStorePromotionActivity);
  }

  private void toDetail(int paramInt1, int paramInt2)
  {
    int i = ((Goods)((Store)this.item_data.get(paramInt1)).getGoods_list().get(paramInt2)).getGoodsTag();
    Goods localGoods = (Goods)((Store)this.item_data.get(paramInt1)).getGoods_list().get(i);
    UMengStatisticsUtils.promotionMsgDetail(this.context);
    Intent localIntent = new Intent(this.context, MerChandiseDetailActivity.class);
    localIntent.putExtra("store", (Serializable)this.item_data.get(paramInt1));
    localIntent.putExtra("goods", localGoods);
    localIntent.putExtra("position", paramInt1);
    localIntent.putExtra("i", paramInt2);
    this.context.startActivity(localIntent);
  }

  public void clickCollect(final int paramInt1, final int paramInt2, final ImageView paramImageView)
  {
    this.collectClick = false;
    paramInt1 = ((Goods)((Store)this.item_data.get(paramInt2)).getGoods_list().get(paramInt1)).getGoodsTag();
    final Goods localGoods = (Goods)((Store)this.item_data.get(paramInt2)).getGoods_list().get(paramInt1);
    this.toast = new ToastDefine(this.context);
    if (this.activity == null);
    User localUser;
    for (Object localObject = this.sActivity; ; localObject = this.activity)
    {
      localUser = ((AndroidApplication)((BaseActivity)localObject).getApplication()).getUser();
      if ((localUser != null) && (localUser.getLoginType() != 6))
        break;
      this.collectClick = true;
      paramImageView = new Intent(this.context, LoginActivity.class);
      paramImageView.putExtra("goodsid", localGoods.getGoodsId());
      paramImageView.putExtra("shopid", ((Store)this.item_data.get(paramInt2)).getShopId());
      this.context.startActivity(paramImageView);
      if ((this.mDialog != null) && (this.mDialog.isShowing()))
        this.mDialog.dismiss();
      return;
    }
    if (this.mDialog == null)
    {
      this.mDialog = new MyDialog(this.context);
      this.mDialog.show();
    }
    final boolean bool = localGoods.isCollect();
    if (bool);
    for (localObject = Url.DEL_COLLECT_URL; ; localObject = Url.COLLECT_GOODS_URL)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("uid", localUser.getId());
      localHashMap.put("goodsid", localGoods.getGoodsId());
      localHashMap.put("shopid", ((Store)this.item_data.get(paramInt2)).getShopId());
      HttpRequest.getInstance().executePostStringRequest(this.context, (String)localObject, HttpWhat.COLLECT_GOODS, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          Promotion_Aadapter.this.toast.setMessage("亲，网络好像出问题了哦~");
          Promotion_Aadapter.this.toast.show();
          Promotion_Aadapter.this.collectClick = true;
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          Promotion_Aadapter.this.collectClick = true;
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            paramAnonymousInt = Integer.valueOf(paramAnonymousResponse.getString("status")).intValue();
            Promotion_Aadapter.this.toast = new ToastDefine(Promotion_Aadapter.this.context);
            if (paramAnonymousInt == ResultCode.RESULT_OK)
            {
              HomeActivity.isChanged = true;
              HomeActivity.isNeedRefresh = true;
              if (HomeActivity.isNeedRefreshGoodsIdList == null)
                HomeActivity.isNeedRefreshGoodsIdList = new ArrayList();
              paramAnonymousResponse = new HashMap();
              paramAnonymousResponse.put("store", Integer.valueOf(((Store)Promotion_Aadapter.this.item_data.get(paramInt2)).getShopId()));
              paramAnonymousResponse.put("goods", Integer.valueOf(localGoods.getGoodsId()));
              Promotion_Aadapter.this.mDialog.dismiss();
              Promotion_Aadapter.this.toast.setDisplay(true);
              Promotion_Aadapter.this.toast.setLayoutParams(150, 50);
              if (bool)
              {
                Promotion_Aadapter.this.toast.setImageResource(false);
                Promotion_Aadapter.this.toast.setMessage("已取消");
                ((Goods)((Store)Promotion_Aadapter.this.item_data.get(paramInt2)).getGoods_list().get(paramInt1)).setCollect(false);
                paramImageView.setImageResource(2130837619);
                paramAnonymousResponse.put("isCollected", Integer.valueOf(2));
              }
              while (true)
              {
                Promotion_Aadapter.this.toast.show();
                HomeActivity.isNeedRefreshGoodsIdList.add(paramAnonymousResponse);
                return;
                Promotion_Aadapter.this.toast.setImageResource(true);
                Promotion_Aadapter.this.toast.setMessage("已收藏");
                ((Goods)((Store)Promotion_Aadapter.this.item_data.get(paramInt2)).getGoods_list().get(paramInt1)).setCollect(true);
                paramImageView.setImageResource(2130837620);
                paramAnonymousResponse.put("isCollected", Integer.valueOf(1));
              }
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            Promotion_Aadapter.this.mDialog.dismiss();
            paramAnonymousResponse.printStackTrace();
            return;
          }
          Promotion_Aadapter.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          Promotion_Aadapter.this.toast.show();
        }
      });
      return;
    }
  }

  public int getCount()
  {
    return this.item_data.size();
  }

  public Object getItem(int paramInt)
  {
    return this.item_data.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return ((Store)this.item_data.get(paramInt)).getShopId();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject1;
    label440: int i;
    if (paramView == null)
    {
      paramView = this.inflater.inflate(2130903188, null);
      paramViewGroup = new ViewHolder();
      paramViewGroup.store_logo = ((ImageView)paramView.findViewById(2131231296));
      paramViewGroup.store_name = ((TextView)paramView.findViewById(2131231297));
      paramViewGroup.store_address = ((TextView)paramView.findViewById(2131231298));
      paramViewGroup.store_distance = ((TextView)paramView.findViewById(2131231299));
      paramViewGroup.storepro_count = ((TextView)paramView.findViewById(2131231301));
      paramViewGroup.store_product_pic_1 = ((ImageView)paramView.findViewById(2131231304));
      paramViewGroup.store_product_pic_2 = ((ImageView)paramView.findViewById(2131231310));
      paramViewGroup.store_collect = ((ImageView)paramView.findViewById(2131231307));
      paramViewGroup.store_share = ((ImageView)paramView.findViewById(2131231308));
      paramViewGroup.store_collect_second = ((ImageView)paramView.findViewById(2131231313));
      paramViewGroup.store_share_second = ((ImageView)paramView.findViewById(2131231314));
      paramViewGroup.mer_scan_1 = ((ImageView)paramView.findViewById(2131231305));
      paramViewGroup.mer_scan_2 = ((ImageView)paramView.findViewById(2131231311));
      paramViewGroup.mer_time_1 = ((TextView)paramView.findViewById(2131231306));
      paramViewGroup.mer_time_2 = ((TextView)paramView.findViewById(2131231312));
      paramViewGroup.scan_more = ((TextView)paramView.findViewById(2131231302));
      paramViewGroup.store_signin = ((ImageView)paramView.findViewById(2131231300));
      paramViewGroup.layoutStore = ((LinearLayout)paramView.findViewById(2131231294));
      paramViewGroup.pro_merchandise = ((LinearLayout)paramView.findViewById(2131231303));
      paramViewGroup.pro_merchandise_second = ((LinearLayout)paramView.findViewById(2131231309));
      paramView.setTag(paramViewGroup);
      if (Utils.itemWidth == 0)
        Utils.itemWidth = DisplayUtil.getItemWidth(this.context);
      localObject1 = (LinearLayout.LayoutParams)paramViewGroup.pro_merchandise.getLayoutParams();
      ((LinearLayout.LayoutParams)localObject1).height = Utils.itemWidth;
      paramViewGroup.pro_merchandise.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      paramViewGroup.pro_merchandise_second.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      ImgLoader.getInstance(this.context).showPic(((Store)this.item_data.get(paramInt)).getShopLogo(), paramViewGroup.store_logo, false);
      paramViewGroup.store_logo.setOnClickListener(new onClickListener(paramInt));
      localObject2 = paramViewGroup.store_name;
      if (((Store)this.item_data.get(paramInt)).getShopName() != null)
        break label715;
      localObject1 = "";
      ((TextView)localObject2).setText((CharSequence)localObject1);
      this.shopAddress = ((Store)this.item_data.get(paramInt)).getShopAddress().toString();
      paramViewGroup.store_address.setText(this.shopAddress);
      paramViewGroup.store_distance.setText(DistanceConversionUtils.getDistance(((Store)this.item_data.get(paramInt)).getDistance()));
      paramViewGroup.store_distance.setOnClickListener(new onClickListener(paramInt));
      localObject2 = paramViewGroup.storepro_count;
      StringBuilder localStringBuilder = new StringBuilder("共有");
      if (((Store)this.item_data.get(paramInt)).getSaleCount() != 0)
        break label736;
      localObject1 = "0";
      label565: ((TextView)localObject2).setText(localObject1 + "款促销");
      if (((Store)this.item_data.get(paramInt)).getShopId() != 0)
        break label760;
      i = 0;
      label608: if (!((Store)this.item_data.get(paramInt)).getIsSign().equals("1"))
        break label781;
      paramViewGroup.store_signin.setVisibility(0);
      paramViewGroup.store_signin.setOnClickListener(new onClickListener(paramInt));
    }
    int j;
    while (true)
    {
      j = 0;
      if (j < ((Store)this.item_data.get(paramInt)).getGoods_list().size())
        break label792;
      paramViewGroup.scan_more.setOnClickListener(new onClickListener(paramInt));
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
      label715: localObject1 = ((Store)this.item_data.get(paramInt)).getShopName();
      break label440;
      label736: localObject1 = Integer.valueOf(((Store)this.item_data.get(paramInt)).getSaleCount());
      break label565;
      label760: i = ((Store)this.item_data.get(paramInt)).getShopId();
      break label608;
      label781: paramViewGroup.store_signin.setVisibility(4);
    }
    label792: label859: int k;
    if (i == ((Goods)((Store)this.item_data.get(paramInt)).getGoods_list().get(j)).getShopId())
    {
      if (((Store)this.item_data.get(paramInt)).getGoods_list().size() != 1)
        break label1129;
      paramViewGroup.pro_merchandise_second.setVisibility(4);
      k = ((Goods)((Store)this.item_data.get(paramInt)).getGoods_list().get(j)).getGoodsTag();
      localObject1 = (Goods)((Store)this.item_data.get(paramInt)).getGoods_list().get(k);
      if (k >= 1)
        break label1193;
      ImgLoader.getInstance(this.context).showPic(((Goods)localObject1).getGoodsLogo(), paramViewGroup.store_product_pic_1, false);
      localObject2 = paramViewGroup.mer_scan_1;
      if (((Goods)localObject1).getGoodsScan() != 1)
        break label1140;
      k = 0;
      label962: ((ImageView)localObject2).setVisibility(k);
      if (((Goods)localObject1).getGoodsEndDate().equals(""))
        break label1146;
      localObject2 = ((Goods)localObject1).getGoodsEndDate().split("-");
      paramViewGroup.mer_time_1.setText(localObject2[1] + "月" + localObject2[2] + "日" + "前有效");
      label1044: if (!((Goods)localObject1).isCollect())
        break label1180;
      paramViewGroup.store_collect.setImageResource(2130837620);
    }
    while (true)
    {
      paramViewGroup.store_collect.setOnClickListener(new onClickListener(j, paramInt, paramViewGroup.store_collect));
      paramViewGroup.store_product_pic_1.setOnClickListener(new onClickListener(j, paramInt));
      paramViewGroup.store_share.setOnClickListener(new onClickListener(j, paramInt));
      j += 1;
      break;
      label1129: paramViewGroup.pro_merchandise_second.setVisibility(0);
      break label859;
      label1140: k = 4;
      break label962;
      label1146: paramViewGroup.mer_time_1.setText(((Goods)localObject1).getGoodsEndDate() + "前有效");
      break label1044;
      label1180: paramViewGroup.store_collect.setImageResource(2130837619);
    }
    label1193: ImgLoader.getInstance(this.context).showPic(((Goods)localObject1).getGoodsLogo(), paramViewGroup.store_product_pic_2, false);
    Object localObject2 = paramViewGroup.mer_scan_2;
    if (((Goods)localObject1).getGoodsScan() == 1)
    {
      k = 0;
      label1231: ((ImageView)localObject2).setVisibility(k);
      if (((Goods)localObject1).getGoodsEndDate().equals(""))
        break label1398;
      localObject2 = ((Goods)localObject1).getGoodsEndDate().split("-");
      paramViewGroup.mer_time_2.setText(localObject2[1] + "月" + localObject2[2] + "日" + "前有效");
      label1313: if (!((Goods)localObject1).isCollect())
        break label1432;
      paramViewGroup.store_collect_second.setImageResource(2130837620);
    }
    while (true)
    {
      paramViewGroup.store_collect_second.setOnClickListener(new onClickListener(j, paramInt, paramViewGroup.store_collect_second));
      paramViewGroup.store_product_pic_2.setOnClickListener(new onClickListener(j, paramInt));
      paramViewGroup.store_share_second.setOnClickListener(new onClickListener(j, paramInt));
      break;
      k = 4;
      break label1231;
      label1398: paramViewGroup.mer_time_2.setText(((Goods)localObject1).getGoodsEndDate() + "前有效");
      break label1313;
      label1432: paramViewGroup.store_collect_second.setImageResource(2130837619);
    }
  }

  public void setItem_data(List<Store> paramList)
  {
    this.item_data = paramList;
    notifyDataSetChanged();
  }

  public final class ViewHolder
  {
    private LinearLayout layoutStore;
    private ImageView mer_scan_1;
    private ImageView mer_scan_2;
    private TextView mer_time_1;
    private TextView mer_time_2;
    private LinearLayout pro_merchandise;
    private LinearLayout pro_merchandise_second;
    public TextView scan_more;
    public TextView store_address;
    private ImageView store_collect;
    private ImageView store_collect_second;
    public TextView store_distance;
    private ImageView store_logo;
    public TextView store_name;
    ImageView store_product_pic_1;
    ImageView store_product_pic_2;
    private ImageView store_share;
    private ImageView store_share_second;
    private ImageView store_signin;
    public ImageView store_singn;
    public TextView storepro_count;

    public ViewHolder()
    {
    }
  }

  class onClickListener
    implements View.OnClickListener
  {
    int i;
    ImageView img;
    int position;

    public onClickListener(int arg2)
    {
      int j;
      this.position = j;
    }

    public onClickListener(int paramInt1, int arg3)
    {
      this.i = paramInt1;
      int j;
      this.position = j;
    }

    public onClickListener(int paramInt1, int paramImageView, ImageView arg4)
    {
      this.i = paramInt1;
      this.position = paramImageView;
      Object localObject;
      this.img = localObject;
    }

    public void onClick(View paramView)
    {
      Object localObject2 = new ShareDialog(Promotion_Aadapter.this.context);
      Object localObject1 = (Store)Promotion_Aadapter.this.item_data.get(this.position);
      int j = ((Goods)((Store)localObject1).getGoods_list().get(this.i)).getGoodsTag();
      Goods localGoods = (Goods)((Store)Promotion_Aadapter.this.item_data.get(this.position)).getGoods_list().get(j);
      switch (paramView.getId())
      {
      case 2131231297:
      case 2131231298:
      case 2131231301:
      case 2131231303:
      case 2131231305:
      case 2131231306:
      case 2131231309:
      case 2131231311:
      case 2131231312:
      default:
      case 2131231299:
      case 2131231300:
      case 2131231304:
      case 2131231310:
      case 2131231307:
      case 2131231313:
        do
        {
          do
          {
            return;
            paramView = new Intent();
            paramView.setClass(Promotion_Aadapter.this.context.getApplicationContext(), BaiduMapActivity.class);
            paramView.putExtra("storeLon", ((Store)localObject1).getLon());
            paramView.putExtra("storeLat", ((Store)localObject1).getLat());
            paramView.putExtra("shopName", ((Store)localObject1).getShopName());
            paramView.putExtra("storeAddress", ((Store)localObject1).getShopAddress());
            paramView.setFlags(268435456);
            Promotion_Aadapter.this.context.getApplicationContext().startActivity(paramView);
            return;
            paramView = new Intent();
            localObject2 = AndroidApplication.getInstance().getCurrentLocation();
          }
          while (localObject2 == null);
          if (((Store)localObject1).getShopId() == ((CurrentLocationInfo)localObject2).getSignShopId())
          {
            if (((CurrentLocationInfo)localObject2).getSignShopId() > 0)
            {
              localObject1 = Calendar.getInstance();
              localObject1 = new SimpleDateFormat("yyyy-MM-dd").format(((Calendar)localObject1).getTime());
              if (((CurrentLocationInfo)localObject2).getLatestSignDate().equals(((String)localObject1).trim()))
              {
                paramView.putExtra("isInShop", true);
                paramView.putExtra("shopId", ((CurrentLocationInfo)localObject2).getSignShopId());
                paramView.putExtra("shopName", ((CurrentLocationInfo)localObject2).getSignShopName());
                paramView.setClass(Promotion_Aadapter.this.context, ScanInStoreGoodsActivity.class);
                paramView.setClass(Promotion_Aadapter.this.context, HasSignedActivity.class);
              }
            }
            while (true)
            {
              Promotion_Aadapter.this.context.startActivity(paramView);
              return;
              paramView.setClass(Promotion_Aadapter.this.context, SignInActivity2.class);
              continue;
              paramView.putExtra("isInShop", true);
              paramView.putExtra("shopId", ((CurrentLocationInfo)localObject2).getSignShopId());
              paramView.putExtra("shopName", ((CurrentLocationInfo)localObject2).getSignShopName());
              paramView.setClass(Promotion_Aadapter.this.context, ScanInStoreGoodsActivity.class);
            }
          }
          Promotion_Aadapter.this.context.startActivity(new Intent(Promotion_Aadapter.this.context, IntoStoreSignInActivity.class));
          return;
          Promotion_Aadapter.this.toDetail(this.position, this.i);
          return;
        }
        while (!Promotion_Aadapter.this.collectClick);
        Promotion_Aadapter.this.clickCollect(this.i, this.position, this.img);
        return;
      case 2131231308:
      case 2131231314:
        paramView = new ShareDialog(Promotion_Aadapter.this.context);
        ((ShareDialog)localObject2).setShare(localGoods.getGoodsLogo(), "【" + ((Store)localObject1).getReatilName() + "】 " + localGoods.getGoodsName(), localGoods.getGoodsDescribe(), localGoods.getGoodsId(), AndroidApplication.getInstance().getUser().getId(), 1);
        paramView.show();
        return;
      case 2131231296:
      case 2131231302:
      }
      if (Promotion_Aadapter.this.searchFlag == 0)
      {
        Promotion_Aadapter.this.activity.iv_back.setVisibility(0);
        Promotion_Aadapter.this.activity.tv_location.setVisibility(8);
        Promotion_Aadapter.this.activity.imgv_screen.setVisibility(8);
        Promotion_Aadapter.this.activity.imgv_msg.setVisibility(4);
        Promotion_Aadapter.this.activity.pro_display_info_layout.setVisibility(0);
        Promotion_Aadapter.this.activity.not_in_shop_layout.setVisibility(8);
        Promotion_Aadapter.this.activity.current_in_shop_layout.setVisibility(0);
        Promotion_Aadapter.this.activity.mAdapterView.setVisibility(8);
        Promotion_Aadapter.this.activity.search_enpty.setVisibility(8);
        Promotion_Aadapter.this.activity.setStore((Store)Promotion_Aadapter.this.item_data.get(this.position));
        Promotion_Aadapter.this.activity.setTemPauseData();
        Promotion_Aadapter.this.activity.diaplayDetail((Store)Promotion_Aadapter.this.item_data.get(this.position));
        Promotion_Aadapter.this.activity.back_sure = true;
        UMengStatisticsUtils.promotionMore(Promotion_Aadapter.this.context);
        return;
      }
      Promotion_Aadapter.this.sActivity.iv_back.setVisibility(0);
      Promotion_Aadapter.this.sActivity.tv_location.setVisibility(8);
      Promotion_Aadapter.this.sActivity.imgv_screen.setVisibility(8);
      Promotion_Aadapter.this.sActivity.imgv_msg.setVisibility(4);
      Promotion_Aadapter.this.sActivity.pro_display_info_layout.setVisibility(0);
      Promotion_Aadapter.this.sActivity.not_in_shop_layout.setVisibility(8);
      Promotion_Aadapter.this.sActivity.current_in_shop_layout.setVisibility(0);
      Promotion_Aadapter.this.sActivity.mAdapterView.setVisibility(8);
      Promotion_Aadapter.this.sActivity.search_enpty.setVisibility(8);
      Promotion_Aadapter.this.sActivity.setStore((Store)Promotion_Aadapter.this.item_data.get(this.position));
      Promotion_Aadapter.this.sActivity.setTemPauseData();
      Promotion_Aadapter.this.sActivity.diaplayDetail((Store)Promotion_Aadapter.this.item_data.get(this.position));
      paramView = Promotion_Aadapter.this.sActivity;
      paramView.back_count += 1;
      Promotion_Aadapter.this.sActivity.back_sure = true;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.Promotion_Aadapter
 * JD-Core Version:    0.6.2
 */