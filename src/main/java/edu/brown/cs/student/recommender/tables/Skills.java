package edu.brown.cs.student.recommender.tables;

import java.util.Map;

public class Skills {
  private int id;
  private String name;
  private int commenting;
  private int testing;
  private int OOP;
  private int algorithms;
  private int teamwork;
  private int frontend;

  public Skills(int id, String name, int commenting, int testing, int OOP, int algorithms,
                int teamwork, int frontend) {
    this.id = id;
    this.name = name;
    this.commenting = commenting;
    this.testing = testing;
    this.OOP = OOP;
    this.algorithms = algorithms;
    this.teamwork = teamwork;
    this.frontend = frontend;
  }

  public Skills(Map<String,String> mapper) {
    this.id = Integer.parseInt(mapper.get("id"));
    this.name = mapper.get("name");
    this.commenting = Integer.parseInt(mapper.get("commenting"));
    this.testing = Integer.parseInt(mapper.get("testing"));
    this.OOP = Integer.parseInt(mapper.get("OOP"));
    this.algorithms = Integer.parseInt(mapper.get("algorithms"));
    this.teamwork = Integer.parseInt(mapper.get("teamwork"));
    this.frontend = Integer.parseInt(mapper.get("frontend"));
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCommenting() {
    return commenting;
  }

  public void setCommenting(int commenting) {
    this.commenting = commenting;
  }

  public int getTesting() {
    return testing;
  }

  public void setTesting(int testing) {
    this.testing = testing;
  }

  public int getOOP() {
    return OOP;
  }

  public void setOOP(int OOP) {
    this.OOP = OOP;
  }

  public int getAlgorithms() {
    return algorithms;
  }

  public void setAlgorithms(int algorithms) {
    this.algorithms = algorithms;
  }

  public int getTeamwork() {
    return teamwork;
  }

  public void setTeamwork(int teamwork) {
    this.teamwork = teamwork;
  }

  public int getFrontend() {
    return frontend;
  }

  public void setFrontend(int frontend) {
    this.frontend = frontend;
  }
}
