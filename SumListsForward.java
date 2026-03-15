// Find the len of the two Lists
// Pad the shorter with Zeros
// Add the lists recursively - use a helper function to add the lists

public class ListNode{
    int val;
    ListNode next;
    
    ListNode(int val){
        this.val = val;
        this.next = null;
    }
    
    
    static class PartialSum{
        int carry;
        ListNode node;
    }
    
    
    public static ListNode sumLists(ListNode l1, ListNode l2){
        
        // Padding the shorter List
        
        ListNode current1 = l1;
        ListNode current2 = l2;
        
        int len1 = 0;
        int len2 = 0;
        
        while(current1 != null){
            len1++;
            current1 = current1.next;
        }
        
        while(current2 != null){
            len2++;
            current2 = current2.next;
        }
        
        current1 = l1;
        current2 = l2;
        
        int diff = Math.abs(len2 - len1);
        
        if(len1 > len2){
            for(int i = 0; i < diff; i++){
                ListNode pad = new ListNode(0);
                pad.next = current2;
                current2 = pad;
            }
        }else{
            for(int i = 0; i < diff; i++){
                ListNode pad = new ListNode(0);
                pad.next = current1;
                current1 = pad;
            }
        }
        
        PartialSum sum = addLists(current1, current2);
        
        
        // If carry remains
        
        if(sum.carry != 0){
            ListNode result = new ListNode(sum.carry);
            
            result.next = sum.node;
            sum.node = result;
        }
        
        
        
        return sum.node;
        
    }
    
    
    public static PartialSum addLists(ListNode current1, ListNode current2){
        if(current1 == null && current2 == null){
            return new PartialSum();
        }
        
        
        
        PartialSum sum = addLists(current1.next, current2.next);
        
        int total = current1.val + current2.val + sum.carry;
        
        ListNode current = new ListNode(total % 10);
        current.next = sum.node;
        
        sum.node = current;
        sum.carry = total/10;
        
        return sum;
    }
    
    
    
    public static void main(String [] args){
        
        ListNode one = new ListNode(7);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(1);
        
        ListNode four = new ListNode(8);
        ListNode five = new ListNode(9);
        
        one.next = two;
        two.next = three;
        
        ListNode l1 = one;
        
        four.next = five;
        
        ListNode l2 = four;
        
        ListNode finalNode = sumLists(l1,l2);
        
        while(finalNode != null){
            System.out.print(finalNode.val);
            
            if(finalNode.next != null){
                System.out.print("->");
            }
            
            finalNode = finalNode.next;
        }
    }
}