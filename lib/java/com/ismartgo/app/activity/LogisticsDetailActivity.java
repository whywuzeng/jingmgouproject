package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.ismartgo.app.adapter.LogisticsAdapter;
import com.ismartgo.app.bean.Logistics;
import com.ismartgo.app.bean.Logistics.DataEntity;
import com.ismartgo.app.bean.Logistics.DataEntity.OrderTrackEntity;
import com.umeng.analytics.MobclickAgent;
import java.util.List;

public class LogisticsDetailActivity extends BaseActivity
{
  private Logistics logistics;
  private ListView logisticsListView;
  private String logisticsName;
  private LogisticsAdapter mLogisticsAdapter;
  private List<Logistics.DataEntity.OrderTrackEntity> orderTrackEntityList;
  private TextView tvExpree;
  private TextView tvExpreeNo;

  private void getData()
  {
    this.logistics = ((Logistics)getIntent().getBundleExtra("express_bundle").getSerializable("express"));
    this.logisticsName = getIntent().getStringExtra("express_name");
  }

  private void initView()
  {
    this.tvExpree = ((TextView)findViewById(2131230939));
    this.tvExpreeNo = ((TextView)findViewById(2131230940));
    this.logisticsListView = ((ListView)findViewById(2131230941));
    if (this.logisticsName != null)
      this.tvExpree.setText(this.logisticsName);
    if (this.logistics != null)
    {
      this.mLogisticsAdapter = new LogisticsAdapter(this, this.orderTrackEntityList);
      this.logisticsListView.setAdapter(this.mLogisticsAdapter);
      setAdapter();
    }
  }

  private void setAdapter()
  {
    if ((this.logistics != null) && (this.logistics.getStatus() == 10001))
    {
      this.tvExpreeNo.append(this.logistics.getData().getLogisticsNum());
      this.orderTrackEntityList = this.logistics.getData().getOrderTrack();
      this.mLogisticsAdapter.setData(this.orderTrackEntityList);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
    initTitleBar();
    this.tv_title.setText("物流详情");
    getData();
    initView();
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.LogisticsDetailActivity
 * JD-Core Version:    0.6.2
 */