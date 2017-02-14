package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.Hashtable;
import java.util.Vector;

public final class GenericMultipleBarcodeReader
  implements MultipleBarcodeReader
{
  private static final int MIN_DIMENSION_TO_RECUR = 100;
  private final Reader delegate;

  public GenericMultipleBarcodeReader(Reader paramReader)
  {
    this.delegate = paramReader;
  }

  private void doDecodeMultiple(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable, Vector paramVector, int paramInt1, int paramInt2)
  {
    while (true)
    {
      try
      {
        Object localObject1 = this.delegate.decode(paramBinaryBitmap, paramHashtable);
        i = 0;
        if (i >= paramVector.size())
          break label371;
        if (((Result)paramVector.elementAt(i)).getText().equals(((Result)localObject1).getText()))
        {
          i = 1;
          if (i == 0);
        }
        else
        {
          i += 1;
          continue;
        }
        paramVector.addElement(translateResultPoints((Result)localObject1, paramInt1, paramInt2));
        localObject1 = ((Result)localObject1).getResultPoints();
        if ((localObject1 == null) || (localObject1.length == 0))
          continue;
        int j = paramBinaryBitmap.getWidth();
        int k = paramBinaryBitmap.getHeight();
        float f2 = j;
        float f4 = k;
        i = 0;
        float f1 = 0.0F;
        float f3 = 0.0F;
        if (i < localObject1.length)
        {
          Object localObject2 = localObject1[i];
          float f6 = localObject2.getX();
          float f5 = localObject2.getY();
          if (f6 >= f2)
            break label368;
          f2 = f6;
          if (f5 >= f4)
            break label365;
          f4 = f5;
          if (f6 <= f3)
            break label362;
          f3 = f6;
          if (f5 <= f1)
            break label359;
          f1 = f5;
          i += 1;
          continue;
        }
        if (f2 > 100.0F)
          doDecodeMultiple(paramBinaryBitmap.crop(0, 0, (int)f2, k), paramHashtable, paramVector, paramInt1, paramInt2);
        if (f4 > 100.0F)
          doDecodeMultiple(paramBinaryBitmap.crop(0, 0, j, (int)f4), paramHashtable, paramVector, paramInt1, paramInt2);
        if (f3 < j - 100)
          doDecodeMultiple(paramBinaryBitmap.crop((int)f3, 0, j - (int)f3, k), paramHashtable, paramVector, paramInt1 + (int)f3, paramInt2);
        if (f1 >= k - 100)
          continue;
        doDecodeMultiple(paramBinaryBitmap.crop(0, (int)f1, j, k - (int)f1), paramHashtable, paramVector, paramInt1, paramInt2 + (int)f1);
        return;
      }
      catch (ReaderException paramBinaryBitmap)
      {
        return;
      }
      label359: continue;
      label362: continue;
      label365: continue;
      label368: continue;
      label371: int i = 0;
    }
  }

  private static Result translateResultPoints(Result paramResult, int paramInt1, int paramInt2)
  {
    ResultPoint[] arrayOfResultPoint1 = paramResult.getResultPoints();
    ResultPoint[] arrayOfResultPoint2 = new ResultPoint[arrayOfResultPoint1.length];
    int i = 0;
    while (i < arrayOfResultPoint1.length)
    {
      ResultPoint localResultPoint = arrayOfResultPoint1[i];
      arrayOfResultPoint2[i] = new ResultPoint(localResultPoint.getX() + paramInt1, localResultPoint.getY() + paramInt2);
      i += 1;
    }
    return new Result(paramResult.getText(), paramResult.getRawBytes(), arrayOfResultPoint2, paramResult.getBarcodeFormat());
  }

  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    return decodeMultiple(paramBinaryBitmap, null);
  }

  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException
  {
    int i = 0;
    Vector localVector = new Vector();
    doDecodeMultiple(paramBinaryBitmap, paramHashtable, localVector, 0, 0);
    if (localVector.isEmpty())
      throw NotFoundException.getNotFoundInstance();
    int j = localVector.size();
    paramBinaryBitmap = new Result[j];
    while (i < j)
    {
      paramBinaryBitmap[i] = ((Result)localVector.elementAt(i));
      i += 1;
    }
    return paramBinaryBitmap;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.GenericMultipleBarcodeReader
 * JD-Core Version:    0.6.2
 */