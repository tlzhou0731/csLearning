package ztlClass;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Create By Intellij idea
 * Author:Macro
 * Date:2022/1/22
 * Time:17:24
 * Describe:
 */

public class Sort {
    public static void quickSort(int nums[]){
        int n = nums.length;
        int l=0, r=n-1;
        quickSort(nums, l, r);
    }
    private static void quickSort(int nums[], int l, int r){
        if(l>=r) return ;
        int mid = nums[(l+r)>>1], i=l-1, j=r+1;
        while(i<j){
            do i++;while (nums[i]<mid);
            do j--;while (nums[j]>mid);
            if(i<j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        quickSort(nums, l, j);
        quickSort(nums, j+1, r);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int nums[] = new int[N];
        for(int i=0; i<N; i++)
            nums[i] = scanner.nextInt();
        nums = ZTL.distinct(nums);
        Sort.quickSort(nums);
        for(int num:nums)
            System.out.print(num+" ");
    }
}
