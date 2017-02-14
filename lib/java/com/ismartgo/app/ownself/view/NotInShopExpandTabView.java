package com.ismartgo.app.ownself.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ToggleButton;
import com.ismartgo.app.interfaces.ViewBaseActionInterface;
import java.util.ArrayList;

public class NotInShopExpandTabView extends LinearLayout
  implements PopupWindow.OnDismissListener
{
  private final int SMALL = 0;
  Context context;
  private int displayHeight;
  private int displayWidth;
  private Context mContext;
  private OnButtonClickListener mOnButtonClickListener;
  private ArrayList<String> mTextArray = new ArrayList();
  private ArrayList<ToggleButton> mToggleButton = new ArrayList();
  private ArrayList<RelativeLayout> mViewArray = new ArrayList();
  private PopupWindow popupWindow;
  private int selectPosition;
  private ToggleButton selectedButton;
  int topItemPadding;

  public NotInShopExpandTabView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init(paramContext);
  }

  public NotInShopExpandTabView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    init(paramContext);
  }

  private void hideView()
  {
    View localView = ((RelativeLayout)this.mViewArray.get(this.selectPosition)).getChildAt(0);
    if ((localView instanceof ViewBaseActionInterface))
      ((ViewBaseActionInterface)localView).hide();
  }

  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    this.displayWidth = ((Activity)this.mContext).getWindowManager().getDefaultDisplay().getWidth();
    this.displayHeight = ((Activity)this.mContext).getWindowManager().getDefaultDisplay().getHeight();
    setOrientation(0);
  }

  private void showPopup(int paramInt)
  {
    View localView = ((RelativeLayout)this.mViewArray.get(this.selectPosition)).getChildAt(0);
    if ((localView instanceof ViewBaseActionInterface))
      ((ViewBaseActionInterface)localView).show();
    if (this.popupWindow.getContentView() != this.mViewArray.get(paramInt))
      this.popupWindow.setContentView((View)this.mViewArray.get(paramInt));
    this.popupWindow.showAsDropDown(this, 0, 0);
  }

  private void startAnimation()
  {
    if (this.popupWindow == null)
    {
      this.popupWindow = new PopupWindow((View)this.mViewArray.get(this.selectPosition), this.displayWidth, this.displayHeight);
      this.popupWindow.setFocusable(false);
      this.popupWindow.setOutsideTouchable(true);
    }
    if (this.selectedButton.isChecked())
      if (!this.popupWindow.isShowing())
        showPopup(this.selectPosition);
    while (!this.popupWindow.isShowing())
    {
      return;
      this.popupWindow.setOnDismissListener(this);
      this.popupWindow.dismiss();
      hideView();
      return;
    }
    this.popupWindow.dismiss();
    hideView();
  }

  public String getTitle(int paramInt)
  {
    if ((paramInt < this.mToggleButton.size()) && (((ToggleButton)this.mToggleButton.get(paramInt)).getText() != null))
      return ((ToggleButton)this.mToggleButton.get(paramInt)).getText().toString();
    return "";
  }

  public void onDismiss()
  {
    showPopup(this.selectPosition);
    this.popupWindow.setOnDismissListener(null);
  }

  public boolean onPressBack()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.popupWindow != null)
    {
      bool1 = bool2;
      if (this.popupWindow.isShowing())
      {
        this.popupWindow.dismiss();
        hideView();
        if (this.selectedButton != null)
          this.selectedButton.setChecked(false);
        bool1 = true;
      }
    }
    return bool1;
  }

  public void setOnButtonClickListener(OnButtonClickListener paramOnButtonClickListener)
  {
    this.mOnButtonClickListener = paramOnButtonClickListener;
  }

  public void setTitle(String paramString)
  {
  }

  public void setTitle(String paramString, int paramInt)
  {
    if (paramInt < this.mToggleButton.size())
      ((ToggleButton)this.mToggleButton.get(paramInt)).setText(paramString);
  }

  public void setValue(ArrayList<String> paramArrayList, ArrayList<View> paramArrayList1)
  {
    if (this.mContext == null);
    while (true)
    {
      return;
      LayoutInflater localLayoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
      this.mTextArray = paramArrayList;
      int i = 0;
      while (i < paramArrayList1.size())
      {
        RelativeLayout localRelativeLayout = new RelativeLayout(this.mContext);
        Object localObject1 = new RelativeLayout.LayoutParams(-1, (int)(this.displayHeight * 0.7D));
        localRelativeLayout.addView((View)paramArrayList1.get(i), (ViewGroup.LayoutParams)localObject1);
        this.mViewArray.add(localRelativeLayout);
        localRelativeLayout.setTag(Integer.valueOf(0));
        localObject1 = (ToggleButton)localLayoutInflater.inflate(2130903187, this, false);
        Object localObject2 = getResources().getDrawable(2130837679);
        ((Drawable)localObject2).setBounds(0, 0, ((Drawable)localObject2).getMinimumWidth(), ((Drawable)localObject2).getMinimumHeight());
        ((ToggleButton)localObject1).setCompoundDrawables(null, null, (Drawable)localObject2, null);
        if (paramArrayList.size() == 1)
        {
          ((ToggleButton)localObject1).setBackgroundColor(0);
          ((ToggleButton)localObject1).setTextColor(-1);
        }
        addView((View)localObject1);
        localObject2 = new ImageView(this.mContext);
        ((View)localObject2).setBackgroundResource(2130837765);
        if (i < paramArrayList1.size() - 1)
          addView((View)localObject2, new LinearLayout.LayoutParams(1, -2));
        this.mToggleButton.add(localObject1);
        ((ToggleButton)localObject1).setTag(Integer.valueOf(i));
        ((ToggleButton)localObject1).setText((CharSequence)this.mTextArray.get(i));
        localRelativeLayout.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            NotInShopExpandTabView.this.onPressBack();
          }
        });
        localRelativeLayout.setBackgroundColor(this.mContext.getResources().getColor(2131099687));
        ((ToggleButton)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = (ToggleButton)paramAnonymousView;
            if ((NotInShopExpandTabView.this.selectedButton != null) && (NotInShopExpandTabView.this.selectedButton != paramAnonymousView))
              NotInShopExpandTabView.this.selectedButton.setChecked(false);
            NotInShopExpandTabView.this.selectedButton = paramAnonymousView;
            NotInShopExpandTabView.this.selectPosition = ((Integer)NotInShopExpandTabView.this.selectedButton.getTag()).intValue();
            NotInShopExpandTabView.this.startAnimation();
            if ((NotInShopExpandTabView.this.mOnButtonClickListener != null) && (paramAnonymousView.isChecked()))
              NotInShopExpandTabView.this.mOnButtonClickListener.onClick(NotInShopExpandTabView.this.selectPosition);
          }
        });
        i += 1;
      }
    }
  }

  public static abstract interface OnButtonClickListener
  {
    public abstract void onClick(int paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.NotInShopExpandTabView
 * JD-Core Version:    0.6.2
 */