package com.yolanda.nohttp.security;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.yolanda.nohttp.Logger;
import java.io.IOException;
import java.io.InputStream;

public class Certificate
{
  private String assetsCerName;
  private final String keyPass;
  private final Context mContext;
  private int resCerId = -1;

  public Certificate(Context paramContext, int paramInt, String paramString)
  {
    this.mContext = paramContext;
    this.resCerId = paramInt;
    this.keyPass = paramString;
  }

  public Certificate(Context paramContext, String paramString1, String paramString2)
  {
    this.mContext = paramContext;
    this.assetsCerName = paramString1;
    this.keyPass = paramString2;
  }

  public InputStream getInputStream()
  {
    Object localObject3 = null;
    Object localObject1 = localObject3;
    try
    {
      if (!TextUtils.isEmpty(this.assetsCerName))
        localObject1 = this.mContext.getAssets().open(this.assetsCerName);
      localObject3 = localObject1;
      if (localObject1 == null)
      {
        localObject3 = localObject1;
        if (this.resCerId > 0)
          localObject3 = this.mContext.getResources().openRawResource(this.resCerId);
      }
      return localObject3;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Logger.wtf(localIOException);
        Object localObject2 = localObject3;
      }
    }
  }

  public char[] getKeyPassCharArray()
  {
    if (!TextUtils.isEmpty(this.keyPass))
      return this.keyPass.toCharArray();
    return null;
  }

  public String getName()
  {
    if (!TextUtils.isEmpty(this.assetsCerName))
      return this.assetsCerName.replace(".", "");
    if (this.resCerId > 0)
      return Integer.toString(this.resCerId);
    return null;
  }

  public boolean hasKeyPass()
  {
    return !TextUtils.isEmpty(this.keyPass);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.security.Certificate
 * JD-Core Version:    0.6.2
 */