import java.util.*;

public class Unique{
    public static int firstUniqChar(String s){
        int [] count = new int[26];
        for(char c: s.toCharArray()){
            c = Character.toLowerCase(c);
             count[c - 'a']++;
             }
        for (int i = 0; i< s.length(); i++){
            char c = Character.toLowerCase(s.charAt(i));
            if(count[c - 'a'] == 1) return i;
        }
        return -1;
    }


    public static void main(String [] args){
        String word = "Hhurraay";

        System.out.println(firstUniqChar(word));

    }
}