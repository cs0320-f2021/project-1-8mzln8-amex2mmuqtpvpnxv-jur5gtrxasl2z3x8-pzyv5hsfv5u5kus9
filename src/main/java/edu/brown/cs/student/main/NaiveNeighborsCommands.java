package edu.brown.cs.student.main;


import java.util.ArrayList;

/**
 * Class containing all the commands for NaiveNeighbours
 */
public class NaiveNeighborsCommands {
    ArrayList<Integer> nearestKNeighbors;
    //

    /**
     * Method conducting the command where naive_neighbors <k> <x> <y> <z> is 5 args
     * @param args1 -- k val
     * @param args2 -- x coordinate
     * @param args3 -- y coordinate
     * @param args4 -- z coordinate
     * @param g
     */
    public void NN_Coord(String args1,String args2,String args3, String args4, Galaxy g ) {
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
     * @param args1 -- k value
     * @param args2 -- name
     */
    public void NN_Star(String args1,String args2,Galaxy g) {
        nearestKNeighbors =
                g.getNearestKNeighborsWithName(args1, args2,);
        System.out.println(
                "Read " + g.getSize() + " stars from "
                        + g.getStarDataFile());
        for (Integer starId : nearestKNeighbors) {
            System.out.println(starId);
    }





}

