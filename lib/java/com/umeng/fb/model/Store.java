package com.umeng.fb.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.fb.util.Log;
import com.umeng.fb.util.d;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Store
{
  private static final String a = Store.class.getName();
  private static Store b;
  private static final String d = "umeng_feedback_conversations";
  private static final String e = "umeng_feedback_user_info";
  private static final String f = "user";
  private static final String g = "uid";
  private static final String h = "device_uuid";
  private static final String i = "conversation_format_version";
  private static final String j = "last_update_at";
  private static final String k = "last_sync_at";
  private Context c;
  private SharedPreferences l;
  private SharedPreferences m;
  private Map<String, Conversation> n = new HashMap();

  private Store(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
    this.l = this.c.getSharedPreferences("umeng_feedback_conversations", 0);
    this.m = this.c.getSharedPreferences("umeng_feedback_user_info", 0);
  }

  public static Store getInstance(Context paramContext)
  {
    if (b == null)
      b = new Store(paramContext);
    return b;
  }

  void a()
  {
    d.a(this.l.edit().clear());
    d.a(this.m.edit().clear());
  }

  public List<String> getAllConversationIds()
  {
    Object localObject = this.l.getAll();
    ArrayList localArrayList = new ArrayList();
    localObject = ((Map)localObject).keySet().iterator();
    while (((Iterator)localObject).hasNext())
      localArrayList.add((String)((Iterator)localObject).next());
    Collections.sort(localArrayList);
    return localArrayList;
  }

  public Conversation getConversationById(String paramString)
  {
    if (!this.n.containsKey(paramString));
    try
    {
      Object localObject = this.l.getString(paramString, "");
      localObject = Conversation.a(this.c, new JSONArray((String)localObject), paramString);
      this.n.put(paramString, localObject);
      return (Conversation)this.n.get(paramString);
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public String getDeviceUUID()
  {
    String str2 = this.m.getString("device_uuid", "");
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      str1 = UUID.randomUUID().toString();
      d.a(this.m.edit().putString("device_uuid", str1));
    }
    return str1;
  }

  public String getUid()
  {
    return this.m.getString("uid", "");
  }

  public UserInfo getUserInfo()
  {
    Object localObject = this.m.getString("user", "");
    if ("".equals(localObject))
      return new UserInfo();
    try
    {
      localObject = new UserInfo(new JSONObject((String)localObject));
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return new UserInfo();
  }

  public long getUserInfoLastSyncAt()
  {
    return this.m.getLong("last_sync_at", 0L);
  }

  public long getUserInfoLastUpdateAt()
  {
    return this.m.getLong("last_update_at", 0L);
  }

  public boolean isMigrated()
  {
    boolean bool = false;
    if (this.m.getInt("conversation_format_version", 0) >= 5)
      bool = true;
    return bool;
  }

  public void migrateData()
  {
    while (true)
    {
      String str1;
      String str2;
      Conversation localConversation;
      try
      {
        Map localMap = this.l.getAll();
        Iterator localIterator = localMap.keySet().iterator();
        if (localIterator.hasNext())
        {
          str1 = (String)localIterator.next();
          str2 = (String)localMap.get(str1);
          localConversation = Conversation.newInstance(this.c, str1);
          JSONArray localJSONArray = new JSONArray(str2);
          int i1 = 0;
          if (i1 >= localJSONArray.length())
            break label240;
          Object localObject = localJSONArray.getJSONObject(i1);
          try
          {
            String str4 = ((JSONObject)localObject).optString("content");
            String str5 = ((JSONObject)localObject).optString("reply_id");
            String str3 = ((JSONObject)localObject).optString("status");
            new Date();
            Date localDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(((JSONObject)localObject).optString("datetime"));
            localObject = new Reply(str4, str5, ((JSONObject)localObject).optString("type"), localDate.getTime());
            ((Reply)localObject).status = str3;
            localConversation.addReply((Reply)localObject);
            i1 += 1;
          }
          catch (ParseException localParseException)
          {
            localParseException.printStackTrace();
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        d.a(this.m.edit().putInt("conversation_format_version", 5));
        return;
      }
      label240: Log.c(a, "migrate data: id=" + str1 + "\n ");
      Log.c(a, "old: \n" + str2 + "\n");
      Log.c(a, "new :\n" + localConversation.toString());
    }
  }

  public void removeConversation(String paramString)
  {
    d.a(this.l.edit().remove(paramString));
    this.n.remove(paramString);
  }

  public void saveConversation(String paramString, Conversation paramConversation)
  {
    d.a(this.l.edit().putString(paramString, paramConversation.toJson().toString()));
    this.n.put(paramString, paramConversation);
  }

  public void saveUserInfo(UserInfo paramUserInfo)
  {
    d.a(this.m.edit().putString("user", paramUserInfo.toJson().toString()).putLong("last_update_at", System.currentTimeMillis()));
  }

  public void setUid(String paramString)
  {
    d.a(this.m.edit().putString("uid", paramString));
  }

  public void setUserInfoLastSyncAt(long paramLong)
  {
    d.a(this.m.edit().putLong("last_sync_at", paramLong));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.Store
 * JD-Core Version:    0.6.2
 */