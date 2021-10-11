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
  private List<String> availability = new ArrayList<>();
  private String language;
  private List<String> marginalized = new ArrayList<>();
  private String preference;

  public APIData(String id, String name, String meeting, String grade, String exp,
                 String horoscope, List<String> availability, String language,
                 List<String> marginalized, String preference) {
    this.id = Integer.parseInt(id);
    this.name = name;
    this.meeting = meeting;
    this.grade = grade;
    this.exp = Integer.parseInt(exp);
    this.horoscope = horoscope;
    this.availability = availability;
    this.language = language;
    this.marginalized = marginalized;
    this.preference = preference;
  }

  public String getId() {
    return String.valueOf(this.id);
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

  public List<String> getAvailability() {
    return availability;
  }

  public void setAvailability(List<String> availability) {
    this.availability = availability;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public List<String> getMarginalized() {
    return marginalized;
  }

  public void setMarginalized(List<String> marginalized) {
    this.marginalized = marginalized;
  }

  public String getPreference() {
    return preference;
  }

  public void setPreference(String preference) {
    this.preference = preference;
  }
}
