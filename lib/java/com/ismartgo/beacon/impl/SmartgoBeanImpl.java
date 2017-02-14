package com.ismartgo.beacon.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.ismartgo.beacon.SmartgoBeaconUtil;
import com.ismartgo.beacon.db.DBDao;
import com.ismartgo.beacon.http.HttpConstants;
import com.ismartgo.beacon.http.HttpJsonParse;
import com.ismartgo.beacon.http.HttpRequestParam;
import com.ismartgo.beacon.http.RequestTask;
import com.ismartgo.beacon.pojo.InitAppInfo;
import com.ismartgo.beacon.util.CalendarUtil;
import com.ismartgo.beacon.util.CommonMethod;
import java.util.Map;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

public class SmartgoBeanImpl
{
  private static Context context;
  private static BeaconHandler handler = new BeaconHandler(null);

  public static void handleOldData()
  {
    Object localObject = DBDao.getInstance(context).queryOldBean(CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY));
    if ((localObject == null) || (((JSONArray)localObject).length() == 0))
    {
      Log.i("smartgo_beacon", "数据库没有旧数据");
      DBDao.getInstance(context).delNoActivityBean(CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY));
      return;
    }
    Log.i("smartgo_beacon", ((JSONArray)localObject).toString());
    String str = CommonMethod.getAppKey(context);
    localObject = HttpRequestParam.getStatisticsInfoParam(context, str, "ismartgo.com", "Android", ((JSONArray)localObject).toString());
    new RequestTask(context, HttpConstants.SAVE_STATISTICS_INFO, (Map)localObject, new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        if (paramAnonymousMessage.what == 260)
          if (paramAnonymousMessage.obj != null);
        do
        {
          while (true)
          {
            return;
            Log.i("smartgo_beacon", "提交信息： " + paramAnonymousMessage.obj);
            try
            {
              if (new JSONObject(paramAnonymousMessage.obj.toString()).optInt("status") == 10001)
              {
                DBDao.getInstance(SmartgoBeanImpl.context).updateOldBean(CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY));
                DBDao.getInstance(SmartgoBeanImpl.context).delOldBean(CalendarUtil.getCurrentTime(CalendarUtil.TIME_TO_DAY));
                return;
              }
            }
            catch (Exception paramAnonymousMessage)
            {
              paramAnonymousMessage.printStackTrace();
              return;
            }
          }
          if (paramAnonymousMessage.what == 258)
          {
            Log.i("smartgo_beacon", "网络异常");
            return;
          }
        }
        while (paramAnonymousMessage.what != 259);
        Log.i("smartgo_beacon", "服务端异常");
      }
    }).executeOnExecutor(Executors.newCachedThreadPool(), new Object[] { "" });
  }

  public static void init(Context paramContext)
  {
    validityKey(paramContext);
  }

  private static void validityKey(Context paramContext)
  {
    context = paramContext;
    Object localObject = CommonMethod.getAppKey(paramContext);
    if ((localObject == null) || ("".equals(((String)localObject).trim())))
    {
      Log.i("smartgo_beacon", "key填写有误，请检查");
      return;
    }
    localObject = HttpRequestParam.getInitAppParam((String)localObject, "ismartgo.com", "Android");
    new RequestTask(paramContext, HttpConstants.INIT_APP, (Map)localObject, handler).executeOnExecutor(Executors.newCachedThreadPool(), new Object[] { "" });
  }

  private static class BeaconHandler extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      if (paramMessage.what == 260)
        if (paramMessage.obj != null);
      do
      {
        do
        {
          return;
          Log.i("smartgo_beacon", "检查key是否正确： " + paramMessage.obj);
          paramMessage = HttpJsonParse.parseJson_InitApp(paramMessage.obj.toString());
        }
        while ((paramMessage == null) || (paramMessage.getStatus() != 10001));
        SmartgoBeaconUtil.getInstance(SmartgoBeanImpl.context).initLbs();
        SmartgoBeanImpl.handleOldData();
        return;
        if (paramMessage.what == 258)
        {
          Log.i("smartgo_beacon", "网络异常");
          return;
        }
      }
      while (paramMessage.what != 259);
      Log.i("smartgo_beacon", "服务端异常");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.impl.SmartgoBeanImpl
 * JD-Core Version:    0.6.2
 */