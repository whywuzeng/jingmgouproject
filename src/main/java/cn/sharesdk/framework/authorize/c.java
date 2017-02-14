package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;

class c
  implements View.OnClickListener
{
  c(RegisterView paramRegisterView)
  {
  }

  public void onClick(View paramView)
  {
    try
    {
      int i = R.getStringRes(paramView.getContext(), "website");
      Object localObject = paramView.getResources().getString(i);
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localObject = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject));
        paramView.getContext().startActivity((Intent)localObject);
      }
      return;
    }
    catch (Throwable paramView)
    {
      Ln.e(paramView);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.c
 * JD-Core Version:    0.6.2
 */