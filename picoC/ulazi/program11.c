#include <stdio.h>

int fib(int n)
{
    if (!(n >= 2))
        return n;
    int a, b;
    a = fib(n-1);
    b = fib(n-2);
    printf("%d %d ", a, b);
    return a + b;
}

int main()
{
    int a, b, c, d, e;
    a = 10;
    b = 20;
    c = 30;
    d = 40;
    
    e = b >= a && !(b >= c) ? b : 0;
    
    printf("%d\n", e);
    printf("\n%d\n", fib(7));

    return 0;
}

/*  Izlaz:
20
1 0 1 1 1 0 2 1 1 0 1 1 3 2 1 0 1 1 1 0 2 1 5 3 1 0 1 1 1 0 2 1 1 0 1 1 3 2 8 5 
13
    
*/
