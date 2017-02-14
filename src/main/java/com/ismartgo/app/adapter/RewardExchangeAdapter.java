package com.ismartgo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ismartgo.app.activity.WebViewActivity;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.grid.utils.DisplayUtil;
import com.ismartgo.app.tools.ImgLoader;
import java.util.List;

public class RewardExchangeAdapter extends BaseAdapter
{
  private static boolean isLoad = true;
  private Context context;
  private int end_index;
  private List<Gift> giftList;
  private LayoutInflater inflater;
  private int itemWidth;
  private GridView mGridView;
  private int start_index;

  public RewardExchangeAdapter(Context paramContext, List<Gift> paramList, GridView paramGridView)
  {
    this.context = paramContext;
    this.giftList = paramList;
    this.inflater = LayoutInflater.from(paramContext);
    this.itemWidth = DisplayUtil.getItemWidth1(paramContext);
    this.mGridView = paramGridView;
  }

  public int getCount()
  {
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
      paramViewGroup = new ViewHolder();
      paramView = this.inflater.inflate(2130903142, null);
      paramViewGroup.layout_exchange_1 = paramView.findViewById(2131231169);
      paramViewGroup.imgLogo = ((ImageView)paramViewGroup.layout_exchange_1.findViewById(2131230957));
      paramViewGroup.tvName = ((TextView)paramViewGroup.layout_exchange_1.findViewById(2131230878));
      paramViewGroup.tvBeans = ((TextView)paramViewGroup.layout_exchange_1.findViewById(2131230783));
      paramViewGroup.imgNewGiftTag = ((ImageView)paramViewGroup.layout_exchange_1.findViewById(2131231195));
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      Object localObject = (LinearLayout.LayoutParams)paramViewGroup.layout_exchange_1.getLayoutParams();
      ((LinearLayout.LayoutParams)localObject).width = this.itemWidth;
      paramViewGroup.layout_exchange_1.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localObject = (Gift)this.giftList.get(paramInt);
      paramViewGroup.tvName.setText(((Gift)localObject).getGiftName());
      paramViewGroup.tvBeans.setText(((Gift)localObject).getRequiredBean() + "豆");
      paramViewGroup.imgLogo.setTag(Integer.valueOf(paramInt));
      ImgLoader.getInstance(this.context).showPic(((Gift)localObject).getGiftLogo(), paramViewGroup.imgLogo, false);
      paramViewGroup.layout_exchange_1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(RewardExchangeAdapter.this.context, WebViewActivity.class);
          paramAnonymousView.putExtra("gift", this.val$gift);
          RewardExchangeAdapter.this.context.startActivity(paramAnonymousView);
        }
      });
      if (((Gift)localObject).getNewGift() != 1)
        break;
      paramViewGroup.imgNewGiftTag.setVisibility(0);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
    paramViewGroup.imgNewGiftTag.setVisibility(8);
    return paramView;
  }

  class ViewHolder
  {
    ImageView imgLogo;
    ImageView imgNewGiftTag;
    View layout_exchange_1;
    TextView tvBeans;
    TextView tvName;

    ViewHolder()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.RewardExchangeAdapter
 * JD-Core Version:    0.6.2
 */