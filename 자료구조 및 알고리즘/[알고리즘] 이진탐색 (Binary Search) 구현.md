## [알고리즘] 이진탐색 (Binary Search) 구현

1. 반복문을 이용한 구현 (Language: JAVA)

   ```java
   import java.util.Arrays;
   import java.util.Scanner;
   
   public class BinarySearch {
   		public static void main(String[] args) {
   		Scanner sc = new Scanner(System.in);
   		//1. 배열 크기 입력받기
   		int N = sc.nextInt();
   		//2. 배열 선언
   		int[] arr = new int[N];
   		for (int i = 0; i < arr.length; i++) {
   			arr[i] = sc.nextInt();
   		}
   		Arrays.sort(arr);
   		System.out.println(Arrays.toString(arr));
   		//3. 검색 키 받기
   		int search_key = sc.nextInt();
   		
   		//4. 이진검색하기
   		int start = 0; // 처음 인덱스
   		int end = arr.length-1; // 마지막 인덱스
   		int mid = 0; // 중간값 인덱스
   		
   		while(start<=end) {
   			mid = (start+end)/2;
   			if(search_key<=arr[mid]) {
   				end = mid-1;
   				if(search_key==arr[mid]) {
   					System.out.println(search_key + "을(를)" + mid + "번째 인덱스에서 검색 성공");
   					return;
   				}
   
   			}else if(search_key > arr[mid]) {
   				start = mid+1;
   			}
   		}
   		System.out.println(search_key + "을(를) 찾지 못하였습니다.");
       }
   }
   ```

   

2. 재귀함수를 이용한 구현

   // 추후 업데이트

   

3. Upper Bound (중복이 존재할 때, 인덱스의 상한선)

   ```java
   public class UpperBound_BinarySearch {
   	public static void main(String[] args) {
   		Scanner sc = new Scanner(System.in);
   		//1. 배열 크기 입력받기
   		int N = sc.nextInt();
   		//2. 배열 선언
   		int[] arr = new int[N];
   		for (int i = 0; i < arr.length; i++) {
   			arr[i] = sc.nextInt();
   		}
   		Arrays.sort(arr);
   		System.out.println(Arrays.toString(arr));
   		//3. 검색 키 받기
   		int search_key = sc.nextInt();
   		
   		//4. 이진검색하기
   		int start = 0; // 처음 인덱스
   		int end = arr.length-1; // 마지막 인덱스
   		int mid = 0; // 중간값 인덱스
   		
   		while(start<end) {
   			mid = (start + end) / 2;
   			if(search_key>=arr[mid]) {
   				start = mid + 1;
   				if(search_key == arr[mid]) {
   					System.out.println(search_key + "을(를)" + mid + "번째 인덱스에서 검색 성공");
   					return;
   				}
   
   			}else if(search_key < arr[mid]) {
   				end = mid;
   			}
   		}
   		System.out.println(search_key + "을(를) 찾지 못하였습니다.");
       }
   }
   ```

   

4. Lower Bound (중복이 존재할 때, 인덱스의 하한선)

   ```java
   public class UpperBound_BinarySearch {
   	public static void main(String[] args) {
   		Scanner sc = new Scanner(System.in);
   		//1. 배열 크기 입력받기
   		int N = sc.nextInt();
   		//2. 배열 선언
   		int[] arr = new int[N];
   		for (int i = 0; i < arr.length; i++) {
   			arr[i] = sc.nextInt();
   		}
   		Arrays.sort(arr);
   		System.out.println(Arrays.toString(arr));
   		//3. 검색 키 받기
   		int search_key = sc.nextInt();
   		
   		//4. 이진검색하기
   		int start = 0; // 처음 인덱스
   		int end = arr.length-1; // 마지막 인덱스
   		int mid = 0; // 중간값 인덱스
   		
   		while(start<end) {
   			mid = (start + end) / 2;
   			if(search_key <= arr[mid]) {
   				end = mid;
   				if(search_key == arr[mid]) {
   					System.out.println(search_key + "을(를)" + mid + "번째 인덱스에서 검색 성공");
   					return;
   				}
   
   			}else if(search_key > arr[mid]) {
   				start = mid+1;
   			}
   		}
   		System.out.println(search_key + "을(를) 찾지 못하였습니다.");
       }
   }
   ```

   