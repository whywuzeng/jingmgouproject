package org.apache.http.entity.mime;

public class MinimalField
{
  private final String name;
  private final String value;

  MinimalField(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.value = paramString2;
  }

  public String getBody()
  {
    return this.value;
  }

  public String getName()
  {
    return this.name;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    localStringBuilder.append(": ");
    localStringBuilder.append(this.value);
    return localStringBuilder.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.apache.http.entity.mime.MinimalField
 * JD-Core Version:    0.6.2
 */