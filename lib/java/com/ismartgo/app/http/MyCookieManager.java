package com.ismartgo.app.http;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCookieManager extends CookieManager
{
  public MyCookieManager()
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("sessionid=f564fsaf3asd4f6as35");
    localHashMap.put("Set-Cookie", localArrayList);
    try
    {
      put(new URI("http://www.baidu.com"), localHashMap);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      localURISyntaxException.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.http.MyCookieManager
 * JD-Core Version:    0.6.2
 */