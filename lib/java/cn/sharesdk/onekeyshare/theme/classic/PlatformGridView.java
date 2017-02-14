package cn.sharesdk.onekeyshare.theme.classic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.CustomerLogo;
import com.mob.tools.gui.ViewPagerAdapter;
import com.mob.tools.gui.ViewPagerClassic;
import com.mob.tools.utils.R;
import com.mob.tools.utils.UIHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PlatformGridView extends LinearLayout
  implements View.OnClickListener, Handler.Callback
{
  private static final int MIN_CLICK_INTERVAL = 1000;
  private static final int MSG_PLATFORM_LIST_GOT = 1;
  private int COLUMN_PER_LINE;
  private int LINE_PER_PAGE;
  private int PAGE_SIZE;
  private View bgView;
  private Bitmap bluePoint;
  private ArrayList<CustomerLogo> customers;
  private Bitmap grayPoint;
  private HashMap<String, String> hiddenPlatforms;
  private long lastClickTime;
  private ViewPagerClassic pager;
  private PlatformListPage parent;
  private Platform[] platformList;
  private ImageView[] points;
  private HashMap<String, Object> reqData;
  private boolean silent;

  public PlatformGridView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public PlatformGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  private void calPageSize()
  {
    float f = R.getScreenWidth(getContext()) / R.getScreenHeight(getContext());
    if (f < 0.63D)
    {
      this.COLUMN_PER_LINE = 3;
      this.LINE_PER_PAGE = 3;
    }
    while (true)
    {
      this.PAGE_SIZE = (this.COLUMN_PER_LINE * this.LINE_PER_PAGE);
      return;
      if (f < 0.75D)
      {
        this.COLUMN_PER_LINE = 3;
        this.LINE_PER_PAGE = 2;
      }
      else
      {
        this.LINE_PER_PAGE = 1;
        if (f >= 1.75D)
          this.COLUMN_PER_LINE = 6;
        else if (f >= 1.5D)
          this.COLUMN_PER_LINE = 5;
        else if (f >= 1.3D)
          this.COLUMN_PER_LINE = 4;
        else
          this.COLUMN_PER_LINE = 3;
      }
    }
  }

  private void disableOverScrollMode(View paramView)
  {
    if (Build.VERSION.SDK_INT < 9)
      return;
    try
    {
      Method localMethod = View.class.getMethod("setOverScrollMode", new Class[] { Integer.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(paramView, new Object[] { Integer.valueOf(2) });
      return;
    }
    catch (Throwable paramView)
    {
      paramView.printStackTrace();
    }
  }

  private void init(Context paramContext)
  {
    calPageSize();
    setOrientation(1);
    this.pager = new ViewPagerClassic(paramContext);
    disableOverScrollMode(this.pager);
    this.pager.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    addView(this.pager);
    new Thread()
    {
      public void run()
      {
        try
        {
          PlatformGridView.this.platformList = ShareSDK.getPlatformList();
          if (PlatformGridView.this.platformList == null)
            PlatformGridView.this.platformList = new Platform[0];
          UIHandler.sendEmptyMessage(1, PlatformGridView.this);
          return;
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
        }
      }
    }
    .start();
  }

  public void afterPlatformListGot()
  {
    Object localObject = new PlatformAdapter(this);
    this.pager.setAdapter((ViewPagerAdapter)localObject);
    int i = 0;
    int j;
    if (this.platformList != null)
    {
      if (this.customers != null)
        break label100;
      i = 0;
      if (this.platformList != null)
        break label111;
      j = 0;
      label46: if (this.hiddenPlatforms != null)
        break label120;
    }
    label100: label111: label120: for (int k = 0; ; k = this.hiddenPlatforms.size())
    {
      k = j - k + i;
      j = k / this.PAGE_SIZE;
      i = j;
      if (k % this.PAGE_SIZE > 0)
        i = j + 1;
      this.points = new ImageView[i];
      if (this.points.length > 0)
        break label131;
      return;
      i = this.customers.size();
      break;
      j = this.platformList.length;
      break label46;
    }
    label131: localObject = getContext();
    LinearLayout localLinearLayout = new LinearLayout((Context)localObject);
    LinearLayout.LayoutParams localLayoutParams;
    if (i > 1)
    {
      j = 0;
      localLinearLayout.setVisibility(j);
      localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams.gravity = 1;
      localLinearLayout.setLayoutParams(localLayoutParams);
      addView(localLinearLayout);
      k = R.dipToPx((Context)localObject, 5);
      j = R.getBitmapRes(getContext(), "light_blue_point");
      if (j > 0)
        this.grayPoint = BitmapFactory.decodeResource(getResources(), j);
      j = R.getBitmapRes(getContext(), "blue_point");
      if (j > 0)
        this.bluePoint = BitmapFactory.decodeResource(getResources(), j);
      j = 0;
    }
    while (true)
    {
      if (j >= i)
      {
        i = this.pager.getCurrentScreen();
        this.points[i].setImageBitmap(this.bluePoint);
        return;
        j = 8;
        break;
      }
      this.points[j] = new ImageView((Context)localObject);
      this.points[j].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      this.points[j].setImageBitmap(this.grayPoint);
      localLayoutParams = new LinearLayout.LayoutParams(k, k);
      localLayoutParams.setMargins(k, k, k, 0);
      this.points[j].setLayoutParams(localLayoutParams);
      localLinearLayout.addView(this.points[j]);
      j += 1;
    }
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    }
    while (true)
    {
      return false;
      afterPlatformListGot();
    }
  }

  public void onClick(View paramView)
  {
    long l = System.currentTimeMillis();
    if (l - this.lastClickTime < 1000L)
      return;
    this.lastClickTime = l;
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramView.getTag());
    this.parent.onPlatformIconClick(paramView, localArrayList);
  }

  public void onConfigurationChanged()
  {
    int i = this.pager.getCurrentScreen();
    int j = this.PAGE_SIZE;
    calPageSize();
    i = i * j / this.PAGE_SIZE;
    removeViewAt(1);
    afterPlatformListGot();
    this.pager.setCurrentScreen(i);
  }

  public void setCustomerLogos(ArrayList<CustomerLogo> paramArrayList)
  {
    this.customers = paramArrayList;
  }

  public void setData(HashMap<String, Object> paramHashMap, boolean paramBoolean)
  {
    this.reqData = paramHashMap;
    this.silent = paramBoolean;
  }

  public void setEditPageBackground(View paramView)
  {
    this.bgView = paramView;
  }

  public void setHiddenPlatforms(HashMap<String, String> paramHashMap)
  {
    this.hiddenPlatforms = paramHashMap;
  }

  public void setParent(PlatformListPage paramPlatformListPage)
  {
    this.parent = paramPlatformListPage;
  }

  private static class GridView extends LinearLayout
  {
    private Object[] beans;
    private View.OnClickListener callback;
    private int lines;
    private PlatformGridView.PlatformAdapter platformAdapter;

    public GridView(PlatformGridView.PlatformAdapter paramPlatformAdapter)
    {
      super();
      this.platformAdapter = paramPlatformAdapter;
      this.callback = PlatformGridView.PlatformAdapter.access$1(paramPlatformAdapter);
    }

    private Bitmap getIcon(Platform paramPlatform)
    {
      if (paramPlatform == null);
      while (paramPlatform.getName() == null)
        return null;
      paramPlatform = "logo_" + paramPlatform.getName();
      int i = R.getBitmapRes(getContext(), paramPlatform.toLowerCase());
      return BitmapFactory.decodeResource(getResources(), i);
    }

    private String getName(Platform paramPlatform)
    {
      if (paramPlatform == null)
        return "";
      if (paramPlatform.getName() == null)
        return "";
      int i = R.getStringRes(getContext(), paramPlatform.getName().toLowerCase());
      if (i > 0)
        return getContext().getString(i);
      return null;
    }

    private LinearLayout getView(int paramInt, View.OnClickListener paramOnClickListener, Context paramContext)
    {
      Object localObject;
      if ((this.beans[paramInt] instanceof Platform))
        localObject = getIcon((Platform)this.beans[paramInt]);
      for (String str = getName((Platform)this.beans[paramInt]); ; str = ((CustomerLogo)this.beans[paramInt]).label)
      {
        LinearLayout localLinearLayout = new LinearLayout(paramContext);
        localLinearLayout.setOrientation(1);
        ImageView localImageView = new ImageView(paramContext);
        paramInt = R.dipToPx(paramContext, 5);
        localImageView.setPadding(paramInt, paramInt, paramInt, paramInt);
        localImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams.setMargins(paramInt, paramInt, paramInt, paramInt);
        localLayoutParams.gravity = 1;
        localImageView.setLayoutParams(localLayoutParams);
        localImageView.setImageBitmap((Bitmap)localObject);
        localLinearLayout.addView(localImageView);
        paramContext = new TextView(paramContext);
        paramContext.setTextColor(-16777216);
        paramContext.setTextSize(1, 14.0F);
        paramContext.setSingleLine();
        paramContext.setIncludeFontPadding(false);
        localObject = new LinearLayout.LayoutParams(-2, -2);
        ((LinearLayout.LayoutParams)localObject).gravity = 1;
        ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
        ((LinearLayout.LayoutParams)localObject).setMargins(paramInt, 0, paramInt, paramInt);
        paramContext.setLayoutParams((ViewGroup.LayoutParams)localObject);
        paramContext.setText(str);
        localLinearLayout.addView(paramContext);
        localLinearLayout.setOnClickListener(paramOnClickListener);
        return localLinearLayout;
        localObject = ((CustomerLogo)this.beans[paramInt]).enableLogo;
      }
    }

    private void init()
    {
      int n = R.dipToPx(getContext(), 5);
      setPadding(0, n, 0, n);
      setOrientation(1);
      int i;
      int i1;
      int k;
      int j;
      LinearLayout.LayoutParams localLayoutParams;
      if (this.beans == null)
      {
        i = 0;
        i1 = PlatformGridView.PlatformAdapter.access$0(this.platformAdapter).COLUMN_PER_LINE;
        k = i / i1;
        j = k;
        if (i % i1 > 0)
          j = k + 1;
        localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
        localLayoutParams.weight = 1.0F;
        k = 0;
      }
      LinearLayout localLinearLayout1;
      while (true)
      {
        if (k >= this.lines)
        {
          return;
          i = this.beans.length;
          break;
        }
        localLinearLayout1 = new LinearLayout(getContext());
        localLinearLayout1.setLayoutParams(localLayoutParams);
        localLinearLayout1.setPadding(n, 0, n, 0);
        addView(localLinearLayout1);
        if (k < j)
          break label150;
        k += 1;
      }
      label150: int m = 0;
      label153: int i2;
      LinearLayout localLinearLayout2;
      if (m < i1)
      {
        i2 = k * i1 + m;
        if (i2 < i)
          break label211;
        localLinearLayout2 = new LinearLayout(getContext());
        localLinearLayout2.setLayoutParams(localLayoutParams);
        localLinearLayout1.addView(localLinearLayout2);
      }
      while (true)
      {
        m += 1;
        break label153;
        break;
        label211: localLinearLayout2 = getView(i2, this.callback, getContext());
        localLinearLayout2.setTag(this.beans[i2]);
        localLinearLayout2.setLayoutParams(localLayoutParams);
        localLinearLayout1.addView(localLinearLayout2);
      }
    }

    public void setData(int paramInt, Object[] paramArrayOfObject)
    {
      this.lines = paramInt;
      this.beans = paramArrayOfObject;
      init();
    }
  }

  private static class PlatformAdapter extends ViewPagerAdapter
  {
    private View.OnClickListener callback;
    private PlatformGridView.GridView[] girds;
    private int lines;
    private List<Object> logos;
    private PlatformGridView platformGridView;

    public PlatformAdapter(PlatformGridView paramPlatformGridView)
    {
      this.platformGridView = paramPlatformGridView;
      this.logos = new ArrayList();
      Platform[] arrayOfPlatform = paramPlatformGridView.platformList;
      HashMap localHashMap = paramPlatformGridView.hiddenPlatforms;
      Object localObject;
      ArrayList localArrayList;
      int j;
      int i;
      if (arrayOfPlatform != null)
      {
        localObject = arrayOfPlatform;
        if (localHashMap != null)
        {
          localObject = arrayOfPlatform;
          if (localHashMap.size() > 0)
          {
            localArrayList = new ArrayList();
            j = arrayOfPlatform.length;
            i = 0;
            if (i < j)
              break label198;
            localObject = new Platform[localArrayList.size()];
            i = 0;
          }
        }
      }
      while (true)
      {
        if (i >= localObject.length)
        {
          this.logos.addAll(Arrays.asList((Object[])localObject));
          localObject = paramPlatformGridView.customers;
          if (localObject != null)
            this.logos.addAll((Collection)localObject);
          this.callback = paramPlatformGridView;
          this.girds = null;
          if (this.logos != null)
          {
            int k = this.logos.size();
            int m = paramPlatformGridView.PAGE_SIZE;
            j = k / m;
            i = j;
            if (k % m > 0)
              i = j + 1;
            this.girds = new PlatformGridView.GridView[i];
          }
          return;
          label198: localObject = arrayOfPlatform[i];
          if (localHashMap.containsKey(((Platform)localObject).getName()));
          while (true)
          {
            i += 1;
            break;
            localArrayList.add(localObject);
          }
        }
        localObject[i] = ((Platform)localArrayList.get(i));
        i += 1;
      }
    }

    public int getCount()
    {
      if (this.girds == null)
        return 0;
      return this.girds.length;
    }

    public View getView(int paramInt, ViewGroup paramViewGroup)
    {
      int m;
      int j;
      int i;
      if (this.girds[paramInt] == null)
      {
        int k = this.platformGridView.PAGE_SIZE;
        m = k * paramInt;
        if (this.logos != null)
          break label139;
        j = 0;
        i = k;
        if (m + k > j)
          i = j - m;
        paramViewGroup = new Object[i];
        j = 0;
      }
      while (true)
      {
        if (j >= i)
        {
          if (paramInt == 0)
          {
            i = this.platformGridView.COLUMN_PER_LINE;
            this.lines = (paramViewGroup.length / i);
            if (paramViewGroup.length % i > 0)
              this.lines += 1;
          }
          this.girds[paramInt] = new PlatformGridView.GridView(this);
          this.girds[paramInt].setData(this.lines, paramViewGroup);
          return this.girds[paramInt];
          label139: j = this.logos.size();
          break;
        }
        paramViewGroup[j] = this.logos.get(m + j);
        j += 1;
      }
    }

    public void onScreenChange(int paramInt1, int paramInt2)
    {
      ImageView[] arrayOfImageView = this.platformGridView.points;
      paramInt2 = 0;
      while (true)
      {
        if (paramInt2 >= arrayOfImageView.length)
        {
          arrayOfImageView[paramInt1].setImageBitmap(this.platformGridView.bluePoint);
          return;
        }
        arrayOfImageView[paramInt2].setImageBitmap(this.platformGridView.grayPoint);
        paramInt2 += 1;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.theme.classic.PlatformGridView
 * JD-Core Version:    0.6.2
 */