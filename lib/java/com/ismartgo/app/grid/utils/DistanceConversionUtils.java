package com.ismartgo.app.grid.utils;

import android.util.Log;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class DistanceConversionUtils
{
  public static String getDistance(String paramString)
  {
    if ((paramString == null) || (paramString.trim().equals("")) || (paramString.trim().equals("null")))
      return "0公里";
    float f = Float.valueOf(paramString).floatValue() / 1000.0F;
    if (f < 1.0F)
    {
      paramString = String.valueOf(f * 1000.0F);
      return paramString.substring(0, paramString.lastIndexOf(".")) + "米";
    }
    if (f == 1.0F)
      return "1公里";
    return new BigDecimal(f).setScale(1, 4).doubleValue() + "公里";
  }

  public static String getDistance1(String paramString)
  {
    if ((paramString == null) || (paramString.trim().equals("")) || (paramString.trim().equals("null")))
      return "0公里";
    paramString = Float.valueOf(paramString);
    if (paramString.floatValue() < 1.0F)
    {
      String str = String.valueOf(paramString.floatValue() * 1000.0F);
      paramString = str;
      if (str.contains("."))
        paramString = str.substring(0, str.indexOf("."));
      return paramString + "米";
    }
    if (paramString.floatValue() == 1.0F)
      return "1公里";
    return new BigDecimal(paramString.floatValue()).setScale(1, 4).doubleValue() + "公里";
  }

  public static void put(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = new FileOutputStream("/sdcard/" + paramString2 + ".txt", true);
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramString2, "gb2312");
      localOutputStreamWriter.write(paramString1);
      localOutputStreamWriter.flush();
      localOutputStreamWriter.close();
      paramString2.close();
      return;
    }
    catch (Exception paramString1)
    {
      Log.e("m", "file write error");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.DistanceConversionUtils
 * JD-Core Version:    0.6.2
 */