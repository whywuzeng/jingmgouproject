package cn.smssdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;

public class SMSSDK
{
  public static final int EVENT_GET_CONTACTS = 4;
  public static final int EVENT_GET_FRIENDS_IN_APP = 6;
  public static final int EVENT_GET_NEW_FRIENDS_COUNT = 7;
  public static final int EVENT_GET_SUPPORTED_COUNTRIES = 1;
  public static final int EVENT_GET_VERIFICATION_CODE = 2;
  public static final int EVENT_SUBMIT_USER_INFO = 5;
  public static final int EVENT_SUBMIT_VERIFICATION_CODE = 3;
  public static final int RESULT_COMPLETE = -1;
  public static final int RESULT_ERROR = 0;
  private static a a;

  private static void a()
  {
    if (a == null)
      throw new NullPointerException("Please call SMSSDK.initSDK(Context, String, String) before any action.");
  }

  public static void getContacts(boolean paramBoolean)
  {
    a();
    a.a(4, Boolean.valueOf(paramBoolean));
  }

  public static String[] getCountry(String paramString)
  {
    a();
    return a.a(paramString);
  }

  public static void getFriendsInApp()
  {
    a();
    a.a(6, null);
  }

  public static HashMap<Character, ArrayList<String[]>> getGroupedCountryList()
  {
    a();
    return a.c();
  }

  public static void getNewFriendsCount()
  {
    a();
    a.a(7, null);
  }

  public static void getSupportedCountries()
  {
    a();
    a.a(1, null);
  }

  public static void getVerificationCode(String paramString1, String paramString2)
  {
    a();
    a.a(2, new String[] { paramString1, paramString2 });
  }

  public static void initSDK(Context paramContext, String paramString1, String paramString2)
  {
    if (a == null)
    {
      a = new a(paramContext);
      a.a(paramString1, paramString2);
      a.a();
    }
  }

  public static void registerEventHandler(EventHandler paramEventHandler)
  {
    a();
    a.a(paramEventHandler);
  }

  public static void submitUserInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    a();
    a.a(5, new String[] { paramString1, paramString2, paramString3, paramString4, paramString5 });
  }

  public static void submitVerificationCode(String paramString1, String paramString2, String paramString3)
  {
    a();
    a.a(3, new String[] { paramString1, paramString2, paramString3 });
  }

  public static void unregisterAllEventHandler()
  {
    a();
    a.b();
  }

  public static void unregisterEventHandler(EventHandler paramEventHandler)
  {
    a();
    a.b(paramEventHandler);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.SMSSDK
 * JD-Core Version:    0.6.2
 */