
public class ListNode{
    int val;
    ListNode next;
    
    ListNode(int val){
        this.val = val;
        this.next = null;
    }
    
    
    public static boolean isPalindrome(ListNode head){
      ListNode slow = head;
      ListNode fast = head;
      
      while(fast != null && fast.next != null){
          slow = slow.next;
          fast = fast.next.next;
      }
      
      
      ListNode prev = null;
      ListNode current = slow;
      
      while(current != null){
          ListNode next = current.next;
          current.next = prev;
          prev = current;
          current = next;
      }
      
      current = prev;
      ListNode original = head;
      
      while(current != null){
          if(current.val != original.val){
              System.out.print("Is palidrome No");
              return false;
          }

          current = current.next;
          original = original.next;
      }
      
      
      
      System.out.print("Is palidrome Yes");
      
      return true;
    }
    
    
    
    
    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        
        node1.next = node2;
        node2.next = node3;
  
        
        ListNode head = node1;
        
      


        isPalindrome(head);
        
    }
    
}