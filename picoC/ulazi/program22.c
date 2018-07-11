#include <stdio.h>

int main()
{
    int *b = 1000;
    int *a = 2000;
    a += b;

    printf("%ld\n", a);

    return 0;
}

/* Izlaz je:
    Error: Wrong type of operands
 */

