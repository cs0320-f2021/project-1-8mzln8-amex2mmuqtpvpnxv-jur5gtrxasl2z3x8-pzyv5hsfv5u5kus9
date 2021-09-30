package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Class representing a k-d tree object.
 * @param <T> - nodes contain data of type T
 */
public class KDTree<T extends Number> {
  private Node<T> root;
  private final int dimensions;
  private PriorityQueue<Node<T>> kNearestNeighbors = new PriorityQueue<>();

  /**
   * Constructor for a KDTree object.
   * @param dataList - a list containing coordinate data
   */
  public KDTree(List<List<T>> dataList) {
    List<Node<T>> nodeList = new ArrayList<>();

    for (List<T> dataPoint : dataList) {
      Node<T> newNode = new Node<T>(dataPoint);
      nodeList.add(newNode);
    }

    this.dimensions = nodeList.get(0).getCoordinates().size();
    this.root = createTree(nodeList, 0, 0, nodeList.size() - 1,
        null);
  }

  /**
   * Recursive method to create a k-d tree.
   * @param nodeList - list of nodes to put into the tree
   * @param depth - the number of levels from the root of the tree
   * @param beginningIndex - the starting index of nodes to consider in the list
   * @param endIndex - the ending index of nodes to consider in the list
   * @param parent - the parent node
   * @return the root node of the k-d tree
   */
  private Node<T> createTree(List<Node<T>> nodeList, int depth,
                            int beginningIndex, int endIndex, Node<T> parent) {
    if (nodeList.size() == 0) {
      return null;
    }

    int axis = depth % this.dimensions;
    nodeList.sort(new NodeComparator<T>(axis));
    int medianIndex = (beginningIndex + endIndex) / 2;
    Node<T> medianNode = nodeList.get(medianIndex);
    medianNode.setAxis(axis);
    medianNode.setParent(parent);
    medianNode.setLeftChild(createTree(nodeList, depth + 1, beginningIndex,
        medianIndex - 1, medianNode));
    medianNode.setRightChild(createTree(nodeList, depth + 1, medianIndex + 1,
        endIndex, medianNode));
    return medianNode;
  }

  /**
   * Helper method to find the k closest neighbors to a set of target coordinates
   * @param root - the root of the k-d tree we are traversing through
   * @param k - the number of closest neighbors to find
   * @param targetCoordinates - the desired set of coordinates to calculate distance to
   */
  private void searchForKNearestNeighborsHelper(Node<T> root, int k, List<T> targetCoordinates) {
    if (root == null) {
      return;
    }

    int currAxis = root.getAxis();
    T rootCoordinate = root.getCoordinates().get(currAxis);
    T targetCoordinate = targetCoordinates.get(currAxis);
    root.setDistanceToTarget(targetCoordinates);

    if (this.kNearestNeighbors.size() < k || (this.kNearestNeighbors.peek() != null
        && root.compareTo(this.kNearestNeighbors.peek()) < 0)) {
      this.kNearestNeighbors.add(root);
    }

    while (this.kNearestNeighbors.size() > k) {
      this.kNearestNeighbors.poll();
    }

    if (targetCoordinate.doubleValue() < rootCoordinate.doubleValue()) {
      searchForKNearestNeighborsHelper(root.getLeftChild(), k, targetCoordinates);
    } else if (targetCoordinate.doubleValue() > rootCoordinate.doubleValue()) {
      searchForKNearestNeighborsHelper(root.getRightChild(), k, targetCoordinates);
    }
  }

  /**
   * Finds the k closest neighbors to a set of target coordinates
   * @param k - the number of closest neighbors to find
   * @param targetCoordinates - the desired set of coordinates to calculate distance to
   * @return a list containing the k nearest neighbors to the given target coordinates
   */
  public PriorityQueue<Node<T>> searchForKNearestNeighbors(int k, List<T> targetCoordinates) {
    if (this.root == null) {
      return null;
    }
    searchForKNearestNeighborsHelper(this.root, k, targetCoordinates);
    return this.kNearestNeighbors;
  }

}