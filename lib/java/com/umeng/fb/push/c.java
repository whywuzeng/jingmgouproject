package com.umeng.fb.push;

import android.content.Intent;

public abstract interface c
{
  public abstract void clearPushInfo();

  public abstract boolean dealFBMessage(FBMessage paramFBMessage);

  public abstract void disable();

  public abstract void enable();

  public abstract void init(Class<?> paramClass, boolean paramBoolean);

  public abstract void init(boolean paramBoolean);

  public abstract boolean onFBMessage(Intent paramIntent);

  public abstract void setConversationId(String paramString);

  public abstract void setFBPushCallbacks(a parama);

  public abstract void setFbFragmentTag(boolean paramBoolean);

  public static abstract interface a
  {
    public abstract void onAddPushDevReply();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.push.c
 * JD-Core Version:    0.6.2
 */