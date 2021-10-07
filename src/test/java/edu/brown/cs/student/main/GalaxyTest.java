package edu.brown.cs.student.main;

import edu.brown.cs.student.stars.Galaxy;
import edu.brown.cs.student.stars.Star;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GalaxyTest {

  @Test
  public void testGetNearestKNeighbors() {
    Star star01 = new Star(0, "Test Star 1", 1, 2, 3);
    Star star02 = new Star("1", "Test Star 2", "4", "5", "6");
    ArrayList<Star> starList = new ArrayList<Star>();
    starList.add(star01);
    starList.add(star02);
    Galaxy galaxy = new Galaxy(starList);
    ArrayList<Integer> foundStarsOne = galaxy.getNearestKNeighbors("1", "0", "0", "0");
    ArrayList<Integer> expectedStarsOne = new ArrayList<Integer>();
    expectedStarsOne.add(0);

    ArrayList<Integer> foundStarsTwo = galaxy.getNearestKNeighbors("2", "0", "0", "0");
    ArrayList<Integer> expectedStarsTwo = new ArrayList<Integer>();
    expectedStarsTwo.add(0);
    expectedStarsTwo.add(1);

    assertEquals(expectedStarsOne, foundStarsOne);
    assertEquals(expectedStarsTwo, foundStarsTwo);
  }

  @Test
  public void testGetNearestKNeighborsWithName() {
    Star star01 = new Star(0, "Test Star 1", 1, 2, 3);
    Star star02 = new Star(1, "Test Star 2", 4, 5, 6);
    ArrayList<Star> starList = new ArrayList<Star>();
    starList.add(star01);
    starList.add(star02);
    Galaxy galaxy = new Galaxy(starList);

    ArrayList<Integer> foundStarsOne =
        galaxy.getNearestKNeighborsWithName("1", "Test Star 1");
    ArrayList<Integer> expectedStarsOne = new ArrayList<Integer>();
    expectedStarsOne.add(1);

    ArrayList<Integer> foundStarsTwo =
        galaxy.getNearestKNeighborsWithName("2", "Test Star 1");
    ArrayList<Integer> expectedStarsTwo = new ArrayList<Integer>();
    expectedStarsTwo.add(1);

    assertEquals(expectedStarsOne, foundStarsOne);
    assertEquals(expectedStarsTwo, foundStarsTwo);
  }

  @Test
  public void testGetNearestKNeighborsWithWrongName() {
    Star star01 = new Star(0, "Test Star 1", 1, 2, 3);
    Star star02 = new Star(1, "Test Star 2", 4, 5, 6);
    ArrayList<Star> starList = new ArrayList<Star>();
    starList.add(star01);
    starList.add(star02);
    Galaxy galaxy = new Galaxy(starList);

    assertThrows(NullPointerException.class,
        () -> {galaxy.getNearestKNeighborsWithName("1", "Test");});
  }
}
