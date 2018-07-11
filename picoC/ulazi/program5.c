#include <stdio.h>

int main()
{
    int a = 10, b = 10, c = 3;
    b = a % c;
    printf("%d %d %d\n", a, b, c);
    printf("%d\n", (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3)))))));
    return 0;
}

/* Dobar izlaz je 10 1 3. 
    6 */