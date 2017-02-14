package net.tsz.afinal.http.entityhandler;

import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;

public class FileEntityHandler
{
  private boolean mStop = false;

  public Object handleEntity(HttpEntity paramHttpEntity, EntityCallBack paramEntityCallBack, String paramString, boolean paramBoolean)
    throws IOException
  {
    Object localObject;
    if ((TextUtils.isEmpty(paramString)) || (paramString.trim().length() == 0))
      localObject = null;
    File localFile;
    long l1;
    label83: InputStream localInputStream;
    long l2;
    do
    {
      do
      {
        do
        {
          do
          {
            return localObject;
            localFile = new File(paramString);
            if (!localFile.exists())
              localFile.createNewFile();
            localObject = localFile;
          }
          while (this.mStop);
          l1 = 0L;
          if (!paramBoolean)
            break;
          l1 = localFile.length();
          paramString = new FileOutputStream(paramString, true);
          localObject = localFile;
        }
        while (this.mStop);
        localInputStream = paramHttpEntity.getContent();
        l2 = paramHttpEntity.getContentLength() + l1;
        localObject = localFile;
      }
      while (l1 >= l2);
      localObject = localFile;
    }
    while (this.mStop);
    paramHttpEntity = new byte[1024];
    while (true)
    {
      int i;
      if ((!this.mStop) && (l1 < l2))
      {
        i = localInputStream.read(paramHttpEntity, 0, 1024);
        if (i > 0);
      }
      else
      {
        paramEntityCallBack.callBack(l2, l1, true);
        localObject = localFile;
        if (!this.mStop)
          break;
        localObject = localFile;
        if (l1 >= l2)
          break;
        throw new IOException("user stop download thread");
        paramString = new FileOutputStream(paramString);
        break label83;
      }
      paramString.write(paramHttpEntity, 0, i);
      l1 += i;
      paramEntityCallBack.callBack(l2, l1, false);
    }
  }

  public boolean isStop()
  {
    return this.mStop;
  }

  public void setStop(boolean paramBoolean)
  {
    this.mStop = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.entityhandler.FileEntityHandler
 * JD-Core Version:    0.6.2
 */