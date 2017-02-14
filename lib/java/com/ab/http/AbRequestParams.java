package com.ab.http;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class AbRequestParams
{
  private static final String TAG = "AbRequestParams";
  protected ConcurrentHashMap<String, FileWrapper> fileParams;
  protected ConcurrentHashMap<String, String> urlParams;

  public AbRequestParams()
  {
    init();
  }

  public AbRequestParams(String paramString1, String paramString2)
  {
    init();
    put(paramString1, paramString2);
  }

  public AbRequestParams(Map<String, String> paramMap)
  {
    init();
    paramMap = paramMap.entrySet().iterator();
    while (true)
    {
      if (!paramMap.hasNext())
        return;
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      put((String)localEntry.getKey(), (String)localEntry.getValue());
    }
  }

  private HttpEntity createMultipartEntity(AbHttpResponseListener paramAbHttpResponseListener)
    throws IOException
  {
    paramAbHttpResponseListener = new AbMultipartEntity(paramAbHttpResponseListener);
    Iterator localIterator = this.urlParams.entrySet().iterator();
    if (!localIterator.hasNext())
      localIterator = this.fileParams.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return paramAbHttpResponseListener;
        localEntry = (Map.Entry)localIterator.next();
        paramAbHttpResponseListener.addPart((String)localEntry.getKey(), (String)localEntry.getValue());
        break;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      FileWrapper localFileWrapper = (FileWrapper)localEntry.getValue();
      paramAbHttpResponseListener.addPart((String)localEntry.getKey(), localFileWrapper.file, localFileWrapper.contentType);
    }
  }

  private void init()
  {
    this.urlParams = new ConcurrentHashMap();
    this.fileParams = new ConcurrentHashMap();
  }

  public HttpEntity createFormEntity()
  {
    try
    {
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(getParamsList(), "UTF-8");
      return localUrlEncodedFormEntity;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return null;
  }

  public HttpEntity getEntity(AbHttpResponseListener paramAbHttpResponseListener)
    throws IOException
  {
    if (this.fileParams.isEmpty())
      return createFormEntity();
    return createMultipartEntity(paramAbHttpResponseListener);
  }

  public String getParamString()
  {
    return URLEncodedUtils.format(getParamsList(), "UTF-8");
  }

  public List<BasicNameValuePair> getParamsList()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.urlParams.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localLinkedList;
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
  }

  public ConcurrentHashMap<String, String> getUrlParams()
  {
    return this.urlParams;
  }

  public void put(String paramString, File paramFile)
  {
    put(paramString, paramFile, "application/octet-stream");
  }

  public void put(String paramString1, File paramFile, String paramString2)
  {
    if ((paramString1 != null) && (paramFile != null))
      this.fileParams.put(paramString1, new FileWrapper(paramFile, paramString2));
  }

  public void put(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
      this.urlParams.put(paramString1, paramString2);
  }

  public void remove(String paramString)
  {
    this.urlParams.remove(paramString);
    this.fileParams.remove(paramString);
  }

  public void setUrlParams(ConcurrentHashMap<String, String> paramConcurrentHashMap)
  {
    this.urlParams = paramConcurrentHashMap;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.urlParams.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localStringBuilder.toString();
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      localStringBuilder.append((String)localEntry.getValue());
    }
  }

  private static class FileWrapper
  {
    public String contentType;
    public File file;

    public FileWrapper(File paramFile, String paramString)
    {
      this.file = paramFile;
      this.contentType = paramString;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.http.AbRequestParams
 * JD-Core Version:    0.6.2
 */