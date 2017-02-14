package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.dpa.util.ToolKit;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.CopyCallback;
import com.alibaba.sdk.android.oss.callback.DeleteCallback;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.AuthenticationType;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.OSSFederationToken;
import com.alibaba.sdk.android.oss.model.ObjectMeta;
import com.alibaba.sdk.android.oss.model.OperationCode;
import com.alibaba.sdk.android.oss.model.Range;
import com.alibaba.sdk.android.oss.model.ResourceToQuery;
import com.alibaba.sdk.android.oss.util.OSSLog;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;

public class NormalDataObject extends BaseObject
{
  static
  {
    if (!NormalDataObject.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  protected NormalDataObject(OSSBucket paramOSSBucket, String paramString)
  {
    super(paramOSSBucket, paramString);
  }

  public void copyFrom(String paramString)
    throws OSSException
  {
    copyFrom(this.bucketName, paramString);
  }

  public void copyFrom(String paramString1, String paramString2)
    throws OSSException
  {
    this.method = BaseObject.HttpMethod.PUT;
    this.requestMeta.addXOSSMetaDirectly("x-oss-copy-source", "/" + paramString1 + "/" + paramString2);
    OSSToolKit.consumeResponseEntity(syncRequest(generateRequest()));
  }

  public void copyFromInBackgroud(String paramString, CopyCallback paramCopyCallback)
    throws OSSException
  {
    copyFromInBackgroud(this.bucketName, paramString, paramCopyCallback);
  }

  public void copyFromInBackgroud(String paramString1, String paramString2, CopyCallback paramCopyCallback)
    throws OSSException
  {
    this.method = BaseObject.HttpMethod.PUT;
    this.requestMeta.addXOSSMetaDirectly("x-oss-copy-source", "/" + paramString1 + "/" + paramString2);
    this.esService.execute(new OSSAsyncTask(this, OperationCode.COPY, paramCopyCallback));
  }

  public void delete()
    throws OSSException
  {
    this.method = BaseObject.HttpMethod.DELETE;
    OSSToolKit.consumeResponseEntity(syncRequest(generateRequest()));
  }

  public void deleteInBackground(DeleteCallback paramDeleteCallback)
  {
    this.method = BaseObject.HttpMethod.DELETE;
    this.esService.execute(new OSSAsyncTask(this, OperationCode.DELETE, paramDeleteCallback));
  }

  public void enableUploadCheckMd5sum()
  {
    this.checkUploadMd5sum = true;
  }

  public List<BasicNameValuePair> getMeta()
  {
    if (this.responseMeta == null)
      return null;
    return this.responseMeta.getMetaNameValues();
  }

  public InputStream getObjectInputStream()
    throws OSSException
  {
    this.method = BaseObject.HttpMethod.GET;
    Object localObject = syncRequest(generateRequest());
    try
    {
      localObject = ((HttpResponse)localObject).getEntity().getContent();
      return localObject;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      throw new OSSException(getBucketName(), this.objectKey, localIllegalStateException);
    }
    catch (IOException localIOException)
    {
      throw new OSSException(getBucketName(), this.objectKey, localIOException);
    }
  }

  public String getResourceURL()
  {
    assert (this.labeledBucket.getBucketACL() != AccessControlList.PRIVATE);
    String str2 = this.objectKey;
    try
    {
      str1 = URLEncoder.encode(str2, "UTF-8");
      if (OSSClient.getClientConfiguration().isSecurityTunnelRequired())
      {
        str2 = "https://";
        return str2 + this.labeledBucket.chooseProperHeaderHost(true) + "/" + str1;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        String str1 = str2;
        if (OSSLog.isEnableLog())
        {
          localUnsupportedEncodingException.printStackTrace();
          str1 = str2;
          continue;
          str2 = "http://";
        }
      }
    }
  }

  public String getResourceURL(String paramString, int paramInt)
  {
    if (getLabeledBucket().getBucketACL() != AccessControlList.PRIVATE)
      paramString = getResourceURL();
    while (true)
    {
      return paramString;
      String str = String.valueOf(OSSClient.getStandardEpochTimeInSecond() + paramInt);
      Object localObject1 = new ResourceToQuery();
      ((ResourceToQuery)localObject1).setBaseResource("/" + this.bucketName + "/" + this.objectKey);
      Object localObject2 = "";
      Object localObject4;
      Object localObject5;
      Object localObject6;
      Object localObject3;
      if (OSSClient.getAuthenticationType() == AuthenticationType.ORIGIN_AKSK)
      {
        localObject1 = this.labeledBucket.generateToken("GET", "", "", str, "", ((ResourceToQuery)localObject1).toCanoResource());
        localObject4 = paramString;
        paramString = (String)localObject2;
        localObject5 = localObject1;
        if (!ToolKit.isEmptyOrNullString((String)localObject1))
          localObject5 = ((String)localObject1).substring(((String)localObject1).indexOf(":") + 1);
        localObject6 = this.objectKey;
        localObject1 = localObject5;
        localObject2 = localObject6;
        localObject3 = localObject4;
      }
      try
      {
        localObject4 = URLEncoder.encode((String)localObject4, "UTF-8");
        localObject1 = localObject5;
        localObject2 = localObject6;
        localObject3 = localObject4;
        localObject7 = URLEncoder.encode((String)localObject5, "UTF-8");
        localObject1 = localObject7;
        localObject2 = localObject6;
        localObject3 = localObject4;
        localObject6 = URLEncoder.encode((String)localObject6, "UTF-8");
        localObject1 = localObject7;
        localObject2 = localObject6;
        localObject3 = localObject4;
        localObject5 = URLEncoder.encode(paramString, "UTF-8");
        if (OSSClient.getClientConfiguration().isSecurityTunnelRequired())
        {
          paramString = "https://";
          localObject1 = paramString.concat(this.labeledBucket.chooseProperHeaderHost(true)).concat("/").concat((String)localObject6).concat("?OSSAccessKeyId=").concat((String)localObject4).concat("&Expires=").concat(str).concat("&Signature=").concat((String)localObject7);
          paramString = (String)localObject1;
          if (OSSClient.getAuthenticationType() != AuthenticationType.FEDERATION_TOKEN)
            continue;
          return ((String)localObject1).concat("&security-token=" + (String)localObject5);
          localObject2 = OSSClient.getFederationToken();
          ((ResourceToQuery)localObject1).setQuery("security-token=" + ((OSSFederationToken)localObject2).getSecurityToken());
          localObject1 = this.labeledBucket.generateTokenWithFederationToken((OSSFederationToken)localObject2, "GET", "", "", str, "", ((ResourceToQuery)localObject1).toCanoResource());
          paramString = ((OSSFederationToken)localObject2).getSecurityToken();
          localObject4 = ((OSSFederationToken)localObject2).getTempAK();
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        while (true)
        {
          localObject5 = paramString;
          Object localObject7 = localObject1;
          localObject6 = localObject2;
          localObject4 = localObject3;
          if (OSSLog.isEnableLog())
          {
            localUnsupportedEncodingException.printStackTrace();
            localObject5 = paramString;
            localObject7 = localObject1;
            localObject6 = localObject2;
            localObject4 = localObject3;
            continue;
            paramString = "http://";
          }
        }
      }
    }
  }

  public void setRange(int paramInt1, int paramInt2)
  {
    setRange(new Range(paramInt1, paramInt2));
  }

  public void setRange(Range paramRange)
  {
    if (!paramRange.checkIsValid())
      throw new IllegalArgumentException("range is illegal, beg:" + paramRange.getBegin() + " end:" + paramRange.getEnd());
    this.range = paramRange;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.NormalDataObject
 * JD-Core Version:    0.6.2
 */