package com.google.zxing.client.result;

public final class CalendarParsedResult extends ParsedResult
{
  private final String attendee;
  private final String description;
  private final String end;
  private final String location;
  private final String start;
  private final String summary;

  public CalendarParsedResult(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    super(ParsedResultType.CALENDAR);
    if (paramString2 == null)
      throw new IllegalArgumentException();
    validateDate(paramString2);
    if (paramString3 == null)
      paramString3 = paramString2;
    while (true)
    {
      this.summary = paramString1;
      this.start = paramString2;
      this.end = paramString3;
      this.location = paramString4;
      this.attendee = paramString5;
      this.description = paramString6;
      return;
      validateDate(paramString3);
    }
  }

  private static void validateDate(String paramString)
  {
    if (paramString != null)
    {
      int j = paramString.length();
      if ((j != 8) && (j != 15) && (j != 16))
        throw new IllegalArgumentException();
      int i = 0;
      while (i < 8)
      {
        if (!Character.isDigit(paramString.charAt(i)))
          throw new IllegalArgumentException();
        i += 1;
      }
      if (j > 8)
      {
        if (paramString.charAt(8) != 'T')
          throw new IllegalArgumentException();
        i = 9;
        while (i < 15)
        {
          if (!Character.isDigit(paramString.charAt(i)))
            throw new IllegalArgumentException();
          i += 1;
        }
        if ((j == 16) && (paramString.charAt(15) != 'Z'))
          throw new IllegalArgumentException();
      }
    }
  }

  public String getAttendee()
  {
    return this.attendee;
  }

  public String getDescription()
  {
    return this.description;
  }

  public String getDisplayResult()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    maybeAppend(this.summary, localStringBuffer);
    maybeAppend(this.start, localStringBuffer);
    maybeAppend(this.end, localStringBuffer);
    maybeAppend(this.location, localStringBuffer);
    maybeAppend(this.attendee, localStringBuffer);
    maybeAppend(this.description, localStringBuffer);
    return localStringBuffer.toString();
  }

  public String getEnd()
  {
    return this.end;
  }

  public String getLocation()
  {
    return this.location;
  }

  public String getStart()
  {
    return this.start;
  }

  public String getSummary()
  {
    return this.summary;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.CalendarParsedResult
 * JD-Core Version:    0.6.2
 */