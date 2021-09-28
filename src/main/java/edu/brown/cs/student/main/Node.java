package main.java.edu.brown.cs.student.main;

import java.util.List;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
  private final List<T> coordinates;
  private Node<T> leftChild = null;
  private Node<T> rightChild = null;
  private double distanceToTarget;

  public Node(List<T> coordinates) {
    this.coordinates = coordinates;
  }
  public Node(List<T> coordinates, Node<T> leftChild, Node<T> rightChild) {
    this.coordinates = coordinates;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

  @Override
  public int compareTo(Node<T> o) {
    return Double.compare(o.distanceToTarget, this.distanceToTarget);
  }

  public List<T> getCoordinates() {
    return coordinates;
  }

  public double getDistanceToTarget() {
    return distanceToTarget;
  }

  public void setLeftChild(Node<T> leftChild) {
    this.leftChild = leftChild;
  }

  public void setRightChild(Node<T> rightChild) {
    this.rightChild = rightChild;
  }

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
