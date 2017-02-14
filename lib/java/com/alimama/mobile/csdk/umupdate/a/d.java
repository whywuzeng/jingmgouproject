package com.alimama.mobile.csdk.umupdate.a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.alimama.mobile.csdk.umupdate.b.d.a;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;

public class d
{
  private static void a(MMEntity paramMMEntity, Promoter paramPromoter, int paramInt)
  {
    new k(paramPromoter, new d.a(paramMMEntity).a(7).b(paramInt).c(3).a(new Promoter[] { paramPromoter })).a();
  }

  public static void a(Promoter paramPromoter, MMEntity paramMMEntity, boolean paramBoolean, int paramInt)
  {
    try
    {
      paramMMEntity = (MMEntity)paramMMEntity.clone();
      if (com.alimama.mobile.a.a().b().c(paramPromoter.app_package_name))
      {
        c(paramMMEntity, paramPromoter, paramInt);
        return;
      }
    }
    catch (CloneNotSupportedException paramMMEntity)
    {
      while (true)
        paramMMEntity = null;
      int j = paramPromoter.landing_type;
      int i = j;
      if (paramBoolean)
      {
        i = j;
        if (j == 0)
          i = 1;
      }
      switch (i)
      {
      default:
        Toast.makeText(com.alimama.mobile.a.a().c(), "不支持该点击.", 0).show();
        return;
      case 2:
      case 3:
      case 4:
      case 0:
      case 1:
      }
    }
    b(paramMMEntity, paramPromoter, paramInt);
    return;
    a(paramMMEntity, paramPromoter, paramInt);
  }

  private static void b(MMEntity paramMMEntity, Promoter paramPromoter, int paramInt)
  {
    new d.a(paramMMEntity).a(2).b(paramInt).c(3).a(new Promoter[] { paramPromoter }).a().a();
    try
    {
      paramMMEntity = new Intent("android.intent.action.VIEW", Uri.parse(paramPromoter.url));
      com.alimama.mobile.a.a().c().startActivity(paramMMEntity);
      return;
    }
    catch (ActivityNotFoundException paramMMEntity)
    {
      g.d(e.e, new Object[] { paramMMEntity.toString() });
      Toast.makeText(com.alimama.mobile.a.a().c(), "无法找到浏览器.", 0).show();
    }
  }

  private static void c(MMEntity paramMMEntity, Promoter paramPromoter, int paramInt)
  {
    new d.a(paramMMEntity).a(5).b(paramInt).c(0).a(new Promoter[] { paramPromoter }).a().a();
    if (!TextUtils.isEmpty(paramPromoter.url_in_app))
    {
      j.b(com.alimama.mobile.a.a().c(), paramPromoter.url_in_app);
      return;
    }
    j.a(com.alimama.mobile.a.a().c(), paramPromoter.app_package_name);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.a.d
 * JD-Core Version:    0.6.2
 */