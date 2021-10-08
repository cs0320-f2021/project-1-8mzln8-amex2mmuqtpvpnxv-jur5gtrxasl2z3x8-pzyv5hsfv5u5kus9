package edu.brown.cs.student.main;

import edu.brown.cs.student.kdtree.KDTree;
import edu.brown.cs.student.kdtree.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class KDTreeTest {

  public List<List<Integer>> testDataset1() {
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(0);
    coor1.add(0);
    coor1.add(0);
    List<Integer> coor2 = new ArrayList<>();
    coor2.add(1);
    coor2.add(1);
    coor2.add(2);
    List<Integer> coor3 = new ArrayList<>();
    coor3.add(2);
    coor3.add(4);
    coor3.add(2);
    List<Integer> coor4 = new ArrayList<>();
    coor4.add(3);
    coor4.add(5);
    coor4.add(8);
    List<Integer> coor5 = new ArrayList<>();
    coor5.add(4);
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

  public List<List<Integer>> testDataset2() {
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(0);
    coor1.add(1);
    coor1.add(1);
    List<Integer> coor2 = new ArrayList<>();
    coor2.add(1);
    coor2.add(2);
    coor2.add(2);
    List<Integer> coor3 = new ArrayList<>();
    coor3.add(2);
    coor3.add(3);
    coor3.add(3);
    List<Integer> coor4 = new ArrayList<>();
    coor4.add(3);
    coor4.add(4);
    coor4.add(4);
    List<Integer> coor5 = new ArrayList<>();
    coor5.add(4);
    coor5.add(1);
    coor5.add(1);

    List<List<Integer>> listOfCoors = new ArrayList<>();
    listOfCoors.add(coor1);
    listOfCoors.add(coor2);
    listOfCoors.add(coor3);
    listOfCoors.add(coor4);
    listOfCoors.add(coor5);

    return listOfCoors;
  }

  public Node<Integer> testTree1() {
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
  public void testNodeConstruction() {
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(0);
    coor1.add(1);
    coor1.add(1);
    Node<Integer> node1 = new Node<Integer>(coor1);
    List<Integer> nodeCoords = new ArrayList<>();
    nodeCoords.add(1);
    nodeCoords.add(1);

    assertEquals(nodeCoords, node1.getCoordinates());
  }

  @Test
  public void testKDTreeStructure() {
    KDTree<Integer> testTree = new KDTree<>(this.testDataset2());
    List<Integer> coor2 = new ArrayList<>();
    coor2.add(2);
    coor2.add(2);
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(1);
    coor1.add(1);

    assertEquals(coor2, testTree.getTree().getCoordinates());
    assertEquals(coor1, testTree.getTree().getLeftChild().getCoordinates());
  }

  @Test
  public void testKNNSearchDataset1() {
    KDTree<Integer> testTree = new KDTree<>(this.testDataset2());
    List<Integer> testCoors = new ArrayList<>();
    testCoors.add(0);
    testCoors.add(0);
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(1);
    coor1.add(1);
    List<Integer> coor3 = new ArrayList<>();
    coor3.add(3);
    coor3.add(3);

    List<Node<Integer>> result1 = testTree.KNNSearch(4, testCoors);
    assertEquals(coor1, result1.get(0).getCoordinates());
    assertEquals(coor3, result1.get(3).getCoordinates());

    List<Node<Integer>> result2 = testTree.KNNSearch(0, testCoors);
    assertEquals(0, result2.size());
  }

  @Test
  public void testKNNSearchDataset2() {
    KDTree<Integer> testTree = new KDTree<>(this.testDataset1());
    List<Integer> testCoors = new ArrayList<>();
    testCoors.add(-1);
    testCoors.add(-1);
    List<Integer> coor1 = new ArrayList<>();
    coor1.add(0);
    coor1.add(0);
    List<Integer> coor5 = new ArrayList<>();
    coor5.add(6);
    coor5.add(9);

    List<Node<Integer>> result1 = testTree.KNNSearch(6, testCoors);
    assertEquals(coor1, result1.get(0).getCoordinates());
    assertEquals(coor5, result1.get(4).getCoordinates());
    assertEquals(0, testTree.getKNearestNeighbors().size());
  }

}
