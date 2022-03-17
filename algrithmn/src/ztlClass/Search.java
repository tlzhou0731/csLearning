package ztlClass;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/1/21
 * Time:20:07
 * Describe:
 */

public class Search {
    public static int binarySearch(int[] a, int key){
        int l=0, r=a.length-1;
        int mid;
        while(l<=r){
            mid = (l+r)>>1;
            if(key==a[mid]) return mid;
            if(key<a[mid]) r = mid-1;
            else l = mid+1;
        }
        return -1;
    }
    public static void main(String[] args) {

    }
}
