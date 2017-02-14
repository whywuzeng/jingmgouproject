package com.ismartgo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class SearchExpandTextAdapter extends ArrayAdapter<String>
{
  private String[] mArrayData;
  private Context mContext;
  private List<String> mListData;
  private OnItemClickListener mOnItemClickListener;
  private int normalDrawbleId;
  private View.OnClickListener onClickListener;
  private int selectedDrawble;
  private int selectedPos = -1;
  private String selectedText = "";
  private float textSize;

  public SearchExpandTextAdapter(Context paramContext, List<String> paramList, int paramInt1, int paramInt2)
  {
    super(paramContext, 2131296367, paramList);
    this.mContext = paramContext;
    this.mListData = paramList;
    this.normalDrawbleId = paramInt2;
    init();
  }

  public SearchExpandTextAdapter(Context paramContext, String[] paramArrayOfString, int paramInt1, int paramInt2)
  {
    super(paramContext, 2131296367, paramArrayOfString);
    this.mContext = paramContext;
    this.mArrayData = paramArrayOfString;
    this.normalDrawbleId = paramInt2;
    init();
  }

  private void init()
  {
    this.onClickListener = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SearchExpandTextAdapter.this.selectedPos = ((Integer)paramAnonymousView.getTag()).intValue();
        SearchExpandTextAdapter.this.setSelectedPosition(SearchExpandTextAdapter.this.selectedPos);
        if (SearchExpandTextAdapter.this.mOnItemClickListener != null)
          SearchExpandTextAdapter.this.mOnItemClickListener.onItemClick(paramAnonymousView, SearchExpandTextAdapter.this.selectedPos);
      }
    };
  }

  public int getSelectedPosition()
  {
    if ((this.mArrayData != null) && (this.selectedPos < this.mArrayData.length))
      return this.selectedPos;
    if ((this.mListData != null) && (this.selectedPos < this.mListData.size()))
      return this.selectedPos;
    return -1;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    if (paramView == null)
      paramViewGroup = LayoutInflater.from(this.mContext).inflate(2130903185, null);
    TextView localTextView = (TextView)paramViewGroup.findViewById(2131231290);
    localTextView.setTag(Integer.valueOf(paramInt));
    String str = "";
    if (this.mListData != null)
    {
      paramView = str;
      if (paramInt < this.mListData.size())
        paramView = (String)this.mListData.get(paramInt);
    }
    while (true)
    {
      localTextView.setText(paramView);
      localTextView.setTextSize(2, this.textSize);
      localTextView.setOnClickListener(this.onClickListener);
      return paramViewGroup;
      paramView = str;
      if (this.mArrayData != null)
      {
        paramView = str;
        if (paramInt < this.mArrayData.length)
          paramView = this.mArrayData[paramInt];
      }
    }
  }

  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener)
  {
    this.mOnItemClickListener = paramOnItemClickListener;
  }

  public void setSelectedPosition(int paramInt)
  {
    if ((this.mListData != null) && (paramInt < this.mListData.size()))
    {
      this.selectedPos = paramInt;
      this.selectedText = ((String)this.mListData.get(paramInt));
      notifyDataSetChanged();
    }
    while ((this.mArrayData == null) || (paramInt >= this.mArrayData.length))
      return;
    this.selectedPos = paramInt;
    this.selectedText = this.mArrayData[paramInt];
    notifyDataSetChanged();
  }

  public void setSelectedPositionNoNotify(int paramInt)
  {
    this.selectedPos = paramInt;
    if ((this.mListData != null) && (paramInt < this.mListData.size()))
      this.selectedText = ((String)this.mListData.get(paramInt));
    while ((this.mArrayData == null) || (paramInt >= this.mArrayData.length))
      return;
    this.selectedText = this.mArrayData[paramInt];
  }

  public void setTextSize(float paramFloat)
  {
    this.textSize = paramFloat;
  }

  public static abstract interface OnItemClickListener
  {
    public abstract void onItemClick(View paramView, int paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.SearchExpandTextAdapter
 * JD-Core Version:    0.6.2
 */