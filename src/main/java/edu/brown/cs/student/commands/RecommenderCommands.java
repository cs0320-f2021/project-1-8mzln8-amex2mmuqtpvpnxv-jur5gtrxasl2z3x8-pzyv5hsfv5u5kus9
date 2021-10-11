package edu.brown.cs.student.commands;

import com.google.gson.Gson;
import edu.brown.cs.student.bloomfilter.*;
import edu.brown.cs.student.kdtree.*;
import edu.brown.cs.student.orm.*;
import edu.brown.cs.student.core.*;
import edu.brown.cs.student.recommender.*;
import edu.brown.cs.student.api.ApiAggregator;
import edu.brown.cs.student.recommender.tables.Interests;
import edu.brown.cs.student.recommender.tables.Negative;
import edu.brown.cs.student.recommender.tables.Positive;
import edu.brown.cs.student.recommender.tables.Skills;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RecommenderCommands implements REPLCommand {
  private List<Student> studentList;
  private KDTree kdTree;
  private BloomFilter bloomFilter;


  private List<Student> AggregateData(List<APIData> apiDataList, List<Interests> interestsList,
                                      List<Negative> negativeList, List<Positive> positiveList,
                                      List<Skills> skillsList) {
    HashMap<Integer, Student> map = new HashMap<>();
    for (APIData a:apiDataList) {
      int id = a.getId();
      if (map.get(id) == null) {
        Student s = new Student(id);
        map.put(id, s);
      }
      map.get(id).updateFromAPIData(a);
    }

    for (Interests i:interestsList) {
      int id = i.getId();
      if (map.get(id) == null) {
        Student s = new Student(id);
        map.put(id, s);
      }
      map.get(id).updateFromInterests(i);
    }

    for (Negative n:negativeList) {
      int id = n.getId();
      if (map.get(id) == null) {
        Student s = new Student(id);
        map.put(id, s);
      }
      map.get(id).updateFromNegatives(n);
    }

    for (Positive p:positiveList) {
      int id = p.getId();
      if (map.get(id) == null) {
        Student s = new Student(id);
        map.put(id,  s);
      }
      map.get(id).updateFromPositive(p);
    }

    for (Skills s:skillsList) {
      int id = s.getId();
      if (map.get(id) == null) {
        Student student = new Student(id);
        map.put(id, student);
      }
      map.get(id).updateFromSkills(s);
    }

    return new ArrayList<>(map.values());
  }

  @Override
  public void handle(String[] args) {
    try {
      if (args[0].equals("recsys_load")) {
        Random r = new Random();
        ApiAggregator api = new ApiAggregator();
        List<APIData> apilist = api.getIntegrationData();
        Gson gson = new Gson();
        // System.out.println(gson.toJson(apilist));

        HashMap<String, String> empty = new HashMap<>();
        Database database = new Database("data/integration/integration.sqlite3");
        List<Interests> interests = database.select(Interests.class,empty);
        List<Negative> negatives = database.select(Negative.class,empty);
        List<Positive> positives = database.select(Positive.class, empty);
        List<Skills> skills = database.select(Skills.class, empty);
//        System.out.println(interests.size());
//        System.out.println(positives.size());
//        System.out.println(negatives.size());
//        System.out.println(skills.size());

        List<Student> studentList = AggregateData(apilist, interests, negatives, positives, skills);
        this.studentList = studentList;
        List<List<Number>> kdData = new ArrayList<>();
        List<List<String>> bloomData = new ArrayList<>();
        for (Student s:studentList) {
          kdData.add(s.getCoordinates());
          bloomData.add(s.getVectorRepresentation());
        }

        double c = r.nextInt(200) + 1;
        int n = r.nextInt(61) + 1;
        int k = r.nextInt(20) + 1;
        this.kdTree = new KDTree(kdData);
        this.bloomFilter = new BloomFilter(c,n,k);
        this.bloomFilter.addAll(bloomData);

        System.out.println("Loaded Recommender with " + studentList.size() + " students");


      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR: Something wrong happened when ingesting data");
    }
  }
}
