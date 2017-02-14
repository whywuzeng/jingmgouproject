package com.ab.view.table;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public class AbTableArrayAdapter extends BaseAdapter
{
  private int[] cellTypes;
  private int[] cellWidth;
  private int columns;
  private List<String[]> contents;
  private Context context;
  private int[] rowHeight;
  private int[] rowTextColor;
  private int[] rowTextSize;
  private AbTable table;
  private int[] tableResource;
  private ArrayList<View> tableView;
  private String[] titles;

  public AbTableArrayAdapter(Context paramContext, AbTable paramAbTable)
  {
    this.context = paramContext;
    this.tableView = new ArrayList();
    setTable(paramAbTable);
  }

  public void addItem(String[] paramArrayOfString)
  {
    this.contents.add(paramArrayOfString);
    notifyDataSetChanged();
  }

  public int getCount()
  {
    return this.contents.size() + 1;
  }

  public AbTableItemView getItem(int paramInt)
  {
    return (AbTableItemView)this.tableView.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i;
    if (paramView == null)
    {
      if (paramInt == 0)
      {
        paramView = new AbTableCell[this.columns];
        i = 0;
        while (true)
        {
          if (i >= this.columns)
          {
            paramView = new AbTableItemView(this.context, this, paramInt, new AbTableRow(paramView, this.rowHeight[0], this.rowTextSize[0], this.rowTextColor[0], this.tableResource[1]), this.table);
            paramView.setBackgroundResource(this.tableResource[0]);
            if (this.tableView.size() <= paramInt)
              break;
            this.tableView.set(paramInt, paramView);
            return paramView;
          }
          paramView[i] = new AbTableCell(this.titles[i], this.cellWidth[i], this.cellTypes[i]);
          i += 1;
        }
      }
      paramViewGroup = new AbTableCell[this.columns];
      localObject = (String[])this.contents.get(paramInt - 1);
      if (this.contents.size() > 0)
        i = 0;
      while (true)
      {
        if (i >= this.columns)
        {
          paramView = new AbTableItemView(this.context, this, paramInt, new AbTableRow(paramViewGroup, this.rowHeight[1], this.rowTextSize[1], this.rowTextColor[1], this.tableResource[3]), this.table);
          paramView.setBackgroundResource(this.tableResource[2]);
          break;
        }
        paramViewGroup[i] = new AbTableCell(localObject[i], this.cellWidth[i], this.cellTypes[i]);
        i += 1;
      }
    }
    if (paramInt == 0)
    {
      paramViewGroup = (AbTableItemView)paramView;
      localObject = new AbTableCell[this.columns];
      i = 0;
      while (true)
      {
        if (i >= this.columns)
        {
          paramViewGroup.setTableRowView(paramInt, new AbTableRow((AbTableCell[])localObject, this.rowHeight[0], this.rowTextSize[0], this.rowTextColor[0], this.tableResource[1]));
          paramView.setBackgroundResource(this.tableResource[0]);
          break;
        }
        localObject[i] = new AbTableCell(this.titles[i], this.cellWidth[i], this.cellTypes[i]);
        i += 1;
      }
    }
    paramViewGroup = (AbTableItemView)paramView;
    Object localObject = new AbTableCell[this.columns];
    String[] arrayOfString = (String[])this.contents.get(paramInt - 1);
    if (this.contents.size() > 0)
      i = 0;
    while (true)
    {
      if (i >= this.columns)
      {
        paramViewGroup.setTableRowView(paramInt, new AbTableRow((AbTableCell[])localObject, this.rowHeight[1], this.rowTextSize[1], this.rowTextColor[1], this.tableResource[3]));
        paramView.setBackgroundResource(this.tableResource[2]);
        break;
      }
      localObject[i] = new AbTableCell(arrayOfString[i], this.cellWidth[i], this.cellTypes[i]);
      i += 1;
    }
    this.tableView.add(paramInt, paramView);
    return paramView;
  }

  public void setTable(AbTable paramAbTable)
  {
    this.table = paramAbTable;
    this.titles = paramAbTable.getTitles();
    this.contents = paramAbTable.getContents();
    this.cellTypes = paramAbTable.getCellTypes();
    this.cellWidth = paramAbTable.getCellWidth();
    this.rowHeight = paramAbTable.getRowHeight();
    this.rowTextSize = paramAbTable.getRowTextSize();
    this.rowTextColor = paramAbTable.getRowTextColor();
    this.tableResource = paramAbTable.getTableResource();
    this.columns = this.cellTypes.length;
    this.tableView.clear();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.table.AbTableArrayAdapter
 * JD-Core Version:    0.6.2
 */