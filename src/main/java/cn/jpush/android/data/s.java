package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.util.h;
import cn.jpush.android.util.x;
import java.util.ArrayList;
import org.json.JSONObject;

public final class s extends d
{
  private static final String[] bb;
  public String W;
  public int X;
  public boolean Y;
  public ArrayList<String> Z = new ArrayList();
  public String a;
  public String aa = "";
  public String ab = "";
  public String ac;

  static
  {
    String[] arrayOfString = new String[9];
    int j = 0;
    Object localObject2 = "_9Wk\027W>U";
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
        i = 72;
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
        localObject2 = "\\\024Mt$";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "x/\\& M?H&<VkVi&\024;Jc.P3\030s:Uk\002&";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\\\024]t-J";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\\\024Kn'N";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "M$gh=T";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "\\\024Jc;";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "Q?Lvr\026d";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\\\024Lo<U.";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        bb = arrayOfString;
        return;
        i = 57;
        continue;
        i = 75;
        continue;
        i = 56;
        continue;
        i = 6;
      }
    }
  }

  public s()
  {
    this.o = 0;
  }

  public final void a(Context paramContext)
  {
    x.b();
    new t(this, paramContext, this).start();
  }

  public final boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    x.b();
    this.a = paramJSONObject.optString(bb[1], "").trim();
    this.W = paramJSONObject.optString(bb[8], "").trim();
    if (!p.a(this.a))
    {
      this.a = (bb[7] + this.a);
      new StringBuilder(bb[2]).append(this.a).toString();
      x.d();
    }
    this.X = paramJSONObject.optInt(bb[4], 0);
    if (paramJSONObject.optInt(bb[6], 0) == 0);
    for (boolean bool = true; ; bool = false)
    {
      this.Y = bool;
      if (this.Y)
        this.Z = h.a(paramJSONObject.optJSONArray(bb[3]));
      this.aa = paramJSONObject.optString(bb[0], "");
      this.ab = paramJSONObject.optString(bb[5], "");
      return true;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.s
 * JD-Core Version:    0.6.2
 */