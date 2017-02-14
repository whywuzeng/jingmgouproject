package com.ismartgo.app.utils;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;

public class UMengStatisticsUtils
{
  public static void bean(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "bean");
  }

  public static void beanBanner(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "bean_banner");
  }

  public static void beanReplyInvite(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "bean_replyinvite");
  }

  public static void beanScanMsg(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "bean_scanmsg");
  }

  public static void beanSignShop(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "bean_signshop");
  }

  public static void beanTicket(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "bean_ticket");
  }

  public static void homeMessage(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "home_message");
  }

  public static void homeMsgDetail(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "home_msgdetail");
  }

  public static void homeReplyInvite(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "home_replyinvite");
  }

  public static void homeScanMsg(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "home_scanmsg");
  }

  public static void homeSearch(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "home_search");
  }

  public static void homeShare(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "home_share");
  }

  public static void homeSignShop(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "home_signshop");
  }

  public static void homeTicket(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "home_ticket");
  }

  public static void me(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "me");
  }

  public static void meGift(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "me_gift");
  }

  public static void promotion(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "promotion");
  }

  public static void promotionLocationMenu(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "promotion_location_menu");
  }

  public static void promotionMore(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "promotion_more");
  }

  public static void promotionMsgDetail(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "promotion_msgdetail");
  }

  public static void promotionSearch(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "promotion_search");
  }

  public static void promotionShare(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "promotion_share");
  }

  public static void promotionStoreMenu(Context paramContext)
  {
    MobclickAgent.onEvent(paramContext, "promotion_store_menu");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.utils.UMengStatisticsUtils
 * JD-Core Version:    0.6.2
 */