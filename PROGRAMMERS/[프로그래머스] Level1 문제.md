## [프로그래머스] Level1 문제 풀이

##### [두 개 뽑아서 더하기](https://programmers.co.kr/learn/courses/30/lessons/68644)

```java
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int[] solution(int[] numbers) {
        		int[] answer = {};
		Set<Integer> sum = new TreeSet<>();

		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				sum.add(numbers[i] + numbers[j]);
			}
		}
		answer = new int[sum.size()];
		ArrayList<Integer> tmp = new ArrayList<>(sum);
		for (int i = 0; i < tmp.size(); i++) {
			answer[i] = tmp.get(i);
		}
		return answer;
    }
}
```



##### [크레인 인형뽑기 게임](https://programmers.co.kr/learn/courses/30/lessons/64061)



##### [완주하지 못한 선수](https://programmers.co.kr/learn/courses/30/lessons/42576)

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
     String answer = "";
		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < completion.length; i++) {
			if (!map.containsKey(completion[i])) {
				map.put(completion[i], 1);
			} else {
				map.put(completion[i], map.get(completion[i]) + 1);
			}
		}
		for (int i = 0; i < participant.length; i++) {
			if (map.containsKey(participant[i])) {
				map.put(participant[i], map.get(participant[i]) - 1);
			} else {
				return participant[i];
			}
		}
		for (String key : map.keySet()) {
			int cnt = map.get(key);
			if (cnt < 0)
				return key;
		}
		return answer;
    }
}
```



##### [모의고사](https://programmers.co.kr/learn/courses/30/lessons/42840)



##### [K번째수](https://programmers.co.kr/learn/courses/30/lessons/42748)

```c++
#include <string>
#include <vector>
#include<algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> temp;
    vector<int> answer;
    for(int com_size= 0;com_size<commands.size();com_size++){
        int i = commands[com_size][0];
        int j = commands[com_size][1];
        int k = commands[com_size][2];
        for(int loc = i-1;loc<j;loc++){
            temp.push_back(array[loc]);
        }
        sort(temp.begin(), temp.end());
        answer.push_back(temp[k-1]);
        temp.clear();
    }
    return answer;
}
```



##### [체육복](https://programmers.co.kr/learn/courses/30/lessons/42862)

- 비교를 통해서 확인하는 방법

```java
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        int[] people = new int[n+2];
        
        for(int i = 0; i < reserve.length; i++){
            people[reserve[i]] = 2; 
        }
        
        for(int i = 0; i < lost.length; i++){
            people[lost[i]]--; 
        }
        
        for(int i = 1; i <= n; i++){
            if(people[i]==-1){
                if(people[i-1]-1 > 0){
                    people[i-1]--;
                    people[i] = 1;
                    continue;
                }
                if(people[i+1]-1 > 0){
                    people[i+1]--;
                    people[i] = 1;
                    continue;
                }
            }
        }
        
        for(int i = 1; i <= n; i++){
            if(people[i] == -1)
                answer--;
        }
        return answer;
    }
}
```

```java
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        Map<Integer, Boolean> clothes = new TreeMap<>();
        Set<Integer> lost_p = new TreeSet<>();
        
        // 잃어버린 사람들 번호
        for(int i = 0; i < lost.length; i++){
            lost_p.add(lost[i]);
        }
        
        // 잃어버린 사람들이 여벌 옷을 가져온 목록에도 있으면 제거
        for(int i = 0; i < reserve.length; i++){
            if(lost_p.contains(reserve[i])){
                lost_p.remove(reserve[i]);
                continue;
            }
            clothes.put(reserve[i], false);
        }
        
        int lost_cnt = lost_p.size();
        
        ArrayList<Integer> lost_list = new ArrayList<>(lost_p);
        
        for(int i = 0; i < lost_list.size(); i++){
            int forward = lost_list.get(i)-1;
            int backward = lost_list.get(i)+1;
            if(clothes.containsKey(forward) && !clothes.get(forward)){
                clothes.put(forward, true);
                lost_cnt--;
                continue;
            }
            if(clothes.containsKey(backward) && !clothes.get(backward)){
                clothes.put(backward, true);
                lost_cnt--;
                continue;
            }
        }
        answer = n - lost_cnt;
        return answer;
    }
}
```



##### [2016년](https://programmers.co.kr/learn/courses/30/lessons/12901)

```java
class Solution {
    static int[] month_day = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] month = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
    
    public String solution(int a, int b) {
        int day = 0;
        
        for(int i = 1; i < a; i++){
            day += month_day[i-1];
        }
        day += b;
        
        return month[(day-1)%7];
    }
}
```



##### [가운데 글자 가져오기](https://programmers.co.kr/learn/courses/30/lessons/12903)

```java
class Solution {
    public String solution(String s) {
        String answer = "";
		if (s.length() % 2 == 1) {
			answer = s.charAt(s.length() / 2) + "";
		} else {
			answer = s.substring(s.length() / 2 - 1, s.length() / 2 + 1);
		}
		return answer;
    }
}
```



##### [3진법 뒤집기](https://programmers.co.kr/learn/courses/30/lessons/68935)

```java
import java.util.*;

