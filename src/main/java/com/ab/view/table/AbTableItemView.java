package com.ab.view.table;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ab.global.AbAppData;
import com.ab.view.listener.AbOnItemClickListener;
import java.util.List;

public class AbTableItemView extends LinearLayout
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbTableItemView";
  private int cellCount;
  private AbTableArrayAdapter mAdapter = null;
  private Context mContext;
  private int mPosition;
  private AbTable mTable = null;
  private View[] rowCell;

  public AbTableItemView(Context paramContext, AbTableArrayAdapter paramAbTableArrayAdapter, final int paramInt, AbTableRow paramAbTableRow, AbTable paramAbTable)
  {
    super(paramContext);
    this.mPosition = paramInt;
    this.mContext = paramContext;
    this.mTable = paramAbTable;
    this.mAdapter = paramAbTableArrayAdapter;
    setOrientation(0);
    this.cellCount = paramAbTableRow.getCellSize();
    this.rowCell = new View[this.cellCount];
    paramInt = 0;
    if (paramInt >= this.cellCount)
      return;
    paramAbTableArrayAdapter = paramAbTableRow.getCellValue(paramInt);
    paramAbTable = new LinearLayout.LayoutParams(-2, paramAbTableRow.height);
    if (paramAbTableArrayAdapter.type == 0)
    {
      localObject1 = new TextView(this.mContext);
      ((TextView)localObject1).setMinimumWidth(paramAbTableArrayAdapter.width);
      ((TextView)localObject1).setMinimumHeight(paramAbTableRow.height);
      if (D)
        Log.d(TAG, "行高：" + paramAbTableRow.height);
      ((TextView)localObject1).setLines(1);
      ((TextView)localObject1).setGravity(17);
      ((TextView)localObject1).setTextColor(paramAbTableRow.textColor);
      if (this.mPosition == 0)
      {
        if (D)
          Log.d(TAG, "标题栏的颜色：" + paramAbTableRow.textColor);
        ((TextView)localObject1).setTypeface(Typeface.DEFAULT_BOLD);
        ((TextView)localObject1).setBackgroundResource(this.mTable.getTableResource()[1]);
        label252: ((TextView)localObject1).setText(String.valueOf(paramAbTableArrayAdapter.value));
        ((TextView)localObject1).setTextSize(paramAbTableRow.textSize);
        this.rowCell[paramInt] = localObject1;
        addView((View)localObject1, paramAbTable);
      }
    }
    do
    {
      paramInt += 1;
      break;
      ((TextView)localObject1).setTypeface(Typeface.DEFAULT);
      ((TextView)localObject1).setBackgroundResource(this.mTable.getTableResource()[3]);
      break label252;
      if (paramAbTableArrayAdapter.type == 1)
      {
        localObject1 = new LinearLayout(this.mContext);
        ((LinearLayout)localObject1).setMinimumWidth(paramAbTableArrayAdapter.width);
        localObject2 = new ImageView(this.mContext);
        if (this.mPosition == 0)
        {
          ((ImageView)localObject2).setImageDrawable(null);
          ((LinearLayout)localObject1).setGravity(17);
          ((LinearLayout)localObject1).addView((View)localObject2, paramAbTable);
          ((LinearLayout)localObject1).setBackgroundResource(this.mTable.getTableResource()[1]);
          addView((View)localObject1, paramAbTable);
        }
        while (true)
        {
          this.rowCell[paramInt] = localObject2;
          break;
          ((ImageView)localObject2).setImageResource(Integer.parseInt((String)paramAbTableArrayAdapter.value));
          ((LinearLayout)localObject1).setGravity(17);
          ((LinearLayout)localObject1).addView((View)localObject2, paramAbTable);
          ((LinearLayout)localObject1).setBackgroundResource(this.mTable.getTableResource()[3]);
          addView((View)localObject1, paramAbTable);
          ((ImageView)localObject2).setOnTouchListener(new View.OnTouchListener()
          {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
              if (paramAnonymousMotionEvent.getAction() == 0)
              {
                paramAnonymousView = AbTableItemView.this.mTable.getItemCellTouchListener();
                if (paramAnonymousView != null)
                  paramAnonymousView.onClick(AbTableItemView.this.mPosition);
              }
              return false;
            }
          });
        }
      }
    }
    while (paramAbTableArrayAdapter.type != 3);
    Object localObject1 = new LinearLayout(this.mContext);
    ((LinearLayout)localObject1).setMinimumWidth(paramAbTableArrayAdapter.width);
    Object localObject2 = new CheckBox(paramContext);
    ((CheckBox)localObject2).setGravity(17);
    ((CheckBox)localObject2).setOnCheckedChangeListener(null);
    ((CheckBox)localObject2).setFocusable(false);
    if (Integer.parseInt(String.valueOf(paramAbTableArrayAdapter.value)) == 1)
    {
      ((CheckBox)localObject2).setChecked(true);
      label576: if (this.mPosition != 0)
        break label655;
      ((LinearLayout)localObject1).setGravity(17);
      ((LinearLayout)localObject1).addView((View)localObject2, paramAbTable);
      ((LinearLayout)localObject1).setBackgroundResource(this.mTable.getTableResource()[1]);
      addView((View)localObject1, paramAbTable);
    }
    while (true)
    {
      ((CheckBox)localObject2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          int i;
          if (AbTableItemView.this.mPosition == 0)
            if (paramAnonymousBoolean)
            {
              AbTableItemView.this.mTable.getTitles()[paramInt] = "1";
              i = 0;
              if (i < AbTableItemView.this.mTable.getContents().size());
            }
          while (true)
          {
            AbTableItemView.this.mAdapter.notifyDataSetChanged();
            paramAnonymousCompoundButton = AbTableItemView.this.mTable.getItemCellCheckListener();
            if (paramAnonymousCompoundButton != null)
              paramAnonymousCompoundButton.onClick(AbTableItemView.this.mPosition);
            return;
            ((String[])AbTableItemView.this.mTable.getContents().get(i))[paramInt] = "1";
            i += 1;
            break;
            AbTableItemView.this.mTable.getTitles()[paramInt] = "0";
            i = 0;
            while (i < AbTableItemView.this.mTable.getContents().size())
            {
              ((String[])AbTableItemView.this.mTable.getContents().get(i))[paramInt] = "0";
              i += 1;
            }
            continue;
            if (paramAnonymousBoolean)
              ((String[])AbTableItemView.this.mTable.getContents().get(AbTableItemView.this.mPosition - 1))[paramInt] = "1";
            else
              ((String[])AbTableItemView.this.mTable.getContents().get(AbTableItemView.this.mPosition - 1))[paramInt] = "0";
          }
        }
      });
      this.rowCell[paramInt] = localObject2;
      break;
      ((CheckBox)localObject2).setChecked(false);
      break label576;
      label655: ((LinearLayout)localObject1).setGravity(17);
      ((LinearLayout)localObject1).addView((View)localObject2, paramAbTable);
      ((LinearLayout)localObject1).setBackgroundResource(this.mTable.getTableResource()[3]);
      addView((View)localObject1, paramAbTable);
    }
  }

  public void setTableRowView(final int paramInt, AbTableRow paramAbTableRow)
  {
    this.mPosition = paramInt;
    paramInt = 0;
    if (paramInt >= this.cellCount)
      return;
    Object localObject1 = paramAbTableRow.getCellValue(paramInt);
    if (((AbTableCell)localObject1).type == 0)
    {
      localObject2 = (TextView)this.rowCell[paramInt];
      ((TextView)localObject2).setMinimumWidth(((AbTableCell)localObject1).width);
      ((TextView)localObject2).setMinimumHeight(paramAbTableRow.height);
      ((TextView)localObject2).setLines(1);
      ((TextView)localObject2).setGravity(17);
      ((TextView)localObject2).setText(String.valueOf(((AbTableCell)localObject1).value));
      ((TextView)localObject2).setTextColor(paramAbTableRow.textColor);
      ((TextView)localObject2).setTextSize(paramAbTableRow.textSize);
      if (this.mPosition == 0)
      {
        ((TextView)localObject2).setTypeface(Typeface.DEFAULT_BOLD);
        ((TextView)localObject2).setBackgroundResource(this.mTable.getTableResource()[1]);
      }
    }
    label277: 
    do
      while (true)
      {
        paramInt += 1;
        break;
        ((TextView)localObject2).setTypeface(Typeface.DEFAULT);
        ((TextView)localObject2).setBackgroundResource(this.mTable.getTableResource()[3]);
        continue;
        if (((AbTableCell)localObject1).type != 1)
          break label277;
        if (this.mPosition == 0)
        {
          localObject1 = (ImageView)this.rowCell[paramInt];
          ((ImageView)localObject1).setImageDrawable(null);
          ((LinearLayout)((ImageView)localObject1).getParent()).setBackgroundResource(this.mTable.getTableResource()[1]);
        }
        else
        {
          localObject2 = (ImageView)this.rowCell[paramInt];
          ((LinearLayout)((ImageView)localObject2).getParent()).setBackgroundResource(this.mTable.getTableResource()[3]);
          ((ImageView)localObject2).setImageResource(Integer.parseInt((String)((AbTableCell)localObject1).value));
          ((ImageView)localObject2).setOnTouchListener(new View.OnTouchListener()
          {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
              if (paramAnonymousMotionEvent.getAction() == 0)
              {
                paramAnonymousView = AbTableItemView.this.mTable.getItemCellTouchListener();
                if (paramAnonymousView != null)
                  paramAnonymousView.onClick(AbTableItemView.this.mPosition);
              }
              return false;
            }
          });
        }
      }
    while (((AbTableCell)localObject1).type != 3);
    Object localObject2 = (CheckBox)this.rowCell[paramInt];
    ((CheckBox)localObject2).setOnCheckedChangeListener(null);
    if (Integer.parseInt(String.valueOf(((AbTableCell)localObject1).value)) == 1)
    {
      ((CheckBox)localObject2).setChecked(true);
      label322: if (this.mPosition != 0)
        break label381;
      ((LinearLayout)((CheckBox)localObject2).getParent()).setBackgroundResource(this.mTable.getTableResource()[1]);
    }
    while (true)
    {
      ((CheckBox)localObject2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          int i;
          if (AbTableItemView.this.mPosition == 0)
            if (paramAnonymousBoolean)
            {
              AbTableItemView.this.mTable.getTitles()[paramInt] = "1";
              i = 0;
              if (i < AbTableItemView.this.mTable.getContents().size());
            }
          while (true)
          {
            AbTableItemView.this.mAdapter.notifyDataSetChanged();
            paramAnonymousCompoundButton = AbTableItemView.this.mTable.getItemCellCheckListener();
            if (paramAnonymousCompoundButton != null)
              paramAnonymousCompoundButton.onClick(AbTableItemView.this.mPosition);
            return;
            ((String[])AbTableItemView.this.mTable.getContents().get(i))[paramInt] = "1";
            i += 1;
            break;
            AbTableItemView.this.mTable.getTitles()[paramInt] = "0";
            i = 0;
            while (i < AbTableItemView.this.mTable.getContents().size())
            {
              ((String[])AbTableItemView.this.mTable.getContents().get(i))[paramInt] = "0";
              i += 1;
            }
            continue;
            if (paramAnonymousBoolean)
              ((String[])AbTableItemView.this.mTable.getContents().get(AbTableItemView.this.mPosition - 1))[paramInt] = "1";
            else
              ((String[])AbTableItemView.this.mTable.getContents().get(AbTableItemView.this.mPosition - 1))[paramInt] = "0";
          }
        }
      });
      ((CheckBox)localObject2).setFocusable(false);
      break;
      ((CheckBox)localObject2).setChecked(false);
      break label322;
      label381: ((LinearLayout)((CheckBox)localObject2).getParent()).setBackgroundResource(this.mTable.getTableResource()[3]);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.table.AbTableItemView
 * JD-Core Version:    0.6.2
 */