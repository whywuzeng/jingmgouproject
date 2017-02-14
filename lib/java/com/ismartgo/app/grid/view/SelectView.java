package com.ismartgo.app.grid.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ismartgo.app.grid.impl.SelectViewCallback;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpRequestParam;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class SelectView
  implements View.OnClickListener, AdapterView.OnItemClickListener, HttpCallback<String>
{
  private AreaAdapter areaAdapter;
  private List<HashMap<String, Object>> areaList;
  private ListView areaListView;
  private int areaPosition;
  private CityAdapter cityAdapter;
  private List<HashMap<String, Object>> cityList;
  private ListView cityListView;
  private int cityPosition;
  private Context context;
  private View cursor;
  private float fromX;
  private SelectViewCallback mSelectViewCallback;
  private int modifyTag;
  private View parentView;
  private PopupWindow pop;
  private View popView;
  private ProgressBar progressbar;
  private ProvinceAdapter provinceAdapter;
  private List<HashMap<String, Object>> provinceList;
  private ListView provinceListView;
  private int provincePosition;
  private float toX;
  private TownAdapter townAdapter;
  private List<HashMap<String, Object>> townList;
  private ListView townListView;
  private int townPosition;
  private TextView tvArea;
  private TextView tvCity;
  private TextView tvProvince;
  private TextView tvTown;

  private void cursorMove(float paramFloat, int paramInt)
  {
    ObjectAnimator.ofFloat(this.cursor, "translationX", new float[] { paramFloat, this.toX }).setDuration(300L).start();
    this.fromX = this.toX;
    int i = 0;
    if (paramInt == 1)
      i = this.tvProvince.getMeasuredWidth();
    while (true)
    {
      this.cursor.getLayoutParams().width = i;
      return;
      if (paramInt == 2)
        i = this.tvCity.getMeasuredWidth();
      else if (paramInt == 3)
        i = this.tvArea.getMeasuredWidth();
      else if (paramInt == 4)
        i = this.tvTown.getMeasuredWidth();
    }
  }

  private int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }

  private void findViewById()
  {
    this.progressbar = ((ProgressBar)this.popView.findViewById(2131231102));
    this.tvProvince = ((TextView)this.popView.findViewById(2131231266));
    this.tvCity = ((TextView)this.popView.findViewById(2131231267));
    this.tvArea = ((TextView)this.popView.findViewById(2131231268));
    this.tvTown = ((TextView)this.popView.findViewById(2131231269));
    this.provinceListView = ((ListView)this.popView.findViewById(2131231271));
    this.cityListView = ((ListView)this.popView.findViewById(2131231272));
    this.areaListView = ((ListView)this.popView.findViewById(2131231273));
    this.townListView = ((ListView)this.popView.findViewById(2131231274));
    this.tvProvince.setOnClickListener(this);
    this.tvCity.setOnClickListener(this);
    this.tvArea.setOnClickListener(this);
    this.tvTown.setOnClickListener(this);
    this.provinceListView.setOnItemClickListener(this);
    this.cityListView.setOnItemClickListener(this);
    this.areaListView.setOnItemClickListener(this);
    this.townListView.setOnItemClickListener(this);
    this.cursor = this.popView.findViewById(2131231270);
    this.tvProvince.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        SelectView.this.tvProvince.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        int i = SelectView.this.tvProvince.getMeasuredWidth();
        SelectView.this.cursor.getLayoutParams().width = i;
      }
    });
  }

  private List<HashMap<String, Object>> getData(String paramString, List<HashMap<String, Object>> paramList)
  {
    Object localObject = HttpJsonParse.parse_SelectView(paramString);
    paramString = paramList;
    if (localObject != null)
    {
      paramString = paramList;
      if (!((HashMap)localObject).isEmpty())
      {
        localObject = new ArrayList(((HashMap)localObject).entrySet());
        Collections.sort((List)localObject, new Comparator()
        {
          public int compare(Map.Entry<String, Object> paramAnonymousEntry1, Map.Entry<String, Object> paramAnonymousEntry2)
          {
            return ((Integer)paramAnonymousEntry1.getValue()).intValue() - ((Integer)paramAnonymousEntry2.getValue()).intValue();
          }
        });
        paramString = paramList;
        if (paramList == null)
          paramString = new ArrayList();
        paramString.clear();
        paramList = ((List)localObject).iterator();
      }
    }
    while (true)
    {
      if (!paramList.hasNext())
        return paramString;
      localObject = (Map.Entry)paramList.next();
      HashMap localHashMap = new HashMap();
      localHashMap.put("name", ((Map.Entry)localObject).getKey());
      localHashMap.put("id", ((Map.Entry)localObject).getValue());
      paramString.add(localHashMap);
    }
  }

  private int getScreenHeight()
  {
    return this.context.getResources().getDisplayMetrics().heightPixels;
  }

  private void initAreaData(int paramInt)
  {
    if (paramInt == 0)
      return;
    HttpRequest.getInstance().executePostStringRequest(this.context, Url.GET_AREA, HttpWhat.GET_AREA_WHAT, HttpRequestParam.getAreaParams(paramInt), this);
    this.progressbar.setVisibility(0);
  }

  private void initCityData(int paramInt)
  {
    if (paramInt == 0)
      return;
    HttpRequest.getInstance().executePostStringRequest(this.context, Url.GET_CITY, HttpWhat.GET_CITY_WHAT, HttpRequestParam.getCityParams(paramInt), this);
    this.progressbar.setVisibility(0);
  }

  private void initPop()
  {
    this.pop = new PopupWindow(this.popView, -1, getScreenHeight() / 5 * 3, true);
    this.pop.setBackgroundDrawable(new ColorDrawable(17170445));
    this.pop.setOutsideTouchable(true);
    this.pop.setAnimationStyle(2131427363);
    WindowManager.LayoutParams localLayoutParams = ((Activity)this.context).getWindow().getAttributes();
    localLayoutParams.alpha = 0.5F;
    ((Activity)this.context).getWindow().setAttributes(localLayoutParams);
    this.pop.showAtLocation(this.parentView, 80, 0, 0);
    this.pop.setOnDismissListener(new PopupWindow.OnDismissListener()
    {
      public void onDismiss()
      {
        WindowManager.LayoutParams localLayoutParams = ((Activity)SelectView.this.context).getWindow().getAttributes();
        localLayoutParams.alpha = 1.0F;
        ((Activity)SelectView.this.context).getWindow().setAttributes(localLayoutParams);
      }
    });
  }

  private void initProvinceData()
  {
    HttpRequest.getInstance().executePostStringRequest(this.context, Url.GET_PROVINCE, HttpWhat.GET_PROVINCE_WHAT, null, this);
    this.progressbar.setVisibility(0);
  }

  private void initTownData(int paramInt)
  {
    if (paramInt == 0)
      return;
    HttpRequest.getInstance().executePostStringRequest(this.context, Url.GET_TOWN, HttpWhat.GET_TOWN_WHAT, HttpRequestParam.getTownParams(paramInt), this);
    this.progressbar.setVisibility(0);
  }

  private void setToX(int paramInt)
  {
    if (paramInt == 1)
      this.toX = 0.0F;
    do
    {
      return;
      if (paramInt == 2)
      {
        this.toX = (this.tvProvince.getMeasuredWidth() + dip2px(this.context, 30.0F));
        return;
      }
      if (paramInt == 3)
      {
        this.toX = (this.tvProvince.getMeasuredWidth() + this.tvCity.getMeasuredWidth() + dip2px(this.context, 30.0F) * 2);
        return;
      }
    }
    while (paramInt != 4);
    this.toX = (this.tvProvince.getMeasuredWidth() + this.tvCity.getMeasuredWidth() + this.tvArea.getMeasuredWidth() + dip2px(this.context, 30.0F) * 3);
  }

  private void showListView(int paramInt)
  {
    if (paramInt == 1)
    {
      this.provinceListView.setVisibility(0);
      this.cityListView.setVisibility(8);
      this.areaListView.setVisibility(8);
      this.townListView.setVisibility(8);
      this.tvProvince.setTextColor(this.context.getResources().getColor(2131099700));
      this.tvCity.setTextColor(this.context.getResources().getColor(2131099705));
      this.tvArea.setTextColor(this.context.getResources().getColor(2131099705));
      this.tvTown.setTextColor(this.context.getResources().getColor(2131099705));
    }
    do
    {
      return;
      if (paramInt == 2)
      {
        this.provinceListView.setVisibility(8);
        this.cityListView.setVisibility(0);
        this.areaListView.setVisibility(8);
        this.townListView.setVisibility(8);
        this.tvCity.setVisibility(0);
        this.tvCity.setTextColor(this.context.getResources().getColor(2131099700));
        this.tvProvince.setTextColor(this.context.getResources().getColor(2131099705));
        this.tvArea.setTextColor(this.context.getResources().getColor(2131099705));
        this.tvTown.setTextColor(this.context.getResources().getColor(2131099705));
        return;
      }
      if (paramInt == 3)
      {
        this.provinceListView.setVisibility(8);
        this.cityListView.setVisibility(8);
        this.areaListView.setVisibility(0);
        this.townListView.setVisibility(8);
        this.tvArea.setVisibility(0);
        this.tvArea.setTextColor(this.context.getResources().getColor(2131099700));
        this.tvCity.setTextColor(this.context.getResources().getColor(2131099705));
        this.tvProvince.setTextColor(this.context.getResources().getColor(2131099705));
        this.tvTown.setTextColor(this.context.getResources().getColor(2131099705));
        return;
      }
      if (paramInt == 4)
      {
        this.provinceListView.setVisibility(8);
        this.cityListView.setVisibility(8);
        this.areaListView.setVisibility(8);
        this.townListView.setVisibility(0);
        this.tvTown.setVisibility(0);
        this.tvTown.setTextColor(this.context.getResources().getColor(2131099700));
        this.tvCity.setTextColor(this.context.getResources().getColor(2131099705));
        this.tvArea.setTextColor(this.context.getResources().getColor(2131099705));
        this.tvProvince.setTextColor(this.context.getResources().getColor(2131099705));
        return;
      }
      if (paramInt == 5)
      {
        this.provinceListView.setVisibility(8);
        this.cityListView.setVisibility(8);
        this.areaListView.setVisibility(0);
        this.townListView.setVisibility(8);
        this.tvTown.setVisibility(0);
        this.tvTown.setTextColor(this.context.getResources().getColor(2131099700));
        this.tvCity.setTextColor(this.context.getResources().getColor(2131099705));
        this.tvArea.setTextColor(this.context.getResources().getColor(2131099705));
        this.tvProvince.setTextColor(this.context.getResources().getColor(2131099705));
        return;
      }
    }
    while (paramInt != 6);
    this.provinceListView.setVisibility(8);
    this.cityListView.setVisibility(0);
    this.areaListView.setVisibility(8);
    this.townListView.setVisibility(8);
    this.tvArea.setVisibility(0);
    this.tvArea.setTextColor(this.context.getResources().getColor(2131099700));
    this.tvCity.setTextColor(this.context.getResources().getColor(2131099705));
    this.tvTown.setTextColor(this.context.getResources().getColor(2131099705));
    this.tvProvince.setTextColor(this.context.getResources().getColor(2131099705));
  }

  public void init(Context paramContext, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString1, String paramString2, String paramString3, String paramString4, SelectViewCallback paramSelectViewCallback)
  {
    this.context = paramContext;
    this.parentView = paramView;
    this.mSelectViewCallback = paramSelectViewCallback;
    this.popView = LayoutInflater.from(paramContext).inflate(2130903173, null);
    findViewById();
    initPop();
    if (paramInt1 == 0)
    {
      initProvinceData();
      return;
    }
    this.tvProvince.setVisibility(0);
    this.tvCity.setVisibility(0);
    this.tvProvince.setText(paramString1);
    this.tvCity.setText(paramString2);
    paramContext = new HashMap();
    paramContext.put("id", Integer.valueOf(paramInt1));
    paramContext.put("name", paramString1);
    this.tvProvince.setTag(paramContext);
    paramContext = new HashMap();
    paramContext.put("id", Integer.valueOf(paramInt2));
    paramContext.put("name", paramString2);
    this.tvCity.setTag(paramContext);
    if (paramInt3 == 0)
    {
      this.modifyTag = 3;
      this.tvCity.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          SelectView.this.tvCity.getViewTreeObserver().removeGlobalOnLayoutListener(this);
          int i = SelectView.this.tvCity.getMeasuredWidth();
          SelectView.this.cursor.getLayoutParams().width = i;
        }
      });
      initProvinceData();
      initCityData(paramInt1);
      return;
    }
    this.tvArea.setVisibility(0);
    this.tvArea.setText(paramString3);
    paramContext = new HashMap();
    paramContext.put("id", Integer.valueOf(paramInt3));
    paramContext.put("name", paramString3);
    this.tvArea.setTag(paramContext);
    this.tvArea.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        SelectView.this.tvArea.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        int i = SelectView.this.tvArea.getMeasuredWidth();
        SelectView.this.cursor.getLayoutParams().width = i;
      }
    });
    if (paramInt4 == 0)
    {
      this.modifyTag = 1;
      initProvinceData();
      initCityData(paramInt1);
      initAreaData(paramInt2);
      return;
    }
    paramContext = new HashMap();
    paramContext.put("id", Integer.valueOf(paramInt4));
    paramContext.put("name", paramString4);
    this.tvTown.setTag(paramContext);
    this.modifyTag = 2;
    initProvinceData();
    initCityData(paramInt1);
    initAreaData(paramInt2);
    initTownData(paramInt3);
    this.tvTown.setVisibility(0);
    this.tvTown.setText(paramString4);
    this.tvTown.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        SelectView.this.tvTown.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        int i = SelectView.this.tvTown.getMeasuredWidth();
        SelectView.this.cursor.getLayoutParams().width = i;
      }
    });
  }

  public void onClick(View paramView)
  {
    int i = 0;
    switch (paramView.getId())
    {
    default:
    case 2131231266:
    case 2131231267:
    case 2131231268:
    case 2131231269:
    }
    while (true)
    {
      cursorMove(this.fromX, i);
      return;
      showListView(1);
      setToX(1);
      i = 1;
      continue;
      showListView(2);
      setToX(2);
      i = 2;
      continue;
      showListView(3);
      setToX(3);
      i = 3;
      continue;
      showListView(4);
      setToX(4);
      i = 4;
    }
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.modifyTag = 0;
    switch (paramAdapterView.getId())
    {
    default:
    case 2131231271:
    case 2131231272:
    case 2131231273:
    case 2131231274:
    }
    do
    {
      return;
      if (this.cityList != null)
        this.cityList.clear();
      if (this.areaList != null)
        this.areaList.clear();
      if (this.townList != null)
        this.townList.clear();
      this.provinceAdapter.setSelectIndex(paramInt);
      this.tvProvince.setText((String)((HashMap)this.provinceList.get(paramInt)).get("name"));
      this.tvProvince.setTag(this.provinceList.get(paramInt));
      initCityData(((Integer)((HashMap)this.provinceList.get(paramInt)).get("id")).intValue());
      if ((this.provinceAdapter != null) && (this.provincePosition != this.provinceAdapter.getSelectIndex()))
      {
        if (this.cityAdapter != null)
          this.cityAdapter.setSelectIndex(-1);
        if (this.areaAdapter != null)
          this.areaAdapter.setSelectIndex(-1);
        if (this.townAdapter != null)
          this.townAdapter.setSelectIndex(-1);
        this.tvCity.setText("请选择");
        this.tvArea.setText("请选择");
        this.tvTown.setText("请选择");
        this.tvArea.setVisibility(4);
        this.tvTown.setVisibility(4);
      }
      this.provincePosition = paramInt;
      return;
      if (this.areaList != null)
        this.areaList.clear();
      if (this.townList != null)
        this.townList.clear();
      this.cityAdapter.setSelectIndex(paramInt);
      this.tvCity.setText((String)((HashMap)this.cityList.get(paramInt)).get("name"));
      this.tvCity.setTag(this.cityList.get(paramInt));
      initAreaData(((Integer)((HashMap)this.cityList.get(paramInt)).get("id")).intValue());
      if ((this.cityAdapter != null) && (this.cityPosition != this.cityAdapter.getSelectIndex()))
      {
        if (this.areaAdapter != null)
          this.areaAdapter.setSelectIndex(-1);
        if (this.townAdapter != null)
          this.townAdapter.setSelectIndex(-1);
        this.tvArea.setText("请选择");
        this.tvTown.setText("请选择");
        this.tvTown.setVisibility(4);
      }
      this.cityPosition = paramInt;
      return;
      if (this.townList != null)
        this.townList.clear();
      this.areaAdapter.setSelectIndex(paramInt);
      this.tvArea.setText((String)((HashMap)this.areaList.get(paramInt)).get("name"));
      this.tvArea.setTag(this.areaList.get(paramInt));
      initTownData(((Integer)((HashMap)this.areaList.get(paramInt)).get("id")).intValue());
      if ((this.areaAdapter != null) && (this.areaPosition != this.areaAdapter.getSelectIndex()))
      {
        if (this.townAdapter != null)
          this.townAdapter.setSelectIndex(-1);
        this.tvTown.setText("请选择");
      }
      this.areaPosition = paramInt;
      return;
      this.townAdapter.setSelectIndex(paramInt);
      this.tvTown.setText((String)((HashMap)this.townList.get(paramInt)).get("name"));
      this.tvTown.setTag(this.townList.get(paramInt));
      this.townPosition = paramInt;
      this.pop.dismiss();
    }
    while (this.mSelectViewCallback == null);
    paramAdapterView = new ArrayList();
    paramAdapterView.add((HashMap)this.tvProvince.getTag());
    paramAdapterView.add((HashMap)this.tvCity.getTag());
    paramAdapterView.add((HashMap)this.tvArea.getTag());
    paramAdapterView.add((HashMap)this.tvTown.getTag());
    this.mSelectViewCallback.callback(paramAdapterView);
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    this.progressbar.setVisibility(8);
    int i;
    if (paramInt == HttpWhat.GET_PROVINCE_WHAT)
    {
      Logger.i("获取省数据: " + (String)paramResponse.get());
      this.provinceList = getData((String)paramResponse.get(), this.provinceList);
      this.provinceAdapter = new ProvinceAdapter(this.provinceList);
      this.provinceListView.setAdapter(this.provinceAdapter);
      if (this.modifyTag > 0)
      {
        i = ((Integer)((HashMap)this.tvProvince.getTag()).get("id")).intValue();
        paramInt = 0;
        if (paramInt < this.provinceList.size());
      }
    }
    label1172: 
    while (true)
    {
      return;
      if (((Integer)((HashMap)this.provinceList.get(paramInt)).get("id")).intValue() == i)
      {
        this.provinceAdapter.setSelectIndex(paramInt);
        this.provinceListView.setSelection(paramInt);
        return;
      }
      paramInt += 1;
      break;
      showListView(1);
      return;
      if (paramInt == HttpWhat.GET_CITY_WHAT)
      {
        Logger.i("获取市数据：" + (String)paramResponse.get());
        this.cityList = getData((String)paramResponse.get(), this.cityList);
        if (this.cityAdapter == null)
        {
          this.cityAdapter = new CityAdapter(this.cityList);
          this.cityListView.setAdapter(this.cityAdapter);
          if (this.modifyTag > 0)
          {
            i = ((Integer)((HashMap)this.tvCity.getTag()).get("id")).intValue();
            paramInt = 0;
          }
        }
        else
        {
          while (true)
          {
            if (paramInt >= this.cityList.size());
            while (true)
            {
              if (this.modifyTag != 3)
                break label424;
              showListView(2);
              setToX(2);
              cursorMove(this.fromX, 2);
              return;
              this.cityAdapter.notifyDataSetChanged();
              this.cityListView.setSelection(0);
              break;
              if (((Integer)((HashMap)this.cityList.get(paramInt)).get("id")).intValue() != i)
                break label426;
              this.cityAdapter.setSelectIndex(paramInt);
              this.cityListView.setSelection(paramInt);
            }
            label424: break;
            label426: paramInt += 1;
          }
        }
        showListView(2);
        setToX(2);
        cursorMove(this.fromX, 2);
        return;
      }
      if (paramInt == HttpWhat.GET_AREA_WHAT)
      {
        Logger.i("区数据：" + (String)paramResponse.get());
        this.areaList = getData((String)paramResponse.get(), this.areaList);
        if ((this.areaList == null) || (this.areaList.isEmpty()))
        {
          Logger.i("没有区数据");
          setToX(3);
          cursorMove(this.fromX, 3);
          showListView(6);
          this.pop.dismiss();
          if (this.mSelectViewCallback != null)
          {
            paramResponse = new ArrayList();
            paramResponse.add((HashMap)this.tvProvince.getTag());
            paramResponse.add((HashMap)this.tvCity.getTag());
            this.mSelectViewCallback.callback(paramResponse);
          }
        }
        else
        {
          if (this.areaAdapter == null)
          {
            this.areaAdapter = new AreaAdapter(this.areaList);
            this.areaListView.setAdapter(this.areaAdapter);
            if (this.modifyTag > 0)
            {
              i = ((Integer)((HashMap)this.tvArea.getTag()).get("id")).intValue();
              paramInt = 0;
            }
          }
          else
          {
            while (true)
            {
              if (paramInt >= this.areaList.size());
              while (true)
              {
                if (this.modifyTag != 1)
                  break label792;
                showListView(3);
                setToX(3);
                cursorMove(this.fromX, 3);
                return;
                this.areaAdapter.notifyDataSetChanged();
                this.areaListView.setSelection(0);
                break;
                if (((Integer)((HashMap)this.areaList.get(paramInt)).get("id")).intValue() != i)
                  break label794;
                this.areaAdapter.setSelectIndex(paramInt);
                this.areaListView.setSelection(paramInt);
              }
              label792: break;
              label794: paramInt += 1;
            }
          }
          showListView(3);
          setToX(3);
          cursorMove(this.fromX, 3);
        }
      }
      else if (paramInt == HttpWhat.GET_TOWN_WHAT)
      {
        Logger.i("镇数据：" + (String)paramResponse.get());
        this.townList = getData((String)paramResponse.get(), this.townList);
        if ((this.townList == null) || (this.townList.isEmpty()))
        {
          Logger.i("没有镇数据");
          showListView(5);
          setToX(4);
          cursorMove(this.fromX, 4);
          this.pop.dismiss();
          if (this.mSelectViewCallback != null)
          {
            paramResponse = new ArrayList();
            paramResponse.add((HashMap)this.tvProvince.getTag());
            paramResponse.add((HashMap)this.tvCity.getTag());
            paramResponse.add((HashMap)this.tvArea.getTag());
            this.mSelectViewCallback.callback(paramResponse);
          }
        }
        else if (this.townAdapter == null)
        {
          this.townAdapter = new TownAdapter(this.townList);
          this.townListView.setAdapter(this.townAdapter);
          showListView(4);
          setToX(4);
          cursorMove(this.fromX, 4);
          if (this.modifyTag > 0)
          {
            i = ((Integer)((HashMap)this.tvTown.getTag()).get("id")).intValue();
            paramInt = 0;
          }
        }
        else
        {
          while (true)
          {
            if (paramInt >= this.townList.size())
              break label1172;
            if (((Integer)((HashMap)this.townList.get(paramInt)).get("id")).intValue() == i)
            {
              this.townAdapter.setSelectIndex(paramInt);
              this.townListView.setSelection(paramInt);
              return;
              this.townAdapter.notifyDataSetChanged();
              this.townListView.setSelection(0);
              break;
            }
            paramInt += 1;
          }
        }
      }
    }
  }

  private class AreaAdapter extends BaseAdapter
  {
    LayoutInflater inflater;
    List<HashMap<String, Object>> list;
    int selectIndex = -1;

    public AreaAdapter()
    {
      Object localObject;
      this.list = localObject;
      this.inflater = LayoutInflater.from(SelectView.this.context);
    }

    public int getCount()
    {
      if (this.list == null)
        return 0;
      return this.list.size();
    }

    public Object getItem(int paramInt)
    {
      return this.list.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public int getSelectIndex()
    {
      return this.selectIndex;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramViewGroup = new ViewHolder();
        paramView = this.inflater.inflate(2130903157, null);
        paramViewGroup.tv1 = ((TextView)paramView.findViewById(2131231200));
        paramView.setTag(paramViewGroup);
      }
      while (true)
      {
        HashMap localHashMap = (HashMap)getItem(paramInt);
        paramViewGroup.tv1.setText((String)localHashMap.get("name"));
        if (paramInt != this.selectIndex)
          break;
        paramViewGroup.tv1.setTextColor(SelectView.this.context.getResources().getColor(2131099700));
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
      }
      paramViewGroup.tv1.setTextColor(SelectView.this.context.getResources().getColor(2131099705));
      return paramView;
    }

    public void setSelectIndex(int paramInt)
    {
      this.selectIndex = paramInt;
      notifyDataSetChanged();
    }

    class ViewHolder
    {
      TextView tv1;

      ViewHolder()
      {
      }
    }
  }

  private class CityAdapter extends BaseAdapter
  {
    LayoutInflater inflater;
    List<HashMap<String, Object>> list;
    int selectIndex = -1;

    public CityAdapter()
    {
      Object localObject;
      this.list = localObject;
      this.inflater = LayoutInflater.from(SelectView.this.context);
    }

    public int getCount()
    {
      if (this.list == null)
        return 0;
      return this.list.size();
    }

    public Object getItem(int paramInt)
    {
      return this.list.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public int getSelectIndex()
    {
      return this.selectIndex;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramViewGroup = new ViewHolder();
        paramView = this.inflater.inflate(2130903157, null);
        paramViewGroup.tv1 = ((TextView)paramView.findViewById(2131231200));
        paramView.setTag(paramViewGroup);
      }
      while (true)
      {
        HashMap localHashMap = (HashMap)getItem(paramInt);
        paramViewGroup.tv1.setText((String)localHashMap.get("name"));
        if (paramInt != this.selectIndex)
          break;
        paramViewGroup.tv1.setTextColor(SelectView.this.context.getResources().getColor(2131099700));
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
      }
      paramViewGroup.tv1.setTextColor(SelectView.this.context.getResources().getColor(2131099705));
      return paramView;
    }

    public void setSelectIndex(int paramInt)
    {
      this.selectIndex = paramInt;
      notifyDataSetChanged();
    }

    class ViewHolder
    {
      TextView tv1;

      ViewHolder()
      {
      }
    }
  }

  private class ProvinceAdapter extends BaseAdapter
  {
    LayoutInflater inflater;
    List<HashMap<String, Object>> list;
    int selectIndex = -1;

    public ProvinceAdapter()
    {
      Object localObject;
      this.list = localObject;
      this.inflater = LayoutInflater.from(SelectView.this.context);
    }

    public int getCount()
    {
      if (this.list == null)
        return 0;
      return this.list.size();
    }

    public Object getItem(int paramInt)
    {
      return this.list.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public int getSelectIndex()
    {
      return this.selectIndex;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramViewGroup = new ViewHolder();
        paramView = this.inflater.inflate(2130903157, null);
        paramViewGroup.tv1 = ((TextView)paramView.findViewById(2131231200));
        paramView.setTag(paramViewGroup);
      }
      while (true)
      {
        HashMap localHashMap = (HashMap)getItem(paramInt);
        paramViewGroup.tv1.setText((String)localHashMap.get("name"));
        if (paramInt != this.selectIndex)
          break;
        paramViewGroup.tv1.setTextColor(SelectView.this.context.getResources().getColor(2131099700));
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
      }
      paramViewGroup.tv1.setTextColor(SelectView.this.context.getResources().getColor(2131099705));
      return paramView;
    }

    public void setSelectIndex(int paramInt)
    {
      this.selectIndex = paramInt;
      notifyDataSetChanged();
    }

    class ViewHolder
    {
      TextView tv1;

      ViewHolder()
      {
      }
    }
  }

  private class TownAdapter extends BaseAdapter
  {
    LayoutInflater inflater;
    List<HashMap<String, Object>> list;
    int selectIndex = -1;

    public TownAdapter()
    {
      Object localObject;
      this.list = localObject;
      this.inflater = LayoutInflater.from(SelectView.this.context);
    }

    public int getCount()
    {
      if (this.list == null)
        return 0;
      return this.list.size();
    }

    public Object getItem(int paramInt)
    {
      return this.list.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public int getSelectIndex()
    {
      return this.selectIndex;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramViewGroup = new ViewHolder();
        paramView = this.inflater.inflate(2130903157, null);
        paramViewGroup.tv1 = ((TextView)paramView.findViewById(2131231200));
        paramView.setTag(paramViewGroup);
      }
      while (true)
      {
        HashMap localHashMap = (HashMap)getItem(paramInt);
        paramViewGroup.tv1.setText((String)localHashMap.get("name"));
        if (paramInt != this.selectIndex)
          break;
        paramViewGroup.tv1.setTextColor(SelectView.this.context.getResources().getColor(2131099700));
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
      }
      paramViewGroup.tv1.setTextColor(SelectView.this.context.getResources().getColor(2131099705));
      return paramView;
    }

    public void setSelectIndex(int paramInt)
    {
      this.selectIndex = paramInt;
      notifyDataSetChanged();
    }

    class ViewHolder
    {
      TextView tv1;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.SelectView
 * JD-Core Version:    0.6.2
 */