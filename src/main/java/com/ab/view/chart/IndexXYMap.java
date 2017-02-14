package com.ab.view.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class IndexXYMap<K, V> extends TreeMap<K, V>
{
  private final List<K> indexList = new ArrayList();
  private double maxXDifference = 0.0D;

  private void updateMaxXDifference()
  {
    if (this.indexList.size() < 2)
      this.maxXDifference = 0.0D;
    while (Math.abs(((Double)this.indexList.get(this.indexList.size() - 1)).doubleValue() - ((Double)this.indexList.get(this.indexList.size() - 2)).doubleValue()) <= this.maxXDifference)
      return;
    this.maxXDifference = Math.abs(((Double)this.indexList.get(this.indexList.size() - 1)).doubleValue() - ((Double)this.indexList.get(this.indexList.size() - 2)).doubleValue());
  }

  public void clear()
  {
    updateMaxXDifference();
    super.clear();
    this.indexList.clear();
  }

  public XYEntry<K, V> getByIndex(int paramInt)
  {
    Object localObject = this.indexList.get(paramInt);
    return new XYEntry(localObject, get(localObject));
  }

  public int getIndexForKey(K paramK)
  {
    return Collections.binarySearch(this.indexList, paramK, null);
  }

  public double getMaxXDifference()
  {
    return this.maxXDifference;
  }

  public K getXByIndex(int paramInt)
  {
    return this.indexList.get(paramInt);
  }

  public V getYByIndex(int paramInt)
  {
    return get(this.indexList.get(paramInt));
  }

  public V put(K paramK, V paramV)
  {
    this.indexList.add(paramK);
    updateMaxXDifference();
    return super.put(paramK, paramV);
  }

  public XYEntry<K, V> removeByIndex(int paramInt)
  {
    Object localObject = this.indexList.remove(paramInt);
    return new XYEntry(localObject, remove(localObject));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.IndexXYMap
 * JD-Core Version:    0.6.2
 */