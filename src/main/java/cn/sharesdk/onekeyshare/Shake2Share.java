package cn.sharesdk.onekeyshare;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.R;

public class Shake2Share extends FakeActivity
  implements SensorEventListener
{
  private static final int SHAKE_THRESHOLD = 1500;
  private static final int UPDATE_INTERVAL = 100;
  private OnShakeListener listener;
  private long mLastUpdateTime;
  private float mLastX;
  private float mLastY;
  private float mLastZ;
  private SensorManager mSensorManager;
  private boolean shaken;

  private void startSensor()
  {
    this.mSensorManager = ((SensorManager)this.activity.getSystemService("sensor"));
    if (this.mSensorManager == null)
      throw new UnsupportedOperationException();
    Sensor localSensor = this.mSensorManager.getDefaultSensor(1);
    if (localSensor == null)
      throw new UnsupportedOperationException();
    if (!this.mSensorManager.registerListener(this, localSensor, 1))
      throw new UnsupportedOperationException();
  }

  private void stopSensor()
  {
    if (this.mSensorManager != null)
    {
      this.mSensorManager.unregisterListener(this);
      this.mSensorManager = null;
    }
  }

  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onCreate()
  {
    startSensor();
    int i = R.getBitmapRes(this.activity, "ssdk_oks_yaoyiyao");
    if (i > 0)
    {
      ImageView localImageView = new ImageView(this.activity);
      localImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      localImageView.setImageResource(i);
      this.activity.setContentView(localImageView);
    }
    i = R.getStringRes(this.activity, "shake2share");
    if (i > 0)
      Toast.makeText(this.activity, i, 0).show();
  }

  public void onDestroy()
  {
    stopSensor();
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    long l1 = System.currentTimeMillis();
    long l2 = l1 - this.mLastUpdateTime;
    if (l2 > 100L)
    {
      if (this.mLastUpdateTime != 0L)
      {
        float f1 = paramSensorEvent.values[0];
        float f2 = paramSensorEvent.values[1];
        float f3 = paramSensorEvent.values[2];
        float f4 = f1 - this.mLastX;
        float f5 = f2 - this.mLastY;
        float f6 = f3 - this.mLastZ;
        if (FloatMath.sqrt(f4 * f4 + f5 * f5 + f6 * f6) / (float)l2 * 10000.0F > 1500.0F)
        {
          if (!this.shaken)
          {
            this.shaken = true;
            finish();
          }
          if (this.listener != null)
            this.listener.onShake();
        }
        this.mLastX = f1;
        this.mLastY = f2;
        this.mLastZ = f3;
      }
      this.mLastUpdateTime = l1;
    }
  }

  public void setActivity(Activity paramActivity)
  {
    super.setActivity(paramActivity);
    int i = R.getBitmapRes(paramActivity, "ssdk_oks_shake_to_share_back");
    if (i > 0)
    {
      paramActivity.setTheme(16973835);
      paramActivity.requestWindowFeature(1);
      paramActivity.getWindow().setBackgroundDrawableResource(i);
    }
  }

  public void setOnShakeListener(OnShakeListener paramOnShakeListener)
  {
    this.listener = paramOnShakeListener;
  }

  public static abstract interface OnShakeListener
  {
    public abstract void onShake();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.Shake2Share
 * JD-Core Version:    0.6.2
 */