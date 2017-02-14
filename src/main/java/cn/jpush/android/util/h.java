package cn.jpush.android.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;

public final class h
{
  public static ArrayList<String> a(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0))
      return localArrayList;
    int i = 0;
    while (i < paramJSONArray.length())
    {
      String str = paramJSONArray.optString(i);
      if (!TextUtils.isEmpty(str))
        localArrayList.add(str);
      i += 1;
    }
    return localArrayList;
  }

  public static JSONArray a(ArrayList<String> paramArrayList)
  {
    JSONArray localJSONArray = new JSONArray();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
      localJSONArray.put((String)paramArrayList.next());
    return localJSONArray;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.h
 * JD-Core Version:    0.6.2
 */