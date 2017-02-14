package com.ismartgo.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import com.ismartgo.app.activity.ScreenBrandActivity;
import com.ismartgo.app.bean.Brands;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"UseSparseArrays", "InflateParams"})
public class ScreenBrand_Adapter extends BaseAdapter
{
  Context context;
  private HashMap<Integer, Brands> data;
  LayoutInflater inflater;
  public Map<Integer, Boolean> selectMap;

  public ScreenBrand_Adapter(Context paramContext, HashMap<Integer, Brands> paramHashMap, String paramString)
  {
    this.context = paramContext;
    this.data = paramHashMap;
    this.inflater = LayoutInflater.from(paramContext);
    this.selectMap = new HashMap();
    int i = 0;
    if (i >= paramHashMap.size())
      if ((paramString != null) && (paramString.length() > 0))
      {
        paramContext = paramString.split(",");
        i = 0;
      }
    while (true)
    {
      if (i >= paramContext.length)
      {
        return;
        this.selectMap.put(Integer.valueOf(i), Boolean.valueOf(false));
        i += 1;
        break;
      }
      this.selectMap.put(Integer.valueOf(Integer.parseInt(paramContext[i])), Boolean.valueOf(true));
      i += 1;
    }
  }

  public int getCount()
  {
    return this.data.size();
  }

  public Object getItem(int paramInt)
  {
    return this.data.get(Integer.valueOf(paramInt));
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = new ViewHolder();
    if (paramView == null)
    {
      paramView = this.inflater.inflate(2130903211, null);
      paramViewGroup.cb_brand = ((CheckBox)paramView.findViewById(2131231371));
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      paramViewGroup.cb_brand.setText(((Brands)this.data.get(Integer.valueOf(paramInt))).getBrandName());
      paramViewGroup.cb_brand.setChecked(((Boolean)this.selectMap.get(Integer.valueOf(paramInt))).booleanValue());
      paramViewGroup.cb_brand.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ((ScreenBrandActivity)ScreenBrand_Adapter.this.context).clickCheckBox(paramInt);
        }
      });
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
  }

  public class ViewHolder
  {
    CheckBox cb_brand;

    public ViewHolder()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.ScreenBrand_Adapter
 * JD-Core Version:    0.6.2
 */