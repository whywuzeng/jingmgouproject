package org.apache.http.entity.mime.content;

import java.io.IOException;
import java.io.OutputStream;

public abstract interface ContentBody extends ContentDescriptor
{
  public abstract String getFilename();

  public abstract void writeTo(OutputStream paramOutputStream)
    throws IOException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.apache.http.entity.mime.content.ContentBody
 * JD-Core Version:    0.6.2
 */