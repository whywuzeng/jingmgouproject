package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiDetector extends Detector
{
  private static final DetectorResult[] EMPTY_DETECTOR_RESULTS = new DetectorResult[0];

  public MultiDetector(BitMatrix paramBitMatrix)
  {
    super(paramBitMatrix);
  }

  public DetectorResult[] detectMulti(Hashtable paramHashtable)
    throws NotFoundException
  {
    int j = 0;
    Object localObject = new MultiFinderPatternFinder(getImage()).findMulti(paramHashtable);
    if ((localObject == null) || (localObject.length == 0))
      throw NotFoundException.getNotFoundInstance();
    paramHashtable = new Vector();
    int i = 0;
    while (true)
    {
      if (i < localObject.length);
      try
      {
        paramHashtable.addElement(processFinderPatternInfo(localObject[i]));
        label63: i += 1;
        continue;
        if (paramHashtable.isEmpty())
          return EMPTY_DETECTOR_RESULTS;
        localObject = new DetectorResult[paramHashtable.size()];
        i = j;
        while (i < paramHashtable.size())
        {
          localObject[i] = ((DetectorResult)paramHashtable.elementAt(i));
          i += 1;
        }
        return localObject;
      }
      catch (ReaderException localReaderException)
      {
        break label63;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.qrcode.detector.MultiDetector
 * JD-Core Version:    0.6.2
 */