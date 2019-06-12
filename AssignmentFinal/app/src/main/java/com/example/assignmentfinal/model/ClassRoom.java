package com.example.assignmentfinal.model;

import android.os.Parcel;
import android.os.Parcelable;


public class ClassRoom implements Parcelable {
    private String classCode;
    private String classname;
    public ClassRoom(){

    }
    public ClassRoom(String classCode, String classname) {
        this.classCode = classCode;
        this.classname = classname;
    }


    protected ClassRoom(Parcel in) {
        classCode = in.readString();
        classname = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(classCode);
        dest.writeString(classname);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClassRoom> CREATOR = new Creator<ClassRoom>() {
        @Override
        public ClassRoom createFromParcel(Parcel in) {
            return new ClassRoom(in);
        }

        @Override
        public ClassRoom[] newArray(int size) {
            return new ClassRoom[size];
        }
    };

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
