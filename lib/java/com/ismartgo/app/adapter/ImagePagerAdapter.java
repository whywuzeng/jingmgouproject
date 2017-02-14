package com.ismartgo.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.activity.InviteFriendsActivity;
import com.ismartgo.app.activity.RewardExchangeActivity;
import com.ismartgo.app.activity.UploadReceiptActivity;
import com.ismartgo.app.activity.WebViewActivity;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagePagerAdapter extends BaseAdapter
{
  private List<String> adIdArray;
  private Context context;
  private List<String> imageIdList;
  private boolean isInfiniteLoop;
  private List<String> linkUrlArray;
  private int size;

  public ImagePagerAdapter(Context paramContext, List<String> paramList1, List<String> paramList2, List<String> paramList3)
  {
    this.context = paramContext;
    this.imageIdList = paramList1;
    if (paramList1 != null)
      this.size = paramList1.size();
    this.adIdArray = paramList3;
    this.linkUrlArray = paramList2;
    this.isInfiniteLoop = false;
  }

  private int getPosition(int paramInt)
  {
    int i = paramInt;
    if (this.isInfiniteLoop)
      i = paramInt % this.size;
    return i;
  }

  public int getCount()
  {
    if (this.isInfiniteLoop)
      return 2147483647;
    return this.imageIdList.size();
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramViewGroup = new ViewHolder(null);
      paramView = new ImageView(this.context);
      paramViewGroup.imageView = paramView;
      paramViewGroup.imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
      paramViewGroup.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      Log.i("Test", "adsurl: " + (String)this.imageIdList.get(getPosition(paramInt)));
      ImgLoader.getInstance(this.context).showPic((String)this.imageIdList.get(getPosition(paramInt)), paramViewGroup.imageView, false);
      paramViewGroup.imageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (String)ImagePagerAdapter.this.linkUrlArray.get(ImagePagerAdapter.this.getPosition(paramInt));
          Object localObject;
          if (BaseActivity.loginUser != null)
          {
            localObject = new HashMap();
            ((Map)localObject).put("cityName", BaseActivity.city);
            ((Map)localObject).put("uid", BaseActivity.loginUser.getId());
            ((Map)localObject).put("adsid", (String)ImagePagerAdapter.this.adIdArray.get(ImagePagerAdapter.this.getPosition(paramInt)));
            HttpRequest.getInstance().executePostStringRequest(ImagePagerAdapter.this.context, Url.ADVERTISE_CLICK_URL, 6, (Map)localObject, new HttpCallback()
            {
              public void onFailed(int paramAnonymous2Int, String paramAnonymous2String, Object paramAnonymous2Object, CharSequence paramAnonymous2CharSequence)
              {
                Logger.d("点击发送失败！");
              }

              public void onSucceed(int paramAnonymous2Int, Response<String> paramAnonymous2Response)
              {
              }
            });
          }
          if ((paramAnonymousView == null) || (paramAnonymousView.equals("")))
            return;
          if (paramAnonymousView.startsWith("smartgoapp"))
            if (paramAnonymousView.equals("smartgoapp://giftlist"))
            {
              paramAnonymousView = new Intent(ImagePagerAdapter.this.context, RewardExchangeActivity.class);
              ImagePagerAdapter.this.context.startActivity(paramAnonymousView);
            }
          while (true)
          {
            Log.i("Test", "广告id: " + String.valueOf(ImagePagerAdapter.this.adIdArray.get(ImagePagerAdapter.this.getPosition(paramInt))));
            try
            {
              paramAnonymousView = new HashMap();
              paramAnonymousView.put("adid", String.valueOf(ImagePagerAdapter.this.adIdArray.get(ImagePagerAdapter.this.getPosition(paramInt))));
              MobclickAgent.onEvent(ImagePagerAdapter.this.context, "adclick", paramAnonymousView);
              return;
            }
            catch (Exception paramAnonymousView)
            {
              paramAnonymousView.printStackTrace();
              return;
            }
            if (paramAnonymousView.equals("smartgoapp://invite"))
            {
              paramAnonymousView = new Intent(ImagePagerAdapter.this.context, InviteFriendsActivity.class);
              ImagePagerAdapter.this.context.startActivity(paramAnonymousView);
            }
            else if (paramAnonymousView.equals("smartgoapp://receipt"))
            {
              paramAnonymousView = new Intent(ImagePagerAdapter.this.context, UploadReceiptActivity.class);
              ImagePagerAdapter.this.context.startActivity(paramAnonymousView);
              continue;
              localObject = new Intent(ImagePagerAdapter.this.context, WebViewActivity.class);
              ((Intent)localObject).putExtra("url", paramAnonymousView);
              Log.i("Test", "广告详情url： " + paramAnonymousView);
              ((Intent)localObject).addFlags(268435456);
              ImagePagerAdapter.this.context.startActivity((Intent)localObject);
            }
          }
        }
      });
      return paramView;
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
  }

  public boolean isInfiniteLoop()
  {
    return this.isInfiniteLoop;
  }

  public ImagePagerAdapter setInfiniteLoop(boolean paramBoolean)
  {
    this.isInfiniteLoop = paramBoolean;
    return this;
  }

  public void setList(List<String> paramList1, List<String> paramList2)
  {
    this.imageIdList = paramList1;
    this.linkUrlArray = paramList2;
    notifyDataSetChanged();
  }

  private static class ViewHolder
  {
    ImageView imageView;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.ImagePagerAdapter
 * JD-Core Version:    0.6.2
 */