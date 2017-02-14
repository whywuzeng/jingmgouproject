package cn.sharesdk.onekeyshare.theme.skyblue;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.PlatformListFakeActivity;
import com.mob.tools.utils.R;
import java.util.List;

public class PlatformListPage extends PlatformListFakeActivity
  implements View.OnClickListener
{
  private PlatformGridViewAdapter gridViewAdapter;

  private void initView()
  {
    View localView = findViewByResName("backImageView");
    localView.setTag(Integer.valueOf(17039360));
    localView.setOnClickListener(this);
    localView = findViewByResName("okImageView");
    localView.setTag(Integer.valueOf(17039370));
    localView.setOnClickListener(this);
    this.gridViewAdapter = new PlatformGridViewAdapter(this.activity);
    this.gridViewAdapter.setCustomerLogos(this.customerLogos);
    ((GridView)findViewByResName("gridView")).setAdapter(this.gridViewAdapter);
    new AsyncTask()
    {
      protected Platform[] doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        return ShareSDK.getPlatformList();
      }

      protected void onPostExecute(Platform[] paramAnonymousArrayOfPlatform)
      {
        PlatformListPage.this.gridViewAdapter.setData(paramAnonymousArrayOfPlatform, PlatformListPage.this.hiddenPlatforms);
      }
    }
    .execute(new Void[0]);
  }

  private void onShareButtonClick(View paramView)
  {
    if ((this.gridViewAdapter == null) || ("locked".equals(paramView.getTag())))
      return;
    List localList = this.gridViewAdapter.getCheckedItems();
    if (localList.size() == 0)
    {
      Toast.makeText(this.activity, R.getStringRes(this.activity, "select_one_plat_at_least"), 0).show();
      return;
    }
    paramView.setTag("locked");
    onShareButtonClick(paramView, localList);
  }

  public void onClick(View paramView)
  {
    Object localObject = paramView.getTag();
    if ((localObject == null) || (!(localObject instanceof Integer)))
      return;
    switch (((Integer)localObject).intValue())
    {
    default:
      return;
    case 17039360:
      setCanceled(true);
      finish();
      return;
    case 17039370:
    }
    onShareButtonClick(paramView);
  }

  public void onCreate()
  {
    super.onCreate();
    this.activity.setContentView(R.getLayoutRes(this.activity, "skyblue_share_platform_list"));
    initView();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.theme.skyblue.PlatformListPage
 * JD-Core Version:    0.6.2
 */