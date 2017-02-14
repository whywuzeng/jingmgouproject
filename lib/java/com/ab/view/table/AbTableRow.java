package com.ab.view.table;

public class AbTableRow
{
  public int backgroundResource;
  public AbTableCell[] cells;
  public int height;
  public int textColor;
  public int textSize;

  public AbTableRow(AbTableCell[] paramArrayOfAbTableCell, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.cells = paramArrayOfAbTableCell;
    this.height = paramInt1;
    this.textSize = paramInt2;
    this.textColor = paramInt3;
    this.backgroundResource = paramInt4;
  }

  public int getCellSize()
  {
    return this.cells.length;
  }

  public AbTableCell getCellValue(int paramInt)
  {
    if (paramInt >= this.cells.length)
      return null;
    return this.cells[paramInt];
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.table.AbTableRow
 * JD-Core Version:    0.6.2
 */