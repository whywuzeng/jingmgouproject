package com.google.zxing.client.result;

public final class URIParsedResult extends ParsedResult
{
  private final String title;
  private final String uri;

  public URIParsedResult(String paramString1, String paramString2)
  {
    super(ParsedResultType.URI);
    this.uri = massageURI(paramString1);
    this.title = paramString2;
  }

  private boolean containsUser()
  {
    int i = this.uri.indexOf(':');
    int j = this.uri.length();
    i += 1;
    while ((i < j) && (this.uri.charAt(i) == '/'))
      i += 1;
    int k = this.uri.indexOf('/', i);
    if (k < 0);
    while (true)
    {
      k = this.uri.indexOf('@', i);
      return (k >= i) && (k < j);
      j = k;
    }
  }

  private static boolean isColonFollowedByPortNumber(String paramString, int paramInt)
  {
    int i = paramString.indexOf('/', paramInt + 1);
    if (i < 0)
      i = paramString.length();
    while (true)
    {
      if (i <= paramInt + 1)
        return false;
      paramInt += 1;
      while (true)
      {
        if (paramInt >= i)
          break label64;
        if ((paramString.charAt(paramInt) < '0') || (paramString.charAt(paramInt) > '9'))
          break;
        paramInt += 1;
      }
      label64: return true;
    }
  }

  private static String massageURI(String paramString)
  {
    int i = paramString.indexOf(':');
    if (i < 0)
      return "http://" + paramString;
    if (isColonFollowedByPortNumber(paramString, i))
      return "http://" + paramString;
    return paramString.substring(0, i).toLowerCase() + paramString.substring(i);
  }

  public String getDisplayResult()
  {
    StringBuffer localStringBuffer = new StringBuffer(30);
    maybeAppend(this.title, localStringBuffer);
    maybeAppend(this.uri, localStringBuffer);
    return localStringBuffer.toString();
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getURI()
  {
    return this.uri;
  }

  public boolean isPossiblyMaliciousURI()
  {
    return containsUser();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URIParsedResult
 * JD-Core Version:    0.6.2
 */