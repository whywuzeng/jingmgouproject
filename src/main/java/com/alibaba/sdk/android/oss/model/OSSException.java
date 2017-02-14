package com.alibaba.sdk.android.oss.model;

import java.io.PrintStream;

public class OSSException extends Exception
{
  private static final long serialVersionUID = 12345678L;
  private String bucketName;
  private ExceptionType eType;
  private Exception localException;
  private String objectKey;
  private OSSResponseInfo ossRespInfo;

  public OSSException(String paramString1, String paramString2, OSSResponseInfo paramOSSResponseInfo)
  {
    this.objectKey = paramString2;
    this.eType = ExceptionType.OSS_EXCEPTION;
    this.ossRespInfo = paramOSSResponseInfo;
  }

  public OSSException(String paramString1, String paramString2, Exception paramException)
  {
    this.objectKey = paramString2;
    this.eType = ExceptionType.LOCAL_EXCEPTION;
    this.localException = paramException;
  }

  public String getBucketName()
  {
    return this.bucketName;
  }

  @Deprecated
  public Exception getException()
  {
    return this.localException;
  }

  public ExceptionType getExceptionType()
  {
    return this.eType;
  }

  public Exception getLocalException()
  {
    return this.localException;
  }

  public String getMessage()
  {
    if (this.eType == ExceptionType.LOCAL_EXCEPTION)
      return "OSSException type: LOCAL_EXCEPTION \nobjectKey: " + this.objectKey + "\n" + "ExceptionMessage: " + this.localException.getMessage();
    if (this.eType == ExceptionType.OSS_EXCEPTION)
      return toString();
    return "unknown type exception";
  }

  public String getObjectKey()
  {
    return this.objectKey;
  }

  public OSSResponseInfo getOssRespInfo()
  {
    return this.ossRespInfo;
  }

  public StackTraceElement[] getStackTrace()
  {
    if (this.eType == ExceptionType.LOCAL_EXCEPTION)
      return this.localException.getStackTrace();
    return super.getStackTrace();
  }

  public void printStackTrace()
  {
    if (this.eType == ExceptionType.LOCAL_EXCEPTION)
    {
      System.err.println("Local_exception: ");
      this.localException.printStackTrace();
      return;
    }
    super.printStackTrace();
  }

  public void setBucketName(String paramString)
  {
    this.bucketName = paramString;
  }

  public void setException(Exception paramException)
  {
    this.localException = paramException;
  }

  public void setObjectKey(String paramString)
  {
    this.objectKey = paramString;
  }

  public void setOssRespInfo(OSSResponseInfo paramOSSResponseInfo)
  {
    this.ossRespInfo = paramOSSResponseInfo;
  }

  public String toString()
  {
    if (this.eType == ExceptionType.LOCAL_EXCEPTION)
      return "OSSException type: LOCAL_EXCEPTION \nobjectKey: " + this.objectKey + "\n" + "ExceptionMessage: " + this.localException.toString();
    if (this.eType == ExceptionType.OSS_EXCEPTION)
      return "OSSException type: OSS_EXCEPTION \nstatusCode: " + this.ossRespInfo.getStatusCode() + "\n" + "objectKey: " + this.objectKey + "\n" + "requestId: " + this.ossRespInfo.getRequestId() + "\n" + "responseCode: " + this.ossRespInfo.getCode() + "\n" + "responseMessage: " + this.ossRespInfo.getMessage() + "\n";
    return "unknown type exception";
  }

  public static enum ExceptionType
  {
    LOCAL_EXCEPTION, OSS_EXCEPTION;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.OSSException
 * JD-Core Version:    0.6.2
 */