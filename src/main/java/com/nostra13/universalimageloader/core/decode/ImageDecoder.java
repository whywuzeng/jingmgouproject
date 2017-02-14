package com.nostra13.universalimageloader.core.decode;

import android.graphics.Bitmap;
import java.io.IOException;

public abstract interface ImageDecoder
{
  public abstract Bitmap decode(ImageDecodingInfo paramImageDecodingInfo)
    throws IOException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.decode.ImageDecoder
 * JD-Core Version:    0.6.2
 */