package org.android.spdy;

public class NetSparseArray<E>
  implements Cloneable
{
  private static final Object DELETED = new Object();
  private boolean mGarbage = false;
  private int[] mKeys;
  private int mSize;
  private Object[] mValues;

  public NetSparseArray()
  {
    this(10);
  }

  public NetSparseArray(int paramInt)
  {
    this.mKeys = new int[paramInt];
    this.mValues = new Object[paramInt];
    this.mSize = 0;
  }

  private static int binarySearch(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
  {
    int j = paramInt1 - 1;
    int i = paramInt1 + paramInt2;
    while (i - j > 1)
    {
      int k = (i + j) / 2;
      if (paramArrayOfInt[k] < paramInt3)
        j = k;
      else
        i = k;
    }
    if (i == paramInt1 + paramInt2)
      paramInt1 = paramInt1 + paramInt2 ^ 0xFFFFFFFF;
    do
    {
      return paramInt1;
      paramInt1 = i;
    }
    while (paramArrayOfInt[i] == paramInt3);
    return i ^ 0xFFFFFFFF;
  }

  private void gc()
  {
    int m = this.mSize;
    int[] arrayOfInt = this.mKeys;
    Object[] arrayOfObject = this.mValues;
    int i = 0;
    int k;
    for (int j = 0; i < m; j = k)
    {
      Object localObject = arrayOfObject[i];
      k = j;
      if (localObject != DELETED)
      {
        if (i != j)
        {
          arrayOfInt[j] = arrayOfInt[i];
          arrayOfObject[j] = localObject;
          arrayOfObject[i] = null;
        }
        k = j + 1;
      }
      i += 1;
    }
    this.mGarbage = false;
    this.mSize = j;
  }

  public void append(int paramInt, E paramE)
  {
    if ((this.mSize != 0) && (paramInt <= this.mKeys[(this.mSize - 1)]))
    {
      put(paramInt, paramE);
      return;
    }
    if ((this.mGarbage) && (this.mSize >= this.mKeys.length))
      gc();
    int i = this.mSize;
    if (i >= this.mKeys.length)
    {
      int j = i + 1;
      int[] arrayOfInt = new int[j];
      Object[] arrayOfObject = new Object[j];
      System.arraycopy(this.mKeys, 0, arrayOfInt, 0, this.mKeys.length);
      System.arraycopy(this.mValues, 0, arrayOfObject, 0, this.mValues.length);
      this.mKeys = arrayOfInt;
      this.mValues = arrayOfObject;
    }
    this.mKeys[i] = paramInt;
    this.mValues[i] = paramE;
    this.mSize = (i + 1);
  }

  public void clear()
  {
    int j = this.mSize;
    Object[] arrayOfObject = this.mValues;
    int i = 0;
    while (i < j)
    {
      arrayOfObject[i] = null;
      i += 1;
    }
    this.mSize = 0;
    this.mGarbage = false;
  }

  public void delete(int paramInt)
  {
    paramInt = binarySearch(this.mKeys, 0, this.mSize, paramInt);
    if ((paramInt >= 0) && (this.mValues[paramInt] != DELETED))
    {
      this.mValues[paramInt] = DELETED;
      this.mGarbage = true;
    }
  }

  public E get(int paramInt)
  {
    return get(paramInt, null);
  }

  public E get(int paramInt, E paramE)
  {
    paramInt = binarySearch(this.mKeys, 0, this.mSize, paramInt);
    if ((paramInt < 0) || (this.mValues[paramInt] == DELETED))
      return paramE;
    return this.mValues[paramInt];
  }

  public int indexOfKey(int paramInt)
  {
    if (this.mGarbage)
      gc();
    return binarySearch(this.mKeys, 0, this.mSize, paramInt);
  }

  public int indexOfValue(E paramE)
  {
    if (this.mGarbage)
      gc();
    int i = 0;
    while (i < this.mSize)
    {
      if (this.mValues[i] == paramE)
        return i;
      i += 1;
    }
    return -1;
  }

  public int keyAt(int paramInt)
  {
    if (this.mGarbage)
      gc();
    return this.mKeys[paramInt];
  }

  public void put(int paramInt, E paramE)
  {
    int i = binarySearch(this.mKeys, 0, this.mSize, paramInt);
    if (i >= 0)
    {
      this.mValues[i] = paramE;
      return;
    }
    int j = i ^ 0xFFFFFFFF;
    if ((j < this.mSize) && (this.mValues[j] == DELETED))
    {
      this.mKeys[j] = paramInt;
      this.mValues[j] = paramE;
      return;
    }
    i = j;
    if (this.mGarbage)
    {
      i = j;
      if (this.mSize >= this.mKeys.length)
      {
        gc();
        i = binarySearch(this.mKeys, 0, this.mSize, paramInt) ^ 0xFFFFFFFF;
      }
    }
    if (this.mSize >= this.mKeys.length)
    {
      j = this.mSize + 20;
      int[] arrayOfInt = new int[j];
      Object[] arrayOfObject = new Object[j];
      System.arraycopy(this.mKeys, 0, arrayOfInt, 0, this.mKeys.length);
      System.arraycopy(this.mValues, 0, arrayOfObject, 0, this.mValues.length);
      this.mKeys = arrayOfInt;
      this.mValues = arrayOfObject;
    }
    if (this.mSize - i != 0)
    {
      System.arraycopy(this.mKeys, i, this.mKeys, i + 1, this.mSize - i);
      System.arraycopy(this.mValues, i, this.mValues, i + 1, this.mSize - i);
    }
    this.mKeys[i] = paramInt;
    this.mValues[i] = paramE;
    this.mSize += 1;
  }

  public void remove(int paramInt)
  {
    delete(paramInt);
  }

  public void removeAt(int paramInt)
  {
    if (this.mValues[paramInt] != DELETED)
    {
      this.mValues[paramInt] = DELETED;
      this.mGarbage = true;
    }
  }

  public void setValueAt(int paramInt, E paramE)
  {
    if (this.mGarbage)
      gc();
    this.mValues[paramInt] = paramE;
  }

  public int size()
  {
    if (this.mGarbage)
      gc();
    return this.mSize;
  }

  public void toArray(E[] paramArrayOfE)
  {
    int i = 0;
    while (i < paramArrayOfE.length)
    {
      paramArrayOfE[i] = this.mValues[i];
      i += 1;
    }
  }

  public E valueAt(int paramInt)
  {
    if (this.mGarbage)
      gc();
    return this.mValues[paramInt];
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.NetSparseArray
 * JD-Core Version:    0.6.2
 */