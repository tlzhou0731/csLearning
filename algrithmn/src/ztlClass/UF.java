package ztlClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/1/20
 * Time:18:09
 * Describe:
 */

class QuickFindUF{
    private int[] id;
    public QuickFindUF(int N){
        id = new int[N];
        for(int i=0; i<N; i++)
            id[i] = i;
    }
    void union(int p, int q){
        int pid = id[p], qid = id[q];
        for(int i=0;i<id.length;i++)
            if(id[i]==qid)
                id[i]=pid;
    }
    boolean connected(int p, int q){
        return id[p]==id[q];
    }

}

class QuickUnionUF{
    private int[] id;
    public QuickUnionUF(int N){
        id = new int[N];
        for(int i=0; i<N; i++)
            id[i] = i;
    }
    private int root(int i){
        while(id[i]!=i) i = id[i];
        return i;
    }
    void union(int p, int q){
        int rootp=root(p), rootq=root(q);
        id[rootp] = rootq;
    }
    boolean connected(int p, int q){
        return root(p)==root(q);
    }
}

class QuickUnionImprovementUF{
    private int[] id;
    private int[] size;
    public QuickUnionImprovementUF(int N){
        id = new int[N];
        size = new int[N];
        for(int i=0; i<N; i++) {
            size[i] = 1;
            id[i] = i;
        }
    }
    private int root(int i){
        while(id[i]!=i) i = id[i];
        return i;
    }
    void union(int p, int q){
        int rp=root(p), rq=root(q);
        if(size[rp]>size[rq]) {
            id[rq] = rp;
            size[rp] += size[rq];
        }else{
            id[rp] = rq;
            size[rq] += size[rp];
        }
    }
    boolean connected(int p, int q){
        return root(p)==root(q);
    }
}

class PathCompressionUF{
    private int[] id;
    public PathCompressionUF(int N){
        id = new int[N];
        for(int i=0; i<N; i++) {
            id[i] = i;
        }
    }
    private int root(int i){
        while(id[i]!=i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    void union(int p, int q){
        id[root(p)] = root(q);
    }
    boolean connected(int p, int q){
        return root(p)==root(q);
    }
}


public class UF {
    private int[] id;
    UF(int N){
        id = new int[N];
        for(int i=0; i<N; i++)
            id[i] = i;
    }
    void union(int p, int q){

    }
    boolean connected(int p, int q){

        return false;
    }
    int solution(int nums[]){
        if(nums.length<2) return nums.length;
        Map<Integer,Integer> mp = new HashMap<Integer,Integer>();
        Map<Integer,Integer> size = new HashMap<Integer,Integer>();
        int len = nums.length, max=1;
        for(int num:nums){
            mp.put(num,num+1);
            size.put(num,0);
        }
        for(int num:nums){
            int count=0, tmp=num;
            while (mp.containsKey(tmp)){
                if(size.containsKey(tmp+1)&&size.get(tmp+1)!=0){
                    count += size.get(tmp+1);
                    size.put(num,count);
                    break;
                }
                count++;
                tmp++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        UF uf = new UF(N);
        for(int i=0; i<N; i++){
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            if(!uf.connected(p, q)){
                uf.union(p, q);
                System.out.println(p+" "+q);
            }
        }
    }
}
