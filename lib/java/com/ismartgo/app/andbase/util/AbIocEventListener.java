package com.ismartgo.app.andbase.util;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import com.ab.global.AbAppException;
import java.lang.reflect.Method;

public class AbIocEventListener
  implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemLongClickListener
{
  public static final int CLICK = 0;
  public static final int ITEMCLICK = 2;
  public static final int ITEMLONGCLICK = 3;
  public static final int LONGCLICK = 1;
  private String clickMethod;
  private Object handler;
  private String itemClickMethod;
  private String itemLongClickMehtod;
  private String itemSelectMethod;
  private String longClickMethod;
  private String nothingSelectedMethod;

  public AbIocEventListener(Object paramObject)
  {
    this.handler = paramObject;
  }

  private Object invokeClickMethod(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if (paramObject == null)
      return null;
    try
    {
      Method localMethod = paramObject.getClass().getDeclaredMethod(paramString, new Class[] { View.class });
      if (localMethod != null)
        return localMethod.invoke(paramObject, paramArrayOfObject);
      throw new AbAppException("no such method:" + paramString);
    }
    catch (Exception paramObject)
    {
      paramObject.printStackTrace();
    }
    return null;
  }

  private Object invokeItemClickMethod(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if (paramObject == null)
      return null;
    try
    {
      Method localMethod = paramObject.getClass().getDeclaredMethod(paramString, new Class[] { AdapterView.class, View.class, Integer.TYPE, Long.TYPE });
      if (localMethod != null)
        return localMethod.invoke(paramObject, paramArrayOfObject);
      throw new AbAppException("no such method:" + paramString);
    }
    catch (Exception paramObject)
    {
      paramObject.printStackTrace();
    }
    return null;
  }

  private boolean invokeItemLongClickMethod(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if (paramObject == null)
      try
      {
        throw new AbAppException("invokeItemLongClickMethod: handler is null :");
      }
      catch (Exception paramObject)
      {
        paramObject.printStackTrace();
        return false;
      }
    Method localMethod = paramObject.getClass().getDeclaredMethod(paramString, new Class[] { AdapterView.class, View.class, Integer.TYPE, Long.TYPE });
    if (localMethod != null)
    {
      paramObject = localMethod.invoke(paramObject, paramArrayOfObject);
      if (paramObject == null);
      for (boolean bool = false; ; bool = Boolean.valueOf(paramObject.toString()).booleanValue())
        return Boolean.valueOf(bool).booleanValue();
    }
    throw new AbAppException("no such method:" + paramString);
  }

  private Object invokeItemSelectMethod(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if (paramObject == null)
      return null;
    try
    {
      Method localMethod = paramObject.getClass().getDeclaredMethod(paramString, new Class[] { AdapterView.class, View.class, Integer.TYPE, Long.TYPE });
      if (localMethod != null)
        return localMethod.invoke(paramObject, paramArrayOfObject);
      throw new AbAppException("no such method:" + paramString);
    }
    catch (Exception paramObject)
    {
      paramObject.printStackTrace();
    }
    return null;
  }

  private boolean invokeLongClickMethod(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if (paramObject == null);
    while (true)
    {
      return false;
      try
      {
        Method localMethod = paramObject.getClass().getDeclaredMethod(paramString, new Class[] { View.class });
        if (localMethod != null)
        {
          paramObject = localMethod.invoke(paramObject, paramArrayOfObject);
          if (paramObject != null)
            return Boolean.valueOf(paramObject.toString()).booleanValue();
        }
        else
        {
          throw new AbAppException("no such method:" + paramString);
        }
      }
      catch (Exception paramObject)
      {
        paramObject.printStackTrace();
      }
    }
    return false;
  }

  private Object invokeNoSelectMethod(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    if (paramObject == null)
      return null;
    try
    {
      Method localMethod = paramObject.getClass().getDeclaredMethod(paramString, new Class[] { AdapterView.class });
      if (localMethod != null)
        return localMethod.invoke(paramObject, paramArrayOfObject);
      throw new AbAppException("no such method:" + paramString);
    }
    catch (Exception paramObject)
    {
      paramObject.printStackTrace();
    }
    return null;
  }

  public AbIocEventListener click(String paramString)
  {
    this.clickMethod = paramString;
    return this;
  }

  public AbIocEventListener itemClick(String paramString)
  {
    this.itemClickMethod = paramString;
    return this;
  }

  public AbIocEventListener itemLongClick(String paramString)
  {
    this.itemLongClickMehtod = paramString;
    return this;
  }

  public AbIocEventListener longClick(String paramString)
  {
    this.longClickMethod = paramString;
    return this;
  }

  public AbIocEventListener noSelect(String paramString)
  {
    this.nothingSelectedMethod = paramString;
    return this;
  }

  public void onClick(View paramView)
  {
    invokeClickMethod(this.handler, this.clickMethod, new Object[] { paramView });
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    invokeItemClickMethod(this.handler, this.itemClickMethod, new Object[] { paramAdapterView, paramView, Integer.valueOf(paramInt), Long.valueOf(paramLong) });
  }

  public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    return invokeItemLongClickMethod(this.handler, this.itemLongClickMehtod, new Object[] { paramAdapterView, paramView, Integer.valueOf(paramInt), Long.valueOf(paramLong) });
  }

  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    invokeItemSelectMethod(this.handler, this.itemSelectMethod, new Object[] { paramAdapterView, paramView, Integer.valueOf(paramInt), Long.valueOf(paramLong) });
  }

  public boolean onLongClick(View paramView)
  {
    return invokeLongClickMethod(this.handler, this.longClickMethod, new Object[] { paramView });
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
    invokeNoSelectMethod(this.handler, this.nothingSelectedMethod, new Object[] { paramAdapterView });
  }

  public AbIocEventListener select(String paramString)
  {
    this.itemSelectMethod = paramString;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbIocEventListener
 * JD-Core Version:    0.6.2
 */