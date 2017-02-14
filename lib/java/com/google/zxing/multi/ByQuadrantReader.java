package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import java.util.Hashtable;

public final class ByQuadrantReader
  implements Reader
{
  private final Reader delegate;

  public ByQuadrantReader(Reader paramReader)
  {
    this.delegate = paramReader;
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, ChecksumException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException
  {
    int j = paramBinaryBitmap.getWidth();
    int i = paramBinaryBitmap.getHeight();
    j /= 2;
    i /= 2;
    Object localObject1 = paramBinaryBitmap.crop(0, 0, j, i);
    try
    {
      localObject1 = this.delegate.decode((BinaryBitmap)localObject1, paramHashtable);
      return localObject1;
    }
    catch (NotFoundException localNotFoundException1)
    {
      Object localObject2 = paramBinaryBitmap.crop(j, 0, j, i);
      try
      {
        localObject2 = this.delegate.decode((BinaryBitmap)localObject2, paramHashtable);
        return localObject2;
      }
      catch (NotFoundException localNotFoundException2)
      {
        Object localObject3 = paramBinaryBitmap.crop(0, i, j, i);
        try
        {
          localObject3 = this.delegate.decode((BinaryBitmap)localObject3, paramHashtable);
          return localObject3;
        }
        catch (NotFoundException localNotFoundException3)
        {
          Object localObject4 = paramBinaryBitmap.crop(j, i, j, i);
          try
          {
            localObject4 = this.delegate.decode((BinaryBitmap)localObject4, paramHashtable);
            return localObject4;
          }
          catch (NotFoundException localNotFoundException4)
          {
            paramBinaryBitmap = paramBinaryBitmap.crop(j / 2, i / 2, j, i);
          }
        }
      }
    }
    return this.delegate.decode(paramBinaryBitmap, paramHashtable);
  }

  public void reset()
  {
    this.delegate.reset();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.ByQuadrantReader
 * JD-Core Version:    0.6.2
 */