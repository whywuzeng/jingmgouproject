package net.tsz.afinal.bitmap.core;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.zip.Adler32;

public class DiskCache
  implements Closeable
{
  private static final int BH_CHECKSUM = 8;
  private static final int BH_KEY = 0;
  private static final int BH_LENGTH = 16;
  private static final int BH_OFFSET = 12;
  private static final int BLOB_HEADER_SIZE = 20;
  private static final int DATA_HEADER_SIZE = 4;
  private static final int IH_ACTIVE_BYTES = 20;
  private static final int IH_ACTIVE_ENTRIES = 16;
  private static final int IH_ACTIVE_REGION = 12;
  private static final int IH_CHECKSUM = 28;
  private static final int IH_MAGIC = 0;
  private static final int IH_MAX_BYTES = 8;
  private static final int IH_MAX_ENTRIES = 4;
  private static final int IH_VERSION = 24;
  private static final int INDEX_HEADER_SIZE = 32;
  private static final int MAGIC_DATA_FILE = -1121680112;
  private static final int MAGIC_INDEX_FILE = -1289277392;
  private static final String TAG = DiskCache.class.getSimpleName();
  private int mActiveBytes;
  private RandomAccessFile mActiveDataFile;
  private int mActiveEntries;
  private int mActiveHashStart;
  private int mActiveRegion;
  private Adler32 mAdler32 = new Adler32();
  private byte[] mBlobHeader = new byte[20];
  private RandomAccessFile mDataFile0;
  private RandomAccessFile mDataFile1;
  private int mFileOffset;
  private RandomAccessFile mInactiveDataFile;
  private int mInactiveHashStart;
  private MappedByteBuffer mIndexBuffer;
  private FileChannel mIndexChannel;
  private RandomAccessFile mIndexFile;
  private byte[] mIndexHeader = new byte[32];
  private LookupRequest mLookupRequest = new LookupRequest();
  private int mMaxBytes;
  private int mMaxEntries;
  private String mPath;
  private int mSlotOffset;
  private int mVersion;

  public DiskCache(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
    throws IOException
  {
    this(paramString, paramInt1, paramInt2, paramBoolean, 0);
  }

  public DiskCache(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
    throws IOException
  {
    File localFile = new File(paramString);
    if ((!localFile.exists()) && (!localFile.mkdirs()))
      throw new IOException("unable to make dirs");
    this.mPath = paramString;
    this.mIndexFile = new RandomAccessFile(paramString + ".idx", "rw");
    this.mDataFile0 = new RandomAccessFile(paramString + ".0", "rw");
    this.mDataFile1 = new RandomAccessFile(paramString + ".1", "rw");
    this.mVersion = paramInt3;
    if ((!paramBoolean) && (loadIndex()));
    do
    {
      return;
      resetCache(paramInt1, paramInt2);
    }
    while (loadIndex());
    closeAll();
    throw new IOException("unable to load index");
  }

  private void clearHash(int paramInt)
  {
    byte[] arrayOfByte = new byte[1024];
    this.mIndexBuffer.position(paramInt);
    paramInt = this.mMaxEntries * 12;
    while (true)
    {
      if (paramInt <= 0)
        return;
      int i = Math.min(paramInt, 1024);
      this.mIndexBuffer.put(arrayOfByte, 0, i);
      paramInt -= i;
    }
  }

  private void closeAll()
  {
    closeSilently(this.mIndexChannel);
    closeSilently(this.mIndexFile);
    closeSilently(this.mDataFile0);
    closeSilently(this.mDataFile1);
  }

  static void closeSilently(Closeable paramCloseable)
  {
    if (paramCloseable == null)
      return;
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable paramCloseable)
    {
    }
  }

  private static void deleteFileSilently(String paramString)
  {
    try
    {
      new File(paramString).delete();
      return;
    }
    catch (Throwable paramString)
    {
    }
  }

  private void flipRegion()
    throws IOException
  {
    this.mActiveRegion = (1 - this.mActiveRegion);
    this.mActiveEntries = 0;
    this.mActiveBytes = 4;
    writeInt(this.mIndexHeader, 12, this.mActiveRegion);
    writeInt(this.mIndexHeader, 16, this.mActiveEntries);
    writeInt(this.mIndexHeader, 20, this.mActiveBytes);
    updateIndexHeader();
    setActiveVariables();
    clearHash(this.mActiveHashStart);
    syncIndex();
  }

  private boolean getBlob(RandomAccessFile paramRandomAccessFile, int paramInt, LookupRequest paramLookupRequest)
    throws IOException
  {
    byte[] arrayOfByte = this.mBlobHeader;
    long l1 = paramRandomAccessFile.getFilePointer();
    long l2 = paramInt;
    try
    {
      paramRandomAccessFile.seek(l2);
      if (paramRandomAccessFile.read(arrayOfByte) != 20)
      {
        Log.w(TAG, "cannot read blob header");
        return false;
      }
      l2 = readLong(arrayOfByte, 0);
      if (l2 != paramLookupRequest.key)
      {
        Log.w(TAG, "blob key does not match: " + l2);
        return false;
      }
      int i = readInt(arrayOfByte, 8);
      int j = readInt(arrayOfByte, 12);
      if (j != paramInt)
      {
        Log.w(TAG, "blob offset does not match: " + j);
        return false;
      }
      j = readInt(arrayOfByte, 16);
      if ((j < 0) || (j > this.mMaxBytes - paramInt - 20))
      {
        Log.w(TAG, "invalid blob length: " + j);
        return false;
      }
      if ((paramLookupRequest.buffer == null) || (paramLookupRequest.buffer.length < j))
        paramLookupRequest.buffer = new byte[j];
      arrayOfByte = paramLookupRequest.buffer;
      paramLookupRequest.length = j;
      if (paramRandomAccessFile.read(arrayOfByte, 0, j) != j)
      {
        Log.w(TAG, "cannot read blob data");
        return false;
      }
      if (checkSum(arrayOfByte, 0, j) != i)
      {
        Log.w(TAG, "blob checksum does not match: " + i);
        return false;
      }
      return true;
    }
    catch (Throwable paramLookupRequest)
    {
      Log.e(TAG, "getBlob failed.", paramLookupRequest);
      return false;
    }
    finally
    {
      paramRandomAccessFile.seek(l1);
    }
    throw paramLookupRequest;
  }

  private void insertInternal(long paramLong, byte[] paramArrayOfByte, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = this.mBlobHeader;
    int i = checkSum(paramArrayOfByte);
    writeLong(arrayOfByte, 0, paramLong);
    writeInt(arrayOfByte, 8, i);
    writeInt(arrayOfByte, 12, this.mActiveBytes);
    writeInt(arrayOfByte, 16, paramInt);
    this.mActiveDataFile.write(arrayOfByte);
    this.mActiveDataFile.write(paramArrayOfByte, 0, paramInt);
    this.mIndexBuffer.putLong(this.mSlotOffset, paramLong);
    this.mIndexBuffer.putInt(this.mSlotOffset + 8, this.mActiveBytes);
    this.mActiveBytes += paramInt + 20;
    writeInt(this.mIndexHeader, 20, this.mActiveBytes);
  }

  private boolean loadIndex()
  {
    try
    {
      this.mIndexFile.seek(0L);
      this.mDataFile0.seek(0L);
      this.mDataFile1.seek(0L);
      byte[] arrayOfByte = this.mIndexHeader;
      if (this.mIndexFile.read(arrayOfByte) != 32)
      {
        Log.w(TAG, "cannot read header");
        return false;
      }
      if (readInt(arrayOfByte, 0) != -1289277392)
      {
        Log.w(TAG, "cannot read header magic");
        return false;
      }
      if (readInt(arrayOfByte, 24) != this.mVersion)
      {
        Log.w(TAG, "version mismatch");
        return false;
      }
      this.mMaxEntries = readInt(arrayOfByte, 4);
      this.mMaxBytes = readInt(arrayOfByte, 8);
      this.mActiveRegion = readInt(arrayOfByte, 12);
      this.mActiveEntries = readInt(arrayOfByte, 16);
      this.mActiveBytes = readInt(arrayOfByte, 20);
      int i = readInt(arrayOfByte, 28);
      if (checkSum(arrayOfByte, 0, 28) != i)
      {
        Log.w(TAG, "header checksum does not match");
        return false;
      }
      if (this.mMaxEntries <= 0)
      {
        Log.w(TAG, "invalid max entries");
        return false;
      }
      if (this.mMaxBytes <= 0)
      {
        Log.w(TAG, "invalid max bytes");
        return false;
      }
      if ((this.mActiveRegion != 0) && (this.mActiveRegion != 1))
      {
        Log.w(TAG, "invalid active region");
        return false;
      }
      if ((this.mActiveEntries < 0) || (this.mActiveEntries > this.mMaxEntries))
      {
        Log.w(TAG, "invalid active entries");
        return false;
      }
      if ((this.mActiveBytes < 4) || (this.mActiveBytes > this.mMaxBytes))
      {
        Log.w(TAG, "invalid active bytes");
        return false;
      }
      if (this.mIndexFile.length() != this.mMaxEntries * 12 * 2 + 32)
      {
        Log.w(TAG, "invalid index file length");
        return false;
      }
      arrayOfByte = new byte[4];
      if (this.mDataFile0.read(arrayOfByte) != 4)
      {
        Log.w(TAG, "cannot read data file magic");
        return false;
      }
      if (readInt(arrayOfByte, 0) != -1121680112)
      {
        Log.w(TAG, "invalid data file magic");
        return false;
      }
      if (this.mDataFile1.read(arrayOfByte) != 4)
      {
        Log.w(TAG, "cannot read data file magic");
        return false;
      }
      if (readInt(arrayOfByte, 0) != -1121680112)
      {
        Log.w(TAG, "invalid data file magic");
        return false;
      }
      this.mIndexChannel = this.mIndexFile.getChannel();
      this.mIndexBuffer = this.mIndexChannel.map(FileChannel.MapMode.READ_WRITE, 0L, this.mIndexFile.length());
      this.mIndexBuffer.order(ByteOrder.LITTLE_ENDIAN);
      setActiveVariables();
      return true;
    }
    catch (IOException localIOException)
    {
      Log.e(TAG, "loadIndex failed.", localIOException);
    }
    return false;
  }

  private boolean lookupInternal(long paramLong, int paramInt)
  {
    int j = (int)(paramLong % this.mMaxEntries);
    int i = j;
    if (j < 0)
      i = j + this.mMaxEntries;
    int k = i;
    while (true)
    {
      j = paramInt + k * 12;
      long l = this.mIndexBuffer.getLong(j);
      int m = this.mIndexBuffer.getInt(j + 8);
      if (m == 0)
      {
        this.mSlotOffset = j;
        return false;
      }
      if (l == paramLong)
      {
        this.mSlotOffset = j;
        this.mFileOffset = m;
        return true;
      }
      k += 1;
      j = k;
      if (k >= this.mMaxEntries)
        j = 0;
      k = j;
      if (j == i)
      {
        Log.w(TAG, "corrupted index: clear the slot.");
        this.mIndexBuffer.putInt(j * 12 + paramInt + 8, 0);
        k = j;
      }
    }
  }

  static int readInt(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24;
  }

  static long readLong(byte[] paramArrayOfByte, int paramInt)
  {
    long l = paramArrayOfByte[(paramInt + 7)] & 0xFF;
    int i = 6;
    while (true)
    {
      if (i < 0)
        return l;
      l = l << 8 | paramArrayOfByte[(paramInt + i)] & 0xFF;
      i -= 1;
    }
  }

  private void resetCache(int paramInt1, int paramInt2)
    throws IOException
  {
    this.mIndexFile.setLength(0L);
    this.mIndexFile.setLength(paramInt1 * 12 * 2 + 32);
    this.mIndexFile.seek(0L);
    byte[] arrayOfByte = this.mIndexHeader;
    writeInt(arrayOfByte, 0, -1289277392);
    writeInt(arrayOfByte, 4, paramInt1);
    writeInt(arrayOfByte, 8, paramInt2);
    writeInt(arrayOfByte, 12, 0);
    writeInt(arrayOfByte, 16, 0);
    writeInt(arrayOfByte, 20, 4);
    writeInt(arrayOfByte, 24, this.mVersion);
    writeInt(arrayOfByte, 28, checkSum(arrayOfByte, 0, 28));
    this.mIndexFile.write(arrayOfByte);
    this.mDataFile0.setLength(0L);
    this.mDataFile1.setLength(0L);
    this.mDataFile0.seek(0L);
    this.mDataFile1.seek(0L);
    writeInt(arrayOfByte, 0, -1121680112);
    this.mDataFile0.write(arrayOfByte, 0, 4);
    this.mDataFile1.write(arrayOfByte, 0, 4);
  }

  private void setActiveVariables()
    throws IOException
  {
    if (this.mActiveRegion == 0)
    {
      localRandomAccessFile = this.mDataFile0;
      this.mActiveDataFile = localRandomAccessFile;
      if (this.mActiveRegion != 1)
        break label103;
    }
    label103: for (RandomAccessFile localRandomAccessFile = this.mDataFile0; ; localRandomAccessFile = this.mDataFile1)
    {
      this.mInactiveDataFile = localRandomAccessFile;
      this.mActiveDataFile.setLength(this.mActiveBytes);
      this.mActiveDataFile.seek(this.mActiveBytes);
      this.mActiveHashStart = 32;
      this.mInactiveHashStart = 32;
      if (this.mActiveRegion != 0)
        break label111;
      this.mInactiveHashStart += this.mMaxEntries * 12;
      return;
      localRandomAccessFile = this.mDataFile1;
      break;
    }
    label111: this.mActiveHashStart += this.mMaxEntries * 12;
  }

  private void updateIndexHeader()
  {
    writeInt(this.mIndexHeader, 28, checkSum(this.mIndexHeader, 0, 28));
    this.mIndexBuffer.position(0);
    this.mIndexBuffer.put(this.mIndexHeader);
  }

  static void writeInt(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = paramInt2;
    paramInt2 = j;
    while (true)
    {
      if (paramInt2 >= 4)
        return;
      paramArrayOfByte[(paramInt1 + paramInt2)] = ((byte)(i & 0xFF));
      i >>= 8;
      paramInt2 += 1;
    }
  }

  static void writeLong(byte[] paramArrayOfByte, int paramInt, long paramLong)
  {
    int i = 0;
    while (true)
    {
      if (i >= 8)
        return;
      paramArrayOfByte[(paramInt + i)] = ((byte)(int)(0xFF & paramLong));
      paramLong >>= 8;
      i += 1;
    }
  }

  int checkSum(byte[] paramArrayOfByte)
  {
    this.mAdler32.reset();
    this.mAdler32.update(paramArrayOfByte);
    return (int)this.mAdler32.getValue();
  }

  int checkSum(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.mAdler32.reset();
    this.mAdler32.update(paramArrayOfByte, paramInt1, paramInt2);
    return (int)this.mAdler32.getValue();
  }

  public void close()
  {
    syncAll();
    closeAll();
  }

  public void delete()
  {
    deleteFileSilently(this.mPath + ".idx");
    deleteFileSilently(this.mPath + ".0");
    deleteFileSilently(this.mPath + ".1");
  }

  int getActiveCount()
  {
    int j = 0;
    int i = 0;
    while (true)
    {
      if (i >= this.mMaxEntries)
      {
        if (j != this.mActiveEntries)
          break;
        return j;
      }
      int m = this.mActiveHashStart;
      int k = j;
      if (this.mIndexBuffer.getInt(m + i * 12 + 8) != 0)
        k = j + 1;
      i += 1;
      j = k;
    }
    Log.e(TAG, "wrong active count: " + this.mActiveEntries + " vs " + j);
    return -1;
  }

  public void insert(long paramLong, byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte.length + 24 > this.mMaxBytes)
      throw new RuntimeException("blob is too large!");
    if ((this.mActiveBytes + 20 + paramArrayOfByte.length > this.mMaxBytes) || (this.mActiveEntries * 2 >= this.mMaxEntries))
      flipRegion();
    if (!lookupInternal(paramLong, this.mActiveHashStart))
    {
      this.mActiveEntries += 1;
      writeInt(this.mIndexHeader, 16, this.mActiveEntries);
    }
    insertInternal(paramLong, paramArrayOfByte, paramArrayOfByte.length);
    updateIndexHeader();
  }

  public boolean lookup(LookupRequest paramLookupRequest)
    throws IOException
  {
    if ((lookupInternal(paramLookupRequest.key, this.mActiveHashStart)) && (getBlob(this.mActiveDataFile, this.mFileOffset, paramLookupRequest)));
    int i;
    do
    {
      return true;
      i = this.mSlotOffset;
      if ((!lookupInternal(paramLookupRequest.key, this.mInactiveHashStart)) || (!getBlob(this.mInactiveDataFile, this.mFileOffset, paramLookupRequest)))
        break;
    }
    while ((this.mActiveBytes + 20 + paramLookupRequest.length > this.mMaxBytes) || (this.mActiveEntries * 2 >= this.mMaxEntries));
    this.mSlotOffset = i;
    try
    {
      insertInternal(paramLookupRequest.key, paramLookupRequest.buffer, paramLookupRequest.length);
      this.mActiveEntries += 1;
      writeInt(this.mIndexHeader, 16, this.mActiveEntries);
      updateIndexHeader();
      return true;
    }
    catch (Throwable paramLookupRequest)
    {
      Log.e(TAG, "cannot copy over");
      return true;
    }
    return false;
  }

  public byte[] lookup(long paramLong)
    throws IOException
  {
    byte[] arrayOfByte = null;
    this.mLookupRequest.key = paramLong;
    this.mLookupRequest.buffer = null;
    if (lookup(this.mLookupRequest))
      arrayOfByte = this.mLookupRequest.buffer;
    return arrayOfByte;
  }

  public void syncAll()
  {
    syncIndex();
    try
    {
      this.mDataFile0.getFD().sync();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        while (true)
        {
          this.mDataFile1.getFD().sync();
          return;
          localThrowable1 = localThrowable1;
          Log.w(TAG, "sync data file 0 failed", localThrowable1);
        }
      }
      catch (Throwable localThrowable2)
      {
        Log.w(TAG, "sync data file 1 failed", localThrowable2);
      }
    }
  }

  public void syncIndex()
  {
    try
    {
      this.mIndexBuffer.force();
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w(TAG, "sync index failed", localThrowable);
    }
  }

  public static class LookupRequest
  {
    public byte[] buffer;
    public long key;
    public int length;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.DiskCache
 * JD-Core Version:    0.6.2
 */