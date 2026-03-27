// Valid parenthesis using a stack

import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

public class Main{
    
    public static String removeInvalidBrackets(String word){
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            
            if(c == '('){
                stack.push(i);
            }else if(c == ')'){
               if(!stack.isEmpty() && word.charAt(stack.peek()) == '('){
                   stack.pop();
               }else{
                   stack.push(i); // invalid ')'
               }
            }
        }
        
        Set<Integer> invalid = new HashSet<>(stack);
        
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!invalid.contains(i)){
                sb.append(c);
            }
        }
        
        return sb.toString();
        
    }
    
    
    
	public static void main(String[] args) {
	    
	    
	    String word =  "le(e)t)c(o)d)e";
	    
	    String result = removeInvalidBrackets(word);
	    
	    System.out.println("The result :"+ result);
	    

	   
	    
	    
		
	}
}