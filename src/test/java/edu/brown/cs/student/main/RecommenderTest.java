package edu.brown.cs.student.main;

import edu.brown.cs.student.commands.RecommenderCommands;
import edu.brown.cs.student.recommender.Recommender;
import org.junit.Test;

import java.util.List;

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
  
  @Test
  public void testCorrectIngestion() {
    RecommenderCommands recommenderCommands = new RecommenderCommands();
    String args[] = new String[2];
    args[0] = "recsys_load";
    args[1] = "responses";
    recommenderCommands.handle(args);
    for (int i = 1; i < 62; i++) {
      assertEquals(true, recommenderCommands.getBloomFilterHashMap().containsKey(String.valueOf(i)));
    }
  }

  @Test
  public void testStudentClassCreation() {
    RecommenderCommands recommenderCommands = new RecommenderCommands();
    String args[] = new String[2];
    args[0] = "recsys_load";
    args[1] = "responses";
    recommenderCommands.handle(args);
    assertEquals(8, recommenderCommands.getStudentList().get(0).getCoordinates().size());
    for (Number n:recommenderCommands.getStudentList().get(0).getCoordinates()) {
      assertEquals(true, !(n == null));
    }
  }
}
