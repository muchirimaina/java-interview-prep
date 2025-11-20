import java.util.*;

public class Anagram{
    public static boolean isAnagram(String s1, String s2){
        System.out.println("I am here ............");
        if(s1 == null || s2 == null || s1.length() != s2.length()) return false;
        System.out.println("Reached here ............");

        char [] a1 = s1.toLowerCase().toCharArray();
        char [] a2 = s2.toLowerCase().toCharArray();

        Arrays.sort(a1);
        Arrays.sort(a2);

        System.out.println("Final stage ............");


        return Arrays.equals(a1,a2);
    }

    public static void main(String [] args){
        String s1 = "car";
        String s2 = "rac";

        System.out.println(isAnagram(s1,s2));
        System.out.println(isAnagram("Tell","Billy"));

    }
}