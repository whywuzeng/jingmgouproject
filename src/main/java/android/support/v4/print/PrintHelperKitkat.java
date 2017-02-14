package android.support.v4.print;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class PrintHelperKitkat
{
  public static final int COLOR_MODE_COLOR = 2;
  public static final int COLOR_MODE_MONOCHROME = 1;
  private static final String LOG_TAG = "PrintHelperKitkat";
  private static final int MAX_PRINT_SIZE = 3500;
  public static final int ORIENTATION_LANDSCAPE = 1;
  public static final int ORIENTATION_PORTRAIT = 2;
  public static final int SCALE_MODE_FILL = 2;
  public static final int SCALE_MODE_FIT = 1;
  int mColorMode = 2;
  final Context mContext;
  BitmapFactory.Options mDecodeOptions = null;
  private final Object mLock = new Object();
  int mOrientation = 1;
  int mScaleMode = 2;

  PrintHelperKitkat(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private Matrix getMatrix(int paramInt1, int paramInt2, RectF paramRectF, int paramInt3)
  {
    Matrix localMatrix = new Matrix();
    float f = paramRectF.width() / paramInt1;
    if (paramInt3 == 2);
    for (f = Math.max(f, paramRectF.height() / paramInt2); ; f = Math.min(f, paramRectF.height() / paramInt2))
    {
      localMatrix.postScale(f, f);
      localMatrix.postTranslate((paramRectF.width() - paramInt1 * f) / 2.0F, (paramRectF.height() - paramInt2 * f) / 2.0F);
      return localMatrix;
    }
  }

  private Bitmap loadBitmap(Uri paramUri, BitmapFactory.Options paramOptions)
    throws FileNotFoundException
  {
    if ((paramUri == null) || (this.mContext == null))
      throw new IllegalArgumentException("bad argument to loadBitmap");
    Uri localUri = null;
    try
    {
      paramUri = this.mContext.getContentResolver().openInputStream(paramUri);
      localUri = paramUri;
      paramOptions = BitmapFactory.decodeStream(paramUri, null, paramOptions);
      if (paramUri != null);
      try
      {
        paramUri.close();
        return paramOptions;
      }
      catch (IOException paramUri)
      {
        Log.w("PrintHelperKitkat", "close fail ", paramUri);
        return paramOptions;
      }
    }
    finally
    {
      if (localUri == null);
    }
    try
    {
      localUri.close();
      throw paramUri;
    }
    catch (IOException paramOptions)
    {
      while (true)
        Log.w("PrintHelperKitkat", "close fail ", paramOptions);
    }
  }

  // ERROR //
  private Bitmap loadConstrainedBitmap(Uri paramUri, int paramInt)
    throws FileNotFoundException
  {
    // Byte code:
    //   0: iload_2
    //   1: ifle +14 -> 15
    //   4: aload_1
    //   5: ifnull +10 -> 15
    //   8: aload_0
    //   9: getfield 56	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
    //   12: ifnonnull +13 -> 25
    //   15: new 108	java/lang/IllegalArgumentException
    //   18: dup
    //   19: ldc 146
    //   21: invokespecial 113	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   24: athrow
    //   25: new 148	android/graphics/BitmapFactory$Options
    //   28: dup
    //   29: invokespecial 149	android/graphics/BitmapFactory$Options:<init>	()V
    //   32: astore 7
    //   34: aload 7
    //   36: iconst_1
    //   37: putfield 153	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   40: aload_0
    //   41: aload_1
    //   42: aload 7
    //   44: invokespecial 155	android/support/v4/print/PrintHelperKitkat:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   47: pop
    //   48: aload 7
    //   50: getfield 158	android/graphics/BitmapFactory$Options:outWidth	I
    //   53: istore 5
    //   55: aload 7
    //   57: getfield 161	android/graphics/BitmapFactory$Options:outHeight	I
    //   60: istore 6
    //   62: iload 5
    //   64: ifle +8 -> 72
    //   67: iload 6
    //   69: ifgt +5 -> 74
    //   72: aconst_null
    //   73: areturn
    //   74: iload 5
    //   76: iload 6
    //   78: invokestatic 164	java/lang/Math:max	(II)I
    //   81: istore 4
    //   83: iconst_1
    //   84: istore_3
    //   85: iload 4
    //   87: iload_2
    //   88: if_icmple +16 -> 104
    //   91: iload 4
    //   93: iconst_1
    //   94: iushr
    //   95: istore 4
    //   97: iload_3
    //   98: iconst_1
    //   99: ishl
    //   100: istore_3
    //   101: goto -16 -> 85
    //   104: iload_3
    //   105: ifle -33 -> 72
    //   108: iload 5
    //   110: iload 6
    //   112: invokestatic 166	java/lang/Math:min	(II)I
    //   115: iload_3
    //   116: idiv
    //   117: ifle -45 -> 72
    //   120: aload_0
    //   121: getfield 48	android/support/v4/print/PrintHelperKitkat:mLock	Ljava/lang/Object;
    //   124: astore 7
    //   126: aload 7
    //   128: monitorenter
    //   129: aload_0
    //   130: new 148	android/graphics/BitmapFactory$Options
    //   133: dup
    //   134: invokespecial 149	android/graphics/BitmapFactory$Options:<init>	()V
    //   137: putfield 46	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   140: aload_0
    //   141: getfield 46	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   144: iconst_1
    //   145: putfield 169	android/graphics/BitmapFactory$Options:inMutable	Z
    //   148: aload_0
    //   149: getfield 46	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   152: iload_3
    //   153: putfield 172	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   156: aload_0
    //   157: getfield 46	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   160: astore 8
    //   162: aload 7
    //   164: monitorexit
    //   165: aload_0
    //   166: aload_1
    //   167: aload 8
    //   169: invokespecial 155	android/support/v4/print/PrintHelperKitkat:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   172: astore 7
    //   174: aload_0
    //   175: getfield 48	android/support/v4/print/PrintHelperKitkat:mLock	Ljava/lang/Object;
    //   178: astore_1
    //   179: aload_1
    //   180: monitorenter
    //   181: aload_0
    //   182: aconst_null
    //   183: putfield 46	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   186: aload_1
    //   187: monitorexit
    //   188: aload 7
    //   190: areturn
    //   191: astore 7
    //   193: aload_1
    //   194: monitorexit
    //   195: aload 7
    //   197: athrow
    //   198: astore_1
    //   199: aload 7
    //   201: monitorexit
    //   202: aload_1
    //   203: athrow
    //   204: astore 7
    //   206: aload_0
    //   207: getfield 48	android/support/v4/print/PrintHelperKitkat:mLock	Ljava/lang/Object;
    //   210: astore_1
    //   211: aload_1
    //   212: monitorenter
    //   213: aload_0
    //   214: aconst_null
    //   215: putfield 46	android/support/v4/print/PrintHelperKitkat:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   218: aload_1
    //   219: monitorexit
    //   220: aload 7
    //   222: athrow
    //   223: astore 7
    //   225: aload_1
    //   226: monitorexit
    //   227: aload 7
    //   229: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   181	188	191	finally
    //   193	195	191	finally
    //   129	165	198	finally
    //   199	202	198	finally
    //   165	174	204	finally
    //   213	220	223	finally
    //   225	227	223	finally
  }

  public int getColorMode()
  {
    return this.mColorMode;
  }

  public int getOrientation()
  {
    return this.mOrientation;
  }

  public int getScaleMode()
  {
    return this.mScaleMode;
  }

  public void printBitmap(final String paramString, final Bitmap paramBitmap, final OnPrintFinishCallback paramOnPrintFinishCallback)
  {
    if (paramBitmap == null)
      return;
    final int i = this.mScaleMode;
    PrintManager localPrintManager = (PrintManager)this.mContext.getSystemService("print");
    Object localObject = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
    if (paramBitmap.getWidth() > paramBitmap.getHeight())
      localObject = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
    localObject = new PrintAttributes.Builder().setMediaSize((PrintAttributes.MediaSize)localObject).setColorMode(this.mColorMode).build();
    localPrintManager.print(paramString, new PrintDocumentAdapter()
    {
      private PrintAttributes mAttributes;

      public void onFinish()
      {
        if (paramOnPrintFinishCallback != null)
          paramOnPrintFinishCallback.onFinish();
      }

      public void onLayout(PrintAttributes paramAnonymousPrintAttributes1, PrintAttributes paramAnonymousPrintAttributes2, CancellationSignal paramAnonymousCancellationSignal, PrintDocumentAdapter.LayoutResultCallback paramAnonymousLayoutResultCallback, Bundle paramAnonymousBundle)
      {
        boolean bool = true;
        this.mAttributes = paramAnonymousPrintAttributes2;
        paramAnonymousCancellationSignal = new PrintDocumentInfo.Builder(paramString).setContentType(1).setPageCount(1).build();
        if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1));
        while (true)
        {
          paramAnonymousLayoutResultCallback.onLayoutFinished(paramAnonymousCancellationSignal, bool);
          return;
          bool = false;
        }
      }

      // ERROR //
      public void onWrite(android.print.PageRange[] paramAnonymousArrayOfPageRange, android.os.ParcelFileDescriptor paramAnonymousParcelFileDescriptor, CancellationSignal paramAnonymousCancellationSignal, android.print.PrintDocumentAdapter.WriteResultCallback paramAnonymousWriteResultCallback)
      {
        // Byte code:
        //   0: new 80	android/print/pdf/PrintedPdfDocument
        //   3: dup
        //   4: aload_0
        //   5: getfield 25	android/support/v4/print/PrintHelperKitkat$1:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   8: getfield 84	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
        //   11: aload_0
        //   12: getfield 46	android/support/v4/print/PrintHelperKitkat$1:mAttributes	Landroid/print/PrintAttributes;
        //   15: invokespecial 87	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //   18: astore_1
        //   19: aload_1
        //   20: iconst_1
        //   21: invokevirtual 91	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
        //   24: astore_3
        //   25: new 93	android/graphics/RectF
        //   28: dup
        //   29: aload_3
        //   30: invokevirtual 99	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //   33: invokevirtual 105	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
        //   36: invokespecial 108	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
        //   39: astore 5
        //   41: aload_0
        //   42: getfield 25	android/support/v4/print/PrintHelperKitkat$1:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   45: aload_0
        //   46: getfield 29	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   49: invokevirtual 114	android/graphics/Bitmap:getWidth	()I
        //   52: aload_0
        //   53: getfield 29	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   56: invokevirtual 117	android/graphics/Bitmap:getHeight	()I
        //   59: aload 5
        //   61: aload_0
        //   62: getfield 31	android/support/v4/print/PrintHelperKitkat$1:val$fittingMode	I
        //   65: invokestatic 121	android/support/v4/print/PrintHelperKitkat:access$000	(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //   68: astore 5
        //   70: aload_3
        //   71: invokevirtual 125	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
        //   74: aload_0
        //   75: getfield 29	android/support/v4/print/PrintHelperKitkat$1:val$bitmap	Landroid/graphics/Bitmap;
        //   78: aload 5
        //   80: aconst_null
        //   81: invokevirtual 131	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   84: aload_1
        //   85: aload_3
        //   86: invokevirtual 135	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   89: aload_1
        //   90: new 137	java/io/FileOutputStream
        //   93: dup
        //   94: aload_2
        //   95: invokevirtual 143	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
        //   98: invokespecial 146	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
        //   101: invokevirtual 150	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
        //   104: aload 4
        //   106: iconst_1
        //   107: anewarray 152	android/print/PageRange
        //   110: dup
        //   111: iconst_0
        //   112: getstatic 156	android/print/PageRange:ALL_PAGES	Landroid/print/PageRange;
        //   115: aastore
        //   116: invokevirtual 162	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFinished	([Landroid/print/PageRange;)V
        //   119: aload_1
        //   120: ifnull +7 -> 127
        //   123: aload_1
        //   124: invokevirtual 165	android/print/pdf/PrintedPdfDocument:close	()V
        //   127: aload_2
        //   128: ifnull +7 -> 135
        //   131: aload_2
        //   132: invokevirtual 166	android/os/ParcelFileDescriptor:close	()V
        //   135: return
        //   136: astore_3
        //   137: ldc 168
        //   139: ldc 170
        //   141: aload_3
        //   142: invokestatic 176	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   145: pop
        //   146: aload 4
        //   148: aconst_null
        //   149: invokevirtual 180	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFailed	(Ljava/lang/CharSequence;)V
        //   152: goto -33 -> 119
        //   155: astore_3
        //   156: aload_1
        //   157: ifnull +7 -> 164
        //   160: aload_1
        //   161: invokevirtual 165	android/print/pdf/PrintedPdfDocument:close	()V
        //   164: aload_2
        //   165: ifnull +7 -> 172
        //   168: aload_2
        //   169: invokevirtual 166	android/os/ParcelFileDescriptor:close	()V
        //   172: aload_3
        //   173: athrow
        //   174: astore_1
        //   175: return
        //   176: astore_1
        //   177: goto -5 -> 172
        //
        // Exception table:
        //   from	to	target	type
        //   89	119	136	java/io/IOException
        //   19	89	155	finally
        //   89	119	155	finally
        //   137	152	155	finally
        //   131	135	174	java/io/IOException
        //   168	172	176	java/io/IOException
      }
    }
    , (PrintAttributes)localObject);
  }

  public void printBitmap(final String paramString, final Uri paramUri, final OnPrintFinishCallback paramOnPrintFinishCallback)
    throws FileNotFoundException
  {
    paramUri = new PrintDocumentAdapter()
    {
      AsyncTask<Uri, Boolean, Bitmap> loadBitmap;
      private PrintAttributes mAttributes;
      Bitmap mBitmap = null;

      private void cancelLoad()
      {
        synchronized (PrintHelperKitkat.this.mLock)
        {
          if (PrintHelperKitkat.this.mDecodeOptions != null)
          {
            PrintHelperKitkat.this.mDecodeOptions.requestCancelDecode();
            PrintHelperKitkat.this.mDecodeOptions = null;
          }
          return;
        }
      }

      public void onFinish()
      {
        super.onFinish();
        cancelLoad();
        this.loadBitmap.cancel(true);
        if (paramOnPrintFinishCallback != null)
          paramOnPrintFinishCallback.onFinish();
      }

      public void onLayout(final PrintAttributes paramAnonymousPrintAttributes1, final PrintAttributes paramAnonymousPrintAttributes2, final CancellationSignal paramAnonymousCancellationSignal, final PrintDocumentAdapter.LayoutResultCallback paramAnonymousLayoutResultCallback, Bundle paramAnonymousBundle)
      {
        boolean bool = true;
        if (paramAnonymousCancellationSignal.isCanceled())
        {
          paramAnonymousLayoutResultCallback.onLayoutCancelled();
          this.mAttributes = paramAnonymousPrintAttributes2;
          return;
        }
        if (this.mBitmap != null)
        {
          paramAnonymousCancellationSignal = new PrintDocumentInfo.Builder(paramString).setContentType(1).setPageCount(1).build();
          if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1));
          while (true)
          {
            paramAnonymousLayoutResultCallback.onLayoutFinished(paramAnonymousCancellationSignal, bool);
            return;
            bool = false;
          }
        }
        this.loadBitmap = new AsyncTask()
        {
          protected Bitmap doInBackground(Uri[] paramAnonymous2ArrayOfUri)
          {
            try
            {
              paramAnonymous2ArrayOfUri = PrintHelperKitkat.this.loadConstrainedBitmap(PrintHelperKitkat.2.this.val$imageFile, 3500);
              return paramAnonymous2ArrayOfUri;
            }
            catch (FileNotFoundException paramAnonymous2ArrayOfUri)
            {
            }
            return null;
          }

          protected void onCancelled(Bitmap paramAnonymous2Bitmap)
          {
            paramAnonymousLayoutResultCallback.onLayoutCancelled();
          }

          protected void onPostExecute(Bitmap paramAnonymous2Bitmap)
          {
            boolean bool = true;
            super.onPostExecute(paramAnonymous2Bitmap);
            PrintHelperKitkat.2.this.mBitmap = paramAnonymous2Bitmap;
            if (paramAnonymous2Bitmap != null)
            {
              paramAnonymous2Bitmap = new PrintDocumentInfo.Builder(PrintHelperKitkat.2.this.val$jobName).setContentType(1).setPageCount(1).build();
              if (!paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1));
              while (true)
              {
                paramAnonymousLayoutResultCallback.onLayoutFinished(paramAnonymous2Bitmap, bool);
                return;
                bool = false;
              }
            }
            paramAnonymousLayoutResultCallback.onLayoutFailed(null);
          }

          protected void onPreExecute()
          {
            paramAnonymousCancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener()
            {
              public void onCancel()
              {
                PrintHelperKitkat.2.this.cancelLoad();
                PrintHelperKitkat.2.1.this.cancel(false);
              }
            });
          }
        };
        this.loadBitmap.execute(new Uri[0]);
        this.mAttributes = paramAnonymousPrintAttributes2;
      }

      // ERROR //
      public void onWrite(android.print.PageRange[] paramAnonymousArrayOfPageRange, android.os.ParcelFileDescriptor paramAnonymousParcelFileDescriptor, CancellationSignal paramAnonymousCancellationSignal, android.print.PrintDocumentAdapter.WriteResultCallback paramAnonymousWriteResultCallback)
      {
        // Byte code:
        //   0: new 136	android/print/pdf/PrintedPdfDocument
        //   3: dup
        //   4: aload_0
        //   5: getfield 34	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   8: getfield 140	android/support/v4/print/PrintHelperKitkat:mContext	Landroid/content/Context;
        //   11: aload_0
        //   12: getfield 95	android/support/v4/print/PrintHelperKitkat$2:mAttributes	Landroid/print/PrintAttributes;
        //   15: invokespecial 143	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //   18: astore_1
        //   19: aload_1
        //   20: iconst_1
        //   21: invokevirtual 147	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
        //   24: astore_3
        //   25: new 149	android/graphics/RectF
        //   28: dup
        //   29: aload_3
        //   30: invokevirtual 155	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //   33: invokevirtual 161	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
        //   36: invokespecial 164	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
        //   39: astore 5
        //   41: aload_0
        //   42: getfield 34	android/support/v4/print/PrintHelperKitkat$2:this$0	Landroid/support/v4/print/PrintHelperKitkat;
        //   45: aload_0
        //   46: getfield 47	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   49: invokevirtual 170	android/graphics/Bitmap:getWidth	()I
        //   52: aload_0
        //   53: getfield 47	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   56: invokevirtual 173	android/graphics/Bitmap:getHeight	()I
        //   59: aload 5
        //   61: aload_0
        //   62: getfield 42	android/support/v4/print/PrintHelperKitkat$2:val$fittingMode	I
        //   65: invokestatic 177	android/support/v4/print/PrintHelperKitkat:access$000	(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //   68: astore 5
        //   70: aload_3
        //   71: invokevirtual 181	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
        //   74: aload_0
        //   75: getfield 47	android/support/v4/print/PrintHelperKitkat$2:mBitmap	Landroid/graphics/Bitmap;
        //   78: aload 5
        //   80: aconst_null
        //   81: invokevirtual 187	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   84: aload_1
        //   85: aload_3
        //   86: invokevirtual 191	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   89: aload_1
        //   90: new 193	java/io/FileOutputStream
        //   93: dup
        //   94: aload_2
        //   95: invokevirtual 199	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
        //   98: invokespecial 202	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
        //   101: invokevirtual 206	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
        //   104: aload 4
        //   106: iconst_1
        //   107: anewarray 208	android/print/PageRange
        //   110: dup
        //   111: iconst_0
        //   112: getstatic 212	android/print/PageRange:ALL_PAGES	Landroid/print/PageRange;
        //   115: aastore
        //   116: invokevirtual 218	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFinished	([Landroid/print/PageRange;)V
        //   119: aload_1
        //   120: ifnull +7 -> 127
        //   123: aload_1
        //   124: invokevirtual 221	android/print/pdf/PrintedPdfDocument:close	()V
        //   127: aload_2
        //   128: ifnull +7 -> 135
        //   131: aload_2
        //   132: invokevirtual 222	android/os/ParcelFileDescriptor:close	()V
        //   135: return
        //   136: astore_3
        //   137: ldc 224
        //   139: ldc 226
        //   141: aload_3
        //   142: invokestatic 232	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   145: pop
        //   146: aload 4
        //   148: aconst_null
        //   149: invokevirtual 236	android/print/PrintDocumentAdapter$WriteResultCallback:onWriteFailed	(Ljava/lang/CharSequence;)V
        //   152: goto -33 -> 119
        //   155: astore_3
        //   156: aload_1
        //   157: ifnull +7 -> 164
        //   160: aload_1
        //   161: invokevirtual 221	android/print/pdf/PrintedPdfDocument:close	()V
        //   164: aload_2
        //   165: ifnull +7 -> 172
        //   168: aload_2
        //   169: invokevirtual 222	android/os/ParcelFileDescriptor:close	()V
        //   172: aload_3
        //   173: athrow
        //   174: astore_1
        //   175: return
        //   176: astore_1
        //   177: goto -5 -> 172
        //
        // Exception table:
        //   from	to	target	type
        //   89	119	136	java/io/IOException
        //   19	89	155	finally
        //   89	119	155	finally
        //   137	152	155	finally
        //   131	135	174	java/io/IOException
        //   168	172	176	java/io/IOException
      }
    };
    paramOnPrintFinishCallback = (PrintManager)this.mContext.getSystemService("print");
    PrintAttributes.Builder localBuilder = new PrintAttributes.Builder();
    localBuilder.setColorMode(this.mColorMode);
    if (this.mOrientation == 1)
      localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
    while (true)
    {
      paramOnPrintFinishCallback.print(paramString, paramUri, localBuilder.build());
      return;
      if (this.mOrientation == 2)
        localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
    }
  }

  public void setColorMode(int paramInt)
  {
    this.mColorMode = paramInt;
  }

  public void setOrientation(int paramInt)
  {
    this.mOrientation = paramInt;
  }

  public void setScaleMode(int paramInt)
  {
    this.mScaleMode = paramInt;
  }

  public static abstract interface OnPrintFinishCallback
  {
    public abstract void onFinish();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.print.PrintHelperKitkat
 * JD-Core Version:    0.6.2
 */