package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Hashtable;

public class Detector
{
  private final BitMatrix image;
  private ResultPointCallback resultPointCallback;

  public Detector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
  }

  private float calculateModuleSizeOneWay(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    float f1 = sizeOfBlackWhiteBlackRunBothWays((int)paramResultPoint1.getX(), (int)paramResultPoint1.getY(), (int)paramResultPoint2.getX(), (int)paramResultPoint2.getY());
    float f2 = sizeOfBlackWhiteBlackRunBothWays((int)paramResultPoint2.getX(), (int)paramResultPoint2.getY(), (int)paramResultPoint1.getX(), (int)paramResultPoint1.getY());
    if (Float.isNaN(f1))
      return f2 / 7.0F;
    if (Float.isNaN(f2))
      return f1 / 7.0F;
    return (f1 + f2) / 14.0F;
  }

  protected static int computeDimension(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, float paramFloat)
    throws NotFoundException
  {
    int i = (round(ResultPoint.distance(paramResultPoint1, paramResultPoint2) / paramFloat) + round(ResultPoint.distance(paramResultPoint1, paramResultPoint3) / paramFloat) >> 1) + 7;
    switch (i & 0x3)
    {
    case 1:
    default:
      return i;
    case 0:
      return i + 1;
    case 2:
      return i - 1;
    case 3:
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int round(float paramFloat)
  {
    return (int)(0.5F + paramFloat);
  }

  private static BitMatrix sampleGrid(BitMatrix paramBitMatrix, PerspectiveTransform paramPerspectiveTransform, int paramInt)
    throws NotFoundException
  {
    return GridSampler.getInstance().sampleGrid(paramBitMatrix, paramInt, paramPerspectiveTransform);
  }

  private float sizeOfBlackWhiteBlackRun(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int k;
    int n;
    int m;
    int i;
    if (Math.abs(paramInt4 - paramInt2) > Math.abs(paramInt3 - paramInt1))
    {
      k = 1;
      if (k == 0)
        break label301;
      n = paramInt4;
      m = paramInt3;
      i = paramInt2;
      paramInt4 = paramInt1;
    }
    while (true)
    {
      int i5 = Math.abs(n - i);
      int i6 = Math.abs(m - paramInt4);
      paramInt1 = -i5;
      int i1;
      label71: int i2;
      label81: int j;
      int i3;
      label108: int i4;
      if (paramInt4 < m)
      {
        i1 = 1;
        if (i >= n)
          break label188;
        i2 = 1;
        paramInt3 = 0;
        paramInt2 = i;
        j = paramInt1 >> 1;
        paramInt1 = paramInt4;
        if (paramInt2 == n)
          break label245;
        if (k == 0)
          break label194;
        i3 = paramInt1;
        if (k == 0)
          break label200;
        i4 = paramInt2;
        label116: if (paramInt3 != 1)
          break label206;
        if (!this.image.get(i3, i4))
          break label298;
        paramInt3 += 1;
      }
      label139: label293: label298: 
      while (true)
      {
        if (paramInt3 == 3)
        {
          paramInt2 -= i;
          paramInt3 = paramInt1 - paramInt4;
          if (i2 >= 0)
            break label293;
        }
        for (paramInt1 = paramInt2 + 1; ; paramInt1 = paramInt2)
        {
          return (float)Math.sqrt(paramInt1 * paramInt1 + paramInt3 * paramInt3);
          k = 0;
          break;
          i1 = -1;
          break label71;
          label188: i2 = -1;
          break label81;
          label194: i3 = paramInt2;
          break label108;
          label200: i4 = paramInt1;
          break label116;
          label206: if (this.image.get(i3, i4))
            break label298;
          paramInt3 += 1;
          break label139;
          j += i6;
          if (j > 0)
          {
            if (paramInt1 == m)
            {
              label245: paramInt1 = n - i;
              paramInt2 = m - paramInt4;
              return (float)Math.sqrt(paramInt1 * paramInt1 + paramInt2 * paramInt2);
            }
            paramInt1 += i1;
            j -= i5;
          }
          while (true)
          {
            paramInt2 += i2;
            break;
          }
        }
      }
      label301: m = paramInt4;
      paramInt4 = paramInt2;
      i = paramInt1;
      n = paramInt3;
    }
  }

  private float sizeOfBlackWhiteBlackRunBothWays(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    float f2 = sizeOfBlackWhiteBlackRun(paramInt1, paramInt2, paramInt3, paramInt4);
    paramInt3 = paramInt1 - (paramInt3 - paramInt1);
    float f1;
    if (paramInt3 < 0)
    {
      f1 = paramInt1 / (paramInt1 - paramInt3);
      paramInt3 = 0;
    }
    while (true)
    {
      paramInt4 = (int)(paramInt2 - f1 * (paramInt4 - paramInt2));
      if (paramInt4 < 0)
      {
        f1 = paramInt2 / (paramInt2 - paramInt4);
        paramInt4 = i;
      }
      while (true)
      {
        float f3 = paramInt1;
        return sizeOfBlackWhiteBlackRun(paramInt1, paramInt2, (int)(f1 * (paramInt3 - paramInt1) + f3), paramInt4) + f2;
        if (paramInt3 <= this.image.getWidth())
          break label182;
        f1 = (this.image.getWidth() - paramInt1) / (paramInt3 - paramInt1);
        paramInt3 = this.image.getWidth();
        break;
        if (paramInt4 > this.image.getHeight())
        {
          f1 = (this.image.getHeight() - paramInt2) / (paramInt4 - paramInt2);
          paramInt4 = this.image.getHeight();
        }
        else
        {
          f1 = 1.0F;
        }
      }
      label182: f1 = 1.0F;
    }
  }

  protected float calculateModuleSize(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3)
  {
    return (calculateModuleSizeOneWay(paramResultPoint1, paramResultPoint2) + calculateModuleSizeOneWay(paramResultPoint1, paramResultPoint3)) / 2.0F;
  }

  public PerspectiveTransform createTransform(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt)
  {
    float f1 = paramInt - 3.5F;
    float f4;
    float f5;
    float f3;
    if (paramResultPoint4 != null)
    {
      f4 = paramResultPoint4.getX();
      f5 = paramResultPoint4.getY();
      f3 = f1 - 3.0F;
    }
    for (float f2 = f3; ; f2 = f1)
    {
      return PerspectiveTransform.quadrilateralToQuadrilateral(3.5F, 3.5F, f1, 3.5F, f2, f3, 3.5F, f1, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint2.getX(), paramResultPoint2.getY(), f4, f5, paramResultPoint3.getX(), paramResultPoint3.getY());
      f4 = paramResultPoint2.getX() - paramResultPoint1.getX() + paramResultPoint3.getX();
      f5 = paramResultPoint2.getY() - paramResultPoint1.getY() + paramResultPoint3.getY();
      f3 = f1;
    }
  }

  public DetectorResult detect()
    throws NotFoundException, FormatException
  {
    return detect(null);
  }

  public DetectorResult detect(Hashtable paramHashtable)
    throws NotFoundException, FormatException
  {
    if (paramHashtable == null);
    for (ResultPointCallback localResultPointCallback = null; ; localResultPointCallback = (ResultPointCallback)paramHashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
    {
      this.resultPointCallback = localResultPointCallback;
      return processFinderPatternInfo(new FinderPatternFinder(this.image, this.resultPointCallback).find(paramHashtable));
    }
  }

  protected AlignmentPattern findAlignmentInRegion(float paramFloat1, int paramInt1, int paramInt2, float paramFloat2)
    throws NotFoundException
  {
    int j = (int)(paramFloat2 * paramFloat1);
    int i = Math.max(0, paramInt1 - j);
    paramInt1 = Math.min(this.image.getWidth() - 1, paramInt1 + j);
    if (paramInt1 - i < paramFloat1 * 3.0F)
      throw NotFoundException.getNotFoundInstance();
    int k = Math.max(0, paramInt2 - j);
    paramInt2 = Math.min(this.image.getHeight() - 1, j + paramInt2);
    if (paramInt2 - k < paramFloat1 * 3.0F)
      throw NotFoundException.getNotFoundInstance();
    return new AlignmentPatternFinder(this.image, i, k, paramInt1 - i, paramInt2 - k, paramFloat1, this.resultPointCallback).find();
  }

  protected BitMatrix getImage()
  {
    return this.image;
  }

  protected ResultPointCallback getResultPointCallback()
  {
    return this.resultPointCallback;
  }

  protected DetectorResult processFinderPatternInfo(FinderPatternInfo paramFinderPatternInfo)
    throws NotFoundException, FormatException
  {
    FinderPattern localFinderPattern1 = paramFinderPatternInfo.getTopLeft();
    FinderPattern localFinderPattern2 = paramFinderPatternInfo.getTopRight();
    FinderPattern localFinderPattern3 = paramFinderPatternInfo.getBottomLeft();
    float f1 = calculateModuleSize(localFinderPattern1, localFinderPattern2, localFinderPattern3);
    if (f1 < 1.0F)
      throw NotFoundException.getNotFoundInstance();
    int j = computeDimension(localFinderPattern1, localFinderPattern2, localFinderPattern3, f1);
    Object localObject2 = Version.getProvisionalVersionForDimension(j);
    int i = ((Version)localObject2).getDimensionForVersion();
    Object localObject1 = null;
    paramFinderPatternInfo = (FinderPatternInfo)localObject1;
    float f2;
    int k;
    int m;
    if (((Version)localObject2).getAlignmentPatternCenters().length > 0)
    {
      f2 = localFinderPattern2.getX();
      float f3 = localFinderPattern1.getX();
      float f4 = localFinderPattern3.getX();
      float f5 = localFinderPattern2.getY();
      float f6 = localFinderPattern1.getY();
      float f7 = localFinderPattern3.getY();
      float f8 = 1.0F - 3.0F / (i - 7);
      float f9 = localFinderPattern1.getX();
      k = (int)((f2 - f3 + f4 - localFinderPattern1.getX()) * f8 + f9);
      m = (int)(localFinderPattern1.getY() + f8 * (f5 - f6 + f7 - localFinderPattern1.getY()));
      i = 4;
      paramFinderPatternInfo = (FinderPatternInfo)localObject1;
      if (i <= 16)
        f2 = i;
    }
    while (true)
    {
      try
      {
        paramFinderPatternInfo = findAlignmentInRegion(f1, k, m, f2);
        localObject1 = createTransform(localFinderPattern1, localFinderPattern2, localFinderPattern3, paramFinderPatternInfo, j);
        localObject2 = sampleGrid(this.image, (PerspectiveTransform)localObject1, j);
        if (paramFinderPatternInfo != null)
          break label290;
        paramFinderPatternInfo = new ResultPoint[3];
        paramFinderPatternInfo[0] = localFinderPattern3;
        paramFinderPatternInfo[1] = localFinderPattern1;
        paramFinderPatternInfo[2] = localFinderPattern2;
        return new DetectorResult((BitMatrix)localObject2, paramFinderPatternInfo);
      }
      catch (NotFoundException paramFinderPatternInfo)
      {
        i <<= 1;
      }
      break;
      label290: localObject1 = new ResultPoint[4];
      localObject1[0] = localFinderPattern3;
      localObject1[1] = localFinderPattern1;
      localObject1[2] = localFinderPattern2;
      localObject1[3] = paramFinderPatternInfo;
      paramFinderPatternInfo = (FinderPatternInfo)localObject1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.Detector
 * JD-Core Version:    0.6.2
 */