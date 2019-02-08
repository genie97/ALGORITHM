## [c++] min/max 관련 함수

- **algorithm 헤더 파일에 있는 함수**

  - min(): 두 수 중 작은 값 반환
  - max(): 두 수 중 큰 값 반환

  ```c++
  #include<algorithm>
  using namespace std;
  min(2, 5); // 2 반환
  max(2, 5); // 5 반환
  ```

  - max_element(): 배열이나 벡터 등에서 최대값의 주소를 반환
  - min_element(): 배열이나 벡터 등에서 최소값의 주소를 반환

  ```c++
  #include<algorithm>
  using namespace std;
  int arr[3] = {12, 43, 32}
  *min_element(arr, arr+3); // 12 반환
  *max_element(arr, arr+3); // 43 반환
  ```


