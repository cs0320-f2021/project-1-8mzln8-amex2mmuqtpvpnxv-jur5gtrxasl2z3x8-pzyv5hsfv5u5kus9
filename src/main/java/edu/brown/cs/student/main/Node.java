package edu.brown.cs.student.main;

import java.util.List;

/**
 * Class representing a node in a k-d tree.
 * @param <T> - node coordinates contain data of type T
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
  private final List<T> coordinates;
  private Node<T> leftChild = null;
  private Node<T> rightChild = null;
  private Node<T> parent = null;
  private double distanceToTarget;

  /**
   * Constructor for Node object with a list of coordinates.
   * @param coordinates - list of coordinates to instantiate
   */
  public Node(List<T> coordinates) {
    this.coordinates = coordinates;
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
   * Calculates the Euclidean distance between a Node object and the given set of coordinates.
   * @param targetCoordinates - the desired set of coordinates to calculate distance to
   * @return the Euclidean distance from the current Node object to the target coordinates
   */

  public double distance(List<T> targetCoordinates) {
    double distanceSquared = 0;
    for (int i = 0; i < targetCoordinates.size(); i++) {
      distanceSquared += Math.pow((double) this.coordinates.get(i) - (double) targetCoordinates.get(i), 2);
      // added a typecast here in an effort to maintain type T but should find alternative fix
    }
    this.distanceToTarget = Math.sqrt(distanceSquared);
    return distanceSquared;
  }

}
