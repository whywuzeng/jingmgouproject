package com.ab.bitmap;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.ab.global.AbAppData;
import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;

public class AbImageDownloadTask extends AsyncTask<AbImageDownloadItem, Integer, AbImageDownloadItem>
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbImageDownloadTask";
  private static Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      paramAnonymousMessage = (AbImageDownloadItem)paramAnonymousMessage.obj;
      paramAnonymousMessage.listener.update(paramAnonymousMessage.bitmap, paramAnonymousMessage.imageUrl);
    }
  };

  protected AbImageDownloadItem doInBackground(AbImageDownloadItem[] paramArrayOfAbImageDownloadItem)
  {
    AbImageDownloadItem localAbImageDownloadItem = paramArrayOfAbImageDownloadItem[0];
    String str = localAbImageDownloadItem.imageUrl;
    if (AbStrUtil.isEmpty(str))
    {
      paramArrayOfAbImageDownloadItem = str;
      if (D)
      {
        Log.d(TAG, "图片URL为空，请先判断");
        paramArrayOfAbImageDownloadItem = str;
      }
      paramArrayOfAbImageDownloadItem = AbImageCache.getCacheKey(paramArrayOfAbImageDownloadItem, localAbImageDownloadItem.width, localAbImageDownloadItem.height, localAbImageDownloadItem.type);
      localAbImageDownloadItem.bitmap = AbImageCache.getBitmapFromCache(paramArrayOfAbImageDownloadItem);
      if (localAbImageDownloadItem.bitmap != null)
        break label135;
      localAbImageDownloadItem.bitmap = AbFileUtil.getBitmapFromSDCache(localAbImageDownloadItem.imageUrl, localAbImageDownloadItem.type, localAbImageDownloadItem.width, localAbImageDownloadItem.height);
      AbImageCache.addBitmapToCache(paramArrayOfAbImageDownloadItem, localAbImageDownloadItem.bitmap);
      if (localAbImageDownloadItem.listener != null)
      {
        paramArrayOfAbImageDownloadItem = handler.obtainMessage();
        paramArrayOfAbImageDownloadItem.obj = localAbImageDownloadItem;
        handler.sendMessage(paramArrayOfAbImageDownloadItem);
      }
    }
    label135: 
    do
    {
      return localAbImageDownloadItem;
      paramArrayOfAbImageDownloadItem = str.trim();
      break;
      if (D)
        Log.d(TAG, "从内存缓存中得到图片:" + paramArrayOfAbImageDownloadItem + "," + localAbImageDownloadItem.bitmap);
    }
    while (localAbImageDownloadItem.listener == null);
    paramArrayOfAbImageDownloadItem = handler.obtainMessage();
    paramArrayOfAbImageDownloadItem.obj = localAbImageDownloadItem;
    handler.sendMessage(paramArrayOfAbImageDownloadItem);
    return localAbImageDownloadItem;
  }

  protected void onCancelled()
  {
    super.onCancelled();
  }

  protected void onPostExecute(AbImageDownloadItem paramAbImageDownloadItem)
  {
    if (paramAbImageDownloadItem.listener != null)
      paramAbImageDownloadItem.listener.update(paramAbImageDownloadItem.bitmap, paramAbImageDownloadItem.imageUrl);
  }

  protected void onPreExecute()
  {
    super.onPreExecute();
  }

  protected void onProgressUpdate(Integer[] paramArrayOfInteger)
  {
    super.onProgressUpdate(paramArrayOfInteger);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.bitmap.AbImageDownloadTask
 * JD-Core Version:    0.6.2
 */