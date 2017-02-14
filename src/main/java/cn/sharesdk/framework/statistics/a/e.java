package cn.sharesdk.framework.statistics.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.mob.tools.utils.Ln;
import java.util.ArrayList;

public class e
{
  public static int a = 0;
  public static int b = 1;
  public static int c = 2;

  public static long a(Context paramContext, String paramString, long paramLong)
  {
    if (paramString != null);
    try
    {
      Object localObject = paramString.trim();
      if (localObject == "");
      for (paramLong = -1L; ; paramLong = paramContext.a("message", (ContentValues)localObject))
      {
        return paramLong;
        paramContext = b.a(paramContext);
        localObject = new ContentValues();
        ((ContentValues)localObject).put("post_time", Long.valueOf(paramLong));
        ((ContentValues)localObject).put("message_data", paramString.toString());
      }
    }
    finally
    {
    }
    throw paramContext;
  }

  public static long a(Context paramContext, ArrayList<String> paramArrayList)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 0;
      while (i < paramArrayList.size())
      {
        localStringBuilder.append("'");
        localStringBuilder.append((String)paramArrayList.get(i));
        localStringBuilder.append("'");
        localStringBuilder.append(",");
        i += 1;
      }
      paramArrayList = localStringBuilder.toString().substring(0, localStringBuilder.length() - 1);
      i = b.a(paramContext).a("message", "_id in ( " + paramArrayList + " )", null);
      Ln.e("delete COUNT == %s", new Object[] { Integer.valueOf(i) });
      long l = i;
      return l;
    }
    finally
    {
    }
    throw paramContext;
  }

  public static ArrayList<d> a(Context paramContext)
  {
    try
    {
      if (b.a(paramContext).a("message") > 0);
      for (paramContext = a(paramContext, null, null); ; paramContext = new ArrayList())
        return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private static ArrayList<d> a(Context paramContext, String paramString, String[] paramArrayOfString)
  {
    ArrayList localArrayList;
    while (true)
    {
      try
      {
        localArrayList = new ArrayList();
        d locald = new d();
        StringBuilder localStringBuilder = new StringBuilder();
        paramArrayOfString = b.a(paramContext).a("message", new String[] { "_id", "post_time", "message_data" }, paramString, paramArrayOfString, null);
        paramContext = localStringBuilder;
        paramString = locald;
        if ((paramArrayOfString == null) || (!paramArrayOfString.moveToNext()))
          break;
        paramString.b.add(paramArrayOfString.getString(0));
        if (paramString.b.size() == 100)
        {
          paramContext.append(paramArrayOfString.getString(2));
          paramString.a = paramContext.toString();
          localArrayList.add(paramString);
          paramString = new d();
          paramContext = new StringBuilder();
          continue;
        }
      }
      finally
      {
      }
      paramContext.append(paramArrayOfString.getString(2) + "\n");
    }
    paramArrayOfString.close();
    if (paramString.b.size() != 0)
    {
      paramString.a = paramContext.toString().substring(0, paramContext.length() - 1);
      localArrayList.add(paramString);
    }
    return localArrayList;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.a.e
 * JD-Core Version:    0.6.2
 */