package com.ab.download;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.ab.global.AbAppData;
import com.ab.util.AbFileUtil;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AbFileDownloader
{
  private static final boolean D = AbAppData.DEBUG;
  private static final String TAG = "FileDownloader";
  private Context context;
  private boolean flag = true;
  private DownFile mDownFile = null;
  private DownFileDao mDownFileDao;
  private int mThreadNum = 1;
  private File saveFile;
  private AbDownloadThread threads;

  public AbFileDownloader(Context paramContext, DownFile paramDownFile, int paramInt)
  {
    try
    {
      this.context = paramContext;
      this.mDownFile = paramDownFile;
      this.mThreadNum = paramInt;
      this.mDownFileDao = new DownFileDao(paramContext);
      paramContext = AbFileUtil.getFileNameFromUrl(this.mDownFile.getDownUrl());
      this.saveFile = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + AbFileUtil.getDownPathFileDir() + paramContext);
      if (!this.saveFile.getParentFile().exists())
        this.saveFile.getParentFile().mkdirs();
      if (!this.saveFile.exists())
      {
        this.saveFile.createNewFile();
        this.mDownFileDao.delete(paramDownFile.getDownUrl());
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static Map<String, String> getHttpResponseHeader(HttpURLConnection paramHttpURLConnection)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    int i = 0;
    while (true)
    {
      String str = paramHttpURLConnection.getHeaderField(i);
      if (str == null)
        return localLinkedHashMap;
      localLinkedHashMap.put(paramHttpURLConnection.getHeaderFieldKey(i), str);
      i += 1;
    }
  }

  public static void printResponseHeader(HttpURLConnection paramHttpURLConnection)
  {
    Iterator localIterator = getHttpResponseHeader(paramHttpURLConnection).entrySet().iterator();
    if (!localIterator.hasNext())
      return;
    Map.Entry localEntry = (Map.Entry)localIterator.next();
    if (localEntry.getKey() != null);
    for (paramHttpURLConnection = (String)localEntry.getKey() + ":"; ; paramHttpURLConnection = "")
    {
      Log.i("FileDownloader", paramHttpURLConnection + (String)localEntry.getValue());
      break;
    }
  }

  public void download(AbDownloadProgressListener paramAbDownloadProgressListener)
    throws Exception
  {
    try
    {
      this.threads = new AbDownloadThread(this, this.mDownFile, this.saveFile);
      this.threads.setPriority(7);
      this.threads.start();
      this.mDownFileDao.save(this.mDownFile);
      while (this.flag)
      {
        if (this.mDownFile.getDownLength() > this.mDownFile.getTotalLength())
          return;
        Thread.sleep(2000L);
        if (this.mDownFile.getDownLength() != -1L)
        {
          if (paramAbDownloadProgressListener != null)
            paramAbDownloadProgressListener.onDownloadSize(this.mDownFile.getDownLength());
          long l1 = this.mDownFile.getDownLength();
          long l2 = this.mDownFile.getTotalLength();
          if (l1 == l2);
        }
      }
    }
    catch (Exception paramAbDownloadProgressListener)
    {
      paramAbDownloadProgressListener.printStackTrace();
    }
  }

  public boolean getFlag()
  {
    return this.flag;
  }

  public File getSaveFile()
  {
    return this.saveFile;
  }

  public AbDownloadThread getThreads()
  {
    return this.threads;
  }

  public void setFlag(boolean paramBoolean)
  {
    this.flag = paramBoolean;
  }

  protected void update(DownFile paramDownFile)
  {
    try
    {
      this.mDownFileDao.update(paramDownFile);
      return;
    }
    finally
    {
      paramDownFile = finally;
    }
    throw paramDownFile;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.download.AbFileDownloader
 * JD-Core Version:    0.6.2
 */