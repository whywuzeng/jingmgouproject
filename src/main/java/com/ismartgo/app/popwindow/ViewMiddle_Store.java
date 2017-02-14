package com.ismartgo.app.popwindow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.adapter.ExpandTextAdapter;
import com.ismartgo.app.adapter.ExpandTextAdapter.OnItemClickListener;
import com.ismartgo.app.bean.RetailCache;
import com.ismartgo.app.bean.SalesArea;
import com.ismartgo.app.bean.ScreenStore;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.Utils;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.interfaces.ViewBaseActionInterface;
import com.ismartgo.app.net.SaleRetailsAreaRequest;
import com.ismartgo.app.service.IbeaconService;
import com.ismartgo.app.url.Url;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ViewMiddle_Store extends LinearLayout
  implements ViewBaseActionInterface
{
  private static ListView plateListView;
  private static ListView regionListView;
  private SparseArray<LinkedList<String>> children = new SparseArray();
  private LinkedList<String> childrenItem = new LinkedList();
  private Context context;
  int currentShopTypeId;
  private List<SalesArea> district_list;
  private ExpandTextAdapter earaListViewAdapter;
  private ArrayList<String> groups = new ArrayList();
  private boolean isFirst = true;
  private boolean isRunning;
  int lastedShopTypeId;
  MyDialog mDialog;
  private OnItemStoreClickListener mOnItemClickListener;
  private OnSelectListener mOnSelectListener;
  MiddleStoreReceiver middleStoreReceiver;
  private int nearFlag;
  private ExpandTextAdapter plateListViewAdapter;
  private int retailId = -1;
  private int retailPosition = 0;
  private List<ScreenStore> screenStore_area;
  private int shopTypeId = -1;
  private String shopTypeName = "";
  private int shopTypePosition = 0;
  private String showString = "";
  private int tBlockPosition = 0;
  private int tEaraPosition = 0;

  public ViewMiddle_Store(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init(paramContext);
  }

  public ViewMiddle_Store(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    init(paramContext);
  }

  private void getBusinessArea()
  {
    this.groups.clear();
    this.screenStore_area = IbeaconService.screenStore_area;
    if ((this.screenStore_area == null) || (this.screenStore_area.size() == 0))
      Log.i("Test", "screenStore_area为空");
    int i;
    if (this.screenStore_area.size() > 0)
    {
      setShopTypeName(((ScreenStore)this.screenStore_area.get(0)).getName());
      i = 0;
    }
    while (true)
    {
      if (i >= this.screenStore_area.size())
      {
        setAdapter();
        return;
      }
      this.groups.add(((ScreenStore)this.screenStore_area.get(i)).getName());
      if ((i == 0) && (this.isFirst))
      {
        Log.i("hahaha", "isFirst: true");
        IbeaconService.shopTypeId = ((ScreenStore)this.screenStore_area.get(i)).getShopTypeId();
        Log.i("hahaha", "设置shopTypeId");
      }
      i += 1;
    }
  }

  public static ListView getRetailList()
  {
    return plateListView;
  }

  public static ListView getShopKindList()
  {
    return regionListView;
  }

  private void init(Context paramContext)
  {
    if (this.middleStoreReceiver == null)
      this.middleStoreReceiver = new MiddleStoreReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction(Utils.MIDDLE_STORE_RECEIVER_ACTION);
    paramContext.registerReceiver(this.middleStoreReceiver, localIntentFilter);
    Log.i("Test", "注册了商店广播");
    LayoutInflater.from(paramContext).inflate(2130903186, this, true);
    regionListView = (ListView)findViewById(2131231291);
    plateListView = (ListView)findViewById(2131231292);
    setBackgroundColor(getResources().getColor(17170443));
    getBusinessArea();
    this.mDialog = new MyDialog(paramContext);
  }

  private void setAdapter()
  {
    this.earaListViewAdapter = new ExpandTextAdapter(getContext(), this.groups, 0, 2130837608);
    this.earaListViewAdapter.setTextSize(15.0F);
    this.earaListViewAdapter.setSelectedPositionNoNotify(this.tEaraPosition);
    regionListView.setAdapter(this.earaListViewAdapter);
    this.earaListViewAdapter.setOnItemClickListener(new ExpandTextAdapter.OnItemClickListener()
    {
      public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
      {
        Log.i("hahaha", "shopTypePosition: " + ViewMiddle_Store.this.shopTypePosition + " position: " + paramAnonymousInt);
        ViewMiddle_Store.this.shopTypePosition = paramAnonymousInt;
        int i = ((ScreenStore)ViewMiddle_Store.this.screenStore_area.get(paramAnonymousInt)).getShopTypeId();
        ViewMiddle_Store.this.lastedShopTypeId = i;
        ViewMiddle_Store.this.setShopTypeId(i);
        paramAnonymousView = ((ScreenStore)ViewMiddle_Store.this.screenStore_area.get(paramAnonymousInt)).getName();
        ViewMiddle_Store.this.setShopTypeName(paramAnonymousView);
        com.ismartgo.app.item.Promotion_Select_menu.to_class_shopTypeId = i;
        if (!ViewMiddle_Store.this.isRunning)
        {
          ViewMiddle_Store.this.isRunning = true;
          if (ViewMiddle_Store.this.mDialog == null)
            ViewMiddle_Store.this.mDialog = new MyDialog(ViewMiddle_Store.this.context);
          ViewMiddle_Store.this.mDialog.show();
          ViewMiddle_Store.this.getThirdMenuData2(paramAnonymousInt, i, IbeaconService.districtId, IbeaconService.townId);
          ViewMiddle_Store.this.plateListViewAdapter.setSelectedPosition(0);
          ViewMiddle_Store.plateListView.setSelection(0);
        }
      }
    });
    if (this.tEaraPosition < this.children.size())
      this.childrenItem.addAll((Collection)this.children.get(this.tEaraPosition));
    this.plateListViewAdapter = new ExpandTextAdapter(getContext(), this.childrenItem, 0, 2130837609);
    this.plateListViewAdapter.setTextSize(15.0F);
    this.plateListViewAdapter.setSelectedPositionNoNotify(this.tBlockPosition);
    plateListView.setAdapter(this.plateListViewAdapter);
    this.plateListViewAdapter.setOnItemClickListener(new ExpandTextAdapter.OnItemClickListener()
    {
      public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
      {
        ViewMiddle_Store.this.retailPosition = paramAnonymousInt;
        ViewMiddle_Store.this.showString = ((String)ViewMiddle_Store.this.childrenItem.get(paramAnonymousInt));
        if (ViewMiddle_Store.this.mOnSelectListener != null)
        {
          if (((SalesArea)ViewMiddle_Store.this.district_list.get(paramAnonymousInt)).getRetailId() <= 0)
            break label151;
          ViewMiddle_Store.this.mOnSelectListener.getValue(ViewMiddle_Store.this.showString, ViewMiddle_Store.this.getShopTypeId());
          ViewMiddle_Store.this.mOnItemClickListener.setItemId(ViewMiddle_Store.this.getShopTypeId(), ((SalesArea)ViewMiddle_Store.this.district_list.get(paramAnonymousInt)).getRetailId(), ViewMiddle_Store.this.shopTypePosition, paramAnonymousInt);
        }
        while (ViewMiddle_Store.this.currentShopTypeId == ViewMiddle_Store.this.lastedShopTypeId)
        {
          return;
          label151: ViewMiddle_Store.this.mOnSelectListener.getValue(ViewMiddle_Store.this.getShopTypeName(), ViewMiddle_Store.this.getShopTypeId());
          ViewMiddle_Store.this.mOnItemClickListener.setItemId(ViewMiddle_Store.this.getShopTypeId(), -1, ViewMiddle_Store.this.shopTypePosition, paramAnonymousInt);
        }
        ViewMiddle_Store.this.currentShopTypeId = ViewMiddle_Store.this.getShopTypeId();
        paramAnonymousView = new Intent();
        paramAnonymousView.putExtra("shopTypeId", ViewMiddle_Store.this.getShopTypeId());
        paramAnonymousView.setAction(Utils.MIDDLE_CLASS_RECEIVER_ACTION);
        ViewMiddle_Store.this.context.sendBroadcast(paramAnonymousView);
      }
    });
    if (this.tBlockPosition < this.childrenItem.size())
      this.showString = ((String)this.childrenItem.get(this.tBlockPosition));
    setDefaultSelect();
  }

  public int getRetailId()
  {
    return this.retailId;
  }

  public int getRetailPosition()
  {
    return this.retailPosition;
  }

  public int getShopTypeId()
  {
    return this.shopTypeId;
  }

  public String getShopTypeName()
  {
    return this.shopTypeName;
  }

  public int getShopTypePosition()
  {
    return this.shopTypePosition;
  }

  public String getShowText()
  {
    return this.showString;
  }

  public void getThirdMenuData2(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    Object localObject1 = null;
    Object localObject3 = IbeaconService.retailCachelist.iterator();
    if (!((Iterator)localObject3).hasNext())
    {
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        this.childrenItem.clear();
        localObject2 = new LinkedList();
        paramInt1 = 0;
      }
    }
    else
      while (true)
      {
        if (paramInt1 >= ((List)localObject1).size())
        {
          this.plateListViewAdapter.notifyDataSetChanged();
          if ((this.mDialog != null) && (this.mDialog.isShowing()))
            this.mDialog.dismiss();
          this.isRunning = false;
          return;
          localObject2 = (RetailCache)((Iterator)localObject3).next();
          if ((((RetailCache)localObject2).getDistrictId() != paramInt3) || (((RetailCache)localObject2).getTownId() != paramInt4) || (((RetailCache)localObject2).getShopTypeId() != paramInt2))
            break;
          Log.i("hahaha", "townId: " + ((RetailCache)localObject2).getTownId() + "  shopTypeId: " + ((RetailCache)localObject2).getShopTypeId() + "  districtId: " + ((RetailCache)localObject2).getDistrictId());
          localObject2 = ((RetailCache)localObject2).getSalesAreasList();
          localObject1 = localObject2;
          if (localObject2 == null)
            break;
          this.district_list = ((List)localObject2);
          localObject1 = localObject2;
          break;
        }
        localObject3 = ((SalesArea)((List)localObject1).get(paramInt1)).getName();
        if ((paramInt1 == 0) && (this.isFirst))
        {
          IbeaconService.retailId = ((SalesArea)((List)localObject1).get(paramInt1)).getRetailId();
          this.isFirst = false;
          Log.i("hahaha", "isFirst: false");
        }
        ((LinkedList)localObject2).add(localObject3);
        this.childrenItem.add(localObject3);
        paramInt1 += 1;
      }
    Log.i("hahaha", "请求网络 获取零售商");
    localObject1 = new SaleRetailsAreaRequest(this.context, Url.RETAILERS_URL);
    ((SaleRetailsAreaRequest)localObject1).initParams("cityName", BaseActivity.city);
    ((SaleRetailsAreaRequest)localObject1).initParams("districtId", String.valueOf(paramInt3));
    ((SaleRetailsAreaRequest)localObject1).initParams("townId", String.valueOf(paramInt4));
    ((SaleRetailsAreaRequest)localObject1).initParams("shopTypeId", String.valueOf(paramInt2));
    if (this.nearFlag == 0)
    {
      ((SaleRetailsAreaRequest)localObject1).initParams("distance", String.valueOf(IbeaconService.nearDistance));
      ((SaleRetailsAreaRequest)localObject1).initParams("lon", String.valueOf(BaseActivity.log));
      ((SaleRetailsAreaRequest)localObject1).initParams("lat", String.valueOf(BaseActivity.lat));
    }
    ((SaleRetailsAreaRequest)localObject1).initParams("lon", String.valueOf(BaseActivity.log));
    ((SaleRetailsAreaRequest)localObject1).initParams("lat", String.valueOf(BaseActivity.lat));
    Object localObject2 = new HashMap();
    ((HashMap)localObject2).put("districtId", Integer.valueOf(paramInt3));
    ((HashMap)localObject2).put("townId", Integer.valueOf(paramInt4));
    ((HashMap)localObject2).put("shopTypeId", Integer.valueOf(paramInt2));
    ((SaleRetailsAreaRequest)localObject1).setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        Log.i("hahaha", "onFailure: " + paramAnonymousString);
        if ((ViewMiddle_Store.this.mDialog != null) && (ViewMiddle_Store.this.mDialog.isShowing()))
          ViewMiddle_Store.this.mDialog.dismiss();
        ViewMiddle_Store.this.childrenItem.clear();
        ViewMiddle_Store.this.plateListViewAdapter.notifyDataSetChanged();
        ViewMiddle_Store.this.isRunning = false;
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        ViewMiddle_Store.this.district_list = ((List)paramAnonymousObject);
        Object localObject = new RetailCache();
        ((RetailCache)localObject).setDistrictId(paramInt3);
        ((RetailCache)localObject).setTownId(paramInt4);
        ((RetailCache)localObject).setShopTypeId(paramInt2);
        ((RetailCache)localObject).setSalesAreasList((List)paramAnonymousObject);
        IbeaconService.retailCachelist.add(localObject);
        ((ScreenStore)ViewMiddle_Store.this.screenStore_area.get(paramInt1)).setSalesAreas_list(ViewMiddle_Store.this.district_list);
        ViewMiddle_Store.this.childrenItem.clear();
        paramAnonymousObject = new LinkedList();
        paramAnonymousInt = 0;
        if (paramAnonymousInt >= ViewMiddle_Store.this.district_list.size())
          paramAnonymousInt = 0;
        while (true)
        {
          if (paramAnonymousInt >= ViewMiddle_Store.this.screenStore_area.size())
          {
            ViewMiddle_Store.this.plateListViewAdapter.notifyDataSetChanged();
            if (paramInt1 < ViewMiddle_Store.this.children.size())
            {
              ViewMiddle_Store.this.childrenItem.clear();
              ViewMiddle_Store.this.childrenItem.addAll((Collection)ViewMiddle_Store.this.children.get(paramInt1));
              ViewMiddle_Store.this.plateListViewAdapter.notifyDataSetChanged();
            }
            if ((ViewMiddle_Store.this.mDialog != null) && (ViewMiddle_Store.this.mDialog.isShowing()))
              ViewMiddle_Store.this.mDialog.dismiss();
            ViewMiddle_Store.this.isRunning = false;
            return;
            localObject = ((SalesArea)ViewMiddle_Store.this.district_list.get(paramAnonymousInt)).getName();
            if ((paramAnonymousInt == 0) && (ViewMiddle_Store.this.isFirst))
            {
              IbeaconService.retailId = ((SalesArea)ViewMiddle_Store.this.district_list.get(paramAnonymousInt)).getRetailId();
              ViewMiddle_Store.this.isFirst = false;
              Log.i("hahaha", "isFirst: false");
            }
            paramAnonymousObject.add(localObject);
            ViewMiddle_Store.this.childrenItem.add(localObject);
            paramAnonymousInt += 1;
            break;
          }
          ViewMiddle_Store.this.children.put(paramAnonymousInt, paramAnonymousObject);
          ViewMiddle_Store.this.childrenItem.addAll((Collection)ViewMiddle_Store.this.children.get(paramAnonymousInt));
          paramAnonymousInt += 1;
        }
      }
    });
    ((SaleRetailsAreaRequest)localObject1).startRequest();
  }

  public void getThirdMenuData3(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4, final int paramInt5)
  {
    Object localObject1 = null;
    Object localObject3 = IbeaconService.retailCachelist.iterator();
    if (!((Iterator)localObject3).hasNext())
    {
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        paramInt2 = -1;
        this.childrenItem.clear();
        localObject2 = new LinkedList();
        paramInt1 = 0;
      }
    }
    else
      while (true)
      {
        if (paramInt1 >= ((List)localObject1).size())
        {
          this.plateListViewAdapter.notifyDataSetChanged();
          if ((this.mDialog != null) && (this.mDialog.isShowing()))
            this.mDialog.dismiss();
          Log.i("hahaha", "position: " + paramInt2);
          paramInt1 = paramInt2;
          if (paramInt2 < 0)
            paramInt1 = 0;
          this.plateListViewAdapter.setSelectedPosition(paramInt1);
          plateListView.setSelection(paramInt1);
          return;
          localObject2 = (RetailCache)((Iterator)localObject3).next();
          if ((((RetailCache)localObject2).getDistrictId() != paramInt3) || (((RetailCache)localObject2).getTownId() != paramInt4) || (((RetailCache)localObject2).getShopTypeId() != paramInt2))
            break;
          localObject2 = ((RetailCache)localObject2).getSalesAreasList();
          localObject1 = localObject2;
          if (localObject2 == null)
            break;
          this.district_list = ((List)localObject2);
          localObject1 = localObject2;
          break;
        }
        localObject3 = ((SalesArea)((List)localObject1).get(paramInt1)).getName();
        ((LinkedList)localObject2).add(localObject3);
        this.childrenItem.add(localObject3);
        if (((SalesArea)this.district_list.get(paramInt1)).getRetailId() == paramInt5)
          paramInt2 = paramInt1;
        paramInt1 += 1;
      }
    localObject1 = new SaleRetailsAreaRequest(this.context, Url.RETAILERS_URL);
    ((SaleRetailsAreaRequest)localObject1).initParams("cityName", BaseActivity.city);
    ((SaleRetailsAreaRequest)localObject1).initParams("districtId", String.valueOf(paramInt3));
    ((SaleRetailsAreaRequest)localObject1).initParams("townId", String.valueOf(paramInt4));
    ((SaleRetailsAreaRequest)localObject1).initParams("shopTypeId", String.valueOf(paramInt2));
    if (this.nearFlag == 0)
    {
      ((SaleRetailsAreaRequest)localObject1).initParams("distance", String.valueOf(IbeaconService.nearDistance));
      ((SaleRetailsAreaRequest)localObject1).initParams("lon", String.valueOf(BaseActivity.log));
      ((SaleRetailsAreaRequest)localObject1).initParams("lat", String.valueOf(BaseActivity.lat));
    }
    ((SaleRetailsAreaRequest)localObject1).initParams("lon", String.valueOf(BaseActivity.log));
    ((SaleRetailsAreaRequest)localObject1).initParams("lat", String.valueOf(BaseActivity.lat));
    Object localObject2 = new HashMap();
    ((HashMap)localObject2).put("districtId", Integer.valueOf(paramInt3));
    ((HashMap)localObject2).put("townId", Integer.valueOf(paramInt4));
    ((HashMap)localObject2).put("shopTypeId", Integer.valueOf(paramInt2));
    ((SaleRetailsAreaRequest)localObject1).setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        Log.i("hahaha", "onFailure: " + paramAnonymousString);
        if ((ViewMiddle_Store.this.mDialog != null) && (ViewMiddle_Store.this.mDialog.isShowing()))
          ViewMiddle_Store.this.mDialog.dismiss();
        ViewMiddle_Store.this.childrenItem.clear();
        ViewMiddle_Store.this.plateListViewAdapter.notifyDataSetChanged();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        ViewMiddle_Store.this.district_list = ((List)paramAnonymousObject);
        Object localObject = new RetailCache();
        ((RetailCache)localObject).setDistrictId(paramInt3);
        ((RetailCache)localObject).setTownId(paramInt4);
        ((RetailCache)localObject).setShopTypeId(paramInt2);
        ((RetailCache)localObject).setSalesAreasList((List)paramAnonymousObject);
        IbeaconService.retailCachelist.add(localObject);
        ((ScreenStore)ViewMiddle_Store.this.screenStore_area.get(paramInt1)).setSalesAreas_list(ViewMiddle_Store.this.district_list);
        ViewMiddle_Store.this.childrenItem.clear();
        paramAnonymousObject = new LinkedList();
        paramAnonymousInt = -1;
        int i = 0;
        if (i >= ViewMiddle_Store.this.district_list.size())
          i = 0;
        while (true)
        {
          if (i >= ViewMiddle_Store.this.screenStore_area.size())
          {
            ViewMiddle_Store.this.plateListViewAdapter.notifyDataSetChanged();
            if (paramInt1 < ViewMiddle_Store.this.children.size())
            {
              ViewMiddle_Store.this.childrenItem.clear();
              ViewMiddle_Store.this.childrenItem.addAll((Collection)ViewMiddle_Store.this.children.get(paramInt1));
              ViewMiddle_Store.this.plateListViewAdapter.notifyDataSetChanged();
            }
            if ((ViewMiddle_Store.this.mDialog != null) && (ViewMiddle_Store.this.mDialog.isShowing()))
              ViewMiddle_Store.this.mDialog.dismiss();
            Log.i("hahaha", "position: " + paramAnonymousInt);
            i = paramAnonymousInt;
            if (paramAnonymousInt < 0)
              i = 0;
            ViewMiddle_Store.this.plateListViewAdapter.setSelectedPosition(i);
            ViewMiddle_Store.plateListView.setSelection(i);
            return;
            localObject = ((SalesArea)ViewMiddle_Store.this.district_list.get(i)).getName();
            paramAnonymousObject.add(localObject);
            ViewMiddle_Store.this.childrenItem.add(localObject);
            if (((SalesArea)ViewMiddle_Store.this.district_list.get(i)).getRetailId() == paramInt5)
              paramAnonymousInt = i;
            i += 1;
            break;
          }
          ViewMiddle_Store.this.children.put(i, paramAnonymousObject);
          ViewMiddle_Store.this.childrenItem.addAll((Collection)ViewMiddle_Store.this.children.get(i));
          i += 1;
        }
      }
    });
    ((SaleRetailsAreaRequest)localObject1).startRequest();
  }

  public void hide()
  {
  }

  public void setCheckMoreBackSelect(int paramInt1, int paramInt2)
  {
    setShopTypeName(((ScreenStore)IbeaconService.screenStore_area.get(paramInt1)).getName());
    Log.i("Test", "setShopTypeName: " + ((ScreenStore)IbeaconService.screenStore_area.get(paramInt1)).getName());
    this.earaListViewAdapter.setSelectedPosition(paramInt1);
    regionListView.setSelection(paramInt1);
    getThirdMenuData2(paramInt2, IbeaconService.shopTypeId, IbeaconService.districtId, IbeaconService.townId);
    this.plateListViewAdapter.setSelectedPosition(paramInt2);
    plateListView.setSelection(paramInt2);
  }

  public void setCheckMoreSelect(int paramInt1, int paramInt2, int paramInt3)
  {
    this.shopTypePosition = paramInt1;
    setShopTypeId(paramInt2);
    this.lastedShopTypeId = paramInt2;
    this.currentShopTypeId = paramInt2;
    com.ismartgo.app.item.Promotion_Select_menu.lastShopTypeId = paramInt2;
    Log.i("hahaha", "设置position: " + this.shopTypePosition);
    IbeaconService.shopTypeId = paramInt2;
    IbeaconService.retailId = paramInt3;
    this.earaListViewAdapter.setSelectedPosition(paramInt1);
    setShopTypeName(((ScreenStore)IbeaconService.screenStore_area.get(paramInt1)).getName());
    Intent localIntent = new Intent();
    localIntent.putExtra("shopTypeId", paramInt2);
    localIntent.setAction(Utils.MIDDLE_CLASS_RECEIVER_ACTION);
    this.context.sendBroadcast(localIntent);
    getThirdMenuData3(paramInt1, paramInt2, IbeaconService.districtId, IbeaconService.townId, paramInt3);
  }

  public void setDefaultSelect()
  {
    regionListView.setSelection(this.tEaraPosition);
    plateListView.setSelection(this.tBlockPosition);
    getThirdMenuData2(this.tBlockPosition, -1, IbeaconService.districtId, IbeaconService.townId);
  }

  public void setDefaultSelect_2()
  {
    removeAllViews();
    init(getContext());
    this.shopTypePosition = 0;
    this.retailPosition = 0;
  }

  public void setOnItemStoreClickListener(OnItemStoreClickListener paramOnItemStoreClickListener)
  {
    this.mOnItemClickListener = paramOnItemStoreClickListener;
  }

  public void setOnSelectListener(OnSelectListener paramOnSelectListener)
  {
    this.mOnSelectListener = paramOnSelectListener;
  }

  public void setRetailId(int paramInt)
  {
    this.retailId = paramInt;
  }

  public void setRetailPosition(int paramInt)
  {
    this.retailPosition = paramInt;
  }

  public void setShopTypeId(int paramInt)
  {
    this.shopTypeId = paramInt;
  }

  public void setShopTypeName(String paramString)
  {
    this.shopTypeName = paramString;
  }

  public void setShopTypePosition(int paramInt)
  {
    this.shopTypePosition = paramInt;
  }

  public void show()
  {
  }

  public void unRegisterReceiver(Context paramContext)
  {
    if (this.middleStoreReceiver != null)
      paramContext.unregisterReceiver(this.middleStoreReceiver);
  }

  public void updateShowText(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null));
    label22: label153: 
    while (true)
    {
      return;
      int i = 0;
      if (i >= this.groups.size())
        i = 0;
      while (true)
      {
        if (i >= this.childrenItem.size())
          break label153;
        if (((String)this.childrenItem.get(i)).equals(paramString2.trim()))
        {
          this.plateListViewAdapter.setSelectedPosition(i);
          this.tBlockPosition = i;
          return;
          if (((String)this.groups.get(i)).equals(paramString1))
          {
            this.earaListViewAdapter.setSelectedPosition(i);
            this.childrenItem.clear();
            if (i < this.children.size())
              this.childrenItem.addAll((Collection)this.children.get(i));
            this.tEaraPosition = i;
            break label22;
          }
          i += 1;
          break;
        }
        i += 1;
      }
    }
  }

  public class MiddleStoreReceiver extends BroadcastReceiver
  {
    public MiddleStoreReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals(Utils.MIDDLE_STORE_RECEIVER_ACTION))
      {
        Log.i("Test", "零售商接收到广播");
        ViewMiddle_Store.this.getThirdMenuData3(ViewMiddle_Store.this.shopTypePosition, IbeaconService.shopTypeId, IbeaconService.districtId, IbeaconService.townId, IbeaconService.retailId);
      }
    }
  }

  public static abstract interface OnItemStoreClickListener
  {
    public abstract void setItemId(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }

  public static abstract interface OnSelectListener
  {
    public abstract void getValue(String paramString, int paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.popwindow.ViewMiddle_Store
 * JD-Core Version:    0.6.2
 */