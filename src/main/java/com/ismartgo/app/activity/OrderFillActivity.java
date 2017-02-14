package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.address.ProvinceActivity;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.bean.UserUsuallyAddress;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.impl.SelectViewCallback;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.RequestJSONUtils;
import com.ismartgo.app.grid.view.SelectView;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.CityDBManager;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;

@SuppressLint({"InlinedApi", "NewApi", "InflateParams"})
public class OrderFillActivity extends BaseActivity
  implements View.OnClickListener, OnWheelChangedListener
{
  public static String TAG = "OrderFillActivity";
  private Button btnCancel;
  private Button btnOk;

  @AbIocView(click="onClick", id=2131230891)
  private Button btn_confirm_order;
  private EditText etAddressee;

  @AbIocView(id=2131230886)
  private TextView et_address;

  @AbIocView(id=2131230888)
  private EditText et_detail_address;

  @AbIocView(id=2131230880)
  private EditText et_num;

  @AbIocView(id=2131230814)
  private EditText et_phone;
  private Gift gift;

  @AbIocView(click="onClick", id=2131230879)
  private ImageButton ib_left;

  @AbIocView(click="onClick", id=2131230881)
  private ImageButton ib_right;

  @AbIocView(id=2131230877)
  private ImageView iv_pic;
  private LinearLayout layoutAddressSelect;

  @AbIocView(id=2131230883)
  private View layout_addressee;

  @AbIocView(id=2131230887)
  private View layout_detail_address;

  @AbIocView(id=2131230889)
  private View layout_exchange_detail;

  @AbIocView(click="onClick", id=2131230885)
  private View layout_select_city;
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
  private int num = 1;
  private View parentView;
  private int resultCode = 10002;
  private List<HashMap<String, Object>> selectViewData;

  @AbIocView(id=2131230783)
  private TextView tv_beans;

  @AbIocView(id=2131230878)
  private TextView tv_name;

  @AbIocView(id=2131230882)
  private TextView tv_remainBeans;

  @AbIocView(id=2131230890)
  private TextView tv_totalBeans;

  private void changeNum(int paramInt)
  {
    if (TextUtils.isEmpty(this.et_num.getText().toString()))
      this.et_num.setText("1");
    int j;
    do
    {
      return;
      j = Integer.parseInt(this.et_num.getText().toString());
    }
    while ((j == 1) && (paramInt == -1));
    int i;
    if (paramInt == -1)
      i = j - 1;
    while (true)
    {
      this.et_num.setText(i);
      return;
      i = j;
      if (paramInt == 1)
        i = j + 1;
    }
  }

  private void confirmOrder()
  {
    if (TextUtils.isEmpty(this.et_num.getText().toString()))
    {
      this.toast.setMessage("您还未选择数量哦...");
      this.toast.show();
      return;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("您确定立即兑换吗？").setNegativeButton("取消", null).setPositiveButton("兑换", new DialogInterface.OnClickListener()
    {
      public void onClick(final DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        final View localView = LayoutInflater.from(OrderFillActivity.this).inflate(2130903170, null);
        final ImageView localImageView = (ImageView)localView.findViewById(2131230877);
        final TextView localTextView1 = (TextView)localView.findViewById(2131231263);
        final TextView localTextView2 = (TextView)localView.findViewById(2131231264);
        final AlertDialog localAlertDialog = new AlertDialog.Builder(OrderFillActivity.this).create();
        localAlertDialog.setCancelable(false);
        localView.findViewById(2131230782).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localAlertDialog.dismiss();
          }
        });
        if (OrderFillActivity.this.num * OrderFillActivity.this.gift.getRequiredBean() > OrderFillActivity.loginUser.getBeanNumber())
        {
          OrderFillActivity.this.toast.setMessage("亲，您的精明豆不够哦~");
          OrderFillActivity.this.toast.show();
        }
        final String str4;
        final String str5;
        final String str6;
        do
        {
          do
          {
            return;
            str4 = OrderFillActivity.this.et_phone.getText().toString().trim();
            paramAnonymousDialogInterface = OrderFillActivity.this.et_address.getText().toString().trim();
            str5 = OrderFillActivity.this.et_detail_address.getText().toString().trim().replace("\n", "");
            str6 = OrderFillActivity.this.etAddressee.getText().toString().trim();
            if (!OrderFillActivity.this.gift.getGiftType().equals("1"))
              break;
          }
          while (!OrderFillActivity.this.isMobileNo(str4));
          if (TextUtils.isEmpty(str6))
          {
            OrderFillActivity.this.toast.setMessage("请填写收件人姓名");
            OrderFillActivity.this.toast.show();
            return;
          }
          if (TextUtils.isEmpty(paramAnonymousDialogInterface))
          {
            OrderFillActivity.this.toast.setMessage("请选择省市区");
            OrderFillActivity.this.toast.show();
            return;
          }
          if (!TextUtils.isEmpty(str5))
            break;
          OrderFillActivity.this.toast.setMessage("请填写详细地址");
          OrderFillActivity.this.toast.show();
          return;
        }
        while ((OrderFillActivity.this.gift.getGiftType().equals("2")) && (!OrderFillActivity.this.isMobileNo(str4)));
        if (OrderFillActivity.this.num < 1)
        {
          OrderFillActivity.this.toast.setMessage("所选数量不能小于1哦...");
          OrderFillActivity.this.toast.show();
          return;
        }
        if (!CommonMethod.patternAddress(str5))
        {
          OrderFillActivity.this.toast.setMessage("地址不能包含特殊符号哦，改一改 ");
          OrderFillActivity.this.toast.show();
          return;
        }
        OrderFillActivity.this.mDialog.show();
        OrderFillActivity.this.btn_confirm_order.setEnabled(false);
        HashMap localHashMap = new HashMap();
        localHashMap.put("giftId", String.valueOf(OrderFillActivity.this.gift.getGiftId()));
        localHashMap.put("uid", OrderFillActivity.loginUser.getId());
        localHashMap.put("buyNumber", String.valueOf(OrderFillActivity.this.num));
        localHashMap.put("giftType", OrderFillActivity.this.gift.getGiftType());
        localHashMap.put("contactNumber", str4);
        localHashMap.put("deliveryAddress", paramAnonymousDialogInterface);
        localHashMap.put("address", str5);
        localHashMap.put("contactPerson", str6);
        localHashMap.put("key", Helper.MD5Params(new String[] { String.valueOf(OrderFillActivity.this.gift.getGiftId()), OrderFillActivity.this.gift.getGiftType(), str4, OrderFillActivity.loginUser.getId(), String.valueOf(OrderFillActivity.this.num) }));
        final String str7 = OrderFillActivity.this.mCurrentProviceName;
        final String str8 = OrderFillActivity.this.mCurrentCityName;
        if (OrderFillActivity.this.mCurrentDistrictName == null)
        {
          paramAnonymousDialogInterface = "";
          if (OrderFillActivity.this.mCurrentTownName != null)
            break label950;
        }
        label950: for (String str1 = ""; ; str1 = OrderFillActivity.this.mCurrentTownName)
        {
          String str2 = "";
          if (!CommonMethod.isEmpty(paramAnonymousDialogInterface))
            str2 = String.valueOf(OrderFillActivity.this.mCurrentDistrictId);
          String str3 = "";
          if (!CommonMethod.isEmpty(str1))
            str3 = String.valueOf(OrderFillActivity.this.mCurrentTownId);
          localHashMap.put("province", str7);
          localHashMap.put("city", str8);
          localHashMap.put("district", paramAnonymousDialogInterface);
          localHashMap.put("town", str1);
          localHashMap.put("province_id", String.valueOf(OrderFillActivity.this.mCurrentProviceId));
          localHashMap.put("city_id", String.valueOf(OrderFillActivity.this.mCurrentCityId));
          localHashMap.put("district_id", str2);
          localHashMap.put("town_id", str3);
          HttpRequest.getInstance().executePostStringRequest(OrderFillActivity.this, Url.EXCHANGE_GIFT_URL, HttpWhat.EXCHANGE_GIFT, localHashMap, new HttpCallback()
          {
            public void onFailed(int paramAnonymous2Int, String paramAnonymous2String, Object paramAnonymous2Object, CharSequence paramAnonymous2CharSequence)
            {
              OrderFillActivity.this.mDialog.dismiss();
              OrderFillActivity.this.toast.setMessage("亲，网络好像出问题了~");
              OrderFillActivity.this.toast.show();
              OrderFillActivity.this.btn_confirm_order.setEnabled(true);
            }

            public void onSucceed(int paramAnonymous2Int, Response<String> paramAnonymous2Response)
            {
              Log.i(OrderFillActivity.TAG, "兑换礼品结果: " + ((String)paramAnonymous2Response.get()).toString());
              OrderFillActivity.this.mDialog.dismiss();
              try
              {
                paramAnonymous2Response = new JSONObject(((String)paramAnonymous2Response.get()).toString());
                if (Integer.valueOf(paramAnonymous2Response.getString("status")).intValue() == ResultCode.RESULT_OK)
                {
                  paramAnonymous2Int = paramAnonymous2Response.getJSONObject("data").getInt("userBeanNum");
                  OrderFillActivity.loginUser.setBeanNumber(paramAnonymous2Int);
                  OrderFillActivity.this.setBeans();
                  localView.findViewById(2131230782).setOnClickListener(new View.OnClickListener()
                  {
                    public void onClick(View paramAnonymous3View)
                    {
                      this.val$aDialog.dismiss();
                      OrderFillActivity.this.setResult(OrderFillActivity.this.resultCode);
                      OrderFillActivity.this.finish();
                    }
                  });
                  localImageView.setImageResource(2130837809);
                  localTextView1.setText("礼品兑换成功");
                  localTextView2.setText("您还有" + OrderFillActivity.loginUser.getBeanNumber() + "个精明豆");
                  localTextView2.setTextColor(-7829368);
                  localTextView2.setTextSize(15.0F);
                  localAlertDialog.show();
                  localAlertDialog.setContentView(localView);
                  paramAnonymous2Response = new UserUsuallyAddress();
                  paramAnonymous2Response.setName(str6);
                  paramAnonymous2Response.setMobile(str4);
                  paramAnonymous2Response.setProvince(str7);
                  paramAnonymous2Response.setCity(str8);
                  paramAnonymous2Response.setDistrict(paramAnonymousDialogInterface);
                  paramAnonymous2Response.setDetailAddress(str5);
                  paramAnonymous2Response.setIsDefault(1);
                  if (OrderFillActivity.usuallyAddressList == null)
                    OrderFillActivity.usuallyAddressList = new ArrayList();
                  if ((OrderFillActivity.usuallyAddressList != null) && (!OrderFillActivity.usuallyAddressList.contains(paramAnonymous2Response)))
                  {
                    OrderFillActivity.usuallyAddressList.clear();
                    OrderFillActivity.usuallyAddressList.add(paramAnonymous2Response);
                    OrderFillActivity.this.setUsuallyAddress(OrderFillActivity.usuallyAddressList);
                  }
                  try
                  {
                    paramAnonymous2Response = new HashMap();
                    paramAnonymous2Response.put("giftid", String.valueOf(OrderFillActivity.this.gift.getGiftId()));
                    MobclickAgent.onEventValue(OrderFillActivity.this.getApplicationContext(), "giftorder", paramAnonymous2Response, OrderFillActivity.this.num);
                    OrderFillActivity.this.btn_confirm_order.setEnabled(true);
                    return;
                  }
                  catch (Exception paramAnonymous2Response)
                  {
                    while (true)
                      paramAnonymous2Response.printStackTrace();
                  }
                }
              }
              catch (Exception paramAnonymous2Response)
              {
                while (true)
                {
                  paramAnonymous2Response.printStackTrace();
                  continue;
                  OrderFillActivity.this.toast.setMessage(paramAnonymous2Response.getString("msg"));
                  OrderFillActivity.this.toast.show();
                }
              }
            }
          });
          return;
          paramAnonymousDialogInterface = OrderFillActivity.this.mCurrentDistrictName;
          break;
        }
      }
    });
    localBuilder.show();
  }

  private void getUserContact()
  {
    if (loginUser == null)
      return;
    RequestJSONUtils.getUserUsuallyAddress(this, loginUser.getId(), new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
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
              break label328;
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data");
            if (paramAnonymousResponse.isNull("userContact"))
              return;
            paramAnonymousResponse = paramAnonymousResponse.getJSONArray("userContact");
            if ((paramAnonymousResponse == null) || (paramAnonymousResponse.length() <= 0) || (OrderFillActivity.usuallyAddressList != null))
              break label329;
            OrderFillActivity.usuallyAddressList = new ArrayList();
            break label329;
            if (paramAnonymousInt >= paramAnonymousResponse.length())
            {
              if ((OrderFillActivity.usuallyAddressList == null) || (OrderFillActivity.usuallyAddressList.size() <= 0))
                break label328;
              OrderFillActivity.this.setUsuallyAddress(OrderFillActivity.usuallyAddressList);
              OrderFillActivity.this.initData();
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
          OrderFillActivity.usuallyAddressList.add(localUserUsuallyAddress);
          paramAnonymousInt += 1;
          continue;
          label328: return;
          label329: paramAnonymousInt = 0;
        }
      }
    });
  }

  private void initData()
  {
    if ((getUserUsuallyAddress() != null) && (getUserUsuallyAddress().size() > 0))
    {
      Object localObject = getUserUsuallyAddress().iterator();
      if (!((Iterator)localObject).hasNext());
      do
      {
        return;
        UserUsuallyAddress localUserUsuallyAddress = (UserUsuallyAddress)((Iterator)localObject).next();
        if (localUserUsuallyAddress.getIsDefault() != 1)
          break;
        this.etAddressee.setText(localUserUsuallyAddress.getName());
        this.et_phone.setText(localUserUsuallyAddress.getMobile());
        localObject = "";
        if (!CommonMethod.isEmpty(localUserUsuallyAddress.getDistrict()))
          localObject = localUserUsuallyAddress.getDistrict();
        String str = "";
        if (!CommonMethod.isEmpty(localUserUsuallyAddress.getTown()))
          str = localUserUsuallyAddress.getTown();
        this.et_address.setText(localUserUsuallyAddress.getProvince() + localUserUsuallyAddress.getCity() + (String)localObject + str);
        this.et_detail_address.setText(localUserUsuallyAddress.getDetailAddress());
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
      localObject = new HashMap();
      ((HashMap)localObject).put("name", this.mCurrentTownName);
      ((HashMap)localObject).put("id", Integer.valueOf(this.mCurrentTownId));
      this.selectViewData.add(localObject);
      return;
    }
    getUserContact();
  }

  private void initListener()
  {
    this.et_num.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        if (TextUtils.isEmpty(OrderFillActivity.this.et_num.getText().toString()))
        {
          OrderFillActivity.this.tv_totalBeans.setText("0豆");
          OrderFillActivity.this.tv_remainBeans.setText(OrderFillActivity.loginUser.getBeanNumber());
          return;
        }
        OrderFillActivity.this.num = Integer.parseInt(OrderFillActivity.this.et_num.getText().toString());
        if (OrderFillActivity.this.num <= 0)
        {
          OrderFillActivity.this.et_num.setText("1");
          OrderFillActivity.this.ib_left.setImageResource(2130837760);
        }
        while (true)
        {
          OrderFillActivity.this.et_num.setSelection(OrderFillActivity.this.et_num.getText().toString().length());
          OrderFillActivity.this.setBeans();
          return;
          OrderFillActivity.this.ib_left.setImageResource(2130837761);
        }
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("订单填写");
    this.etAddressee = ((EditText)findViewById(2131230884));
    if (this.gift.getGiftType().equals("1"))
    {
      this.layout_addressee.setVisibility(0);
      this.layout_select_city.setVisibility(0);
      this.layout_detail_address.setVisibility(0);
      this.layout_exchange_detail.setVisibility(8);
    }
    while (true)
    {
      setBeans();
      this.tv_name.setText(this.gift.getGiftName());
      this.tv_beans.setText(this.gift.getRequiredBean() + "豆");
      ImgLoader.getInstance(this).showPic(StringUtils.getImgUrl(this.gift.getGiftLogo()), this.iv_pic, false);
      if (loginUser.getBeanNumber() >= this.gift.getRequiredBean())
        break;
      this.btn_confirm_order.setEnabled(false);
      this.btn_confirm_order.setBackgroundResource(2130837527);
      return;
      if (this.gift.getGiftType().equals("2"))
      {
        this.layout_addressee.setVisibility(8);
        this.layout_select_city.setVisibility(8);
        this.layout_detail_address.setVisibility(8);
        this.layout_exchange_detail.setVisibility(0);
      }
    }
    this.btn_confirm_order.setEnabled(true);
    this.btn_confirm_order.setBackgroundResource(2130837524);
  }

  private void setBeans()
  {
    int i = this.num * this.gift.getRequiredBean();
    this.tv_totalBeans.setText(i + "豆");
    if (loginUser.getBeanNumber() < i)
    {
      this.tv_remainBeans.setText("0豆");
      this.ib_right.setImageResource(2130837836);
      this.ib_right.setEnabled(false);
      return;
    }
    i = loginUser.getBeanNumber() - i;
    if ((i <= 0) || (i < this.gift.getRequiredBean()))
    {
      this.ib_right.setImageResource(2130837836);
      this.ib_right.setEnabled(false);
    }
    while (true)
    {
      this.tv_remainBeans.setText(i + "豆");
      return;
      this.ib_right.setImageResource(2130837835);
      this.ib_right.setEnabled(true);
    }
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

  private void setUpViews()
  {
    this.mViewProvince = ((WheelView)findViewById(2131230893));
    this.mViewCity = ((WheelView)findViewById(2131230894));
    this.mViewDistrict = ((WheelView)findViewById(2131230895));
    this.layoutAddressSelect = ((LinearLayout)findViewById(2131230892));
    this.btnOk = ((Button)findViewById(2131230874));
    this.btnCancel = ((Button)findViewById(2131230873));
    this.btnOk.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        OrderFillActivity.this.et_address.setText(OrderFillActivity.this.mCurrentProviceName + OrderFillActivity.this.mCurrentCityName + OrderFillActivity.this.mCurrentDistrictName);
        OrderFillActivity.this.layoutAddressSelect.setVisibility(8);
      }
    });
    this.btnCancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (OrderFillActivity.this.layoutAddressSelect.getVisibility() == 0)
          OrderFillActivity.this.layoutAddressSelect.setVisibility(8);
      }
    });
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

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == ProvinceActivity.regionRequestCode) && (paramInt2 == ProvinceActivity.regionResultCode))
    {
      String str = new CityDBManager(this).getAddressByAreaId(paramIntent.getStringExtra("id").split(",")[2]);
      paramIntent = str;
      if (str.startsWith("北京省"))
        paramIntent = str.replace("北京省", "");
      this.et_address.setText(paramIntent);
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
    case 2131230879:
    case 2131230881:
      do
      {
        do
        {
          return;
          changeNum(-1);
        }
        while (Integer.parseInt(this.et_num.getText().toString()) > 1);
        this.ib_left.setImageResource(2130837760);
        return;
        changeNum(1);
      }
      while (Integer.parseInt(this.et_num.getText().toString()) <= 1);
      this.ib_left.setImageResource(2130837761);
      return;
    case 2131230886:
      paramView = new Intent(this, ProvinceActivity.class);
      paramView.putExtra("title", "select_province");
      startActivityForResult(paramView, ProvinceActivity.regionRequestCode);
      return;
    case 2131230891:
      if (loginUser.getLoginType() == 6)
      {
        this.toast.setMessage("您还未登录哦...");
        this.toast.show();
        return;
      }
      confirmOrder();
      return;
    case 2131230885:
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
          OrderFillActivity.this.selectViewData = paramAnonymousList;
          OrderFillActivity.this.mCurrentProviceName = ((String)((HashMap)paramAnonymousList.get(0)).get("name"));
          OrderFillActivity.this.mCurrentCityName = ((String)((HashMap)paramAnonymousList.get(1)).get("name"));
          OrderFillActivity.this.mCurrentProviceId = ((Integer)((HashMap)paramAnonymousList.get(0)).get("id")).intValue();
          OrderFillActivity.this.mCurrentCityId = ((Integer)((HashMap)paramAnonymousList.get(1)).get("id")).intValue();
          if (paramAnonymousList.size() <= 2)
            break label305;
          OrderFillActivity.this.mCurrentDistrictName = ((String)((HashMap)paramAnonymousList.get(2)).get("name"));
          OrderFillActivity.this.mCurrentDistrictId = ((Integer)((HashMap)paramAnonymousList.get(2)).get("id")).intValue();
          if (paramAnonymousList.size() <= 3)
            break label325;
          OrderFillActivity.this.mCurrentTownName = ((String)((HashMap)paramAnonymousList.get(3)).get("name"));
          OrderFillActivity.this.mCurrentTownId = ((Integer)((HashMap)paramAnonymousList.get(3)).get("id")).intValue();
          localStringBuffer = new StringBuffer();
          paramAnonymousList = paramAnonymousList.iterator();
        }
        while (true)
        {
          if (!paramAnonymousList.hasNext())
          {
            Logger.i("选择数据： " + localStringBuffer.toString());
            OrderFillActivity.this.et_address.setText(localStringBuffer.toString());
            return;
            label305: OrderFillActivity.this.mCurrentDistrictName = "";
            OrderFillActivity.this.mCurrentDistrictId = 0;
            break;
            label325: OrderFillActivity.this.mCurrentTownName = "";
            OrderFillActivity.this.mCurrentTownId = 0;
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
    this.parentView = LayoutInflater.from(this).inflate(2130903055, null);
    setContentView(this.parentView);
    this.mDialog = new MyDialog(this);
    this.gift = ((Gift)getIntent().getSerializableExtra("gift"));
    initView();
    initListener();
    setUpViews();
    setUpListener();
    setUpData();
    initData();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.layoutAddressSelect.getVisibility() == 0))
    {
      this.layoutAddressSelect.setVisibility(8);
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.OrderFillActivity
 * JD-Core Version:    0.6.2
 */