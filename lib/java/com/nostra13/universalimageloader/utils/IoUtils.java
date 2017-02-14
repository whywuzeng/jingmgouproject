package com.nostra13.universalimageloader.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtils
{
  public static final int CONTINUE_LOADING_PERCENTAGE = 75;
  public static final int DEFAULT_BUFFER_SIZE = 32768;
  public static final int DEFAULT_IMAGE_TOTAL_SIZE = 512000;

  public static void closeSilently(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Exception paramCloseable)
    {
    }
  }

  public static boolean copyStream(InputStream paramInputStream, OutputStream paramOutputStream, CopyListener paramCopyListener)
    throws IOException
  {
    return copyStream(paramInputStream, paramOutputStream, paramCopyListener, 32768);
  }

  public static boolean copyStream(InputStream paramInputStream, OutputStream paramOutputStream, CopyListener paramCopyListener, int paramInt)
    throws IOException
  {
    int j = 0;
    int k = paramInputStream.available();
    int i = k;
    if (k <= 0)
      i = 512000;
    byte[] arrayOfByte = new byte[paramInt];
    if (shouldStopLoading(paramCopyListener, 0, i))
      return false;
    do
    {
      k = paramInputStream.read(arrayOfByte, 0, paramInt);
      if (k == -1)
        break;
      paramOutputStream.write(arrayOfByte, 0, k);
      k = j + k;
      j = k;
    }
    while (!shouldStopLoading(paramCopyListener, k, i));
    return false;
    paramOutputStream.flush();
    return true;
  }

  // ERROR //
  public static void readAndCloseStream(InputStream paramInputStream)
  {
    // Byte code:
    //   0: ldc 12
    //   2: newarray byte
    //   4: astore_2
    //   5: aload_0
    //   6: aload_2
    //   7: iconst_0
    //   8: ldc 12
    //   10: invokevirtual 50	java/io/InputStream:read	([BII)I
    //   13: istore_1
    //   14: iload_1
    //   15: iconst_m1
    //   16: if_icmpne -11 -> 5
    //   19: aload_0
    //   20: invokestatic 63	com/nostra13/universalimageloader/utils/IoUtils:closeSilently	(Ljava/io/Closeable;)V
    //   23: return
    //   24: astore_2
    //   25: aload_0
    //   26: invokestatic 63	com/nostra13/universalimageloader/utils/IoUtils:closeSilently	(Ljava/io/Closeable;)V
    //   29: return
    //   30: astore_2
    //   31: aload_0
    //   32: invokestatic 63	com/nostra13/universalimageloader/utils/IoUtils:closeSilently	(Ljava/io/Closeable;)V
    //   35: aload_2
    //   36: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	14	24	java/io/IOException
    //   5	14	30	finally
  }

  private static boolean shouldStopLoading(CopyListener paramCopyListener, int paramInt1, int paramInt2)
  {
    return (paramCopyListener != null) && (!paramCopyListener.onBytesCopied(paramInt1, paramInt2)) && (paramInt1 * 100 / paramInt2 < 75);
  }

  public static abstract interface CopyListener
  {
    public abstract boolean onBytesCopied(int paramInt1, int paramInt2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.IoUtils
 * JD-Core Version:    0.6.2
 */