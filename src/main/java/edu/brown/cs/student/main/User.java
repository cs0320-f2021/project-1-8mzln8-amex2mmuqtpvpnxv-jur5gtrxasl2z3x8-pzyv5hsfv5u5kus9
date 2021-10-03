package edu.brown.cs.student.main;

import com.google.gson.Gson;

public class User {
    private int user_id;
    private String weight;
    private String bust_size;
    private String height;
    private int age;
    private String body_type;
    private String horoscope;

    public int getUser_id() {
        return user_id;
    }

    public String getWeight() {
        return weight;
    }

    public String getBust_size() {
        return bust_size;
    }

    public String getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public String getBody_type() {
        return body_type;
    }

    public String getHoroscope() {
        return horoscope;
    }

    public int[] getCoords(){
       int[] coords = new int[3];
       int weight = Integer.parseInt(this.getWeight().replace("lbs",""));
       // Reformat height
       String[] temp_height = this.getHeight().split("\'");
       temp_height[1] = temp_height[1].replace("\"","").trim();
       // Translate height to inches
       int height = Integer.parseInt(temp_height[0]) * 12 + Integer.parseInt(temp_height[1]);
       coords[0] = weight;
       coords[1] = height;
       coords[2] = this.getAge();
       return coords;
    }


    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
