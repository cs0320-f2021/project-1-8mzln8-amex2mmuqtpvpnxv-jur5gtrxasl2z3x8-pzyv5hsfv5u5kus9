package edu.brown.cs.student.main;

/**
 * Class containing all the operations
 */
public class MathBotCommands implements commandAction {

    /**
     * Method that sums two inputs
     * @param args1 -- argument of type String
     * @param args2 -- argument of type String
     */
    public void sum(String args1, String args2) {
        try {
            MathBot mathbot = new MathBot();

            double sum = mathbot.add(Double.parseDouble(args1),
                    Double.parseDouble(args2));
            System.out.println(sum);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("incorrect argument inputs for add");
        }
    }

    /**
     * Method that subracts two inputs
     * @param args1 -- argument of type String
     * @param args2 -- argument of type String
     */
    public void subtract(String args1, String args2) {
        try {
            MathBot mathbot = new MathBot();
            double sum = mathbot.subtract(Double.parseDouble(args1),
                    Double.parseDouble(args2));
            System.out.println(sum);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("incorrect argument inputs for add");
        }
    }



}