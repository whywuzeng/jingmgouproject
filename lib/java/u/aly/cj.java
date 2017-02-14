package u.aly;

import java.io.ByteArrayOutputStream;

public class cj extends ByteArrayOutputStream
{
  public cj()
  {
  }

  public cj(int paramInt)
  {
    super(paramInt);
  }

  public byte[] a()
  {
    return this.buf;
  }

  public int b()
  {
    return this.count;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.cj
 * JD-Core Version:    0.6.2
 */