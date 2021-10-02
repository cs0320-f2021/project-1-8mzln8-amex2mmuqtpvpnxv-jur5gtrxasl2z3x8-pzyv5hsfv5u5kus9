package edu.brown.cs.student.main;


import com.google.gson.Gson;

/**
 * Rent class displaying rent data for a given user
 */
public class Rent {
        final private String fit ;
        final private int user_id;
        final private int item_id;
        final private int rating;
        final private String rented_for;
        final private String category;
        final private int size;
        final private int ID;


    /**
     * Constructors for given rent data
     * @param fit -- the fit of user of type String
     * @param userID -- UserID of user of type int
     * @param itemID -- ItemID of user of type int
     * @param rating -- rating of user of type int
     * @param rentedFor -- purpose of renting of type String
     * @param category -- category of type String
     * @param size -- Size of type int
     * @param ID -- ID of type int
     */
    public Rent(String fit, int user_id, int item_id,
                int rating, String rented_for, String category,
                int size, int ID) {
        this.fit = fit;
        this.user_id = user_id;
        this.item_id = item_id;
        this.rating = rating;
        this.rented_for = rented_for;
        this.category = category;
        this.size = size;
        this.ID = ID;
        }

    /**
     * getter method for fit
      * @return ft of type String
     */
    public String getFit() {
        return fit;
    }

    /**
     * getter method for userID
     * @return ft of type int
     */
    public int user_id() {
        return user_id;
    }

    /**
     * getter method for ItemID
     * @return ft of type int
     */
    public int getItemID() {
        return item_id;
    }

    /**
     * getter method for rating
     * @return rating of type int
     */
    public int getRating() {
        return rating;
    }

    /**
     * getter method for String
     * @return rentedFor of type String
     */
    public String getRentedFor() {
        return rented_for;
    }

    /**
     * getter method for category
     * @return category of type String
     */
    public String getCategory() {
        return category;
    }

    /**
     * getter method for Size
     * @return size of type int
     */
    public int getSize() {
        return size;
    }

    /**
     * getter method for ID
     * @return ID of type int
     */
    public int getID() {
        return ID;
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    }


