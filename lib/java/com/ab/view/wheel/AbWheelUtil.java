package com.ab.view.wheel;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ab.activity.AbActivity;
import com.ab.util.AbDateUtil;
import com.ab.util.AbStrUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AbWheelUtil
{
  public static void initWheelDatePicker(AbActivity paramAbActivity, final TextView paramTextView, final AbWheelView paramAbWheelView1, final AbWheelView paramAbWheelView2, final AbWheelView paramAbWheelView3, Button paramButton1, Button paramButton2, int paramInt1, int paramInt2, int paramInt3, final int paramInt4, int paramInt5, boolean paramBoolean)
  {
    Object localObject1 = Calendar.getInstance();
    int i = ((Calendar)localObject1).get(1);
    int j = ((Calendar)localObject1).get(2) + 1;
    int k = ((Calendar)localObject1).get(5);
    if (paramBoolean)
    {
      paramInt1 = i;
      paramInt2 = j;
      paramInt3 = k;
    }
    paramTextView.setText(AbStrUtil.dateTimeFormat(paramInt1 + "-" + paramInt2 + "-" + paramInt3));
    Object localObject2 = Arrays.asList(new String[] { "1", "3", "5", "7", "8", "10", "12" });
    final List localList = Arrays.asList(new String[] { "4", "6", "9", "11" });
    paramAbWheelView1.setAdapter(new AbNumericWheelAdapter(paramInt4, paramInt4 + paramInt5));
    paramAbWheelView1.setCyclic(true);
    paramAbWheelView1.setLabel("年");
    paramAbWheelView1.setCurrentItem(paramInt1 - paramInt4);
    paramAbWheelView1.setValueTextSize(32);
    paramAbWheelView1.setLabelTextSize(30);
    paramAbWheelView1.setLabelTextColor(-2147483648);
    paramAbWheelView2.setAdapter(new AbNumericWheelAdapter(1, 12));
    paramAbWheelView2.setCyclic(true);
    paramAbWheelView2.setLabel("月");
    paramAbWheelView2.setCurrentItem(paramInt2 - 1);
    paramAbWheelView2.setValueTextSize(32);
    paramAbWheelView2.setLabelTextSize(30);
    paramAbWheelView2.setLabelTextColor(-2147483648);
    if (((List)localObject2).contains(String.valueOf(j + 1)))
      paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 31));
    while (true)
    {
      paramAbWheelView3.setCyclic(true);
      paramAbWheelView3.setLabel("日");
      paramAbWheelView3.setCurrentItem(paramInt3 - 1);
      paramAbWheelView3.setValueTextSize(32);
      paramAbWheelView3.setLabelTextSize(30);
      paramAbWheelView3.setLabelTextColor(-2147483648);
      localObject1 = new AbOnWheelChangedListener()
      {
        public void onChanged(AbWheelView paramAnonymousAbWheelView, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          paramAnonymousInt1 = this.val$startYear;
          int i = paramAbWheelView2.getCurrentItem();
          if (this.val$list_big.contains(String.valueOf(paramAbWheelView2.getCurrentItem() + 1)))
            paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 31));
          while (true)
          {
            paramAbWheelView2.setCurrentItem(i);
            return;
            if (localList.contains(String.valueOf(paramAbWheelView2.getCurrentItem() + 1)))
              paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 30));
            else if (AbDateUtil.isLeapYear(paramAnonymousInt2 + paramAnonymousInt1))
              paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 29));
            else
              paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 28));
          }
        }
      };
      localObject2 = new AbOnWheelChangedListener()
      {
        public void onChanged(AbWheelView paramAnonymousAbWheelView, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          paramAnonymousInt1 = paramAnonymousInt2 + 1;
          if (AbWheelUtil.this.contains(String.valueOf(paramAnonymousInt1)))
            paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 31));
          while (true)
          {
            paramAbWheelView3.setCurrentItem(0);
            return;
            if (localList.contains(String.valueOf(paramAnonymousInt1)))
              paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 30));
            else if (AbDateUtil.isLeapYear(paramAbWheelView1.getCurrentItem() + paramInt4))
              paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 29));
            else
              paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 28));
          }
        }
      };
      paramAbWheelView1.addChangingListener((AbOnWheelChangedListener)localObject1);
      paramAbWheelView2.addChangingListener((AbOnWheelChangedListener)localObject2);
      paramButton1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AbWheelUtil.this.removeDialog(1);
          int i = paramAbWheelView1.getCurrentItem();
          paramAnonymousView = paramAbWheelView1.getAdapter().getItem(i);
          i = paramAbWheelView2.getCurrentItem();
          String str1 = paramAbWheelView2.getAdapter().getItem(i);
          i = paramAbWheelView3.getCurrentItem();
          String str2 = paramAbWheelView3.getAdapter().getItem(i);
          paramTextView.setText(AbStrUtil.dateTimeFormat(paramAnonymousView + "-" + str1 + "-" + str2));
        }
      });
      paramButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AbWheelUtil.this.removeDialog(1);
        }
      });
      return;
      if (localList.contains(String.valueOf(j + 1)))
        paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 30));
      else if (AbDateUtil.isLeapYear(i))
        paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 29));
      else
        paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(1, 28));
    }
  }

  public static void initWheelTimePicker(AbActivity paramAbActivity, final TextView paramTextView, final AbWheelView paramAbWheelView1, final AbWheelView paramAbWheelView2, final AbWheelView paramAbWheelView3, Button paramButton1, Button paramButton2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean)
  {
    Object localObject = Calendar.getInstance();
    int m = ((Calendar)localObject).get(1);
    int i1 = ((Calendar)localObject).get(2);
    int k = ((Calendar)localObject).get(5);
    int j = ((Calendar)localObject).get(11);
    int i = ((Calendar)localObject).get(12);
    int n = ((Calendar)localObject).get(13);
    if (paramBoolean)
    {
      paramInt1 = m;
      paramInt2 = i1 + 1;
      paramInt3 = k;
      paramInt4 = j;
      paramInt5 = i;
    }
    paramTextView.setText(AbStrUtil.dateTimeFormat(paramInt1 + "-" + paramInt2 + "-" + paramInt3 + " " + paramInt4 + ":" + paramInt5 + ":" + n));
    localObject = Arrays.asList(new String[] { "1", "3", "5", "7", "8", "10", "12" });
    Arrays.asList(new String[] { "4", "6", "9", "11" });
    ArrayList localArrayList1 = new ArrayList();
    final ArrayList localArrayList2 = new ArrayList();
    i = 1;
    if (i >= 13)
    {
      paramInt1 = localArrayList1.indexOf(paramInt2 + "月" + " " + paramInt3 + "日");
      paramAbWheelView1.setAdapter(new AbStringWheelAdapter(localArrayList1, AbStrUtil.strLength("12月 12日")));
      paramAbWheelView1.setCyclic(true);
      paramAbWheelView1.setLabel("");
      paramAbWheelView1.setCurrentItem(paramInt1);
      paramAbWheelView1.setValueTextSize(32);
      paramAbWheelView1.setLabelTextSize(30);
      paramAbWheelView1.setLabelTextColor(-2147483648);
      paramAbWheelView2.setAdapter(new AbNumericWheelAdapter(0, 23));
      paramAbWheelView2.setCyclic(true);
      paramAbWheelView2.setLabel("点");
      paramAbWheelView2.setCurrentItem(paramInt4);
      paramAbWheelView2.setValueTextSize(32);
      paramAbWheelView2.setLabelTextSize(30);
      paramAbWheelView2.setLabelTextColor(-2147483648);
      paramAbWheelView3.setAdapter(new AbNumericWheelAdapter(0, 59));
      paramAbWheelView3.setCyclic(true);
      paramAbWheelView3.setLabel("分");
      paramAbWheelView3.setCurrentItem(paramInt5);
      paramAbWheelView3.setValueTextSize(32);
      paramAbWheelView3.setLabelTextSize(30);
      paramAbWheelView3.setLabelTextColor(-2147483648);
      paramButton1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AbWheelUtil.this.removeDialog(1);
          int k = paramAbWheelView1.getCurrentItem();
          int i = paramAbWheelView2.getCurrentItem();
          int j = paramAbWheelView3.getCurrentItem();
          paramAnonymousView = (String)localArrayList2.get(k);
          k = Calendar.getInstance().get(13);
          paramAnonymousView = AbStrUtil.dateTimeFormat(paramAnonymousView + " " + i + ":" + j + ":" + k);
          paramTextView.setText(paramAnonymousView);
        }
      });
      paramButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AbWheelUtil.this.removeDialog(1);
        }
      });
      return;
    }
    if (((List)localObject).contains(String.valueOf(i)))
    {
      j = 1;
      label511: if (j < 32);
    }
    while (true)
    {
      i += 1;
      break;
      localArrayList1.add(i + "月" + " " + j + "日");
      localArrayList2.add(paramInt1 + "-" + i + "-" + j);
      j += 1;
      break label511;
      if (i == 2)
      {
        if (AbDateUtil.isLeapYear(paramInt1))
        {
          j = 1;
          while (j < 28)
          {
            localArrayList1.add(i + "月" + " " + j + "日");
            localArrayList2.add(paramInt1 + "-" + i + "-" + j);
            j += 1;
          }
        }
        else
        {
          j = 1;
          while (j < 29)
          {
            localArrayList1.add(i + "月" + " " + j + "日");
            localArrayList2.add(paramInt1 + "-" + i + "-" + j);
            j += 1;
          }
        }
      }
      else
      {
        j = 1;
        while (j < 29)
        {
          localArrayList1.add(i + "月" + " " + j + "日");
          localArrayList2.add(paramInt1 + "-" + i + "-" + j);
          j += 1;
        }
      }
    }
  }

  public static void initWheelTimePicker2(AbActivity paramAbActivity, final TextView paramTextView, final AbWheelView paramAbWheelView1, final AbWheelView paramAbWheelView2, Button paramButton1, Button paramButton2, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Calendar localCalendar = Calendar.getInstance();
    int j = localCalendar.get(11);
    int i = localCalendar.get(12);
    if (paramBoolean)
    {
      paramInt1 = j;
      paramInt2 = i;
    }
    paramTextView.setText(AbStrUtil.dateTimeFormat(paramInt1 + ":" + paramInt2));
    paramAbWheelView1.setAdapter(new AbNumericWheelAdapter(0, 23));
    paramAbWheelView1.setCyclic(true);
    paramAbWheelView1.setLabel("点");
    paramAbWheelView1.setCurrentItem(paramInt1);
    paramAbWheelView1.setValueTextSize(32);
    paramAbWheelView1.setLabelTextSize(30);
    paramAbWheelView1.setLabelTextColor(-2147483648);
    paramAbWheelView2.setAdapter(new AbNumericWheelAdapter(0, 59));
    paramAbWheelView2.setCyclic(true);
    paramAbWheelView2.setLabel("分");
    paramAbWheelView2.setCurrentItem(paramInt2);
    paramAbWheelView2.setValueTextSize(32);
    paramAbWheelView2.setLabelTextSize(30);
    paramAbWheelView2.setLabelTextColor(-2147483648);
    paramButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbWheelUtil.this.removeDialog(1);
        int i = paramAbWheelView1.getCurrentItem();
        int j = paramAbWheelView2.getCurrentItem();
        paramAnonymousView = AbStrUtil.dateTimeFormat(i + ":" + j);
        paramTextView.setText(paramAnonymousView);
      }
    });
    paramButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbWheelUtil.this.removeDialog(1);
      }
    });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.wheel.AbWheelUtil
 * JD-Core Version:    0.6.2
 */