package cn.sharesdk.wechat.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import com.mob.tools.utils.Ln;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class WXImageObject
  implements WXMediaMessage.IMediaObject
{
  public byte[] imageData;
  public String imagePath;
  public String imageUrl;

  public WXImageObject()
  {
  }

  public WXImageObject(Bitmap paramBitmap)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 85, localByteArrayOutputStream);
      this.imageData = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.close();
      return;
    }
    catch (Exception paramBitmap)
    {
      Ln.e(paramBitmap);
    }
  }

  public WXImageObject(byte[] paramArrayOfByte)
  {
    this.imageData = paramArrayOfByte;
  }

  public boolean checkArgs()
  {
    if (((this.imageData == null) || (this.imageData.length == 0)) && ((this.imagePath == null) || (this.imagePath.length() == 0)) && ((this.imageUrl == null) || (this.imageUrl.length() == 0)))
    {
      Ln.e("checkArgs fail, all arguments are null", new Object[0]);
      return false;
    }
    if ((this.imageData != null) && (this.imageData.length > 10485760))
    {
      Ln.e("checkArgs fail, content is too large", new Object[0]);
      return false;
    }
    if ((this.imagePath != null) && (this.imagePath.length() > 10240))
    {
      Ln.e("checkArgs fail, path is invalid", new Object[0]);
      return false;
    }
    if ((this.imagePath != null) && (new File(this.imagePath).length() > 10485760L))
    {
      Ln.e("checkArgs fail, image content is too large", new Object[0]);
      return false;
    }
    if ((this.imageUrl != null) && (this.imageUrl.length() > 10240))
    {
      Ln.e("checkArgs fail, url is invalid", new Object[0]);
      return false;
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putByteArray("_wximageobject_imageData", this.imageData);
    paramBundle.putString("_wximageobject_imagePath", this.imagePath);
    paramBundle.putString("_wximageobject_imageUrl", this.imageUrl);
  }

  public int type()
  {
    return 2;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.imageData = paramBundle.getByteArray("_wximageobject_imageData");
    this.imagePath = paramBundle.getString("_wximageobject_imagePath");
    this.imageUrl = paramBundle.getString("_wximageobject_imageUrl");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.WXImageObject
 * JD-Core Version:    0.6.2
 */