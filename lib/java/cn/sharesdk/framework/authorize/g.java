package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.TitleLayout;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;

public class g extends a
  implements Handler.Callback, ResizeLayout.OnResizeListener
{
  protected AuthorizeListener b;
  private AuthorizeAdapter c;
  private RegisterView d;
  private WebView e;

  private AuthorizeAdapter c()
  {
    try
    {
      ActivityInfo localActivityInfo = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128);
      if ((localActivityInfo.metaData != null) && (!localActivityInfo.metaData.isEmpty()))
      {
        String str = localActivityInfo.metaData.getString("AuthorizeAdapter");
        if (str != null)
        {
          localObject = str;
          if (str.length() > 0);
        }
        else
        {
          str = localActivityInfo.metaData.getString("Adapter");
          if (str == null)
            break label124;
          localObject = str;
          if (str.length() <= 0)
            break label124;
        }
        Object localObject = Class.forName((String)localObject).newInstance();
        if (!(localObject instanceof AuthorizeAdapter))
          return null;
        localObject = (AuthorizeAdapter)localObject;
        return localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
      return null;
    }
    return null;
    label124: return null;
  }

  public void OnResize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.c != null)
      this.c.onResize(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void a(AuthorizeListener paramAuthorizeListener)
  {
    this.b = paramAuthorizeListener;
  }

  protected RegisterView b()
  {
    RegisterView localRegisterView = new RegisterView(this.activity);
    localRegisterView.a().setOnClickListener(new h(this));
    this.e = localRegisterView.b();
    Object localObject = this.e.getSettings();
    ((WebSettings)localObject).setBuiltInZoomControls(true);
    ((WebSettings)localObject).setJavaScriptEnabled(true);
    this.e.setVerticalScrollBarEnabled(false);
    this.e.setHorizontalScrollBarEnabled(false);
    localObject = this.a.getAuthorizeWebviewClient(this);
    this.e.setWebViewClient((WebViewClient)localObject);
    new j(this).start();
    return localRegisterView;
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 2:
    }
    while (true)
    {
      return false;
      if (paramMessage.arg1 == 1)
      {
        paramMessage = this.a.getAuthorizeListener();
        if (paramMessage != null)
          paramMessage.onError(new Throwable("Network error (platform: " + this.a.getPlatform().getName() + ")"));
      }
      else
      {
        paramMessage = (String)paramMessage.obj;
        if (TextUtils.isEmpty(paramMessage))
        {
          finish();
          paramMessage = this.a.getAuthorizeListener();
          if (paramMessage != null)
            paramMessage.onError(new Throwable("Authorize URL is empty (platform: " + this.a.getPlatform().getName() + ")"));
        }
        else
        {
          this.e.loadUrl(paramMessage);
        }
      }
    }
  }

  public void onCreate()
  {
    Object localObject;
    String str;
    if (this.d == null)
    {
      this.d = b();
      this.d.a(this);
      this.d.a(this.c.isNotitle());
      this.c.setBodyView(this.d.d());
      this.c.setWebView(this.d.b());
      localObject = this.d.c();
      this.c.setTitleView((TitleLayout)localObject);
      str = this.a.getPlatform().getName();
      this.c.setPlatformName(this.a.getPlatform().getName());
    }
    try
    {
      i = R.getStringRes(getContext(), str);
      ((TitleLayout)localObject).getTvTitle().setText(i);
      this.c.onCreate();
      if ((this.c != null) && (!this.c.isPopUpAnimationDisable()))
      {
        localObject = new ScaleAnimation(0.0F, 1.0F, 0.0F, 1.0F, 1, 0.5F, 1, 0.5F);
        ((ScaleAnimation)localObject).setDuration(550L);
        ((ScaleAnimation)localObject).setInterpolator(new a(null));
        this.d.setAnimation((Animation)localObject);
      }
      this.activity.setContentView(this.d);
      return;
    }
    catch (Throwable localThrowable2)
    {
      while (true)
        try
        {
          int i = R.getStringRes(getContext(), "weibo_oauth_regiseter");
          ((TitleLayout)localObject).getTvTitle().setText(i);
        }
        catch (Throwable localThrowable1)
        {
          Ln.e(localThrowable2);
        }
    }
  }

  public void onDestroy()
  {
    if (this.c != null)
      this.c.onDestroy();
  }

  public boolean onFinish()
  {
    if (this.c != null)
      return this.c.onFinish();
    return super.onFinish();
  }

  public boolean onKeyEvent(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    if (this.c != null)
      bool = this.c.onKeyEvent(paramInt, paramKeyEvent);
    if ((!bool) && (paramInt == 4) && (paramKeyEvent.getAction() == 0))
    {
      AuthorizeListener localAuthorizeListener = this.a.getAuthorizeListener();
      if (localAuthorizeListener != null)
        localAuthorizeListener.onCancel();
    }
    if (bool)
      return true;
    return super.onKeyEvent(paramInt, paramKeyEvent);
  }

  public void onPause()
  {
    if (this.c != null)
      this.c.onPause();
  }

  public void onRestart()
  {
    if (this.c != null)
      this.c.onRestart();
  }

  public void onResume()
  {
    if (this.c != null)
      this.c.onResume();
  }

  public void onStart()
  {
    if (this.c != null)
      this.c.onStart();
  }

  public void onStop()
  {
    if (this.c != null)
      this.c.onStop();
  }

  public void setActivity(Activity paramActivity)
  {
    super.setActivity(paramActivity);
    if (this.c == null)
    {
      this.c = c();
      if (this.c == null)
        this.c = new AuthorizeAdapter();
    }
    this.c.setActivity(paramActivity);
  }

  private static class a
    implements Interpolator
  {
    private float[] a = { 0.0F, 0.0269268F, 0.05384702F, 0.08075392F, 0.1076409F, 0.1345013F, 0.1613285F, 0.188116F, 0.214857F, 0.241545F, 0.2681734F, 0.2947356F, 0.3212251F, 0.3476354F, 0.3739598F, 0.4001921F, 0.4263255F, 0.4523538F, 0.4782705F, 0.5040692F, 0.5297434F, 0.555287F, 0.580694F, 0.6059569F, 0.6310707F, 0.6560288F, 0.6808249F, 0.7054532F, 0.7299073F, 0.7541814F, 0.7782694F, 0.8021654F, 0.8258634F, 0.8493577F, 0.872642F, 0.8957118F, 0.9185602F, 0.941182F, 0.9635715F, 0.9857233F, 1.007632F, 1.029292F, 1.050698F, 1.071845F, 1.092727F, 1.11334F, 1.133678F, 1.153736F, 1.173509F, 1.192993F, 1.18934F, 1.172811F, 1.156547F, 1.140553F, 1.124833F, 1.109391F, 1.09423F, 1.079354F, 1.064768F, 1.050473F, 1.036475F, 1.022775F, 1.009379F, 0.996289F, 0.9835081F, 0.9710398F, 0.958887F, 0.9470527F, 0.93554F, 0.9243516F, 0.9134902F, 0.9029586F, 0.9048271F, 0.911403F, 0.9177547F, 0.92388F, 0.9297765F, 0.9354441F, 0.9408808F, 0.9460853F, 0.9510565F, 0.955793F, 0.9602937F, 0.9645574F, 0.9685832F, 0.9723699F, 0.9759167F, 0.9792228F, 0.9822872F, 0.9851093F, 0.9876884F, 0.9900237F, 0.9921147F, 0.993961F, 0.995562F, 0.9969173F, 0.9980267F, 0.9988899F, 0.9995065F, 0.9998766F, 1.0F };

    public float getInterpolation(float paramFloat)
    {
      int j = 100;
      int k = (int)(100.0F * paramFloat);
      int i = k;
      if (k < 0)
        i = 0;
      if (i > 100)
        i = j;
      while (true)
        return this.a[i];
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.g
 * JD-Core Version:    0.6.2
 */