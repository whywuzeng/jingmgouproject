package com.ismartgo.app.activity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.ismartgo.app.adapter.LogisticsAdapter;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.Logistics;
import com.ismartgo.app.bean.Logistics.DataEntity;
import com.ismartgo.app.bean.Logistics.DataEntity.OrderTrackEntity;
import com.ismartgo.app.grid.utils.DisplayUtil;
import com.ismartgo.app.grid.view.BorderImageView;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpRequestParam;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.tools.TimeUtils;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MyExchangeDetailActivity extends BaseActivity
  implements HttpCallback<String>, View.OnClickListener
{
  private Gift gift;
  private RelativeLayout giftDetail;
  ImageView img1;
  ImageView img2;
  ImageView img3;
  private ImageView imgArrow;
  private ImageView imgGiftArrow;
  private BorderImageView imgLogo;
  RelativeLayout layout1;
  LinearLayout layout2;
  private RelativeLayout layoutLogisticsDetail;
  private LinearLayout layoutPersonInfo;
  private Logistics logistics;
  private ListView logisticsListView;
  private LogisticsAdapter mLogisticsAdapter;
  private List<Logistics.DataEntity.OrderTrackEntity> orderTrackEntityList;
  TextView tv1;
  int tv1Width;
  TextView tv2;
  TextView tv3;
  int tv3Width;
  private TextView tvAddress;
  private TextView tvBeansNum;
  private TextView tvDate;
  private TextView tvDescription;
  private TextView tvName;
  private TextView tvNo;
  private TextView tvNoLogistics;
  private TextView tvNum;
  private TextView tvOrderCancelReason;
  private TextView tvPhone;
  private TextView tvReceiver;
  private TextView tvState;
  private View viewLogisticsMap;
  private View viewOrderCancel;

  private void initView()
  {
    this.imgLogo = ((BorderImageView)findViewById(2131230877));
    this.imgLogo.setBorderWidth(1.5F);
    this.imgLogo.setColour(getResources().getColor(2131099698));
    this.tvNo = ((TextView)findViewById(2131230977));
    this.tvDate = ((TextView)findViewById(2131230923));
    this.tvBeansNum = ((TextView)findViewById(2131230783));
    this.tvName = ((TextView)findViewById(2131230878));
    this.tvNum = ((TextView)findViewById(2131230972));
    ImgLoader.getInstance(this).showPic(StringUtils.getImgUrl(this.gift.getGiftLogo()), this.imgLogo, false);
    this.tvNo.append(String.valueOf(this.gift.getGiftNumber()));
    this.tvDate.append(this.gift.getDate().substring(0, this.gift.getDate().lastIndexOf(":")));
    this.tvBeansNum.append(String.valueOf(this.gift.getRequiredBean()) + "豆");
    this.tvName.setText(String.valueOf(this.gift.getGiftName()));
    this.tvNum.append(String.valueOf(this.gift.getCount()));
    this.tvReceiver = ((TextView)findViewById(2131230975));
    this.tvPhone = ((TextView)findViewById(2131230862));
    this.tvAddress = ((TextView)findViewById(2131230976));
    this.tvReceiver.setText(this.gift.getReceiver());
    this.tvPhone.setText(this.gift.getPhoneNum());
    this.tvAddress.setText(this.gift.getAddress());
    this.layoutPersonInfo = ((LinearLayout)findViewById(2131230974));
    this.imgArrow = ((ImageView)findViewById(2131230811));
    this.imgGiftArrow = ((ImageView)findViewById(2131230973));
    this.layoutLogisticsDetail = ((RelativeLayout)findViewById(2131231256));
    this.layoutLogisticsDetail.setOnClickListener(this);
    this.giftDetail = ((RelativeLayout)findViewById(2131230971));
    this.giftDetail.setOnClickListener(this);
    this.tvOrderCancelReason = ((TextView)findViewById(2131231262));
    this.viewLogisticsMap = findViewById(2131230969);
    this.viewOrderCancel = findViewById(2131230970);
    if ((this.gift != null) && (this.gift.getGiftType().equals("1")))
    {
      this.layoutPersonInfo.setVisibility(0);
      this.tvState = ((TextView)findViewById(2131230979));
      this.tvState.setText(this.gift.getFlag());
      this.tvDescription = ((TextView)findViewById(2131231255));
      this.layout1 = ((RelativeLayout)findViewById(2131231247));
      this.layout2 = ((LinearLayout)findViewById(2131231251));
      this.tv1 = ((TextView)findViewById(2131231248));
      this.tv2 = ((TextView)findViewById(2131231249));
      this.tv3 = ((TextView)findViewById(2131231250));
      this.img1 = ((ImageView)findViewById(2131231252));
      this.img2 = ((ImageView)findViewById(2131231253));
      this.img3 = ((ImageView)findViewById(2131231254));
      if (this.gift != null)
      {
        if (!this.gift.getFlag().equals("已下单"))
          break label776;
        this.tv1.setTextSize(19.0F);
        this.img1.setBackgroundResource(2130837529);
        this.tvDescription.setText(2131296404);
      }
    }
    label776: label904: 
    do
      while (true)
      {
        this.tv1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
          public void onGlobalLayout()
          {
            MyExchangeDetailActivity.this.tv1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MyExchangeDetailActivity.this.tv1Width = MyExchangeDetailActivity.this.tv1.getMeasuredWidth();
            MyExchangeDetailActivity.this.tv3.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MyExchangeDetailActivity.this.tv3Width = MyExchangeDetailActivity.this.tv3.getMeasuredWidth();
            System.out.println("tv1Width: " + MyExchangeDetailActivity.this.tv1Width + " tv3Width: " + MyExchangeDetailActivity.this.tv3Width);
            int i = MyExchangeDetailActivity.this.img1.getLeft() - MyExchangeDetailActivity.this.tv1Width / 2 + MyExchangeDetailActivity.this.img1.getMeasuredWidth() / 2;
            int j = DisplayUtil.getScreenWidth(MyExchangeDetailActivity.this) - (MyExchangeDetailActivity.this.img3.getLeft() + MyExchangeDetailActivity.this.tv3Width / 2 + MyExchangeDetailActivity.this.img3.getMeasuredWidth() / 2);
            System.out.println("left: " + i + " right: " + j + "  laytoutRight " + MyExchangeDetailActivity.this.layout2.getPaddingRight() + " img3 width: " + MyExchangeDetailActivity.this.img3.getMeasuredWidth() / 2);
            MyExchangeDetailActivity.this.layout1.setPadding(i, MyExchangeDetailActivity.this.layout1.getPaddingTop(), j, MyExchangeDetailActivity.this.layout1.getPaddingBottom());
          }
        });
        this.tvNoLogistics = ((TextView)findViewById(2131231257));
        System.out.println("number: " + this.gift.getLogisticsNumber());
        if (!TextUtils.isEmpty(this.gift.getLogisticsNumber()))
          break label977;
        this.tvNoLogistics.setVisibility(0);
        this.imgArrow.setVisibility(8);
        return;
        this.layoutPersonInfo.setVisibility(8);
        break;
        if (this.gift.getFlag().equals("待发货"))
        {
          this.tv2.setTextSize(19.0F);
          this.img1.setBackgroundResource(2130837529);
          this.img2.setBackgroundResource(2130837529);
          this.tvDescription.setText(2131296405);
        }
        else
        {
          if (!this.gift.getFlag().equals("已发货"))
            break label904;
          this.tv3.setTextSize(19.0F);
          this.img1.setBackgroundResource(2130837529);
          this.img2.setBackgroundResource(2130837529);
          this.img3.setBackgroundResource(2130837529);
          this.tvDescription.setText(2131296406);
        }
      }
    while (!this.gift.getFlag().equals("已取消"));
    this.viewOrderCancel.setVisibility(0);
    this.viewLogisticsMap.setVisibility(8);
    this.imgArrow.setVisibility(8);
    this.tvOrderCancelReason.setText("取消原因： " + this.gift.getChannelReason());
    return;
    label977: this.tvNoLogistics.setText("正在查询物流");
    this.logisticsListView = ((ListView)findViewById(2131230941));
    this.mLogisticsAdapter = new LogisticsAdapter(this, this.orderTrackEntityList);
    this.logisticsListView.setAdapter(this.mLogisticsAdapter);
    requestLogisticsInfo(this.gift.getGiftNumber(), this.gift.getLogisticsNumber(), this.gift.getLogisticsCompany());
  }

  private void requestLogisticsInfo(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = HttpRequestParam.lookOverLogistics(paramString1, paramString2, paramString3);
    HttpRequest.getInstance().executePostStringRequest(this, Url.LOOK_OVER_LOGISTICS, HttpWhat.LOOK_OVER_LOGISTICS, paramString1, this);
  }

  private void setAdapter(String paramString)
  {
    this.logistics = HttpJsonParse.logisticsJSONParse(paramString);
    int i;
    if (this.logistics != null)
    {
      if (this.logistics.getStatus() != 10001)
        break label273;
      this.logisticsListView.setVisibility(0);
      this.tvNoLogistics.setVisibility(8);
      i = this.logistics.getData().getOrderTrack().size();
      if (i > 4)
      {
        paramString = (RelativeLayout.LayoutParams)this.logisticsListView.getLayoutParams();
        paramString.height = DisplayUtil.dip2px(this, 232.0F);
        this.logisticsListView.setLayoutParams(paramString);
        this.orderTrackEntityList = new ArrayList();
        paramString = this.logistics.getData().getOrderTrack();
        i = 0;
      }
    }
    else
    {
      while (true)
      {
        if (i >= 4)
        {
          this.mLogisticsAdapter.setData(this.orderTrackEntityList);
          return;
        }
        this.orderTrackEntityList.add((Logistics.DataEntity.OrderTrackEntity)paramString.get(i));
        i += 1;
      }
    }
    paramString = (RelativeLayout.LayoutParams)this.logisticsListView.getLayoutParams();
    if (i == 1)
      paramString.height = DisplayUtil.dip2px(this, 58.0F);
    while (true)
    {
      this.logisticsListView.setLayoutParams(paramString);
      this.orderTrackEntityList = this.logistics.getData().getOrderTrack();
      break;
      if (i == 2)
        paramString.height = DisplayUtil.dip2px(this, 116.0F);
      else if (i == 3)
        paramString.height = DisplayUtil.dip2px(this, 174.0F);
      else if (i >= 4)
        paramString.height = DisplayUtil.dip2px(this, 232.0F);
    }
    label273: this.tvNoLogistics.setText(this.logistics.getMsg());
  }

  public void CopyNum(View paramView)
  {
    if ((this.gift == null) || (TextUtils.isEmpty(this.gift.getGiftNumber())))
      return;
    ((ClipboardManager)getSystemService("clipboard")).setText(String.valueOf(this.gift.getGiftNumber()));
    this.toast.setMessage("复制成功");
    this.toast.show();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131231256:
    case 2131230971:
    }
    do
    {
      do
        return;
      while (this.logistics == null);
      paramView = new Intent();
      paramView.setClass(this, LogisticsDetailActivity.class);
      Bundle localBundle = new Bundle();
      localBundle.putSerializable("express", this.logistics);
      paramView.putExtra("express_bundle", localBundle);
      paramView.putExtra("express_name", this.gift.getLogisticsCompany());
      startActivity(paramView);
      return;
    }
    while (this.gift == null);
    paramView = new Intent(this, WebViewActivity.class);
    paramView.putExtra("gift", this.gift);
    startActivity(paramView);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903076);
    initTitleBar();
    this.tv_title.setText("兑换详情");
    this.gift = ((Gift)getIntent().getSerializableExtra("consumptionCoupon"));
    initView();
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    Log.i("Test", "message: " + paramCharSequence);
    Toast.makeText(this, paramCharSequence, 0).show();
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

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    if (paramResponse != null)
    {
      System.out.println("物流信息： " + (String)paramResponse.get());
      SharedPreferenceUtil.setLogisticsInfo(this, (String)paramResponse.get(), String.valueOf(this.gift.getGiftId()));
      SharedPreferenceUtil.setLogisticsExpire(this, TimeUtils.getCurrentTimeAdapterTenMinute());
      setAdapter((String)paramResponse.get());
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MyExchangeDetailActivity
 * JD-Core Version:    0.6.2
 */