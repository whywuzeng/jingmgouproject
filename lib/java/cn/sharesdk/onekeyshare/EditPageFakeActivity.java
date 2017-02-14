package cn.sharesdk.onekeyshare;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EditPageFakeActivity extends FakeActivity
{
  protected View backgroundView;
  protected boolean dialogMode;
  protected List<Platform> platforms;
  private ArrayList<ImageInfo> shareImageList;
  protected HashMap<String, Object> shareParamMap;
  protected ArrayList<String> toFriendList;

  protected String getAtUserButtonText(String paramString)
  {
    if ("FacebookMessenger".equals(paramString))
      return "To";
    return "@";
  }

  protected String getJoinSelectedUser(HashMap<String, Object> paramHashMap)
  {
    if ((paramHashMap != null) && (paramHashMap.containsKey("selected")))
    {
      Object localObject = (ArrayList)paramHashMap.get("selected");
      if ("FacebookMessenger".equals(((Platform)paramHashMap.get("platform")).getName()))
      {
        this.toFriendList = ((ArrayList)localObject);
        return null;
      }
      paramHashMap = new StringBuilder();
      localObject = ((ArrayList)localObject).iterator();
      while (true)
      {
        if (!((Iterator)localObject).hasNext())
          return paramHashMap.toString();
        String str = (String)((Iterator)localObject).next();
        paramHashMap.append('@').append(str).append(' ');
      }
    }
    return null;
  }

  public String getLogoName(String paramString)
  {
    if (paramString == null)
      return "";
    int i = R.getStringRes(getContext(), paramString);
    return getContext().getString(i);
  }

  public boolean haveImage()
  {
    String str1 = (String)this.shareParamMap.get("imageUrl");
    String str2 = (String)this.shareParamMap.get("imagePath");
    Bitmap localBitmap = (Bitmap)this.shareParamMap.get("viewToShare");
    String[] arrayOfString = (String[])this.shareParamMap.get("imageArray");
    if ((!TextUtils.isEmpty(str2)) && (new File(str2).exists()));
    while (((localBitmap != null) && (!localBitmap.isRecycled())) || (!TextUtils.isEmpty(str1)) || ((arrayOfString != null) && (arrayOfString.length > 0)))
      return true;
    return false;
  }

  protected boolean initImageList(ImageListResultsCallback paramImageListResultsCallback)
  {
    String str = (String)this.shareParamMap.get("imageUrl");
    Object localObject2 = (String)this.shareParamMap.get("imagePath");
    Bitmap localBitmap = (Bitmap)this.shareParamMap.get("viewToShare");
    Object localObject1 = (String[])this.shareParamMap.get("imageArray");
    this.shareImageList = new ArrayList();
    if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (new File((String)localObject2).exists()))
    {
      localObject1 = new ImageInfo();
      ((ImageInfo)localObject1).paramName = "imagePath";
      ((ImageInfo)localObject1).srcValue = ((String)localObject2);
      this.shareImageList.add(localObject1);
      this.shareParamMap.remove("imagePath");
    }
    while (this.shareImageList.size() == 0)
    {
      return false;
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
      {
        localObject1 = new ImageInfo();
        ((ImageInfo)localObject1).paramName = "viewToShare";
        ((ImageInfo)localObject1).bitmap = localBitmap;
        this.shareImageList.add(localObject1);
        this.shareParamMap.remove("viewToShare");
      }
      else if (!TextUtils.isEmpty(str))
      {
        localObject1 = new ImageInfo();
        ((ImageInfo)localObject1).paramName = "imageUrl";
        ((ImageInfo)localObject1).srcValue = str;
        this.shareImageList.add(localObject1);
        this.shareParamMap.remove("imageUrl");
      }
      else if ((localObject1 != null) && (localObject1.length > 0))
      {
        int j = localObject1.length;
        int i = 0;
        if (i >= j)
        {
          this.shareParamMap.remove("imageArray");
        }
        else
        {
          str = localObject1[i];
          if (TextUtils.isEmpty(str));
          while (true)
          {
            i += 1;
            break;
            localObject2 = new ImageInfo();
            ((ImageInfo)localObject2).paramName = "imageArray";
            ((ImageInfo)localObject2).srcValue = str;
            this.shareImageList.add(localObject2);
          }
        }
      }
    }
    new AsyncTask()
    {
      protected EditPageFakeActivity.ImageListResultsCallback doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        Iterator localIterator = EditPageFakeActivity.this.shareImageList.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
            return (EditPageFakeActivity.ImageListResultsCallback)paramAnonymousArrayOfObject[0];
          EditPageFakeActivity.ImageInfo localImageInfo = (EditPageFakeActivity.ImageInfo)localIterator.next();
          if (localImageInfo.bitmap == null)
            try
            {
              String str = localImageInfo.srcValue;
              if (!str.startsWith("http://"))
              {
                localObject = str;
                if (!str.startsWith("https://"));
              }
              else
              {
                localObject = BitmapHelper.downloadBitmap(EditPageFakeActivity.this.activity, str);
              }
              Object localObject = BitmapHelper.getBitmap((String)localObject);
              if (localObject != null)
                localImageInfo.bitmap = ((Bitmap)localObject);
            }
            catch (Throwable localThrowable)
            {
              localThrowable.printStackTrace();
            }
        }
      }

      protected void onPostExecute(EditPageFakeActivity.ImageListResultsCallback paramAnonymousImageListResultsCallback)
      {
        paramAnonymousImageListResultsCallback.onFinish(EditPageFakeActivity.this.shareImageList);
      }
    }
    .execute(new Object[] { paramImageListResultsCallback });
    return true;
  }

  protected boolean isShowAtUserLayout(String paramString)
  {
    return ("SinaWeibo".equals(paramString)) || ("TencentWeibo".equals(paramString)) || ("Facebook".equals(paramString)) || ("Twitter".equals(paramString)) || ("FacebookMessenger".equals(paramString));
  }

  public boolean onFinish()
  {
    this.shareImageList = null;
    return super.onFinish();
  }

  protected void removeImage(ImageInfo paramImageInfo)
  {
    if ((this.shareImageList == null) || (paramImageInfo == null))
      return;
    this.shareImageList.remove(paramImageInfo);
  }

  public void setBackgroundView(View paramView)
  {
    this.backgroundView = paramView;
  }

  public void setDialogMode()
  {
    this.dialogMode = true;
  }

  public void setPlatforms(List<Platform> paramList)
  {
    this.platforms = paramList;
  }

  protected void setResultAndFinish()
  {
    Object localObject1 = new ArrayList();
    Object localObject2;
    if (this.shareImageList != null)
    {
      localObject2 = this.shareImageList.iterator();
      if (!((Iterator)localObject2).hasNext())
      {
        this.shareImageList.clear();
        if (((ArrayList)localObject1).size() != 0)
          break label228;
        this.shareParamMap.put("imageArray", null);
      }
    }
    else
    {
      label57: localObject1 = new HashMap();
      localObject2 = this.platforms.iterator();
    }
    while (true)
    {
      if (!((Iterator)localObject2).hasNext())
      {
        localObject2 = new HashMap();
        ((HashMap)localObject2).put("editRes", localObject1);
        setResult((HashMap)localObject2);
        finish();
      }
      Object localObject3;
      label228: HashMap localHashMap;
      int i;
      do
      {
        return;
        localObject3 = (ImageInfo)((Iterator)localObject2).next();
        if (("imagePath".equals(((ImageInfo)localObject3).paramName)) || ("imageUrl".equals(((ImageInfo)localObject3).paramName)))
        {
          this.shareParamMap.put(((ImageInfo)localObject3).paramName, ((ImageInfo)localObject3).srcValue);
          break;
        }
        if ("viewToShare".equals(((ImageInfo)localObject3).paramName))
        {
          this.shareParamMap.put(((ImageInfo)localObject3).paramName, ((ImageInfo)localObject3).bitmap);
          break;
        }
        if (!"imageArray".equals(((ImageInfo)localObject3).paramName))
          break;
        ((ArrayList)localObject1).add(((ImageInfo)localObject3).srcValue);
        break;
        this.shareParamMap.put("imageArray", ((ArrayList)localObject1).toArray(new String[((ArrayList)localObject1).size()]));
        break label57;
        localObject3 = (Platform)((Iterator)localObject2).next();
        if (!"FacebookMessenger".equals(((Platform)localObject3).getName()))
          break label420;
        localHashMap = new HashMap(this.shareParamMap);
        if ((this.toFriendList != null) && (this.toFriendList.size() > 0))
          localHashMap.put("address", this.toFriendList.get(this.toFriendList.size() - 1));
        if (localHashMap.get("address") != null)
          break label402;
        i = R.getStringRes(this.activity, "select_a_friend");
      }
      while (i <= 0);
      Toast.makeText(getContext(), this.activity.getString(i) + " - " + ((Platform)localObject3).getName(), 0).show();
      return;
      label402: ((HashMap)localObject1).put(localObject3, localHashMap);
      ShareSDK.logDemoEvent(3, (Platform)localObject3);
      continue;
      label420: ShareSDK.logDemoEvent(3, (Platform)localObject3);
      ((HashMap)localObject1).put(localObject3, this.shareParamMap);
    }
  }

  public void setShareData(HashMap<String, Object> paramHashMap)
  {
    this.shareParamMap = paramHashMap;
  }

  public static class ImageInfo
  {
    public Bitmap bitmap;
    public String paramName;
    public String srcValue;
  }

  protected static abstract interface ImageListResultsCallback
  {
    public abstract void onFinish(ArrayList<EditPageFakeActivity.ImageInfo> paramArrayList);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.EditPageFakeActivity
 * JD-Core Version:    0.6.2
 */