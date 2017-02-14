package com.ismartgo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ismartgo.app.bean.Logistics.DataEntity.OrderTrackEntity;
import java.util.List;

public class LogisticsAdapter extends BaseAdapter
{
  private Context context;
  private LayoutInflater infalter;
  private List<Logistics.DataEntity.OrderTrackEntity> logistics;

  public LogisticsAdapter(Context paramContext, List<Logistics.DataEntity.OrderTrackEntity> paramList)
  {
    this.context = paramContext;
    this.logistics = paramList;
    this.infalter = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    if (this.logistics == null)
      return 0;
    return this.logistics.size();
  }

  public Object getItem(int paramInt)
  {
    return this.logistics.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Logistics.DataEntity.OrderTrackEntity localOrderTrackEntity;
    if (paramView == null)
    {
      paramView = this.infalter.inflate(2130903141, null);
      paramViewGroup = new ViewHolder();
      paramViewGroup.view1 = paramView.findViewById(2131231165);
      paramViewGroup.imgTagLatestLogistics = ((ImageView)paramView.findViewById(2131231166));
      paramViewGroup.tvLogisticsInfo = ((TextView)paramView.findViewById(2131231167));
      paramViewGroup.tvLogisticsTime = ((TextView)paramView.findViewById(2131231168));
      paramView.setTag(paramViewGroup);
      localOrderTrackEntity = (Logistics.DataEntity.OrderTrackEntity)getItem(paramInt);
      if (paramInt != 0)
        break label146;
      paramViewGroup.view1.setVisibility(4);
      paramViewGroup.imgTagLatestLogistics.setImageResource(2130837887);
    }
    while (true)
    {
      paramViewGroup.tvLogisticsInfo.setText(localOrderTrackEntity.getContent());
      paramViewGroup.tvLogisticsTime.setText(localOrderTrackEntity.getMsgTime());
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
      label146: paramViewGroup.view1.setVisibility(0);
      paramViewGroup.imgTagLatestLogistics.setImageResource(2130837556);
    }
  }

  public void setData(List<Logistics.DataEntity.OrderTrackEntity> paramList)
  {
    this.logistics = paramList;
    notifyDataSetChanged();
  }

  class ViewHolder
  {
    ImageView imgTagLatestLogistics;
    TextView tvLogisticsInfo;
    TextView tvLogisticsTime;
    View view1;

    ViewHolder()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.LogisticsAdapter
 * JD-Core Version:    0.6.2
 */