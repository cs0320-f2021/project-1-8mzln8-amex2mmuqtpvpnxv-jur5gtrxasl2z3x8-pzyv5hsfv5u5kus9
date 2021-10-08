package edu.brown.cs.student.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandActions {
  Map<String, REPLCommand> commandMap = new HashMap<String, REPLCommand>();

  public CommandActions() {
    MathBotCommands mathBotCommand = new MathBotCommands();
    commandMap.put("add", mathBotCommand);
    commandMap.put("subtract", mathBotCommand);
    NaiveNeighborsCommands naiveNeighborsCommand = new NaiveNeighborsCommands();
    commandMap.put("stars", naiveNeighborsCommand);
    commandMap.put("naive_neighbors", naiveNeighborsCommand);
    RunwayCommands runwayCommand = new RunwayCommands();
    commandMap.put("users", runwayCommand);
    commandMap.put("similar", runwayCommand);
    commandMap.put("classify", runwayCommand);
    ApiCommands apiCommand = new ApiCommands();
    commandMap.put("data", apiCommand);
    commandMap.put("json", apiCommand);
  }

  public void addNewCommand(String command, REPLCommand commandAction) {
    commandMap.put(command, commandAction);
  }

  public Map<String, REPLCommand> getCommandMap() {
    return commandMap;
  }
}
