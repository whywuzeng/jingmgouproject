package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.bean.BeanAllRecord;
import com.ismartgo.app.bean.BeanCostRecord;
import com.ismartgo.app.bean.BeanDetail;
import com.ismartgo.app.bean.BeanGetRecord;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.RequestJSONUtils;
import com.ismartgo.app.grid.utils.ResponseJsonUtils;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.pulltorefreshlistview.view.OnRefreshListener;
import com.ismartgo.pulltorefreshlistview.view.RefreshListView;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBeansActivity extends BaseActivity
  implements View.OnClickListener, OnRefreshListener
{
  public static String TAG = "MyBeasActivity";
  private BeanRecordAdapter adapter;
  private BeanDetail beanDetail;
  private Button btn1;
  private Button btn2;
  private Button btn3;
  private Map<String, BeanDetail> cacheMap;
  private String currentDate;
  private int currentPositon;
  private List<HashMap<String, Object>> dateList;
  private Gallery gallery;
  private GalleryAdapter galleryAdapter;
  private Gift gift1;
  private Gift gift2;
  private boolean hasLoaded;
  private LayoutInflater infalter;
  private boolean isLoading;
  private boolean isRefreshing = false;

  @AbIocView(click="onClick", id=2131231169)
  private View layout_exchange_1;

  @AbIocView(click="onClick", id=2131231201)
  private View layout_exchange_2;
  private MyDialog mDialog;
  private int pages = 1;
  private int position = 0;
  private RefreshListView recordListView;
  private ToastDefine toast;
  private TextView tvLastMonth;
  private TextView tvNextMonth;

  @AbIocView(click="onClick", id=2131231222)
  private RelativeLayout tv_invite;

  @AbIocView(id=2131231228)
  private TextView tv_invite_decribe;

  @AbIocView(click="onClick", id=2131231265)
  private TextView tv_more_gifts;

  @AbIocView(id=2131230972)
  private TextView tv_num;

  @AbIocView(click="onClick", id=2131231215)
  private RelativeLayout tv_scan;

  @AbIocView(id=2131231221)
  private TextView tv_scan_decribe;

  @AbIocView(click="onClick", id=2131231202)
  private RelativeLayout tv_signIn;

  @AbIocView(id=2131231208)
  private TextView tv_sign_decribe;
  private int type = 1;

  private void getDataList()
  {
    try
    {
      if (this.currentDate != null)
      {
        this.dateList = getMonthBetween("2015-01", this.currentDate);
        this.galleryAdapter = new GalleryAdapter();
        this.gallery.setAdapter(this.galleryAdapter);
        this.gallery.setSelection(this.dateList.size() - 1);
        this.position = (this.dateList.size() - 1);
      }
      return;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
  }

  private List<HashMap<String, Object>> getMonthBetween(String paramString1, String paramString2)
    throws ParseException
  {
    ArrayList localArrayList = new ArrayList();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM");
    Calendar localCalendar1 = Calendar.getInstance();
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar1.setTime(localSimpleDateFormat.parse(paramString1));
    localCalendar1.set(localCalendar1.get(1), localCalendar1.get(2), 1);
    localCalendar2.setTime(localSimpleDateFormat.parse(paramString2));
    localCalendar2.set(localCalendar2.get(1), localCalendar2.get(2), 2);
    while (true)
    {
      if (!localCalendar1.before(localCalendar2))
        return localArrayList;
      paramString1 = new HashMap();
      paramString1.put("year", Integer.valueOf(localCalendar1.get(1)));
      paramString1.put("month", Integer.valueOf(localCalendar1.get(2) + 1));
      paramString1.put("date", localSimpleDateFormat.format(localCalendar1.getTime()));
      localArrayList.add(paramString1);
      localCalendar1.add(2, 1);
    }
  }

  private void getUserBeanDetail(int paramInt)
  {
    if (loginUser != null)
    {
      if ((!this.hasLoaded) && (this.mDialog != null) && (!this.mDialog.isShowing()) && (!this.isRefreshing))
        this.mDialog.show();
      RequestJSONUtils.getUserBeanNew(this, loginUser.getId(), paramInt, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          if (!MyBeansActivity.this.hasLoaded)
          {
            if ((MyBeansActivity.this.mDialog != null) && (MyBeansActivity.this.mDialog.isShowing()))
              MyBeansActivity.this.mDialog.dismiss();
            return;
          }
          MyBeansActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
          MyBeansActivity.this.toast.show();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          Log.i("json", "获取精明豆明细数据： " + ((String)paramAnonymousResponse.get()).toString());
          if (MyBeansActivity.this.isRefreshing)
          {
            MyBeansActivity.this.recordListView.hideHeaderView();
            MyBeansActivity.this.beanDetail.getAllRecordList().clear();
            MyBeansActivity.this.beanDetail.getCostRecordList().clear();
            MyBeansActivity.this.beanDetail.getGetRecordList().clear();
            MyBeansActivity.this.isRefreshing = false;
          }
          BeanDetail localBeanDetail = ResponseJsonUtils.parseUserBeanDetailJSON(((String)paramAnonymousResponse.get()).toString());
          Log.i("json", ((String)paramAnonymousResponse.get()).toString());
          if ((localBeanDetail != null) && (localBeanDetail.getAllRecordList() != null) && (localBeanDetail.getAllRecordList().size() > 0))
            if (MyBeansActivity.this.beanDetail == null)
            {
              MyBeansActivity.this.beanDetail = localBeanDetail;
              if (MyBeansActivity.this.adapter != null)
                MyBeansActivity.this.adapter.notifyDataSetChanged();
              if (MyBeansActivity.this.galleryAdapter != null)
                MyBeansActivity.this.galleryAdapter.notifyDataSetChanged();
            }
          while (true)
          {
            if ((!MyBeansActivity.this.hasLoaded) && (MyBeansActivity.this.mDialog != null) && (MyBeansActivity.this.mDialog.isShowing()))
              MyBeansActivity.this.mDialog.dismiss();
            MyBeansActivity.this.isLoading = false;
            if (MyBeansActivity.this.recordListView != null)
              MyBeansActivity.this.recordListView.hideFooterView();
            return;
            MyBeansActivity.this.beanDetail.getAllRecordList().addAll(localBeanDetail.getAllRecordList());
            MyBeansActivity.this.beanDetail.getCostRecordList().addAll(localBeanDetail.getCostRecordList());
            MyBeansActivity.this.beanDetail.getGetRecordList().addAll(localBeanDetail.getGetRecordList());
            break;
            MyBeansActivity.this.toast.setMessage("没有更多了");
            MyBeansActivity.this.toast.show();
          }
        }
      });
    }
  }

  private void getUserBeanDetail(final String paramString)
  {
    if ((loginUser != null) && (paramString != null))
    {
      if ((!this.hasLoaded) && (this.mDialog != null) && (!this.mDialog.isShowing()))
        this.mDialog.show();
      RequestJSONUtils.getUserBeanDetail(this, loginUser.getId(), paramString, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          if (!MyBeansActivity.this.hasLoaded)
          {
            if ((MyBeansActivity.this.mDialog != null) && (MyBeansActivity.this.mDialog.isShowing()))
              MyBeansActivity.this.mDialog.dismiss();
            return;
          }
          paramAnonymousString = MyBeansActivity.this;
          paramAnonymousString.position += 1;
          MyBeansActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
          MyBeansActivity.this.toast.show();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          BeanDetail localBeanDetail = ResponseJsonUtils.parseUserBeanDetailJSON(((String)paramAnonymousResponse.get()).toString());
          Log.i("json", ((String)paramAnonymousResponse.get()).toString());
          if ((localBeanDetail != null) && (localBeanDetail.getAllRecordList() != null) && (localBeanDetail.getAllRecordList().size() > 0))
            if (MyBeansActivity.this.beanDetail == null)
            {
              MyBeansActivity.this.beanDetail = localBeanDetail;
              MyBeansActivity.this.cacheMap.put(paramString, MyBeansActivity.this.beanDetail);
              if (MyBeansActivity.this.adapter != null)
                MyBeansActivity.this.adapter.notifyDataSetChanged();
              if (MyBeansActivity.this.galleryAdapter != null)
                MyBeansActivity.this.galleryAdapter.notifyDataSetChanged();
            }
          while (true)
          {
            if ((!MyBeansActivity.this.hasLoaded) && (MyBeansActivity.this.mDialog != null) && (MyBeansActivity.this.mDialog.isShowing()))
              MyBeansActivity.this.mDialog.dismiss();
            MyBeansActivity.this.isLoading = false;
            if (MyBeansActivity.this.recordListView != null)
              MyBeansActivity.this.recordListView.hideFooterView();
            return;
            MyBeansActivity.this.beanDetail.getAllRecordList().addAll(localBeanDetail.getAllRecordList());
            MyBeansActivity.this.beanDetail.getCostRecordList().addAll(localBeanDetail.getCostRecordList());
            MyBeansActivity.this.beanDetail.getGetRecordList().addAll(localBeanDetail.getGetRecordList());
            break;
            if (MyBeansActivity.this.hasLoaded)
            {
              paramAnonymousResponse = MyBeansActivity.this;
              paramAnonymousResponse.position += 1;
            }
            MyBeansActivity.this.toast.setMessage("没有更多了");
            MyBeansActivity.this.toast.show();
          }
        }
      });
    }
  }

  private void initData()
  {
    Calendar localCalendar = Calendar.getInstance();
    this.currentDate = new SimpleDateFormat("yyyy-MM").format(localCalendar.getTime());
    getDataList();
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("我的精明豆");
    this.tv_sign_decribe.setText(StringUtils.StringFilter(StringUtils.ToDBC(getString(2131296362))));
    this.tv_scan_decribe.setText(StringUtils.StringFilter(StringUtils.ToDBC(getString(2131296364))));
    this.tv_invite_decribe.setText(StringUtils.StringFilter(StringUtils.ToDBC(getString(2131296365))));
    this.gallery = ((Gallery)findViewById(2131231278));
    this.gallery.setSpacing(getWindowManager().getDefaultDisplay().getWidth() / 2);
    this.gallery.setCallbackDuringFling(false);
    this.gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (MyBeansActivity.this.currentPositon == paramAnonymousInt)
          return;
        MyBeansActivity.this.currentPositon = paramAnonymousInt;
        MyBeansActivity.this.currentDate = ((String)((HashMap)MyBeansActivity.this.dateList.get(paramAnonymousInt)).get("date"));
        MyBeansActivity.this.beanDetail = null;
        MyBeansActivity.this.adapter.notifyDataSetChanged();
        MyBeansActivity.this.getUserBeanDetail(MyBeansActivity.this.currentDate);
      }

      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
      {
      }
    });
    this.btn1 = ((Button)findViewById(2131230850));
    this.btn2 = ((Button)findViewById(2131230851));
    this.btn3 = ((Button)findViewById(2131230852));
    this.btn1.setOnClickListener(this);
    this.btn2.setOnClickListener(this);
    this.btn3.setOnClickListener(this);
    this.infalter = LayoutInflater.from(this);
    this.recordListView = ((RefreshListView)findViewById(2131230853));
    this.recordListView.setOnRefreshListener(this);
    this.adapter = new BeanRecordAdapter();
    this.recordListView.setAdapter(this.adapter);
    this.mDialog = new MyDialog(this);
    this.tvNextMonth = ((TextView)findViewById(2131231279));
    this.tvLastMonth = ((TextView)findViewById(2131231280));
    this.tvNextMonth.setOnClickListener(this);
    this.tvLastMonth.setOnClickListener(this);
    this.toast = new ToastDefine(this);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131231265:
    case 2131231202:
    case 2131231215:
    case 2131231222:
    case 2131231169:
    case 2131231201:
    case 2131230850:
    case 2131230851:
    case 2131230852:
    case 2131231279:
    case 2131231280:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              startActivity(new Intent(this, RewardExchangeActivity.class));
              finish();
              return;
              EarnBeansActivity.clickSignInAndScan(this, 1);
              return;
              EarnBeansActivity.clickSignInAndScan(this, 2);
              return;
              startActivity(new Intent(this, InviteFriendsActivity.class));
              return;
              toGiftDetail(this.gift1);
              return;
              toGiftDetail(this.gift2);
              return;
              this.btn1.setBackgroundResource(2130837554);
              this.btn2.setBackgroundResource(2130837558);
              this.btn3.setBackgroundResource(2130837571);
              this.btn1.setTextColor(-1);
              this.btn2.setTextColor(getResources().getColor(2131099700));
              this.btn3.setTextColor(getResources().getColor(2131099700));
              this.type = 1;
            }
            while (this.adapter == null);
            this.adapter.notifyDataSetChanged();
            this.recordListView.setSelection(0);
            return;
            this.btn1.setBackgroundResource(2130837555);
            this.btn2.setBackgroundResource(2130837557);
            this.btn3.setBackgroundResource(2130837571);
            this.btn2.setTextColor(-1);
            this.btn1.setTextColor(getResources().getColor(2131099700));
            this.btn3.setTextColor(getResources().getColor(2131099700));
            this.type = 2;
          }
          while (this.adapter == null);
          this.adapter.notifyDataSetChanged();
          this.recordListView.setSelection(0);
          return;
          this.btn1.setBackgroundResource(2130837555);
          this.btn2.setBackgroundResource(2130837558);
          this.btn3.setBackgroundResource(2130837570);
          this.btn3.setTextColor(-1);
          this.btn2.setTextColor(getResources().getColor(2131099700));
          this.btn1.setTextColor(getResources().getColor(2131099700));
          this.type = 3;
        }
        while (this.adapter == null);
        this.adapter.notifyDataSetChanged();
        this.recordListView.setSelection(0);
        return;
      }
      while ((this.dateList != null) && (this.currentPositon == this.dateList.size() - 1));
      this.currentPositon += 1;
      this.gallery.setSelection(this.currentPositon);
      this.currentDate = ((String)((HashMap)this.dateList.get(this.currentPositon)).get("date"));
      getUserBeanDetail(this.currentDate);
      return;
    }
    while (this.currentPositon == 0);
    this.currentPositon -= 1;
    this.gallery.setSelection(this.currentPositon);
    this.currentDate = ((String)((HashMap)this.dateList.get(this.currentPositon)).get("date"));
    getUserBeanDetail(this.currentDate);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903049);
    initView();
    if (this.cacheMap == null)
      this.cacheMap = new HashMap();
    getUserBeanDetail(this.pages);
  }

  public void onDownPullRefresh()
  {
    this.isRefreshing = true;
    this.pages = 1;
    getUserBeanDetail(this.pages);
  }

  public void onLoadingMore()
  {
    if ((this.isLoading) || (this.pages == 0))
      return;
    this.hasLoaded = true;
    this.pages += 1;
    this.adapter.notifyDataSetChanged();
    getUserBeanDetail(this.pages);
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    if (loginUser != null)
    {
      this.tv_num.setText(String.valueOf(loginUser.getBeanNumber()));
      Log.i(TAG, "豆数： " + String.valueOf(loginUser.getBeanNumber()));
    }
    while (true)
    {
      MobclickAgent.onResume(this);
      return;
      this.tv_num.setText("0");
    }
  }

  class BeanRecordAdapter extends BaseAdapter
  {
    BeanRecordAdapter()
    {
    }

    public int getCount()
    {
      if (MyBeansActivity.this.beanDetail == null);
      do
      {
        return 0;
        if (MyBeansActivity.this.type == 1)
          return MyBeansActivity.this.beanDetail.getAllRecordList().size();
        if (MyBeansActivity.this.type == 2)
          return MyBeansActivity.this.beanDetail.getGetRecordList().size();
      }
      while (MyBeansActivity.this.type != 3);
      return MyBeansActivity.this.beanDetail.getCostRecordList().size();
    }

    public Object getItem(int paramInt)
    {
      if (MyBeansActivity.this.type == 1)
        return MyBeansActivity.this.beanDetail.getAllRecordList().get(paramInt);
      if (MyBeansActivity.this.type == 2)
        return MyBeansActivity.this.beanDetail.getGetRecordList().get(paramInt);
      if (MyBeansActivity.this.type == 3)
        return MyBeansActivity.this.beanDetail.getCostRecordList().get(paramInt);
      return null;
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = MyBeansActivity.this.infalter.inflate(2130903106, null);
        paramViewGroup = new ViewHolder();
        paramViewGroup.tvExplanation = ((TextView)paramView.findViewById(2131231092));
        paramViewGroup.tvDate = ((TextView)paramView.findViewById(2131230923));
        paramViewGroup.tvNum = ((TextView)paramView.findViewById(2131230972));
        paramView.setTag(paramViewGroup);
        if (MyBeansActivity.this.type != 1)
          break label282;
        localObject = (BeanAllRecord)getItem(paramInt);
        paramViewGroup.tvExplanation.setText(((BeanAllRecord)localObject).getExplanation());
        arrayOfString = ((BeanAllRecord)localObject).getDate().split("-");
        paramViewGroup.tvDate.setText(arrayOfString[1] + "月" + arrayOfString[2] + "日");
        if (((BeanAllRecord)localObject).getType() != 1)
          break label225;
        paramViewGroup.tvNum.setTextColor(MyBeansActivity.this.getResources().getColor(2131099718));
        paramViewGroup.tvNum.setText("+" + ((BeanAllRecord)localObject).getNum());
      }
      label225: 
      do
      {
        do
        {
          return paramView;
          paramViewGroup = (ViewHolder)paramView.getTag();
          break;
        }
        while (((BeanAllRecord)localObject).getType() != 2);
        paramViewGroup.tvNum.setTextColor(MyBeansActivity.this.getResources().getColor(2131099700));
        paramViewGroup.tvNum.setText("-" + ((BeanAllRecord)localObject).getNum());
        return paramView;
        if (MyBeansActivity.this.type == 2)
        {
          localObject = (BeanGetRecord)getItem(paramInt);
          paramViewGroup.tvExplanation.setText(((BeanGetRecord)localObject).getExplanation());
          arrayOfString = ((BeanGetRecord)localObject).getDate().split("-");
          paramViewGroup.tvDate.setText(arrayOfString[1] + "月" + arrayOfString[2] + "日");
          paramViewGroup.tvNum.setTextColor(MyBeansActivity.this.getResources().getColor(2131099718));
          paramViewGroup.tvNum.setText("+" + ((BeanGetRecord)localObject).getNum());
          return paramView;
        }
      }
      while (MyBeansActivity.this.type != 3);
      label282: Object localObject = (BeanCostRecord)getItem(paramInt);
      paramViewGroup.tvExplanation.setText(((BeanCostRecord)localObject).getExplanation());
      paramViewGroup.tvDate.setText(((BeanCostRecord)localObject).getDate());
      String[] arrayOfString = ((BeanCostRecord)localObject).getDate().split("-");
      paramViewGroup.tvDate.setText(arrayOfString[1] + "月" + arrayOfString[2] + "日");
      paramViewGroup.tvNum.setTextColor(MyBeansActivity.this.getResources().getColor(2131099700));
      paramViewGroup.tvNum.setText("-" + ((BeanCostRecord)localObject).getNum());
      return paramView;
    }

    class ViewHolder
    {
      TextView tvDate;
      TextView tvExplanation;
      TextView tvNum;

      ViewHolder()
      {
      }
    }
  }

  class GalleryAdapter extends BaseAdapter
  {
    GalleryAdapter()
    {
    }

    public int getCount()
    {
      if (MyBeansActivity.this.dateList == null)
        return 0;
      return MyBeansActivity.this.dateList.size();
    }

    public Object getItem(int paramInt)
    {
      return MyBeansActivity.this.dateList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = null;
      if (paramView == null)
      {
        paramViewGroup = new ViewHolder();
        paramView = MyBeansActivity.this.infalter.inflate(2130903138, null);
        paramViewGroup.tvMonth = ((TextView)paramView.findViewById(2131231156));
        paramViewGroup.tvEarnBeanNum = ((TextView)paramView.findViewById(2131231157));
        paramViewGroup.tvCostBeanNum = ((TextView)paramView.findViewById(2131231158));
        paramView.setTag(paramViewGroup);
      }
      while (true)
      {
        HashMap localHashMap = (HashMap)getItem(paramInt);
        paramViewGroup.tvMonth.getPaint().setFakeBoldText(true);
        paramViewGroup.tvMonth.setText(localHashMap.get("year") + "年" + localHashMap.get("month") + "月");
        if ((MyBeansActivity.this.beanDetail != null) && (localHashMap.get("date").equals(MyBeansActivity.this.currentDate)))
        {
          paramViewGroup.tvEarnBeanNum.setText(MyBeansActivity.this.beanDetail.getBeanGetSum() + "豆");
          paramViewGroup.tvCostBeanNum.setText(MyBeansActivity.this.beanDetail.getBeanUseSum() + "豆");
        }
        return paramView;
        paramView.setTag(null);
      }
    }

    class ViewHolder
    {
      TextView tvCostBeanNum;
      TextView tvEarnBeanNum;
      TextView tvMonth;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MyBeansActivity
 * JD-Core Version:    0.6.2
 */