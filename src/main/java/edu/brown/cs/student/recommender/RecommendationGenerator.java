package edu.brown.cs.student.recommender;
import edu.brown.cs.student.bloomfilter.AndSimilarityComparator;
import edu.brown.cs.student.bloomfilter.BloomFilter;
import edu.brown.cs.student.bloomfilter.BloomFilterRecommender;
import edu.brown.cs.student.kdtree.KDTree;
import edu.brown.cs.student.kdtree.Node;
import java.util.Map;

import java.util.*;

public class RecommendationGenerator <T extends Number> {
    String[] args;
    BloomFilterRecommender<Student> bloom;
    KDTree KDTree;
    List<Student> studentList;
    Student studentOfInterest;
    public RecommendationGenerator(String[] args, List<Student> studentList,KDTree KDTree, HashMap<String,Student> idToStudent, HashMap<String,BloomFilter> idToBloom){
        this.args = args;
        this.studentList = studentList;
        this.KDTree = KDTree;
        int id = Integer.parseInt(args[2]);
        BloomFilterRecommender bloomFilterRecommender = new BloomFilterRecommender(idToStudent,.02);
        BloomFilter filterOfInterest = idToBloom.get(args[2]);
        AndSimilarityComparator comparator = new AndSimilarityComparator(filterOfInterest);
        bloomFilterRecommender.setBloomFilterComparator(comparator);
        this.bloom = bloomFilterRecommender;
        this.studentOfInterest = null;
        for(Student stud : studentList){
            if(Integer.parseInt(stud.getId()) == id){
                this.studentOfInterest = stud;
                break;
            }
        }
    }

    public void recsys_recs(){
        int numberOfRecs = Integer.parseInt(args[1]);
        List<Integer> recs = this.generateRecommendations(this.studentOfInterest,numberOfRecs);
        for (Integer rec : recs) {
            System.out.println(rec);
        }
    }

    private List<Integer> generateRecommendations(Student student , int k){
        if(student == null) System.out.println("Student not found");
        List<Number> targetCoordinates = student.getCoordinates(); //This will become whatever input was used to load the kdtree in the previous part
        List<Student> tempBloomRecs = this.bloom.getTopKRecommendations(student,k);
        List<Node<Number>>  tempKDTreeRecs = this.KDTree.KNNSearch(k+1,targetCoordinates);
        List<Integer> kDTreeRecs = new ArrayList<>();
        List<Integer> bloomRecs = new ArrayList<>();
        for(Node<Number> node : tempKDTreeRecs){
            kDTreeRecs.add(node.getUniqueID());
        }
        for(Student content : tempBloomRecs){
            int id = Integer.parseInt(content.getId());
            bloomRecs.add(id);
        }
        //Now that we have recs from both types, get the ids of people recommended by both
        //Then insert them into the result by highest average placement order.
        //If we need more recommendations use recommendations from the kDTree
        return intersectionOfLists(bloomRecs,kDTreeRecs,k);
    }
    private List<Integer> intersectionOfLists(List<Integer> l1, List<Integer> l2, int k){
        Set<Integer> set = new HashSet<>(l1);
        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> extras = new ArrayList<>();

        for(Integer num : l2){
            if(set.contains(num)) {
                map.put(num, 0);
            }else{
                extras.add(num);
            }
        }
        for(int i = 0; i < l1.size(); i++){
            int num = l1.get(i);
            if(map.containsKey(num)){
                map.put(num,map.get(num)+i+1);
            }
        }
        for(int i = 0; i < l2.size(); i++){
            int num = l2.get(i);
            if(map.containsKey(num)){
                map.put(num,map.get(num)+i+1);
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((n1, n2) -> n1[1] - n2[1]);
        for(int id : map.keySet()){
            pq.add(new int[] {id , map.get(id)});
        }
        while(!pq.isEmpty())result.add(pq.poll()[0]);
        int size = result.size();
        int index = 0;
        int extrasSize = extras.size();
        while(size < k && index < extrasSize){
            result.add(extras.get(index));
            index++;
        }
        return result;
    }
}
