package edu.brown.cs.student.stars;

import edu.brown.cs.student.stars.Star;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing a collection of Star objects.
 */
public class Galaxy {
  private ArrayList<Star> starList = new ArrayList<Star>();
  private String starDataFile;
  private int size;

  /**
   * Empty constructor.
   */
  public Galaxy() {

  }

  /**
   * Constructor that reads in a CSV file to populate a Galaxy object.
   * @param starDataFile CSV file containing star data
   */
  public Galaxy(String starDataFile) {
    this.starDataFile = starDataFile;
    try (BufferedReader br = new BufferedReader(new FileReader(starDataFile))) {
      String line;
      String[] starData;
      br.readLine();
      while ((line = br.readLine()) != null) {
        line = line.trim();
        starData = line.split(",");
        this.starList.add(new Star(starData[0], starData[1], starData[2], starData[3],
            starData[4]));
      }
      this.size = this.starList.size();
    } catch (Exception e) {
      System.out.println("ERROR: Input file cannot be read");
    }
  }

  /**
   * Default constructor.
   * @param starList list of stars to set as the Galaxy's starList field
   */
  public Galaxy(ArrayList<Star> starList) {
    this.starList = starList;
  }

  /**
   * Gets the Galaxy's most recent CSV file (if exists) containing star data.
   * @return the Galaxy's most recent CSV file containing star data
   */
  public String getStarDataFile() {
    return this.starDataFile;
  }

  /**
   * Gets the size of the Galaxy (i.e. how many stars it has)
   * @return the size of the Galaxy
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Gets the list of stars in the Galaxy.
   * @return the list of stars in the Galaxy
   */
  public ArrayList<Star> getStarList() {
    return this.starList;
  }

  /**
   * Given the name of a star, searches the Galaxy for the star with the name.
   * @param inputName the given star name
   * @return the star whose name matches the input name, null if no names match
   */
  private Star searchForStarName(String inputName) {
    String name = inputName;
    if (inputName.startsWith("\"")) {
      name = inputName.substring(1, inputName.length() - 1);
    }
    for (Star star : this.starList) {
      if (star.getName().equals(name)) {
        return star;
      }
    }
    return null;
  }

  /**
   * Gets a list of stars of a specified length that are closest in distance to the given location.
   * @param k the number of neighboring stars to list
   * @param inputX the x coordinate of the given location
   * @param inputY the y coordinate of the given location
   * @param inputZ the z coordinate of the given location
   * @return a list of star ids that are closest in distance to the given location
   */
  public ArrayList<Integer> getNearestKNeighbors(String k, String inputX, String inputY,
                                                 String inputZ) {
    int integerK = Integer.parseInt(k);
    float floatX = Float.parseFloat(inputX);
    float floatY = Float.parseFloat(inputY);
    float floatZ = Float.parseFloat(inputZ);

    ArrayList<Star> nearestKNeighbors = new ArrayList<Star>();

    for (Star star : this.starList) {
      star.setDistanceToStar(floatX, floatY, floatZ);
      nearestKNeighbors.add(star);
      Collections.sort(nearestKNeighbors);
    }
    return getKNeighbors(integerK, nearestKNeighbors);
  }

  /**
   * Gets a list of stars of a specified length that are closest in distance to the given star.
   * @param k the number of neighboring stars to list
   * @param starName the name of the star
   * @return a list of star ids that are closest in distance to the given star
   */
  public ArrayList<Integer> getNearestKNeighborsWithName(String k, String starName) {
    int integerK = Integer.parseInt(k);

    Star referenceStar = searchForStarName(starName);
    if (referenceStar == null) {
      throw new NullPointerException("ERROR: Given name does not match any stars in the galaxy!");
    }

    ArrayList<Star> nearestKNeighbors = new ArrayList<Star>();

    for (Star star : starList) {
      if (star.getName().equals(referenceStar.getName())) {
        continue;
      }
      star.setDistanceToStar(referenceStar.getX(), referenceStar.getY(), referenceStar.getZ());
      nearestKNeighbors.add(star);
      Collections.sort(nearestKNeighbors);
    }
    return getKNeighbors(integerK, nearestKNeighbors);
  }

  /**
   * Helper function that formulates the list given a specified number of neighbors.
   * @param integerK the given number of neighboring stars to list
   * @param nearestKNeighbors the list of distances corresponding to each star
   * @return a list of star ids with a length equal to the specified number of neighbors
   */
  private ArrayList<Integer> getKNeighbors(int integerK, ArrayList<Star> nearestKNeighbors) {
    if (nearestKNeighbors.size() > integerK) {
      while (nearestKNeighbors.size() > integerK) {
        nearestKNeighbors.remove(nearestKNeighbors.size() - 1);
      }
    }

    ArrayList<Integer> idList = new ArrayList<Integer>();
    for (Star star : nearestKNeighbors) {
      idList.add(star.getId());
    }
    return idList;
  }
}
