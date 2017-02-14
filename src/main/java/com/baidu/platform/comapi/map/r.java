package com.baidu.platform.comapi.map;

import android.os.Handler;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MessageCenter;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class r
{
  private static final String a = r.class.getSimpleName();
  private static r c;
  private a b;
  private w d;
  private Handler e;

  public static r a()
  {
    if (c == null)
    {
      c = new r();
      c.g();
    }
    return c;
  }

  private void g()
  {
    h();
    this.d = new w();
    this.e = new s(this);
    MessageCenter.registMessage(65289, this.e);
  }

  private void h()
  {
    EnvironmentUtilities.initAppDirectory(BMapManager.getContext());
    this.b = new a();
    this.b.a();
    String str5 = SysOSUtil.getModuleFileName();
    String str4 = EnvironmentUtilities.getAppSDCardPath();
    String str3 = EnvironmentUtilities.getAppCachePath();
    String str2 = EnvironmentUtilities.getAppSecondCachePath();
    int i = EnvironmentUtilities.getMapTmpStgMax();
    int j = EnvironmentUtilities.getDomTmpStgMax();
    int k = EnvironmentUtilities.getItsTmpStgMax();
    if (SysOSUtil.getDensityDpi() >= 180);
    for (String str1 = "/h/"; ; str1 = "/l/")
    {
      str5 = str5 + "/cfg";
      String str7 = str4 + "/vmp";
      str4 = str5 + str1;
      str5 = str5 + "/a/";
      String str6 = str7 + str1;
      str1 = str7 + str1;
      str3 = str3 + "/tmp/";
      str2 = str2 + "/tmp/";
      this.b.a(str4, str6, str3, str2, str1, str5, SysOSUtil.GetScreenSizeX(), SysOSUtil.GetScreenSizeY(), SysOSUtil.getDensityDpi(), i, j, k, 0);
      this.b.e();
      return;
    }
  }

  public ArrayList<q> a(String paramString)
  {
    if ((paramString.equals("")) || (this.b == null));
    while (true)
    {
      return null;
      Object localObject1 = this.b.a(paramString);
      if ((localObject1 != null) && (!((String)localObject1).equals("")))
      {
        paramString = new ArrayList();
        try
        {
          localObject1 = new JSONObject((String)localObject1);
          if ((localObject1 != null) && (((JSONObject)localObject1).length() != 0))
          {
            localObject1 = ((JSONObject)localObject1).optJSONArray("dataset");
            if (localObject1 != null)
            {
              int i = 0;
              while (i < ((JSONArray)localObject1).length())
              {
                q localq1 = new q();
                Object localObject2 = ((JSONArray)localObject1).getJSONObject(i);
                localq1.a = ((JSONObject)localObject2).optInt("id");
                localq1.b = ((JSONObject)localObject2).optString("name");
                localq1.c = ((JSONObject)localObject2).optInt("mapsize");
                localq1.d = ((JSONObject)localObject2).optInt("cty");
                if (((JSONObject)localObject2).has("child"))
                {
                  localObject2 = ((JSONObject)localObject2).optJSONArray("child");
                  ArrayList localArrayList = new ArrayList();
                  int j = 0;
                  while (j < ((JSONArray)localObject2).length())
                  {
                    q localq2 = new q();
                    JSONObject localJSONObject = ((JSONArray)localObject2).optJSONObject(j);
                    localq2.a = localJSONObject.optInt("id");
                    localq2.b = localJSONObject.optString("name");
                    localq2.c = localJSONObject.optInt("mapsize");
                    localq2.d = localJSONObject.optInt("cty");
                    localArrayList.add(localq2);
                    j += 1;
                  }
                  localq1.a(localArrayList);
                }
                paramString.add(localq1);
                i += 1;
              }
            }
          }
        }
        catch (JSONException paramString)
        {
          paramString.printStackTrace();
          return null;
        }
      }
    }
    return paramString;
  }

  public void a(v paramv)
  {
    if (this.d != null)
      this.d.a(paramv);
  }

  public boolean a(int paramInt)
  {
    if ((this.b == null) || (paramInt < 0))
      return false;
    return this.b.b(paramInt);
  }

  public boolean a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.b == null)
      return false;
    return this.b.a(paramBoolean1, paramBoolean2);
  }

  public void b()
  {
    MessageCenter.unregistMessage(65289, this.e);
    this.b.b();
    c = null;
  }

  public void b(v paramv)
  {
    if (this.d != null)
      this.d.b(paramv);
  }

  public boolean b(int paramInt)
  {
    if ((this.b == null) || (paramInt < 0))
      return false;
    return this.b.a(paramInt, false, 0);
  }

  public ArrayList<q> c()
  {
    if (this.b == null)
      return null;
    Object localObject1 = this.b.k();
    ArrayList localArrayList1 = new ArrayList();
    try
    {
      localObject1 = new JSONObject((String)localObject1).optJSONArray("dataset");
      int i = 0;
      while (i < ((JSONArray)localObject1).length())
      {
        q localq1 = new q();
        Object localObject2 = ((JSONArray)localObject1).optJSONObject(i);
        localq1.a = ((JSONObject)localObject2).optInt("id");
        localq1.b = ((JSONObject)localObject2).optString("name");
        localq1.c = ((JSONObject)localObject2).optInt("mapsize");
        localq1.d = ((JSONObject)localObject2).optInt("cty");
        if (((JSONObject)localObject2).has("child"))
        {
          localObject2 = ((JSONObject)localObject2).optJSONArray("child");
          ArrayList localArrayList2 = new ArrayList();
          int j = 0;
          while (j < ((JSONArray)localObject2).length())
          {
            q localq2 = new q();
            JSONObject localJSONObject = ((JSONArray)localObject2).optJSONObject(j);
            localq2.a = localJSONObject.optInt("id");
            localq2.b = localJSONObject.optString("name");
            localq2.c = localJSONObject.optInt("mapsize");
            localq2.d = localJSONObject.optInt("cty");
            localArrayList2.add(localq2);
            j += 1;
          }
          localq1.a(localArrayList2);
        }
        localArrayList1.add(localq1);
        i += 1;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return null;
    }
    return localJSONException;
  }

  public boolean c(int paramInt)
  {
    if ((this.b == null) || (paramInt < 0))
      return false;
    return this.b.b(paramInt, false, 0);
  }

  public ArrayList<q> d()
  {
    if (this.b == null)
      return null;
    Object localObject1 = this.b.a("");
    ArrayList localArrayList1 = new ArrayList();
    try
    {
      localObject1 = new JSONObject((String)localObject1).optJSONArray("dataset");
      int i = 0;
      while (i < ((JSONArray)localObject1).length())
      {
        q localq1 = new q();
        Object localObject2 = ((JSONArray)localObject1).optJSONObject(i);
        localq1.a = ((JSONObject)localObject2).optInt("id");
        localq1.b = ((JSONObject)localObject2).optString("name");
        localq1.c = ((JSONObject)localObject2).optInt("mapsize");
        localq1.d = ((JSONObject)localObject2).optInt("cty");
        if (((JSONObject)localObject2).has("child"))
        {
          localObject2 = ((JSONObject)localObject2).optJSONArray("child");
          ArrayList localArrayList2 = new ArrayList();
          int j = 0;
          while (j < ((JSONArray)localObject2).length())
          {
            q localq2 = new q();
            JSONObject localJSONObject = ((JSONArray)localObject2).optJSONObject(j);
            localq2.a = localJSONObject.optInt("id");
            localq2.b = localJSONObject.optString("name");
            localq2.c = localJSONObject.optInt("mapsize");
            localq2.d = localJSONObject.optInt("cty");
            localArrayList2.add(localq2);
            j += 1;
          }
          localq1.a(localArrayList2);
        }
        localArrayList1.add(localq1);
        i += 1;
      }
      return localArrayList1;
    }
    catch (Exception localException)
    {
      return null;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  public boolean d(int paramInt)
  {
    if (this.b == null)
      return false;
    return this.b.b(0, true, paramInt);
  }

  public ArrayList<u> e()
  {
    int i = 0;
    if (this.b == null);
    Object localObject;
    do
    {
      return null;
      localObject = this.b.j();
    }
    while ((localObject == null) || (((String)localObject).equals("")));
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      t localt;
      try
      {
        localObject = new JSONObject((String)localObject);
        if (((JSONObject)localObject).length() == 0)
          break;
        localObject = ((JSONObject)localObject).optJSONArray("dataset");
        if (i >= ((JSONArray)localObject).length())
          break label299;
        u localu = new u();
        localt = new t();
        JSONObject localJSONObject = ((JSONArray)localObject).optJSONObject(i);
        localt.a = localJSONObject.optInt("id");
        localt.b = localJSONObject.optString("name");
        localt.c = localJSONObject.optString("pinyin");
        localt.h = localJSONObject.optInt("mapoldsize");
        localt.i = localJSONObject.optInt("ratio");
        localt.l = localJSONObject.optInt("status");
        localt.g = new GeoPoint(localJSONObject.optInt("y"), localJSONObject.optInt("x"));
        if (localJSONObject.optInt("up") == 1)
        {
          localt.j = true;
          localt.e = localJSONObject.optInt("lev");
          if (!localt.j)
            break label290;
          localt.k = localJSONObject.optInt("mapsize");
          localu.a(localt);
          localArrayList.add(localu);
          i += 1;
          continue;
        }
        localt.j = false;
        continue;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return null;
      }
      label290: localt.k = 0;
    }
    label299: return localException;
  }

  public boolean e(int paramInt)
  {
    if ((this.b == null) || (paramInt < 0))
      return false;
    return this.b.b(paramInt, false);
  }

  public boolean f(int paramInt)
  {
    if ((this.b == null) || (paramInt < 0))
      return false;
    return this.b.a(paramInt, false);
  }

  public u g(int paramInt)
  {
    if ((this.b == null) || (paramInt < 0));
    Object localObject;
    do
    {
      return null;
      localObject = this.b.c(paramInt);
    }
    while ((localObject == null) || (((String)localObject).equals("")));
    u localu = new u();
    t localt = new t();
    while (true)
    {
      try
      {
        localObject = new JSONObject((String)localObject);
        if (((JSONObject)localObject).length() == 0)
          break;
        localt.a = ((JSONObject)localObject).optInt("id");
        localt.b = ((JSONObject)localObject).optString("name");
        localt.c = ((JSONObject)localObject).optString("pinyin");
        localt.d = ((JSONObject)localObject).optString("headchar");
        localt.h = ((JSONObject)localObject).optInt("mapoldsize");
        localt.i = ((JSONObject)localObject).optInt("ratio");
        localt.l = ((JSONObject)localObject).optInt("status");
        localt.g = new GeoPoint(((JSONObject)localObject).optInt("y"), ((JSONObject)localObject).optInt("x"));
        if (((JSONObject)localObject).optInt("up") == 1)
        {
          localt.j = true;
          localt.e = ((JSONObject)localObject).optInt("lev");
          if (localt.j)
          {
            localt.k = ((JSONObject)localObject).optInt("mapsize");
            localt.f = ((JSONObject)localObject).optInt("ver");
            localu.a(localt);
            return localu;
          }
        }
        else
        {
          localt.j = false;
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return null;
      }
      localt.k = 0;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.r
 * JD-Core Version:    0.6.2
 */