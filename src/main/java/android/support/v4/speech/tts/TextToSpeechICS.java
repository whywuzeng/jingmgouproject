package android.support.v4.speech.tts;

import android.content.Context;
import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

class TextToSpeechICS
{
  private static final String TAG = "android.support.v4.speech.tts";

  static TextToSpeech construct(Context paramContext, TextToSpeech.OnInitListener paramOnInitListener, String paramString)
  {
    if (Build.VERSION.SDK_INT < 14)
    {
      if (paramString == null)
        return new TextToSpeech(paramContext, paramOnInitListener);
      Log.w("android.support.v4.speech.tts", "Can't specify tts engine on this device");
      return new TextToSpeech(paramContext, paramOnInitListener);
    }
    return new TextToSpeech(paramContext, paramOnInitListener, paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.speech.tts.TextToSpeechICS
 * JD-Core Version:    0.6.2
 */