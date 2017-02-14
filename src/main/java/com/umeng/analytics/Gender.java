package com.umeng.analytics;

import java.util.Locale;
import u.aly.aw;

public enum Gender
{
  public int value;

  private Gender(int paramInt)
  {
    this.value = paramInt;
  }

  public static Gender getGender(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return Unknown;
    case 1:
      return Male;
    case 2:
    }
    return Female;
  }

  public static aw transGender(Gender paramGender)
  {
    switch (4.a[paramGender.ordinal()])
    {
    default:
      return aw.c;
    case 1:
      return aw.a;
    case 2:
    }
    return aw.b;
  }

  public int value()
  {
    return this.value;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.Gender
 * JD-Core Version:    0.6.2
 */