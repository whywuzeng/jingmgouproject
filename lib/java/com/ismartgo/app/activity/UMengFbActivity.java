package com.ismartgo.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ab.view.ioc.AbIocView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.SyncListener;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.UserInfo;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UMengFbActivity extends BaseActivity
  implements Comparator
{
  private ReplyAdapter adapter;

  @AbIocView(click="confirm", id=2131230782)
  private Button btn_confirm;

  @AbIocView(id=2131230781)
  private EditText et_feedback;
  private EditText etphone;
  private EditText etqq;
  private ListView fbListView;
  private FeedbackAgent mAgent;
  private Conversation mComversation;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (UMengFbActivity.this.mComversation.getReplyList().size() == 0)
      {
        UMengFbActivity.this.mSwipeRefreshLayout.setVisibility(8);
        UMengFbActivity.this.tvEmpty.setVisibility(0);
      }
      while (true)
      {
        Collections.sort(UMengFbActivity.this.mComversation.getReplyList(), UMengFbActivity.this);
        UMengFbActivity.this.adapter.notifyDataSetChanged();
        return;
        UMengFbActivity.this.mSwipeRefreshLayout.setVisibility(0);
        UMengFbActivity.this.tvEmpty.setVisibility(8);
      }
    }
  };
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private TextView tvEmpty;

  private void initData()
  {
    this.mAgent = new FeedbackAgent(this);
    this.mComversation = new FeedbackAgent(this).getDefaultConversation();
    this.adapter = new ReplyAdapter();
    this.fbListView.setAdapter(this.adapter);
    sync();
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("意见反馈");
    this.etqq = ((EditText)findViewById(2131231075));
    this.etphone = ((EditText)findViewById(2131230814));
    this.mSwipeRefreshLayout = ((SwipeRefreshLayout)findViewById(2131231072));
    this.mSwipeRefreshLayout.setColorScheme(new int[] { 2131099700 });
    this.fbListView = ((ListView)findViewById(2131231073));
    this.tvEmpty = ((TextView)findViewById(2131231074));
    this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
    {
      public void onRefresh()
      {
        UMengFbActivity.this.sync();
      }
    });
    this.btn_confirm.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str1 = UMengFbActivity.this.etqq.getText().toString().trim();
        String str2 = UMengFbActivity.this.etphone.getText().toString().trim();
        String str3 = UMengFbActivity.this.et_feedback.getText().toString().trim();
        if ((TextUtils.isEmpty(str1)) && (TextUtils.isEmpty(str2)))
          Toast.makeText(UMengFbActivity.this, "您的联系方式还没填呢~", 0).show();
        do
        {
          return;
          if (TextUtils.isEmpty(str3))
          {
            Toast.makeText(UMengFbActivity.this, "您反馈内容忘记填了哦！", 0).show();
            return;
          }
          UMengFbActivity.this.etqq.getEditableText().clear();
          UMengFbActivity.this.etphone.getEditableText().clear();
          Object localObject = UMengFbActivity.this.mAgent.getUserInfo();
          paramAnonymousView = (View)localObject;
          if (localObject == null)
            paramAnonymousView = new UserInfo();
          Map localMap = paramAnonymousView.getContact();
          localObject = localMap;
          if (localMap == null)
            localObject = new HashMap();
          if (!TextUtils.isEmpty(str1))
            ((Map)localObject).put("qq", str1);
          if (!TextUtils.isEmpty(str2))
            ((Map)localObject).put("phone", str2);
          paramAnonymousView.setContact((Map)localObject);
          UMengFbActivity.this.mAgent.setUserInfo(paramAnonymousView);
          new Thread()
          {
            public void run()
            {
              super.run();
              boolean bool = UMengFbActivity.this.mAgent.updateUserInfo();
              System.out.println("更新用户是否成功： " + bool);
            }
          }
          .start();
          UMengFbActivity.this.et_feedback.getEditableText().clear();
        }
        while (TextUtils.isEmpty(str3));
        UMengFbActivity.this.mComversation.addUserReply(str3);
        UMengFbActivity.this.mHandler.sendMessage(new Message());
        UMengFbActivity.this.sync();
      }
    });
  }

  private void sync()
  {
    this.mComversation.sync(new SyncListener()
    {
      public void onReceiveDevReply(List<Reply> paramAnonymousList)
      {
        UMengFbActivity.this.mSwipeRefreshLayout.setRefreshing(false);
        UMengFbActivity.this.mHandler.sendMessage(new Message());
        if ((paramAnonymousList != null) && (paramAnonymousList.size() < 1));
      }

      public void onSendUserReply(List<Reply> paramAnonymousList)
      {
      }
    });
    this.adapter.notifyDataSetChanged();
  }

  public int compare(Object paramObject1, Object paramObject2)
  {
    return (int)(((Reply)paramObject2).created_at - ((Reply)paramObject1).created_at);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903099);
    initView();
    initData();
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

  class ReplyAdapter extends BaseAdapter
  {
    private LayoutInflater inflater = (LayoutInflater)UMengFbActivity.this.getSystemService("layout_inflater");

    public ReplyAdapter()
    {
    }

    public int getCount()
    {
      return UMengFbActivity.this.mComversation.getReplyList().size();
    }

    public Object getItem(int paramInt)
    {
      return UMengFbActivity.this.mComversation.getReplyList().get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return ((Reply)UMengFbActivity.this.mComversation.getReplyList().get(paramInt)).hashCode();
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Object localObject;
      if (paramView == null)
      {
        paramView = this.inflater.inflate(2130903137, null);
        paramViewGroup = new ViewHolder();
        paramViewGroup.replyContent = ((TextView)paramView.findViewById(2131231154));
        paramViewGroup.replyTime = ((TextView)paramView.findViewById(2131231155));
        paramViewGroup.replyHeader = ((ImageView)paramView.findViewById(2131231153));
        paramView.setTag(paramViewGroup);
        localObject = (Reply)getItem(paramInt);
        if (!((Reply)localObject).type.equals("dev_reply"))
          break label164;
        paramViewGroup.replyHeader.setImageResource(2130837665);
      }
      while (true)
      {
        paramViewGroup.replyContent.setText(((Reply)localObject).content);
        localObject = new Date(((Reply)localObject).created_at);
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        paramViewGroup.replyTime.setText(localSimpleDateFormat.format((Date)localObject));
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
        break;
        label164: paramViewGroup.replyHeader.setImageResource(2130837673);
      }
    }

    class ViewHolder
    {
      TextView replyContent;
      ImageView replyHeader;
      TextView replyTime;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.UMengFbActivity
 * JD-Core Version:    0.6.2
 */