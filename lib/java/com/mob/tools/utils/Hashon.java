package com.mob.tools.utils;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Hashon
{
  private ArrayList<?> arrayToList(Object paramObject)
  {
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    int i = 0;
    ArrayList localArrayList;
    Object localObject;
    if ((paramObject instanceof byte[]))
    {
      localArrayList = new ArrayList();
      localObject = (byte[])paramObject;
      j = localObject.length;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(Byte.valueOf(localObject[i]));
        i += 1;
      }
    }
    if ((paramObject instanceof short[]))
    {
      localArrayList = new ArrayList();
      localObject = (short[])paramObject;
      k = localObject.length;
      i = j;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= k)
          break;
        localArrayList.add(Short.valueOf(localObject[i]));
        i += 1;
      }
    }
    if ((paramObject instanceof int[]))
    {
      localArrayList = new ArrayList();
      localObject = (int[])paramObject;
      j = localObject.length;
      i = k;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(Integer.valueOf(localObject[i]));
        i += 1;
      }
    }
    if ((paramObject instanceof long[]))
    {
      localArrayList = new ArrayList();
      localObject = (long[])paramObject;
      j = localObject.length;
      i = m;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(Long.valueOf(localObject[i]));
        i += 1;
      }
    }
    if ((paramObject instanceof float[]))
    {
      localArrayList = new ArrayList();
      localObject = (float[])paramObject;
      j = localObject.length;
      i = n;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(Float.valueOf(localObject[i]));
        i += 1;
      }
    }
    if ((paramObject instanceof double[]))
    {
      localArrayList = new ArrayList();
      localObject = (double[])paramObject;
      j = localObject.length;
      i = i1;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(Double.valueOf(localObject[i]));
        i += 1;
      }
    }
    if ((paramObject instanceof char[]))
    {
      localArrayList = new ArrayList();
      localObject = (char[])paramObject;
      j = localObject.length;
      i = i2;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(Character.valueOf(localObject[i]));
        i += 1;
      }
    }
    if ((paramObject instanceof boolean[]))
    {
      localArrayList = new ArrayList();
      localObject = (boolean[])paramObject;
      j = localObject.length;
      i = i3;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(Boolean.valueOf(localObject[i]));
        i += 1;
      }
    }
    if ((paramObject instanceof String[]))
    {
      localArrayList = new ArrayList();
      localObject = (String[])paramObject;
      j = localObject.length;
      i = i4;
      while (true)
      {
        paramObject = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(localObject[i]);
        i += 1;
      }
    }
    paramObject = null;
    return paramObject;
  }

  private String format(String paramString, ArrayList<Object> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("[\n");
    String str = paramString + "\t";
    paramArrayList = paramArrayList.iterator();
    int i = 0;
    if (paramArrayList.hasNext())
    {
      Object localObject = paramArrayList.next();
      if (i > 0)
        localStringBuffer.append(",\n");
      localStringBuffer.append(str);
      if ((localObject instanceof HashMap))
        localStringBuffer.append(format(str, (HashMap)localObject));
      while (true)
      {
        i += 1;
        break;
        if ((localObject instanceof ArrayList))
          localStringBuffer.append(format(str, (ArrayList)localObject));
        else if ((localObject instanceof String))
          localStringBuffer.append('"').append(localObject).append('"');
        else
          localStringBuffer.append(localObject);
      }
    }
    localStringBuffer.append('\n').append(paramString).append(']');
    return localStringBuffer.toString();
  }

  private String format(String paramString, HashMap<String, Object> paramHashMap)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("{\n");
    String str = paramString + "\t";
    paramHashMap = paramHashMap.entrySet().iterator();
    int i = 0;
    if (paramHashMap.hasNext())
    {
      Object localObject = (Map.Entry)paramHashMap.next();
      if (i > 0)
        localStringBuffer.append(",\n");
      localStringBuffer.append(str).append('"').append((String)((Map.Entry)localObject).getKey()).append("\":");
      localObject = ((Map.Entry)localObject).getValue();
      if ((localObject instanceof HashMap))
        localStringBuffer.append(format(str, (HashMap)localObject));
      while (true)
      {
        i += 1;
        break;
        if ((localObject instanceof ArrayList))
          localStringBuffer.append(format(str, (ArrayList)localObject));
        else if ((localObject instanceof String))
          localStringBuffer.append('"').append(localObject).append('"');
        else
          localStringBuffer.append(localObject);
      }
    }
    localStringBuffer.append('\n').append(paramString).append('}');
    return localStringBuffer.toString();
  }

  private ArrayList<Object> fromJson(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramJSONArray.length();
    int i = 0;
    if (i < j)
    {
      Object localObject2 = paramJSONArray.opt(i);
      Object localObject1;
      if ((localObject2 instanceof JSONObject))
        localObject1 = fromJson((JSONObject)localObject2);
      while (true)
      {
        localArrayList.add(localObject1);
        i += 1;
        break;
        localObject1 = localObject2;
        if ((localObject2 instanceof JSONArray))
          localObject1 = fromJson((JSONArray)localObject2);
      }
    }
    return localArrayList;
  }

  private HashMap<String, Object> fromJson(JSONObject paramJSONObject)
    throws JSONException
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject2 = paramJSONObject.opt(str);
      Object localObject1 = localObject2;
      if (JSONObject.NULL.equals(localObject2))
        localObject1 = null;
      if (localObject1 != null)
      {
        if ((localObject1 instanceof JSONObject))
          localObject2 = fromJson((JSONObject)localObject1);
        while (true)
        {
          localHashMap.put(str, localObject2);
          break;
          localObject2 = localObject1;
          if ((localObject1 instanceof JSONArray))
            localObject2 = fromJson((JSONArray)localObject1);
        }
      }
    }
    return localHashMap;
  }

  private JSONArray getJSONArray(ArrayList<Object> paramArrayList)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = paramArrayList.iterator();
    if (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((localObject instanceof HashMap))
        paramArrayList = getJSONObject((HashMap)localObject);
      while (true)
      {
        localJSONArray.put(paramArrayList);
        break;
        paramArrayList = localObject;
        if ((localObject instanceof ArrayList))
          paramArrayList = getJSONArray((ArrayList)localObject);
      }
    }
    return localJSONArray;
  }

  private JSONObject getJSONObject(HashMap<String, Object> paramHashMap)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = localEntry.getValue();
      if ((localObject instanceof HashMap))
        paramHashMap = getJSONObject((HashMap)localObject);
      while (true)
      {
        localJSONObject.put((String)localEntry.getKey(), paramHashMap);
        break;
        if ((localObject instanceof ArrayList))
        {
          paramHashMap = getJSONArray((ArrayList)localObject);
        }
        else
        {
          paramHashMap = localObject;
          if (isBasicArray(localObject))
            paramHashMap = getJSONArray(arrayToList(localObject));
        }
      }
    }
    return localJSONObject;
  }

  private boolean isBasicArray(Object paramObject)
  {
    return ((paramObject instanceof byte[])) || ((paramObject instanceof short[])) || ((paramObject instanceof int[])) || ((paramObject instanceof long[])) || ((paramObject instanceof float[])) || ((paramObject instanceof double[])) || ((paramObject instanceof char[])) || ((paramObject instanceof boolean[])) || ((paramObject instanceof String[]));
  }

  public String format(String paramString)
  {
    try
    {
      paramString = format("", fromJson(paramString));
      return paramString;
    }
    catch (Throwable paramString)
    {
      Ln.w(paramString);
    }
    return "";
  }

  public String fromHashMap(HashMap<String, Object> paramHashMap)
  {
    try
    {
      paramHashMap = getJSONObject(paramHashMap);
      if (paramHashMap == null)
        return "";
      paramHashMap = paramHashMap.toString();
      return paramHashMap;
    }
    catch (Throwable paramHashMap)
    {
      Ln.w(paramHashMap);
    }
    return "";
  }

  public HashMap<String, Object> fromJson(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return new HashMap();
    String str1 = paramString;
    String str2 = paramString;
    try
    {
      if (paramString.startsWith("["))
      {
        str1 = paramString;
        str2 = paramString;
        if (paramString.endsWith("]"))
        {
          str2 = paramString;
          str1 = "{\"fakelist\":" + paramString + "}";
        }
      }
      str2 = str1;
      paramString = fromJson(new JSONObject(str1));
      return paramString;
    }
    catch (Throwable paramString)
    {
      Ln.e(str2, new Object[0]);
      Ln.w(paramString);
    }
    return new HashMap();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.utils.Hashon
 * JD-Core Version:    0.6.2
 */