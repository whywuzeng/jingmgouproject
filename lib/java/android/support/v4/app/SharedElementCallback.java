package android.support.v4.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback
{
  private Matrix mTempMatrix;

  public Parcelable onCaptureSharedElementSnapshot(View paramView, Matrix paramMatrix, RectF paramRectF)
  {
    int i = Math.round(paramRectF.width());
    int j = Math.round(paramRectF.height());
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (i > 0)
    {
      localObject1 = localObject2;
      if (j > 0)
      {
        if (this.mTempMatrix == null)
          this.mTempMatrix = new Matrix();
        this.mTempMatrix.set(paramMatrix);
        this.mTempMatrix.postTranslate(-paramRectF.left, -paramRectF.top);
        localObject1 = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
        paramMatrix = new Canvas((Bitmap)localObject1);
        paramMatrix.concat(this.mTempMatrix);
        paramView.draw(paramMatrix);
      }
    }
    return localObject1;
  }

  public View onCreateSnapshotView(Context paramContext, Parcelable paramParcelable)
  {
    ImageView localImageView = null;
    if ((paramParcelable instanceof Bitmap))
    {
      paramParcelable = (Bitmap)paramParcelable;
      localImageView = new ImageView(paramContext);
      localImageView.setImageBitmap(paramParcelable);
    }
    return localImageView;
  }

  public void onMapSharedElements(List<String> paramList, Map<String, View> paramMap)
  {
  }

  public void onRejectSharedElements(List<View> paramList)
  {
  }

  public void onSharedElementEnd(List<String> paramList, List<View> paramList1, List<View> paramList2)
  {
  }

  public void onSharedElementStart(List<String> paramList, List<View> paramList1, List<View> paramList2)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.app.SharedElementCallback
 * JD-Core Version:    0.6.2
 */