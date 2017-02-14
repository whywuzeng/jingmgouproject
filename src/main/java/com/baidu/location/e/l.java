package com.baidu.location.e;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class l
  implements SensorEventListener, com.baidu.location.b.f
{
  private static l id;
  private SensorManager h8;
  private boolean h9 = false;
  private boolean ia;
  private float ib;
  private float[] ic;
  private double ie = 4.9E-324D;
  private float[] ig;
  private boolean ih = false;

  public static l cg()
  {
    if (id == null)
      id = new l();
    return id;
  }

  public void jdMethod_byte(boolean paramBoolean)
  {
    this.ia = paramBoolean;
  }

  public void jdMethod_case(boolean paramBoolean)
  {
    this.h9 = paramBoolean;
  }

  public boolean cc()
  {
    return this.h9;
  }

  public boolean cd()
  {
    return this.ia;
  }

  public void ce()
  {
    try
    {
      boolean bool = this.ih;
      if (bool);
      while (true)
      {
        return;
        if (this.h8 == null)
          this.h8 = ((SensorManager)com.baidu.location.f.getServiceContext().getSystemService("sensor"));
        if (this.h8 != null)
        {
          Sensor localSensor = this.h8.getDefaultSensor(11);
          if ((localSensor != null) && (this.ia))
            this.h8.registerListener(this, localSensor, 3);
          localSensor = this.h8.getDefaultSensor(6);
          if ((localSensor != null) && (this.h9))
            this.h8.registerListener(this, localSensor, 3);
        }
        this.ih = true;
      }
    }
    finally
    {
    }
  }

  public double cf()
  {
    return this.ie;
  }

  public void ch()
  {
    try
    {
      boolean bool = this.ih;
      if (!bool);
      while (true)
      {
        return;
        if (this.h8 != null)
        {
          this.h8.unregisterListener(this);
          this.h8 = null;
        }
        this.ih = false;
      }
    }
    finally
    {
    }
  }

  public float ci()
  {
    return this.ib;
  }

  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    switch (paramSensorEvent.sensor.getType())
    {
    default:
    case 11:
      do
      {
        return;
        this.ig = ((float[])paramSensorEvent.values.clone());
      }
      while (this.ig == null);
      paramSensorEvent = new float[9];
      SensorManager.getRotationMatrixFromVector(paramSensorEvent, this.ig);
      float[] arrayOfFloat = new float[3];
      SensorManager.getOrientation(paramSensorEvent, arrayOfFloat);
      this.ib = ((float)Math.toDegrees(arrayOfFloat[0]));
      if (this.ib >= 0.0F);
      for (double d = this.ib; ; d = this.ib + 360.0F)
      {
        this.ib = ((float)Math.floor(d));
        return;
      }
    case 6:
    }
    this.ic = ((float[])paramSensorEvent.values.clone());
    this.ie = SensorManager.getAltitude(1013.25F, this.ic[0]);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.l
 * JD-Core Version:    0.6.2
 */