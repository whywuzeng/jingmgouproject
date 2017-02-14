package com.umeng.message.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map<Ljava.lang.String;Ljava.lang.String;>;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q
{
  private static long a = System.currentTimeMillis();

  public static String a(Map<String, String> paramMap)
  {
    Object localObject1;
    HashMap localHashMap;
    Object localObject2;
    String str;
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      if (!at.a(w.a().i()))
        paramMap.put(v.m.toString(), w.a().i());
      if (!at.a(w.a().f()))
        paramMap.put(v.l.toString(), w.a().f());
      if (!at.a(w.a().j()))
        paramMap.put(v.o.toString(), w.a().j());
      if (!at.a(w.a().g()))
        paramMap.put(v.n.toString(), w.a().g());
      if (!paramMap.containsKey(v.s.toString()))
        paramMap.put(v.s.toString(), w.a().c().b());
      if (!paramMap.containsKey(v.j.toString()))
        paramMap.put(v.j.toString(), w.a().l());
      if (!at.a(w.a().h()))
        paramMap.put(v.i.toString(), w.a().h());
      if (!at.a(w.a().b()))
        paramMap.put(v.k.toString(), w.a().b());
      if (!paramMap.containsKey(v.B.toString()))
        paramMap.put(v.B.toString(), "" + System.currentTimeMillis());
      if (!paramMap.containsKey(v.t.toString()))
        paramMap.put(v.t.toString(), "" + a);
      localObject1 = z.a(w.a().k());
      if (localObject1 != null)
      {
        localHashMap = new HashMap();
        localHashMap.putAll((Map)localObject1);
        localObject1 = paramMap.keySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          if ((!((String)localObject2).equals(v.c.toString())) && (!((String)localObject2).equals(v.d.toString())) && (!((String)localObject2).equals(v.e.toString())) && (!((String)localObject2).equals(v.q.toString())) && (!((String)localObject2).equals(v.r.toString())) && (!((String)localObject2).equals(v.u.toString())))
          {
            str = (String)paramMap.get(localObject2);
            if ((!at.a((String)localObject2)) && (str != null))
              localHashMap.put(localObject2, str);
          }
        }
        localObject1 = (String)localHashMap.get(v.A.toString());
        localObject2 = (String)localHashMap.get("_mac");
        paramMap = (Map<String, String>)localObject1;
        if (localObject2 != null)
        {
          if (localObject1 != null)
          {
            paramMap = String.format("%s,_mac=%s", new Object[] { localObject1, localObject2 });
            localHashMap.remove("_mac");
          }
        }
        else
        {
          localObject1 = (String)localHashMap.get(u.a.toString());
          if (localObject1 == null)
            break label1247;
          if (paramMap == null)
            break label997;
          paramMap = String.format("%s,_did=%s", new Object[] { paramMap, localObject1 });
          label672: localHashMap.remove(u.a.toString());
        }
      }
    }
    label946: label974: label976: label1237: label1247: 
    while (true)
    {
      localObject2 = R.a(w.a().k());
      localObject1 = paramMap;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (localHashMap.containsKey(v.u.toString()))
        {
          localObject1 = localObject2;
          if (((String)localObject2).equals(localHashMap.get(v.u.toString())))
            localObject1 = "utdid";
        }
        if (paramMap == null)
          break label1014;
      }
      int i;
      label885: label1014: for (localObject1 = String.format("%s,_umid=%s", new Object[] { paramMap, localObject1 }); ; localObject1 = String.format("_umid=%s", new Object[] { localObject1 }))
      {
        if (localObject1 != null)
          localHashMap.put(v.A.toString(), localObject1);
        localObject1 = new StringBuffer();
        localObject2 = v.values();
        int j = localObject2.length;
        i = 0;
        if (i < j)
        {
          str = localObject2[i];
          if (str != v.H)
            break label1031;
        }
        if (!localHashMap.containsKey(v.H.toString()))
          break label1237;
        ((StringBuffer)localObject1).append(c(at.a(localHashMap.get(v.H.toString()))));
        localHashMap.remove(v.H.toString());
        i = 0;
        localObject2 = localHashMap.keySet().iterator();
        while (true)
        {
          if (!((Iterator)localObject2).hasNext())
            break label1183;
          str = (String)((Iterator)localObject2).next();
          if (!localHashMap.containsKey(str))
            break label1232;
          paramMap = at.a(localHashMap.get(str));
          if (i == 0)
            break label1121;
          if (!"StackTrace".equals(str))
            break;
          ((StringBuffer)localObject1).append("StackTrace=====>").append(d(paramMap));
          i = 0;
        }
        paramMap = String.format("_mac=%s", new Object[] { localObject2 });
        break;
        paramMap = String.format("_did=%s", new Object[] { localObject1 });
        break label672;
      }
      label997: if (localHashMap.containsKey(str.toString()))
      {
        paramMap = at.a(localHashMap.get(str.toString()));
        localHashMap.remove(str.toString());
      }
      while (true)
      {
        ((StringBuffer)localObject1).append(c(paramMap)).append("||");
        i += 1;
        break;
        ((StringBuffer)localObject1).append(c(str)).append("=").append(d(paramMap));
        break label974;
        label1121: if ("StackTrace".equals(str))
        {
          ((StringBuffer)localObject1).append(",").append("StackTrace=====>").append(d(paramMap));
          break label976;
        }
        ((StringBuffer)localObject1).append(",").append(c(str)).append("=").append(d(paramMap));
        break label976;
        localObject1 = ((StringBuffer)localObject1).toString();
        paramMap = (Map<String, String>)localObject1;
        if (!at.a((String)localObject1))
        {
          paramMap = (Map<String, String>)localObject1;
          if (((String)localObject1).endsWith("||"))
            paramMap = (String)localObject1 + "-";
        }
        return paramMap;
        return null;
        paramMap = null;
        break label946;
        i = 1;
        break label885;
        paramMap = null;
      }
    }
  }

  public static Map<String, String> a(String paramString)
  {
    int i = 0;
    if (!at.a(paramString))
    {
      HashMap localHashMap = new HashMap();
      String[] arrayOfString = paramString.split("\\|\\|");
      paramString = localHashMap;
      if (arrayOfString != null)
      {
        paramString = localHashMap;
        if (arrayOfString.length > 0)
        {
          v[] arrayOfv = v.values();
          int k = arrayOfv.length;
          int j = 0;
          while (true)
          {
            paramString = localHashMap;
            if (i >= k)
              break;
            paramString = arrayOfv[i];
            if ((j < arrayOfString.length) && (arrayOfString[j] != null))
              localHashMap.put(paramString.toString(), arrayOfString[j]);
            j += 1;
            i += 1;
          }
        }
      }
    }
    else
    {
      paramString = null;
    }
    return paramString;
  }

  private static String b(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (!"".equals(paramString))
        str = Pattern.compile("(\\|\\||[\t\r\n])*").matcher(paramString).replaceAll("");
    }
    return str;
  }

  private static String c(String paramString)
  {
    if (at.a(paramString))
      return "-";
    return b(paramString);
  }

  private static String d(String paramString)
  {
    return b(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.Q
 * JD-Core Version:    0.6.2
 */