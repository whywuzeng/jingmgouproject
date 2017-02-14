package com.ismartgo.app.item;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.ismartgo.app.activity.PromotionActivity;
import com.ismartgo.app.activity.ScanStoreGoodsActivity;
import com.ismartgo.app.activity.SearchForPromotionActivity;
import com.ismartgo.app.activity.SearchStorePromotionActivity;
import com.ismartgo.app.bean.Area;
import com.ismartgo.app.bean.GoodsClazz;
import com.ismartgo.app.bean.ScreenStore;
import com.ismartgo.app.grid.utils.Utils;
import com.ismartgo.app.ownself.view.ExpandTabView;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.popwindow.ViewMiddle_Class;
import com.ismartgo.app.popwindow.ViewMiddle_Class.OnItemClazzClickListener;
import com.ismartgo.app.popwindow.ViewMiddle_Class.OnSelectListener;
import com.ismartgo.app.popwindow.ViewMiddle_Neighbor;
import com.ismartgo.app.popwindow.ViewMiddle_Neighbor.OnItemClickListener;
import com.ismartgo.app.popwindow.ViewMiddle_Neighbor.OnSelectListener;
import com.ismartgo.app.popwindow.ViewMiddle_Store;
import com.ismartgo.app.popwindow.ViewMiddle_Store.OnItemStoreClickListener;
import com.ismartgo.app.popwindow.ViewMiddle_Store.OnSelectListener;
import com.ismartgo.app.service.IbeaconService;
import com.ismartgo.app.utils.UMengStatisticsUtils;
import java.util.ArrayList;
import java.util.List;

public class Promotion_Select_menu
{
  public static int lastShopTypeId;
  public static int to_class_shopTypeId = -1;
  private int activityFlag;
  Context context;
  private int districtPositionBeforeMore = 0;
  ExpandTabView expandTabView;
  int flag;
  public Dialog mDialog;
  ArrayList<String> mTextArray = new ArrayList();
  private ArrayList<View> mViewArray = new ArrayList();
  private int retailPositionBeforeMore = 0;
  private int shopTypePositionBeforeMore = 0;
  private ToastDefine toast;
  private int townPositionBeforeMore;
  public ViewMiddle_Class viewMiddle_class;
  public ViewMiddle_Neighbor viewMiddle_neighbor;
  public ViewMiddle_Store viewMiddle_store;

  public Promotion_Select_menu()
  {
  }

  public Promotion_Select_menu(Context paramContext, ExpandTabView paramExpandTabView, int paramInt1, int paramInt2)
  {
    this.context = paramContext;
    this.expandTabView = paramExpandTabView;
    this.flag = paramInt1;
    this.activityFlag = paramInt2;
    this.toast = new ToastDefine(paramContext);
    this.viewMiddle_neighbor = new ViewMiddle_Neighbor(paramContext);
    this.viewMiddle_store = new ViewMiddle_Store(paramContext);
    this.viewMiddle_class = new ViewMiddle_Class(paramContext);
  }

  private int getPositon(View paramView)
  {
    int i = 0;
    while (true)
    {
      int j;
      if (i >= this.mViewArray.size())
        j = -1;
      do
      {
        return j;
        j = i;
      }
      while (this.mViewArray.get(i) == paramView);
      i += 1;
    }
  }

  private void initVaule()
  {
    if (this.flag != 2)
    {
      this.mViewArray.add(this.viewMiddle_neighbor);
      if ((IbeaconService.area != null) && (IbeaconService.area.size() > 0))
        this.mTextArray.add(((Area)IbeaconService.area.get(0)).getAreaName());
    }
    else
    {
      this.mViewArray.add(this.viewMiddle_store);
      if ((IbeaconService.screenStore_area == null) || (IbeaconService.screenStore_area.size() <= 0))
        break label241;
      this.mTextArray.add(((ScreenStore)IbeaconService.screenStore_area.get(0)).getName());
      label112: this.mViewArray.add(this.viewMiddle_class);
      if ((IbeaconService.goodsClazzLists == null) || (IbeaconService.goodsClazzLists.size() <= 0))
        break label254;
      Log.i("Test", "设置品类title: " + ((ScreenStore)IbeaconService.screenStore_area.get(0)).getName());
      this.mTextArray.add(((GoodsClazz)((GoodsClazz)IbeaconService.goodsClazzLists.get(0)).getGoodsClazzList().get(0)).getName());
    }
    while (true)
    {
      this.expandTabView.setValue(this.mTextArray, this.mViewArray);
      return;
      this.mTextArray.add("附近");
      break;
      label241: this.mTextArray.add("全部商店");
      break label112;
      label254: this.mTextArray.add("全部品类");
    }
  }

  private void sendMiddleStoreUpdateBroadcastReceiver()
  {
    Intent localIntent = new Intent();
    localIntent.setAction(Utils.MIDDLE_STORE_RECEIVER_ACTION);
    this.context.sendBroadcast(localIntent);
  }

