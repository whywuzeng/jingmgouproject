package org.android.spdy;

public class SpdyErrorException extends RuntimeException
{
  private static final long serialVersionUID = 4422888579699220045L;
  private int error = 0;

  public SpdyErrorException(int paramInt)
  {
    this.error = paramInt;
  }

  public SpdyErrorException(String paramString, int paramInt)
  {
    super(paramString);
    this.error = paramInt;
  }

  public SpdyErrorException(String paramString, Throwable paramThrowable, int paramInt)
  {
    super(paramString, paramThrowable);
    this.error = paramInt;
  }

  public SpdyErrorException(Throwable paramThrowable, int paramInt)
  {
    super(paramThrowable);
    this.error = paramInt;
  }

  public int SpdyErrorGetCode()
  {
    return this.error;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.SpdyErrorException
 * JD-Core Version:    0.6.2
 */