package com.ismartgo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ismartgo.app.activity.ModifyReceiptActivity;
import com.ismartgo.app.activity.MyReceiptListActivity;
import com.ismartgo.app.activity.ShootReceiptActivity;
import com.ismartgo.app.bean.NewReceiptInfo;
import com.ismartgo.app.bean.ReceiptDayInfo;
import com.ismartgo.app.bean.ReceiptItems;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.url.Url;
import java.util.ArrayList;
import java.util.List;

public class MyReceiptAdapter extends BaseAdapter
{
  private Context context;
  private List<NewReceiptInfo> infos = new ArrayList();

  public MyReceiptAdapter(Context paramContext, List<NewReceiptInfo> paramList)
  {
    this.context = paramContext;
    this.infos = paramList;
  }

  public int getCount()
  {
    return this.infos.size();
  }

  public Object getItem(int paramInt)
  {
    return this.infos.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    if (((NewReceiptInfo)this.infos.get(paramInt)).getType() == 0)
      return 0;
    return 1;
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    if (getItemViewType(paramInt) == 0)
    {
      if (paramView == null)
      {
        paramViewGroup = new ViewHolder2();
        paramView = LayoutInflater.from(this.context).inflate(2130903145, null);
        paramViewGroup.ivType = ((ImageView)paramView.findViewById(2131231173));
        paramViewGroup.tvType = ((TextView)paramView.findViewById(2131231174));
        paramViewGroup.view1 = paramView.findViewById(2131231172);
        paramViewGroup.view2 = paramView.findViewById(2131231176);
        paramViewGroup.tvReceiptMoney = ((TextView)paramView.findViewById(2131231175));
        paramView.setTag(paramViewGroup);
        localObject = ((NewReceiptInfo)this.infos.get(paramInt)).getItems();
        ImgLoader.getInstance(this.context).showPic(((ReceiptItems)localObject).getShopTypeUrl(), paramViewGroup.ivType, true);
        paramViewGroup.ivType.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new Intent(MyReceiptAdapter.this.context, ModifyReceiptActivity.class);
            paramAnonymousView.putExtra("intentFlag", 1);
            paramAnonymousView.putExtra("ReceiptInfo", ((NewReceiptInfo)MyReceiptAdapter.this.infos.get(paramInt)).getItems());
            ((MyReceiptListActivity)MyReceiptAdapter.this.context).startActivityForResult(paramAnonymousView, 1);
          }
        });
        switch (((NewReceiptInfo)this.infos.get(paramInt)).getFlag())
        {
        default:
          paramViewGroup.view1.setVisibility(8);
          paramViewGroup.view2.setVisibility(8);
        case 0:
        case 1:
        case 2:
        }
      }
      while (true)
      {
        paramViewGroup.tvReceiptMoney.setText(((ReceiptItems)localObject).getMoney());
        paramViewGroup.tvType.setText(((ReceiptItems)localObject).getShopTypeName());
        return paramView;
        paramViewGroup = (ViewHolder2)paramView.getTag();
        break;
        paramViewGroup.view1.setVisibility(0);
        paramViewGroup.view2.setVisibility(0);
        continue;
        paramViewGroup.view1.setVisibility(0);
        paramViewGroup.view2.setVisibility(8);
        continue;
        paramViewGroup.view1.setVisibility(8);
        paramViewGroup.view2.setVisibility(0);
      }
    }
    if (paramView == null)
    {
      paramViewGroup = new ViewHolder1();
      paramView = LayoutInflater.from(this.context).inflate(2130903146, null);
      paramViewGroup.btLottery = ((Button)paramView.findViewById(2131231179));
      paramViewGroup.tvDate = ((TextView)paramView.findViewById(2131231178));
      paramViewGroup.tvMoney = ((TextView)paramView.findViewById(2131231180));
      paramView.setTag(paramViewGroup);
      localObject = ((NewReceiptInfo)this.infos.get(paramInt)).getDayInfo();
      if (paramInt != 0)
        break label489;
      paramViewGroup.btLottery.setVisibility(0);
      paramViewGroup.btLottery.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(MyReceiptAdapter.this.context, ShootReceiptActivity.class);
          paramAnonymousView.putExtra("url", Url.MY_RECEIPT_LOTTERY);
          MyReceiptAdapter.this.context.startActivity(paramAnonymousView);
        }
      });
    }
    while (true)
    {
      paramViewGroup.tvDate.setText(((ReceiptDayInfo)localObject).getTime());
      paramViewGroup.tvMoney.setText(((ReceiptDayInfo)localObject).getMoney());
      return paramView;
      paramViewGroup = (ViewHolder1)paramView.getTag();
      break;
      label489: if (paramViewGroup.btLottery.getVisibility() == 0)
        paramViewGroup.btLottery.setVisibility(8);
    }
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  public void setInfos(List<NewReceiptInfo> paramList)
  {
    this.infos = paramList;
    notifyDataSetChanged();
  }

  class ViewHolder1
  {
    Button btLottery;
    TextView tvDate;
    TextView tvMoney;

    ViewHolder1()
    {
    }
  }

  class ViewHolder2
  {
    ImageView ivType;
    TextView tvReceiptMoney;
    TextView tvType;
    View view1;
    View view2;

    ViewHolder2()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.MyReceiptAdapter
 * JD-Core Version:    0.6.2
 */