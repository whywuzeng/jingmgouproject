package cn.jpush.android.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

final class f extends Handler
{
  private static final String z;
  private Context b;

  static
  {
    Object localObject1 = "\003CK\032^4'k\032Bpig\017\f'hz\020".toCharArray();
    int j = localObject1.length;
    int m = 0;
    int i = 0;
    Object localObject2 = localObject1;
    int k = j;
    label30: int n;
    if (j <= 1)
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 44;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[k] = ((char)(i ^ n));
      m += 1;
      if (j == 0)
      {
        k = j;
        break label30;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break;
      z = new String(localObject2).intern();
      return;
      i = 80;
      continue;
      i = 7;
      continue;
      i = 8;
      continue;
      i = 123;
    }
  }

  public f(DownloadService paramDownloadService, Context paramContext)
  {
    super(paramContext.getMainLooper());
    this.b = paramContext;
  }

  public final void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    Toast.makeText(this.b, z, 1).show();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.f
 * JD-Core Version:    0.6.2
 */