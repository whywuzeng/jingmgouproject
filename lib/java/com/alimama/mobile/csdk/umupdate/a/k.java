package com.alimama.mobile.csdk.umupdate.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.widget.Toast;
import com.alimama.mobile.csdk.umupdate.b.c;
import com.alimama.mobile.csdk.umupdate.b.d.a;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.umeng.update.net.b;

public class k
{
  static final String a = "xp";
  private static final String b = k.class.getName();
  private Context c = com.alimama.mobile.a.a().c();
  private com.umeng.update.net.a d;
  private com.alimama.mobile.csdk.umupdate.b.d e;
  private Promoter f;

  public k(Promoter paramPromoter, d.a parama)
  {
    this.f = paramPromoter;
    this.e = parama.a();
    this.d = new com.umeng.update.net.a(this.c.getApplicationContext(), "xp", paramPromoter.title, paramPromoter.url, new a());
    paramPromoter = parama.a(1).a();
    if (paramPromoter != null)
    {
      paramPromoter = paramPromoter.b();
      this.d.a(new String[] { paramPromoter });
      if ((e.f) && (Build.VERSION.SDK_INT >= 16))
        this.d.a(true);
    }
    else
    {
      return;
    }
    this.d.a(false);
  }

  public void a()
  {
    g.b(b, new Object[] { "start Download." });
    this.d.a();
  }

  public class a
    implements com.umeng.update.net.d
  {
    private String b = k.a(k.this).url;
    private b c = b.a(k.b(k.this));

    public a()
    {
    }

    public void onEnd(int paramInt1, int paramInt2, String paramString)
    {
      g.b(k.b(), new Object[] { "XpDownloadListener.onEndresult = " + paramInt1 + " file = " + paramString });
      if (paramInt1 == 1)
        this.c.e("xp", this.b);
    }

    public void onProgressUpdate(int paramInt)
    {
      g.b(k.b(), new Object[] { "XpDownloadListener.onProgressUpdate" });
    }

    public void onStart()
    {
      g.b(k.b(), new Object[] { "XpDownloadListener.onStart" });
      if (e.g)
        Toast.makeText(k.b(k.this), com.alimama.mobile.a.a().d().c() + k.a(k.this).title, 0).show();
      if (k.c(k.this) != null)
        new c().sendAsync(k.c(k.this), null);
      this.c.a("xp", this.b);
    }

    public void onStatus(int paramInt)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.a.k
 * JD-Core Version:    0.6.2
 */