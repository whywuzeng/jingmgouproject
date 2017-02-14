package net.tsz.afinal.bitmap.core;

import java.util.ArrayList;

public class BytesBufferPool
{
  private final int mBufferSize;
  private final ArrayList<BytesBuffer> mList;
  private final int mPoolSize;

  public BytesBufferPool(int paramInt1, int paramInt2)
  {
    this.mList = new ArrayList(paramInt1);
    this.mPoolSize = paramInt1;
    this.mBufferSize = paramInt2;
  }

  public void clear()
  {
    try
    {
      this.mList.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public BytesBuffer get()
  {
    try
    {
      int i = this.mList.size();
      if (i > 0);
      for (BytesBuffer localBytesBuffer = (BytesBuffer)this.mList.remove(i - 1); ; localBytesBuffer = new BytesBuffer(this.mBufferSize, null))
        return localBytesBuffer;
    }
    finally
    {
    }
  }

  public void recycle(BytesBuffer paramBytesBuffer)
  {
    try
    {
      int i = paramBytesBuffer.data.length;
      int j = this.mBufferSize;
      if (i != j);
      while (true)
      {
        return;
        if (this.mList.size() < this.mPoolSize)
        {
          paramBytesBuffer.offset = 0;
          paramBytesBuffer.length = 0;
          this.mList.add(paramBytesBuffer);
        }
      }
    }
    finally
    {
    }
    throw paramBytesBuffer;
  }

  public static class BytesBuffer
  {
    public byte[] data;
    public int length;
    public int offset;

    private BytesBuffer(int paramInt)
    {
      this.data = new byte[paramInt];
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.BytesBufferPool
 * JD-Core Version:    0.6.2
 */