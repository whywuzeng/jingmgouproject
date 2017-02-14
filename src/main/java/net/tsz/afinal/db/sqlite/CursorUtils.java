package net.tsz.afinal.db.sqlite;

import android.database.Cursor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.db.table.Id;
import net.tsz.afinal.db.table.ManyToOne;
import net.tsz.afinal.db.table.OneToMany;
import net.tsz.afinal.db.table.Property;
import net.tsz.afinal.db.table.TableInfo;

public class CursorUtils
{
  public static <T> T dbModel2Entity(DbModel paramDbModel, Class<?> paramClass)
  {
    if (paramDbModel != null)
      paramDbModel = paramDbModel.getDataMap();
    Object localObject1;
    String str;
    TableInfo localTableInfo;
    label110: 
    do
      while (true)
      {
        try
        {
          localObject1 = paramClass.newInstance();
          Iterator localIterator = paramDbModel.entrySet().iterator();
          if (!localIterator.hasNext())
            return localObject1;
          paramDbModel = (Map.Entry)localIterator.next();
          str = (String)paramDbModel.getKey();
          localTableInfo = TableInfo.get(paramClass);
          localObject2 = (Property)localTableInfo.propertyMap.get(str);
          if (localObject2 == null)
            break;
          if (paramDbModel.getValue() != null)
            break label110;
          paramDbModel = null;
          ((Property)localObject2).setValue(localObject1, paramDbModel);
          continue;
        }
        catch (Exception paramDbModel)
        {
          paramDbModel.printStackTrace();
        }
        return null;
        paramDbModel = paramDbModel.getValue().toString();
      }
    while (!localTableInfo.getId().getColumn().equals(str));
    Object localObject2 = localTableInfo.getId();
    if (paramDbModel.getValue() == null);
    for (paramDbModel = null; ; paramDbModel = paramDbModel.getValue().toString())
    {
      ((Id)localObject2).setValue(localObject1, paramDbModel);
      break;
    }
  }

  public static DbModel getDbModel(Cursor paramCursor)
  {
    if ((paramCursor != null) && (paramCursor.getColumnCount() > 0))
    {
      DbModel localDbModel = new DbModel();
      int j = paramCursor.getColumnCount();
      int i = 0;
      while (true)
      {
        if (i >= j)
          return localDbModel;
        localDbModel.set(paramCursor.getColumnName(i), paramCursor.getString(i));
        i += 1;
      }
    }
    return null;
  }

  public static <T> T getEntity(Cursor paramCursor, Class<T> paramClass, FinalDb paramFinalDb)
  {
    if (paramCursor != null);
    while (true)
    {
      Object localObject2;
      Object localObject1;
      int i;
      Object localObject3;
      try
      {
        localObject2 = TableInfo.get(paramClass);
        int j = paramCursor.getColumnCount();
        if (j > 0)
        {
          localObject1 = paramClass.newInstance();
          i = 0;
          if (i >= j)
          {
            localObject3 = ((TableInfo)localObject2).oneToManyMap.values().iterator();
            if (((Iterator)localObject3).hasNext())
              break label179;
            localObject2 = ((TableInfo)localObject2).manyToOneMap.values().iterator();
            if (((Iterator)localObject2).hasNext())
              break label227;
            return localObject1;
          }
          localObject3 = paramCursor.getColumnName(i);
          localObject4 = (Property)((TableInfo)localObject2).propertyMap.get(localObject3);
          if (localObject4 != null)
            ((Property)localObject4).setValue(localObject1, paramCursor.getString(i));
          else if (((TableInfo)localObject2).getId().getColumn().equals(localObject3))
            ((TableInfo)localObject2).getId().setValue(localObject1, paramCursor.getString(i));
        }
      }
      catch (Exception paramCursor)
      {
        paramCursor.printStackTrace();
      }
      return null;
      label179: Object localObject4 = (OneToMany)((Iterator)localObject3).next();
      if (((OneToMany)localObject4).getDataType() == OneToManyLazyLoader.class)
      {
        ((OneToMany)localObject4).setValue(localObject1, new OneToManyLazyLoader(localObject1, paramClass, ((OneToMany)localObject4).getOneClass(), paramFinalDb));
        continue;
        label227: localObject3 = (ManyToOne)((Iterator)localObject2).next();
        if (((ManyToOne)localObject3).getDataType() == ManyToOneLazyLoader.class)
        {
          localObject4 = new ManyToOneLazyLoader(localObject1, paramClass, ((ManyToOne)localObject3).getManyClass(), paramFinalDb);
          ((ManyToOneLazyLoader)localObject4).setFieldValue(Integer.valueOf(paramCursor.getInt(paramCursor.getColumnIndex(((ManyToOne)localObject3).getColumn()))));
          ((ManyToOne)localObject3).setValue(localObject1, localObject4);
          continue;
          i += 1;
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.db.sqlite.CursorUtils
 * JD-Core Version:    0.6.2
 */