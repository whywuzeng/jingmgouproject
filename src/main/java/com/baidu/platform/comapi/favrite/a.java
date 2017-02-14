package com.baidu.platform.comapi.favrite;

import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  private static a b = null;
  private com.baidu.platform.comjni.map.favorite.a a = null;
  private boolean c = false;
  private boolean d = false;
  private Vector<String> e = null;
  private Vector<String> f = null;
  private boolean g = false;
  private c h = new c(null);
  private b i = new b(null);

  public static a a()
  {
    if (b == null);
    try
    {
      if (b == null)
      {
        b = new a();
        b.h();
      }
      return b;
    }
    finally
    {
    }
  }

  public static boolean g()
  {
    return (b != null) && (b.a != null) && (b.a.d());
  }

  private boolean h()
  {
    boolean bool = true;
    if (this.a == null)
    {
      this.a = new com.baidu.platform.comjni.map.favorite.a();
      if (this.a.a() == 0L)
      {
        this.a = null;
        bool = false;
      }
    }
    else
    {
      return bool;
    }
    j();
    i();
    return true;
  }

  private boolean i()
  {
    if (this.a == null)
      return false;
    String str = SysOSUtil.getModuleFileName() + "/";
    this.a.a(1);
    return this.a.a(str, "fav_poi", "fifo", 10, 501, -1);
  }

  private void j()
  {
    this.c = false;
    this.d = false;
  }

  // ERROR //
  public int a(String paramString, FavSyncPoi paramFavSyncPoi)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 38	com/baidu/platform/comapi/favrite/a:a	Lcom/baidu/platform/comjni/map/favorite/a;
    //   6: astore 4
    //   8: aload 4
    //   10: ifnonnull +9 -> 19
    //   13: iconst_0
    //   14: istore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_3
    //   18: ireturn
    //   19: aload_1
    //   20: ifnull +401 -> 421
    //   23: aload_1
    //   24: ldc 107
    //   26: invokevirtual 113	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   29: ifne +392 -> 421
    //   32: aload_2
    //   33: ifnonnull +6 -> 39
    //   36: goto +385 -> 421
    //   39: aload_0
    //   40: invokespecial 72	com/baidu/platform/comapi/favrite/a:j	()V
    //   43: aload_0
    //   44: invokevirtual 116	com/baidu/platform/comapi/favrite/a:e	()Ljava/util/ArrayList;
    //   47: astore 4
    //   49: aload 4
    //   51: ifnull +365 -> 416
    //   54: aload 4
    //   56: invokevirtual 122	java/util/ArrayList:size	()I
    //   59: istore_3
    //   60: goto +366 -> 426
    //   63: aload 4
    //   65: ifnull +66 -> 131
    //   68: aload 4
    //   70: invokevirtual 122	java/util/ArrayList:size	()I
    //   73: ifle +58 -> 131
    //   76: aload 4
    //   78: invokevirtual 126	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   81: astore 4
    //   83: aload 4
    //   85: invokeinterface 131 1 0
    //   90: ifeq +41 -> 131
    //   93: aload_0
    //   94: aload 4
    //   96: invokeinterface 135 1 0
    //   101: checkcast 109	java/lang/String
    //   104: invokevirtual 138	com/baidu/platform/comapi/favrite/a:b	(Ljava/lang/String;)Lcom/baidu/platform/comapi/favrite/FavSyncPoi;
    //   107: astore 5
    //   109: aload 5
    //   111: ifnull -28 -> 83
    //   114: aload_1
    //   115: aload 5
    //   117: getfield 143	com/baidu/platform/comapi/favrite/FavSyncPoi:b	Ljava/lang/String;
    //   120: invokevirtual 113	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   123: ifeq -40 -> 83
    //   126: iconst_m1
    //   127: istore_3
    //   128: goto -113 -> 15
    //   131: new 145	org/json/JSONObject
    //   134: dup
    //   135: invokespecial 146	org/json/JSONObject:<init>	()V
    //   138: astore 4
    //   140: aload_2
    //   141: aload_1
    //   142: putfield 143	com/baidu/platform/comapi/favrite/FavSyncPoi:b	Ljava/lang/String;
    //   145: invokestatic 151	java/lang/System:currentTimeMillis	()J
    //   148: invokestatic 155	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   151: astore 5
    //   153: new 76	java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   160: aload 5
    //   162: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: ldc 157
    //   167: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload_2
    //   171: invokevirtual 160	java/lang/Object:hashCode	()I
    //   174: invokevirtual 163	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   177: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: astore_1
    //   181: aload_2
    //   182: aload 5
    //   184: putfield 165	com/baidu/platform/comapi/favrite/FavSyncPoi:h	Ljava/lang/String;
    //   187: aload_2
    //   188: aload_1
    //   189: putfield 167	com/baidu/platform/comapi/favrite/FavSyncPoi:a	Ljava/lang/String;
    //   192: aload 4
    //   194: ldc 169
    //   196: aload_2
    //   197: getfield 171	com/baidu/platform/comapi/favrite/FavSyncPoi:i	Z
    //   200: invokevirtual 175	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   203: pop
    //   204: aload 4
    //   206: ldc 177
    //   208: aload_2
    //   209: getfield 143	com/baidu/platform/comapi/favrite/FavSyncPoi:b	Ljava/lang/String;
    //   212: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   215: pop
    //   216: new 145	org/json/JSONObject
    //   219: dup
    //   220: invokespecial 146	org/json/JSONObject:<init>	()V
    //   223: astore 5
    //   225: aload 5
    //   227: ldc 182
    //   229: aload_2
    //   230: getfield 185	com/baidu/platform/comapi/favrite/FavSyncPoi:c	Lcom/baidu/mapapi/model/inner/Point;
    //   233: invokevirtual 190	com/baidu/mapapi/model/inner/Point:getmPtx	()I
    //   236: invokevirtual 193	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   239: pop
    //   240: aload 5
    //   242: ldc 195
    //   244: aload_2
    //   245: getfield 185	com/baidu/platform/comapi/favrite/FavSyncPoi:c	Lcom/baidu/mapapi/model/inner/Point;
    //   248: invokevirtual 198	com/baidu/mapapi/model/inner/Point:getmPty	()I
    //   251: invokevirtual 193	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   254: pop
    //   255: aload 4
    //   257: ldc 200
    //   259: aload 5
    //   261: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   264: pop
    //   265: aload 4
    //   267: ldc 202
    //   269: aload_2
    //   270: getfield 204	com/baidu/platform/comapi/favrite/FavSyncPoi:e	Ljava/lang/String;
    //   273: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   276: pop
    //   277: aload 4
    //   279: ldc 206
    //   281: aload_2
    //   282: getfield 209	com/baidu/platform/comapi/favrite/FavSyncPoi:g	I
    //   285: invokevirtual 193	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   288: pop
    //   289: aload 4
    //   291: ldc 211
    //   293: aload_2
    //   294: getfield 213	com/baidu/platform/comapi/favrite/FavSyncPoi:f	Ljava/lang/String;
    //   297: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   300: pop
    //   301: aload 4
    //   303: ldc 215
    //   305: aload_2
    //   306: getfield 217	com/baidu/platform/comapi/favrite/FavSyncPoi:d	Ljava/lang/String;
    //   309: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   312: pop
    //   313: aload 4
    //   315: ldc 219
    //   317: aload_2
    //   318: getfield 165	com/baidu/platform/comapi/favrite/FavSyncPoi:h	Ljava/lang/String;
    //   321: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   324: pop
    //   325: new 145	org/json/JSONObject
    //   328: dup
    //   329: invokespecial 146	org/json/JSONObject:<init>	()V
    //   332: astore 5
    //   334: aload 5
    //   336: ldc 221
    //   338: aload 4
    //   340: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   343: pop
    //   344: aload 5
    //   346: ldc 223
    //   348: aload_2
    //   349: getfield 225	com/baidu/platform/comapi/favrite/FavSyncPoi:j	Ljava/lang/String;
    //   352: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   355: pop
    //   356: aload_0
    //   357: getfield 38	com/baidu/platform/comapi/favrite/a:a	Lcom/baidu/platform/comjni/map/favorite/a;
    //   360: aload_1
    //   361: aload 5
    //   363: invokevirtual 226	org/json/JSONObject:toString	()Ljava/lang/String;
    //   366: invokevirtual 229	com/baidu/platform/comjni/map/favorite/a:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   369: ifeq +21 -> 390
    //   372: aload_0
    //   373: invokespecial 72	com/baidu/platform/comapi/favrite/a:j	()V
    //   376: iconst_1
    //   377: istore_3
    //   378: invokestatic 231	com/baidu/platform/comapi/favrite/a:g	()Z
    //   381: pop
    //   382: goto -367 -> 15
    //   385: astore_1
    //   386: aload_0
    //   387: monitorexit
    //   388: aload_1
    //   389: athrow
    //   390: invokestatic 231	com/baidu/platform/comapi/favrite/a:g	()Z
    //   393: pop
    //   394: iconst_0
    //   395: istore_3
    //   396: goto -381 -> 15
    //   399: astore_1
    //   400: invokestatic 231	com/baidu/platform/comapi/favrite/a:g	()Z
    //   403: pop
    //   404: iconst_0
    //   405: istore_3
    //   406: goto -391 -> 15
    //   409: astore_1
    //   410: invokestatic 231	com/baidu/platform/comapi/favrite/a:g	()Z
    //   413: pop
    //   414: aload_1
    //   415: athrow
    //   416: iconst_0
    //   417: istore_3
    //   418: goto +8 -> 426
    //   421: iconst_m1
    //   422: istore_3
    //   423: goto -408 -> 15
    //   426: iload_3
    //   427: iconst_1
    //   428: iadd
    //   429: sipush 500
    //   432: if_icmple -369 -> 63
    //   435: bipush 254
    //   437: istore_3
    //   438: goto -423 -> 15
    //
    // Exception table:
    //   from	to	target	type
    //   2	8	385	finally
    //   23	32	385	finally
    //   39	49	385	finally
    //   54	60	385	finally
    //   68	83	385	finally
    //   83	109	385	finally
    //   114	126	385	finally
    //   378	382	385	finally
    //   390	394	385	finally
    //   400	404	385	finally
    //   410	416	385	finally
    //   131	376	399	org/json/JSONException
    //   131	376	409	finally
  }

  public boolean a(String paramString)
  {
    boolean bool2 = false;
    try
    {
      com.baidu.platform.comjni.map.favorite.a locala = this.a;
      boolean bool1;
      if (locala == null)
        bool1 = bool2;
      while (true)
      {
        return bool1;
        bool1 = bool2;
        if (paramString != null)
        {
          bool1 = bool2;
          if (!paramString.equals(""))
          {
            bool1 = bool2;
            if (c(paramString))
            {
              j();
              bool1 = this.a.a(paramString);
            }
          }
        }
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public FavSyncPoi b(String paramString)
  {
    if ((this.a == null) || (paramString == null) || (paramString.equals("")));
    while (true)
    {
      return null;
      try
      {
        if (c(paramString))
        {
          FavSyncPoi localFavSyncPoi = new FavSyncPoi();
          Object localObject1 = this.a.b(paramString);
          if ((localObject1 != null) && (!((String)localObject1).equals("")))
          {
            Object localObject2 = new JSONObject((String)localObject1);
            localObject1 = ((JSONObject)localObject2).optJSONObject("Fav_Sync");
            localObject2 = ((JSONObject)localObject2).optString("Fav_Content");
            localFavSyncPoi.b = ((JSONObject)localObject1).optString("uspoiname");
            JSONObject localJSONObject = ((JSONObject)localObject1).optJSONObject("pt");
            localFavSyncPoi.c = new Point(localJSONObject.optInt("x"), localJSONObject.optInt("y"));
            localFavSyncPoi.e = ((JSONObject)localObject1).optString("ncityid");
            localFavSyncPoi.f = ((JSONObject)localObject1).optString("uspoiuid");
            localFavSyncPoi.g = ((JSONObject)localObject1).optInt("npoitype");
            localFavSyncPoi.d = ((JSONObject)localObject1).optString("addr");
            localFavSyncPoi.h = ((JSONObject)localObject1).optString("addtimesec");
            localFavSyncPoi.i = ((JSONObject)localObject1).optBoolean("bdetail");
            localFavSyncPoi.j = ((String)localObject2);
            localFavSyncPoi.a = paramString;
            return localFavSyncPoi;
          }
        }
      }
      catch (NullPointerException paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }

  public void b()
  {
    if (b != null)
    {
      if (b.a != null)
      {
        b.a.b();
        b.a = null;
      }
      b = null;
    }
  }

  // ERROR //
  public boolean b(String paramString, FavSyncPoi paramFavSyncPoi)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: iload 4
    //   7: istore_3
    //   8: aload_0
    //   9: getfield 38	com/baidu/platform/comapi/favrite/a:a	Lcom/baidu/platform/comjni/map/favorite/a;
    //   12: ifnull +33 -> 45
    //   15: iload 4
    //   17: istore_3
    //   18: aload_1
    //   19: ifnull +26 -> 45
    //   22: aload_1
    //   23: ldc 107
    //   25: invokevirtual 113	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   28: istore 5
    //   30: iload 4
    //   32: istore_3
    //   33: iload 5
    //   35: ifne +10 -> 45
    //   38: aload_2
    //   39: ifnonnull +10 -> 49
    //   42: iload 4
    //   44: istore_3
    //   45: aload_0
    //   46: monitorexit
    //   47: iload_3
    //   48: ireturn
    //   49: aload_0
    //   50: aload_1
    //   51: invokevirtual 234	com/baidu/platform/comapi/favrite/a:c	(Ljava/lang/String;)Z
    //   54: istore 5
    //   56: iload 4
    //   58: istore_3
    //   59: iload 5
    //   61: ifeq -16 -> 45
    //   64: new 145	org/json/JSONObject
    //   67: dup
    //   68: invokespecial 146	org/json/JSONObject:<init>	()V
    //   71: astore 6
    //   73: aload 6
    //   75: ldc 177
    //   77: aload_2
    //   78: getfield 143	com/baidu/platform/comapi/favrite/FavSyncPoi:b	Ljava/lang/String;
    //   81: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   84: pop
    //   85: new 145	org/json/JSONObject
    //   88: dup
    //   89: invokespecial 146	org/json/JSONObject:<init>	()V
    //   92: astore 7
    //   94: aload 7
    //   96: ldc 182
    //   98: aload_2
    //   99: getfield 185	com/baidu/platform/comapi/favrite/FavSyncPoi:c	Lcom/baidu/mapapi/model/inner/Point;
    //   102: invokevirtual 190	com/baidu/mapapi/model/inner/Point:getmPtx	()I
    //   105: invokevirtual 193	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   108: pop
    //   109: aload 7
    //   111: ldc 195
    //   113: aload_2
    //   114: getfield 185	com/baidu/platform/comapi/favrite/FavSyncPoi:c	Lcom/baidu/mapapi/model/inner/Point;
    //   117: invokevirtual 198	com/baidu/mapapi/model/inner/Point:getmPty	()I
    //   120: invokevirtual 193	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   123: pop
    //   124: aload 6
    //   126: ldc 200
    //   128: aload 7
    //   130: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   133: pop
    //   134: aload 6
    //   136: ldc 202
    //   138: aload_2
    //   139: getfield 204	com/baidu/platform/comapi/favrite/FavSyncPoi:e	Ljava/lang/String;
    //   142: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   145: pop
    //   146: aload 6
    //   148: ldc 206
    //   150: aload_2
    //   151: getfield 209	com/baidu/platform/comapi/favrite/FavSyncPoi:g	I
    //   154: invokevirtual 193	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   157: pop
    //   158: aload 6
    //   160: ldc 211
    //   162: aload_2
    //   163: getfield 213	com/baidu/platform/comapi/favrite/FavSyncPoi:f	Ljava/lang/String;
    //   166: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   169: pop
    //   170: aload 6
    //   172: ldc 215
    //   174: aload_2
    //   175: getfield 217	com/baidu/platform/comapi/favrite/FavSyncPoi:d	Ljava/lang/String;
    //   178: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   181: pop
    //   182: aload_2
    //   183: invokestatic 151	java/lang/System:currentTimeMillis	()J
    //   186: invokestatic 155	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   189: putfield 165	com/baidu/platform/comapi/favrite/FavSyncPoi:h	Ljava/lang/String;
    //   192: aload 6
    //   194: ldc 219
    //   196: aload_2
    //   197: getfield 165	com/baidu/platform/comapi/favrite/FavSyncPoi:h	Ljava/lang/String;
    //   200: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   203: pop
    //   204: aload 6
    //   206: ldc 169
    //   208: iconst_0
    //   209: invokevirtual 175	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   212: pop
    //   213: new 145	org/json/JSONObject
    //   216: dup
    //   217: invokespecial 146	org/json/JSONObject:<init>	()V
    //   220: astore 7
    //   222: aload 7
    //   224: ldc 221
    //   226: aload 6
    //   228: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   231: pop
    //   232: aload 7
    //   234: ldc 223
    //   236: aload_2
    //   237: getfield 225	com/baidu/platform/comapi/favrite/FavSyncPoi:j	Ljava/lang/String;
    //   240: invokevirtual 180	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   243: pop
    //   244: aload_0
    //   245: invokespecial 72	com/baidu/platform/comapi/favrite/a:j	()V
    //   248: iload 4
    //   250: istore_3
    //   251: aload_0
    //   252: getfield 38	com/baidu/platform/comapi/favrite/a:a	Lcom/baidu/platform/comjni/map/favorite/a;
    //   255: ifnull -210 -> 45
    //   258: aload_0
    //   259: getfield 38	com/baidu/platform/comapi/favrite/a:a	Lcom/baidu/platform/comjni/map/favorite/a;
    //   262: aload_1
    //   263: aload 7
    //   265: invokevirtual 226	org/json/JSONObject:toString	()Ljava/lang/String;
    //   268: invokevirtual 271	com/baidu/platform/comjni/map/favorite/a:b	(Ljava/lang/String;Ljava/lang/String;)Z
    //   271: istore 5
    //   273: iload 4
    //   275: istore_3
    //   276: iload 5
    //   278: ifeq -233 -> 45
    //   281: iconst_1
    //   282: istore_3
    //   283: goto -238 -> 45
    //   286: astore_1
    //   287: aload_0
    //   288: monitorexit
    //   289: aload_1
    //   290: athrow
    //   291: astore_1
    //   292: iload 4
    //   294: istore_3
    //   295: goto -250 -> 45
    //
    // Exception table:
    //   from	to	target	type
    //   8	15	286	finally
    //   22	30	286	finally
    //   49	56	286	finally
    //   64	248	286	finally
    //   251	273	286	finally
    //   64	248	291	org/json/JSONException
    //   251	273	291	org/json/JSONException
  }

  public boolean c()
  {
    try
    {
      com.baidu.platform.comjni.map.favorite.a locala = this.a;
      boolean bool;
      if (locala == null)
        bool = false;
      while (true)
      {
        return bool;
        j();
        bool = this.a.c();
        g();
      }
    }
    finally
    {
    }
  }

  public boolean c(String paramString)
  {
    return (this.a != null) && (paramString != null) && (!paramString.equals("")) && (this.a.c(paramString));
  }

  public ArrayList<String> d()
  {
    if (this.a == null)
      return null;
    if ((this.d) && (this.f != null))
      return new ArrayList(this.f);
    try
    {
      Object localObject = new Bundle();
      this.a.a((Bundle)localObject);
      localObject = ((Bundle)localObject).getStringArray("rstString");
      if (localObject != null)
        if (this.f == null)
        {
          this.f = new Vector();
          break label251;
        }
      while (true)
      {
        if (j < localObject.length)
        {
          if (localObject[j].equals("data_version"))
            break label256;
          String str = this.a.b(localObject[j]);
          if (str == null)
            break label256;
          if (str.equals(""))
          {
            break label256;
            this.f.clear();
          }
          else
          {
            this.f.add(localObject[j]);
            break label256;
          }
        }
        else
        {
          j = this.f.size();
          if (j > 0);
          while (true)
          {
            try
            {
              Collections.sort(this.f, new a());
              this.d = true;
              if (this.f == null)
                break label266;
              if (!this.f.isEmpty())
                break;
            }
            catch (Exception localException1)
            {
              localException1.printStackTrace();
              continue;
            }
            if (this.f != null)
            {
              this.f.clear();
              this.f = null;
            }
          }
          ArrayList localArrayList = new ArrayList(this.f);
          break label268;
        }
        label251: int j = 0;
        continue;
        label256: j += 1;
      }
    }
    catch (Exception localException2)
    {
      return null;
    }
    label266: ArrayList<String> localArrayList1 = null;
    label268: return localArrayList1;
  }

  public ArrayList<String> e()
  {
    if (this.a == null);
    while (true)
    {
      return null;
      if ((this.c) && (this.e != null))
        return new ArrayList(this.e);
      try
      {
        Object localObject1 = new Bundle();
        this.a.a((Bundle)localObject1);
        localObject1 = ((Bundle)localObject1).getStringArray("rstString");
        label82: int k;
        int j;
        if (localObject1 != null)
          if (this.e == null)
          {
            this.e = new Vector();
            k = localObject1.length;
            j = 0;
          }
        while (true)
        {
          if (j < k)
          {
            Object localObject2 = localObject1[j];
            if (localObject2.equals("data_version"))
              break label222;
            this.e.add(localObject2);
            break label222;
            this.e.clear();
            break label82;
          }
          j = this.e.size();
          if (j > 0);
          while (true)
          {
            try
            {
              Collections.sort(this.e, new a());
              this.c = true;
              if ((this.e == null) || (this.e.size() == 0))
                break;
              return new ArrayList(this.e);
            }
            catch (Exception localException1)
            {
              localException1.printStackTrace();
              continue;
            }
            if (this.e != null)
            {
              this.e.clear();
              this.e = null;
            }
          }
          label222: j += 1;
        }
      }
      catch (Exception localException2)
      {
      }
    }
    return null;
  }

  public String f()
  {
    if ((b.a(this.i)) && (!c.a(this.h)) && (!c.b(this.h)))
      return c.c(this.h);
    b.b(this.i);
    if (this.a == null)
      return null;
    Object localObject2 = d();
    Object localObject1 = new JSONObject();
    if (localObject2 != null);
    while (true)
      try
      {
        JSONArray localJSONArray = new JSONArray();
        int j = 0;
        localObject2 = ((ArrayList)localObject2).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          String str = (String)((Iterator)localObject2).next();
          if ((str != null) && (!str.equals("data_version")))
          {
            Object localObject3 = this.a.b(str);
            if ((localObject3 != null) && (!((String)localObject3).equals("")))
            {
              localObject3 = new JSONObject((String)localObject3).optJSONObject("Fav_Sync");
              ((JSONObject)localObject3).put("key", str);
              localJSONArray.put(j, localObject3);
              j += 1;
            }
          }
        }
        else
        {
          if (j > 0)
          {
            ((JSONObject)localObject1).put("favcontents", localJSONArray);
            ((JSONObject)localObject1).put("favpoinum", j);
          }
          b.c(this.i);
          c.a(this.h, ((JSONObject)localObject1).toString());
          localObject1 = c.c(this.h);
          return localObject1;
        }
      }
      catch (JSONException localJSONException)
      {
        return null;
      }
  }

  class a
    implements Comparator<String>
  {
    a()
    {
    }

    public int a(String paramString1, String paramString2)
    {
      return paramString2.compareTo(paramString1);
    }
  }

  private class b
  {
    private long b;
    private long c;

    private b()
    {
    }

    private void a()
    {
      this.b = System.currentTimeMillis();
    }

    private void b()
    {
      this.c = System.currentTimeMillis();
    }

    private boolean c()
    {
      return this.c - this.b > 1000L;
    }
  }

  private class c
  {
    private String b;
    private long c = 5000L;
    private long d = 0L;

    private c()
    {
    }

    private String a()
    {
      return this.b;
    }

    private void a(String paramString)
    {
      this.b = paramString;
      this.d = System.currentTimeMillis();
    }

    private boolean b()
    {
      return TextUtils.isEmpty(this.b);
    }

    private boolean c()
    {
      return true;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.favrite.a
 * JD-Core Version:    0.6.2
 */