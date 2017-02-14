package com.ta.utdid2.core.persistent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

public class TransactionXMLFile
{
  private static final Object GLOBAL_COMMIT_LOCK = new Object();
  public static final int MODE_PRIVATE = 0;
  public static final int MODE_WORLD_READABLE = 1;
  public static final int MODE_WORLD_WRITEABLE = 2;
  private File mPreferencesDir;
  private final Object mSync = new Object();
  private HashMap<File, MySharedPreferencesImpl> sSharedPrefs = new HashMap();

  public TransactionXMLFile(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      this.mPreferencesDir = new File(paramString);
      return;
    }
    throw new RuntimeException("Directory can not be empty");
  }

  private File getPreferencesDir()
  {
    synchronized (this.mSync)
    {
      File localFile = this.mPreferencesDir;
      return localFile;
    }
  }

  private File getSharedPrefsFile(String paramString)
  {
    return makeFilename(getPreferencesDir(), paramString + ".xml");
  }

  private static File makeBackupFile(File paramFile)
  {
    return new File(paramFile.getPath() + ".bak");
  }

  private File makeFilename(File paramFile, String paramString)
  {
    if (paramString.indexOf(File.separatorChar) < 0)
      return new File(paramFile, paramString);
    throw new IllegalArgumentException("File " + paramString + " contains a path separator");
  }

  public MySharedPreferences getMySharedPreferences(String arg1, int paramInt)
  {
    File localFile = getSharedPrefsFile(???);
    MySharedPreferencesImpl localMySharedPreferencesImpl;
    Object localObject6;
    Object localObject7;
    Object localObject8;
    Object localObject1;
    Object localObject4;
    Object localObject5;
    synchronized (GLOBAL_COMMIT_LOCK)
    {
      localMySharedPreferencesImpl = (MySharedPreferencesImpl)this.sSharedPrefs.get(localFile);
      if ((localMySharedPreferencesImpl != null) && (!localMySharedPreferencesImpl.a()))
        return localMySharedPreferencesImpl;
      ??? = makeBackupFile(localFile);
      if (???.exists())
      {
        localFile.delete();
        ???.renameTo(localFile);
      }
      if ((localFile.exists()) && (!localFile.canRead()));
      localObject6 = null;
      localObject7 = null;
      localObject8 = null;
      localObject1 = null;
      ??? = (String)localObject1;
      if (localFile.exists())
      {
        ??? = (String)localObject1;
        if (localFile.canRead())
        {
          localObject1 = localObject6;
          localObject4 = localObject7;
          localObject5 = localObject8;
        }
      }
    }
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      localObject1 = localObject6;
      localObject4 = localObject7;
      localObject5 = localObject8;
      ??? = b.a(localFileInputStream);
      localObject1 = ???;
      localObject4 = ???;
      localObject5 = ???;
      localFileInputStream.close();
      localObject5 = GLOBAL_COMMIT_LOCK;
      if (localMySharedPreferencesImpl != null)
      {
        try
        {
          localMySharedPreferencesImpl.a(???);
          localObject1 = localMySharedPreferencesImpl;
          return localObject1;
        }
        finally
        {
        }
        localObject2 = finally;
        throw localObject2;
      }
    }
    catch (XmlPullParserException )
    {
      while (true)
      {
        try
        {
          ??? = new FileInputStream(localFile);
          localObject4 = new byte[???.available()];
          ???.read((byte[])localObject4);
          new String((byte[])localObject4, 0, localObject4.length, "UTF-8");
          ??? = localObject2;
        }
        catch (FileNotFoundException )
        {
          ???.printStackTrace();
          ??? = localObject2;
        }
        catch (IOException )
        {
          ???.printStackTrace();
          ??? = localObject2;
        }
        continue;
        localObject4 = (MySharedPreferencesImpl)this.sSharedPrefs.get(localFile);
        Object localObject3 = localObject4;
        if (localObject4 == null)
        {
          localObject3 = new MySharedPreferencesImpl(localFile, paramInt, ???);
          this.sSharedPrefs.put(localFile, localObject3);
        }
      }
    }
    catch (IOException )
    {
      while (true)
        ??? = (String)localObject4;
    }
    catch (FileNotFoundException )
    {
      while (true)
        ??? = (String)localObject5;
    }
  }

  private static final class MySharedPreferencesImpl
    implements MySharedPreferences
  {
    private static final Object f = new Object();
    private final File a;
    private final File b;
    private final int c;
    private Map d;
    private boolean e = false;
    private WeakHashMap<MySharedPreferences.OnSharedPreferenceChangeListener, Object> g;

    MySharedPreferencesImpl(File paramFile, int paramInt, Map paramMap)
    {
      this.a = paramFile;
      this.b = TransactionXMLFile.makeBackupFile(paramFile);
      this.c = paramInt;
      if (paramMap != null);
      while (true)
      {
        this.d = paramMap;
        this.g = new WeakHashMap();
        return;
        paramMap = new HashMap();
      }
    }

    private FileOutputStream a(File paramFile)
    {
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
        return localFileOutputStream;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        if (!paramFile.getParentFile().mkdir())
          return null;
        try
        {
          paramFile = new FileOutputStream(paramFile);
          return paramFile;
        }
        catch (FileNotFoundException paramFile)
        {
        }
      }
      return null;
    }

    private boolean b()
    {
      if (this.a.exists())
        if (!this.b.exists())
          if (this.a.renameTo(this.b))
            break label44;
      while (true)
      {
        return false;
        this.a.delete();
        try
        {
          label44: FileOutputStream localFileOutputStream = a(this.a);
          if (localFileOutputStream == null)
            continue;
          b.a(this.d, localFileOutputStream);
          localFileOutputStream.close();
          this.b.delete();
          return true;
        }
        catch (IOException localIOException)
        {
          if ((!this.a.exists()) || (this.a.delete()))
            continue;
          return false;
        }
        catch (XmlPullParserException localXmlPullParserException)
        {
          label80: break label80;
        }
      }
    }

    public void a(Map paramMap)
    {
      if (paramMap != null)
        try
        {
          this.d = paramMap;
          return;
        }
        finally
        {
        }
    }

    public void a(boolean paramBoolean)
    {
      try
      {
        this.e = paramBoolean;
        return;
      }
      finally
      {
      }
    }

    public boolean a()
    {
      try
      {
        boolean bool = this.e;
        return bool;
      }
      finally
      {
      }
    }

    public boolean checkFile()
    {
      return (this.a != null) && (new File(this.a.getAbsolutePath()).exists());
    }

    public boolean contains(String paramString)
    {
      try
      {
        boolean bool = this.d.containsKey(paramString);
        return bool;
      }
      finally
      {
      }
      throw paramString;
    }

    public MySharedPreferences.MyEditor edit()
    {
      return new EditorImpl();
    }

    public Map<String, ?> getAll()
    {
      try
      {
        HashMap localHashMap = new HashMap(this.d);
        return localHashMap;
      }
      finally
      {
      }
    }

    public boolean getBoolean(String paramString, boolean paramBoolean)
    {
      try
      {
        paramString = (Boolean)this.d.get(paramString);
        if (paramString != null)
          paramBoolean = paramString.booleanValue();
        return paramBoolean;
      }
      finally
      {
      }
      throw paramString;
    }

    public float getFloat(String paramString, float paramFloat)
    {
      try
      {
        paramString = (Float)this.d.get(paramString);
        if (paramString != null)
          paramFloat = paramString.floatValue();
        return paramFloat;
      }
      finally
      {
      }
      throw paramString;
    }

    public int getInt(String paramString, int paramInt)
    {
      try
      {
        paramString = (Integer)this.d.get(paramString);
        if (paramString != null)
          paramInt = paramString.intValue();
        return paramInt;
      }
      finally
      {
      }
      throw paramString;
    }

    public long getLong(String paramString, long paramLong)
    {
      try
      {
        paramString = (Long)this.d.get(paramString);
        if (paramString != null)
          paramLong = paramString.longValue();
        return paramLong;
      }
      finally
      {
      }
      throw paramString;
    }

    public String getString(String paramString1, String paramString2)
    {
      while (true)
      {
        try
        {
          paramString1 = (String)this.d.get(paramString1);
          if (paramString1 != null)
            return paramString1;
        }
        finally
        {
        }
        paramString1 = paramString2;
      }
    }

    public void registerOnSharedPreferenceChangeListener(MySharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
    {
      try
      {
        this.g.put(paramOnSharedPreferenceChangeListener, f);
        return;
      }
      finally
      {
      }
      throw paramOnSharedPreferenceChangeListener;
    }

    public void unregisterOnSharedPreferenceChangeListener(MySharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
    {
      try
      {
        this.g.remove(paramOnSharedPreferenceChangeListener);
        return;
      }
      finally
      {
      }
      throw paramOnSharedPreferenceChangeListener;
    }

    public final class EditorImpl
      implements MySharedPreferences.MyEditor
    {
      private boolean mClear = false;
      private final Map<String, Object> mModified = new HashMap();

      public EditorImpl()
      {
      }

      public MySharedPreferences.MyEditor clear()
      {
        try
        {
          this.mClear = true;
          return this;
        }
        finally
        {
        }
      }

      public boolean commit()
      {
        int i = 0;
        while (true)
        {
          Iterator localIterator;
          Object localObject6;
          Object localObject5;
          synchronized (TransactionXMLFile.GLOBAL_COMMIT_LOCK)
          {
            if (TransactionXMLFile.MySharedPreferencesImpl.a(TransactionXMLFile.MySharedPreferencesImpl.this).size() > 0)
              i = 1;
            if (i == 0)
              break label335;
            localArrayList = new ArrayList();
            HashSet localHashSet = new HashSet(TransactionXMLFile.MySharedPreferencesImpl.a(TransactionXMLFile.MySharedPreferencesImpl.this).keySet());
            try
            {
              if (this.mClear)
              {
                TransactionXMLFile.MySharedPreferencesImpl.b(TransactionXMLFile.MySharedPreferencesImpl.this).clear();
                this.mClear = false;
              }
              localIterator = this.mModified.entrySet().iterator();
              if (!localIterator.hasNext())
                break label210;
              localObject6 = (Map.Entry)localIterator.next();
              localObject5 = (String)((Map.Entry)localObject6).getKey();
              localObject6 = ((Map.Entry)localObject6).getValue();
              if (localObject6 == this)
              {
                TransactionXMLFile.MySharedPreferencesImpl.b(TransactionXMLFile.MySharedPreferencesImpl.this).remove(localObject5);
                if (i == 0)
                  continue;
                localArrayList.add(localObject5);
                continue;
              }
            }
            finally
            {
            }
          }
          TransactionXMLFile.MySharedPreferencesImpl.b(TransactionXMLFile.MySharedPreferencesImpl.this).put(localObject5, localObject6);
          continue;
          label210: this.mModified.clear();
          boolean bool = TransactionXMLFile.MySharedPreferencesImpl.c(TransactionXMLFile.MySharedPreferencesImpl.this);
          if (bool)
            TransactionXMLFile.MySharedPreferencesImpl.this.a(true);
          if (i != 0)
          {
            i = localArrayList.size() - 1;
            while (i >= 0)
            {
              ??? = (String)localArrayList.get(i);
              localIterator = localObject2.iterator();
              while (localIterator.hasNext())
              {
                localObject5 = (MySharedPreferences.OnSharedPreferenceChangeListener)localIterator.next();
                if (localObject5 != null)
                  ((MySharedPreferences.OnSharedPreferenceChangeListener)localObject5).onSharedPreferenceChanged(TransactionXMLFile.MySharedPreferencesImpl.this, (String)???);
              }
              i -= 1;
            }
          }
          return bool;
          label335: Object localObject3 = null;
          ArrayList localArrayList = null;
        }
      }

      public MySharedPreferences.MyEditor putBoolean(String paramString, boolean paramBoolean)
      {
        try
        {
          this.mModified.put(paramString, Boolean.valueOf(paramBoolean));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public MySharedPreferences.MyEditor putFloat(String paramString, float paramFloat)
      {
        try
        {
          this.mModified.put(paramString, Float.valueOf(paramFloat));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public MySharedPreferences.MyEditor putInt(String paramString, int paramInt)
      {
        try
        {
          this.mModified.put(paramString, Integer.valueOf(paramInt));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public MySharedPreferences.MyEditor putLong(String paramString, long paramLong)
      {
        try
        {
          this.mModified.put(paramString, Long.valueOf(paramLong));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public MySharedPreferences.MyEditor putString(String paramString1, String paramString2)
      {
        try
        {
          this.mModified.put(paramString1, paramString2);
          return this;
        }
        finally
        {
        }
        throw paramString1;
      }

      public MySharedPreferences.MyEditor remove(String paramString)
      {
        try
        {
          this.mModified.put(paramString, this);
          return this;
        }
        finally
        {
        }
        throw paramString;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.core.persistent.TransactionXMLFile
 * JD-Core Version:    0.6.2
 */