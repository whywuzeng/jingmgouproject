package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ismartgo.app.adapter.MyReceiptAdapter;
import com.ismartgo.app.bean.MyReceiptInfo;
import com.ismartgo.app.bean.NewReceiptInfo;
import com.ismartgo.app.bean.ReceiptDayInfo;
import com.ismartgo.app.bean.ReceiptItems;
import com.ismartgo.app.bean.ReceiptMonthInfo;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyReceiptListActivity extends BaseActivity
  implements View.OnClickListener, AbsListView.OnScrollListener
{
  public static final int RECEIPT_RESAULT_CODE = 1;
  private MyReceiptAdapter adapter;
  private Calendar calendar;
  private List<NewReceiptInfo> data;
  private MyDialog dialog;
  private View footView;
  private int footViewCount = 0;
  private MyReceiptInfo infos = new MyReceiptInfo();
  private boolean isLoading = false;
  private boolean isState = false;
  private ReceiptItems item;
  private ImageView ivBack;
  private ImageView ivCount;
  private ImageView ivTakePhoto;
  private LinearLayout layout;
  private ListView lvReceiptList;
  private List<ReceiptMonthInfo> monthinfos = new ArrayList();
  private RelativeLayout receiptList;
  private TextView tvCurrentDay;
  private TextView tvTimes;
  private TextView tvTotal;
  private TextView tvTotalCost;
  private TextView tvTotalCount;

  private void addFootView(List<NewReceiptInfo> paramList)
  {
    if ((paramList.size() < 20) && (this.footViewCount == 0))
    {
      this.footView.setVisibility(0);
      this.footViewCount += 1;
    }
  }

  private List<NewReceiptInfo> addNewDateInfo(List<NewReceiptInfo> paramList, ReceiptItems paramReceiptItems, boolean paramBoolean)
  {
    NewReceiptInfo localNewReceiptInfo = new NewReceiptInfo();
    Object localObject = paramReceiptItems.getDate().split("-");
    if ((paramBoolean) && (localObject.length == 3));
    for (localObject = localObject[1] + "月" + localObject[2] + "日"; ; localObject = localObject[2] + "日")
    {
      localObject = new ReceiptDayInfo((String)localObject, paramReceiptItems.getDaymoney(), paramReceiptItems.getMonth());
      localNewReceiptInfo.setType(1);
      localNewReceiptInfo.setFlag(0);
      localNewReceiptInfo.setDayInfo((ReceiptDayInfo)localObject);
      paramList.add(localNewReceiptInfo);
      localObject = new NewReceiptInfo();
      ((NewReceiptInfo)localObject).setFlag(2);
      ((NewReceiptInfo)localObject).setType(0);
      ((NewReceiptInfo)localObject).setItems(paramReceiptItems);
      paramList.add(localObject);
      return paramList;
    }
  }

  private void changeHeadData(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = CommonMethod.parseMonth(paramString1);
    this.tvTotalCost.setText(paramString1 + "购物花费");
    this.tvTotalCount.setText(paramString1 + "购物次数");
    this.tvTotal.setText(paramString2);
    Log.e("smartgo", paramString2);
    this.tvTimes.setText(paramString3);
  }

  private List<NewReceiptInfo> getNewSort(MyReceiptInfo paramMyReceiptInfo)
  {
    Object localObject = new ArrayList();
    List localList = paramMyReceiptInfo.getItems();
    paramMyReceiptInfo = (MyReceiptInfo)localObject;
    int i;
    if (localList != null)
    {
      paramMyReceiptInfo = (MyReceiptInfo)localObject;
      if (localList.size() > 0)
      {
        Collections.sort(localList, new Comparator()
        {
          public int compare(ReceiptItems paramAnonymousReceiptItems1, ReceiptItems paramAnonymousReceiptItems2)
          {
            int i = 1;
            long l1;
            long l2;
            do
            {
              try
              {
                l1 = new SimpleDateFormat("yyyy-MM-dd").parse(paramAnonymousReceiptItems1.getDate()).getTime();
                l2 = new SimpleDateFormat("yyyy-MM-dd").parse(paramAnonymousReceiptItems2.getDate()).getTime();
                if (l1 >= l2)
                  continue;
                return 1;
                int j = paramAnonymousReceiptItems1.getId();
                int k = paramAnonymousReceiptItems2.getId();
                if (j >= k)
                  return -1;
              }
              catch (ParseException paramAnonymousReceiptItems1)
              {
                paramAnonymousReceiptItems1.printStackTrace();
                i = 0;
              }
              return i;
            }
            while (l1 <= l2);
            return -1;
          }
        });
        i = 0;
        paramMyReceiptInfo = (MyReceiptInfo)localObject;
        if (i < localList.size())
          break label63;
      }
    }
    return paramMyReceiptInfo;
    label63: if (i == 0)
      paramMyReceiptInfo = addNewDateInfo(paramMyReceiptInfo, (ReceiptItems)localList.get(i), true);
    while (true)
    {
      i += 1;
      break;
      if (((ReceiptItems)localList.get(i)).getDate().equals(((ReceiptItems)localList.get(i - 1)).getDate()))
      {
        localObject = new NewReceiptInfo();
        ((NewReceiptInfo)localObject).setFlag(0);
        ((NewReceiptInfo)localObject).setType(0);
        ((NewReceiptInfo)localObject).setItems((ReceiptItems)localList.get(i));
        paramMyReceiptInfo.add(localObject);
      }
      else
      {
        if (((NewReceiptInfo)paramMyReceiptInfo.get(paramMyReceiptInfo.size() - 1)).getFlag() == 2)
          ((NewReceiptInfo)paramMyReceiptInfo.get(paramMyReceiptInfo.size() - 1)).setFlag(3);
        while (true)
        {
          if (!((ReceiptItems)localList.get(i)).getMonth().equals(((ReceiptItems)localList.get(i - 1)).getMonth()))
            break label298;
          paramMyReceiptInfo = addNewDateInfo(paramMyReceiptInfo, (ReceiptItems)localList.get(i), false);
          break;
          ((NewReceiptInfo)paramMyReceiptInfo.get(paramMyReceiptInfo.size() - 1)).setFlag(1);
        }
        label298: paramMyReceiptInfo = addNewDateInfo(paramMyReceiptInfo, (ReceiptItems)localList.get(i), true);
      }
    }
  }

  private void initClickListener()
  {
    this.ivCount.setOnClickListener(this);
    this.ivBack.setOnClickListener(this);
    this.ivTakePhoto.setOnClickListener(this);
    this.lvReceiptList.setOnScrollListener(this);
  }

  private void initData(int paramInt)
  {
    if (!this.isLoading)
      this.dialog.show();
    HashMap localHashMap = new HashMap();
    if (BaseActivity.loginUser == null);
    for (String str = "0"; ; str = BaseActivity.loginUser.getId())
    {
      localHashMap.put("uid", str);
      localHashMap.put("maxreceiptid", paramInt);
      HttpRequest.getInstance().executePostStringRequest(this, SharedPreferenceUtil.getReceiptSvUrl(this) + Url.MY_RECEIPT_LIST, HttpWhat.MY_RECEIPT_LIST, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          if (!MyReceiptListActivity.this.isLoading)
            MyReceiptListActivity.this.dialog.dismiss();
          if (MyReceiptListActivity.this.isLoading)
          {
            ToastDefine.makeText(MyReceiptListActivity.this, "网络加载出错！", 0).show();
            MyReceiptListActivity.this.isLoading = false;
            return;
          }
          MyReceiptListActivity.this.noDataLayout();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          Logger.i("我的小票列表： " + (String)paramAnonymousResponse.get());
          if (!MyReceiptListActivity.this.isLoading)
            MyReceiptListActivity.this.dialog.dismiss();
          MyReceiptListActivity.this.infos = HttpJsonParse.getReceiptInfo(((String)paramAnonymousResponse.get()).toString());
          if ((MyReceiptListActivity.this.infos != null) && (MyReceiptListActivity.this.infos.getItems() != null) && (MyReceiptListActivity.this.infos.getMonthinfos() != null))
            if (MyReceiptListActivity.this.isLoading)
            {
              MyReceiptListActivity.this.monthinfos.addAll(MyReceiptListActivity.this.infos.getMonthinfos());
              paramAnonymousResponse = MyReceiptListActivity.this.getNewSort(MyReceiptListActivity.this.infos);
              if (paramAnonymousResponse.size() > 0)
              {
                MyReceiptListActivity.this.loadNewListChange(paramAnonymousResponse);
                MyReceiptListActivity.this.addFootView(paramAnonymousResponse);
                MyReceiptListActivity.this.data.addAll(paramAnonymousResponse);
                MyReceiptListActivity.this.adapter.notifyDataSetChanged();
                MyReceiptListActivity.this.isLoading = false;
              }
            }
          do
          {
            return;
            MyReceiptListActivity.this.monthinfos = MyReceiptListActivity.this.infos.getMonthinfos();
            MyReceiptListActivity.this.data = MyReceiptListActivity.this.getNewSort(MyReceiptListActivity.this.infos);
            MyReceiptListActivity.this.addFootView(MyReceiptListActivity.this.data);
            MyReceiptListActivity.this.adapter.setInfos(MyReceiptListActivity.this.data);
            return;
            if (!MyReceiptListActivity.this.isLoading)
            {
              MyReceiptListActivity.this.noDataLayout();
              return;
            }
          }
          while (MyReceiptListActivity.this.footViewCount != 0);
          MyReceiptListActivity.this.footView.setVisibility(0);
          paramAnonymousResponse = MyReceiptListActivity.this;
          paramAnonymousResponse.footViewCount += 1;
        }
      });
      return;
    }
  }

  private void initView()
  {
    initTitleBar();
    this.lvReceiptList = ((ListView)findViewById(2131230988));
    this.receiptList = ((RelativeLayout)findViewById(2131230987));
    this.layout = ((LinearLayout)findViewById(2131230989));
    this.tvCurrentDay = ((TextView)this.layout.findViewById(2131230991));
    this.calendar = Calendar.getInstance();
    this.tvTotalCost = ((TextView)findViewById(2131230982));
    this.tvTotalCost.setText(this.calendar.get(2) + 1 + "月购物花费");
    this.tvTotalCount = ((TextView)findViewById(2131230985));
    this.tvTotalCount.setText(this.calendar.get(2) + 1 + "月购物次数");
    this.ivCount = ((ImageView)findViewById(2131230981));
    this.ivBack = ((ImageView)findViewById(2131230980));
    this.ivTakePhoto = ((ImageView)findViewById(2131230986));
    this.tvTotal = ((TextView)findViewById(2131230983));
    this.tvTimes = ((TextView)findViewById(2131230984));
    this.footView = LayoutInflater.from(this).inflate(2130903147, null);
    this.footView.setVisibility(8);
    this.lvReceiptList.addFooterView(this.footView);
    this.data = new ArrayList();
    this.adapter = new MyReceiptAdapter(this, this.data);
    this.lvReceiptList.setAdapter(this.adapter);
    this.dialog = new MyDialog(this);
    this.dialog.setCancelable(false);
  }

  private void loadNewListChange(List<NewReceiptInfo> paramList)
  {
    if (((NewReceiptInfo)paramList.get(0)).getDayInfo().getMonth().equals(((NewReceiptInfo)this.data.get(this.data.size() - 1)).getItems().getMonth()))
    {
      paramList.remove(0);
      if (((NewReceiptInfo)paramList.get(0)).getFlag() == 2)
      {
        ((NewReceiptInfo)paramList.get(0)).setFlag(0);
        return;
      }
      ((NewReceiptInfo)paramList.get(0)).setFlag(1);
      return;
    }
    if (((NewReceiptInfo)this.data.get(this.data.size() - 1)).getFlag() == 2)
    {
      ((NewReceiptInfo)this.data.get(this.data.size() - 1)).setFlag(3);
      return;
    }
    ((NewReceiptInfo)this.data.get(this.data.size() - 1)).setFlag(1);
  }

  private void setHeadMonthData(int paramInt)
  {
    NewReceiptInfo localNewReceiptInfo;
    if ((this.data != null) && (this.data.size() > 0))
    {
      localNewReceiptInfo = (NewReceiptInfo)this.data.get(paramInt);
      if (localNewReceiptInfo != null)
      {
        if (localNewReceiptInfo.getType() != 0)
          break label165;
        paramInt = 0;
        if (paramInt < this.monthinfos.size())
          break label60;
      }
    }
    while (true)
    {
      return;
      label60: if (((ReceiptMonthInfo)this.monthinfos.get(paramInt)).getMonth().equals(localNewReceiptInfo.getItems().getMonth()))
      {
        changeHeadData(((ReceiptMonthInfo)this.monthinfos.get(paramInt)).getMonth(), String.valueOf(((ReceiptMonthInfo)this.monthinfos.get(paramInt)).getMoney()), ((ReceiptMonthInfo)this.monthinfos.get(paramInt)).getTimes());
        return;
      }
      paramInt += 1;
      break;
      label165: paramInt = 0;
      while (paramInt < this.monthinfos.size())
      {
        if (((ReceiptMonthInfo)this.monthinfos.get(paramInt)).getMonth().equals(localNewReceiptInfo.getDayInfo().getMonth()))
        {
          changeHeadData(localNewReceiptInfo.getDayInfo().getMonth(), String.valueOf(((ReceiptMonthInfo)this.monthinfos.get(paramInt)).getMoney()), ((ReceiptMonthInfo)this.monthinfos.get(paramInt)).getTimes());
          return;
        }
        paramInt += 1;
      }
    }
  }

  public void noDataLayout()
  {
    this.receiptList.setVisibility(8);
    this.layout.setVisibility(0);
    this.tvCurrentDay.setText(String.valueOf(this.calendar.get(5)) + "日");
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 1)
    {
      this.isLoading = false;
      initData(0);
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230986:
      startActivity(new Intent(this, CameraActivity1.class));
      return;
    case 2131230980:
      finish();
      return;
    case 2131230981:
    }
    startActivity(new Intent(this, MyReceiptStatisticActivity.class));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903077);
    initView();
    initClickListener();
    initData(0);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    initData(0);
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    setHeadMonthData(paramInt1);
    if (paramInt1 + paramInt2 == paramInt3)
    {
      this.isState = true;
      return;
    }
    this.isState = false;
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (((paramInt == 0) || (paramInt == 2)) && (this.isState) && (!this.isLoading))
    {
      this.isLoading = true;
      if (this.infos.getItems().size() > 0)
        paramInt = 0;
    }
    while (true)
    {
      if (paramInt >= this.infos.getItems().size())
      {
        initData(this.item.getId());
        return;
      }
      if (paramInt == 0)
        this.item = ((ReceiptItems)this.infos.getItems().get(0));
      if (this.item.getId() > ((ReceiptItems)this.infos.getItems().get(paramInt)).getId())
        this.item = ((ReceiptItems)this.infos.getItems().get(paramInt));
      paramInt += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MyReceiptListActivity
 * JD-Core Version:    0.6.2
 */