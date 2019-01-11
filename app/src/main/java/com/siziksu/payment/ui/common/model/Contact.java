package com.siziksu.payment.ui.common.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Contact implements Parcelable {

    public int id;
    public String name;
    public String phone;
    public String thumbnailPath;
    public String thumbnailExtension;

    public Contact() {}

    @Override
    public String toString() {
        return "{\"Name\" : \"" + name + "\", \"Phone\" : \"" + phone + "\", \"Avatar\" : \"" + getAvatar() + "\"}";
    }

    @Override
    public boolean equals(Object obj) {
        if (name == null || !(obj instanceof Contact)) {
            return false;
        }
        return name.equals(((Contact) obj).name);
    }

    @Override
    public int hashCode() {
        return 31 * 17 + name.length();
    }

    public String getAvatar() {
        if (thumbnailPath != null && thumbnailExtension != null) {
            return thumbnailPath + "/standard_medium." + thumbnailExtension;
        } else {
            return "";
        }
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.thumbnailPath);
        dest.writeString(this.thumbnailExtension);
    }

    protected Contact(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.phone = in.readString();
        this.thumbnailPath = in.readString();
        this.thumbnailExtension = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {return new Contact(source);}

        @Override
        public Contact[] newArray(int size) {return new Contact[size];}
    };
}
