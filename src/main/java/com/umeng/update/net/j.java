package com.umeng.update.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.animation.Animation;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;
import u.upd.a;
import u.upd.b;
import u.upd.f.a;
import u.upd.n;

public class j
{
  public static boolean a = false;
  private static final String b = j.class.getName();
  private static final long c = 52428800L;
  private static final long d = 10485760L;
  private static final long e = 1800000L;
  private static final Map<ImageView, String> f = Collections.synchronizedMap(new WeakHashMap());
  private static Thread g;

  private static long a(File paramFile)
  {
    long l2;
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()))
    {
      l2 = 0L;
      return l2;
    }
    Stack localStack = new Stack();
    localStack.clear();
    localStack.push(paramFile);
    long l1 = 0L;
    while (true)
    {
      l2 = l1;
      if (localStack.isEmpty())
        break;
      paramFile = ((File)localStack.pop()).listFiles();
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramFile[i];
        l2 = l1;
        if (!localObject.isDirectory())
          l2 = l1 + localObject.length();
        i += 1;
        l1 = l2;
      }
    }
  }

  private static Bitmap a(Bitmap paramBitmap)
  {
    try
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
      RectF localRectF = new RectF(localRect);
      localPaint.setAntiAlias(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localPaint.setColor(-12434878);
      localCanvas.drawRoundRect(localRectF, paramBitmap.getWidth() / 6, paramBitmap.getHeight() / 6, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
      paramBitmap.recycle();
      return localBitmap;
    }
    catch (OutOfMemoryError paramBitmap)
    {
      b.e(b, "Cant`t create round corner bitmap. [OutOfMemoryError] ");
    }
    return null;
  }

  public static File a(String paramString, Context paramContext, boolean[] paramArrayOfBoolean)
    throws IOException
  {
    if (a.b())
    {
      String str = Environment.getExternalStorageDirectory().getCanonicalPath();
      paramString = new File(str + "/download/.um" + paramString);
      paramString.mkdirs();
      if (paramString.exists())
      {
        paramArrayOfBoolean[0] = true;
        return paramString;
      }
    }
    paramString = paramContext.getCacheDir().getAbsolutePath();
    new File(paramString).mkdir();
    a(paramString, 505, -1, -1);
    paramString = paramString + "/umdownload";
    new File(paramString).mkdir();
    a(paramString, 505, -1, -1);
    paramString = new File(paramString);
    paramArrayOfBoolean[0] = false;
    return paramString;
  }

  public static String a(Context paramContext, String paramString)
  {
    if (n.d(paramString))
      return null;
    try
    {
      localObject = b(paramString) + ".tmp";
      if (a.b())
        paramContext = Environment.getExternalStorageDirectory().getCanonicalPath();
      InputStream localInputStream;
      for (long l = 52428800L; ; l = 10485760L)
      {
        paramContext = new File(paramContext + "/download/.um");
        a(paramContext, l, 1800000L);
        localObject = new File(paramContext, (String)localObject);
        try
        {
          ((File)localObject).createNewFile();
          paramContext = new FileOutputStream((File)localObject);
          localInputStream = (InputStream)new URL(paramString).openConnection().getContent();
          byte[] arrayOfByte = new byte[4096];
          while (true)
          {
            int i = localInputStream.read(arrayOfByte);
            if (i == -1)
              break;
            paramContext.write(arrayOfByte, 0, i);
          }
        }
        catch (Exception paramContext)
        {
        }
        b.a(b, paramContext.getStackTrace().toString() + "\t url:\t" + n.a + paramString);
        if ((localObject != null) && (((File)localObject).exists()))
          ((File)localObject).deleteOnExit();
        return null;
        paramContext = paramContext.getCacheDir().getCanonicalPath();
      }
      paramContext.flush();
      localInputStream.close();
      paramContext.close();
      paramContext = new File(((File)localObject).getParent(), ((File)localObject).getName().replace(".tmp", ""));
      ((File)localObject).renameTo(paramContext);
      b.a(b, "download img[" + paramString + "]  to " + paramContext.getCanonicalPath());
      paramContext = paramContext.getCanonicalPath();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      while (true)
        Object localObject = null;
    }
  }

  public static void a(Context paramContext, ImageView paramImageView, String paramString, boolean paramBoolean)
  {
    a(paramContext, paramImageView, paramString, paramBoolean, null, null, false);
  }

  public static void a(Context paramContext, ImageView paramImageView, String paramString, boolean paramBoolean, a parama)
  {
    a(paramContext, paramImageView, paramString, paramBoolean, parama, null, false);
  }

  public static void a(Context paramContext, ImageView paramImageView, String paramString, boolean paramBoolean, a parama, Animation paramAnimation)
  {
    a(paramContext, paramImageView, paramString, paramBoolean, parama, null, false);
  }

  public static void a(Context paramContext, ImageView paramImageView, String paramString, boolean paramBoolean1, a parama, Animation paramAnimation, boolean paramBoolean2)
  {
    if (paramImageView == null);
    do
    {
      return;
      f.put(paramImageView, paramString);
      try
      {
        File localFile = b(paramContext, paramString);
        if ((localFile == null) || (!localFile.exists()) || (a))
          break;
        if (parama != null)
          parama.a(b.a);
        Drawable localDrawable = c(localFile.getAbsolutePath());
        if (localDrawable == null)
          localFile.delete();
        b(paramContext, paramImageView, localDrawable, paramBoolean1, parama, paramAnimation, paramBoolean2, paramString);
        return;
      }
      catch (Exception paramContext)
      {
        b.b(b, "", paramContext);
      }
    }
    while (parama == null);
    parama.a(f.a.b);
    return;
    new c(paramContext, paramImageView, paramString, b.b, null, paramBoolean1, parama, paramAnimation, paramBoolean2).execute(new Object[0]);
  }

  public static void a(File arg0, long paramLong1, final long paramLong2)
    throws IOException
  {
    if (???.exists())
    {
      if (a(???.getCanonicalFile()) > paramLong1)
      {
        if (g == null)
          g = new Thread(new Runnable()
          {
            public void run()
            {
              j.a(this.a, paramLong2);
              j.a(null);
            }
          });
        synchronized (g)
        {
          g.start();
          return;
        }
      }
    }
    else if (!???.mkdirs())
      b.b(b, "Failed to create directory" + ???.getAbsolutePath() + ". Check permission. Make sure WRITE_EXTERNAL_STORAGE is added in your Manifest.xml");
  }

  private static boolean a(ImageView paramImageView, String paramString)
  {
    paramImageView = (String)f.get(paramImageView);
    return (paramImageView != null) && (!paramImageView.equals(paramString));
  }

  public static boolean a(String paramString, int paramInt)
  {
    int i = 432;
    if ((paramInt & 0x1) != 0)
      i = 436;
    int j = i;
    if ((paramInt & 0x2) != 0)
      j = i | 0x2;
    return a(paramString, j, -1, -1);
  }

  public static boolean a(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      Class.forName("android.os.FileUtils").getMethod("setPermissions", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE }).invoke(null, new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(-1), Integer.valueOf(-1) });
      return true;
    }
    catch (ClassNotFoundException paramString)
    {
      b.b(b, "error when set permissions:", paramString);
      return false;
    }
    catch (NoSuchMethodException paramString)
    {
      while (true)
        b.b(b, "error when set permissions:", paramString);
    }
    catch (IllegalArgumentException paramString)
    {
      while (true)
        b.b(b, "error when set permissions:", paramString);
    }
    catch (IllegalAccessException paramString)
    {
      while (true)
        b.b(b, "error when set permissions:", paramString);
    }
    catch (InvocationTargetException paramString)
    {
      while (true)
        b.b(b, "error when set permissions:", paramString);
    }
  }

  protected static File b(Context paramContext, String paramString)
    throws IOException
  {
    paramString = b(paramString);
    if (a.b());
    for (paramContext = Environment.getExternalStorageDirectory().getCanonicalPath(); ; paramContext = paramContext.getCacheDir().getCanonicalPath())
    {
      paramContext = new File(new File(paramContext + "/download/.um"), paramString);
      if (!paramContext.exists())
        break;
      return paramContext;
    }
    return null;
  }

  private static String b(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    String str = "";
    if (i >= 0)
      str = paramString.substring(i);
    return n.a(paramString) + str;
  }

  private static void b(Context paramContext, ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, a parama, Animation paramAnimation, boolean paramBoolean2, String paramString)
  {
    paramContext = paramDrawable;
    if (paramBoolean2)
    {
      paramContext = paramDrawable;
      if (paramDrawable == null);
    }
    while (true)
    {
      try
      {
        paramContext = new BitmapDrawable(a(((BitmapDrawable)paramDrawable).getBitmap()));
        break label207;
        if (parama != null)
          parama.a(f.a.b);
        b.e(b, "bind drawable failed. drawable [" + paramContext + "]  imageView[+" + paramImageView + "+]");
        return;
        if (a(paramImageView, paramString))
        {
          if (parama == null)
            continue;
          parama.a(f.a.b);
          continue;
        }
      }
      catch (Exception paramContext)
      {
        b.b(b, "bind failed", paramContext);
        if (parama == null)
          continue;
        parama.a(f.a.b);
        continue;
      }
      finally
      {
      }
      if (paramBoolean1 == true)
        paramImageView.setBackgroundDrawable(paramContext);
      while (true)
      {
        if (paramAnimation != null)
          paramImageView.startAnimation(paramAnimation);
        if (parama == null)
          break;
        parama.a(f.a.a);
        break;
        paramImageView.setImageDrawable(paramContext);
      }
      label207: if (paramContext != null)
        if (paramImageView != null);
    }
  }

  private static void b(File paramFile, long paramLong)
  {
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.canWrite()) || (!paramFile.isDirectory()));
    while (true)
    {
      return;
      paramFile = paramFile.listFiles();
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramFile[i];
        if ((!localObject.isDirectory()) && (new Date().getTime() - localObject.lastModified() > paramLong))
          localObject.delete();
        i += 1;
      }
    }
  }

  private static Drawable c(String paramString)
  {
    try
    {
      paramString = Drawable.createFromPath(paramString);
      return paramString;
    }
    catch (OutOfMemoryError paramString)
    {
      b.e(b, "Resutil fetchImage OutOfMemoryError:" + paramString.toString());
    }
    return null;
  }

  public static abstract interface a
  {
    public abstract void a(j.b paramb);

    public abstract void a(f.a parama);
  }

  public static enum b
  {
  }

  static class c extends AsyncTask<Object, Integer, Drawable>
  {
    private Context a;
    private String b;
    private ImageView c;
    private j.b d;
    private boolean e;
    private j.a f;
    private Animation g;
    private boolean h;
    private File i;

    public c(Context paramContext, ImageView paramImageView, String paramString, j.b paramb, File paramFile, boolean paramBoolean1, j.a parama, Animation paramAnimation, boolean paramBoolean2)
    {
      this.i = paramFile;
      this.a = paramContext;
      this.b = paramString;
      this.f = parama;
      this.d = paramb;
      this.e = paramBoolean1;
      this.g = paramAnimation;
      this.c = paramImageView;
      this.h = paramBoolean2;
    }

    protected Drawable a(Object[] paramArrayOfObject)
    {
      if (j.a);
      try
      {
        Thread.sleep(3000L);
        if ((this.i != null) && (this.i.exists()))
        {
          paramArrayOfObject = j.a(this.i.getAbsolutePath());
          if (paramArrayOfObject == null)
            this.i.delete();
          b.c(j.a(), "get drawable from cacheFile.");
          return paramArrayOfObject;
        }
      }
      catch (InterruptedException paramArrayOfObject)
      {
        while (true)
          paramArrayOfObject.printStackTrace();
      }
      while (true)
      {
        try
        {
          j.a(this.a, this.b);
          paramArrayOfObject = j.b(this.a, this.b);
          if ((paramArrayOfObject != null) && (paramArrayOfObject.exists()))
          {
            paramArrayOfObject = j.a(paramArrayOfObject.getAbsolutePath());
            b.c(j.a(), "get drawable from net else file.");
            return paramArrayOfObject;
          }
        }
        catch (Exception paramArrayOfObject)
        {
          b.e(j.a(), paramArrayOfObject.toString(), paramArrayOfObject);
          return null;
        }
        paramArrayOfObject = null;
      }
    }

    protected void a(Drawable paramDrawable)
    {
      j.a(this.a, this.c, paramDrawable, this.e, this.f, this.g, this.h, this.b);
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      if (this.f != null)
        this.f.a(this.d);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.net.j
 * JD-Core Version:    0.6.2
 */