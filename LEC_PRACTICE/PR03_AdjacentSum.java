public class PR03_AdjacentSum {
	static int sum=0;
	static int[]dx = {0,0,1,-1};
	static int[]dy = {1,-1,0,0};
	public static void main(String[] args) {
		int[][] arr = new int [5][5]; // 2차원 배열의 선언
		int num = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = num; //i*5+j로 표현할 수 있음
				num++;
			}
		}	
	
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[x].length; y++) {
				abs_sum(arr, x, y);	
				//System.out.print(sum+" ");
			}	
			//System.out.println();
		}
		System.out.println("total sum is " + sum);
	}
	private static void abs_sum(int[][] arr, int x, int y) {
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= arr.length || ny < 0 || ny >= 5) continue;
			sum += Math.abs(arr[x][y]-arr[nx][ny]);		
		}
	}
	
}
