package okio;

final class SegmentPool
{
  static final long MAX_SIZE = 65536L;
  static long byteCount;
  static Segment next;

  static void recycle(Segment paramSegment)
  {
    if ((paramSegment.next != null) || (paramSegment.prev != null))
      throw new IllegalArgumentException();
    if (paramSegment.shared)
      return;
    try
    {
      if (byteCount + 2048L > 65536L)
        return;
    }
    finally
    {
    }
    byteCount += 2048L;
    paramSegment.next = next;
    paramSegment.limit = 0;
    paramSegment.pos = 0;
    next = paramSegment;
  }

  static Segment take()
  {
    try
    {
      if (next != null)
      {
        Segment localSegment = next;
        next = localSegment.next;
        localSegment.next = null;
        byteCount -= 2048L;
        return localSegment;
      }
      return new Segment();
    }
    finally
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     okio.SegmentPool
 * JD-Core Version:    0.6.2
 */