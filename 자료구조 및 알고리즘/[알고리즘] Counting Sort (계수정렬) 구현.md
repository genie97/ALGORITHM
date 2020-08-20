## [알고리즘] Counting Sort (계수정렬) 구현

#### 1. Counting Sort

- Time Complexity: O(n)
- 일반적 상황에서 가장 빠른 정렬 알고리즘:  Quick Sort
  - Time Complexity: O(nlogn)
- 모든 데이터가 양의 정수일 때, 데이터의 개수가 N, 데이터 중 최댓값이 k이면 O(N+K)를 보장
- 가장 큰 데이터와 가장 작은 데이터의 차이가 너무 크다면 계수정렬을 사용하기 어려움

<hr>

#### 구현 [문제적용] 

- [백준 수정렬하기3](https://www.acmicpc.net/problem/10989)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10989_수정렬하기3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[10001];

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[num]++;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == 0)
				continue;
			for (int j = 0; j < arr[i]; j++) {
				sb.append(i).append('\n');
			}
		}
		
		System.out.println(sb);
	}
}
```



