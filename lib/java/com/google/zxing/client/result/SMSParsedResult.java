package com.google.zxing.client.result;

public final class SMSParsedResult extends ParsedResult
{
  private final String body;
  private final String[] numbers;
  private final String subject;
  private final String[] vias;

  public SMSParsedResult(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(ParsedResultType.SMS);
    this.numbers = new String[] { paramString1 };
    this.vias = new String[] { paramString2 };
    this.subject = paramString3;
    this.body = paramString4;
  }

  public SMSParsedResult(String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2)
  {
    super(ParsedResultType.SMS);
    this.numbers = paramArrayOfString1;
    this.vias = paramArrayOfString2;
    this.subject = paramString1;
    this.body = paramString2;
  }

  public String getBody()
  {
    return this.body;
  }

  public String getDisplayResult()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    maybeAppend(this.numbers, localStringBuffer);
    maybeAppend(this.subject, localStringBuffer);
    maybeAppend(this.body, localStringBuffer);
    return localStringBuffer.toString();
  }

  public String[] getNumbers()
  {
    return this.numbers;
  }

  public String getSMSURI()
  {
    int k = 1;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("sms:");
    int i = 0;
    int j = 1;
    if (i < this.numbers.length)
    {
      if (j != 0)
        j = 0;
      while (true)
      {
        localStringBuffer.append(this.numbers[i]);
        if (this.vias[i] != null)
        {
          localStringBuffer.append(";via=");
          localStringBuffer.append(this.vias[i]);
        }
        i += 1;
        break;
        localStringBuffer.append(',');
      }
    }
    if (this.body != null)
    {
      i = 1;
      if (this.subject == null)
        break label198;
    }
    label198: for (j = k; ; j = 0)
    {
      if ((i != 0) || (j != 0))
      {
        localStringBuffer.append('?');
        if (i != 0)
        {
          localStringBuffer.append("body=");
          localStringBuffer.append(this.body);
        }
        if (j != 0)
        {
          if (i != 0)
            localStringBuffer.append('&');
          localStringBuffer.append("subject=");
          localStringBuffer.append(this.subject);
        }
      }
      return localStringBuffer.toString();
      i = 0;
      break;
    }
  }

  public String getSubject()
  {
    return this.subject;
  }

  public String[] getVias()
  {
    return this.vias;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.SMSParsedResult
 * JD-Core Version:    0.6.2
 */