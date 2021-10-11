package edu.brown.cs.student.recommender.tables;

import java.util.Map;

public class Negative {
  private int id;
  private String trait;

  public Negative(int id, String trait) {
    this.id = id;
    this.trait = trait;
  }

  public Negative(Map<String, String> mapper) {
    this.id = Integer.parseInt(mapper.get("id"));
    this.trait = mapper.get("trait");
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTrait() {
    return trait;
  }

  public void setTrait(String trait) {
    this.trait = trait;
  }
}
