package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.Collections;
import com.google.zxing.common.Comparator;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public final class Detector
{
  private static final Integer[] INTEGERS = { new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4) };
  private final BitMatrix image;
  private final WhiteRectangleDetector rectangleDetector;

  public Detector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
    this.rectangleDetector = new WhiteRectangleDetector(paramBitMatrix);
  }

  private ResultPoint correctTopRight(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt)
  {
    float f1 = distance(paramResultPoint1, paramResultPoint2) / paramInt;
    int i = distance(paramResultPoint3, paramResultPoint4);
    float f2 = (paramResultPoint4.getX() - paramResultPoint3.getX()) / i;
    float f3 = (paramResultPoint4.getY() - paramResultPoint3.getY()) / i;
    ResultPoint localResultPoint = new ResultPoint(f2 * f1 + paramResultPoint4.getX(), f1 * f3 + paramResultPoint4.getY());
    f1 = distance(paramResultPoint1, paramResultPoint2) / paramInt;
    paramInt = distance(paramResultPoint2, paramResultPoint4);
    f2 = (paramResultPoint4.getX() - paramResultPoint2.getX()) / paramInt;
    f3 = (paramResultPoint4.getY() - paramResultPoint2.getY()) / paramInt;
    paramResultPoint1 = new ResultPoint(f2 * f1 + paramResultPoint4.getX(), f1 * f3 + paramResultPoint4.getY());
    if (!isValid(localResultPoint))
      if (!isValid(paramResultPoint1));
    do
    {
      return paramResultPoint1;
      return null;
      if (!isValid(paramResultPoint1))
        return localResultPoint;
    }
    while (Math.abs(transitionsBetween(paramResultPoint3, localResultPoint).getTransitions() - transitionsBetween(paramResultPoint2, localResultPoint).getTransitions()) > Math.abs(transitionsBetween(paramResultPoint3, paramResultPoint1).getTransitions() - transitionsBetween(paramResultPoint2, paramResultPoint1).getTransitions()));
    return localResultPoint;
  }

  private static int distance(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    return round((float)Math.sqrt((paramResultPoint1.getX() - paramResultPoint2.getX()) * (paramResultPoint1.getX() - paramResultPoint2.getX()) + (paramResultPoint1.getY() - paramResultPoint2.getY()) * (paramResultPoint1.getY() - paramResultPoint2.getY())));
  }

  private static void increment(Hashtable paramHashtable, ResultPoint paramResultPoint)
  {
    Integer localInteger = (Integer)paramHashtable.get(paramResultPoint);
    if (localInteger == null);
    for (localInteger = INTEGERS[1]; ; localInteger = INTEGERS[(localInteger.intValue() + 1)])
    {
      paramHashtable.put(paramResultPoint, localInteger);
      return;
    }
  }

  private boolean isValid(ResultPoint paramResultPoint)
  {
    return (paramResultPoint.getX() >= 0.0F) && (paramResultPoint.getX() < this.image.width) && (paramResultPoint.getY() > 0.0F) && (paramResultPoint.getY() < this.image.height);
  }

  private static int round(float paramFloat)
  {
    return (int)(0.5F + paramFloat);
  }

  private static BitMatrix sampleGrid(BitMatrix paramBitMatrix, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt)
    throws NotFoundException
  {
    return GridSampler.getInstance().sampleGrid(paramBitMatrix, paramInt, 0.5F, 0.5F, paramInt - 0.5F, 0.5F, paramInt - 0.5F, paramInt - 0.5F, 0.5F, paramInt - 0.5F, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint4.getX(), paramResultPoint4.getY(), paramResultPoint3.getX(), paramResultPoint3.getY(), paramResultPoint2.getX(), paramResultPoint2.getY());
  }

  private ResultPointsAndTransitions transitionsBetween(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    int j = (int)paramResultPoint1.getX();
    int i = (int)paramResultPoint1.getY();
    int i1 = (int)paramResultPoint2.getX();
    int i2 = (int)paramResultPoint2.getY();
    int n;
    if (Math.abs(i2 - i) > Math.abs(i1 - j))
    {
      n = 1;
      if (n == 0)
        break label341;
    }
    while (true)
    {
      int i7 = Math.abs(i2 - i);
      int i8 = Math.abs(i1 - j);
      int i5 = -i7;
      int i3;
      label87: int i4;
      label96: int i6;
      label114: label122: boolean bool1;
      label143: label164: label172: boolean bool2;
      if (j < i1)
      {
        i3 = 1;
        if (i >= i2)
          break label260;
        i4 = 1;
        i6 = 0;
        BitMatrix localBitMatrix = this.image;
        if (n == 0)
          break label266;
        k = j;
        if (n == 0)
          break label272;
        m = i;
        bool1 = localBitMatrix.get(k, m);
        i5 >>= 1;
        k = i6;
        if (i == i2)
          break label334;
        localBitMatrix = this.image;
        if (n == 0)
          break label279;
        m = j;
        if (n == 0)
          break label285;
        i6 = i;
        boolean bool3 = localBitMatrix.get(m, i6);
        m = k;
        bool2 = bool1;
        if (bool3 != bool1)
        {
          m = k + 1;
          bool2 = bool3;
        }
        i6 = i5 + i8;
        k = i6;
        i5 = j;
        if (i6 <= 0)
          break label306;
        if (j != i1)
          break label292;
      }
      while (true)
      {
        return new ResultPointsAndTransitions(paramResultPoint1, paramResultPoint2, m, null);
        n = 0;
        break;
        i3 = -1;
        break label87;
        label260: i4 = -1;
        break label96;
        label266: k = i;
        break label114;
        label272: m = j;
        break label122;
        label279: m = i;
        break label164;
        label285: i6 = j;
        break label172;
        label292: i5 = j + i3;
        k = i6 - i7;
        label306: i += i4;
        i6 = k;
        j = i5;
        k = m;
        bool1 = bool2;
        i5 = i6;
        break label143;
        label334: m = k;
      }
      label341: int k = i1;
      i1 = i2;
      int m = j;
      i2 = k;
      j = i;
      i = m;
    }
  }

  public DetectorResult detect()
    throws NotFoundException
  {
    Object localObject1 = this.rectangleDetector.detect();
    ResultPoint localResultPoint1 = localObject1[0];
    ResultPoint localResultPoint2 = localObject1[1];
    ResultPoint localResultPoint3 = localObject1[2];
    ResultPoint localResultPoint4 = localObject1[3];
    Object localObject2 = new Vector(4);
    ((Vector)localObject2).addElement(transitionsBetween(localResultPoint1, localResultPoint2));
    ((Vector)localObject2).addElement(transitionsBetween(localResultPoint1, localResultPoint3));
    ((Vector)localObject2).addElement(transitionsBetween(localResultPoint2, localResultPoint4));
    ((Vector)localObject2).addElement(transitionsBetween(localResultPoint3, localResultPoint4));
    Collections.insertionSort((Vector)localObject2, new ResultPointsAndTransitionsComparator(null));
    localObject1 = (ResultPointsAndTransitions)((Vector)localObject2).elementAt(0);
    localObject2 = (ResultPointsAndTransitions)((Vector)localObject2).elementAt(1);
    Hashtable localHashtable = new Hashtable();
    increment(localHashtable, ((ResultPointsAndTransitions)localObject1).getFrom());
    increment(localHashtable, ((ResultPointsAndTransitions)localObject1).getTo());
    increment(localHashtable, ((ResultPointsAndTransitions)localObject2).getFrom());
    increment(localHashtable, ((ResultPointsAndTransitions)localObject2).getTo());
    localObject2 = null;
    Object localObject3 = null;
    localObject1 = null;
    Object localObject6 = localHashtable.keys();
    if (((Enumeration)localObject6).hasMoreElements())
    {
      localObject4 = (ResultPoint)((Enumeration)localObject6).nextElement();
      if (((Integer)localHashtable.get(localObject4)).intValue() == 2)
      {
        localObject3 = localObject2;
        localObject2 = localObject4;
      }
      while (true)
      {
        localObject4 = localObject3;
        localObject3 = localObject2;
        localObject2 = localObject4;
        break;
        if (localObject2 == null)
        {
          localObject2 = localObject3;
          localObject3 = localObject4;
        }
        else
        {
          localObject5 = localObject2;
          localObject1 = localObject4;
          localObject2 = localObject3;
          localObject3 = localObject5;
        }
      }
    }
    if ((localObject2 == null) || (localObject3 == null) || (localObject1 == null))
      throw NotFoundException.getNotFoundInstance();
    localObject6 = new ResultPoint[3];
    localObject6[0] = localObject2;
    localObject6[1] = localObject3;
    localObject6[2] = localObject1;
    ResultPoint.orderBestPatterns((ResultPoint[])localObject6);
    Object localObject4 = localObject6[0];
    Object localObject5 = localObject6[1];
    localObject6 = localObject6[2];
    if (!localHashtable.containsKey(localResultPoint1))
      localObject1 = localResultPoint1;
    while (true)
    {
      int j = Math.min(transitionsBetween((ResultPoint)localObject6, (ResultPoint)localObject1).getTransitions(), transitionsBetween((ResultPoint)localObject4, (ResultPoint)localObject1).getTransitions());
      int i = j;
      if ((j & 0x1) == 1)
        i = j + 1;
      localObject3 = correctTopRight(localObject5, (ResultPoint)localObject4, (ResultPoint)localObject6, (ResultPoint)localObject1, i + 2);
      localObject2 = localObject3;
      if (localObject3 == null)
        localObject2 = localObject1;
      j = Math.max(transitionsBetween((ResultPoint)localObject6, (ResultPoint)localObject2).getTransitions(), transitionsBetween((ResultPoint)localObject4, (ResultPoint)localObject2).getTransitions()) + 1;
      i = j;
      if ((j & 0x1) == 1)
        i = j + 1;
      return new DetectorResult(sampleGrid(this.image, (ResultPoint)localObject6, localObject5, (ResultPoint)localObject4, (ResultPoint)localObject2, i), new ResultPoint[] { localObject6, localObject5, localObject4, localObject2 });
      if (!localHashtable.containsKey(localResultPoint2))
        localObject1 = localResultPoint2;
      else if (!localHashtable.containsKey(localResultPoint3))
        localObject1 = localResultPoint3;
      else
        localObject1 = localResultPoint4;
    }
  }

  private static class ResultPointsAndTransitions
  {
    private final ResultPoint from;
    private final ResultPoint to;
    private final int transitions;

    private ResultPointsAndTransitions(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, int paramInt)
    {
      this.from = paramResultPoint1;
      this.to = paramResultPoint2;
      this.transitions = paramInt;
    }

    ResultPointsAndTransitions(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, int paramInt, Detector.1 param1)
    {
      this(paramResultPoint1, paramResultPoint2, paramInt);
    }

    public ResultPoint getFrom()
    {
      return this.from;
    }

    public ResultPoint getTo()
    {
      return this.to;
    }

    public int getTransitions()
    {
      return this.transitions;
    }

    public String toString()
    {
      return this.from + "/" + this.to + '/' + this.transitions;
    }
  }

  private static class ResultPointsAndTransitionsComparator
    implements Comparator
  {
    private ResultPointsAndTransitionsComparator()
    {
    }

    ResultPointsAndTransitionsComparator(Detector.1 param1)
    {
      this();
    }

    public int compare(Object paramObject1, Object paramObject2)
    {
      return ((Detector.ResultPointsAndTransitions)paramObject1).getTransitions() - ((Detector.ResultPointsAndTransitions)paramObject2).getTransitions();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.detector.Detector
 * JD-Core Version:    0.6.2
 */