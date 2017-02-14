package com.ismartgo.app.popwindow;

import android.app.Dialog;
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
import com.ismartgo.app.adapter.ExpandTextAdapter;
import com.ismartgo.app.adapter.ExpandTextAdapter.OnItemClickListener;
import com.ismartgo.app.bean.GoodsClazz;
import com.ismartgo.app.grid.utils.Utils;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.interfaces.ViewBaseActionInterface;
import com.ismartgo.app.net.ClassAreaRequest;
import com.ismartgo.app.service.IbeaconService;
import com.ismartgo.app.url.Url;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ViewMiddle_Class extends LinearLayout
  implements ViewBaseActionInterface
{
  private int category1Position;
  private int category2Position;
  private SparseArray<LinkedList<String>> children = new SparseArray();
  private LinkedList<String> childrenItem = new LinkedList();
  private String clazz1String = "";
  private int clazzId = -1;
  private ExpandTextAdapter earaListViewAdapter;
  private List<GoodsClazz> goodsClazzList2;
  private ArrayList<String> groups = new ArrayList();
  List<GoodsClazz> listGoodsClazz1 = new ArrayList();
  private Dialog mDialog;
  private OnItemClazzClickListener mOnItemClickListener;
  private OnSelectListener mOnSelectListener;
  private ListView plateListView;
  private ExpandTextAdapter plateListViewAdapter;
  ViewMiddleClazzReceiver receiver;
  private ListView regionListView;
  public int shopTypeId = -1;
  private String showString = "不限";
  private int tBlockPosition = 0;
  private int tEaraPosition = 0;

  public ViewMiddle_Class(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public ViewMiddle_Class(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  private void getThirdMenuData(final int paramInt1, int paramInt2)
  {
    this.goodsClazzList2 = ((GoodsClazz)IbeaconService.goodsClazzLists.get(paramInt1)).getGoodsClazzList();
    if (this.goodsClazzList2 != null)
    {
      localObject = new LinkedList();
      paramInt2 = 0;
      if (paramInt2 >= this.goodsClazzList2.size())
        paramInt2 = 0;
      while (true)
      {
        if (paramInt2 >= IbeaconService.goodsClazzLists.size())
        {
          if (paramInt1 < this.children.size())
          {
            this.childrenItem.clear();
            this.childrenItem.addAll((Collection)this.children.get(paramInt1));
            this.plateListViewAdapter.notifyDataSetChanged();
          }
          return;
          ((LinkedList)localObject).add(((GoodsClazz)this.goodsClazzList2.get(paramInt2)).getName());
          paramInt2 += 1;
          break;
        }
        this.children.put(paramInt2, localObject);
        paramInt2 += 1;
      }
    }
    Object localObject = new ClassAreaRequest(getContext(), Url.SHOP_CATEGORY_URL);
    ((ClassAreaRequest)localObject).initParams(paramInt2);
    ((ClassAreaRequest)localObject).setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        ViewMiddle_Class.this.mDialog.dismiss();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        ViewMiddle_Class.this.goodsClazzList2 = ((List)paramAnonymousObject);
        ((GoodsClazz)IbeaconService.goodsClazzLists.get(paramInt1)).setGoodsClazzList(ViewMiddle_Class.this.goodsClazzList2);
        paramAnonymousObject = new LinkedList();
        paramAnonymousInt = 0;
        if (paramAnonymousInt >= ViewMiddle_Class.this.goodsClazzList2.size())
          paramAnonymousInt = 0;
        while (true)
        {
          if (paramAnonymousInt >= IbeaconService.goodsClazzLists.size())
          {
            if (paramInt1 < ViewMiddle_Class.this.children.size())
            {
              ViewMiddle_Class.this.childrenItem.clear();
              ViewMiddle_Class.this.childrenItem.addAll((Collection)ViewMiddle_Class.this.children.get(paramInt1));
              ViewMiddle_Class.this.plateListViewAdapter.notifyDataSetChanged();
            }
            ViewMiddle_Class.this.mDialog.dismiss();
            return;
            paramAnonymousObject.add(((GoodsClazz)ViewMiddle_Class.this.goodsClazzList2.get(paramAnonymousInt)).getName());
            paramAnonymousInt += 1;
            break;
          }
          ViewMiddle_Class.this.children.put(paramAnonymousInt, paramAnonymousObject);
          paramAnonymousInt += 1;
        }
      }
    });
    ((ClassAreaRequest)localObject).startRequest();
  }

  private void getThirdMenuData2(GoodsClazz paramGoodsClazz, int paramInt)
  {
    paramGoodsClazz = paramGoodsClazz.getGoodsClazzList();
    this.goodsClazzList2 = paramGoodsClazz;
    LinkedList localLinkedList = new LinkedList();
    this.childrenItem.clear();
    paramInt = 0;

    /**
     * 就是要一个循环来取数据  然后再塞入
     */
    while (true)
    {
      if (paramInt >= paramGoodsClazz.size())
      {
        if (this.plateListViewAdapter != null)
          break;
        this.plateListViewAdapter = new ExpandTextAdapter(getContext(), this.childrenItem, 0, 2130837609);
        this.plateListViewAdapter.setTextSize(15.0F);
        this.plateListViewAdapter.setSelectedPositionNoNotify(this.tBlockPosition);
        this.plateListView.setAdapter(this.plateListViewAdapter);
        return;
      }
      GoodsClazz localGoodsClazz = (GoodsClazz)paramGoodsClazz.get(paramInt);
      localLinkedList.add(localGoodsClazz.getName());
      this.children.put(paramInt, localLinkedList);
      this.childrenItem.add(localGoodsClazz.getName());
      paramInt += 1;
    }
    this.plateListViewAdapter.notifyDataSetChanged();
  }

  private void setAdapter(ArrayList<String> paramArrayList, final int paramInt)
  {
    this.earaListViewAdapter = new ExpandTextAdapter(getContext(), paramArrayList, 0, 2130837608);
    this.earaListViewAdapter.setTextSize(15.0F);
    this.earaListViewAdapter.setSelectedPositionNoNotify(this.tEaraPosition);
    this.regionListView.setAdapter(this.earaListViewAdapter);
    this.earaListViewAdapter.setOnItemClickListener(new ExpandTextAdapter.OnItemClickListener()
    {
      public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
      {
        ViewMiddle_Class.this.category1Position = paramAnonymousInt;
        ViewMiddle_Class.this.listGoodsClazz1.clear();
        paramAnonymousView = IbeaconService.goodsClazzLists.iterator();
        while (true)
        {
          if (!paramAnonymousView.hasNext())
          {
            paramAnonymousView = (GoodsClazz)ViewMiddle_Class.this.listGoodsClazz1.get(paramAnonymousInt);
            ViewMiddle_Class.this.clazzId = paramAnonymousView.getClazzId();
            ViewMiddle_Class.this.clazz1String = paramAnonymousView.getName();
            ViewMiddle_Class.this.getThirdMenuData2(paramAnonymousView, paramAnonymousInt);
            ViewMiddle_Class.this.plateListViewAdapter.setSelectedPosition(0);
            ViewMiddle_Class.this.plateListView.setSelection(0);
            return;
          }
          GoodsClazz localGoodsClazz = (GoodsClazz)paramAnonymousView.next();
          if (localGoodsClazz.getShopTypeId() == paramInt)
            ViewMiddle_Class.this.listGoodsClazz1.add(localGoodsClazz);
        }
      }
    });
    if ((this.children.size() == 0) || (this.tEaraPosition < this.children.size()))
    {
      if (paramInt == -1)
      {
        this.childrenItem.clear();
        this.childrenItem.add("全部");
      }
    }
    else
    {
      this.plateListViewAdapter = new ExpandTextAdapter(getContext(), this.childrenItem, 0, 2130837609);
      this.plateListViewAdapter.setTextSize(15.0F);
      this.plateListViewAdapter.setSelectedPositionNoNotify(this.tBlockPosition);
      this.plateListView.setAdapter(this.plateListViewAdapter);
      this.plateListViewAdapter.setOnItemClickListener(new ExpandTextAdapter.OnItemClickListener()
      {
        public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
        {
          ViewMiddle_Class.this.category2Position = paramAnonymousInt;
          ViewMiddle_Class.this.showString = ((String)ViewMiddle_Class.this.childrenItem.get(paramAnonymousInt));
          if (ViewMiddle_Class.this.mOnSelectListener != null)
          {
            if (((GoodsClazz)ViewMiddle_Class.this.goodsClazzList2.get(paramAnonymousInt)).getClazzId() >= 0)
              break label119;
            ViewMiddle_Class.this.mOnSelectListener.getValue(ViewMiddle_Class.this.clazz1String);
          }
          while (true)
          {
            ViewMiddle_Class.this.mOnItemClickListener.setItemId(ViewMiddle_Class.this.clazzId, ((GoodsClazz)ViewMiddle_Class.this.goodsClazzList2.get(paramAnonymousInt)).getClazzId());
            return;
            label119: ViewMiddle_Class.this.mOnSelectListener.getValue(ViewMiddle_Class.this.showString);
          }
        }
      });
      if (this.tBlockPosition < this.childrenItem.size())
        this.showString = ((String)this.childrenItem.get(this.tBlockPosition));
      setDefaultSelect();
      return;
    }
    this.listGoodsClazz1.clear();
    paramArrayList = IbeaconService.goodsClazzLists.iterator();
    while (true)
    {
      if (!paramArrayList.hasNext())
      {
        if (this.listGoodsClazz1.size() <= 0)
          break;
        paramArrayList = (GoodsClazz)this.listGoodsClazz1.get(0);
        this.clazzId = paramArrayList.getClazzId();
        this.clazz1String = paramArrayList.getName();
        getThirdMenuData2(paramArrayList, 0);
        break;
      }
      GoodsClazz localGoodsClazz = (GoodsClazz)paramArrayList.next();
      if (localGoodsClazz.getShopTypeId() == paramInt)
        this.listGoodsClazz1.add(localGoodsClazz);
    }
  }

  public void getBusinessClass(int paramInt)
  {
    if (this.groups != null)
      this.groups.clear();
    int i;
    if (IbeaconService.goodsClazzLists.size() > 0)
      i = 0;
    while (true)
    {
      if (i >= IbeaconService.goodsClazzLists.size())
      {
        setAdapter(this.groups, paramInt);
        return;
      }
      if (((GoodsClazz)IbeaconService.goodsClazzLists.get(i)).getShopTypeId() == paramInt)
        this.groups.add(((GoodsClazz)IbeaconService.goodsClazzLists.get(i)).getName());
      i += 1;
    }
  }

  public String getShowText()
  {
    return this.showString;
  }

  public void hide()
  {
  }

  public void init(Context paramContext)
  {
    if (this.receiver == null)
      this.receiver = new ViewMiddleClazzReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction(Utils.MIDDLE_CLASS_RECEIVER_ACTION);
    paramContext.registerReceiver(this.receiver, localIntentFilter);
    Log.i("Test", "注册了品类广播");
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903186, this, true);
    this.regionListView = ((ListView)findViewById(2131231291));
    this.plateListView = ((ListView)findViewById(2131231292));
    setBackgroundColor(getResources().getColor(17170443));
    getBusinessClass(IbeaconService.shopTypeId);
  }

  public void initReceiver(Context paramContext)
  {
    if (this.receiver == null)
      this.receiver = new ViewMiddleClazzReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction(Utils.MIDDLE_CLASS_RECEIVER_ACTION);
    paramContext.registerReceiver(this.receiver, localIntentFilter);
  }

  public boolean isMainCategoryId(int paramInt)
  {
    int i = 0;
    while (true)
    {
      if (i >= IbeaconService.goodsClazzLists.size())
        return false;
      if (((GoodsClazz)IbeaconService.goodsClazzLists.get(i)).getClazzId() == paramInt)
        return true;
      i += 1;
    }
  }

  public void setDefaultSelect()
  {
    this.regionListView.setSelection(this.tEaraPosition);
    this.plateListView.setSelection(this.tBlockPosition);
  }

  public void setDefaultSelect_2()
  {
    removeAllViews();
    init(getContext());
  }

  public void setOnItemClazzClickListener(OnItemClazzClickListener paramOnItemClazzClickListener)
  {
    this.mOnItemClickListener = paramOnItemClazzClickListener;
  }

  public void setOnSelectListener(OnSelectListener paramOnSelectListener)
  {
    this.mOnSelectListener = paramOnSelectListener;
  }

  public void show()
  {
  }

  public void unRegisterReceiver(Context paramContext)
  {
    if (this.receiver != null)
      paramContext.unregisterReceiver(this.receiver);
  }

  public void updateShowText(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    int i = 0;
    if (i >= this.groups.size())
      label22: i = 0;
    while (true)
    {
      if (i >= this.childrenItem.size());
      while (true)
      {
        setDefaultSelect();
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
        if (!((String)this.childrenItem.get(i)).replace("不限", "").equals(paramString2.trim()))
          break label162;
        this.plateListViewAdapter.setSelectedPosition(i);
        this.tBlockPosition = i;
      }
      label162: i += 1;
    }
  }

  public static abstract interface OnItemClazzClickListener
  {
    public abstract void setItemId(int paramInt1, int paramInt2);
  }

  public static abstract interface OnSelectListener
  {
    public abstract void getValue(String paramString);
  }

  public class ViewMiddleClazzReceiver extends BroadcastReceiver
  {
    public ViewMiddleClazzReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals(Utils.MIDDLE_CLASS_RECEIVER_ACTION))
      {
        int i = paramIntent.getIntExtra("shopTypeId", -1000);
        Log.i("Test", "品类接收到广播   shopTypeId: " + i);
        ViewMiddle_Class.this.getBusinessClass(i);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.popwindow.ViewMiddle_Class
 * JD-Core Version:    0.6.2
 */