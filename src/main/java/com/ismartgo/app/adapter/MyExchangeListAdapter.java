package com.ismartgo.app.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.grid.view.BorderImageView;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
import java.util.ArrayList;

public class MyExchangeListAdapter extends BaseAdapter
{
  private Context context;
  private ArrayList<Gift> giftList = new ArrayList();
  private LayoutInflater mInflater;

  public MyExchangeListAdapter(Context paramContext, ArrayList<Gift> paramArrayList)
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
    return ((Gift)this.giftList.get(paramInt)).getGiftId();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Gift localGift;
    if (paramView == null)
    {
      paramViewGroup = new ViewHolder(null);
      paramView = this.mInflater.inflate(2130903144, null);
      paramViewGroup.iv_pic = ((BorderImageView)paramView.findViewById(2131230877));
      paramViewGroup.tv_name = ((TextView)paramView.findViewById(2131230878));
      paramViewGroup.tv_flag = ((TextView)paramView.findViewById(2131230979));
      paramViewGroup.tv_beans = ((TextView)paramView.findViewById(2131230783));
      paramViewGroup.tv_date = ((TextView)paramView.findViewById(2131230923));
      paramViewGroup.tv_num = ((TextView)paramView.findViewById(2131230972));
      paramViewGroup.iv_pic.setBorderWidth(1.5F);
      paramViewGroup.iv_pic.setColour(paramView.getResources().getColor(2131099698));
      paramView.setTag(paramViewGroup);
      localGift = (Gift)this.giftList.get(paramInt);
      paramViewGroup.tv_name.setText(localGift.getGiftName());
      paramViewGroup.tv_flag.setText(localGift.getFlag());
      paramViewGroup.tv_num.setText("数量:  x" + String.valueOf(localGift.getCount()));
      paramViewGroup.tv_beans.setText("豆数:  " + String.valueOf(localGift.getRequiredBean()));
      if ((!"null".equals(localGift.getDate())) && (!localGift.getDate().equals(null)))
        break label298;
      paramViewGroup.tv_date.setVisibility(4);
    }
    while (true)
    {
      ImgLoader.getInstance(this.context).showPic(StringUtils.getImgUrl(localGift.getGiftLogo()), paramViewGroup.iv_pic, false);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
      label298: paramViewGroup.tv_date.setText("兑换时间:  " + localGift.getDate().substring(0, localGift.getDate().lastIndexOf(":")));
    }
  }

  public void setList(ArrayList<Gift> paramArrayList)
  {
    this.giftList = paramArrayList;
    notifyDataSetChanged();
  }

  private static class ViewHolder
  {
    BorderImageView iv_pic;
    TextView tv_No;
    TextView tv_beans;
    TextView tv_date;
    TextView tv_flag;
    TextView tv_name;
    TextView tv_num;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.MyExchangeListAdapter
 * JD-Core Version:    0.6.2
 */