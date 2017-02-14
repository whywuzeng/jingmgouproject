package cn.sharesdk.onekeyshare;

import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.AuthorizeAdapter;
import com.mob.tools.utils.R;

public class MyAdapter extends AuthorizeAdapter
{
  public void onCreate()
  {
    hideShareSDKLogo();
    Object localObject = getTitleLayout();
    int i = R.getStringRes(getActivity(), "second_title");
    ((TitleLayout)localObject).getTvTitle().setText(i);
    ((TitleLayout)localObject).setBackgroundColor(-7829368);
    ((TitleLayout)localObject).getTvTitle().setGravity(17);
    disablePopUpAnimation();
    localObject = (View)getBodyView().getParent();
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, -1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    localTranslateAnimation.setDuration(1000L);
    ((View)localObject).setAnimation(localTranslateAnimation);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.MyAdapter
 * JD-Core Version:    0.6.2
 */