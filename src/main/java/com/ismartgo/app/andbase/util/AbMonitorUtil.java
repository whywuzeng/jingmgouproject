// INTERNAL ERROR //

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbMonitorUtil
 * JD-Core Version:    0.6.2
 */
package com.ismartgo.app.andbase.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.ab.view.app.AbMonitorView;

public final class AbMonitorUtil
{
    private static AbMonitorView mAbMonitorView;
    private static Handler mMonitorHandler;
    public static boolean mMonitorOpened = false;
    private static WindowManager.LayoutParams mMonitorParams = null;
    private static Runnable mMonitorRunnable;
    private static WindowManager mWindowManager;

    static
    {
        mAbMonitorView = null;
        mMonitorHandler = new Handler();
        mMonitorRunnable = null;
        mWindowManager = null;
    }

    public static void closeMonitor()
    {
        if (mMonitorOpened)
        {
            if ((mWindowManager != null) && (mAbMonitorView != null)) {
                mWindowManager.removeView(mAbMonitorView);
            }
            mMonitorOpened = false;
            if ((mMonitorHandler != null) && (mMonitorRunnable != null)) {
                mMonitorHandler.removeCallbacks(mMonitorRunnable);
            }
        }
    }

    public static void openMonitor(Context paramContext)
    {
        if (mMonitorOpened) {
            return;
        }
        mWindowManager = ((Activity)paramContext).getWindowManager();
        int i = AbAppUtil.getDisplayMetrics(paramContext).widthPixels;
        mAbMonitorView = new AbMonitorView(paramContext);
        mMonitorParams = new WindowManager.LayoutParams();
        mMonitorParams.type = 2002;
        mMonitorParams.format = 1;
        mMonitorParams.flags = 40;
        mMonitorParams.width = AbViewUtil.scale(paramContext, 100.0F);
        mMonitorParams.height = AbViewUtil.scale(paramContext, 50.0F);
        mWindowManager.addView(mAbMonitorView, mMonitorParams);
        mMonitorOpened = true;
        mMonitorRunnable = new Runnable()
        {
            public void run()
            {
                AbMonitorUtil.mAbMonitorView.postInvalidate();
                AbMonitorUtil.mMonitorHandler.postDelayed(this, 0L);
            }
        };
        mMonitorHandler.postDelayed(mMonitorRunnable, 0L);
        mAbMonitorView.setOnTouchListener(new View.OnTouchListener()
        {
            int lastX;
            int lastY;
            int paramX;
            int paramY;

            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
                switch (paramAnonymousMotionEvent.getAction())
                {
                    case 1:
                    default:
                    case 0:
                        for (;;)
                        {
                            return true;
                            this.lastX = ((int)paramAnonymousMotionEvent.getRawX());
                            this.lastY = ((int)paramAnonymousMotionEvent.getRawY());
                            this.paramX = AbMonitorUtil.mMonitorParams.x;
                            this.paramY = AbMonitorUtil.mMonitorParams.y;
                        }
                }
                int i = (int)paramAnonymousMotionEvent.getRawX() - this.lastX;
                int j = (int)paramAnonymousMotionEvent.getRawY();
                int k = this.lastY;
                if (this.paramX + i > this.val$diaplayWidth / 2) {}
                for (AbMonitorUtil.mMonitorParams.x = this.val$diaplayWidth;; AbMonitorUtil.mMonitorParams.x = 0)
                {
                    AbMonitorUtil.mMonitorParams.x = (this.paramX + i);
                    AbMonitorUtil.mMonitorParams.y = (this.paramY + (j - k));
                    AbMonitorUtil.mWindowManager.updateViewLayout(AbMonitorUtil.mAbMonitorView, AbMonitorUtil.mMonitorParams);
                    break;
                }
            }
        });
    }
}
