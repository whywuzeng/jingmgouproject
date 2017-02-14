package com.linj.video.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VideoPlayerContainer extends LinearLayout
  implements View.OnClickListener, VideoPlayerView.PlayerListener, VideoPlayerOperation
{
  private static final String TAG = "VideoPlayerContainer";
  private LinearLayout mBottomBar;
  private TextView mCurrentTimeView;
  private TextView mDurationView;
  private Handler mHandler;
  private ImageView mPauseButton;
  private SeekBar mProgressBar;
  private SimpleDateFormat mTimeFormat;
  private VideoPlayerView mVideoPlayerView;
  SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener()
  {
    public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
    {
      VideoPlayerContainer.this.mCurrentTimeView.setText(VideoPlayerContainer.this.mTimeFormat.format(new Date(paramAnonymousInt * 1000)));
    }

    public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
    {
      VideoPlayerContainer.this.pausedPlay();
    }

    public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
    {
      VideoPlayerContainer.this.seekPosition(paramAnonymousSeekBar.getProgress() * 1000);
    }
  };
  Runnable playerRunnable = new Runnable()
  {
    public void run()
    {
      if (VideoPlayerContainer.this.mVideoPlayerView.isPlaying())
      {
        int i = (int)Math.floor(VideoPlayerContainer.this.getCurrentPosition() / 1000);
        VideoPlayerContainer.this.mProgressBar.setProgress(i);
        VideoPlayerContainer.this.mHandler.postAtTime(this, VideoPlayerContainer.this.mCurrentTimeView, SystemClock.uptimeMillis() + 500L);
      }
    }
  };

  public VideoPlayerContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
    this.mHandler = new Handler();
    this.mTimeFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());
  }

  private void initView(Context paramContext)
  {
    inflate(paramContext, 2130903244, this);
    this.mVideoPlayerView = ((VideoPlayerView)findViewById(2131231465));
    this.mVideoPlayerView.setPalyerListener(this);
    this.mBottomBar = ((LinearLayout)findViewById(2131231466));
    this.mCurrentTimeView = ((TextView)this.mBottomBar.findViewById(2131231468));
    this.mDurationView = ((TextView)this.mBottomBar.findViewById(2131231469));
    this.mPauseButton = ((ImageView)this.mBottomBar.findViewById(2131231467));
    this.mProgressBar = ((SeekBar)this.mBottomBar.findViewById(2131231470));
    this.mPauseButton.setOnClickListener(this);
    this.mProgressBar.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
  }

  public int getCurrentPosition()
  {
    return this.mVideoPlayerView.getCurrentPosition();
  }

  public boolean isPlaying()
  {
    return this.mVideoPlayerView.isPlaying();
  }

  public void onClick(View paramView)
  {
    if (this.mVideoPlayerView.isPlaying())
    {
      pausedPlay();
      return;
    }
    resumePlay();
  }

  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    setVisibility(8);
    this.mProgressBar.setProgress(0);
    this.mCurrentTimeView.setText("00:00");
    paramMediaPlayer.reset();
  }

  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    setVisibility(0);
    int i = paramMediaPlayer.getDuration();
    this.mDurationView.setText(this.mTimeFormat.format(new Date(i)));
    this.mProgressBar.setMax((int)Math.floor(i / 1000));
    paramMediaPlayer.start();
    this.mHandler.removeCallbacks(null, null);
    this.mHandler.post(this.playerRunnable);
  }

  public void onSeekComplete(MediaPlayer paramMediaPlayer)
  {
    resumePlay();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return true;
  }

  public void pausedPlay()
  {
    this.mVideoPlayerView.pausedPlay();
    this.mPauseButton.setImageResource(2130837947);
  }

  public void playVideo(String paramString)
    throws IllegalArgumentException, SecurityException, IllegalStateException, IOException
  {
    this.mVideoPlayerView.playVideo(paramString);
  }

  public void resumePlay()
  {
    this.mVideoPlayerView.resumePlay();
    this.mHandler.removeCallbacks(null, null);
    this.mHandler.postDelayed(this.playerRunnable, 500L);
    this.mPauseButton.setImageResource(2130837946);
  }

  public void seekPosition(int paramInt)
  {
    this.mVideoPlayerView.seekPosition(paramInt);
  }

  public void stopPlay()
  {
    this.mVideoPlayerView.stopPlay();
    setVisibility(8);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.video.view.VideoPlayerContainer
 * JD-Core Version:    0.6.2
 */