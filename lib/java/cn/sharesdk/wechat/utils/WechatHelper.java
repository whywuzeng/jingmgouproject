package cn.sharesdk.wechat.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import com.mob.tools.utils.UIHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;

public class WechatHelper
{
  private static boolean a = false;
  private static WechatHelper b;
  private j c = new j();
  private k d;

  private int a(Bitmap paramBitmap, Bitmap.CompressFormat paramCompressFormat)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(paramCompressFormat, 100, localByteArrayOutputStream);
    int i = localByteArrayOutputStream.size();
    localByteArrayOutputStream.close();
    return i;
  }

  private static int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int k = paramOptions.outHeight;
    int j = paramOptions.outWidth;
    int i = 1;
    j /= 2;
    k /= 2;
    while ((j / i > paramInt1) || (k / i > paramInt2))
      i *= 2;
    return i + 2;
  }

  private Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    double d1;
    if ((j <= paramInt) && (i <= paramInt))
      d1 = 1.0D;
    while (true)
    {
      return Bitmap.createScaledBitmap(paramBitmap, (int)(i * d1), (int)(d1 * j), true);
      if (j > i)
        d1 = paramInt / j;
      else
        d1 = paramInt / i;
    }
  }

  public static WechatHelper a()
  {
    if (b == null)
      b = new WechatHelper();
    return b;
  }

  private void a(Context paramContext, String paramString1, String paramString2, Bitmap paramBitmap, int paramInt, k paramk)
  {
    WXImageObject localWXImageObject = new WXImageObject();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    localByteArrayOutputStream.flush();
    localByteArrayOutputStream.close();
    localWXImageObject.imageData = localByteArrayOutputStream.toByteArray();
    paramBitmap = new WXMediaMessage();
    paramBitmap.title = paramString1;
    paramBitmap.mediaObject = localWXImageObject;
    paramBitmap.description = paramString2;
    paramBitmap.thumbData = a(paramContext, localWXImageObject.imageData);
    a(paramBitmap, "img", paramInt, paramk);
  }

  private void a(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt, k paramk)
  {
    WXImageObject localWXImageObject = new WXImageObject();
    if (paramString3.startsWith("/data/"))
      localWXImageObject.imageData = a(paramString3);
    while (true)
    {
      WXMediaMessage localWXMediaMessage = new WXMediaMessage();
      localWXMediaMessage.title = paramString1;
      localWXMediaMessage.mediaObject = localWXImageObject;
      localWXMediaMessage.description = paramString2;
      localWXMediaMessage.thumbData = b(paramContext, paramString3);
      a(localWXMediaMessage, "img", paramInt, paramk);
      return;
      localWXImageObject.imagePath = paramString3;
    }
  }

  private void a(Context paramContext, String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, int paramInt, k paramk)
  {
    WXVideoObject localWXVideoObject = new WXVideoObject();
    localWXVideoObject.videoUrl = paramString3;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXVideoObject;
    paramString1 = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramString1);
    paramString1.flush();
    paramString1.close();
    paramString3.thumbData = a(paramContext, paramString1.toByteArray());
    a(paramString3, "video", paramInt, paramk);
  }

  private void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, k paramk)
  {
    WXVideoObject localWXVideoObject = new WXVideoObject();
    localWXVideoObject.videoUrl = paramString3;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXVideoObject;
    paramString3.thumbData = b(paramContext, paramString4);
    a(paramString3, "video", paramInt, paramk);
  }

  private void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, Bitmap paramBitmap, int paramInt, k paramk)
  {
    WXMusicObject localWXMusicObject = new WXMusicObject();
    localWXMusicObject.musicUrl = paramString4;
    localWXMusicObject.musicDataUrl = paramString3;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXMusicObject;
    paramString1 = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramString1);
    paramString1.flush();
    paramString1.close();
    paramString3.thumbData = a(paramContext, paramString1.toByteArray());
    a(paramString3, "music", paramInt, paramk);
  }

  private void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt, k paramk)
  {
    WXMusicObject localWXMusicObject = new WXMusicObject();
    localWXMusicObject.musicUrl = paramString4;
    localWXMusicObject.musicDataUrl = paramString3;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXMusicObject;
    paramString3.thumbData = b(paramContext, paramString5);
    a(paramString3, "music", paramInt, paramk);
  }

  private void a(WXMediaMessage paramWXMediaMessage, String paramString, int paramInt, k paramk)
  {
    Object localObject1 = DeviceHelper.getInstance(paramk.b().getContext());
    String str = ((DeviceHelper)localObject1).getPackageName() + ".wxapi.WXEntryActivity";
    try
    {
      localObject1 = Class.forName(str);
      if ((localObject1 != null) && (!WechatHandlerActivity.class.isAssignableFrom((Class)localObject1)))
      {
        localObject1 = str + " does not extend from " + WechatHandlerActivity.class.getName();
        if (a)
          throw new Throwable((String)localObject1);
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Ln.e(localThrowable);
        localObject2 = null;
      }
      new Throwable((String)localObject2).printStackTrace();
      Object localObject2 = new d();
      ((d)localObject2).c = (paramString + System.currentTimeMillis());
      ((d)localObject2).a = paramWXMediaMessage;
      ((d)localObject2).b = paramInt;
      this.d = paramk;
      this.c.a((m)localObject2);
    }
  }

  private void a(String paramString1, String paramString2, int paramInt, k paramk)
  {
    WXTextObject localWXTextObject = new WXTextObject();
    localWXTextObject.text = paramString2;
    WXMediaMessage localWXMediaMessage = new WXMediaMessage();
    localWXMediaMessage.title = paramString1;
    localWXMediaMessage.mediaObject = localWXTextObject;
    localWXMediaMessage.description = paramString2;
    a(localWXMediaMessage, "text", paramInt, paramk);
  }

  private byte[] a(Context paramContext, Bitmap paramBitmap, Bitmap.CompressFormat paramCompressFormat)
  {
    if (paramBitmap == null)
      throw new RuntimeException("checkArgs fail, thumbData is null");
    if (paramBitmap.isRecycled())
      throw new RuntimeException("checkArgs fail, thumbData is recycled");
    int i = 120;
    while ((i > 40) && (a(paramBitmap, paramCompressFormat) > 32768))
    {
      int j = R.dipToPx(paramContext, i);
      i -= 5;
      paramBitmap = a(paramBitmap, j);
    }
    paramContext = new ByteArrayOutputStream();
    paramBitmap.compress(paramCompressFormat, 100, paramContext);
    paramContext.flush();
    paramContext.close();
    return paramContext.toByteArray();
  }

  private byte[] a(Context paramContext, byte[] paramArrayOfByte)
  {
    Bitmap.CompressFormat localCompressFormat = BitmapHelper.getBmpFormat(paramArrayOfByte);
    int i = R.dipToPx(paramContext, 120);
    if (Bitmap.CompressFormat.PNG == localCompressFormat)
      i = R.dipToPx(paramContext, 90);
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    if (paramArrayOfByte.length > 32768)
    {
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
      localOptions.inSampleSize = a(localOptions, i, i);
    }
    localOptions.inJustDecodeBounds = false;
    return a(paramContext, BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions), localCompressFormat);
  }

  private byte[] a(String paramString)
  {
    try
    {
      paramString = new FileInputStream(paramString);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte[1024];
      for (int i = paramString.read(arrayOfByte); i > 0; i = paramString.read(arrayOfByte))
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      localByteArrayOutputStream.flush();
      paramString.close();
      localByteArrayOutputStream.close();
      paramString = localByteArrayOutputStream.toByteArray();
      return paramString;
    }
    catch (Throwable paramString)
    {
      Ln.e(paramString);
    }
    return null;
  }

  private void b(Context paramContext, String paramString1, String paramString2, Bitmap paramBitmap, int paramInt, k paramk)
  {
    WXEmojiObject localWXEmojiObject = new WXEmojiObject();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    localByteArrayOutputStream.flush();
    localWXEmojiObject.emojiData = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    paramBitmap = new WXMediaMessage();
    paramBitmap.title = paramString1;
    paramBitmap.mediaObject = localWXEmojiObject;
    paramBitmap.description = paramString2;
    paramBitmap.thumbData = a(paramContext, localWXEmojiObject.emojiData);
    a(paramBitmap, "emoji", paramInt, paramk);
  }

  private void b(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt, k paramk)
  {
    WXEmojiObject localWXEmojiObject = new WXEmojiObject();
    localWXEmojiObject.emojiPath = paramString3;
    WXMediaMessage localWXMediaMessage = new WXMediaMessage();
    localWXMediaMessage.title = paramString1;
    localWXMediaMessage.mediaObject = localWXEmojiObject;
    localWXMediaMessage.description = paramString2;
    localWXMediaMessage.thumbData = b(paramContext, paramString3);
    a(localWXMediaMessage, "emoji", paramInt, paramk);
  }

  private void b(Context paramContext, String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, int paramInt, k paramk)
  {
    WXWebpageObject localWXWebpageObject = new WXWebpageObject();
    localWXWebpageObject.webpageUrl = paramString3;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXWebpageObject;
    if ((paramBitmap != null) && (!paramBitmap.isRecycled()))
    {
      paramString1 = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramString1);
      paramString1.flush();
      paramString1.close();
      paramString3.thumbData = a(paramContext, paramString1.toByteArray());
      if (paramString3.thumbData == null)
        throw new RuntimeException("checkArgs fail, thumbData is null");
      if (paramString3.thumbData.length > 32768)
        throw new RuntimeException("checkArgs fail, thumbData is too large: " + paramString3.thumbData.length + " > " + 32768);
    }
    a(paramString3, "webpage", paramInt, paramk);
  }

  private void b(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, k paramk)
  {
    WXWebpageObject localWXWebpageObject = new WXWebpageObject();
    localWXWebpageObject.webpageUrl = paramString3;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXWebpageObject;
    if ((paramString4 != null) && (new File(paramString4).exists()))
    {
      paramString3.thumbData = b(paramContext, paramString4);
      if (paramString3.thumbData == null)
        throw new RuntimeException("checkArgs fail, thumbData is null");
      if (paramString3.thumbData.length > 32768)
        throw new RuntimeException("checkArgs fail, thumbData is too large: " + paramString3.thumbData.length + " > " + 32768);
    }
    a(paramString3, "webpage", paramInt, paramk);
  }

  private void b(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, Bitmap paramBitmap, int paramInt, k paramk)
  {
    WXAppExtendObject localWXAppExtendObject = new WXAppExtendObject();
    localWXAppExtendObject.filePath = paramString3;
    localWXAppExtendObject.extInfo = paramString4;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXAppExtendObject;
    paramString1 = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramString1);
    paramString1.flush();
    paramString1.close();
    paramString3.thumbData = a(paramContext, paramString1.toByteArray());
    a(paramString3, "appdata", paramInt, paramk);
  }

  private void b(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt, k paramk)
  {
    WXAppExtendObject localWXAppExtendObject = new WXAppExtendObject();
    localWXAppExtendObject.filePath = paramString3;
    localWXAppExtendObject.extInfo = paramString4;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXAppExtendObject;
    paramString3.thumbData = b(paramContext, paramString5);
    a(paramString3, "appdata", paramInt, paramk);
  }

  private byte[] b(Context paramContext, String paramString)
  {
    File localFile = new File(paramString);
    if (!localFile.exists())
      throw new FileNotFoundException();
    Bitmap.CompressFormat localCompressFormat = BitmapHelper.getBmpFormat(paramString);
    int i = R.dipToPx(paramContext, 120);
    if (Bitmap.CompressFormat.PNG == localCompressFormat)
      i = R.dipToPx(paramContext, 90);
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    if (localFile.length() > 32768L)
    {
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inSampleSize = a(localOptions, i, i);
    }
    localOptions.inJustDecodeBounds = false;
    paramString = BitmapFactory.decodeFile(paramString, localOptions);
    if (paramString == null)
    {
      localFile.delete();
      throw new RuntimeException("checkArgs fail, thumbData is null");
    }
    return a(paramContext, paramString, localCompressFormat);
  }

  private void c(Context paramContext, String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, int paramInt, k paramk)
  {
    WXFileObject localWXFileObject = new WXFileObject();
    localWXFileObject.filePath = paramString3;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXFileObject;
    paramString1 = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramString1);
    paramString1.flush();
    paramString1.close();
    paramString3.thumbData = a(paramContext, paramString1.toByteArray());
    a(paramString3, "filedata", paramInt, paramk);
  }

  private void c(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, k paramk)
  {
    WXFileObject localWXFileObject = new WXFileObject();
    localWXFileObject.filePath = paramString3;
    paramString3 = new WXMediaMessage();
    paramString3.title = paramString1;
    paramString3.description = paramString2;
    paramString3.mediaObject = localWXFileObject;
    paramString3.thumbData = b(paramContext, paramString4);
    a(paramString3, "filedata", paramInt, paramk);
  }

  public void a(k paramk)
  {
    this.d = paramk;
    paramk = new a();
    paramk.a = "snsapi_userinfo";
    paramk.b = "sharesdk_wechat_auth";
    this.c.a(paramk);
  }

  public void a(k paramk, Platform.ShareParams paramShareParams, PlatformActionListener paramPlatformActionListener)
  {
    Platform localPlatform = paramk.b();
    Object localObject1 = paramShareParams.getImagePath();
    Object localObject2 = paramShareParams.getImageUrl();
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      paramk = (k)localObject1;
      if (new File((String)localObject1).exists());
    }
    else
    {
      paramk = paramShareParams.getImageData();
      if ((paramk == null) || (paramk.isRecycled()))
        break label465;
      localObject1 = new File(R.getCachePath(localPlatform.getContext(), "images"), System.currentTimeMillis() + ".png");
      localObject2 = new FileOutputStream((File)localObject1);
      paramk.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)localObject2);
      ((FileOutputStream)localObject2).flush();
      ((FileOutputStream)localObject2).close();
      paramk = ((File)localObject1).getAbsolutePath();
    }
    Intent localIntent = new Intent("android.intent.action.SEND");
    localObject1 = localPlatform.getShortLintk(paramShareParams.getText(), false);
    localIntent.putExtra("android.intent.extra.TEXT", (String)localObject1);
    localIntent.putExtra("Kdescription", (String)localObject1);
    if (!TextUtils.isEmpty(paramk))
    {
      localObject1 = new File(paramk);
      if (((File)localObject1).exists())
      {
        if (!paramk.startsWith("/data/"))
          break label534;
        localObject2 = new File(R.getCachePath(localPlatform.getContext(), "images"), System.currentTimeMillis() + ((File)localObject1).getName());
        String str = ((File)localObject2).getAbsolutePath();
        ((File)localObject2).createNewFile();
        if (!R.copyFile(paramk, str))
          break label534;
        localObject1 = localObject2;
      }
    }
    label534: 
    while (true)
    {
      localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile((File)localObject1));
      localObject1 = URLConnection.getFileNameMap().getContentTypeFor(paramk);
      if (localObject1 != null)
      {
        paramk = (k)localObject1;
        if (((String)localObject1).length() > 0);
      }
      else
      {
        paramk = "image/*";
      }
      localIntent.setType(paramk);
      label343: if (((Integer)paramShareParams.get("scene", Integer.class)).intValue() == 1);
      for (paramk = "com.tencent.mm.ui.tools.ShareToTimeLineUI"; ; paramk = "com.tencent.mm.ui.tools.ShareImgUI")
      {
        localIntent.setClassName("com.tencent.mm", paramk);
        localIntent.addFlags(268435456);
        localPlatform.getContext().startActivity(localIntent);
        paramk = DeviceHelper.getInstance(localPlatform.getContext());
        localObject1 = localPlatform.getContext().getPackageName();
        localObject2 = new HashMap();
        ((HashMap)localObject2).put("ShareParams", paramShareParams);
        if (!TextUtils.isEmpty(paramk.getTopTaskPackageName()))
          break label509;
        if ((paramPlatformActionListener != null) && (paramPlatformActionListener != null))
          paramPlatformActionListener.onComplete(localPlatform, 9, (HashMap)localObject2);
        return;
        label465: paramk = (k)localObject1;
        if (TextUtils.isEmpty((CharSequence)localObject2))
          break;
        paramk = BitmapHelper.downloadBitmap(localPlatform.getContext(), (String)localObject2);
        break;
        localIntent.setType("text/plain");
        break label343;
      }
      label509: UIHandler.sendEmptyMessageDelayed(0, 2000L, new l(this, paramk, (String)localObject1, paramPlatformActionListener, localPlatform, (HashMap)localObject2));
      return;
    }
  }

  public boolean a(Context paramContext, String paramString)
  {
    return this.c.a(paramContext, paramString);
  }

  public boolean a(WechatHandlerActivity paramWechatHandlerActivity)
  {
    return this.c.a(paramWechatHandlerActivity, this.d);
  }

  public void b(k paramk)
  {
    Platform localPlatform = paramk.b();
    Object localObject1 = paramk.a();
    Object localObject2 = paramk.c();
    int i = ((Platform.ShareParams)localObject1).getShareType();
    String str1 = ((Platform.ShareParams)localObject1).getTitle();
    String str2 = ((Platform.ShareParams)localObject1).getText();
    int j = ((Platform.ShareParams)localObject1).getScence();
    String str3 = ((Platform.ShareParams)localObject1).getImagePath();
    String str4 = ((Platform.ShareParams)localObject1).getImageUrl();
    Bitmap localBitmap = ((Platform.ShareParams)localObject1).getImageData();
    String str5 = ((Platform.ShareParams)localObject1).getMusicUrl();
    String str6 = ((Platform.ShareParams)localObject1).getUrl();
    String str7 = ((Platform.ShareParams)localObject1).getFilePath();
    String str8 = ((Platform.ShareParams)localObject1).getExtInfo();
    localObject1 = localPlatform.getContext();
    switch (i)
    {
    case 3:
    default:
      if (localObject2 != null)
        ((PlatformActionListener)localObject2).onError(localPlatform, 9, new IllegalArgumentException("shareType = " + i));
      return;
    case 1:
      a(str1, str2, j, paramk);
      return;
    case 2:
      if ((str3 != null) && (str3.length() > 0))
      {
        a((Context)localObject1, str1, str2, str3, j, paramk);
        return;
      }
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
      {
        a((Context)localObject1, str1, str2, localBitmap, j, paramk);
        return;
      }
      if ((str4 != null) && (str4.length() > 0))
      {
        a((Context)localObject1, str1, str2, BitmapHelper.downloadBitmap(localPlatform.getContext(), str4), j, paramk);
        return;
      }
      a((Context)localObject1, str1, str2, "", j, paramk);
      return;
    case 5:
      str5 = localPlatform.getShortLintk(str5 + " " + str6, false);
      localObject2 = str5.split(" ")[0];
      str5 = str5.split(" ")[1];
      if ((str3 != null) && (str3.length() > 0))
      {
        a((Context)localObject1, str1, str2, (String)localObject2, str5, str3, j, paramk);
        return;
      }
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
      {
        a((Context)localObject1, str1, str2, (String)localObject2, str5, localBitmap, j, paramk);
        return;
      }
      if ((str4 != null) && (str4.length() > 0))
      {
        a((Context)localObject1, str1, str2, (String)localObject2, str5, BitmapHelper.downloadBitmap(localPlatform.getContext(), str4), j, paramk);
        return;
      }
      a((Context)localObject1, str1, str2, (String)localObject2, str5, "", j, paramk);
      return;
    case 6:
      localObject2 = localPlatform.getShortLintk(str6, false);
      if ((str3 != null) && (str3.length() > 0))
      {
        a((Context)localObject1, str1, str2, (String)localObject2, str3, j, paramk);
        return;
      }
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
      {
        a((Context)localObject1, str1, str2, (String)localObject2, localBitmap, j, paramk);
        return;
      }
      if ((str4 != null) && (str4.length() > 0))
      {
        a((Context)localObject1, str1, str2, (String)localObject2, BitmapHelper.downloadBitmap(localPlatform.getContext(), str4), j, paramk);
        return;
      }
      a((Context)localObject1, str1, str2, (String)localObject2, "", j, paramk);
      return;
    case 4:
      localObject2 = localPlatform.getShortLintk(str6, false);
      if ((str3 != null) && (str3.length() > 0))
      {
        b((Context)localObject1, str1, str2, (String)localObject2, str3, j, paramk);
        return;
      }
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
      {
        b((Context)localObject1, str1, str2, (String)localObject2, localBitmap, j, paramk);
        return;
      }
      if ((str4 != null) && (str4.length() > 0))
      {
        b((Context)localObject1, str1, str2, (String)localObject2, BitmapHelper.downloadBitmap(localPlatform.getContext(), str4), j, paramk);
        return;
      }
      b((Context)localObject1, str1, str2, (String)localObject2, "", j, paramk);
      return;
    case 7:
      if (j == 1)
        throw new Throwable("WechatMoments does not support SAHRE_APP");
      if (j == 2)
        throw new Throwable("WechatFavorite does not support SAHRE_APP");
      if ((str3 != null) && (str3.length() > 0))
      {
        b((Context)localObject1, str1, str2, str7, str8, str3, j, paramk);
        return;
      }
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
      {
        b((Context)localObject1, str1, str2, str7, str8, localBitmap, j, paramk);
        return;
      }
      if ((str4 != null) && (str4.length() > 0))
      {
        b((Context)localObject1, str1, str2, str7, str8, BitmapHelper.downloadBitmap(localPlatform.getContext(), str4), j, paramk);
        return;
      }
      b((Context)localObject1, str1, str2, str7, str8, "", j, paramk);
      return;
    case 8:
      if (j == 1)
        throw new Throwable("WechatMoments does not support SHARE_FILE");
      if ((str3 != null) && (str3.length() > 0))
      {
        c((Context)localObject1, str1, str2, str7, str3, j, paramk);
        return;
      }
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
      {
        c((Context)localObject1, str1, str2, str7, localBitmap, j, paramk);
        return;
      }
      if ((str4 != null) && (str4.length() > 0))
      {
        c((Context)localObject1, str1, str2, str7, BitmapHelper.downloadBitmap(localPlatform.getContext(), str4), j, paramk);
        return;
      }
      c((Context)localObject1, str1, str2, str7, "", j, paramk);
      return;
    case 9:
    }
    if (j == 1)
      throw new Throwable("WechatMoments does not support SHARE_EMOJI");
    if (j == 2)
      throw new Throwable("WechatFavorite does not support SHARE_EMOJI");
    if ((str3 != null) && (str3.length() > 0))
    {
      b((Context)localObject1, str1, str2, str3, j, paramk);
      return;
    }
    if ((str4 != null) && (str4.length() > 0))
    {
      b((Context)localObject1, str1, str2, BitmapHelper.downloadBitmap(localPlatform.getContext(), str4), j, paramk);
      return;
    }
    if ((localBitmap != null) && (!localBitmap.isRecycled()))
    {
      b((Context)localObject1, str1, str2, localBitmap, j, paramk);
      return;
    }
    b((Context)localObject1, str1, str2, "", j, paramk);
  }

  public boolean b()
  {
    return this.c.a();
  }

  public boolean c()
  {
    return this.c.b();
  }

  public boolean d()
  {
    return this.c.c();
  }

  public static class ShareParams extends Platform.ShareParams
  {

    @Deprecated
    public String extInfo;

    @Deprecated
    public String filePath;

    @Deprecated
    public Bitmap imageData;

    @Deprecated
    public String imageUrl;

    @Deprecated
    public String musicUrl;

    @Deprecated
    protected int scene;

    @Deprecated
    public int shareType;

    @Deprecated
    public String title;

    @Deprecated
    public String url;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.WechatHelper
 * JD-Core Version:    0.6.2
 */