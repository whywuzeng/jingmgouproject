package net.tsz.afinal.db.sqlite;

import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.FinalDb;

public class OneToManyLazyLoader<O, M>
{
  FinalDb db;
  List<M> entities;
  Class<M> listItemClazz;
  Class<O> ownerClazz;
  O ownerEntity;

  public OneToManyLazyLoader(O paramO, Class<O> paramClass, Class<M> paramClass1, FinalDb paramFinalDb)
  {
    this.ownerEntity = paramO;
    this.ownerClazz = paramClass;
    this.listItemClazz = paramClass1;
    this.db = paramFinalDb;
  }

  public List<M> getList()
  {
    if (this.entities == null)
      this.db.loadOneToMany(this.ownerEntity, this.ownerClazz, new Class[] { this.listItemClazz });
    if (this.entities == null)
      this.entities = new ArrayList();
    return this.entities;
  }

  public void setList(List<M> paramList)
  {
    this.entities = paramList;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.sqlite.OneToManyLazyLoader
 * JD-Core Version:    0.6.2
 */