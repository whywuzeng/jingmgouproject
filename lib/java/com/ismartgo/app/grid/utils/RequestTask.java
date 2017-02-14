package com.ismartgo.app.grid.utils;

import android.os.Handler;
import android.util.Log;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

public class RequestTask
{
  private Handler handler;
  private FinalHttp http = getInstance();
  private AjaxCallBack<Object> mAjaxCallBack;
  private AjaxParams params;
  private String url;

  public RequestTask()
  {
  }

  public RequestTask(String paramString, AjaxParams paramAjaxParams, Handler paramHandler)
  {
    this.url = paramString;
    this.params = paramAjaxParams;
    this.handler = paramHandler;
  }

  public RequestTask(String paramString, AjaxParams paramAjaxParams, AjaxCallBack<Object> paramAjaxCallBack)
  {
    this.url = paramString;
    this.params = paramAjaxParams;
    this.mAjaxCallBack = paramAjaxCallBack;
  }

  private FinalHttp getInstance()
  {
    if (this.http == null)
    {
      this.http = new FinalHttp();
      this.http.configCharset("utf-8");
    }
    return this.http;
  }

  public void get()
  {
    if (this.params != null)
    {
      String str = this.params.getParamString();
      Log.i("Test", "p: " + str);
      this.url += "?";
      this.http.get(this.url, this.params, this.mAjaxCallBack);
      return;
    }
    this.http.get(this.url, this.mAjaxCallBack);
  }

  public void get1(String paramString)
  {
    Log.i("Test", "url: " + paramString);
    this.http.get(paramString, this.mAjaxCallBack);
  }

  public void post()
  {
    this.http.post(this.url, this.params, this.mAjaxCallBack);
  }

  public void setHttpParams(String paramString, AjaxParams paramAjaxParams, AjaxCallBack<Object> paramAjaxCallBack)
  {
    this.url = paramString;
    this.params = paramAjaxParams;
    this.mAjaxCallBack = paramAjaxCallBack;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.RequestTask
 * JD-Core Version:    0.6.2
 */