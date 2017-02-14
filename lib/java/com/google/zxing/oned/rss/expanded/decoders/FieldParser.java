package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;

final class FieldParser
{
  private static final Object[][] FOUR_DIGIT_DATA_LENGTH = { { "7001", localObject1 }, localObject10, { "7003", localInteger1 }, { "8001", localObject2 }, { "8002", localObject3, localObject4 }, localObject11, localObject12, { "8005", localInteger2 }, localObject13, localObject14, { "8008", localObject5, localInteger3 }, { "8018", localObject6 }, { "8020", localObject7, localObject8 }, { "8100", localInteger4 }, localObject15, localObject16, { "8110", localObject9, localInteger5 } };
  private static final Object[][] THREE_DIGIT_DATA_LENGTH;
  private static final Object[][] THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
  private static final Object[][] TWO_DIGIT_DATA_LENGTH;
  private static final Object VARIABLE_LENGTH = new Object();

  static
  {
    TWO_DIGIT_DATA_LENGTH = new Object[][] { { "00", new Integer(18) }, { "01", new Integer(14) }, { "02", new Integer(14) }, { "10", VARIABLE_LENGTH, new Integer(20) }, { "11", new Integer(6) }, { "12", new Integer(6) }, { "13", new Integer(6) }, { "15", new Integer(6) }, { "17", new Integer(6) }, { "20", new Integer(2) }, { "21", VARIABLE_LENGTH, new Integer(20) }, { "22", VARIABLE_LENGTH, new Integer(29) }, { "30", VARIABLE_LENGTH, new Integer(8) }, { "37", VARIABLE_LENGTH, new Integer(8) }, { "90", VARIABLE_LENGTH, new Integer(30) }, { "91", VARIABLE_LENGTH, new Integer(30) }, { "92", VARIABLE_LENGTH, new Integer(30) }, { "93", VARIABLE_LENGTH, new Integer(30) }, { "94", VARIABLE_LENGTH, new Integer(30) }, { "95", VARIABLE_LENGTH, new Integer(30) }, { "96", VARIABLE_LENGTH, new Integer(30) }, { "97", VARIABLE_LENGTH, new Integer(30) }, { "98", VARIABLE_LENGTH, new Integer(30) }, { "99", VARIABLE_LENGTH, new Integer(30) } };
    Object localObject1 = VARIABLE_LENGTH;
    Integer localInteger1 = new Integer(30);
    Object localObject2 = VARIABLE_LENGTH;
    Object localObject3 = new Integer(30);
    Object localObject4 = VARIABLE_LENGTH;
    Integer localInteger2 = new Integer(6);
    Object localObject5 = VARIABLE_LENGTH;
    Integer localInteger3 = new Integer(30);
    Object localObject6 = VARIABLE_LENGTH;
    Object localObject7 = new Integer(30);
    Object localObject8 = VARIABLE_LENGTH;
    Integer localInteger4 = new Integer(17);
    Object localObject9 = VARIABLE_LENGTH;
    Integer localInteger5 = new Integer(20);
    Object localObject10 = VARIABLE_LENGTH;
    Object localObject11 = new Integer(30);
    Object localObject12 = VARIABLE_LENGTH;
    Object localObject13 = new Integer(30);
    Object localObject14 = new Integer(17);
    Object localObject15 = VARIABLE_LENGTH;
    Object localObject16 = new Integer(30);
    Integer localInteger6 = new Integer(13);
    Object localObject20 = { "411", new Integer(13) };
    Integer localInteger7 = new Integer(13);
    Integer localInteger8 = new Integer(13);
    Integer localInteger9 = new Integer(13);
    Object localObject17 = VARIABLE_LENGTH;
    Integer localInteger10 = new Integer(20);
    Object localObject18 = VARIABLE_LENGTH;
    Integer localInteger11 = new Integer(15);
    Integer localInteger12 = new Integer(3);
    Object localObject19 = VARIABLE_LENGTH;
    Integer localInteger13 = new Integer(15);
    Integer localInteger14 = new Integer(3);
    Integer localInteger15 = new Integer(3);
    Integer localInteger16 = new Integer(3);
    THREE_DIGIT_DATA_LENGTH = new Object[][] { { "240", localObject1, localInteger1 }, { "241", localObject2, localObject3 }, { "242", localObject4, localInteger2 }, { "250", localObject5, localInteger3 }, { "251", localObject6, localObject7 }, { "253", localObject8, localInteger4 }, { "254", localObject9, localInteger5 }, { "400", localObject10, localObject11 }, { "401", localObject12, localObject13 }, { "402", localObject14 }, { "403", localObject15, localObject16 }, { "410", localInteger6 }, localObject20, { "412", localInteger7 }, { "413", localInteger8 }, { "414", localInteger9 }, { "420", localObject17, localInteger10 }, { "421", localObject18, localInteger11 }, { "422", localInteger12 }, { "423", localObject19, localInteger13 }, { "424", localInteger14 }, { "425", localInteger15 }, { "426", localInteger16 } };
    localObject1 = new Integer(6);
    localInteger1 = new Integer(6);
    localObject2 = new Integer(6);
    localObject3 = new Integer(6);
    localObject4 = new Integer(6);
    localInteger2 = new Integer(6);
    localObject5 = new Integer(6);
    localInteger3 = new Integer(6);
    localObject6 = new Integer(6);
    localObject7 = new Integer(6);
    localObject8 = new Integer(6);
    localInteger4 = new Integer(6);
    localObject9 = new Integer(6);
    localInteger5 = new Integer(6);
    localObject10 = new Integer(6);
    localObject11 = new Integer(6);
    localObject12 = new Integer(6);
    localObject13 = new Integer(6);
    localObject14 = new Integer(6);
    localObject15 = new Integer(6);
    localObject16 = new Integer(6);
    localInteger6 = new Integer(6);
    localInteger7 = new Integer(6);
    localInteger8 = new Integer(6);
    localInteger9 = new Integer(6);
    localObject17 = new Integer(6);
    localInteger10 = new Integer(6);
    localObject18 = new Integer(6);
    localInteger11 = new Integer(6);
    Object[] arrayOfObject1 = { "345", new Integer(6) };
    localInteger12 = new Integer(6);
    localObject19 = new Integer(6);
    localInteger13 = new Integer(6);
    Object[] arrayOfObject2 = { "349", new Integer(6) };
    localInteger14 = new Integer(6);
    localInteger15 = new Integer(6);
    localInteger16 = new Integer(6);
    Object[] arrayOfObject3 = { "353", new Integer(6) };
    Object[] arrayOfObject4 = { "354", new Integer(6) };
    Object[] arrayOfObject5 = { "355", new Integer(6) };
    localObject20 = new Integer(6);
    Object[] arrayOfObject6 = { "357", new Integer(6) };
    Integer localInteger17 = new Integer(6);
    Integer localInteger18 = new Integer(6);
    Integer localInteger19 = new Integer(6);
    Integer localInteger20 = new Integer(6);
    Integer localInteger21 = new Integer(6);
    Integer localInteger22 = new Integer(6);
    Integer localInteger23 = new Integer(6);
    Integer localInteger24 = new Integer(6);
    Integer localInteger25 = new Integer(6);
    Integer localInteger26 = new Integer(6);
    Object localObject21 = VARIABLE_LENGTH;
    Integer localInteger27 = new Integer(15);
    Object localObject22 = VARIABLE_LENGTH;
    Integer localInteger28 = new Integer(18);
    Object[] arrayOfObject7 = { "392", VARIABLE_LENGTH, new Integer(15) };
    Object[] arrayOfObject8 = { "393", VARIABLE_LENGTH, new Integer(18) };
    Object localObject23 = VARIABLE_LENGTH;
    Integer localInteger29 = new Integer(30);
    THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = new Object[][] { { "310", localObject1 }, { "311", localInteger1 }, { "312", localObject2 }, { "313", localObject3 }, { "314", localObject4 }, { "315", localInteger2 }, { "316", localObject5 }, { "320", localInteger3 }, { "321", localObject6 }, { "322", localObject7 }, { "323", localObject8 }, { "324", localInteger4 }, { "325", localObject9 }, { "326", localInteger5 }, { "327", localObject10 }, { "328", localObject11 }, { "329", localObject12 }, { "330", localObject13 }, { "331", localObject14 }, { "332", localObject15 }, { "333", localObject16 }, { "334", localInteger6 }, { "335", localInteger7 }, { "336", localInteger8 }, { "340", localInteger9 }, { "341", localObject17 }, { "342", localInteger10 }, { "343", localObject18 }, { "344", localInteger11 }, arrayOfObject1, { "346", localInteger12 }, { "347", localObject19 }, { "348", localInteger13 }, arrayOfObject2, { "350", localInteger14 }, { "351", localInteger15 }, { "352", localInteger16 }, arrayOfObject3, arrayOfObject4, arrayOfObject5, { "356", localObject20 }, arrayOfObject6, { "360", localInteger17 }, { "361", localInteger18 }, { "362", localInteger19 }, { "363", localInteger20 }, { "364", localInteger21 }, { "365", localInteger22 }, { "366", localInteger23 }, { "367", localInteger24 }, { "368", localInteger25 }, { "369", localInteger26 }, { "390", localObject21, localInteger27 }, { "391", localObject22, localInteger28 }, arrayOfObject7, arrayOfObject8, { "703", localObject23, localInteger29 } };
    localObject1 = new Integer(13);
    localObject10 = new Object[] { "7002", VARIABLE_LENGTH, new Integer(30) };
    localInteger1 = new Integer(10);
    localObject2 = new Integer(14);
    localObject3 = VARIABLE_LENGTH;
    localObject4 = new Integer(20);
    localObject11 = new Object[] { "8003", VARIABLE_LENGTH, new Integer(30) };
    localObject12 = new Object[] { "8004", VARIABLE_LENGTH, new Integer(30) };
    localInteger2 = new Integer(6);
    localObject13 = new Object[] { "8006", new Integer(18) };
    localObject14 = new Object[] { "8007", VARIABLE_LENGTH, new Integer(30) };
    localObject5 = VARIABLE_LENGTH;
    localInteger3 = new Integer(12);
    localObject6 = new Integer(18);
    localObject7 = VARIABLE_LENGTH;
    localObject8 = new Integer(25);
    localInteger4 = new Integer(6);
    localObject15 = new Object[] { "8101", new Integer(10) };
    localObject16 = new Object[] { "8102", new Integer(2) };
    localObject9 = VARIABLE_LENGTH;
    localInteger5 = new Integer(30);
  }

