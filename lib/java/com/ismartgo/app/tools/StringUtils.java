package com.ismartgo.app.tools;

import com.ismartgo.app.url.Url;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils
{
  public static String StringFilter(String paramString)
    throws PatternSyntaxException
  {
    paramString = paramString.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replace("(", "(").replace(")", ")");
    return Pattern.compile("[『』]").matcher(paramString).replaceAll("").trim();
  }

  public static String ToDBC(String paramString)
  {
    paramString = paramString.toCharArray();
    int i = 0;
    if (i >= paramString.length)
      return new String(paramString);
    if (paramString[i] == '　')
      paramString[i] = 32;
    while (true)
    {
      i += 1;
      break;
      if ((paramString[i] > 65280) && (paramString[i] < 65375))
        paramString[i] = ((char)(paramString[i] - 65248));
    }
  }

  public static String getImgUrl(String paramString)
  {
    if (paramString.startsWith("http://"))
      return paramString;
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL));
    if (paramString.startsWith("/"));
    while (true)
    {
      return paramString;
      paramString = "/" + paramString;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.StringUtils
 * JD-Core Version:    0.6.2
 */