package com.ismartgo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ismartgo.app.bean.ReceiptMode;
import com.ismartgo.app.grid.CircleImageView;
import com.ismartgo.app.tools.ImgLoader;
import java.util.List;

public class ReceiptModeAdapter extends BaseAdapter
{
  private Context context;
  private LayoutInflater inflater;
  private List<ReceiptMode> receiptModeList;

  public ReceiptModeAdapter(Context paramContext, List<ReceiptMode> paramList)
  {
    this.context = paramContext;
    this.receiptModeList = paramList;
    this.inflater = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    if (this.receiptModeList == null)
      return 0;
    return this.receiptModeList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.receiptModeList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.inflater.inflate(2130903151, null);
      paramViewGroup = new ViewHolder();
      paramViewGroup.imgReceiptMode = ((CircleImageView)paramView.findViewById(2131231189));
      paramViewGroup.tvTitle = ((TextView)paramView.findViewById(2131231001));
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      ReceiptMode localReceiptMode = (ReceiptMode)this.receiptModeList.get(paramInt);
      paramViewGroup.tvTitle.setText(localReceiptMode.getTitle());
      ImgLoader.getInstance(this.context).showPic(localReceiptMode.getUrl(), paramViewGroup.imgReceiptMode, false);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
  }

  public void setReceiptModeList(List<ReceiptMode> paramList)
  {
    this.receiptModeList = paramList;
    notifyDataSetChanged();
  }

  class ViewHolder
  {
    CircleImageView imgReceiptMode;
    TextView tvTitle;

    ViewHolder()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.ReceiptModeAdapter
 * JD-Core Version:    0.6.2
 */