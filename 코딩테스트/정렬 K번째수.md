## 정렬: K번째수

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

------

