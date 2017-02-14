package cn.sharesdk.onekeyshare;

import cn.sharesdk.framework.Platform;
import com.mob.tools.FakeActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FollowerListFakeActivity extends FakeActivity
{
  protected Platform platform;

  public static FollowersResult parseFollowers(String paramString, HashMap<String, Object> paramHashMap, HashMap<String, Boolean> paramHashMap1)
  {
    if ((paramHashMap == null) || (paramHashMap.size() <= 0))
      return null;
    boolean bool2 = false;
    ArrayList localArrayList = new ArrayList();
    boolean bool1;
    if ("SinaWeibo".equals(paramString))
    {
      paramString = ((ArrayList)paramHashMap.get("users")).iterator();
      if (!paramString.hasNext())
      {
        if (((Integer)paramHashMap.get("total_number")).intValue() <= paramHashMap1.size())
          break label226;
        bool1 = true;
      }
    }
    Object localObject1;
    Object localObject2;
    label226: 
    do
    {
      Object localObject3;
      while (true)
      {
        paramString = new FollowersResult();
        paramString.list = localArrayList;
        paramString.hasNextPage = bool1;
        return paramString;
        localObject1 = (HashMap)paramString.next();
        localObject2 = String.valueOf(((HashMap)localObject1).get("id"));
        if (paramHashMap1.containsKey(localObject2))
          break;
        localObject3 = new Following();
        ((Following)localObject3).uid = ((String)localObject2);
        ((Following)localObject3).screenName = String.valueOf(((HashMap)localObject1).get("name"));
        ((Following)localObject3).description = String.valueOf(((HashMap)localObject1).get("description"));
        ((Following)localObject3).icon = String.valueOf(((HashMap)localObject1).get("profile_image_url"));
        ((Following)localObject3).atName = ((Following)localObject3).screenName;
        paramHashMap1.put(((Following)localObject3).uid, Boolean.valueOf(true));
        localArrayList.add(localObject3);
        break;
        bool1 = false;
      }
      if ("TencentWeibo".equals(paramString))
      {
        if (((Integer)paramHashMap.get("hasnext")).intValue() == 0);
        for (bool2 = true; ; bool2 = false)
        {
          paramString = ((ArrayList)paramHashMap.get("info")).iterator();
          while (true)
          {
            bool1 = bool2;
            if (!paramString.hasNext())
              break;
            paramHashMap = (HashMap)paramString.next();
            localObject2 = String.valueOf(paramHashMap.get("name"));
            if (!paramHashMap1.containsKey(localObject2))
            {
              localObject1 = new Following();
              ((Following)localObject1).screenName = String.valueOf(paramHashMap.get("nick"));
              ((Following)localObject1).uid = ((String)localObject2);
              ((Following)localObject1).atName = ((String)localObject2);
              localObject2 = ((ArrayList)paramHashMap.get("tweet")).iterator();
              if (((Iterator)localObject2).hasNext())
                ((Following)localObject1).description = String.valueOf(((HashMap)((Iterator)localObject2).next()).get("text"));
              ((Following)localObject1).icon = (String.valueOf(paramHashMap.get("head")) + "/100");
              paramHashMap1.put(((Following)localObject1).uid, Boolean.valueOf(true));
              localArrayList.add(localObject1);
            }
          }
        }
      }
      if ("Facebook".equals(paramString))
      {
        paramString = ((ArrayList)paramHashMap.get("data")).iterator();
        while (true)
        {
          if (!paramString.hasNext())
          {
            bool1 = ((HashMap)paramHashMap.get("paging")).containsKey("next");
            break;
          }
          localObject2 = (HashMap)paramString.next();
          localObject3 = String.valueOf(((HashMap)localObject2).get("id"));
          if (!paramHashMap1.containsKey(localObject3))
          {
            localObject1 = new Following();
            ((Following)localObject1).uid = ((String)localObject3);
            ((Following)localObject1).atName = ("[" + (String)localObject3 + "]");
            ((Following)localObject1).screenName = String.valueOf(((HashMap)localObject2).get("name"));
            localObject2 = (HashMap)((HashMap)localObject2).get("picture");
            if (localObject2 != null)
              ((Following)localObject1).icon = String.valueOf(((HashMap)((HashMap)localObject2).get("data")).get("url"));
            paramHashMap1.put(((Following)localObject1).uid, Boolean.valueOf(true));
            localArrayList.add(localObject1);
          }
        }
      }
      if ("Twitter".equals(paramString))
      {
        paramString = ((ArrayList)paramHashMap.get("users")).iterator();
        while (true)
        {
          bool1 = bool2;
          if (!paramString.hasNext())
            break;
          paramHashMap = (HashMap)paramString.next();
          localObject1 = String.valueOf(paramHashMap.get("screen_name"));
          if (!paramHashMap1.containsKey(localObject1))
          {
            localObject2 = new Following();
            ((Following)localObject2).uid = ((String)localObject1);
            ((Following)localObject2).atName = ((String)localObject1);
            ((Following)localObject2).screenName = String.valueOf(paramHashMap.get("name"));
            ((Following)localObject2).description = String.valueOf(paramHashMap.get("description"));
            ((Following)localObject2).icon = String.valueOf(paramHashMap.get("profile_image_url"));
            paramHashMap1.put(((Following)localObject2).uid, Boolean.valueOf(true));
            localArrayList.add(localObject2);
          }
        }
      }
      bool1 = bool2;
    }
    while (!"FacebookMessenger".equals(paramString));
    paramString = ((ArrayList)paramHashMap.get("users")).iterator();
    while (true)
    {
      if (!paramString.hasNext())
      {
        bool1 = false;
        break;
      }
      paramHashMap = (HashMap)paramString.next();
      localObject1 = String.valueOf(paramHashMap.get("jid"));
      if (!paramHashMap1.containsKey(localObject1))
      {
        localObject2 = new Following();
        ((Following)localObject2).uid = ((String)localObject1);
        ((Following)localObject2).atName = ((String)localObject1);
        ((Following)localObject2).screenName = String.valueOf(paramHashMap.get("name"));
        paramHashMap1.put(((Following)localObject2).uid, Boolean.valueOf(true));
        localArrayList.add(localObject2);
      }
    }
  }

  public Platform getPlatform()
  {
    return this.platform;
  }

  public boolean isRadioMode(String paramString)
  {
    return "FacebookMessenger".equals(paramString);
  }

  public void setPlatform(Platform paramPlatform)
  {
    this.platform = paramPlatform;
  }

  public void setResultForChecked(ArrayList<String> paramArrayList)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("selected", paramArrayList);
    localHashMap.put("platform", this.platform);
    setResult(localHashMap);
  }

  public static class FollowersResult
  {
    public boolean hasNextPage = false;
    public ArrayList<FollowerListFakeActivity.Following> list;
  }

  public static class Following
  {
    public String atName;
    public boolean checked;
    public String description;
    public String icon;
    public String screenName;
    public String uid;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.FollowerListFakeActivity
 * JD-Core Version:    0.6.2
 */