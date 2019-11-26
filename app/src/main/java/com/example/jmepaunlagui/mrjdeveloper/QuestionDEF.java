package com.example.jmepaunlagui.mrjdeveloper;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionDEF implements Parcelable {
    private String DEFquestion;
    private String DEFoption1;
    private String DEFoption2;
    private String DEFoption3;
    private String DEFoption4;
    private int DEFanswerNr;

    public QuestionDEF() {
    }

    public QuestionDEF(String DEFquestion, String DEFoption1, String DEFoption2, String DEFoption3, String DEFoption4, int DEFanswerNr) {
        this.DEFquestion = DEFquestion;
        this.DEFoption1 = DEFoption1;
        this.DEFoption2 = DEFoption2;
        this.DEFoption3 = DEFoption3;
        this.DEFoption4 = DEFoption4;
        this.DEFanswerNr = DEFanswerNr;
    }

    protected QuestionDEF(Parcel in) {
        DEFquestion = in.readString();
        DEFoption1 = in.readString();
        DEFoption2 = in.readString();
        DEFoption3 = in.readString();
        DEFoption4 = in.readString();
        DEFanswerNr = in.readInt();
    }

    public static final Creator<QuestionDEF> CREATOR = new Creator<QuestionDEF>() {
        @Override
        public QuestionDEF createFromParcel(Parcel in) {
            return new QuestionDEF(in);
        }

        @Override
        public QuestionDEF[] newArray(int size) {
            return new QuestionDEF[size];
        }
    };

    public String getDEFquestion() {
        return DEFquestion;
    }

    public void setDEFquestion(String DEFquestion) {
        this.DEFquestion = DEFquestion;
    }

    public String getDEFoption1() {
        return DEFoption1;
    }

    public void setDEFoption1(String DEFoption1) {
        this.DEFoption1 = DEFoption1;
    }

    public String getDEFoption2() {
        return DEFoption2;
    }

    public void setDEFoption2(String DEFoption2) {
        this.DEFoption2 = DEFoption2;
    }

    public String getDEFoption3() {
        return DEFoption3;
    }

    public void setDEFoption3(String DEFoption3) {
        this.DEFoption3 = DEFoption3;
    }

    public String getDEFoption4() {
        return DEFoption4;
    }

    public void setDEFoption4(String DEFoption4) {
        this.DEFoption4 = DEFoption4;
    }

    public int getDEFanswerNr() {
        return DEFanswerNr;
    }

    public void setDEFanswerNr(int DEFanswerNr) {
        this.DEFanswerNr = DEFanswerNr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(DEFquestion);
        dest.writeString(DEFoption1);
        dest.writeString(DEFoption2);
        dest.writeString(DEFoption3);
        dest.writeString(DEFoption4);
        dest.writeInt(DEFanswerNr);
    }
}