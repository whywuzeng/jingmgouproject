package com.yolanda.nohttp;

public abstract interface RequestMethod
{
  public static final int DELETE = 3;
  public static final int GET = 0;
  public static final int HEAD = 4;
  public static final String[] METHOD = { "GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "TRACE", "PATCH" };
  public static final int OPTIONS = 5;
  public static final int PATCH = 7;
  public static final int POST = 1;
  public static final int PUT = 2;
  public static final int TRACE = 6;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.RequestMethod
 * JD-Core Version:    0.6.2
 */