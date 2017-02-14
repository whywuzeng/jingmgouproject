package com.umeng.fb.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Reply
  implements Comparable<Reply>
{
  public static final String CONTENT_TYPE_AUDIO_REPLY = "audio_reply";
  public static final String CONTENT_TYPE_IMAGE_REPLY = "image_reply";
  public static final String CONTENT_TYPE_TEXT_REPLY = "text_reply";
  public static final String STATUS_NOT_SENT = "not_sent";
  public static final String STATUS_SENDING = "sending";
  public static final String STATUS_SENT = "sent";
  public static final String STATUS_WILL_SENT = "will_sent";
  public static final String TYPE_DEV_REPLY = "dev_reply";
  public static final String TYPE_NEW_FEEDBACK = "new_feedback";
  public static final String TYPE_USER_REPLY = "user_reply";
  private static final String a = Reply.class.getName();
  private static final String b = "content";
  private static final String c = "reply_id";
  private static final String d = "type";
  private static final String e = "created_at";
  private static final String f = "status";
  private static final String g = "content_type";
  private static final String h = "audio_duration";
  public float audio_duration;
  public String content;
  public String content_type;
  public long created_at;
  public String feedback_id;
  public String reply_id;
  public String status;
  public String type;

  public Reply(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    this.content = paramString1;
    this.reply_id = paramString2;
    this.type = paramString3;
    this.created_at = paramLong;
    this.status = "not_sent";
    this.content_type = "text_reply";
    this.audio_duration = -1.0F;
  }

  public Reply(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4, float paramFloat)
  {
    this.content = paramString1;
    this.reply_id = paramString2;
    this.type = paramString3;
    this.created_at = paramLong;
    this.status = "not_sent";
    this.content_type = paramString4;
    this.audio_duration = paramFloat;
  }

  public static Reply fromJson(JSONObject paramJSONObject)
    throws JSONException
  {
    String str3 = paramJSONObject.optString("content", "").trim();
    String str4 = paramJSONObject.optString("reply_id", "");
    String str2 = paramJSONObject.getString("type");
    String str1 = paramJSONObject.optString("content_type", null);
    long l = paramJSONObject.getLong("created_at");
    float f1 = (float)paramJSONObject.optLong("audio_duration", -1L);
    Object localObject = str1;
    if (str1 == null)
      localObject = "text_reply";
    localObject = new Reply(str3, str4, str2, l, (String)localObject, f1);
    if ("dev_reply".equals(str2))
    {
      ((Reply)localObject).status = "sent";
      return localObject;
    }
    ((Reply)localObject).status = paramJSONObject.optString("status", "not_sent");
    return localObject;
  }

  public int compareTo(Reply paramReply)
  {
    long l = this.created_at - paramReply.created_at;
    if (l > 0L)
      return 1;
    if (l == 0L)
      return 0;
    return -1;
  }

  public JSONObject toJson()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("content", this.content);
      localJSONObject.put("reply_id", this.reply_id);
      localJSONObject.put("type", this.type);
      localJSONObject.put("created_at", this.created_at);
      localJSONObject.put("status", this.status);
      localJSONObject.put("content_type", this.content_type);
      localJSONObject.put("audio_duration", this.audio_duration);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }

  public String toString()
  {
    return toJson().toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.Reply
 * JD-Core Version:    0.6.2
 */