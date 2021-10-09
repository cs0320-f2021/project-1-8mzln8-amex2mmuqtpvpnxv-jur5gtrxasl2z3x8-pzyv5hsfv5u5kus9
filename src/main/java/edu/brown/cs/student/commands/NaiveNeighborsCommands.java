package edu.brown.cs.student.commands;

import edu.brown.cs.student.stars.Galaxy;

import java.util.ArrayList;

/**
 * Class containing all the commands for NaiveNeighbors
 */
public class NaiveNeighborsCommands implements REPLCommand {
    ArrayList<Integer> nearestKNeighbors;
    Galaxy galaxy;

    /**
     * Method conducting the command where naive_neighbors <k> <x> <y> <z> is 5 args
     * @param args1 - k value
     * @param args2 - x coordinate
     * @param args3 - y coordinate
     * @param args4 - z coordinate
     * @param g - galaxy
     */
    public void KNNCoordinates(String args1, String args2, String args3, String args4, Galaxy g) {
        nearestKNeighbors = g.getNearestKNeighbors(args1, args2,
                args3, args4);
        System.out.println("Read " + g.getSize() + " stars from "
                + g.getStarDataFile());
        for (Integer starId : nearestKNeighbors) {
            System.out.println(starId);
        }
    }

    /**
     * Method conducting the command where naive_neighbors <k> <“name”>
     * @param args1 - k value
     * @param args2 - name
     */
    public void KNNStarName(String args1, String args2, Galaxy g) {
        nearestKNeighbors =
                g.getNearestKNeighborsWithName(args1, args2);
        System.out.println(
                "Read " + g.getSize() + " stars from "
                        + g.getStarDataFile());
        for (Integer starId : nearestKNeighbors) {
            System.out.println(starId);
        }
    }

    /**
     * Custom handle method for NaiveNeighborsCommands
     * @param args - array of Strings parsed from a command line
     */
    @Override
    public void handle(String[] args) {
        try {
            if (args[0].equals("stars")) {
                this.galaxy = new Galaxy(args[1]);
            } else if (args[0].equals("naive_neighbors")) {
                if (args[1].equals("0")) {
                    System.out.println("Read "
                        + this.galaxy.getSize() +
                        " stars from "
                        + this.galaxy.getStarDataFile());
                } else {
                    if (args.length > 3) {
                        this.KNNCoordinates(args[1], args[2], args[3], args[4], this.galaxy);
                    } else {
                        this.KNNStarName(args[1], args[2], this.galaxy);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Incorrect arguments for naive neighbors command");
        }
    }
}







