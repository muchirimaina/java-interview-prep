import java.util.*;

public class Unique{
    public static String firstUniqueProduct(String [] products){

        if(products == null || products.length == 0) return null;

        Map<String, Integer> countMap = new LinkedHashMap<>();

        for(String product:products){
            countMap.put(product, countMap.getOrDefault(product,0)+1);
        }

        for(Map.Entry<String,Integer> entry: countMap.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
            
        }

        return null;
    }

    public static void main (String [] args){
        String [] input = {"Chocolate", "Brown Sugar", "Chocolate"};
        System.out.println(firstUniqueProduct(input));
    }
}