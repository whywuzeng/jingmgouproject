package com.ismartgo.app.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ismartgo.app.adapter.ReceiptMonthAdapter;
import com.ismartgo.app.adapter.ReceiptStatisticAdapter;
import com.ismartgo.app.bean.ReceiptMode;
import com.ismartgo.app.bean.ReceiptStatisticInfo;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.DisplayUtil;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyReceiptStatisticActivity extends BaseActivity
  implements View.OnClickListener
{
  private ReceiptStatisticAdapter adapter;
  private String currentDate;
  private MyDialog dialog;
  private LinearLayout emptyLayout;
  private Gallery gallery;
  private View head;
  private ReceiptStatisticInfo infos;
  private boolean isFirst = true;
  private ImageView ivBack;
  private ImageView ivIndicator;
  private ListView lvReceiptStatistic;
  private ReceiptMonthAdapter monthAdapter;
  private List<HashMap<String, String>> months;
  private List<ReceiptMode> receiptModeList;
  private TextView tvMoney;

  private void getData(String paramString)
  {
    HashMap localHashMap = new HashMap();
    if (BaseActivity.loginUser == null);
    for (String str = "0"; ; str = BaseActivity.loginUser.getId())
    {
      localHashMap.put("uid", str);
      localHashMap.put("month", paramString);
      HttpRequest.getInstance().executePostStringRequest(this, SharedPreferenceUtil.getReceiptSvUrl(this) + Url.MY_RECEIPT_STATISTIC, HttpWhat.MY_RECEIPT_STATISTIC, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          MyReceiptStatisticActivity.this.dialog.dismiss();
          MyReceiptStatisticActivity.this.hideLayout();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          Logger.i("小票统计数据： " + (String)paramAnonymousResponse.get());
          MyReceiptStatisticActivity.this.dialog.dismiss();
          MyReceiptStatisticActivity.this.infos = HttpJsonParse.getReceiptStatistic(((String)paramAnonymousResponse.get()).toString());
          if ((MyReceiptStatisticActivity.this.infos != null) && (MyReceiptStatisticActivity.this.infos.getItems() != null))
          {
            if (MyReceiptStatisticActivity.this.emptyLayout.getVisibility() == 0)
            {
              MyReceiptStatisticActivity.this.emptyLayout.setVisibility(8);
              MyReceiptStatisticActivity.this.ivIndicator.setBackgroundDrawable(MyReceiptStatisticActivity.this.getResources().getDrawable(2130837575));
            }
            if (MyReceiptStatisticActivity.this.lvReceiptStatistic.getVisibility() == 8)
              MyReceiptStatisticActivity.this.lvReceiptStatistic.setVisibility(0);
            MyReceiptStatisticActivity.this.tvMoney.setText(MyReceiptStatisticActivity.this.infos.getTotalmoney());
            MyReceiptStatisticActivity.this.adapter.setInfos(MyReceiptStatisticActivity.this.infos, MyReceiptStatisticActivity.this.receiptModeList);
            MyReceiptStatisticActivity.this.lvReceiptStatistic.setAdapter(MyReceiptStatisticActivity.this.adapter);
            return;
          }
          MyReceiptStatisticActivity.this.hideLayout();
        }
      });
      return;
    }
  }

  private void getDataList()
  {
    Calendar localCalendar = Calendar.getInstance();
    this.currentDate = new SimpleDateFormat("yyyy-MM").format(localCalendar.getTime());
    try
    {
      if (this.currentDate != null)
      {
        this.months = getMonthBetween("2016-01", this.currentDate);
        this.gallery.setAdapter(this.monthAdapter);
        this.monthAdapter.setDateList(this.months);
        this.gallery.setSelection(this.months.size() - 1);
      }
      return;
    }
    catch (android.net.ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
  }

  private List<HashMap<String, String>> getMonthBetween(String paramString1, String paramString2)
    throws android.net.ParseException
  {
    ArrayList localArrayList = new ArrayList();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM");
    Calendar localCalendar1 = Calendar.getInstance();
    Calendar localCalendar2 = Calendar.getInstance();
    try
    {
      localCalendar1.setTime(localSimpleDateFormat.parse(paramString1));
      localCalendar1.set(localCalendar1.get(1), localCalendar1.get(2), 1);
      localCalendar2.setTime(localSimpleDateFormat.parse(paramString2));
      localCalendar2.set(localCalendar2.get(1), localCalendar2.get(2), 2);
      if (!localCalendar1.before(localCalendar2))
        return localArrayList;
    }
    catch (java.text.ParseException paramString1)
    {
      while (true)
        paramString1.printStackTrace();
      paramString1 = new HashMap();
      paramString1.put("year", localCalendar1.get(1));
      if (localCalendar1.get(2) >= 9)
        break label199;
    }
    paramString1.put("month", "0" + (localCalendar1.get(2) + 1));
    while (true)
    {
      localArrayList.add(paramString1);
      localCalendar1.add(2, 1);
      break;
      label199: paramString1.put("month", localCalendar1.get(2) + 1);
    }
  }

  private void hideLayout()
  {
    this.ivIndicator.setBackgroundDrawable(getResources().getDrawable(2130837576));
    this.lvReceiptStatistic.setVisibility(8);
    this.emptyLayout.setVisibility(0);
  }

  private void initClickListener()
  {
    this.ivBack.setOnClickListener(this);
  }

  private void initData(final String paramString)
  {
    if ((SharedPreferenceUtil.getReceiptShopFlag(this) == 0) || (SharedPreferenceUtil.getReceiptShopType(this).equals("")))
      HttpRequest.getInstance().executePostStringRequest(this, SharedPreferenceUtil.getReceiptSvUrl(this) + Url.MY_RECEIPT_SHOP_TYPE, HttpWhat.MY_SHOP_SORT, null, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          MyReceiptStatisticActivity.this.dialog.dismiss();
          MyReceiptStatisticActivity.this.hideLayout();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          MyReceiptStatisticActivity.this.receiptModeList = HttpJsonParse.jsonParse_receiptMode(((String)paramAnonymousResponse.get()).toString());
          if (MyReceiptStatisticActivity.this.receiptModeList != null)
          {
            SharedPreferenceUtil.setReceiptShopType(MyReceiptStatisticActivity.this, ((String)paramAnonymousResponse.get()).toString());
            SharedPreferenceUtil.setReceiptShopFlag(MyReceiptStatisticActivity.this, 1);
            MyReceiptStatisticActivity.this.getData(paramString);
          }
        }
      });
    do
    {
      return;
      this.receiptModeList = HttpJsonParse.jsonParse_receiptMode(SharedPreferenceUtil.getReceiptShopType(this));
    }
    while (this.receiptModeList == null);
    Log.d("smartgo", "使用缓存: " + paramString);
    getData(paramString);
  }

  private void initHeadData()
  {
    this.months = new ArrayList();
    this.monthAdapter = new ReceiptMonthAdapter(this.months, this);
    this.gallery = ((Gallery)findViewById(2131231022));
    this.gallery.setCallbackDuringFling(false);
    this.gallery.setSpacing(DisplayUtil.dip2px(this, 25.0F));
    this.gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        ((TextView)paramAnonymousView).setTextColor(Color.parseColor("#ffffff"));
        MyReceiptStatisticActivity.this.dialog.show();
        MyReceiptStatisticActivity.this.initData((String)((HashMap)MyReceiptStatisticActivity.this.months.get(paramAnonymousInt)).get("year") + (String)((HashMap)MyReceiptStatisticActivity.this.months.get(paramAnonymousInt)).get("month"));
      }

      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
      {
      }
    });
    getDataList();
  }

  private void initView()
  {
    applyKitKatTranslucency();
    this.ivBack = ((ImageView)findViewById(2131231020));
    this.lvReceiptStatistic = ((ListView)findViewById(2131231024));
    this.emptyLayout = ((LinearLayout)findViewById(2131231025));
    this.ivIndicator = ((ImageView)findViewById(2131231023));
    this.head = LayoutInflater.from(this).inflate(2130903153, null);
    this.head.setBackgroundColor(getResources().getColor(2131099715));
    this.tvMoney = ((TextView)this.head.findViewById(2131231194));
    this.receiptModeList = new ArrayList();
    this.infos = new ReceiptStatisticInfo();
    this.adapter = new ReceiptStatisticAdapter(this, this.infos);
    this.lvReceiptStatistic.addHeaderView(this.head);
    this.lvReceiptStatistic.setAdapter(this.adapter);
    this.dialog = new MyDialog(this);
    this.dialog.setCancelable(true);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231020:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903084);
    initView();
    initHeadData();
    initClickListener();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MyReceiptStatisticActivity
 * JD-Core Version:    0.6.2
 */