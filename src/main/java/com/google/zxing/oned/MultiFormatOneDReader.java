package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.RSS14Reader;
import com.google.zxing.oned.rss.expanded.RSSExpandedReader;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatOneDReader extends OneDReader
{
  private final Vector readers;

  public MultiFormatOneDReader(Hashtable paramHashtable)
  {
    Vector localVector;
    if (paramHashtable == null)
    {
      localVector = null;
      if ((paramHashtable == null) || (paramHashtable.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) == null))
        break label389;
    }
    label389: for (boolean bool = true; ; bool = false)
    {
      this.readers = new Vector();
      if (localVector != null)
      {
        if ((localVector.contains(BarcodeFormat.EAN_13)) || (localVector.contains(BarcodeFormat.UPC_A)) || (localVector.contains(BarcodeFormat.EAN_8)) || (localVector.contains(BarcodeFormat.UPC_E)))
          this.readers.addElement(new MultiFormatUPCEANReader(paramHashtable));
        if (localVector.contains(BarcodeFormat.CODE_39))
          this.readers.addElement(new Code39Reader(bool));
        if (localVector.contains(BarcodeFormat.CODE_93))
          this.readers.addElement(new Code93Reader());
        if (localVector.contains(BarcodeFormat.CODE_128))
          this.readers.addElement(new Code128Reader());
        if (localVector.contains(BarcodeFormat.ITF))
          this.readers.addElement(new ITFReader());
        if (localVector.contains(BarcodeFormat.CODABAR))
          this.readers.addElement(new CodaBarReader());
        if (localVector.contains(BarcodeFormat.RSS14))
          this.readers.addElement(new RSS14Reader());
        if (localVector.contains(BarcodeFormat.RSS_EXPANDED))
          this.readers.addElement(new RSSExpandedReader());
      }
      if (this.readers.isEmpty())
      {
        this.readers.addElement(new MultiFormatUPCEANReader(paramHashtable));
        this.readers.addElement(new Code39Reader());
        this.readers.addElement(new Code93Reader());
        this.readers.addElement(new Code128Reader());
        this.readers.addElement(new ITFReader());
        this.readers.addElement(new RSS14Reader());
        this.readers.addElement(new RSSExpandedReader());
      }
      return;
      localVector = (Vector)paramHashtable.get(DecodeHintType.POSSIBLE_FORMATS);
      break;
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException
  {
    int j = this.readers.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (OneDReader)this.readers.elementAt(i);
      try
      {
        localObject = ((OneDReader)localObject).decodeRow(paramInt, paramBitArray, paramHashtable);
        return localObject;
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
 * Qualified Name:     com.google.zxing.oned.MultiFormatOneDReader
 * JD-Core Version:    0.6.2
 */