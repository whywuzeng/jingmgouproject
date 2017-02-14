package com.ismartgo.app.andbase.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class AbToastUtil
{
  public static final int SHOW_TOAST = 0;
  private static Handler baseHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 0:
      }
      AbToastUtil.showToast(AbToastUtil.mContext, paramAnonymousMessage.getData().getString("TEXT"));
    }
  };
  private static Context mContext = null;

  public static void showToast(Context paramContext, int paramInt)
  {
    mContext = paramContext;
    Toast.makeText(paramContext, paramContext.getResources().getText(paramInt), 0).show();
  }

  public static void showToast(Context paramContext, String paramString)
  {
    mContext = paramContext;
    if (!AbStrUtil.isEmpty(paramString))
      Toast.makeText(paramContext, paramString, 0).show();
  }

  public static void showToastInThread(Context paramContext, int paramInt)
  {
    mContext = paramContext;
    Message localMessage = baseHandler.obtainMessage(0);
    Bundle localBundle = new Bundle();
    localBundle.putString("TEXT", paramContext.getResources().getString(paramInt));
    localMessage.setData(localBundle);
    baseHandler.sendMessage(localMessage);
  }

  public static void showToastInThread(Context paramContext, String paramString)
  {
    mContext = paramContext;
    paramContext = baseHandler.obtainMessage(0);
    Bundle localBundle = new Bundle();
    localBundle.putString("TEXT", paramString);
    paramContext.setData(localBundle);
    baseHandler.sendMessage(paramContext);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbToastUtil
 * JD-Core Version:    0.6.2
 */