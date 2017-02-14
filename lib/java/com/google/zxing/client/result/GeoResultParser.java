package com.google.zxing.client.result;

import com.google.zxing.Result;

final class GeoResultParser extends ResultParser
{
  public static GeoParsedResult parse(Result paramResult)
  {
    paramResult = paramResult.getText();
    if ((paramResult == null) || ((!paramResult.startsWith("geo:")) && (!paramResult.startsWith("GEO:"))));
    while (true)
    {
      return null;
      int i = paramResult.indexOf('?', 4);
      String str;
      label52: int j;
      if (i < 0)
      {
        paramResult = paramResult.substring(4);
        str = null;
        i = paramResult.indexOf(',');
        if (i >= 0)
          j = paramResult.indexOf(',', i + 1);
      }
      else
      {
        try
        {
          double d3 = Double.parseDouble(paramResult.substring(0, i));
          if ((d3 <= 90.0D) && (d3 >= -90.0D))
          {
            double d1;
            if (j < 0)
              d1 = Double.parseDouble(paramResult.substring(i + 1));
            for (double d2 = 0.0D; (d1 <= 180.0D) && (d1 >= -180.0D) && (d2 >= 0.0D); d2 = Double.parseDouble(paramResult.substring(j + 1)))
            {
              return new GeoParsedResult(d3, d1, d2, str);
              str = paramResult.substring(i + 1);
              paramResult = paramResult.substring(4, i);
              break label52;
              d1 = Double.parseDouble(paramResult.substring(i + 1, j));
            }
          }
        }
        catch (NumberFormatException paramResult)
        {
        }
      }
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.GeoResultParser
 * JD-Core Version:    0.6.2
 */