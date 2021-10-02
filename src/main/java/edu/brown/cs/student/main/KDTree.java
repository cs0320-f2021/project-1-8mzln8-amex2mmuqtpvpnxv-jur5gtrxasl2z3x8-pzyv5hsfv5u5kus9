package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.Collections;
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
    if (beginningIndex >= endIndex || nodeList.size() == 0) {
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


  private void tidyHeap(int k) {
    while (this.kNearestNeighbors.size() > k++) {
      this.kNearestNeighbors.poll();
    }
  }


  public int compareNodeToRadiusOnAxis(Node<T> root, List<T> targetCoordinates) {
    Node<T> best = this.kNearestNeighbors.peek();
    int comparisonAxis = root.getAxis();
    long bestDistanceOnAxis = Math.subtractExact(best.getCoordinates().get(comparisonAxis).longValue(),
        targetCoordinates.get(comparisonAxis).longValue());
    long rootDistanceOnAxis = Math.subtractExact(root.getCoordinates().get(comparisonAxis).longValue(),
        targetCoordinates.get(comparisonAxis).longValue());
    if (rootDistanceOnAxis < bestDistanceOnAxis) {
      return 1;
    }
    return 0;
  }

  /**
   * Algorithm to backtrack
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
    if (this.compareNodeToRadiusOnAxis(parent, targetCoordinates) == 1) {
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
   * @throws ArithmeticException,NullPointerException - an error message if traversal failed or produced incorrect output
   */
  public Node<T> basicBSTSearch(Node<T> root, List<T> targetCoordinates) throws ArithmeticException, NullPointerException {
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
  public PriorityQueue<Node<T>> KNN(int k, List<T> targetCoordinates) {
    if (this.root == null) {
      return null;
    }
    Node<T> bottom = basicBSTSearch(this.root, targetCoordinates);
    this.knnBacktracking(bottom, this.root.getParent(), targetCoordinates);
    this.tidyHeap(k);
    return this.kNearestNeighbors;
  }

  public PriorityQueue<Node<T>> getkNearestNeighbors() {
    return this.kNearestNeighbors;
  }

  public void setkNearestNeighbors(
      PriorityQueue<Node<T>> kNearestNeighbors) {
    this.kNearestNeighbors = kNearestNeighbors;
  }
}