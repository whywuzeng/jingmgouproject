package com.umeng.message;

import android.content.Context;
import com.umeng.message.entity.UMessage;

public abstract interface UHandler
{
  public abstract void handleMessage(Context paramContext, UMessage paramUMessage);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UHandler
 * JD-Core Version:    0.6.2
 */