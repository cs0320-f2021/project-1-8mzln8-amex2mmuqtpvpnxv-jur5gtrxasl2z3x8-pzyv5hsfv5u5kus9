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
        int id = 0;
        try{
            id = Integer.parseInt(args[2]);
        }catch(Exception ignored){}
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

    /**
     * Runs the command recsys_recs
     */
    public void recsys_recs(){
        int numberOfRecs = 0;
        try {
            numberOfRecs = Integer.parseInt(args[1]);
        }catch(Exception e){
            System.out.println("Please enter an integer for the number of recommendations");
            return;
        }
        if(this.studentOfInterest == null){
            System.out.println("Please input a valid integer for argument 2");
            return;
        }
        List<Integer> recs = this.generateRecommendations(this.studentOfInterest,numberOfRecs);
        for (Integer rec : recs) {
            System.out.println(rec);
        }
    }

    /**
     *
     * @param student - the student we are creating recommendations for
     * @param k - the total number of recommendations
     * @return - a list of recommendations
     */
    private List<Integer> generateRecommendations(Student student , int k) {
        if(student == null) {
            System.out.println("No student with the given id exists in the system. Please try a different id");
            return new ArrayList<>();
        }
        List<Number> targetCoordinates = student.getCoordinates(); //This will become whatever input was used to load the kdtree in the previous part
        List<Student> tempBloomRecs = this.bloom.getTopKRecommendations(student,k);
        List<Node<Number>>  tempKDTreeRecs = this.KDTree.KNNSearch(k,targetCoordinates.subList(1, targetCoordinates.size()));
        Set<Integer> kDTreeRecs = new HashSet<>();
        Set<Integer> bloomRecs = new HashSet<>();
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
        return intersectionOfLists(new ArrayList<>(bloomRecs),new ArrayList<>(kDTreeRecs),k,Integer.parseInt(studentOfInterest.getId()));
    }

    /**
     *
     * @param l1 - a list of integers
     * @param l2 - a list of integers
     * @param k - the total number of recommendations
     * @param idOfInterest - the id of the student we are creating recommendations for
     * @return - a list of recommendations
     */
    private static List<Integer> intersectionOfLists(List<Integer> l1, List<Integer> l2, int k, int idOfInterest){
        Set<Integer> set1 = new HashSet<>(l1);
        Set<Integer> set2 = new HashSet<>(l2);
        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> extras1 = new ArrayList<>();
        List<Integer> extras2 = new ArrayList<>();

        for(Integer num : l2){
            if(set1.contains(num)) {
                map.put(num, 0);
            }else{
                extras1.add(num);
            }
        }
        for(Integer num : l1){
            if(set2.contains(num)) {
                map.put(num, 0);
            }else{
                extras2.add(num);
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
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        for(int id : map.keySet()){
            pq.add(new int[] {id , map.get(id)});
        }
        while(!pq.isEmpty())result.add(pq.poll()[0]);
        int n = result.size();
        int index = 0;
        List<Integer> extras = merge(extras1,extras2);
        int extrasSize = extras.size();
        result.remove(Integer.valueOf(idOfInterest));
        while(n < k && index < extrasSize){
            if(extras.get(index) == idOfInterest) index++;
            if(!result.contains(extras.get(index))){
                result.add(extras.get(index));
                n += 1;
            }
            index++;
        }
        return result;
    }

    /**
     *
     * @param a - a collection of items
     * @param b - a collection of items
     * @param <T> - an arbitrary type of object that is contained within both lists
     * @return - A list that elements taken alternatively from collections a and b
     */
    private static <T> ArrayList<T> merge(Collection<T> a, Collection<T> b) {
        Iterator<T> itA = a.iterator();
        Iterator<T> itB = b.iterator();
        ArrayList<T> result = new ArrayList<T>();

        while (itA.hasNext() || itB.hasNext()) {
            if (itA.hasNext()) result.add(itA.next());
            if (itB.hasNext()) result.add(itB.next());
        }
        return result;
    }
}

