package com.ab.view.table;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.ab.view.listener.AbOnItemClickListener;
import java.util.List;

public class AbTable
{
  private static AbTable mAbTable = null;
  private static int mScreenHeight = 0;
  private static int mScreenWidth = 0;
  private int[] cellTypes;
  private int[] cellWidth;
  private List<String[]> contents;
  private AbOnItemClickListener itemCellCheckListener;
  private AbOnItemClickListener itemCellTouchListener;
  private int[] rowHeight;
  private int[] rowTextColor;
  private int[] rowTextSize;
  private int[] tableResource;
  private String[] titles;

  public static AbTable newAbTable(Context paramContext, int paramInt)
  {
    if (paramInt <= 0)
    {
      Toast.makeText(paramContext, "请设置表格的列数>0!", 0).show();
      return null;
    }
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    mScreenWidth = paramContext.getWidth();
    mScreenHeight = paramContext.getHeight();
    mAbTable = new AbTable();
    mAbTable.titles = new String[paramInt];
    int i = 0;
    if (i >= paramInt)
    {
      mAbTable.cellTypes = new int[paramInt];
      i = 0;
      label83: if (i < paramInt)
        break label239;
      mAbTable.cellWidth = new int[paramInt];
      i = 0;
    }
    while (true)
    {
      if (i >= paramInt)
      {
        mAbTable.rowHeight = new int[] { 30, 30 };
        mAbTable.rowTextSize = new int[] { 18, 16 };
        mAbTable.rowTextColor = new int[] { Color.rgb(113, 113, 113), Color.rgb(113, 113, 113) };
        mAbTable.tableResource = new int[] { 17170445, 17170445, 17170445, 17170445 };
        return mAbTable;
        mAbTable.titles[i] = ("标题" + i);
        i += 1;
        break;
        label239: mAbTable.cellTypes[i] = 0;
        i += 1;
        break label83;
      }
      mAbTable.cellWidth[i] = (mScreenWidth / paramInt);
      i += 1;
    }
  }

  public int[] getCellTypes()
  {
    return this.cellTypes;
  }

  public int[] getCellWidth()
  {
    return this.cellWidth;
  }

  public List<String[]> getContents()
  {
    return this.contents;
  }

  public AbOnItemClickListener getItemCellCheckListener()
  {
    return this.itemCellCheckListener;
  }

  public AbOnItemClickListener getItemCellTouchListener()
  {
    return this.itemCellTouchListener;
  }

  public int[] getRowHeight()
  {
    return this.rowHeight;
  }

  public int[] getRowTextColor()
  {
    return this.rowTextColor;
  }

  public int[] getRowTextSize()
  {
    return this.rowTextSize;
  }

  public int[] getTableResource()
  {
    return this.tableResource;
  }

  public String[] getTitles()
  {
    return this.titles;
  }

  public void setCellTypes(int[] paramArrayOfInt)
  {
    this.cellTypes = paramArrayOfInt;
  }

  public void setCellWidth(int[] paramArrayOfInt)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfInt.length)
        return;
      mAbTable.cellWidth[i] = (mScreenWidth * paramArrayOfInt[i] / 100);
      i += 1;
    }
  }

  public void setContents(List<String[]> paramList)
  {
    this.contents = paramList;
  }

  public void setItemCellCheckListener(AbOnItemClickListener paramAbOnItemClickListener)
  {
    this.itemCellCheckListener = paramAbOnItemClickListener;
  }

  public void setItemCellTouchListener(AbOnItemClickListener paramAbOnItemClickListener)
  {
    this.itemCellTouchListener = paramAbOnItemClickListener;
  }

  public void setRowHeight(int[] paramArrayOfInt)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfInt.length)
        return;
      mAbTable.rowHeight[i] = paramArrayOfInt[i];
      i += 1;
    }
  }

  public void setRowTextColor(int[] paramArrayOfInt)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfInt.length)
        return;
      mAbTable.rowTextColor[i] = paramArrayOfInt[i];
      i += 1;
    }
  }

  public void setRowTextSize(int[] paramArrayOfInt)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfInt.length)
        return;
      mAbTable.rowTextSize[i] = paramArrayOfInt[i];
      i += 1;
    }
  }

  public void setTableResource(int[] paramArrayOfInt)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfInt.length)
        return;
      this.tableResource[i] = paramArrayOfInt[i];
      i += 1;
    }
  }

  public void setTitles(String[] paramArrayOfString)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfString.length)
        return;
      mAbTable.titles[i] = paramArrayOfString[i];
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.table.AbTable
 * JD-Core Version:    0.6.2
 */