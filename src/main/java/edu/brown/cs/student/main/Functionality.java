
/**
 * Class representing REPL Functionality
 * contains hashmap with key representing commands of type String and commandAction object
 */
public class Functionality {
 private Hashmap commandHashMap = new Hashmap<String,commandAction>();

commandHashmap.put();


    /**
     * Getter method to return commandHashmap
     * @return commandHashMap of type hashmap
     */
    public Hashmap getCommandHashMap() {
        return commandHashMap;
    }

    /**
     * Getter method that sets hashmap to inputted val
     * @param commandHashMap -- the hashmap we want to update to
     */
    public void setCommandHashMap(Hashmap commandHashMap) {
        this.commandHashMap = commandHashMap;
    }
}