package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Class representing a k-d tree object.
 * @param <T> - nodes contain data of type T which extends Number
 */
public class KDTree<T extends Number> {
  private Node<T> root;
  private final int dimensions;
  private PriorityQueue<Node<T>> kNearestNeighbors = new PriorityQueue<>();

  public Node<T> getTree() {
    return root;
  }

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
    this.root = createTree(nodeList, 0,
        null);
  }

  /**
   * Recursive method to create a k-d tree.
   * @param nodeList - list of nodes to put into the tree
   * @param depth - the number of levels from the root of the tree
   * @param parent - the parent node
   * @return the root node of the k-d tree
   */
  private Node<T> createTree(List<Node<T>> nodeList, int depth, Node<T> parent) {
    if (nodeList.size() == 0) {
      return null;
    }

    int axis = depth % this.dimensions;
    nodeList.sort(new NodeComparator<T>(axis));
    int medianIndex = (nodeList.size() - 1) / 2;
    Node<T> medianNode = nodeList.get(medianIndex);
    medianNode.setAxis(axis);
    medianNode.setParent(parent);
    if (medianIndex == 0 || medianIndex == nodeList.size() - 1) {
      return medianNode;
    }

    List<Node<T>> leftNodes = nodeList.subList(0, medianIndex - 1);
    List<Node<T>> rightNodes = nodeList.subList(medianIndex + 1, nodeList.size() - 1);

    medianNode.setLeftChild(createTree(leftNodes, depth + 1,  medianNode));
    medianNode.setRightChild(createTree(rightNodes, depth + 1, medianNode));
    return medianNode;
  }

  /**
   * Removes elements from the kNearestNeighbors heap until there are k elements or less
   * @param k - the desired number of elements to have in the heap
   */
  private void tidyHeap(int k) {
    while (this.kNearestNeighbors.size() > k) {
      this.kNearestNeighbors.poll();
    }
  }

  /**
   * Compares the single-axis distance from the target to the root node and the highest priority node
   * in the kNearestNeighbors heap
   * @param root - the root node of a KDTree
   * @param targetCoordinates - the target to measure the distance to
   * @return 1 if the root is closer to the target, -1 if the root is farther from the target, and
   * 0 if both are equidistant from the target
   * @throws NullPointerException if the kNearestNeighbors heap is empty
   */
  public int compareNodeToRadius(Node<T> root, List<T> targetCoordinates) throws NullPointerException {
    if (this.kNearestNeighbors.peek() == null) {
      throw new NullPointerException("K nearest neighbors is empty");
    }
    Node<T> best = this.kNearestNeighbors.peek();
    root.setDistanceToTarget(targetCoordinates);
    best.setDistanceToTarget(targetCoordinates);
    return root.compareTo(best);
  }

  /**
   * Algorithm to backtrack up the KDTree
   * @param node - node to start backtracking from
   * @param targetCoordinates - target
   */
  private void knnBacktracking(Node<T> node, Node<T> top, List<T> targetCoordinates) {
    this.kNearestNeighbors.add(node);
    Node<T> parent = node.getParent();
    if (parent == null || parent.equals(top)) { // checks to see if we hit top of tree or visited subtree
      return;
    }
    // checks to see if there's valid space on other side of parent node
    if (this.compareNodeToRadius(parent, targetCoordinates) == 1) {
      if (parent.getRightChild().equals(node) && parent.getLeftChild() != null) {
        // gets relevant leaf node and backtracks until it reaches parent
        Node<T> leaf = basicBSTSearch(parent.getLeftChild(), targetCoordinates);
        knnBacktracking(leaf, parent, targetCoordinates);
      }
      if (parent.getLeftChild().equals(node) && parent.getRightChild() != null) {
        // gets relevant leaf node and backtracks until it reaches parent
        Node<T> leaf = basicBSTSearch(parent.getRightChild(), targetCoordinates);
        knnBacktracking(leaf, parent, targetCoordinates);
      }
    }
    // no valid space on other side of parent, so recurs.
    knnBacktracking(parent, null, targetCoordinates);
  }


  /**
   * Helper method to find the k closest neighbors to a set of target coordinates
   * @param root - the root of the k-d tree we are traversing through
   * @param targetCoordinates - the desired set of coordinates to calculate distance to
   * @throws ArithmeticException,NullPointerException - an error message if traversal failed or
   * produced incorrect output
   */
  public Node<T> basicBSTSearch(Node<T> root, List<T> targetCoordinates)
      throws ArithmeticException, NullPointerException {
    if (root == null) {
      throw new NullPointerException("Hit a null leaf without returning");
    }

    int currAxis = root.getAxis();
    Number rootCoordinate = root.getCoordinates().get(currAxis);
    Number targetCoordinate = targetCoordinates.get(currAxis);
    root.setDistanceToTarget(targetCoordinates);

    if (targetCoordinate.doubleValue() < rootCoordinate.doubleValue()) {
      if (root.getLeftChild() == null) {
        return root;
      }
      return basicBSTSearch(root.getLeftChild(), targetCoordinates);
    } else if (targetCoordinate.doubleValue() >= rootCoordinate.doubleValue()) {
      if (root.getRightChild() == null) {
        return root;
      }
      return basicBSTSearch(root.getRightChild(), targetCoordinates);
    }
    throw new ArithmeticException("At a valid node but did not pass comparator conditions");
  }

  /**
   * Finds the k closest neighbors to a set of target coordinates
   * @param k - the number of closest neighbors to find
   * @param targetCoordinates - the desired set of coordinates to calculate distance to
   * @return a list containing the k nearest neighbors to the given target coordinates
   */
  public PriorityQueue<Node<T>> KNNSearch(int k, List<T> targetCoordinates) {
    if (this.root == null) {
      return null;
    }
    Node<T> bottom = basicBSTSearch(this.root, targetCoordinates);
    this.knnBacktracking(bottom, this.root.getParent(), targetCoordinates);
    this.tidyHeap(k);
    return this.kNearestNeighbors;
  }

  /**
   * Gets the kNearestNeighbors field
   * @return the kNearestNeighbors field
   */
  public PriorityQueue<Node<T>> getKNearestNeighbors() {
    return this.kNearestNeighbors;
  }

  /**
   * Sets the kNearestNeighbors field
   * @param kNearestNeighbors the Priority Queue to set the kNearestNeighbors field to
   */
  public void setKNearestNeighbors(
      PriorityQueue<Node<T>> kNearestNeighbors) {
    this.kNearestNeighbors = kNearestNeighbors;
  }
}