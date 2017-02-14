package cn.jpush.android.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import cn.jpush.android.b.a.e;
import cn.jpush.android.data.d;

public final class q
{
  public static e a;
  private static int b;
  private static int c;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[7];
    int j = 0;
    Object localObject2 = " _\005v\"c\033";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 2;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "!_\005v\"c\033";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "=^\033lk-B1oky\001U";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "=^\033lk-BU%\"";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = ".R\021kjy\001U";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "1^\034xj-\033O?";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        b = 400;
        c = 600;
        a = null;
        return;
        i = 89;
        continue;
        i = 59;
        continue;
        i = 117;
        continue;
        i = 31;
      }
    }
  }

  // ERROR //
  private static android.graphics.Bitmap a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 59	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   4: invokevirtual 65	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   7: astore_0
    //   8: aload_0
    //   9: aload_1
    //   10: invokevirtual 71	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   13: astore_1
    //   14: aload_1
    //   15: invokestatic 77	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   18: astore_0
    //   19: aload_1
    //   20: invokevirtual 82	java/io/InputStream:close	()V
    //   23: aload_0
    //   24: areturn
    //   25: astore_1
    //   26: aconst_null
    //   27: astore_0
    //   28: aload_1
    //   29: invokevirtual 85	java/io/IOException:printStackTrace	()V
    //   32: aload_0
    //   33: areturn
    //   34: astore_1
    //   35: goto -7 -> 28
    //
    // Exception table:
    //   from	to	target	type
    //   8	19	25	java/io/IOException
    //   19	23	34	java/io/IOException
  }

  public static void a(Context paramContext, d paramd)
  {
    new Handler(Looper.getMainLooper()).post(new r(paramd, paramContext));
  }

  public static void a(WindowManager paramWindowManager, WebView paramWebView, ImageButton paramImageButton)
  {
    paramWindowManager.removeView(paramWebView);
    paramWindowManager.removeView(paramImageButton);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.q
 * JD-Core Version:    0.6.2
 */