package com.ab.task;

import android.os.AsyncTask;

public class AbTask extends AsyncTask<AbTaskItem, Integer, AbTaskItem>
{
  protected AbTaskItem doInBackground(AbTaskItem[] paramArrayOfAbTaskItem)
  {
    paramArrayOfAbTaskItem = paramArrayOfAbTaskItem[0];
    if (paramArrayOfAbTaskItem.listener != null)
      paramArrayOfAbTaskItem.listener.get();
    return paramArrayOfAbTaskItem;
  }

  protected void onCancelled()
  {
    super.onCancelled();
  }

  protected void onPostExecute(AbTaskItem paramAbTaskItem)
  {
    if (paramAbTaskItem.listener != null)
      paramAbTaskItem.listener.update();
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
 * Qualified Name:     com.ab.task.AbTask
 * JD-Core Version:    0.6.2
 */