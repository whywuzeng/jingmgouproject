package net.tsz.afinal.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

public class PreferencesCookieStore
  implements CookieStore
{
  private static final String COOKIE_NAME_PREFIX = "cookie_";
  private static final String COOKIE_NAME_STORE = "names";
  private static final String COOKIE_PREFS = "CookiePrefsFile";
  private final SharedPreferences cookiePrefs;
  private final ConcurrentHashMap<String, Cookie> cookies;

  public PreferencesCookieStore(Context paramContext)
  {
    this.cookiePrefs = paramContext.getSharedPreferences("CookiePrefsFile", 0);
    this.cookies = new ConcurrentHashMap();
    paramContext = this.cookiePrefs.getString("names", null);
    int j;
    if (paramContext != null)
    {
      paramContext = TextUtils.split(paramContext, ",");
      j = paramContext.length;
    }
    while (true)
    {
      if (i >= j)
      {
        clearExpired(new Date());
        return;
      }
      String str = paramContext[i];
      Object localObject = this.cookiePrefs.getString("cookie_" + str, null);
      if (localObject != null)
      {
        localObject = decodeCookie((String)localObject);
        if (localObject != null)
          this.cookies.put(str, localObject);
      }
      i += 1;
    }
  }

  public void addCookie(Cookie paramCookie)
  {
    String str = paramCookie.getName();
    if (!paramCookie.isExpired(new Date()))
      this.cookies.put(str, paramCookie);
    while (true)
    {
      SharedPreferences.Editor localEditor = this.cookiePrefs.edit();
      localEditor.putString("names", TextUtils.join(",", this.cookies.keySet()));
      localEditor.putString("cookie_" + str, encodeCookie(new SerializableCookie(paramCookie)));
      localEditor.commit();
      return;
      this.cookies.remove(str);
    }
  }

  protected String byteArrayToHexString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    int j = paramArrayOfByte.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return localStringBuffer.toString().toUpperCase();
      int k = paramArrayOfByte[i] & 0xFF;
      if (k < 16)
        localStringBuffer.append('0');
      localStringBuffer.append(Integer.toHexString(k));
      i += 1;
    }
  }

  public void clear()
  {
    this.cookies.clear();
    SharedPreferences.Editor localEditor = this.cookiePrefs.edit();
    Iterator localIterator = this.cookies.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localEditor.remove("names");
        localEditor.commit();
        return;
      }
      String str = (String)localIterator.next();
      localEditor.remove("cookie_" + str);
    }
  }

  public boolean clearExpired(Date paramDate)
  {
    boolean bool = false;
    SharedPreferences.Editor localEditor = this.cookiePrefs.edit();
    Iterator localIterator = this.cookies.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (bool)
          localEditor.putString("names", TextUtils.join(",", this.cookies.keySet()));
        localEditor.commit();
        return bool;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = (String)localEntry.getKey();
      if (((Cookie)localEntry.getValue()).isExpired(paramDate))
      {
        this.cookies.remove(str);
        localEditor.remove("cookie_" + str);
        bool = true;
      }
    }
  }

  protected Cookie decodeCookie(String paramString)
  {
    paramString = new ByteArrayInputStream(hexStringToByteArray(paramString));
    try
    {
      paramString = ((SerializableCookie)new ObjectInputStream(paramString).readObject()).getCookie();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  protected String encodeCookie(SerializableCookie paramSerializableCookie)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      new ObjectOutputStream(localByteArrayOutputStream).writeObject(paramSerializableCookie);
      return byteArrayToHexString(localByteArrayOutputStream.toByteArray());
    }
    catch (Exception paramSerializableCookie)
    {
    }
    return null;
  }

  public List<Cookie> getCookies()
  {
    return new ArrayList(this.cookies.values());
  }

  protected byte[] hexStringToByteArray(String paramString)
  {
    int j = paramString.length();
    byte[] arrayOfByte = new byte[j / 2];
    int i = 0;
    while (true)
    {
      if (i >= j)
        return arrayOfByte;
      arrayOfByte[(i / 2)] = ((byte)((Character.digit(paramString.charAt(i), 16) << 4) + Character.digit(paramString.charAt(i + 1), 16)));
      i += 2;
    }
  }

  public class SerializableCookie
    implements Serializable
  {
    private static final long serialVersionUID = 6374381828722046732L;
    private transient BasicClientCookie clientCookie;
    private final transient Cookie cookie;

    public SerializableCookie(Cookie arg2)
    {
      Object localObject;
      this.cookie = localObject;
    }

    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      this.clientCookie = new BasicClientCookie((String)paramObjectInputStream.readObject(), (String)paramObjectInputStream.readObject());
      this.clientCookie.setComment((String)paramObjectInputStream.readObject());
      this.clientCookie.setDomain((String)paramObjectInputStream.readObject());
      this.clientCookie.setExpiryDate((Date)paramObjectInputStream.readObject());
      this.clientCookie.setPath((String)paramObjectInputStream.readObject());
      this.clientCookie.setVersion(paramObjectInputStream.readInt());
      this.clientCookie.setSecure(paramObjectInputStream.readBoolean());
    }

    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.writeObject(this.cookie.getName());
      paramObjectOutputStream.writeObject(this.cookie.getValue());
      paramObjectOutputStream.writeObject(this.cookie.getComment());
      paramObjectOutputStream.writeObject(this.cookie.getDomain());
      paramObjectOutputStream.writeObject(this.cookie.getExpiryDate());
      paramObjectOutputStream.writeObject(this.cookie.getPath());
      paramObjectOutputStream.writeInt(this.cookie.getVersion());
      paramObjectOutputStream.writeBoolean(this.cookie.isSecure());
    }

    public Cookie getCookie()
    {
      Object localObject = this.cookie;
      if (this.clientCookie != null)
        localObject = this.clientCookie;
      return localObject;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.PreferencesCookieStore
 * JD-Core Version:    0.6.2
 */