
package com.appsynth.places.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Viewport implements Parcelable
{

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;
    public final static Creator<Viewport> CREATOR = new Creator<Viewport>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Viewport createFromParcel(Parcel in) {
            return new Viewport(in);
        }

        public Viewport[] newArray(int size) {
            return (new Viewport[size]);
        }

    }
    ;

    protected Viewport(Parcel in) {
        this.northeast = ((Northeast) in.readValue((Northeast.class.getClassLoader())));
        this.southwest = ((Southwest) in.readValue((Southwest.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Viewport() {
    }

    /**
     * 
     * @param southwest
     * @param northeast
     */
    public Viewport(Northeast northeast, Southwest southwest) {
        super();
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("northeast", northeast).append("southwest", southwest).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(northeast);
        dest.writeValue(southwest);
    }

    public int describeContents() {
        return  0;
    }

}
