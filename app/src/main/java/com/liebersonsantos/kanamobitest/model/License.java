package com.liebersonsantos.kanamobitest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class License implements Parcelable {

    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("spdx_id")
    private String spdxId;
    @SerializedName("url")
    private String url;
    @SerializedName("node_id")
    private String nodeId;

    public License() {
    }

    public License(String key, String name, String spdxId, String url, String nodeId) {
        this.key = key;
        this.name = name;
        this.spdxId = spdxId;
        this.url = url;
        this.nodeId = nodeId;
    }

    protected License(Parcel in) {
        key = in.readString();
        name = in.readString();
        spdxId = in.readString();
        url = in.readString();
        nodeId = in.readString();
    }

    public static final Creator<License> CREATOR = new Creator<License>() {
        @Override
        public License createFromParcel(Parcel in) {
            return new License(in);
        }

        @Override
        public License[] newArray(int size) {
            return new License[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(spdxId);
        dest.writeString(url);
        dest.writeString(nodeId);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpdxId() {
        return spdxId;
    }

    public void setSpdxId(String spdxId) {
        this.spdxId = spdxId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
