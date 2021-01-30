// 10자리가 넘어가면 Long으로 확인해야함

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2529_부등호 {
	static int[] candidate;
	static boolean[] selected;
	static String maxv;
	static String minv;
	static String sign;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sign = br.readLine();
		candidate = new int[N + 1];
		selected = new boolean[10];

		maxv = Long.MIN_VALUE + "";
		minv = Long.MAX_VALUE + "";

		perm(0);

		System.out.println(maxv + "\n" + minv);
	}

	static void perm(int cnt) {
		if (cnt == N + 1) {
			if (!check())
				return;
			String v = number();
			if (Long.parseLong(maxv) < Long.parseLong(v)) {
				maxv = v;
			}
			if (Long.parseLong(minv) > Long.parseLong(v)) {
				minv = v;
			}
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			candidate[cnt] = i;
			perm(cnt + 1);
			selected[i] = false;
		}
	}

	static String number() {
		String res = "";
		for (int i = 0; i < candidate.length; i++) {
			res += candidate[i];
		}
		return res;
	}

	static boolean check() {
		for (int i = 0, j = 0; i < candidate.length - 1; i++, j += 2) {
			if (sign.charAt(j) == '<') {
				if (candidate[i] > candidate[i + 1])
					return false;
			} else {
				if (candidate[i] < candidate[i + 1])
					return false;
			}
		}
		return true;
	}
}
