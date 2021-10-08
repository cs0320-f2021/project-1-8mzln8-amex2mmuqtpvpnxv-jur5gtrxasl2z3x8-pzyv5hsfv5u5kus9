package edu.brown.cs.student.commands;

import com.google.gson.Gson;
import edu.brown.cs.student.api.ApiAggregator;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ApiCommands implements REPLCommand {

  public void handle(String[] args) {
    try {
      if (args[0].equals("data")) {
        ApiAggregator api = new ApiAggregator();
        List<Object> list = api.getData(args[1]);
        Gson gson = new Gson();
        System.out.println(gson.toJson(list));
      } else if (args[0].equals("json")) {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(args[2]));
        ApiAggregator api = new ApiAggregator();
        Type type = api.setType(args[1]);
        List<Object> list = gson.fromJson(reader, type);
        reader.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Could not load in file");
    }
  }
}
