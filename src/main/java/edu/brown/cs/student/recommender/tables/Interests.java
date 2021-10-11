package edu.brown.cs.student.recommender.tables;

import java.util.Map;

public class Interests {
  private int id;
  private String interest;

  public Interests(int id, String interest) {
    this.id = id;
    this.interest = interest;
  }

  public Interests(Map<String, String> mapper) {
    this.id = Integer.parseInt(mapper.get("id"));
    this.interest = mapper.get("interest");
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getInterest() {
    return interest;
  }

  public void setInterest(String interest) {
    this.interest = interest;
  }
}