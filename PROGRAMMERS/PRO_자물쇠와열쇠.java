
public class 자물쇠와열쇠 {
	public static void main(String[] args) {
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		System.out.println(solution(key, lock));
	}

	static boolean solution(int[][] key, int[][] lock) {
		int lockSize = lock.length;
		int s = key.length - 1;
		int e = s + lockSize;
		int bigSize = lockSize + (2 * s); // 한칸 겹쳤을 때 큰 사각형

		for (int i = 0; i < 4; i++) { // 회전 4번 (360도)
			for (int j = 0; j < e; j++) {
				for (int k = 0; k < e; k++) {
					if (isUnlock(lock, key, bigSize, j, k, s, e)) {
						return true;
					}
				}
			}
			key = rotateKey(key);
		}
		return false;
	}

	static int[][] rotateKey(int[][] key) {
		int size = key.length;
		int ni = size - 1;
		int[][] temp = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				temp[j][ni] = key[i][j];
			}
			ni -= 1;
		}
		return temp;
	}

	static boolean isUnlock(int[][] lock, int[][] key, int bigSize, int x, int y, int s, int e) {
		int[][] bigSquare = new int[bigSize][bigSize];

		 
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				bigSquare[x + i][y + j] += key[i][j];
			}
		}
		
		for (int i = s; i < e; i++) {
			for (int j = s; j < e; j++) {
				bigSquare[i][j] += lock[i-s][j-s];
				if(bigSquare[i][j] != 1) return false;
			}
		}
		return true;
	}
}
