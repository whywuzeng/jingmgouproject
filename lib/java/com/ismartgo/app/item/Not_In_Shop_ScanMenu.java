package com.ismartgo.app.item;

import android.content.Context;
import android.view.View;
import com.ismartgo.app.activity.PromotionActivity;
import com.ismartgo.app.activity.ScanStoreGoodsActivity;
import com.ismartgo.app.activity.SearchForPromotionActivity;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.ownself.view.NotInShopExpandTabView;
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
import java.util.ArrayList;

public class Not_In_Shop_ScanMenu
{
  public static int to_class_shopTypeId = -1;
  private int activityFlag;
  Context context;
  NotInShopExpandTabView expandTabView;
  int flag;
  public MyDialog mDialog;
  ArrayList<String> mTextArray = new ArrayList();
  private ArrayList<View> mViewArray = new ArrayList();
  private ToastDefine toast;
  private ViewMiddle_Class viewMiddle_class;
  private ViewMiddle_Neighbor viewMiddle_neighbor;
  private ViewMiddle_Store viewMiddle_store;

  public Not_In_Shop_ScanMenu()
  {
  }

  public Not_In_Shop_ScanMenu(Context paramContext, NotInShopExpandTabView paramNotInShopExpandTabView, int paramInt1, int paramInt2)
  {
    this.context = paramContext;
    this.expandTabView = paramNotInShopExpandTabView;
    this.flag = paramInt1;
    this.activityFlag = paramInt2;
    this.toast = new ToastDefine(paramContext);
    this.mDialog = new MyDialog(paramContext);
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

  private void initListener()
  {
    this.viewMiddle_neighbor.setOnSelectListener(new ViewMiddle_Neighbor.OnSelectListener()
    {
      public void getValue(String paramAnonymousString)
      {
        Not_In_Shop_ScanMenu.this.onRefresh(Not_In_Shop_ScanMenu.this.viewMiddle_neighbor, paramAnonymousString);
      }
    });
    this.viewMiddle_store.setOnSelectListener(new ViewMiddle_Store.OnSelectListener()
    {
      public void getValue(String paramAnonymousString, int paramAnonymousInt)
      {
        Not_In_Shop_ScanMenu.this.onRefresh(Not_In_Shop_ScanMenu.this.viewMiddle_store, paramAnonymousString);
      }
    });
    this.viewMiddle_class.setOnSelectListener(new ViewMiddle_Class.OnSelectListener()
    {
      public void getValue(String paramAnonymousString)
      {
        Not_In_Shop_ScanMenu.this.onRefresh(Not_In_Shop_ScanMenu.this.viewMiddle_class, paramAnonymousString);
      }
    });
    this.viewMiddle_store.setOnItemStoreClickListener(new ViewMiddle_Store.OnItemStoreClickListener()
    {
      public void setItemId(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        if ((paramAnonymousInt2 <= 0) || (Integer.valueOf(paramAnonymousInt2) == null))
        {
          paramAnonymousInt3 = -1;
          if (paramAnonymousInt3 == -1)
            break label75;
          if (Not_In_Shop_ScanMenu.to_class_shopTypeId != paramAnonymousInt1)
            Not_In_Shop_ScanMenu.to_class_shopTypeId = paramAnonymousInt1;
          label29: if (Not_In_Shop_ScanMenu.this.activityFlag != 1)
            break label82;
          ((PromotionActivity)Not_In_Shop_ScanMenu.this.context).setScreenStore(paramAnonymousInt1, paramAnonymousInt2);
          ((PromotionActivity)Not_In_Shop_ScanMenu.this.context).page = 1;
        }
        label75: label82: 
        do
        {
          return;
          paramAnonymousInt3 = paramAnonymousInt2;
          break;
          Not_In_Shop_ScanMenu.to_class_shopTypeId = -1;
          break label29;
          if (Not_In_Shop_ScanMenu.this.activityFlag == 2)
          {
            ((SearchForPromotionActivity)Not_In_Shop_ScanMenu.this.context).page = 1;
            ((SearchForPromotionActivity)Not_In_Shop_ScanMenu.this.context).setScreenStore(paramAnonymousInt1, paramAnonymousInt2);
            return;
          }
        }
        while (Not_In_Shop_ScanMenu.this.activityFlag != 3);
        ((ScanStoreGoodsActivity)Not_In_Shop_ScanMenu.this.context).setScreenStore(paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.viewMiddle_neighbor.setOnItemClickListener(new ViewMiddle_Neighbor.OnItemClickListener()
    {
      public void setItemId(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5)
      {
        if (Not_In_Shop_ScanMenu.this.activityFlag == 1)
        {
          ((PromotionActivity)Not_In_Shop_ScanMenu.this.context).setScreenArea(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
          ((PromotionActivity)Not_In_Shop_ScanMenu.this.context).page = 1;
        }
        while (Not_In_Shop_ScanMenu.this.activityFlag != 2)
          return;
        ((SearchForPromotionActivity)Not_In_Shop_ScanMenu.this.context).page = 1;
        ((SearchForPromotionActivity)Not_In_Shop_ScanMenu.this.context).setScreenArea(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
      }
    });
    this.viewMiddle_class.setOnItemClazzClickListener(new ViewMiddle_Class.OnItemClazzClickListener()
    {
      public void setItemId(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (Not_In_Shop_ScanMenu.this.activityFlag == 1)
        {
          ((PromotionActivity)Not_In_Shop_ScanMenu.this.context).page = 1;
          ((PromotionActivity)Not_In_Shop_ScanMenu.this.context).setShopClazz(paramAnonymousInt2);
        }
        if (Not_In_Shop_ScanMenu.this.activityFlag == 2)
        {
          ((SearchForPromotionActivity)Not_In_Shop_ScanMenu.this.context).page = 1;
          ((SearchForPromotionActivity)Not_In_Shop_ScanMenu.this.context).setShopClazz(paramAnonymousInt2);
        }
      }
    });
  }

  private void initVaule()
  {
    if (this.flag != 2)
    {
      this.mViewArray.add(this.viewMiddle_neighbor);
      this.mTextArray.add("全部商店");
    }
    this.mViewArray.add(this.viewMiddle_store);
    this.mTextArray.add("全部门店");
    switch (this.flag)
    {
    case 0:
    default:
    case 1:
    }
    while (true)
    {
      this.expandTabView.setValue(this.mTextArray, this.mViewArray);
      return;
      this.mViewArray.add(this.viewMiddle_class);
      this.mTextArray.add("全部品类");
    }
  }

  private void onRefresh(View paramView, String paramString)
  {
    this.expandTabView.onPressBack();
    int i = getPositon(paramView);
    if ((i >= 0) && (!this.expandTabView.getTitle(i).equals(paramString)))
      this.expandTabView.setTitle(paramString, i);
  }

  public boolean isMainCategoryId(int paramInt)
  {
    return this.viewMiddle_class.isMainCategoryId(paramInt);
  }

  public void setItem()
  {
    initVaule();
    initListener();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.item.Not_In_Shop_ScanMenu
 * JD-Core Version:    0.6.2
 */