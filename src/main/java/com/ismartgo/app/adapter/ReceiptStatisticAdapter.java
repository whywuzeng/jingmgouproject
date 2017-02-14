package com.ismartgo.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ismartgo.app.bean.ReceiptMode;
import com.ismartgo.app.bean.ReceiptStatisticInfo;
import com.ismartgo.app.bean.ReceiptStatisticInfo.Items;
import com.ismartgo.app.tools.ImgLoader;
import com.yolanda.nohttp.Logger;
import java.util.List;

public class ReceiptStatisticAdapter extends BaseAdapter
{
  private Context context;
  private ReceiptStatisticInfo infos;
  private List<ReceiptMode> list;
  double money;

  public ReceiptStatisticAdapter(Context paramContext, ReceiptStatisticInfo paramReceiptStatisticInfo)
  {
    this.context = paramContext;
    this.infos = paramReceiptStatisticInfo;
  }

  public int getCount()
  {
    return this.infos.getItems().size();
  }

  public Object getItem(int paramInt)
  {
    return this.infos.getItems().get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ReceiptStatisticInfo.Items localItems;
    ReceiptMode localReceiptMode;
    if (paramView == null)
    {
      paramViewGroup = new ViewHolder();
      paramView = LayoutInflater.from(this.context).inflate(2130903153, null);
      paramViewGroup.ivReceipt = ((ImageView)paramView.findViewById(2131231191));
      paramViewGroup.tvType = ((TextView)paramView.findViewById(2131231192));
      paramViewGroup.tvMoney = ((TextView)paramView.findViewById(2131231194));
      paramViewGroup.lpLong = ((ProgressBar)paramView.findViewById(2131231193));
      paramView.setTag(paramViewGroup);
      localItems = (ReceiptStatisticInfo.Items)this.infos.getItems().get(paramInt);
      localReceiptMode = new ReceiptMode();
      paramInt = 0;
      label113: if (paramInt < this.list.size())
        break label310;
      label126: TextView localTextView = paramViewGroup.tvType;
      if (localReceiptMode.getTitle() != null)
        break label395;
      localObject = "";
      label144: localTextView.setText((CharSequence)localObject);
      paramViewGroup.tvMoney.setText(localItems.getMoney());
      paramViewGroup.lpLong.setVisibility(0);
      if (!localReceiptMode.isHasSetDrawable())
        if (localReceiptMode.getColor() != null)
          break label405;
    }
    label395: label405: for (Object localObject = "#000000"; ; localObject = localReceiptMode.getColor())
    {
      localObject = new ClipDrawable(new ColorDrawable(Color.parseColor((String)localObject)), 3, 1);
      paramViewGroup.lpLong.setProgressDrawable((Drawable)localObject);
      localReceiptMode.setHasSetDrawable(true);
      int i = (int)(localItems.getMoney() / this.money * 100.0D);
      paramInt = i;
      if (i == 0)
        paramInt = 1;
      paramViewGroup.lpLong.setProgress(paramInt);
      ImgLoader.getInstance(this.context).showPic(localReceiptMode.getUrl(), paramViewGroup.ivReceipt, false);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
      label310: if (localItems.getId() == ((ReceiptMode)this.list.get(paramInt)).getId())
      {
        localReceiptMode = (ReceiptMode)this.list.get(paramInt);
        Logger.i("mode: " + localReceiptMode.getTitle() + " - " + localReceiptMode.getColor());
        break label126;
      }
      paramInt += 1;
      break label113;
      localObject = localReceiptMode.getTitle();
      break label144;
    }
  }

  public void setInfos(ReceiptStatisticInfo paramReceiptStatisticInfo, List<ReceiptMode> paramList)
  {
    this.infos = paramReceiptStatisticInfo;
    this.list = paramList;
    int i;
    if ((paramList != null) && (paramReceiptStatisticInfo.getItems().size() > 0))
    {
      i = 0;
      if (i < paramReceiptStatisticInfo.getItems().size());
    }
    else
    {
      notifyDataSetChanged();
      return;
    }
    if (i != 0)
      if (((ReceiptStatisticInfo.Items)paramReceiptStatisticInfo.getItems().get(i)).getMoney() <= ((ReceiptStatisticInfo.Items)paramReceiptStatisticInfo.getItems().get(i - 1)).getMoney());
    for (this.money = ((ReceiptStatisticInfo.Items)paramReceiptStatisticInfo.getItems().get(i)).getMoney(); ; this.money = ((ReceiptStatisticInfo.Items)paramReceiptStatisticInfo.getItems().get(i)).getMoney())
    {
      i += 1;
      break;
    }
  }

  class ViewHolder
  {
    ImageView ivReceipt;
    ProgressBar lpLong;
    TextView tvMoney;
    TextView tvType;

    ViewHolder()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.ReceiptStatisticAdapter
 * JD-Core Version:    0.6.2
 */