package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.RawNetworkCallback;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.WeakHashMap;

public class BitmapProcessor
{
  private static final int CAPACITY = 3;
  private static final int MAX_REQ_TIME = 200;
  private static final int MAX_SIZE = 40;
  private static final int OVERFLOW_SIZE = 50;
  private static BitmapProcessor instance;
  private File cacheDir;
  private WeakHashMap<String, Bitmap> cacheMap = new WeakHashMap();
  private ManagerThread manager;
  private Vector<ImageReq> netReqTPS = new Vector();
  private Vector<ImageReq> reqList = new Vector();
  private boolean work;
  private WorkerThread[] workerList = new WorkerThread[3];

  private BitmapProcessor(Context paramContext)
  {
    this.cacheDir = new File(R.getImageCachePath(paramContext));
    this.manager = new ManagerThread(this);
  }

  public static Bitmap getBitmapFromCache(String paramString)
  {
    if (instance == null)
      throw new RuntimeException("Call BitmapProcessor.prepare(String) before start");
    return (Bitmap)instance.cacheMap.get(paramString);
  }

  public static void prepare(Context paramContext)
  {
    try
    {
      if (instance == null)
        instance = new BitmapProcessor(paramContext.getApplicationContext());
      return;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  public static void process(String paramString, BitmapCallback paramBitmapCallback)
  {
    if (instance == null)
      throw new RuntimeException("Call BitmapProcessor.prepare(String) before start");
    if (paramString == null)
      return;
    ImageReq localImageReq = new ImageReq();
    ImageReq.access$002(localImageReq, paramString);
    ImageReq.access$102(localImageReq, paramBitmapCallback);
    instance.reqList.add(localImageReq);
    if (instance.reqList.size() > 50)
      while (instance.reqList.size() > 40)
        instance.reqList.remove(0);
    start();
  }

  public static void start()
  {
    if (instance == null)
      throw new RuntimeException("Call BitmapProcessor.prepare(String) before start");
    instance.work = true;
  }

  public static void stop()
  {
    int i = 0;
    if (instance != null)
    {
      instance.work = false;
      instance.reqList.clear();
      instance.manager.cancel();
      while (i < instance.workerList.length)
      {
        if (instance.workerList[i] != null)
          instance.workerList[i].interrupt();
        i += 1;
      }
      instance = null;
    }
  }

  public static abstract interface BitmapCallback
  {
    public abstract void onImageGot(String paramString, Bitmap paramBitmap);
  }

  public static class ImageReq
  {
    private BitmapProcessor.BitmapCallback callback;
    private Bitmap image;
    private long reqTime = System.currentTimeMillis();
    private String url;
    private BitmapProcessor.WorkerThread worker;

    private void throwComplete(Bitmap paramBitmap)
    {
      this.image = paramBitmap;
      if (this.callback != null)
        this.callback.onImageGot(this.url, this.image);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("url=").append(this.url);
      localStringBuilder.append("time=").append(this.reqTime);
      localStringBuilder.append("worker=").append(this.worker.getName()).append(" (").append(this.worker.getId()).append("");
      return localStringBuilder.toString();
    }
  }

  private static class ManagerThread extends Timer
  {
    private BitmapProcessor processor;

    public ManagerThread(BitmapProcessor paramBitmapProcessor)
    {
      this.processor = paramBitmapProcessor;
      schedule(new TimerTask()
      {
        private int counter;

        public void run()
        {
          if (BitmapProcessor.this.work)
          {
            this.counter -= 1;
            if (this.counter <= 0)
            {
              this.counter = 100;
              BitmapProcessor.ManagerThread.this.scan();
            }
          }
        }
      }
      , 0L, 200L);
    }

    private void scan()
    {
      if (!this.processor.work)
        return;
      long l = System.currentTimeMillis();
      int i = 0;
      label17: boolean bool;
      if (i < this.processor.workerList.length)
      {
        if (this.processor.workerList[i] != null)
          break label143;
        this.processor.workerList[i] = new BitmapProcessor.WorkerThread(this.processor);
        this.processor.workerList[i].setName("worker " + i);
        BitmapProcessor.WorkerThread localWorkerThread = this.processor.workerList[i];
        if (i != 0)
          break label137;
        bool = true;
        label110: BitmapProcessor.WorkerThread.access$602(localWorkerThread, bool);
        this.processor.workerList[i].start();
      }
      while (true)
      {
        i += 1;
        break label17;
        break;
        label137: bool = false;
        break label110;
        label143: if (l - BitmapProcessor.WorkerThread.access$700(this.processor.workerList[i]) > 20000L)
        {
          this.processor.workerList[i].interrupt();
          bool = BitmapProcessor.WorkerThread.access$600(this.processor.workerList[i]);
          this.processor.workerList[i] = new BitmapProcessor.WorkerThread(this.processor);
          this.processor.workerList[i].setName("worker " + i);
          BitmapProcessor.WorkerThread.access$602(this.processor.workerList[i], bool);
          this.processor.workerList[i].start();
        }
      }
    }
  }

  private static class PatchInputStream extends FilterInputStream
  {
    InputStream in;

    protected PatchInputStream(InputStream paramInputStream)
    {
      super();
      this.in = paramInputStream;
    }

    public long skip(long paramLong)
      throws IOException
    {
      long l2;
      for (long l1 = 0L; ; l1 += l2)
        if (l1 < paramLong)
        {
          l2 = this.in.skip(paramLong - l1);
          if (l2 != 0L);
        }
        else
        {
          return l1;
        }
    }
  }

  private static class WorkerThread extends Thread
  {
    private BitmapProcessor.ImageReq curReq;
    private long lastReport;
    private boolean localType;
    private BitmapProcessor processor;

    public WorkerThread(BitmapProcessor paramBitmapProcessor)
    {
      this.processor = paramBitmapProcessor;
      this.lastReport = System.currentTimeMillis();
    }

    private void doLocalTask()
      throws Throwable
    {
      int i = this.processor.reqList.size();
      if (i > 0);
      for (BitmapProcessor.ImageReq localImageReq = (BitmapProcessor.ImageReq)this.processor.reqList.remove(i - 1); ; localImageReq = null)
      {
        if (localImageReq != null)
        {
          Bitmap localBitmap = (Bitmap)this.processor.cacheMap.get(localImageReq.url);
          if (localBitmap != null)
          {
            this.curReq = localImageReq;
            BitmapProcessor.ImageReq.access$1002(this.curReq, this);
            localImageReq.throwComplete(localBitmap);
          }
          while (true)
          {
            this.lastReport = System.currentTimeMillis();
            return;
            if (new File(this.processor.cacheDir, Data.MD5(localImageReq.url)).exists())
            {
              doTask(localImageReq);
              this.lastReport = System.currentTimeMillis();
              return;
            }
            if (this.processor.netReqTPS.size() > 40)
            {
              while (this.processor.reqList.size() > 0)
                this.processor.reqList.remove(0);
              this.processor.netReqTPS.remove(0);
            }
            this.processor.netReqTPS.add(localImageReq);
          }
        }
        this.lastReport = System.currentTimeMillis();
        Thread.sleep(30L);
        return;
      }
    }

    private void doNetworkTask()
      throws Throwable
    {
      BitmapProcessor.ImageReq localImageReq = null;
      if (this.processor.netReqTPS.size() > 0)
        localImageReq = (BitmapProcessor.ImageReq)this.processor.netReqTPS.remove(0);
      if (localImageReq == null)
      {
        int i = this.processor.reqList.size();
        if (i > 0)
          localImageReq = (BitmapProcessor.ImageReq)this.processor.reqList.remove(i - 1);
      }
      while (true)
      {
        if (localImageReq != null)
        {
          Bitmap localBitmap = (Bitmap)this.processor.cacheMap.get(localImageReq.url);
          if (localBitmap != null)
          {
            this.curReq = localImageReq;
            BitmapProcessor.ImageReq.access$1002(this.curReq, this);
            localImageReq.throwComplete(localBitmap);
          }
          while (true)
          {
            this.lastReport = System.currentTimeMillis();
            return;
            doTask(localImageReq);
          }
        }
        this.lastReport = System.currentTimeMillis();
        Thread.sleep(30L);
        return;
      }
    }

    private void doTask(final BitmapProcessor.ImageReq paramImageReq)
      throws Throwable
    {
      this.curReq = paramImageReq;
      BitmapProcessor.ImageReq.access$1002(this.curReq, this);
      final boolean bool;
      Object localObject;
      if ((paramImageReq.url.toLowerCase().endsWith("png")) || (paramImageReq.url.toLowerCase().endsWith("gif")))
      {
        bool = true;
        localObject = new File(this.processor.cacheDir, Data.MD5(paramImageReq.url));
        if (!((File)localObject).exists())
          break label149;
        localObject = BitmapHelper.getBitmap(((File)localObject).getAbsolutePath());
        if (localObject != null)
        {
          this.processor.cacheMap.put(paramImageReq.url, localObject);
          paramImageReq.throwComplete((Bitmap)localObject);
        }
        this.curReq = null;
      }
      while (true)
      {
        if (localObject != null)
        {
          this.processor.cacheMap.put(paramImageReq.url, localObject);
          paramImageReq.throwComplete((Bitmap)localObject);
        }
        this.curReq = null;
        return;
        bool = false;
        break;
        label149: new NetworkHelper().rawGet(paramImageReq.url, new RawNetworkCallback()
        {
          public void onResponse(InputStream paramAnonymousInputStream)
            throws Throwable
          {
            paramAnonymousInputStream = BitmapHelper.getBitmap(new BitmapProcessor.PatchInputStream(paramAnonymousInputStream), 1);
            if ((paramAnonymousInputStream == null) || (paramAnonymousInputStream.isRecycled()))
            {
              BitmapProcessor.WorkerThread.access$1402(BitmapProcessor.WorkerThread.this, null);
              return;
            }
            BitmapProcessor.WorkerThread.this.saveFile(paramAnonymousInputStream, this.val$file, bool);
            if (paramAnonymousInputStream != null)
            {
              BitmapProcessor.this.cacheMap.put(paramImageReq.url, paramAnonymousInputStream);
              paramImageReq.throwComplete(paramAnonymousInputStream);
            }
            BitmapProcessor.WorkerThread.access$1402(BitmapProcessor.WorkerThread.this, null);
          }
        });
        localObject = null;
      }
    }

    private void saveFile(Bitmap paramBitmap, File paramFile, boolean paramBoolean)
    {
      try
      {
        if (paramFile.exists())
          paramFile.delete();
        if (!paramFile.getParentFile().exists())
          paramFile.getParentFile().mkdirs();
        paramFile.createNewFile();
        if (paramBoolean);
        for (Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.PNG; ; localCompressFormat = Bitmap.CompressFormat.JPEG)
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
          paramBitmap.compress(localCompressFormat, 100, localFileOutputStream);
          localFileOutputStream.flush();
          localFileOutputStream.close();
          return;
        }
      }
      catch (Throwable paramBitmap)
      {
        if (paramFile.exists())
          paramFile.delete();
      }
    }

    public void interrupt()
    {
      try
      {
        super.interrupt();
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }

    public void run()
    {
      while (this.processor.work)
      {
        try
        {
          if (!this.localType)
            break label33;
          doLocalTask();
        }
        catch (Throwable localThrowable)
        {
          Ln.w(localThrowable);
        }
        continue;
        label33: doNetworkTask();
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.BitmapProcessor
 * JD-Core Version:    0.6.2
 */