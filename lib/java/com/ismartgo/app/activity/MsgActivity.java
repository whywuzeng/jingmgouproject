package com.ismartgo.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.MsgListAdapter;
import com.ismartgo.app.andbase.util.AbLoadDialogFragment;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.IsNewMsg;
import com.ismartgo.app.bean.Message;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.net.DelMessageRequest;
import com.ismartgo.app.net.MessageRequest;
import com.ismartgo.app.ownself.view.AbPullToRefreshView;
import com.ismartgo.app.ownself.view.AbPullToRefreshView.OnFooterLoadListener;
import com.ismartgo.app.ownself.view.AbPullToRefreshView.OnHeaderRefreshListener;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.SystemBarTintManager;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MsgActivity extends AbActivity
  implements AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener
{
  private int MessageTag = 0;
  private String TAG = "MsgActivity";
  private MsgListAdapter adapter;
  AndroidApplication application;
  private LinearLayout data_nothing_layout;
  private boolean isDel;
  private IsNewMsg isNewMsg;
  protected ImageView iv_right;

  @AbIocView(id=2131230967)
  private ListView lv_msg;
  private AbPullToRefreshView mAbPullToRefreshView = null;
  MyDialog mDialog;
  private AbLoadDialogFragment mDialogFragment = null;
  private ArrayList<Message> msgList = new ArrayList();
  MessageRequest msgRequest = null;
  private int page = 1;
  protected ToastDefine toast;
  protected ImageView tv_left;
  private TextView tv_noMore_txt;
  protected TextView tv_right;
  protected TextView tv_title;
  private int uId;
  private User user;

  private void applyKitKatTranslucency()
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      setTranslucentStatus(true);
      SystemBarTintManager localSystemBarTintManager = new SystemBarTintManager(this);
      localSystemBarTintManager.setStatusBarTintEnabled(true);
      localSystemBarTintManager.setNavigationBarTintEnabled(true);
      localSystemBarTintManager.setTintColor(getResources().getColor(2131099700));
    }
  }

  private void getMsg(int paramInt1, int paramInt2)
  {
    if (paramInt1 > 0)
    {
      this.msgRequest = new MessageRequest(this, Url.MESSAGE_URL);
      getMsgRequest(paramInt1, paramInt2, this.page);
      return;
    }
    if (paramInt2 > 0)
    {
      this.msgRequest = new MessageRequest(this, Url.UPDATEMESSAGE_URL);
      getMsgRequest(paramInt1, paramInt2, this.page);
      return;
    }
    this.mDialog.dismiss();
  }

  private void getMsgRequest(int paramInt1, int paramInt2, int paramInt3)
  {
    this.msgRequest.initParams(paramInt1, paramInt2, paramInt3);
    this.msgRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        MsgActivity.this.mDialog.dismiss();
        MsgActivity.this.mAbPullToRefreshView.onFooterLoadFinish();
        MsgActivity.this.mAbPullToRefreshView.onHeaderRefreshFinish();
        MsgActivity.this.lv_msg.setVisibility(8);
        MsgActivity.this.mAbPullToRefreshView.setVisibility(8);
        MsgActivity.this.data_nothing_layout.setVisibility(0);
        MsgActivity.this.tv_noMore_txt.setText("没有新消息");
        MsgActivity.this.mDialog.dismiss();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        if (MsgActivity.this.isDel)
          MsgActivity.this.isDel = false;
        do
        {
          return;
          MsgActivity.this.mAbPullToRefreshView.onFooterLoadFinish();
          MsgActivity.this.mAbPullToRefreshView.onHeaderRefreshFinish();
          if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
          {
            MsgActivity.this.isNewMsg = ((IsNewMsg)paramAnonymousObject);
            if (MsgActivity.this.isNewMsg.getMsg_list() != null)
            {
              if (MsgActivity.this.page >= 2)
              {
                MsgActivity.this.msgList.addAll((ArrayList)MsgActivity.this.isNewMsg.getMsg_list());
                paramAnonymousInt = 0;
                while (true)
                {
                  if (paramAnonymousInt >= MsgActivity.this.msgList.size())
                  {
                    MsgActivity.this.adapter.setList(MsgActivity.this.msgList);
                    MsgActivity.this.adapter.notifyDataSetChanged();
                    MsgActivity.this.mDialog.dismiss();
                    return;
                  }
                  Log.e(MsgActivity.this.TAG, "标题--------->" + ((Message)MsgActivity.this.msgList.get(paramAnonymousInt)).getTitle());
                  Log.e(MsgActivity.this.TAG, "是否已读--------->" + ((Message)MsgActivity.this.msgList.get(paramAnonymousInt)).getIsRead());
                  paramAnonymousInt += 1;
                }
              }
              MsgActivity.this.msgList = ((ArrayList)MsgActivity.this.isNewMsg.getMsg_list());
              paramAnonymousInt = 0;
              while (true)
              {
                if (paramAnonymousInt >= MsgActivity.this.msgList.size())
                {
                  MsgActivity.this.adapter.setList(MsgActivity.this.msgList);
                  MsgActivity.this.adapter.notifyDataSetChanged();
                  MsgActivity.this.mDialog.dismiss();
                  return;
                }
                Log.e(MsgActivity.this.TAG, "标题--------->" + ((Message)MsgActivity.this.msgList.get(paramAnonymousInt)).getTitle());
                paramAnonymousInt += 1;
              }
            }
            if (MsgActivity.this.page > 1)
            {
              Toast.makeText(MsgActivity.this, 2131296376, 1).show();
              return;
            }
            System.out.println("page==1");
            MsgActivity.this.lv_msg.setVisibility(8);
            MsgActivity.this.mAbPullToRefreshView.setVisibility(8);
            MsgActivity.this.data_nothing_layout.setVisibility(0);
            MsgActivity.this.mDialog.dismiss();
            MsgActivity.this.tv_noMore_txt.setText("没有新消息");
            return;
          }
        }
        while (MsgActivity.this.page > 1);
        MsgActivity.this.lv_msg.setVisibility(8);
        MsgActivity.this.mAbPullToRefreshView.setVisibility(8);
        MsgActivity.this.data_nothing_layout.setVisibility(0);
        MsgActivity.this.tv_noMore_txt.setText("没有新消息");
        MsgActivity.this.mDialog.dismiss();
      }
    });
    this.msgRequest.startRequest();
  }

  private void initView()
  {
    this.toast = new ToastDefine(this);
    this.application = ((AndroidApplication)getApplication());
    this.user = this.application.getUser();
    this.lv_msg = ((ListView)findViewById(2131230967));
    this.data_nothing_layout = ((LinearLayout)findViewById(2131230855));
    this.tv_noMore_txt = ((TextView)findViewById(2131231100));
    this.mAbPullToRefreshView = ((AbPullToRefreshView)findViewById(2131230857));
    this.mAbPullToRefreshView.setOnHeaderRefreshListener(this);
    this.mAbPullToRefreshView.setOnFooterLoadListener(this);
    if (this.user == null);
    for (int i = 0; ; i = Integer.valueOf(this.user.getId()).intValue())
    {
      this.uId = i;
      initTitleBar();
      this.tv_title.setText(getString(2131296354));
      getMsg(this.uId, this.MessageTag);
      this.adapter = new MsgListAdapter(this, this.msgList);
      this.lv_msg.setAdapter(this.adapter);
      this.lv_msg.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = new Intent();
          paramAnonymousAdapterView.setClass(MsgActivity.this, WebViewActivity.class);
          paramAnonymousAdapterView.putExtra("url", ((Message)MsgActivity.this.msgList.get(paramAnonymousInt)).getContent());
          MsgActivity.this.startActivity(paramAnonymousAdapterView);
          ((Message)MsgActivity.this.msgList.get(paramAnonymousInt)).setIsRead(2);
          MsgActivity.this.adapter.notifyDataSetChanged();
          MsgActivity.this.getMsg(MsgActivity.this.MessageTag, ((Message)MsgActivity.this.msgList.get(paramAnonymousInt)).getId());
          MsgActivity.this.isDel = true;
        }
      });
      this.lv_msg.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
        public boolean onItemLongClick(final AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong)
        {
          final int i = ((Message)MsgActivity.this.msgList.get(paramAnonymousInt)).getId();
          paramAnonymousAdapterView = new AlertDialog.Builder(MsgActivity.this).create();
          paramAnonymousAdapterView.show();
          paramAnonymousAdapterView.getWindow().setContentView(2130903116);
          ((TextView)paramAnonymousAdapterView.getWindow().findViewById(2131231115)).setText("确定删除该条消息吗？");
          paramAnonymousAdapterView.getWindow().findViewById(2131230782).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              paramAnonymousAdapterView.dismiss();
              paramAnonymous2View = new DelMessageRequest(MsgActivity.this, Url.DELETEMESSAGE_URL);
              paramAnonymous2View.initParams(i);
              paramAnonymous2View.setOnCompleteListener(new OnCompleteListener()
              {
                public void onFailure(Throwable paramAnonymous3Throwable, int paramAnonymous3Int, String paramAnonymous3String)
                {
                  MsgActivity.this.toast.setMessage(MsgActivity.this.getString(2131296392));
                  MsgActivity.this.toast.show();
                  this.val$mDialog.dismiss();
                }

                public void onResult(Object paramAnonymous3Object, int paramAnonymous3Int)
                {
                  if (paramAnonymous3Int == ResultCode.RESULT_OK)
                  {
                    MsgActivity.this.msgList.remove(this.val$p);
                    MsgActivity.this.adapter.setList(MsgActivity.this.msgList);
                    MsgActivity.this.adapter.notifyDataSetChanged();
                    this.val$mDialog.dismiss();
                    if (MsgActivity.this.msgList.isEmpty())
                    {
                      MsgActivity.this.lv_msg.setVisibility(8);
                      MsgActivity.this.mAbPullToRefreshView.setVisibility(8);
                      MsgActivity.this.data_nothing_layout.setVisibility(0);
                    }
                  }
                }
              });
              paramAnonymous2View.startRequest();
            }
          });
          paramAnonymousAdapterView.getWindow().findViewById(2131230873).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              paramAnonymousAdapterView.dismiss();
            }
          });
          return true;
        }
      });
      return;
    }
  }

  private void setTranslucentStatus(boolean paramBoolean)
  {
    Window localWindow = getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    if (paramBoolean);
    for (localLayoutParams.flags |= 67108864; ; localLayoutParams.flags &= -67108865)
    {
      localWindow.setAttributes(localLayoutParams);
      return;
    }
  }

  private void updateMessage(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("mesId", paramString);
    HttpRequest.getInstance().executePostStringRequest(this, Url.UPDATEMESSAGE_URL, 8, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
      }
    });
  }

  public void initTitleBar()
  {
    this.iv_right = ((ImageView)findViewById(2131231090));
    this.tv_left = ((ImageView)findViewById(2131231076));
    this.tv_title = ((TextView)findViewById(2131231001));
    this.tv_right = ((TextView)findViewById(2131231091));
    this.tv_left.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MsgActivity.this.finish();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    this.mDialog = new MyDialog(this);
    this.page = 1;
    this.mDialog.show();
    this.msgList.clear();
    initView();
  }

  public void onFooterLoad(AbPullToRefreshView paramAbPullToRefreshView)
  {
    this.mAbPullToRefreshView.postDelayed(new Runnable()
    {
      public void run()
      {
        MsgActivity localMsgActivity = MsgActivity.this;
        localMsgActivity.page += 1;
        MsgActivity.this.getMsg(MsgActivity.this.uId, MsgActivity.this.MessageTag);
      }
    }
    , 1000L);
  }

  public void onHeaderRefresh(AbPullToRefreshView paramAbPullToRefreshView)
  {
    this.mAbPullToRefreshView.postDelayed(new Runnable()
    {
      public void run()
      {
        MsgActivity.this.page = 1;
        MsgActivity.this.msgList.clear();
        MsgActivity.this.initView();
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
 * Qualified Name:     com.ismartgo.app.activity.MsgActivity
 * JD-Core Version:    0.6.2
 */