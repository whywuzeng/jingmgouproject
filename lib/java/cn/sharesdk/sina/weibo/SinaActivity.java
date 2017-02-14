package cn.sharesdk.sina.weibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import com.mob.tools.utils.UIHandler;
import com.sina.weibo.sdk.api.BaseMediaObject;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.Utility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SinaActivity extends Activity
  implements Handler.Callback, IWeiboHandler.Response, WeiboAuthListener
{
  private static Platform.ShareParams f;
  private static AuthorizeListener g;
  private String a;
  private long b;
  private SsoHandler c;
  private AuthInfo d;
  private IWeiboShareAPI e;

  private int a(Bitmap paramBitmap, Bitmap.CompressFormat paramCompressFormat)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(paramCompressFormat, 100, localByteArrayOutputStream);
    int i = localByteArrayOutputStream.size();
    localByteArrayOutputStream.close();
    return i;
  }

  private Bitmap a(Context paramContext, Bitmap paramBitmap)
  {
    try
    {
      File localFile = File.createTempFile("bm_tmp", ".jpg");
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      paramContext = a(paramContext, localFile.getAbsolutePath());
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      Ln.e(paramContext);
    }
    return null;
  }

  private Bitmap a(Context paramContext, String paramString)
  {
    File localFile = new File(paramString);
    if (!localFile.exists())
      throw new FileNotFoundException();
    Bitmap.CompressFormat localCompressFormat = BitmapHelper.getBmpFormat(paramString);
    int i = R.dipToPx(paramContext, 120);
    if (Bitmap.CompressFormat.PNG == localCompressFormat)
      i = R.dipToPx(paramContext, 90);
    paramContext = BitmapFactory.decodeFile(paramString, new BitmapFactory.Options());
    if (localFile.length() > this.b)
    {
      if ((i > 40) && (a(paramContext, localCompressFormat) > 32768))
      {
        i -= 5;
        int j = paramContext.getWidth();
        int k = paramContext.getHeight();
        double d1;
        if ((k <= i) && (j <= i))
          d1 = 1.0D;
        while (true)
        {
          paramContext = Bitmap.createScaledBitmap(paramContext, (int)(j * d1), (int)(d1 * k), true);
          break;
          if (k > j)
            d1 = i / k;
          else
            d1 = i / j;
        }
      }
      paramString = new FileOutputStream(File.createTempFile("sina_bm_tmp", "." + localCompressFormat.name().toLowerCase()));
      paramContext.compress(localCompressFormat, 100, paramString);
      paramString.flush();
      paramString.close();
      return paramContext;
    }
    Ln.i("sina weibo decode bitmap size ==>>" + a(paramContext, localCompressFormat), new Object[0]);
    return paramContext;
  }

  private void a()
  {
    WeiboMultiMessage localWeiboMultiMessage = new WeiboMultiMessage();
    if (!TextUtils.isEmpty(f.getText()))
      localWeiboMultiMessage.textObject = d();
    if ((f.getImageData() != null) || (!TextUtils.isEmpty(f.getImagePath())))
      localWeiboMultiMessage.imageObject = c();
    if (!TextUtils.isEmpty(f.getUrl()))
      localWeiboMultiMessage.mediaObject = b();
    SendMultiMessageToWeiboRequest localSendMultiMessageToWeiboRequest = new SendMultiMessageToWeiboRequest();
    localSendMultiMessageToWeiboRequest.transaction = String.valueOf(System.currentTimeMillis());
    localSendMultiMessageToWeiboRequest.multiMessage = localWeiboMultiMessage;
    this.e.sendRequest(this, localSendMultiMessageToWeiboRequest, this.d, this.a, this);
  }

  public static void a(Platform.ShareParams paramShareParams)
  {
    f = paramShareParams;
  }

  public static void a(AuthorizeListener paramAuthorizeListener)
  {
    g = paramAuthorizeListener;
  }

  private BaseMediaObject b()
  {
    WebpageObject localWebpageObject = new WebpageObject();
    localWebpageObject.identify = Utility.generateGUID();
    localWebpageObject.title = f.getTitle();
    localWebpageObject.description = f.getText();
    try
    {
      this.b = 32768L;
      if (f.getImageData() != null)
        localWebpageObject.setThumbImage(a(this, f.getImageData()));
      while (true)
      {
        localWebpageObject.actionUrl = f.getUrl();
        localWebpageObject.defaultText = f.getText();
        return localWebpageObject;
        if (!TextUtils.isEmpty(f.getImagePath()))
          localWebpageObject.setThumbImage(a(this, f.getImagePath()));
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Ln.e(localThrowable);
        localWebpageObject.setThumbImage(null);
      }
    }
  }

  private ImageObject c()
  {
    ImageObject localImageObject3 = new ImageObject();
    try
    {
      if (f.getImageData() != null)
      {
        this.b = 2097152L;
        localImageObject3.setImageObject(a(this, f.getImageData()));
        return localImageObject3;
      }
      ImageObject localImageObject1 = localImageObject3;
      if (!TextUtils.isEmpty(f.getImagePath()))
      {
        this.b = 10485760L;
        localImageObject3.setImageObject(a(this, f.getImagePath()));
        return localImageObject3;
      }
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
      ImageObject localImageObject2 = null;
      return localImageObject2;
    }
  }

  private TextObject d()
  {
    TextObject localTextObject = new TextObject();
    localTextObject.text = f.getText();
    return localTextObject;
  }

  private void e()
  {
    UIHandler.sendEmptyMessageDelayed(1, 200L, this);
  }

  public boolean handleMessage(Message paramMessage)
  {
    if (paramMessage.what == 1)
      finish();
    return false;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (this.c != null)
      this.c.authorizeCallBack(paramInt1, paramInt2, paramIntent);
    e();
  }

  public void onCancel()
  {
    e();
    if (g != null)
      g.onCancel();
  }

  public void onComplete(Bundle paramBundle)
  {
    e();
    if (g != null)
      g.onComplete(paramBundle);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    int i = localBundle.getInt("action");
    String str2 = localBundle.getString("appkey");
    String str3 = localBundle.getString("redirectUrl");
    String str1 = localBundle.getString("permissions");
    boolean bool = localBundle.getBoolean("isUserClient");
    paramBundle = str1;
    if (str1 == null)
      paramBundle = "";
    this.d = new AuthInfo(this, str2, str3, paramBundle);
    this.c = new SsoHandler(this, this.d);
    if (i == 1)
    {
      if (bool)
      {
        this.c.authorize(this);
        return;
      }
      this.c.authorizeWeb(this);
      return;
    }
    if (i == 2)
    {
      this.a = localBundle.getString("token");
      this.e = WeiboShareSDK.createWeiboAPI(this, str2);
      this.e.registerApp();
      a();
      return;
    }
    e();
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    Ln.i("onNewIntent ==>>", new Object[] { paramIntent.getExtras().toString() });
    this.e.handleWeiboResponse(paramIntent, this);
  }

  public void onResponse(BaseResponse paramBaseResponse)
  {
    e();
    switch (paramBaseResponse.errCode)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    do
    {
      do
      {
        do
          return;
        while (g == null);
        g.onComplete(null);
        return;
      }
      while (g == null);
      g.onCancel();
      return;
    }
    while (g == null);
    g.onError(new Throwable());
  }

  public void onWeiboException(WeiboException paramWeiboException)
  {
    e();
    if (g != null)
      g.onError(paramWeiboException);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.SinaActivity
 * JD-Core Version:    0.6.2
 */