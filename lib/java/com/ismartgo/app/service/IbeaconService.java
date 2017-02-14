// INTERNAL ERROR //

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.service.IbeaconService
 * JD-Core Version:    0.6.2
 */

package com.ismartgo.app.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.activity.WebViewActivity;
import com.ismartgo.app.bean.Area;
import com.ismartgo.app.bean.DistrictArea;
import com.ismartgo.app.bean.GoodsClazz;
import com.ismartgo.app.bean.IBeaconMsgLog;
import com.ismartgo.app.bean.IbeaconDataBean;
import com.ismartgo.app.bean.RetailCache;
import com.ismartgo.app.bean.ScreenStore;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ibeacon.MyIbeacon;
import com.ismartgo.app.ibeacon.iBeaconClass;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.item.Promotion_Select_menu;
import com.ismartgo.app.net.BusinessAreaRequest;
import com.ismartgo.app.net.ClassAreaRequest;
import com.ismartgo.app.net.DistrictAreaRequest;
import com.ismartgo.app.popwindow.ViewMiddle_Class;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Response;

import net.tsz.afinal.FinalDb;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class IbeaconService
        extends Service
{
    public static String IBeaconShopName = "";
    private static String TAG = "IbeaconService";
    public static List<Area> area;
    public static String brandIds;
    public static String brandValues;
    public static String category1Id;
    public static String category2Id;
    private static int categoryRequestCount;
    private static Context context;
    public static int districtId;
    public static List<GoodsClazz> goodsClazzLists;
    static MyIbeacon myIbeacon;
    public static int nearDistance;
    private static int regionRequestCount;
    public static List<RetailCache> retailCachelist;
    public static int retailId;
    public static List<ScreenStore> screenStore_area = new ArrayList();
    public static int shopTypeId;
    public static String temCategory1Id;
    public static String temCategory2Id;
    public static int temDistrictId;
    public static int temRetailId;
    public static int temShopTypeId;
    public static int temTownId;
    public static int townId;
    iBeaconData beaconData;
    FinalDb db;
    private int notificationId = 1;
    TimeCount timeCount;

    static
    {
        area = new ArrayList();
        goodsClazzLists = new ArrayList();
        districtId = -2;
        townId = -2;
        shopTypeId = -1;
        retailId = -1;
        category1Id = "";
        category2Id = "";
        brandIds = "";
        brandValues = "";
        temDistrictId = -1;
        temTownId = -1;
        temShopTypeId = -1;
        temRetailId = -1;
        temCategory1Id = "";
        temCategory2Id = "";
        retailCachelist = new ArrayList();
        regionRequestCount = 1;
        categoryRequestCount = 1;
    }

    private void clearIbeaconData()
    {
        IBeaconShopName = "";
        SharedPreferenceUtil.saveIbeaconData(getBaseContext(), new iBeaconClass.iBeacon());
        SharedPreferenceUtil.saveRequestIbeaconData(getBaseContext(), "", "", "", "", "", "", "", "", "", "", "");
    }

    public static void getAreaCondition_1(Context paramContext)
    {
        String str = SharedPreferenceUtil.getLocationInfo(paramContext).getString("city", "");
        BusinessAreaRequest localBusinessAreaRequest = new BusinessAreaRequest(paramContext, Url.PROMOTIONSCREENAREA_URL);
        localBusinessAreaRequest.initParams(str);
        localBusinessAreaRequest.setOnCompleteListener(new OnCompleteListener()
        {
            public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
            {
                Log.e(IbeaconService.TAG, "getAreaCondition_1返回错误： " + paramAnonymousString);
                if (IbeaconService.regionRequestCount <= 2)
                {
                    IbeaconService.regionRequestCount += 1;
                    IbeaconService.getAreaCondition_1(IbeaconService.this);
                }
            }

            public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
            {
                if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
                {
                    IbeaconService.area = (List)paramAnonymousObject;
                    if ((IbeaconService.area != null) && (IbeaconService.area.size() > 0))
                    {
                        Log.i("getAreaCondition_1返回数据", "区域有数据");
                        IbeaconService.districtId = ((Area)IbeaconService.area.get(0)).getDistrictId();
                        IbeaconService.townId = ((DistrictArea)((Area)IbeaconService.area.get(0)).getDistrictList().get(0)).getTownId();
                    }
                }
            }
        });
        localBusinessAreaRequest.startRequest();
    }

    public static void getClazzCondition_1(ViewMiddle_Class paramViewMiddle_Class)
    {
        ClassAreaRequest localClassAreaRequest = new ClassAreaRequest(context, Url.SHOP_CATEGORY_URL);
        localClassAreaRequest.shopTypeId = Promotion_Select_menu.to_class_shopTypeId;
        localClassAreaRequest.setOnCompleteListener(new OnCompleteListener()
        {
            public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString) {}

            public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
            {
                if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
                {
                    IbeaconService.goodsClazzLists = (List)paramAnonymousObject;
                    paramAnonymousInt = 0;
                }
                for (;;)
                {
                    if (paramAnonymousInt >= IbeaconService.goodsClazzLists.size()) {
                        return;
                    }
                    IbeaconService.getClazzCondition_2(paramAnonymousInt, IbeaconService.this);
                    paramAnonymousInt += 1;
                }
            }
        });
        localClassAreaRequest.startRequest();
    }

    public static void getClazzCondition_2(int paramInt, final ViewMiddle_Class paramViewMiddle_Class)
    {
        ClassAreaRequest localClassAreaRequest = new ClassAreaRequest(context, Url.SHOP_CATEGORY_URL);
        localClassAreaRequest.shopTypeId = Promotion_Select_menu.to_class_shopTypeId;
        localClassAreaRequest.initParams(((GoodsClazz)goodsClazzLists.get(paramInt)).getClazzId());
        localClassAreaRequest.setOnCompleteListener(new OnCompleteListener()
        {
            public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString) {}

            public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
            {
                paramAnonymousObject = (List)paramAnonymousObject;
                ((GoodsClazz)IbeaconService.goodsClazzLists.get(this.val$index)).setGoodsClazzList((List)paramAnonymousObject);
                if ((paramViewMiddle_Class != null) && (this.val$index == IbeaconService.goodsClazzLists.size() - 1)) {
                    paramViewMiddle_Class.getBusinessClass(IbeaconService.shopTypeId);
                }
            }
        });
        localClassAreaRequest.startRequest();
    }

    public static void getClazzCondition_3(ViewMiddle_Class paramViewMiddle_Class)
    {
        paramViewMiddle_Class = new ClassAreaRequest(context, Url.SHOP_CATEGORY_URL);

        paramViewMiddle_Class.setOnCompleteListener(new OnCompleteListener()
        {
            public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
            {
                Log.e(IbeaconService.TAG, "getClazzCondition_3返回错误： " + paramAnonymousString);
                if (IbeaconService.categoryRequestCount <= 2)
                {
                    IbeaconService.categoryRequestCount += 1;
                    IbeaconService.getClazzCondition_3(null);
                }
            }

            public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
            {
                if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
                {
                    Log.e(IbeaconService.TAG, "getClazzCondition_3返回有数据");
                    IbeaconService.shopTypeId = ((ScreenStore)IbeaconService.screenStore_area.get(0)).getShopTypeId();
                    IbeaconService.goodsClazzLists = (List)paramAnonymousObject;
                }
            }
        });
        paramViewMiddle_Class.execute();
    }

    private void requestIbeaconDate(final iBeaconClass.iBeacon paramiBeacon)
    {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", paramiBeacon.proximityUuid);
        localHashMap.put("minor", paramiBeacon.minor);
        localHashMap.put("major", paramiBeacon.major);
        HttpRequest.getInstance().executePostStringRequest(context, Url.INQUIRY_IBEACON_DATA_URL, HttpWhat.INQUIRY_IBEACON_DATA, localHashMap, new HttpCallback()
        {
            public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
            {
                IbeaconService.IBeaconShopName = "";
            }

            public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
            {
                try
                {
                    paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
                    if (paramAnonymousResponse.getInt("status") != 10001) {
                        break label358;
                    }
                    paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data");
                    if (paramAnonymousResponse.isNull("ibeacon")) {
                        return;
                    }
                    localObject = paramAnonymousResponse.getJSONObject("ibeacon");
                    paramAnonymousResponse = new IbeaconDataBean();
                    paramAnonymousResponse.setDistance(((JSONObject)localObject).getString("distance"));
                    paramAnonymousResponse.setIbeaconnumber(((JSONObject)localObject).getString("ibeaconnumber"));
                    paramAnonymousResponse.setId(((JSONObject)localObject).getString("id"));
                    paramAnonymousResponse.setMajor(((JSONObject)localObject).getString("major"));
                    paramAnonymousResponse.setMinor(((JSONObject)localObject).getString("minor"));
                    paramAnonymousResponse.setUuid(((JSONObject)localObject).getString("uuid"));
                    paramAnonymousResponse.setShopid(((JSONObject)localObject).getString("shopid"));
                    paramAnonymousResponse.setShopname(((JSONObject)localObject).getString("shopname"));
                    paramAnonymousResponse.setStoptime(((JSONObject)localObject).getString("stoptime"));
                    paramAnonymousResponse.setThirdlink(((JSONObject)localObject).getString("thirdlink"));
                    paramAnonymousResponse.setTitle(((JSONObject)localObject).getString("title"));
                    if ((paramAnonymousResponse.getDistance() == null) || (paramAnonymousResponse.getDistance().trim().equals(""))) {
                        return;
                    }
                    if (Integer.parseInt(paramAnonymousResponse.getDistance()) < paramiBeacon.distance)
                    {
                        IbeaconService.this.clearIbeaconData();
                        return;
                    }
                }
                catch (JSONException paramAnonymousResponse)
                {
                    paramAnonymousResponse.printStackTrace();
                    IbeaconService.this.clearIbeaconData();
                    return;
                }
                SharedPreferenceUtil.saveIbeaconData(IbeaconService.this.getBaseContext(), paramiBeacon);
                Object localObject = SharedPreferenceUtil.getRequestIbeaconData(IbeaconService.this.getBaseContext());
                if ((!((IbeaconDataBean)localObject).getUuid().equalsIgnoreCase(paramAnonymousResponse.getUuid())) || (!((IbeaconDataBean)localObject).getMajor().equals(paramAnonymousResponse.getMajor())) || (!((IbeaconDataBean)localObject).getMinor().equals(paramAnonymousResponse.getMinor())))
                {
                    SharedPreferenceUtil.saveRequestIbeaconData(IbeaconService.this.getBaseContext(), paramAnonymousResponse.getDistance(), paramAnonymousResponse.getIbeaconnumber(), paramAnonymousResponse.getId(), paramAnonymousResponse.getMajor(), paramAnonymousResponse.getMinor(), paramAnonymousResponse.getShopid(), paramAnonymousResponse.getShopname(), paramAnonymousResponse.getStoptime(), paramAnonymousResponse.getThirdlink(), paramAnonymousResponse.getTitle(), paramAnonymousResponse.getUuid());
                    return;
                    label358:
                    IbeaconService.this.clearIbeaconData();
                }
            }
        });
    }

    public void getAreaCondition_2(final int paramInt)
    {
        DistrictAreaRequest localDistrictAreaRequest = new DistrictAreaRequest(getApplicationContext(), Url.PROMOTIONSCREENAREA_URL);
        localDistrictAreaRequest.initParams(BaseActivity.city, ((Area)area.get(paramInt)).getDistrictId(), ((Area)area.get(paramInt)).getAreaName());
        localDistrictAreaRequest.setOnCompleteListener(new OnCompleteListener()
        {
            public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString) {}

            public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
            {
                paramAnonymousObject = (List)paramAnonymousObject;
                IbeaconService.townId = ((DistrictArea)((List)paramAnonymousObject).get(0)).getTownId();
                IbeaconService.nearDistance = ((DistrictArea)((List)paramAnonymousObject).get(0)).getDistance();
                Log.i("Test", "districtId: " + IbeaconService.districtId + "  townId: " + IbeaconService.townId + "  nearDistance: " + IbeaconService.nearDistance);
                ((Area)IbeaconService.area.get(paramInt)).setDistrictList((List)paramAnonymousObject);
            }
        });
        localDistrictAreaRequest.startRequest();
    }

    public IBinder onBind(Intent paramIntent)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        context = this;
        this.db = FinalDb.create(getBaseContext());
        clearIbeaconData();
        getClazzCondition_3(null);
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.e(TAG, "服务被结束了");
        stopSelf();
    }

    public void setIbeaconListener(iBeaconData paramiBeaconData)
    {
        this.beaconData = paramiBeaconData;
    }

    class TimeCount
            extends CountDownTimer
    {
        String major;
        String minor;
        String uuid;

        public TimeCount(long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3)
        {
            super(paramLong2);
            this.uuid = paramString1;
            this.major = paramString2;
            this.minor = paramString3;
        }

        public void onFinish()
        {
            Object localObject1 = SharedPreferenceUtil.getRequestIbeaconData(IbeaconService.this.getBaseContext());
            if ((!((IbeaconDataBean)localObject1).getTitle().equals("")) && (!((IbeaconDataBean)localObject1).getStoptime().equals("")) && (!((IbeaconDataBean)localObject1).getThirdlink().equals("")) && (IbeaconService.this.db.findAllByWhere(IBeaconMsgLog.class, "uuid='" + ((IbeaconDataBean)localObject1).getUuid() + "' and major='" + ((IbeaconDataBean)localObject1).getMajor() + "' and minor='" + ((IbeaconDataBean)localObject1).getMinor() + "'").size() <= 0))
            {
                Object localObject2 = new IBeaconMsgLog();
                Object localObject3 = new Date();
                ((IBeaconMsgLog)localObject2).setDate(((Date)localObject3).toLocaleString().substring(0, ((Date)localObject3).toLocaleString().indexOf(" ")));
                ((IBeaconMsgLog)localObject2).setUuid(((IbeaconDataBean)localObject1).getUuid());
                ((IBeaconMsgLog)localObject2).setMajor(((IbeaconDataBean)localObject1).getMajor());
                ((IBeaconMsgLog)localObject2).setMinor(((IbeaconDataBean)localObject1).getMinor());
                IbeaconService.this.db.save(localObject2);
                localObject2 = (NotificationManager)IbeaconService.this.getSystemService("notification");
                localObject3 = new Notification(2130837736, "精明购", System.currentTimeMillis());
                Object localObject4 = new Intent(IbeaconService.this.getBaseContext(), WebViewActivity.class);
                ((Intent)localObject4).putExtra("url", ((IbeaconDataBean)localObject1).getThirdlink());
                localObject4 = PendingIntent.getActivity(IbeaconService.this.getBaseContext(), 0, (Intent)localObject4, 0);
                ((Notification)localObject3).setLatestEventInfo(IbeaconService.this.getApplicationContext(), "通知标题", "通知显示的内容", (PendingIntent)localObject4);
                ((Notification)localObject3).flags = 16;
                ((Notification)localObject3).defaults = 1;
                ((Notification)localObject3).setLatestEventInfo(IbeaconService.this.getBaseContext(), "精明购", ((IbeaconDataBean)localObject1).getTitle(), (PendingIntent)localObject4);
                localObject1 = IbeaconService.this;
                int i = ((IbeaconService)localObject1).notificationId;
                ((IbeaconService)localObject1).notificationId = (i + 1);
                ((NotificationManager)localObject2).notify(i, (Notification)localObject3);
            }
        }

        public void onTick(long paramLong)
        {
            if (IbeaconService.this.timeCount != null)
            {
                IbeaconDataBean localIbeaconDataBean = SharedPreferenceUtil.getRequestIbeaconData(IbeaconService.this.getBaseContext());
                if ((!localIbeaconDataBean.getUuid().equals(this.uuid)) || (!localIbeaconDataBean.getMajor().equals(this.major)) || (!localIbeaconDataBean.getMinor().equals(this.minor)))
                {
                    IbeaconService.this.timeCount.cancel();
                    IbeaconService.this.timeCount = null;
                }
            }
        }
    }

    public static abstract interface iBeaconData
    {
        public abstract void setIbeaconData(Vector<iBeaconClass.iBeacon> paramVector, boolean paramBoolean);
    }
}
