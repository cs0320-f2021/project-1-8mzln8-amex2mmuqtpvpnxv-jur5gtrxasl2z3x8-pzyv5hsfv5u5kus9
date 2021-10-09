package edu.brown.cs.student.commands;

import com.google.gson.Gson;
import edu.brown.cs.student.api.ApiAggregator;
import edu.brown.cs.student.kdtree.KDTree;
import edu.brown.cs.student.kdtree.Node;
import edu.brown.cs.student.runway.User;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunwayCommands implements REPLCommand {
  HashMap<String, Integer> horoscope = new HashMap<>();
  private KDTree kdTree;
  private List<User> userList;

  public RunwayCommands() {
    List<String> horoscopes = new ArrayList<>();
    horoscopes.add("Aries");
    horoscopes.add("Taurus");
    horoscopes.add("Gemini");
    horoscopes.add("Cancer");
    horoscopes.add("Leo");
    horoscopes.add("Virgo");
    horoscopes.add("Libra");
    horoscopes.add("Scorpio");
    horoscopes.add("Sagittarius");
    horoscopes.add("Capricorn");
    horoscopes.add("Aquarius");
    horoscopes.add("Pisces");

    for (String horoscope : horoscopes) {
      this.horoscope.put(horoscope, 0);
    }
  }

  /**
   * Method conducting the command where users <k> <x> <y> <z> is 5 args
   * @param args1 -- k value
   * @param args2 -- x coordinate
   * @param args3 -- y coordinate
   * @param args4 -- z coordinate
   * @param kdtree - the given KDTree to search through
   */
  public void SimilarKNNCoords(String args1, String args2, String args3, String args4, KDTree kdtree) {
    List<Double> coordinates = new ArrayList<>();
    coordinates.add(Double.parseDouble(args2));
    coordinates.add(Double.parseDouble(args3));
    coordinates.add(Double.parseDouble(args4));
    List<Node<Double>> nearestKNeighbors = kdtree.KNNSearch(Integer.parseInt(args1), coordinates);
    for (Node<Double> node : nearestKNeighbors) {
      System.out.println(node.getUniqueID());
    }
  }

  /**
   * Search for similar Users based on the user ID
   * @param args1 - k value
   * @param user - the given user ID
   * @param kdtree - the given KDTree to search through
   */
  public void SimilarKNNUniqueID(String args1, User user, KDTree kdtree) {
    List<Node<Double>> nearestKNeighbors = kdtree.KNNSearch(Integer.parseInt(args1),
        user.getCoords().subList(1, user.getCoords().size()));
    for (Node<Double> node : nearestKNeighbors) {
      System.out.println(node.getUniqueID());
    }
  }

  /**
   * Method conducting the command where users <k> <x> <y> <z> is 5 args
   * @param args1 -- k value
   * @param args2 -- x coordinate
   * @param args3 -- y coordinate
   * @param args4 -- z coordinate
   * @param kdtree - the given KDTree to search through
   */
  public void ClassifyKNNCoords(String args1, String args2, String args3, String args4,
                                KDTree kdtree, List<User> userList) {
    List<Double> coordinates = new ArrayList<>();
    coordinates.add(Double.parseDouble(args2));
    coordinates.add(Double.parseDouble(args3));
    coordinates.add(Double.parseDouble(args4));
    List<Node<Double>> nearestKNeighbors = kdtree.KNNSearch(Integer.parseInt(args1), coordinates);
    fillHoroscopes(userList, nearestKNeighbors);
  }

  /**
   * Search for similar Users based on the user ID
   * @param args1 - k value
   * @param user - the given user ID
   * @param kdtree - the given KDTree to search through
   */
  public void ClassifyKNNUniqueID(String args1, User user, KDTree kdtree, List<User> userList) {
    List<Node<Double>> nearestKNeighbors = kdtree.KNNSearch(Integer.parseInt(args1),
        user.getCoords().subList(1, user.getCoords().size()));
    fillHoroscopes(userList, nearestKNeighbors);
  }


  /**
   * Fills in the Horoscopes hashmap
   * @param userList - the list of users
   * @param nearestKNeighbors - the k nearest neighbors to the target coordinates
   */
  private void fillHoroscopes(List<User> userList, List<Node<Double>> nearestKNeighbors) {
    for (Node<Double> node : nearestKNeighbors) {
      for (User u : userList) {
        if (node.getUniqueID() == u.getUser_id()) {
          this.horoscope.put(u.getHoroscope(), this.horoscope.get(u.getHoroscope()) + 1);
        }
      }
    }
    for (Map.Entry<String, Integer> entry : horoscope.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      System.out.println(key + ": " + value);
    }
  }

  @Override
  public void handle(String[] args) {
    try {
      switch (args[0]) {
        case "similar":
          if (args.length > 3) {
            this.SimilarKNNCoords(args[1], args[2], args[3], args[4], this.kdTree);
          } else {
            int userIDToSearch = Integer.parseInt(args[2]);
            for (User user : this.userList) {
              if (userIDToSearch == user.getUser_id()) {
                this.SimilarKNNUniqueID(args[1], user, this.kdTree);
              }
            }
          }
          break;
        case "classify":
          if (args.length > 3) {
            this.ClassifyKNNCoords(args[1], args[2], args[3],
                args[4], this.kdTree, this.userList);
          } else {
            int userIDToSearch = Integer.parseInt(args[2]);
            for (User user : this.userList) {
              if (userIDToSearch == user.getUser_id()) {
                this.ClassifyKNNUniqueID(args[1], user, this.kdTree, this.userList);
              }
            }
          }
          break;
        case "users":
          Gson gson = new Gson();
          // arguments[1] should be the filepath
          Reader reader = Files.newBufferedReader(Paths.get(args[1]));
          ApiAggregator api = new ApiAggregator();
          Type type = api.setType("users");
          this.userList = gson.fromJson(reader, type);
          List<List<Number>> dataList = new ArrayList<>();
          for (User user : this.userList) {
            dataList.add(user.getCoords());
          }
          this.kdTree = new KDTree<>(dataList);
          reader.close();
          break;
      }
    } catch (Exception e) {
      System.out.println("Incorrect arguments for " + args[0] + " command");
    }
  }
}