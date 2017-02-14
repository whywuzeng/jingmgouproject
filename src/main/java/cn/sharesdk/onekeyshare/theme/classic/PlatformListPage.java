package cn.sharesdk.onekeyshare.theme.classic;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.sharesdk.onekeyshare.PlatformListFakeActivity;
import com.mob.tools.utils.R;
import java.util.ArrayList;

public class PlatformListPage extends PlatformListFakeActivity
  implements View.OnClickListener
{
  private Animation animHide;
  private Animation animShow;
  private Button btnCancel;
  private boolean finishing;
  private FrameLayout flPage;
  private PlatformGridView grid;
  private LinearLayout llPage;

  private void initAnim()
  {
    this.animShow = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 1.0F, 1, 0.0F);
    this.animShow.setDuration(300L);
    this.animHide = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
    this.animHide.setDuration(300L);
  }

  private void initPageView()
  {
    this.flPage = new FrameLayout(getContext());
    this.flPage.setOnClickListener(this);
    this.flPage.setBackgroundDrawable(new ColorDrawable(1426063360));
    this.llPage = new LinearLayout(getContext())
    {
      public boolean onTouchEvent(MotionEvent paramAnonymousMotionEvent)
      {
        return true;
      }
    };
    this.llPage.setOrientation(1);
    this.llPage.setBackgroundDrawable(new ColorDrawable(-1));
    Object localObject = new FrameLayout.LayoutParams(-1, -2);
    ((FrameLayout.LayoutParams)localObject).gravity = 80;
    this.llPage.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.flPage.addView(this.llPage);
    this.grid = new PlatformGridView(getContext());
    this.grid.setEditPageBackground(getBackgroundView());
    localObject = new LinearLayout.LayoutParams(-1, -2);
    this.grid.setLayoutParams((ViewGroup.LayoutParams)localObject);
    this.llPage.addView(this.grid);
    this.btnCancel = new Button(getContext());
    this.btnCancel.setTextColor(-12950017);
    this.btnCancel.setTextSize(1, 20.0F);
    int i = R.getStringRes(getContext(), "cancel");
    if (i > 0)
      this.btnCancel.setText(i);
    this.btnCancel.setPadding(0, 0, 0, R.dipToPx(getContext(), 5));
    i = R.getBitmapRes(getContext(), "classic_platform_corners_bg");
    if (i > 0)
      this.btnCancel.setBackgroundResource(i);
    while (true)
    {
      localObject = new LinearLayout.LayoutParams(-1, R.dipToPx(getContext(), 45));
      i = R.dipToPx(getContext(), 10);
      ((LinearLayout.LayoutParams)localObject).setMargins(i, i, i, i);
      this.btnCancel.setLayoutParams((ViewGroup.LayoutParams)localObject);
      this.llPage.addView(this.btnCancel);
      return;
      this.btnCancel.setBackgroundDrawable(new ColorDrawable(-1));
    }
  }

  public void onClick(View paramView)
  {
    if ((paramView.equals(this.flPage)) || (paramView.equals(this.btnCancel)))
    {
      setCanceled(true);
      finish();
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.grid != null)
      this.grid.onConfigurationChanged();
  }

  public void onCreate()
  {
    super.onCreate();
    this.finishing = false;
    initPageView();
    initAnim();
    this.activity.setContentView(this.flPage);
    this.grid.setData(this.shareParamsMap, this.silent);
    this.grid.setHiddenPlatforms(this.hiddenPlatforms);
    this.grid.setCustomerLogos(this.customerLogos);
    this.grid.setParent(this);
    this.btnCancel.setOnClickListener(this);
    this.llPage.clearAnimation();
    this.llPage.startAnimation(this.animShow);
  }

  public boolean onFinish()
  {
    if (this.finishing)
      return super.onFinish();
    if (this.animHide == null)
    {
      this.finishing = true;
      return false;
    }
    this.finishing = true;
    this.animHide.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        PlatformListPage.this.flPage.setVisibility(8);
        PlatformListPage.this.finish();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    this.llPage.clearAnimation();
    this.llPage.startAnimation(this.animHide);
    return true;
  }

  public void onPlatformIconClick(View paramView, ArrayList<Object> paramArrayList)
  {
    onShareButtonClick(paramView, paramArrayList);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.theme.classic.PlatformListPage
 * JD-Core Version:    0.6.2
 */