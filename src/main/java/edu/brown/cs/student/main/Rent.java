package edu.brown.cs.student.main;


/**
 * Rent class displaying rent data for a given user
 */
public class Rent {
        final private String fit ;
        final private int userID;
        final private int itemID;
        final private int rating;
        final private String rentedFor;
        final private String category;
        final private int size;
        final private int ID;


    /**
     * Constructors for given rent data
     * @param fit -- the fit of user of type String
    * @param fit1
     * @param userID -- UserID of user of type int
     * @param itemID -- ItemID of user of type int
     * @param rating -- rating of user of type int
     * @param rentedFor -- purpose of renting of type String
     * @param category -- category of type String
     * @param size -- Size of type int
     * @param ID -- ID of type int
     */
    public Rent(String fit, int userID, int itemID,
                int rating, String rentedFor, String category,
                int size, int ID) {
        this.fit = fit;
        this.userID = userID;
        this.itemID = itemID;
        this.rating = rating;
        this.rentedFor = rentedFor;
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
    public int getUserID() {
        return userID;
    }

    /**
     * getter method for ItemID
     * @return ft of type int
     */
    public int getItemID() {
        return itemID;
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
        return rentedFor;
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


    }


