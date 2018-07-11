#include <stdio.h>

char f(char a, char b, int c, char d, int e, int f)
{
    return a+b+c+d+e+f;
}

int main()
{
    char a, b, c, d;
    a = 10;
    b = 20;
    c = 30;
    d = 40;

    char e = f(a && b || !c, b >= a && d >= c, c >= b && c <= d, 0, 100, 5 && 0);
    
    printf("%d\n", e);

    return 0;
}

/*  Izlaz je:
    103
*/

