package com.umeng.message.proguard;

import java.util.Map;

public class ab
  implements aa
{
  private String a = null;
  private String b = null;

  public ab(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
  }

  private String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
    throws Exception
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramString1).append(paramString2).append(paramString3).append(paramString4).append(paramString5).append(paramString6).append(paramString7).append(paramString8);
    paramString1 = am.a(am.c(localStringBuffer.toString().getBytes("UTF-8")));
    return am.a(am.c((paramString1 + this.b).getBytes()));
  }

  public String a()
  {
    return this.a;
  }

  public String a(Map<String, String> paramMap)
  {
    if ((this.b != null) && (paramMap.containsKey("appkey")) && (paramMap.containsKey("channel")) && (paramMap.containsKey("app_version")) && (paramMap.containsKey("platform")) && (paramMap.containsKey("sdk_version")) && (paramMap.containsKey("utdid")) && (paramMap.containsKey("t")) && (paramMap.containsKey("v")))
      try
      {
        paramMap = a((String)paramMap.get("appkey"), (String)paramMap.get("channel"), (String)paramMap.get("app_version"), (String)paramMap.get("platform"), (String)paramMap.get("sdk_version"), (String)paramMap.get("utdid"), (String)paramMap.get("t"), (String)paramMap.get("v"));
        return paramMap;
      }
      catch (Exception paramMap)
      {
        paramMap.printStackTrace();
      }
    return null;
  }

  public String b()
  {
    return this.b;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ab
 * JD-Core Version:    0.6.2
 */