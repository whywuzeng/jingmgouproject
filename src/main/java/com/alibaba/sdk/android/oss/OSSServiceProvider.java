package com.alibaba.sdk.android.oss;

import android.content.Context;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.AuthenticationType;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.StsTokenGetter;
import com.alibaba.sdk.android.oss.model.TokenGenerator;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.alibaba.sdk.android.oss.storage.OSSData;
import com.alibaba.sdk.android.oss.storage.OSSFile;
import com.alibaba.sdk.android.oss.storage.OSSMeta;
import com.alibaba.sdk.android.oss.storage.OSSMultipart;

public class OSSServiceProvider
  implements OSSService
{
  private static OSSServiceProvider instance;

  public static OSSServiceProvider getService()
  {
    try
    {
      if (instance == null)
        instance = new OSSServiceProvider();
      OSSServiceProvider localOSSServiceProvider = instance;
      return localOSSServiceProvider;
    }
    finally
    {
    }
  }

  public OSSBucket getOssBucket(String paramString)
  {
    return new OSSBucket(paramString);
  }

  public OSSData getOssData(OSSBucket paramOSSBucket, String paramString)
  {
    return new OSSData(paramOSSBucket, paramString);
  }

  public OSSFile getOssFile(OSSBucket paramOSSBucket, String paramString)
  {
    return new OSSFile(paramOSSBucket, paramString);
  }

  public OSSMeta getOssMeta(OSSBucket paramOSSBucket, String paramString)
  {
    return new OSSMeta(paramOSSBucket, paramString);
  }

  public OSSMultipart getOssMultipart(OSSBucket paramOSSBucket, String paramString)
  {
    return new OSSMultipart(paramOSSBucket, paramString);
  }

  public void setApplicationContext(Context paramContext)
  {
    OSSClient.setApplicationContext(paramContext);
  }

  public void setAuthenticationType(AuthenticationType paramAuthenticationType)
  {
    OSSClient.setAuthenticationType(paramAuthenticationType);
  }

  public void setClientConfiguration(ClientConfiguration paramClientConfiguration)
  {
    OSSClient.setClientConfiguration(paramClientConfiguration);
  }

  public void setCustomStandardTimeWithEpochSec(long paramLong)
  {
    OSSClient.setCustomStandardTimeWithEpochSec(paramLong);
  }

  public void setGlobalDefaultACL(AccessControlList paramAccessControlList)
  {
    OSSClient.setGlobalDefaultACL(paramAccessControlList);
  }

  public void setGlobalDefaultHostId(String paramString)
  {
    OSSClient.setGlobalDefaultHostId(paramString);
  }

  public void setGlobalDefaultStsTokenGetter(StsTokenGetter paramStsTokenGetter)
  {
    OSSClient.setGlobalDefaultStsTokenGetter(paramStsTokenGetter);
  }

  public void setGlobalDefaultTokenGenerator(TokenGenerator paramTokenGenerator)
  {
    OSSClient.setGlobalDefaultTokenGenerator(paramTokenGenerator);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.OSSServiceProvider
 * JD-Core Version:    0.6.2
 */