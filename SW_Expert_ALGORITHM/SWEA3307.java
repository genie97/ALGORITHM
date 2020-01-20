import java.util.Scanner;
public class SWEA3307{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = sc.nextInt();
			int[] arr = new int[1001];
			for(int i=0;i<N;i++) {
				arr[i] = sc.nextInt();
			}
			int[] cnt = new int[1001];
			for(int i=0;i<N;i++) {
				cnt[i] = 1;
			}
			
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					if(arr[i]<arr[j] && cnt[i]>=cnt[j]) {
						cnt[j]= cnt[i] + 1;	
					}	
				}
			}
			int max =0;
			for(int i=0;i<N;i++) {
				if(max<cnt[i])
					max = cnt[i];
			}
			System.out.println("#" + testCase +" " + max);
		}
	}
}
