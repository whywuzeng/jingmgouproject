package com.baidu.location.b;

public class a
{
  private String a;
  private String jdField_do;
  private boolean jdField_if;

  public a(String paramString1, boolean paramBoolean, String paramString2)
  {
    this.jdField_do = paramString1;
    this.jdField_if = paramBoolean;
    this.a = paramString2;
  }

  public void a(String paramString)
  {
    this.a = paramString;
  }

  public void a(boolean paramBoolean)
  {
    this.jdField_if = paramBoolean;
  }

  public boolean a()
  {
    return this.jdField_if;
  }

  public String jdField_do()
  {
    return this.jdField_do;
  }

  public String jdField_if()
  {
    return this.a;
  }

  public void jdField_if(String paramString)
  {
    this.jdField_do = paramString;
  }

  public String toString()
  {
    return "SDCardInfo [label=" + this.a + ", mountPoint=" + this.jdField_do + ", isRemoveable=" + this.jdField_if + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.a
 * JD-Core Version:    0.6.2
 */