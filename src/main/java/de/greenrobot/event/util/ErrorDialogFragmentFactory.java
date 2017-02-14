package de.greenrobot.event.util;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Bundle;

public abstract class ErrorDialogFragmentFactory<T>
{
  protected final ErrorDialogConfig config;

  protected ErrorDialogFragmentFactory(ErrorDialogConfig paramErrorDialogConfig)
  {
    this.config = paramErrorDialogConfig;
  }

  protected abstract T createErrorFragment(ThrowableFailureEvent paramThrowableFailureEvent, Bundle paramBundle);

  protected String getMessageFor(ThrowableFailureEvent paramThrowableFailureEvent, Bundle paramBundle)
  {
    int i = this.config.getMessageIdForThrowable(paramThrowableFailureEvent.throwable);
    return this.config.resources.getString(i);
  }

  protected String getTitleFor(ThrowableFailureEvent paramThrowableFailureEvent, Bundle paramBundle)
  {
    return this.config.resources.getString(this.config.defaultTitleId);
  }

  protected T prepareErrorFragment(ThrowableFailureEvent paramThrowableFailureEvent, boolean paramBoolean, Bundle paramBundle)
  {
    if (paramThrowableFailureEvent.isSuppressErrorUi())
      return null;
    if (paramBundle != null);
    for (paramBundle = (Bundle)paramBundle.clone(); ; paramBundle = new Bundle())
    {
      if (!paramBundle.containsKey("de.greenrobot.eventbus.errordialog.title"))
        paramBundle.putString("de.greenrobot.eventbus.errordialog.title", getTitleFor(paramThrowableFailureEvent, paramBundle));
      if (!paramBundle.containsKey("de.greenrobot.eventbus.errordialog.message"))
        paramBundle.putString("de.greenrobot.eventbus.errordialog.message", getMessageFor(paramThrowableFailureEvent, paramBundle));
      if (!paramBundle.containsKey("de.greenrobot.eventbus.errordialog.finish_after_dialog"))
        paramBundle.putBoolean("de.greenrobot.eventbus.errordialog.finish_after_dialog", paramBoolean);
      if ((!paramBundle.containsKey("de.greenrobot.eventbus.errordialog.event_type_on_close")) && (this.config.defaultEventTypeOnDialogClosed != null))
        paramBundle.putSerializable("de.greenrobot.eventbus.errordialog.event_type_on_close", this.config.defaultEventTypeOnDialogClosed);
      if ((!paramBundle.containsKey("de.greenrobot.eventbus.errordialog.icon_id")) && (this.config.defaultDialogIconId != 0))
        paramBundle.putInt("de.greenrobot.eventbus.errordialog.icon_id", this.config.defaultDialogIconId);
      return createErrorFragment(paramThrowableFailureEvent, paramBundle);
    }
  }

  @TargetApi(11)
  public static class Honeycomb extends ErrorDialogFragmentFactory<android.app.Fragment>
  {
    public Honeycomb(ErrorDialogConfig paramErrorDialogConfig)
    {
      super();
    }

    protected android.app.Fragment createErrorFragment(ThrowableFailureEvent paramThrowableFailureEvent, Bundle paramBundle)
    {
      paramThrowableFailureEvent = new ErrorDialogFragments.Honeycomb();
      paramThrowableFailureEvent.setArguments(paramBundle);
      return paramThrowableFailureEvent;
    }
  }

  public static class Support extends ErrorDialogFragmentFactory<android.support.v4.app.Fragment>
  {
    public Support(ErrorDialogConfig paramErrorDialogConfig)
    {
      super();
    }

    protected android.support.v4.app.Fragment createErrorFragment(ThrowableFailureEvent paramThrowableFailureEvent, Bundle paramBundle)
    {
      paramThrowableFailureEvent = new ErrorDialogFragments.Support();
      paramThrowableFailureEvent.setArguments(paramBundle);
      return paramThrowableFailureEvent;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.util.ErrorDialogFragmentFactory
 * JD-Core Version:    0.6.2
 */