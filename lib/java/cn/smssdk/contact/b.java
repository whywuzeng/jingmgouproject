package cn.smssdk.contact;

import android.database.ContentObserver;
import android.os.Handler;

class b extends ContentObserver
{
  b(a parama, Handler paramHandler)
  {
    super(paramHandler);
  }

  public void onChange(boolean paramBoolean)
  {
    a.a(this.a).a();
    if (a.b(this.a) != null)
      a.b(this.a).onContactChange(paramBoolean);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.contact.b
 * JD-Core Version:    0.6.2
 */