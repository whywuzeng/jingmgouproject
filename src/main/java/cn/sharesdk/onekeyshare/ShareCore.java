package cn.sharesdk.onekeyshare;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.utils.R;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class ShareCore
{
  private ShareContentCustomizeCallback customizeCallback;

  public static boolean canAuthorize(Context paramContext, String paramString)
  {
    return ((!"WechatMoments".equals(paramString)) && (!"WechatFavorite".equals(paramString)) && (!"ShortMessage".equals(paramString)) && (!"Email".equals(paramString)) && (!"Pinterest".equals(paramString)) && (!"Yixin".equals(paramString)) && (!"YixinMoments".equals(paramString)) && (!"Line".equals(paramString)) && (!"Bluetooth".equals(paramString)) && (!"WhatsApp".equals(paramString)) && (!"BaiduTieba".equals(paramString))) || ("Laiwang".equals(paramString)) || ("LaiwangMoments".equals(paramString));
  }

  public static boolean canGetUserInfo(Context paramContext, String paramString)
  {
    return (!"WechatMoments".equals(paramString)) && (!"WechatFavorite".equals(paramString)) && (!"ShortMessage".equals(paramString)) && (!"Email".equals(paramString)) && (!"Pinterest".equals(paramString)) && (!"Yixin".equals(paramString)) && (!"YixinMoments".equals(paramString)) && (!"Line".equals(paramString)) && (!"Bluetooth".equals(paramString)) && (!"WhatsApp".equals(paramString)) && (!"Pocket".equals(paramString)) && (!"BaiduTieba".equals(paramString)) && (!"Laiwang".equals(paramString)) && (!"LaiwangMoments".equals(paramString));
  }

  public static boolean isDirectShare(Platform paramPlatform)
  {
    return ((paramPlatform instanceof CustomPlatform)) || (isUseClientToShare(paramPlatform.getName()));
  }

  public static boolean isUseClientToShare(String paramString)
  {
    if (("Wechat".equals(paramString)) || ("WechatMoments".equals(paramString)) || ("WechatFavorite".equals(paramString)) || ("ShortMessage".equals(paramString)) || ("Email".equals(paramString)) || ("GooglePlus".equals(paramString)) || ("QQ".equals(paramString)) || ("Pinterest".equals(paramString)) || ("Instagram".equals(paramString)) || ("Yixin".equals(paramString)) || ("YixinMoments".equals(paramString)) || ("QZone".equals(paramString)) || ("Mingdao".equals(paramString)) || ("Line".equals(paramString)) || ("KakaoStory".equals(paramString)) || ("KakaoTalk".equals(paramString)) || ("Bluetooth".equals(paramString)) || ("WhatsApp".equals(paramString)) || ("BaiduTieba".equals(paramString)) || ("Laiwang".equals(paramString)) || ("LaiwangMoments".equals(paramString)));
    Intent localIntent;
    do
    {
      do
      {
        return true;
        if (!"Evernote".equals(paramString))
          break;
      }
      while ("true".equals(ShareSDK.getPlatform(paramString).getDevinfo("ShareByAppClient")));
      do
      {
        do
          return false;
        while (!"SinaWeibo".equals(paramString));
        paramString = ShareSDK.getPlatform(paramString);
      }
      while (!"true".equals(paramString.getDevinfo("ShareByAppClient")));
      localIntent = new Intent("android.intent.action.SEND");
      localIntent.setPackage("com.sina.weibo");
      localIntent.setType("image/*");
    }
    while (paramString.getContext().getPackageManager().resolveActivity(localIntent, 0) != null);
    return false;
  }

  public void setShareContentCustomizeCallback(ShareContentCustomizeCallback paramShareContentCustomizeCallback)
  {
    this.customizeCallback = paramShareContentCustomizeCallback;
  }

  public boolean share(Platform paramPlatform, HashMap<String, Object> paramHashMap)
  {
    if ((paramPlatform == null) || (paramHashMap == null))
      return false;
    try
    {
      Object localObject = (String)paramHashMap.get("imagePath");
      Bitmap localBitmap = (Bitmap)paramHashMap.get("viewToShare");
      if ((TextUtils.isEmpty((CharSequence)localObject)) && (localBitmap != null) && (!localBitmap.isRecycled()))
      {
        localObject = new File(R.getCachePath(paramPlatform.getContext(), "screenshot"), String.valueOf(System.currentTimeMillis()) + ".jpg");
        FileOutputStream localFileOutputStream = new FileOutputStream((File)localObject);
        localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
        localFileOutputStream.flush();
        localFileOutputStream.close();
        paramHashMap.put("imagePath", ((File)localObject).getAbsolutePath());
      }
      paramHashMap = new Platform.ShareParams(paramHashMap);
      if (this.customizeCallback != null)
        this.customizeCallback.onShare(paramPlatform, paramHashMap);
      paramPlatform.share(paramHashMap);
      return true;
    }
    catch (Throwable paramPlatform)
    {
      paramPlatform.printStackTrace();
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.ShareCore
 * JD-Core Version:    0.6.2
 */