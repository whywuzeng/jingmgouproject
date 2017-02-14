package net.tsz.afinal.exception;

import java.io.PrintStream;

public class ViewException extends AfinalException
{
  private static final long serialVersionUID = 1L;
  private String strMsg = null;

  public ViewException(String paramString)
  {
    this.strMsg = paramString;
  }

  public void printStackTrace()
  {
    if (this.strMsg != null)
      System.err.println(this.strMsg);
    super.printStackTrace();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.exception.ViewException
 * JD-Core Version:    0.6.2
 */