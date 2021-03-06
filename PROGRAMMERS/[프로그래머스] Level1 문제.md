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

```java
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> s = new Stack<>();
    
        for(int i = 0; i < moves.length; i++){
            int r = moves[i] - 1;
            for(int c = 0; c < board[r].length; c++){
                if(board[c][r] > 0) {
                    s.add(board[c][r]);
                    board[c][r] = 0;
                    break;
                }
            }
            if(s.size() >= 2){
                int a = s.pop();
                int b = s.pop();
                if(a == b) {
                    answer+=2;
                } else{
                    s.add(b);
                    s.add(a);
                }
            }
        }
        
        return answer;
    }
}
```



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



**[신규 아이디 추천](https://programmers.co.kr/learn/courses/30/lessons/72410)**

- 정규식으로 문자열 처리하는 기본문제

```java
class Solution {
    public String solution(String new_id) {
        // 1단계: 모든 대문자를 소문자로
        new_id = new_id.toLowerCase();
       
        // 2단계: 알파벳 소문자, 숫자, -, _, . 제외한 모든 문자 제거
        // [^문자] 문자를 제외한 나머지
        new_id = new_id.replaceAll("[^a-z0-9-_.]","");
     
        // 3단계: 마침표가 2번 이상 연속된 부분을 .로 치환
        // []+ 앞의 문자가 1번 이상
        new_id = new_id.replaceAll("[.]+",".");
        
        // 4단계: 처음 .이나 끝에 . 제거하기
        // ^[.] 시작 [.]$ 끝 or -> |
        new_id = new_id.replaceAll("^[.]|[.]$","");
       
        // 5단계: 빈 문자열이라면 a를 대입
        // 시작하는 문자와 끝나는 문자 사이가 모두 비었다 => ^$로 빈문자열 확인 가능
        new_id = new_id.replaceAll("^$","a");
        
        // 6단계: new_id의 길이가 16자 이상이라면 첫 15개를 제외한 나머지를 제거한다
        if(new_id.length() >= 16){
            new_id = new_id.substring(0,15);
            // 만약 제거 후 마침표 (.)가 끝이면 제거하기
            new_id = new_id.replaceAll("[.]$","");
        } 
            
        // 7단계: 아이디의 길이가 2자 이하라면 마지막 문자를 3이 될때까지 반복
        char ch = new_id.charAt(new_id.length()-1);
        while(new_id.length() < 3){
            new_id += ch;
        }
        return new_id;
    }
}
```



##### [모의고사](https://programmers.co.kr/learn/courses/30/lessons/42840)

```java
import java.util.*;

class Solution {
    static int[] A = {1, 2, 3, 4, 5};
    static int[] B = {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] C = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    public int[] solution(int[] answers) {
        
        int[] cnt = new int[3];
        
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == A[i%5])
                cnt[0]++;
        }
        
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == B[i%8])
                cnt[1]++;
        }
        
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == C[i%10])
                cnt[2]++;
        }
        
        int max = Math.max(cnt[0], Math.max(cnt[1], cnt[2]));
                
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < cnt.length; i++){
           if(cnt[i] == max){
               list.add(i+1);
           }
        }
        
        return list.stream().mapToInt(i -> i.intValue()).toArray();
    }
}
```



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



**[서울에서 김서방 찾기](https://programmers.co.kr/learn/courses/30/lessons/12919)**

```java
class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        for(int i = 0; i < seoul.length; i++){
            if(seoul[i].equals("Kim")){
                answer = "김서방은 " + i +"에 있다";
                break;
            }
        }
        return answer;
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

```java
class Solution {
    public long solution(long n) {
        long answer = 0;
        long sqrt_n = (long)(Math.sqrt(n));
        if(n != sqrt_n * sqrt_n)
            return -1;
        
        answer = (sqrt_n+1) * (sqrt_n+1);
        
        return answer;
    }
}
```



##### [제일 작은 수 제거하기](https://programmers.co.kr/learn/courses/30/lessons/12935)

```java
import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        if(arr.length == 1){
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[arr.length-1];
            int min = arr[0];
            for(int i = 0; i < arr.length; i++){
                min = Math.min(arr[i], min);
            }
            
            int idx = 0;
            for(int i = 0; i < arr.length; i++){
                if(arr[i] == min) continue;
                answer[idx++] = arr[i];
            }
        }
        return answer;
    }
}
```



##### [짝수와 홀수](https://programmers.co.kr/learn/courses/30/lessons/12937)

```java
class Solution {
    public String solution(int num) {
        if(num % 2 == 0)
            return "Even";
        else 
            return "Odd";
    }
}
```



##### [키패드 누르기](https://programmers.co.kr/learn/courses/30/lessons/67256)

```java
class Solution {
    static int[][] pad = {{3, 1}, {0, 0}, {0, 1}, {0, 2},
                          {1, 0}, {1, 1}, {1, 2}, 
                          {2, 0}, {2, 1}, {2, 2}};
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int rcx = 3, rcy = 0;
        int lcx = 3, lcy = 2;
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
                answer += "L";
                lcx = pad[numbers[i]][0];
                lcy = pad[numbers[i]][1];
            } else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                 answer += "R";
                 rcx = pad[numbers[i]][0];
                 rcy = pad[numbers[i]][1];
            } else {
                int ldiff = Math.abs(lcx - pad[numbers[i]][0]) +  Math.abs(lcy - pad[numbers[i]][1]);
                
                int rdiff = Math.abs(rcx - pad[numbers[i]][0]) +  Math.abs(rcy - pad[numbers[i]][1]);
                if(ldiff <= rdiff){
                    if(ldiff == rdiff){
                        if(hand.equals("right")){
                            answer += "R";
                            rcx = pad[numbers[i]][0];
                            rcy = pad[numbers[i]][1];
                        } else{
                            answer += "L";
                            lcx = pad[numbers[i]][0];
                            lcy = pad[numbers[i]][1];
                        }
                    } else {
                        answer += "L";
                        lcx = pad[numbers[i]][0];
                        lcy = pad[numbers[i]][1];
                    }
                } else{
                    answer += "R";
                    rcx = pad[numbers[i]][0];
                    rcy = pad[numbers[i]][1];
                }
            }
        }
        return answer;
    }
}
```



##### [최대공약수와 최소공배수](https://programmers.co.kr/learn/courses/30/lessons/12940)

```java
class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        answer[0] = gcd(n, m);
        answer[1] = answer[0] * (n/answer[0]) * (m/answer[0]);
        
        return answer;
    }
    static int gcd(int n, int m){
        if(m == 0) 
            return n;
        else 
            return gcd(m, n%m);
    }    
}
```



##### [콜라츠 추측](https://programmers.co.kr/learn/courses/30/lessons/12943)

```java
class Solution {
    public int solution(int num) {
        int answer = 0;
        long n = (long) num;
        
        while(answer++ < 500){
            if(n == 1) 
                break;
            
            if(n % 2 == 0){
                n /= 2;
            } else {
                n *= 3;
                n += 1;
            }
        }
        
        if(n != 1)
            return -1;
        else 
            return answer-1;
    }
}
```



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

```java
class Solution {
    public boolean solution(int x) {
        String s = x + "";
        int sum = 0;
        for(int i = 0; i < s.length(); i++){
            sum+= s.charAt(i)-'0';
        }
        if(x % sum == 0) return true;
        else return false;
    }
}
```



##### [핸드폰 번호 가리기](https://programmers.co.kr/learn/courses/30/lessons/12948)

```java
class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int len = phone_number.length();
        for(int i = 0; i < len - 4; i++){
            answer += "*";
        }
        for(int i = len-4; i < len; i++){
            answer += phone_number.charAt(i);
        }
        
        return answer;
    }
}
```



##### [행렬의 덧셈](https://programmers.co.kr/learn/courses/30/lessons/12950)

```java
class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr1[0].length];
        
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr1[i].length; j++){
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        return answer;
    }
}
```



##### [x만큼 간격이 있는 n개의 숫자](https://programmers.co.kr/learn/courses/30/lessons/12954)

```java
class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        answer[0] = x;
        for(int i = 1; i < n; i++){
            answer[i] = answer[i-1] + x;
        }
        return answer;
    }
}
```



##### [직사각형 별찍기](https://programmers.co.kr/learn/courses/30/lessons/12969)

```java
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < b; i++){
            for(int j = 0; j < a; j++){
                sb.append("*");
            }
            sb.append('\n');
        }
        
        System.out.println(sb.toString());
    }
}
```



##### [예산](https://programmers.co.kr/learn/courses/30/lessons/12982)

```java
import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        int cnt = 0; 
        int sum = 0;
        for(int i = 0; i < d.length; i++){
            if(sum + d[i] <= budget){
                cnt++;
                sum+=d[i];
            } else
                break;
        }
        return cnt;
    }
}
```



##### [[1차\] 비밀지도](https://programmers.co.kr/learn/courses/30/lessons/17681)

```java
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] |= arr2[i];
        }
        for(int i = 0; i < arr1.length; i++){
            answer[i] = toBinary(arr1[i], n);
        }
        return answer;
    }
    static String toBinary(int num, int n){
        String result = "";
        while(num > 0){
            int r = num % 2;
            if(r == 0)
                result = " " + result;
            else
                result = "#" + result;
            num /= 2;
        }
        while(result.length() < n){
            result = " " + result;
        }
        return result;
    }
}
```



##### [실패율](https://programmers.co.kr/learn/courses/30/lessons/42889)

```java
import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        ArrayList<double[]> list = new ArrayList<>();
        
        for(int i = 1; i <= N; i++){
            int total = 0;
            int failed = 0;
            
            for(int j = 0; j < stages.length; j++){
                if(stages[j] == i)
                    failed++;
                if(stages[j] >= i)
                    total++;
            }        
            if(total == 0){
                list.add(new double[]{i, 0.0});
            } else {
                list.add(new double[]{i, (double)(failed)/total});
            } 
        }
        
        Collections.sort(list, new Comparator<>(){
            public int compare(double[] a, double[] b){
                return Double.compare(b[1], a[1]);
            }
        });
        
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i)[1]);
            answer[i] = (int)(list.get(i)[0]);
        }
        return answer;
    }
}
```



##### [[1차\] 다트 게임](https://programmers.co.kr/learn/courses/30/lessons/17682)

```java
import java.util.*;

