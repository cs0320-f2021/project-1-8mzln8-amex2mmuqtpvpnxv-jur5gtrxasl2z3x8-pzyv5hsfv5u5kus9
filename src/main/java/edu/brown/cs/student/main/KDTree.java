package edu.brown.cs.student.main;

import java.util.ArrayList;
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
      Node<T> newNode = new Node<>(dataPoint);
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
    nodeList.sort(new NodeComparator<>(axis));
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
   * @param node - the root node of a KDTree
   * @param targetCoordinates - the target to measure the distance to
   * @return 1 if the root is closer to the target, -1 if the root is farther from the target, and
   * 0 if both are equidistant from the target
   * @throws NullPointerException if the kNearestNeighbors heap is empty
   */
  public int compareNodeToRadius(Node<T> node, List<T> targetCoordinates) throws NullPointerException {
    if (this.kNearestNeighbors.peek() == null) {
      throw new NullPointerException("K nearest neighbors is empty");
    }
    Node<T> best = this.kNearestNeighbors.peek();
    Number bestCoordinate = best.getCoordinates().get(best.getAxis());
    Number nodeCoordinate = node.getCoordinates().get(node.getAxis());
    if (best.getDistanceToTarget() > (bestCoordinate.doubleValue() - nodeCoordinate.doubleValue())) {
      return 1;
    } else {
      return 0;
    }
  }

  private void addNodeToQueue(Node<T> node, List<T> targetCoordinates) {
    node.setDistanceToTarget(targetCoordinates);
    this.kNearestNeighbors.add(node);
  }

  /**
   * Helper method to find the k closest neighbors to a set of target coordinates
   * @param root - the root of the k-d tree we are traversing through
   * @param targetCoordinates - the desired set of coordinates to calculate distance to
   * @throws ArithmeticException,NullPointerException - an error message if traversal failed or
   * produced incorrect output
   */
  public void basicBSTSearch(Node<T> root, List<T> targetCoordinates, int k) {
    this.addNodeToQueue(root, targetCoordinates);
    this.tidyHeap(k);
    int comparison = this.compareNodeToRadius(root, targetCoordinates);
    if (comparison == 1) {
      basicBSTSearch(root.getLeftChild(), targetCoordinates, k);
      basicBSTSearch(root.getRightChild(), targetCoordinates, k);
    } else {
      int currAxis = root.getAxis();
      Number rootCoordinate = root.getCoordinates().get(currAxis);
      Number targetCoordinate = targetCoordinates.get(currAxis);
      root.setDistanceToTarget(targetCoordinates);

      if (targetCoordinate.doubleValue() < rootCoordinate.doubleValue()) {
        basicBSTSearch(root.getLeftChild(), targetCoordinates, k);
      } else if (targetCoordinate.doubleValue() >= rootCoordinate.doubleValue()) {
        basicBSTSearch(root.getRightChild(), targetCoordinates, k);
      }
    }
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
    basicBSTSearch(this.root, targetCoordinates, k);
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