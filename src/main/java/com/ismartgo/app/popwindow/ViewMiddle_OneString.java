package com.ismartgo.app.popwindow;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ismartgo.app.adapter.ExpandTextAdapter;
import com.ismartgo.app.adapter.ExpandTextAdapter.OnItemClickListener;
import com.ismartgo.app.interfaces.ViewBaseActionInterface;
import java.util.LinkedList;

public class ViewMiddle_OneString extends LinearLayout
  implements ViewBaseActionInterface
{
  private ListView AreaListView;
  private LinkedList<String> areaList = new LinkedList();
  private ExpandTextAdapter areaListViewAdapter;
  public int areaPosition = 0;
  private OnSelectListener mOnSelectListener;
  private String showString = "";

  public ViewMiddle_OneString(Context paramContext, LinkedList<String> paramLinkedList)
  {
    super(paramContext);
    this.areaList = paramLinkedList;
    init(paramContext);
  }

  public ViewMiddle_OneString(Context paramContext, LinkedList<String> paramLinkedList, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.areaList = paramLinkedList;
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903186, this, true);
    this.AreaListView = ((ListView)findViewById(2131231291));
    findViewById(2131231292).setVisibility(8);
    setBackgroundColor(getResources().getColor(17170443));
    this.areaListViewAdapter = new ExpandTextAdapter(paramContext, this.areaList, 0, 2130837608);
    this.areaListViewAdapter.setSelectedPositionNoNotify(this.areaPosition);
    this.AreaListView.setAdapter(this.areaListViewAdapter);
    this.areaListViewAdapter.setOnItemClickListener(new ExpandTextAdapter.OnItemClickListener()
    {
      public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
      {
        ViewMiddle_OneString.this.showString = ((String)ViewMiddle_OneString.this.areaList.get(paramAnonymousInt));
        if (ViewMiddle_OneString.this.mOnSelectListener != null)
          ViewMiddle_OneString.this.mOnSelectListener.getValue(ViewMiddle_OneString.this.showString);
      }
    });
    setDefaultSelect();
  }

  public String getShowText()
  {
    return this.showString;
  }

  public void hide()
  {
  }

  public void setDefaultSelect()
  {
    this.AreaListView.setSelection(this.areaPosition);
  }

  public void setOnSelectListener(OnSelectListener paramOnSelectListener)
  {
    this.mOnSelectListener = paramOnSelectListener;
  }

  public void show()
  {
  }

  public void updateShowText(String paramString)
  {
    if (paramString == null)
      return;
    int i = 0;
    while (true)
    {
      if (i >= this.areaList.size());
      while (true)
      {
        setDefaultSelect();
        return;
        if (!((String)this.areaList.get(i)).equals(paramString))
          break;
        this.areaListViewAdapter.setSelectedPosition(i);
        this.areaPosition = i;
      }
      i += 1;
    }
  }

  public static abstract interface OnSelectListener
  {
    public abstract void getValue(String paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.popwindow.ViewMiddle_OneString
 * JD-Core Version:    0.6.2
 */