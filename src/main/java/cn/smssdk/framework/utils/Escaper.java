package cn.smssdk.framework.utils;

public abstract interface Escaper
{
  public abstract Appendable escape(Appendable paramAppendable);

  public abstract String escape(String paramString);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.utils.Escaper
 * JD-Core Version:    0.6.2
 */