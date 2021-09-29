package edu.brown.cs.student.main;

import edu.brown.cs.student.main.Node;

import java.util.Comparator;

public class NodeComparator<T extends Comparable<T>> implements Comparator<Node<T>> {
  private final int axisToCompare;

  public NodeComparator(int axisToCompare) {
    this.axisToCompare = axisToCompare;
  }

  @Override
  public int compare(Node<T> o1, Node<T> o2) {
    return Integer.compare(0,
        o1.getCoordinates().get(this.axisToCompare).compareTo(o2.getCoordinates().get(this.axisToCompare)));
  }
}
