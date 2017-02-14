package com.mob.tools.utils;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import java.util.Random;

public class UIHandler
{
  private static Handler handler;
  private static final String[] prays = { "ICDilI/ilJPjgIDjgIDjgIDilI/ilJMK4pSP4pSb4pS74pSB4pSB4pSB4pSb4pS74pSTCuKUg+OAgOOAgOOAgOOAgOOAgOOAgOOAgOKUgwrilIPjgIDjgIDjgIDilIHjgIDjgIDjgIDilIMK4pSD44CA4pSz4pSb44CA4pSX4pSz44CA4pSDCuKUg+OAgOOAgOOAgOOAgOOAgOOAgOOAgOKUgwrilIPjgIDjgIDjgIDilLvjgIDjgIDjgIDilIMK4pSD44CA44CA44CA44CA44CA44CA44CA4pSDCuKUl+KUgeKUk+OAgOOAgOOAgOKUj+KUgeKUmwogICAg4pSD44CA44CA44CA4pSDICAgQ29kZSBpcyBmYXIgYXdheSBmcm9tIGJ1ZyB3aXRoIHRoZSBhbmltYWwgcHJvdGVjdGluZwogICAg4pSD44CA44CA44CA4pSDICAg56We5YW95L+d5L2R77yM5Luj56CB5pegQlVHCiAgICDilIPjgIDjgIDjgIDilJfilIHilIHilIHilJMKICAgIOKUg+OAgOOAgOOAgOOAgOOAgOOAgOOAgOKUo+KUkwogICAg4pSD44CA44CA44CA44CA44CA44CA44CA4pSP4pSbCiAgICDilJfilJPilJPilI/ilIHilLPilJPilI/ilJsKICAgICAg4pSD4pSr4pSr44CA4pSD4pSr4pSrCiAgICAgIOKUl+KUu+KUm+OAgOKUl+KUu+KUmwo=", "44CA4pSP4pST44CA44CA44CA4pSP4pSTCuKUj+KUm+KUu+KUgeKUgeKUgeKUm+KUu+KUkwrilIPjgIDjgIDjgIDjgIDjgIDjgIDjgIDilIMK4pSD44CA44CA44CA4pSB44CA44CA44CA4pSDCuKUg+OAgO+8nuOAgOOAgOOAgO+8nOOAgOKUgwrilIPjgIDjgIDjgIDjgIDjgIDjgIDjgIDilIMK4pSDLi4u44CA4oyS44CALi4u44CA4pSDCuKUg+OAgOOAgOOAgOOAgOOAgOOAgOOAgOKUgwrilJfilIHilJPjgIDjgIDjgIDilI/ilIHilJsK44CA44CA4pSD44CA44CA44CA4pSDICAgIENvZGUgaXMgZmFyIGF3YXkgZnJvbSBidWcgd2l0aCB0aGUgYW5pbWFsIHByb3RlY3RpbmcK44CA44CA4pSD44CA44CA44CA4pSDICAgIOelnuWFveS/neS9kSzku6PnoIHml6BidWcK44CA44CA4pSD44CA44CA44CA4pSDCuOAgOOAgOKUg+OAgOOAgOOAgOKUgwrjgIDjgIDilIPjgIDjgIDjgIDilIMK44CA44CA4pSD44CA44CA44CA4pSDCuOAgOOAgOKUg+OAgOOAgOOAgOKUl+KUgeKUgeKUgeKUkwrjgIDjgIDilIPjgIDjgIDjgIDjgIDjgIDjgIDjgIDilKPilJMK44CA44CA4pSD44CA44CA44CA44CA44CA44CA44CA4pSP4pSbCuOAgOOAgOKUl+KUk+KUk+KUj+KUgeKUs+KUk+KUj+KUmwrjgIDjgIDjgIDilIPilKvilKvjgIDilIPilKvilKsK44CA44CA44CA4pSX4pS74pSb44CA4pSX4pS74pSbCg==", "44CA44CA4pSP4pST44CA44CA44CA4pSP4pSTKyArCuOAgOKUj+KUm+KUu+KUgeKUgeKUgeKUm+KUu+KUkyArICsK44CA4pSD44CA44CA44CA44CA44CA44CA44CA4pSDCuOAgOKUg+OAgOOAgOOAgOKUgeOAgOOAgOOAgOKUgyArKyArICsgKwrjgIDilIPjgIAg4paI4paI4paI4paI4pSB4paI4paI4paI4paIIOKUgysK44CA4pSD44CA44CA44CA44CA44CA44CA44CA4pSDICsK44CA4pSD44CA44CA44CA4pS744CA44CA44CA4pSDCuOAgOKUg+OAgOOAgOOAgOOAgOOAgOOAgOOAgOKUgyArICsgCuOAgOKUl+KUgeKUk+OAgOOAgOOAgOKUj+KUgeKUmwrjgIDjgIDjgIDilIPjgIDjgIDjgIDilIMK44CA44CA44CA4pSD44CA44CA44CA4pSDICsgKyArICsgCuOAgOOAgOOAgOKUg+OAgOOAgOOAgOKUgyAgICAgICAgICAgICAgICAgQ29kZSBpcyBmYXIgYXdheSBmcm9tIGJ1ZyB3aXRoIHRoZSBhbmltYWwgcHJvdGVjdGluZwrjgIDjgIDjgIDilIPjgIDjgIDjgIDilIMgKyAgICAg56We5YW95L+d5L2RLOS7o+eggeaXoGJ1ZwrjgIDjgIDjgIDilIPjgIDjgIDjgIDilIMK44CA44CA44CA4pSD44CA44CA44CA4pSD44CA44CAKwrjgIDjgIDjgIDilIPjgIAg44CA44CA4pSX4pSB4pSB4pSB4pSTICsgKwrjgIDjgIDjgIDilIMg44CA44CA44CA44CA44CA44CA44CA4pSj4pSTIArjgIDjgIDjgIDilIMg44CA44CA44CA44CA44CA44CA44CA4pSP4pSbIArjgIDjgIDjgIDilJfilJPilJPilI/ilIHilLPilJPilI/ilJsgKyArICsgKwrjgIDjgIDjgIDjgIDilIPilKvilKvjgIDilIPilKvilKsK44CA44CA44CA44CA4pSX4pS74pSb44CA4pSX4pS74pSbKyArICsgKwo=" };

