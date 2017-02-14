package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.ScreenBrand_Adapter;
import com.ismartgo.app.bean.Brands;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.service.IbeaconService;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ScreenBrandActivity extends BaseActivity
{
  private final int RESULT_CODE = 10002;

  @AbIocView(click="screenBrandClick", id=2131230997)
  CheckBox brand_all_checked;

  @AbIocView(click="screenBrandClick", id=2131230999)
  CheckBox brand_none_checked;

  @AbIocView(id=2131230942)
  private View bt_select;

  @AbIocView(id=2131230855)
  private LinearLayout load_nothing_layout;
  private MyDialog mDialog;

  @AbIocView(click="screenBrandClick", id=2131231000)
  ImageView pv_back;

  @AbIocView(click="screenBrandClick", id=2131231002)
  TextView pv_screening;
  ScreenBrand_Adapter screenBrandAdapter;

  @AbIocView(id=2131231003, itemClick="screenBrankItemClick")
  ListView screen_brand;

  @AbIocView(id=2131230954)
  private LinearLayout screen_brand_layout;
  private HashMap<Integer, Brands> screen_map;
  private String selected = "";

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("筛选品牌");
  }

  private void selectedOK()
  {
    Object localObject2 = "";
    Object localObject1 = "";
    int i = 0;
    while (true)
    {
      if (i >= this.screenBrandAdapter.selectMap.size())
      {
        localObject3 = localObject2;
        if (((String)localObject2).endsWith(","))
          localObject3 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
        localObject2 = localObject1;
        if (((String)localObject1).endsWith(","))
          localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
        Log.e("System", "--->str=" + (String)localObject3);
        localObject1 = new Intent();
        ((Intent)localObject1).putExtra("brandIds", (String)localObject3);
        ((Intent)localObject1).putExtra("brandValues", (String)localObject2);
        setResult(10002, (Intent)localObject1);
        finish();
        return;
      }
      Object localObject4 = localObject1;
      Object localObject3 = localObject2;
      if (((Boolean)this.screenBrandAdapter.selectMap.get(Integer.valueOf(i))).booleanValue())
      {
        localObject3 = localObject2 + i + ",";
        localObject4 = localObject1 + ((Brands)this.screen_map.get(Integer.valueOf(i))).getBrandId() + ",";
      }
      i += 1;
      localObject1 = localObject4;
      localObject2 = localObject3;
    }
  }

  private void setData()
  {
    HashMap localHashMap = new HashMap();
    this.mDialog.show();
    localHashMap.put("categoryId", getIntent().getStringExtra("categoryId"));
    localHashMap.put("categoryId2", IbeaconService.category2Id);
    HttpRequest.getInstance().executePostStringRequest(this, Url.SHOP_BRAND_URL, 52, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        ScreenBrandActivity.this.toast.setMessage(ScreenBrandActivity.this.getString(2131296392));
        ScreenBrandActivity.this.toast.show();
        ScreenBrandActivity.this.mDialog.dismiss();
        ScreenBrandActivity.this.bt_select.setVisibility(8);
        ScreenBrandActivity.this.screen_brand_layout.setVisibility(8);
        ScreenBrandActivity.this.load_nothing_layout.setVisibility(0);
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        ScreenBrandActivity.this.mDialog.dismiss();
        Log.e("hahaha", "--->品牌,t=" + ((String)paramAnonymousResponse.get()).toString());
        while (true)
        {
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() != ResultCode.RESULT_OK)
              break label424;
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONArray("brands");
            if ((paramAnonymousResponse == null) || (paramAnonymousResponse.length() == 0))
            {
              ScreenBrandActivity.this.bt_select.setVisibility(8);
              ScreenBrandActivity.this.screen_brand_layout.setVisibility(8);
              ScreenBrandActivity.this.load_nothing_layout.setVisibility(0);
              ScreenBrandActivity.this.toast.setMessage("所选品类下无品牌可选");
              ScreenBrandActivity.this.toast.show();
              return;
            }
            ScreenBrandActivity.this.screen_brand_layout.setVisibility(0);
            ScreenBrandActivity.this.load_nothing_layout.setVisibility(8);
            ScreenBrandActivity.this.bt_select.setVisibility(0);
            ScreenBrandActivity.this.screen_map = new HashMap();
            paramAnonymousInt = 0;
            if (paramAnonymousInt >= paramAnonymousResponse.length())
            {
              ScreenBrandActivity.this.screenBrandAdapter = new ScreenBrand_Adapter(ScreenBrandActivity.this, ScreenBrandActivity.this.screen_map, ScreenBrandActivity.this.selected);
              ScreenBrandActivity.this.screen_brand.setAdapter(ScreenBrandActivity.this.screenBrandAdapter);
              paramAnonymousResponse = ScreenBrandActivity.this.brand_all_checked;
              if (!ScreenBrandActivity.this.screenBrandAdapter.selectMap.containsValue(Boolean.valueOf(false)))
                break label451;
              bool = false;
              paramAnonymousResponse.setChecked(bool);
              paramAnonymousResponse = ScreenBrandActivity.this.brand_none_checked;
              if (!ScreenBrandActivity.this.screenBrandAdapter.selectMap.containsValue(Boolean.valueOf(true)))
                break label456;
              bool = false;
              paramAnonymousResponse.setChecked(bool);
              ScreenBrandActivity.this.screenBrandAdapter.notifyDataSetChanged();
              return;
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            paramAnonymousResponse.printStackTrace();
            return;
          }
          JSONObject localJSONObject = paramAnonymousResponse.getJSONObject(paramAnonymousInt);
          Brands localBrands = new Brands();
          localBrands.setBrandId(localJSONObject.getInt("id"));
          localBrands.setBrandName(localJSONObject.getString("name"));
          ScreenBrandActivity.this.screen_map.put(Integer.valueOf(paramAnonymousInt), localBrands);
          paramAnonymousInt += 1;
          continue;
          label424: ScreenBrandActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          ScreenBrandActivity.this.toast.show();
          return;
          label451: boolean bool = true;
          continue;
          label456: bool = true;
        }
      }
    });
  }

  public void clickCheckBox(int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = ((Boolean)this.screenBrandAdapter.selectMap.get(Integer.valueOf(paramInt))).booleanValue();
    Object localObject = this.screenBrandAdapter.selectMap;
    if (bool1)
    {
      bool1 = false;
      ((Map)localObject).put(Integer.valueOf(paramInt), Boolean.valueOf(bool1));
      localObject = this.brand_all_checked;
      if (!this.screenBrandAdapter.selectMap.containsValue(Boolean.valueOf(false)))
        break label135;
      bool1 = false;
      label83: ((CheckBox)localObject).setChecked(bool1);
      localObject = this.brand_none_checked;
      if (!this.screenBrandAdapter.selectMap.containsValue(Boolean.valueOf(true)))
        break label140;
    }
    label135: label140: for (bool1 = bool2; ; bool1 = true)
    {
      ((CheckBox)localObject).setChecked(bool1);
      this.screenBrandAdapter.notifyDataSetChanged();
      return;
      bool1 = true;
      break;
      bool1 = true;
      break label83;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903080);
    this.mDialog = new MyDialog(this);
    if (getIntent().getStringExtra("brandIds") != null)
      this.selected = getIntent().getStringExtra("brandIds");
    initView();
    setData();
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  public void screenBrandClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131230998:
    case 2131231001:
    default:
      return;
    case 2131231000:
      finish();
      return;
    case 2131231002:
      selectedOK();
      return;
    case 2131230997:
      this.brand_all_checked.setChecked(true);
      this.brand_none_checked.setChecked(false);
      i = 0;
      while (true)
      {
        if (i >= this.screen_map.size())
        {
          this.screenBrandAdapter.notifyDataSetChanged();
          return;
        }
        this.screenBrandAdapter.selectMap.put(Integer.valueOf(i), Boolean.valueOf(true));
        i += 1;
      }
    case 2131230999:
    }
    this.brand_none_checked.setChecked(true);
    this.brand_all_checked.setChecked(false);
    int i = 0;
    while (true)
    {
      if (i >= this.screen_map.size())
      {
        this.screenBrandAdapter.notifyDataSetChanged();
        return;
      }
      this.screenBrandAdapter.selectMap.put(Integer.valueOf(i), Boolean.valueOf(false));
      i += 1;
    }
  }

  public void screenBrankItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    clickCheckBox(paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ScreenBrandActivity
 * JD-Core Version:    0.6.2
 */