  public void clear()
  {
    this.mViewArray.clear();
    this.mTextArray.clear();
    if (this.expandTabView != null)
      this.expandTabView.removeAllViews();
  }

  public int getDistrictPosition()
  {
    return this.districtPositionBeforeMore;
  }

  public int getRetailPosition()
  {
    return this.retailPositionBeforeMore;
  }

  public int getShopTypePosition()
  {
    return this.shopTypePositionBeforeMore;
  }

  public int getTownPosition()
  {
    return this.townPositionBeforeMore;
  }

  public ViewMiddle_Class getViewMiddle_class()
  {
    return this.viewMiddle_class;
  }

  public ViewMiddle_Neighbor getViewMiddle_neighbor()
  {
    return this.viewMiddle_neighbor;
  }

  public ViewMiddle_Store getViewMiddle_store()
  {
    return this.viewMiddle_store;
  }

  public void initListener()
  {
    switch (this.flag)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      this.viewMiddle_neighbor.setOnSelectListener(new ViewMiddle_Neighbor.OnSelectListener()
      {
        public void getValue(String paramAnonymousString)
        {
          Log.i("Test", "商区 setOnSelectListener: showText: " + paramAnonymousString);
          Promotion_Select_menu.this.onRefresh(Promotion_Select_menu.this.viewMiddle_neighbor, paramAnonymousString);
        }
      });
      this.viewMiddle_store.setOnSelectListener(new ViewMiddle_Store.OnSelectListener()
      {
        public void getValue(String paramAnonymousString, int paramAnonymousInt)
        {
          Log.i("Test", "商店 setOnSelectListener: shopTypeId: " + paramAnonymousInt + " lastShopTypeId: " + Promotion_Select_menu.lastShopTypeId + "  showText: " + paramAnonymousString);
          Promotion_Select_menu.this.onRefresh(Promotion_Select_menu.this.viewMiddle_store, paramAnonymousString);
          if (Promotion_Select_menu.lastShopTypeId != paramAnonymousInt)
          {
            Promotion_Select_menu.this.expandTabView.setTitle("全部品类", 2);
            Promotion_Select_menu.lastShopTypeId = paramAnonymousInt;
          }
        }
      });
      this.viewMiddle_class.setOnSelectListener(new ViewMiddle_Class.OnSelectListener()
      {
        public void getValue(String paramAnonymousString)
        {
          Log.i("Test", "品类 setOnSelectListener: showText:  " + paramAnonymousString);
          Promotion_Select_menu.this.onRefresh(Promotion_Select_menu.this.viewMiddle_class, paramAnonymousString);
        }
      });
      this.viewMiddle_store.setOnItemStoreClickListener(new ViewMiddle_Store.OnItemStoreClickListener()
      {
        public void setItemId(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
        {
          IbeaconService.shopTypeId = paramAnonymousInt1;
          IbeaconService.retailId = paramAnonymousInt2;
          IbeaconService.category1Id = "-1";
          IbeaconService.category2Id = "-1";
          IbeaconService.brandIds = "";
          IbeaconService.brandValues = "";
          if (PromotionActivity.topItemCount == 2)
          {
            Promotion_Select_menu.this.shopTypePositionBeforeMore = paramAnonymousInt3;
            Promotion_Select_menu.this.retailPositionBeforeMore = paramAnonymousInt4;
          }
          Log.i("Test", "商店 setOnItemStoreClickListener: shopTypeId: " + paramAnonymousInt1 + "  retailId: " + paramAnonymousInt2);
          if ((paramAnonymousInt2 <= 0) || (Integer.valueOf(paramAnonymousInt2) == null))
          {
            paramAnonymousInt3 = -1;
            if (paramAnonymousInt3 == -1)
              break label175;
            Promotion_Select_menu.to_class_shopTypeId = paramAnonymousInt1;
            label105: if (Promotion_Select_menu.this.activityFlag != 1)
              break label182;
            ((PromotionActivity)Promotion_Select_menu.this.context).isInShop = false;
            ((PromotionActivity)Promotion_Select_menu.this.context).setScreenStore(paramAnonymousInt1, paramAnonymousInt2);
            ((PromotionActivity)Promotion_Select_menu.this.context).page = 1;
          }
          while (true)
          {
            UMengStatisticsUtils.promotionStoreMenu(Promotion_Select_menu.this.context);
            return;
            paramAnonymousInt3 = paramAnonymousInt2;
            break;
            label175: Promotion_Select_menu.to_class_shopTypeId = -1;
            break label105;
            label182: if (Promotion_Select_menu.this.activityFlag == 2)
            {
              ((SearchForPromotionActivity)Promotion_Select_menu.this.context).page = 1;
              ((SearchForPromotionActivity)Promotion_Select_menu.this.context).setScreenStore(paramAnonymousInt1, paramAnonymousInt2);
            }
            else if (Promotion_Select_menu.this.activityFlag == 3)
            {
              ((ScanStoreGoodsActivity)Promotion_Select_menu.this.context).setScreenStore(paramAnonymousInt1, paramAnonymousInt2);
            }
            else if (Promotion_Select_menu.this.activityFlag == 5)
            {
              ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).isInShop = false;
              ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).page = 1;
              ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).setScreenStore(paramAnonymousInt1, paramAnonymousInt2);
            }
          }
        }
      });
      this.viewMiddle_neighbor.setOnItemClickListener(new ViewMiddle_Neighbor.OnItemClickListener()
      {
        public void setItemId(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5)
        {
          IbeaconService.townId = paramAnonymousInt2;
          IbeaconService.districtId = paramAnonymousInt1;
          Log.i("hahaha", "点击之后数据： districtId: " + paramAnonymousInt1 + "  townId: " + paramAnonymousInt2 + "  nearDistance: " + paramAnonymousInt3);
          Promotion_Select_menu.this.sendMiddleStoreUpdateBroadcastReceiver();
          if (PromotionActivity.topItemCount == 2)
          {
            Promotion_Select_menu.this.districtPositionBeforeMore = paramAnonymousInt4;
            Promotion_Select_menu.this.townPositionBeforeMore = paramAnonymousInt5;
          }
          if (Promotion_Select_menu.this.activityFlag == 1)
          {
            ((PromotionActivity)Promotion_Select_menu.this.context).isInShop = false;
            ((PromotionActivity)Promotion_Select_menu.this.context).page = 1;
            ((PromotionActivity)Promotion_Select_menu.this.context).setScreenArea(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
          }
          do
          {
            return;
            if (Promotion_Select_menu.this.activityFlag == 2)
            {
              ((SearchForPromotionActivity)Promotion_Select_menu.this.context).page = 1;
              ((SearchForPromotionActivity)Promotion_Select_menu.this.context).setScreenArea(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
              return;
            }
          }
          while (Promotion_Select_menu.this.activityFlag != 5);
          ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).isInShop = false;
          ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).page = 1;
          ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).setScreenArea(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
        }
      });
      this.viewMiddle_class.setOnItemClazzClickListener(new ViewMiddle_Class.OnItemClazzClickListener()
      {
        public void setItemId(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          IbeaconService.category1Id = String.valueOf(paramAnonymousInt1);
          IbeaconService.category2Id = String.valueOf(paramAnonymousInt2);
          IbeaconService.brandIds = "";
          IbeaconService.brandValues = "";
          if (Promotion_Select_menu.this.activityFlag == 1)
          {
            if (((PromotionActivity)Promotion_Select_menu.this.context).isInShop)
              ((PromotionActivity)Promotion_Select_menu.this.context).initWaterFull2();
          }
          else
          {
            if (Promotion_Select_menu.this.activityFlag != 2)
              break label166;
            ((SearchForPromotionActivity)Promotion_Select_menu.this.context).page = 1;
            ((SearchForPromotionActivity)Promotion_Select_menu.this.context).setShopClazz(paramAnonymousInt2);
          }
          while (true)
          {
            Log.i("Test", "品类  clazzId1: " + paramAnonymousInt1 + "  clazzId2: " + paramAnonymousInt2);
            return;
            ((PromotionActivity)Promotion_Select_menu.this.context).page = 1;
            ((PromotionActivity)Promotion_Select_menu.this.context).setShopClazz(paramAnonymousInt2);
            break;
            label166: if (Promotion_Select_menu.this.activityFlag == 5)
              if (((SearchStorePromotionActivity)Promotion_Select_menu.this.context).isInShop)
              {
                ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).initWaterFull2();
              }
              else
              {
                ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).page = 1;
                ((SearchStorePromotionActivity)Promotion_Select_menu.this.context).setShopClazz(paramAnonymousInt2);
              }
          }
        }
      });
      return;
      PromotionActivity.topItemCount = 2;
      SearchStorePromotionActivity.topItemCount = 2;
      this.expandTabView.getChildAt(this.expandTabView.getChildCount() - 1).setVisibility(8);
      continue;
      PromotionActivity.topItemCount = 3;
      SearchStorePromotionActivity.topItemCount = 3;
      this.expandTabView.getChildAt(this.expandTabView.getChildCount() - 1).setVisibility(0);
    }
  }

  public boolean isMainCategoryId(int paramInt)
  {
    return this.viewMiddle_class.isMainCategoryId(paramInt);
  }

  public void onRefresh(View paramView, String paramString)
  {
    this.expandTabView.onPressBack();
    int i = getPositon(paramView);
    if ((i >= 0) && (!this.expandTabView.getTitle(i).equals(paramString)))
      this.expandTabView.setTitle(paramString, i);
  }

  public void setFlag(int paramInt)
  {
    this.flag = paramInt;
  }

  public void setItem()
  {
    initVaule();
    initListener();
  }

  public void unRegisterReceiver(Context paramContext)
  {
    if (this.viewMiddle_store != null)
      this.viewMiddle_store.unRegisterReceiver(paramContext);
    if (this.viewMiddle_class != null)
      this.viewMiddle_class.unRegisterReceiver(paramContext);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.item.Promotion_Select_menu
 * JD-Core Version:    0.6.2
 */