package com.ismartgo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;

public class ReceiptMonthAdapter extends BaseAdapter
{
  private Context context;
  private List<HashMap<String, String>> dateList;

  public ReceiptMonthAdapter(List<HashMap<String, String>> paramList, Context paramContext)
  {
    this.dateList = paramList;
    this.context = paramContext;
  }

  public int getCount()
  {
    if (this.dateList == null)
      return 0;
    return this.dateList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.dateList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramViewGroup = new ViewHolder();
      paramView = LayoutInflater.from(this.context).inflate(2130903152, null);
      paramViewGroup.tvMonth = ((TextView)paramView.findViewById(2131231190));
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      HashMap localHashMap = (HashMap)this.dateList.get(paramInt);
      paramViewGroup.tvMonth.setText((String)localHashMap.get("year") + "年" + (String)localHashMap.get("month") + "月");
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
  }

  public void setDateList(List<HashMap<String, String>> paramList)
  {
    this.dateList = paramList;
    notifyDataSetChanged();
  }

  class ViewHolder
  {
    TextView tvMonth;

    ViewHolder()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.ReceiptMonthAdapter
 * JD-Core Version:    0.6.2
 */