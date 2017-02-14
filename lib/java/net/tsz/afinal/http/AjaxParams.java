package net.tsz.afinal.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

public class AjaxParams
{
  private static String ENCODING = "UTF-8";
  protected ConcurrentHashMap<String, FileWrapper> fileParams;
  protected ConcurrentHashMap<String, String> urlParams;

  public AjaxParams()
  {
    init();
  }

  public AjaxParams(String paramString1, String paramString2)
  {
    init();
    put(paramString1, paramString2);
  }

  public AjaxParams(Map<String, String> paramMap)
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

  public AjaxParams(Object[] paramArrayOfObject)
  {
    init();
    int j = paramArrayOfObject.length;
    if (j % 2 != 0)
      throw new IllegalArgumentException("Supplied arguments must be even");
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      put(String.valueOf(paramArrayOfObject[i]), String.valueOf(paramArrayOfObject[(i + 1)]));
      i += 2;
    }
  }

  private void init()
  {
    this.urlParams = new ConcurrentHashMap();
    this.fileParams = new ConcurrentHashMap();
  }

  public HttpEntity getEntity()
  {
    Object localObject;
    if (!this.fileParams.isEmpty())
    {
      localObject = new MultipartEntity();
      Iterator localIterator = this.urlParams.entrySet().iterator();
      int i;
      int j;
      while (true)
      {
        if (!localIterator.hasNext())
        {
          i = 0;
          j = this.fileParams.entrySet().size();
          localIterator = this.fileParams.entrySet().iterator();
          if (localIterator.hasNext())
            break;
          return localObject;
        }
        localEntry = (Map.Entry)localIterator.next();
        ((MultipartEntity)localObject).addPart((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      FileWrapper localFileWrapper = (FileWrapper)localEntry.getValue();
      boolean bool;
      if (localFileWrapper.inputStream != null)
      {
        if (i != j - 1)
          break label212;
        bool = true;
        label166: if (localFileWrapper.contentType == null)
          break label217;
        ((MultipartEntity)localObject).addPart((String)localEntry.getKey(), localFileWrapper.getFileName(), localFileWrapper.inputStream, localFileWrapper.contentType, bool);
      }
      while (true)
      {
        i += 1;
        break;
        label212: bool = false;
        break label166;
        label217: ((MultipartEntity)localObject).addPart((String)localEntry.getKey(), localFileWrapper.getFileName(), localFileWrapper.inputStream, bool);
      }
    }
    try
    {
      localObject = new UrlEncodedFormEntity(getParamsList(), ENCODING);
      return localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }

  public String getParamString()
  {
    return URLEncodedUtils.format(getParamsList(), ENCODING);
  }

  protected List<BasicNameValuePair> getParamsList()
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

  public void put(String paramString, File paramFile)
    throws FileNotFoundException
  {
    put(paramString, new FileInputStream(paramFile), paramFile.getName());
  }

  public void put(String paramString, InputStream paramInputStream)
  {
    put(paramString, paramInputStream, null);
  }

  public void put(String paramString1, InputStream paramInputStream, String paramString2)
  {
    put(paramString1, paramInputStream, paramString2, null);
  }

  public void put(String paramString1, InputStream paramInputStream, String paramString2, String paramString3)
  {
    if ((paramString1 != null) && (paramInputStream != null))
      this.fileParams.put(paramString1, new FileWrapper(paramInputStream, paramString2, paramString3));
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

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.urlParams.entrySet().iterator();
    if (!localIterator.hasNext())
      localIterator = this.fileParams.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return localStringBuilder.toString();
        localEntry = (Map.Entry)localIterator.next();
        if (localStringBuilder.length() > 0)
          localStringBuilder.append("&");
        localStringBuilder.append((String)localEntry.getKey());
        localStringBuilder.append("=");
        localStringBuilder.append((String)localEntry.getValue());
        break;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      localStringBuilder.append("FILE");
    }
  }

  private static class FileWrapper
  {
    public String contentType;
    public String fileName;
    public InputStream inputStream;

    public FileWrapper(InputStream paramInputStream, String paramString1, String paramString2)
    {
      this.inputStream = paramInputStream;
      this.fileName = paramString1;
      this.contentType = paramString2;
    }

    public String getFileName()
    {
      if (this.fileName != null)
        return this.fileName;
      return "nofilename";
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.AjaxParams
 * JD-Core Version:    0.6.2
 */