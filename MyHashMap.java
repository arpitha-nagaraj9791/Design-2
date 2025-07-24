// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

import java.util.LinkedList;

public class MyHashMap{
    private class Node{
        int key, value;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private int n;
    private int N;
    LinkedList<Node> buckets[];

    public MyHashMap(){
        this.N = 4;
        buckets = new LinkedList[N];

        for(int i = 0; i < N; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    private int hashFunction(int key){
        return Math.abs(key) % N;
    }

    private int searchInLL(int key, int bi){
        LinkedList<Node> ll = buckets[bi];
        for(int i = 0; i < ll.size(); i++){
            if(ll.get(i).key == key){
                return i;
            }
        }
        return -1;
    }

    private void rehash(){
        LinkedList<Node> oldBuckets[] = buckets;
        N = N * 2;
        buckets = new LinkedList[N];

        for(int i = 0; i < N; i++){
            buckets[i] = new LinkedList<>();
        }

        n = 0;
        for(int i = 0; i < oldBuckets.length; i++){
            LinkedList<Node> ll = oldBuckets[i];

            for(int j = 0; j < ll.size(); j++){
                Node node = ll.get(j);
                put(node.key, node.value);
            }
        }
    }

    public void put(int key, int value){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);

        if(di == -1){
            buckets[bi].add(new Node(key, value));
            n++;
        }else{
            Node node = buckets[bi].get(di);
            node.value = value;
        }

        double lambda = (double)n/N;
        if(lambda > 2.0){
            rehash();
        }
    }

    public int get(int key){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);

        if(di == -1){
            return -1;
        }else{
            Node node = buckets[bi].get(di);
            return node.value;
        }
    }

    public void remove(int key){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);

        if(di == -1){
            return;
        }else{
            Node node = buckets[bi].remove(di);
            n--;
        }
    }
}