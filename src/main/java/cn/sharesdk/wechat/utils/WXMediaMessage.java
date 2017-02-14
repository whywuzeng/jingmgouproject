package cn.sharesdk.wechat.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import com.mob.tools.utils.Ln;
import java.io.ByteArrayOutputStream;

public final class WXMediaMessage
{
  public static final String ACTION_WXAPPMESSAGE = "com.tencent.mm.sdk.openapi.Intent.ACTION_WXAPPMESSAGE";
  public String description;
  public IMediaObject mediaObject;
  public int sdkVer;
  public byte[] thumbData;
  public String title;

  public WXMediaMessage()
  {
    this(null);
  }

  public WXMediaMessage(IMediaObject paramIMediaObject)
  {
    this.mediaObject = paramIMediaObject;
  }

  public final int getType()
  {
    if (this.mediaObject == null)
      return 0;
    return this.mediaObject.type();
  }

  public final void setThumbImage(Bitmap paramBitmap)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 85, localByteArrayOutputStream);
      this.thumbData = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.close();
      return;
    }
    catch (Exception paramBitmap)
    {
      Ln.e(paramBitmap);
      Ln.e("put thumb failed", new Object[0]);
    }
  }

  public static abstract interface IMediaObject
  {
    public static final int TYPE_APPDATA = 7;
    public static final int TYPE_EMOJI = 8;
    public static final int TYPE_FILE = 6;
    public static final int TYPE_IMAGE = 2;
    public static final int TYPE_MUSIC = 3;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_URL = 5;
    public static final int TYPE_VIDEO = 4;

    public abstract boolean checkArgs();

    public abstract void serialize(Bundle paramBundle);

    public abstract int type();

    public abstract void unserialize(Bundle paramBundle);
  }

  public static class a
  {
    public static Bundle a(WXMediaMessage paramWXMediaMessage)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("_wxobject_sdkVer", paramWXMediaMessage.sdkVer);
      localBundle.putString("_wxobject_title", paramWXMediaMessage.title);
      localBundle.putString("_wxobject_description", paramWXMediaMessage.description);
      localBundle.putByteArray("_wxobject_thumbdata", paramWXMediaMessage.thumbData);
      if (paramWXMediaMessage.mediaObject != null)
      {
        localBundle.putString("_wxobject_identifier_", "com.tencent.mm.sdk.openapi." + paramWXMediaMessage.mediaObject.getClass().getSimpleName());
        paramWXMediaMessage.mediaObject.serialize(localBundle);
      }
      return localBundle;
    }

    public static WXMediaMessage a(Bundle paramBundle)
    {
      WXMediaMessage localWXMediaMessage = new WXMediaMessage();
      localWXMediaMessage.sdkVer = paramBundle.getInt("_wxobject_sdkVer");
      localWXMediaMessage.title = paramBundle.getString("_wxobject_title");
      localWXMediaMessage.description = paramBundle.getString("_wxobject_description");
      localWXMediaMessage.thumbData = paramBundle.getByteArray("_wxobject_thumbdata");
      String str2 = paramBundle.getString("_wxobject_identifier_");
      if ((str2 == null) || (str2.length() <= 0))
        return localWXMediaMessage;
      String str1 = str2;
      try
      {
        str2 = str2.replace("com.tencent.mm.sdk.openapi", "cn.sharesdk.wechat.utils");
        str1 = str2;
        localWXMediaMessage.mediaObject = ((WXMediaMessage.IMediaObject)Class.forName(str2).newInstance());
        str1 = str2;
        localWXMediaMessage.mediaObject.unserialize(paramBundle);
        return localWXMediaMessage;
      }
      catch (Exception paramBundle)
      {
        Ln.e(paramBundle);
        Ln.e("get media object from bundle failed: unknown ident " + str1, new Object[0]);
      }
      return localWXMediaMessage;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.WXMediaMessage
 * JD-Core Version:    0.6.2
 */