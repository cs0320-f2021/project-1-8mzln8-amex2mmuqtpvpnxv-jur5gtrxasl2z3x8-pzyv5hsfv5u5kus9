package edu.brown.cs.student.commands;

import com.google.gson.Gson;
import edu.brown.cs.student.bloomfilter.*;
import edu.brown.cs.student.kdtree.*;
import edu.brown.cs.student.orm.*;
import edu.brown.cs.student.core.*;
import edu.brown.cs.student.recommender.*;
import edu.brown.cs.student.api.ApiAggregator;

import java.util.ArrayList;
import java.util.List;

public class RecommenderCommands implements REPLCommand {
  private Database orm;
  private ApiAggregator api;
  private KDTree kdtree;
  private BloomFilter bloom;


  @Override
  public void handle(String[] args) {
    try {
      if (args[0].equals("recsys_load")) {
        ApiAggregator api = new ApiAggregator();
        List<Object> apilist = api.getIntegrationData();
        Gson gson = new Gson();
        System.out.println(gson.toJson(apilist));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR: Something wrong happened when getting data from API");
    }
  }
}
