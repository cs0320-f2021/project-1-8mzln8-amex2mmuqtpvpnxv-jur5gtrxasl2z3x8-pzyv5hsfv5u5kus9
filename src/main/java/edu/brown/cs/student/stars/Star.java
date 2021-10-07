package edu.brown.cs.student.stars;

/**
 * Class representing a star.
 */
public class Star implements Comparable<Star> {
  private int id;
  private String name;
  private float x;
  private float y;
  private float z;
  private float distance;

  /**
   * Default constructor.
   * @param id unique id of the star
   * @param name name of the star
   * @param x x coordinate of the star
   * @param y y coordinate of the star
   * @param z z coordinate of the star
   */
  public Star(int id, String name, float x, float y, float z) {
    this.id = id;
    this.name = name;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Constructor taking in param types of all strings.
   * @param id unique id of the star
   * @param name name of the star
   * @param x x coordinate of the star
   * @param y y coordinate of the star
   * @param z z coordinate of the star
   */
  public Star(String id, String name, String x, String y, String z) {
    this.id = Integer.parseInt(id);
    this.name = name;
    this.x = Float.parseFloat(x);
    this.y = Float.parseFloat(y);
    this.z = Float.parseFloat(z);
  }

  /**
   * Empty constructor.
   */
  public Star() {

  }

  /**
   * Gets the id of the star.
   * @return the id of the star
   */
  public int getId() {
    return this.id;
  }

  /**
   * Gets the x coordinate of the star.
   * @return the x coordinate of the star
   */
  public float getX() {
    return this.x;
  }

  /**
   * Gets the y coordinate of the star.
   * @return the y coordinate of the star
   */
  public float getY() {
    return this.y;
  }

  /**
   * Gets the z coordinate of the star.
   * @return the z coordinate of the star
   */
  public float getZ() {
    return this.z;
  }

  /**
   * Gets the name of the star.
   * @return the name of the star
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the distance to the star from a pre-specified location.
   * @return the distance to the star
   */
  public float getDistance() {
    return this.distance;
  }

  /**
   * Sets the distance to the star from the specified location.
   * @param inputX the x coordinate of the specified location
   * @param inputY the y coordinate of the specified location
   * @param inputZ the z coordinate of the specified location
   */
  public void setDistanceToStar(float inputX, float inputY, float inputZ) {
    double distanceSquaredX = Math.pow(this.x - inputX, 2);
    double distanceSquaredY = Math.pow(this.y - inputY, 2);
    double distanceSquaredZ = Math.pow(this.z - inputZ, 2);
    this.distance = (float) Math.sqrt(distanceSquaredX + distanceSquaredY + distanceSquaredZ);
  }

  /**
   * Compares the distance field of two stars.
   * @param star the star to compare the current Star object to
   * @return the star whose distance is smaller
   */
  @Override
  public int compareTo(Star star) {
    return Float.compare(this.distance, star.getDistance());
  }
}

