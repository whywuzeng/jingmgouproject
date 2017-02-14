package cn.smssdk.contact;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.HashMap;

public class c
{
  private ContentResolver a;

  public c(ContentResolver paramContentResolver)
  {
    this.a = paramContentResolver;
  }

  public ArrayList<HashMap<String, Object>> a(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    paramString1 = this.a.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
    if (paramString1 != null)
    {
      if (paramString1.moveToFirst())
      {
        paramArrayOfString1 = new ArrayList();
        do
        {
          paramArrayOfString2 = new HashMap();
          int j = paramString1.getColumnCount();
          int i = 0;
          while (true)
            if (i < j)
            {
              paramString2 = paramString1.getColumnName(i);
              try
              {
                paramUri = paramString1.getString(i);
                paramArrayOfString2.put(paramString2, paramUri);
                i += 1;
              }
              catch (Throwable paramUri)
              {
                while (true)
                  paramUri = paramString1.getBlob(i);
              }
            }
          paramArrayOfString1.add(paramArrayOfString2);
        }
        while (paramString1.moveToNext());
      }
      for (paramUri = paramArrayOfString1; ; paramUri = null)
      {
        paramString1.close();
        return paramUri;
      }
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.contact.c
 * JD-Core Version:    0.6.2
 */