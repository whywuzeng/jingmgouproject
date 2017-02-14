package com.linj.video.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import java.io.IOException;

public class VideoPlayerView extends SurfaceView
  implements VideoPlayerOperation
{
  private static final String TAG = "VideoSurfaceView";
  private SurfaceHolder.Callback callback = new SurfaceHolder.Callback()
  {
    public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
    }

    public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
    {
      VideoPlayerView.this.mMediaPlayer.setDisplay(VideoPlayerView.this.getHolder());
    }

    public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
    {
      if (VideoPlayerView.this.mMediaPlayer.isPlaying())
        VideoPlayerView.this.mMediaPlayer.stop();
      VideoPlayerView.this.mMediaPlayer.reset();
    }
  };
  private MediaPlayer mMediaPlayer;

  public VideoPlayerView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public VideoPlayerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void init()
  {
    this.mMediaPlayer = new MediaPlayer();
    getHolder().addCallback(this.callback);
  }

  public int getCurrentPosition()
  {
    if (isPlaying())
      return this.mMediaPlayer.getCurrentPosition();
    return 0;
  }

  public boolean isPlaying()
  {
    return this.mMediaPlayer.isPlaying();
  }

  public void pausedPlay()
  {
    this.mMediaPlayer.pause();
  }

  public void playVideo(String paramString)
    throws IllegalArgumentException, SecurityException, IllegalStateException, IOException
  {
    if ((this.mMediaPlayer != null) && (this.mMediaPlayer.isPlaying()))
      this.mMediaPlayer.stop();
    this.mMediaPlayer.reset();
    this.mMediaPlayer.setDataSource(paramString);
    this.mMediaPlayer.prepare();
  }

  public void resumePlay()
  {
    this.mMediaPlayer.start();
  }

  public void seekPosition(int paramInt)
  {
    if (isPlaying())
      this.mMediaPlayer.pause();
    if ((paramInt < 0) || (paramInt > this.mMediaPlayer.getDuration()))
    {
      this.mMediaPlayer.stop();
      return;
    }
    this.mMediaPlayer.seekTo(paramInt);
  }

  protected void setPalyerListener(PlayerListener paramPlayerListener)
  {
    this.mMediaPlayer.setOnCompletionListener(paramPlayerListener);
    this.mMediaPlayer.setOnSeekCompleteListener(paramPlayerListener);
    this.mMediaPlayer.setOnPreparedListener(paramPlayerListener);
  }

  public void stopPlay()
  {
    this.mMediaPlayer.stop();
    this.mMediaPlayer.reset();
  }

  public static abstract interface PlayerListener extends MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnPreparedListener
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.video.view.VideoPlayerView
 * JD-Core Version:    0.6.2
 */