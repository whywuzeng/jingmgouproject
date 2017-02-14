package cn.sharesdk.onekeyshare.theme.skyblue;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.EditPageFakeActivity;
import cn.sharesdk.onekeyshare.EditPageFakeActivity.ImageInfo;
import cn.sharesdk.onekeyshare.EditPageFakeActivity.ImageListResultsCallback;
import cn.sharesdk.onekeyshare.PicViewer;
import com.mob.tools.utils.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EditPage extends EditPageFakeActivity
  implements View.OnClickListener, TextWatcher
{
  private static final int MAX_TEXT_COUNT = 140;
  private TextView textCounterTextView;
  private EditText textEditText;
  private EditText titleEditText;

  private void initAtUserView()
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewByResName("atLayout");
    Iterator localIterator = this.platforms.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Platform localPlatform = (Platform)localIterator.next();
      String str = localPlatform.getName();
      if (isShowAtUserLayout(str))
      {
        View localView = LayoutInflater.from(this.activity).inflate(R.getLayoutRes(this.activity, "skyblue_editpage_at_layout"), null);
        TextView localTextView1 = (TextView)localView.findViewById(R.getIdRes(this.activity, "atDescTextView"));
        TextView localTextView2 = (TextView)localView.findViewById(R.getIdRes(this.activity, "atTextView"));
        View.OnClickListener local1 = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            FollowListPage localFollowListPage = new FollowListPage();
            localFollowListPage.setPlatform((Platform)paramAnonymousView.getTag());
            localFollowListPage.showForResult(EditPage.this.activity, null, EditPage.this);
          }
        };
        localTextView2.setTag(localPlatform);
        localTextView2.setOnClickListener(local1);
        localTextView1.setTag(localPlatform);
        localTextView1.setOnClickListener(local1);
        localTextView2.setText(getAtUserButtonText(str));
        localTextView1.setText(getContext().getString(R.getStringRes(this.activity, "list_friends"), new Object[] { getLogoName(str) }));
        localLinearLayout.addView(localView);
      }
    }
  }

  private void initBodyView()
  {
    View localView = findViewByResName("closeImageView");
    localView.setTag("close");
    localView.setOnClickListener(this);
    if (this.shareParamMap.containsKey("title"))
    {
      this.titleEditText = ((EditText)findViewByResName("titleEditText"));
      this.titleEditText.setText(String.valueOf(this.shareParamMap.get("title")));
    }
    this.textCounterTextView = ((TextView)findViewByResName("textCounterTextView"));
    this.textCounterTextView.setText(String.valueOf(140));
    this.textEditText = ((EditText)findViewByResName("textEditText"));
    this.textEditText.addTextChangedListener(this);
    this.textEditText.setText(String.valueOf(this.shareParamMap.get("text")));
    initAtUserView();
  }

  private void initImageListView()
  {
    HorizontalScrollView localHorizontalScrollView = (HorizontalScrollView)findViewByResName("hScrollView");
    if (!initImageList(new EditPageFakeActivity.ImageListResultsCallback()
    {
      public void onFinish(ArrayList<EditPageFakeActivity.ImageInfo> paramAnonymousArrayList)
      {
        if (paramAnonymousArrayList == null);
        while (true)
        {
          return;
          LinearLayout localLinearLayout = (LinearLayout)EditPage.this.findViewByResName("imagesLinearLayout");
          paramAnonymousArrayList = paramAnonymousArrayList.iterator();
          while (paramAnonymousArrayList.hasNext())
          {
            EditPageFakeActivity.ImageInfo localImageInfo = (EditPageFakeActivity.ImageInfo)paramAnonymousArrayList.next();
            if (localImageInfo.bitmap != null)
              localLinearLayout.addView(EditPage.this.makeImageItemView(localImageInfo));
          }
        }
      }
    }))
      localHorizontalScrollView.setVisibility(8);
  }

  private void initTitleView()
  {
    View localView = findViewByResName("backImageView");
    localView.setTag("close");
    localView.setOnClickListener(this);
    localView = findViewByResName("okImageView");
    localView.setTag("ok");
    localView.setOnClickListener(this);
  }

  private void initView()
  {
    if (!this.dialogMode)
    {
      RelativeLayout localRelativeLayout = (RelativeLayout)findViewByResName("mainRelLayout");
      RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)localRelativeLayout.getLayoutParams();
      localLayoutParams.setMargins(0, 0, 0, 0);
      localLayoutParams.height = -1;
      localRelativeLayout.setLayoutParams(localLayoutParams);
    }
    initTitleView();
    initBodyView();
    initImageListView();
  }

  private View makeImageItemView(final EditPageFakeActivity.ImageInfo paramImageInfo)
  {
    final View localView = LayoutInflater.from(this.activity).inflate(R.getLayoutRes(this.activity, "skyblue_editpage_inc_image_layout"), null);
    Object localObject = (ImageView)localView.findViewById(R.getIdRes(this.activity, "imageView"));
    ((ImageView)localObject).setImageBitmap(paramImageInfo.bitmap);
    ((ImageView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new PicViewer();
        paramAnonymousView.setImageBitmap(paramImageInfo.bitmap);
        paramAnonymousView.show(EditPage.this.activity, null);
      }
    });
    localObject = localView.findViewById(R.getIdRes(this.activity, "imageRemoveBtn"));
    ((View)localObject).setTag(paramImageInfo);
    ((View)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localView.setVisibility(8);
        EditPage.this.removeImage((EditPageFakeActivity.ImageInfo)paramAnonymousView.getTag());
      }
    });
    return localView;
  }

  private void onShareButtonClick(View paramView)
  {
    if (this.shareParamMap.containsKey("title"))
    {
      paramView = this.titleEditText.getText().toString().trim();
      this.shareParamMap.put("title", paramView);
    }
    paramView = this.textEditText.getText().toString().trim();
    this.shareParamMap.put("text", paramView);
    setResultAndFinish();
  }

  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onClick(View paramView)
  {
    if (paramView.getTag() == null);
    String str;
    do
    {
      return;
      str = (String)paramView.getTag();
      if (str.equals("close"))
      {
        paramView = this.platforms.iterator();
        while (true)
        {
          if (!paramView.hasNext())
          {
            finish();
            return;
          }
          ShareSDK.logDemoEvent(5, (Platform)paramView.next());
        }
      }
    }
    while (!str.equals("ok"));
    onShareButtonClick(paramView);
  }

  public void onCreate()
  {
    if ((this.shareParamMap == null) || (this.platforms == null))
    {
      finish();
      return;
    }
    this.activity.setContentView(R.getLayoutRes(this.activity, "skyblue_editpage"));
    initView();
  }

  public boolean onFinish()
  {
    this.textCounterTextView = null;
    this.textEditText = null;
    this.titleEditText = null;
    return super.onFinish();
  }

  public void onResult(HashMap<String, Object> paramHashMap)
  {
    paramHashMap = getJoinSelectedUser(paramHashMap);
    if (paramHashMap != null)
      this.textEditText.append(paramHashMap);
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 = 140 - this.textEditText.length();
    this.textCounterTextView.setText(String.valueOf(paramInt1));
    paramCharSequence = this.textCounterTextView;
    if (paramInt1 > 0);
    for (paramInt1 = -3158065; ; paramInt1 = -65536)
    {
      paramCharSequence.setTextColor(paramInt1);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.theme.skyblue.EditPage
 * JD-Core Version:    0.6.2
 */