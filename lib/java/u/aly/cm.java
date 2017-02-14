package u.aly;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class cm
{
  public static cl a(Class<? extends cl> paramClass, int paramInt)
  {
    try
    {
      paramClass = (cl)paramClass.getMethod("findByValue", new Class[] { Integer.TYPE }).invoke(null, new Object[] { Integer.valueOf(paramInt) });
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      return null;
    }
    catch (IllegalAccessException paramClass)
    {
      return null;
    }
    catch (InvocationTargetException paramClass)
    {
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.cm
 * JD-Core Version:    0.6.2
 */