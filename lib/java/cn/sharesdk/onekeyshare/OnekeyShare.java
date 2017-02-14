package cn.sharesdk.onekeyshare;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.R;
import com.mob.tools.utils.UIHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class OnekeyShare
  implements PlatformActionListener, Handler.Callback
{
  private static final int MSG_ACTION_CCALLBACK = 2;
  private static final int MSG_CANCEL_NOTIFY = 3;
  private static final int MSG_TOAST = 1;
  private View bgView;
  private PlatformActionListener callback = this;
  private Context context;
  private ArrayList<CustomerLogo> customers = new ArrayList();
  private ShareContentCustomizeCallback customizeCallback;
  private boolean dialogMode = false;
  private boolean disableSSO;
  private HashMap<String, String> hiddenPlatforms = new HashMap();
  private PlatformListFakeActivity.OnShareButtonClickListener onShareButtonClickListener;
  private HashMap<String, Object> shareParamsMap = new HashMap();
  private boolean silent;
  private OnekeyShareTheme theme;

  private void showNotification(String paramString)
  {
    Toast.makeText(this.context, paramString, 0).show();
  }

  public void addHiddenPlatform(String paramString)
  {
    this.hiddenPlatforms.put(paramString, paramString);
  }

  public void disableSSOWhenAuthorize()
  {
    this.disableSSO = true;
  }

  public PlatformActionListener getCallback()
  {
    return this.callback;
  }

  public ShareContentCustomizeCallback getShareContentCustomizeCallback()
  {
    return this.customizeCallback;
  }

  public String getText()
  {
    if (this.shareParamsMap.containsKey("text"))
      return String.valueOf(this.shareParamsMap.get("text"));
    return null;
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    NotificationManager localNotificationManager;
    do
    {
      int i;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          return false;
                          paramMessage = String.valueOf(paramMessage.obj);
                          Toast.makeText(this.context, paramMessage, 0).show();
                          return false;
                          switch (paramMessage.arg1)
                          {
                          default:
                            return false;
                          case 1:
                            i = R.getStringRes(this.context, "share_completed");
                          case 2:
                          case 3:
                          }
                        }
                        while (i <= 0);
                        showNotification(this.context.getString(i));
                        return false;
                        paramMessage = paramMessage.obj.getClass().getSimpleName();
                        if ((!"WechatClientNotExistException".equals(paramMessage)) && (!"WechatTimelineNotSupportedException".equals(paramMessage)) && (!"WechatFavoriteNotSupportedException".equals(paramMessage)))
                          break;
                        i = R.getStringRes(this.context, "wechat_client_inavailable");
                      }
                      while (i <= 0);
                      showNotification(this.context.getString(i));
                      return false;
                      if (!"GooglePlusClientNotExistException".equals(paramMessage))
                        break;
                      i = R.getStringRes(this.context, "google_plus_client_inavailable");
                    }
                    while (i <= 0);
                    showNotification(this.context.getString(i));
                    return false;
                    if (!"QQClientNotExistException".equals(paramMessage))
                      break;
                    i = R.getStringRes(this.context, "qq_client_inavailable");
                  }
                  while (i <= 0);
                  showNotification(this.context.getString(i));
                  return false;
                  if ((!"YixinClientNotExistException".equals(paramMessage)) && (!"YixinTimelineNotSupportedException".equals(paramMessage)))
                    break;
                  i = R.getStringRes(this.context, "yixin_client_inavailable");
                }
                while (i <= 0);
                showNotification(this.context.getString(i));
                return false;
                if (!"KakaoTalkClientNotExistException".equals(paramMessage))
                  break;
                i = R.getStringRes(this.context, "kakaotalk_client_inavailable");
              }
              while (i <= 0);
              showNotification(this.context.getString(i));
              return false;
              if (!"KakaoStoryClientNotExistException".equals(paramMessage))
                break;
              i = R.getStringRes(this.context, "kakaostory_client_inavailable");
            }
            while (i <= 0);
            showNotification(this.context.getString(i));
            return false;
            if (!"WhatsAppClientNotExistException".equals(paramMessage))
              break;
            i = R.getStringRes(this.context, "whatsapp_client_inavailable");
          }
          while (i <= 0);
          showNotification(this.context.getString(i));
          return false;
          i = R.getStringRes(this.context, "share_failed");
        }
        while (i <= 0);
        showNotification(this.context.getString(i));
        return false;
        i = R.getStringRes(this.context, "share_canceled");
      }
      while (i <= 0);
      showNotification(this.context.getString(i));
      return false;
      localNotificationManager = (NotificationManager)paramMessage.obj;
    }
    while (localNotificationManager == null);
    localNotificationManager.cancel(paramMessage.arg1);
    return false;
  }

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.arg1 = 3;
    localMessage.arg2 = paramInt;
    localMessage.obj = paramPlatform;
    UIHandler.sendMessage(localMessage, this);
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    paramHashMap = new Message();
    paramHashMap.what = 2;
    paramHashMap.arg1 = 1;
    paramHashMap.arg2 = paramInt;
    paramHashMap.obj = paramPlatform;
    if (!paramPlatform.getName().equals(SinaWeibo.NAME))
      UIHandler.sendMessage(paramHashMap, this);
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.arg1 = 2;
    localMessage.arg2 = paramInt;
    localMessage.obj = paramThrowable;
    UIHandler.sendMessage(localMessage, this);
    ShareSDK.logDemoEvent(4, paramPlatform);
  }

  public void setAddress(String paramString)
  {
    this.shareParamsMap.put("address", paramString);
  }

  public void setCallback(PlatformActionListener paramPlatformActionListener)
  {
    this.callback = paramPlatformActionListener;
  }

  public void setComment(String paramString)
  {
    this.shareParamsMap.put("comment", paramString);
  }

  public void setCustomerLogo(Bitmap paramBitmap1, Bitmap paramBitmap2, String paramString, View.OnClickListener paramOnClickListener)
  {
    CustomerLogo localCustomerLogo = new CustomerLogo();
    localCustomerLogo.label = paramString;
    localCustomerLogo.enableLogo = paramBitmap1;
    localCustomerLogo.disableLogo = paramBitmap2;
    localCustomerLogo.listener = paramOnClickListener;
    this.customers.add(localCustomerLogo);
  }

  public void setDialogMode()
  {
    this.dialogMode = true;
    this.shareParamsMap.put("dialogMode", Boolean.valueOf(this.dialogMode));
  }

  public void setEditPageBackground(View paramView)
  {
    this.bgView = paramView;
  }

  public void setExecuteUrl(String paramString)
  {
    this.shareParamsMap.put("executeurl", paramString);
  }

  public void setFilePath(String paramString)
  {
    this.shareParamsMap.put("filePath", paramString);
  }

  public void setImageArray(String[] paramArrayOfString)
  {
    this.shareParamsMap.put("imageArray", paramArrayOfString);
  }

  public void setImagePath(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.shareParamsMap.put("imagePath", paramString);
  }

  public void setImageUrl(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.shareParamsMap.put("imageUrl", paramString);
  }

  public void setInstallUrl(String paramString)
  {
    this.shareParamsMap.put("installurl", paramString);
  }

  public void setLatitude(float paramFloat)
  {
    this.shareParamsMap.put("latitude", Float.valueOf(paramFloat));
  }

  public void setLongitude(float paramFloat)
  {
    this.shareParamsMap.put("longitude", Float.valueOf(paramFloat));
  }

  public void setMusicUrl(String paramString)
  {
    this.shareParamsMap.put("musicUrl", paramString);
  }

  public void setOnShareButtonClickListener(PlatformListFakeActivity.OnShareButtonClickListener paramOnShareButtonClickListener)
  {
    this.onShareButtonClickListener = paramOnShareButtonClickListener;
  }

  public void setPlatform(String paramString)
  {
    this.shareParamsMap.put("platform", paramString);
  }

  public void setShareContentCustomizeCallback(ShareContentCustomizeCallback paramShareContentCustomizeCallback)
  {
    this.customizeCallback = paramShareContentCustomizeCallback;
  }

  public void setShareFromQQAuthSupport(boolean paramBoolean)
  {
    this.shareParamsMap.put("isShareTencentWeibo", Boolean.valueOf(paramBoolean));
  }

  public void setSilent(boolean paramBoolean)
  {
    this.silent = paramBoolean;
  }

  public void setSite(String paramString)
  {
    this.shareParamsMap.put("site", paramString);
  }

  public void setSiteUrl(String paramString)
  {
    this.shareParamsMap.put("siteUrl", paramString);
  }

  public void setText(String paramString)
  {
    this.shareParamsMap.put("text", paramString);
  }

  public void setTheme(OnekeyShareTheme paramOnekeyShareTheme)
  {
    this.theme = paramOnekeyShareTheme;
  }

  public void setTitle(String paramString)
  {
    this.shareParamsMap.put("title", paramString);
  }

  public void setTitleUrl(String paramString)
  {
    this.shareParamsMap.put("titleUrl", paramString);
  }

  public void setUrl(String paramString)
  {
    this.shareParamsMap.put("url", paramString);
  }

  public void setVenueDescription(String paramString)
  {
    this.shareParamsMap.put("venueDescription", paramString);
  }

  public void setVenueName(String paramString)
  {
    this.shareParamsMap.put("venueName", paramString);
  }

  public void setViewToShare(View paramView)
  {
    try
    {
      paramView = BitmapHelper.captureView(paramView, paramView.getWidth(), paramView.getHeight());
      this.shareParamsMap.put("viewToShare", paramView);
      return;
    }
    catch (Throwable paramView)
    {
      paramView.printStackTrace();
    }
  }

  public void share(HashMap<Platform, HashMap<String, Object>> paramHashMap)
  {
    int j = 0;
    paramHashMap = paramHashMap.entrySet().iterator();
    Object localObject1;
    while (true)
    {
      if (!paramHashMap.hasNext())
        return;
      localObject2 = (Map.Entry)paramHashMap.next();
      localObject1 = (Platform)((Map.Entry)localObject2).getKey();
      ((Platform)localObject1).SSOSetting(this.disableSSO);
      localObject3 = ((Platform)localObject1).getName();
      if (("KakaoTalk".equals(localObject3)) && (!((Platform)localObject1).isClientValid()))
      {
        localObject1 = new Message();
        ((Message)localObject1).what = 1;
        i = R.getStringRes(this.context, "kakaotalk_client_inavailable");
        ((Message)localObject1).obj = this.context.getString(i);
        UIHandler.sendMessage((Message)localObject1, this);
      }
      else if (("KakaoStory".equals(localObject3)) && (!((Platform)localObject1).isClientValid()))
      {
        localObject1 = new Message();
        ((Message)localObject1).what = 1;
        i = R.getStringRes(this.context, "kakaostory_client_inavailable");
        ((Message)localObject1).obj = this.context.getString(i);
        UIHandler.sendMessage((Message)localObject1, this);
      }
      else if (("Line".equals(localObject3)) && (!((Platform)localObject1).isClientValid()))
      {
        localObject1 = new Message();
        ((Message)localObject1).what = 1;
        i = R.getStringRes(this.context, "line_client_inavailable");
        ((Message)localObject1).obj = this.context.getString(i);
        UIHandler.sendMessage((Message)localObject1, this);
      }
      else if (("WhatsApp".equals(localObject3)) && (!((Platform)localObject1).isClientValid()))
      {
        localObject1 = new Message();
        ((Message)localObject1).what = 1;
        i = R.getStringRes(this.context, "whatsapp_client_inavailable");
        ((Message)localObject1).obj = this.context.getString(i);
        UIHandler.sendMessage((Message)localObject1, this);
      }
      else if (("Pinterest".equals(localObject3)) && (!((Platform)localObject1).isClientValid()))
      {
        localObject1 = new Message();
        ((Message)localObject1).what = 1;
        i = R.getStringRes(this.context, "pinterest_client_inavailable");
        ((Message)localObject1).obj = this.context.getString(i);
        UIHandler.sendMessage((Message)localObject1, this);
      }
      else if (("Instagram".equals(localObject3)) && (!((Platform)localObject1).isClientValid()))
      {
        localObject1 = new Message();
        ((Message)localObject1).what = 1;
        i = R.getStringRes(this.context, "instagram_client_inavailable");
        ((Message)localObject1).obj = this.context.getString(i);
        UIHandler.sendMessage((Message)localObject1, this);
      }
      else
      {
        boolean bool1 = "Laiwang".equals(localObject3);
        boolean bool2 = "LaiwangMoments".equals(localObject3);
        if (((!bool1) && (!bool2)) || (((Platform)localObject1).isClientValid()))
          break;
        localObject1 = new Message();
        ((Message)localObject1).what = 1;
        i = R.getStringRes(this.context, "laiwang_client_inavailable");
        ((Message)localObject1).obj = this.context.getString(i);
        UIHandler.sendMessage((Message)localObject1, this);
      }
    }
    if ((!"YixinMoments".equals(localObject3)) && (!"Yixin".equals(localObject3)));
    for (int i = 0; ; i = 1)
    {
      if ((i == 0) || (((Platform)localObject1).isClientValid()))
        break label642;
      localObject1 = new Message();
      ((Message)localObject1).what = 1;
      i = R.getStringRes(this.context, "yixin_client_inavailable");
      ((Message)localObject1).obj = this.context.getString(i);
      UIHandler.sendMessage((Message)localObject1, this);
      break;
    }
    label642: Object localObject2 = (HashMap)((Map.Entry)localObject2).getValue();
    int k = 1;
    Object localObject3 = String.valueOf(((HashMap)localObject2).get("imagePath"));
    if ((localObject3 != null) && (new File((String)localObject3).exists()))
    {
      k = 2;
      if (((String)localObject3).endsWith(".gif"))
        i = 9;
    }
    while (true)
    {
      ((HashMap)localObject2).put("shareType", Integer.valueOf(i));
      i = j;
      if (j == 0)
        i = 1;
      ((Platform)localObject1).setPlatformActionListener(this.callback);
      localObject3 = new ShareCore();
      ((ShareCore)localObject3).setShareContentCustomizeCallback(this.customizeCallback);
      ((ShareCore)localObject3).share((Platform)localObject1, (HashMap)localObject2);
      j = i;
      break;
      i = k;
      if (((HashMap)localObject2).containsKey("url"))
      {
        i = k;
        if (!TextUtils.isEmpty(((HashMap)localObject2).get("url").toString()))
        {
          k = 4;
          i = k;
          if (((HashMap)localObject2).containsKey("musicUrl"))
          {
            i = k;
            if (!TextUtils.isEmpty(((HashMap)localObject2).get("musicUrl").toString()))
            {
              i = 5;
              continue;
              localObject3 = (Bitmap)((HashMap)localObject2).get("viewToShare");
              if ((localObject3 != null) && (!((Bitmap)localObject3).isRecycled()))
              {
                k = 2;
                i = k;
                if (((HashMap)localObject2).containsKey("url"))
                {
                  i = k;
                  if (!TextUtils.isEmpty(((HashMap)localObject2).get("url").toString()))
                  {
                    k = 4;
                    i = k;
                    if (((HashMap)localObject2).containsKey("musicUrl"))
                    {
                      i = k;
                      if (!TextUtils.isEmpty(((HashMap)localObject2).get("musicUrl").toString()))
                        i = 5;
                    }
                  }
                }
              }
              else
              {
                localObject3 = ((HashMap)localObject2).get("imageUrl");
                i = k;
                if (localObject3 != null)
                {
                  i = k;
                  if (!TextUtils.isEmpty(String.valueOf(localObject3)))
                  {
                    k = 2;
                    if (String.valueOf(localObject3).endsWith(".gif"))
                    {
                      i = 9;
                    }
                    else
                    {
                      i = k;
                      if (((HashMap)localObject2).containsKey("url"))
                      {
                        i = k;
                        if (!TextUtils.isEmpty(((HashMap)localObject2).get("url").toString()))
                        {
                          k = 4;
                          i = k;
                          if (((HashMap)localObject2).containsKey("musicUrl"))
                          {
                            i = k;
                            if (!TextUtils.isEmpty(((HashMap)localObject2).get("musicUrl").toString()))
                              i = 5;
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  public void show(Context paramContext)
  {
    ShareSDK.initSDK(paramContext);
    this.context = paramContext;
    ShareSDK.logDemoEvent(1, null);
    Object localObject;
    if (this.shareParamsMap.containsKey("platform"))
    {
      localObject = String.valueOf(this.shareParamsMap.get("platform"));
      Platform localPlatform = ShareSDK.getPlatform((String)localObject);
      if ((this.silent) || (ShareCore.isUseClientToShare((String)localObject)) || ((localPlatform instanceof CustomPlatform)))
      {
        paramContext = new HashMap();
        paramContext.put(ShareSDK.getPlatform((String)localObject), this.shareParamsMap);
        share(paramContext);
        return;
      }
    }
    try
    {
      if (OnekeyShareTheme.SKYBLUE == this.theme);
      for (localObject = (PlatformListFakeActivity)Class.forName("cn.sharesdk.onekeyshare.theme.skyblue.PlatformListPage").newInstance(); ; localObject = (PlatformListFakeActivity)Class.forName("cn.sharesdk.onekeyshare.theme.classic.PlatformListPage").newInstance())
      {
        ((PlatformListFakeActivity)localObject).setDialogMode(this.dialogMode);
        ((PlatformListFakeActivity)localObject).setShareParamsMap(this.shareParamsMap);
        ((PlatformListFakeActivity)localObject).setSilent(this.silent);
        ((PlatformListFakeActivity)localObject).setCustomerLogos(this.customers);
        ((PlatformListFakeActivity)localObject).setBackgroundView(this.bgView);
        ((PlatformListFakeActivity)localObject).setHiddenPlatforms(this.hiddenPlatforms);
        ((PlatformListFakeActivity)localObject).setOnShareButtonClickListener(this.onShareButtonClickListener);
        ((PlatformListFakeActivity)localObject).setThemeShareCallback(new ThemeShareCallback()
        {
          public void doShare(HashMap<Platform, HashMap<String, Object>> paramAnonymousHashMap)
          {
            OnekeyShare.this.share(paramAnonymousHashMap);
          }
        });
        if (!this.shareParamsMap.containsKey("platform"))
          break;
        ((PlatformListFakeActivity)localObject).showEditPage(paramContext, ShareSDK.getPlatform(String.valueOf(this.shareParamsMap.get("platform"))));
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    ((PlatformListFakeActivity)localObject).show(paramContext, null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.OnekeyShare
 * JD-Core Version:    0.6.2
 */