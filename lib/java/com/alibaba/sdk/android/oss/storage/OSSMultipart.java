package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.dpa.util.HttpdnsMini;
import com.alibaba.sdk.android.dpa.util.ToolKit;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.ResourceToQuery;
import com.alibaba.sdk.android.oss.model.UploadPartInfo;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;

public class OSSMultipart extends BaseObject
{
  private List<UploadPartResult> completedUploadPartList = new ArrayList();
  private int currentPartNumber;
  private byte[] dataToUpload;
  private InputStream inputStreamToUpload;
  private long needToUploadFromStream;
  private List<UploadPartResult> partlist;
  private String uploadId;

  public OSSMultipart(OSSBucket paramOSSBucket, String paramString)
  {
    super(paramOSSBucket, paramString);
  }

  private HttpUriRequest buildInitialRequest(BaseObject.HttpMethod paramHttpMethod, boolean paramBoolean, ResourceToQuery paramResourceToQuery)
    throws UnsupportedEncodingException
  {
    String str4 = "/" + URLEncoder.encode(getObjectKey(), "UTF-8") + "?" + paramResourceToQuery.getOnlyUriQuery();
    String str3 = getLabeledBucket().chooseProperHeaderHost(false);
    String str1 = HttpdnsMini.getInstance().getIpByHostAsync(str3);
    if ((str1 == null) || (OSSClient.detectIfProxyExist()))
      str1 = str3;
    String str2;
    if (OSSClient.getClientConfiguration().isSecurityTunnelRequired())
    {
      str2 = "https://";
      str2 = str2 + str1 + str4;
      str1 = null;
      switch (1.$SwitchMap$com$alibaba$sdk$android$oss$storage$BaseObject$HttpMethod[paramHttpMethod.ordinal()])
      {
      default:
        paramHttpMethod = str1;
      case 1:
      case 2:
      case 3:
      case 4:
      }
    }
    while (true)
    {
      paramResourceToQuery.setBaseResource("/" + getBucketName() + "/" + getObjectKey());
      paramHttpMethod.setHeader("Host", str3);
      paramHttpMethod.setHeader("Content-Type", "");
      paramHttpMethod.setHeader("Accept-Encoding", "");
      if (!paramBoolean)
        break label298;
      OSSToolKit.buildRequestWithCompleteInfo(paramHttpMethod, this, paramResourceToQuery);
      return paramHttpMethod;
      str2 = "http://";
      break;
      paramHttpMethod = new HttpGet(str2);
      continue;
      paramHttpMethod = new HttpPut(str2);
      continue;
      paramHttpMethod = new HttpPost(str2);
      continue;
      paramHttpMethod = new HttpDelete(str2);
    }
    label298: OSSToolKit.buildMutilPartUploadRequest(paramHttpMethod, this, paramResourceToQuery);
    return paramHttpMethod;
  }

