package edu.brown.cs.student.commands;

import edu.brown.cs.student.main.MathBot;

/**
 * Class containing all the MathBot operations
 */
public class MathBotCommands implements REPLCommand {

    /**
     * Method that sums two inputs
     * @param args1 - argument of type String
     * @param args2 - argument of type String
     */
    public void sum(String args1, String args2) {
        try {
            MathBot mathbot = new MathBot();

            double sum = mathbot.add(Double.parseDouble(args1),
                    Double.parseDouble(args2));
            System.out.println(sum);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Incorrect argument inputs for add command");
        }
    }

    /**
     * Method that subtracts two inputs
     * @param args1 - argument of type String
     * @param args2 - argument of type String
     */
    public void subtract(String args1, String args2) {
        try {
            MathBot mathbot = new MathBot();
            double sum = mathbot.subtract(Double.parseDouble(args1),
                    Double.parseDouble(args2));
            System.out.println(sum);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Incorrect argument inputs for subtract command");
        }
    }

    /**
     * Custom handle method for MathBotCommands
     * @param args - array of Strings parsed from a command line
     */
    @Override
    public void handle(String[] args) {
        if (args[0].equals("add")) {
            this.sum(args[1], args[2]);
        } else if (args[0].equals("subtract")) {
            this.subtract(args[1], args[2]);
        }
    }
}
