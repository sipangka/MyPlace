
package com.appsynth.places.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class GeoCodeReverseResponse implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Address> addresses = null;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Creator<GeoCodeReverseResponse> CREATOR = new Creator<GeoCodeReverseResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GeoCodeReverseResponse createFromParcel(Parcel in) {
            return new GeoCodeReverseResponse(in);
        }

        public GeoCodeReverseResponse[] newArray(int size) {
            return (new GeoCodeReverseResponse[size]);
        }

    }
    ;

    protected GeoCodeReverseResponse(Parcel in) {
        in.readList(this.addresses, (Address.class.getClassLoader()));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GeoCodeReverseResponse() {
    }

    /**
     * 
     * @param status
     * @param results
     */
    public GeoCodeReverseResponse(List<Address> results, String status) {
        super();
        this.addresses = results;
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> results) {
        this.addresses = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("addresses", addresses).append("status", status).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(addresses);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