  private static Message getShellMessage(int paramInt, Handler.Callback paramCallback)
  {
    Message localMessage = new Message();
    localMessage.what = paramInt;
    return getShellMessage(localMessage, paramCallback);
  }

  private static Message getShellMessage(Message paramMessage, Handler.Callback paramCallback)
  {
    Message localMessage = new Message();
    localMessage.obj = new InnerObj(paramMessage, paramCallback);
    return localMessage;
  }

  private static void handleMessage(Message paramMessage)
  {
    Object localObject = (InnerObj)paramMessage.obj;
    paramMessage = ((InnerObj)localObject).msg;
    localObject = ((InnerObj)localObject).callback;
    if (localObject != null)
      ((Handler.Callback)localObject).handleMessage(paramMessage);
  }

  private static void prepare()
  {
    try
    {
      if (handler == null)
      {
        reset();
        printPray();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static void printPray()
  {
    try
    {
      Object localObject = new Random();
      localObject = Base64.decode(prays[(java.lang.Math.abs(localObject.nextInt()) % 3)], 2);
      Ln.d("\n" + new String((byte[])localObject, "utf-8"), new Object[0]);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
    }
  }

  private static void reset()
  {
    handler = new Handler(Looper.getMainLooper(), new Handler.Callback()
    {
      public boolean handleMessage(Message paramAnonymousMessage)
      {
        UIHandler.handleMessage(paramAnonymousMessage);
        return false;
      }
    });
  }

  public static boolean sendEmptyMessage(int paramInt, Handler.Callback paramCallback)
  {
    prepare();
    return handler.sendMessage(getShellMessage(paramInt, paramCallback));
  }

  public static boolean sendEmptyMessageAtTime(int paramInt, long paramLong, Handler.Callback paramCallback)
  {
    prepare();
    return handler.sendMessageAtTime(getShellMessage(paramInt, paramCallback), paramLong);
  }

  public static boolean sendEmptyMessageDelayed(int paramInt, long paramLong, Handler.Callback paramCallback)
  {
    prepare();
    return handler.sendMessageDelayed(getShellMessage(paramInt, paramCallback), paramLong);
  }

  public static boolean sendMessage(Message paramMessage, Handler.Callback paramCallback)
  {
    prepare();
    return handler.sendMessage(getShellMessage(paramMessage, paramCallback));
  }

  public static boolean sendMessageAtFrontOfQueue(Message paramMessage, Handler.Callback paramCallback)
  {
    prepare();
    return handler.sendMessageAtFrontOfQueue(getShellMessage(paramMessage, paramCallback));
  }

  public static boolean sendMessageAtTime(Message paramMessage, long paramLong, Handler.Callback paramCallback)
  {
    prepare();
    return handler.sendMessageAtTime(getShellMessage(paramMessage, paramCallback), paramLong);
  }

  public static boolean sendMessageDelayed(Message paramMessage, long paramLong, Handler.Callback paramCallback)
  {
    prepare();
    return handler.sendMessageDelayed(getShellMessage(paramMessage, paramCallback), paramLong);
  }

  private static final class InnerObj
  {
    public final Handler.Callback callback;
    public final Message msg;

    public InnerObj(Message paramMessage, Handler.Callback paramCallback)
    {
      this.msg = paramMessage;
      this.callback = paramCallback;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.utils.UIHandler
 * JD-Core Version:    0.6.2
 */