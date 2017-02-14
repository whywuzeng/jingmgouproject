package com.ab.http;

import android.os.Environment;
import com.ab.util.AbFileUtil;
import java.io.File;

public class AbFileHttpResponseListener extends AbHttpResponseListener
{
  private static final String TAG = "AbFileHttpResponseListener";
  private File mFile;

  public AbFileHttpResponseListener()
  {
  }

  public AbFileHttpResponseListener(File paramFile)
  {
    this.mFile = paramFile;
  }

  public AbFileHttpResponseListener(String paramString)
  {
  }

  public File getFile()
  {
    return this.mFile;
  }

  public void onFailure(int paramInt, String paramString, Throwable paramThrowable)
  {
  }

  public void onSuccess(int paramInt)
  {
  }

  public void onSuccess(int paramInt, File paramFile)
  {
  }

  public void sendFailureMessage(int paramInt, Throwable paramThrowable)
  {
    sendMessage(obtainMessage(1, new Object[] { Integer.valueOf(paramInt), paramThrowable }));
  }

  public void sendSuccessMessage(int paramInt)
  {
    sendMessage(obtainMessage(0, new Object[] { Integer.valueOf(paramInt) }));
  }

  public void setFile(File paramFile)
  {
    this.mFile = paramFile;
    try
    {
      if (!paramFile.getParentFile().exists())
        paramFile.getParentFile().mkdirs();
      if (!paramFile.exists())
        paramFile.createNewFile();
      return;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
  }

  public void setFile(String paramString)
  {
    if (AbFileUtil.isCanUseSD())
      setFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + AbFileUtil.getDownPathFileDir() + paramString));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.http.AbFileHttpResponseListener
 * JD-Core Version:    0.6.2
 */