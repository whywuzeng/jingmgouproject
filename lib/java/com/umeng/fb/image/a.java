package com.umeng.fb.image;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.umeng.fb.util.c;

public class a
{
  private static final String a = a.class.getName();
  private static a b;
  private LruCache<String, Bitmap> c;
  private Handler d;

  private a()
  {
    b();
  }

  private Bitmap a(String paramString)
  {
    return (Bitmap)this.c.get(paramString);
  }

  private Bitmap a(String paramString, int paramInt)
  {
    return b.a(paramString, paramInt);
  }

  public static a a()
  {
    if (b == null);
    try
    {
      if (b == null)
        b = new a();
      return b;
    }
    finally
    {
    }
  }

  private void a(String paramString, Bitmap paramBitmap)
  {
    if ((a(paramString) == null) && (paramBitmap != null))
      this.c.put(paramString, paramBitmap);
  }

  private void a(String paramString, ImageView paramImageView, Bitmap paramBitmap)
  {
    Message localMessage = Message.obtain();
    a locala = new a(null);
    locala.a = paramBitmap;
    locala.c = paramString;
    locala.b = paramImageView;
    localMessage.obj = locala;
    this.d.sendMessage(localMessage);
  }

  private void b()
  {
    this.c = new LruCache((int)Runtime.getRuntime().maxMemory() / 8)
    {
      protected int a(String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        return paramAnonymousBitmap.getRowBytes() * paramAnonymousBitmap.getHeight();
      }
    };
    this.d = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        Object localObject = (a.a)paramAnonymousMessage.obj;
        paramAnonymousMessage = ((a.a)localObject).a;
        ImageView localImageView = ((a.a)localObject).b;
        String str = ((a.a)localObject).c;
        localObject = ((a.a)localObject).b.getLayoutParams();
        ((ViewGroup.LayoutParams)localObject).height = paramAnonymousMessage.getHeight();
        ((ViewGroup.LayoutParams)localObject).width = paramAnonymousMessage.getWidth();
        localImageView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        if (localImageView.getTag().toString().equals(str))
          localImageView.setImageBitmap(paramAnonymousMessage);
      }
    };
  }

  private void b(final String paramString, final ImageView paramImageView, final int paramInt)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Bitmap localBitmap = a.a(a.this, paramString, paramInt);
        if (localBitmap != null)
        {
          a.a(a.this, paramString, localBitmap);
          a.a(a.this, paramString, paramImageView, localBitmap);
        }
      }
    }).start();
  }

  public void a(String paramString, ImageView paramImageView, int paramInt)
  {
    if ((paramString != null) && (c.c(paramString)) && (paramImageView != null))
    {
      paramImageView.setTag(paramString);
      Bitmap localBitmap = a(paramString);
      if (localBitmap != null)
        a(paramString, paramImageView, localBitmap);
    }
    else
    {
      return;
    }
    b(paramString, paramImageView, paramInt);
  }

  private class a
  {
    Bitmap a;
    ImageView b;
    String c;

    private a()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.image.a
 * JD-Core Version:    0.6.2
 */