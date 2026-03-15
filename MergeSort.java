import java.util.*;


public class Solution {
    public int[] sortArray(int[] nums) {
        mergesort(nums, 0, nums.length-1);
        return nums;

    }

    private void mergesort(int[] nums,int left,int right){

        if(left >= right) return;

        int midpoint = left + (right-left)/2;

        mergesort(nums,left,midpoint);
        mergesort(nums,midpoint+1,right);

        merge(nums, left,midpoint,right);
    }

    private void merge(int[] nums, int left, int midpoint, int right){

        int[] tempArr1 = Arrays.copyOfRange(nums, left, midpoint+1);
        int[] tempArr2 = Arrays.copyOfRange(nums, midpoint+1, right+1);

        int i = 0;
        int j = 0;
        int k = left;

        while(i < tempArr1.length && j < tempArr2.length){
            if(tempArr1[i] <= tempArr2[j]){
                nums[k] = tempArr1[i];
                k++;
                i++;
            }else{
                nums[k] = tempArr2[j];
                k++;
                j++;
            }
        }

        while(i < tempArr1.length){
            nums[k] = tempArr1[i];
            k++;
            i++;

        }

        while(j < tempArr2.length){
            nums[k] = tempArr2[j];
            k++;
            j++;
        }
    }


     public void main(String[] args){
        int[] nums = {1,34,5,6,3,53,64,4,6,4,32};


        System.out.println(Arrays.toString(sortArray(nums)));
    }
   
}