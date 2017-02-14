package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import com.mob.tools.utils.Ln;
import java.io.File;

public class WXAppExtendObject
  implements WXMediaMessage.IMediaObject
{
  public String extInfo;
  public byte[] fileData;
  public String filePath;

  public WXAppExtendObject()
  {
  }

  public WXAppExtendObject(String paramString1, String paramString2)
  {
    this.extInfo = paramString1;
    this.filePath = paramString2;
  }

  public WXAppExtendObject(String paramString, byte[] paramArrayOfByte)
  {
    this.extInfo = paramString;
    this.fileData = paramArrayOfByte;
  }

  public boolean checkArgs()
  {
    if (((this.extInfo == null) || (this.extInfo.length() == 0)) && ((this.filePath == null) || (this.filePath.length() == 0)) && ((this.fileData == null) || (this.fileData.length == 0)))
    {
      Ln.e("checkArgs fail, all arguments is null", new Object[0]);
      return false;
    }
    if ((this.extInfo != null) && (this.extInfo.length() > 2048))
    {
      Ln.e("checkArgs fail, extInfo is invalid", new Object[0]);
      return false;
    }
    if ((this.filePath != null) && (this.filePath.length() > 10240))
    {
      Ln.e("checkArgs fail, filePath is invalid", new Object[0]);
      return false;
    }
    if ((this.filePath != null) && (new File(this.filePath).length() > 10485760L))
    {
      Ln.e("checkArgs fail, fileSize is too large", new Object[0]);
      return false;
    }
    if ((this.fileData != null) && (this.fileData.length > 10485760))
    {
      Ln.e("checkArgs fail, fileData is too large", new Object[0]);
      return false;
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putString("_wxappextendobject_extInfo", this.extInfo);
    paramBundle.putByteArray("_wxappextendobject_fileData", this.fileData);
    paramBundle.putString("_wxappextendobject_filePath", this.filePath);
  }

  public int type()
  {
    return 7;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.extInfo = paramBundle.getString("_wxappextendobject_extInfo");
    this.fileData = paramBundle.getByteArray("_wxappextendobject_fileData");
    this.filePath = paramBundle.getString("_wxappextendobject_filePath");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.WXAppExtendObject
 * JD-Core Version:    0.6.2
 */