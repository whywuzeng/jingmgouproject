package net.tsz.afinal.db.table;

public class OneToMany extends Property
{
  private Class<?> oneClass;

  public Class<?> getOneClass()
  {
    return this.oneClass;
  }

  public void setOneClass(Class<?> paramClass)
  {
    this.oneClass = paramClass;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.table.OneToMany
 * JD-Core Version:    0.6.2
 */