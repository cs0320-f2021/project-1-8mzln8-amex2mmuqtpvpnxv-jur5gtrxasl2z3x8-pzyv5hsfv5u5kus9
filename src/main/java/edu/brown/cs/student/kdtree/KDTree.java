package edu.brown.cs.student.kdtree;

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
    this.root = createTree(nodeList, 0, null);
  }

  /**
   * Recursive method to create a k-d tree.
   * @param nodeList - list of nodes to put into the tree
   * @param depth - the number of levels from the root of the tree
   * @param parent - the parent node
   * @return the root node of the kd tree
   */
  private Node<T> createTree(List<Node<T>> nodeList, int depth, Node<T> parent) {
    if (nodeList.size() == 0) {
      return null;
    }

    int axis = depth % this.dimensions;
    nodeList.sort(new NodeComparator<>(axis));
    int medianIndex = nodeList.size() / 2;
    Node<T> medianNode = nodeList.get(medianIndex);
    medianNode.setAxis(axis);
    medianNode.setParent(parent);

    List<Node<T>> leftNodes = new ArrayList<>(nodeList.subList(0, medianIndex));
    List<Node<T>> rightNodes = new ArrayList<>(nodeList.subList(medianIndex + 1, nodeList.size()));

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
   * Compares the single-axis distance from the target to the root node and the highest priority
   * node in the kNearestNeighbors heap
   * @param node - the root node of a KDTree
   * @return 1 if the root is closer to the target, -1 if the root is farther from the target, and
   * 0 if both are equidistant from the target
   * @throws NullPointerException if the kNearestNeighbors heap is empty
   */
  private int compareNodeToRadius(Node<T> node) throws NullPointerException {
    if (this.kNearestNeighbors.peek() == null) {
      throw new NullPointerException("K nearest neighbors is empty");
    }
    Node<T> best = this.kNearestNeighbors.peek();
    Number bestValueOnAxis = best.getCoordinates().get(best.getAxis());
    Number nodeValueOnAxis = node.getCoordinates().get(node.getAxis());
    if (best.getDistanceToTarget() >
        (Math.abs(bestValueOnAxis.doubleValue() - nodeValueOnAxis.doubleValue()))) {
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
   */
  private void basicBSTSearch(Node<T> root, List<T> targetCoordinates, int k) {
    if (root == null) {
      return;
    }
    root.setDistanceToTarget(targetCoordinates);
    this.addNodeToQueue(root, targetCoordinates);
    this.tidyHeap(k);
    int comparison = this.compareNodeToRadius(root);
    if (comparison == 1 || (this.kNearestNeighbors.size() < k)) {
      basicBSTSearch(root.getLeftChild(), targetCoordinates, k);
      basicBSTSearch(root.getRightChild(), targetCoordinates, k);
    } else {
      int currAxis = root.getAxis();
      Number rootValueOnAxis = root.getCoordinates().get(currAxis);
      Number targetValueOnAxis = targetCoordinates.get(currAxis);

      if (targetValueOnAxis.doubleValue() < rootValueOnAxis.doubleValue()) {
        basicBSTSearch(root.getLeftChild(), targetCoordinates, k);
      } else if (targetValueOnAxis.doubleValue() >= rootValueOnAxis.doubleValue()) {
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
  public List<Node<T>> KNNSearch(int k, List<T> targetCoordinates) {
    if (this.root == null) {
      return null;
    }
    if (k == 0) {
      return new ArrayList<>();
    }
    basicBSTSearch(this.root, targetCoordinates, k);
    List<Node<T>> listOfKNN = new ArrayList<>();
    while (this.kNearestNeighbors.peek() != null) {
      listOfKNN.add(this.kNearestNeighbors.poll());
    }
    Collections.reverse(listOfKNN);
    return listOfKNN;
  }

  /**
   * Gets the root node of the KDTree
   * @return the root node
   */
  public Node<T> getTree() {
    return this.root;
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