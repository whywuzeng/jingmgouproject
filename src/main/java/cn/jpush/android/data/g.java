package cn.jpush.android.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.jpush.android.b.a.a;
import cn.jpush.android.b.a.b;
import cn.jpush.android.b.a.f;
import cn.jpush.android.util.x;
import java.io.IOException;
import java.util.Random;

public final class g extends LinearLayout
{
  private static final String[] F;
  public static f b;
  private float A;
  private WebView B;
  private d C;
  private Bitmap D;
  private Bitmap E;
  int[][] a = { { 0, 0 }, { 1, 1 }, { 0, 1 } };
  Handler c = new h(this);
  private LinearLayout d;
  private LinearLayout e;
  private LinearLayout f;
  private LinearLayout g;
  private LinearLayout h;
  private LinearLayout i;
  private ImageView j;
  private TextView k;
  private ScrollView l;
  private TextView m;
  private TextView n;
  private TextView o;
  private TextView p;
  private TextView q;
  private TextView r;
  private ImageView s;
  private Button t;
  private Button u;
  private Context v;
  private Bitmap w = null;
  private Bitmap x = null;
  private int y = 0;
  private int z;

  static
  {
    String[] arrayOfString = new String[13];
    int i2 = 0;
    Object localObject2 = "T任讑刧";
    int i1 = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i3 = arrayOfChar.length;
    int i6 = 0;
    int i4 = 0;
    int i8 = i1;
    localObject2 = arrayOfChar;
    int i9 = i2;
    Object localObject3 = localObject1;
    int i5 = i3;
    Object localObject4;
    int i7;
    if (i3 <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i7 = i1;
      label68: i5 = i4;
      label71: localObject2 = localObject1;
      i6 = localObject2[i4];
      switch (i5 % 5)
      {
      default:
        i1 = 18;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i4] = ((char)(i1 ^ i6));
      i5 += 1;
      if (i3 == 0)
      {
        i4 = i3;
        break label71;
      }
      i6 = i5;
      i5 = i3;
      localObject3 = localObject4;
      i9 = i2;
      localObject2 = localObject1;
      i8 = i7;
      i7 = i8;
      localObject1 = localObject2;
      i2 = i9;
      localObject4 = localObject3;
      i3 = i5;
      i4 = i6;
      if (i5 > i6)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i8)
      {
      default:
        localObject3[i9] = localObject1;
        i2 = 1;
        localObject2 = "\007$4Sq\034\003:YX\02574c`\035%2DM";
        i1 = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i9] = localObject1;
        i2 = 2;
        localObject2 = ">\021 Rz#$7";
        i1 = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i9] = localObject1;
        i2 = 3;
        localObject2 = "!\025\023\f*";
        i1 = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i9] = localObject1;
        i2 = 4;
        localObject2 = "\030.4EG\006-o";
        i1 = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i9] = localObject1;
        i2 = 5;
        localObject2 = "\00754SMD";
        i1 = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i9] = localObject1;
        i2 = 6;
        localObject2 = "輛亷亞绬";
        i1 = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i9] = localObject1;
        i2 = 7;
        localObject2 = "\020$3~{\027.;";
        i1 = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i9] = localObject1;
        i2 = 8;
        localObject2 = "\020$3~{\031&";
        i1 = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i9] = localObject1;
        i2 = 9;
        localObject2 = "\00754SME";
        i1 = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i9] = localObject1;
        i2 = 10;
        localObject2 = "厢淉";
        i1 = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i9] = localObject1;
        i2 = 11;
        localObject2 = "炙镁輺仗";
        i1 = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i9] = localObject1;
        i2 = 12;
        localObject2 = "乿輼寜裤";
        i1 = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i9] = localObject1;
        F = arrayOfString;
        b = null;
        return;
        i1 = 116;
        continue;
        i1 = 65;
        continue;
        i1 = 85;
        continue;
        i1 = 33;
      }
    }
  }

  public g(Context paramContext, d paramd)
  {
    super(paramContext, null);
    x.c();
    this.v = paramContext;
    this.C = paramd;
    this.z = this.v.getResources().getDisplayMetrics().widthPixels;
    this.A = this.v.getResources().getDisplayMetrics().density;
    try
    {
      this.w = BitmapFactory.decodeStream(this.v.getAssets().open(F[5]));
      this.x = BitmapFactory.decodeStream(this.v.getAssets().open(F[9]));
      label175: this.y = new Random().nextInt(this.a.length);
      paramContext = new LinearLayout.LayoutParams(-1, -1);
      setLayoutParams(paramContext);
      setOrientation(1);
      setPadding(5, 5, 5, 5);
      this.d = new LinearLayout(this.v);
      this.d.setOrientation(1);
      this.d.setGravity(17);
      this.d.setPadding(1, 1, 1, 1);
      this.d.setBackgroundColor(Color.rgb(117, 117, 117));
      addView(this.d, paramContext);
      paramContext = new LinearLayout.LayoutParams(-1, 75);
      this.e = new LinearLayout(this.v);
      this.e.setBackgroundColor(Color.rgb(0, 160, 200));
      this.e.setPadding(5, 5, 5, 5);
      this.e.setOrientation(0);
      this.e.setGravity(17);
      this.d.addView(this.e, paramContext);
      paramContext = new LinearLayout.LayoutParams(72, 72);
      this.j = new ImageView(this.v);
      this.j.setScaleType(ImageView.ScaleType.FIT_XY);
      try
      {
        paramd = BitmapFactory.decodeStream(this.v.getAssets().open(F[7]));
        if (paramd != null)
          this.j.setImageBitmap(paramd);
        label442: this.e.addView(this.j, paramContext);
        paramContext = new LinearLayout.LayoutParams(-1, -2);
        this.k = new TextView(this.v);
        this.k.setMaxEms(10);
        this.k.setMaxLines(1);
        this.k.setSingleLine(true);
        this.k.setTextColor(-16777216);
        this.k.setPadding(15, 0, 0, 0);
        this.k.setTextSize(20.0F);
        this.e.addView(this.k, paramContext);
        paramContext = new LinearLayout.LayoutParams(-1, -2);
        this.l = new ScrollView(this.v);
        paramContext.weight = 1.0F;
        this.d.addView(this.l, paramContext);
        paramContext = new LinearLayout.LayoutParams(-1, -2);
        this.f = new LinearLayout(this.v);
        this.f.setOrientation(1);
        this.l.addView(this.f, paramContext);
        paramContext = new LinearLayout.LayoutParams(-1, -2);
        paramContext.setMargins(0, 1, 0, 0);
        this.m = new TextView(this.v);
        this.m.setPadding(20, 5, 0, 5);
        this.m.setBackgroundColor(Color.rgb(229, 229, 229));
        this.m.setText(F[11]);
        this.m.setTextColor(-16777216);
        this.m.setTextSize(15.0F);
        this.f.addView(this.m, paramContext);
        LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams1.setMargins(0, 1, 0, 0);
        paramd = new LinearLayout(this.v);
        paramd.setBackgroundColor(Color.rgb(247, 248, 249));
        paramd.setGravity(17);
        this.f.addView(paramd, localLayoutParams1);
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams1 = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams1.weight = 1.0F;
        LinearLayout localLinearLayout = new LinearLayout(this.v);
        localLinearLayout.setOrientation(1);
        paramd.addView(localLinearLayout, localLayoutParams1);
        this.n = new TextView(this.v);
        this.n.setPadding(20, 10, 5, 5);
        this.n.setTextColor(-16777216);
        this.n.setTextSize(12.0F);
        localLinearLayout.addView(this.n, localLayoutParams2);
        this.o = new TextView(this.v);
        this.o.setPadding(20, 5, 5, 5);
        this.o.setTextColor(-16777216);
        this.o.setTextSize(12.0F);
        localLinearLayout.addView(this.o, localLayoutParams2);
        this.p = new TextView(this.v);
        this.p.setPadding(20, 5, 5, 10);
        this.p.setTextColor(-16777216);
        this.p.setTextSize(12.0F);
        localLinearLayout.addView(this.p, localLayoutParams2);
        localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams1.weight = 1.0F;
        this.i = new LinearLayout(this.v);
        this.i.setOrientation(1);
        this.i.setGravity(17);
        this.i.setPadding(0, 0, 100, 0);
        paramd.addView(this.i, localLayoutParams2);
        a(this.i);
        this.q = new TextView(this.v);
        this.q.setBackgroundColor(Color.rgb(229, 229, 229));
        this.m.setTextSize(15.0F);
        this.q.setPadding(20, 5, 5, 5);
        this.q.setTextColor(-16777216);
        this.q.setText(F[6]);
        this.f.addView(this.q, paramContext);
        paramContext = new LinearLayout.LayoutParams(-1, -2);
        paramContext.setMargins(0, 1, 0, 0);
        this.r = new TextView(this.v);
        this.r.setBackgroundColor(Color.rgb(247, 248, 249));
        this.r.setTextSize(12.0F);
        this.r.setPadding(20, 20, 20, 20);
        this.r.setTextColor(-16777216);
        this.f.addView(this.r, paramContext);
        paramContext = new LinearLayout.LayoutParams(-1, -2);
        this.h = new LinearLayout(this.v);
        this.h.setOrientation(1);
        this.h.setGravity(17);
        this.h.setPadding(0, 20, 0, 0);
        this.f.addView(this.h, paramContext);
        paramContext = new LinearLayout.LayoutParams((int)(220.0F * this.A), (int)(300.0F * this.A));
        this.s = new ImageView(this.v);
        this.s.setScaleType(ImageView.ScaleType.FIT_XY);
        try
        {
          paramd = BitmapFactory.decodeStream(this.v.getAssets().open(F[8]));
          if (paramd != null)
            this.s.setImageBitmap(paramd);
          label1456: this.h.addView(this.s, paramContext);
          paramContext = new LinearLayout.LayoutParams(-1, -2);
          this.g = new LinearLayout(this.v);
          this.g.setBackgroundColor(Color.rgb(0, 160, 200));
          this.g.setPadding(5, 5, 5, 5);
          this.g.setOrientation(0);
          this.g.setGravity(17);
          this.d.addView(this.g, paramContext);
          paramContext = new LinearLayout.LayoutParams(this.z / 2, -2);
          this.t = new Button(this.v);
          this.t.setText(F[12]);
          this.g.addView(this.t, paramContext);
          this.u = new Button(this.v);
          this.u.setText(F[10]);
          this.g.addView(this.u, paramContext);
          return;
        }
        catch (IOException paramd)
        {
          break label1456;
        }
      }
      catch (IOException paramd)
      {
        break label442;
      }
    }
    catch (IOException paramContext)
    {
      break label175;
    }
  }

  private void a(LinearLayout paramLinearLayout)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    Object localObject2 = new LinearLayout(this.v);
    ((LinearLayout)localObject2).setOrientation(0);
    ((LinearLayout)localObject2).setGravity(17);
    paramLinearLayout.addView((View)localObject2, localLayoutParams);
    int i1;
    Object localObject1;
    if (this.w != null)
    {
      i1 = 0;
      while (i1 < 3)
      {
        localObject1 = new ImageView(this.v);
        ((ImageView)localObject1).setImageBitmap(this.w);
        ((LinearLayout)localObject2).addView((View)localObject1, localLayoutParams);
        i1 += 1;
      }
    }
    int[] arrayOfInt = this.a[this.y];
    if (arrayOfInt.length == 2)
    {
      ImageView localImageView = new ImageView(this.v);
      if (arrayOfInt[0] == 0)
      {
        localObject1 = this.w;
        localImageView.setImageBitmap((Bitmap)localObject1);
        ((LinearLayout)localObject2).addView(localImageView, localLayoutParams);
        localImageView = new ImageView(this.v);
        if (arrayOfInt[1] != 0)
          break label290;
        localObject1 = this.w;
        label179: localImageView.setImageBitmap((Bitmap)localObject1);
        ((LinearLayout)localObject2).addView(localImageView, localLayoutParams);
      }
    }
    while (true)
    {
      localObject1 = new Random();
      localObject2 = new TextView(this.v);
      ((TextView)localObject2).setPadding(10, 5, 5, 5);
      ((TextView)localObject2).setTextSize(15.0F);
      ((TextView)localObject2).setText(String.valueOf(((Random)localObject1).nextInt(2000) + 200) + F[0]);
      paramLinearLayout.addView((View)localObject2, localLayoutParams);
      return;
      localObject1 = this.x;
      break;
      label290: localObject1 = this.x;
      break label179;
      i1 = 0;
      while (i1 < 2)
      {
        localObject1 = new ImageView(this.v);
        ((ImageView)localObject1).setImageBitmap(this.x);
        ((LinearLayout)localObject2).addView((View)localObject1, localLayoutParams);
        i1 += 1;
      }
    }
  }

  public final void a()
  {
    if (this.B != null)
      this.B.destroy();
  }

  public final void a(View.OnClickListener paramOnClickListener)
  {
    if (this.u != null)
      this.u.setOnClickListener(paramOnClickListener);
  }

  @SuppressLint({"NewApi"})
  public final void a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    new StringBuilder(F[4]).append(paramString).toString();
    x.c();
    if (this.f != null)
    {
      this.e.setVisibility(8);
      this.f.removeAllViews();
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams.weight = 1.0F;
      this.B = new WebView(this.v);
      this.B.clearCache(true);
      this.B.getSettings().setJavaScriptEnabled(true);
      this.B.getSettings().setDefaultTextEncodingName(F[3]);
      this.B.getSettings().setSupportZoom(true);
      this.B.getSettings().setCacheMode(2);
      b = new f(this.v, this.C);
      this.B.removeJavascriptInterface(F[1]);
      this.B.setWebChromeClient(new a(F[2], b.class));
      this.B.loadUrl(paramString);
      this.B.requestFocus();
      this.f.addView(this.B, localLayoutParams);
      this.t.setVisibility(8);
      this.u.setVisibility(0);
    }
  }

  protected final void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected final void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.g
 * JD-Core Version:    0.6.2
 */