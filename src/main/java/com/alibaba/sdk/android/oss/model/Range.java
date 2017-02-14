package com.alibaba.sdk.android.oss.model;

public class Range
{
  public static final int INFINITE = -1;
  private int begin;
  private int end;

  public Range(int paramInt1, int paramInt2)
  {
    this.begin = paramInt1;
    this.end = paramInt2;
  }

  public boolean checkIsValid()
  {
    if ((this.begin < -1) || (this.end < -1));
    while ((this.begin >= 0) && (this.end >= 0) && (this.begin > this.end))
      return false;
    return true;
  }

  public int getBegin()
  {
    return this.begin;
  }

  public int getEnd()
  {
    return this.end;
  }

  public void setBegin(int paramInt)
  {
    this.begin = paramInt;
  }

  public void setEnd(int paramInt)
  {
    this.end = paramInt;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("bytes=");
    if (this.begin == -1)
    {
      str = "";
      localStringBuilder = localStringBuilder.append(str).append("-");
      if (this.end != -1)
        break label66;
    }
    label66: for (String str = ""; ; str = String.valueOf(this.end))
    {
      return str;
      str = String.valueOf(this.begin);
      break;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.Range
 * JD-Core Version:    0.6.2
 */