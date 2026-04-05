public class Stack{
    
    static class StackNode{
        int val;
        StackNode next;
        
        StackNode(int val){
            this.val = val;                          
            this.next = null;
        }
    }
    
    StackNode top;
    
    public void push(int val){
        StackNode node = new StackNode(val);
        node.next = top;
        top = node;
    }
    
    public int pop(){
        if(isEmpty()) return -1;
        
        int val = top.val;
        top = top.next;
        return val;
        
    }
    
    public int peek(){
        if(isEmpty()) return -1;
        return top.val;
    }
    
    public boolean isEmpty(){
        return top == null;
    }
    
    public static void main(String[] args){
        Stack myStack = new Stack();
        myStack.push(5);
        System.out.println("Pop : "+ myStack.pop());
        myStack.push(9);
        System.out.println("Peek : "+ myStack.peek());
        myStack.pop();
        System.out.println("Peek : "+ myStack.peek());
    }
    
    
    
    
}