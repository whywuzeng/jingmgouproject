package com.ab.util;

import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.text.Layout;
import android.text.TextPaint;
import java.util.ArrayList;
import java.util.List;

public final class AbGraphical
{
  public static int drawText(Canvas paramCanvas, String paramString, int paramInt1, TextPaint paramTextPaint, int paramInt2, int paramInt3)
  {
    if (AbStrUtil.isEmpty(paramString))
      return 1;
    paramString = getDrawRowStr(paramString, paramInt1, paramTextPaint);
    Paint.FontMetrics localFontMetrics = paramTextPaint.getFontMetrics();
    int i = (int)Math.ceil(localFontMetrics.descent - localFontMetrics.ascent) + 2;
    paramInt1 = 0;
    while (true)
    {
      if (paramInt1 >= paramString.size())
        return paramString.size();
      int j = i / 2;
      paramCanvas.drawText((String)paramString.get(paramInt1), paramInt2, j + paramInt3 + i * paramInt1, paramTextPaint);
      paramInt1 += 1;
    }
  }

  public static float getDesiredWidth(String paramString, TextPaint paramTextPaint)
  {
    return Layout.getDesiredWidth(paramString, paramTextPaint);
  }

  public static int getDrawRowCount(String paramString, int paramInt, TextPaint paramTextPaint)
  {
    ArrayList localArrayList;
    int i;
    if (paramString.indexOf("\n") != -1)
    {
      paramString = paramString.split("\n");
      localArrayList = new ArrayList();
      i = 0;
    }
    while (true)
    {
      if (i >= paramString.length)
      {
        return localArrayList.size();
        localObject = new String[1];
        localObject[0] = paramString;
        paramString = (String)localObject;
        break;
      }
      Object localObject = paramString[i];
      int j = subStringLength((String)localObject, paramInt, paramTextPaint);
      if (j <= 0)
        localArrayList.add(localObject);
      while (true)
      {
        if (((String)localObject).length() <= j + 1)
          break label160;
        localObject = ((String)localObject).substring(j + 1);
        break;
        if (j == ((String)localObject).length() - 1)
          localArrayList.add(localObject);
        else
          localArrayList.add(((String)localObject).substring(0, j + 1));
      }
      label160: i += 1;
    }
  }

  public static List<String> getDrawRowStr(String paramString, int paramInt, TextPaint paramTextPaint)
  {
    ArrayList localArrayList;
    int i;
    if (paramString.indexOf("\n") != -1)
    {
      paramString = paramString.split("\n");
      localArrayList = new ArrayList();
      i = 0;
    }
    while (true)
    {
      if (i >= paramString.length)
      {
        return localArrayList;
        localObject = new String[1];
        localObject[0] = paramString;
        paramString = (String)localObject;
        break;
      }
      Object localObject = paramString[i];
      int j = subStringLength((String)localObject, paramInt, paramTextPaint);
      if (j <= 0)
        localArrayList.add(localObject);
      while (true)
      {
        if (((String)localObject).length() <= j + 1)
          break label155;
        localObject = ((String)localObject).substring(j + 1);
        break;
        if (j == ((String)localObject).length() - 1)
          localArrayList.add(localObject);
        else
          localArrayList.add(((String)localObject).substring(0, j + 1));
      }
      label155: i += 1;
    }
  }

  public static float getStringWidth(String paramString, TextPaint paramTextPaint)
  {
    return paramTextPaint.measureText(paramString);
  }

  public static int subStringLength(String paramString, int paramInt, TextPaint paramTextPaint)
  {
    if (AbStrUtil.isEmpty(paramString))
    {
      i = 0;
      return i;
    }
    int j = 0;
    int i = 0;
    while (true)
    {
      if (i >= paramString.length())
        paramInt = j;
      while (true)
      {
        i = paramInt;
        if (paramInt != 0)
          break;
        return paramString.length() - 1;
        float f = paramTextPaint.measureText(paramString.substring(0, i + 1));
        if (f > paramInt)
        {
          paramInt = i - 1;
        }
        else
        {
          if (f != paramInt)
            break label87;
          paramInt = i;
        }
      }
      label87: i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.AbGraphical
 * JD-Core Version:    0.6.2
 */