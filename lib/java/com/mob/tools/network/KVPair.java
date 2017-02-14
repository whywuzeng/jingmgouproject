package com.mob.tools.network;

public class KVPair<T>
{
  public final String name;
  public final T value;

  public KVPair(String paramString, T paramT)
  {
    this.name = paramString;
    this.value = paramT;
  }

  public String toString()
  {
    return this.name + " = " + this.value;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.KVPair
 * JD-Core Version:    0.6.2
 */