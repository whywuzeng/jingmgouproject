package com.ab.view.chart;

import java.util.Map.Entry;

public class XYEntry<K, V>
  implements Map.Entry<K, V>
{
  private final K key;
  private V value;

  public XYEntry(K paramK, V paramV)
  {
    this.key = paramK;
    this.value = paramV;
  }

  public K getKey()
  {
    return this.key;
  }

  public V getValue()
  {
    return this.value;
  }

  public V setValue(V paramV)
  {
    this.value = paramV;
    return this.value;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.XYEntry
 * JD-Core Version:    0.6.2
 */