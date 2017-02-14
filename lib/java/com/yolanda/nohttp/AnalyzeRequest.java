package com.yolanda.nohttp;

import java.util.Set;

public abstract interface AnalyzeRequest extends BasicAnalyzeRequest
{
  public abstract byte[] getRequestBody();

  public abstract Object getTag();

  public abstract Set<String> keySet();

  public abstract Object value(String paramString);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.AnalyzeRequest
 * JD-Core Version:    0.6.2
 */