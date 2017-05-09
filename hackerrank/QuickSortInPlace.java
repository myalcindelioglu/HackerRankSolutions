package com.ttech.training.hackerrank;

import java.util.*;

public class QuickSortInPlace {
    
    static void swap(int[] ar, int ind1, int ind2) {
        int temp = ar[ind1];
        ar[ind1] = ar[ind2];
        ar[ind2] = temp;
    }
    
    static void quicksort(int[] ar, int lo, int hi) {
        int p;
        if (lo < hi) {
            p = partition(ar, lo, hi);
            quicksort(ar, lo, p - 1);
            quicksort(ar, p + 1, hi);
        }
    }
    
    static int partition(int[] ar, int lo, int hi) {
        int pivot = ar[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (ar[j] < pivot){
                i++;
                swap(ar, i, j);
            }
            
        }
        swap(ar, i+1, hi);
        printAr(ar);
        return i+1;
    }
    
    static void printAr(int[] ar) {
        for (int i : ar) {
            System.out.print(i+" ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ar[] = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = in.nextInt();
        }
        in.close();
        quicksort(ar, 0, n-1);
        
    }
}
