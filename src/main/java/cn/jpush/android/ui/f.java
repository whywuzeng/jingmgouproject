package cn.jpush.android.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.jpush.android.data.d;
import cn.jpush.android.data.l;
import cn.jpush.android.data.o;
import cn.jpush.android.util.a;
import cn.jpush.android.util.x;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class f extends ArrayAdapter<d>
{
  private static final String[] z;
  private Context a = null;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "&i\007oP6Y\027l\033}(\005eR";
    int i = -1;
    int j = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int k = localObject2.length;
    int m;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      localObject3 = localObject1;
      n = j;
      localObject1 = localObject2;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 53;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label121;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "\"s\031gj7r\024y\0334h\022";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "#c\001+W-r\030jEd`\024bY!bU&\025&i\007oP6Y\027l\033}(\005eR";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 68;
        break label99;
        i = 6;
        break label99;
        i = 117;
        break label99;
        i = 11;
        break label99;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  public f(Context paramContext, int paramInt, List<d> paramList)
  {
    super(paramContext, -2147483648, paramList);
    this.a = paramContext;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    if (paramView == null)
    {
      Object localObject1 = (d)getItem(paramInt);
      paramInt = a.a(this.a, 5.0F);
      paramViewGroup = new FrameLayout(this.a);
      paramView = new LinearLayout(this.a);
      paramView.setPadding(paramInt, paramInt, paramInt, paramInt);
      paramView.setOrientation(1);
      try
      {
        InputStream localInputStream = this.a.getResources().getAssets().open(z[0]);
        localObject3 = BitmapFactory.decodeStream(localInputStream);
        if (localObject3 == null)
          throw new RuntimeException(z[2]);
      }
      catch (IOException localIOException1)
      {
        while (true)
        {
          localObject3 = new LinearLayout.LayoutParams(-2, -2);
          localObject2 = new LinearLayout(this.a);
          ((LinearLayout)localObject2).setOrientation(0);
          paramView.addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
          localObject3 = new ImageView(this.a);
          ((ImageView)localObject3).setBackgroundColor(-16711936);
          ((ImageView)localObject3).setScaleType(ImageView.ScaleType.CENTER_CROP);
          Object localObject4 = BitmapFactory.decodeFile(((l)localObject1).ad.l);
          if (localObject4 != null)
            ((ImageView)localObject3).setImageBitmap((Bitmap)localObject4);
          paramInt = a.a(this.a, 45.0F);
          ((LinearLayout)localObject2).addView((View)localObject3, new LinearLayout.LayoutParams(paramInt, paramInt));
          localObject3 = new LinearLayout(this.a);
          ((LinearLayout)localObject3).setOrientation(1);
          localObject4 = new TextView(this.a);
          ((TextView)localObject4).setTextSize(22.0F);
          ((TextView)localObject4).setSingleLine();
          ((TextView)localObject4).setEllipsize(TextUtils.TruncateAt.END);
          ((TextView)localObject4).setText(((d)localObject1).c);
          ((LinearLayout)localObject3).addView((View)localObject4);
          localObject4 = new TextView(this.a);
          ((TextView)localObject4).setSingleLine();
          ((TextView)localObject4).setEllipsize(TextUtils.TruncateAt.END);
          ((TextView)localObject4).setText(((d)localObject1).c);
          ((LinearLayout)localObject3).addView((View)localObject4);
          localObject1 = new LinearLayout.LayoutParams(-2, -2);
          ((LinearLayout.LayoutParams)localObject1).gravity = 16;
          ((LinearLayout.LayoutParams)localObject1).leftMargin = a.a(this.a, 5.0F);
          ((LinearLayout.LayoutParams)localObject1).rightMargin = a.a(this.a, 5.0F);
          ((LinearLayout)localObject2).addView((View)localObject3, (ViewGroup.LayoutParams)localObject1);
          localObject1 = new RelativeLayout(this.a);
          paramInt = a.a(this.a, 18.0F);
          localObject3 = new LinearLayout.LayoutParams(paramInt, paramInt);
          localObject2 = new LinearLayout(this.a);
          ((LinearLayout)localObject2).setOrientation(0);
          paramInt = 0;
          label456: if (paramInt < 3)
          {
            localObject4 = new ImageView(this.a);
            try
            {
              ((ImageView)localObject4).setImageBitmap(BitmapFactory.decodeStream(this.a.getAssets().open(z[1])));
              ((LinearLayout)localObject2).addView((View)localObject4, (ViewGroup.LayoutParams)localObject3);
              paramInt += 1;
              break label456;
              paramViewGroup.setBackgroundDrawable(new NinePatchDrawable(this.a.getResources(), new NinePatch((Bitmap)localObject3, ((Bitmap)localObject3).getNinePatchChunk(), null)));
              ((InputStream)localObject2).close();
            }
            catch (IOException localIOException2)
            {
              while (true)
              {
                f.class.getSimpleName();
                localIOException2.getMessage();
                x.f();
              }
            }
          }
        }
        Object localObject3 = new RelativeLayout.LayoutParams(-2, -2);
        ((RelativeLayout.LayoutParams)localObject3).addRule(9, -1);
        ((RelativeLayout.LayoutParams)localObject3).addRule(15, -1);
        ((RelativeLayout)localObject1).addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
        Object localObject2 = new RelativeLayout.LayoutParams(-2, -2);
        ((RelativeLayout.LayoutParams)localObject2).addRule(11, -1);
        ((RelativeLayout.LayoutParams)localObject2).addRule(15, -1);
        ((RelativeLayout)localObject1).addView(new TextView(this.a), (ViewGroup.LayoutParams)localObject2);
        localObject2 = new LinearLayout.LayoutParams(-1, -2);
        ((LinearLayout.LayoutParams)localObject2).topMargin = a.a(this.a, 5.0F);
        paramView.addView((View)localObject1, (ViewGroup.LayoutParams)localObject2);
        localObject1 = new FrameLayout.LayoutParams(-1, -2);
        ((FrameLayout.LayoutParams)localObject1).gravity = 17;
        ((FrameLayout.LayoutParams)localObject1).topMargin = a.a(this.a, 2.0F);
        ((FrameLayout.LayoutParams)localObject1).leftMargin = ((FrameLayout.LayoutParams)localObject1).topMargin;
        ((FrameLayout.LayoutParams)localObject1).bottomMargin = ((FrameLayout.LayoutParams)localObject1).topMargin;
        ((FrameLayout.LayoutParams)localObject1).rightMargin = ((FrameLayout.LayoutParams)localObject1).topMargin;
        paramViewGroup.addView(paramView, (ViewGroup.LayoutParams)localObject1);
      }
    }
    return paramViewGroup;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.f
 * JD-Core Version:    0.6.2
 */