
// INTERNAL ERROR //

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.ClassLoaderSavedState
 * JD-Core Version:    0.6.2
 */

package com.ismartgo.app.grid;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public abstract class ClassLoaderSavedState
        implements Parcelable
{
    public static final Parcelable.Creator<ClassLoaderSavedState> CREATOR = new Parcelable.Creator()
    {
        public ClassLoaderSavedState createFromParcel(Parcel paramAnonymousParcel)
        {
            if (paramAnonymousParcel.readParcelable(null) != null) {
                throw new IllegalStateException("superState must be null");
            }
            return ClassLoaderSavedState.EMPTY_STATE;
        }

        public ClassLoaderSavedState[] newArray(int paramAnonymousInt)
        {
            return new ClassLoaderSavedState[paramAnonymousInt];
        }
    };
    public static final ClassLoaderSavedState EMPTY_STATE = new ClassLoaderSavedState(// INTERNAL ERROR //