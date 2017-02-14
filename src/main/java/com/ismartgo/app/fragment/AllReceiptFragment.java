package com.ismartgo.app.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.activity.UploadReceiptActivityRetailActivity;
import com.ismartgo.app.bean.ReceiptActivityInfo;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.utils.DistanceConversionUtils;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.ResponseJsonUtils;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import de.greenrobot.event.EventBus;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllReceiptFragment extends Fragment
  implements View.OnClickListener, AdapterView.OnItemClickListener, AbOnListViewListener
{
  private static final String TAG = "AllReceiptFragment";
  private AllReceiptAdatper adapter;
  private String city;
  private Context context;
  private View emptyView;
  private View failureView;
  private ImageView imgEmpty;
  private ImageView imgFailure;
  private boolean isBusy;
  private String lat;
  private String lon;
  private MyDialog mDialog;
  private AbPullListView mListView;
  private int page = 1;
  private View parentView;
  private List<ReceiptActivityInfo> receiptList;
  private TextView tvEmptyInfo;
  private TextView tvFailureInfo;
  private String uid;

  private void TestData()
  {
    this.receiptList = new ArrayList();
    int i = 1;
    while (true)
    {
      if (i >= 10)
      {
        this.adapter.notifyDataSetChanged();
        return;
      }
      ReceiptActivityInfo localReceiptActivityInfo = new ReceiptActivityInfo();
      localReceiptActivityInfo.setId(i);
      localReceiptActivityInfo.setTitle("title " + i);
      localReceiptActivityInfo.setBegindate("2015-12-12");
      localReceiptActivityInfo.setEnddate("2015-12-12");
      localReceiptActivityInfo.setDistance(100);
      localReceiptActivityInfo.setPrizename("prizename");
      localReceiptActivityInfo.setClickcnt(i);
      localReceiptActivityInfo.setJoined(1);
      localReceiptActivityInfo.setImgurl("imgurl");
      localReceiptActivityInfo.setPrizedetail("prizedetail");
      localReceiptActivityInfo.setShops("shops");
      localReceiptActivityInfo.setDesc("desc");
      localReceiptActivityInfo.setRules("rules");
      localReceiptActivityInfo.setDetailurl("detailurl");
      this.receiptList.add(localReceiptActivityInfo);
      i += 1;
    }
  }

  private void initData(Bundle paramBundle)
  {
    if (paramBundle == null)
    {
      this.uid = BaseActivity.loginUser.getId();
      this.city = BaseActivity.city;
      this.lat = BaseActivity.lat;
      this.lon = BaseActivity.log;
      return;
    }
    this.uid = paramBundle.getString("uid");
    this.city = paramBundle.getString("city");
    this.lat = paramBundle.getString("lat");
    this.lon = paramBundle.getString("lon");
  }

  private void request()
  {
    this.isBusy = true;
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", this.uid);
    localHashMap.put("devcode", TelephoneUtils.getIMEI(getActivity()));
    localHashMap.put("cityname", this.city);
    localHashMap.put("lat", this.lat);
    localHashMap.put("lon", this.lon);
    localHashMap.put("pages", String.valueOf(this.page));
    localHashMap.put("pagesize", String.valueOf(30));
    Log.i("AllReceiptFragment", "开始请求");
    HttpRequest.getInstance().executePostStringRequest(this.context, Url.ALL_RECEIPT_ACTIVITY_URL, 48, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        AllReceiptFragment.this.mDialog.dismiss();
        if ((AllReceiptFragment.this.receiptList != null) && (AllReceiptFragment.this.receiptList.size() > 0))
          AllReceiptFragment.this.failureView.setVisibility(8);
        while (true)
        {
          Log.i("AllReceiptFragment", "请求活动数据失败： ");
          AllReceiptFragment.this.mListView.stopLoadMore();
          AllReceiptFragment.this.mListView.stopRefresh();
          AllReceiptFragment.this.isBusy = false;
          return;
          AllReceiptFragment.this.failureView.setVisibility(0);
          AllReceiptFragment.this.imgFailure.setImageResource(2130837833);
          AllReceiptFragment.this.tvFailureInfo.setText("哎哟，加载失败，点击重试");
        }
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i("AllReceiptFragment", "请求活动数据： " + ((String)paramAnonymousResponse.get()).toString());
        AllReceiptFragment.this.mDialog.dismiss();
        AllReceiptFragment.this.failureView.setVisibility(8);
        AllReceiptFragment.this.mListView.stopLoadMore();
        AllReceiptFragment.this.mListView.stopRefresh();
        paramAnonymousResponse = ResponseJsonUtils.parseReceiptActivity(String.valueOf(((String)paramAnonymousResponse.get()).toString()));
        if ((paramAnonymousResponse == null) || (paramAnonymousResponse.size() == 0))
        {
          if ((AllReceiptFragment.this.receiptList == null) || (AllReceiptFragment.this.receiptList.size() == 0))
          {
            AllReceiptFragment.this.mListView.setVisibility(8);
            AllReceiptFragment.this.emptyView.setVisibility(0);
            AllReceiptFragment.this.imgEmpty.setImageResource(2130837833);
            AllReceiptFragment.this.tvEmptyInfo.setText("咦，没有找到相关活动~选选其他吧");
          }
          return;
        }
        if ((AllReceiptFragment.this.page == 1) && (AllReceiptFragment.this.receiptList != null))
          AllReceiptFragment.this.receiptList.clear();
        if (AllReceiptFragment.this.receiptList == null)
          AllReceiptFragment.this.receiptList = paramAnonymousResponse;
        while (true)
        {
          AllReceiptFragment.this.adapter.notifyDataSetChanged();
          AllReceiptFragment.this.isBusy = false;
          return;
          AllReceiptFragment.this.receiptList.addAll(paramAnonymousResponse);
        }
      }
    });
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.context = paramActivity;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231232:
    }
    if (this.mDialog != null)
      this.mDialog.show();
    this.page = 1;
    request();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    EventBus.getDefault().register(this);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.parentView = paramLayoutInflater.inflate(2130903119, paramViewGroup, false);
    this.mListView = ((AbPullListView)this.parentView.findViewById(2131231118));
    this.adapter = new AllReceiptAdatper();
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(this);
    this.mListView.setAbOnListViewListener(this);
    this.emptyView = this.parentView.findViewById(2131231229);
    this.imgEmpty = ((ImageView)this.parentView.findViewById(2131231230));
    this.tvEmptyInfo = ((TextView)this.parentView.findViewById(2131231231));
    this.failureView = this.parentView.findViewById(2131231232);
    this.failureView.setOnClickListener(this);
    this.imgFailure = ((ImageView)this.parentView.findViewById(2131231233));
    this.tvFailureInfo = ((TextView)this.parentView.findViewById(2131231234));
    this.mDialog = new MyDialog(getActivity());
    this.mDialog.show();
    initData(paramBundle);
    request();
    return this.parentView;
  }

  public void onDestroy()
  {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
    Log.i("AllReceiptFragment", "unregister");
  }

  public void onEventMainThread(Object paramObject)
  {
    int j;
    int i;
    if (paramObject != null)
    {
      paramObject = (Map)paramObject;
      String str = (String)paramObject.get("tag");
      j = ((Integer)paramObject.get("gameid")).intValue();
      if ((str != null) && (str.equals("AllReceiptFragment")) && (this.receiptList != null) && (this.receiptList.size() > 0))
        i = 0;
    }
    while (true)
    {
      if (i >= this.receiptList.size());
      while (true)
      {
        if (this.adapter != null)
          this.adapter.notifyDataSetChanged();
        return;
        paramObject = (ReceiptActivityInfo)this.receiptList.get(i);
        if (paramObject.getId() != j)
          break;
        paramObject.setJoined(1);
        this.receiptList.remove(i);
        this.receiptList.add(i, paramObject);
      }
      i += 1;
    }
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramInt -= 1;
    if ((this.receiptList == null) || (this.receiptList.size() == 0))
      return;
    paramAdapterView = new Intent(this.context, UploadReceiptActivityRetailActivity.class);
    paramAdapterView.putExtra("receipt_detail", ((ReceiptActivityInfo)this.receiptList.get(paramInt)).getDetailurl());
    paramAdapterView.putExtra("gameid", ((ReceiptActivityInfo)this.receiptList.get(paramInt)).getId());
    paramAdapterView.putExtra("end_date", ((ReceiptActivityInfo)this.receiptList.get(paramInt)).getEnddate());
    paramAdapterView.putExtra("tag", "AllReceiptFragment");
    startActivity(paramAdapterView);
  }

  public void onLoadMore()
  {
    if (this.isBusy)
    {
      this.mListView.stopLoadMore();
      return;
    }
    this.page += 1;
    request();
  }

  public void onPause()
  {
    super.onPause();
    MobclickAgent.onPageEnd("AllReceiptFragment");
  }

  public void onRefresh()
  {
    if (this.isBusy)
    {
      this.mListView.stopRefresh();
      return;
    }
    this.page = 1;
    request();
  }

  public void onResume()
  {
    super.onResume();
    MobclickAgent.onPageStart("AllReceiptFragment");
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putString("uid", BaseActivity.loginUser.getId());
    paramBundle.putString("cityname", BaseActivity.city);
    paramBundle.putString("lat", BaseActivity.lat);
    paramBundle.putString("lon", BaseActivity.log);
    super.onSaveInstanceState(paramBundle);
  }

  class AllReceiptAdatper extends BaseAdapter
  {
    private LayoutInflater inflater = LayoutInflater.from(AllReceiptFragment.this.context);

    public AllReceiptAdatper()
    {
    }

    public int getCount()
    {
      if (AllReceiptFragment.this.receiptList == null)
        return 0;
      return AllReceiptFragment.this.receiptList.size();
    }

    public Object getItem(int paramInt)
    {
      return AllReceiptFragment.this.receiptList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.inflater.inflate(2130903135, null);
        paramViewGroup = new ViewHolder();
        paramViewGroup.imgActivity = ((ImageView)paramView.findViewById(2131231142));
        paramViewGroup.tvActivtyName = ((TextView)paramView.findViewById(2131231143));
        paramViewGroup.tvDate = ((TextView)paramView.findViewById(2131230923));
        paramViewGroup.tvDistance = ((TextView)paramView.findViewById(2131231145));
        paramViewGroup.tvPopularity = ((TextView)paramView.findViewById(2131231151));
        paramViewGroup.tvReward = ((TextView)paramView.findViewById(2131231152));
        paramViewGroup.tvJoinCount = ((TextView)paramView.findViewById(2131231149));
        paramViewGroup.tvVerifyState = ((TextView)paramView.findViewById(2131231150));
        paramViewGroup.layoutVerifyState = paramView.findViewById(2131231147);
        paramViewGroup.layoutVerify = paramView.findViewById(2131231146);
        paramView.setTag(paramViewGroup);
      }
      while (true)
      {
        paramViewGroup.layoutVerifyState.setVisibility(8);
        ReceiptActivityInfo localReceiptActivityInfo = (ReceiptActivityInfo)AllReceiptFragment.this.receiptList.get(paramInt);
        ImgLoader.getInstance(AllReceiptFragment.this.context).showPic(localReceiptActivityInfo.getImgurl(), paramViewGroup.imgActivity, false);
        paramViewGroup.tvActivtyName.setText(localReceiptActivityInfo.getTitle());
        try
        {
          paramViewGroup.tvDate.setText(CommonMethod.parseTime(localReceiptActivityInfo.getBegindate()) + "-" + CommonMethod.parseTime(localReceiptActivityInfo.getEnddate()));
          paramViewGroup.tvDistance.setText(DistanceConversionUtils.getDistance(String.valueOf(localReceiptActivityInfo.getDistance())));
          paramViewGroup.tvPopularity.setText(String.valueOf(localReceiptActivityInfo.getClickcnt()));
          paramViewGroup.tvReward.setText(localReceiptActivityInfo.getPrizename());
          paramViewGroup.tvJoinCount.setVisibility(8);
          paramInt = localReceiptActivityInfo.getJoined();
          if (paramInt == 0)
          {
            paramViewGroup.tvVerifyState.setVisibility(8);
            return paramView;
            paramViewGroup = (ViewHolder)paramView.getTag();
          }
        }
        catch (ParseException localParseException)
        {
          do
            while (true)
              localParseException.printStackTrace();
          while (paramInt != 1);
          paramViewGroup.tvVerifyState.setVisibility(0);
          paramViewGroup.tvVerifyState.setText("已参加");
          paramViewGroup.tvVerifyState.setBackgroundColor(Color.parseColor("#FF963B"));
        }
      }
      return paramView;
    }

    class ViewHolder
    {
      ImageView imgActivity;
      View layoutVerify;
      View layoutVerifyState;
      TextView tvActivtyName;
      TextView tvDate;
      TextView tvDistance;
      TextView tvJoinCount;
      TextView tvPopularity;
      TextView tvReward;
      TextView tvVerifyState;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.fragment.AllReceiptFragment
 * JD-Core Version:    0.6.2
 */