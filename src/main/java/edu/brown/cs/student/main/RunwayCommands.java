package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunwayCommands implements CommandAction {
  HashMap<String, Integer> horoscope = new HashMap<>();

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
      System.out.println(node.getUserID());
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
        user.getCoords().subList(1, 3));
    for (Node<Double> node : nearestKNeighbors) {
      System.out.println(node.getUserID());
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
    for (Node<Double> node : nearestKNeighbors) {
      for (User u : userList) {
        if (node.getUserID() == u.getUser_id()) {
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

  /**
   * Search for similar Users based on the user ID
   * @param args1 - k value
   * @param user - the given user ID
   * @param kdtree - the given KDTree to search through
   */
  public void ClassifyKNNUniqueID(String args1, User user, KDTree kdtree, List<User> userList) {
    List<Node<Double>> nearestKNeighbors = kdtree.KNNSearch(Integer.parseInt(args1),
        user.getCoords().subList(1, 3));
    for (Node<Double> node : nearestKNeighbors) {
      for (User u : userList) {
        if (node.getUserID() == u.getUser_id()) {
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
}