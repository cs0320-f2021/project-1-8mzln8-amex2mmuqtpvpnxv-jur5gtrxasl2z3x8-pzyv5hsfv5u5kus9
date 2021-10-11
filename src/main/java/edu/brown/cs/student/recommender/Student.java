package edu.brown.cs.student.recommender;

import edu.brown.cs.student.recommender.tables.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Student implements Item {
  private int id;
  private String name;
  private List<String> interests = new ArrayList<>();
  private List<String> negatives = new ArrayList<>();
  private List<String> positives = new ArrayList<>();
  private int commenting;
  private int testing;
  private int OOP;
  private int algorithms;
  private int teamwork;
  private int frontend;
  private String meeting;
  private String grade;
  private int exp;
  private String horoscope;
  private List<String> availability = new ArrayList<>();
  private String language;
  private List<String> marginalized = new ArrayList<>();
  private String preference;

  public Student(int id) {
    this.id = id;
  }

  @Override
  public List<String> getVectorRepresentation() {
    List<String> v = new ArrayList<>();
    v.add(this.getId());

    if (!this.interests.isEmpty()) {
      v.addAll(this.interests);
    }
    if (!this.negatives.isEmpty()) {
      v.addAll(this.negatives);
    }
    if (!this.positives.isEmpty()) {
      v.addAll(this.positives);
    }


    if (!this.meeting.isBlank()) {
      v.add(this.meeting);
    }
    if (!this.grade.isBlank()) {
      v.add(this.grade);
    }
    if(!this.horoscope.isBlank()) {
      v.add(this.horoscope);
    }
    if (!this.availability.isEmpty()) {
      v.addAll(this.availability);
    }
    if (!this.language.isBlank()) {
      v.add(this.language);
    }
    if (!this.marginalized.isEmpty()) {
      v.addAll(this.marginalized);
    }
    v.add(this.preference);
    System.out.println(v);
    return v;
  }

  public List<Number> getCoordinates() {
    List<Number> c = new ArrayList<>();
    c.add(this.commenting);
    c.add(this.testing);
    c.add(this.OOP);
    c.add(this.algorithms);
    c.add(this.teamwork);
    c.add(this.frontend);
    c.add(this.exp);
    return c;
  }

  @Override
  public String getId() {
    return null;
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

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getPreference() {
    return preference;
  }

  public void setPreference(String preference) {
    this.preference = preference;
  }

  public List<String> getInterests() {
    return interests;
  }

  public void insertInterests(String interest) {
    this.interests.add(interest);
  }

  public List<String> getNegatives() {
    return negatives;
  }

  public void insertNegative(String negative) {
    this.negatives.add(negative);
  }

  public List<String> getPositives() {
    return positives;
  }

  public void insertPositives(String positive) {
    this.positives.add(positive);
  }

  public List<String> getAvailability() {
    return availability;
  }

  public void setAvailability(List<String> availability) {
    this.availability = availability;
  }

  public List<String> getMarginalized() {
    return marginalized;
  }

  public void setMarginalized(List<String> marginalized) {
    this.marginalized = marginalized;
  }

  public void updateFromInterests(Interests i) {
    if (i.getId() == this.id) {
      this.insertInterests(i.getInterest());
    }
  }

  public void updateFromNegatives(Negative n) {
    if (n.getId() == this.id) {
      this.insertNegative(n.getTrait());
    }
  }

  public void updateFromPositive(Positive p) {
    if (p.getId() == this.id) {
      this.insertNegative(p.getTrait());
    }
  }

  public void updateFromSkills(Skills s) {
    if (s.getId() == this.id) {
      this.commenting = s.getCommenting();
      this.testing = s.getTesting();
      this.OOP = s.getOOP();
      this.algorithms = s.getAlgorithms();
      this.teamwork = s.getTeamwork();
      this.frontend = s.getFrontend();
    }
  }

  public void updateFromAPIData(APIData a) {
    if (a.getId() == this.id) {
      this.meeting = a.getMeeting();
      this.grade = a.getGrade();
      this.exp = a.getExp();
      this.horoscope = a.getHoroscope();
      this.availability = a.getAvailability();
      System.out.println(this.availability);
      this.language = a.getLanguage();
      this.marginalized = a.getMarginalized();
      this.preference = a.getPreference();
    }
  }

}
