package com.baidu.location.f.a;

import android.content.Context;
import android.text.TextUtils;

public class a
{
  private static final boolean a = false;
  private static final String jdField_if = a.class.getSimpleName();

  private static String a(Context paramContext)
  {
    return b.a(paramContext);
  }

  public static String jdField_if(Context paramContext)
  {
    String str2 = a(paramContext);
    String str1 = b.jdMethod_do(paramContext);
    paramContext = str1;
    if (TextUtils.isEmpty(str1))
      paramContext = "0";
    paramContext = new StringBuffer(paramContext).reverse().toString();
    return str2 + "|" + paramContext;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.f.a.a
 * JD-Core Version:    0.6.2
 */