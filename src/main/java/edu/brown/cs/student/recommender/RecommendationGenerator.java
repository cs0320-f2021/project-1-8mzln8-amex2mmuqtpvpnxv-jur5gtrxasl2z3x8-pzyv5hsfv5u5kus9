package edu.brown.cs.student.recommender;
import com.google.common.base.Functions;
import com.google.common.collect.Ordering;
import edu.brown.cs.student.bloomfilter.BloomFilter;
import edu.brown.cs.student.bloomfilter.BloomFilterRecommender;
import edu.brown.cs.student.commands.RunwayCommands;
import edu.brown.cs.student.kdtree.KDTree;
import edu.brown.cs.student.kdtree.Node;
import java.util.Map;
import java.util.Map.Entry;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationGenerator<E,T extends Number> implements{
    String[] args;
    BloomFilterRecommender<E> bloom;
    KDTree<T> kdtree;
    public RecommendationGenerator(String args[]){
        this.args = args;
    }
    public void recsys_rc(){
        int numberOfRecs = Integer.parseInt(args[1]);
        int id = Integer.parseInt(args[2]);
        List<Integer> recs = this.generateRecommendations(numberOfRecs,id);
        for (Integer rec : recs) {
            System.out.println(rec);
        }
    }

    private List<Integer> generateRecommendations( E item , int k){
        List<T> targetCoordinates = new ArrayList<>(); //This will become whatever input was used to load the kdtree in the previous part
        List<E> tempBloomRecs = this.bloom.getTopKRecommendations(item,k);
        List<Node<T>>  tempKDTreeRecs = this.kdtree.KNNSearch(k+1,targetCoordinates);
        List<Integer> kDTreeRecs = new ArrayList<>();
        List<Integer> bloomRecs = new ArrayList<>();
        for(Node<T> node : tempKDTreeRecs){
            kDTreeRecs.add(node.getUniqueID());
        }
        for(E content : tempBloomRecs){
            int id = 1;//Insert content.getUniqueID();
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
