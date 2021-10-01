package edu.brown.cs.student.main;

import com.google.gson.Gson;

public class User {
    private String fit;
    private int user_id;
    private int item_id;
    private int rating;
    private String rented_for;
    private String category;
    private int size;
    private int id;

    /**
     *
     * @return - the value of fit
     */
    public String getFit() {
        return fit;
    }
    /**
     *
     * @return - the value of user_id
     */
    public int getUser_id() {
        return user_id;
    }
    /**
     *
     * @return - the value of item_id
     */
    public int getItem_id() {
        return item_id;
    }
    /**
     *
     * @return - the value of rating
     */
    public int getRating() {
        return rating;
    }
    /**
     *
     * @return - the value of rented_for
     */
    public String getRented_for() {
        return rented_for;
    }
    /**
     *
     * @return - the value of category
     */
    public String getCategory() {
        return category;
    }
    /**
     *
     * @return - the value of size
     */
    public int getSize() {
        return size;
    }
    /**
     *
     * @return - the id of the item
     */
    public int getId() {
        return id;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
