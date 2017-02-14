package com.alimama.mobile;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.alimama.mobile.csdk.umupdate.a.g;
import com.alimama.mobile.csdk.umupdate.a.h;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.umeng.update.net.j;
import java.util.ArrayList;
import java.util.List;

public class MMAdView extends FrameLayout
  implements View.OnClickListener, a.a
{
  a a;
  MMEntity b;
  View c;
  View d;
  TextView e;
  LinearLayout f;
  boolean g = false;

  public MMAdView(Context paramContext)
  {
    super(paramContext);
  }

  public MMAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public MMAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void dataReceived(int paramInt, List<Promoter> paramList)
  {
    if (this.g)
    {
      g.c("reviced mm promoters,but the activity is finish.", new Object[0]);
      return;
    }
    if ((paramList != null) && (paramList.size() > 0))
    {
      ArrayList localArrayList = new ArrayList();
      paramInt = 0;
      while ((paramInt < paramList.size()) && (paramInt < 4))
      {
        Promoter localPromoter = (Promoter)paramList.get(paramInt);
        View localView = inflate(getContext(), this.a.d().b(), null);
        localView.setTag(localPromoter);
        Object localObject = (ImageView)localView.findViewById(this.a.d().h());
        j.a(getContext(), (ImageView)localObject, localPromoter.icon, false);
        localObject = new LinearLayout.LayoutParams(-2, -2);
        ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
        this.f.addView(localView, (ViewGroup.LayoutParams)localObject);
        localView.setOnClickListener(this);
        localArrayList.add(localPromoter);
        paramInt += 1;
      }
      this.a.a(this.b, (Promoter[])localArrayList.toArray(new Promoter[localArrayList.size()]));
      this.c.setVisibility(8);
      return;
    }
    this.e.setVisibility(0);
    this.d.setVisibility(4);
    this.e.setText("加载失败...");
  }

  public void destroy()
  {
    this.g = true;
  }

  public boolean init(String paramString)
  {
    this.a = a.a();
    if (!this.a.e())
      this.a.a(getContext());
    try
    {
      inflate(getContext(), a.a().d().a(), this);
      this.c = findViewById(this.a.d().d());
      this.f = ((LinearLayout)findViewById(this.a.d().e()));
      this.b = new MMEntity(paramString);
      setVisibility(0);
      this.c.setVisibility(0);
      this.e = ((TextView)findViewById(this.a.d().f()));
      this.e.setVisibility(4);
      this.d = findViewById(this.a.d().g());
      this.d.setVisibility(0);
      this.a.a(this.b, this);
      return true;
    }
    catch (Exception paramString)
    {
      Log.w("mmsdk", "An error occurred while initializing MMAdView.", paramString);
    }
    return false;
  }

  public void onClick(View paramView)
  {
    if ((paramView.getTag() instanceof Promoter))
    {
      paramView = (Promoter)paramView.getTag();
      this.a.a(this.b, paramView);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.MMAdView
 * JD-Core Version:    0.6.2
 */