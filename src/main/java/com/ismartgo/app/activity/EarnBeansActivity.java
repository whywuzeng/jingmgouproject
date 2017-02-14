package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.ImagePagerAdapter;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Advertise;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.FunctionMode;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.net.HomeAdvertiseRequest;
import com.ismartgo.app.ownself.view.CircleFlowIndicator;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.ownself.view.ViewFlow;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.ismartgo.app.utils.LogUtils;
import com.ismartgo.app.utils.UMengStatisticsUtils;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EarnBeansActivity extends BaseActivity
  implements View.OnClickListener
{
  public static String TAG = "EarnBeasActivity";
  private static MyDialog mDialog;
  private static ToastDefine toast;
  private ArrayList<String> adIdList = new ArrayList();
  private int beanNumber;
  private ArrayList<String> imageUrlList = new ArrayList();
  private ImagePagerAdapter imgAdapter;
  private boolean isInShop;
  private List<FunctionMode> items = new ArrayList();
  private ImageView ivIntoShop;
  private ImageView ivInvite;
  private ImageView ivScan;
  private ImageView ivUpload;
  private ArrayList<String> linkUrlArray = new ArrayList();
  private CircleFlowIndicator mFlowIndicator;
  private ViewFlow mViewFlow;
  private int quitCount = 0;
  private int shopId;
  private String shopName;
  private TextView tvIntoShop;
  private TextView tvInvite;
  private TextView tvScan;
  private TextView tvUpload;

  @AbIocView(click="onClick", id=2131231222)
  private RelativeLayout tv_invite;

  @AbIocView(id=2131231228)
  private TextView tv_invite_decribe;

  @AbIocView(click="onClick", id=2131231215)
  private RelativeLayout tv_scan;

  @AbIocView(id=2131231221)
  private TextView tv_scan_decribe;

  @AbIocView(click="onClick", id=2131231202)
  private RelativeLayout tv_signIn;

  @AbIocView(id=2131231208)
  private TextView tv_sign_decribe;

  @AbIocView(click="onClick", id=2131231209)
  private RelativeLayout tv_upload_receipt;

  @SuppressLint({"SimpleDateFormat"})
  public static void clickSignInAndScan(Context paramContext, int paramInt)
  {
    Object localObject = AndroidApplication.getInstance().getCurrentLocation();
    if (localObject != null)
    {
      Intent localIntent = new Intent();
      if (paramInt == 1)
        if (((CurrentLocationInfo)localObject).getSignShopId() > 0)
        {
          Calendar localCalendar = Calendar.getInstance();
          String str = new SimpleDateFormat("yyyy-MM-dd").format(localCalendar.getTime());
          System.out.println("签到日期： " + localCalendar.getTime());
          if (((CurrentLocationInfo)localObject).getLatestSignDate().equals(str.trim()))
            localIntent.setClass(paramContext, HasSignedActivity.class);
        }
      while (true)
      {
        paramContext.startActivity(localIntent);
        return;
        localIntent.setClass(paramContext, SignInActivity2.class);
        continue;
        localIntent.setClass(paramContext, IntoStoreSignInActivity.class);
        continue;
        if (paramInt == 2)
          if (((CurrentLocationInfo)localObject).getScanShopId() > 0)
          {
            localIntent.putExtra("isInShop", true);
            localIntent.putExtra("shopId", ((CurrentLocationInfo)localObject).getScanShopId());
            localIntent.putExtra("shopName", ((CurrentLocationInfo)localObject).getScanShopName());
            localIntent.setClass(paramContext, ScanInStoreGoodsActivity.class);
          }
          else
          {
            localIntent.setClass(paramContext, ScanStoreGoodsActivity.class);
          }
      }
    }
    localObject = new Intent();
    if (paramInt == 1)
      ((Intent)localObject).setClass(paramContext, IntoStoreSignInActivity.class);
    while (true)
    {
      paramContext.startActivity((Intent)localObject);
      return;
      if (paramInt == 2)
        ((Intent)localObject).setClass(paramContext, ScanStoreGoodsActivity.class);
    }
  }

  private void getAdvertiseImg()
  {
    Object localObject = new HomeAdvertiseRequest(this, Url.ADVERTISE_URL);
    ((HomeAdvertiseRequest)localObject).initParams(city, 2);
    ((HomeAdvertiseRequest)localObject).setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        EarnBeansActivity.toast.setMessage("亲，网络好像出问题了哦~");
        EarnBeansActivity.toast.show();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        Object localObject = (Infos)paramAnonymousObject;
        if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
        {
          paramAnonymousObject = ((Infos)localObject).getAdver_list();
          EarnBeansActivity.this.imageUrlList.clear();
          EarnBeansActivity.this.linkUrlArray.clear();
          EarnBeansActivity.this.adIdList.clear();
          paramAnonymousInt = 0;
          if (paramAnonymousInt >= paramAnonymousObject.size())
          {
            paramAnonymousObject = EarnBeansActivity.this;
            localObject = new ImagePagerAdapter(EarnBeansActivity.this, EarnBeansActivity.this.imageUrlList, EarnBeansActivity.this.linkUrlArray, EarnBeansActivity.this.adIdList);
            if (EarnBeansActivity.this.imageUrlList.size() <= 1)
              break label334;
            bool = true;
            paramAnonymousObject.imgAdapter = ((ImagePagerAdapter)localObject).setInfiniteLoop(bool);
            EarnBeansActivity.this.initAdBanner();
          }
        }
        label334: 
        while ((((Infos)localObject).getMsg().toString().trim().equals("")) || (((Infos)localObject).getMsg().toString().trim().equals("成功")))
          while (true)
          {
            return;
            Log.e("TAG", ((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath() + "---" + ((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
            EarnBeansActivity.this.adIdList.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getId());
            EarnBeansActivity.this.imageUrlList.add(StringUtils.getImgUrl(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath()));
            if (!((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink().equals(""))
              EarnBeansActivity.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
            while (true)
            {
              paramAnonymousInt += 1;
              break;
              EarnBeansActivity.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getDetail());
            }
            boolean bool = false;
          }
        EarnBeansActivity.toast.setMessage(((Infos)localObject).getMsg());
        EarnBeansActivity.toast.show();
      }
    });
    ((HomeAdvertiseRequest)localObject).startRequest();
    localObject = SharedPreferenceUtil.getHomeHeadButton(this);
    if ((SharedPreferenceUtil.getHomeBottonFlag(this) == 0) || (((String)localObject).equals("")))
    {
      HttpRequest.getInstance().executePostStringRequest(this, Url.HOME_HEAD_MODE, HttpWhat.GET_HOME_HEAD_MODE, null, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          Log.d("smartgo", paramAnonymousResponse.toString());
          EarnBeansActivity.this.provideData(((String)paramAnonymousResponse.get()).toString(), false);
        }
      });
      return;
    }
    Log.d("smartgo", "头部使用了缓存");
    provideData((String)localObject, true);
  }

  private void initAdBanner()
  {
    this.mViewFlow = ((ViewFlow)findViewById(2131231084));
    this.mViewFlow.setAdapter(this.imgAdapter);
    this.mViewFlow.setmSideBuffer(this.imageUrlList.size());
    this.mViewFlow.setFlowIndicator(this.mFlowIndicator);
    if (this.imageUrlList.size() > 1)
    {
      this.mViewFlow.setTimeSpan(5000L);
      this.mViewFlow.setSelection(this.imageUrlList.size() * 1000);
      this.mViewFlow.startAutoFlowTimer();
    }
    while (true)
    {
      this.mViewFlow.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          UMengStatisticsUtils.beanBanner(EarnBeansActivity.this);
        }
      });
      return;
      this.mFlowIndicator.setVisibility(8);
    }
  }

  private void initView()
  {
    initTitleBar(1);
    this.tv_left.setVisibility(8);
    this.tv_title.setText("赚精明豆");
    this.tv_sign_decribe.setText(StringUtils.StringFilter(StringUtils.ToDBC(getString(2131296362))));
    this.tv_scan_decribe.setText(StringUtils.StringFilter(StringUtils.ToDBC(getString(2131296364))));
    this.tv_invite_decribe.setText(StringUtils.StringFilter(StringUtils.ToDBC(getString(2131296365))));
    this.mFlowIndicator = ((CircleFlowIndicator)findViewById(2131231085));
    this.tvIntoShop = ((TextView)findViewById(2131231205));
    this.tvUpload = ((TextView)findViewById(2131231212));
    this.tvScan = ((TextView)findViewById(2131231218));
    this.tvInvite = ((TextView)findViewById(2131231225));
    this.ivIntoShop = ((ImageView)findViewById(2131231204));
    this.ivUpload = ((ImageView)findViewById(2131231211));
    this.ivScan = ((ImageView)findViewById(2131231217));
    this.ivInvite = ((ImageView)findViewById(2131231224));
    toast = new ToastDefine(this);
  }

  private void provideData(String paramString, boolean paramBoolean)
  {
    this.items = HttpJsonParse.getHomeFunctionInfo(paramString);
    int i;
    if (this.items != null)
    {
      if (paramBoolean)
      {
        SharedPreferenceUtil.setHomeHeadButton(this, paramString);
        SharedPreferenceUtil.setHomeBottonFlag(this, 1);
      }
      i = 0;
      if (i < this.items.size());
    }
    else
    {
      return;
    }
    if (((FunctionMode)this.items.get(i)).getClickUrl().equals("smartgoapp://sign"))
    {
      ImgLoader.getInstance(this).showPic(((FunctionMode)this.items.get(i)).getImgUrl(), this.ivIntoShop, true);
      this.tvIntoShop.setText(((FunctionMode)this.items.get(i)).getTitle());
    }
    while (true)
    {
      i += 1;
      break;
      if (((FunctionMode)this.items.get(i)).getClickUrl().equals("smartgoapp://saomiao"))
      {
        ImgLoader.getInstance(this).showPic(((FunctionMode)this.items.get(i)).getImgUrl(), this.ivScan, true);
        this.tvScan.setText(((FunctionMode)this.items.get(i)).getTitle());
      }
      else if (((FunctionMode)this.items.get(i)).getClickUrl().equals("smartgoapp://invite_friend"))
      {
        ImgLoader.getInstance(this).showPic(((FunctionMode)this.items.get(i)).getImgUrl(), this.ivInvite, true);
        this.tvInvite.setText(((FunctionMode)this.items.get(i)).getTitle());
      }
      else if (((FunctionMode)this.items.get(i)).getClickUrl().equals("smartgoapp://receipt"))
      {
        ImgLoader.getInstance(this).showPic(((FunctionMode)this.items.get(i)).getImgUrl(), this.ivUpload, true);
        this.tvUpload.setText(((FunctionMode)this.items.get(i)).getTitle());
      }
    }
  }

  public boolean IsInShop()
  {
    return this.isInShop;
  }

  public int getBeanNumber()
  {
    return this.beanNumber;
  }

  public int getShopId()
  {
    return this.shopId;
  }

  public String getShopName()
  {
    return this.shopName;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231202:
      UMengStatisticsUtils.beanSignShop(this);
      clickSignInAndScan(this, 1);
      return;
    case 2131231215:
      UMengStatisticsUtils.beanScanMsg(this);
      clickSignInAndScan(this, 2);
      return;
    case 2131231222:
      UMengStatisticsUtils.beanReplyInvite(this);
      startActivity(new Intent(this, InviteFriendsActivity.class));
      return;
    case 2131231209:
    }
    UMengStatisticsUtils.beanTicket(this);
    startActivity(new Intent(this, UploadReceiptActivity.class));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    mDialog = new MyDialog(this);
    initView();
    getAdvertiseImg();
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.quitCount == 1)
      {
        LogUtils.i("Test", "EarnBeansActivity退出");
        AndroidApplication.getInstance().stopService();
        super.onKeyDown(paramInt, paramKeyEvent);
        return true;
      }
      this.quitCount += 1;
      toast.setMessage("再按一次退出精明购！");
      toast.setDisplay(false);
      toast.show();
      new QuitThread(null).start();
      return true;
    }
    return false;
  }

  protected void onPause()
  {
    super.onPause();
    PromotionActivity.isClickInto = true;
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  public void setBeanNumber(int paramInt)
  {
    this.beanNumber = paramInt;
  }

  public void setInShop(boolean paramBoolean)
  {
    this.isInShop = paramBoolean;
  }

  public void setShopId(int paramInt)
  {
    this.shopId = paramInt;
  }

  public void setShopName(String paramString)
  {
    this.shopName = paramString;
  }

  private class QuitThread extends Thread
  {
    private QuitThread()
    {
    }

    public void run()
    {
      try
      {
        sleep(2000L);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      finally
      {
        EarnBeansActivity.this.quitCount = 0;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.EarnBeansActivity
 * JD-Core Version:    0.6.2
 */