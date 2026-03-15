import java.util.*;

public class TwoSumCool{

        public static String wordFBI(String word){

            StringBuilder sb = new StringBuilder(word);
            sb.append(" A  ge   n     t");
            String vila = sb.toString();
            String test7 = vila.replaceAll("\\s+","*");
            String test = test7.substring(1,6);
            StringBuilder test2 = new StringBuilder(test);
            String test3 = test2.insert(2,"What do you want?").toString(); 

            for(int i = 0; i < 5; i++){
                System.out.println(test3);
            }
            return "";
    }
    public static void main(String[] args){
        String word = "F    B   I";
        System.out.println(wordFBI(word));        
    }


}
