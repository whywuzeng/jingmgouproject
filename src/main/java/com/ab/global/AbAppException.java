package com.ab.global;

import com.ab.util.AbStrUtil;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.HttpHostConnectException;

public class AbAppException extends Exception
{
  private static final long serialVersionUID = 1L;
  private String msg = null;

  public AbAppException(Exception paramException)
  {
    try
    {
      if ((paramException instanceof HttpHostConnectException))
      {
        this.msg = "连接远程地址失败";
        return;
      }
      if ((paramException instanceof ConnectException))
      {
        this.msg = "无法连接到网络";
        return;
      }
      if ((paramException instanceof UnknownHostException))
      {
        this.msg = "连接远程地址失败";
        return;
      }
      if ((paramException instanceof SocketException))
      {
        this.msg = "网络连接出错，请重试";
        return;
      }
      if ((paramException instanceof SocketTimeoutException))
      {
        this.msg = "连接超时，请重试";
        return;
      }
      if ((paramException instanceof NullPointerException))
      {
        this.msg = "抱歉，远程服务出错了";
        return;
      }
      if ((paramException instanceof ClientProtocolException))
      {
        this.msg = "Http请求参数错误";
        return;
      }
      if ((paramException == null) || (AbStrUtil.isEmpty(paramException.getMessage())))
      {
        this.msg = "抱歉，程序出错了";
        return;
      }
      this.msg = paramException.getMessage();
      return;
    }
    catch (Exception paramException)
    {
    }
  }

  public AbAppException(String paramString)
  {
    super(paramString);
    this.msg = paramString;
  }

  public String getMessage()
  {
    return this.msg;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.global.AbAppException
 * JD-Core Version:    0.6.2
 */