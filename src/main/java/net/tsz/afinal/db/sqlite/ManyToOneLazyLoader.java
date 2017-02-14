package net.tsz.afinal.db.sqlite;

import net.tsz.afinal.FinalDb;

public class ManyToOneLazyLoader<M, O>
{
  FinalDb db;
  private Object fieldValue;
  boolean hasLoaded = false;
  Class<M> manyClazz;
  M manyEntity;
  Class<O> oneClazz;
  O oneEntity;

  public ManyToOneLazyLoader(M paramM, Class<M> paramClass, Class<O> paramClass1, FinalDb paramFinalDb)
  {
    this.manyEntity = paramM;
    this.manyClazz = paramClass;
    this.oneClazz = paramClass1;
    this.db = paramFinalDb;
  }

  public O get()
  {
    if ((this.oneEntity == null) && (!this.hasLoaded))
    {
      this.db.loadManyToOne(null, this.manyEntity, this.manyClazz, new Class[] { this.oneClazz });
      this.hasLoaded = true;
    }
    return this.oneEntity;
  }

  public Object getFieldValue()
  {
    return this.fieldValue;
  }

  public void set(O paramO)
  {
    this.oneEntity = paramO;
  }

  public void setFieldValue(Object paramObject)
  {
    this.fieldValue = paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.sqlite.ManyToOneLazyLoader
 * JD-Core Version:    0.6.2
 */