package com.baidu.location.c;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

 enum m
{
  m(String paramString3, String paramInt1, String paramInt2, int arg6, int arg7)
  {
    super(paramString1, paramString2, paramString3, paramInt1, paramInt2, i, j, null);
  }

  List a(JSONObject paramJSONObject, String paramString, int paramInt)
  {
    Iterator localIterator = paramJSONObject.keys();
    StringBuffer localStringBuffer1 = new StringBuffer();
    StringBuffer localStringBuffer2 = new StringBuffer();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (true)
    {
      String str1;
      String str2;
      String str3;
      String str4;
      String str5;
      String str6;
      String str7;
      if (localIterator.hasNext())
      {
        str1 = null;
        str2 = null;
        str3 = null;
        str4 = null;
        str5 = null;
        str6 = null;
        str7 = (String)localIterator.next();
      }
      try
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject(str7);
        if (localJSONObject.has("cy"))
          str1 = localJSONObject.getString("cy");
        if (localJSONObject.has("cyc"))
          str2 = localJSONObject.getString("cyc");
        if (localJSONObject.has("prov"))
          str3 = localJSONObject.getString("prov");
        if (localJSONObject.has("ctc"))
          str4 = localJSONObject.getString("ctc");
        if (localJSONObject.has("ct"))
          str5 = localJSONObject.getString("ct");
        if (localJSONObject.has("dist"))
          str6 = localJSONObject.getString("dist");
        if (localStringBuffer1.length() > 0)
          localStringBuffer1.append(",");
        localStringBuffer1.append("(\"").append(str7).append("\",\"").append(str1).append("\",\"").append(str2).append("\",\"").append(str3).append("\",\"").append(str5).append("\",\"").append(str4).append("\",\"").append(str6).append("\",").append(System.currentTimeMillis() / 1000L).append(",\"\")");
        k.b.a(localStringBuffer2, str7, paramString, 0);
        label316: if ((i % 50 == 49) && (localStringBuffer1.length() > 0))
        {
          localArrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[] { "RGCAREA", localStringBuffer1 }));
          localArrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[] { "RGCUPDATE", localStringBuffer2 }));
          localStringBuffer1.setLength(0);
        }
        i += 1;
        continue;
        if (localStringBuffer1.length() > 0)
        {
          localArrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[] { "RGCAREA", localStringBuffer1 }));
          localArrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[] { "RGCUPDATE", localStringBuffer2 }));
          localStringBuffer1.setLength(0);
        }
        localArrayList.add(String.format(Locale.US, "DELETE FROM RGCAREA WHERE gridkey NOT IN (SELECT gridkey FROM RGCAREA LIMIT %d);", new Object[] { Integer.valueOf(paramInt) }));
        return localArrayList;
      }
      catch (JSONException localJSONException)
      {
        break label316;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c.m
 * JD-Core Version:    0.6.2
 */