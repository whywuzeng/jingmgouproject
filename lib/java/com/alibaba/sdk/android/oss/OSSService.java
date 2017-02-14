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

public abstract interface OSSService
{
  public abstract OSSBucket getOssBucket(String paramString);

  public abstract OSSData getOssData(OSSBucket paramOSSBucket, String paramString);

  public abstract OSSFile getOssFile(OSSBucket paramOSSBucket, String paramString);

  public abstract OSSMeta getOssMeta(OSSBucket paramOSSBucket, String paramString);

  public abstract OSSMultipart getOssMultipart(OSSBucket paramOSSBucket, String paramString);

  public abstract void setApplicationContext(Context paramContext);

  public abstract void setAuthenticationType(AuthenticationType paramAuthenticationType);

  public abstract void setClientConfiguration(ClientConfiguration paramClientConfiguration);

  public abstract void setCustomStandardTimeWithEpochSec(long paramLong);

  public abstract void setGlobalDefaultACL(AccessControlList paramAccessControlList);

  public abstract void setGlobalDefaultHostId(String paramString);

  public abstract void setGlobalDefaultStsTokenGetter(StsTokenGetter paramStsTokenGetter);

  public abstract void setGlobalDefaultTokenGenerator(TokenGenerator paramTokenGenerator);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.OSSService
 * JD-Core Version:    0.6.2
 */