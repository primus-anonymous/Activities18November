package com.neocaptainnemo.activities18november.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class CounterSecond implements Parcelable {

    private int value;

    private double doubleValue;

    private String stringValue;

    private Theme theme;

    public CounterSecond(int value) {
        this.value = value;
    }

    protected CounterSecond(Parcel in) {
        value = in.readInt();
        doubleValue = in.readDouble();
        stringValue = in.readString();
    }

    public static final Creator<CounterSecond> CREATOR = new Creator<CounterSecond>() {
        @Override
        public CounterSecond createFromParcel(Parcel in) {
            return new CounterSecond(in);
        }

        @Override
        public CounterSecond[] newArray(int size) {
            return new CounterSecond[size];
        }
    };

    public int getValue() {
        return value;
    }

    public void increase() {
        value++;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value);
        dest.writeDouble(doubleValue);
        dest.writeString(stringValue);
        dest.writeSerializable(theme);
    }
}
