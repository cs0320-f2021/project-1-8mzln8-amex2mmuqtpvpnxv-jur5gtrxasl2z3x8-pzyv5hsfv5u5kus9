package edu.brown.cs.student.recommender.tables;

public class Positive {
  private int id;
  private String trait;

  public Positive(int id, String trait) {
    this.id = id;
    this.trait = trait;
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
