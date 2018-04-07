
package com.appsynth.places.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class Result implements Parcelable
{

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("opening_hours")
    @Expose
    private OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("alt_ids")
    @Expose
    private List<AltId> altIds = null;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("types")
    @Expose
    private List<String> types = null;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    protected Result(Parcel in) {
        this.geometry = ((Geometry) in.readValue((Geometry.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.openingHours = ((OpeningHours) in.readValue((OpeningHours.class.getClassLoader())));
        in.readList(this.photos, (Photo.class.getClassLoader()));
        this.placeId = ((String) in.readValue((String.class.getClassLoader())));
        this.scope = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.altIds, (AltId.class.getClassLoader()));
        this.reference = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.types, (String.class.getClassLoader()));
        this.vicinity = ((String) in.readValue((String.class.getClassLoader())));
        this.formattedAddress = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param photos
     * @param id
     * @param icon
     * @param vicinity
     * @param scope
     * @param placeId
     * @param openingHours
     * @param name
     * @param altIds
     * @param types
     * @param reference
     * @param geometry
     * @param formattedAddress
     */
    public Result(Geometry geometry, String icon, String id, String name, OpeningHours openingHours, List<Photo> photos, String placeId, String scope, List<AltId> altIds, String reference, List<String> types, String vicinity, String formattedAddress) {
        super();
        this.geometry = geometry;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.openingHours = openingHours;
        this.photos = photos;
        this.placeId = placeId;
        this.scope = scope;
        this.altIds = altIds;
        this.reference = reference;
        this.types = types;
        this.vicinity = vicinity;
        this.formattedAddress = formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<AltId> getAltIds() {
        return altIds;
    }

    public void setAltIds(List<AltId> altIds) {
        this.altIds = altIds;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("geometry", geometry).append("icon", icon).append("id", id).append("name", name).append("openingHours", openingHours).append("photos", photos).append("placeId", placeId).append("scope", scope).append("altIds", altIds).append("reference", reference).append("types", types).append("vicinity", vicinity).append("formattedAddress", formattedAddress).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(geometry);
        dest.writeValue(icon);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(openingHours);
        dest.writeList(photos);
        dest.writeValue(placeId);
        dest.writeValue(scope);
        dest.writeList(altIds);
        dest.writeValue(reference);
        dest.writeList(types);
        dest.writeValue(vicinity);
        dest.writeValue(formattedAddress);
    }

    public int describeContents() {
        return  0;
    }

}
