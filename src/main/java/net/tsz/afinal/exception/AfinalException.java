package net.tsz.afinal.exception;

public class AfinalException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  public AfinalException()
  {
  }

  public AfinalException(String paramString)
  {
    super(paramString);
  }

  public AfinalException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  public AfinalException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.exception.AfinalException
 * JD-Core Version:    0.6.2
 */