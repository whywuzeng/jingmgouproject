package com.ismartgo.app.ownself.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ToggleButton;
import com.ismartgo.app.activity.PromotionActivity;
import com.ismartgo.app.interfaces.ViewBaseActionInterface;
import com.ismartgo.app.service.IbeaconService;
import java.io.PrintStream;
import java.util.ArrayList;

public class ExpandTabView extends LinearLayout
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

  public ExpandTabView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init(paramContext);
  }

  public ExpandTabView(Context paramContext, AttributeSet paramAttributeSet)
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

  public void hidePopup()
  {
    if ((this.popupWindow != null) && (this.popupWindow.isShowing()))
    {
      this.popupWindow.dismiss();
      hideView();
      ((ToggleButton)this.mToggleButton.get(2)).setChecked(false);
    }
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
    {
      System.out.println("--->setTitle1=" + paramString + "," + paramInt);
      ((ToggleButton)this.mToggleButton.get(paramInt)).setText(paramString);
      System.out.println("--->setTitle2=" + paramString + "," + paramInt);
      if (this.selectedButton != null);
    }
    else
    {
      return;
    }
    this.selectedButton.setPadding(this.topItemPadding, 0, this.topItemPadding, 0);
  }

  @SuppressLint({"ResourceAsColor"})
  public void setValue(ArrayList<String> paramArrayList, ArrayList<View> paramArrayList1)
  {
    if (this.mContext == null)
      return;
    LayoutInflater localLayoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
    this.mTextArray = paramArrayList;
    int i = 0;
    label29: RelativeLayout localRelativeLayout;
    Object localObject;
    if (i < paramArrayList1.size())
    {
      localRelativeLayout = new RelativeLayout(this.mContext);
      localObject = new RelativeLayout.LayoutParams(-1, (int)(this.displayHeight * 0.6D));
      localRelativeLayout.addView((View)paramArrayList1.get(i), (ViewGroup.LayoutParams)localObject);
      this.mViewArray.add(localRelativeLayout);
      localRelativeLayout.setTag(Integer.valueOf(0));
      localObject = (ToggleButton)localLayoutInflater.inflate(2130903187, this, false);
      if (paramArrayList.size() == 1)
      {
        ((ToggleButton)localObject).setBackgroundColor(0);
        ((ToggleButton)localObject).setTextColor(getResources().getColor(2131099705));
      }
      addView((View)localObject);
      View localView = new View(this.mContext);
      localView.setBackgroundResource(2130837765);
      if (i < paramArrayList1.size() - 1)
        addView(localView, new LinearLayout.LayoutParams(1, -1));
      this.mToggleButton.add(localObject);
      ((ToggleButton)localObject).setTag(Integer.valueOf(i));
      ((ToggleButton)localObject).setText((CharSequence)this.mTextArray.get(i));
      PromotionActivity.topItemCount = 3;
      Log.e("Home", "------" + PromotionActivity.topItemCount);
      if (PromotionActivity.topItemCount != 3)
        break label370;
    }
    label370: for (this.topItemPadding = ((int)(((Activity)this.context).getWindowManager().getDefaultDisplay().getWidth() / 3.0F / 6.0F)); ; this.topItemPadding = 170)
    {
      ((ToggleButton)localObject).setPadding(this.topItemPadding, 0, this.topItemPadding, 0);
      localRelativeLayout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ExpandTabView.this.onPressBack();
        }
      });
      localRelativeLayout.setBackgroundColor(this.mContext.getResources().getColor(2131099687));
      ((ToggleButton)localObject).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (ToggleButton)paramAnonymousView;
          if ((ExpandTabView.this.selectedButton != null) && (ExpandTabView.this.selectedButton != paramAnonymousView))
            ExpandTabView.this.selectedButton.setChecked(false);
          ExpandTabView.this.selectedButton = paramAnonymousView;
          ExpandTabView.this.selectPosition = ((Integer)ExpandTabView.this.selectedButton.getTag()).intValue();
          if ((ExpandTabView.this.selectPosition == 2) && (IbeaconService.shopTypeId < 0))
          {
            ExpandTabView.this.selectedButton.setChecked(false);
            paramAnonymousView = new ToastDefine(ExpandTabView.this.context);
            paramAnonymousView.setMessage("请先选择商店类型");
            paramAnonymousView.show();
            return;
          }
          ExpandTabView.this.startAnimation();
          if ((ExpandTabView.this.mOnButtonClickListener != null) && (paramAnonymousView.isChecked()))
            ExpandTabView.this.mOnButtonClickListener.onClick(ExpandTabView.this.selectPosition);
          Log.i("Ibeacon", "点击： " + ExpandTabView.this.selectPosition);
        }
      });
      i += 1;
      break label29;
      break;
    }
  }

  public static abstract interface OnButtonClickListener
  {
    public abstract void onClick(int paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.ExpandTabView
 * JD-Core Version:    0.6.2
 */