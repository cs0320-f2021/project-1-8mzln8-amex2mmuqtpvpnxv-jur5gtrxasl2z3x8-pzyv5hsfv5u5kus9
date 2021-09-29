package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KDTree<T extends Comparable<T>> {
  private Node<T> root;
  private final int dimensions;
  private PriorityQueue<Node<T>> kNearestNeighbors;

  public KDTree(List<List<T>> dataList) {
    List<Node<T>> nodeList = new ArrayList<>();

    for (List<T> dataPoint : dataList) {
      Node<T> newNode = new Node<T>(dataPoint);
      nodeList.add(newNode);
    }

    this.dimensions = nodeList.get(0).getCoordinates().size();
    this.root = createTree(nodeList, 0, 0, nodeList.size() - 1, null);
  }

  public Node<T> createTree(List<Node<T>> nodeList, int depth, int beginningIndex, int endIndex, Node<T> parent) {
    if (nodeList.size() == 0) {
      return null;
    }

    int currAxis = depth % this.dimensions;
    nodeList.sort(new NodeComparator<T>(currAxis));
    int medianIndex = (beginningIndex + endIndex) / 2;
    Node<T> medianNode = nodeList.get(medianIndex);
    medianNode.setParent(parent);
    medianNode.setLeftChild(createTree(nodeList, depth + 1, beginningIndex,
        medianIndex - 1, medianNode));
    medianNode.setRightChild(createTree(nodeList, depth + 1, medianIndex + 1,
        endIndex, medianNode));
    return medianNode;
  }

}