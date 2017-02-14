package com.ab.view.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.ab.global.AbAppData;
import com.ab.util.AbGraphical;

public class AbMonitorView extends View
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbMonitorView";
  private int mCounter;
  private int mFps;
  private final Paint mPaint = new Paint();
  private long mStartTime = -1L;

  public AbMonitorView(Context paramContext)
  {
    this(paramContext, null);
  }

  public AbMonitorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext);
    this.mPaint.setColor(-1);
    this.mPaint.setAntiAlias(true);
    this.mPaint.setTypeface(Typeface.DEFAULT);
    this.mPaint.setTextSize(16.0F);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.drawColor(Color.argb(80, 0, 0, 0));
    if (this.mStartTime == -1L)
    {
      this.mStartTime = SystemClock.elapsedRealtime();
      this.mCounter = 0;
    }
    long l1 = SystemClock.elapsedRealtime();
    long l2 = l1 - this.mStartTime;
    if (l2 != 0L)
      this.mFps = ((int)(this.mCounter * 1000 / l2));
    String str = this.mFps + " fps";
    TextPaint localTextPaint = new TextPaint(1);
    localTextPaint.setTypeface(Typeface.DEFAULT);
    localTextPaint.setTextSize(16.0F);
    Paint.FontMetrics localFontMetrics = localTextPaint.getFontMetrics();
    int i = (int)Math.ceil(localFontMetrics.descent - localFontMetrics.ascent);
    int j = (int)AbGraphical.getStringWidth(str, localTextPaint);
    paramCanvas.drawText(str, (getWidth() - j) / 2, i + 2, this.mPaint);
    if (l2 > 1000L)
    {
      this.mStartTime = l1;
      this.mFps = this.mCounter;
      this.mCounter = 0;
    }
    this.mCounter += 1;
    super.onDraw(paramCanvas);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.app.AbMonitorView
 * JD-Core Version:    0.6.2
 */