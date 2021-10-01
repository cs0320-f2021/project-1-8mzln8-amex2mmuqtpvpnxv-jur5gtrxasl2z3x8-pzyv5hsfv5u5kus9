package edu.brown.cs.student.main;

import edu.brown.cs.student.main.Galaxy;

/**
 * Class used to represent star command functionality
 * contains no constructors or fields, just a large method containing all the if else statements pertaining to
 * stars
 */
public class starsAction {
    private Galaxy galaxy;


    public void action(){
        try {
            this.galaxy = new Galaxy();
        } catch (Exception e) {
            System.out.println("ERROR: No stars file was specified");
        }

    }

}