package edu.brown.cs.student.recommender;


import java.util.List;

public class APIData {
  private int id;
  private String name;
  private String meeting;
  private String grade;
  private int exp;
  private String horoscope;
  private String meeting_times;
  private String preferred_language;
  private String marginalized_groups;
  private String prefer_group;


  public APIData(String id, String name, String meeting, String grade, String exp,
                 String horoscope, String meeting_times, String preferred_language,
                 String marginalized_groups, String prefer_group) {
    this.id = Integer.parseInt(id);
    this.name = name;
    this.meeting = meeting;
    this.grade = grade;
    this.exp = Integer.parseInt(exp);
    this.horoscope = horoscope;
    this.meeting_times = meeting_times;
    this.preferred_language = preferred_language;
    this.marginalized_groups = marginalized_groups;
    this.prefer_group = prefer_group;
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

  public String getMeeting_times() {
    return meeting_times;
  }

  public void setMeeting_times(String meeting_times) {
    this.meeting_times = meeting_times;
  }

  public String getPreferred_language() {
    return preferred_language;
  }

  public void setPreferred_language(String preferred_language) {
    this.preferred_language = preferred_language;
  }

  public String getMarginalized_groups() {
    return marginalized_groups;
  }

  public void setMarginalized_groups(String marginalized_groups) {
    this.marginalized_groups = marginalized_groups;
  }

  public String getPrefer_group() {
    return prefer_group;
  }

  public void setPrefer_group(String prefer_group) {
    this.prefer_group = prefer_group;
  }
}
