package com.example.jmepaunlagui.mrjdeveloper;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionCOD implements Parcelable {

    private String CODquestion;
    private String CODoption1;
    private String CODoption2;
    private String CODoption3;
    private String CODoption4;
    private int CODanswerNr;

    public QuestionCOD() {
    }

    public QuestionCOD(String CODquestion, String CODoption1, String CODoption2, String CODoption3, String CODoption4, int CODanswerNr) {
        this.CODquestion = CODquestion;
        this.CODoption1 = CODoption1;
        this.CODoption2 = CODoption2;
        this.CODoption3 = CODoption3;
        this.CODoption4 = CODoption4;
        this.CODanswerNr = CODanswerNr;
    }

    protected QuestionCOD(Parcel in) {
        CODquestion = in.readString();
        CODoption1 = in.readString();
        CODoption2 = in.readString();
        CODoption3 = in.readString();
        CODoption4 = in.readString();
        CODanswerNr = in.readInt();
    }

    public static final Creator<QuestionCOD> CREATOR = new Creator<QuestionCOD>() {
        @Override
        public QuestionCOD createFromParcel(Parcel in) {
            return new QuestionCOD(in);
        }

        @Override
        public QuestionCOD[] newArray(int size) {
            return new QuestionCOD[size];
        }
    };

    public String getCODquestion() {
        return CODquestion;
    }

    public void setCODquestion(String CODquestion) {
        this.CODquestion = CODquestion;
    }

    public String getCODoption1() {
        return CODoption1;
    }

    public void setCODoption1(String CODoption1) {
        this.CODoption1 = CODoption1;
    }

    public String getCODoption2() {
        return CODoption2;
    }

    public void setCODoption2(String CODoption2) {
        this.CODoption2 = CODoption2;
    }

    public String getCODoption3() {
        return CODoption3;
    }

    public void setCODoption3(String CODoption3) {
        this.CODoption3 = CODoption3;
    }

    public String getCODoption4() {
        return CODoption4;
    }

    public void setCODoption4(String CODoption4) {
        this.CODoption4 = CODoption4;
    }

    public int getCODanswerNr() {
        return CODanswerNr;
    }

    public void setCODanswerNr(int CODanswerNr) {
        this.CODanswerNr = CODanswerNr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CODquestion);
        dest.writeString(CODoption1);
        dest.writeString(CODoption2);
        dest.writeString(CODoption3);
        dest.writeString(CODoption4);
        dest.writeInt(CODanswerNr);
    }
}