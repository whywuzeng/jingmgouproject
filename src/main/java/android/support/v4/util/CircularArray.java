package android.support.v4.util;

public class CircularArray<E>
{
  private int mCapacityBitmask;
  private E[] mElements;
  private int mHead;
  private int mTail;

  public CircularArray()
  {
    this(8);
  }

  public CircularArray(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("capacity must be positive");
    int i = paramInt;
    if (Integer.bitCount(paramInt) != 1)
      i = 1 << Integer.highestOneBit(paramInt) + 1;
    this.mCapacityBitmask = (i - 1);
    this.mElements = ((Object[])new Object[i]);
  }

  private void doubleCapacity()
  {
    int i = this.mElements.length;
    int j = i - this.mHead;
    int k = i << 1;
    if (k < 0)
      throw new RuntimeException("Too big");
    Object[] arrayOfObject = new Object[k];
    System.arraycopy(this.mElements, this.mHead, arrayOfObject, 0, j);
    System.arraycopy(this.mElements, 0, arrayOfObject, j, this.mHead);
    this.mElements = ((Object[])arrayOfObject);
    this.mHead = 0;
    this.mTail = i;
    this.mCapacityBitmask = (k - 1);
  }

  public final void addFirst(E paramE)
  {
    this.mHead = (this.mHead - 1 & this.mCapacityBitmask);
    this.mElements[this.mHead] = paramE;
    if (this.mHead == this.mTail)
      doubleCapacity();
  }

  public final void addLast(E paramE)
  {
    this.mElements[this.mTail] = paramE;
    this.mTail = (this.mTail + 1 & this.mCapacityBitmask);
    if (this.mTail == this.mHead)
      doubleCapacity();
  }

  public final E get(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= size()))
      throw new ArrayIndexOutOfBoundsException();
    int i = this.mHead;
    int j = this.mCapacityBitmask;
    return this.mElements[(i + paramInt & j)];
  }

  public final E getFirst()
  {
    if (this.mHead == this.mTail)
      throw new ArrayIndexOutOfBoundsException();
    return this.mElements[this.mHead];
  }

  public final E getLast()
  {
    if (this.mHead == this.mTail)
      throw new ArrayIndexOutOfBoundsException();
    return this.mElements[(this.mTail - 1 & this.mCapacityBitmask)];
  }

  public final boolean isEmpty()
  {
    return this.mHead == this.mTail;
  }

  public final E popFirst()
  {
    if (this.mHead == this.mTail)
      throw new ArrayIndexOutOfBoundsException();
    Object localObject = this.mElements[this.mHead];
    this.mElements[this.mHead] = null;
    this.mHead = (this.mHead + 1 & this.mCapacityBitmask);
    return localObject;
  }

  public final E popLast()
  {
    if (this.mHead == this.mTail)
      throw new ArrayIndexOutOfBoundsException();
    int i = this.mTail - 1 & this.mCapacityBitmask;
    Object localObject = this.mElements[i];
    this.mElements[i] = null;
    this.mTail = i;
    return localObject;
  }

  public final int size()
  {
    return this.mTail - this.mHead & this.mCapacityBitmask;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.util.CircularArray
 * JD-Core Version:    0.6.2
 */