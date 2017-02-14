package com.umeng.fb.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import com.umeng.fb.SyncListener;
import com.umeng.fb.net.a;
import com.umeng.fb.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;

public class Conversation
  implements Comparable<Conversation>
{
  private static final String a = Conversation.class.getName();
  private List<Reply> b = new ArrayList();
  private Context c;
  private String d;
  private boolean e = false;
  private OnChangeListener f;

  private Conversation(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
  }

  protected static Conversation a(Context paramContext, JSONArray paramJSONArray, String paramString)
    throws JSONException
  {
    paramContext = new Conversation(paramContext);
    int i = 0;
    while (i < paramJSONArray.length())
    {
      Reply localReply = Reply.fromJson(paramJSONArray.getJSONObject(i));
      paramContext.b.add(localReply);
      if ("new_feedback".equals(localReply.type))
        paramContext.e = true;
      i += 1;
    }
    paramContext.d = paramString;
    Collections.sort(paramContext.b);
    Log.c(a, "fromJson: json = " + paramJSONArray.toString() + "\n" + "fromJson: conversation = " + paramContext.toString());
    return paramContext;
  }

  private boolean a(Reply paramReply)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      Reply localReply = (Reply)localIterator.next();
      if ((!TextUtils.isEmpty(localReply.reply_id)) && ("dev_reply".equals(localReply.type)) && ((localReply.reply_id.equals(paramReply.reply_id)) || (localReply.reply_id.equals("RP" + paramReply.created_at + "1111"))))
        return true;
    }
    return false;
  }

  private void b()
  {
    Log.c(a, "onChange: " + toString());
    Store.getInstance(this.c).saveConversation(this.d, this);
    if (this.f != null)
      this.f.onChange();
  }

  private static String c()
  {
    return "R" + UUID.randomUUID().toString();
  }

  private static String d()
  {
    return "C" + UUID.randomUUID().toString();
  }

  public static Conversation newInstance(Context paramContext)
  {
    return newInstance(paramContext, d());
  }

  public static Conversation newInstance(Context paramContext, String paramString)
  {
    Conversation localConversation = new Conversation(paramContext);
    localConversation.b = new ArrayList();
    localConversation.d = paramString;
    Store.getInstance(paramContext).saveConversation(localConversation.d, localConversation);
    return localConversation;
  }

  public void addReply(Reply paramReply)
  {
    this.b.add(paramReply);
    b();
  }

  public void addUserReply(String paramString)
  {
    addUserReply(paramString, c(), "text_reply", -1.0F);
  }

  public void addUserReply(String paramString1, String paramString2, String paramString3, float paramFloat)
  {
    if ((this.e) || (this.b.size() > 0))
      paramString1 = new Reply(paramString1, paramString2, "user_reply", new Date().getTime(), paramString3, paramFloat);
    while (true)
    {
      paramString1.status = "will_sent";
      addReply(paramString1);
      return;
      paramString1 = new Reply(paramString1, paramString2, "new_feedback", new Date().getTime(), paramString3, paramFloat);
      this.e = true;
    }
  }

  public int compareTo(Conversation paramConversation)
  {
    if ((getReplyList().size() > 0) && (paramConversation.getReplyList().size() > 0))
    {
      long l = ((Reply)getReplyList().get(0)).created_at - ((Reply)paramConversation.getReplyList().get(0)).created_at;
      if (l > 0L)
        return 1;
      if (l == 0L)
        return 0;
      return -1;
    }
    return 1;
  }

  public String getId()
  {
    return this.d;
  }

  public List<Reply> getReplyList()
  {
    return this.b;
  }

  public void sendReplyOnlyOne(final String paramString, final Reply paramReply)
  {
    paramString = new Runnable()
    {
      public void run()
      {
        paramReply.status = "sending";
        this.b.post(new Runnable()
        {
          public void run()
          {
            Conversation.a(Conversation.this);
          }
        });
        if ("user_reply".equals(paramReply.type));
        for (final Map localMap = new a(Conversation.c(Conversation.this)).a(paramString, paramReply); (localMap != null) && (localMap.size() == 2); localMap = new a(Conversation.c(Conversation.this)).b(paramString, paramReply))
        {
          this.b.post(new Runnable()
          {
            public void run()
            {
              Conversation.2.this.a.created_at = ((Long)localMap.get("created_at")).longValue();
              Conversation.2.this.a.status = "sent";
              Collections.sort(Conversation.d(Conversation.this));
              Conversation.a(Conversation.this);
            }
          });
          return;
        }
        this.b.post(new Runnable()
        {
          public void run()
          {
            Conversation.2.this.a.status = "not_sent";
            Conversation.a(Conversation.this);
          }
        });
      }
    };
    paramReply = Store.getInstance(this.c).getUid();
    if ((paramReply == null) || ("".equals(paramReply)))
    {
      new GetUidTask(paramString).execute(new Void[0]);
      return;
    }
    new Thread(paramString).start();
  }

  public void setOnChangeListener(OnChangeListener paramOnChangeListener)
  {
    this.f = paramOnChangeListener;
  }

  public void sync(final SyncListener paramSyncListener)
  {
    if (getReplyList().size() == 0)
    {
      if (paramSyncListener != null)
      {
        paramSyncListener.onReceiveDevReply(new ArrayList());
        paramSyncListener.onSendUserReply(new ArrayList());
      }
      return;
    }
    Log.c(a, "sync id=" + this.d + ":\t " + this);
    paramSyncListener = new Runnable()
    {
      public void run()
      {
        final ArrayList localArrayList1 = new ArrayList();
        final ArrayList localArrayList2 = new ArrayList();
        long l1 = 0L;
        int i = 0;
        final Reply localReply;
        Object localObject;
        if (i < Conversation.this.getReplyList().size())
        {
          localReply = (Reply)Conversation.this.getReplyList().get(i);
          long l2;
          if (("user_reply".equals(localReply.type)) || ("new_feedback".equals(localReply.type)))
            if (!"not_sent".equals(localReply.status))
            {
              l2 = l1;
              if (!"will_sent".equals(localReply.status));
            }
            else
            {
              localReply.status = "sending";
              localArrayList1.add(localReply);
              this.a.post(new Runnable()
              {
                public void run()
                {
                  Conversation.a(Conversation.this);
                }
              });
              if (!"new_feedback".equals(localReply.type))
                break label260;
              localObject = new a(Conversation.c(Conversation.this)).b(Conversation.b(Conversation.this), localReply);
              label185: Log.c(Conversation.a(), "result - " + localObject);
              if ((localObject == null) || (((Map)localObject).size() != 2))
                break label291;
              this.a.post(new Runnable()
              {
                public void run()
                {
                  localReply.created_at = ((Long)this.b.get("created_at")).longValue();
                  localReply.status = "sent";
                  Conversation.a(Conversation.this);
                }
              });
              l2 = l1;
            }
          while (true)
          {
            i += 1;
            l1 = l2;
            break;
            label260: localObject = new a(Conversation.c(Conversation.this)).a(Conversation.b(Conversation.this), localReply);
            break label185;
            label291: this.a.post(new Runnable()
            {
              public void run()
              {
                localReply.status = "not_sent";
                Conversation.a(Conversation.this);
              }
            });
            l2 = l1;
            continue;
            l2 = l1;
            if ("dev_reply".equals(localReply.type))
            {
              l2 = l1;
              if (l1 <= localReply.created_at)
                l2 = localReply.created_at;
            }
          }
        }
        try
        {
          localObject = new a(Conversation.c(Conversation.this)).a(Conversation.b(Conversation.this), l1).iterator();
          while (((Iterator)localObject).hasNext())
          {
            localReply = (Reply)((Iterator)localObject).next();
            if (("dev_reply".equals(localReply.type)) && (!Conversation.a(Conversation.this, localReply)))
              localArrayList2.add(localReply);
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
        }
        this.a.post(new Runnable()
        {
          public void run()
          {
            Conversation.d(Conversation.this).addAll(localArrayList2);
            Collections.sort(Conversation.d(Conversation.this));
            Conversation.a(Conversation.this);
            if (Conversation.1.this.b != null)
            {
              Conversation.1.this.b.onReceiveDevReply(localArrayList2);
              Conversation.1.this.b.onSendUserReply(localArrayList1);
            }
          }
        });
      }
    };
    String str = Store.getInstance(this.c).getUid();
    if ((str == null) || ("".equals(str)))
    {
      new GetUidTask(paramSyncListener).execute(new Void[0]);
      return;
    }
    new Thread(paramSyncListener).start();
  }

  public JSONArray toJson()
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
      localJSONArray.put(((Reply)localIterator.next()).toJson());
    return localJSONArray;
  }

  public String toString()
  {
    return toJson().toString();
  }

  class GetUidTask extends AsyncTask<Void, Integer, Boolean>
  {
    private Runnable b;

    public GetUidTask(Runnable arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    private void a()
    {
      if ((Conversation.d(Conversation.this) == null) || (Conversation.d(Conversation.this).isEmpty()))
        return;
      Iterator localIterator = Conversation.d(Conversation.this).iterator();
      while (localIterator.hasNext())
      {
        Reply localReply = (Reply)localIterator.next();
        boolean bool1 = "sending".equals(localReply.status);
        boolean bool2 = "will_sent".equals(localReply.status);
        if ((bool1) || (bool2))
          localReply.status = "not_sent";
      }
      Conversation.a(Conversation.this);
    }

    protected Boolean a(Void[] paramArrayOfVoid)
    {
      paramArrayOfVoid = new a(Conversation.c(Conversation.this));
      String str = paramArrayOfVoid.a();
      if ((str == null) || ("".equals(str)))
        paramArrayOfVoid.a();
      return Boolean.valueOf(true);
    }

    protected void a(Boolean paramBoolean)
    {
      paramBoolean = Store.getInstance(Conversation.c(Conversation.this)).getUid();
      if ((paramBoolean != null) && (!"".equals(paramBoolean)))
      {
        new Thread(this.b).start();
        return;
      }
      Log.c(Conversation.a(), "get uid fail");
      a();
    }
  }

  public static abstract interface OnChangeListener
  {
    public abstract void onChange();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.Conversation
 * JD-Core Version:    0.6.2
 */