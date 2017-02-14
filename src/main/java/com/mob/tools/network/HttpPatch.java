package com.mob.tools.network;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class HttpPatch extends HttpEntityEnclosingRequestBase
{
  public static final String METHOD_NAME = "PATCH";

  public HttpPatch()
  {
  }

  public HttpPatch(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpPatch(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "PATCH";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.HttpPatch
 * JD-Core Version:    0.6.2
 */