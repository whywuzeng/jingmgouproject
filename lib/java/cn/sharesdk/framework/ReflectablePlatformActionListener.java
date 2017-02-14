package cn.sharesdk.framework;

import android.os.Handler.Callback;
import android.os.Message;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

public class ReflectablePlatformActionListener
  implements PlatformActionListener
{
  private int a;
  private Handler.Callback b;
  private int c;
  private Handler.Callback d;
  private int e;
  private Handler.Callback f;

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    if (this.f != null)
    {
      Message localMessage = new Message();
      localMessage.what = this.e;
      localMessage.obj = new Object[] { paramPlatform, Integer.valueOf(paramInt) };
      UIHandler.sendMessage(localMessage, this.f);
    }
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    if (this.b != null)
    {
      Message localMessage = new Message();
      localMessage.what = this.a;
      localMessage.obj = new Object[] { paramPlatform, Integer.valueOf(paramInt), paramHashMap };
      UIHandler.sendMessage(localMessage, this.b);
    }
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    if (this.d != null)
    {
      Message localMessage = new Message();
      localMessage.what = this.c;
      localMessage.obj = new Object[] { paramPlatform, Integer.valueOf(paramInt), paramThrowable };
      UIHandler.sendMessage(localMessage, this.d);
    }
  }

  public void setOnCancelCallback(int paramInt, Handler.Callback paramCallback)
  {
    this.e = paramInt;
    this.f = paramCallback;
  }

  public void setOnCompleteCallback(int paramInt, Handler.Callback paramCallback)
  {
    this.a = paramInt;
    this.b = paramCallback;
  }

  public void setOnErrorCallback(int paramInt, Handler.Callback paramCallback)
  {
    this.c = paramInt;
    this.d = paramCallback;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.ReflectablePlatformActionListener
 * JD-Core Version:    0.6.2
 */