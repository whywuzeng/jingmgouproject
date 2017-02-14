package com.ismartgo.app.grid.utils;

public class EmojiFilter
{
  public static boolean containsEmoji(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim())));
    while (true)
    {
      return false;
      int j = paramString.length();
      int i = 0;
      while (i < j)
      {
        if (isEmojiCharacter(paramString.charAt(i)))
          return true;
        i += 1;
      }
    }
  }

  public static String filterEmoji(String paramString)
  {
    if (!containsEmoji(paramString))
      return paramString;
    Object localObject1 = null;
    int j = paramString.length();
    int i = 0;
    while (true)
    {
      if (i >= j)
      {
        if (localObject1 != null)
          break;
        return paramString.replaceAll(" ", "%20");
      }
      char c = paramString.charAt(i);
      Object localObject2 = localObject1;
      if (isEmojiCharacter(c))
      {
        localObject2 = localObject1;
        if (localObject1 == null)
          localObject2 = new StringBuilder(paramString.length());
        ((StringBuilder)localObject2).append(c);
      }
      i += 1;
      localObject1 = localObject2;
    }
    if (localObject1.length() == j)
      return paramString.replaceAll(" ", "%20").trim();
    return localObject1.toString().replaceAll(" ", "%20").trim();
  }

  private static boolean isEmojiCharacter(char paramChar)
  {
    return (paramChar == 0) || (paramChar == '\t') || (paramChar == '\n') || (paramChar == '\r') || ((paramChar >= ' ') && (paramChar <= 55295)) || ((paramChar >= 57344) && (paramChar <= 65533)) || ((paramChar >= 65536) && (paramChar <= 1114111));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.EmojiFilter
 * JD-Core Version:    0.6.2
 */