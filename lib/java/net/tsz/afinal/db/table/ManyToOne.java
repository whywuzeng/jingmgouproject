package net.tsz.afinal.db.table;

public class ManyToOne extends Property
{
  private Class<?> manyClass;

  public Class<?> getManyClass()
  {
    return this.manyClass;
  }

  public void setManyClass(Class<?> paramClass)
  {
    this.manyClass = paramClass;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.table.ManyToOne
 * JD-Core Version:    0.6.2
 */