package com.ismartgo.app.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.ismartgo.app.bean.ReceiptInfo;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.ResponseJsonUtils;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HasJoinPassFragment extends BaseFragment
  implements View.OnClickListener, AbOnListViewListener, HttpCallback<String>
{
  private static final String ALL_ACTIVITY_WHAT = UUID.randomUUID().toString();
  private static final String TAG = "HasJoinPassFragment";
  private AllReceiptAdatper adapter;
  private Context context;
  private View emptyView;
  private View failureView;
  private ImageView imgEmpty;
  private ImageView imgFailure;
  private boolean isBusy;
  private boolean isPrepared;
  private MyDialog mDialog;
  private boolean mHasLoadedOnce;
  private AbPullListView mListView;
  private int page = 1;
  private View parentView;
  private List<ReceiptInfo> receiptList;
  private TextView tvEmptyInfo;
  private TextView tvFailureInfo;

  private void initData(View paramView)
  {
    this.mListView = ((AbPullListView)paramView.findViewById(2131231118));
    this.mListView.setAbOnListViewListener(this);
    this.adapter = new AllReceiptAdatper();
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousInt -= 1;
        if ((HasJoinPassFragment.this.receiptList == null) || (HasJoinPassFragment.this.receiptList.size() < paramAnonymousInt))
          return;
        paramAnonymousAdapterView = new Intent(HasJoinPassFragment.this.context, UploadReceiptActivityRetailActivity.class);
        paramAnonymousAdapterView.putExtra("receipt_detail", ((ReceiptInfo)HasJoinPassFragment.this.receiptList.get(paramAnonymousInt)).getDetailurl());
        paramAnonymousAdapterView.putExtra("gameid", ((ReceiptInfo)HasJoinPassFragment.this.receiptList.get(paramAnonymousInt)).getGameId());
        paramAnonymousAdapterView.putExtra("end_date", ((ReceiptInfo)HasJoinPassFragment.this.receiptList.get(paramAnonymousInt)).getEnddate());
        paramAnonymousAdapterView.putExtra("tag", "HasJoinPassFragment");
        HasJoinPassFragment.this.startActivity(paramAnonymousAdapterView);
      }
    });
    this.emptyView = paramView.findViewById(2131231229);
    this.imgEmpty = ((ImageView)paramView.findViewById(2131231230));
    this.tvEmptyInfo = ((TextView)paramView.findViewById(2131231231));
    this.failureView = paramView.findViewById(2131231232);
    this.failureView.setOnClickListener(this);
    this.imgFailure = ((ImageView)paramView.findViewById(2131231233));
    this.tvFailureInfo = ((TextView)paramView.findViewById(2131231234));
    this.mDialog = new MyDialog(getActivity());
  }

  private void request()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", BaseActivity.loginUser.getId());
    localHashMap.put("devcode", TelephoneUtils.getIMEI(getActivity().getApplicationContext()));
    localHashMap.put("status", String.valueOf(2));
    localHashMap.put("pages", String.valueOf(this.page));
    localHashMap.put("pagesize", String.valueOf(30));
    HttpRequest.getInstance().executePostStringRequest(this.context, Url.HAS_JOIN_ACTIVITY_URL, 41, localHashMap, this);
  }

  protected void lazyLoad()
  {
    if ((!this.isPrepared) || (!this.isVisible) || (this.mHasLoadedOnce))
      return;
    this.mDialog.show();
    request();
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
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.parentView == null)
    {
      this.parentView = paramLayoutInflater.inflate(2130903119, paramViewGroup, false);
      this.isPrepared = true;
      initData(this.parentView);
      lazyLoad();
    }
    paramLayoutInflater = (ViewGroup)this.parentView.getParent();
    if (paramLayoutInflater != null)
      paramLayoutInflater.removeView(this.parentView);
    return this.parentView;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    this.mDialog.dismiss();
    if ((this.receiptList != null) && (this.receiptList.size() > 0))
      this.failureView.setVisibility(8);
    while (true)
    {
      Log.i("HasJoinPassFragment", "HasJoinPassFragment 请求活动数据失败： ");
      this.mListView.stopLoadMore();
      this.mListView.stopRefresh();
      this.isBusy = false;
      return;
      this.failureView.setVisibility(0);
      this.imgFailure.setImageResource(2130837833);
      this.tvFailureInfo.setText("哎哟，加载失败，点击重试");
    }
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
    MobclickAgent.onPageEnd("HasJoinPassFragment");
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
    MobclickAgent.onPageStart("HasJoinPassFragment");
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    Log.i("HasJoinPassFragment", "HasJoinPassFragment 请求活动数据： " + ((String)paramResponse.get()).toString());
    this.mDialog.dismiss();
    this.failureView.setVisibility(8);
    this.emptyView.setVisibility(8);
    this.mHasLoadedOnce = true;
    this.mListView.stopLoadMore();
    this.mListView.stopRefresh();
    paramResponse = ResponseJsonUtils.parseReceipt(String.valueOf(((String)paramResponse.get()).toString()));
    if ((paramResponse == null) || (paramResponse.size() == 0))
    {
      if ((this.receiptList == null) || (this.receiptList.size() == 0))
      {
        this.mListView.setVisibility(8);
        this.emptyView.setVisibility(0);
        this.imgEmpty.setImageResource(2130837833);
        this.tvEmptyInfo.setText("咦，这里空空如也~");
      }
      return;
    }
    if ((this.page == 1) && (this.receiptList != null))
      this.receiptList.clear();
    if (this.receiptList == null)
      this.receiptList = paramResponse;
    while (true)
    {
      this.adapter.notifyDataSetChanged();
      this.isBusy = false;
      return;
      this.receiptList.addAll(paramResponse);
    }
  }

  class AllReceiptAdatper extends BaseAdapter
  {
    private LayoutInflater inflater = LayoutInflater.from(HasJoinPassFragment.this.context);

    public AllReceiptAdatper()
    {
    }

    public int getCount()
    {
      if (HasJoinPassFragment.this.receiptList == null)
        return 0;
      return HasJoinPassFragment.this.receiptList.size();
    }

    public Object getItem(int paramInt)
    {
      return HasJoinPassFragment.this.receiptList.get(paramInt);
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
        paramViewGroup.layoutDiatance = paramView.findViewById(2131231144);
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
        ReceiptInfo localReceiptInfo = (ReceiptInfo)HasJoinPassFragment.this.receiptList.get(paramInt);
        ImgLoader.getInstance(HasJoinPassFragment.this.context).showPic(localReceiptInfo.getImgurl(), paramViewGroup.imgActivity, false);
        paramViewGroup.tvActivtyName.setText(localReceiptInfo.getTitle());
        try
        {
          paramViewGroup.tvDate.setText(CommonMethod.parseTime(localReceiptInfo.getBegindate()) + "-" + CommonMethod.parseTime(localReceiptInfo.getEnddate()));
          paramViewGroup.layoutDiatance.setVisibility(8);
          paramViewGroup.tvPopularity.setText(String.valueOf(localReceiptInfo.getClickcnt()));
          paramViewGroup.tvReward.setText(localReceiptInfo.getPrizename());
          paramViewGroup.tvJoinCount.setText("第" + String.valueOf(localReceiptInfo.getJoinno()) + "次");
          paramInt = localReceiptInfo.getStatus();
          if (paramInt == 1)
          {
            paramViewGroup.tvVerifyState.setText("待审核");
            paramViewGroup.tvVerifyState.setBackgroundColor(Color.parseColor("#FF963B"));
            return paramView;
            paramViewGroup = (ViewHolder)paramView.getTag();
          }
        }
        catch (ParseException localParseException)
        {
          do
          {
            while (true)
              localParseException.printStackTrace();
            if (paramInt == 2)
            {
              paramViewGroup.tvVerifyState.setText("已通过");
              paramViewGroup.tvVerifyState.setBackgroundColor(Color.parseColor("#F2572D"));
              return paramView;
            }
          }
          while (paramInt != 3);
          paramViewGroup.tvVerifyState.setText("未通过");
          paramViewGroup.tvVerifyState.setBackgroundColor(Color.parseColor("#999999"));
        }
      }
      return paramView;
    }

    class ViewHolder
    {
      ImageView imgActivity;
      View layoutDiatance;
      View layoutVerify;
      View layoutVerifyState;
      TextView tvActivtyName;
      TextView tvDate;
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
 * Qualified Name:     com.ismartgo.app.fragment.HasJoinPassFragment
 * JD-Core Version:    0.6.2
 */