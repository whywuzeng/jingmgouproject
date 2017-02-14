package cn.sharesdk.onekeyshare.theme.skyblue;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.onekeyshare.FollowerListFakeActivity;
import cn.sharesdk.onekeyshare.FollowerListFakeActivity.FollowersResult;
import cn.sharesdk.onekeyshare.FollowerListFakeActivity.Following;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.gui.BitmapProcessor;
import com.mob.tools.gui.PullToRefreshListAdapter;
import com.mob.tools.gui.PullToRefreshView;
import com.mob.tools.utils.R;
import com.mob.tools.utils.UIHandler;
import java.util.ArrayList;
import java.util.HashMap;

public class FollowListPage extends FollowerListFakeActivity
  implements View.OnClickListener, AdapterView.OnItemClickListener
{
  private FollowAdapter adapter;
  private int lastPosition = -1;
  private TitleLayout llTitle;

  public void onClick(View paramView)
  {
    int i;
    int j;
    if (paramView.equals(this.llTitle.getBtnRight()))
    {
      paramView = new ArrayList();
      i = 0;
      j = this.adapter.getCount();
    }
    while (true)
    {
      if (i >= j)
      {
        setResultForChecked(paramView);
        finish();
        return;
      }
      if (this.adapter.getItem(i).checked)
        paramView.add(this.adapter.getItem(i).atName);
      i += 1;
    }
  }

  public void onCreate()
  {
    Object localObject1 = new LinearLayout(getContext());
    ((LinearLayout)localObject1).setBackgroundColor(-657931);
    ((LinearLayout)localObject1).setOrientation(1);
    this.activity.setContentView((View)localObject1);
    this.llTitle = new TitleLayout(getContext());
    int i = R.getBitmapRes(getContext(), "title_back");
    if (i > 0)
      this.llTitle.setBackgroundResource(i);
    this.llTitle.getBtnBack().setOnClickListener(this);
    i = R.getStringRes(getContext(), "multi_share");
    if (i > 0)
      this.llTitle.getTvTitle().setText(i);
    this.llTitle.getBtnRight().setVisibility(0);
    i = R.getStringRes(getContext(), "finish");
    if (i > 0)
      this.llTitle.getBtnRight().setText(i);
    this.llTitle.getBtnRight().setOnClickListener(this);
    this.llTitle.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    ((LinearLayout)localObject1).addView(this.llTitle);
    FrameLayout localFrameLayout = new FrameLayout(getContext());
    Object localObject2 = new LinearLayout.LayoutParams(-1, -2);
    ((LinearLayout.LayoutParams)localObject2).weight = 1.0F;
    localFrameLayout.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((LinearLayout)localObject1).addView(localFrameLayout);
    localObject1 = new PullToRefreshView(getContext());
    ((PullToRefreshView)localObject1).setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    localFrameLayout.addView((View)localObject1);
    this.adapter = new FollowAdapter((PullToRefreshView)localObject1);
    this.adapter.setPlatform(this.platform);
    ((PullToRefreshView)localObject1).setAdapter(this.adapter);
    this.adapter.getListView().setOnItemClickListener(this);
    localObject2 = new ImageView(getContext());
    i = R.getBitmapRes(getContext(), "title_shadow");
    if (i > 0)
      ((ImageView)localObject2).setBackgroundResource(i);
    ((ImageView)localObject2).setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
    localFrameLayout.addView((View)localObject2);
    ((PullToRefreshView)localObject1).performPulling(true);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    boolean bool = false;
    if (isRadioMode(this.platform.getName()))
    {
      if (this.lastPosition >= 0)
        this.adapter.getItem(this.lastPosition).checked = false;
      this.lastPosition = paramInt;
    }
    paramAdapterView = this.adapter.getItem(paramInt);
    if (paramAdapterView.checked);
    while (true)
    {
      paramAdapterView.checked = bool;
      this.adapter.notifyDataSetChanged();
      return;
      bool = true;
    }
  }

  private static class FollowAdapter extends PullToRefreshListAdapter
    implements PlatformActionListener, Handler.Callback
  {
    private static final int FOLLOW_LIST_EMPTY = 2;
    private Bitmap bmChd;
    private Bitmap bmUnch;
    private int curPage = -1;
    private ArrayList<FollowerListFakeActivity.Following> follows = new ArrayList();
    private boolean hasNext = true;
    private FollowListPage.PRTHeader llHeader = new FollowListPage.PRTHeader(getContext());
    private HashMap<String, Boolean> map = new HashMap();
    private Platform platform;

    public FollowAdapter(PullToRefreshView paramPullToRefreshView)
    {
      super();
      int i = R.getBitmapRes(getContext(), "auth_follow_cb_chd");
      if (i > 0)
        this.bmChd = BitmapFactory.decodeResource(paramPullToRefreshView.getResources(), i);
      i = R.getBitmapRes(getContext(), "auth_follow_cb_unc");
      if (i > 0)
        this.bmUnch = BitmapFactory.decodeResource(paramPullToRefreshView.getResources(), i);
    }

    private void next()
    {
      if (this.hasNext)
        this.platform.listFriend(15, this.curPage + 1, null);
    }

    public int getCount()
    {
      if (this.follows == null)
        return 0;
      return this.follows.size();
    }

    public View getHeaderView()
    {
      return this.llHeader;
    }

    public FollowerListFakeActivity.Following getItem(int paramInt)
    {
      return (FollowerListFakeActivity.Following)this.follows.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      boolean bool = "FacebookMessenger".equals(this.platform.getName());
      Object localObject2;
      Object localObject1;
      if (paramView == null)
      {
        localObject2 = new LinearLayout(paramViewGroup.getContext());
        localObject1 = new FollowListPage.FollowListItem(null);
        ((LinearLayout)localObject2).setTag(localObject1);
        paramView = (View)localObject2;
        int i = R.dipToPx(getContext(), 52);
        int j = R.dipToPx(paramViewGroup.getContext(), 10);
        int k = R.dipToPx(paramViewGroup.getContext(), 5);
        if (!bool)
        {
          ((FollowListPage.FollowListItem)localObject1).aivIcon = new AsyncImageView(getContext());
          localObject3 = new LinearLayout.LayoutParams(i, i);
          ((LinearLayout.LayoutParams)localObject3).gravity = 16;
          ((LinearLayout.LayoutParams)localObject3).setMargins(j, k, j, k);
          ((FollowListPage.FollowListItem)localObject1).aivIcon.setLayoutParams((ViewGroup.LayoutParams)localObject3);
          ((LinearLayout)localObject2).addView(((FollowListPage.FollowListItem)localObject1).aivIcon);
        }
        Object localObject3 = new LinearLayout(paramViewGroup.getContext());
        ((LinearLayout)localObject3).setPadding(0, j, j, j);
        ((LinearLayout)localObject3).setOrientation(1);
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams.gravity = 16;
        localLayoutParams.weight = 1.0F;
        ((LinearLayout)localObject3).setLayoutParams(localLayoutParams);
        ((LinearLayout)localObject2).addView((View)localObject3);
        ((FollowListPage.FollowListItem)localObject1).tvName = new TextView(paramViewGroup.getContext());
        ((FollowListPage.FollowListItem)localObject1).tvName.setTextColor(-16777216);
        ((FollowListPage.FollowListItem)localObject1).tvName.setTextSize(1, 18.0F);
        ((FollowListPage.FollowListItem)localObject1).tvName.setSingleLine();
        if (bool)
          ((FollowListPage.FollowListItem)localObject1).tvName.setPadding(j, 0, 0, 0);
        ((LinearLayout)localObject3).addView(((FollowListPage.FollowListItem)localObject1).tvName);
        if (!bool)
        {
          ((FollowListPage.FollowListItem)localObject1).tvSign = new TextView(paramViewGroup.getContext());
          ((FollowListPage.FollowListItem)localObject1).tvSign.setTextColor(2130706432);
          ((FollowListPage.FollowListItem)localObject1).tvSign.setTextSize(1, 14.0F);
          ((FollowListPage.FollowListItem)localObject1).tvSign.setSingleLine();
          ((LinearLayout)localObject3).addView(((FollowListPage.FollowListItem)localObject1).tvSign);
        }
        ((FollowListPage.FollowListItem)localObject1).ivCheck = new ImageView(paramViewGroup.getContext());
        ((FollowListPage.FollowListItem)localObject1).ivCheck.setPadding(0, 0, j, 0);
        paramViewGroup = new LinearLayout.LayoutParams(-2, -2);
        paramViewGroup.gravity = 16;
        ((FollowListPage.FollowListItem)localObject1).ivCheck.setLayoutParams(paramViewGroup);
        ((LinearLayout)localObject2).addView(((FollowListPage.FollowListItem)localObject1).ivCheck);
        paramViewGroup = (ViewGroup)localObject1;
        localObject2 = getItem(paramInt);
        paramViewGroup.tvName.setText(((FollowerListFakeActivity.Following)localObject2).screenName);
        if (!bool)
          paramViewGroup.tvSign.setText(((FollowerListFakeActivity.Following)localObject2).description);
        localObject3 = paramViewGroup.ivCheck;
        if (!((FollowerListFakeActivity.Following)localObject2).checked)
          break label564;
        localObject1 = this.bmChd;
        label486: ((ImageView)localObject3).setImageBitmap((Bitmap)localObject1);
        if (!bool)
        {
          if (!isFling())
            break label585;
          localObject1 = BitmapProcessor.getBitmapFromCache(((FollowerListFakeActivity.Following)localObject2).icon);
          if ((localObject1 == null) || (((Bitmap)localObject1).isRecycled()))
            break label573;
          paramViewGroup.aivIcon.setImageBitmap((Bitmap)localObject1);
        }
      }
      while (true)
      {
        if (paramInt == getCount() - 1)
          next();
        return paramView;
        paramViewGroup = (FollowListPage.FollowListItem)paramView.getTag();
        break;
        label564: localObject1 = this.bmUnch;
        break label486;
        label573: paramViewGroup.aivIcon.execute(null, 0);
        continue;
        label585: paramViewGroup.aivIcon.execute(((FollowerListFakeActivity.Following)localObject2).icon, 0);
      }
    }

    public boolean handleMessage(Message paramMessage)
    {
      if (paramMessage.what < 0)
        ((Activity)getContext()).finish();
      while (true)
      {
        return false;
        if (paramMessage.what == 2)
        {
          notifyDataSetChanged();
        }
        else
        {
          if (this.curPage <= 0)
            this.follows.clear();
          paramMessage = (ArrayList)paramMessage.obj;
          this.follows.addAll(paramMessage);
          notifyDataSetChanged();
        }
      }
    }

    public void onCancel(Platform paramPlatform, int paramInt)
    {
      UIHandler.sendEmptyMessage(-1, this);
    }

    public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
    {
      paramPlatform = FollowListPage.parseFollowers(this.platform.getName(), paramHashMap, this.map);
      if (paramPlatform == null)
        UIHandler.sendEmptyMessage(2, this);
      do
      {
        return;
        this.hasNext = paramPlatform.hasNextPage;
      }
      while ((paramPlatform.list == null) || (paramPlatform.list.size() <= 0));
      this.curPage += 1;
      paramHashMap = new Message();
      paramHashMap.what = 1;
      paramHashMap.obj = paramPlatform.list;
      UIHandler.sendMessage(paramHashMap, this);
    }

    public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
    {
      paramThrowable.printStackTrace();
    }

    public void onPullDown(int paramInt)
    {
      this.llHeader.onPullDown(paramInt);
    }

    public void onRequest()
    {
      this.llHeader.onRequest();
      this.curPage = -1;
      this.hasNext = true;
      this.map.clear();
      next();
    }

    public void onReversed()
    {
      super.onReversed();
      this.llHeader.reverse();
    }

    public void setPlatform(Platform paramPlatform)
    {
      this.platform = paramPlatform;
      paramPlatform.setPlatformActionListener(this);
    }
  }

  private static class FollowListItem
  {
    public AsyncImageView aivIcon;
    public ImageView ivCheck;
    public TextView tvName;
    public TextView tvSign;
  }

  private static class PRTHeader extends LinearLayout
  {
    private FollowListPage.RotateImageView ivArrow;
    private ProgressBar pbRefreshing;
    private TextView tvHeader;

    public PRTHeader(Context paramContext)
    {
      super();
      setOrientation(1);
      LinearLayout localLinearLayout = new LinearLayout(paramContext);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams.gravity = 1;
      addView(localLinearLayout, localLayoutParams);
      this.ivArrow = new FollowListPage.RotateImageView(paramContext);
      int i = R.getBitmapRes(paramContext, "ssdk_oks_ptr_ptr");
      if (i > 0)
        this.ivArrow.setImageResource(i);
      i = R.dipToPx(paramContext, 32);
      localLayoutParams = new LinearLayout.LayoutParams(i, i);
      localLayoutParams.gravity = 16;
      localLinearLayout.addView(this.ivArrow, localLayoutParams);
      this.pbRefreshing = new ProgressBar(paramContext);
      localLinearLayout.addView(this.pbRefreshing, localLayoutParams);
      this.pbRefreshing.setVisibility(8);
      this.tvHeader = new TextView(getContext());
      this.tvHeader.setTextSize(1, 18.0F);
      this.tvHeader.setGravity(17);
      i = R.dipToPx(getContext(), 10);
      this.tvHeader.setPadding(i, i, i, i);
      this.tvHeader.setTextColor(-16777216);
      paramContext = new LinearLayout.LayoutParams(-2, -2);
      paramContext.gravity = 16;
      localLinearLayout.addView(this.tvHeader, paramContext);
    }

    public void onPullDown(int paramInt)
    {
      if (paramInt > 100)
      {
        int j = (paramInt - 100) * 180 / 20;
        int i = j;
        if (j > 180)
          i = 180;
        j = i;
        if (i < 0)
          j = 0;
        this.ivArrow.setRotation(j);
        if (paramInt >= 100)
          break label87;
        paramInt = R.getStringRes(getContext(), "pull_to_refresh");
        if (paramInt > 0)
          this.tvHeader.setText(paramInt);
      }
      label87: 
      do
      {
        return;
        this.ivArrow.setRotation(0);
        break;
        paramInt = R.getStringRes(getContext(), "release_to_refresh");
      }
      while (paramInt <= 0);
      this.tvHeader.setText(paramInt);
    }

    public void onRequest()
    {
      this.ivArrow.setVisibility(8);
      this.pbRefreshing.setVisibility(0);
      int i = R.getStringRes(getContext(), "refreshing");
      if (i > 0)
        this.tvHeader.setText(i);
    }

    public void reverse()
    {
      this.pbRefreshing.setVisibility(8);
      this.ivArrow.setRotation(180);
      this.ivArrow.setVisibility(0);
    }
  }

  private static class RotateImageView extends ImageView
  {
    private int rotation;

    public RotateImageView(Context paramContext)
    {
      super();
    }

    protected void onDraw(Canvas paramCanvas)
    {
      paramCanvas.rotate(this.rotation, getWidth() / 2, getHeight() / 2);
      super.onDraw(paramCanvas);
    }

    public void setRotation(int paramInt)
    {
      this.rotation = paramInt;
      invalidate();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.theme.skyblue.FollowListPage
 * JD-Core Version:    0.6.2
 */