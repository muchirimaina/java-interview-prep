// remove any uncessary ) or ( | example le(e)t)c(o)d)e expected: le(e)tc(o)de

public class ValidParenthesis{
    
    
    
    
    public static String removeExtraBrackets(String s){
        StringBuilder sb = new StringBuilder();
        int open = 0;
        
        // Two pass: remove extra )
        // count (
        
        
        for(char c: s.toCharArray()){
            if(c == '('){
                open++; 
            }else{
                if(c == ')'){
                    if(open == 0) continue;
                    open--;
                    
                }
            }
            
            sb.append(c);
        }
        
        // remove extra (
        
        StringBuilder result = new StringBuilder();
        for(int i = sb.length()-1; i >= 0; i--){
            char c = sb.charAt(i);
            if(c == '(' && open > 0){
                open--;
                continue;
            }
            
            
            result.append(c);
        }
        
        return result.reverse().toString();
        
        
    }
    
    public static void main(String[] args){
        String s = "le(e)t)c(o)d)e";
        
        String z = removeExtraBrackets(s);
        
        System.out.println("Final string :"+ z);
        
        
    }
    
    
}