class Solution {
    public int solution(int n) {
        String s = "";
        
        while(n > 0){
            int r = n % 3;
            s += r;
            n /= 3;
        }
    
        int answer = 0;
        
        for(int idx = s.length() - 1, e = 0; idx >= 0; idx--, e++){
            int num = s.charAt(idx) - '0';
            
            answer += num * Math.pow(3, e);
        }
        
        return answer;
    }
}
```



##### [같은 숫자는 싫어](https://programmers.co.kr/learn/courses/30/lessons/12906)

```java
import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
		ArrayList<Integer> tmp = new ArrayList<>();
		int cur = arr[0];
		tmp.add(cur);
		for (int i = 1; i < arr.length; i++) {
			if (cur == arr[i])
				continue;
			tmp.add(arr[i]);
			cur = arr[i];
		}
		answer = new int[tmp.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = tmp.get(i);
		}
		return answer;
    }
}
```



##### [나누어 떨어지는 숫자 배열](https://programmers.co.kr/learn/courses/30/lessons/12910)

```java
import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % divisor == 0)
                list.add(arr[i]);
        }
        
        if(list.size() == 0){
            answer = new int[1];
            answer[0] = -1;
            return answe자r;
        }
        
        answer = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}
```



##### [두 정수 사이의 합](https://programmers.co.kr/learn/courses/30/lessons/12912)

```java
class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        if(a > b){
            int tmp = a;
            a = b;
            b = tmp;
        }
        
        if(a == b) 
            return a;
        
        for(int i = a; i <= b; i++){
            answer += i;
        }
        
        return answer;
    }
}
```



##### [문자열 내 마음대로 정렬하기](https://programmers.co.kr/learn/courses/30/lessons/12915)

```java
import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {  
        Arrays.sort(strings, new Comparator<>(){
            public int compare(String a, String b){
                char an = a.charAt(n);
                char bn = b.charAt(n);
                if(an == bn) 
                    return a.compareTo(b);
                return Integer.compare((int)an, (int)bn);
            }
        });
        
        return strings;
    }
}
```



##### [문자열 내 p와 y의 개수](https://programmers.co.kr/learn/courses/30/lessons/12916)

```java
class Solution {
    boolean solution(String s) {        
        int yCnt = 0;
        int pCnt = 0;
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == 'p' || ch == 'P')
                pCnt++;
            if(ch == 'y' || ch == 'Y')
                yCnt++;
        }
        if(yCnt == pCnt)
            return true;
        else
            return false;
    }
}
```



##### [문자열 내림차순으로 배치하기](https://programmers.co.kr/learn/courses/30/lessons/12917)

```java
import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        char[] strArr = s.toCharArray();
        Arrays.sort(strArr);
    
        for(int i = strArr.length-1; i >= 0; i--){
            answer += strArr[i];
        }
        
        return answer;
    }
}
```



##### [문자열 다루기 기본](https://programmers.co.kr/learn/courses/30/lessons/12918)

```java
class Solution {
    public boolean solution(String s) {
        if(s.length() != 4 && s.length() != 6)
            return false;
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isAlphabetic(ch)){
                return false;
            }
        }
        
        return true;
    }
}
```



##### [소수 찾기](https://programmers.co.kr/learn/courses/30/lessons/12921)

```java
import java.util.*;

