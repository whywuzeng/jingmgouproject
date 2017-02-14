package com.ismartgo.app.grid.impl;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.webkit.DownloadListener;
import com.ismartgo.app.common.CommonMethod;
import java.io.File;

public class WebDownloadListener
  implements DownloadListener
{
  private Activity activity;
  private DownloadManager downManager;
  private String filePath;
  private DownLoadCompleteReceiver receiver;

  public WebDownloadListener(Activity paramActivity)
  {
    this.activity = paramActivity;
    if (this.downManager == null)
    {
      this.downManager = ((DownloadManager)paramActivity.getSystemService("download"));
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
      this.receiver = new DownLoadCompleteReceiver();
      paramActivity.registerReceiver(this.receiver, localIntentFilter);
    }
  }

  private void install(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
    this.filePath = null;
  }

  public DownLoadCompleteReceiver getReceiver()
  {
    return this.receiver;
  }

  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    if (!paramString1.endsWith(".apk"))
      return;
    paramString2 = new DownloadManager.Request(Uri.parse(paramString1));
    paramString2.setAllowedNetworkTypes(2);
    paramString2.setNotificationVisibility(0);
    paramString2.setAllowedOverRoaming(false);
    paramString3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    paramString2.setTitle(paramString1.substring(paramString1.lastIndexOf("/") + 1, paramString1.length()));
    paramString3 = paramString3.getAbsolutePath();
    paramLong = System.currentTimeMillis();
    for (paramString1 = ""; ; paramString1 = paramString1 + (int)(Math.random() * 10.0D))
      if (paramString1.length() >= 4)
      {
        this.filePath = (paramString3 + "/" + paramLong + "_" + paramString1 + ".apk");
        paramString2.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, paramLong + "_" + paramString1 + ".apk");
        this.downManager.enqueue(paramString2);
        return;
      }
  }

  public class DownLoadCompleteReceiver extends BroadcastReceiver
  {
    public DownLoadCompleteReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent.getAction().equals("android.intent.action.DOWNLOAD_COMPLETE")) && (!CommonMethod.isEmpty(WebDownloadListener.this.filePath)))
        WebDownloadListener.this.install(WebDownloadListener.this.activity, WebDownloadListener.this.filePath);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.impl.WebDownloadListener
 * JD-Core Version:    0.6.2
 */