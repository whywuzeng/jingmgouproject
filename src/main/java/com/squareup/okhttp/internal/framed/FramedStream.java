package com.squareup.okhttp.internal.framed;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class FramedStream
{
  long bytesLeftInWriteWindow;
  private final FramedConnection connection;
  private ErrorCode errorCode = null;
  private final int id;
  private final StreamTimeout readTimeout = new StreamTimeout();
  private final List<Header> requestHeaders;
  private List<Header> responseHeaders;
  final FramedDataSink sink;
  private final FramedDataSource source;
  long unacknowledgedBytesRead = 0L;
  private final StreamTimeout writeTimeout = new StreamTimeout();

  static
  {
    if (!FramedStream.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  FramedStream(int paramInt, FramedConnection paramFramedConnection, boolean paramBoolean1, boolean paramBoolean2, List<Header> paramList)
  {
    if (paramFramedConnection == null)
      throw new NullPointerException("connection == null");
    if (paramList == null)
      throw new NullPointerException("requestHeaders == null");
    this.id = paramInt;
    this.connection = paramFramedConnection;
    this.bytesLeftInWriteWindow = paramFramedConnection.peerSettings.getInitialWindowSize(65536);
    this.source = new FramedDataSource(paramFramedConnection.okHttpSettings.getInitialWindowSize(65536), null);
    this.sink = new FramedDataSink();
    FramedDataSource.access$102(this.source, paramBoolean2);
    FramedDataSink.access$202(this.sink, paramBoolean1);
    this.requestHeaders = paramList;
  }

  private void cancelStreamIfNecessary()
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    while (true)
    {
      boolean bool;
      int i;
      try
      {
        if ((!this.source.finished) && (this.source.closed))
        {
          if (this.sink.finished)
            break label112;
          if (this.sink.closed)
          {
            break label112;
            bool = isOpen();
            if (i == 0)
              break label95;
            close(ErrorCode.CANCEL);
            return;
          }
        }
        i = 0;
        continue;
      }
      finally
      {
      }
      label95: if (!bool)
      {
        this.connection.removeStream(this.id);
        return;
        label112: i = 1;
      }
    }
  }

  private void checkOutNotClosed()
    throws IOException
  {
    if (this.sink.closed)
      throw new IOException("stream closed");
    if (this.sink.finished)
      throw new IOException("stream finished");
    if (this.errorCode != null)
      throw new IOException("stream was reset: " + this.errorCode);
  }

  private boolean closeInternal(ErrorCode paramErrorCode)
  {
    assert (!Thread.holdsLock(this));
    try
    {
      if (this.errorCode != null)
        return false;
      if ((this.source.finished) && (this.sink.finished))
        return false;
    }
    finally
    {
    }
    this.errorCode = paramErrorCode;
    notifyAll();
    this.connection.removeStream(this.id);
    return true;
  }

  private void waitForIo()
    throws InterruptedIOException
  {
    try
    {
      wait();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    throw new InterruptedIOException();
  }

  void addBytesToWriteWindow(long paramLong)
  {
    this.bytesLeftInWriteWindow += paramLong;
    if (paramLong > 0L)
      notifyAll();
  }

  public void close(ErrorCode paramErrorCode)
    throws IOException
  {
    if (!closeInternal(paramErrorCode))
      return;
    this.connection.writeSynReset(this.id, paramErrorCode);
  }

  public void closeLater(ErrorCode paramErrorCode)
  {
    if (!closeInternal(paramErrorCode))
      return;
    this.connection.writeSynResetLater(this.id, paramErrorCode);
  }

  public FramedConnection getConnection()
  {
    return this.connection;
  }

  public ErrorCode getErrorCode()
  {
    try
    {
      ErrorCode localErrorCode = this.errorCode;
      return localErrorCode;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getId()
  {
    return this.id;
  }

  public List<Header> getRequestHeaders()
  {
    return this.requestHeaders;
  }

  public List<Header> getResponseHeaders()
    throws IOException
  {
    try
    {
      this.readTimeout.enter();
    }
    finally
    {
      try
      {
        if ((this.responseHeaders != null) || (this.errorCode != null))
          break label45;
        waitForIo();
      }
      finally
      {
        this.readTimeout.exitAndThrowIfTimedOut();
      }
    }
    label45: this.readTimeout.exitAndThrowIfTimedOut();
    if (this.responseHeaders != null)
    {
      List localList = this.responseHeaders;
      return localList;
    }
    throw new IOException("stream was reset: " + this.errorCode);
  }

  public Sink getSink()
  {
    try
    {
      if ((this.responseHeaders == null) && (!isLocallyInitiated()))
        throw new IllegalStateException("reply before requesting the sink");
    }
    finally
    {
    }
    return this.sink;
  }

  public Source getSource()
  {
    return this.source;
  }

  public boolean isLocallyInitiated()
  {
    if ((this.id & 0x1) == 1);
    for (int i = 1; this.connection.client == i; i = 0)
      return true;
    return false;
  }

  public boolean isOpen()
  {
    boolean bool = false;
    try
    {
      Object localObject1 = this.errorCode;
      if (localObject1 != null);
      while (true)
      {
        return bool;
        if (((this.source.finished) || (this.source.closed)) && ((this.sink.finished) || (this.sink.closed)))
        {
          localObject1 = this.responseHeaders;
          if (localObject1 != null);
        }
        else
        {
          bool = true;
        }
      }
    }
    finally
    {
    }
  }

  public Timeout readTimeout()
  {
    return this.readTimeout;
  }

  void receiveData(BufferedSource paramBufferedSource, int paramInt)
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    this.source.receive(paramBufferedSource, paramInt);
  }

  void receiveFin()
  {
    assert (!Thread.holdsLock(this));
    try
    {
      FramedDataSource.access$102(this.source, true);
      boolean bool = isOpen();
      notifyAll();
      if (!bool)
        this.connection.removeStream(this.id);
      return;
    }
    finally
    {
    }
  }

  void receiveHeaders(List<Header> paramList, HeadersMode paramHeadersMode)
  {
    assert (!Thread.holdsLock(this));
    Object localObject = null;
    boolean bool = true;
    do
      while (true)
      {
        try
        {
          if (this.responseHeaders == null)
          {
            if (paramHeadersMode.failIfHeadersAbsent())
            {
              paramList = ErrorCode.PROTOCOL_ERROR;
              if (paramList == null)
                break;
              closeLater(paramList);
              return;
            }
            this.responseHeaders = paramList;
            bool = isOpen();
            notifyAll();
            paramList = localObject;
            continue;
          }
        }
        finally
        {
        }
        if (paramHeadersMode.failIfHeadersPresent())
        {
          paramList = ErrorCode.STREAM_IN_USE;
        }
        else
        {
          paramHeadersMode = new ArrayList();
          paramHeadersMode.addAll(this.responseHeaders);
          paramHeadersMode.addAll(paramList);
          this.responseHeaders = paramHeadersMode;
          paramList = localObject;
        }
      }
    while (bool);
    this.connection.removeStream(this.id);
  }

  void receiveRstStream(ErrorCode paramErrorCode)
  {
    try
    {
      if (this.errorCode == null)
      {
        this.errorCode = paramErrorCode;
        notifyAll();
      }
      return;
    }
    finally
    {
      paramErrorCode = finally;
    }
    throw paramErrorCode;
  }

  public void reply(List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    boolean bool = false;
    if (paramList == null)
      try
      {
        throw new NullPointerException("responseHeaders == null");
      }
      finally
      {
      }
    if (this.responseHeaders != null)
      throw new IllegalStateException("reply already sent");
    this.responseHeaders = paramList;
    if (!paramBoolean)
    {
      FramedDataSink.access$202(this.sink, true);
      bool = true;
    }
    this.connection.writeSynReply(this.id, bool, paramList);
    if (bool)
      this.connection.flush();
  }

  public Timeout writeTimeout()
  {
    return this.writeTimeout;
  }

  final class FramedDataSink
    implements Sink
  {
    private static final long EMIT_BUFFER_SIZE = 16384L;
    private boolean closed;
    private boolean finished;
    private final Buffer sendBuffer = new Buffer();

    static
    {
      if (!FramedStream.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    FramedDataSink()
    {
    }

    private void emitDataFrame(boolean paramBoolean)
      throws IOException
    {
      synchronized (FramedStream.this)
      {
        FramedStream.this.writeTimeout.enter();
      }
      FramedStream.this.writeTimeout.exitAndThrowIfTimedOut();
      FramedStream.this.checkOutNotClosed();
      long l = Math.min(FramedStream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
      FramedStream localFramedStream = FramedStream.this;
      localFramedStream.bytesLeftInWriteWindow -= l;
      FramedStream.this.writeTimeout.enter();
      try
      {
        ??? = FramedStream.this.connection;
        int i = FramedStream.this.id;
        if ((paramBoolean) && (l == this.sendBuffer.size()));
        for (paramBoolean = true; ; paramBoolean = false)
        {
          ((FramedConnection)???).writeData(i, paramBoolean, this.sendBuffer, l);
          return;
        }
      }
      finally
      {
        FramedStream.this.writeTimeout.exitAndThrowIfTimedOut();
      }
    }

    public void close()
      throws IOException
    {
      assert (!Thread.holdsLock(FramedStream.this));
      synchronized (FramedStream.this)
      {
        if (this.closed)
          return;
        if (FramedStream.this.sink.finished)
          break label113;
        if (this.sendBuffer.size() > 0L)
        {
          if (this.sendBuffer.size() <= 0L)
            break label113;
          emitDataFrame(true);
        }
      }
      FramedStream.this.connection.writeData(FramedStream.this.id, true, null, 0L);
      label113: synchronized (FramedStream.this)
      {
        this.closed = true;
        FramedStream.this.connection.flush();
        FramedStream.this.cancelStreamIfNecessary();
        return;
      }
    }

    public void flush()
      throws IOException
    {
      assert (!Thread.holdsLock(FramedStream.this));
      synchronized (FramedStream.this)
      {
        FramedStream.this.checkOutNotClosed();
        if (this.sendBuffer.size() > 0L)
        {
          emitDataFrame(false);
          FramedStream.this.connection.flush();
        }
      }
    }

    public Timeout timeout()
    {
      return FramedStream.this.writeTimeout;
    }

    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      assert (!Thread.holdsLock(FramedStream.this));
      this.sendBuffer.write(paramBuffer, paramLong);
      while (this.sendBuffer.size() >= 16384L)
        emitDataFrame(false);
    }
  }

  private final class FramedDataSource
    implements Source
  {
    private boolean closed;
    private boolean finished;
    private final long maxByteCount;
    private final Buffer readBuffer = new Buffer();
    private final Buffer receiveBuffer = new Buffer();

    static
    {
      if (!FramedStream.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    private FramedDataSource(long arg2)
    {
      Object localObject;
      this.maxByteCount = localObject;
    }

    private void checkNotClosed()
      throws IOException
    {
      if (this.closed)
        throw new IOException("stream closed");
      if (FramedStream.this.errorCode != null)
        throw new IOException("stream was reset: " + FramedStream.this.errorCode);
    }

    private void waitUntilReadable()
      throws IOException
    {
      FramedStream.this.readTimeout.enter();
      try
      {
        if (this.readBuffer.size() == 0L)
          if ((!this.finished) && (!this.closed) && (FramedStream.this.errorCode == null))
            FramedStream.this.waitForIo();
      }
      finally
      {
        FramedStream.this.readTimeout.exitAndThrowIfTimedOut();
      }
    }

    public void close()
      throws IOException
    {
      synchronized (FramedStream.this)
      {
        this.closed = true;
        this.readBuffer.clear();
        FramedStream.this.notifyAll();
        FramedStream.this.cancelStreamIfNecessary();
        return;
      }
    }

    public long read(Buffer arg1, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      synchronized (FramedStream.this)
      {
        waitUntilReadable();
        checkNotClosed();
        if (this.readBuffer.size() == 0L)
          return -1L;
        paramLong = this.readBuffer.read(???, Math.min(paramLong, this.readBuffer.size()));
        ??? = FramedStream.this;
        ???.unacknowledgedBytesRead += paramLong;
        if (FramedStream.this.unacknowledgedBytesRead >= FramedStream.this.connection.okHttpSettings.getInitialWindowSize(65536) / 2)
        {
          FramedStream.this.connection.writeWindowUpdateLater(FramedStream.this.id, FramedStream.this.unacknowledgedBytesRead);
          FramedStream.this.unacknowledgedBytesRead = 0L;
        }
        synchronized (FramedStream.this.connection)
        {
          ??? = FramedStream.this.connection;
          ((FramedConnection)???).unacknowledgedBytesRead += paramLong;
          if (FramedStream.this.connection.unacknowledgedBytesRead >= FramedStream.this.connection.okHttpSettings.getInitialWindowSize(65536) / 2)
          {
            FramedStream.this.connection.writeWindowUpdateLater(0, FramedStream.this.connection.unacknowledgedBytesRead);
            FramedStream.this.connection.unacknowledgedBytesRead = 0L;
          }
          return paramLong;
        }
      }
    }

    void receive(BufferedSource paramBufferedSource, long paramLong)
      throws IOException
    {
      long l = paramLong;
      if (!$assertionsDisabled)
      {
        l = paramLong;
        if (Thread.holdsLock(FramedStream.this))
          throw new AssertionError();
      }
      while (true)
      {
        l -= paramLong;
        synchronized (FramedStream.this)
        {
          if (this.readBuffer.size() == 0L)
          {
            i = 1;
            this.readBuffer.writeAll(this.receiveBuffer);
            if (i != 0)
              FramedStream.this.notifyAll();
            if (l > 0L);
            boolean bool;
            synchronized (FramedStream.this)
            {
              bool = this.finished;
              if (this.readBuffer.size() + l > this.maxByteCount)
              {
                i = 1;
                if (i != 0)
                {
                  paramBufferedSource.skip(l);
                  FramedStream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                }
              }
              else
              {
                i = 0;
              }
            }
            if (bool)
            {
              paramBufferedSource.skip(l);
              return;
            }
            paramLong = paramBufferedSource.read(this.receiveBuffer, l);
            if (paramLong != -1L)
              continue;
            throw new EOFException();
          }
          int i = 0;
        }
      }
    }

    public Timeout timeout()
    {
      return FramedStream.this.readTimeout;
    }
  }

  class StreamTimeout extends AsyncTimeout
  {
    StreamTimeout()
    {
    }

    public void exitAndThrowIfTimedOut()
      throws IOException
    {
      if (exit())
        throw newTimeoutException(null);
    }

    protected IOException newTimeoutException(IOException paramIOException)
    {
      SocketTimeoutException localSocketTimeoutException = new SocketTimeoutException("timeout");
      if (paramIOException != null)
        localSocketTimeoutException.initCause(paramIOException);
      return localSocketTimeoutException;
    }

    protected void timedOut()
    {
      FramedStream.this.closeLater(ErrorCode.CANCEL);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.framed.FramedStream
 * JD-Core Version:    0.6.2
 */