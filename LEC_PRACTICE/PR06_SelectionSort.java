import java.util.Arrays;

public class PR06_SelectionSort {
	public static int[] arr;

	public static void main(String[] args) {
		arr = new int[]{64, 25, 10, 22, 11};
		SelectionSort(0);
		System.out.println(Arrays.toString(arr));
	}
	public static void SelectionSort(int idx) {
		if(idx == arr.length) {
			return;
		}
		else {
			int min = arr[idx];
			int minIdx = idx;
			for (int i = idx + 1; i < arr.length; i++) {
				if(min > arr[i]) {
					min = arr[i];
					minIdx = i;
				}
			}
			int tmp = arr[idx];
			arr[idx] = min;
			arr[minIdx] = tmp;
			SelectionSort(idx+1);
		}
	}
}
