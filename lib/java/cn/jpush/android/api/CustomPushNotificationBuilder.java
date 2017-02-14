package cn.jpush.android.api;

import android.content.Context;
import android.widget.RemoteViews;
import cn.jpush.android.a;

public class CustomPushNotificationBuilder extends BasicPushNotificationBuilder
{
  private static final String[] z;
  public int layout;
  public int layoutContentId;
  public int layoutIconDrawable = a.a;
  public int layoutIconId;
  public int layoutTitleId;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "^\003\026\005#P):.\023b";
    int k = -1;
    int m = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int j = localObject2.length;
    int i;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (j <= 1)
    {
      i = 0;
      localObject3 = localObject1;
      n = m;
      localObject1 = localObject2;
      i1 = k;
      m = i;
      for (k = i; ; k = j)
      {
        localObject2 = localObject1;
        i2 = localObject2[k];
        switch (m % 5)
        {
        default:
          i = 76;
          localObject2[k] = ((char)(i ^ i2));
          m += 1;
          if (j != 0)
            break label121;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      k = j;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      j = k;
      i = m;
      if (k > m)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "b):.\023";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 61;
        break label99;
        i = 118;
        break label99;
        i = 101;
        break label99;
        i = 113;
        break label99;
        i = 0;
        i2 = k;
        i3 = m;
        localObject4 = localObject1;
        k = j;
        m = i;
      }
    }
  }

  CustomPushNotificationBuilder(Context paramContext)
  {
    super(paramContext);
  }

  public CustomPushNotificationBuilder(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramContext);
    this.layout = paramInt1;
    this.layoutIconId = paramInt2;
    this.layoutTitleId = paramInt3;
    this.layoutContentId = paramInt4;
  }

  final void a(String[] paramArrayOfString)
  {
    super.a(paramArrayOfString);
    this.layout = Integer.parseInt(paramArrayOfString[5]);
    this.layoutIconId = Integer.parseInt(paramArrayOfString[6]);
    this.layoutTitleId = Integer.parseInt(paramArrayOfString[7]);
    this.layoutContentId = Integer.parseInt(paramArrayOfString[8]);
    this.layoutIconDrawable = Integer.parseInt(paramArrayOfString[9]);
  }

  final RemoteViews b(String paramString)
  {
    RemoteViews localRemoteViews = new RemoteViews(this.a.getPackageName(), this.layout);
    localRemoteViews.setTextViewText(this.layoutTitleId, this.b);
    localRemoteViews.setImageViewResource(this.layoutIconId, this.layoutIconDrawable);
    localRemoteViews.setTextViewText(this.layoutContentId, paramString);
    return localRemoteViews;
  }

  final String b()
  {
    return super.b() + z[1] + this.layout + z[1] + this.layoutIconId + z[1] + this.layoutTitleId + z[1] + this.layoutContentId + z[1] + this.layoutIconDrawable;
  }

  public String toString()
  {
    return z[0] + b();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.CustomPushNotificationBuilder
 * JD-Core Version:    0.6.2
 */