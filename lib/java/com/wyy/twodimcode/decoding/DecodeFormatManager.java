package com.wyy.twodimcode.decoding;

import android.content.Intent;
import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

final class DecodeFormatManager
{
  private static final Pattern COMMA_PATTERN = Pattern.compile(",");
  static final Vector<BarcodeFormat> DATA_MATRIX_FORMATS;
  static final Vector<BarcodeFormat> ONE_D_FORMATS;
  static final Vector<BarcodeFormat> PRODUCT_FORMATS = new Vector(5);
  static final Vector<BarcodeFormat> QR_CODE_FORMATS;

  static
  {
    PRODUCT_FORMATS.add(BarcodeFormat.UPC_A);
    PRODUCT_FORMATS.add(BarcodeFormat.UPC_E);
    PRODUCT_FORMATS.add(BarcodeFormat.EAN_13);
    PRODUCT_FORMATS.add(BarcodeFormat.EAN_8);
    PRODUCT_FORMATS.add(BarcodeFormat.RSS14);
    ONE_D_FORMATS = new Vector(PRODUCT_FORMATS.size() + 4);
    ONE_D_FORMATS.addAll(PRODUCT_FORMATS);
    ONE_D_FORMATS.add(BarcodeFormat.CODE_39);
    ONE_D_FORMATS.add(BarcodeFormat.CODE_93);
    ONE_D_FORMATS.add(BarcodeFormat.CODE_128);
    ONE_D_FORMATS.add(BarcodeFormat.ITF);
    QR_CODE_FORMATS = new Vector(1);
    QR_CODE_FORMATS.add(BarcodeFormat.QR_CODE);
    DATA_MATRIX_FORMATS = new Vector(1);
    DATA_MATRIX_FORMATS.add(BarcodeFormat.DATA_MATRIX);
  }

  static Vector<BarcodeFormat> parseDecodeFormats(Intent paramIntent)
  {
    List localList = null;
    String str = paramIntent.getStringExtra("SCAN_FORMATS");
    if (str != null)
      localList = Arrays.asList(COMMA_PATTERN.split(str));
    return parseDecodeFormats(localList, paramIntent.getStringExtra("SCAN_MODE"));
  }

  static Vector<BarcodeFormat> parseDecodeFormats(Uri paramUri)
  {
    List localList2 = paramUri.getQueryParameters("SCAN_FORMATS");
    List localList1 = localList2;
    if (localList2 != null)
    {
      localList1 = localList2;
      if (localList2.size() == 1)
      {
        localList1 = localList2;
        if (localList2.get(0) != null)
          localList1 = Arrays.asList(COMMA_PATTERN.split((CharSequence)localList2.get(0)));
      }
    }
    return parseDecodeFormats(localList1, paramUri.getQueryParameter("SCAN_MODE"));
  }

  private static Vector<BarcodeFormat> parseDecodeFormats(Iterable<String> paramIterable, String paramString)
  {
    if (paramIterable != null)
    {
      Vector localVector = new Vector();
      try
      {
        paramIterable = paramIterable.iterator();
        while (true)
        {
          if (!paramIterable.hasNext())
            return localVector;
          localVector.add(BarcodeFormat.valueOf((String)paramIterable.next()));
        }
      }
      catch (IllegalArgumentException paramIterable)
      {
      }
    }
    if (paramString != null)
    {
      if ("PRODUCT_MODE".equals(paramString))
        return PRODUCT_FORMATS;
      if ("QR_CODE_MODE".equals(paramString))
        return QR_CODE_FORMATS;
      if ("DATA_MATRIX_MODE".equals(paramString))
        return DATA_MATRIX_FORMATS;
      if ("ONE_D_MODE".equals(paramString))
        return ONE_D_FORMATS;
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.decoding.DecodeFormatManager
 * JD-Core Version:    0.6.2
 */