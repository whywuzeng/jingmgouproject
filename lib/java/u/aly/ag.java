package u.aly;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class ag extends at
  implements q
{
  public ag()
  {
    a(System.currentTimeMillis());
    a(au.a);
  }

  public ag(String paramString)
  {
    this();
    a(paramString);
  }

  public ag(Throwable paramThrowable)
  {
    this();
    a(a(paramThrowable));
  }

  private String a(Throwable paramThrowable)
  {
    Object localObject2 = null;
    if (paramThrowable == null)
      return null;
    Object localObject1 = localObject2;
    try
    {
      StringWriter localStringWriter = new StringWriter();
      localObject1 = localObject2;
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
      localObject1 = localObject2;
      paramThrowable.printStackTrace(localPrintWriter);
      localObject1 = localObject2;
      for (paramThrowable = paramThrowable.getCause(); paramThrowable != null; paramThrowable = paramThrowable.getCause())
      {
        localObject1 = localObject2;
        paramThrowable.printStackTrace(localPrintWriter);
        localObject1 = localObject2;
      }
      localObject1 = localObject2;
      paramThrowable = localStringWriter.toString();
      localObject1 = paramThrowable;
      localPrintWriter.close();
      localObject1 = paramThrowable;
      localStringWriter.close();
      return paramThrowable;
    }
    catch (Exception paramThrowable)
    {
      paramThrowable.printStackTrace();
    }
    return localObject1;
  }

  public ag a(boolean paramBoolean)
  {
    if (paramBoolean);
    for (au localau = au.a; ; localau = au.b)
    {
      a(localau);
      return this;
    }
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
 * Qualified Name:     u.aly.ag
 * JD-Core Version:    0.6.2
 */