public class test1 {
	public static void main(String[] args) {
		String s = "1D#2S*3S";

		System.out.println(solution(s));
	}

	public static int solution(String dartResult) {
		int answer = 0;
		int cur = 0;
		int prev = Integer.MIN_VALUE;

		for (int i = 0; i < dartResult.length(); i++) {
			char ch = dartResult.charAt(i);
			if (Character.isDigit(ch)) {
				if (ch == '1') {
					if (i + 1 < dartResult.length() && dartResult.charAt(i + 1) == '0') {
						i++;
						cur = 10;
					} else {
						cur = 1;
					}
				} else {
					cur = ch - '0';
				}
			} else if (Character.isAlphabetic(ch)) {
				if (ch == 'D') {
					cur = (cur * cur);
				} else if (ch == 'T') {
					cur = (cur * cur * cur);
				}

				char nch = '.';
				if (i + 1 < dartResult.length()) {
					nch = dartResult.charAt(i + 1);
				}

				if (nch == '#' || nch == '*') {
					if (nch == '#') {
						cur *= -1;
					} else {
						if (prev == Integer.MIN_VALUE) {
							cur *= 2;
						} else {
							answer -= prev;
							prev *= 2;
							answer += prev;
							cur *= 2;
						}
					}
				}
				answer += cur;
				prev = cur;

			}
		}

		return answer;
	}
}
```



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

