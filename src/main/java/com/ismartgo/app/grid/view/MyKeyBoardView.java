package com.ismartgo.app.grid.view;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import com.ismartgo.app.adapter.KeyBoardAdapter;
import com.ismartgo.app.bean.MyKeyBoard;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.impl.KeyBoardCallback;
import com.ismartgo.app.ownself.view.ToastDefine;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyKeyBoardView
  implements AdapterView.OnItemClickListener
{
  private Activity activity;
  private KeyBoardAdapter adapter;
  private StringBuffer buffer = new StringBuffer();
  private KeyBoardCallback callback;
  private boolean isEquals;
  private List<MyKeyBoard> keyList;
  private GridView keyboard;
  private String money;
  private List<Double> numList = new ArrayList();
  private int status = 1;
  private double temNum;

  public MyKeyBoardView(String paramString)
  {
    this.money = paramString;
  }

  private void initKeyBoard()
  {
    this.keyList = new ArrayList();
    MyKeyBoard localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(7);
    localMyKeyBoard.setText(String.valueOf(7));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(8);
    localMyKeyBoard.setText(String.valueOf(8));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(9);
    localMyKeyBoard.setText(String.valueOf(9));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(10);
    localMyKeyBoard.setText("");
    localMyKeyBoard.setFeaturdResource(2130837743);
    localMyKeyBoard.setFeatures(true);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(4);
    localMyKeyBoard.setText(String.valueOf(4));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(5);
    localMyKeyBoard.setText(String.valueOf(5));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(6);
    localMyKeyBoard.setText(String.valueOf(6));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(11);
    localMyKeyBoard.setText("");
    localMyKeyBoard.setFeaturdResource(2130837740);
    localMyKeyBoard.setFeatures(true);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(1);
    localMyKeyBoard.setText(String.valueOf(1));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(2);
    localMyKeyBoard.setText(String.valueOf(2));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(3);
    localMyKeyBoard.setText(String.valueOf(3));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(12);
    localMyKeyBoard.setText("");
    localMyKeyBoard.setFeaturdResource(2130837736);
    localMyKeyBoard.setFeatures(true);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(14);
    localMyKeyBoard.setText("C");
    localMyKeyBoard.setFeatures(true);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(0);
    localMyKeyBoard.setText(String.valueOf(0));
    localMyKeyBoard.setFeatures(false);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(15);
    localMyKeyBoard.setText(".");
    localMyKeyBoard.setFeatures(true);
    this.keyList.add(localMyKeyBoard);
    localMyKeyBoard = new MyKeyBoard();
    localMyKeyBoard.setId(13);
    localMyKeyBoard.setText("确定");
    localMyKeyBoard.setFeatures(true);
    this.keyList.add(localMyKeyBoard);
    this.adapter = new KeyBoardAdapter(this.activity, this.keyList);
    this.keyboard.setAdapter(this.adapter);
    this.keyboard.setOnItemClickListener(this);
  }

  public void init(Activity paramActivity, KeyBoardCallback paramKeyBoardCallback)
  {
    this.keyboard = ((GridView)paramActivity.findViewById(2131231246));
    this.callback = paramKeyBoardCallback;
    this.activity = paramActivity;
    initKeyBoard();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (this.callback != null)
    {
      paramAdapterView = (MyKeyBoard)this.keyList.get(paramInt);
      if (paramAdapterView.isFeatures())
        break label108;
      if (!this.isEquals)
        break label36;
    }
    label35: label36: label108: double d;
    label595: 
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  break label35;
                  break label35;
                  break label35;
                  break label35;
                  break label35;
                  do
                    return;
                  while ((this.buffer.toString().contains(".")) && (this.buffer.length() - 1 - this.buffer.lastIndexOf(".") == 2));
                  this.buffer.append(paramAdapterView.getText());
                  this.money = null;
                  this.callback.keywordsCallback(this.buffer.toString());
                  return;
                  if (paramAdapterView.getId() == 14)
                  {
                    this.buffer.setLength(0);
                    this.numList.clear();
                    this.callback.keywordsCallback("0");
                    this.status = 1;
                    if (this.isEquals)
                    {
                      ((MyKeyBoard)this.keyList.get(this.keyList.size() - 1)).setText("确认");
                      this.adapter.notifyDataSetChanged();
                    }
                    this.isEquals = false;
                    return;
                  }
                  if (paramAdapterView.getId() != 10)
                    break;
                }
                while ((this.isEquals) || (CommonMethod.isEmpty(this.buffer.toString())));
                this.buffer = this.buffer.delete(this.buffer.length() - 1, this.buffer.length());
                if (this.buffer.length() == 0)
                {
                  this.callback.keywordsCallback("0");
                  return;
                }
                this.callback.keywordsCallback(this.buffer.toString());
                return;
                if (paramAdapterView.getId() != 15)
                  break;
              }
              while ((this.isEquals) || (CommonMethod.isEmpty(this.buffer.toString())) || (this.buffer.toString().contains(".")));
              this.buffer.append(paramAdapterView.getText());
              this.callback.keywordsCallback(this.buffer.toString());
              return;
              if (paramAdapterView.getId() != 11)
                break;
            }
            while (CommonMethod.isEmpty(this.buffer.toString()));
            if ((this.buffer.toString().contains(".")) && (this.buffer.length() - 1 == this.buffer.toString().lastIndexOf(".")))
            {
              paramAdapterView = this.buffer.toString().substring(0, this.buffer.toString().lastIndexOf("."));
              this.buffer.setLength(0);
              this.buffer.append(paramAdapterView);
            }
            if (!this.isEquals)
            {
              d = Double.valueOf(this.buffer.toString()).doubleValue();
              if (this.status != 1)
                break label595;
              this.numList.add(Double.valueOf(d));
            }
            while (true)
            {
              ((MyKeyBoard)this.keyList.get(this.keyList.size() - 1)).setText("");
              ((MyKeyBoard)this.keyList.get(this.keyList.size() - 1)).setFeaturdResource(2130837730);
              this.adapter.notifyDataSetChanged();
              this.buffer.setLength(0);
              this.isEquals = false;
              this.status = 1;
              return;
              if (this.status == 2)
                this.numList.add(Double.valueOf(-d));
            }
            if (paramAdapterView.getId() != 12)
              break;
          }
          while (CommonMethod.isEmpty(this.buffer.toString()));
          if ((this.buffer.toString().contains(".")) && (this.buffer.length() - 1 == this.buffer.toString().lastIndexOf(".")))
          {
            paramAdapterView = this.buffer.toString().substring(0, this.buffer.toString().lastIndexOf("."));
            this.buffer.setLength(0);
            this.buffer.append(paramAdapterView);
          }
          if (!this.isEquals)
          {
            d = Double.valueOf(this.buffer.toString()).doubleValue();
            if (this.status != 1)
              break label851;
            this.numList.add(Double.valueOf(d));
          }
          while (true)
          {
            ((MyKeyBoard)this.keyList.get(this.keyList.size() - 1)).setText("");
            ((MyKeyBoard)this.keyList.get(this.keyList.size() - 1)).setFeaturdResource(2130837730);
            this.adapter.notifyDataSetChanged();
            this.buffer.setLength(0);
            this.isEquals = false;
            this.status = 2;
            return;
            if (this.status == 2)
              this.numList.add(Double.valueOf(-d));
          }
        }
        while (paramAdapterView.getId() != 13);
        if (this.isEquals)
        {
          if (this.temNum < 0.0D)
          {
            ToastDefine.makeText(this.activity.getApplicationContext(), "金额不能为负数", 0).show();
            return;
          }
          if (this.temNum == 0.0D)
          {
            ToastDefine.makeText(this.activity.getApplicationContext(), "金额不能为0", 0).show();
            return;
          }
          this.callback.uploadCallback(String.valueOf(this.temNum));
          return;
        }
        if (!CommonMethod.isEmpty(this.money))
        {
          if (!CommonMethod.isEmpty(this.buffer.toString()))
          {
            d = Double.valueOf(this.buffer.toString()).doubleValue();
            if (d < 0.0D)
            {
              ToastDefine.makeText(this.activity.getApplicationContext(), "金额不能为负数", 0).show();
              return;
            }
            if (d == 0.0D)
            {
              ToastDefine.makeText(this.activity.getApplicationContext(), "金额不能为0", 0).show();
              return;
            }
            this.callback.uploadCallback(String.valueOf(d));
            return;
          }
          this.callback.uploadCallback(this.money);
          return;
        }
        if ((this.numList.size() != 0) && (!this.numList.isEmpty()))
          break;
      }
      while (CommonMethod.isEmpty(this.buffer.toString()));
      d = Double.valueOf(this.buffer.toString()).doubleValue();
      if (d < 0.0D)
      {
        ToastDefine.makeText(this.activity.getApplicationContext(), "金额不能为负数", 0).show();
        return;
      }
      if (d == 0.0D)
      {
        ToastDefine.makeText(this.activity.getApplicationContext(), "金额不能为0", 0).show();
        return;
      }
      this.callback.uploadCallback(String.valueOf(d));
      return;
    }
    while (CommonMethod.isEmpty(this.buffer.toString()));
    label851: this.temNum = Double.valueOf(this.buffer.toString()).doubleValue();
    if (this.status == 2)
      this.temNum = (-this.temNum);
    paramView = this.numList.iterator();
    while (true)
    {
      if (!paramView.hasNext())
      {
        if (String.valueOf(this.temNum).length() - String.valueOf(this.temNum).lastIndexOf(".") > 2)
          this.temNum = Double.valueOf(new DecimalFormat("#.##").format(this.temNum)).doubleValue();
        this.buffer.setLength(0);
        this.buffer.append(String.valueOf(this.temNum));
        this.numList.clear();
        this.numList.add(Double.valueOf(this.temNum));
        this.status = 1;
        this.callback.keywordsCallback(String.valueOf(this.temNum));
        this.isEquals = true;
        paramAdapterView.setText("确定");
        this.adapter.notifyDataSetChanged();
        return;
      }
      d = ((Double)paramView.next()).doubleValue();
      this.temNum += d;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.MyKeyBoardView
 * JD-Core Version:    0.6.2
 */