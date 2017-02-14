package com.squareup.wire;

final class f
{
  static void a(Object paramObject, String paramString)
  {
    if (paramObject == null)
      throw new NullPointerException(paramString + " == null");
  }

  static void a(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.f
 * JD-Core Version:    0.6.2
 */