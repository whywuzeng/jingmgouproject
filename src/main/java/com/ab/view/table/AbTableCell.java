package com.ab.view.table;

public class AbTableCell
{
  public int type;
  public Object value;
  public int width;

  public AbTableCell(Object paramObject, int paramInt1, int paramInt2)
  {
    this.value = paramObject;
    this.width = paramInt1;
    this.type = paramInt2;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.table.AbTableCell
 * JD-Core Version:    0.6.2
 */