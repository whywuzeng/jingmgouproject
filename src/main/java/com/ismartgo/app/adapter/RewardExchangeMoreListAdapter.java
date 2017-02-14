package com.ismartgo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ismartgo.app.activity.RewardExchangeMoreActivity;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.tools.ImgLoader;
import java.util.LinkedList;
import java.util.List;

public class RewardExchangeMoreListAdapter extends BaseAdapter
{
  private RewardExchangeMoreActivity activity;
  private Context context;
  private List<Gift> giftList;
  private LinkedList<Gift> giftList1;
  private LinkedList<Gift> giftList2;
  private LayoutInflater mInflater;

  public RewardExchangeMoreListAdapter(Context paramContext, RewardExchangeMoreActivity paramRewardExchangeMoreActivity, LinkedList<Gift> paramLinkedList1, LinkedList<Gift> paramLinkedList2)
  {
    this.activity = paramRewardExchangeMoreActivity;
    this.context = paramContext;
    this.mInflater = LayoutInflater.from(paramContext);
    this.giftList1 = paramLinkedList1;
    this.giftList2 = paramLinkedList2;
  }

  public RewardExchangeMoreListAdapter(Context paramContext, LinkedList<Gift> paramLinkedList1, LinkedList<Gift> paramLinkedList2)
  {
    this.context = paramContext;
    this.mInflater = LayoutInflater.from(paramContext);
    this.giftList1 = paramLinkedList1;
    this.giftList2 = paramLinkedList2;
  }

  public RewardExchangeMoreListAdapter(Context paramContext, List<Gift> paramList)
  {
    this.context = paramContext;
    this.mInflater = LayoutInflater.from(paramContext);
    this.giftList = paramList;
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
    return ((Gift)this.giftList.get(paramInt)).getGiftId();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramViewGroup = new ViewHolder(null);
      paramView = this.mInflater.inflate(2130903142, null);
      paramViewGroup.imgLogo = ((ImageView)paramView.findViewById(2131230957));
      paramViewGroup.tvName = ((TextView)paramView.findViewById(2131230878));
      paramViewGroup.tvBeans = ((TextView)paramView.findViewById(2131230783));
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      Gift localGift = (Gift)this.giftList.get(paramInt);
      paramViewGroup.tvName.setText(localGift.getGiftName());
      paramViewGroup.tvBeans.setText(localGift.getRequiredBean() + "豆");
      ImgLoader.getInstance(this.context).showPic(localGift.getGiftLogo(), paramViewGroup.imgLogo, false);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
  }

  public void setData(LinkedList<Gift> paramLinkedList1, LinkedList<Gift> paramLinkedList2)
  {
    this.giftList1 = paramLinkedList1;
    this.giftList2 = paramLinkedList2;
    notifyDataSetChanged();
  }

  public void setData(List<Gift> paramList)
  {
    this.giftList = paramList;
    notifyDataSetChanged();
  }

  public void setList(LinkedList<Gift> paramLinkedList1, LinkedList<Gift> paramLinkedList2)
  {
    this.giftList1 = paramLinkedList1;
    this.giftList2 = paramLinkedList2;
    notifyDataSetChanged();
  }

  private static class ViewHolder
  {
    ImageView imgLogo;
    View layout_exchange_1;
    View layout_exchange_2;
    TextView tvBeans;
    TextView tvName;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.RewardExchangeMoreListAdapter
 * JD-Core Version:    0.6.2
 */