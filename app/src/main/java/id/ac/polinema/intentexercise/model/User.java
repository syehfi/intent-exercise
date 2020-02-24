package id.ac.polinema.intentexercise.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String fullname;
    private String email;
    private String homepage;
    private String about;
    private Uri image;

    public User(String fullname, String email, String homepage, String about, Uri image) {
        this.fullname = fullname;
        this.email = email;
        this.homepage = homepage;
        this.about = about;
        this.image = image;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullname);
        dest.writeString(this.email);
        dest.writeString(this.homepage);
        dest.writeString(this.about);
        dest.writeParcelable(this.image, flags);
    }

    protected User(Parcel in) {
        this.fullname = in.readString();
        this.email = in.readString();
        this.homepage = in.readString();
        this.about = in.readString();
        this.image = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
