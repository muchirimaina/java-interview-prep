// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        int[][] arr = new int[3][3];

//         int[][] arr = {
//     {1,2,3},
//     {4,5},
//     {7,8,9,5}
// };

        
        
        System.out.println("Enter 9 numbers:");
        

         for(int r = 0; r < arr.length; r++){
            for(int c =0; c < arr[r].length; c++){
                 arr[r][c] = in.nextInt();

            }
        }

        in.close();
        
        for(int[]a: arr){
           System.out.println(Arrays.toString(a));
        }
        
    }
}