package com.umeng.fb.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FeedbackPush
  implements c
{
  private static FeedbackPush b;
  private final String a = FeedbackPush.class.getName();
  private c c;

  private FeedbackPush(Context paramContext)
  {
    if (a())
    {
      this.c = b.a(paramContext);
      return;
    }
    this.c = new a();
  }

  private boolean a()
  {
    try
    {
      Class.forName("com.umeng.message.UmengService");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.d(this.a, "service not found: com.umeng.message.UmengService");
    }
    return false;
  }

  public static FeedbackPush getInstance(Context paramContext)
  {
    if (b == null);
    try
    {
      if (b == null)
        b = new FeedbackPush(paramContext);
      return b;
    }
    finally
    {
    }
    throw paramContext;
  }

  public void clearPushInfo()
  {
    this.c.clearPushInfo();
  }

  public boolean dealFBMessage(FBMessage paramFBMessage)
  {
    return this.c.dealFBMessage(paramFBMessage);
  }

  public void disable()
  {
    this.c.disable();
  }

  public void enable()
  {
    this.c.enable();
  }

  public void init(Class<?> paramClass, boolean paramBoolean)
  {
    this.c.init(paramClass, paramBoolean);
  }

  public void init(boolean paramBoolean)
  {
    this.c.init(paramBoolean);
  }

  public boolean onFBMessage(Intent paramIntent)
  {
    return this.c.onFBMessage(paramIntent);
  }

  public void setConversationId(String paramString)
  {
    this.c.setConversationId(paramString);
  }

  public void setFBPushCallbacks(c.a parama)
  {
    this.c.setFBPushCallbacks(parama);
  }

  public void setFbFragmentTag(boolean paramBoolean)
  {
    this.c.setFbFragmentTag(paramBoolean);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.push.FeedbackPush
 * JD-Core Version:    0.6.2
 */