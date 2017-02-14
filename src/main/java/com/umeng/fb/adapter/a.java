package com.umeng.fb.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.umeng.fb.audio.AudioAgent;
import com.umeng.fb.common.b;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Conversation.OnChangeListener;
import com.umeng.fb.model.Reply;
import com.umeng.fb.res.e;
import com.umeng.fb.res.f;
import com.umeng.fb.res.g;
import com.umeng.fb.res.h;
import java.util.List;

public class a extends BaseAdapter
{
  public static final int a = 0;
  public static final int b = 1;
  private static Handler o;
  private final String c = a.class.getName();
  private LayoutInflater d;
  private AnimationDrawable e;
  private Conversation f;
  private Context g;
  private AudioAgent h;
  private com.umeng.fb.image.a i;
  private Dialog j;
  private final int k = 3;
  private final int l = 0;
  private final int m = 1;
  private final int n = 2;

  public a(Context paramContext, Conversation paramConversation)
  {
    this.g = paramContext;
    this.d = LayoutInflater.from(this.g);
    this.i = com.umeng.fb.image.a.a();
    b();
    this.f = paramConversation;
    this.f.setOnChangeListener(new Conversation.OnChangeListener()
    {
      public void onChange()
      {
        a.this.notifyDataSetChanged();
      }
    });
  }

  private int a(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    if (localDisplayMetrics.widthPixels > localDisplayMetrics.heightPixels)
      return localDisplayMetrics.heightPixels;
    return localDisplayMetrics.widthPixels;
  }

  private int a(Context paramContext, int paramInt)
  {
    int i2 = a(paramContext);
    int i1 = 100 + paramInt * i2 / 80;
    paramInt = i1;
    if (i1 > i2 * 0.7D)
      paramInt = (int)(i2 * 0.7D);
    return paramInt;
  }

  public static Handler a()
  {
    return o;
  }

