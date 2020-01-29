
public class Main_570 {
	public static void main(String[] args) {
		int [][] map = new int[5][5];
		for (int i = 0; i < map[0].length; i++) {
			map[0][i] = 1;
		}
		for (int i = 0; i < map.length; i++) {
			map[i][0] = 1;
		}
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				map[i][j] = map[i-1][j] + map[i][j-1];
			}
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
