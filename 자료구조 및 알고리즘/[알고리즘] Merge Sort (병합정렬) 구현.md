## [알고리즘] Merge Sort (병합정렬) 구현

>  여러 개의 정렬된 자료의 집합을 병합하여 한 개의 정렬된 집합으로 만드는 방식

- 분할 정복 알고리즘 활용

  - 자료를 최소 단위의 문제까지 나눈 후에 차례대로 정렬하여 최종 결과를 얻음
  - top - down 방식

- 시간 복잡도 

  O[NlogN]

  

- 구현

  ```java
  import java.util.ArrayList;
  
  public class MergeSort {
  
  	public static void main(String[] args) {
  		int[] num = { 69, 10, 30, 2, 16, 8, 31, 22 };
  		ArrayList<Integer> list = new ArrayList<>();
  		for (int i = 0; i < num.length; i++) {
  			list.add(num[i]);
  		}
  
  		System.out.println(mergeSort(list));
  	}
  
  	public static ArrayList<Integer> mergeSort(ArrayList<Integer> arr) {
  		if (arr.size() == 1) {
  			return arr; // 개수가 하나 남았을 때 리턴
  		}
  		ArrayList<Integer> left = new ArrayList<>();
  		ArrayList<Integer> right = new ArrayList<>();
  
  		int mid = arr.size() / 2;
  
  		for (int i = 0; i < mid; i++) {
  			left.add(arr.get(i));
  		}
  		for (int i = mid; i < arr.size(); i++) {
  			right.add(arr.get(i));
  		}
  		left = mergeSort(left);
  		right = mergeSort(right);
  
  		return merge(left, right);
  	}
  
  	private static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
  		ArrayList<Integer> result = new ArrayList<>();
  
  		while (left.size() > 0 || right.size() > 0) {
  			if (left.size() > 0 && right.size() > 0) {
  				if (left.get(0) <= right.get(0)) {
  					result.add(left.get(0));
  					left.remove(0);
  				} else {
  					result.add(right.get(0));
  					right.remove(0);
  				}
  			} else if (left.size() > 0) {
  				result.add(left.get(0));
  				left.remove(0);
  			} else if (right.size() > 0) {
  				result.add(right.get(0));
  				right.remove(0);
  			}
  		}
  		return result;
  	}
  }
  
  ```

  
