package edu.brown.cs.student.recommender;


import java.util.ArrayList;
import java.util.List;

public class APIData {
  private int id;
  private String name;
  private String meeting;
  private String grade;
  private int exp;
  private String horoscope;

  public APIData(String id, String name, String meeting, String grade, String exp,
                 String horoscope) {
    this.id = Integer.parseInt(id);
    this.name = name;
    this.meeting = meeting;
    this.grade = grade;
    this.exp = Integer.parseInt(exp);
    this.horoscope = horoscope;
  }

  public int getId() {
    return this.id;
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

  public String getMeeting() {
    return meeting;
  }

  public void setMeeting(String meeting) {
    this.meeting = meeting;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public int getExp() {
    return exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }

  public String getHoroscope() {
    return horoscope;
  }

  public void setHoroscope(String horoscope) {
    this.horoscope = horoscope;
  }

}
