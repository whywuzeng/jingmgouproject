package com.google.zxing.client.result;

public final class TextParsedResult extends ParsedResult
{
  private final String language;
  private final String text;

  public TextParsedResult(String paramString1, String paramString2)
  {
    super(ParsedResultType.TEXT);
    this.text = paramString1;
    this.language = paramString2;
  }

  public String getDisplayResult()
  {
    return this.text;
  }

  public String getLanguage()
  {
    return this.language;
  }

  public String getText()
  {
    return this.text;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.TextParsedResult
 * JD-Core Version:    0.6.2
 */