package com.ismartgo.app.address;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ismartgo.app.bean.Address;
import java.util.List;

@SuppressLint({"InflateParams"})
public class ListRegionAdapter extends BaseAdapter
{
  private List<Address> addressList;
  private LayoutInflater inflater;

  public ListRegionAdapter(Context paramContext, List<Address> paramList)
  {
    this.addressList = paramList;
    this.inflater = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    if (this.addressList == null)
      return 0;
    return this.addressList.size();
  }

  public Object getItem(int paramInt)
  {
    return null;
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
      paramView = this.inflater.inflate(2130903150, null);
      paramViewGroup.txt = ((TextView)paramView.findViewById(2131231188));
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      Address localAddress = (Address)this.addressList.get(paramInt);
      paramViewGroup.txt.setText(localAddress.getName());
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
  }

  public void setList(List<Address> paramList)
  {
    this.addressList = paramList;
  }

  public final class ViewHolder
  {
    public TextView txt;

    public ViewHolder()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.address.ListRegionAdapter
 * JD-Core Version:    0.6.2
 */