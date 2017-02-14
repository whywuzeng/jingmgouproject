package com.nostra13.universalimageloader.cache.disc.impl.ext;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class DiskLruCache
  implements Closeable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  static final String JOURNAL_FILE_TEMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,64}");
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream()
  {
    public void write(int paramAnonymousInt)
      throws IOException
    {
    }
  };
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Callable<Void> cleanupCallable = new Callable()
  {
    public Void call()
      throws Exception
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.this.journalWriter == null)
          return null;
        DiskLruCache.this.trimToSize();
        DiskLruCache.this.trimToFileCount();
        if (DiskLruCache.this.journalRebuildRequired())
        {
          DiskLruCache.this.rebuildJournal();
          DiskLruCache.access$502(DiskLruCache.this, 0);
        }
        return null;
      }
    }
  };
  private final File directory;
  final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private int fileCount = 0;
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  private Writer journalWriter;
  private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private int maxFileCount;
  private long maxSize;
  private long nextSequenceNumber = 0L;
  private int redundantOpCount;
  private long size = 0L;
  private final int valueCount;

  private DiskLruCache(File paramFile, int paramInt1, int paramInt2, long paramLong, int paramInt3)
  {
    this.directory = paramFile;
    this.appVersion = paramInt1;
    this.journalFile = new File(paramFile, "journal");
    this.journalFileTmp = new File(paramFile, "journal.tmp");
    this.journalFileBackup = new File(paramFile, "journal.bkp");
    this.valueCount = paramInt2;
    this.maxSize = paramLong;
    this.maxFileCount = paramInt3;
  }

  private void checkNotClosed()
  {
    if (this.journalWriter == null)
      throw new IllegalStateException("cache is closed");
  }

  private void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    Entry localEntry;
    try
    {
      localEntry = paramEditor.entry;
      if (localEntry.currentEditor != paramEditor)
        throw new IllegalStateException();
    }
    finally
    {
    }
    if ((paramBoolean) && (!localEntry.readable))
    {
      i = 0;
      while (i < this.valueCount)
      {
        if (paramEditor.written[i] == 0)
        {
          paramEditor.abort();
          throw new IllegalStateException("Newly created entry didn't create value for index " + i);
        }
        if (!localEntry.getDirtyFile(i).exists())
        {
          paramEditor.abort();
          return;
        }
        i += 1;
      }
    }
    int i = 0;
    while (true)
    {
      long l1;
      if (i < this.valueCount)
      {
        paramEditor = localEntry.getDirtyFile(i);
        if (paramBoolean)
        {
          if (paramEditor.exists())
          {
            File localFile = localEntry.getCleanFile(i);
            paramEditor.renameTo(localFile);
            l1 = localEntry.lengths[i];
            long l2 = localFile.length();
            localEntry.lengths[i] = l2;
            this.size = (this.size - l1 + l2);
            this.fileCount += 1;
          }
        }
        else
          deleteIfExists(paramEditor);
      }
      else
      {
        this.redundantOpCount += 1;
        Entry.access$802(localEntry, null);
        if ((localEntry.readable | paramBoolean))
        {
          Entry.access$702(localEntry, true);
          this.journalWriter.write("CLEAN " + localEntry.key + localEntry.getLengths() + '\n');
          if (paramBoolean)
          {
            l1 = this.nextSequenceNumber;
            this.nextSequenceNumber = (1L + l1);
            Entry.access$1302(localEntry, l1);
          }
        }
        while (true)
        {
          this.journalWriter.flush();
          if ((this.size <= this.maxSize) && (this.fileCount <= this.maxFileCount) && (!journalRebuildRequired()))
            break;
          this.executorService.submit(this.cleanupCallable);
          break;
          this.lruEntries.remove(localEntry.key);
          this.journalWriter.write("REMOVE " + localEntry.key + '\n');
        }
      }
      i += 1;
    }
  }

  private static void deleteIfExists(File paramFile)
    throws IOException
  {
    if ((paramFile.exists()) && (!paramFile.delete()))
      throw new IOException();
  }

  private Editor edit(String paramString, long paramLong)
    throws IOException
  {
    Editor localEditor1 = null;
    while (true)
    {
      Entry localEntry;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry = (Entry)this.lruEntries.get(paramString);
        if (paramLong != -1L)
        {
          localObject = localEditor1;
          if (localEntry != null)
          {
            long l = localEntry.sequenceNumber;
            if (l != paramLong)
              localObject = localEditor1;
          }
          else
          {
            return localObject;
          }
        }
        if (localEntry == null)
        {
          localObject = new Entry(paramString, null);
          this.lruEntries.put(paramString, localObject);
          localEditor1 = new Editor((Entry)localObject, null);
          Entry.access$802((Entry)localObject, localEditor1);
          this.journalWriter.write("DIRTY " + paramString + '\n');
          this.journalWriter.flush();
          localObject = localEditor1;
          continue;
        }
      }
      finally
      {
      }
      Editor localEditor2 = localEntry.currentEditor;
      Object localObject = localEntry;
      if (localEditor2 != null)
        localObject = localEditor1;
    }
  }

  private static String inputStreamToString(InputStream paramInputStream)
    throws IOException
  {
    return Util.readFully(new InputStreamReader(paramInputStream, Util.UTF_8));
  }

  private boolean journalRebuildRequired()
  {
    return (this.redundantOpCount >= 2000) && (this.redundantOpCount >= this.lruEntries.size());
  }

  public static DiskLruCache open(File paramFile, int paramInt1, int paramInt2, long paramLong, int paramInt3)
    throws IOException
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("maxSize <= 0");
    if (paramInt3 <= 0)
      throw new IllegalArgumentException("maxFileCount <= 0");
    if (paramInt2 <= 0)
      throw new IllegalArgumentException("valueCount <= 0");
    Object localObject = new File(paramFile, "journal.bkp");
    File localFile;
    if (((File)localObject).exists())
    {
      localFile = new File(paramFile, "journal");
      if (!localFile.exists())
        break label168;
      ((File)localObject).delete();
    }
    while (true)
    {
      localObject = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong, paramInt3);
      if (((DiskLruCache)localObject).journalFile.exists())
        try
        {
          ((DiskLruCache)localObject).readJournal();
          ((DiskLruCache)localObject).processJournal();
          ((DiskLruCache)localObject).journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(((DiskLruCache)localObject).journalFile, true), Util.US_ASCII));
          return localObject;
          label168: renameTo((File)localObject, localFile, false);
        }
        catch (IOException localIOException)
        {
          System.out.println("DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
          ((DiskLruCache)localObject).delete();
        }
    }
    paramFile.mkdirs();
    paramFile = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong, paramInt3);
    paramFile.rebuildJournal();
    return paramFile;
  }

  private void processJournal()
    throws IOException
  {
    deleteIfExists(this.journalFileTmp);
    Iterator localIterator = this.lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      int i;
      if (localEntry.currentEditor == null)
      {
        i = 0;
        while (i < this.valueCount)
        {
          this.size += localEntry.lengths[i];
          this.fileCount += 1;
          i += 1;
        }
      }
      else
      {
        Entry.access$802(localEntry, null);
        i = 0;
        while (i < this.valueCount)
        {
          deleteIfExists(localEntry.getCleanFile(i));
          deleteIfExists(localEntry.getDirtyFile(i));
          i += 1;
        }
        localIterator.remove();
      }
    }
  }

  // ERROR //
  private void readJournal()
    throws IOException
  {
    // Byte code:
    //   0: new 459	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader
    //   3: dup
    //   4: new 461	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: getfield 146	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache:journalFile	Ljava/io/File;
    //   12: invokespecial 463	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   15: getstatic 397	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:US_ASCII	Ljava/nio/charset/Charset;
    //   18: invokespecial 464	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
    //   21: astore_2
    //   22: aload_2
    //   23: invokevirtual 467	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:readLine	()Ljava/lang/String;
    //   26: astore_3
    //   27: aload_2
    //   28: invokevirtual 467	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:readLine	()Ljava/lang/String;
    //   31: astore 4
    //   33: aload_2
    //   34: invokevirtual 467	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:readLine	()Ljava/lang/String;
    //   37: astore 5
    //   39: aload_2
    //   40: invokevirtual 467	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:readLine	()Ljava/lang/String;
    //   43: astore 6
    //   45: aload_2
    //   46: invokevirtual 467	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:readLine	()Ljava/lang/String;
    //   49: astore 7
    //   51: ldc 45
    //   53: aload_3
    //   54: invokevirtual 473	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   57: ifeq +54 -> 111
    //   60: ldc 54
    //   62: aload 4
    //   64: invokevirtual 473	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   67: ifeq +44 -> 111
    //   70: aload_0
    //   71: getfield 139	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache:appVersion	I
    //   74: invokestatic 478	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   77: aload 5
    //   79: invokevirtual 473	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   82: ifeq +29 -> 111
    //   85: aload_0
    //   86: getfield 152	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache:valueCount	I
    //   89: invokestatic 478	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   92: aload 6
    //   94: invokevirtual 473	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   97: ifeq +14 -> 111
    //   100: ldc_w 480
    //   103: aload 7
    //   105: invokevirtual 473	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   108: ifne +77 -> 185
    //   111: new 163	java/io/IOException
    //   114: dup
    //   115: new 238	java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial 239	java/lang/StringBuilder:<init>	()V
    //   122: ldc_w 482
    //   125: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: aload_3
    //   129: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: ldc_w 484
    //   135: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: aload 4
    //   140: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc_w 484
    //   146: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 6
    //   151: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: ldc_w 484
    //   157: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: aload 7
    //   162: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: ldc_w 486
    //   168: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: invokevirtual 252	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokespecial 487	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   177: athrow
    //   178: astore_3
    //   179: aload_2
    //   180: invokestatic 491	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   183: aload_3
    //   184: athrow
    //   185: iconst_0
    //   186: istore_1
    //   187: aload_0
    //   188: aload_2
    //   189: invokevirtual 467	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:readLine	()Ljava/lang/String;
    //   192: invokespecial 494	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache:readJournalLine	(Ljava/lang/String;)V
    //   195: iload_1
    //   196: iconst_1
    //   197: iadd
    //   198: istore_1
    //   199: goto -12 -> 187
    //   202: astore_3
    //   203: aload_0
    //   204: iload_1
    //   205: aload_0
    //   206: getfield 110	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   209: invokevirtual 366	java/util/LinkedHashMap:size	()I
    //   212: isub
    //   213: putfield 208	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache:redundantOpCount	I
    //   216: aload_2
    //   217: invokestatic 491	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   220: return
    //
    // Exception table:
    //   from	to	target	type
    //   22	111	178	finally
    //   111	178	178	finally
    //   187	195	178	finally
    //   203	216	178	finally
    //   187	195	202	java/io/EOFException
  }

  private void readJournalLine(String paramString)
    throws IOException
  {
    int i = paramString.indexOf(' ');
    if (i == -1)
      throw new IOException("unexpected journal line: " + paramString);
    int j = i + 1;
    int k = paramString.indexOf(' ', j);
    Object localObject2;
    Object localObject1;
    if (k == -1)
    {
      localObject2 = paramString.substring(j);
      localObject1 = localObject2;
      if (i != "REMOVE".length())
        break label112;
      localObject1 = localObject2;
      if (!paramString.startsWith("REMOVE"))
        break label112;
      this.lruEntries.remove(localObject2);
    }
    label112: 
    do
    {
      return;
      localObject1 = paramString.substring(j, k);
      Entry localEntry = (Entry)this.lruEntries.get(localObject1);
      localObject2 = localEntry;
      if (localEntry == null)
      {
        localObject2 = new Entry((String)localObject1, null);
        this.lruEntries.put(localObject1, localObject2);
      }
      if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
      {
        paramString = paramString.substring(k + 1).split(" ");
        Entry.access$702((Entry)localObject2, true);
        Entry.access$802((Entry)localObject2, null);
        ((Entry)localObject2).setLengths(paramString);
        return;
      }
      if ((k == -1) && (i == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
      {
        Entry.access$802((Entry)localObject2, new Editor((Entry)localObject2, null));
        return;
      }
    }
    while ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ")));
    throw new IOException("unexpected journal line: " + paramString);
  }

  private void rebuildJournal()
    throws IOException
  {
    while (true)
    {
      Entry localEntry;
      try
      {
        if (this.journalWriter != null)
          this.journalWriter.close();
        BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Util.US_ASCII));
        try
        {
          localBufferedWriter.write("libcore.io.DiskLruCache");
          localBufferedWriter.write("\n");
          localBufferedWriter.write("1");
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(this.appVersion));
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(this.valueCount));
          localBufferedWriter.write("\n");
          localBufferedWriter.write("\n");
          Iterator localIterator = this.lruEntries.values().iterator();
          if (!localIterator.hasNext())
            break;
          localEntry = (Entry)localIterator.next();
          if (localEntry.currentEditor != null)
          {
            localBufferedWriter.write("DIRTY " + localEntry.key + '\n');
            continue;
          }
        }
        finally
        {
          localBufferedWriter.close();
        }
      }
      finally
      {
      }
      localObject1.write("CLEAN " + localEntry.key + localEntry.getLengths() + '\n');
    }
    localObject1.close();
    if (this.journalFile.exists())
      renameTo(this.journalFile, this.journalFileBackup, true);
    renameTo(this.journalFileTmp, this.journalFile, false);
    this.journalFileBackup.delete();
    this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Util.US_ASCII));
  }

  private static void renameTo(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
      deleteIfExists(paramFile2);
    if (!paramFile1.renameTo(paramFile2))
      throw new IOException();
  }

  private void trimToFileCount()
    throws IOException
  {
    while (this.fileCount > this.maxFileCount)
      remove((String)((Map.Entry)this.lruEntries.entrySet().iterator().next()).getKey());
  }

  private void trimToSize()
    throws IOException
  {
    while (this.size > this.maxSize)
      remove((String)((Map.Entry)this.lruEntries.entrySet().iterator().next()).getKey());
  }

  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches())
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + paramString + "\"");
  }

  public void close()
    throws IOException
  {
    while (true)
    {
      try
      {
        Object localObject1 = this.journalWriter;
        if (localObject1 == null)
          return;
        localObject1 = new ArrayList(this.lruEntries.values()).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          Entry localEntry = (Entry)((Iterator)localObject1).next();
          if (localEntry.currentEditor == null)
            continue;
          localEntry.currentEditor.abort();
          continue;
        }
      }
      finally
      {
      }
      trimToSize();
      trimToFileCount();
      this.journalWriter.close();
      this.journalWriter = null;
    }
  }

  public void delete()
    throws IOException
  {
    close();
    Util.deleteContents(this.directory);
  }

  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }

  public long fileCount()
  {
    try
    {
      int i = this.fileCount;
      long l = i;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void flush()
    throws IOException
  {
    try
    {
      checkNotClosed();
      trimToSize();
      trimToFileCount();
      this.journalWriter.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Snapshot get(String paramString)
    throws IOException
  {
    Object localObject2 = null;
    try
    {
      checkNotClosed();
      validateKey(paramString);
      Entry localEntry = (Entry)this.lruEntries.get(paramString);
      Object localObject1;
      if (localEntry == null)
        localObject1 = localObject2;
      while (true)
      {
        return localObject1;
        localObject1 = localObject2;
        if (localEntry.readable)
        {
          localObject1 = new File[this.valueCount];
          InputStream[] arrayOfInputStream = new InputStream[this.valueCount];
          int i = 0;
          while (true)
          {
            try
            {
              if (i >= this.valueCount)
                break label148;
              File localFile = localEntry.getCleanFile(i);
              localObject1[i] = localFile;
              arrayOfInputStream[i] = new FileInputStream(localFile);
              i += 1;
              continue;
            }
            catch (FileNotFoundException paramString)
            {
              i = 0;
              localObject1 = localObject2;
            }
            if (i >= this.valueCount)
              break;
            localObject1 = localObject2;
            if (arrayOfInputStream[i] == null)
              break;
            Util.closeQuietly(arrayOfInputStream[i]);
            i += 1;
          }
          label148: this.redundantOpCount += 1;
          this.journalWriter.append("READ " + paramString + '\n');
          if (journalRebuildRequired())
            this.executorService.submit(this.cleanupCallable);
          localObject1 = new Snapshot(paramString, localEntry.sequenceNumber, (File[])localObject1, arrayOfInputStream, localEntry.lengths, null);
        }
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public File getDirectory()
  {
    return this.directory;
  }

  public int getMaxFileCount()
  {
    try
    {
      int i = this.maxFileCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long getMaxSize()
  {
    try
    {
      long l = this.maxSize;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isClosed()
  {
    try
    {
      Writer localWriter = this.journalWriter;
      if (localWriter == null)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public boolean remove(String paramString)
    throws IOException
  {
    while (true)
    {
      Entry localEntry;
      int i;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry = (Entry)this.lruEntries.get(paramString);
        if (localEntry != null)
        {
          localObject = localEntry.currentEditor;
          if (localObject == null);
        }
        else
        {
          bool = false;
          return bool;
        }
        i = 0;
        if (i >= this.valueCount)
          break label156;
        Object localObject = localEntry.getCleanFile(i);
        if ((((File)localObject).exists()) && (!((File)localObject).delete()))
          throw new IOException("failed to delete " + localObject);
      }
      finally
      {
      }
      this.size -= localEntry.lengths[i];
      this.fileCount -= 1;
      localEntry.lengths[i] = 0L;
      i += 1;
      continue;
      label156: this.redundantOpCount += 1;
      this.journalWriter.append("REMOVE " + paramString + '\n');
      this.lruEntries.remove(paramString);
      if (journalRebuildRequired())
        this.executorService.submit(this.cleanupCallable);
      boolean bool = true;
    }
  }

  public void setMaxSize(long paramLong)
  {
    try
    {
      this.maxSize = paramLong;
      this.executorService.submit(this.cleanupCallable);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long size()
  {
    try
    {
      long l = this.size;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final class Editor
  {
    private boolean committed;
    private final DiskLruCache.Entry entry;
    private boolean hasErrors;
    private final boolean[] written;

    private Editor(DiskLruCache.Entry arg2)
    {
      DiskLruCache.Entry localEntry;
      this.entry = localEntry;
      if (DiskLruCache.Entry.access$700(localEntry));
      for (this$1 = null; ; this$1 = new boolean[DiskLruCache.this.valueCount])
      {
        this.written = DiskLruCache.this;
        return;
      }
    }

    public void abort()
      throws IOException
    {
      DiskLruCache.this.completeEdit(this, false);
    }

    public void abortUnlessCommitted()
    {
      if (!this.committed);
      try
      {
        abort();
        return;
      }
      catch (IOException localIOException)
      {
      }
    }

    public void commit()
      throws IOException
    {
      if (this.hasErrors)
      {
        DiskLruCache.this.completeEdit(this, false);
        DiskLruCache.this.remove(DiskLruCache.Entry.access$1200(this.entry));
      }
      while (true)
      {
        this.committed = true;
        return;
        DiskLruCache.this.completeEdit(this, true);
      }
    }

    public String getString(int paramInt)
      throws IOException
    {
      InputStream localInputStream = newInputStream(paramInt);
      if (localInputStream != null)
        return DiskLruCache.inputStreamToString(localInputStream);
      return null;
    }

    public InputStream newInputStream(int paramInt)
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$800(this.entry) != this)
          throw new IllegalStateException();
      }
      if (!DiskLruCache.Entry.access$700(this.entry))
        return null;
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(this.entry.getCleanFile(paramInt));
        return localFileInputStream;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
      }
      return null;
    }

    public OutputStream newOutputStream(int paramInt)
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$800(this.entry) != this)
          throw new IllegalStateException();
      }
      if (!DiskLruCache.Entry.access$700(this.entry))
        this.written[paramInt] = true;
      File localFile = this.entry.getDirtyFile(paramInt);
      try
      {
        Object localObject2 = new FileOutputStream(localFile);
        localObject2 = new FaultHidingOutputStream((OutputStream)localObject2, null);
        return localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
        while (true)
        {
          DiskLruCache.this.directory.mkdirs();
          try
          {
            FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          }
          catch (FileNotFoundException localFileNotFoundException2)
          {
            OutputStream localOutputStream = DiskLruCache.NULL_OUTPUT_STREAM;
            return localOutputStream;
          }
        }
      }
    }

    // ERROR //
    public void set(int paramInt, String paramString)
      throws IOException
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: new 131	java/io/OutputStreamWriter
      //   6: dup
      //   7: aload_0
      //   8: iload_1
      //   9: invokevirtual 133	com/nostra13/universalimageloader/cache/disc/impl/ext/DiskLruCache$Editor:newOutputStream	(I)Ljava/io/OutputStream;
      //   12: getstatic 139	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:UTF_8	Ljava/nio/charset/Charset;
      //   15: invokespecial 142	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
      //   18: astore_3
      //   19: aload_3
      //   20: aload_2
      //   21: invokevirtual 148	java/io/Writer:write	(Ljava/lang/String;)V
      //   24: aload_3
      //   25: invokestatic 152	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   28: return
      //   29: astore_3
      //   30: aload 4
      //   32: astore_2
      //   33: aload_2
      //   34: invokestatic 152	com/nostra13/universalimageloader/cache/disc/impl/ext/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   37: aload_3
      //   38: athrow
      //   39: astore 4
      //   41: aload_3
      //   42: astore_2
      //   43: aload 4
      //   45: astore_3
      //   46: goto -13 -> 33
      //
      // Exception table:
      //   from	to	target	type
      //   3	19	29	finally
      //   19	24	39	finally
    }

    private class FaultHidingOutputStream extends FilterOutputStream
    {
      private FaultHidingOutputStream(OutputStream arg2)
      {
        super();
      }

      public void close()
      {
        try
        {
          this.out.close();
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2402(DiskLruCache.Editor.this, true);
        }
      }

      public void flush()
      {
        try
        {
          this.out.flush();
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2402(DiskLruCache.Editor.this, true);
        }
      }

      public void write(int paramInt)
      {
        try
        {
          this.out.write(paramInt);
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2402(DiskLruCache.Editor.this, true);
        }
      }

      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        try
        {
          this.out.write(paramArrayOfByte, paramInt1, paramInt2);
          return;
        }
        catch (IOException paramArrayOfByte)
        {
          DiskLruCache.Editor.access$2402(DiskLruCache.Editor.this, true);
        }
      }
    }
  }

  private final class Entry
  {
    private DiskLruCache.Editor currentEditor;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;

    private Entry(String arg2)
    {
      Object localObject;
      this.key = localObject;
      this.lengths = new long[DiskLruCache.this.valueCount];
    }

    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }

    private void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != DiskLruCache.this.valueCount)
        throw invalidLengths(paramArrayOfString);
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          this.lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i += 1;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw invalidLengths(paramArrayOfString);
      }
    }

    public File getCleanFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "." + paramInt);
    }

    public File getDirtyFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "." + paramInt + ".tmp");
    }

    public String getLengths()
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      long[] arrayOfLong = this.lengths;
      int j = arrayOfLong.length;
      int i = 0;
      while (i < j)
      {
        long l = arrayOfLong[i];
        localStringBuilder.append(' ').append(l);
        i += 1;
      }
      return localStringBuilder.toString();
    }
  }

  public final class Snapshot
    implements Closeable
  {
    private File[] files;
    private final InputStream[] ins;
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;

    private Snapshot(String paramLong, long arg3, File[] paramArrayOfInputStream, InputStream[] paramArrayOfLong, long[] arg7)
    {
      this.key = paramLong;
      this.sequenceNumber = ???;
      this.files = paramArrayOfInputStream;
      this.ins = paramArrayOfLong;
      Object localObject;
      this.lengths = localObject;
    }

    public void close()
    {
      InputStream[] arrayOfInputStream = this.ins;
      int j = arrayOfInputStream.length;
      int i = 0;
      while (i < j)
      {
        Util.closeQuietly(arrayOfInputStream[i]);
        i += 1;
      }
    }

    public DiskLruCache.Editor edit()
      throws IOException
    {
      return DiskLruCache.this.edit(this.key, this.sequenceNumber);
    }

    public File getFile(int paramInt)
    {
      return this.files[paramInt];
    }

    public InputStream getInputStream(int paramInt)
    {
      return this.ins[paramInt];
    }

    public long getLength(int paramInt)
    {
      return this.lengths[paramInt];
    }

    public String getString(int paramInt)
      throws IOException
    {
      return DiskLruCache.inputStreamToString(getInputStream(paramInt));
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache
 * JD-Core Version:    0.6.2
 */