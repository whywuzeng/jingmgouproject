package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;

public final class QRCode
{
  public static final int NUM_MASK_PATTERNS = 8;
  private ErrorCorrectionLevel ecLevel = null;
  private int maskPattern = -1;
  private ByteMatrix matrix = null;
  private int matrixWidth = -1;
  private Mode mode = null;
  private int numDataBytes = -1;
  private int numECBytes = -1;
  private int numRSBlocks = -1;
  private int numTotalBytes = -1;
  private int version = -1;

  public static boolean isValidMaskPattern(int paramInt)
  {
    return (paramInt >= 0) && (paramInt < 8);
  }

  public int at(int paramInt1, int paramInt2)
  {
    paramInt1 = this.matrix.get(paramInt1, paramInt2);
    if ((paramInt1 != 0) && (paramInt1 != 1))
      throw new RuntimeException("Bad value");
    return paramInt1;
  }

  public ErrorCorrectionLevel getECLevel()
  {
    return this.ecLevel;
  }

  public int getMaskPattern()
  {
    return this.maskPattern;
  }

  public ByteMatrix getMatrix()
  {
    return this.matrix;
  }

  public int getMatrixWidth()
  {
    return this.matrixWidth;
  }

  public Mode getMode()
  {
    return this.mode;
  }

  public int getNumDataBytes()
  {
    return this.numDataBytes;
  }

  public int getNumECBytes()
  {
    return this.numECBytes;
  }

  public int getNumRSBlocks()
  {
    return this.numRSBlocks;
  }

  public int getNumTotalBytes()
  {
    return this.numTotalBytes;
  }

  public int getVersion()
  {
    return this.version;
  }

  public boolean isValid()
  {
    return (this.mode != null) && (this.ecLevel != null) && (this.version != -1) && (this.matrixWidth != -1) && (this.maskPattern != -1) && (this.numTotalBytes != -1) && (this.numDataBytes != -1) && (this.numECBytes != -1) && (this.numRSBlocks != -1) && (isValidMaskPattern(this.maskPattern)) && (this.numTotalBytes == this.numDataBytes + this.numECBytes) && (this.matrix != null) && (this.matrixWidth == this.matrix.getWidth()) && (this.matrix.getWidth() == this.matrix.getHeight());
  }

  public void setECLevel(ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    this.ecLevel = paramErrorCorrectionLevel;
  }

  public void setMaskPattern(int paramInt)
  {
    this.maskPattern = paramInt;
  }

  public void setMatrix(ByteMatrix paramByteMatrix)
  {
    this.matrix = paramByteMatrix;
  }

  public void setMatrixWidth(int paramInt)
  {
    this.matrixWidth = paramInt;
  }

  public void setMode(Mode paramMode)
  {
    this.mode = paramMode;
  }

  public void setNumDataBytes(int paramInt)
  {
    this.numDataBytes = paramInt;
  }

  public void setNumECBytes(int paramInt)
  {
    this.numECBytes = paramInt;
  }

  public void setNumRSBlocks(int paramInt)
  {
    this.numRSBlocks = paramInt;
  }

  public void setNumTotalBytes(int paramInt)
  {
    this.numTotalBytes = paramInt;
  }

  public void setVersion(int paramInt)
  {
    this.version = paramInt;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(200);
    localStringBuffer.append("<<\n");
    localStringBuffer.append(" mode: ");
    localStringBuffer.append(this.mode);
    localStringBuffer.append("\n ecLevel: ");
    localStringBuffer.append(this.ecLevel);
    localStringBuffer.append("\n version: ");
    localStringBuffer.append(this.version);
    localStringBuffer.append("\n matrixWidth: ");
    localStringBuffer.append(this.matrixWidth);
    localStringBuffer.append("\n maskPattern: ");
    localStringBuffer.append(this.maskPattern);
    localStringBuffer.append("\n numTotalBytes: ");
    localStringBuffer.append(this.numTotalBytes);
    localStringBuffer.append("\n numDataBytes: ");
    localStringBuffer.append(this.numDataBytes);
    localStringBuffer.append("\n numECBytes: ");
    localStringBuffer.append(this.numECBytes);
    localStringBuffer.append("\n numRSBlocks: ");
    localStringBuffer.append(this.numRSBlocks);
    if (this.matrix == null)
      localStringBuffer.append("\n matrix: null\n");
    while (true)
    {
      localStringBuffer.append(">>\n");
      return localStringBuffer.toString();
      localStringBuffer.append("\n matrix:\n");
      localStringBuffer.append(this.matrix.toString());
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.encoder.QRCode
 * JD-Core Version:    0.6.2
 */