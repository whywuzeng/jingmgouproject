package com.ismartgo.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ismartgo.app.bean.Message;
import java.util.ArrayList;

@SuppressLint({"InflateParams"})
public class MsgListAdapter extends BaseAdapter
{
  private LayoutInflater mInflater;
  private ArrayList<Message> msgList = new ArrayList();

  public MsgListAdapter(Context paramContext, ArrayList<Message> paramArrayList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.msgList = paramArrayList;
  }

  public int getCount()
  {
    if (this.msgList == null)
      return 0;
    return this.msgList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.msgList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return ((Message)this.msgList.get(paramInt)).getId();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramViewGroup = new ViewHolder(null);
      paramView = this.mInflater.inflate(2130903143, null);
      paramViewGroup.tv_title = ((TextView)paramView.findViewById(2131231001));
      paramViewGroup.tv_date = ((TextView)paramView.findViewById(2131230923));
      paramViewGroup.imgMsgState = ((ImageView)paramView.findViewById(2131231170));
      paramView.setTag(paramViewGroup);
      if (((Message)this.msgList.get(paramInt)).getIsRead() != 1)
        break label271;
      paramViewGroup.imgMsgState.setImageResource(2130837800);
    }
    while (true)
    {
      paramViewGroup.tv_title.setText(((Message)this.msgList.get(paramInt)).getTitle());
      if (((Message)this.msgList.get(paramInt)).getTime().equals(""))
        break label283;
      String[] arrayOfString2 = ((Message)this.msgList.get(paramInt)).getTime().split(" ");
      String[] arrayOfString1 = arrayOfString2[0].split("-");
      arrayOfString2 = arrayOfString2[1].split(":");
      paramViewGroup.tv_date.setText(arrayOfString1[0].substring(2, 4) + "-" + arrayOfString1[1] + "-" + arrayOfString1[2] + " " + arrayOfString2[0] + ":" + arrayOfString2[1]);
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      break;
      label271: paramViewGroup.imgMsgState.setImageResource(2130837799);
    }
    label283: paramViewGroup.tv_date.setText(((Message)this.msgList.get(paramInt)).getTime());
    return paramView;
  }

  public void setList(ArrayList<Message> paramArrayList)
  {
    this.msgList = paramArrayList;
    notifyDataSetChanged();
  }

  private static class ViewHolder
  {
    ImageView imgMsgState;
    TextView tv_date;
    TextView tv_title;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.MsgListAdapter
 * JD-Core Version:    0.6.2
 */