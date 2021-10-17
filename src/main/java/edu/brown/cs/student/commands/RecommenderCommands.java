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
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RecommenderCommands implements REPLCommand {
  private List<Student> studentList;
  private KDTree kdTree;
  private BloomFilter bloomFilter;
  private List<List<Integer>> groups;


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

    return new ArrayList<> (map.values());
  }




  @Override
  public void handle(String[] args) {

    try {
      switch (args[0]) {
        case "recsys_load":
          Random r = new Random();
          ApiAggregator api = new ApiAggregator();
          List<APIData> apilist = api.getIntegrationData();
          Gson gson = new Gson();
          System.out.println(gson.toJson(apilist));

          HashMap<String, String> empty = new HashMap<>();
          Database database = new Database("data/integration/integration.sqlite3");
          List<Interests> interests = database.select(Interests.class, empty);
          List<Negative> negatives = database.select(Negative.class, empty);
          List<Positive> positives = database.select(Positive.class, empty);
          List<Skills> skills = database.select(Skills.class, empty);
//        System.out.println(interests.size());
//        System.out.println(positives.size());
//        System.out.println(negatives.size());
//        System.out.println(skills.size());

          List<Student> studentList =
              AggregateData(apilist, interests, negatives, positives, skills);
          this.studentList = studentList;

          List<List<Number>> kdData = new ArrayList<>();
          List<List<String>> bloomData = new ArrayList<>();

          for (Student s : studentList) {
            kdData.add(s.getCoordinates());
            bloomData.add(s.getVectorRepresentation());
          }

          double c = r.nextInt(200) + 1;
          int n = r.nextInt(61) + 1;
          int k = r.nextInt(20) + 1;
          this.kdTree = new KDTree(kdData);
          this.bloomFilter = new BloomFilter(c, n, k);
          this.bloomFilter.addAll(bloomData);

          System.out.println("Loaded Recommender with " + studentList.size() + " students");

          break;
        case "recsys_recs":

          break;
        case "recsys_gen_groups":
          if ((this.kdTree == null) || (this.bloomFilter == null)) {
            System.out.println("ERROR: Please run recys_load to load your data");
            return;
          }
          if ((this.studentList == null)) {
            System.out.println("ERROR: StudentList is empty! Please select different dataset");
            return;
          }

          int numGroups = Integer.parseInt(args[1]);

          int groupSize;
          int numLargerGroups = 0;

          if (this.studentList.size() % numGroups != 0) {
            groupSize = this.studentList.size() / numGroups;
            numLargerGroups = this.studentList.size() - groupSize * numGroups;
          } else {
            groupSize = this.studentList.size() / numGroups;
          }

          if ((numGroups <= 0) || (numGroups > this.studentList.size())) {
            System.out.println("ERROR: Invalid number of students");
            return;
          }

          List<Student> studentListCopy = new ArrayList<Student>();
          studentListCopy.addAll(this.studentList);

          for (Student s : studentListCopy) {
            List<Node<Double>> partners;
            List<Integer> partnerIDs = new ArrayList<>();
            while (numLargerGroups > 0) {
              partners = this.kdTree.KNNSearch(groupSize + 1, s.getInvertedCoordinates());
              for (Node<Double> node : partners) {
                partnerIDs.add(node.getUniqueID());
              }
              groups.add(partnerIDs);

            }
            partners = this.kdTree.KNNSearch(groupSize, s.getInvertedCoordinates());
            for (Node<Double> node : partners) {
              partnerIDs.add(node.getUniqueID());
            }
            groups.add(partnerIDs);
          }


          /*
          int iterator = 0;
          for (int i = 0; i < numGroups;
               i++) { //added first n number of students to a different team
            groups.get(iterator).add((Student) studentListCopy.get(i)); //add students
            studentListCopy.remove(studentListCopy.get(i)); //remove student from copy
            iterator++; //iterate through to next Group
          }

          Map<Student, List<Student>> studentPreferences = new HashMap<Student, List<Student>>();

          for (Student s : studentListCopy) {
            //TODO: implement getpreferenceOrder
            List<Student> preferenceOrder = getPreferenceOrder(s);


            studentPreferences.put(s, preferenceOrder);
            addStudent(s, preferenceOrder, numGroups, groups, studentListCopy,
                groupSize); //see bottom of class for method

          }

          for (Student s : studentListCopy) { //remaining students added randomly since groups are full
            int groupRand = ThreadLocalRandom.current().nextInt(0, numGroups - 1);
            groups.get(groupRand).add(s); //add student to group
            studentListCopy.remove(s); //remove student from copy
          }

          for (List<Student> Group : groups) {
            System.out.println(Group);
          }

           */

          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR: Something wrong happened when ingesting data");
    }
  }

  /**
   * Helper Method that adds a Student to a group based on their most preferred destination
   * @param s -- The student that we want to add
   * @param preferenceOrder -- List containing student s's preferences in descending order
   * @param numGroups -- Total number of Groups
   * @param groups -- List containing list of student groups
   * @param studentListCopy -- a copy of the list of students
   * @param GroupSize -- the maximum size a group can be
   */
  private  void addStudent(Student s, List<Student> preferenceOrder,
                           int numGroups, ArrayList<ArrayList<Student>> groups,
                           List<Student> studentListCopy, int GroupSize){
    for(int n = 0; n < numGroups; n++) { //loop through groups for each student
      int preferenceRank = 0;
      List<Student> g = groups.get(n);
      Student mostPreferredStudent = preferenceOrder.get(preferenceRank);

      if(g.contains(mostPreferredStudent)) { //check if it matches choice
        if(groups.get(n).size() < GroupSize) { //check if there's space
          groups.get(n).add(s); //add student to group
          studentListCopy.remove(s); //remove student from copy
          return;
        } else {
          preferenceRank++; //move on to the next choice
        }
      }
    }
  }

  private List<Student> getPreferenceOrder(Student s){
    List<Student> preferenceOrder = new ArrayList<Student>();
    //TODO:calculate preference order for each student by scoring KD tree and Bloom filter totals for each student
    //TA said you can find KD Tree ranking and Bloom Filter ranking and then make one that's combination of both
    //I was also thinking using one of the kd and bloom filter randomly just because of time constraint if this is
    // really difficult



    return preferenceOrder;
  }






}
