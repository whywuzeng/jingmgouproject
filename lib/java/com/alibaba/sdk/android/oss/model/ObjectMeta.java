package com.alibaba.sdk.android.oss.model;

import android.util.Pair;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class ObjectMeta extends XOSSMeta
{
  private String cacheControl = null;
  private String contentDisposition = null;
  private String contentEncoding = null;
  private String contentLength = null;
  private String contentMD5 = null;
  private String contentRange = null;
  private String contentType = null;
  private Date date = null;
  private String eTag = null;
  private Date expirationTime = null;
  private Date lastModified = null;
  private String server = null;

  public void addXOSSMetaHeader(String paramString1, String paramString2)
  {
    if (paramString1.toLowerCase().startsWith("x-oss-"))
      addXOSSMetaDirectly(paramString1, paramString2);
  }

  public String getCacheControl()
  {
    return this.cacheControl;
  }

  public String getContentDisposition()
  {
    return this.contentDisposition;
  }

  public String getContentEncoding()
  {
    return this.contentEncoding;
  }

  public String getContentLength()
  {
    return this.contentLength;
  }

  public String getContentMD5()
  {
    return this.contentMD5;
  }

  public String getContentRange()
  {
    return this.contentRange;
  }

  public String getContentType()
  {
    return this.contentType;
  }

  public Date getDate()
  {
    return this.date;
  }

  public Date getExpirationTime()
  {
    return this.expirationTime;
  }

  public Date getLastModified()
  {
    return this.lastModified;
  }

  public List<BasicNameValuePair> getMetaNameValues()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getXOSSMetaHeaderList().iterator();
    while (localIterator.hasNext())
    {
      Pair localPair = (Pair)localIterator.next();
      localArrayList.add(new BasicNameValuePair((String)localPair.first, (String)localPair.second));
    }
    if (this.cacheControl != null)
      localArrayList.add(new BasicNameValuePair("Cache-control", this.cacheControl));
    if (this.contentDisposition != null)
      localArrayList.add(new BasicNameValuePair("Content-Disposition", this.contentDisposition));
    if (this.contentEncoding != null)
      localArrayList.add(new BasicNameValuePair("Content-Encoding", this.contentEncoding));
    if (this.contentLength != null)
      localArrayList.add(new BasicNameValuePair("Content-Length", this.contentLength));
    if (this.contentType != null)
      localArrayList.add(new BasicNameValuePair("Content-Type", this.contentType));
    if (this.contentMD5 != null)
      localArrayList.add(new BasicNameValuePair("Content-MD5", this.contentMD5));
    if (this.date != null)
      localArrayList.add(new BasicNameValuePair("Date", OSSToolKit.date2GMTFormat(this.date)));
    if (this.eTag != null)
      localArrayList.add(new BasicNameValuePair("ETag", this.eTag));
    if (this.expirationTime != null)
      localArrayList.add(new BasicNameValuePair("Expires", OSSToolKit.date2GMTFormat(this.expirationTime)));
    if (this.lastModified != null)
      localArrayList.add(new BasicNameValuePair("Last-Modified", OSSToolKit.date2GMTFormat(this.lastModified)));
    if (this.server != null)
      localArrayList.add(new BasicNameValuePair("Server", this.server));
    return localArrayList;
  }

  public String getServer()
  {
    return this.server;
  }

  public String geteTag()
  {
    return this.eTag;
  }

  public void setCacheControl(String paramString)
  {
    this.cacheControl = paramString;
  }

  public void setContentDisposition(String paramString)
  {
    this.contentDisposition = paramString;
  }

  public void setContentEncoding(String paramString)
  {
    this.contentEncoding = paramString;
  }

  public void setContentLength(String paramString)
  {
    this.contentLength = paramString;
  }

  public void setContentMD5(String paramString)
  {
    this.contentMD5 = paramString;
  }

  public void setContentRange(String paramString)
  {
    this.contentRange = paramString;
  }

  public void setContentType(String paramString)
  {
    this.contentType = paramString;
  }

  public void setDate(Date paramDate)
  {
    this.date = paramDate;
  }

  public void setExpirationTime(Date paramDate)
  {
    this.expirationTime = paramDate;
  }

  public void setLastModified(Date paramDate)
  {
    this.lastModified = paramDate;
  }

  public void setServer(String paramString)
  {
    this.server = paramString;
  }

  public void seteTag(String paramString)
  {
    this.eTag = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.ObjectMeta
 * JD-Core Version:    0.6.2
 */