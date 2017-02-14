package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class GeneralAppIdDecoder
{
  private final StringBuffer buffer = new StringBuffer();
  private final CurrentParsingState current = new CurrentParsingState();
  private final BitArray information;

  GeneralAppIdDecoder(BitArray paramBitArray)
  {
    this.information = paramBitArray;
  }

  private DecodedChar decodeAlphanumeric(int paramInt)
  {
    int i = extractNumericValueFromBitArray(paramInt, 5);
    if (i == 15)
      return new DecodedChar(paramInt + 5, '$');
    if ((i >= 5) && (i < 15))
      return new DecodedChar(paramInt + 5, (char)(i + 48 - 5));
    i = extractNumericValueFromBitArray(paramInt, 6);
    if ((i >= 32) && (i < 58))
      return new DecodedChar(paramInt + 6, (char)(i + 33));
    switch (i)
    {
    default:
      throw new RuntimeException("Decoding invalid alphanumeric value: " + i);
    case 58:
      return new DecodedChar(paramInt + 6, '*');
    case 59:
      return new DecodedChar(paramInt + 6, ',');
    case 60:
      return new DecodedChar(paramInt + 6, '-');
    case 61:
      return new DecodedChar(paramInt + 6, '.');
    case 62:
    }
    return new DecodedChar(paramInt + 6, '/');
  }

  private DecodedChar decodeIsoIec646(int paramInt)
  {
    int i = extractNumericValueFromBitArray(paramInt, 5);
    if (i == 15)
      return new DecodedChar(paramInt + 5, '$');
    if ((i >= 5) && (i < 15))
      return new DecodedChar(paramInt + 5, (char)(i + 48 - 5));
    i = extractNumericValueFromBitArray(paramInt, 7);
    if ((i >= 64) && (i < 90))
      return new DecodedChar(paramInt + 7, (char)(i + 1));
    if ((i >= 90) && (i < 116))
      return new DecodedChar(paramInt + 7, (char)(i + 7));
    i = extractNumericValueFromBitArray(paramInt, 8);
    switch (i)
    {
    default:
      throw new RuntimeException("Decoding invalid ISO/IEC 646 value: " + i);
    case 232:
      return new DecodedChar(paramInt + 8, '!');
    case 233:
      return new DecodedChar(paramInt + 8, '"');
    case 234:
      return new DecodedChar(paramInt + 8, '%');
    case 235:
      return new DecodedChar(paramInt + 8, '&');
    case 236:
      return new DecodedChar(paramInt + 8, '\'');
    case 237:
      return new DecodedChar(paramInt + 8, '(');
    case 238:
      return new DecodedChar(paramInt + 8, ')');
    case 239:
      return new DecodedChar(paramInt + 8, '*');
    case 240:
      return new DecodedChar(paramInt + 8, '+');
    case 241:
      return new DecodedChar(paramInt + 8, ',');
    case 242:
      return new DecodedChar(paramInt + 8, '-');
    case 243:
      return new DecodedChar(paramInt + 8, '.');
    case 244:
      return new DecodedChar(paramInt + 8, '/');
    case 245:
      return new DecodedChar(paramInt + 8, ':');
    case 246:
      return new DecodedChar(paramInt + 8, ';');
    case 247:
      return new DecodedChar(paramInt + 8, '<');
    case 248:
      return new DecodedChar(paramInt + 8, '=');
    case 249:
      return new DecodedChar(paramInt + 8, '>');
    case 250:
      return new DecodedChar(paramInt + 8, '?');
    case 251:
      return new DecodedChar(paramInt + 8, '_');
    case 252:
    }
    return new DecodedChar(paramInt + 8, ' ');
  }

  private DecodedNumeric decodeNumeric(int paramInt)
  {
    if (paramInt + 7 > this.information.size)
    {
      paramInt = extractNumericValueFromBitArray(paramInt, 4);
      if (paramInt == 0)
        return new DecodedNumeric(this.information.size, 10, 10);
      return new DecodedNumeric(this.information.size, paramInt - 1, 10);
    }
    int i = extractNumericValueFromBitArray(paramInt, 7);
    return new DecodedNumeric(paramInt + 7, (i - 8) / 11, (i - 8) % 11);
  }

  static int extractNumericValueFromBitArray(BitArray paramBitArray, int paramInt1, int paramInt2)
  {
    int j = 0;
    if (paramInt2 > 32)
      throw new IllegalArgumentException("extractNumberValueFromBitArray can't handle more than 32 bits");
    int i = 0;
    while (i < paramInt2)
    {
      int k = j;
      if (paramBitArray.get(paramInt1 + i))
        k = j | 1 << paramInt2 - i - 1;
      i += 1;
      j = k;
    }
    return j;
  }

  private boolean isAlphaOr646ToNumericLatch(int paramInt)
  {
    if (paramInt + 3 > this.information.size)
      return false;
    int i = paramInt;
    while (true)
    {
      if (i >= paramInt + 3)
        break label42;
      if (this.information.get(i))
        break;
      i += 1;
    }
    label42: return true;
  }

  private boolean isAlphaTo646ToAlphaLatch(int paramInt)
  {
    if (paramInt + 1 > this.information.size);
    int i;
    do
    {
      return false;
      i = 0;
      if ((i >= 5) || (i + paramInt >= this.information.size))
        break label75;
      if (i != 2)
        break;
    }
    while (!this.information.get(paramInt + 2));
    while (!this.information.get(paramInt + i))
    {
      i += 1;
      break;
    }
    return false;
    label75: return true;
  }

  private boolean isNumericToAlphaNumericLatch(int paramInt)
  {
    if (paramInt + 1 > this.information.size)
      return false;
    int i = 0;
    while (true)
    {
      if ((i >= 4) || (i + paramInt >= this.information.size))
        break label55;
      if (this.information.get(paramInt + i))
        break;
      i += 1;
    }
    label55: return true;
  }

  private boolean isStillAlpha(int paramInt)
  {
    boolean bool = true;
    if (paramInt + 5 > this.information.size);
    do
    {
      return false;
      int i = extractNumericValueFromBitArray(paramInt, 5);
      if ((i >= 5) && (i < 16))
        return true;
    }
    while (paramInt + 6 > this.information.size);
    paramInt = extractNumericValueFromBitArray(paramInt, 6);
    if ((paramInt >= 16) && (paramInt < 63));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  private boolean isStillIsoIec646(int paramInt)
  {
    boolean bool = true;
    if (paramInt + 5 > this.information.size);
    do
    {
      do
      {
        return false;
        i = extractNumericValueFromBitArray(paramInt, 5);
        if ((i >= 5) && (i < 16))
          return true;
      }
      while (paramInt + 7 > this.information.size);
      int i = extractNumericValueFromBitArray(paramInt, 7);
      if ((i >= 64) && (i < 116))
        return true;
    }
    while (paramInt + 8 > this.information.size);
    paramInt = extractNumericValueFromBitArray(paramInt, 8);
    if ((paramInt >= 232) && (paramInt < 253));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  private boolean isStillNumeric(int paramInt)
  {
    if (paramInt + 7 > this.information.size)
      return paramInt + 4 <= this.information.size;
    int i = paramInt;
    while (true)
    {
      if (i >= paramInt + 3)
        break label58;
      if (this.information.get(i))
        break;
      i += 1;
    }
    label58: return this.information.get(paramInt + 3);
  }

  private BlockParsedResult parseAlphaBlock()
  {
    Object localObject;
    while (isStillAlpha(this.current.position))
    {
      localObject = decodeAlphanumeric(this.current.position);
      this.current.position = ((DecodedChar)localObject).getNewPosition();
      if (((DecodedChar)localObject).isFNC1())
        return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
      this.buffer.append(((DecodedChar)localObject).getValue());
    }
    if (isAlphaOr646ToNumericLatch(this.current.position))
    {
      localObject = this.current;
      ((CurrentParsingState)localObject).position += 3;
      this.current.setNumeric();
    }
    while (!isAlphaTo646ToAlphaLatch(this.current.position))
      return new BlockParsedResult(false);
    if (this.current.position + 5 < this.information.size)
      localObject = this.current;
    for (((CurrentParsingState)localObject).position += 5; ; this.current.position = this.information.size)
    {
      this.current.setIsoIec646();
      break;
    }
  }

  private DecodedInformation parseBlocks()
  {
    int i = this.current.position;
    BlockParsedResult localBlockParsedResult;
    boolean bool;
    if (this.current.isAlpha())
    {
      localBlockParsedResult = parseAlphaBlock();
      bool = localBlockParsedResult.isFinished();
      label28: if (i == this.current.position)
        break label90;
      i = 1;
      label41: if ((i != 0) || (bool))
        break label95;
    }
    while (true)
    {
      return localBlockParsedResult.getDecodedInformation();
      if (this.current.isIsoIec646())
      {
        localBlockParsedResult = parseIsoIec646Block();
        bool = localBlockParsedResult.isFinished();
        break label28;
      }
      localBlockParsedResult = parseNumericBlock();
      bool = localBlockParsedResult.isFinished();
      break label28;
      label90: i = 0;
      break label41;
      label95: if (!bool)
        break;
    }
  }

  private BlockParsedResult parseIsoIec646Block()
  {
    Object localObject;
    while (isStillIsoIec646(this.current.position))
    {
      localObject = decodeIsoIec646(this.current.position);
      this.current.position = ((DecodedChar)localObject).getNewPosition();
      if (((DecodedChar)localObject).isFNC1())
        return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
      this.buffer.append(((DecodedChar)localObject).getValue());
    }
    if (isAlphaOr646ToNumericLatch(this.current.position))
    {
      localObject = this.current;
      ((CurrentParsingState)localObject).position += 3;
      this.current.setNumeric();
    }
    while (!isAlphaTo646ToAlphaLatch(this.current.position))
      return new BlockParsedResult(false);
    if (this.current.position + 5 < this.information.size)
      localObject = this.current;
    for (((CurrentParsingState)localObject).position += 5; ; this.current.position = this.information.size)
    {
      this.current.setAlpha();
      break;
    }
  }

  private BlockParsedResult parseNumericBlock()
  {
    Object localObject;
    while (isStillNumeric(this.current.position))
    {
      localObject = decodeNumeric(this.current.position);
      this.current.position = ((DecodedNumeric)localObject).getNewPosition();
      if (((DecodedNumeric)localObject).isFirstDigitFNC1())
      {
        if (((DecodedNumeric)localObject).isSecondDigitFNC1());
        for (localObject = new DecodedInformation(this.current.position, this.buffer.toString()); ; localObject = new DecodedInformation(this.current.position, this.buffer.toString(), ((DecodedNumeric)localObject).getSecondDigit()))
          return new BlockParsedResult((DecodedInformation)localObject, true);
      }
      this.buffer.append(((DecodedNumeric)localObject).getFirstDigit());
      if (((DecodedNumeric)localObject).isSecondDigitFNC1())
        return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
      this.buffer.append(((DecodedNumeric)localObject).getSecondDigit());
    }
    if (isNumericToAlphaNumericLatch(this.current.position))
    {
      this.current.setAlpha();
      localObject = this.current;
      ((CurrentParsingState)localObject).position += 4;
    }
    return new BlockParsedResult(false);
  }

  String decodeAllCodes(StringBuffer paramStringBuffer, int paramInt)
    throws NotFoundException
  {
    String str = null;
    while (true)
    {
      DecodedInformation localDecodedInformation = decodeGeneralPurposeField(paramInt, str);
      paramStringBuffer.append(FieldParser.parseFieldsInGeneralPurpose(localDecodedInformation.getNewString()));
      if (localDecodedInformation.isRemaining());
      for (str = String.valueOf(localDecodedInformation.getRemainingValue()); paramInt == localDecodedInformation.getNewPosition(); str = null)
        return paramStringBuffer.toString();
      paramInt = localDecodedInformation.getNewPosition();
    }
  }

  DecodedInformation decodeGeneralPurposeField(int paramInt, String paramString)
  {
    this.buffer.setLength(0);
    if (paramString != null)
      this.buffer.append(paramString);
    this.current.position = paramInt;
    paramString = parseBlocks();
    if ((paramString != null) && (paramString.isRemaining()))
      return new DecodedInformation(this.current.position, this.buffer.toString(), paramString.getRemainingValue());
    return new DecodedInformation(this.current.position, this.buffer.toString());
  }

  int extractNumericValueFromBitArray(int paramInt1, int paramInt2)
  {
    return extractNumericValueFromBitArray(this.information, paramInt1, paramInt2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.GeneralAppIdDecoder
 * JD-Core Version:    0.6.2
 */