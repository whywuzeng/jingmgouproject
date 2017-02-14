package com.google.zxing.common;

import java.util.Hashtable;

public final class CharacterSetECI extends ECI
{
  private static Hashtable NAME_TO_ECI;
  private static Hashtable VALUE_TO_ECI;
  private final String encodingName;

  private CharacterSetECI(int paramInt, String paramString)
  {
    super(paramInt);
    this.encodingName = paramString;
  }

  private static void addCharacterSet(int paramInt, String paramString)
  {
    CharacterSetECI localCharacterSetECI = new CharacterSetECI(paramInt, paramString);
    VALUE_TO_ECI.put(new Integer(paramInt), localCharacterSetECI);
    NAME_TO_ECI.put(paramString, localCharacterSetECI);
  }

  private static void addCharacterSet(int paramInt, String[] paramArrayOfString)
  {
    int i = 0;
    CharacterSetECI localCharacterSetECI = new CharacterSetECI(paramInt, paramArrayOfString[0]);
    VALUE_TO_ECI.put(new Integer(paramInt), localCharacterSetECI);
    paramInt = i;
    while (paramInt < paramArrayOfString.length)
    {
      NAME_TO_ECI.put(paramArrayOfString[paramInt], localCharacterSetECI);
      paramInt += 1;
    }
  }

  public static CharacterSetECI getCharacterSetECIByName(String paramString)
  {
    if (NAME_TO_ECI == null)
      initialize();
    return (CharacterSetECI)NAME_TO_ECI.get(paramString);
  }

  public static CharacterSetECI getCharacterSetECIByValue(int paramInt)
  {
    if (VALUE_TO_ECI == null)
      initialize();
    if ((paramInt < 0) || (paramInt >= 900))
      throw new IllegalArgumentException("Bad ECI value: " + paramInt);
    return (CharacterSetECI)VALUE_TO_ECI.get(new Integer(paramInt));
  }

  private static void initialize()
  {
    VALUE_TO_ECI = new Hashtable(29);
    NAME_TO_ECI = new Hashtable(29);
    addCharacterSet(0, "Cp437");
    addCharacterSet(1, new String[] { "ISO8859_1", "ISO-8859-1" });
    addCharacterSet(2, "Cp437");
    addCharacterSet(3, new String[] { "ISO8859_1", "ISO-8859-1" });
    addCharacterSet(4, "ISO8859_2");
    addCharacterSet(5, "ISO8859_3");
    addCharacterSet(6, "ISO8859_4");
    addCharacterSet(7, "ISO8859_5");
    addCharacterSet(8, "ISO8859_6");
    addCharacterSet(9, "ISO8859_7");
    addCharacterSet(10, "ISO8859_8");
    addCharacterSet(11, "ISO8859_9");
    addCharacterSet(12, "ISO8859_10");
    addCharacterSet(13, "ISO8859_11");
    addCharacterSet(15, "ISO8859_13");
    addCharacterSet(16, "ISO8859_14");
    addCharacterSet(17, "ISO8859_15");
    addCharacterSet(18, "ISO8859_16");
    addCharacterSet(20, new String[] { "SJIS", "Shift_JIS" });
  }

  public String getEncodingName()
  {
    return this.encodingName;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.CharacterSetECI
 * JD-Core Version:    0.6.2
 */