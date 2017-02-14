package com.google.zxing;

import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.pdf417.PDF417Reader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatReader
  implements Reader
{
  private Hashtable hints;
  private Vector readers;

  private Result decodeInternal(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    int j = this.readers.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (Reader)this.readers.elementAt(i);
      try
      {
        localObject = ((Reader)localObject).decode(paramBinaryBitmap, this.hints);
        return localObject;
      }
      catch (ReaderException localReaderException)
      {
        i += 1;
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    setHints(null);
    return decodeInternal(paramBinaryBitmap);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException
  {
    setHints(paramHashtable);
    return decodeInternal(paramBinaryBitmap);
  }

  public Result decodeWithState(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    if (this.readers == null)
      setHints(null);
    return decodeInternal(paramBinaryBitmap);
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

  public void setHints(Hashtable paramHashtable)
  {
    int j = 0;
    this.hints = paramHashtable;
    int i;
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      if (paramHashtable != null)
        break label361;
    }
    label361: for (Vector localVector = null; ; localVector = (Vector)paramHashtable.get(DecodeHintType.POSSIBLE_FORMATS))
    {
      this.readers = new Vector();
      if (localVector != null)
      {
        if ((localVector.contains(BarcodeFormat.UPC_A)) || (localVector.contains(BarcodeFormat.UPC_E)) || (localVector.contains(BarcodeFormat.EAN_13)) || (localVector.contains(BarcodeFormat.EAN_8)) || (localVector.contains(BarcodeFormat.CODE_39)) || (localVector.contains(BarcodeFormat.CODE_93)) || (localVector.contains(BarcodeFormat.CODE_128)) || (localVector.contains(BarcodeFormat.ITF)) || (localVector.contains(BarcodeFormat.RSS14)) || (localVector.contains(BarcodeFormat.RSS_EXPANDED)))
          j = 1;
        if ((j != 0) && (i == 0))
          this.readers.addElement(new MultiFormatOneDReader(paramHashtable));
        if (localVector.contains(BarcodeFormat.QR_CODE))
          this.readers.addElement(new QRCodeReader());
        if (localVector.contains(BarcodeFormat.DATA_MATRIX))
          this.readers.addElement(new DataMatrixReader());
        if (localVector.contains(BarcodeFormat.PDF417))
          this.readers.addElement(new PDF417Reader());
        if ((j != 0) && (i != 0))
          this.readers.addElement(new MultiFormatOneDReader(paramHashtable));
      }
      if (this.readers.isEmpty())
      {
        if (i == 0)
          this.readers.addElement(new MultiFormatOneDReader(paramHashtable));
        this.readers.addElement(new QRCodeReader());
        this.readers.addElement(new DataMatrixReader());
        if (i != 0)
          this.readers.addElement(new MultiFormatOneDReader(paramHashtable));
      }
      return;
      i = 0;
      break;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.MultiFormatReader
 * JD-Core Version:    0.6.2
 */