  static String parseFieldsInGeneralPurpose(String paramString)
    throws NotFoundException
  {
    if (paramString.length() == 0)
      return "";
    if (paramString.length() < 2)
      throw NotFoundException.getNotFoundInstance();
    String str = paramString.substring(0, 2);
    int i = 0;
    while (i < TWO_DIGIT_DATA_LENGTH.length)
    {
      if (TWO_DIGIT_DATA_LENGTH[i][0].equals(str))
      {
        if (TWO_DIGIT_DATA_LENGTH[i][1] == VARIABLE_LENGTH)
          return processVariableAI(2, ((Integer)TWO_DIGIT_DATA_LENGTH[i][2]).intValue(), paramString);
        return processFixedAI(2, ((Integer)TWO_DIGIT_DATA_LENGTH[i][1]).intValue(), paramString);
      }
      i += 1;
    }
    if (paramString.length() < 3)
      throw NotFoundException.getNotFoundInstance();
    str = paramString.substring(0, 3);
    i = 0;
    while (i < THREE_DIGIT_DATA_LENGTH.length)
    {
      if (THREE_DIGIT_DATA_LENGTH[i][0].equals(str))
      {
        if (THREE_DIGIT_DATA_LENGTH[i][1] == VARIABLE_LENGTH)
          return processVariableAI(3, ((Integer)THREE_DIGIT_DATA_LENGTH[i][2]).intValue(), paramString);
        return processFixedAI(3, ((Integer)THREE_DIGIT_DATA_LENGTH[i][1]).intValue(), paramString);
      }
      i += 1;
    }
    i = 0;
    while (i < THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH.length)
    {
      if (THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[i][0].equals(str))
      {
        if (THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[i][1] == VARIABLE_LENGTH)
          return processVariableAI(4, ((Integer)THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[i][2]).intValue(), paramString);
        return processFixedAI(4, ((Integer)THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[i][1]).intValue(), paramString);
      }
      i += 1;
    }
    if (paramString.length() < 4)
      throw NotFoundException.getNotFoundInstance();
    str = paramString.substring(0, 4);
    i = 0;
    while (i < FOUR_DIGIT_DATA_LENGTH.length)
    {
      if (FOUR_DIGIT_DATA_LENGTH[i][0].equals(str))
      {
        if (FOUR_DIGIT_DATA_LENGTH[i][1] == VARIABLE_LENGTH)
          return processVariableAI(4, ((Integer)FOUR_DIGIT_DATA_LENGTH[i][2]).intValue(), paramString);
        return processFixedAI(4, ((Integer)FOUR_DIGIT_DATA_LENGTH[i][1]).intValue(), paramString);
      }
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static String processFixedAI(int paramInt1, int paramInt2, String paramString)
    throws NotFoundException
  {
    if (paramString.length() < paramInt1)
      throw NotFoundException.getNotFoundInstance();
    String str1 = paramString.substring(0, paramInt1);
    if (paramString.length() < paramInt1 + paramInt2)
      throw NotFoundException.getNotFoundInstance();
    String str2 = paramString.substring(paramInt1, paramInt1 + paramInt2);
    paramString = paramString.substring(paramInt1 + paramInt2);
    return '(' + str1 + ')' + str2 + parseFieldsInGeneralPurpose(paramString);
  }

  private static String processVariableAI(int paramInt1, int paramInt2, String paramString)
    throws NotFoundException
  {
    String str1 = paramString.substring(0, paramInt1);
    if (paramString.length() < paramInt1 + paramInt2);
    for (paramInt2 = paramString.length(); ; paramInt2 = paramInt1 + paramInt2)
    {
      String str2 = paramString.substring(paramInt1, paramInt2);
      paramString = paramString.substring(paramInt2);
      return '(' + str1 + ')' + str2 + parseFieldsInGeneralPurpose(paramString);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.FieldParser
 * JD-Core Version:    0.6.2
 */