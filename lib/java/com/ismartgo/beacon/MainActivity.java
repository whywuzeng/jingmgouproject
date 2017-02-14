package com.ismartgo.beacon;

import android.app.Activity;
import android.os.Bundle;
import com.ismartgo.beacon.impl.SmartgoBeanImpl;

public class MainActivity extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    SmartgoBeanImpl.init(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.MainActivity
 * JD-Core Version:    0.6.2
 */