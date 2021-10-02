package edu.brown.cs.student.main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

  public List<List<Integer>> testset1() {
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(0);
    coor1.add(0);
    List<Integer> coor2 = new ArrayList<>();
    coor2.add(1);
    coor2.add(2);
    List<Integer> coor3 = new ArrayList<>();
    coor3.add(4);
    coor3.add(2);
    List<Integer> coor4 = new ArrayList<>();
    coor4.add(5);
    coor4.add(8);
    List<Integer> coor5 = new ArrayList<>();
    coor5.add(6);
    coor5.add(9);

    List<List<Integer>> listOfCoors = new ArrayList<>();
    listOfCoors.add(coor1);
    listOfCoors.add(coor2);
    listOfCoors.add(coor3);
    listOfCoors.add(coor4);
    listOfCoors.add(coor5);

    return listOfCoors;

  }

  public Node testTree1() {
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(0);
    coor1.add(0);
    List<Integer> coor2 = new ArrayList<>();
    coor2.add(1);
    coor2.add(2);
    List<Integer> coor3 = new ArrayList<>();
    coor3.add(4);
    coor3.add(2);
    List<Integer> coor4 = new ArrayList<>();
    coor4.add(5);
    coor4.add(8);
    List<Integer> coor5 = new ArrayList<>();
    coor5.add(6);
    coor5.add(9);
    Node<Integer> node1 = new Node<>(coor1);
    Node<Integer> node2 = new Node<>(coor2);
    Node<Integer> node3 = new Node<>(coor3);
    Node<Integer> node4 = new Node<>(coor4);
    Node<Integer> node5 = new Node<>(coor5);

    node3.setAxis(0);
    node3.setLeftChild(node2);
    node3.setRightChild(node4);
    node3.setParent(null);

    node2.setAxis(1);
    node2.setLeftChild(node1);
    node2.setRightChild(null);
    node2.setParent(node3);

    node1.setAxis(0);
    node1.setLeftChild(null);
    node1.setRightChild(null);
    node1.setParent(node2);

    node4.setAxis(1);
    node4.setLeftChild(null);
    node4.setRightChild(node5);
    node4.setParent(node3);

    node5.setAxis(0);
    node5.setLeftChild(null);
    node5.setRightChild(null);
    node5.setParent(node4);

    return node3;
  }

  @Test
  public void testCompareNodeToRadiusOnAxis() {
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(0);
    coor1.add(0);

    Node<Integer> node1 = new Node<>(coor1);

    List<Integer> coor2 = new ArrayList<>();
    coor2.add(1);
    coor2.add(2);

    Node<Integer> node2 = new Node<>(coor2);

    List<Integer> coor3 = new ArrayList<>();
    coor3.add(4);
    coor3.add(2);

    Node<Integer> node3 = new Node<>(coor3);

    List<Integer> coor4 = new ArrayList<>();
    coor4.add(5);
    coor4.add(8);

    Node<Integer> node4 = new Node<>(coor4);

    List<Integer> coor5 = new ArrayList<>();
    coor5.add(6);
    coor5.add(9);

    Node<Integer> node5 = new Node<>(coor5);

    List<List<Integer>> listOfCoors = new ArrayList<>();
    listOfCoors.add(coor1);
    listOfCoors.add(coor2);
    listOfCoors.add(coor3);
    listOfCoors.add(coor4);
    listOfCoors.add(coor5);

    KDTree<Integer> testTree = new KDTree<>(listOfCoors);
    PriorityQueue<Node<Integer>> queue = new PriorityQueue<>(Collections.reverseOrder());
    queue.add(node1);
    queue.add(node2);
    queue.add(node3);
    queue.add(node4);
    queue.add(node5);

    node3.setAxis(0);
    node3.setLeftChild(node2);
    node3.setRightChild(node4);
    node3.setParent(null);

    node2.setAxis(1);
    node2.setLeftChild(node1);
    node2.setRightChild(null);
    node2.setParent(node3);

    node1.setAxis(0);
    node1.setLeftChild(null);
    node1.setRightChild(null);
    node1.setParent(node2);

    node4.setAxis(1);
    node4.setLeftChild(null);
    node4.setRightChild(node5);
    node4.setParent(node3);

    node5.setAxis(0);
    node5.setLeftChild(null);
    node5.setRightChild(null);
    node5.setParent(node4);

    testTree.setkNearestNeighbors(queue);
    node1.setAxis(0);
    int result1 = testTree.compareNodeToRadiusOnAxis(node4, coor2);
    assertEquals(result1, 0);

    System.out.println("Expected root: " + node3.getCoordinates());
    System.out.println("Created root: " + testTree.getTree().getCoordinates());
    assertEquals(node3.getCoordinates(), testTree.getTree().getCoordinates());

    List<Integer> testcoords = new ArrayList<>();
    testcoords.add(-1);
    testcoords.add(-1);
    Node result = testTree.basicBSTSearch(testTree.getTree(),testcoords);
    assertEquals(node1.getCoordinates(), result.getCoordinates());
  }


  @Test
  public void testBackTracking() {
    List<List<Integer>> dataset = this.testset1();
    KDTree<Integer> testTree = new KDTree<>(dataset);
    List<Integer> testcoords = new ArrayList<>();
    testcoords.add(-1);
    testcoords.add(-1);
    testTree.KNN(3,testcoords);
    assertEquals(testTree.getkNearestNeighbors().size(), 3);
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(4);
    coor1.add(2);
    assertEquals(testTree.getkNearestNeighbors().peek().getCoordinates(),coor1);
  }
}