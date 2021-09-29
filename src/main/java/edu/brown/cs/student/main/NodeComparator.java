package edu.brown.cs.student.main;

import java.util.Comparator;

/**
 * Class containing a Comparator for the Node class.
 * @param <T> - node coordinates contain data of type T
 */
public class NodeComparator<T extends Comparable<T>> implements Comparator<Node<T>> {
  private final int axisToCompare;

  /**
   * Constructor for NodeComparator object.
   * @param axisToCompare - the current axis to compare to, used as an index
   */
  public NodeComparator(int axisToCompare) {
    this.axisToCompare = axisToCompare;
  }

  /**
   * Custom compare method for Node objects.
   * @param o1 - one Node object to compare
   * @param o2 - another Node object to compare
   * @return the result of the comparison
   */
  @Override
  public int compare(Node<T> o1, Node<T> o2) {
    return Integer.compare(0,
        o1.getCoordinates().get(this.axisToCompare)
            .compareTo(o2.getCoordinates().get(this.axisToCompare)));
  }
}
