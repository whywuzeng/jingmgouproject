package com.ismartgo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ismartgo.app.bean.MyKeyBoard;
import com.ismartgo.app.common.CommonMethod;
import java.util.List;

public class KeyBoardAdapter extends BaseAdapter
{
  private Context context;
  private LayoutInflater inflater;
  private List<MyKeyBoard> keyList;

  public KeyBoardAdapter(Context paramContext, List<MyKeyBoard> paramList)
  {
    this.context = paramContext;
    this.keyList = paramList;
    this.inflater = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    if (this.keyList == null)
      return 0;
    return this.keyList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.keyList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.inflater.inflate(2130903140, null);
      paramViewGroup = new ViewHolder();
      paramViewGroup.tvNum = ((TextView)paramView.findViewById(2131230972));
      paramViewGroup.imgFeature = ((ImageView)paramView.findViewById(2131231164));
      paramView.setTag(paramViewGroup);
    }
    MyKeyBoard localMyKeyBoard;
    while (true)
    {
      localMyKeyBoard = (MyKeyBoard)getItem(paramInt);
      if (localMyKeyBoard.isFeatures())
        break;
      paramViewGroup.tvNum.setVisibility(0);
      paramViewGroup.imgFeature.setVisibility(8);
      paramViewGroup.tvNum.setText(localMyKeyBoard.getText());
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
    if (CommonMethod.isEmpty(localMyKeyBoard.getText()))
    {
      paramViewGroup.tvNum.setVisibility(8);
      paramViewGroup.imgFeature.setVisibility(0);
      paramViewGroup.imgFeature.setImageResource(localMyKeyBoard.getFeaturdResource());
      return paramView;
    }
    paramViewGroup.tvNum.setVisibility(0);
    paramViewGroup.imgFeature.setVisibility(8);
    paramViewGroup.tvNum.setText(localMyKeyBoard.getText());
    return paramView;
  }

  class ViewHolder
  {
    ImageView imgFeature;
    TextView tvNum;

    ViewHolder()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.KeyBoardAdapter
 * JD-Core Version:    0.6.2
 */