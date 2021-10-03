package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a node in a k-d tree.
 * @param <T> - node coordinates contain data of type T which extends Number
 */
public class Node<T extends Number> implements Comparable<Node<T>> {
  private final List<T> coordinates;
  private int axis;
  private Node<T> leftChild = null;
  private Node<T> rightChild = null;
  private Node<T> parent = null;
  private double distanceToTarget;
  private int userID;

  /**
   * Constructor for Node object with a list of coordinates.
   * @param coordinates - list of coordinates to instantiate
   */
  public Node(List<T> coordinates) {
    this.userID = coordinates.get(0).intValue();
    this.coordinates = coordinates.subList(1, coordinates.size());
  }

  /**
   * Constructor for Node object with a list of coordinates, the left and right child nodes,
   * and the parent node.
   * @param coordinates - list of coordinates to instantiate
   * @param leftChild - left child node to instantiate
   * @param rightChild - right child node to instantiate
   * @param parent - parent node to instantiate
   */
  public Node(List<T> coordinates, Node<T> leftChild, Node<T> rightChild, Node<T> parent) {
    this.coordinates = coordinates;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
    this.parent = parent;
  }

  /**
   * Custom compareTo method for Node object that compares the distanceToTarget field.
   * @param o - Object to compare to
   * @return the result of the comparison between the distanceToTarget fields of the two Objects
   */
  @Override
  public int compareTo(Node<T> o) {
    return Double.compare(o.distanceToTarget, this.distanceToTarget);
  }

  /**
   * Gets the coordinates field of Node object.
   * @return the coordinates field
   */
  public List<T> getCoordinates() {
    return coordinates;
  }

  /**
   * Gets the distanceToTarget field of Node object.
   * @return the distanceToTarget field
   */
  public double getDistanceToTarget() {
    return distanceToTarget;
  }

  /**
   * Gets the leftChild field of Node object.
   * @return the leftChild field
   */
  public Node<T> getLeftChild() {
    return leftChild;
  }

  /**
   * Gets the rightChild field of Node object.
   * @return the rightChild field
   */
  public Node<T> getRightChild() {
    return rightChild;
  }

  /**
   * Gets the parent field of Node object.
   * @return the parent field
   */
  public Node<T> getParent() {
    return parent;
  }

  /**
   * Gets the axis field of Node object.
   * @return the axis field
   */
  public int getAxis() {
    return axis;
  }

  /**
<<<<<<< HEAD
   * Gets the user ID of the Node object.
   * @return the user ID
   */
  public int getUserID() {
    return userID;
  }

  /**
=======
>>>>>>> 9eb14dcd1258110ce6fb6cbe4ed647eacd8a10c5
   * Sets the left child field with the given Node object.
   * @param leftChild - Node object to set
   */
  public void setLeftChild(Node<T> leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * Sets the right child field with the given Node object.
   * @param rightChild - Node object to set
   */
  public void setRightChild(Node<T> rightChild) {
    this.rightChild = rightChild;
  }

  /**
   * Sets the parent field with the given Node object.
   * @param parent - Node object to set
   */
  public void setParent(Node<T> parent) {this.parent = parent; }

  /**
   * Sets the axis field with the given axis value
   * @param axis - axis value to set
   */
  public void setAxis(int axis) {
    this.axis = axis;
  }

  /**
   * Sets the distanceToTarget field of Node object with the appropriate Euclidean distance
   * @param targetCoordinates - the desired set of coordinates to calculate distance to
   */
  public void setDistanceToTarget(List<T> targetCoordinates) {
    this.distanceToTarget = calculateDistance(targetCoordinates);
  }

  /**
   * Calculates the Euclidean distance between a Node object and the given set of coordinates.
   * @param targetCoordinates - the desired set of coordinates to calculate distance to
   */
  private double calculateDistance(List<T> targetCoordinates) {
    double distanceSquared = 0;
    for (int i = 0; i < targetCoordinates.size(); i++) {
      distanceSquared += Math.pow(
          Math.subtractExact(this.coordinates.get(i).longValue(),
              targetCoordinates.get(i).longValue()), 2);
    }
    return Math.sqrt(distanceSquared);
  }

}