  private HttpResponse completeMultipartUploadHelper()
    throws OSSException
  {
    Object localObject1 = new ResourceToQuery();
    ((ResourceToQuery)localObject1).setQuery("uploadId=" + this.uploadId);
    try
    {
      localObject1 = (HttpPost)buildInitialRequest(BaseObject.HttpMethod.POST, true, (ResourceToQuery)localObject1);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("<CompleteMultipartUpload>\n");
      int i = 0;
      while (i < this.completedUploadPartList.size())
      {
        ((StringBuilder)localObject2).append("<Part>\n");
        ((StringBuilder)localObject2).append("<PartNumber>" + (i + 1) + "</PartNumber>\n");
        ((StringBuilder)localObject2).append("<ETag>" + ((UploadPartResult)this.completedUploadPartList.get(i)).geteTag() + "</ETag>\n");
        ((StringBuilder)localObject2).append("</Part>\n");
        i += 1;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw OSSToolKit.buildLocalException(this.labeledBucket.getBucketName(), this.objectKey, localUnsupportedEncodingException);
    }
    ((StringBuilder)localObject2).append("</CompleteMultipartUpload>\n");
    Object localObject2 = ((StringBuilder)localObject2).toString().getBytes();
    localUnsupportedEncodingException.setEntity(new InputStreamEntity(new ByteArrayInputStream((byte[])localObject2), localObject2.length));
    try
    {
      localObject2 = OSSToolKit.executeRequestWithLog(httpClient, localUnsupportedEncodingException);
      if (((HttpResponse)localObject2).getStatusLine().getStatusCode() != 200)
        throw OSSToolKit.handleExceptionalResponse((HttpResponse)localObject2, localUnsupportedEncodingException, getLabeledBucket().getBucketName(), getObjectKey());
    }
    catch (IOException localIOException)
    {
      throw OSSToolKit.buildLocalException(this.labeledBucket.getBucketName(), this.objectKey, localIOException);
    }
    return localObject2;
  }

  public void abortMultipartUpload()
    throws OSSException
  {
    Object localObject = new ResourceToQuery();
    ((ResourceToQuery)localObject).setQuery("uploadId=" + this.uploadId);
    try
    {
      localObject = (HttpDelete)buildInitialRequest(BaseObject.HttpMethod.DELETE, false, (ResourceToQuery)localObject);
      HttpResponse localHttpResponse = OSSToolKit.executeRequestWithLog(httpClient, (HttpUriRequest)localObject);
      if (localHttpResponse.getStatusLine().getStatusCode() == 204)
      {
        OSSToolKit.consumeResponseEntity(localHttpResponse);
        return;
      }
      throw OSSToolKit.handleExceptionalResponse(localHttpResponse, (HttpUriRequest)localObject, getBucketName(), getObjectKey());
    }
    catch (Exception localException)
    {
      if ((localException instanceof OSSException))
        throw ((OSSException)localException);
      throw OSSToolKit.buildLocalException(this.labeledBucket.getBucketName(), this.objectKey, localException);
    }
  }

  public void completeMultipartUpload()
    throws OSSException
  {
    OSSToolKit.consumeResponseEntity(completeMultipartUploadHelper());
  }

  public String completeMultipartUploadWithServerCallback()
    throws OSSException
  {
    Object localObject = completeMultipartUploadHelper();
    try
    {
      localObject = ToolKit.readFullyToString(((HttpResponse)localObject).getEntity().getContent());
      return localObject;
    }
    catch (Exception localException)
    {
      throw OSSToolKit.buildLocalException(this.labeledBucket.getBucketName(), this.objectKey, localException);
    }
  }

  public void designatePartList(List<UploadPartResult> paramList)
  {
    this.completedUploadPartList = paramList;
  }

  public void designateUploadId(String paramString)
  {
    this.uploadId = paramString;
  }

  public String initiateMultiPartUpload()
    throws OSSException
  {
    Object localObject = new ResourceToQuery();
    ((ResourceToQuery)localObject).setQuery("uploads");
    try
    {
      localObject = (HttpPost)buildInitialRequest(BaseObject.HttpMethod.POST, true, (ResourceToQuery)localObject);
      ((HttpPost)localObject).setEntity(new StringEntity("<init multipart upload>"));
      HttpResponse localHttpResponse = OSSToolKit.executeRequestWithLog(httpClient, (HttpUriRequest)localObject);
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        this.uploadId = OSSToolKit.parseUploadIdFromXml(localHttpResponse.getEntity().getContent());
        OSSToolKit.consumeResponseEntity(localHttpResponse);
        return this.uploadId;
      }
      throw OSSToolKit.handleExceptionalResponse(localHttpResponse, (HttpUriRequest)localObject, getBucketName(), getObjectKey());
    }
    catch (Exception localException)
    {
      if ((localException instanceof OSSException))
        throw ((OSSException)localException);
      throw OSSToolKit.buildLocalException(this.labeledBucket.getBucketName(), this.objectKey, localException);
    }
  }

  public List<UploadPartInfo> listParts()
    throws OSSException
  {
    Object localObject = new ResourceToQuery();
    ((ResourceToQuery)localObject).setQuery("uploadId=" + this.uploadId);
    try
    {
      localObject = (HttpGet)buildInitialRequest(BaseObject.HttpMethod.GET, false, (ResourceToQuery)localObject);
      HttpResponse localHttpResponse = OSSToolKit.executeRequestWithLog(httpClient, (HttpUriRequest)localObject);
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
        return OSSToolKit.parsePartListFromResponse(localHttpResponse.getEntity().getContent());
      throw OSSToolKit.handleExceptionalResponse(localHttpResponse, (HttpUriRequest)localObject, getBucketName(), getObjectKey());
    }
    catch (Exception localException)
    {
      if ((localException instanceof OSSException))
        throw ((OSSException)localException);
      throw OSSToolKit.buildLocalException(this.labeledBucket.getBucketName(), this.objectKey, localException);
    }
  }

