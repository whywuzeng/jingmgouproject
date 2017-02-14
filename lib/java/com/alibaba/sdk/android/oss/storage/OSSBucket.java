package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.dpa.util.HttpdnsMini;
import com.alibaba.sdk.android.dpa.util.ToolKit;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.ListObjectOption;
import com.alibaba.sdk.android.oss.model.ListObjectResult;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.OSSFederationToken;
import com.alibaba.sdk.android.oss.model.ResourceToQuery;
import com.alibaba.sdk.android.oss.model.SharedComponent;
import com.alibaba.sdk.android.oss.model.TokenGenerator;
import com.alibaba.sdk.android.oss.util.OSSLog;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

public class OSSBucket
{
  private AccessControlList bucketACL;
  private String bucketHostId;
  private String bucketName;
  private TokenGenerator bucketTokenGen;
  private String cdnHostId;
  private String refer;

  public OSSBucket(String paramString)
  {
    OSSToolKit.ensureBucketNameValid(paramString);
    this.bucketName = paramString;
    this.bucketTokenGen = OSSClient.getGlobalDefaultTokenGenerator();
    this.bucketACL = OSSClient.getGlobalDefaultACL();
    this.bucketHostId = OSSClient.getGlobalDefaultHostId();
    this.cdnHostId = null;
    this.refer = null;
  }

  private String chooseProperUrlHost(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.cdnHostId != null))
      return this.cdnHostId;
    return this.bucketHostId;
  }

  private boolean isOriginBucketHost(String paramString)
  {
    return (paramString.toLowerCase().endsWith(".aliyuncs.com")) || (paramString.toLowerCase().endsWith("oss-test.aliyun-inc.com"));
  }

  public String chooseProperHeaderHost(boolean paramBoolean)
  {
    String str2 = chooseProperUrlHost(paramBoolean);
    String str1 = str2;
    if (isOriginBucketHost(str2))
      str1 = this.bucketName + "." + str2;
    return str1;
  }

  public String generateToken(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    if (this.bucketTokenGen == null)
      throw new IllegalStateException("当前TokenGenerator为空！！！\n1. 请检查您是否在初始化时设置加签器;\n2. 请检查您获取bucket是否在设置加签器之后，或者已经为bucket单独设置加签器；\n3. 如果您bucket为公共权限，请确认初始化时您已经调用接口声明ACL;\n4. 如果您使用STS鉴权方式，请检查您是否已经在初始化声明鉴权方式。");
    return this.bucketTokenGen.generateToken(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6);
  }

  public String generateTokenWithFederationToken(OSSFederationToken paramOSSFederationToken, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    paramString1 = paramString1 + "\n" + paramString2 + "\n" + paramString3 + "\n" + paramString4 + "\n" + paramString5 + paramString6;
    return OSSToolKit.generateToken(paramOSSFederationToken.getTempAK(), paramOSSFederationToken.getTempSK(), paramString1);
  }

  public AccessControlList getBucketACL()
  {
    return this.bucketACL;
  }

  public String getBucketHostId()
  {
    return this.bucketHostId;
  }

  public String getBucketName()
  {
    return this.bucketName;
  }

  public TokenGenerator getBucketTokenGen()
  {
    return this.bucketTokenGen;
  }

  public String getCdnAccelerateHostId()
  {
    return this.cdnHostId;
  }

  public String getRefer()
  {
    return this.refer;
  }

  public ListObjectResult listObjectsInBucket(ListObjectOption paramListObjectOption)
    throws OSSException
  {
    String str = chooseProperHeaderHost(false);
    Object localObject1 = HttpdnsMini.getInstance().getIpByHostAsync(str);
    if ((localObject1 == null) || (OSSClient.detectIfProxyExist()))
      localObject1 = str;
    Object localObject2;
    if (OSSClient.getClientConfiguration().isSecurityTunnelRequired())
      localObject2 = "https://";
    while (true)
    {
      localObject1 = new StringBuilder().append((String)localObject2).append((String)localObject1).append("/");
      if (paramListObjectOption == null)
      {
        paramListObjectOption = "";
        label69: paramListObjectOption = paramListObjectOption;
        OSSLog.logD("[listObjectInBucket] - url: " + paramListObjectOption);
        paramListObjectOption = new HttpGet(paramListObjectOption);
        localObject1 = new ResourceToQuery();
        ((ResourceToQuery)localObject1).setBaseResource("/" + this.bucketName + "/");
        OSSToolKit.buildObjectListRequest(paramListObjectOption, this, (ResourceToQuery)localObject1);
        paramListObjectOption.setHeader("Host", str);
        if (OSSLog.isEnableLog())
          OSSToolKit.printRequestHeader(paramListObjectOption);
        localObject1 = SharedComponent.getSharedClient();
      }
      try
      {
        localObject1 = OSSToolKit.executeRequestWithLog((HttpClient)localObject1, paramListObjectOption);
        if ((((HttpResponse)localObject1).getStatusLine().getStatusCode() >= 200) && (((HttpResponse)localObject1).getStatusLine().getStatusCode() < 300))
        {
          localObject2 = OSSToolKit.parseObjectListResponse(((HttpResponse)localObject1).getEntity().getContent());
          OSSToolKit.consumeResponseEntity((HttpResponse)localObject1);
          return localObject2;
          localObject2 = "http://";
          continue;
          paramListObjectOption = paramListObjectOption.genQueryString();
          break label69;
        }
        else
        {
          throw OSSToolKit.handleExceptionalResponse((HttpResponse)localObject1, paramListObjectOption, this.bucketName, "");
        }
      }
      catch (Exception localException)
      {
      }
    }
    try
    {
      paramListObjectOption.abort();
      label272: if (OSSLog.isEnableLog())
        localException.printStackTrace();
      throw OSSToolKit.buildLocalException(this.bucketName, "", localException);
    }
    catch (Exception paramListObjectOption)
    {
      break label272;
    }
  }

  public void setBucketACL(AccessControlList paramAccessControlList)
  {
    this.bucketACL = paramAccessControlList;
  }

  public void setBucketAccessRefer(String paramString)
  {
    this.refer = paramString;
  }

  public void setBucketHostId(String paramString)
  {
    ToolKit.validateHostName(paramString);
    HttpdnsMini.getInstance().getIpByHostAsync(paramString);
    this.bucketHostId = paramString;
  }

  public void setBucketTokenGen(TokenGenerator paramTokenGenerator)
  {
    this.bucketTokenGen = paramTokenGenerator;
  }

  public void setCdnAccelerateHostId(String paramString)
  {
    ToolKit.validateHostName(paramString);
    HttpdnsMini.getInstance().getIpByHostAsync(paramString);
    this.cdnHostId = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.OSSBucket
 * JD-Core Version:    0.6.2
 */