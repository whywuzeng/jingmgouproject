package com.ismartgo.app.popwindow;

import android.content.Context;
import android.content.Intent;
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
import com.ismartgo.app.bean.Area;
import com.ismartgo.app.bean.DistrictArea;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.grid.utils.Utils;
import com.ismartgo.app.interfaces.ViewBaseActionInterface;
import com.ismartgo.app.service.IbeaconService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ViewMiddle_Neighbor extends LinearLayout
  implements ViewBaseActionInterface
{
  private List<Area> area;
  private SparseArray<LinkedList<String>> children = new SparseArray();
  private LinkedList<String> childrenItem = new LinkedList();
  private Context context;
  private String disname = "";
  private int districtId;
  private List<DistrictArea> district_list;
  private ExpandTextAdapter earaListViewAdapter;
  private ArrayList<String> groups = new ArrayList();
  private boolean isFirst = true;
  private MyDialog mDialog;
  private OnItemClickListener mOnItemClickListener;
  private OnSelectListener mOnSelectListener;
  private String nearByName = "附近";
  private ListView plateListView;
  private ExpandTextAdapter plateListViewAdapter;
  private int platePosition = 0;
  private ListView regionListView;
  private int regionPosition = 0;
  private String showString = "";
  private int tBlockPosition = 0;
  private int tEaraPosition = 0;
  private int townId;

  public ViewMiddle_Neighbor(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init(paramContext);
  }

  public ViewMiddle_Neighbor(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    init(paramContext);
  }

  private void getBusinessArea()
  {
    this.area = IbeaconService.area;
    this.groups.clear();
    int i;
    if (this.area.size() > 0)
    {
      setDisname(((Area)this.area.get(0)).getAreaName());
      i = 0;
    }
    while (true)
    {
      if (i >= this.area.size())
      {
        setAdapter();
        return;
      }
      this.groups.add(((Area)this.area.get(i)).getAreaName());
      i += 1;
    }
  }

  private void init(Context paramContext)
  {
    LayoutInflater.from(paramContext).inflate(2130903186, this, true);
    this.regionListView = ((ListView)findViewById(2131231291));
    this.plateListView = ((ListView)findViewById(2131231292));
    setBackgroundColor(getResources().getColor(17170443));
    getBusinessArea();
  }

  private void sendMiddleStoreUpdateBroadcastReceiver()
  {
    Intent localIntent = new Intent();
    localIntent.setAction(Utils.MIDDLE_STORE_RECEIVER_ACTION);
    this.context.sendBroadcast(localIntent);
  }

  private void setAdapter()
  {
    Log.i("hahaha", "商区setadapter");
    this.earaListViewAdapter = new ExpandTextAdapter(getContext(), this.groups, 0, 2130837608);
    this.earaListViewAdapter.setTextSize(15.0F);
    this.regionListView.setAdapter(this.earaListViewAdapter);
    this.earaListViewAdapter.setOnItemClickListener(new ExpandTextAdapter.OnItemClickListener()
    {
      public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
      {
        ViewMiddle_Neighbor.this.platePosition = paramAnonymousInt;
        int i = ((Area)ViewMiddle_Neighbor.this.area.get(paramAnonymousInt)).getDistrictId();
        ViewMiddle_Neighbor.this.setDistrictId(i);
        paramAnonymousView = ((Area)ViewMiddle_Neighbor.this.area.get(paramAnonymousInt)).getAreaName();
        ViewMiddle_Neighbor.this.setDisname(paramAnonymousView);
        ViewMiddle_Neighbor.this.getThirdMenuData(paramAnonymousInt, i);
        ViewMiddle_Neighbor.this.plateListViewAdapter.setDefaultSelection(0);
        ViewMiddle_Neighbor.this.plateListView.setSelection(0);
        IbeaconService.temDistrictId = i;
      }
    });
    if (this.tEaraPosition < this.children.size())
      this.childrenItem.addAll((Collection)this.children.get(this.tEaraPosition));
    this.plateListViewAdapter = new ExpandTextAdapter(getContext(), this.childrenItem, 0, 2130837609);
    this.plateListViewAdapter.setTextSize(15.0F);
    this.plateListView.setAdapter(this.plateListViewAdapter);
    this.plateListViewAdapter.setOnItemClickListener(new ExpandTextAdapter.OnItemClickListener()
    {
      public void onItemClick(View paramAnonymousView, int paramAnonymousInt)
      {
        ViewMiddle_Neighbor.this.regionPosition = paramAnonymousInt;
        ViewMiddle_Neighbor.this.showString = ((String)ViewMiddle_Neighbor.this.childrenItem.get(paramAnonymousInt));
        if (ViewMiddle_Neighbor.this.mOnSelectListener != null)
        {
          if (((DistrictArea)ViewMiddle_Neighbor.this.district_list.get(paramAnonymousInt)).getTownId() >= 0)
            break label184;
          ViewMiddle_Neighbor.this.mOnSelectListener.getValue(ViewMiddle_Neighbor.this.getDisname());
          ViewMiddle_Neighbor.this.mOnItemClickListener.setItemId(((DistrictArea)ViewMiddle_Neighbor.this.district_list.get(paramAnonymousInt)).getDistrictId(), ((DistrictArea)ViewMiddle_Neighbor.this.district_list.get(paramAnonymousInt)).getTownId(), -1, ViewMiddle_Neighbor.this.platePosition, paramAnonymousInt);
        }
        while (true)
        {
          IbeaconService.temDistrictId = ((DistrictArea)ViewMiddle_Neighbor.this.district_list.get(paramAnonymousInt)).getDistrictId();
          IbeaconService.temTownId = ((DistrictArea)ViewMiddle_Neighbor.this.district_list.get(paramAnonymousInt)).getTownId();
          return;
          label184: ViewMiddle_Neighbor.this.mOnSelectListener.getValue(ViewMiddle_Neighbor.this.showString);
          ViewMiddle_Neighbor.this.mOnItemClickListener.setItemId(((DistrictArea)ViewMiddle_Neighbor.this.district_list.get(paramAnonymousInt)).getDistrictId(), ((DistrictArea)ViewMiddle_Neighbor.this.district_list.get(paramAnonymousInt)).getTownId(), -1, ViewMiddle_Neighbor.this.platePosition, paramAnonymousInt);
        }
      }
    });
    if (this.tBlockPosition < this.childrenItem.size())
      this.showString = ((String)this.childrenItem.get(this.tBlockPosition));
    setDefaultSelect();
  }

  public String getDisname()
  {
    return this.disname;
  }

  public int getDistrictId()
  {
    return this.districtId;
  }

  public int getPlatPosition()
  {
    return this.platePosition;
  }

  public int getRegionPosition()
  {
    return this.regionPosition;
  }

  public String getShowText()
  {
    return this.showString;
  }

  public void getThirdMenuData(int paramInt1, int paramInt2)
  {
    this.district_list = ((Area)this.area.get(paramInt1)).getDistrictList();
    LinkedList localLinkedList;
    if (this.district_list != null)
    {
      localLinkedList = new LinkedList();
      paramInt2 = 0;
      if (paramInt2 < this.district_list.size())
        break label110;
      paramInt2 = 0;
    }
    while (true)
    {
      if (paramInt2 >= this.area.size())
      {
        if (paramInt1 < this.children.size())
        {
          this.childrenItem.clear();
          this.childrenItem.addAll((Collection)this.children.get(paramInt1));
          this.plateListViewAdapter.notifyDataSetChanged();
        }
        return;
        label110: if ((paramInt2 == 0) && (this.isFirst))
        {
          IbeaconService.townId = ((DistrictArea)this.district_list.get(paramInt2)).getTownId();
          this.isFirst = false;
        }
        localLinkedList.add(((DistrictArea)this.district_list.get(paramInt2)).getName());
        paramInt2 += 1;
        break;
      }
      this.children.put(paramInt2, localLinkedList);
      paramInt2 += 1;
    }
  }

  public int getTownId()
  {
    return this.townId;
  }

  public void hide()
  {
  }

  public void setCheckMoreBackSelect(int paramInt1, int paramInt2)
  {
    LinkedList localLinkedList;
    int i;
    if (this.earaListViewAdapter != null)
    {
      this.earaListViewAdapter.setSelectedPosition(paramInt1);
      this.regionListView.setSelection(paramInt1);
      if ((this.area != null) && (this.area.size() > 0))
      {
        this.district_list = ((Area)this.area.get(paramInt1)).getDistrictList();
        setDisname(((Area)this.area.get(paramInt1)).getAreaName());
        if (this.district_list != null)
        {
          localLinkedList = new LinkedList();
          i = 0;
          if (i < this.district_list.size())
            break label189;
          i = 0;
        }
      }
    }
    while (true)
    {
      if (i >= this.area.size())
      {
        if (paramInt1 < this.children.size())
        {
          this.childrenItem.clear();
          this.childrenItem.addAll((Collection)this.children.get(paramInt1));
          this.plateListViewAdapter.notifyDataSetChanged();
        }
        this.plateListViewAdapter.setSelectedPosition(paramInt2);
        this.plateListView.setSelection(paramInt2);
        return;
        label189: localLinkedList.add(((DistrictArea)this.district_list.get(i)).getName());
        i += 1;
        break;
      }
      this.children.put(i, localLinkedList);
      i += 1;
    }
  }

  public void setCheckMoreSelect()
  {
    this.plateListViewAdapter.setSelectedPosition(0);
    this.plateListView.setSelection(0);
    this.earaListViewAdapter.setSelectedPosition(0);
    this.regionListView.setSelection(0);
  }

  public void setDefaultSelect()
  {
    getThirdMenuData(this.tBlockPosition, ((Area)this.area.get(this.tBlockPosition)).getDistrictId());
    this.earaListViewAdapter.setDefaultSelection(0);
    this.plateListViewAdapter.setDefaultSelection(0);
  }

  public void setDefaultSelect_2()
  {
    removeAllViews();
    this.tEaraPosition = 0;
    this.tBlockPosition = 0;
    init(getContext());
    this.regionPosition = 0;
    this.platePosition = 0;
  }

  public void setDisname(String paramString)
  {
    this.disname = paramString;
  }

  public void setDistrictId(int paramInt)
  {
    this.districtId = paramInt;
  }

  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener)
  {
    this.mOnItemClickListener = paramOnItemClickListener;
  }

  public void setOnSelectListener(OnSelectListener paramOnSelectListener)
  {
    this.mOnSelectListener = paramOnSelectListener;
  }

  public void setPlatePosition(int paramInt)
  {
    this.platePosition = paramInt;
  }

  public void setRegionPosition(int paramInt)
  {
    this.regionPosition = paramInt;
  }

  public void setTownId(int paramInt)
  {
    this.townId = paramInt;
  }

  public void show()
  {
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
        if (!((String)this.childrenItem.get(i)).equals(paramString2.trim()))
          break label155;
        this.plateListViewAdapter.setSelectedPosition(i);
        this.tBlockPosition = i;
      }
      label155: i += 1;
    }
  }

  public static abstract interface OnItemClickListener
  {
    public abstract void setItemId(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  }

  public static abstract interface OnSelectListener
  {
    public abstract void getValue(String paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.popwindow.ViewMiddle_Neighbor
 * JD-Core Version:    0.6.2
 */