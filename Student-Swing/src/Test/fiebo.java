package Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class fiebo {
    public static int func(int n){
        if (n==1||n==2){
            return 1;
        }
        else {
            return func(n-1)+func(n-2);
        }
    }
    public static int diedai(int n){
        int n1 = 1;
        int n2 = 1;
        int sum = 0;
        for(int i = 2;i<n;i++){
            sum = n2;
            n2 = sum+n1;
            n1 = sum;
        }
        return n2;
    }

    public static int[] QuickSort(int []A,int l,int h){

            int temp = A[l];
            if (l >= h){
                return A;
            }
            while (l < h){
              while (l < h && A[h] > temp){
                  h--;
              }
              if (l < h){
                  A[l++] = A[h];
              }
              while (l < h && A[l] < temp){
                  l++;
              }
              if (l < h){
                  A[h--] = A[l];
              }
            }
            A[l] = temp;
            QuickSort(A,0,l-1);
            QuickSort(A,l+1,h);
        return A;
    }

    public static void main(String[] args) {
        int A[] = new int[]{6,4,1,3,9,7,6,5};
        int[] ints = QuickSort(A,0,7);
        System.out.println(Arrays.toString(ints));
    }
}


