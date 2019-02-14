dp0 = [1, 0, 1]
dp1 = [0, 1, 1]

T = int(input())

def fibo(n):
    dplen = len(dp0)
    if dplen <= n:
        for i in range(dplen, n + 1):
            dp0.append(dp0[i - 1] + dp0[i - 2])
            dp1.append(dp1[i - 1] + dp1[i - 2])
    print("%d %d" % (dp0[n], dp1[n]))

for i in range(T):
    N = int(input())
    fibo(N)