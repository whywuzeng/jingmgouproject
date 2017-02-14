package cn.jpush.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import cn.jpush.android.data.d;
import cn.jpush.android.util.x;
import java.util.List;

public class ListViewActivity extends Activity
{
  private static final String z;
  private d a;

  static
  {
    Object localObject1 = "\035e?R".toCharArray();
    int j = localObject1.length;
    int m = 0;
    int i = 0;
    Object localObject2 = localObject1;
    int k = j;
    label30: int n;
    if (j <= 1)
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 112;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[k] = ((char)(i ^ n));
      m += 1;
      if (j == 0)
      {
        k = j;
        break label30;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break;
      z = new String(localObject2).intern();
      return;
      i = 127;
      continue;
      i = 10;
      continue;
      i = 91;
      continue;
      i = 43;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    x.d();
    super.onCreate(paramBundle);
    if (getIntent() != null)
    {
      this.a = ((d)getIntent().getSerializableExtra(z));
      Object localObject = this.a;
      x.d();
      paramBundle = new GridView(getApplicationContext());
      paramBundle.setNumColumns(2);
      localObject = ((d)localObject).C;
      paramBundle.setAdapter(new f(this, -2147483648, (List)localObject));
      paramBundle.setOnItemClickListener(new e(this, (List)localObject));
      setContentView(paramBundle);
      return;
    }
    x.e();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.ListViewActivity
 * JD-Core Version:    0.6.2
 */