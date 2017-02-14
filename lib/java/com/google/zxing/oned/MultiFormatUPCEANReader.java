package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatUPCEANReader extends OneDReader
{
  private final Vector readers;

  public MultiFormatUPCEANReader(Hashtable paramHashtable)
  {
    if (paramHashtable == null)
    {
      paramHashtable = null;
      this.readers = new Vector();
      if (paramHashtable != null)
      {
        if (!paramHashtable.contains(BarcodeFormat.EAN_13))
          break label164;
        this.readers.addElement(new EAN13Reader());
      }
    }
    while (true)
    {
      if (paramHashtable.contains(BarcodeFormat.EAN_8))
        this.readers.addElement(new EAN8Reader());
      if (paramHashtable.contains(BarcodeFormat.UPC_E))
        this.readers.addElement(new UPCEReader());
      if (this.readers.isEmpty())
      {
        this.readers.addElement(new EAN13Reader());
        this.readers.addElement(new EAN8Reader());
        this.readers.addElement(new UPCEReader());
      }
      return;
      paramHashtable = (Vector)paramHashtable.get(DecodeHintType.POSSIBLE_FORMATS);
      break;
      label164: if (paramHashtable.contains(BarcodeFormat.UPC_A))
        this.readers.addElement(new UPCAReader());
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException
  {
    int[] arrayOfInt = UPCEANReader.findStartGuardPattern(paramBitArray);
    int j = this.readers.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (UPCEANReader)this.readers.elementAt(i);
      try
      {
        localObject = ((UPCEANReader)localObject).decodeRow(paramInt, paramBitArray, arrayOfInt, paramHashtable);
        if ((BarcodeFormat.EAN_13.equals(((Result)localObject).getBarcodeFormat())) && (((Result)localObject).getText().charAt(0) == '0'))
        {
          paramInt = 1;
          if (paramHashtable != null)
            break label144;
          paramBitArray = null;
          label87: if ((paramBitArray != null) && (!paramBitArray.contains(BarcodeFormat.UPC_A)))
            break label158;
        }
        label144: label158: for (i = 1; ; i = 0)
        {
          if ((paramInt == 0) || (i == 0))
            break label164;
          return new Result(((Result)localObject).getText().substring(1), null, ((Result)localObject).getResultPoints(), BarcodeFormat.UPC_A);
          paramInt = 0;
          break;
          paramBitArray = (Vector)paramHashtable.get(DecodeHintType.POSSIBLE_FORMATS);
          break label87;
        }
        label164: return localObject;
      }
      catch (ReaderException localReaderException)
      {
        i += 1;
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }

  public void reset()
  {
    int j = this.readers.size();
    int i = 0;
    while (i < j)
    {
      ((Reader)this.readers.elementAt(i)).reset();
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.MultiFormatUPCEANReader
 * JD-Core Version:    0.6.2
 */