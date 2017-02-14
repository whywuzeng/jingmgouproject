package com.ismartgo.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.CouldSignInAdapter;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.net.OutShopSignRequest;
import com.ismartgo.app.ownself.view.AbPullToRefreshView;
import com.ismartgo.app.ownself.view.AbPullToRefreshView.OnFooterLoadListener;
import com.ismartgo.app.ownself.view.AbPullToRefreshView.OnHeaderRefreshListener;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.List;

public class IntoStoreSignInActivity extends BaseActivity
  implements AbPullToRefreshView.OnFooterLoadListener, AbPullToRefreshView.OnHeaderRefreshListener
{
  private AndroidApplication application;
  CouldSignInAdapter couldSignInAdapter;

  @AbIocView(id=2131231059)
  private LinearLayout could_sign_layout;

  @AbIocView(id=2131231061)
  public TextView enpty_tips;
  private boolean isLoading;
  private MyDialog mDialog;

  @AbIocView(id=2131230905)
  AbPullToRefreshView mPullToRefreshView;
  public int page = 1;
  private int pageSize = 30;

  @AbIocView(click="earnBeasClick", id=2131231000)
  TextView pv_back;

  @AbIocView(id=2131231002)
  ImageView pv_screening;

  @AbIocView(id=2131231005)
  public LinearLayout search_enpty;

  @AbIocView(id=2131231060)
  ListView sign_store_listview;
  List<Store> store_list = new ArrayList();
  private ToastDefine toast;

  @AbIocView(id=2131231001)
  TextView tv_title;
  private User user;

  private void initView()
  {
    this.application = ((AndroidApplication)getApplication());
    this.user = this.application.getUser();
    initTitleBar();
    this.tv_title.setText("到店签到");
    this.pv_screening.setVisibility(4);
    this.mPullToRefreshView.setOnFooterLoadListener(this);
    this.mPullToRefreshView.setOnHeaderRefreshListener(this);
    this.store_list.clear();
    outShopSignRequest(1);
  }

  private void outShopSignRequest(final int paramInt)
  {
    if (this.user == null)
    {
      this.could_sign_layout.setVisibility(8);
      this.search_enpty.setVisibility(0);
      this.enpty_tips.setText(2131296390);
      this.mDialog.dismiss();
      return;
    }
    OutShopSignRequest localOutShopSignRequest = new OutShopSignRequest(this, Url.OUTSHOPSIGN_URL);
    localOutShopSignRequest.initParams(this.user.getId(), BaseActivity.city, BaseActivity.log, BaseActivity.lat, this.page, this.pageSize);
    localOutShopSignRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        IntoStoreSignInActivity.this.mPullToRefreshView.onFooterLoadFinish();
        IntoStoreSignInActivity.this.mPullToRefreshView.onHeaderRefreshFinish();
        IntoStoreSignInActivity.this.could_sign_layout.setVisibility(8);
        IntoStoreSignInActivity.this.search_enpty.setVisibility(0);
        IntoStoreSignInActivity.this.enpty_tips.setText(2131296392);
        IntoStoreSignInActivity.this.toast.setMessage("亲，网络好像出问题了~");
        IntoStoreSignInActivity.this.toast.show();
        IntoStoreSignInActivity.this.mDialog.dismiss();
        IntoStoreSignInActivity.this.isLoading = false;
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        paramAnonymousObject = (List)paramAnonymousObject;
        if (paramAnonymousInt == ResultCode.RESULT_OK)
          if (paramInt == 2)
          {
            IntoStoreSignInActivity.this.toast.setMessage(IntoStoreSignInActivity.this.getString(2131296381));
            IntoStoreSignInActivity.this.toast.show();
          }
        while (true)
        {
          IntoStoreSignInActivity.this.mPullToRefreshView.onFooterLoadFinish();
          IntoStoreSignInActivity.this.mPullToRefreshView.onHeaderRefreshFinish();
          IntoStoreSignInActivity.this.mDialog.dismiss();
          IntoStoreSignInActivity.this.isLoading = false;
          return;
          if ((paramAnonymousObject != null) && (paramAnonymousObject.size() > 0))
          {
            IntoStoreSignInActivity.this.search_enpty.setVisibility(8);
            IntoStoreSignInActivity.this.could_sign_layout.setVisibility(0);
            IntoStoreSignInActivity.this.store_list.addAll(paramAnonymousObject);
            IntoStoreSignInActivity.this.setAdapter();
          }
          else
          {
            IntoStoreSignInActivity.this.enpty_tips.setText("亲，您附近没有可以签到的店哦~");
            IntoStoreSignInActivity.this.search_enpty.setVisibility(0);
            IntoStoreSignInActivity.this.could_sign_layout.setVisibility(8);
            continue;
            if ((paramAnonymousObject != null) && (paramAnonymousObject.size() > 0))
            {
              if (IntoStoreSignInActivity.this.store_list != null)
              {
                IntoStoreSignInActivity.this.store_list.addAll(paramAnonymousObject);
                IntoStoreSignInActivity.this.setAdapter();
              }
            }
            else
            {
              paramAnonymousObject = IntoStoreSignInActivity.this;
              paramAnonymousObject.page -= 1;
            }
          }
        }
      }
    });
    localOutShopSignRequest.startRequest();
  }

  private void setAdapter()
  {
    if ((this.store_list == null) || (this.store_list.size() == 0))
      return;
    if (this.couldSignInAdapter == null)
    {
      this.couldSignInAdapter = new CouldSignInAdapter(getBaseContext(), this, this.store_list);
      this.sign_store_listview.setAdapter(this.couldSignInAdapter);
      return;
    }
    this.couldSignInAdapter.notifyChangeData(this.store_list);
  }

  public void earnBeasClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231000:
    }
    finish();
  }

  public void loginClick(View paramView)
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    applyKitKatTranslucency();
    setContentView(2130903095);
    this.mDialog = new MyDialog(this);
    this.mDialog.show();
    this.toast = new ToastDefine(this);
    initView();
  }

  public void onFooterLoad(AbPullToRefreshView paramAbPullToRefreshView)
  {
    if (this.isLoading)
      return;
    this.isLoading = true;
    this.mPullToRefreshView.postDelayed(new Runnable()
    {
      public void run()
      {
        IntoStoreSignInActivity localIntoStoreSignInActivity = IntoStoreSignInActivity.this;
        localIntoStoreSignInActivity.page += 1;
        IntoStoreSignInActivity.this.outShopSignRequest(2);
      }
    }
    , 1000L);
  }

  public void onHeaderRefresh(AbPullToRefreshView paramAbPullToRefreshView)
  {
    if (this.isLoading)
      return;
    this.isLoading = true;
    this.mPullToRefreshView.postDelayed(new Runnable()
    {
      public void run()
      {
        IntoStoreSignInActivity.this.page = 1;
        if (IntoStoreSignInActivity.this.store_list != null)
          IntoStoreSignInActivity.this.store_list.clear();
        IntoStoreSignInActivity.this.outShopSignRequest(1);
      }
    }
    , 1000L);
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.IntoStoreSignInActivity
 * JD-Core Version:    0.6.2
 */