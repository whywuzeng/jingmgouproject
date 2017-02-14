package com.linj.video.view;

import java.io.IOException;

public abstract interface VideoPlayerOperation
{
  public abstract int getCurrentPosition();

  public abstract boolean isPlaying();

  public abstract void pausedPlay();

  public abstract void playVideo(String paramString)
    throws IllegalArgumentException, SecurityException, IllegalStateException, IOException;

  public abstract void resumePlay();

  public abstract void seekPosition(int paramInt);

  public abstract void stopPlay();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.video.view.VideoPlayerOperation
 * JD-Core Version:    0.6.2
 */