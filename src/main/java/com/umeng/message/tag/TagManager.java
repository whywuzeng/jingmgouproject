package com.umeng.message.tag;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.common.message.Log;
import com.umeng.common.message.UmengMessageDeviceConfig;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.UmengRegistrar;
import com.umeng.message.proguard.k;
import com.umeng.message.proguard.l;
import com.umeng.message.protobuffer.PushResponse;
import com.umeng.message.protobuffer.PushResponse.Info;
import com.umeng.message.protobuffer.PushResponse.responseCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class TagManager
{
  private static final String a = TagManager.class.getName();
  private static final String b = "ok";
  private static final String c = "fail";
  private static TagManager d;
  private Context e;

  private TagManager(Context paramContext)
  {
    this.e = paramContext.getApplicationContext();
  }

  private static String a(List<String> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
      localStringBuilder.append((String)paramList.next()).append(",");
    if ((localStringBuilder.length() > 0) && (localStringBuilder.charAt(localStringBuilder.length() - 1) == ','))
      localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
    return localStringBuilder.toString();
  }

  private static String a(String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(paramArrayOfString[i]).append(",");
      i += 1;
    }
    if ((localStringBuilder.length() > 0) && (localStringBuilder.charAt(localStringBuilder.length() - 1) == ','))
      localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
    return localStringBuilder.toString();
  }

  private JSONObject a()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("header", UTrack.getInstance(this.e).getHeader());
    localJSONObject.put("utdid", UmengMessageDeviceConfig.getUtdid(this.e));
    localJSONObject.put("device_token", UmengRegistrar.getRegistrationId(this.e));
    localJSONObject.put("ts", System.currentTimeMillis());
    return localJSONObject;
  }

  private static JSONObject a(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    String str = k.c(paramString).H().r("application/json").i(paramJSONObject.toString()).b("UTF-8");
    Log.c(a, "postJson() url=" + paramString + "\n request = " + paramJSONObject + "\n response = " + str);
    return new JSONObject(str);
  }

  private boolean b()
  {
    if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.e)))
    {
      Log.b(a, "UTDID is empty");
      return false;
    }
    if (TextUtils.isEmpty(UmengRegistrar.getRegistrationId(this.e)))
    {
      Log.b(a, "RegistrationId is empty");
      return false;
    }
    return true;
  }

  private boolean c()
  {
    boolean bool = true;
    if (MessageSharedPrefs.getInstance(this.e).getTagSendPolicy() == 1);
    while (true)
    {
      if (bool)
        Log.c(a, "tag is disabled by the server");
      return bool;
      bool = false;
    }
  }

  private Result d()
  {
    Result localResult = new Result(new JSONObject());
    localResult.remain = MessageSharedPrefs.getInstance(this.e).getTagRemain();
    localResult.status = "ok";
    localResult.jsonString = ("status:" + localResult.status + "," + " remain:" + localResult.remain + "," + "description:" + localResult.status);
    return localResult;
  }

  public static TagManager getInstance(Context paramContext)
  {
    try
    {
      if (d == null)
        d = new TagManager(paramContext.getApplicationContext());
      paramContext = d;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public Result add(String[] paramArrayOfString)
    throws Exception
  {
    if (c())
      throw new Exception("Tag API is disabled by the server.");
    if (!b())
      throw new Exception("No utdid or device_token");
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      throw new Exception("No tags");
    Object localObject1 = new ArrayList();
    int j = paramArrayOfString.length;
    int i = 0;
    Object localObject2;
    while (i < j)
    {
      localObject2 = paramArrayOfString[i];
      if ((!MessageSharedPrefs.getInstance(this.e).isTagSet((String)localObject2)) && (!((ArrayList)localObject1).contains(localObject2)))
        ((ArrayList)localObject1).add(localObject2);
      i += 1;
    }
    if (((ArrayList)localObject1).size() == 0)
      localObject1 = d();
    PushResponse localPushResponse;
    do
    {
      do
      {
        return localObject1;
        localObject2 = a();
        ((JSONObject)localObject2).put("tags", a((List)localObject1));
        localObject1 = new l(this.e);
        localPushResponse = ((l)localObject1).a(((l)localObject1).a((JSONObject)localObject2), MsgConstant.TAG_ENDPOINT + "/add");
        localObject2 = new Result(localPushResponse);
        if (localPushResponse != null)
          Log.c(a, "addTags: " + localPushResponse.code + ", " + localPushResponse.description);
        localObject1 = localObject2;
      }
      while (localPushResponse == null);
      localObject1 = localObject2;
    }
    while (!localPushResponse.code.equals(PushResponse.responseCode.SUCCESS));
    MessageSharedPrefs.getInstance(this.e).addTags(paramArrayOfString);
    MessageSharedPrefs.getInstance(this.e).setTagRemain(((Result)localObject2).remain);
    return localObject2;
  }

  public Result delete(String[] paramArrayOfString)
    throws Exception
  {
    if (c())
      throw new Exception("Tag API is disabled by the server.");
    if (!b())
      throw new Exception("No utdid or device_token");
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      throw new Exception("No tags");
    Object localObject1 = a();
    ((JSONObject)localObject1).put("tags", a(paramArrayOfString));
    Object localObject2 = new l(this.e);
    localObject1 = ((l)localObject2).a(((l)localObject2).a((JSONObject)localObject1), MsgConstant.TAG_ENDPOINT + "/delete");
    localObject2 = new Result((PushResponse)localObject1);
    Log.c(a, "removeTags: " + ((PushResponse)localObject1).code + ", " + ((PushResponse)localObject1).description);
    if ((localObject1 != null) && (((PushResponse)localObject1).code.equals(PushResponse.responseCode.SUCCESS)))
    {
      MessageSharedPrefs.getInstance(this.e).removeTags(paramArrayOfString);
      MessageSharedPrefs.getInstance(this.e).setTagRemain(((Result)localObject2).remain);
    }
    return localObject2;
  }

  public List<String> list()
    throws Exception
  {
    if (c())
      throw new Exception("Tag API is disabled by the server.");
    if (!b())
      throw new Exception("No utdid or device_token");
    Object localObject = a();
    l locall = new l(this.e);
    localObject = locall.a(locall.a((JSONObject)localObject), MsgConstant.TAG_ENDPOINT + "/get");
    if (localObject != null)
      Log.c(a, "listTags: " + ((PushResponse)localObject).code + ", " + ((PushResponse)localObject).description);
    if ((localObject != null) && (((PushResponse)localObject).code.equals(PushResponse.responseCode.SUCCESS)) && (((PushResponse)localObject).info != null) && (((PushResponse)localObject).info.tags != null))
    {
      Log.c(a, ((PushResponse)localObject).info.tags);
      return Arrays.asList(((PushResponse)localObject).info.tags.split(","));
    }
    return null;
  }

  public Result reset()
    throws Exception
  {
    if (c())
      throw new Exception("Tag API is disabled by the server.");
    if (!b())
      throw new Exception("No utdid or device_token");
    Object localObject1 = a();
    Object localObject2 = new l(this.e);
    localObject1 = ((l)localObject2).a(((l)localObject2).a((JSONObject)localObject1), MsgConstant.TAG_ENDPOINT + "/reset");
    localObject2 = new Result((PushResponse)localObject1);
    if (localObject1 != null)
      Log.c(a, "clearTags: " + ((PushResponse)localObject1).code + ", " + ((PushResponse)localObject1).description);
    if ((localObject1 != null) && (((PushResponse)localObject1).code.equals(PushResponse.responseCode.SUCCESS)))
      MessageSharedPrefs.getInstance(this.e).resetTags();
    return localObject2;
  }

  public static class Result
  {
    public String errors;
    public String jsonString;
    public int remain;
    public String status;

    public Result(PushResponse paramPushResponse)
    {
      if (paramPushResponse.code.equals(PushResponse.responseCode.SUCCESS))
        this.status = "success";
      while (true)
      {
        if (paramPushResponse.info != null)
          this.remain = paramPushResponse.info.tagRemainCount.intValue();
        this.errors = paramPushResponse.description;
        this.jsonString = ("status:" + this.status + "," + " remain:" + this.remain + "," + "description:" + this.errors);
        return;
        if (paramPushResponse.code.equals(PushResponse.responseCode.INVALID_REQUEST))
          this.status = "invalid_request";
        else if (paramPushResponse.code.equals(PushResponse.responseCode.SERVER_EXCEPTION))
          this.status = "server_exception";
      }
    }

    public Result(JSONObject paramJSONObject)
    {
      this.status = paramJSONObject.optString("success", "fail");
      this.remain = paramJSONObject.optInt("remain", 0);
      this.errors = paramJSONObject.optString("errors");
      this.jsonString = paramJSONObject.toString();
    }

    public String toString()
    {
      return this.jsonString;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.tag.TagManager
 * JD-Core Version:    0.6.2
 */