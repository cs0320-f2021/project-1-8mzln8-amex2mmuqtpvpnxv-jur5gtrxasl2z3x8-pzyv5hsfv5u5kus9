package edu.brown.cs.student.main;

import edu.brown.cs.student.commands.RecommenderCommands;
import edu.brown.cs.student.recommender.Recommender;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecommenderTest {

  @Test
  public void testStudentSize() {
    RecommenderCommands recommenderCommands = new RecommenderCommands();
    String args[] = new String[2];
    args[0] = "recsys_load";
    args[1] = "responses";
    recommenderCommands.handle(args);
    assertEquals(61, recommenderCommands.getStudentList().size());
  }

  @Test
  public void testHashMapSize() {
    RecommenderCommands recommenderCommands = new RecommenderCommands();
    String args[] = new String[2];
    args[0] = "recsys_load";
    args[1] = "responses";
    recommenderCommands.handle(args);
    assertEquals(61, recommenderCommands.getBloomFilterHashMap().size());
  }
}
