## [알고리즘] Union-Find (합집합 찾기)

**: Disjoint-Set Algorithm (서로소 집합 알고리즘)**

- Array에 부모 노드의 번호를 저장한다
- 연결되어 있음은 **재귀함수**를 통해 처리한다

------

**[1] getParent  함수**

: 부모 노드의 번호를 찾는 함수

```c++
//int getParent(int node)
if(parent[x]==x)
    return x;
return parent[x] = getParent(parent[x]);
```



**[2] unionParent 함수**

: 부모노드의 번호 중 가장 root인 부모를 찾는 함수

```c++
//void unionParent(int a, int b)
a = getParent(a);
b = getParent(b);
if(a<b)
    parent[b] = a;
else
    parent[a] = b;
```



**[2] findParent함수**

: 같은 부모를 가지고 있는지 확인하는 함수

```c++
//int findParent(int a, int b)
a = getParent(a);
b = getParent(b);
if(a==b)
    return 1;
else
    return 0;
```

