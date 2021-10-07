package edu.brown.cs.student.main;

import edu.brown.cs.student.stars.Star;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StarTest {

  @Test
  public void testSetDistance() {
    Star star01 = new Star(0, "Test Star", 1, 2, 3);
    star01.setDistanceToStar(0, 0,0);
    float distance01 = star01.getDistance();
    assertEquals(3.74,distance01,0.01);

    Star star02 = new Star("0", "Test Star", "0", "0", "0");
    star02.setDistanceToStar(0, 0,0);
    float distance02 = star02.getDistance();
    assertEquals(0,distance02,0.01);
  }
}
