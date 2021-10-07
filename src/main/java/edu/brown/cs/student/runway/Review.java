package edu.brown.cs.student.runway;

import com.google.gson.Gson;

public class Review {
    final private String review_text;
    final private String review_summary;
    final private String review_date;
    final int id;

    public Review(String review_text, String review_summary, String review_date, int id){
        this.review_text = review_text;
        this.review_summary = review_text;
        this.review_date = review_date;
        this.id = id;
    }

    public String getReview_text() {
        return review_text;
    }

    public String getReview_summary() {
        return review_summary;
    }

    public String getReview_date() {
        return review_date;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
