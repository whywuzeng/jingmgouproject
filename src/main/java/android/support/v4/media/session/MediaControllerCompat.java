package android.support.v4.media.session;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;

public final class MediaControllerCompat
{
  private final MediaControllerImpl mImpl;

  public MediaControllerCompat(Context paramContext, MediaSessionCompat.Token paramToken)
    throws RemoteException
  {
    if (paramToken == null)
      throw new IllegalArgumentException("sessionToken must not be null");
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.mImpl = new MediaControllerImplApi21(paramContext, paramToken);
      return;
    }
    this.mImpl = new MediaControllerImplBase();
  }

  public MediaControllerCompat(Context paramContext, MediaSessionCompat paramMediaSessionCompat)
  {
    if (paramMediaSessionCompat == null)
      throw new IllegalArgumentException("session must not be null");
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.mImpl = new MediaControllerImplApi21(paramContext, paramMediaSessionCompat);
      return;
    }
    this.mImpl = new MediaControllerImplBase();
  }

  public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent == null)
      throw new IllegalArgumentException("KeyEvent may not be null");
    return this.mImpl.dispatchMediaButtonEvent(paramKeyEvent);
  }

  public Object getMediaController()
  {
    return this.mImpl.getMediaController();
  }

  public MediaMetadataCompat getMetadata()
  {
    return this.mImpl.getMetadata();
  }

  public PlaybackInfo getPlaybackInfo()
  {
    return this.mImpl.getPlaybackInfo();
  }

  public PlaybackStateCompat getPlaybackState()
  {
    return this.mImpl.getPlaybackState();
  }

  public int getRatingType()
  {
    return this.mImpl.getRatingType();
  }

  public TransportControls getTransportControls()
  {
    return this.mImpl.getTransportControls();
  }

  public void registerCallback(Callback paramCallback)
  {
    registerCallback(paramCallback, null);
  }

  public void registerCallback(Callback paramCallback, Handler paramHandler)
  {
    if (paramCallback == null)
      throw new IllegalArgumentException("callback cannot be null");
    Handler localHandler = paramHandler;
    if (paramHandler == null)
      localHandler = new Handler();
    this.mImpl.registerCallback(paramCallback, localHandler);
  }

  public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("command cannot be null or empty");
    this.mImpl.sendCommand(paramString, paramBundle, paramResultReceiver);
  }

  public void unregisterCallback(Callback paramCallback)
  {
    if (paramCallback == null)
      throw new IllegalArgumentException("callback cannot be null");
    this.mImpl.unregisterCallback(paramCallback);
  }

  public static abstract class Callback
  {
    final Object mCallbackObj;

    public Callback()
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        this.mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21(null));
        return;
      }
      this.mCallbackObj = null;
    }

    public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat)
    {
    }

    public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat)
    {
    }

    public void onSessionDestroyed()
    {
    }

    public void onSessionEvent(String paramString, Bundle paramBundle)
    {
    }

    private class StubApi21
      implements MediaControllerCompatApi21.Callback
    {
      private StubApi21()
      {
      }

      public void onMetadataChanged(Object paramObject)
      {
        MediaControllerCompat.Callback.this.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(paramObject));
      }

      public void onPlaybackStateChanged(Object paramObject)
      {
        MediaControllerCompat.Callback.this.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(paramObject));
      }

      public void onSessionDestroyed()
      {
        MediaControllerCompat.Callback.this.onSessionDestroyed();
      }

      public void onSessionEvent(String paramString, Bundle paramBundle)
      {
        MediaControllerCompat.Callback.this.onSessionEvent(paramString, paramBundle);
      }
    }
  }

  static abstract interface MediaControllerImpl
  {
    public abstract boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent);

    public abstract Object getMediaController();

    public abstract MediaMetadataCompat getMetadata();

    public abstract MediaControllerCompat.PlaybackInfo getPlaybackInfo();

    public abstract PlaybackStateCompat getPlaybackState();

    public abstract int getRatingType();

    public abstract MediaControllerCompat.TransportControls getTransportControls();

    public abstract void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler);

    public abstract void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver);

    public abstract void unregisterCallback(MediaControllerCompat.Callback paramCallback);
  }

  static class MediaControllerImplApi21
    implements MediaControllerCompat.MediaControllerImpl
  {
    private final Object mControllerObj;

    public MediaControllerImplApi21(Context paramContext, MediaSessionCompat.Token paramToken)
      throws RemoteException
    {
      this.mControllerObj = MediaControllerCompatApi21.fromToken(paramContext, paramToken.getToken());
      if (this.mControllerObj == null)
        throw new RemoteException();
    }

    public MediaControllerImplApi21(Context paramContext, MediaSessionCompat paramMediaSessionCompat)
    {
      this.mControllerObj = MediaControllerCompatApi21.fromToken(paramContext, paramMediaSessionCompat.getSessionToken().getToken());
    }

    public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
    {
      return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, paramKeyEvent);
    }

    public Object getMediaController()
    {
      return this.mControllerObj;
    }

    public MediaMetadataCompat getMetadata()
    {
      Object localObject = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
      if (localObject != null)
        return MediaMetadataCompat.fromMediaMetadata(localObject);
      return null;
    }

    public MediaControllerCompat.PlaybackInfo getPlaybackInfo()
    {
      Object localObject = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
      if (localObject != null)
        return new MediaControllerCompat.PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(localObject), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(localObject), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(localObject), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(localObject), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(localObject));
      return null;
    }

    public PlaybackStateCompat getPlaybackState()
    {
      Object localObject = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
      if (localObject != null)
        return PlaybackStateCompat.fromPlaybackState(localObject);
      return null;
    }

    public int getRatingType()
    {
      return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
    }

    public MediaControllerCompat.TransportControls getTransportControls()
    {
      Object localObject = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
      if (localObject != null)
        return new MediaControllerCompat.TransportControlsApi21(localObject);
      return null;
    }

    public void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler)
    {
      MediaControllerCompatApi21.registerCallback(this.mControllerObj, paramCallback.mCallbackObj, paramHandler);
    }

    public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      MediaControllerCompatApi21.sendCommand(this.mControllerObj, paramString, paramBundle, paramResultReceiver);
    }

    public void unregisterCallback(MediaControllerCompat.Callback paramCallback)
    {
      MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, paramCallback.mCallbackObj);
    }
  }

  static class MediaControllerImplBase
    implements MediaControllerCompat.MediaControllerImpl
  {
    public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
    {
      return false;
    }

    public Object getMediaController()
    {
      return null;
    }

    public MediaMetadataCompat getMetadata()
    {
      return null;
    }

    public MediaControllerCompat.PlaybackInfo getPlaybackInfo()
    {
      return null;
    }

    public PlaybackStateCompat getPlaybackState()
    {
      return null;
    }

    public int getRatingType()
    {
      return 0;
    }

    public MediaControllerCompat.TransportControls getTransportControls()
    {
      return null;
    }

    public void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler)
    {
    }

    public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
    }

    public void unregisterCallback(MediaControllerCompat.Callback paramCallback)
    {
    }
  }

  public static final class PlaybackInfo
  {
    public static final int PLAYBACK_TYPE_LOCAL = 1;
    public static final int PLAYBACK_TYPE_REMOTE = 2;
    private final int mAudioStream;
    private final int mCurrentVolume;
    private final int mMaxVolume;
    private final int mPlaybackType;
    private final int mVolumeControl;

    PlaybackInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      this.mPlaybackType = paramInt1;
      this.mAudioStream = paramInt2;
      this.mVolumeControl = paramInt3;
      this.mMaxVolume = paramInt4;
      this.mCurrentVolume = paramInt5;
    }

    public int getAudioStream()
    {
      return this.mAudioStream;
    }

    public int getCurrentVolume()
    {
      return this.mCurrentVolume;
    }

    public int getMaxVolume()
    {
      return this.mMaxVolume;
    }

    public int getPlaybackType()
    {
      return this.mPlaybackType;
    }

    public int getVolumeControl()
    {
      return this.mVolumeControl;
    }
  }

  public static abstract class TransportControls
  {
    public abstract void fastForward();

    public abstract void pause();

    public abstract void play();

    public abstract void rewind();

    public abstract void seekTo(long paramLong);

    public abstract void setRating(RatingCompat paramRatingCompat);

    public abstract void skipToNext();

    public abstract void skipToPrevious();

    public abstract void stop();
  }

  static class TransportControlsApi21 extends MediaControllerCompat.TransportControls
  {
    private final Object mControlsObj;

    public TransportControlsApi21(Object paramObject)
    {
      this.mControlsObj = paramObject;
    }

    public void fastForward()
    {
      MediaControllerCompatApi21.TransportControls.fastForward(this.mControlsObj);
    }

    public void pause()
    {
      MediaControllerCompatApi21.TransportControls.pause(this.mControlsObj);
    }

    public void play()
    {
      MediaControllerCompatApi21.TransportControls.play(this.mControlsObj);
    }

    public void rewind()
    {
      MediaControllerCompatApi21.TransportControls.rewind(this.mControlsObj);
    }

    public void seekTo(long paramLong)
    {
      MediaControllerCompatApi21.TransportControls.seekTo(this.mControlsObj, paramLong);
    }

    public void setRating(RatingCompat paramRatingCompat)
    {
      Object localObject = this.mControlsObj;
      if (paramRatingCompat != null);
      for (paramRatingCompat = paramRatingCompat.getRating(); ; paramRatingCompat = null)
      {
        MediaControllerCompatApi21.TransportControls.setRating(localObject, paramRatingCompat);
        return;
      }
    }

    public void skipToNext()
    {
      MediaControllerCompatApi21.TransportControls.skipToNext(this.mControlsObj);
    }

    public void skipToPrevious()
    {
      MediaControllerCompatApi21.TransportControls.skipToPrevious(this.mControlsObj);
    }

    public void stop()
    {
      MediaControllerCompatApi21.TransportControls.stop(this.mControlsObj);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat
 * JD-Core Version:    0.6.2
 */