package com.google.zxing.client.result;

public abstract class ParsedResult
{
  private final ParsedResultType type;

  protected ParsedResult(ParsedResultType paramParsedResultType)
  {
    this.type = paramParsedResultType;
  }

  public static void maybeAppend(String paramString, StringBuffer paramStringBuffer)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      if (paramStringBuffer.length() > 0)
        paramStringBuffer.append('\n');
      paramStringBuffer.append(paramString);
    }
  }

  public static void maybeAppend(String[] paramArrayOfString, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfString != null)
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        if ((paramArrayOfString[i] != null) && (paramArrayOfString[i].length() > 0))
        {
          if (paramStringBuffer.length() > 0)
            paramStringBuffer.append('\n');
          paramStringBuffer.append(paramArrayOfString[i]);
        }
        i += 1;
      }
    }
  }

  public abstract String getDisplayResult();

  public ParsedResultType getType()
  {
    return this.type;
  }

  public String toString()
  {
    return getDisplayResult();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ParsedResult
 * JD-Core Version:    0.6.2
 */