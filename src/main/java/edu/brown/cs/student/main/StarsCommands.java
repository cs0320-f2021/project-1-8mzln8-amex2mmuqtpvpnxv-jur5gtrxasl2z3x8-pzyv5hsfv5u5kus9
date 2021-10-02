package edu.brown.cs.student.main;


/**
 * Class containing all the commands for "stars" command
 */
public class StarsCommands implements commandAction {

    /**
     * Create Galaxy based of of argument
      * @param arguments1 -- String we want to make galaxy out of
     * @return galaxy
     */
    public Galaxy createGalaxy(String arguments1) {
        try {
            return new Galaxy(arguments1);
        } catch (Exception e) {
            System.out.println("ERROR: No stars file was specified");
        }
        return new Galaxy(arguments1);
    }
}