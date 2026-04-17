public class Preparation{
    
    
    String one = "Sleep";
    String two = "Eat";
    String three = "Code";
    
    
    public String statusActivity(int n){
        if(n == 1){
            return one;
        }else if(n == 2){
            return two;
        }else if(n == 3){
           return three; 
        }else{
            return "Not an activity";
        }
        
    }
    
    public String givenTwoActivities(int n, int y){
        if(n == y) return "Invalid input";
        // Since 1+2+3 = 6
        int activity = 6 - (n+y);
        
        return statusActivity(activity);
       
    }
    
    public static void main(String[] args){
        
        Preparation result = new Preparation();
        String answer = result.statusActivity(3);
        
        String answerTwo = result.givenTwoActivities(3,2);

        System.out.println(answer);
        System.out.println(answerTwo);
    }
        
    
    
}
    
