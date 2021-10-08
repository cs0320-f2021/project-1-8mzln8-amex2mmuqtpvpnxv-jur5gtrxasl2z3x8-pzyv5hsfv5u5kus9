package edu.brown.cs.student.commands;


import edu.brown.cs.student.stars.Galaxy;

/**
 * Class containing all the commands for "stars" command
 */
public class StarsCommands implements CommandAction {

    /**
     * Create Galaxy based of argument
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
