package br.com.alura.schedule.models;

import java.io.Serializable;
import java.util.List;

public class Assessment implements Serializable {
    private List<String> mTopics;
    private String mDiscipline;
    private String mDate;

    public Assessment( List<String> topics, String disciplines, String date ) {
        this.mDiscipline = disciplines;
        this.mTopics = topics;
        this.mDate = date;
    }

    public List<String> getTopics() {
        return mTopics;
    }

    public void setTopics( List<String> topics ) {
        this.mTopics = topics;
    }

    public String getDiscipline() {
        return mDiscipline;
    }

    public void setDiscipline( String discipline ) {
        this.mDiscipline = mDiscipline;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate( String date ) {
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        return this.mDiscipline;
    }
}
