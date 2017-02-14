package com.ismartgo.app.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ismartgo.app.activity.ScanInStoreGoodsActivity;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.grid.utils.DistanceConversionUtils;
import com.ismartgo.app.map.BaiduMapActivity;
import com.ismartgo.app.tools.ImgLoader;
import java.util.List;

public class CouldSignInAdapter extends BaseAdapter
{
  Activity activity;
  Context context;
  LayoutInflater inflater;
  List<Store> merList;

  public CouldSignInAdapter(Context paramContext, Activity paramActivity, List<Store> paramList)
  {
    this.activity = paramActivity;
    this.context = paramContext;
    this.merList = paramList;
    this.inflater = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    if (this.merList == null)
      return 0;
    return this.merList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.merList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  @SuppressLint({"InflateParams"})
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = new ViewHolder();
    if (paramView == null)
    {
      paramView = this.inflater.inflate(2130903221, null);
      paramViewGroup.sign_layout = ((RelativeLayout)paramView.findViewById(2131231398));
      paramViewGroup.store_logo = ((ImageView)paramView.findViewById(2131231296));
      paramViewGroup.store_signin = ((ImageView)paramView.findViewById(2131231300));
      paramViewGroup.store_name = ((TextView)paramView.findViewById(2131231297));
      paramViewGroup.store_address = ((TextView)paramView.findViewById(2131231298));
      paramViewGroup.store_distance = ((TextView)paramView.findViewById(2131231299));
      paramViewGroup.tv_beans = ((TextView)paramView.findViewById(2131230783));
      paramViewGroup.tv_total_bean = ((TextView)paramView.findViewById(2131231400));
      paramViewGroup.tv_remain_bean = ((TextView)paramView.findViewById(2131231401));
      paramView.setTag(paramViewGroup);
      paramViewGroup.sign_layout.setOnClickListener(new onClickListener(paramInt));
      if (this.merList.size() > paramInt)
      {
        ImgLoader.getInstance(this.context).showPic(((Store)this.merList.get(paramInt)).getShopLogo(), paramViewGroup.store_logo, false);
        paramViewGroup.store_logo.setOnClickListener(new onClickListener(paramInt));
        paramViewGroup.store_name.setText(((Store)this.merList.get(paramInt)).getShopName());
        paramViewGroup.store_address.setText(((Store)this.merList.get(paramInt)).getShopAddress());
        paramViewGroup.store_distance.setText(DistanceConversionUtils.getDistance(((Store)this.merList.get(paramInt)).getDistance()));
        paramViewGroup.tv_beans.setText(String.valueOf(((Store)this.merList.get(paramInt)).getBeans()) + "豆");
        if (!((Store)this.merList.get(paramInt)).getUserIsSign().equals("Y"))
          break label587;
        paramViewGroup.store_signin.setVisibility(0);
        paramViewGroup.store_signin.setOnClickListener(new onClickListener(paramInt));
      }
    }
    while (true)
    {
      paramViewGroup.store_signin.setOnClickListener(new onClickListener(paramInt));
      paramViewGroup.store_distance.setOnClickListener(new onClickListener(paramInt));
      paramViewGroup.tv_total_bean.setText(this.context.getResources().getString(2131296402, new Object[] { ((Store)this.merList.get(paramInt)).getTotalBean() }));
      if ((((Store)this.merList.get(paramInt)).getRemainBean() <= 0) || (((Store)this.merList.get(paramInt)).getRemainBean() < ((Store)this.merList.get(paramInt)).getBeans()))
        break label598;
      paramViewGroup.tv_remain_bean.setText(this.context.getResources().getString(2131296403, new Object[] { ((Store)this.merList.get(paramInt)).getRemainBean() + "豆" }));
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
      label587: paramViewGroup.store_signin.setVisibility(4);
    }
    label598: paramViewGroup.tv_remain_bean.setText(this.context.getResources().getString(2131296403, new Object[] { "已经被领完啦" }));
    return paramView;
  }

  public void notifyChangeData(List<Store> paramList)
  {
    this.merList = paramList;
    notifyDataSetChanged();
  }

  public class ViewHolder
  {
    private TextView display_earn_beanscount;
    private RelativeLayout sign_layout;
    private TextView store_address;
    private TextView store_distance;
    private ImageView store_logo;
    private TextView store_name;
    private ImageView store_signin;
    TextView tv_beans;
    TextView tv_remain_bean;
    TextView tv_total_bean;

    public ViewHolder()
    {
    }
  }

  class onClickListener
    implements View.OnClickListener
  {
    int position;

    public onClickListener(int arg2)
    {
      int i;
      this.position = i;
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
      case 2131231296:
      case 2131231398:
      case 2131231299:
      case 2131231300:
      }
      do
      {
        do
          return;
        while ((CouldSignInAdapter.this.merList == null) || (CouldSignInAdapter.this.merList.size() == 0));
        Intent localIntent = new Intent();
        localIntent.setClass(CouldSignInAdapter.this.context, ScanInStoreGoodsActivity.class);
        localIntent.putExtra("isInShop", false);
        localIntent.putExtra("shopId", ((Store)CouldSignInAdapter.this.merList.get(this.position)).getShopId());
        localIntent.putExtra("shopName", ((Store)CouldSignInAdapter.this.merList.get(this.position)).getShopName());
        localIntent.putExtra("beanNumber", ((Store)CouldSignInAdapter.this.merList.get(this.position)).getBeans());
        localIntent.setFlags(276824064);
        paramView.getContext().startActivity(localIntent);
        return;
      }
      while ((CouldSignInAdapter.this.merList == null) || (CouldSignInAdapter.this.merList.size() == 0));
      paramView = new Intent();
      paramView.setClass(CouldSignInAdapter.this.context, BaiduMapActivity.class);
      paramView.putExtra("storeLon", ((Store)CouldSignInAdapter.this.merList.get(this.position)).getLon());
      paramView.putExtra("storeLat", ((Store)CouldSignInAdapter.this.merList.get(this.position)).getLat());
      paramView.putExtra("shopName", ((Store)CouldSignInAdapter.this.merList.get(this.position)).getShopName());
      paramView.putExtra("storeAddress", ((Store)CouldSignInAdapter.this.merList.get(this.position)).getShopAddress());
      paramView.setFlags(268435456);
      CouldSignInAdapter.this.context.getApplicationContext().startActivity(paramView);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.CouldSignInAdapter
 * JD-Core Version:    0.6.2
 */