class Solution {
    public int solution(int n) {
        int[] isPrime = new int[n+1];
        Arrays.fill(isPrime, 1);
        
        isPrime[1] = 0;
        
        for(int i = 2; i <= n; i++){
            if(isPrime[i] == 0) continue;
            for(int j = i * 2; j <= n; j=j+i){
                isPrime[j] = 0;
            }
        }
        
        int answer = 0;
        
        for(int i = 1; i <=n; i++){
            if(isPrime[i] == 1)
                answer++;
        }
        
        return answer;
    }
}
```



##### [수박수박수박수박수박수?](https://programmers.co.kr/learn/courses/30/lessons/12922)

```java
class Solution {
    public String solution(int n) {
        String answer = "";
        for(int i = 0; i < n; i++){
            if(i%2 ==0)
                answer += "수";
            else
                answer += "박";
        }
        return answer;
    }
}
```



##### [문자열을 정수로 바꾸기](https://programmers.co.kr/learn/courses/30/lessons/12925)

```java
class Solution {
    public int solution(String s) {
        int answer = Integer.parseInt(s);
        return answer;
        
    }
}
```



##### [시저 암호](https://programmers.co.kr/learn/courses/30/lessons/12926)

```java
class Solution {
    
    public String solution(String s, int n) {
        String answer = "";
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
                        
            if('A' <= ch && ch <= 'Z'){
                ch = (char)((ch - 'A' + n) % 26 + 'A');
            } else if('a' <= ch && ch <= 'z') {
                ch = (char)((ch - 'a' + n) % 26 + 'a');
            } 
            answer += ch;
        }
        return answer;
    }
}
```



##### [내적](https://programmers.co.kr/learn/courses/30/lessons/70128)

```java
class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        for(int i = 0; i < a.length; i++){
            answer += (a[i] * b[i]);
        }
        return answer;
    }
}
```



##### [약수의 합](https://programmers.co.kr/learn/courses/30/lessons/12928)

```java
class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // 0의 약수는 없다
        if(n == 0)
            return 0;
        
        for(int i = 1; i <= (int)(Math.sqrt(n)); i++){
            if(n % i == 0){
                answer += i;  
                if(i != (n/i)){
                    answer += (n/i);    
                } 
            }
        }
        return answer;
    }
}
```



##### [이상한 문자 만들기](https://programmers.co.kr/learn/courses/30/lessons/12930)

```java
class Solution {
    public String solution(String s) {
        String answer = "";
        char[] charArr = s.toCharArray();
        int point = 0;
        for(int i = 0 ; i < charArr.length; i++){
            if(charArr[i] == ' '){
                answer += " ";
                point = 0;
                continue;
            }
            if(point % 2 == 0)
                answer += Character.toUpperCase(charArr[i]);
            else
                answer += Character.toLowerCase(charArr[i]);
            point++;
        }
        return answer;
    }
}
```



##### [자릿수 더하기](https://programmers.co.kr/learn/courses/30/lessons/12931)

```java
import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String s = n + "";
        for(int i = 0; i < s.length(); i++){
            answer+= (s.charAt(i) - '0'); 
        }
        return answer;
    }
}
```



##### [자연수 뒤집어 배열로 만들기](https://programmers.co.kr/learn/courses/30/lessons/12932)

```java
class Solution {
    public int[] solution(long n) {
        int[] answer = {};
        StringBuilder sb = new StringBuilder((n+""));
        String s = sb.reverse().toString();
        
        answer = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            answer[i] = s.charAt(i)-'0';
        }
        
