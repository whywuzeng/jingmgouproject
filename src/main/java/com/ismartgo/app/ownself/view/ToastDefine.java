package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastDefine extends Toast
{
  private View layout;
  private String message;
  private Toast toast;
  private ImageView toastImage;
  private LinearLayout toastLayout;
  private TextView toastText;

  public ToastDefine(Context paramContext)
  {
    super(paramContext);
    this.layout = LayoutInflater.from(paramContext).inflate(2130903225, null);
    this.toastLayout = ((LinearLayout)this.layout.findViewById(2131231406));
    this.toastImage = ((ImageView)this.layout.findViewById(2131231407));
    this.toastText = ((TextView)this.layout.findViewById(2131231408));
    this.toast = new Toast(paramContext);
  }

  public void setDisplay(boolean paramBoolean)
  {
    ImageView localImageView = this.toastImage;
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      localImageView.setVisibility(i);
      return;
    }
  }

  public void setImageResource(boolean paramBoolean)
  {
    ImageView localImageView = this.toastImage;
    if (paramBoolean);
    for (int i = 2130837620; ; i = 2130837619)
    {
      localImageView.setImageResource(i);
      return;
    }
  }

  public void setLayoutParams(int paramInt1, int paramInt2)
  {
    this.layout.setMinimumWidth(paramInt1);
    this.layout.setMinimumHeight(paramInt2);
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
    this.toastText.setText(this.message);
    this.toast.setGravity(17, 0, 0);
    this.toast.setDuration(0);
  }

  public void show()
  {
    this.toast.setView(this.layout);
    if ((this.message != null) && (!this.message.equals("")))
      this.toast.show();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.ToastDefine
 * JD-Core Version:    0.6.2
 */