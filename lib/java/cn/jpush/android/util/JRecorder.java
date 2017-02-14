package cn.jpush.android.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class JRecorder
{
  private static ExecutorService a;
  private static Context c;
  private static Handler d;
  private static ArrayList<v> e;
  private static volatile boolean f;
  private static final String[] z;
  private ArrayList<w> b;

  static
  {
    String[] arrayOfString = new String[12];
    int j = 0;
    Object localObject2 = "\030w B[\016g-D]\031";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 41;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "\016g1L]\003}-";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\036k3H";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\030s-JL";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\005d&_E\013k";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\003f*@L";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "\030w B[\016M7TY\017";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\003| _L\013&C]";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "\030w3B[\0362'DZ\013p/HMJp:\r\023";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "\030w B[\016b&_@\005v";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "\030w3B[\0362&CH\b~&I\tP";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = Executors.newSingleThreadExecutor();
        d = null;
        e = new ArrayList();
        f = false;
        return;
        i = 106;
        continue;
        i = 18;
        continue;
        i = 67;
        continue;
        i = 45;
      }
    }
  }

  private JRecorder()
  {
    if (d == null)
      d = new Handler(Looper.getMainLooper());
  }

  private JRecorder(int paramInt, Context paramContext)
  {
    this();
    c = paramContext.getApplicationContext();
    this.b = new ArrayList();
    paramContext = new v(this, (byte)0);
    paramContext.a = paramInt;
    paramContext.b = this.b;
    e.add(paramContext);
  }

  private static JSONObject a(ArrayList<w> paramArrayList)
  {
    if (paramArrayList == null);
    int k;
    do
    {
      return null;
      k = paramArrayList.size();
    }
    while (k <= 0);
    JSONObject localJSONObject = new JSONObject();
    long l1 = ((w)paramArrayList.get(k - 1)).a;
    long l2 = ((w)paramArrayList.get(0)).a;
    int i = 0;
    int j = 0;
    while (i < k)
    {
      j = (int)(j + ((w)paramArrayList.get(i)).b);
      i += 1;
    }
    localJSONObject.put(z[6], z[8]);
    localJSONObject.put(z[1], l1 - l2);
    localJSONObject.put(z[3], j - ((w)paramArrayList.get(0)).b);
    return localJSONObject;
  }

  private static void b()
  {
    Iterator localIterator = e.iterator();
    while (localIterator.hasNext())
      ((v)localIterator.next()).b.clear();
    e.clear();
  }

  public static JRecorder getIncreamentsRecorder(Context paramContext)
  {
    return new JRecorder(0, paramContext);
  }

  public static JRecorder getSuperpositionRecorder(Context paramContext)
  {
    return new JRecorder(1, paramContext);
  }

  public static void parseRecordCommand(String paramString)
  {
    if (f)
    {
      x.c();
      return;
    }
    try
    {
      int i = new JSONObject(paramString).getInt(z[10]);
      f = true;
      new StringBuilder(z[11]).append(i).append("s").toString();
      x.c();
      if (d == null)
        d = new Handler(Looper.getMainLooper());
      d.postDelayed(new t(), i * 1000);
      return;
    }
    catch (JSONException paramString)
    {
      f = false;
      new StringBuilder(z[9]).append(paramString.getMessage()).toString();
      x.c();
    }
  }

  public void record(int paramInt)
  {
    if (!f)
    {
      x.c();
      return;
    }
    a.execute(new s(this, paramInt));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.JRecorder
 * JD-Core Version:    0.6.2
 */