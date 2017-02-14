package com.mob.tools.network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MultiPart extends HTTPPart
{
  private ArrayList<HTTPPart> parts = new ArrayList();

  public MultiPart append(HTTPPart paramHTTPPart)
    throws Throwable
  {
    this.parts.add(paramHTTPPart);
    return this;
  }

  protected InputStream getInputStream()
    throws Throwable
  {
    MultiPartInputStream localMultiPartInputStream = new MultiPartInputStream();
    Iterator localIterator = this.parts.iterator();
    while (localIterator.hasNext())
      localMultiPartInputStream.addInputStream(((HTTPPart)localIterator.next()).getInputStream());
    return localMultiPartInputStream;
  }

  protected long length()
    throws Throwable
  {
    Iterator localIterator = this.parts.iterator();
    for (long l = 0L; localIterator.hasNext(); l = ((HTTPPart)localIterator.next()).length() + l);
    return l;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.parts.iterator();
    while (localIterator.hasNext())
      localStringBuilder.append(((HTTPPart)localIterator.next()).toString());
    return localStringBuilder.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.MultiPart
 * JD-Core Version:    0.6.2
 */