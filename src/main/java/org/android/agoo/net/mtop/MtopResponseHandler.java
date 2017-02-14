package org.android.agoo.net.mtop;

import com.umeng.message.proguard.bd;
import java.util.Map;
import org.android.agoo.net.async.AsyncHttpResponseHandler;

public abstract class MtopResponseHandler extends AsyncHttpResponseHandler
{
  private static final String a = "MtopResponseHandler";
  public static final String g = "SUCCESS";
  public static final String h = "FAIL";
  public static final String i = "ERROR_SERVICE_NOT_AVAILABLE";

  protected void a(Map<String, String> paramMap, String paramString)
  {
    try
    {
      Result localResult = MtopResponseHelper.parse(paramString);
      if (localResult.isSuccess())
      {
        onSuccess(paramMap, localResult.getData());
        return;
      }
      onFailure(localResult.getRetCode(), localResult.getRetDesc());
      return;
    }
    catch (Throwable localThrowable)
    {
      bd.e("MtopResponseHandler", "handleSuccessMessage:" + paramString, localThrowable);
      onFailure(localThrowable, paramMap, paramString);
    }
  }

  public void onFailure(String paramString1, String paramString2)
  {
  }

  public void onFailure(Throwable paramThrowable, String paramString)
  {
    onFailure("ERROR_SERVICE_NOT_AVAILABLE", paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopResponseHandler
 * JD-Core Version:    0.6.2
 */