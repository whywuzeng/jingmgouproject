package cn.smssdk.framework.network;

import java.io.InputStream;
import org.apache.http.entity.InputStreamEntity;

public abstract class c
{
  private int a;

  protected abstract InputStream a();

  protected abstract long b();

  public InputStreamEntity c()
  {
    InputStream localInputStream = a();
    if (this.a > 0)
      localInputStream.skip(this.a);
    return new InputStreamEntity(localInputStream, b());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.network.c
 * JD-Core Version:    0.6.2
 */