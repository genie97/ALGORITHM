import java.util.Scanner;

public class SWEA1231_D4_중위순회 {
	public static String[] tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i <= 10; i++) {
			int N = Integer.parseInt(sc.nextLine());
			tree = new String[N + 1];
			for (int j = 0; j < N; j++) {
				String[] line = sc.nextLine().split(" ");
				int idx = Integer.parseInt(line[0]);
				String root = line[1];
				tree[idx] = root;
			}
			System.out.print("#" + i + " ");
			inorder(1);
			System.out.println();
		}
	}

	public static void inorder(int idx) {
		if (idx >= tree.length || tree[idx] == null) {
			return;
		}
		// 왼쪽
		inorder(idx * 2);
		// 부모
		System.out.print(tree[idx]);
		// 오른쪽
		inorder(idx * 2 + 1);
	}
}
