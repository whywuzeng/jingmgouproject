package u.aly;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ct
  implements Serializable
{
  private static Map<Class<? extends ch>, Map<? extends co, ct>> d = new HashMap();
  public final String a;
  public final byte b;
  public final cu c;

  public ct(String paramString, byte paramByte, cu paramcu)
  {
    this.a = paramString;
    this.b = paramByte;
    this.c = paramcu;
  }

  public static Map<? extends co, ct> a(Class<? extends ch> paramClass)
  {
    if (!d.containsKey(paramClass));
    try
    {
      paramClass.newInstance();
      return (Map)d.get(paramClass);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new RuntimeException("InstantiationException for TBase class: " + paramClass.getName() + ", message: " + localInstantiationException.getMessage());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException("IllegalAccessException for TBase class: " + paramClass.getName() + ", message: " + localIllegalAccessException.getMessage());
    }
  }

  public static void a(Class<? extends ch> paramClass, Map<? extends co, ct> paramMap)
  {
    d.put(paramClass, paramMap);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.ct
 * JD-Core Version:    0.6.2
 */