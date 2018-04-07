
package com.appsynth.places.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class SearchResponse implements Parcelable
{

    @SerializedName("html_attributions")
    @Expose
    private List<String> htmlAttributions = null;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Creator<SearchResponse> CREATOR = new Creator<SearchResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SearchResponse createFromParcel(Parcel in) {
            return new SearchResponse(in);
        }

        public SearchResponse[] newArray(int size) {
            return (new SearchResponse[size]);
        }

    }
    ;

    protected SearchResponse(Parcel in) {
        in.readList(this.htmlAttributions, (String.class.getClassLoader()));
        in.readList(this.results, (Result.class.getClassLoader()));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchResponse() {
    }

    /**
     * 
     * @param results
     * @param status
     * @param htmlAttributions
     */
    public SearchResponse(List<String> htmlAttributions, List<Result> results, String status) {
        super();
        this.htmlAttributions = htmlAttributions;
        this.results = results;
        this.status = status;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("htmlAttributions", htmlAttributions).append("results", results).append("status", status).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(htmlAttributions);
        dest.writeList(results);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
