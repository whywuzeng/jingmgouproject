package cn.sharesdk.onekeyshare.theme.skyblue;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.CustomerLogo;
import cn.sharesdk.onekeyshare.ShareCore;
import com.mob.tools.utils.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PlatformGridViewAdapter extends BaseAdapter
  implements View.OnClickListener
{
  private List<Integer> checkedPositionList = new ArrayList();
  private final Context context;
  private int directOnlyPosition = -1;
  private List<Object> logos = new ArrayList();

  public PlatformGridViewAdapter(Context paramContext)
  {
    this.context = paramContext;
  }

  private Bitmap getIcon(Platform paramPlatform, String paramString)
  {
    paramPlatform = "skyblue_logo_" + paramPlatform.getName() + paramString;
    int i = R.getBitmapRes(this.context, paramPlatform);
    return BitmapFactory.decodeResource(this.context.getResources(), i);
  }

  private String getName(Platform paramPlatform)
  {
    if (paramPlatform == null)
      return "";
    if (paramPlatform.getName() == null)
      return "";
    int i = R.getStringRes(this.context, paramPlatform.getName());
    if (i > 0)
      return this.context.getString(i);
    return null;
  }

  public List<Object> getCheckedItems()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.directOnlyPosition != -1)
      localArrayList.add(getItem(this.directOnlyPosition));
    while (true)
    {
      return localArrayList;
      Iterator localIterator = this.checkedPositionList.iterator();
      while (localIterator.hasNext())
        localArrayList.add(getItem(((Integer)localIterator.next()).intValue()));
    }
  }

  public int getCount()
  {
    return this.logos.size();
  }

  public Object getItem(int paramInt)
  {
    return this.logos.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    Object localObject1;
    boolean bool;
    label128: int i;
    label156: Object localObject2;
    if (paramView == null)
    {
      localView = LayoutInflater.from(this.context).inflate(R.getLayoutRes(this.context, "skyblue_share_platform_list_item"), null);
      paramViewGroup = new ViewHolder();
      paramViewGroup.checkedImageView = ((ImageView)localView.findViewById(R.getIdRes(this.context, "checkedImageView")));
      paramViewGroup.logoImageView = ((ImageView)localView.findViewById(R.getIdRes(this.context, "logoImageView")));
      paramViewGroup.nameTextView = ((TextView)localView.findViewById(R.getIdRes(this.context, "nameTextView")));
      localView.setTag(paramViewGroup);
      localObject1 = getItem(paramInt);
      if (!(localObject1 instanceof Platform))
        break label322;
      bool = ShareCore.isDirectShare((Platform)localObject1);
      if (this.directOnlyPosition != -1)
        break label334;
      if ((this.checkedPositionList.isEmpty()) || (!bool))
        break label328;
      i = 1;
      if (!(localObject1 instanceof Platform))
        break label360;
      localObject2 = (Platform)localObject1;
      if (i == 0)
        break label354;
      paramView = "";
      label179: paramView = getIcon((Platform)localObject2, paramView);
      localObject1 = getName((Platform)localObject1);
      localView.setOnClickListener(this);
      if ((this.directOnlyPosition == -1) || (this.directOnlyPosition == paramInt))
        break label403;
      localObject2 = "skyblue_platform_checked_disabled";
      label224: paramViewGroup.position = Integer.valueOf(paramInt);
      paramViewGroup.checkedImageView.setImageBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.getBitmapRes(this.context, (String)localObject2)));
      localObject2 = paramViewGroup.checkedImageView;
      if (!this.checkedPositionList.contains(paramViewGroup.position))
        break label410;
    }
    label403: label410: for (paramInt = 0; ; paramInt = 8)
    {
      ((ImageView)localObject2).setVisibility(paramInt);
      paramViewGroup.nameTextView.setText((CharSequence)localObject1);
      paramViewGroup.logoImageView.setImageBitmap(paramView);
      return localView;
      paramViewGroup = (ViewHolder)paramView.getTag();
      localView = paramView;
      break;
      label322: bool = true;
      break label128;
      label328: i = 0;
      break label156;
      label334: if (paramInt != this.directOnlyPosition);
      for (i = 1; ; i = 0)
        break;
      label354: paramView = "_checked";
      break label179;
      label360: localObject1 = (CustomerLogo)localObject1;
      if (i != 0);
      for (paramView = ((CustomerLogo)localObject1).disableLogo; ; paramView = ((CustomerLogo)localObject1).enableLogo)
      {
        localObject1 = ((CustomerLogo)localObject1).label;
        localView.setOnClickListener(this);
        break;
      }
      localObject2 = "skyblue_platform_checked";
      break label224;
    }
  }

  public void onClick(View paramView)
  {
    paramView = ((ViewHolder)paramView.getTag()).position;
    if ((this.directOnlyPosition != -1) && (paramView.intValue() != this.directOnlyPosition))
      return;
    Object localObject = getItem(paramView.intValue());
    boolean bool;
    if ((localObject instanceof Platform))
    {
      bool = ShareCore.isDirectShare((Platform)localObject);
      label55: if ((bool) && (this.directOnlyPosition == -1) && (!this.checkedPositionList.isEmpty()))
        break label120;
      if (!this.checkedPositionList.contains(paramView))
        break label122;
      this.checkedPositionList.remove(paramView);
      if (bool)
        this.directOnlyPosition = -1;
    }
    while (true)
    {
      notifyDataSetChanged();
      return;
      bool = true;
      break label55;
      label120: break;
      label122: this.checkedPositionList.add(paramView);
      if (bool)
        this.directOnlyPosition = paramView.intValue();
    }
  }

  public void setCustomerLogos(ArrayList<CustomerLogo> paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.size() == 0))
      return;
    this.logos.addAll(paramArrayList);
  }

  public void setData(Platform[] paramArrayOfPlatform, HashMap<String, String> paramHashMap)
  {
    if (paramArrayOfPlatform == null)
      return;
    ArrayList localArrayList;
    int i;
    if ((paramHashMap != null) && (paramHashMap.size() > 0))
    {
      localArrayList = new ArrayList();
      int j = paramArrayOfPlatform.length;
      i = 0;
      if (i >= j)
        this.logos.addAll(localArrayList);
    }
    while (true)
    {
      this.checkedPositionList.clear();
      notifyDataSetChanged();
      return;
      Platform localPlatform = paramArrayOfPlatform[i];
      if (paramHashMap.containsKey(localPlatform.getName()));
      while (true)
      {
        i += 1;
        break;
        localArrayList.add(localPlatform);
      }
      this.logos.addAll(Arrays.asList(paramArrayOfPlatform));
    }
  }

  static class ViewHolder
  {
    public ImageView checkedImageView;
    public ImageView logoImageView;
    public TextView nameTextView;
    public Integer position;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.theme.skyblue.PlatformGridViewAdapter
 * JD-Core Version:    0.6.2
 */