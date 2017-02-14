package com.ismartgo.app.grid.utils;

import net.tsz.afinal.http.AjaxParams;

public class RequestParamUtils
{
  public static AjaxParams waterFallRequest(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    AjaxParams localAjaxParams = new AjaxParams();
    localAjaxParams.put("uid", paramString1);
    localAjaxParams.put("mode", paramString2);
    localAjaxParams.put("city", paramString3);
    localAjaxParams.put("lon", paramString4);
    localAjaxParams.put("lat", paramString5);
    localAjaxParams.put("page", paramString6);
    return localAjaxParams;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.RequestParamUtils
 * JD-Core Version:    0.6.2
 */