  private void a(String paramString)
  {
    if (this.j == null)
    {
      this.j = new Dialog(this.g, 16973831);
      this.j.setContentView(f.m(this.g));
      this.j.getWindow().setWindowAnimations(h.b(this.g));
    }
    ImageView localImageView = (ImageView)this.j.findViewById(e.C(this.g));
    localImageView.setImageBitmap(BitmapFactory.decodeFile(com.umeng.fb.util.c.b(this.g, paramString)));
    this.j.show();
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        a.g(a.this).dismiss();
      }
    });
  }

  private void b()
  {
    o = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default:
        case 0:
          do
          {
            return;
            a.a(a.this);
          }
          while ((a.b(a.this) == null) || (!a.b(a.this).getPlayStatus()));
          a.b(a.this).stopPlayer();
          return;
        case 1:
        }
        a.this.notifyDataSetChanged();
      }
    };
  }

  private void c()
  {
    if ((this.e != null) && (this.e.isRunning()))
    {
      this.e.stop();
      this.e.selectDrawable(0);
    }
  }

  public int getCount()
  {
    List localList = this.f.getReplyList();
    if (localList == null)
      return 0;
    return localList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.f.getReplyList().get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    Reply localReply = (Reply)this.f.getReplyList().get(paramInt);
    if ("text_reply".equals(localReply.content_type))
      return 0;
    if ("audio_reply".equals(localReply.content_type))
      return 1;
    return 2;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i1 = 1;
    Reply localReply1 = (Reply)this.f.getReplyList().get(paramInt);
    boolean bool1;
    label142: boolean bool2;
    if (paramView == null)
      if ("text_reply".equals(localReply1.content_type))
      {
        paramView = this.d.inflate(f.b(this.g), null);
        paramViewGroup = new d(null);
        paramView.setTag(paramViewGroup);
        paramViewGroup.a(paramView);
        paramViewGroup.a(localReply1);
        if (paramInt + 1 < getCount())
        {
          Reply localReply2 = (Reply)this.f.getReplyList().get(paramInt + 1);
          if ((!"new_feedback".equals(localReply1.type)) || (!"user_reply".equals(localReply2.type)))
            break label294;
          bool1 = true;
          bool2 = localReply2.type.equals(localReply1.type);
          if (paramInt + 1 != getCount())
            break label300;
        }
      }
    label294: label300: for (paramInt = i1; ; paramInt = 0)
    {
      if ((bool2 | bool1 | paramInt))
        paramViewGroup.g.setVisibility(8);
      return paramView;
      if ("audio_reply".equals(localReply1.content_type))
      {
        paramView = this.d.inflate(f.c(this.g), null);
        paramViewGroup = new a(null);
        paramView.setTag(paramViewGroup);
        paramViewGroup.a(paramView);
        break;
      }
      paramView = this.d.inflate(f.d(this.g), null);
      paramViewGroup = new c(null);
      paramView.setTag(paramViewGroup);
      paramViewGroup.a(paramView);
      break;
      paramViewGroup = (b)paramView.getTag();
      break;
      bool1 = false;
      break label142;
    }
  }

  public int getViewTypeCount()
  {
    return 3;
  }

  private class a extends a.b
  {
    View a;
    View b;
    TextView c;

    private a()
    {
      super(null);
    }

    public void a(View paramView)
    {
      super.a(paramView);
      this.a = paramView.findViewById(e.y(a.d(a.this)));
      this.b = paramView.findViewById(e.z(a.d(a.this)));
      this.c = ((TextView)paramView.findViewById(e.A(a.d(a.this))));
      this.a.setOnClickListener(this);
    }

    public void a(Reply paramReply)
    {
      super.a(paramReply);
      this.c.setText((int)paramReply.audio_duration + "\"");
      paramReply = new RelativeLayout.LayoutParams(a.a(a.this, a.d(a.this), (int)paramReply.audio_duration), -2);
      this.a.setLayoutParams(paramReply);
      if (!b.a(a.d(a.this)).d());
    }

    public void onClick(View paramView)
    {
      super.onClick(paramView);
      if (paramView == this.a)
      {
        if (a.b(a.this) == null)
          a.a(a.this, AudioAgent.getInstance(a.d(a.this)));
        a.a(a.this);
        paramView = (AnimationDrawable)this.b.getBackground();
        if (a.b(a.this).getPlayStatus())
        {
          a.b(a.this).stopPlayer();
          if ((a.e(a.this) == null) || (!paramView.equals(a.e(a.this))));
        }
      }
      else
      {
        return;
      }
      a.a(a.this, paramView);
      a.e(a.this).start();
      a.b(a.this).startPlay(this.i.reply_id);
    }
  }

  private class b
    implements View.OnClickListener
  {
    TextView e;
    View f;
    View g;
    ImageView h;
    Reply i;

    private b()
    {
    }

    public void a(View paramView)
    {
      this.e = ((TextView)paramView.findViewById(e.e(a.d(a.this))));
      this.g = paramView.findViewById(e.i(a.d(a.this)));
      this.f = paramView.findViewById(e.p(a.d(a.this)));
      this.h = ((ImageView)paramView.findViewById(e.q(a.d(a.this))));
      this.h.setOnClickListener(this);
      this.h.setClickable(true);
    }

    public void a(Reply paramReply)
    {
      this.i = paramReply;
      if ("dev_reply".equals(paramReply.type))
      {
        this.f.setBackgroundColor(a.d(a.this).getResources().getColor(com.umeng.fb.res.c.a(a.d(a.this))));
        this.e.setText(com.umeng.fb.util.d.a(a.d(a.this), paramReply.created_at));
      }
      while (true)
      {
        this.g.setVisibility(0);
        return;
        this.f.setBackgroundColor(a.d(a.this).getResources().getColor(com.umeng.fb.res.c.c(a.d(a.this))));
        if ("not_sent".equals(paramReply.status))
        {
          this.e.setText(g.d(a.d(a.this)));
          this.h.setImageResource(com.umeng.fb.res.d.a(a.d(a.this)));
          this.h.setAnimation(null);
          this.h.setVisibility(0);
          this.h.setClickable(true);
        }
        else if (("sending".equals(paramReply.status)) || ("will_sent".equals(paramReply.status)))
        {
          this.e.setText(g.e(a.d(a.this)));
          this.h.setImageResource(com.umeng.fb.res.d.a(a.d(a.this)));
          this.h.setVisibility(0);
          paramReply = new RotateAnimation(0.0F, -360.0F, 1, 0.5F, 1, 0.5F);
          paramReply.setInterpolator(new LinearInterpolator());
          paramReply.setRepeatCount(-1);
          paramReply.setDuration(700L);
          this.h.startAnimation(paramReply);
          this.h.setClickable(false);
        }
        else
        {
          this.e.setText(com.umeng.fb.util.d.a(a.d(a.this), paramReply.created_at));
          this.h.setAnimation(null);
          this.h.setVisibility(8);
          this.h.setClickable(false);
        }
      }
    }

    public void onClick(View paramView)
    {
      if (paramView == this.h)
        a.c(a.this).sendReplyOnlyOne(a.c(a.this).getId(), this.i);
    }
  }

  private class c extends a.b
  {
    ImageView a;

    private c()
    {
      super(null);
    }

    public void a(View paramView)
    {
      super.a(paramView);
      this.a = ((ImageView)paramView.findViewById(e.B(a.d(a.this))));
      this.a.setOnClickListener(this);
    }

    public void a(Reply paramReply)
    {
      super.a(paramReply);
      a.f(a.this).a(com.umeng.fb.util.c.b(a.d(a.this), paramReply.reply_id), this.a, a.a(a.this, a.d(a.this)));
    }

    public void onClick(View paramView)
    {
      super.onClick(paramView);
      if (paramView == this.a)
        a.a(a.this, this.i.reply_id);
    }
  }

  private class d extends a.b
  {
    TextView a;

    private d()
    {
      super(null);
    }

    public void a(View paramView)
    {
      super.a(paramView);
      this.a = ((TextView)paramView.findViewById(e.b(a.d(a.this))));
    }

    public void a(Reply paramReply)
    {
      super.a(paramReply);
      this.a.setText(paramReply.content);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.adapter.a
 * JD-Core Version:    0.6.2
 */