package com.ismartgo.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ismartgo.app.bean.Gift;
import java.util.ArrayList;

@SuppressLint({"InflateParams"})
public class CunsumptionCouponListAdapter extends BaseAdapter
{
  private Context context;
  private ArrayList<Gift> giftList = new ArrayList();
  private LayoutInflater mInflater;

  public CunsumptionCouponListAdapter(Context paramContext, ArrayList<Gift> paramArrayList)
  {
    this.context = paramContext;
    this.mInflater = LayoutInflater.from(paramContext);
    this.giftList = paramArrayList;
  }

  public int getCount()
  {
    if (this.giftList == null)
      return 0;
    return this.giftList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.giftList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramViewGroup = new ViewHolder(null);
      paramView = this.mInflater.inflate(2130903136, null);
      paramViewGroup.tv_name = ((TextView)paramView.findViewById(2131230878));
      paramViewGroup.tv_number = ((TextView)paramView.findViewById(2131230922));
      paramViewGroup.tv_date = ((TextView)paramView.findViewById(2131230923));
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      Gift localGift = (Gift)this.giftList.get(paramInt);
      paramViewGroup.tv_name.setText(localGift.getGiftName());
      paramViewGroup.tv_number.setText("券号: " + localGift.getGiftNumber());
      paramViewGroup.tv_date.setText("有效期至:  " + localGift.getDate());
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
  }

  public void setList(ArrayList<Gift> paramArrayList)
  {
    this.giftList = paramArrayList;
    notifyDataSetChanged();
  }

  private static class ViewHolder
  {
    TextView tv_date;
    TextView tv_name;
    TextView tv_number;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.CunsumptionCouponListAdapter
 * JD-Core Version:    0.6.2
 */