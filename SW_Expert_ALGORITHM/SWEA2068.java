import java.util.Arrays;
import java.util.Scanner;
public class SWEA2068{
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int TC = sc.nextInt();
	        for (int testCase = 1; testCase <= TC; testCase++) {
	            int max = 0;
	            for(int i=0;i<10;i++) {
	                int num = sc.nextInt();
	                if(num > max) {
	                    max = num;
	                }
	            }    
	            System.out.println("#" + testCase + " " + max);
	        } 
	    }
}
