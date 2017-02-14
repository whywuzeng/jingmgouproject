package com.alimama.mobile.csdk.umupdate.a;

import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Message;
import android.text.TextUtils;
import com.alimama.mobile.a.a;
import com.alimama.mobile.csdk.umupdate.b.d;
import com.alimama.mobile.csdk.umupdate.b.d.a;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.alimama.mobile.csdk.umupdate.models.c;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class i extends b<Void, Void, Message>
{
  static final String e = i.class.getCanonicalName();
  com.alimama.mobile.csdk.umupdate.models.b f;
  private final a.a g;
  private final int h;
  private final boolean i;

  public i(com.alimama.mobile.csdk.umupdate.models.b paramb, a.a parama, int paramInt, boolean paramBoolean)
  {
    this.g = parama;
    this.h = paramInt;
    this.i = paramBoolean;
    this.f = paramb;
  }

  private List<Promoter> a(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.f.b().warp(paramJSONObject);
      Object localObject = this.f.a();
      if (((c)localObject).b() != this.f.b().preload)
        ((c)localObject).a(this.f.b().preload);
      localObject = new ArrayList();
      a((Collection)localObject, Promoter.class, paramJSONObject, this.f.b().slot_act_params);
      return localObject;
    }
    return null;
  }

  private List<Promoter> a(boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      List localList = a(this.f.a().a(paramBoolean1, this.f.b().sid_expired, paramBoolean2));
      if (localList != null)
      {
        int j = localList.size();
        if (j > 0);
      }
      else
      {
        localList = null;
      }
      return localList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private List<Promoter> a(int[] paramArrayOfInt)
  {
    int k = new Random().nextInt(1000);
    Object localObject = a(this.f.b());
    if (localObject == null)
      return null;
    boolean bool;
    if (((Map)localObject).containsKey("sid"))
      if (TextUtils.isEmpty((String)((Map)localObject).get("sid")))
        bool = true;
    while (true)
    {
      localObject = new com.alimama.mobile.csdk.umupdate.b.b().a(new com.alimama.mobile.csdk.umupdate.b.e((Map)localObject));
      if (paramArrayOfInt != null)
        if (localObject != null)
          break label124;
      label124: for (int j = 0; ; j = ((com.alimama.mobile.csdk.umupdate.b.f)localObject).a)
      {
        paramArrayOfInt[0] = j;
        if ((localObject != null) && (((com.alimama.mobile.csdk.umupdate.b.f)localObject).b != null))
          break label133;
        return null;
        bool = false;
        break;
      }
      label133: g.b("   requestLive get resStr: " + ((com.alimama.mobile.csdk.umupdate.b.f)localObject).b.toString(), new Object[0]);
      try
      {
        paramArrayOfInt = a(((com.alimama.mobile.csdk.umupdate.b.f)localObject).b);
        if ((paramArrayOfInt == null) || (paramArrayOfInt.size() <= 0))
        {
          if (!bool)
            break;
          this.f.a().a();
          return null;
        }
        if ((this.i) && (((com.alimama.mobile.csdk.umupdate.b.f)localObject).a == 1))
          this.f.a().a(bool, ((com.alimama.mobile.csdk.umupdate.b.f)localObject).b);
        return paramArrayOfInt;
      }
      catch (Exception paramArrayOfInt)
      {
        g.d(e, new Object[] { k + "  request from network error:", paramArrayOfInt });
        return null;
      }
      bool = true;
    }
    return null;
  }

  private <T extends Promoter> void a(Collection paramCollection, Class<T> paramClass, JSONObject paramJSONObject, String paramString)
  {
    g.c("get promoters use class " + paramClass.toString(), new Object[0]);
    try
    {
      if (paramJSONObject.has("promoters"))
      {
        paramJSONObject = paramJSONObject.getJSONArray("promoters");
        int j = 0;
        while (j < paramJSONObject.length())
        {
          Promoter localPromoter = Promoter.getPromoterFromJson((JSONObject)paramJSONObject.get(j), paramClass);
          localPromoter.slot_act_pams = paramString;
          paramCollection.add(localPromoter);
          j += 1;
        }
      }
      g.d("failed requesting", new Object[0]);
      return;
    }
    catch (Exception paramCollection)
    {
      g.a(paramCollection, "", new Object[0]);
    }
  }

  protected int a(List<Promoter> paramList)
  {
    if (paramList == null)
    {
      k = 0;
      return k;
    }
    ArrayList localArrayList = new ArrayList();
    int k = paramList.size() - 1;
    int j = 0;
    label28: if (k >= 0)
    {
      Promoter localPromoter = (Promoter)paramList.get(k);
      boolean bool = this.f.b().filterInstalledApp;
      if ((localPromoter == null) || (!localPromoter.filterInstalledApp) || (!bool) || (!com.alimama.mobile.a.a().b().c(localPromoter.app_package_name)))
        break label248;
      g.c(e.e, new Object[] { "Installed: " + ((Promoter)paramList.get(k)).title + ". Remove from the list." });
      localPromoter = (Promoter)paramList.remove(k);
      localArrayList.add(localPromoter);
      if (localPromoter.new_tip != 1)
        break label248;
      j += 1;
    }
    label248: 
    while (true)
    {
      k -= 1;
      break label28;
      k = j;
      if (localArrayList.size() <= 0)
        break;
      new d.a(this.f.b()).a(-1).b(-1).c(-1).a((Promoter[])localArrayList.toArray(new Promoter[0])).a().a();
      return j;
    }
  }

  protected Message a(Void[] paramArrayOfVoid)
  {
    boolean bool2 = TextUtils.isEmpty(this.f.b().sid);
    if (bool2)
      this.f.b().timeline[0] = System.currentTimeMillis();
    int[] arrayOfInt = new int[1];
    boolean bool1;
    if (this.h == 1)
      if (TextUtils.isEmpty(this.f.b().sid))
      {
        bool1 = true;
        paramArrayOfVoid = a(bool1, true);
        if ((paramArrayOfVoid == null) || (paramArrayOfVoid.size() <= 0))
          break label150;
        arrayOfInt[0] = 1;
        g.c("get data from local-cache.", new Object[0]);
      }
    while (true)
    {
      if (bool2)
        this.f.b().timeline[1] = System.currentTimeMillis();
      Message localMessage = new Message();
      localMessage.obj = paramArrayOfVoid;
      localMessage.arg1 = arrayOfInt[0];
      return localMessage;
      bool1 = false;
      break;
      label150: arrayOfInt[0] = 0;
      g.c("get data from local-cache.but has no data.", new Object[0]);
      continue;
      paramArrayOfVoid = a(arrayOfInt);
      g.c("get data from live.", new Object[0]);
    }
  }

  public Map<String, Object> a(MMEntity paramMMEntity)
  {
    HashMap localHashMap = new HashMap();
    Object localObject1 = com.alimama.mobile.a.a().b();
    localHashMap.put("sdk_version", e.b);
    localHashMap.put("sdk_channel", e.a);
    localHashMap.put("protocol_version", e.c);
    if (TextUtils.isEmpty(e.d))
      paramMMEntity = ((a)localObject1).f("MUNION_CHANNEL");
    while (true)
    {
      if (!TextUtils.isEmpty(paramMMEntity))
        localHashMap.put("channel", paramMMEntity);
      localHashMap.put("device_id", ((a)localObject1).r());
      localHashMap.put("idmd5", j.c(((a)localObject1).r()));
      localHashMap.put("device_model", Build.MODEL);
      localHashMap.put("os", "android");
      paramMMEntity = ((a)localObject1).p();
      if (!TextUtils.isEmpty(paramMMEntity))
        localHashMap.put("mc", paramMMEntity);
      localHashMap.put("os_version", Build.VERSION.RELEASE);
      localHashMap.put("locale", ((a)localObject1).n());
      localHashMap.put("language", ((a)localObject1).m());
      localHashMap.put("timezone", ((a)localObject1).o());
      localHashMap.put("resolution", ((a)localObject1).t());
      paramMMEntity = ((a)localObject1).C();
      localHashMap.put("access", paramMMEntity[0]);
      localHashMap.put("access_subtype", paramMMEntity[1]);
      localHashMap.put("carrier", ((a)localObject1).E());
      paramMMEntity = ((a)localObject1).D();
      if (paramMMEntity != null)
      {
        localHashMap.put("lat", String.valueOf(paramMMEntity.getLatitude()));
        localHashMap.put("lng", String.valueOf(paramMMEntity.getLongitude()));
        localHashMap.put("gps_type", paramMMEntity.getProvider());
        localHashMap.put("gpst", String.valueOf(paramMMEntity.getTime()));
        localHashMap.put("gps_accuracy", String.valueOf(paramMMEntity.getAccuracy()));
      }
      localHashMap.put("cpu", ((a)localObject1).F());
      Object localObject2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      paramMMEntity = localObject2.split(" ")[0];
      localObject2 = localObject2.split(" ")[1];
      localHashMap.put("date", paramMMEntity);
      localHashMap.put("time", localObject2);
      localHashMap.put("brand", Build.MANUFACTURER);
      localHashMap.put("timezone", ((a)localObject1).o());
      localHashMap.put("apnm", ((a)localObject1).g());
      localHashMap.put("apvn", ((a)localObject1).h());
      localHashMap.put("apvc", ((a)localObject1).i());
      localHashMap.put("adnm", ((a)localObject1).f());
      if (!TextUtils.isEmpty(this.f.b().slotId))
      {
        localHashMap.put("slot_id", this.f.b().slotId);
        label660: if (!TextUtils.isEmpty(this.f.b().filterPromoter))
          localHashMap.put("promoter", this.f.b().filterPromoter);
        localHashMap.put("layout_type", Integer.valueOf(this.f.b().layoutType));
        if (!TextUtils.isEmpty(this.f.b().keywords))
          localHashMap.put("keywords", j.b(this.f.b().keywords));
        if (!TextUtils.isEmpty(this.f.b().slot_act_params))
          localObject1 = this.f.b().slot_act_params.split("&");
      }
      else
      {
        try
        {
          paramMMEntity = new HashMap();
          int k = localObject1.length;
          int j = 0;
          while (true)
            if (j < k)
            {
              localObject2 = localObject1[j].split("=");
              if (localObject2.length == 2)
                paramMMEntity.put(localObject2[0], localObject2[1]);
              j += 1;
              continue;
              paramMMEntity = e.d;
              break;
              if (!TextUtils.isEmpty(this.f.b().appkey))
              {
                localHashMap.put("app_key", this.f.b().appkey);
                break label660;
              }
              g.d("Both APPKEY and SLOTID are empty, please specify either one. Request aborted.", new Object[0]);
              return null;
            }
          localObject1 = paramMMEntity.keySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            localHashMap.put(localObject2, paramMMEntity.get(localObject2));
          }
        }
        catch (Exception paramMMEntity)
        {
        }
      }
    }
    if (!TextUtils.isEmpty(this.f.b().urlParams))
      localHashMap.put("url_params", this.f.b().urlParams);
    if (!TextUtils.isEmpty(this.f.b().tag))
      localHashMap.put("tags", this.f.b().tag);
    if (this.f.b().autofill != 1)
      localHashMap.put("autofill", Integer.valueOf(this.f.b().autofill));
    if (!TextUtils.isEmpty(this.f.b().sid))
      localHashMap.put("sid", this.f.b().sid);
    if (!TextUtils.isEmpty(this.f.b().psid))
      localHashMap.put("psid", this.f.b().psid);
    localHashMap.put("req_desc", Integer.valueOf(1));
    if (this.f.b().resType == null)
    {
      paramMMEntity = "";
      localHashMap.put("resource_type", paramMMEntity);
      if (this.f.b().template != null)
      {
        localObject1 = new StringBuilder().append(this.f.b().template.toString());
        if (!TextUtils.isEmpty(this.f.b().templateAttrs))
          break label1333;
      }
    }
    label1333: for (paramMMEntity = ""; ; paramMMEntity = "." + this.f.b().templateAttrs)
    {
      localHashMap.put("template", paramMMEntity);
      if (this.f.b().landing_type > 0)
        localHashMap.put("landing_type", Integer.valueOf(this.f.b().landing_type));
      return localHashMap;
      paramMMEntity = this.f.b().resType.toString();
      break;
    }
  }

  protected void a(Message paramMessage)
  {
    List localList;
    int j;
    int k;
    MMEntity localMMEntity;
    if (this.g != null)
    {
      if ((paramMessage == null) || (paramMessage.obj == null))
        break label174;
      localList = (List)paramMessage.obj;
      if (this.f.b().filterInstalledApp)
      {
        j = a(localList);
        if ((j > 0) && (this.f.b().newTips > 0))
        {
          k = this.f.b().newTips;
          j = this.f.b().newTips - j;
          localMMEntity = this.f.b();
          if (j <= 0)
            break label169;
        }
      }
    }
    while (true)
    {
      localMMEntity.newTips = j;
      g.b("new tips has changed " + k + " ===> " + this.f.b().newTips, new Object[0]);
      this.g.dataReceived(paramMessage.arg1, localList);
      return;
      label169: j = -1;
    }
    label174: this.g.dataReceived(paramMessage.arg1, new ArrayList());
  }

  protected void c()
  {
    super.c();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.a.i
 * JD-Core Version:    0.6.2
 */