  public void setUploadpart(int paramInt, InputStream paramInputStream, long paramLong)
  {
    this.dataToUpload = null;
    this.currentPartNumber = paramInt;
    this.inputStreamToUpload = paramInputStream;
    this.needToUploadFromStream = paramLong;
  }

  public void setUploadpart(int paramInt, byte[] paramArrayOfByte)
  {
    this.inputStreamToUpload = null;
    this.currentPartNumber = paramInt;
    this.dataToUpload = paramArrayOfByte;
  }

  // ERROR //
  public UploadPartResult uploadPart()
    throws OSSException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: lconst_0
    //   3: lstore_1
    //   4: aload_0
    //   5: getfield 328	com/alibaba/sdk/android/oss/storage/OSSMultipart:dataToUpload	[B
    //   8: ifnull +235 -> 243
    //   11: new 235	java/io/ByteArrayInputStream
    //   14: dup
    //   15: aload_0
    //   16: getfield 328	com/alibaba/sdk/android/oss/storage/OSSMultipart:dataToUpload	[B
    //   19: iconst_0
    //   20: aload_0
    //   21: getfield 328	com/alibaba/sdk/android/oss/storage/OSSMultipart:dataToUpload	[B
    //   24: arraylength
    //   25: invokespecial 340	java/io/ByteArrayInputStream:<init>	([BII)V
    //   28: astore_3
    //   29: aload_0
    //   30: getfield 328	com/alibaba/sdk/android/oss/storage/OSSMultipart:dataToUpload	[B
    //   33: arraylength
    //   34: i2l
    //   35: lstore_1
    //   36: new 233	org/apache/http/entity/InputStreamEntity
    //   39: dup
    //   40: aload_3
    //   41: lload_1
    //   42: invokespecial 241	org/apache/http/entity/InputStreamEntity:<init>	(Ljava/io/InputStream;J)V
    //   45: astore_3
    //   46: new 61	com/alibaba/sdk/android/oss/model/ResourceToQuery
    //   49: dup
    //   50: invokespecial 167	com/alibaba/sdk/android/oss/model/ResourceToQuery:<init>	()V
    //   53: astore 4
    //   55: aload 4
    //   57: new 38	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   64: ldc_w 342
    //   67: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload_0
    //   71: getfield 330	com/alibaba/sdk/android/oss/storage/OSSMultipart:currentPartNumber	I
    //   74: invokevirtual 194	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   77: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   80: invokevirtual 174	com/alibaba/sdk/android/oss/model/ResourceToQuery:setQuery	(Ljava/lang/String;)V
    //   83: aload 4
    //   85: new 38	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   92: ldc 169
    //   94: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload_0
    //   98: getfield 171	com/alibaba/sdk/android/oss/storage/OSSMultipart:uploadId	Ljava/lang/String;
    //   101: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: invokevirtual 174	com/alibaba/sdk/android/oss/model/ResourceToQuery:setQuery	(Ljava/lang/String;)V
    //   110: aload_0
    //   111: getstatic 345	com/alibaba/sdk/android/oss/storage/BaseObject$HttpMethod:PUT	Lcom/alibaba/sdk/android/oss/storage/BaseObject$HttpMethod;
    //   114: iconst_0
    //   115: aload 4
    //   117: invokespecial 180	com/alibaba/sdk/android/oss/storage/OSSMultipart:buildInitialRequest	(Lcom/alibaba/sdk/android/oss/storage/BaseObject$HttpMethod;ZLcom/alibaba/sdk/android/oss/model/ResourceToQuery;)Lorg/apache/http/client/methods/HttpUriRequest;
    //   120: checkcast 149	org/apache/http/client/methods/HttpPut
    //   123: astore 4
    //   125: aload 4
    //   127: aload_3
    //   128: invokevirtual 346	org/apache/http/client/methods/HttpPut:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   131: getstatic 249	com/alibaba/sdk/android/oss/storage/OSSMultipart:httpClient	Lorg/apache/http/client/HttpClient;
    //   134: aload 4
    //   136: invokeinterface 352 2 0
    //   141: astore_3
    //   142: aload_3
    //   143: invokeinterface 259 1 0
    //   148: invokeinterface 264 1 0
    //   153: sipush 200
    //   156: if_icmpne +141 -> 297
    //   159: aload_3
    //   160: ldc_w 354
    //   163: invokeinterface 358 2 0
    //   168: invokeinterface 363 1 0
    //   173: astore 4
    //   175: new 38	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   182: ldc_w 365
    //   185: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: aload 4
    //   190: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: invokestatic 370	com/alibaba/sdk/android/oss/util/OSSLog:logD	(Ljava/lang/String;)V
    //   199: new 204	com/alibaba/sdk/android/oss/model/UploadPartResult
    //   202: dup
    //   203: invokespecial 371	com/alibaba/sdk/android/oss/model/UploadPartResult:<init>	()V
    //   206: astore 5
    //   208: aload 5
    //   210: aload_0
    //   211: getfield 330	com/alibaba/sdk/android/oss/storage/OSSMultipart:currentPartNumber	I
    //   214: invokevirtual 375	com/alibaba/sdk/android/oss/model/UploadPartResult:setPartNumber	(I)V
    //   217: aload 5
    //   219: aload 4
    //   221: invokevirtual 378	com/alibaba/sdk/android/oss/model/UploadPartResult:seteTag	(Ljava/lang/String;)V
    //   224: aload_0
    //   225: getfield 31	com/alibaba/sdk/android/oss/storage/OSSMultipart:completedUploadPartList	Ljava/util/List;
    //   228: aload 5
    //   230: invokeinterface 382 2 0
    //   235: pop
    //   236: aload_3
    //   237: invokestatic 278	com/alibaba/sdk/android/oss/util/OSSToolKit:consumeResponseEntity	(Lorg/apache/http/HttpResponse;)V
    //   240: aload 5
    //   242: areturn
    //   243: aload_0
    //   244: getfield 332	com/alibaba/sdk/android/oss/storage/OSSMultipart:inputStreamToUpload	Ljava/io/InputStream;
    //   247: ifnull -211 -> 36
    //   250: aload_0
    //   251: getfield 332	com/alibaba/sdk/android/oss/storage/OSSMultipart:inputStreamToUpload	Ljava/io/InputStream;
    //   254: astore_3
    //   255: aload_0
    //   256: getfield 334	com/alibaba/sdk/android/oss/storage/OSSMultipart:needToUploadFromStream	J
    //   259: lstore_1
    //   260: goto -224 -> 36
    //   263: astore_3
    //   264: aload_0
    //   265: getfield 215	com/alibaba/sdk/android/oss/storage/OSSMultipart:labeledBucket	Lcom/alibaba/sdk/android/oss/storage/OSSBucket;
    //   268: invokevirtual 216	com/alibaba/sdk/android/oss/storage/OSSBucket:getBucketName	()Ljava/lang/String;
    //   271: aload_0
    //   272: getfield 219	com/alibaba/sdk/android/oss/storage/OSSMultipart:objectKey	Ljava/lang/String;
    //   275: aload_3
    //   276: invokestatic 223	com/alibaba/sdk/android/oss/util/OSSToolKit:buildLocalException	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)Lcom/alibaba/sdk/android/oss/model/OSSException;
    //   279: athrow
    //   280: astore_3
    //   281: aload_0
    //   282: getfield 215	com/alibaba/sdk/android/oss/storage/OSSMultipart:labeledBucket	Lcom/alibaba/sdk/android/oss/storage/OSSBucket;
    //   285: invokevirtual 216	com/alibaba/sdk/android/oss/storage/OSSBucket:getBucketName	()Ljava/lang/String;
    //   288: aload_0
    //   289: getfield 219	com/alibaba/sdk/android/oss/storage/OSSMultipart:objectKey	Ljava/lang/String;
    //   292: aload_3
    //   293: invokestatic 223	com/alibaba/sdk/android/oss/util/OSSToolKit:buildLocalException	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)Lcom/alibaba/sdk/android/oss/model/OSSException;
    //   296: athrow
    //   297: aload_3
    //   298: aload 4
    //   300: aload_0
    //   301: invokevirtual 117	com/alibaba/sdk/android/oss/storage/OSSMultipart:getBucketName	()Ljava/lang/String;
    //   304: aload_0
    //   305: invokevirtual 49	com/alibaba/sdk/android/oss/storage/OSSMultipart:getObjectKey	()Ljava/lang/String;
    //   308: invokestatic 268	com/alibaba/sdk/android/oss/util/OSSToolKit:handleExceptionalResponse	(Lorg/apache/http/HttpResponse;Lorg/apache/http/client/methods/HttpUriRequest;Ljava/lang/String;Ljava/lang/String;)Lcom/alibaba/sdk/android/oss/model/OSSException;
    //   311: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   110	125	263	java/io/UnsupportedEncodingException
    //   131	142	280	java/io/IOException
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.OSSMultipart
 * JD-Core Version:    0.6.2
 */