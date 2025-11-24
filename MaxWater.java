import java.util.*;

public class MaxArea{
    public static int maxArea(int [] height){
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while(left < right){
            //Current width
            int width = right - left;

            //Height is limmited by the shorter line
            int currentHeight = Math.min(height[left],height[right]);

            //Area = Width * Height
            maxWater = Math.max(maxWater, width * currentHeight);

            // Move pointer with the shorter side 
            if(height[left] < height[right]){
                left ++;
            } else{
                right --;
            }

            
        }
        return maxWater;
    }

    public static void main(String [] args){
        int [] height = {1,8,6,2,5,4,8,3,7};

        System.out.println("The maximum area is : "+ maxArea(height));
    }
}