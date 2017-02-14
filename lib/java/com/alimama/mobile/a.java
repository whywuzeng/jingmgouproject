package com.alimama.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.c.a;
import com.alimama.mobile.csdk.umupdate.a.g;
import com.alimama.mobile.csdk.umupdate.a.h;
import com.alimama.mobile.csdk.umupdate.a.i;
import com.alimama.mobile.csdk.umupdate.b.d.a;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.alimama.mobile.csdk.umupdate.models.b;
import java.util.List;

public class a
{
  private static a a;
  private com.alimama.mobile.csdk.umupdate.a.a b;
  private Context c;
  private h d;
  private boolean e = false;

  public static a a()
  {
    if (a == null)
      a = new a();
    return a;
  }

  private void a(final b paramb, final a parama)
  {
    if (TextUtils.isEmpty(paramb.b().sid));
    for (final boolean bool = true; ; bool = false)
    {
      new i(paramb, new a()
      {
        private void a(boolean paramAnonymousBoolean)
        {
          try
          {
            ??? = (MMEntity)paramb.b().clone();
            ((MMEntity)???).clear();
            b localb = new b((MMEntity)???);
            com.alimama.mobile.csdk.umupdate.models.c localc;
            if (paramAnonymousBoolean)
              localc = paramb.a();
            synchronized (a.a(a.this).getSharedPreferences(localc.c(), 0))
            {
              SharedPreferences.Editor localEditor = ((SharedPreferences)???).edit();
              localEditor.remove(localc.e());
              localEditor.commit();
              new i(localb, null, 0, true).a(i.c, new Void[0]);
              return;
            }
          }
          catch (CloneNotSupportedException localCloneNotSupportedException)
          {
            localCloneNotSupportedException.printStackTrace();
          }
        }

        public void dataReceived(int paramAnonymousInt, List<Promoter> paramAnonymousList)
        {
          if (paramAnonymousInt == 1)
          {
            parama.dataReceived(paramAnonymousInt, paramAnonymousList);
            a(bool);
            return;
          }
          paramAnonymousList = new a.a()
          {
            public void dataReceived(int paramAnonymous2Int, List<Promoter> paramAnonymous2List)
            {
              a.1.this.a.dataReceived(paramAnonymous2Int, paramAnonymous2List);
              if (paramAnonymous2Int == 1)
                a.1.a(a.1.this, a.1.this.b);
            }
          };
          new i(paramb, paramAnonymousList, 0, false).a(i.c, new Void[0]);
        }
      }
      , 1, false).a(i.c, new Void[0]);
      return;
    }
  }

  private void a(b paramb, a parama, int paramInt)
  {
    if (paramInt == 1)
    {
      a(paramb, parama);
      return;
    }
    new i(paramb, parama, 0, false).a(i.c, new Void[0]);
  }

  private void b(MMEntity paramMMEntity, a parama)
  {
    paramMMEntity = new b(paramMMEntity);
    com.alimama.mobile.csdk.umupdate.models.c localc = paramMMEntity.a();
    a(paramMMEntity, parama, this.c.getSharedPreferences(localc.c(), 0).getInt(localc.d(), 0));
  }

  public void a(Context paramContext)
  {
    if (!this.e)
    {
      this.c = paramContext.getApplicationContext();
      paramContext = new com.alimama.mobile.csdk.umupdate.a.c();
      c.a locala = new c.a();
      locala.a = "*";
      locala.c = "2G/3G";
      locala.d = "Wi-Fi";
      locala.b = "Unknown";
      paramContext.a(this.c, locala);
      this.b = paramContext;
      this.d = new h(this.c);
      this.e = true;
    }
  }

  public void a(MMEntity paramMMEntity, a parama)
  {
    paramMMEntity.clear();
    b(paramMMEntity, parama);
  }

  public void a(MMEntity paramMMEntity, Promoter paramPromoter)
  {
    com.alimama.mobile.csdk.umupdate.a.d.a(paramPromoter, paramMMEntity, false, 0);
  }

  public void a(MMEntity paramMMEntity, Promoter[] paramArrayOfPromoter)
  {
    if ((paramArrayOfPromoter == null) || (paramArrayOfPromoter.length == 0))
    {
      paramMMEntity = new StringBuilder().append("unable send impression report.[promoters=");
      if (paramArrayOfPromoter == null);
      for (int i = 0; ; i = paramArrayOfPromoter.length)
      {
        g.e(i + "]", new Object[0]);
        return;
      }
    }
    new d.a(paramMMEntity).a(0).b(0).c(3).a(paramArrayOfPromoter).a().a();
  }

  public com.alimama.mobile.csdk.umupdate.a.a b()
  {
    return this.b;
  }

  public Context c()
  {
    return this.c;
  }

  public h d()
  {
    return this.d;
  }

  public boolean e()
  {
    return this.e;
  }

  public static abstract interface a
  {
    public static final int h = 0;
    public static final int i = 1;
    public static final int j = 2;

    public abstract void dataReceived(int paramInt, List<Promoter> paramList);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.a
 * JD-Core Version:    0.6.2
 */