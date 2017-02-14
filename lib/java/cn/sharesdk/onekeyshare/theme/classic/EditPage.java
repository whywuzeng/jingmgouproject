package cn.sharesdk.onekeyshare.theme.classic;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.onekeyshare.EditPageFakeActivity;
import cn.sharesdk.onekeyshare.EditPageFakeActivity.ImageInfo;
import cn.sharesdk.onekeyshare.EditPageFakeActivity.ImageListResultsCallback;
import cn.sharesdk.onekeyshare.PicViewer;
import cn.sharesdk.onekeyshare.ShareCore;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.R;
import com.mob.tools.utils.UIHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EditPage extends EditPageFakeActivity
  implements View.OnClickListener, TextWatcher
{
  private static final int DIM_COLOR = 2133996082;
  private static final int MAX_TEXT_COUNT = 140;
  private Drawable background;
  private EditText etContent;
  private Bitmap image;
  private EditPageFakeActivity.ImageInfo imgInfo;
  private ImageView ivImage;
  private ImageView ivPin;
  private LinearLayout llBody;
  private LinearLayout llPlat;
  private TitleLayout llTitle;
  private Platform[] platformList;
  private ProgressBar progressBar;
  private RelativeLayout rlPage;
  private RelativeLayout rlThumb;
  private TextView tvCounter;
  private View[] views;

  private void genBackground()
  {
    this.background = new ColorDrawable(2133996082);
    if (this.backgroundView != null);
    try
    {
      Bitmap localBitmap = BitmapHelper.blur(BitmapHelper.captureView(this.backgroundView, this.backgroundView.getWidth(), this.backgroundView.getHeight()), 20, 8);
      this.background = new LayerDrawable(new Drawable[] { new BitmapDrawable(this.activity.getResources(), localBitmap), this.background });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }

  private LinearLayout getAtLine(String paramString)
  {
    if (!isShowAtUserLayout(paramString))
      return null;
    LinearLayout localLinearLayout = new LinearLayout(getContext());
    Object localObject = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject).rightMargin = R.dipToPx(getContext(), 4);
    ((LinearLayout.LayoutParams)localObject).gravity = 83;
    ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
    localLinearLayout.setLayoutParams((ViewGroup.LayoutParams)localObject);
    localLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new FollowListPage();
        paramAnonymousView.setPlatform((Platform)EditPage.this.platforms.get(0));
        paramAnonymousView.showForResult(EditPage.this.activity, null, EditPage.this);
      }
    });
    localObject = new TextView(getContext());
    int i = R.getBitmapRes(this.activity, "btn_back_nor");
    if (i > 0)
      ((TextView)localObject).setBackgroundResource(i);
    i = R.dipToPx(getContext(), 32);
    ((TextView)localObject).setLayoutParams(new LinearLayout.LayoutParams(i, i));
    ((TextView)localObject).setTextSize(1, 18.0F);
    ((TextView)localObject).setText(getAtUserButtonText(paramString));
    ((TextView)localObject).setPadding(0, 0, 0, R.dipToPx(getContext(), 2));
    ((TextView)localObject).setTypeface(Typeface.DEFAULT_BOLD);
    ((TextView)localObject).setTextColor(-16777216);
    ((TextView)localObject).setGravity(17);
    localLinearLayout.addView((View)localObject);
    localObject = new TextView(getContext());
    ((TextView)localObject).setTextSize(1, 18.0F);
    ((TextView)localObject).setTextColor(-16777216);
    i = R.getStringRes(this.activity, "list_friends");
    ((TextView)localObject).setText(getContext().getString(i, new Object[] { getName(paramString) }));
    paramString = new LinearLayout.LayoutParams(-2, -2);
    paramString.gravity = 16;
    ((TextView)localObject).setLayoutParams(paramString);
    localLinearLayout.addView((View)localObject);
    return localLinearLayout;
  }

  private LinearLayout getBodyBottom()
  {
    LinearLayout localLinearLayout = new LinearLayout(getContext());
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    Object localObject = getAtLine(((Platform)this.platforms.get(0)).getName());
    if (localObject != null)
      localLinearLayout.addView((View)localObject);
    this.tvCounter = new TextView(getContext());
    this.tvCounter.setText(String.valueOf(140));
    this.tvCounter.setTextColor(-3158065);
    this.tvCounter.setTextSize(1, 18.0F);
    this.tvCounter.setTypeface(Typeface.DEFAULT_BOLD);
    localObject = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject).gravity = 16;
    this.tvCounter.setLayoutParams((ViewGroup.LayoutParams)localObject);
    localLinearLayout.addView(this.tvCounter);
    return localLinearLayout;
  }

  private ImageView getImagePin()
  {
    this.ivPin = new ImageView(getContext());
    int i = R.getBitmapRes(this.activity, "pin");
    if (i > 0)
      this.ivPin.setImageResource(i);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(R.dipToPx(getContext(), 80), R.dipToPx(getContext(), 36));
    localLayoutParams.topMargin = R.dipToPx(getContext(), 6);
    localLayoutParams.addRule(6, this.llBody.getId());
    localLayoutParams.addRule(11);
    this.ivPin.setLayoutParams(localLayoutParams);
    this.ivPin.setVisibility(8);
    return this.ivPin;
  }

  private LinearLayout getMainBody()
  {
    LinearLayout localLinearLayout = new LinearLayout(getContext());
    localLinearLayout.setOrientation(1);
    Object localObject = new LinearLayout.LayoutParams(-1, -2);
    ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
    int i = R.dipToPx(getContext(), 4);
    ((LinearLayout.LayoutParams)localObject).setMargins(i, i, i, i);
    localLinearLayout.setLayoutParams((ViewGroup.LayoutParams)localObject);
    localObject = new LinearLayout(getContext());
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams.weight = 1.0F;
    localLinearLayout.addView((View)localObject, localLayoutParams);
    this.etContent = new EditText(getContext());
    this.etContent.setGravity(51);
    this.etContent.setBackgroundDrawable(null);
    this.etContent.setText(String.valueOf(this.shareParamMap.get("text")));
    this.etContent.addTextChangedListener(this);
    localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.weight = 1.0F;
    this.etContent.setLayoutParams(localLayoutParams);
    ((LinearLayout)localObject).addView(this.etContent);
    ((LinearLayout)localObject).addView(getThumbView());
    localLinearLayout.addView(getBodyBottom());
    return localLinearLayout;
  }

  private String getName(String paramString)
  {
    if (paramString == null)
      return "";
    int i = R.getStringRes(getContext(), paramString.toLowerCase());
    return getContext().getString(i);
  }

  private LinearLayout getPageBody()
  {
    this.llBody = new LinearLayout(getContext());
    this.llBody.setId(2);
    int i = R.getBitmapRes(this.activity, "edittext_back");
    if (i > 0)
      this.llBody.setBackgroundResource(i);
    this.llBody.setOrientation(1);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(5, this.llTitle.getId());
    localLayoutParams.addRule(3, this.llTitle.getId());
    localLayoutParams.addRule(7, this.llTitle.getId());
    if (!this.dialogMode)
      localLayoutParams.addRule(12);
    i = R.dipToPx(getContext(), 3);
    localLayoutParams.setMargins(i, i, i, i);
    this.llBody.setLayoutParams(localLayoutParams);
    this.llBody.addView(getMainBody());
    this.llBody.addView(getSep());
    this.llBody.addView(getPlatformList());
    return this.llBody;
  }

  private TitleLayout getPageTitle()
  {
    this.llTitle = new TitleLayout(getContext());
    this.llTitle.setId(1);
    this.llTitle.setBackgroundColor(-16777216);
    this.llTitle.getBtnBack().setOnClickListener(this);
    int i = R.getStringRes(this.activity, "multi_share");
    if (i > 0)
      this.llTitle.getTvTitle().setText(i);
    this.llTitle.getBtnRight().setVisibility(0);
    i = R.getStringRes(this.activity, "share");
    if (i > 0)
      this.llTitle.getBtnRight().setText(i);
    this.llTitle.getBtnRight().setOnClickListener(this);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(9);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    this.llTitle.setLayoutParams(localLayoutParams);
    return this.llTitle;
  }

  private RelativeLayout getPageView()
  {
    this.rlPage = new RelativeLayout(getContext());
    this.rlPage.setBackgroundDrawable(this.background);
    if (this.dialogMode)
    {
      RelativeLayout localRelativeLayout = new RelativeLayout(getContext());
      localRelativeLayout.setBackgroundColor(-1070452174);
      int i = R.dipToPx(getContext(), 8);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(R.getScreenWidth(getContext()) - i * 2, -2);
      localLayoutParams.topMargin = i;
      localLayoutParams.bottomMargin = i;
      localLayoutParams.addRule(13);
      localRelativeLayout.setLayoutParams(localLayoutParams);
      this.rlPage.addView(localRelativeLayout);
      localRelativeLayout.addView(getPageTitle());
      localRelativeLayout.addView(getPageBody());
      localRelativeLayout.addView(getImagePin());
    }
    while (true)
    {
      return this.rlPage;
      this.rlPage.addView(getPageTitle());
      this.rlPage.addView(getPageBody());
      this.rlPage.addView(getImagePin());
    }
  }

  private Bitmap getPlatLogo(Platform paramPlatform)
  {
    if (paramPlatform == null);
    int i;
    do
    {
      do
        return null;
      while (paramPlatform.getName() == null);
      paramPlatform = "logo_" + paramPlatform.getName();
      i = R.getBitmapRes(this.activity, paramPlatform.toLowerCase());
    }
    while (i <= 0);
    return BitmapFactory.decodeResource(this.activity.getResources(), i);
  }

  private LinearLayout getPlatformList()
  {
    LinearLayout localLinearLayout = new LinearLayout(getContext());
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    Object localObject = new TextView(getContext());
    int i = R.getStringRes(this.activity, "share_to");
    if (i > 0)
      ((TextView)localObject).setText(i);
    ((TextView)localObject).setTextColor(-3158065);
    ((TextView)localObject).setTextSize(1, 18.0F);
    i = R.dipToPx(getContext(), 9);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 16;
    localLayoutParams.setMargins(i, 0, 0, 0);
    ((TextView)localObject).setLayoutParams(localLayoutParams);
    localLinearLayout.addView((View)localObject);
    localObject = new HorizontalScrollView(getContext());
    ((HorizontalScrollView)localObject).setHorizontalScrollBarEnabled(false);
    ((HorizontalScrollView)localObject).setHorizontalFadingEdgeEnabled(false);
    localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(i, i, i, i);
    ((HorizontalScrollView)localObject).setLayoutParams(localLayoutParams);
    localLinearLayout.addView((View)localObject);
    this.llPlat = new LinearLayout(getContext());
    this.llPlat.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
    ((HorizontalScrollView)localObject).addView(this.llPlat);
    return localLinearLayout;
  }

  private View getSep()
  {
    View localView = new View(getContext());
    localView.setBackgroundColor(-16777216);
    localView.setLayoutParams(new LinearLayout.LayoutParams(-1, R.dipToPx(getContext(), 1)));
    return localView;
  }

  private RelativeLayout getThumbView()
  {
    this.rlThumb = new RelativeLayout(getContext());
    this.rlThumb.setId(1);
    Object localObject = new LinearLayout.LayoutParams(R.dipToPx(getContext(), 82), R.dipToPx(getContext(), 98));
    this.rlThumb.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.ivImage = new ImageView(getContext());
    int i = R.getBitmapRes(this.activity, "btn_back_nor");
    if (i > 0)
      this.ivImage.setBackgroundResource(i);
    this.ivImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    this.ivImage.setImageBitmap(this.image);
    i = R.dipToPx(getContext(), 4);
    this.ivImage.setPadding(i, i, i, i);
    i = R.dipToPx(getContext(), 74);
    localObject = new RelativeLayout.LayoutParams(i, i);
    int j = R.dipToPx(getContext(), 16);
    int k = R.dipToPx(getContext(), 8);
    ((RelativeLayout.LayoutParams)localObject).setMargins(0, j, k, 0);
    this.ivImage.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.ivImage.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((EditPage.this.image != null) && (!EditPage.this.image.isRecycled()))
        {
          paramAnonymousView = new PicViewer();
          paramAnonymousView.setImageBitmap(EditPage.this.image);
          paramAnonymousView.show(EditPage.this.activity, null);
        }
      }
    });
    this.rlThumb.addView(this.ivImage);
    int m = R.dipToPx(getContext(), 24);
    this.progressBar = new ProgressBar(getContext());
    this.progressBar.setPadding(m, m, m, m);
    localObject = new RelativeLayout.LayoutParams(i, i);
    ((RelativeLayout.LayoutParams)localObject).setMargins(0, j, k, 0);
    this.progressBar.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.rlThumb.addView(this.progressBar);
    localObject = new Button(getContext());
    ((Button)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EditPage.this.rlThumb.setVisibility(8);
        EditPage.this.ivPin.setVisibility(8);
        EditPage.this.removeImage(EditPage.this.imgInfo);
      }
    });
    i = R.getBitmapRes(this.activity, "img_cancel");
    if (i > 0)
      ((Button)localObject).setBackgroundResource(i);
    i = R.dipToPx(getContext(), 20);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(i, i);
    localLayoutParams.addRule(11);
    localLayoutParams.addRule(12);
    ((Button)localObject).setLayoutParams(localLayoutParams);
    this.rlThumb.addView((View)localObject);
    if (!haveImage())
      this.rlThumb.setVisibility(8);
    return this.rlThumb;
  }

  private void hideSoftInput()
  {
    try
    {
      ((InputMethodManager)this.activity.getSystemService("input_method")).hideSoftInputFromWindow(this.etContent.getWindowToken(), 0);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }

  private void showThumb()
  {
    initImageList(new EditPageFakeActivity.ImageListResultsCallback()
    {
      public void onFinish(ArrayList<EditPageFakeActivity.ImageInfo> paramAnonymousArrayList)
      {
        if ((paramAnonymousArrayList == null) || (paramAnonymousArrayList.size() == 0))
          return;
        EditPage.this.imgInfo = ((EditPageFakeActivity.ImageInfo)paramAnonymousArrayList.get(0));
        EditPage.this.image = EditPage.this.imgInfo.bitmap;
        EditPage.this.rlThumb.setVisibility(0);
        EditPage.this.ivPin.setVisibility(0);
        EditPage.this.progressBar.setVisibility(8);
        EditPage.this.ivImage.setImageBitmap(EditPage.this.image);
      }
    });
  }

  public void afterPlatformListGot()
  {
    if (this.platformList == null);
    final int m;
    LinearLayout.LayoutParams localLayoutParams;
    FrameLayout.LayoutParams localLayoutParams1;
    final int k;
    int i;
    for (int j = 0; ; j = this.platformList.length)
    {
      this.views = new View[j];
      m = R.dipToPx(getContext(), 24);
      localLayoutParams = new LinearLayout.LayoutParams(m, m);
      final int n = R.dipToPx(getContext(), 9);
      localLayoutParams.setMargins(0, 0, n, 0);
      localLayoutParams1 = new FrameLayout.LayoutParams(-1, -1);
      localLayoutParams1.gravity = 51;
      k = 0;
      i = 0;
      if (i < j)
        break;
      UIHandler.sendEmptyMessageDelayed(0, 333L, new Handler.Callback()
      {
        public boolean handleMessage(Message paramAnonymousMessage)
        {
          ((HorizontalScrollView)EditPage.this.llPlat.getParent()).scrollTo(k * (m + n), 0);
          return false;
        }
      });
      return;
    }
    FrameLayout localFrameLayout = new FrameLayout(getContext());
    localFrameLayout.setLayoutParams(localLayoutParams);
    if (i >= j - 1)
      localFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(m, m));
    this.llPlat.addView(localFrameLayout);
    localFrameLayout.setOnClickListener(this);
    Object localObject = new ImageView(getContext());
    ((ImageView)localObject).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    ((ImageView)localObject).setImageBitmap(getPlatLogo(this.platformList[i]));
    ((ImageView)localObject).setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    localFrameLayout.addView((View)localObject);
    this.views[i] = new View(getContext());
    this.views[i].setBackgroundColor(-805306369);
    this.views[i].setOnClickListener(this);
    localObject = this.platformList[i].getName();
    Iterator localIterator = this.platforms.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.views[i].setLayoutParams(localLayoutParams1);
        localFrameLayout.addView(this.views[i]);
        i += 1;
        break;
      }
      if (((String)localObject).equals(((Platform)localIterator.next()).getName()))
      {
        this.views[i].setVisibility(4);
        k = i;
      }
    }
  }

  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onClick(View paramView)
  {
    int i;
    if (paramView.equals(this.llTitle.getBtnBack()))
    {
      paramView = null;
      i = 0;
      if (i >= this.views.length)
      {
        label27: if (paramView != null)
          ShareSDK.logDemoEvent(5, paramView);
        finish();
      }
    }
    do
    {
      return;
      if (this.views[i].getVisibility() == 4)
      {
        paramView = this.platformList[i];
        break label27;
      }
      i += 1;
      break;
      if (!paramView.equals(this.llTitle.getBtnRight()))
        break label210;
      paramView = this.etContent.getText().toString();
      this.shareParamMap.put("text", paramView);
      this.platforms.clear();
      i = 0;
      while (true)
      {
        if (i >= this.views.length)
        {
          if (this.platforms.size() <= 0)
            break;
          setResultAndFinish();
          return;
        }
        if (this.views[i].getVisibility() != 0)
          this.platforms.add(this.platformList[i]);
        i += 1;
      }
      i = R.getStringRes(this.activity, "select_one_plat_at_least");
    }
    while (i <= 0);
    Toast.makeText(getContext(), i, 0).show();
    return;
    label210: if ((paramView instanceof FrameLayout))
    {
      ((FrameLayout)paramView).getChildAt(1).performClick();
      return;
    }
    if (paramView.getVisibility() == 4)
    {
      paramView.setVisibility(0);
      return;
    }
    paramView.setVisibility(4);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.activity.getResources().getConfiguration().orientation == 2)
    {
      hideSoftInput();
      this.activity.getWindow().setSoftInputMode(35);
      this.rlPage.setBackgroundColor(2133996082);
      this.rlPage.postDelayed(new Runnable()
      {
        public void run()
        {
          EditPage.this.genBackground();
          EditPage.this.rlPage.setBackgroundDrawable(EditPage.this.background);
        }
      }
      , 1000L);
      return;
    }
    hideSoftInput();
    this.activity.getWindow().setSoftInputMode(37);
    this.rlPage.setBackgroundColor(2133996082);
    this.rlPage.postDelayed(new Runnable()
    {
      public void run()
      {
        EditPage.this.genBackground();
        EditPage.this.rlPage.setBackgroundDrawable(EditPage.this.background);
      }
    }
    , 1000L);
  }

  public void onCreate()
  {
    if ((this.shareParamMap == null) || (this.platforms == null) || (this.platforms.size() < 1))
    {
      finish();
      return;
    }
    genBackground();
    this.activity.setContentView(getPageView());
    onTextChanged(this.etContent.getText(), 0, this.etContent.length(), 0);
    showThumb();
    new Thread()
    {
      public void run()
      {
        while (true)
        {
          Platform[] arrayOfPlatform;
          int i;
          try
          {
            EditPage.this.platformList = ShareSDK.getPlatformList();
            if (EditPage.this.platformList == null)
              return;
            ArrayList localArrayList = new ArrayList();
            arrayOfPlatform = EditPage.this.platformList;
            int j = arrayOfPlatform.length;
            i = 0;
            if (i >= j)
            {
              EditPage.this.platformList = new Platform[localArrayList.size()];
              i = 0;
              if (i < EditPage.this.platformList.length)
                break label139;
              UIHandler.sendEmptyMessage(1, new Handler.Callback()
              {
                public boolean handleMessage(Message paramAnonymous2Message)
                {
                  EditPage.this.afterPlatformListGot();
                  return false;
                }
              });
              return;
            }
          }
          catch (Throwable localThrowable)
          {
            localThrowable.printStackTrace();
            return;
          }
          Platform localPlatform = arrayOfPlatform[i];
          String str = localPlatform.getName();
          if ((!(localPlatform instanceof CustomPlatform)) && (!ShareCore.isUseClientToShare(str)))
          {
            localThrowable.add(localPlatform);
            break label163;
            label139: EditPage.this.platformList[i] = ((Platform)localThrowable.get(i));
            i += 1;
          }
          else
          {
            label163: i += 1;
          }
        }
      }
    }
    .start();
  }

  public boolean onFinish()
  {
    hideSoftInput();
    return super.onFinish();
  }

  public void onResult(HashMap<String, Object> paramHashMap)
  {
    paramHashMap = getJoinSelectedUser(paramHashMap);
    if (paramHashMap != null)
      this.etContent.append(paramHashMap);
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 = 140 - this.etContent.length();
    this.tvCounter.setText(String.valueOf(paramInt1));
    paramCharSequence = this.tvCounter;
    if (paramInt1 > 0);
    for (paramInt1 = -3158065; ; paramInt1 = -65536)
    {
      paramCharSequence.setTextColor(paramInt1);
      return;
    }
  }

  public void setActivity(Activity paramActivity)
  {
    super.setActivity(paramActivity);
    Window localWindow = paramActivity.getWindow();
    if (paramActivity.getResources().getConfiguration().orientation == 2)
    {
      localWindow.setSoftInputMode(35);
      return;
    }
    localWindow.setSoftInputMode(37);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.theme.classic.EditPage
 * JD-Core Version:    0.6.2
 */