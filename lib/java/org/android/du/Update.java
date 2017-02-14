package org.android.du;

import android.content.Context;
import com.umeng.message.proguard.bI;
import com.umeng.message.proguard.bN;
import org.android.agoo.ut.UTFactroy;

public class Update
{
  public static final String ACTION_DU_UPDATE_SUCCESS = "com.taobao.du.update";
  private bI mClassLoaderEngine;
  private String mComponentId;
  private Context mContext;
  private bN mInvokeUtil;
  private boolean mLoadclassSuccess;

  protected Update(Context paramContext, String paramString)
  {
    this.mComponentId = paramString;
    this.mContext = paramContext;
    this.mLoadclassSuccess = loadClass(paramContext);
  }

  private boolean loadClass(Context paramContext)
  {
    try
    {
      this.mClassLoaderEngine = new bI(paramContext, this.mComponentId);
      this.mInvokeUtil = new bN(this.mClassLoaderEngine.c());
      return this.mClassLoaderEngine.a();
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public <T> T getBean(Class<T> paramClass)
  {
    if (paramClass == null)
    {
      UTFactroy.getInstance().commitEvent(this.mContext, "getBean", new String[] { "ERROR_EVENT_GETBEAN_ERROR", "cls param cla==null" });
      return null;
    }
    if (this.mClassLoaderEngine.a(paramClass) != null)
      return getBean(this.mClassLoaderEngine.a(paramClass));
    return getBean(paramClass.getName());
  }

  public Object getBean(String paramString)
  {
    return this.mInvokeUtil.a(this.mContext, paramString, null, null);
  }

  public Object getBean(String paramString1, String paramString2)
  {
    if (this.mLoadclassSuccess)
      return this.mInvokeUtil.a(this.mContext, paramString2, null, null);
    return this.mInvokeUtil.a(this.mContext, paramString1, null, null);
  }

  public Object getClassInstance(String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    return this.mInvokeUtil.a(this.mContext, paramString, paramArrayOfClass, paramArrayOfObject);
  }

  public ClassLoader getClassLoader()
  {
    if (this.mClassLoaderEngine == null)
      return null;
    return this.mClassLoaderEngine.c();
  }

  public Object invokeClassMethod(String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    return this.mInvokeUtil.a(this.mContext, paramString1, paramString2, paramArrayOfClass, paramArrayOfObject);
  }

  public Object invokeMethod(Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    return bN.a(this.mContext, paramObject, paramString, paramArrayOfClass, paramArrayOfObject);
  }

  public boolean ismLoadclassSuccess()
  {
    return this.mLoadclassSuccess;
  }

  public void setmLoadclassSuccess(boolean paramBoolean)
  {
    this.mLoadclassSuccess = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.du.Update
 * JD-Core Version:    0.6.2
 */