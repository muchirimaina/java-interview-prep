public class LastWord {
    public static int lengthOfLastWord(String s) {
        int length = 0;
        boolean counting = false;

        for(int i = s.length()-1; i >=0; i--){
            if(s.charAt(i) != ' '){
                counting = true;
                length++;
            }else if(counting){
                break;
            }
        }
        return length;    
    }


    public static void main(String [] args){
        String s = "   The quick    brown fox    ";

        System.out.println(lengthOfLastWord(s));
    }
}