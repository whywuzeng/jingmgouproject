package com.ismartgo.app.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.bean.UserUsuallyAddress;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.impl.SelectViewCallback;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.view.SelectView;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.mrwujay.cascade.model.CityModel;
import com.mrwujay.cascade.model.DistrictModel;
import com.mrwujay.cascade.model.ProvinceModel;
import com.mrwujay.cascade.service.XmlParserHandler;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import net.tsz.afinal.FinalHttp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;

public class SetUsuallyAddressActivity extends BaseActivity
  implements View.OnClickListener, TextWatcher, OnWheelChangedListener
{
  private Button btnCancel;
  private Button btnOk;
  private Button btnSubmit;
  private EditText etDetailAddress;
  private EditText etName;
  private EditText etPhone;
  private FinalHttp http;
  private LinearLayout layoutSelectAddress;
  public Map<String, String[]> mCitisDatasMap = new HashMap();
  public int mCurrentCityId;
  public String mCurrentCityName;
  public int mCurrentDistrictId;
  public String mCurrentDistrictName = "";
  public int mCurrentProviceId;
  public String mCurrentProviceName;
  public int mCurrentTownId;
  public String mCurrentTownName;
  private MyDialog mDialog;
  public Map<String, String[]> mDistrictDatasMap = new HashMap();
  public String[] mProvinceDatas;
  private WheelView mViewCity;
  private WheelView mViewDistrict;
  private WheelView mViewProvince;
  private View parentView;
  private List<HashMap<String, Object>> selectViewData;
  private ToastDefine toast;
  private TextView tvAddress;

  private void getUserContact()
  {
    if (loginUser == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", String.valueOf(loginUser.getId()));
    HttpRequest.getInstance().executePostStringRequest(this, Url.GET_USER_CONTACT, HttpWhat.GET_USER_CONTACT, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        Log.i("Test", "获取用户常用地址数据失败： " + paramAnonymousString);
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i("Test", "获取用户常用地址数据： " + ((String)paramAnonymousResponse.get()).toString());
        while (true)
        {
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (paramAnonymousResponse.getInt("status") != ResultCode.RESULT_OK)
              break label322;
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data");
            if (paramAnonymousResponse.isNull("userContact"))
              return;
            paramAnonymousResponse = paramAnonymousResponse.getJSONArray("userContact");
            if ((paramAnonymousResponse == null) || (paramAnonymousResponse.length() <= 0))
              break label323;
            SetUsuallyAddressActivity.usuallyAddressList = new ArrayList();
            break label323;
            if (paramAnonymousInt >= paramAnonymousResponse.length())
            {
              if ((SetUsuallyAddressActivity.usuallyAddressList != null) && (SetUsuallyAddressActivity.usuallyAddressList.size() > 0))
                SetUsuallyAddressActivity.this.setUsuallyAddress(SetUsuallyAddressActivity.usuallyAddressList);
              SetUsuallyAddressActivity.this.initData();
              return;
            }
          }
          catch (JSONException paramAnonymousResponse)
          {
            paramAnonymousResponse.printStackTrace();
            return;
          }
          UserUsuallyAddress localUserUsuallyAddress = new UserUsuallyAddress();
          JSONObject localJSONObject = paramAnonymousResponse.getJSONObject(paramAnonymousInt);
          localUserUsuallyAddress.setName(localJSONObject.optString("contactPerson"));
          localUserUsuallyAddress.setMobile(localJSONObject.optString("mobile"));
          localUserUsuallyAddress.setProvince(localJSONObject.optString("province"));
          localUserUsuallyAddress.setCity(localJSONObject.optString("city"));
          localUserUsuallyAddress.setDistrict(localJSONObject.optString("district"));
          localUserUsuallyAddress.setDetailAddress(localJSONObject.optString("address"));
          localUserUsuallyAddress.setIsDefault(localJSONObject.getInt("isDefault"));
          localUserUsuallyAddress.setTown(localJSONObject.optString("town"));
          localUserUsuallyAddress.setProvinceId(localJSONObject.optInt("province_id"));
          localUserUsuallyAddress.setCityId(localJSONObject.optInt("city_id"));
          localUserUsuallyAddress.setDistrictId(localJSONObject.optInt("district_id"));
          localUserUsuallyAddress.setTownId(localJSONObject.optInt("town_id"));
          SetUsuallyAddressActivity.usuallyAddressList.add(localUserUsuallyAddress);
          paramAnonymousInt += 1;
          continue;
          label322: return;
          label323: paramAnonymousInt = 0;
        }
      }
    });
  }

  private void initData()
  {
    if ((getUserUsuallyAddress() != null) && (getUserUsuallyAddress().size() > 0))
    {
      localObject = getUserUsuallyAddress().iterator();
      if (((Iterator)localObject).hasNext())
        break label35;
    }
    label35: 
    do
    {
      return;
      UserUsuallyAddress localUserUsuallyAddress = (UserUsuallyAddress)((Iterator)localObject).next();
      if (localUserUsuallyAddress.getIsDefault() != 1)
        break;
      this.etName.setText(localUserUsuallyAddress.getName());
      this.etPhone.setText(localUserUsuallyAddress.getMobile());
      localObject = "";
      if (!CommonMethod.isEmpty(localUserUsuallyAddress.getDistrict()))
        localObject = localUserUsuallyAddress.getDistrict();
      String str = "";
      if (!CommonMethod.isEmpty(localUserUsuallyAddress.getTown()))
        str = localUserUsuallyAddress.getTown();
      this.tvAddress.setText(localUserUsuallyAddress.getProvince() + localUserUsuallyAddress.getCity() + (String)localObject + str);
      this.etDetailAddress.setText(localUserUsuallyAddress.getDetailAddress());
      this.mCurrentProviceName = localUserUsuallyAddress.getProvince();
      this.mCurrentCityName = localUserUsuallyAddress.getCity();
      this.mCurrentDistrictName = localUserUsuallyAddress.getDistrict();
      this.mCurrentTownName = localUserUsuallyAddress.getTown();
      this.mCurrentProviceId = localUserUsuallyAddress.getProvinceId();
      this.mCurrentCityId = localUserUsuallyAddress.getCityId();
      this.mCurrentDistrictId = localUserUsuallyAddress.getDistrictId();
      this.mCurrentTownId = localUserUsuallyAddress.getTownId();
      this.selectViewData = new ArrayList();
      localObject = new HashMap();
      ((HashMap)localObject).put("name", this.mCurrentProviceName);
      ((HashMap)localObject).put("id", Integer.valueOf(this.mCurrentProviceId));
      this.selectViewData.add(localObject);
      localObject = new HashMap();
      ((HashMap)localObject).put("name", this.mCurrentCityName);
      ((HashMap)localObject).put("id", Integer.valueOf(this.mCurrentCityId));
      this.selectViewData.add(localObject);
      if (!CommonMethod.isEmpty(this.mCurrentDistrictName))
      {
        localObject = new HashMap();
        ((HashMap)localObject).put("name", this.mCurrentDistrictName);
        ((HashMap)localObject).put("id", Integer.valueOf(this.mCurrentDistrictId));
        this.selectViewData.add(localObject);
      }
    }
    while (CommonMethod.isEmpty(this.mCurrentTownName));
    Object localObject = new HashMap();
    ((HashMap)localObject).put("name", this.mCurrentTownName);
    ((HashMap)localObject).put("id", Integer.valueOf(this.mCurrentTownId));
    this.selectViewData.add(localObject);
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("添加地址");
    this.etName = ((EditText)findViewById(2131231038));
    this.etPhone = ((EditText)findViewById(2131230814));
    this.tvAddress = ((TextView)findViewById(2131230976));
    this.tvAddress.setOnClickListener(this);
    this.etDetailAddress = ((EditText)findViewById(2131230888));
    this.btnSubmit = ((Button)findViewById(2131231039));
    this.btnSubmit.setOnClickListener(this);
    this.etName.addTextChangedListener(this);
    this.etPhone.addTextChangedListener(this);
    this.etDetailAddress.addTextChangedListener(this);
    this.layoutSelectAddress = ((LinearLayout)findViewById(2131230892));
    this.mViewProvince = ((WheelView)findViewById(2131230893));
    this.mViewCity = ((WheelView)findViewById(2131230894));
    this.mViewDistrict = ((WheelView)findViewById(2131230895));
    setUpListener();
    setUpData();
    this.btnOk = ((Button)findViewById(2131230874));
    this.btnCancel = ((Button)findViewById(2131230873));
    this.btnOk.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SetUsuallyAddressActivity.this.tvAddress.setText(SetUsuallyAddressActivity.this.mCurrentProviceName + SetUsuallyAddressActivity.this.mCurrentCityName + SetUsuallyAddressActivity.this.mCurrentDistrictName);
        SetUsuallyAddressActivity.this.layoutSelectAddress.setVisibility(8);
      }
    });
    this.btnCancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SetUsuallyAddressActivity.this.layoutSelectAddress.getVisibility() == 0)
          SetUsuallyAddressActivity.this.layoutSelectAddress.setVisibility(8);
      }
    });
  }

  private void setUpData()
  {
    initProvinceDatas(this);
    this.mViewProvince.setViewAdapter(new ArrayWheelAdapter(this, this.mProvinceDatas));
    this.mViewProvince.setVisibleItems(7);
    this.mViewCity.setVisibleItems(7);
    this.mViewDistrict.setVisibleItems(7);
    updateCities();
    updateAreas();
  }

  private void setUpListener()
  {
    this.mViewProvince.addChangingListener(this);
    this.mViewCity.addChangingListener(this);
    this.mViewDistrict.addChangingListener(this);
  }

  private void updateAreas()
  {
    int i = this.mViewCity.getCurrentItem();
    this.mCurrentCityName = ((String[])this.mCitisDatasMap.get(this.mCurrentProviceName))[i];
    String[] arrayOfString2 = (String[])this.mDistrictDatasMap.get(this.mCurrentCityName);
    String[] arrayOfString1 = arrayOfString2;
    if (arrayOfString2 == null)
    {
      arrayOfString1 = new String[1];
      arrayOfString1[0] = "";
    }
    if (arrayOfString1.length > 0);
    for (this.mCurrentDistrictName = arrayOfString1[0]; ; this.mCurrentDistrictName = "")
    {
      this.mViewDistrict.setViewAdapter(new ArrayWheelAdapter(this, arrayOfString1));
      this.mViewDistrict.setCurrentItem(0);
      return;
    }
  }

  private void updateCities()
  {
    int i = this.mViewProvince.getCurrentItem();
    this.mCurrentProviceName = this.mProvinceDatas[i];
    String[] arrayOfString2 = (String[])this.mCitisDatasMap.get(this.mCurrentProviceName);
    String[] arrayOfString1 = arrayOfString2;
    if (arrayOfString2 == null)
    {
      arrayOfString1 = new String[1];
      arrayOfString1[0] = "";
    }
    this.mViewCity.setViewAdapter(new ArrayWheelAdapter(this, arrayOfString1));
    this.mViewCity.setCurrentItem(0);
    updateAreas();
  }

  public void afterTextChanged(Editable paramEditable)
  {
    if ((TextUtils.isEmpty(this.etName.getText().toString().trim())) || (TextUtils.isEmpty(this.etPhone.getText().toString().trim())) || (TextUtils.isEmpty(this.etDetailAddress.getText().toString().trim())) || (TextUtils.isEmpty(this.tvAddress.getText().toString().trim())))
    {
      this.btnSubmit.setBackgroundResource(2130837527);
      this.btnSubmit.setEnabled(false);
      return;
    }
    this.btnSubmit.setEnabled(true);
    this.btnSubmit.setBackgroundResource(2130837524);
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void initProvinceDatas(Context paramContext)
  {
    paramContext = paramContext.getAssets();
    try
    {
      paramContext = paramContext.open("province_data.xml");
      Object localObject1 = SAXParserFactory.newInstance().newSAXParser();
      Object localObject2 = new XmlParserHandler();
      ((SAXParser)localObject1).parse(paramContext, (DefaultHandler)localObject2);
      paramContext.close();
      paramContext = ((XmlParserHandler)localObject2).getDataList();
      if ((paramContext != null) && (!paramContext.isEmpty()))
      {
        this.mCurrentProviceName = ((ProvinceModel)paramContext.get(0)).getName();
        localObject1 = ((ProvinceModel)paramContext.get(0)).getCityList();
        if ((localObject1 != null) && (!((List)localObject1).isEmpty()))
        {
          this.mCurrentCityName = ((CityModel)((List)localObject1).get(0)).getName();
          this.mCurrentDistrictName = ((DistrictModel)((CityModel)((List)localObject1).get(0)).getDistrictList().get(0)).getName();
        }
      }
      this.mProvinceDatas = new String[paramContext.size()];
      int i = 0;
      int j;
      while (true)
      {
        if (i >= paramContext.size())
          return;
        this.mProvinceDatas[i] = ((ProvinceModel)paramContext.get(i)).getName();
        localObject1 = ((ProvinceModel)paramContext.get(i)).getCityList();
        localObject2 = new String[((List)localObject1).size()];
        j = 0;
        if (j < ((List)localObject1).size())
          break;
        this.mCitisDatasMap.put(((ProvinceModel)paramContext.get(i)).getName(), localObject2);
        i += 1;
      }
      localObject2[j] = ((CityModel)((List)localObject1).get(j)).getName();
      List localList = ((CityModel)((List)localObject1).get(j)).getDistrictList();
      String[] arrayOfString = new String[localList.size()];
      DistrictModel[] arrayOfDistrictModel = new DistrictModel[localList.size()];
      int k = 0;
      while (true)
      {
        if (k >= localList.size())
        {
          this.mDistrictDatasMap.put(localObject2[j], arrayOfString);
          j += 1;
          break;
        }
        DistrictModel localDistrictModel = new DistrictModel(((DistrictModel)localList.get(k)).getName());
        arrayOfDistrictModel[k] = localDistrictModel;
        arrayOfString[k] = localDistrictModel.getName();
        k += 1;
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public void onChanged(WheelView paramWheelView, int paramInt1, int paramInt2)
  {
    if (paramWheelView == this.mViewProvince)
      updateCities();
    do
    {
      return;
      if (paramWheelView == this.mViewCity)
      {
        updateAreas();
        return;
      }
    }
    while (paramWheelView != this.mViewDistrict);
    this.mCurrentDistrictName = ((String[])this.mDistrictDatasMap.get(this.mCurrentCityName))[paramInt2];
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231039:
      if (this.mDialog == null)
        this.mDialog = new MyDialog(this);
      if ((this.mDialog != null) && (!this.mDialog.isShowing()))
        this.mDialog.show();
      setUserContact();
      return;
    case 2131230976:
    }
    int j = 0;
    int k = 0;
    int m = 0;
    int i = 0;
    int i1 = 0;
    Object localObject1 = "";
    Object localObject2 = "";
    paramView = "";
    String str2 = "";
    int n = i1;
    View localView = paramView;
    String str1 = str2;
    if (this.selectViewData != null)
    {
      int i2 = ((Integer)((HashMap)this.selectViewData.get(0)).get("id")).intValue();
      int i3 = ((Integer)((HashMap)this.selectViewData.get(1)).get("id")).intValue();
      String str3 = (String)((HashMap)this.selectViewData.get(0)).get("name");
      String str4 = (String)((HashMap)this.selectViewData.get(1)).get("name");
      if (this.selectViewData.size() > 2)
      {
        i = ((Integer)((HashMap)this.selectViewData.get(2)).get("id")).intValue();
        paramView = (String)((HashMap)this.selectViewData.get(2)).get("name");
      }
      j = i2;
      k = i3;
      m = i;
      n = i1;
      localObject1 = str3;
      localObject2 = str4;
      localView = paramView;
      str1 = str2;
      if (this.selectViewData.size() > 3)
      {
        n = ((Integer)((HashMap)this.selectViewData.get(3)).get("id")).intValue();
        str1 = (String)((HashMap)this.selectViewData.get(3)).get("name");
        localView = paramView;
        localObject2 = str4;
        localObject1 = str3;
        m = i;
        k = i3;
        j = i2;
      }
    }
    new SelectView().init(this, this.parentView, j, k, m, n, (String)localObject1, (String)localObject2, localView, str1, new SelectViewCallback()
    {
      public void callback(List<HashMap<String, Object>> paramAnonymousList)
      {
        label244: StringBuffer localStringBuffer;
        if (paramAnonymousList != null)
        {
          SetUsuallyAddressActivity.this.selectViewData = paramAnonymousList;
          SetUsuallyAddressActivity.this.mCurrentProviceName = ((String)((HashMap)paramAnonymousList.get(0)).get("name"));
          SetUsuallyAddressActivity.this.mCurrentCityName = ((String)((HashMap)paramAnonymousList.get(1)).get("name"));
          SetUsuallyAddressActivity.this.mCurrentProviceId = ((Integer)((HashMap)paramAnonymousList.get(0)).get("id")).intValue();
          SetUsuallyAddressActivity.this.mCurrentCityId = ((Integer)((HashMap)paramAnonymousList.get(1)).get("id")).intValue();
          if (paramAnonymousList.size() <= 2)
            break label305;
          SetUsuallyAddressActivity.this.mCurrentDistrictName = ((String)((HashMap)paramAnonymousList.get(2)).get("name"));
          SetUsuallyAddressActivity.this.mCurrentDistrictId = ((Integer)((HashMap)paramAnonymousList.get(2)).get("id")).intValue();
          if (paramAnonymousList.size() <= 3)
            break label325;
          SetUsuallyAddressActivity.this.mCurrentTownName = ((String)((HashMap)paramAnonymousList.get(3)).get("name"));
          SetUsuallyAddressActivity.this.mCurrentTownId = ((Integer)((HashMap)paramAnonymousList.get(3)).get("id")).intValue();
          localStringBuffer = new StringBuffer();
          paramAnonymousList = paramAnonymousList.iterator();
        }
        while (true)
        {
          if (!paramAnonymousList.hasNext())
          {
            Logger.i("选择数据： " + localStringBuffer.toString());
            SetUsuallyAddressActivity.this.tvAddress.setText(localStringBuffer.toString());
            return;
            label305: SetUsuallyAddressActivity.this.mCurrentDistrictName = "";
            SetUsuallyAddressActivity.this.mCurrentDistrictId = 0;
            break;
            label325: SetUsuallyAddressActivity.this.mCurrentTownName = "";
            SetUsuallyAddressActivity.this.mCurrentTownId = 0;
            break label244;
          }
          localStringBuffer.append(((HashMap)paramAnonymousList.next()).get("name"));
        }
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.parentView = LayoutInflater.from(this).inflate(2130903088, null);
    setContentView(this.parentView);
    initView();
    if ((usuallyAddressList == null) || (usuallyAddressList.size() == 0))
    {
      getUserContact();
      return;
    }
    initData();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.layoutSelectAddress.getVisibility() == 0))
    {
      this.layoutSelectAddress.setVisibility(8);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
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

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void setUserContact()
  {
    if (loginUser == null)
      return;
    final String str5 = this.etName.getText().toString().trim();
    final String str6 = this.etPhone.getText().toString().trim();
    HashMap localHashMap = new HashMap();
    final String str7 = this.mCurrentProviceName;
    final String str8 = this.mCurrentCityName;
    final String str1;
    if (this.mCurrentDistrictName == null)
    {
      str1 = "";
      label72: if (this.mCurrentTownName != null)
        break label222;
    }
    String str3;
    String str4;
    final String str9;
    label222: for (final String str2 = ""; ; str2 = this.mCurrentTownName)
    {
      str3 = "";
      if (!CommonMethod.isEmpty(str1))
        str3 = String.valueOf(this.mCurrentDistrictId);
      str4 = "";
      if (!CommonMethod.isEmpty(str2))
        str4 = String.valueOf(this.mCurrentTownId);
      str9 = this.etDetailAddress.getText().toString().trim().replace("\n", "");
      if (CommonMethod.patternAddress(str9))
        break label230;
      if (this.toast == null)
        this.toast = new ToastDefine(this);
      this.toast.setMessage("地址不能包含特殊符号哦，改一改 ");
      this.toast.show();
      if ((this.mDialog == null) || (!this.mDialog.isShowing()))
        break;
      this.mDialog.dismiss();
      return;
      str1 = this.mCurrentDistrictName;
      break label72;
    }
    label230: localHashMap.put("uid", String.valueOf(loginUser.getId()));
    localHashMap.put("contactPerson", str5);
    localHashMap.put("mobile", str6);
    localHashMap.put("province", str7);
    localHashMap.put("city", str8);
    localHashMap.put("district", str1);
    localHashMap.put("town", str2);
    localHashMap.put("province_id", String.valueOf(this.mCurrentProviceId));
    localHashMap.put("city_id", String.valueOf(this.mCurrentCityId));
    localHashMap.put("district_id", str3);
    localHashMap.put("town_id", str4);
    localHashMap.put("address", str9);
    localHashMap.put("isDefault", "1");
    HttpRequest.getInstance().executePostStringRequest(this, Url.SET_USER_CONTACT, HttpWhat.SET_USER_CONTACT, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        if ((SetUsuallyAddressActivity.this.mDialog != null) && (SetUsuallyAddressActivity.this.mDialog.isShowing()))
          SetUsuallyAddressActivity.this.mDialog.dismiss();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i("Test", "提交用户常用地址数据： " + ((String)paramAnonymousResponse.get()).toString());
        if ((SetUsuallyAddressActivity.this.mDialog != null) && (SetUsuallyAddressActivity.this.mDialog.isShowing()))
          SetUsuallyAddressActivity.this.mDialog.dismiss();
        try
        {
          if (new JSONObject(((String)paramAnonymousResponse.get()).toString()).getInt("status") == ResultCode.RESULT_OK)
          {
            if (SetUsuallyAddressActivity.this.toast == null)
              SetUsuallyAddressActivity.this.toast = new ToastDefine(SetUsuallyAddressActivity.this);
            SetUsuallyAddressActivity.this.toast.setMessage("提交成功");
            paramAnonymousResponse = new UserUsuallyAddress();
            paramAnonymousResponse.setName(str5);
            paramAnonymousResponse.setMobile(str6);
            paramAnonymousResponse.setProvince(str7);
            paramAnonymousResponse.setCity(str8);
            paramAnonymousResponse.setDistrict(str1);
            paramAnonymousResponse.setDetailAddress(str9);
            paramAnonymousResponse.setIsDefault(1);
            paramAnonymousResponse.setTown(str2);
            paramAnonymousResponse.setProvinceId(SetUsuallyAddressActivity.this.mCurrentProviceId);
            paramAnonymousResponse.setCityId(SetUsuallyAddressActivity.this.mCurrentCityId);
            if (!CommonMethod.isEmpty(str1))
              paramAnonymousResponse.setDistrictId(SetUsuallyAddressActivity.this.mCurrentDistrictId);
            if (!CommonMethod.isEmpty(str2))
              paramAnonymousResponse.setTownId(SetUsuallyAddressActivity.this.mCurrentTownId);
            if (SetUsuallyAddressActivity.usuallyAddressList == null)
              SetUsuallyAddressActivity.usuallyAddressList = new ArrayList();
            if (SetUsuallyAddressActivity.usuallyAddressList != null)
            {
              SetUsuallyAddressActivity.usuallyAddressList.clear();
              SetUsuallyAddressActivity.usuallyAddressList.add(paramAnonymousResponse);
            }
            SetUsuallyAddressActivity.this.finish();
          }
          return;
        }
        catch (JSONException paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SetUsuallyAddressActivity
 * JD-Core Version:    0.6.2
 */