        return answer;
    }
}
```



##### [정수 내림차순으로 배치하기](https://programmers.co.kr/learn/courses/30/lessons/12933)

```java
import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        char[] arr = (n+"").toCharArray();
        
        Arrays.sort(arr);
        String s = "";
        for(int i = arr.length-1; i >=0; i--){
            s += arr[i];
        }
        answer = Long.parseLong(s);
        return answer;
    }
}
```



##### [정수 제곱근 판별](https://programmers.co.kr/learn/courses/30/lessons/12934)



##### [제일 작은 수 제거하기](https://programmers.co.kr/learn/courses/30/lessons/12935)



##### [짝수와 홀수](https://programmers.co.kr/learn/courses/30/lessons/12937)



##### [키패드 누르기](https://programmers.co.kr/learn/courses/30/lessons/67256)



##### [최대공약수와 최소공배수](https://programmers.co.kr/learn/courses/30/lessons/12940)



##### [콜라츠 추측](https://programmers.co.kr/learn/courses/30/lessons/12943)



##### [평균 구하기](https://programmers.co.kr/learn/courses/30/lessons/12944)

```java
class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        for(int i = 0; i < arr.length; i++) {
            answer += arr[i];
        }
        answer /= arr.length;
        return answer;
    }
}
```



##### [하샤드 수](https://programmers.co.kr/learn/courses/30/lessons/12947)



##### [핸드폰 번호 가리기](https://programmers.co.kr/learn/courses/30/lessons/12948)



##### [행렬의 덧셈](https://programmers.co.kr/learn/courses/30/lessons/12950)



##### [x만큼 간격이 있는 n개의 숫자](https://programmers.co.kr/learn/courses/30/lessons/12954)



##### [직사각형 별찍기](https://programmers.co.kr/learn/courses/30/lessons/12969)



##### [예산](https://programmers.co.kr/learn/courses/30/lessons/12982)



##### [[1차\] 비밀지도](https://programmers.co.kr/learn/courses/30/lessons/17681)



##### [실패율](https://programmers.co.kr/learn/courses/30/lessons/42889)



##### [[1차\] 다트 게임](https://programmers.co.kr/learn/courses/30/lessons/17682)



<hr>

### SQL 문제

##### [아픈 동물 찾기](https://programmers.co.kr/learn/courses/30/lessons/59036)

```sql
-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION like 'Sick'
```

##### [역순 정렬하기](https://programmers.co.kr/learn/courses/30/lessons/59035)

```sql
-- 코드를 입력하세요
SELECT NAME, DATETIME 
FROM ANIMAL_INS
ORDER BY ANIMAL_ID DESC;
```

##### [모든 레코드 조회하기](https://programmers.co.kr/learn/courses/30/lessons/59034)

```sql
-- 코드를 입력하세요
SELECT*FROM ANIMAL_INS
```



##### [최댓값 구하기](https://programmers.co.kr/learn/courses/30/lessons/59415)

```sql
-- 코드를 입력하세요
SELECT MAX(DATETIME) AS 시간 
FROM ANIMAL_INS
```



##### [어린 동물 찾기](https://programmers.co.kr/learn/courses/30/lessons/59037)

```sql
-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION != 'Aged';
```



##### [동물의 아이디와 이름](https://programmers.co.kr/learn/courses/30/lessons/59403)

```sql
-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;
```



##### [이름이 없는 동물의 아이디](https://programmers.co.kr/learn/courses/30/lessons/59039)

```sql
-- 코드를 입력하세요
SELECT ANIMAL_ID 
FROM ANIMAL_INS
WHERE NAME IS NULL
```



##### [여러 기준으로 정렬하기](https://programmers.co.kr/learn/courses/30/lessons/59404)

```sql
-- 코드를 입력하세요--
SELECT ANIMAL_ID, NAME, DATETIME
FROM ANIMAL_INS
ORDER BY NAME ASC, DATETIME DESC;
```



##### [상위 n개 레코드](https://programmers.co.kr/learn/courses/30/lessons/59405)

```sql
-- 코드를 입력하세요
SELECT NAME
FROM ANIMAL_INS
ORDER BY DATETIME
LIMIT 1;
```



##### [이름이 있는 동물의 아이디](https://programmers.co.kr/learn/courses/30/lessons/59407)

```sql
-- 코드를 입력하세요
SELECT ANIMAL_ID 
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
```

