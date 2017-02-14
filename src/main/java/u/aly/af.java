package u.aly;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class af extends av
  implements q
{
  public af(String paramString, Map<String, Object> paramMap, long paramLong, int paramInt)
  {
    a(paramString);
    b(System.currentTimeMillis());
    if (paramMap.size() > 0)
      a(b(paramMap));
    if (paramInt > 0);
    while (true)
    {
      a(paramInt);
      if (paramLong > 0L)
        a(paramLong);
      return;
      paramInt = 1;
    }
  }

  public static ae a(String paramString1, String paramString2, Map<String, Object> paramMap)
  {
    ae localae = new ae();
    localae.b = paramString1;
    localae.c = paramString2;
    localae.d = paramMap;
    return localae;
  }

  public static String b(String paramString1, String paramString2, Map<String, Object> paramMap)
  {
    return paramString1 + paramString2;
  }

  private HashMap<String, bh> b(Map<String, Object> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    HashMap localHashMap = new HashMap();
    int i = 0;
    label207: 
    while ((i < 10) && (paramMap.hasNext()))
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      bh localbh = new bh();
      Object localObject = localEntry.getValue();
      if ((localObject instanceof String))
        localbh.b((String)localObject);
      while (true)
      {
        if (!localbh.k())
          break label207;
        localHashMap.put(localEntry.getKey(), localbh);
        i += 1;
        break;
        if ((localObject instanceof Long))
          localbh.b(((Long)localObject).longValue());
        else if ((localObject instanceof Integer))
          localbh.b(((Integer)localObject).longValue());
        else if ((localObject instanceof Float))
          localbh.b(((Float)localObject).longValue());
        else if ((localObject instanceof Double))
          localbh.b(((Double)localObject).longValue());
      }
    }
    return localHashMap;
  }

  public void a(bn parambn, String paramString)
  {
    Object localObject;
    bc localbc;
    if (parambn.s() > 0)
    {
      localObject = parambn.u().iterator();
      do
      {
        if (!((Iterator)localObject).hasNext())
          break;
        localbc = (bc)((Iterator)localObject).next();
      }
      while (!paramString.equals(localbc.c()));
    }
    while (true)
    {
      localObject = localbc;
      if (localbc == null)
      {
        localObject = new bc();
        ((bc)localObject).a(paramString);
        parambn.a((bc)localObject);
      }
      ((bc)localObject).a(this);
      return;
      localbc = null;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.af
 * JD-Core Version:    0.6.2
 */